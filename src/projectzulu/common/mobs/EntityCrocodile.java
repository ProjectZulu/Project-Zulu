package projectzulu.common.mobs;

import java.util.EnumSet;

import net.minecraft.entity.EnumEntitySize;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import projectzulu.common.API.CustomEntityList;
import projectzulu.common.API.ItemBlockList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIFollowParent;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAIMate;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIPanic;
import projectzulu.common.mobs.entityai.EntityAISmoothSwimming;
import projectzulu.common.mobs.entityai.EntityAITempt;
import projectzulu.common.mobs.entityai.EntityAIWander;
import cpw.mods.fml.common.Loader;

public class EntityCrocodile extends EntityGenericAnimal{	
	public EntityCrocodile(World par1World){
		super(par1World);
		this.myEntitySize = EnumEntitySize.SIZE_6;
		setSize(1.7f, 0.9f);
		
		this.moveSpeed = 0.25f;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISmoothSwimming(this, true));
		this.tasks.addTask(1, new EntityAIPanic(this, this.moveSpeed));

		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, false));
//		this.tasks.addTask(4, new EntityAIFollowOwner(this, this.moveSpeed,	10.0F, 2.0F));

		this.tasks.addTask(5, new EntityAIMate(this, this.moveSpeed));
		this.tasks.addTask(6, new EntityAITempt(this, this.moveSpeed, Item.chickenRaw.shiftedIndex, false));
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
			return 4; 
		case 2:
			return 5; 
		case 3:
			return 6; 
		default:
			return 3;
		}
	}

	@Override
	public String getTexture() {
		this.texture = "/mods/Crocodile.png";
		return super.getTexture();
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
		
		if (CustomEntityList.crocodile.get().secondarySpawnRate - rand.nextInt(100) >= 0 && super.getCanSpawnHere()
				&& worldObj.canBlockSeeTheSky(var1, var2, var3)){
			wasSuccesful = true;
		}
		
		if(CustomEntityList.crocodile.get().reportSpawningInLog){
			if(wasSuccesful){
				ProjectZuluLog.info("Successfully spawned %s at X:%s Y:%s Z:%s in %s",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}else{
				ProjectZuluLog.info("Failed to spawn %s at X:%s Y:%s Z:%s in %s, Spawning Location Inhospitable",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}
		}
		return wasSuccesful;
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound(){ return "mods.sounds.crocoodileliving"; }
	
	public float getSpeedModifier() {
		float var1 = super.getSpeedModifier();
		if(isInWater()){
			var1 *= 2.0f;
		}
		return super.getSpeedModifier();
	};
	
	@Override
	protected void updateAITick() {
		if( this.isInWater() ){
			this.angerLevel = 400 + this.rand.nextInt(400);
		}
		super.updateAITick();
	}

	@Override
	protected int decreaseAirSupply(int par1) {
		return par1;
	}

	@Override
	public boolean isValidBreedingItem(ItemStack itemStack) {
		if(itemStack != null && itemStack.getItem().shiftedIndex == Item.chickenRaw.shiftedIndex){
			return true;
		}else{
			return super.isValidBreedingItem(itemStack);
		}
	}

	/**
	 * Drop 0-2 items of this living's type
	 */
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3 = this.rand.nextInt(3 + par2);
		int var4;

		if(Loader.isModLoaded(DefaultProps.BlocksModId)){
			if(var3 == 0){
				if(ItemBlockList.scaleItem.isPresent()){
					this.dropItem(ItemBlockList.scaleItem.get().shiftedIndex, 1);
				}
			}else{
				if(ItemBlockList.scrapMeat.isPresent()){
					this.dropItem(ItemBlockList.scrapMeat.get().shiftedIndex, 1);
				}
			}
		}else{
			this.dropItem(Item.beefRaw.shiftedIndex,1);
		}

	}

	@Override
	protected void dropRareDrop(int par1) {
		if(Loader.isModLoaded(DefaultProps.BlocksModId) && ItemBlockList.mobHeads.isPresent()){
			entityDropItem(new ItemStack(ItemBlockList.mobHeads.get().blockID,1,1), 1);
		}
		super.dropRareDrop(par1);
	}
}