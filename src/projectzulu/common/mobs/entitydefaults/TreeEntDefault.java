package projectzulu.common.mobs.entitydefaults;

import java.io.File;

import net.minecraft.block.Block;
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
import projectzulu.common.mobs.entity.EntityTreeEnt;
import projectzulu.common.mobs.models.ModelTreeEnt;

import com.google.common.base.Optional;

public class TreeEntDefault extends DefaultSpawnable{
	
	public TreeEntDefault(){
		super("TreeEnt", EntityTreeEnt.class);		
		setSpawnProperties(EnumCreatureType.creature, 1, 7, 1, 1);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelTreeEnt.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 = (17 << 16) + (6 << 8) + 3;
		eggColor2 = (83 << 16) + (56 << 8) + 29;
		
		defaultBiomesToSpawn.add(BiomeGenBase.taiga.biomeName);
		defaultBiomesToSpawn.add(BiomeGenBase.taigaHills.biomeName);
		defaultBiomesToSpawn.add("Alpine");
		defaultBiomesToSpawn.add("Mountain Taiga");
		defaultBiomesToSpawn.add("Snow Forest");
		defaultBiomesToSpawn.add("Snowy Rainforest");
	}
	
	@Override
	public void outputDataToList(File configDirectory) {
		Configuration config = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		config.load();
		CustomMobData customMobData = new CustomMobData(mobName, reportSpawningInLog);
		customMobData.shouldDespawn = config.get("MOB CONTROLS."+mobName, mobName+" Should Despawn", enumCreatureType == EnumCreatureType.creature ? false : true).getBoolean(true);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Block.wood, 1, 8);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.Bark.meta(), 4);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.BlackLichen.meta(), 4);
		config.save();
		CustomEntityList.TREEENT.modData = Optional.of(customMobData);	
	}
}