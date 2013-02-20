package projectzulu.common.mobs.entitydefaults;

import java.io.File;

import net.minecraft.entity.EnumCreatureType;
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
import projectzulu.common.mobs.entity.EntitySandWorm;
import projectzulu.common.mobs.models.ModelSandWorm;

import com.google.common.base.Optional;

public class SandwormDefault extends DefaultSpawnable{
	
	public SandwormDefault(){
		super("SandWorm", EntitySandWorm.class);
		
		setSpawnProperties(EnumCreatureType.creature, 1, 100, 1, 1);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelSandWorm.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 = (24 << 16) + (0 << 8) + 8;
		eggColor2 = (49 << 16) + (16 << 8) + 8;
		
		defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);
		defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
		defaultBiomesToSpawn.add("Mountainous Desert");
	}
	
	@Override
	public void outputDataToList(File configDirectory) {
		Configuration config = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		config.load();
		CustomMobData customMobData = new CustomMobData(mobName, reportSpawningInLog);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scrapMeat, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.SmallHeart.meta(), 4);
		config.save();
		CustomEntityList.sandworm = Optional.of(customMobData);	
	}
}
