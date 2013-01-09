package projectzulu.common.mobs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumEntitySize;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAIMoveTwardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.World;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;

public class EntitySandWorm extends EntityMob {
	/** deincrements, and a distance-to-home check is done at 0 */
	private int homeCheckTimer = 0;
	Village villageObj = null;
	private int attackTimer;
	private int holdRoseTick;
	public int pie = 0;
	public int counter = 0;
	public boolean isHidden = false;
	public int lastAttacked = 0;

	boolean FirstRun = true;
	public EntitySandWorm(World par1World) {
		super(par1World);
		
		this.myEntitySize = EnumEntitySize.SIZE_6;
		setSize(1.5f, 1.0f);
		
		//boundingBox.setBounds(-20,-20,-5.0,0.05,0.5,2.5);		
		//this.setSize(1.4F, 2.9F);
		this.moveSpeed = 0.22f;
		this.getNavigator().setAvoidsWater(false);
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, this.moveSpeed, true));
		this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, this.moveSpeed, 32.0F));
		this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, this.moveSpeed, true));
		this.tasks.addTask(4, new EntityAIMoveTwardsRestriction(this, this.moveSpeed));
		//this.tasks.addTask(6, new EntityAIWander(this, this.moveSpeed));
		//this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		//this.tasks.addTask(8, new EntityAILookIdle(this));
		//this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, 16.0F, 0, false));

	}
	
	/** As if 1.4.2 Represents what was Attack Strength in 1.3.2 */
	@Override
	public int getAttackStrength(Entity par1Entity) {
		switch (par1Entity.worldObj.difficultySetting) {
		case 0:
			return 3; 
		case 1:
			return 3; 
		case 2:
			return 4; 
		case 3:
			return 6; 
		default:
			return 3;
		}
    }

	protected void entityInit() {
		super.entityInit();
		//        this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
	}

	@Override
	public String getTexture() {
		if (isHidden) {
			this.texture = DefaultProps.mobDiretory + "SandWorm_hidden.png";
		}else{
			this.texture = DefaultProps.mobDiretory + "SandWorm.png";
		}
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
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);

		if(super.getCanSpawnHere() && this.worldObj.canBlockSeeTheSky(var1, var2, var3)){
			ProjectZuluLog.info("Successfully spawned %s at X:%s Y:%s Z:%s in %s",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			return true;
		}else{
//			ProjectZuluLog.info("Failed to spawn %s at X:%s Y:%s Z:%s in %s, Spawning Location Inhospitable",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			return false;
		}

		
//		return	worldObj.getClosestPlayerToEntity(this, 24) == null
//				&& worldObj.getSavedLightValue(EnumSkyBlock.Block, var1, var2, var3) < 1
//				&& this.worldObj.checkIfAABBIsClear(this.boundingBox) 
//				&& this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() 
//				&& !this.worldObj.isAnyLiquid(this.boundingBox)
//				&& this.worldObj.canBlockSeeTheSky(var1, var2, var3);
		//return this.isValidLightLevel() && super.getCanSpawnHere();
	}

//	@Override
//	protected boolean isValidLightLevel()
//	{
//		int var1 = MathHelper.floor_double(this.posX);
//		int var2 = MathHelper.floor_double(this.boundingBox.minY);
//		int var3 = MathHelper.floor_double(this.posZ);
//
//		if (this.worldObj.canBlockSeeTheSky(var1, var2, var3)) {
//			return true;
//		}else{
//			return false;
//		}
//
//	}
	/**
	 * main AI tick function, replaces updateEntityActionState
	 */
	protected void updateAITick(){
		super.updateAITick();
	}

	public int getMaxHealth(){
		return 20;
	}

	/**
	 * Decrements the entity's air supply when underwater
	 */
	protected int decreaseAirSupply(int par1){
		return par1;
	}

	// When Entity Collides with player if it is Hidden, mark it as not
	@Override
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
		isHidden = false;
		lastAttacked += 200;
		super.onCollideWithPlayer(par1EntityPlayer);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		isHidden = false;
		lastAttacked += 200;
		return super.attackEntityFrom(par1DamageSource, par2);
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	public void onLivingUpdate(){
		if(this.worldObj.isDaytime() && !this.worldObj.isRemote && counter % (10*20) == 0){
			heal(1);
		}
		
//		if(FirstRun){
//			AxisAlignedBB newBoundingBox = boundingBox.copy(); 
//			newBoundingBox.expand(5, 5, 5);
//			boundingBox.setBB(newBoundingBox);
//			FirstRun = false;
//		}
		
		
		super.onLivingUpdate();


		if (isHidden) {
			this.moveSpeed = changeNavigatorSpeed(0.0f);
			this.getNavigator().clearPathEntity();
			//changeNavigatorSpeed(0.0f);
		}
		else{
			this.moveSpeed = changeNavigatorSpeed(0.22f);
		}

		counter++;
		lastAttacked = (int)Math.max(lastAttacked-1, 0.0);
		if (lastAttacked == 0) {
			isHidden = true;
		}
	} 

	private float changeNavigatorSpeed(float newMoveSpeed){
		PathEntity tempPathEntity = this.getNavigator().getPath();
		this.getNavigator().setPath(tempPathEntity, newMoveSpeed);
		return newMoveSpeed;
	}

	public int getAttackTimer(){
		return this.attackTimer;
	}


	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound(){
		return null;
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound(){
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
		this.worldObj.playSoundAtEntity(this, "sand", 1.0F, 1.0F);
	}

	public int getHoldRoseTick(){
		return this.holdRoseTick;
	}
    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk(){
        return 1;
    }


	/**
	 * Drop 0-2 items of this living's type
	 */
	@Override
	protected void dropFewItems(boolean par1, int par2){
		int var3 = rand.nextInt(2 + par2);
		for (int i = 0; i < var3; i++) {
			ItemStack loot = CustomEntityList.sandworm.get().getLootItem(rand);
			if(loot != null){
				entityDropItem(loot, 1);
			}
		}
	}
}
