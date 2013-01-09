package projectzulu.common.mobs;

import net.minecraft.entity.passive.IAnimals;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import projectzulu.common.API.ItemBlockList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIFlyingWander;
import projectzulu.common.mobs.entityai.EntityAIStayStill;
import cpw.mods.fml.common.Loader;

public class EntityFinch extends EntityGenericAnimal implements IAnimals{
	int stayStillTimer = 0;
	public EntityFinch(World par1World){
		super(par1World);
		//		noClip = true;
		this.setSize(0.5f, 0.5f);
		this.moveSpeed = 0.22f;
		this.maxFlightHeight = 5;
		this.getNavigator().setAvoidsWater(true);		
		this.tasks.addTask(2, new EntityAIStayStill(this, EntityStates.posture));

//        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, this.moveSpeed, false));
		this.tasks.addTask(6, new EntityAIFlyingWander(this, this.moveSpeed));
//        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, false));		
//        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityPlayer.class, 16.0F, 0, true));
	}
	
	@Override
	public boolean defaultGrounded() {
		return false;
	}

	@Override
	protected boolean canDespawn() {
		return true;
	}
	
	/**
	 * Called when the mob is falling. Calculates and applies fall damage.
	 */
	protected void fall(float par1){}

	/**
	 * Takes in the distance the entity has fallen this tick and whether its on the ground to update the fall distance
	 * and deal fall damage if landing on the ground.  Args: distanceFallenThisTick, onGround
	 */
	protected void updateFallState(double par1, boolean par3) {}
	
	@Override
	public int getMaxHealth() {
		return 8;
	}
	
	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound() {
		return "mods.sounds.birdhurt";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound() {
		return "mods.sounds.bird";
	}

	/**
	 * Plays step sound at given x, y, z for the entity
	 */
	protected void playStepSound(int par1, int par2, int par3, int par4) {
		this.worldObj.playSoundAtEntity(this, "mods.sounds.birdplop", 1.0F, 1.0F);
	}
	
	@Override
	public boolean isValidBreedingItem(ItemStack itemStack) {
		return false;
	}
	
	@Override
	protected boolean shouldPanic() {
		return true;
	}

	@Override
	public void updateAIState() {
		if(stayStillTimer > 0){
			entityState = EntityStates.posture;
		}else{
			super.updateAIState();
		}
	}
	
	protected void updateAITasks() {
		 super.updateAITasks();
		 
		 /* If We are above Solid Ground, have a small Chance at Landing */
		 if (this.rand.nextInt(100) == 0 && this.worldObj.isBlockNormalCube(MathHelper.floor_double(this.posX), (int)this.posY - 1, MathHelper.floor_double(this.posZ))){
			 stayStillTimer = 400;
		 }
		 
		 /* If Posing, Check if We should Stay Flee */
		 if(getEntityState() == EntityStates.posture){
			 /* If we are not on a Normal Block, Fly Free */
			 if (!this.worldObj.isBlockNormalCube(MathHelper.floor_double(this.posX), (int)this.posY - 1, MathHelper.floor_double(this.posZ))){
				 stayStillTimer = 0;
			 }
			 else{
				 /* Chance to Chance Direction We are Facing ?--Do We Care?--? */
				 if (this.rand.nextInt(200) == 0) {
					 this.rotationYawHead = (float)this.rand.nextInt(360);
				 }
				 
				 /* If Player is nearby, Entity Should Fly away, think Pigeons */
				 if (this.worldObj.getClosestPlayerToEntity(this, 4.0D) != null || this.rand.nextInt(400) == 0 ){
					 stayStillTimer = 0;
				 }
			 }
			 /* Deincrement stayStillTimer, if it hits zero Entity Should Fly
			  * : Note that timer is likely being set to max again by the initial shouldSit check */
			 stayStillTimer = Math.max(stayStillTimer-1, 0);
		 }
	}

	 /**
	  * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	  * prevent them from trampling crops
	  */
	 protected boolean canTriggerWalking() {
		 return false;
	 }
	 
	 @Override
	 protected void dropRareDrop(int par1) {
			if(Loader.isModLoaded(DefaultProps.BlocksModId) && ItemBlockList.mobHeads.isPresent()){
			 entityDropItem(new ItemStack(ItemBlockList.mobHeads.get().blockID,1,0), 1);
		 }
		 super.dropRareDrop(par1);
	 }
}
