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
import projectzulu.common.mobs.entity.EntityPelican;
import projectzulu.common.mobs.models.ModelPelican;

import com.google.common.base.Optional;

public class PelicanDefault extends DefaultSpawnable{
	
	public PelicanDefault(){
		super("Pelican", EntityPelican.class);		
		setSpawnProperties(EnumCreatureType.monster, 7, 5, 1, 1);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelPelican.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 =  (214 << 16) + (214 << 8) + 214;					eggColor2 = (168 << 16) + (62 << 8) + 10;
		defaultBiomesToSpawn.add(BiomeGenBase.river.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.beach.biomeName);
	}
	
	@Override
	public void outputDataToList(File configDirectory) {
		Configuration config = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		config.load();
		CustomMobData customMobData = new CustomMobData(mobName, reportSpawningInLog);
		customMobData.shouldDespawn = config.get("MOB CONTROLS."+mobName, mobName+" Should Despawn", enumCreatureType == EnumCreatureType.creature ? false : true).getBoolean(true);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.chickenRaw, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.feather, 0, 8);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.LargeHeart.meta(), 4);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.Talon.meta(), 4);
		config.save();
		CustomEntityList.PELICAN.modData = Optional.of(customMobData);	
	}
}
