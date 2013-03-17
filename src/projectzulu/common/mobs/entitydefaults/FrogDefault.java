package projectzulu.common.mobs.entitydefaults;

import java.io.File;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.DefaultSpawnable;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.entity.EntityFrog;
import projectzulu.common.mobs.models.ModelFrog;

import com.google.common.base.Optional;

public class FrogDefault extends DefaultSpawnable{
	
	public FrogDefault(){
		super("Frog", EntityFrog.class, EnumCreatureType.creature);		
		setSpawnProperties(10, 100, 1, 3);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelFrog.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 = (95 << 16) + (186 << 8) + 50;
		eggColor2 = (105 << 16) + (203 << 8) + 67;
		defaultBiomesToSpawn.add(BiomeGenBase.swampland.biomeName);		
		defaultBiomesToSpawn.add("Green Swamplands"); 
		defaultBiomesToSpawn.add("Marsh");
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scrapMeat, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.Gill.meta(), 4);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.FrogLegs.meta(), 4);
		super.outputDataToList(config, customMobData);
	}
}