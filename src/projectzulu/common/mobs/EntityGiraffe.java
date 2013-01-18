package projectzulu.common.mobs;

import java.util.EnumSet;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.ItemBlockList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIFollowParent;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAIMate;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIPanic;
import projectzulu.common.mobs.entityai.EntityAITempt;
import projectzulu.common.mobs.entityai.EntityAIWander;
import cpw.mods.fml.common.Loader;

public class EntityGiraffe extends EntityGenericAnimal {	

	public EntityGiraffe(World par1World) {
		super(par1World);
		setSize(2.3f, 4.0f);
		
		this.moveSpeed = 0.3f;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, this.moveSpeed));

		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, false));
//		this.tasks.addTask(4, new EntityAIFollowOwner(this, this.moveSpeed,	10.0F, 2.0F));

		this.tasks.addTask(5, new EntityAIMate(this, this.moveSpeed));
		this.tasks.addTask(6, new EntityAITempt(this, this.moveSpeed, Block.tallGrass.blockID, false));
		this.tasks.addTask(7, new EntityAIFollowParent(this, this.moveSpeed));
		this.tasks.addTask(9, new EntityAIWander(this, this.moveSpeed, 120));

		this.targetTasks.addTask(3,	new EntityAIHurtByTarget(this, false, false));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityPlayer.class, 16.0F, 0, true));
//		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityLiving.class, 16.0F, 0, false, true, IMob.mobSelector));

	}

//	@Override
//	public float getAttackDistance() {
//		return 2.0f;
//	}
	
	@Override
	protected int getAttackStrength(World par1World) {
		switch (par1World.difficultySetting) {
		case 0:
			return 4; 
		case 1:
			return 5; 
		case 2:
			return 6; 
		case 3:
			return 7; 
		default:
			return 3;
		}
	}

	@Override
	public String getTexture() {

		this.texture = DefaultProps.mobDiretory + "giraffe.png";
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
		
		if (CustomEntityList.giraffe.get().secondarySpawnRate - rand.nextInt(100) >= 0 && super.getCanSpawnHere()
				&& worldObj.canBlockSeeTheSky(var1, var2, var3)){
			wasSuccesful = true;
		}
		
		if(CustomEntityList.giraffe.get().reportSpawningInLog){
			if(wasSuccesful){
				ProjectZuluLog.info("Successfully spawned %s at X:%s Y:%s Z:%s in %s",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}else{
				ProjectZuluLog.info("Failed to spawn %s at X:%s Y:%s Z:%s in %s, Spawning Location Inhospitable",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}
		}
		return wasSuccesful;
	}
	
	@Override
	public int getMaxHealth(){return 20;}
	
	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	@Override
	public int getTotalArmorValue() {
		return 3;
	}
	
	@Override
	public void knockBack(Entity par1Entity, int par2, double par3, double par5) {
		this.isAirBorne = true;
		float var7 = MathHelper.sqrt_double(par3 * par3 + par5 * par5);
		float var8 = 0.4F;
		this.motionX /= 2.0D;
		this.motionY /= 2.0D;
		this.motionZ /= 2.0D;
		this.motionX -= par3 / (double)var7 * (double)var8*0.2;
		this.motionY += (double)var8;
		this.motionZ -= par5 / (double)var7 * (double)var8*0.2;

		if (this.motionY > 0.1000000059604645D) {
			this.motionY = 0.3D;
		}
	}
	
	@Override
	public boolean isValidBreedingItem(ItemStack itemStack) {
		if(itemStack != null && (itemStack.getItem().itemID == Block.tallGrass.blockID)){
			return true;
		}
		return super.isValidBreedingItem(itemStack);
	}
	
	/**
	 * Drop 0-2 items of this living's type
	 */
	@Override
	protected void dropFewItems(boolean par1, int par2){
		int var3 = rand.nextInt(3 + par2);
		for (int i = 0; i < var3; i++) {
			ItemStack loot = CustomEntityList.giraffe.get().getLootItem(rand);
			if(loot != null){
				entityDropItem(loot, 1);
			}
		}
	}

	@Override
	protected void dropRareDrop(int par1) {
		if(Loader.isModLoaded(DefaultProps.BlocksModId) && ItemBlockList.mobHeads.isPresent()){
			entityDropItem(new ItemStack(ItemBlockList.mobHeads.get().blockID,1,8), 1);
		}
		super.dropRareDrop(par1);
	}
}