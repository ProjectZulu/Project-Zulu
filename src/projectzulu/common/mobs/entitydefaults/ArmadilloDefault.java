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
import projectzulu.common.mobs.entity.EntityArmadillo;
import projectzulu.common.mobs.models.ModelArmadillo;

import com.google.common.base.Optional;

public class ArmadilloDefault extends DefaultSpawnable{
	
	public ArmadilloDefault(){
		super("Armadillo", EntityArmadillo.class, EnumCreatureType.creature);		
		setSpawnProperties(10, 100, 2, 4);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelArmadillo.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 = (116 << 16) + (64 << 8) + 33;
		eggColor2 = (60 << 16) + (51 << 8) + 10;
		
		defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);			defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
		defaultBiomesToSpawn.add("Mountainous Desert");						defaultBiomesToSpawn.add("Savanna");
		defaultBiomesToSpawn.add("Mountain Ridge");	
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scaleItem, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scrapMeat, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.SmallHeart.meta(), 4);
		super.outputDataToList(config, customMobData);
	}
}
