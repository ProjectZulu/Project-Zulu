package projectzulu.common.mobs.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumEntitySize;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.core.DefaultProps;

public class EntityMimic extends EntityHerd
{	

	/** Above zero if this PigZombie is Angry. */
	private int angerLevel = 0;


	public int counter = 0;
	public boolean charge = false;
	public boolean takeCover = false;
	public boolean inCover = false;
	public boolean attacking = false;
	public boolean printMessage = false;


	public EntityModelRotation eBODYROT = new EntityModelRotation();
	public EntityModelRotation eARMLEFTOPROT = new EntityModelRotation();
	public EntityModelRotation eARMLEFBOTROT = new EntityModelRotation();
	public EntityModelRotation eARMRIGTOPROT = new EntityModelRotation();
	public EntityModelRotation eARMRIGBOTROT = new EntityModelRotation();

	public EntityModelRotation eLEGRIGTOPROT = new EntityModelRotation();
	public EntityModelRotation eLEGRIGBOTROT = new EntityModelRotation();
	public EntityModelRotation eLEGLEFTTOPROT = new EntityModelRotation();
	public EntityModelRotation eLEGLEFBOTROT = new EntityModelRotation();

	public EntityModelRotation eBACKTREESTEM1 = new EntityModelRotation();
	public EntityModelRotation eBACKTREESTEM2 = new EntityModelRotation();
	public EntityModelRotation eBACKTREESTEM3 = new EntityModelRotation();
	public EntityModelRotation eBACKTREESTEM4 = new EntityModelRotation();
	public EntityModelRotation eBACKTREESTEM5 = new EntityModelRotation();
	public EntityModelRotation eBACKTREESTEM6 = new EntityModelRotation();

	//	public EntityModelRotation etoer1 = new EntityModelRotation();
	//	public EntityModelRotation etoer2 = new EntityModelRotation();
	//	public EntityModelRotation etoer3 = new EntityModelRotation();
	//	public EntityModelRotation etoer4 = new EntityModelRotation();
	//	public EntityModelRotation etoer5 = new EntityModelRotation();
	//	public EntityModelRotation etoer6 = new EntityModelRotation();
	//
	//	public EntityModelRotation etoel1 = new EntityModelRotation();
	//	public EntityModelRotation etoel2 = new EntityModelRotation();
	//	public EntityModelRotation etoel3 = new EntityModelRotation();
	//	public EntityModelRotation etoel4 = new EntityModelRotation();
	//	public EntityModelRotation etoel5 = new EntityModelRotation();
	//	public EntityModelRotation etoel6 = new EntityModelRotation();

	public float chargeCooldown = 10f;
	public float chargeTime = 5f;
	public float timeLeftCharging = chargeTime;

	private int randomSoundDelay = 0;
	public int transitioning = 0;
	public float attackAnimTime = 20;

	public int stampedeTime = 0;
	Vec3 stampedeDirection = Vec3.createVectorHelper(0, 0, 0);

	private Entity targetedEntity;
	private float distToTarget;

	// Used When in custom strcutres when Spawning Mimic to ensure they don't fall until the structure-world has been generated
	private boolean hover = false;

	public enum listStates {
		idle(0), 
		looking(1),
		attacking(2),
		attackAnim(3),
		attackAnim2(4);

		public final int index;
		listStates(int index) {
			this.index = index;
		}
		public int index() { 
			return index; 
		}
	}
	public int entityState = 0;

	public EntityMimic(World par1World){
		super(par1World);
		this.moveSpeed = 0.4f;
		this.myEntitySize = EnumEntitySize.SIZE_6;
		setSize(1.0f, 1.5f);
	}

	public EntityMimic(World par1World, double parx, double pary, double parz, boolean shouldHover){
		super(par1World);

		setLocationAndAngles(parx, pary, parz, 1, 1);
		setPosition(parx, pary, parz);
		setSize(1.0f, 1.5f);

		this.moveSpeed = 0.4f;
		this.myEntitySize = EnumEntitySize.SIZE_6;
		this.hover = shouldHover;
	}



	@Override
	public String getTexture() {
		this.texture = DefaultProps.mobDiretory + "MimicChest.png";
		return super.getTexture();
	}


	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	@Override
	public boolean getCanSpawnHere()
	{
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);

		return worldObj.getClosestPlayerToEntity(this, 32) == null
				&& worldObj.getSavedLightValue(EnumSkyBlock.Block, var1, var2, var3) < 1
				&& this.worldObj.checkIfAABBIsClear(this.boundingBox) 
				&& this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() 
				&& !this.worldObj.isAnyLiquid(this.boundingBox)
				&& this.worldObj.canBlockSeeTheSky(var1, var2, var3);
	}


	@Override
	protected void entityInit(){super.entityInit();}
	public int getMaxHealth(){return 20;}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	public boolean isAIEnabled(){return false;}
	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue(){return 6;}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound(){
		//		return "sounds.treeentliving";
		return null;

	}
	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		//return "mob.creeper";
		return null;
	}
	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound()
	{
		//return "mob.creeperdeath";
		return null;
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setShort("Anger", (short)this.angerLevel);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		this.angerLevel = par1NBTTagCompound.getShort("Anger");
	}

	public void onLivingUpdate()
	{
		this.entityAge += 2;
		super.onLivingUpdate();
	}


	public void onUpdate(){		
		if(worldObj.difficultySetting == 0){
			this.setDead();
		}

		//Sets MoveSpeed Based on State		
		if(entityState == listStates.idle.index){
			this.moveSpeed = 0.0f;
		}else if(entityState == listStates.attacking.index || entityState == listStates.attackAnim.index 
				|| entityState == listStates.attackAnim2.index || entityState == listStates.looking.index){
			this.moveSpeed = 0.6f;
		}


		/* Handle Updating Searching For Target*/
		if(targetedEntity == null && angerLevel > 0){
			/* Get NearestPlayer Distance */
			Entity nearbyPlayer = this.worldObj.getClosestPlayerToEntity(this, 100.0D);
			if(nearbyPlayer != null){
				targetedEntity = nearbyPlayer;
				distToTarget = nearbyPlayer.getDistanceToEntity(this);
			}
		}else if(targetedEntity != null){
			distToTarget = targetedEntity.getDistanceToEntity(this);
		}


		/* Handle State Switches */
		/* Attacking State is Set when attacked by Entity*/
		/* AttackAnim is Set when The EntityDecides to attack*/	
		if(targetedEntity == null || targetedEntity.isDead || distToTarget > 32 
				|| (targetedEntity instanceof EntityPlayer && ((EntityPlayer)targetedEntity).capabilities.isCreativeMode) ) {
			targetedEntity = null;
			if(angerLevel < 0 && entityState != listStates.idle.index){
				entityState = listStates.idle.index;
			}else if( entityState == listStates.attacking.index || entityState == listStates.attackAnim.index ){
				entityState = listStates.looking.index;
			}

		}else if(angerLevel > 0 && entityState == listStates.attackAnim2.index && attackTime <= attackAnimTime/2){
			entityState = listStates.attackAnim.index;

		}else if(angerLevel > 0 && entityState == listStates.attackAnim.index && attackTime <= 0){
			entityState = listStates.attacking.index;

		}


		if(entityState == listStates.idle.index){
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
			}else if(this.targetedEntity.getDistanceToEntity(this) < 10f && this.pathToEntity != null && 1-rand.nextInt(20) >= 0 ){
				this.pathToEntity = null;
				this.pathToEntity = this.worldObj.getPathEntityToEntity(this, this.targetedEntity, 32f, false, true, false, true);
			}

		}

		/* Try To Attack Target */
		if(entityState == listStates.attacking.index && angerLevel > 0 && distToTarget < 1.5 && attackTime <= 0){
			entityState = listStates.attackAnim2.index;
			customAttackEntity(targetedEntity, targetedEntity.getDistanceToEntity(this));
			this.attackTime = (int) attackAnimTime;
		}


		//Check If Entity Should START Charging
		if(this.chargeCooldown == 0 && charge == false  && targetedEntity != null && this.getDistanceToEntity(targetedEntity) < 20.0f){
			this.charge = true;
		}
		//Decrement chargeCooldown. when this is zero. Entity can charge again
		chargeCooldown = (float)Math.max(chargeCooldown-0.1,0);
		//If entity is charging, then chargeTime should be decremented
		if(charge == true){
			timeLeftCharging = Math.max(timeLeftCharging -= 0.1, 0);
		}
		//*Decrement chargeTimer. when this is zero charge should be set to false, to stop spinning. 
		//and chargeTime should be set back to start
		if (timeLeftCharging == 0) {
			charge = false;
			timeLeftCharging = chargeTime;
			this.chargeCooldown = 10;
		}





		//If angry, calm down
		Math.max(angerLevel-1,0);
		Math.max(stampedeTime-1,0);

		counter++;
		super.onUpdate();

		if(this.entityState == listStates.idle.index){
			this.renderYawOffset = this.rotationYaw = 0f;
			this.motionY = this.motionY > 0 ? 0 : this.motionY;
		}

		if(hover == true){
			this.motionY = 0;
			if(counter > (20*10) ){
				hover = false;
			}
		}

	}

	private float changeNavigatorSpeed(float newMoveSpeed){
		PathEntity tempPathEntity = this.getNavigator().getPath();
		this.getNavigator().setPath(tempPathEntity, newMoveSpeed);
		return newMoveSpeed;
	}

	public double normalizeTo360(double angle){

		while(angle<0 || angle > 360){
			if(angle<0.0){
				angle += 360;
			}
			if(angle>360.0){
				angle -= 360;
			}
		}
		return angle;
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	@Override
	public boolean canBePushed()
	{
		if(entityState == listStates.idle.index){
			return false;
		}else{
			return !this.isDead;
		}

	}


	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
	{
		Entity var3 = par1DamageSource.getEntity();

		if (var3 instanceof EntityPlayer)
		{
			//			List var4 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0D, 32.0D, 32.0D));
			//			Iterator var5 = var4.iterator();
			//
			//			while (var5.hasNext())
			//			{
			//				Entity var6 = (Entity)var5.next();
			//
			//				if (var6 instanceof EntityMimic)
			//				{
			//					EntityMimic var7 = (EntityMimic)var6;
			//					var7.becomeAngryAt(var3);
			//				}
			//			}
			this.becomeAngryAt(var3);
		}
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	/**
	 * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
	 */
	@Override
	protected void attackEntity(Entity par1Entity, float par2)
	{
		//        if (this.attackTime <= 0 && par2 < 4.7 && par1Entity.boundingBox.maxY > this.boundingBox.minY && par1Entity.boundingBox.minY < this.boundingBox.maxY)
		//        {
		//            this.attackTime = 20;
		//            this.attackEntityAsMob(par1Entity);
		//        }
	}

	/**
	 * Time remaining during which the Animal is sped up and flees.
	 */
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




		if (var1)
		{
			this.pathToEntity = this.worldObj.getEntityPathToXYZ(this, var2, var3, var4, 10.0F, true, false, false, true);
		}

		this.worldObj.theProfiler.endSection();
	}

	/**
	 * Determines if an entity can be despawned, used on idle far away entities
	 */
	protected boolean canDespawn()
	{
		return false;
	}


	/**
	 * Takes a coordinate in and returns a weight to determine how likely this creature will try to path to the block.
	 * Args: x, y, z
	 */
	public float getBlockPathWeight(int par1, int par2, int par3)
	{
		/*This is currently Copied from Mammoth, eventually change this to favor pathing to TreeEnt Mother Tree*/

		/*Add Favor Distance away from Villages / Torches */

		/*Calculate Centroid Of Nearby Mammoths, as Well as the Herd*/

		//    	List<Vec3> centroids = new ArrayList<Vec3>();
		//    	
		//    	AxisAlignedBB var15 = this.boundingBox.copy();
		//        var15 = var15.expand(100, 60, 100);
		//        List nearbyEntities = this.worldObj.getEntitiesWithinAABB(EntityTreeEnt.class, var15);
		//        Vec3 herdCentroid = Vec3.createVectorHelper(0, 0, 0);
		//        int i = 0;
		//        if (nearbyEntities != null && !nearbyEntities.isEmpty()){
		//            Iterator var10 = nearbyEntities.iterator();
		//            while (var10.hasNext()){
		//                Entity var4 = (Entity)var10.next();
		//
		//                if (var4 instanceof EntityTreeEnt){
		//                    centroids.add(i, Vec3.createVectorHelper(var4.posX, var4.posY, var4.posZ) );
		//                    herdCentroid = Vec3.createVectorHelper(herdCentroid.xCoord + var4.posX, herdCentroid.yCoord + var4.posY, herdCentroid.zCoord + var4.posZ);
		//                    i++;
		//                }
		//            }
		//        }
		//        herdCentroid = Vec3.createVectorHelper(herdCentroid.xCoord/i, herdCentroid.yCoord/i, herdCentroid.zCoord/i);


		return (30 - worldObj.getSavedLightValue(EnumSkyBlock.Block, (int)this.posX, (int)this.posY, (int)this.posZ));
	}


	/**
	 * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
	 */
	protected void customAttackEntity(Entity par1Entity, float par2)
	{
		if (par2 < 1.5 && par1Entity.boundingBox.maxY > this.boundingBox.minY && par1Entity.boundingBox.minY < this.boundingBox.maxY)
		{
			this.attackEntityAsMob(par1Entity);
		}
	}


	/**
	 * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
	 * (Animals, Spiders at day, peaceful PigZombies).
	 */
	//	protected Entity findPlayerToAttack()
	//	{
	//		return this.angerLevel == 0 ? null : super.findPlayerToAttack();
	//	}

	/**
	 * Causes this PigZombie to become angry at the supplied Entity (which will be a player).
	 */
	private void becomeAngryAt(Entity par1Entity)
	{
		//		this.entityToAttack = par1Entity;
		this.targetedEntity = par1Entity;
		this.entityState = listStates.attacking.index;
		this.angerLevel = 400 + this.rand.nextInt(400);
		this.randomSoundDelay = this.rand.nextInt(40);
	}

	/**
	 * Returns the item ID for the item the mob drops on death.
	 */	
	protected int getDropItemId()
	{
		if(rand.nextBoolean()){
			return Item.rottenFlesh.itemID;
		}else{
			return Item.beefRaw.itemID;
		}
	}

	/**
	 * Drop 0-2 items of this living's type
	 */
	protected void dropFewItems(boolean par1, int par2){
		int var3 = rand.nextInt(1 + par2);
		for (int i = 0; i < var3; i++) {
			ItemStack loot = CustomEntityList.mimic.get().getLootItem(rand);
			if(loot != null){
				entityDropItem(loot, 1);
			}
		}
	}

	protected void dropRareDrop(int par1) {
		//        if (par1 > 0)
		//        {
		//            ItemStack var2 = new ItemStack(Item.bow);
		//            EnchantmentHelper.addRandomEnchantment(this.rand, var2, 5);
		//            this.entityDropItem(var2, 0.0F);
		//        }
		//        else
		//        {
		//            this.dropItem(Item.bow.itemID, 1);
		//        }
	}


	//	Called when player interacts with mob, eg get milk, saddle
	public boolean interact(EntityPlayer par1EntityPlayer){
		this.becomeAngryAt(par1EntityPlayer);

		return super.interact(par1EntityPlayer);
	}
}