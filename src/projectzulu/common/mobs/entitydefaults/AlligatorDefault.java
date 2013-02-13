package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.entity.EntityCrocodile;

import com.google.common.base.Optional;

public class AlligatorDefault extends DefaultSpawnable{
	
	public AlligatorDefault(){
		super("Alligator", EntityCrocodile.class);		
		setSpawnProperties(EnumCreatureType.creature, 10, 100, 1, 2);
		setRegistrationProperties(128, 3, true);
		
		eggColor1 = (32 << 16) + (39 << 8) + 33;
		eggColor2 = (52 << 16) + (65 << 8) + 54;
		defaultBiomesToSpawn.add(BiomeGenBase.swampland.biomeName);	
		defaultBiomesToSpawn.add(BiomeGenBase.river.biomeName);
		defaultBiomesToSpawn.add("Green Swamplands"); 	
		defaultBiomesToSpawn.add("Marsh");
	}
	
	@Override
	public void outputDataToList() {
		if(shouldExist){
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			if(ItemList.scaleItem.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.scaleItem.get()), 10); }
			if(ItemList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.scrapMeat.get()), 10); }
			if(ItemList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.Gill.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
			}
			CustomEntityList.crocodile = Optional.of(customMobData);	
		}
	}
}