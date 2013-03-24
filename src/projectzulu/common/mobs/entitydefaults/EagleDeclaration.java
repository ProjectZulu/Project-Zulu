package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import projectzulu.common.mobs.entity.EntityEagle;
import projectzulu.common.mobs.models.ModelEagle;

public class EagleDeclaration extends SpawnableDeclaration{
	
	public EagleDeclaration(){
		super("Eagle", EntityEagle.class, EnumCreatureType.ambient);		
		setSpawnProperties(5, 5, 1, 1);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelEagle.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 =  (224 << 16) + (224 << 8) + 224;						eggColor2 = (28 << 16) + (21 << 8) + 17;
		defaultBiomesToSpawn.add(BiomeGenBase.extremeHills.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.extremeHillsEdge.biomeName);
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.chickenRaw, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.feather, 0, 8);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.Talon.meta(), 4);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.SmallHeart.meta(), 4);
		super.outputDataToList(config, customMobData);
	}
}
