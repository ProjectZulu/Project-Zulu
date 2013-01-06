package projectzulu.common.mobs;

import projectzulu.common.core.CustomEntityList;
import projectzulu.common.core.ProjectZuluLog;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityBlueFinch extends EntityFinch{

	public EntityBlueFinch(World par1World) {
		super(par1World);
	}
	
	@Override
	public String getTexture() {
		this.texture = "/mods/finch_blue.png";
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
		
		if (CustomEntityList.blueFinch.get().secondarySpawnRate - rand.nextInt(100) >= 0 && super.getCanSpawnHere() 
				&& worldObj.getClosestPlayerToEntity(this, 32) == null && this.worldObj.getSavedLightValue(EnumSkyBlock.Block, var1, var2, var3) < 1
				&& worldObj.canBlockSeeTheSky(var1, var2, var3) ){
			wasSuccesful = true;
		}
		
		if(CustomEntityList.blueFinch.get().reportSpawningInLog){
			if(wasSuccesful){
				ProjectZuluLog.info("Successfully spawned %s at X:%s Y:%s Z:%s in %s",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}else{
				ProjectZuluLog.info("Failed to spawn %s at X:%s Y:%s Z:%s in %s, Spawning Location Inhospitable",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}
		}
		return wasSuccesful;
	}

}
