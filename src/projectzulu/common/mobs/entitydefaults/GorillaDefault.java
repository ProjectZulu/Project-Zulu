package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.entity.EntityArmadillo;
import projectzulu.common.mobs.entity.EntityGorilla;

import com.google.common.base.Optional;

public class GorillaDefault extends DefaultSpawnable{
	
	public GorillaDefault(){
		super("Gorilla", EntityGorilla.class);		
		setSpawnProperties(EnumCreatureType.creature, 10, 100, 1, 1);
		setRegistrationProperties(128, 3, true);
		
		eggColor1 =  (25 << 16) + (25 << 8) + 25;						eggColor2 = (93 << 16) + (93 << 8) + 93;
		
		defaultBiomesToSpawn.add(BiomeGenBase.jungle.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.jungleHills.biomeName); 
		defaultBiomesToSpawn.add("Mini Jungle");						defaultBiomesToSpawn.add("Extreme Jungle");	
	}
	
	@Override
	public void outputDataToList() {
		if(shouldExist){
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			if(ItemList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.scrapMeat.get()), 10); }
			if(ItemList.furPelt.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.furPelt.get()), 8); }
			if(ItemList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
			}
			CustomEntityList.gorilla = Optional.of(customMobData);	
		}
	}
}
