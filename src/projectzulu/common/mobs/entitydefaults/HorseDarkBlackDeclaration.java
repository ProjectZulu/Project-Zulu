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
import projectzulu.common.mobs.entity.EntityHorseDarkBlack;
import projectzulu.common.mobs.models.ModelHorse;

public class HorseDarkBlackDeclaration extends SpawnableDeclaration{
	
	public HorseDarkBlackDeclaration(){
		super("Horse Dark Black", EntityHorseDarkBlack.class, EnumCreatureType.creature);		
		setSpawnProperties(5, 100, 1, 2);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelHorse.class, "projectzulu.common.mobs.renders.RenderGenericLiving");
        setDropAmount(0, 3);

		eggColor1 =  (17 << 16) + (17 << 8) + 17;						eggColor2 = (51 << 16) + (51 << 8) + 51;
		defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 		
		defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName); 		
		defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");
		defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");
		defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");
		defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");
		defaultBiomesToSpawn.add("Woodlands");
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.beefRaw, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scrapMeat, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems,
				ItemGenerics.Properties.LargeHeart.meta(), 4);
		super.outputDataToList(config, customMobData);
	}
}