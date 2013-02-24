package projectzulu.common.mobs.entitydefaults;

import java.io.File;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.DefaultSpawnable;
import projectzulu.common.core.ItemGenerics;
import net.minecraftforge.common.Configuration;
import projectzulu.common.mobs.entity.EntityFox;
import projectzulu.common.mobs.models.ModelFox;

import com.google.common.base.Optional;

public class FoxDefault extends DefaultSpawnable{

	public FoxDefault(){
		super("Fox", EntityFox.class);		
		setSpawnProperties(EnumCreatureType.creature, 10, 100, 1, 3);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelFox.class, "projectzulu.common.mobs.renders.RenderTameable");

		eggColor1 = (204 << 16) + (132 << 8) + 22;
		eggColor2 = (224 << 16) + (224 << 8) + 224;
		
		defaultBiomesToSpawn.add(BiomeGenBase.taiga.biomeName);		defaultBiomesToSpawn.add(BiomeGenBase.taigaHills.biomeName);
		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName);	defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);
		defaultBiomesToSpawn.add("Birch Forest"); 					defaultBiomesToSpawn.add("Forested Island");
		defaultBiomesToSpawn.add("Forested Hills"); 				defaultBiomesToSpawn.add("Green Hills");
		defaultBiomesToSpawn.add("Mountain Taiga"); 				defaultBiomesToSpawn.add("Pine Forest");
		defaultBiomesToSpawn.add("Rainforest"); 					defaultBiomesToSpawn.add("Redwood Forest");
		defaultBiomesToSpawn.add("Lush Redwoods"); 					defaultBiomesToSpawn.add("Snow Forest");
		defaultBiomesToSpawn.add("Snowy Rainforest"); 				defaultBiomesToSpawn.add("Temperate Rainforest");
		defaultBiomesToSpawn.add("Woodlands");
	}
	
	public void outputDataToList(File configDirectory) {
		Configuration config = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		config.load();
		CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
		customMobData.shouldDespawn = config.get("MOB CONTROLS."+mobName, mobName+" Should Despawn", enumCreatureType == EnumCreatureType.creature ? false : true).getBoolean(true);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.beefRaw, 0, 5);		
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.furPelt, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scrapMeat, 0, 15);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1, ItemGenerics.Properties.SmallHeart.meta(), 4);
		ConfigHelper.userItemConfigRangeToMobData(config, "MOB CONTROLS."+mobName, customMobData);
		config.save();
		CustomEntityList.FOX.modData = Optional.of(customMobData);	
	}
}

