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
import projectzulu.common.mobs.entity.EntityBeaver;
import projectzulu.common.mobs.models.ModelBeaver;

import com.google.common.base.Optional;

public class BeaverDefault extends DefaultSpawnable{
	
	public BeaverDefault(){
		super("Beaver", EntityBeaver.class);		
		setSpawnProperties(EnumCreatureType.creature, 10, 100, 1, 2);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelBeaver.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 = (54 << 16) + (36 << 8) + 9;
		eggColor2 = (67 << 16) + (45 << 8) + 11;
		defaultBiomesToSpawn.add(BiomeGenBase.taiga.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.taigaHills.biomeName);	
		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);
		
		defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");
		defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");
		defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");
		defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");
		defaultBiomesToSpawn.add("Woodlands");
	}
	
	@Override
	public void outputDataToList(File configDirectory) {
		Configuration config = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		config.load();
		CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
		customMobData.shouldDespawn = config.get("MOB CONTROLS."+mobName, mobName+" Should Despawn", enumCreatureType == EnumCreatureType.creature ? false : true).getBoolean(true);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.furPelt, 0, 8);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scrapMeat, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.BlackLichen.meta(), 4);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.SmallHeart.meta(), 4);
		ConfigHelper.userItemConfigRangeToMobData(config, "MOB CONTROLS."+mobName, customMobData);
		config.save();
		CustomEntityList.BEAVER.modData = Optional.of(customMobData);
	}
}