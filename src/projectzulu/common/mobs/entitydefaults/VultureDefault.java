package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultSpawnable;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.entity.EntityVulture;
import projectzulu.common.mobs.models.ModelVulture;

public class VultureDefault extends DefaultSpawnable{
	
	public VultureDefault(){
		super("Vulture", EntityVulture.class, EnumCreatureType.monster);		
		setSpawnProperties(2, 5, 1, 3);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelVulture.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 = (78 << 16) + (72 << 8) + 56;
		eggColor2 = (120 << 16) + (110 << 8) + 86;
		defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);
		defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
		defaultBiomesToSpawn.add("Mountainous Desert");
		defaultBiomesToSpawn.add("Mountain Ridge");
		defaultBiomesToSpawn.add("Wasteland");
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.feather, 0, 8);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.chickenRaw, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.SmallHeart.meta(), 4);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.Talon.meta(), 4);
		super.outputDataToList(config, customMobData);
	}
}
