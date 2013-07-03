package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIFollowParent;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAIMate;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIPanic;
import projectzulu.common.mobs.entityai.EntityAITempt;
import projectzulu.common.mobs.entityai.EntityAIWander;

public class EntityRabbit extends EntityGenericAnimal implements IAnimals {	

	public EntityRabbit(World par1World){
		super(par1World);
		setSize(0.5f, 0.5f);
		
		this.moveSpeed = 0.3f;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, this.moveSpeed, true));

//		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, false));
//		this.tasks.addTask(4, new EntityAIFollowOwner(this, this.moveSpeed,	10.0F, 2.0F));

		this.tasks.addTask(5, new EntityAIMate(this, this.moveSpeed, true));
		this.tasks.addTask(6, new EntityAITempt(this, this.moveSpeed, Item.carrot.itemID, false, true));
		this.tasks.addTask(6, new EntityAITempt(this, this.moveSpeed, Item.appleRed.itemID, false, true));

		this.tasks.addTask(7, new EntityAIFollowParent(this, this.moveSpeed));
		this.tasks.addTask(9, new EntityAIWander(this, this.moveSpeed, 40, true));

		this.targetTasks.addTask(3,	new EntityAIHurtByTarget(this, false, false));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityPlayer.class, 16.0F, 0, true));
//		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityLiving.class, 16.0F, 0, false, true, IMob.mobSelector));
	}

	@Override
	protected int getAttackStrength(World par1World) {
		switch (par1World.difficultySetting) {
		case 0:
			return 1; 
		case 1:
			return 1; 
		case 2:
			return 2; 
		case 3:
			return 3; 
		default:
			return 2;
		}
	}
	
	@Override
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }

	@Override
	public int getMaxHealth(){return 10;}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	@Override
	public int getTotalArmorValue(){return 0;}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound(){
		return "sounds.rabbithurt";
	}
	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	protected String getDeathSound(){
		return "sounds.rabbitdead";
	}
	
	@Override
	public boolean isValidBreedingItem(ItemStack itemStack) {
		if( itemStack != null && (itemStack.itemID == Item.appleRed.itemID || itemStack.itemID == Item.carrot.itemID) ){
			return true;
		}else{
			return super.isValidBreedingItem(itemStack);
		}
	}
	@Override
	protected boolean shouldPanic() {
		return true;
	}
	@Override
	public boolean shouldNotifySimilar(EntityPlayer attackingPlayer) {
		return true;
	}
	/**
	 * Plays step sound at given x, y, z for the entity
	 */
	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4){ /*Does Not Play a Step Sound*/ }
}