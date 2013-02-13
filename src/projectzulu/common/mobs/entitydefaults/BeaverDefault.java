package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.entity.EntityArmadillo;
import projectzulu.common.mobs.entity.EntityBeaver;

import com.google.common.base.Optional;

public class BeaverDefault extends DefaultSpawnable{
	
	public BeaverDefault(){
		super("Beaver", EntityBeaver.class);		
		setSpawnProperties(EnumCreatureType.creature, 10, 100, 1, 2);
		setRegistrationProperties(128, 3, true);

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
	public void outputDataToList() {
		if(shouldExist){
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			if(ItemList.furPelt.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.furPelt.get()), 8); }
			if(ItemList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.scrapMeat.get()), 10); }
			if(ItemList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.BlackLichen.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.SmallHeart.meta()), 4);
			}
			CustomEntityList.beaver = Optional.of(customMobData);	
		}
	}
}