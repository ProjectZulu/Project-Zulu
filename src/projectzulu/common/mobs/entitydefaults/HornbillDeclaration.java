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
import projectzulu.common.mobs.entity.EntityHornBill;
import projectzulu.common.mobs.models.ModelHornBill;

public class HornbillDeclaration extends SpawnableDeclaration{
	
	public HornbillDeclaration(){
		super("Horn Bill", EntityHornBill.class, EnumCreatureType.ambient);		
		setSpawnProperties(10, 25, 1, 1);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelHornBill.class, "projectzulu.common.mobs.renders.RenderGenericLiving");
        setDropAmount(0, 2);

		eggColor1 =  (26 << 16) + (19 << 8) + 15;						eggColor2 = (199 << 16) + (33 << 8) + 14;
		defaultBiomesToSpawn.add(BiomeGenBase.jungle.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.jungleHills.biomeName); 			
		defaultBiomesToSpawn.add("Mini Jungle");						defaultBiomesToSpawn.add("Extreme Jungle");
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.chickenRaw, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.feather, 0, 8);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems,
				ItemGenerics.Properties.SmallHeart.meta(), 4);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems,
				ItemGenerics.Properties.Talon.meta(), 4);
		super.outputDataToList(config, customMobData);
	}
}
