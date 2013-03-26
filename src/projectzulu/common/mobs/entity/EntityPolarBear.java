package projectzulu.common.mobs.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import projectzulu.common.api.BlockList;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.mobs.entityai.EntityAIAttackOnCollide;
import cpw.mods.fml.common.Loader;

public class EntityPolarBear extends EntityBear{

	public EntityPolarBear(World par1World) {
		super(par1World);
		setSize(2.0f, 2.7f);
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, false, 13f));
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

		this.texture = DefaultProps.mobDiretory + "bearpolar.png";

		return super.getTexture();
	}

	@Override
	public int getMaxHealth(){return 25;}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	@Override
    public int getTotalArmorValue(){return 6;}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	@Override
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
		boolean wasSuccesful = false;
		
		if (CustomEntityList.POLARBEAR.modData.get().secondarySpawnRate - rand.nextInt(100) >= 0 && super.getCanSpawnHere()
				&& worldObj.canBlockSeeTheSky(var1, var2, var3)){
			wasSuccesful = true;
		}
		
		if(CustomEntityList.POLARBEAR.modData.get().reportSpawningInLog){
			if(wasSuccesful){
				ProjectZuluLog.info("Successfully spawned %s at X:%s Y:%s Z:%s in %s",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}else{
				ProjectZuluLog.info("Failed to spawn %s at X:%s Y:%s Z:%s in %s, Spawning Location Inhospitable",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}
		}
		return wasSuccesful;
	}
	
	@Override
	protected void dropRareDrop(int par1) {
		if(Loader.isModLoaded(DefaultProps.BlocksModId) && BlockList.mobHeads.isPresent()){
			entityDropItem(new ItemStack(BlockList.mobHeads.get().blockID,1,5), 1);
		}
		super.dropRareDrop(par1);
	}

}
