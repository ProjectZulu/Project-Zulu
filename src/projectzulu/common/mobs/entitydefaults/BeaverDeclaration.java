package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import projectzulu.common.mobs.entity.EntityBeaver;
import projectzulu.common.mobs.models.ModelBeaver;

public class BeaverDeclaration extends SpawnableDeclaration{
	
	public BeaverDeclaration(){
		super("Beaver", EntityBeaver.class, EnumCreatureType.creature);		
		setSpawnProperties(10, 100, 1, 2);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelBeaver.class, "projectzulu.common.mobs.renders.RenderGenericLiving");
        setDropAmount(0, 2);

		eggColor1 = (54 << 16) + (36 << 8) + 9;
		eggColor2 = (67 << 16) + (45 << 8) + 11;
		defaultBiomesToSpawn.add(BiomeGenBase.taiga.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.taigaHills.biomeName);	
		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);
		
		defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");
		defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");
		defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");
		defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");
		defaultBiomesToSpawn.add("Woodlands");
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.furPelt, 0, 8);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scrapMeat, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems,
				ItemGenerics.Properties.BlackLichen.meta(), 4);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems,
				ItemGenerics.Properties.SmallHeart.meta(), 4);
		super.outputDataToList(config, customMobData);
	}
}