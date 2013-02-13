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
import projectzulu.common.mobs.EntityMammoth;

import com.google.common.base.Optional;

public class MammothDefault extends DefaultSpawnable{

	public MammothDefault(){
		super("Mammoth", EntityMammoth.class);		
		setSpawnProperties(EnumCreatureType.creature, 1, 7, 1, 3);
		setRegistrationProperties(128, 3, true);
		
		eggColor1 = (20 << 16) + (12 << 8) + 0;
		eggColor2 = (69 << 16) + (42 << 8) + 0;
		defaultBiomesToSpawn.add(BiomeGenBase.icePlains.biomeName);
		defaultBiomesToSpawn.add(BiomeGenBase.taigaHills.biomeName);
		defaultBiomesToSpawn.add("Tundra"); 
		defaultBiomesToSpawn.add("Ice Wasteland");
		defaultBiomesToSpawn.add("Glacier");
	}

	@Override
	public void outputDataToList() {
		if(shouldExist){
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 15);
			if(ItemList.furPelt.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.furPelt.get()), 10); }
			if(ItemList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.scrapMeat.get()), 10); }
			if(ItemList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.Tusk.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
			}
			CustomEntityList.instance.mammoth = Optional.of(customMobData);	
		}
	}
}

