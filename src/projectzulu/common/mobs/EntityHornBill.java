package projectzulu.common.mobs;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import projectzulu.common.API.CustomEntityList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import projectzulu.common.mobs.entityai.EntityAIFlyingWander;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;

public class EntityHornBill extends EntityGenericAnimal{

	public EntityHornBill(World par1World){
		super(par1World);
		this.setSize(0.8f, 1.2f);
		this.moveSpeed = 0.22f;
		this.maxFlightHeight = 20;
		this.getNavigator().setAvoidsWater(true);		
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, this.moveSpeed, false));
		this.tasks.addTask(6, new EntityAIFlyingWander(this, this.moveSpeed));
		
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, false));		
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityPlayer.class, 16.0F, 0, true));
	}

	@Override
	public boolean defaultGrounded(){
		return false;
	}

	@Override
	protected boolean canDespawn(){
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
	public String getTexture(){
		this.texture = DefaultProps.mobDiretory + "hornbill.png";
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
		
		if (CustomEntityList.hornBill.get().secondarySpawnRate - rand.nextInt(100) >= 0 && super.getCanSpawnHere() 
				&& worldObj.getClosestPlayerToEntity(this, 32) == null && this.worldObj.getSavedLightValue(EnumSkyBlock.Block, var1, var2, var3) < 1
				&& worldObj.canBlockSeeTheSky(var1, var2, var3) ){
			wasSuccesful = true;
		}
		
		if(CustomEntityList.hornBill.get().reportSpawningInLog){
			if(wasSuccesful){
				ProjectZuluLog.info("Successfully spawned %s at X:%s Y:%s Z:%s in %s",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}else{
				ProjectZuluLog.info("Failed to spawn %s at X:%s Y:%s Z:%s in %s, Spawning Location Inhospitable",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}
		}
		return wasSuccesful;
	}

	public int getMaxHealth() {
		return 20;
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	protected String getLivingSound(){
		return "mods.sounds.hornbillliving";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	protected String getHurtSound(){
		return "mods.sounds.hornbillhurt";
	}
	
	/**
	 * Drop 0-2 items of this living's type
	 */
	@Override
	protected void dropFewItems(boolean par1, int par2){
		int var3 = rand.nextInt(2 + par2);
		for (int i = 0; i < var3; i++) {
			ItemStack loot = CustomEntityList.beaver.get().getLootItem(rand);
			if(loot != null){
				entityDropItem(loot, 1);
			}
		}
	}
}
