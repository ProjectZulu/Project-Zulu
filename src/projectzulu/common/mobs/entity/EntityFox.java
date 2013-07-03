package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIFollowOwner;
import projectzulu.common.mobs.entityai.EntityAIFollowParent;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAIMate;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIOwnerHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAIOwnerHurtTarget;
import projectzulu.common.mobs.entityai.EntityAIPanic;
import projectzulu.common.mobs.entityai.EntityAIStayStill;
import projectzulu.common.mobs.entityai.EntityAITempt;
import projectzulu.common.mobs.entityai.EntityAIWander;

public class EntityFox extends EntityGenericAnimal implements IAnimals {
	
	public EntityFox(World par1World) {
		super(par1World);
		setSize(0.6f, 1.0f);

		this.moveSpeed = 0.3f;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, this.moveSpeed));

		this.tasks.addTask(2, new EntityAIStayStill(this, EntityStates.sitting));

		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, false));
		this.tasks.addTask(4, new EntityAIFollowOwner(this, this.moveSpeed,	10.0F, 2.0F));
		
		this.tasks.addTask(5, new EntityAIMate(this, this.moveSpeed));
		this.tasks.addTask(6, new EntityAITempt(this, this.moveSpeed, Item.egg.itemID, false));
		this.tasks.addTask(7, new EntityAIFollowParent(this, this.moveSpeed));
		this.tasks.addTask(9, new EntityAIWander(this, this.moveSpeed, 120));

        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(11, new EntityAILookIdle(this));

		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3,	new EntityAIHurtByTarget(this, false, false));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityPlayer.class, 16.0F, 0, true));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityLiving.class, 16.0F, 0, false, true, IMob.mobSelector));
	}
	
	@Override
	public int getMaxHealth() {	return 12; }
	@Override
	public int getTotalArmorValue(){return 2;}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound() { return "sounds.foxhurtsound"; }
	
	@Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }
	
	@Override
	protected void updateAITasks() {	
		super.updateAITasks();
	}
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
	}
	
	@Override
	public boolean isValidTamingItem(ItemStack itemStack) {
		if(itemStack == null){
			return false;
		}
		
		if(itemStack.itemID == Item.egg.itemID ){
			return true;
		}
		return super.isValidTamingItem(itemStack);
	}
	
	@Override
	public boolean isValidBreedingItem(ItemStack itemStack) {
		if (itemStack != null && itemStack.getItem().itemID == Item.egg.itemID) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean shouldNotifySimilar(EntityPlayer attackingPlayer) {
		return true;
	}
	
    /** 
     * Validates if Itemstack can be used to Heal Entity
     * Caution: ItemStack may be Null
      */
    @Override
    public int getHealingValueIfValid(ItemStack itemStack){

    	if(itemStack == null){
			System.out.println("Is Null");
    		return 0;
    	}
    	
    	if(itemStack.itemID == Item.egg.itemID) {
			System.out.println(((ItemFood) Item.melon).getHealAmount());

    		return ((ItemFood) Item.melon).getHealAmount();
		}
    	
		return 0;
    }
	
	@Override
	public boolean isRideable() {
		return super.isRideable();
	}
	@Override
	protected boolean shouldPanic() {
		if(isTamed()){
			return false;
		}else{
			return true;
		}
	}
}