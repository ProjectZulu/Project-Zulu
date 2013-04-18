package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import projectzulu.common.mobs.entity.EntityMammoth;
import projectzulu.common.mobs.models.ModelMammoth;

public class MammothDeclaration extends SpawnableDeclaration{

	public MammothDeclaration(){
		super("Mammoth", EntityMammoth.class, EnumCreatureType.creature);		
		setSpawnProperties(1, 7, 1, 3);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelMammoth.class, "projectzulu.common.mobs.renders.RenderGenericLiving");
        setDropAmount(0, 4);

		eggColor1 = (20 << 16) + (12 << 8) + 0;
		eggColor2 = (69 << 16) + (42 << 8) + 0;
		defaultBiomesToSpawn.add(BiomeGenBase.icePlains.biomeName);
		defaultBiomesToSpawn.add(BiomeGenBase.taigaHills.biomeName);
		defaultBiomesToSpawn.add("Tundra"); 
		defaultBiomesToSpawn.add("Ice Wasteland");
		defaultBiomesToSpawn.add("Glacier");
	}

	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.furPelt, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scrapMeat, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems,
				ItemGenerics.Properties.LargeHeart.meta(), 4);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems,
				ItemGenerics.Properties.Tusk.meta(), 4);
		super.outputDataToList(config, customMobData);
	}
}

