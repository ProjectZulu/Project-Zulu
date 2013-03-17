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
import projectzulu.common.mobs.entity.EntityRhino;
import projectzulu.common.mobs.models.ModelRhino;

import com.google.common.base.Optional;

public class RhinoDefault extends DefaultSpawnable{
	
	public RhinoDefault(){
		super("Rhino", EntityRhino.class, EnumCreatureType.creature);		
		setSpawnProperties(10, 100, 1, 2);
		setRegistrationProperties(128, 3, true);		
		setModelAndRender(ModelRhino.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 = (95 << 16) + (93 << 8) + 94;						eggColor2 = (173 << 16) + (170 << 8) + 172;
		
		defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName); 
		defaultBiomesToSpawn.add("Savanna");		
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scrapMeat, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.LargeHeart.meta(), 4);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.Tusk.meta(), 8);
		super.outputDataToList(config, customMobData);
	}
}
