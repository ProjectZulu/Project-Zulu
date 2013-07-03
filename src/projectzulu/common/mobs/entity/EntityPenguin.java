package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectzulu.common.api.BlockList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIFollowParent;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAIMate;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIPanic;
import projectzulu.common.mobs.entityai.EntityAITempt;
import projectzulu.common.mobs.entityai.EntityAIWander;
import cpw.mods.fml.common.Loader;

public class EntityPenguin extends EntityGenericAnimal implements IAnimals {	

	public EntityPenguin(World par1World){
		super(par1World);
		setSize(0.9f, 1.5f);
		this.moveSpeed = 0.25f;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, this.moveSpeed));

		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, false));
//		this.tasks.addTask(4, new EntityAIFollowOwner(this, this.moveSpeed,	10.0F, 2.0F));

		this.tasks.addTask(5, new EntityAIMate(this, this.moveSpeed));
		this.tasks.addTask(6, new EntityAITempt(this, this.moveSpeed, Item.fishRaw.itemID, false));
		this.tasks.addTask(6, new EntityAITempt(this, this.moveSpeed, Item.fishCooked.itemID, false));
		this.tasks.addTask(7, new EntityAIFollowParent(this, this.moveSpeed));
		
		this.tasks.addTask(9, new EntityAIWander(this, this.moveSpeed, 120));

		this.targetTasks.addTask(3,	new EntityAIHurtByTarget(this, false, false));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityPlayer.class, 16.0F, 0, true));
//		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityLiving.class, 16.0F, 0, false, true, IMob.mobSelector));
	}
	
	@Override
	protected int getAttackStrength(World par1World) {
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
    protected boolean isValidLocation(World world, int xCoord, int yCoord, int zCoord) {
        return worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
    }
	
	@Override
	public int getMaxHealth(){return 15;}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	@Override
	public int getTotalArmorValue(){return 0;}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound(){return "sounds.penguinhurt";}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound(){ return "sounds.penguinhurt"; }

	/** 
	 * Checks if the Provided ItemStack is considered an item that should be used for Breeding
	 * This is overriden by each Entity if deviations from default are desired
	 */
	@Override
    public boolean isValidBreedingItem(ItemStack itemStack){
		if( itemStack != null && (itemStack.itemID == Item.fishRaw.itemID || itemStack.itemID == Item.fishCooked.itemID) ){
			return true;
		}else{
			return super.isValidBreedingItem(itemStack);
		}
	}

	@Override
	protected void dropRareDrop(int par1) {
		if(Loader.isModLoaded(DefaultProps.BlocksModId) && BlockList.mobHeads.isPresent()){
			entityDropItem(new ItemStack(BlockList.mobHeads.get().blockID,1,13), 1);
		}
		super.dropRareDrop(par1);
	}

}