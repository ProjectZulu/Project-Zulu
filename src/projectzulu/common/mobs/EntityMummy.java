package projectzulu.common.mobs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTwardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import projectzulu.common.core.DefaultProps;

public class EntityMummy extends EntityMob{
    public EntityMummy(World par1World){
        super(par1World);
        this.setSize(0.6F, 1.4F);
        this.moveSpeed = 0.25F;
        initializeAI();
        this.experienceValue = 0;
    }
    
    public EntityMummy(World par1World, double parx, double pary, double parz){
        super(par1World);
        this.setSize(0.6F, 1.4F);
		setLocationAndAngles(parx, pary, parz, 1, 1);
		setPosition(parx, pary, parz);
		
		//motionX = motionY = motionZ = 0.0D;
        this.moveSpeed = 0.29F;
        
		yOffset = 0.0f;
		this.getNavigator().setAvoidsWater(false);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, this.moveSpeed, true));
		//this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, this.moveSpeed, 32.0F));
		//this.tasks.addTask(2, new EntityAIRangedAttack(this, this.moveSpeed, 3, 60));
		//this.tasks.addTask(2, new EntityAIKeepDistanceFromTarget(this, this.moveSpeed, 32.0F,worldObj));
		this.tasks.addTask(4, new EntityAIMoveTwardsRestriction(this, this.moveSpeed));
		this.tasks.addTask(6, new EntityAIWander(this, this.moveSpeed));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		//this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 32.0F, 0, true));
    }
    
    public void initializeAI(){
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, this.moveSpeed, true));
		//this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, this.moveSpeed, 32.0F));
		//this.tasks.addTask(2, new EntityAIRangedAttack(this, this.moveSpeed, 3, 60));
		//this.tasks.addTask(2, new EntityAIKeepDistanceFromTarget(this, this.moveSpeed, 32.0F,worldObj));
		this.tasks.addTask(4, new EntityAIMoveTwardsRestriction(this, this.moveSpeed));
		this.tasks.addTask(6, new EntityAIWander(this, this.moveSpeed));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		//this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 32.0F, 0, true));

    }

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	public boolean isAIEnabled(){
		return true;
	}
	
    public String getTexture(){
    	return DefaultProps.mobDiretory + "Mummy.png";
    }
    
    protected void entityInit(){
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)0));
    }

    /**
     * Get the experience points the entity currently has.
     */
    @Override
    protected int getExperiencePoints(EntityPlayer par1EntityPlayer){
        return 0;
    }

    
    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate(){
        super.onUpdate();

        if (!this.worldObj.isRemote){
            this.setBesideClimbableBlock(this.isCollidedHorizontally);
        }
    }

    public int getMaxHealth(){
        return 16;
    }

    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset(){
        return (double)this.height * 0.75D - 0.5D;
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking(){
        return true;
    }

    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack(){
        float var1 = this.getBrightness(1.0F);

        if (var1 < 0.5F){
            double var2 = 16.0D;
            return this.worldObj.getClosestVulnerablePlayerToEntity(this, var2);
        }else{
            return null;
        }
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound(){
        //return "mob.spider";
        return null;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound(){
       // return "mob.spider";
    	return null;
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound(){
        //return "mob.spiderdeath";
    	return null;
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity par1Entity, float par2){
        	super.attackEntity(par1Entity, par2);
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId(){
        return Item.silk.shiftedIndex;
    }

    /**
     * Drop 0-2 items of this living's type
     */
    protected void dropFewItems(boolean par1, int par2){
//        super.dropFewItems(par1, par2);
//
//        if (par1 && (this.rand.nextInt(3) == 0 || this.rand.nextInt(1 + par2) > 0))
//        {
//            this.dropItem(Item.spiderEye.shiftedIndex, 1);
//        }
    }

    /**
     * returns true if this entity is by a ladder, false otherwise
     */
    public boolean isOnLadder(){
        return this.isBesideClimbableBlock();
    }

    /**
     * Sets the Entity inside a web block.
     */
    public void setInWeb() {}

    /**
     * How large the spider should be scaled.
     */
    public float spiderScaleAmount(){
        return 1.0F;
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute(){
        return EnumCreatureAttribute.UNDEAD;
    }

    public boolean isPotionApplicable(PotionEffect par1PotionEffect){
        return par1PotionEffect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(par1PotionEffect);
    }

    /**
     * Returns true if the WatchableObject (Byte) is 0x01 otherwise returns false. The WatchableObject is updated using
     * setBesideClimableBlock.
     */
    public boolean isBesideClimbableBlock(){
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    /**
     * Updates the WatchableObject (Byte) created in entityInit(), setting it to 0x01 if par1 is true or 0x00 if it is
     * false.
     */
    public void setBesideClimbableBlock(boolean par1){
        byte var2 = this.dataWatcher.getWatchableObjectByte(16);

        if (par1){
            var2 = (byte)(var2 | 1);
        }else{
            var2 &= -2;
        }

        this.dataWatcher.updateObject(16, Byte.valueOf(var2));
    }
}
