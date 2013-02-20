package projectzulu.common.mobs.entitydefaults;

import java.io.File;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.DefaultSpawnable;
import projectzulu.common.core.ItemGenerics;
import net.minecraftforge.common.Configuration;
import projectzulu.common.mobs.entity.EntityCrocodile;
import projectzulu.common.mobs.models.ModelCrocodile;

import com.google.common.base.Optional;

public class AlligatorDefault extends DefaultSpawnable{
	
	public AlligatorDefault(){
		super("Alligator", EntityCrocodile.class);		
		setSpawnProperties(EnumCreatureType.creature, 10, 100, 1, 2);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelCrocodile.class, "projectzulu.common.mobs.renders.RenderGenericLiving");
		
		eggColor1 = (32 << 16) + (39 << 8) + 33;
		eggColor2 = (52 << 16) + (65 << 8) + 54;
		defaultBiomesToSpawn.add(BiomeGenBase.swampland.biomeName);	
		defaultBiomesToSpawn.add(BiomeGenBase.river.biomeName);
		defaultBiomesToSpawn.add("Green Swamplands"); 	
		defaultBiomesToSpawn.add("Marsh");
	}
	
	@Override
	public void outputDataToList(File configDirectory) {
		Configuration config = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		config.load();
		CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.beefRaw, 0, 5);		
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scaleItem, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scrapMeat, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1, ItemGenerics.Properties.Gill.meta(), 4);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1, ItemGenerics.Properties.LargeHeart.meta(), 4);
		config.save();
		CustomEntityList.crocodile = Optional.of(customMobData);
	}
}