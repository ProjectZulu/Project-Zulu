package projectzulu.common.mobs;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.village.Village;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.ItemBlockList;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.common.Loader;

public class CopyOfEntityVultureWorkingAsOf053 extends EntityFlying// implements IMob
{
	/** deincrements, and a distance-to-home check is done at 0 */
	private int homeCheckTimer = 0;
	Village villageObj = null;
	private int attackTimer;
	private int holdRoseTick;
	public int pie = 0;

	private int maxTargetHealthToAttack = 15;
	
	int testState = 0;

	int distanceToCircle = 3;
	int distToTargetXZ = 0;

	boolean manyVultures = false;

	Vec3 distToWayPoint = Vec3.createVectorHelper(0, 0, 0);

	public boolean isHidden = false;
	public int lastAttacked = 0;

	public EntityModelRotation eFEETROT = new EntityModelRotation();
	public EntityModelRotation eBODYROT = new EntityModelRotation();

	public EntityModelRotation eLEFTWING = new EntityModelRotation();
	public EntityModelRotation ewingleft4 = new EntityModelRotation();
	public EntityModelRotation ewingleft3 = new EntityModelRotation();
	public EntityModelRotation ewingleft2 = new EntityModelRotation();
	public EntityModelRotation ewingleft1 = new EntityModelRotation();

	public EntityModelRotation eRIGTHWING = new EntityModelRotation();
	public EntityModelRotation ewingrig4 = new EntityModelRotation();
	public EntityModelRotation ewingrig3 = new EntityModelRotation();
	public EntityModelRotation ewingrig2 = new EntityModelRotation();
	public EntityModelRotation ewingrig1 = new EntityModelRotation();

	public EntityModelRotation eNECKROT1 = new EntityModelRotation();
	public EntityModelRotation eNECKROT2 = new EntityModelRotation();
	public EntityModelRotation eNECKROT3 = new EntityModelRotation();
	public EntityModelRotation eHEADROT = new EntityModelRotation();
	public EntityModelRotation eTAILROT = new EntityModelRotation();

	//Variables used to describe vulture state for logic and animation
	public enum listStates {
		idle(0), 
		following(1),
		attacking(2);

		private final int index;

		listStates(int index) {
			this.index = index;
		}
		public int index() { 
			return index; 
		}
	}
	public int entityState = 0;
	private ChunkCoordinates targetPosition;

	public boolean facingPlayer = false;

	public int counter = 0;

	/*
	 * Ghast Variables
	 */
	public int courseChangeCooldown = 0;
	public double waypointX;
	public double waypointY;
	public double waypointZ;
	private Entity targetedEntity = null;

	/** Cooldown time between target loss and new target aquirement. */
	private int aggroCooldown = 0;
	public int prevAttackCounter = 0;
	public int attackCounter = 0;

	private int posChangeTracker = 0;

	Vec3[] lastPosition = new Vec3[4];

	boolean debugBoolean = false;
	public CopyOfEntityVultureWorkingAsOf053(World par1World)
	{

		super(par1World);
		//		noClip = true;

		//		boundingBox.setBB( boundingBox.getBoundingBox(-0.05,-0.5,-7, 0.05,0.5,1.0) );
		this.setSize(1.0f, 1.4f);
		//boundingBox.setBounds(-20,-20,-5.0,0.05,0.5,2.5);		
		//this.setSize(1.4F, 2.9F);
		this.moveSpeed = 0.22f;
	}

	/**
	 * Called when the mob is falling. Calculates and applies fall damage.
	 */
	protected void fall(float par1) {}


	protected void entityInit(){
		super.entityInit();
		this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
	}

	@Override
	public String getTexture(){
		this.texture = "/mods/Vulture.png";
		return super.getTexture();
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	public boolean isAIEnabled(){
		return true;
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	@Override
	public boolean getCanSpawnHere(){

		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);

		//		AxisAlignedBB copyOfBB = this.boundingBox.copy();
		//		copyOfBB = copyOfBB.expand(120f, 80f, 120f);
		//		List allEntiiesList = this.worldObj.getEntitiesWithinAABB(EntityVulture.class, copyOfBB);

		if(CustomEntityList.vulture.get().secondarySpawnRate - rand.nextInt(100) >= 0){
			return  worldObj.getClosestPlayerToEntity(this, 32) == null &&
					this.worldObj.getSavedLightValue(EnumSkyBlock.Block, var1, var2, var3) < 1 && 
					this.worldObj.canBlockSeeTheSky(var1, var2, var3) && 
					super.getCanSpawnHere();
			//					&& allEntiiesList.size() < mod_ProjectZulu.maxSpawnInRadius;
		}else{
			return false;
		}

	}

	public int getMaxHealth(){
		return 14;
	}

	/**
	 * Decrements the entity's air supply when underwater
	 */
	protected int decreaseAirSupply(int par1){
		return par1;
	}

	//	// When Entity Collides with player if it is Hidden, mark it as not
	//	@Override
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {

		if ( this.attackTime <= 0  && par1EntityPlayer.getDistanceToEntity(this) < 1.5 ) {
			this.attackEntity(par1EntityPlayer, 1);
			this.attackTime = 20;
		}
		super.onCollideWithPlayer(par1EntityPlayer);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	public void onLivingUpdate(){

		if(this.worldObj.isDaytime() && !this.worldObj.isRemote && counter % (1*20) == 0){
			heal(1);
		}

		if(worldObj.difficultySetting == 0){
			this.setDead();
		}
		this.despawnEntity();

		if(targetedEntity == null){
			//Get NearestPlayer Distance

			Entity nearbyPlayer = this.worldObj.getClosestPlayerToEntity(this, 100.0D);
			if(nearbyPlayer != null){ 
				targetedEntity = nearbyPlayer;
				distToTargetXZ = (int) Math.sqrt( Math.pow(nearbyPlayer.posX-this.posX, 2) + Math.pow(nearbyPlayer.posZ-this.posZ, 2) ); 
			}
		}else{
			distToTargetXZ = (int) Math.sqrt( Math.pow(targetedEntity.posX-this.posX, 2) + Math.pow(targetedEntity.posZ-this.posZ, 2) ); 
		}

		boolean canSee = false;
		if(targetedEntity != null){
			canSee = this.worldObj.rayTraceBlocks(worldObj.getWorldVec3Pool().getVecFromPool(targetedEntity.posX, targetedEntity.posY+(double)targetedEntity.getEyeHeight(), targetedEntity.posZ),
					worldObj.getWorldVec3Pool().getVecFromPool(this.posX,this.posY,this.posZ)) == null;
		}
				
		/* Handle State Updates  */
		//If Player goes outside attacking range, go idle (Or if goes creative)
		if(targetedEntity != null && (targetedEntity instanceof EntityPlayer && ((EntityPlayer)targetedEntity).capabilities.isCreativeMode 
				 || !canSee) ){
			entityState = listStates.idle.index;
			targetedEntity = null;
			manyVultures = false;
		}else if( (entityState == listStates.attacking.index || entityState == listStates.following.index) && (distToTargetXZ > 32 || targetedEntity == null || targetedEntity.isDead)){
			entityState = listStates.idle.index;
			targetedEntity = null;
		}else if(entityState == listStates.idle.index && targetedEntity != null && distToTargetXZ < 16){
			entityState = listStates.following.index;
		}else if(entityState == listStates.following.index && targetedEntity != null && counter % 60==0){
			int nearbyVultures = 0;
			AxisAlignedBB var15 = this.boundingBox.copy();
			var15 = var15.expand(10, 10, 10);
			List nearbyEntities = this.worldObj.getEntitiesWithinAABB(CopyOfEntityVultureWorkingAsOf053.class, var15);
			if (nearbyEntities != null && !nearbyEntities.isEmpty()){
				Iterator var10 = nearbyEntities.iterator();

				while (var10.hasNext()){
					Entity var4 = (Entity)var10.next();

					if (var4 instanceof CopyOfEntityVultureWorkingAsOf053 && ( ((CopyOfEntityVultureWorkingAsOf053)var4).entityState == listStates.following.index  || ((CopyOfEntityVultureWorkingAsOf053)var4).entityState == listStates.attacking.index ) ){
						nearbyVultures += 1;
					}
				}
			}
			if(  ((EntityLiving)targetedEntity).getHealth() < maxTargetHealthToAttack && ((EntityLiving)targetedEntity).getHealth() < nearbyVultures*4  ){
				entityState = listStates.attacking.index;
			}
		}		

		/* If Target Position is not an Air Block, Target Position = null (i.e. Don't try to go there) */
		if (this.targetPosition != null && (!this.worldObj.isAirBlock(this.targetPosition.posX, this.targetPosition.posY, this.targetPosition.posZ) || this.targetPosition.posY < 1)){
			this.targetPosition = null;
		}

		/* Handle Movement Target Based On State*/
		if(entityState == listStates.idle.index){

			/* Idle Movement: If We don't have a target lets wander to a random place */
			if (this.targetPosition == null || this.rand.nextInt(30) == 0 || this.targetPosition.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0F){
				double targetY;
				if ( posY - 3 < worldObj.getPrecipitationHeight( (int)posX, (int)posZ) ) {
					targetY = this.posY + (double)((this.rand.nextFloat() * 1.0F + 0.0F) * 7.0F);
				}else if( posY > worldObj.getPrecipitationHeight( (int)posX, (int)posZ) + 20 ){
					targetY = this.posY + (double)((this.rand.nextFloat() * 1.0F - 1.0F) * 7.0F);
				}else{
					targetY = this.posY + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 7.0F);
				}

				this.targetPosition = new ChunkCoordinates((int)this.posX + this.rand.nextInt(15) - 7, (int) targetY, (int)this.posZ + this.rand.nextInt(15) - 7);
			}
			adjustRotationToWaypoint();
			
		}else if(entityState == listStates.following.index && targetedEntity != null){	

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
			/** Y axis WayPoint  (Fly To Height 12 Blocks above player)  */
			targetY = this.posY + (targetedEntity.posY + 12 - this.posY) / Math.abs(targetedEntity.posY + 12 - this.posY);

			this.targetPosition = new ChunkCoordinates((int)targetX, (int)targetY, (int)targetZ);
			this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;

		}else if(entityState == listStates.attacking.index && targetedEntity != null){
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


			//        	if (this.courseChangeCooldown-- <= 0 || this.targetPosition.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 1.0D ||  1 - this.rand.nextInt(10) > 0){
			//        		adjustAttackingWayPoint(targetedEntity, distToTargetX, distToTargetY, distToTargetZ);
			//        	}
			//        	this.courseChangeCooldown += this.rand.nextInt(10) + 6;

			this.targetPosition = new ChunkCoordinates((int)targetX, (int)targetY, (int)targetZ);
			this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.targetedEntity.posX - this.posX, this.targetedEntity.posZ - this.posZ)) * 180.0F / (float)Math.PI;
		}

		/* Get The Direction I want to travel in */
		double var1 = (double)this.targetPosition.posX + 0.5D - this.posX;
		double var3 = (double)this.targetPosition.posY + 0.1D - this.posY;
		double var5 = (double)this.targetPosition.posZ + 0.5D - this.posZ;

		/* Change Velocity */
		/* Normalize the Direction I want to travel in, then add and scale it to Motion */
		this.motionX += (Math.signum(var1) * 0.5D - this.motionX) * 0.10000000149011612D*0.3D;
		this.motionY += (Math.signum(var3) * 0.699999988079071D - this.motionY) * 0.10000000149011612D*0.3D;
		this.motionZ += (Math.signum(var5) * 0.5D - this.motionZ) * 0.10000000149011612D*0.3D;
		float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
		float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
		this.moveForward = 0.5F;
		this.rotationYaw += var8;

		//			adjustMovementToWayPoint(distToWayPoint.xCoord, distToWayPoint.yCoord, distToWayPoint.zCoord, 0.1, 0.1, 0.1, 0.12f);



		counter++;
		this.prevAttackCounter = this.attackCounter;
		attackTime--;
		super.onLivingUpdate();
	}

	private void adjustRotationToWaypoint() {		
		double var8 = targetPosition.posX - this.posX;
		double var10 = targetPosition.posZ - this.posZ;
		float var14 = (float)(Math.atan2(var10, var8) * 180.0D / Math.PI);// - 90.0F;
		float var15 = MathHelper.wrapAngleTo180_float(var14 - this.rotationYaw);

		if (var15 > 30.0F){
			var15 = 30.0F;
		}

		if (var15 < -30.0F){
			var15 = -30.0F;
		}

		this.renderYawOffset = this.rotationYaw += var15;      
	}

	protected void attackEntity(Entity par1Entity, float par2)
	{
		if (this.isPotionActive(Potion.damageBoost))
		{
			par2 += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
		}

		if (this.isPotionActive(Potion.weakness))
		{
			par2 -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
		}

		par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), (int)par2);
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2){
		if(par1DamageSource.getSourceOfDamage() != null){
			entityState = listStates.attacking.index;
			targetedEntity = par1DamageSource.getSourceOfDamage();
		}

		return super.attackEntityFrom(par1DamageSource, par2);
	}


	private void adjustIdleWayPoint(){
		this.waypointX = this.posX + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
		this.waypointZ = this.posZ + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);

		//Ensures that the entity will not target a block into the ground
		if ( posY - 3 < worldObj.getTopSolidOrLiquidBlock( (int)posX, (int)posZ) ) {
			this.waypointY = this.posY + (double)((this.rand.nextFloat() * 1.0F + 0.0F) * 16.0F);
		}else if( posY > worldObj.getTopSolidOrLiquidBlock( (int)posX, (int)posZ) + 20 ){
			this.waypointY = this.posY + (double)((this.rand.nextFloat() * 1.0F - 1.0F) * 16.0F);
		}else{
			this.waypointY = this.posY + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
		}
	}

	private void adjustFollowingWayPoint(Entity targetedEntity, double distToTargetX, double distToTargetY, double distToTargetZ, double distanceToCircle){

		if(Math.abs(targetedEntity.posX - this.posX) >= distanceToCircle / 2 ){
			this.waypointX = this.posX + (targetedEntity.posX - this.posX) / Math.abs(targetedEntity.posX - this.posX)*2 ;
		}else{
			this.waypointX = this.posX + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 1.0F);
		}

		if(Math.abs(targetedEntity.posZ - this.posZ) >= distanceToCircle / 2 ){
			this.waypointZ = this.posZ + (targetedEntity.posZ - this.posZ) / Math.abs(targetedEntity.posZ - this.posZ)*2 ;
		}else{
			this.waypointZ = this.posZ + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 1.0F);
		}

		/** Y axis WayPoint  (Fly To Height 12 Blocks above player)  */
		this.waypointY = this.posY + (targetedEntity.posY + 12 - this.posY) / Math.abs(targetedEntity.posY + 12 - this.posY);
	}

	/**
	 * Adjusts Waypoint Toward target Entity
	 **/
	private void adjustAttackingWayPoint(Entity targetedEntity, double distToTargetX, double distToTargetY, double distToTargetZ){

		this.waypointX = this.posX + (targetedEntity.posX - this.posX) / Math.abs(targetedEntity.posX - this.posX) ;

		this.waypointZ = this.posZ + (targetedEntity.posZ - this.posZ) / Math.abs(targetedEntity.posZ - this.posZ) ;

		this.waypointY = this.posY + 
				( (targetedEntity.boundingBox.maxY+targetedEntity.boundingBox.minY)/2  - this.posY) / 
				Math.abs( (targetedEntity.boundingBox.maxY+targetedEntity.boundingBox.minY)/2 - this.posY);
		//			this.waypointY = this.posY + (targetedEntity.posY  - this.posY) / Math.abs(targetedEntity.posY - this.posY);

	}

	/**
	 * Adjusts the Speed of the Entity in each direction proportional to the waypoint distance from current point
	 * args: dist2WPX, dist2WPY, dist2WPZ, VelXScale, VelYScale, VelZScale, MaxSpeed in any 1 Direction
	 */
	private void adjustMovementToWayPoint(double distToWPX, double distToWPY, double distToWPZ , double velXScale, double velYScale, double velZScale, float maxSpeed){
		double var7 = distToWPX * distToWPX + distToWPY * distToWPY + distToWPZ * distToWPZ;
		var7 = (double)MathHelper.sqrt_double(var7);

		if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, var7) 
				&& this.worldObj.isAirBlock((int)this.waypointX, (int)this.waypointY, (int)this.waypointZ))
		{

			this.motionX = clamp( (float)(this.motionX + distToWPX / var7 * velXScale), -maxSpeed, maxSpeed);
			this.motionY = clamp( (float)(this.motionY + distToWPY / var7 * velXScale), -maxSpeed, maxSpeed);
			this.motionZ = clamp( (float)(this.motionZ + distToWPZ / var7 * velXScale), -maxSpeed, maxSpeed);

		}
		else
		{
			this.waypointX = this.posX;
			this.waypointY = this.posY;
			this.waypointZ = this.posZ;
		}
	}

	/**
	 * Adjusts the Speed of the Entity in each direction proportional to the waypoint distance from current point, but does so 
	 * args: dist2WPX, dist2WPY, dist2WPZ, VelXScale, VelYScale, VelZScale, MaxSpeed in any 1 Direction
	 */
	private void adjustMovementToWayPoint(double velXScale, double velYScale, double velZScale, float maxSpeed){


		//		distToWayPoint.xCoord = this.waypointX - this.posX;
		//		distToWayPoint.yCoord = this.waypointY - this.posY;
		//		distToWayPoint.zCoord = this.waypointZ - this.posZ;

		double var7 = (this.waypointX - this.posX) * (this.waypointX - this.posX) 
				+ (this.waypointY - this.posY) * (this.waypointY - this.posY) 
				+ (this.waypointZ - this.posZ) * (this.waypointZ - this.posZ);
		var7 = (double)MathHelper.sqrt_double(var7);

		if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, var7) )
		{

			this.motionX = clamp( (float)(this.motionX + (this.waypointX - this.posX) / var7 * velXScale), -maxSpeed, maxSpeed);
			this.motionY = clamp( (float)(this.motionY + (this.waypointY - this.posY) / var7 * velXScale), -maxSpeed, maxSpeed);
			this.motionZ = clamp( (float)(this.motionZ + (this.waypointZ - this.posZ) / var7 * velXScale), -maxSpeed, maxSpeed);

		}
		else
		{
			this.waypointX = this.posX;
			this.waypointY = this.posY;
			this.waypointZ = this.posZ;
		}
	}

	private float clamp(float number, float min, float max){
		return Math.max(Math.min(number, max), min);
	}


	/**
	 * True if the Vulture has an unobstructed line of travel to the waypoint.
	 */
	private boolean isCourseTraversable(double par1, double par3, double par5, double par7)
	{
		double var9 = (this.waypointX - this.posX) / par7;
		double var11 = (this.waypointY - this.posY) / par7;
		double var13 = (this.waypointZ - this.posZ) / par7;
		AxisAlignedBB var15 = this.boundingBox.copy();
		//		var15.expand(1, 1, 1);

		for (int var16 = 1; (double)var16 < par7; ++var16)
		{
			var15.offset(var9, var11, var13);

			if (!this.worldObj.getCollidingBoundingBoxes(this, var15).isEmpty())
			{
				return false;
			}
		}

		return true;
	}

	public boolean interact(EntityPlayer par1EntityPlayer){
		debugBoolean = !debugBoolean;
		return super.interact(par1EntityPlayer);
	}


	private float changeNavigatorSpeed(float newMoveSpeed){
		PathEntity tempPathEntity = this.getNavigator().getPath();
		this.getNavigator().setPath(tempPathEntity, newMoveSpeed);
		return newMoveSpeed;
	}

	//	public void handleHealthUpdate(byte par1){
	//		if (par1 == 4)
	//		{
	//			this.attackTimer = 10;
	//			this.worldObj.playSoundAtEntity(this, "mob.irongolem.throw", 1.0F, 1.0F);
	//		}
	//		else if (par1 == 11)
	//		{
	//			this.holdRoseTick = 400;
	//		}
	//		else
	//		{
	//			super.handleHealthUpdate(par1);
	//		}
	//	}


	public int getAttackTimer()
	{
		return this.attackTimer;
	}


	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound()
	{
		return "sounds.vulturehurt";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound()
	{
		return null;
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	protected String getDeathSound(){
		return null;	
	}

	/**
	 * Plays step sound at given x, y, z for the entity
	 */
	protected void playStepSound(int par1, int par2, int par3, int par4){
		this.worldObj.playSoundAtEntity(this, "mob.irongolem.walk", 1.0F, 1.0F);
	}

	//	public int getHoldRoseTick()
	//	{
	//		return this.holdRoseTick;
	//	}

	/**
	 * Drop 0-2 items of this living's type
	 */
	protected void dropFewItems(boolean par1, int par2){
		int var3 = this.rand.nextInt(2) + this.rand.nextInt(1 + par2);

		for (int var4 = 0; var4 < var3; ++var4)
		{
			this.dropItem(Item.feather.itemID, 1);
		}

		if (this.isBurning())
		{
			this.dropItem(Item.chickenCooked.itemID, 1);
		}
		else
		{
			this.dropItem(Item.chickenRaw.itemID, 1);
		}

	}

	/**
	 * Will return how many at most can spawn in a chunk at once.
	 */
	public int getMaxSpawnedInChunk()
	{
		return 3;
	}

	@Override
	protected void dropRareDrop(int par1) {
		if(Loader.isModLoaded(DefaultProps.BlocksModId) && ItemBlockList.mobHeads.isPresent()){
			entityDropItem(new ItemStack(ItemBlockList.mobHeads.get().blockID,1,16), 1);
		}
		super.dropRareDrop(par1);
	}

}
