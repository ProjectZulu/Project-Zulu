package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import projectzulu.common.mobs.entity.EntityFrog;
import projectzulu.common.mobs.models.ModelFrog;

public class FrogDeclaration extends SpawnableDeclaration{
	
	public FrogDeclaration(){
		super("Frog", EntityFrog.class, EnumCreatureType.creature);		
		setSpawnProperties(10, 100, 1, 3);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelFrog.class, "projectzulu.common.mobs.renders.RenderGenericLiving");
        setDropAmount(0, 1);

		eggColor1 = (95 << 16) + (186 << 8) + 50;
		eggColor2 = (105 << 16) + (203 << 8) + 67;
		defaultBiomesToSpawn.add(BiomeGenBase.swampland.biomeName);		
		defaultBiomesToSpawn.add("Green Swamplands"); 
		defaultBiomesToSpawn.add("Marsh");
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scrapMeat, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems,
				ItemGenerics.Properties.Gill.meta(), 4);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems,
				ItemGenerics.Properties.FrogLegs.meta(), 4);
		super.outputDataToList(config, customMobData);
	}
}