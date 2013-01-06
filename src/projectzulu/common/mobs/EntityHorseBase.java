package projectzulu.common.mobs;

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
        this.tasks.addTask(4, new EntityAITempt(this, this.moveSpeed, Item.appleRed.shiftedIndex, false));
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
	protected String getLivingSound(){ return "mods.sounds.horse"; }
	
	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound(){ return "mods.sounds.horsehurt"; }
	
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
		if(itemStack != null && (itemStack.getItem().shiftedIndex == Item.appleRed.shiftedIndex 
				|| itemStack.getItem().shiftedIndex == Item.carrot.shiftedIndex )){
			return true;
		}
		return false;
	}

	/**
	 * Returns the item ID for the item the mob drops on death.
	 */	
	protected int getDropItemId() {
		if(rand.nextBoolean()){
			return Item.rottenFlesh.shiftedIndex;
		}else{
			return Item.beefRaw.shiftedIndex;
		}
	}

	/**
	 * Drop 0-2 items of this living's type
	 */
	protected void dropFewItems(boolean par1, int par2) {
		int var3 = this.rand.nextInt(3 + par2) + 1;
		int var4;

//		if(Loader.isModLoaded(DefaultProps.BlocksModId)){
//			this.dropItem(mod_ProjectZulu.furPeltID+256, 1);
//			entityDropItem(new ItemStack(mod_ProjectZulu.genericCraftingItems1ID+256,1,1), 2);
//		}

		for (var4 = 0; var4 < var3; ++var4) {
			if(var4 == 2){
				this.dropItem(Item.leather.shiftedIndex,1);
			}else{
				this.dropItem(Item.beefRaw.shiftedIndex,1);
			}
		}
	}

}