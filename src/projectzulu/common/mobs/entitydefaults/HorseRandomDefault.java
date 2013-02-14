package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.DefaultSpawnable;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.entity.EntityHorseRandom;
import projectzulu.common.mobs.models.ModelHorse;

import com.google.common.base.Optional;

public class HorseRandomDefault extends DefaultSpawnable{
	
	public HorseRandomDefault(){
		super("HorseRandom", EntityHorseRandom.class);		
		setSpawnProperties(EnumCreatureType.creature, 5, 100, 3, 4);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelHorse.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 =  (200 << 16) + (245 << 8) + 245;					eggColor2 = (51 << 16) + (51 << 8) + 51;
		
		defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 		
		defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName); 		
		defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");
		defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");
		defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");
		defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");
		defaultBiomesToSpawn.add("Woodlands");
	}
	
	@Override
	public void outputDataToList() {
		CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
		customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
		if(ItemList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.scrapMeat.get()), 10); }
		if(ItemList.genericCraftingItems1.isPresent()){
			customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
		}
		CustomEntityList.horseRandom = Optional.of(customMobData);	
	}
}
