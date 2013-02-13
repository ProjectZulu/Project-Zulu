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
import projectzulu.common.mobs.EntityPelican;

import com.google.common.base.Optional;

public class PelicanDefault extends DefaultSpawnable{
	
	public PelicanDefault(){
		super("Pelican", EntityPelican.class);		
		setSpawnProperties(EnumCreatureType.monster, 7, 5, 1, 1);
		setRegistrationProperties(128, 3, true);
		
		eggColor1 =  (214 << 16) + (214 << 8) + 214;					eggColor2 = (168 << 16) + (62 << 8) + 10;
		defaultBiomesToSpawn.add(BiomeGenBase.river.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.beach.biomeName);
	}
	
	@Override
	public void outputDataToList() {
		if(shouldExist){
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.chickenRaw), 10);
			customMobData.addLootToMob(new ItemStack(Item.feather), 8);
			if(ItemList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.Talon.meta()), 4);
			}
			CustomEntityList.pelican = Optional.of(customMobData);	
		}
	}
}
