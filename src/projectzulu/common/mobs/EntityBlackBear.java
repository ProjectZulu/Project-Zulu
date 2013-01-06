package projectzulu.common.mobs;

import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import projectzulu.common.core.CustomEntityList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemBlockList;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import cpw.mods.fml.common.Loader;

public class EntityBlackBear extends EntityBear{

	public EntityBlackBear(World par1World) {
		super(par1World);
		setSize(1.0f, 1.35f);
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, false));
	}
	
	/** 
	 * Set Entity Attack Strength
	 * This is overriden by each Entity if deviations from default are desired
	 **/
	@Override
	protected int getAttackStrength(World par1World){
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
	public String getTexture() {
		
		this.texture = "/mods/bearblack.png";
		return this.texture;
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
		
		if (CustomEntityList.blackBear.get().secondarySpawnRate - rand.nextInt(100) >= 0 && super.getCanSpawnHere()
				&& worldObj.canBlockSeeTheSky(var1, var2, var3)){
			wasSuccesful = true;
		}
		
		if(CustomEntityList.blackBear.get().reportSpawningInLog){
			if(wasSuccesful){
				ProjectZuluLog.info("Successfully spawned %s at X:%s Y:%s Z:%s in %s",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}else{
				ProjectZuluLog.info("Failed to spawn %s at X:%s Y:%s Z:%s in %s, Spawning Location Inhospitable",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}
		}
		return wasSuccesful;
	}

	/**
	 * Used when Entity Initializes to set Max Health
	 */
	@Override
	public int getMaxHealth(){
		return 15;
		}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	public int getTotalArmorValue(){
		return 2;
	}


	
	public void onUpdate()
	{
		super.onUpdate();
	}
	
	@Override
	protected void dropRareDrop(int par1) {
		if(Loader.isModLoaded(DefaultProps.BlocksModId) && ItemBlockList.mobHeads.isPresent()){
			entityDropItem(new ItemStack(ItemBlockList.mobHeads.get().blockID,1,3), 1);
		}
		super.dropRareDrop(par1);
	}
}
