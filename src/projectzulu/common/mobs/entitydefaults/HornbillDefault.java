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
import projectzulu.common.mobs.entity.EntityHornBill;
import projectzulu.common.mobs.models.ModelHornBill;

import com.google.common.base.Optional;

public class HornbillDefault extends DefaultSpawnable{
	
	public HornbillDefault(){
		super("Horn Bill", EntityHornBill.class);		
		setSpawnProperties(EnumCreatureType.monster, 10, 25, 1, 1);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelHornBill.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 =  (26 << 16) + (19 << 8) + 15;						eggColor2 = (199 << 16) + (33 << 8) + 14;
		defaultBiomesToSpawn.add(BiomeGenBase.jungle.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.jungleHills.biomeName); 			
		defaultBiomesToSpawn.add("Mini Jungle");						defaultBiomesToSpawn.add("Extreme Jungle");
	}
	
	@Override
	public void outputDataToList(File configDirectory) {
		Configuration config = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		config.load();
		CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
		customMobData.shouldDespawn = config.get("MOB CONTROLS."+mobName, mobName+" Should Despawn", enumCreatureType == EnumCreatureType.creature ? false : true).getBoolean(true);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.chickenRaw, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.feather, 0, 8);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.SmallHeart.meta(), 4);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.Talon.meta(), 4);
		config.save();
		CustomEntityList.HORNBILL.modData = Optional.of(customMobData);	
	}
}
