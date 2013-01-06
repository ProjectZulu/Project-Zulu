package projectzulu.common.mobs;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import projectzulu.common.mod_ProjectZulu;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemBlockList;
import projectzulu.common.core.ProjectZuluLog;
import cpw.mods.fml.common.Loader;

public class EntityZulu extends EntityHerd implements IAnimals {	
	/* Uncategorized Variables */
	public final static float attackAnimTime = 20;
	int distanceToCircle = 3;
	/* Entity Target Variables */
	protected Entity targetedEntity;
	protected float distToTarget;
	protected float distToTargetXZ;

	/* Debug Variables */
	public int counter = 0;

	/*Charge Ability Variables*/
	protected boolean isCharging = false;
	protected float timeSinceLastCharge = 5f;
	protected float chargeTriggerThreshold = 10*20f;
	protected float chargeTime = 0.2f*chargeTriggerThreshold;
	protected int chargeSpeedModifier = 1;

	/* Entity State Variables */
	protected int loveTimer = 0;
	protected int loveCooldown = 0;
	protected int angerLevel = 0;
	protected int entityState = 0;
	//	public int getState(){
	//		return entityState;
	//	}
	public enum listStates {
		idle(0), 
		looking(1),
		attacking(2),
		attackAnim(3),
		attackAnim2(4),
		posture(5),
		following(6);

		public final int index;
		listStates(int index) {
			this.index = index;
		}
		public int index() { 
			return index; 
		}
	}

	public EntityZulu(World par1World){
		super(par1World);
		experienceValue = 3;
		UpdateMoveSpeedBasedOnState(par1World);
	}

	@Override
	public String getTexture() {
		return super.getTexture();
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	@Override
	public boolean getCanSpawnHere(){
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
		
		//		ProjectZuluLog.info("Checking if %s can Spawn at X:%s Y:%s Z:%s in Biome: %s",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
		//		ProjectZuluLog.info("Checking if %s can Spawn at X:%s Y:%s Z:%s in Biome: %s",getEntityName(),var1,var2,var3);
		if( isValidLightLevel(worldObj, var1, var2, var3) && 
				//this.worldObj.canBlockSeeTheSky(var1, var2, var3) && 
				super.getCanSpawnHere()){
			ProjectZuluLog.info("Successfully spawned %s at X:%s Y:%s Z:%s in %s",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			return true;
		}else{
			ProjectZuluLog.info("Failed to spawn %s at X:%s Y:%s Z:%s in %s, Spawning Location Inhospitable",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			return false;
		}
	}
	
	protected boolean isValidLightLevel(World world, int xCoord, int yCoord, int zCoord){
		return worldObj.getSavedLightValue(EnumSkyBlock.Block, xCoord, yCoord, zCoord) < 1;
	}
	
	/**
	 * Determines if an entity can be despawned, used on idle far away entities
	 */
	protected boolean canDespawn(){
		return false;
	}


	@Override
	protected void entityInit(){
		super.entityInit();
		//		 this.dataWatcher.addObject(17, Byte.valueOf((byte)0));
		/* Love Cooldown */
		this.dataWatcher.addObject(18, Short.valueOf((short) 0));
		/* Attack Timer */
		this.dataWatcher.addObject(19, Short.valueOf((short) 0));
	}

	protected int getLoveCooldown(){
		return this.dataWatcher.getWatchableObjectShort(18);
	}
	protected void updateLoveCooldown(){
		this.dataWatcher.updateObject(18, (short)(loveCooldown)); 
	}

	protected int getAttackTimer(){
		return this.dataWatcher.getWatchableObjectShort(19); 
	}
	protected void updateAttackTimer(){
		this.dataWatcher.updateObject(19, (short)(attackTime)); 
	}

	@Override
	public int getMaxHealth(){
		return 10;
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	public boolean isAIEnabled(){return false;}
	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue(){return 0;}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound){
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("Anger", (short)this.angerLevel);
		par1NBTTagCompound.setShort("LoveCooldown", (short)this.loveCooldown);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound){
		super.readEntityFromNBT(par1NBTTagCompound);
		this.angerLevel = par1NBTTagCompound.getShort("Anger");
		this.loveCooldown = par1NBTTagCompound.getShort("LoveCooldown");
	}

	public void onUpdate(){
		if(worldObj.difficultySetting == 0 && mod_ProjectZulu.despawnInPeaceful){
			this.setDead();
		}

		UpdateTargetBasedOnState();
		UpdateEntityState();

		UpdateMoveSpeedBasedOnState(worldObj);

		if( isEntityGrounded() ){
			GroundUpdateEntityMovementTargetsBasedOnState();
		}else{
			//			AirUpdateTargetBasedOnState();
			//			AirUpdateEntityState();
			AirUpdateEntityMovementTargetsBasedOnState();
		}

		HandleSpecialAbilities();

		/* Try To Attack Target */
		TryToAttack();

		/* Update Datawatchers */
		if(!worldObj.isRemote){
			updateLoveCooldown();
			updateAttackTimer();
		}
		loveCooldown = getLoveCooldown();		
		attackTime = getAttackTimer();
		//If angry, calm down
		angerLevel = Math.max(angerLevel-1,0);
		counter++;
		loveCooldown = Math.max(loveCooldown-1,0);
		super.onUpdate();
	}

	/** 
	 * Update Entity Movement Speed
	 * This is overriden by each Entity if deviations from default are desired
	 */
	protected void UpdateMoveSpeedBasedOnState(World par1World){
		//Sets MoveSpeed Based on State		
		if(entityState == listStates.idle.index || entityState == listStates.looking.index){
			this.moveSpeed = 0.25f;
		}else if(entityState == listStates.attacking.index || entityState == listStates.attackAnim.index || entityState == listStates.attackAnim2.index){
			this.moveSpeed = 0.5f;
		}
		if(isCharging){
			this.moveSpeed *= chargeSpeedModifier;
		}

	}

	/** 
	 * Update Entity target
	 * This is overriden by each Entity if deviations from default are desired
	 */
	protected void UpdateTargetBasedOnState() {

		/* Handle Updating Searching For Target*/
		if(targetedEntity == null && angerLevel > 0){
			/* Angry and Don't have Target, check for one */
			Entity nearbyPlayer = this.worldObj.getClosestPlayerToEntity(this, 100.0D);
			if(nearbyPlayer != null){
				targetedEntity = nearbyPlayer;
				distToTarget = nearbyPlayer.getDistanceToEntity(this);
				distToTargetXZ = (int) Math.sqrt( Math.pow(nearbyPlayer.posX-this.posX, 2) + Math.pow(nearbyPlayer.posZ-this.posZ, 2) ); 

			}
		}else if(angerLevel > 0){
			/* Angry and Have Target, get Distance*/
			distToTarget = targetedEntity.getDistanceToEntity(this);
			distToTargetXZ = (int) Math.sqrt( Math.pow(targetedEntity.posX-this.posX, 2) + Math.pow(targetedEntity.posZ-this.posZ, 2) ); 

		}else if( isEntityInLove() ){ 
			/* If In Love Mode, Cycle through Nearby Entities for a breeding Target*/
			float var1 = 24.0F;
			List beedingTargetList = worldObj.getEntitiesWithinAABB(this.getClass(), this.boundingBox.expand((double)var1, (double)var1, (double)var1));
			Iterator beedingTargetIterator = beedingTargetList.iterator();
			EntityZulu breedingTarget = null;
			do
			{
				if (!beedingTargetIterator.hasNext())
				{
					break;
				}

				breedingTarget = (EntityZulu)beedingTargetIterator.next();
			}
			while (!canMateWith(breedingTarget));

			if(breedingTarget != null && canMateWith(breedingTarget)){
				targetedEntity = breedingTarget;
				distToTarget = targetedEntity.getDistanceToEntity(this);
			}

		}else{
			/* Check For Player Holding Breeding item */
			EntityPlayer nearbyPlayer = (EntityPlayer)this.worldObj.getClosestPlayerToEntity(this, 100.0D);
			if(nearbyPlayer != null &&  isValidFollowItem(nearbyPlayer.inventory.getCurrentItem()) ){
				targetedEntity = nearbyPlayer;
				distToTarget = nearbyPlayer.getDistanceToEntity(this);
				if(shouldAttackForFavorite( nearbyPlayer) ){
					angerLevel = 400 + this.rand.nextInt(400);
					entityState = listStates.attacking.index;
				}
			}
		}

		if(worldObj.difficultySetting == 0){
			targetedEntity = null;
		}

	}
	protected void AirUpdateTargetBasedOnState() {
		if(targetedEntity == null){
			Entity nearbyPlayer = this.worldObj.getClosestPlayerToEntity(this, 100.0D);
			if(nearbyPlayer != null){ 
				targetedEntity = nearbyPlayer;
				distToTargetXZ = (int) Math.sqrt( Math.pow(nearbyPlayer.posX-this.posX, 2) + Math.pow(nearbyPlayer.posZ-this.posZ, 2) ); 
			}
		}else{
			distToTargetXZ = (int) Math.sqrt( Math.pow(targetedEntity.posX-this.posX, 2) + Math.pow(targetedEntity.posZ-this.posZ, 2) ); 
		}
	}

	/** 
	 * Handle Enum State Switches/ Updates
	 * This is overriden by each Entity if deviations from default are desired
	 */
	protected void UpdateEntityState(){
		if(targetedEntity == null || targetedEntity.isDead || distToTarget > 32 
				|| (targetedEntity instanceof EntityPlayer && ((EntityPlayer)targetedEntity).capabilities.isCreativeMode 
						&& !isValidFollowItem(((EntityPlayer)targetedEntity).inventory.getCurrentItem()) ) ) {
			/* If target is Null or if the Target is Invalid (i.e. Player in Creative) */
			targetedEntity = null;

			if(angerLevel <= 0){
				entityState = listStates.idle.index;
			}else{
				entityState = listStates.looking.index;
			}

		}else if(entityState == listStates.looking.index && targetedEntity != null){
			entityState = listStates.attacking.index;

		}else if(angerLevel > 0 && entityState == listStates.attackAnim2.index && attackTime <= attackAnimTime/2){
			/* AttackAnim is Set when The EntityDecides to attack*/	
			entityState = listStates.attackAnim.index;

		}else if(angerLevel > 0 && entityState == listStates.attackAnim.index && attackTime <= 0){
			/* Attacking State is Set when attacked by Entity*/
			entityState = listStates.attacking.index;

		}
	}

	protected void AirUpdateEntityState(){
		/* Handle State Updates  */
		//If Player goes outside attacking range, go idle (Or if goes creative)
		if(targetedEntity != null && targetedEntity instanceof EntityPlayer && ((EntityPlayer)targetedEntity).capabilities.isCreativeMode){
			entityState = listStates.idle.index;
			targetedEntity = null;
		}else if( (entityState == listStates.attacking.index || entityState == listStates.following.index) && (distToTargetXZ > 32 || targetedEntity == null || targetedEntity.isDead)){
			entityState = listStates.idle.index;
			targetedEntity = null;
		}else if(entityState == listStates.idle.index && targetedEntity != null && distToTargetXZ < 16){
			entityState = listStates.attacking.index;
		}
	}


	/** 
	 * Update Entity Desired Movement Target
	 * This is overriden by each Entity if deviations from default are desired
	 */
	protected void GroundUpdateEntityMovementTargetsBasedOnState() {

		if(targetedEntity != null && canMateWith(targetedEntity) && angerLevel == 0 && loveTimer > 0){
			/* If In love Mode, Ignore State and Move toward love target */
			if(this.pathToEntity == null){
				this.pathToEntity = this.worldObj.getPathEntityToEntity(this, this.targetedEntity, 32f, false, true, false, true);
			}else if(this.targetedEntity.getDistanceToEntity(this) < 10f && this.pathToEntity != null && 1-rand.nextInt(30) >= 0 ){
				this.pathToEntity = null;
				this.pathToEntity = this.worldObj.getPathEntityToEntity(this, this.targetedEntity, 32f, false, true, false, true);
			}
		}else if(entityState == listStates.idle.index){
			if(this.pathToEntity == null && this.rand.nextInt(60) == 0){
				updateWanderPath();
			}

		}else if(entityState == listStates.looking.index){
			if(this.pathToEntity == null && this.rand.nextInt(60) == 0){
				updateWanderPath();
			}			

		}else if(entityState == listStates.attacking.index || entityState == listStates.attackAnim.index || entityState == listStates.attackAnim.index){

			if(this.pathToEntity == null){
				this.pathToEntity = this.worldObj.getPathEntityToEntity(this, this.targetedEntity, 32f, false, true, false, true);
			}else if(this.targetedEntity.getDistanceToEntity(this) < 10f && this.pathToEntity != null && 1-rand.nextInt(60) >= 0 ){
				this.pathToEntity = null;
				this.pathToEntity = this.worldObj.getPathEntityToEntity(this, this.targetedEntity, 32f, false, true, false, true);
			}
		}else if(targetedEntity != null){
			/* Neglecting any States, if Entity has a target, path towards it */
			if(this.pathToEntity == null){
				this.pathToEntity = this.worldObj.getPathEntityToEntity(this, this.targetedEntity, 32f, false, true, false, true);
			}else if(this.targetedEntity.getDistanceToEntity(this) < 10f && this.pathToEntity != null && 1-rand.nextInt(60) >= 0 ){
				this.pathToEntity = null;
				this.pathToEntity = this.worldObj.getPathEntityToEntity(this, this.targetedEntity, 32f, false, true, false, true);
			}

		}
	}

	/** 
	 * Flying Mob Equivalent to GroundUpdateEntityMovementTarget
	 * Update Entity Desired Movement Target
	 * This is overriden by each Entity if deviations from default are desired
	 */
	protected void AirUpdateEntityMovementTargetsBasedOnState() {

		/* If Target Position is not an Air Block, Target Position = null (i.e. Don't try to go there) */
		if (this.targetPosition != null && (!this.worldObj.isAirBlock(this.targetPosition.posX, this.targetPosition.posY, this.targetPosition.posZ) || this.targetPosition.posY < 3 
				|| this.worldObj.getBlockMaterial(this.targetPosition.posX, this.targetPosition.posY, this.targetPosition.posZ).equals(Material.water))){
			this.targetPosition = null;
		}

		if(targetedEntity != null && canMateWith(targetedEntity) && angerLevel == 0 && loveTimer > 0){
			double targetX;
			double targetY;
			double targetZ;

			if(Math.abs(targetedEntity.posX - this.posX) >= distanceToCircle / 2 ){
				targetX = this.posX + (targetedEntity.posX - this.posX) / Math.abs(targetedEntity.posX - this.posX)*2 ;
			}else{
				targetX = this.posX + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 1.0F);
			}
			if(Math.abs(targetedEntity.posZ - this.posZ) >= distanceToCircle / 2 ){
				targetZ = this.posZ + (targetedEntity.posZ - this.posZ) / Math.abs(targetedEntity.posZ - this.posZ)*2 ;
			}else{
				targetZ = this.posZ + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 1.0F);
			}

			targetY = this.posY + 
					( (targetedEntity.boundingBox.maxY+targetedEntity.boundingBox.minY)/2  - this.posY) / 
					Math.abs( (targetedEntity.boundingBox.maxY+targetedEntity.boundingBox.minY)/2 - this.posY);
			this.targetPosition = new ChunkCoordinates((int)targetX, (int)targetY, (int)targetZ);
			this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.targetedEntity.posX - this.posX, this.targetedEntity.posZ - this.posZ)) * 180.0F / (float)Math.PI;


		}else if(entityState == listStates.idle.index || entityState == listStates.looking.index){
			/* Idle Movement: If We don't have a target lets wander to a random place */
			if (this.targetPosition == null || this.rand.nextInt(30) == 0 || this.targetPosition.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0F){
				double targetY;
				//				worldObj.getPrecipitationHeight(par1, par2)
				if ( posY - 3 < worldObj.getPrecipitationHeight( (int)posX, (int)posZ) ) {
					/* Go Up */
					targetY = this.posY + (double)((this.rand.nextFloat() * 1.0F + 0.0F) * 7.0F);
				}else if( posY > worldObj.getPrecipitationHeight( (int)posX, (int)posZ) + maxFlightHeight ){
					/* Go Down */
					targetY = this.posY + (double)((this.rand.nextFloat() * 1.0F - 1.0F) * 7.0F);
				}else{
					/* Go Who Gives a Fuck */
					targetY = this.posY + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 7.0F);
				}
				this.targetPosition = new ChunkCoordinates((int)this.posX + this.rand.nextInt(15) - 7, (int) targetY, (int)this.posZ + this.rand.nextInt(15) - 7);
			}
			adjustRotationToWaypoint();

		}else if( targetedEntity != null ){
			double targetX;
			double targetY;
			double targetZ;

			if(Math.abs(targetedEntity.posX - this.posX) >= distanceToCircle / 2 ){
				targetX = this.posX + (targetedEntity.posX - this.posX) / Math.abs(targetedEntity.posX - this.posX)*2 ;
			}else{
				targetX = this.posX + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 1.0F);
			}
			if(Math.abs(targetedEntity.posZ - this.posZ) >= distanceToCircle / 2 ){
				targetZ = this.posZ + (targetedEntity.posZ - this.posZ) / Math.abs(targetedEntity.posZ - this.posZ)*2 ;
			}else{
				targetZ = this.posZ + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 1.0F);
			}

			targetY = this.posY + 
					( (targetedEntity.boundingBox.maxY+targetedEntity.boundingBox.minY)/2  - this.posY) / 
					Math.abs( (targetedEntity.boundingBox.maxY+targetedEntity.boundingBox.minY)/2 - this.posY);
			this.targetPosition = new ChunkCoordinates((int)targetX, (int)targetY, (int)targetZ);
			this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.targetedEntity.posX - this.posX, this.targetedEntity.posZ - this.posZ)) * 180.0F / (float)Math.PI;

		}



		/* Handle Movement Target Based On State*/
		if(entityState == listStates.idle.index){


		}else if(entityState == listStates.attacking.index ){
		}

	}


	/** 
	 * Handles Entity Specific Abilities
	 * This is overriden by each Entity if deviations from default are desired
	 */
	protected void HandleSpecialAbilities() {
		HandleChargeAbility();
		HandleEntityBreeding();
	}

	/** 
	 * Charge Ability
	 * This is overriden by each Entity if deviations from default are desired
	 */
	protected void HandleChargeAbility(){
		/* Check If Entity Should START Charging */
		if(this.timeSinceLastCharge > chargeTriggerThreshold && !isCharging){// && targetedEntity != null && this.getDistanceToEntity(targetedEntity) < 20.0f){
			this.isCharging = true;
			this.timeSinceLastCharge = 0.0f;
		}

		/* Check If Entity Should STOP Charging */
		if(isCharging && this.timeSinceLastCharge > chargeTime){
			this.isCharging = false;
		}

		/* Increase Time Since Last Charge */
		this.timeSinceLastCharge++;
	}

	/** 
	 * Breeding Ability
	 * This is overriden by each Entity if deviations from default are desired
	 */	
	protected void HandleEntityBreeding(){
		/* Don't Immediately Mate Once Entity is in Love move, Wait till Love Timer Decreases */
		if(loveTimer < 10 && canMateWith(targetedEntity)){
			spawnBaby();
		}

		/* Spawn Heart Particles if in love mode*/
		if (loveTimer > 0){
			--loveTimer;
			String var1 = "heart";

			if (loveTimer % 10 == 0)
			{
				double var2 = this.rand.nextGaussian() * 0.02D;
				double var4 = this.rand.nextGaussian() * 0.02D;
				double var6 = this.rand.nextGaussian() * 0.02D;
				this.worldObj.spawnParticle(var1, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, var2, var4, var6);
			}
		}
	}

	/** 
	 * Checks if Entity is in Love
	 */
	public boolean isEntityInLove(){
		return loveTimer > 0; 
	}

	/**
	 * Returns true if the mob is currently able to mate with the specified mob.
	 */
	public boolean canMateWith(Entity par1Entity){
		if(par1Entity instanceof EntityZulu){
			return par1Entity == this ? false : (par1Entity.getClass() != this.getClass() ? false : this.isEntityInLove() && ((EntityZulu)par1Entity).isEntityInLove() );
		}else {
			return false;
		}
	}

	/**
	 * Spawns a baby animal of the same type.
	 */
	private void spawnBaby()
	{
		EntityZulu var1 = getBabyAnimalEntity(this.targetedEntity);

		if (var1 != null)
		{
			/* Add Age (Functions as Cooldown so that Entity can't mate?) */
			this.setGrowingAge(6000);
			((EntityZulu)targetedEntity).setGrowingAge(6000);

			/* Turn off Love in Each Animal */
			this.loveTimer = 0;
			((EntityZulu)targetedEntity).loveTimer = 0;

			/* Configure Baby then Spawn it into World, Set Age (- means child) */
			var1.setGrowingAge(-24000);
			if(!worldObj.isRemote){
				var1.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
				this.worldObj.spawnEntityInWorld(var1);
				Random var2 = this.getRNG();

				for (int var3 = 0; var3 < 7; ++var3)
				{
					double var4 = var2.nextGaussian() * 0.02D;
					double var6 = var2.nextGaussian() * 0.02D;
					double var8 = var2.nextGaussian() * 0.02D;
					this.worldObj.spawnParticle("heart",
							this.posX + (double)(var2.nextFloat() * this.width * 2.0F) - (double)this.width,
							this.posY + 0.5D + (double)(var2.nextFloat() * this.height),
							this.posZ + (double)(var2.nextFloat() * this.width * 2.0F) - (double)this.width,
							var4, var6, var8);
				}
			}
		}
	}

	/** 
	 * Entity Attempts to Attack
	 * This is overriden by each Entity if deviations from default are desired
	 */
	protected void TryToAttack() {

		if(entityState == listStates.attacking.index && angerLevel > 0 && distToTarget < getAttackDistance() && attackTime <= 0){
			entityState = listStates.attackAnim2.index;
			customAttackEntity(targetedEntity);
			this.attackTime = (int) attackAnimTime;
		}
	}

	/** 
	 * Gets Entity Attack Range
	 * This is overriden by each Entity if deviations from default are desired
	 */
	public float getAttackDistance(){
		return 2f;
	}

	/**
	 * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
	 */
	protected void customAttackEntity(Entity par1Entity)
	{
		if (par1Entity.boundingBox.maxY > this.boundingBox.minY && par1Entity.boundingBox.minY < this.boundingBox.maxY)
		{
			this.attackEntityAsMob(par1Entity);
		}
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2){
		//TODO Check This should only work when attackEntityFrom returns True
		Entity var3 = par1DamageSource.getEntity();

		if (var3 != null && var3 instanceof EntityLiving && shouldCallForHelp(var3)){
			List var4 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(20.0D, 20.0D, 20.0D));
			Iterator var5 = var4.iterator();

			while (var5.hasNext()){
				Entity var6 = (Entity)var5.next();

				if (var6.getClass().equals(this.getClass()) ){
					EntityZulu var7 = (EntityZulu)var6;
					var7.becomeAngryAt(var3);
				}
			}
		}
		this.becomeAngryAt(var3);

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	/**
	 * Determine whether the Entity Should Call For Help
	 */
	public boolean shouldCallForHelp(Entity attackingEntity){
		return true;
	}

	/** 
	 * Checks if the Provided ItemStack is considered an item the EntityShould Follow
	 * This is overriden by each Entity if deviations from default are desired
	 */
	public boolean isValidFollowItem(ItemStack itemStack){
		return false; 
	}

	/** 
	 * Checks if the Entity should attack the provided Entity for its Favorite Item
	 * This is overriden by each Entity if deviations from default are desired
	 */
	public boolean shouldAttackForFavorite(EntityPlayer targetEntity){
		return false; 
	}

	/** 
	 * Checks if the Provided ItemStack is considered an item that should be used for Breeding
	 * This is overriden by each Entity if deviations from default are desired
	 */
	public boolean isValidBreedingItem(ItemStack itemStack){
		return false; 
	}

	/**
	 * This function is used when two same-species animals in 'love mode' breed to generate the new baby animal of their Type.
	 */
	public EntityZulu getBabyAnimalEntity(Entity par1EntityAnimal)
	{
		Object object = null;
		try {
			Class<?> thisClass = par1EntityAnimal.getClass();
			Constructor<?> ctor= thisClass.getConstructor(World.class);
			try {
				object = ctor.newInstance(new Object[] { this.worldObj });
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return (EntityZulu) object;
		//		return new EntityRabbit(this.worldObj);

	}


	/**
	 * Time remaining during which the Animal is sped up and flees.
	 */
	@Override
	protected void updateWanderPath()
	{
		this.worldObj.theProfiler.startSection("stroll");
		boolean var1 = false;
		int var2 = -1;
		int var3 = -1;
		int var4 = -1;
		float var5 = -99999.0F;

		for (int var6 = 0; var6 < 10; ++var6)
		{
			int var7 = MathHelper.floor_double(this.posX + (double)this.rand.nextInt(13) - 6.0D);
			int var8 = MathHelper.floor_double(this.posY + (double)this.rand.nextInt(7) - 3.0D);
			int var9 = MathHelper.floor_double(this.posZ + (double)this.rand.nextInt(13) - 6.0D);
			float var10 = this.getBlockPathWeight(var7, var8, var9);

			if (var10 > var5)
			{
				var5 = var10;
				var2 = var7;
				var3 = var8;
				var4 = var9;
				var1 = true;
			}
		}
		if (var1){
			this.pathToEntity = this.worldObj.getEntityPathToXYZ(this, var2, var3, var4, 10.0F, true, false, false, true);
		}

		this.worldObj.theProfiler.endSection();
	}

	/**
	 * Takes a coordinate in and returns a weight to determine how likely this creature will try to path to the block.
	 * Args: x, y, z
	 */
	@Override
	public float getBlockPathWeight(int par1, int par2, int par3){
		return (30 - worldObj.getSavedLightValue(EnumSkyBlock.Block, (int)this.posX, (int)this.posY, (int)this.posZ));
	}

	/**
	 * Causes this Entity to become angry at the supplied Entity (which will be a player).
	 */
	public void becomeAngryAt(Entity par1Entity){
		this.targetedEntity = par1Entity;
		this.entityState = listStates.attacking.index;
		this.angerLevel = 400 + this.rand.nextInt(400);
	}

	public int getAngerLevel(){
		return angerLevel;
	}

	/*	Called when player interacts with mob, e.g. get milk, saddle */
	@Override
	public boolean interact(EntityPlayer par1EntityPlayer){
		if(isValidBreedingItem(par1EntityPlayer.inventory.getCurrentItem()) ){
			if(loveTimer <= 0 && loveCooldown <= 0){
				loveTimer = 20*20;
				loveCooldown = 20*60*5;
				angerLevel = 0;
				entityState = listStates.idle.index;
				if(!par1EntityPlayer.capabilities.isCreativeMode){
					par1EntityPlayer.inventory.getCurrentItem().stackSize -= 1;
				}
			}
			return true;
		}else {
			return super.interact(par1EntityPlayer);
		}		
	}

	/**
	 * Drop 0-2 items of this living's type
	 */
	@Override
	protected void dropFewItems(boolean par1, int par2){
		int var3 = this.rand.nextInt(2 + par2);
		int var4;

		if(Loader.isModLoaded(DefaultProps.BlocksModId)){
			if(var3 == 0){
				if(ItemBlockList.furPelt.isPresent()){
					this.dropItem(ItemBlockList.furPelt.get().shiftedIndex, 1);
				}
			}else{
				this.dropItem(Item.beefRaw.shiftedIndex,1);
			}
		}else{
			this.dropItem(Item.beefRaw.shiftedIndex,1);
		}

	}

	@Override
	public EntityItem dropItem(int par1, int par2) {
		//		if(par1 == -1 || par1-256 == -1 ){
		//			return null;
		//		}else{
		return super.dropItem(par1, par2);
		//		}
	}

}