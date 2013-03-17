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
import projectzulu.common.mobs.entity.EntityLizard;
import projectzulu.common.mobs.models.ModelLizard;

import com.google.common.base.Optional;

public class LizardDefault extends DefaultSpawnable{
	public LizardDefault(){
		super("Lizard", EntityLizard.class, EnumCreatureType.monster);		
		setSpawnProperties(10, 100, 1, 1);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelLizard.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 = (114 << 16) + (102 << 8) + 74;
		eggColor2 = (181 << 16) + (171 << 8) + 146;
		
		defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);
		defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
		defaultBiomesToSpawn.add("Mountainous Desert");
		defaultBiomesToSpawn.add("Savanna");
		defaultBiomesToSpawn.add("Mountain Ridge");
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scaleItem, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scrapMeat, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.SmallHeart.meta(), 4);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.PoisonDroplet.meta(), 4);
		super.outputDataToList(config, customMobData);
	}
}
