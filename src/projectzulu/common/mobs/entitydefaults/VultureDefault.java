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
import projectzulu.common.mobs.entity.EntityVulture;

import com.google.common.base.Optional;

public class VultureDefault extends DefaultSpawnable{
	
	public VultureDefault(){
		super("Vulture", EntityVulture.class);		
		setSpawnProperties(EnumCreatureType.monster, 2, 5, 1, 3);
		setRegistrationProperties(128, 3, true);

		eggColor1 = (78 << 16) + (72 << 8) + 56;
		eggColor2 = (120 << 16) + (110 << 8) + 86;
		defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);
		defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
		defaultBiomesToSpawn.add("Mountainous Desert");
		defaultBiomesToSpawn.add("Savanna");
		defaultBiomesToSpawn.add("Mountain Ridge");
		defaultBiomesToSpawn.add("Wasteland");
	}
	
	@Override
	public void outputDataToList() {
		if(shouldExist){
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.feather), 8);
			customMobData.addLootToMob(new ItemStack(Item.chickenRaw), 10);
			if(ItemList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.SmallHeart.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.Talon.meta()), 4);
			}
			CustomEntityList.vulture = Optional.of(customMobData);	
		}
	}
}
