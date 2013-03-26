package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIControlledByPlayer;
import projectzulu.common.mobs.entityai.EntityAIFollowParent;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAIMate;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIPanic;
import projectzulu.common.mobs.entityai.EntityAITempt;
import projectzulu.common.mobs.entityai.EntityAIWander;

public class EntityHorseBase extends EntityGenericAnimal{	
	
    private final EntityAIControlledByPlayer aiControlledByPlayer;

	public EntityHorseBase(World par1World) {
		super(par1World);
		setSize(1.5f, 2.0f);
		
		this.moveSpeed = 0.3f;
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        
        this.tasks.addTask(1, aiControlledByPlayer = new EntityAIControlledByPlayer(this, 0.34F)  );
        
        this.tasks.addTask(2, new EntityAIPanic(this, this.moveSpeed));
        this.tasks.addTask(3, new EntityAIMate(this, this.moveSpeed));
        this.tasks.addTask(4, new EntityAITempt(this, this.moveSpeed, Item.appleRed.itemID, false));
        this.tasks.addTask(5, new EntityAIFollowParent(this, this.moveSpeed));
        
        this.tasks.addTask(6, new EntityAIAttackOnCollide(this, this.moveSpeed, false));
        this.tasks.addTask(8, new EntityAIWander(this, this.moveSpeed, 20));
    	
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, false));		
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityPlayer.class, 16.0F, 0, true));
//        jumpMovementFactor = 2.0f;
//        stepHeight = 1.0f;
	}
	
	@Override
	public boolean isRideable() { return true;	}
	
	@Override
	protected int getAttackStrength(World par1World) {
		switch (par1World.difficultySetting) {
		case 0:
			return 0;
		case 1:
			return 3; 
		case 2:
			return 4; 
		case 3:
			return 5; 
		default:
			return super.getAttackStrength(par1World);
		}
	}
	
	@Override
	public int getMaxHealth(){return 20;}
	@Override
	public int getTotalArmorValue(){return 0;}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound(){ return "sounds.horse"; }
	
	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound(){ return "sounds.horsehurt"; }
	
	@Override
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);

		return super.getCanSpawnHere() && worldObj.canBlockSeeTheSky(var1, var2, var3);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
//		return false;
		if( this.riddenByEntity != null && 
				(par1DamageSource.getEntity() != null && par1DamageSource.getEntity().equals(this.riddenByEntity)) ||
				(par1DamageSource.getSourceOfDamage() != null && par1DamageSource.getSourceOfDamage().equals(this.riddenByEntity)) ){
			return false;
		}else{
			return super.attackEntityFrom(par1DamageSource, par2);
		}
	}
	
	@Override
	public boolean isValidBreedingItem(ItemStack itemStack) {
		if(itemStack != null && (itemStack.getItem().itemID == Item.appleRed.itemID 
				|| itemStack.getItem().itemID == Item.carrot.itemID )){
			return true;
		}
		return false;
	}
}