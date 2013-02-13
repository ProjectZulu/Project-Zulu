package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import projectzulu.common.api.BlockList;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIFollowParent;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAIMate;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIPanic;
import projectzulu.common.mobs.entityai.EntityAIStayStill;
import projectzulu.common.mobs.entityai.EntityAITempt;
import projectzulu.common.mobs.entityai.EntityAIWander;
import cpw.mods.fml.common.Loader;

public class EntityArmadillo extends EntityGenericAnimal implements IAnimals {	
	
	/* Model Assist Variables, Legacy: Code should be Reworked without them */
	public EntityModelRotation eWHOLE = new EntityModelRotation();
	public EntityModelRotation eHEADPIECE = new EntityModelRotation();
	public EntityModelRotation eREARRTR1 = new EntityModelRotation();
	public EntityModelRotation eREARRTR2 = new EntityModelRotation();
	public EntityModelRotation eREARRTR3 = new EntityModelRotation();
	public EntityModelRotation etail = new EntityModelRotation();
	public EntityModelRotation eleg3 = new EntityModelRotation();
	public EntityModelRotation eleg4 = new EntityModelRotation();
	
	/* General Abilite Variabeles */
	int ticksToCheckAbilities = 3;

	/* In Cover Variables*/
	int inCoverTimer = 0;
	static final int inCoverTimerMax = 20;

	/* Charge Variables */
	protected boolean isCharging = false;
	public boolean isCharging(){
		return isCharging;
	}
	protected float timeSinceLastCharge = 5f;
	protected float chargeTriggerThreshold = 10f*20f + rand.nextInt(10*20);
	protected float chargeTime = 0.2f*chargeTriggerThreshold;
	protected int chargeSpeedModifier = 2;

	public EntityArmadillo(World par1World) {
		super(par1World);
		this.moveSpeed = 0.25f;
		boundingBox.setBounds(-0.05,-0.05,-0.15,0.05,1.8,0.15);
		
		this.moveSpeed = 0.3f;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, this.moveSpeed));

		this.tasks.addTask(2, new EntityAIStayStill(this, EntityStates.inCover));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, false));
//		this.tasks.addTask(4, new EntityAIFollowOwner(this, this.moveSpeed,	10.0F, 2.0F));

		this.tasks.addTask(5, new EntityAIMate(this, this.moveSpeed));
		this.tasks.addTask(6, new EntityAITempt(this, this.moveSpeed, Item.spiderEye.itemID, false));
		this.tasks.addTask(7, new EntityAIFollowParent(this, this.moveSpeed));
		this.tasks.addTask(9, new EntityAIWander(this, this.moveSpeed, 120));

//		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
//		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3,	new EntityAIHurtByTarget(this, false, false));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityPlayer.class, 16.0F, 0, true));
//		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityLiving.class, 16.0F, 0, false, true, IMob.mobSelector));

	}

	/** 
	 * Set Entity Attack Strength
	 * This is overriden by each Entity if deviations from default are desired
	 **/
	@Override
	protected int getAttackStrength(World par1World){
		switch (par1World.difficultySetting) {
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
	
	@Override
	public String getTexture() {
		this.texture = DefaultProps.mobDiretory + "Armadillo.png";
		return this.texture;
	}


	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	@Override
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
		boolean wasSuccesful = false;
		
		if (CustomEntityList.armadillo.get().secondarySpawnRate - rand.nextInt(100) >= 0 && super.getCanSpawnHere()
				&& worldObj.canBlockSeeTheSky(var1, var2, var3)){
			wasSuccesful = true;
		}
		
		if(CustomEntityList.armadillo.get().reportSpawningInLog){
			if(wasSuccesful){
				ProjectZuluLog.info("Successfully spawned %s at X:%s Y:%s Z:%s in %s",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}else{
				ProjectZuluLog.info("Failed to spawn %s at X:%s Y:%s Z:%s in %s, Spawning Location Inhospitable",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}
		}
		return wasSuccesful;
	}

	public int getMaxHealth(){
		return 12;
	}
	
	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue() {
		if(getEntityState() == EntityStates.inCover){
			return 30;
		}else{
			return 4;
		}
	}
	
	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound(){ return "sounds.armadilloliving"; }
	
	@Override
	public void updateAIState() {
		if(inCoverTimer > 0){
			entityState = EntityStates.inCover;
		}else{
			super.updateAIState();
		}
	}
	
	@Override
	protected void updateAITasks() {
		super.updateAITasks();

		if(ticksExisted % ticksToCheckAbilities == 0){
			/* Checks if There is a player that can see Armadillo and is using a bow. If so, Take Cover
			/* Also, if there is no Target, then don't take cover */
			EntityPlayer tempE = this.worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);

			boolean canSee = false;
			boolean isFacing = false;
			if(tempE != null){
				//Condition 1: Check if player is using an item, and if so is it a bow
				int var1 = Item.bow.itemID;

				//Condition 2: Is the player facing the target. "boolean canSee"
				double angleEntToPlayer = Math.atan2(posX-tempE.posX, tempE.posZ-posZ)*(180.0/Math.PI)+180;
				double playerRotationYaw = (double)tempE.rotationYaw;
				double difference = Math.abs( MathHelper.wrapAngleTo180_double( normalizeTo360(angleEntToPlayer) - normalizeTo360(playerRotationYaw) ));
				if (difference < 90){
					isFacing = true;
				}
				
				//Condition 3: Can the player see the target
				canSee = this.worldObj.rayTraceBlocks(worldObj.getWorldVec3Pool().getVecFromPool(tempE.posX, tempE.posY+(double)tempE.getEyeHeight(), tempE.posZ),
						worldObj.getWorldVec3Pool().getVecFromPool(this.posX,this.posY,this.posZ)) == null;
			}
			/* If any of the conditions above failed, then Armadillo should not be in Cover */
			if(tempE == null || canSee == false || tempE.isUsingItem() == false || isFacing == false || tempE.inventory.getCurrentItem().itemID != Item.bow.itemID){
				inCoverTimer = Math.max(inCoverTimer - ticksToCheckAbilities, 0);
			}else{
				/* Reminder: This only occurs when all the above are true. */
				inCoverTimer = inCoverTimerMax;
			}
		}
		
	}
	
	@Override
	public void onLivingUpdate() {
		if(this.worldObj.isDaytime() && !this.worldObj.isRemote && ticksExisted % (10*20) == 0){
			heal(1);
		}
		
		if(ticksExisted % ticksToCheckAbilities == 0){
			/* Check If Entity Should START Charging */
			if(this.timeSinceLastCharge > chargeTriggerThreshold && !isCharging){// && targetedEntity != null && this.getDistanceToEntity(targetedEntity) < 20.0f){
				this.isCharging = true;
				this.timeSinceLastCharge = 0;
				chargeTriggerThreshold = 10f*20f + rand.nextInt(10*20);
			}

			/* Check If Entity Should STOP Charging */
			if(isCharging && this.timeSinceLastCharge > chargeTime){
				this.isCharging = false;
			}

			/* Increase Time Since Last Charge */
			this.timeSinceLastCharge += ticksToCheckAbilities;
		}
		
		super.onLivingUpdate();
	}
	
	@Override
	public float getSpeedModifier() {
		float var1 = super.getSpeedModifier();
		if(isCharging){
			var1 *= chargeSpeedModifier;
		}
		return super.getSpeedModifier();
	}
		
	private double normalizeTo360(double angle){

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

	@Override
	public boolean isValidBreedingItem(ItemStack itemStack) {
		if(itemStack != null && itemStack.getItem().itemID == Item.spiderEye.itemID){
			return true;
		}else{
			return super.isValidBreedingItem(itemStack);
		}
	}

	/**
	 * Drop 0-2 items of this living's type
	 */
	@Override
	protected void dropFewItems(boolean par1, int par2){
		int var3 = rand.nextInt(2 + par2);
		for (int i = 0; i < var3; i++) {
			ItemStack loot = CustomEntityList.armadillo.get().getLootItem(rand);
			if(loot != null){
				entityDropItem(loot, 1);
			}
		}
	}
	
	@Override
	protected void dropRareDrop(int par1) {
		if(Loader.isModLoaded(DefaultProps.BlocksModId)){
			if(BlockList.mobHeads.isPresent()){
				entityDropItem(new ItemStack(BlockList.mobHeads.get().blockID,1,2), 1);
			}
		}
		super.dropRareDrop(par1);
	}
}
