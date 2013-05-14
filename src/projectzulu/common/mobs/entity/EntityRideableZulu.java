package projectzulu.common.mobs.entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import projectzulu.common.Properties;
import projectzulu.common.core.ProjectZuluLog;
@Deprecated
public class EntityRideableZulu extends EntityRideableHerd implements IAnimals
{	
	/* Uncategorized Variables */
	public float attackAnimTime = 20;

	/* Entity Target Variables */
	protected Entity targetedEntity;
	protected float distToTarget;

	/* Debug Variables */
	public int counter = 0;

	/*Charge Ability Variables*/
	protected boolean isCharging = false;
	protected float timeSinceLastCharge = 5f;
	protected float chargeTriggerThreshold = 10*20f;
	protected float chargeTime = 0.2f*chargeTriggerThreshold;

	/* Entity State Variables */
	protected int loveTimer = 0;
	protected int loveCooldown = 0;
	protected int angerLevel = 0;
	protected int entityState = 0;
	public int getState(){
		return entityState;
	}
	protected enum listStates {
		idle(0), 
		looking(1),
		attacking(2),
		attackAnim(3),
		attackAnim2(4),
		posture(5);

		public final int index;
		listStates(int index) {
			this.index = index;
		}
		public int index() { 
			return index; 
		}
	}

	//	public int getStateId(String nameOfState){
	//		return listStates.valueOf(nameOfState).index;
	//	}

	public EntityRideableZulu(World par1World){
		super(par1World);

		attackStrength(par1World);
		UpdateMoveSpeedBasedOnState(par1World);
	}

	/** 
	 * Set Entity Attack Strength
	 * This is overriden by each Entity if deviations from default are desired
	 */
	protected void attackStrength(World par1World){
		switch (par1World.difficultySetting) {
		case 0:
			this.attackStrength = 3; 
			break;
		case 1:
			this.attackStrength = 3; 
			break;
		case 2:
			this.attackStrength = 4; 
			break;
		case 3:
			this.attackStrength = 6; 
			break;
		default:
			this.attackStrength = 3;
			break;
		}
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

		ProjectZuluLog.info("Checking if %s can Spawn at X:%s Y:%s Z:%s",getEntityName(),var1,var2,var3);
		return  this.worldObj.getSavedLightValue(EnumSkyBlock.Block, var1, var2, var3) < 1 && 
				this.worldObj.canBlockSeeTheSky(var1, var2, var3) && 
				super.getCanSpawnHere();
	}

	/**
	 * Determines if an entity can be despawned, used on idle far away entities
	 */
	@Override
    protected boolean canDespawn(){
		return false;
	}


	@Override
	protected void entityInit(){
		super.entityInit();
		//		 this.dataWatcher.addObject(17, Byte.valueOf((byte)0));
		/* Love Cooldown */
		this.dataWatcher.addObject(18, Short.valueOf((short) 0));

	}	

	public void updateLoveCooldown(){
		this.dataWatcher.updateObject(18, (short)(loveCooldown));
	}

	public int getLoveCooldown(){
		return this.dataWatcher.getWatchableObjectShort(18);
	}

	@Override
	public int getMaxHealth(){
		return 10;
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	@Override
    public boolean isAIEnabled(){return false;}
	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	@Override
    public int getTotalArmorValue(){return 0;}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound){
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("Anger", (short)this.angerLevel);
		par1NBTTagCompound.setShort("LoveCooldown", (short)this.loveCooldown);

	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound){
		super.readEntityFromNBT(par1NBTTagCompound);
		this.angerLevel = par1NBTTagCompound.getShort("Anger");
		this.loveCooldown = par1NBTTagCompound.getShort("LoveCooldown");
	}

	@Override
    public void onUpdate(){
		if(worldObj.difficultySetting == 0 && Properties.despawnInPeaceful){
			this.setDead();
		}

		UpdateMoveSpeedBasedOnState(worldObj);
		UpdateTargetBasedOnState();
		UpdateEntityState();

		UpdateEntityMovementTargetsBasedOnState();
		HandleSpecialAbilities();

		/* Try To Attack Target */
		TryToAttack();

		/* Update Datawatchers */
		if(!worldObj.isRemote){
			updateLoveCooldown();
		}
		loveCooldown = getLoveCooldown();

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
			}
		}else if(angerLevel > 0){
			/* Angry and Have Target, get Distance*/
			distToTarget = targetedEntity.getDistanceToEntity(this);
		}else if( isEntityInLove() ){ 
			/* If In Love Mode, Cycle through Nearby Entities for a breeding Target*/
			float var1 = 24.0F;
			List beedingTargetList = worldObj.getEntitiesWithinAABB(this.getClass(), this.boundingBox.expand(var1, var1, var1));
			Iterator beedingTargetIterator = beedingTargetList.iterator();
			EntityRideableZulu breedingTarget = null;
			do
			{
				if (!beedingTargetIterator.hasNext())
				{
					break;
				}

				breedingTarget = (EntityRideableZulu)beedingTargetIterator.next();
			}
			while (!this.canMateWith(breedingTarget));

			if(breedingTarget != null && canMateWith(breedingTarget)){
				targetedEntity = breedingTarget;
				distToTarget = targetedEntity.getDistanceToEntity(this);
			}

		}else{
			/* Check For Player Holding Breeding item */
			EntityPlayer nearbyPlayer = this.worldObj.getClosestPlayerToEntity(this, 100.0D);
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

	/** 
	 * Handle Enum State Switches/ Updates
	 * This is overriden by each Entity if deviations from default are desired
	 */
	protected void UpdateEntityState() {

		if(targetedEntity == null || targetedEntity.isDead || distToTarget > 32 
				|| (targetedEntity instanceof EntityPlayer && ((EntityPlayer)targetedEntity).capabilities.isCreativeMode 
						&& !isValidFollowItem(((EntityPlayer)targetedEntity).inventory.getCurrentItem()) ) ) {
			/* If target is Null or if the Target is Invalid (i.e. Player in Creative) */
			targetedEntity = null;

			if(angerLevel <= 0 && entityState != listStates.idle.index){
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


	/** 
	 * Update Entity Desired Movement Target
	 * This is overriden by each Entity if deviations from default are desired
	 */
	protected void UpdateEntityMovementTargetsBasedOnState() {

		if(targetedEntity != null && canMateWith(targetedEntity) && angerLevel == 0 && loveTimer > 0){
			/* If In love Mode, Ignore State and Move toward love target */
			if(this.pathToEntity == null){
				this.pathToEntity = this.worldObj.getPathEntityToEntity(this, this.targetedEntity, 32f, false, true, false, true);
			}else if(this.targetedEntity.getDistanceToEntity(this) < 10f && this.pathToEntity != null && 1-rand.nextInt(20) >= 0 ){
				this.pathToEntity = null;
				this.pathToEntity = this.worldObj.getPathEntityToEntity(this, this.targetedEntity, 32f, false, true, false, true);
			}
		}else if(entityState == listStates.idle.index){
			if(this.pathToEntity == null && this.rand.nextInt(20) == 0){
				updateWanderPath();
			}

		}else if(entityState == listStates.looking.index){
			if(this.pathToEntity == null && this.rand.nextInt(20) == 0){
				updateWanderPath();
			}			

		}else if(entityState == listStates.attacking.index || entityState == listStates.attackAnim.index || entityState == listStates.attackAnim.index){

			if(this.pathToEntity == null){
				this.pathToEntity = this.worldObj.getPathEntityToEntity(this, this.targetedEntity, 32f, false, true, false, true);
			}else if(this.targetedEntity.getDistanceToEntity(this) < 10f && this.pathToEntity != null && 1-rand.nextInt(20) >= 0 ){
				this.pathToEntity = null;
				this.pathToEntity = this.worldObj.getPathEntityToEntity(this, this.targetedEntity, 32f, false, true, false, true);
			}
		}else if(targetedEntity != null){
			/* Neglecting any States, if Entity has a target, path towards it */
			if(this.pathToEntity == null){
				this.pathToEntity = this.worldObj.getPathEntityToEntity(this, this.targetedEntity, 32f, false, true, false, true);
			}else if(this.targetedEntity.getDistanceToEntity(this) < 10f && this.pathToEntity != null && 1-rand.nextInt(20) >= 0 ){
				this.pathToEntity = null;
				this.pathToEntity = this.worldObj.getPathEntityToEntity(this, this.targetedEntity, 32f, false, true, false, true);
			}

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
		if(this.timeSinceLastCharge > chargeTriggerThreshold && !isCharging && targetedEntity != null && this.getDistanceToEntity(targetedEntity) < 20.0f){
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
		if(loveTimer < 15 && canMateWith(targetedEntity)){
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
				this.worldObj.spawnParticle(var1, this.posX + this.rand.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + this.rand.nextFloat() * this.height, this.posZ + this.rand.nextFloat() * this.width * 2.0F - this.width, var2, var4, var6);
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
		if(par1Entity instanceof EntityRideableZulu){
			return par1Entity == this ? false : (par1Entity.getClass() != this.getClass() ? false : this.isEntityInLove() && ((EntityRideableZulu)par1Entity).isEntityInLove() );
		}else {
			return false;
		}
	}
	/* Is here to override the EntityAnimal canMAteWith which only accepts animals*/
	@Override
	public boolean canMateWith(EntityAnimal par1EntityAnimal) {
		return canMateWith((Entity)par1EntityAnimal);
	}

	/**
	 * Spawns a baby animal of the same type.
	 */
	private void spawnBaby()
	{
		EntityRideableZulu var1 = spawnBabyAnimal(this.targetedEntity);

		if (var1 != null)
		{
			/* Add Age (Functions as Cooldown so that Entity can't mate?) */
			this.setGrowingAge(6000);
			((EntityRideableZulu)targetedEntity).setGrowingAge(6000);

			/* Turn off Love in Each Animal */
			this.loveTimer = 0;
			((EntityRideableZulu)targetedEntity).loveTimer = 0;

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
							this.posX + var2.nextFloat() * this.width * 2.0F - this.width,
							this.posY + 0.5D + var2.nextFloat() * this.height,
							this.posZ + var2.nextFloat() * this.width * 2.0F - this.width,
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
		Entity var3 = par1DamageSource.getEntity();

		if (var3 instanceof EntityPlayer && shouldCallForHelp(var3)){
			List var4 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0D, 32.0D, 32.0D));
			Iterator var5 = var4.iterator();

			while (var5.hasNext()){
				Entity var6 = (Entity)var5.next();

				if (var6.getClass().equals(this.getClass()) ){
					EntityRideableZulu var7 = (EntityRideableZulu)var6;
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
	public EntityRideableZulu spawnBabyAnimal(Entity par1EntityAnimal)
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
		return (EntityRideableZulu) object;
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
			int var7 = MathHelper.floor_double(this.posX + this.rand.nextInt(13) - 6.0D);
			int var8 = MathHelper.floor_double(this.posY + this.rand.nextInt(7) - 3.0D);
			int var9 = MathHelper.floor_double(this.posZ + this.rand.nextInt(13) - 6.0D);
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
			if(angerLevel > 0){
				angerLevel = 0;
				if(!par1EntityPlayer.capabilities.isCreativeMode){
					par1EntityPlayer.inventory.getCurrentItem().stackSize -= 1;
				}

			}else if(loveTimer <= 0 && loveCooldown <= 0){
				loveTimer = 20*20;
				loveCooldown = 20*60*5;
				if(!par1EntityPlayer.capabilities.isCreativeMode){
					par1EntityPlayer.inventory.getCurrentItem().stackSize -= 1;
				}
			}
			return true;
		}else {
			return super.interact(par1EntityPlayer);
		}		
	}

	@Override
	public EntityItem dropItem(int par1, int par2) {
		if(par1 == -1 || par1-256 == -1 ){
			return null;
		}else{
			return super.dropItem(par1, par2);
		}
	}

}