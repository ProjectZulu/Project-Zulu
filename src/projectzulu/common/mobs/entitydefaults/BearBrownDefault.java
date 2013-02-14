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
import projectzulu.common.mobs.entity.EntityBrownBear;
import projectzulu.common.mobs.models.ModelBrownBear;

import com.google.common.base.Optional;

public class BearBrownDefault extends DefaultSpawnable{
	
	public BearBrownDefault(){
		super("Brown Bear", EntityBrownBear.class);		
		setSpawnProperties(EnumCreatureType.creature, 10, 100, 1, 2);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelBrownBear.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 = (51 << 16) + (34 << 8) + 8;							eggColor2 = (63 << 16) + (42 << 8) + 10;
		defaultBiomesToSpawn.add(BiomeGenBase.icePlains.biomeName); 	defaultBiomesToSpawn.add(BiomeGenBase.iceMountains.biomeName);	
		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);	
		defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");
		defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");
		defaultBiomesToSpawn.add("Mountain Taiga");						defaultBiomesToSpawn.add("Redwood Forest");
		defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Snow Forest");						
		defaultBiomesToSpawn.add("Temperate Rainforest");				defaultBiomesToSpawn.add("Woodlands");			
	}
	
	@Override
	public void outputDataToList() {
		if(shouldExist){
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			if(ItemList.furPelt.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.furPelt.get()), 8); }
			if(ItemList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.scrapMeat.get()), 10); }
			if(ItemList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.BlackLichen.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
			}
			CustomEntityList.brownBear = Optional.of(customMobData);	
		}
	}
}