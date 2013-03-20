package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAIMoveTowardsRestriction;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIWander;

public class EntityMummy extends EntityGenericAnimal{
    public EntityMummy(World par1World){
        super(par1World);
        this.setSize(0.6F, 1.8F);
        this.moveSpeed = 0.25F;
        
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, this.moveSpeed, true));

		this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, this.moveSpeed));
		this.tasks.addTask(6, new EntityAIWander(this, this.moveSpeed, 80));
		
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(8, new EntityAILookIdle(this));
		
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, true));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityPlayer.class, 16.0F, 0, true));
    }
    
    public EntityMummy(World par1World, double parx, double pary, double parz){
        this(par1World);
		yOffset = 0.0f;
		setLocationAndAngles(parx, pary, parz, 1, 1);
		setPosition(parx, pary, parz);
    }
	
    public String getTexture(){
    	return DefaultProps.mobDiretory + "mummy.png";
    }
    
    protected void entityInit(){
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)0));
    }
    
    @Override
    protected void updateAITick() {
    	setAngerLevel(100);
    	super.updateAITick();
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
	 * Drop 0-2 items of this living's type
	 */
	@Override
	protected void dropFewItems(boolean par1, int par2){
		int var3 = rand.nextInt(2 + par2);
		for (int i = 0; i < var3; i++) {
			ItemStack loot = CustomEntityList.MUMMY.modData.get().getLootItem(rand);
			if(loot != null){
				entityDropItem(loot, 1);
			}
		}
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
