package projectzulu.experimental.defaultentities;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.EntityArmadillo;
import projectzulu.common.mobs.EntityBoar;

import com.google.common.base.Optional;

public class BoarDefault extends DefaultSpawnable{
	
	public BoarDefault(){
		super("Boar", EntityBoar.class);		
		setSpawnProperties(EnumCreatureType.creature, 10, 100, 1, 3);
		setRegistrationProperties(128, 3, true);
		
		eggColor1 = (122 << 16) + (77 << 8) + 32;
		eggColor2 = (158 << 16) + (99 << 8) + 42;
		defaultBiomesToSpawn.add(BiomeGenBase.taiga.biomeName);
		defaultBiomesToSpawn.add(BiomeGenBase.taigaHills.biomeName);
		defaultBiomesToSpawn.add("Alpine"); 
		defaultBiomesToSpawn.add("Mountain Taiga");
		defaultBiomesToSpawn.add("Snowy Rainforest");
	}
	
	@Override
	public void outputDataToList() {
		if(shouldExist){
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 2);
			if(ItemList.furPelt.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.furPelt.get()), 10); }
			if(ItemList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.scrapMeat.get()), 10); }
			if(ItemList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.SmallHeart.meta()), 5);
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.Tusk.meta()), 10);

			}
			CustomEntityList.boar = Optional.of(customMobData);	
		}
	}
}