package projectzulu.common.mobs.entitydefaults;

import java.io.File;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.DefaultSpawnable;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.entity.EntityGorilla;
import projectzulu.common.mobs.models.ModelGorilla;

import com.google.common.base.Optional;

public class GorillaDefault extends DefaultSpawnable{
	
	public GorillaDefault(){
		super("Gorilla", EntityGorilla.class, EnumCreatureType.creature);		
		setSpawnProperties(10, 100, 1, 1);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelGorilla.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 =  (25 << 16) + (25 << 8) + 25;						eggColor2 = (93 << 16) + (93 << 8) + 93;
		
		defaultBiomesToSpawn.add(BiomeGenBase.jungle.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.jungleHills.biomeName); 
		defaultBiomesToSpawn.add("Mini Jungle");						defaultBiomesToSpawn.add("Extreme Jungle");	
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.beefRaw, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scrapMeat, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.furPelt, 0, 8);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.LargeHeart.meta(), 4);
		super.outputDataToList(config, customMobData);
	}
}
