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
import projectzulu.common.mobs.entity.EntityEagle;
import projectzulu.common.mobs.models.ModelEagle;

import com.google.common.base.Optional;

public class EagleDefault extends DefaultSpawnable{
	
	public EagleDefault(){
		super("Eagle", EntityEagle.class);		
		setSpawnProperties(EnumCreatureType.monster, 5, 5, 1, 1);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelEagle.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 =  (224 << 16) + (224 << 8) + 224;						eggColor2 = (28 << 16) + (21 << 8) + 17;
		defaultBiomesToSpawn.add(BiomeGenBase.extremeHills.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.extremeHillsEdge.biomeName);
	}
	
	@Override
	public void outputDataToList(File configDirectory) {
		
		Configuration config = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		config.load();
		CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.chickenRaw, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.feather, 0, 8);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.Talon.meta(), 4);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.SmallHeart.meta(), 4);
		config.save();
		CustomEntityList.eagle = Optional.of(customMobData);
	}
}
