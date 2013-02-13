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
import projectzulu.common.mobs.entity.EntityEagle;

import com.google.common.base.Optional;

public class EagleDefault extends DefaultSpawnable{
	
	public EagleDefault(){
		super("Eagle", EntityEagle.class);		
		setSpawnProperties(EnumCreatureType.monster, 5, 5, 1, 1);
		setRegistrationProperties(128, 3, true);
		
		eggColor1 =  (224 << 16) + (224 << 8) + 224;						eggColor2 = (28 << 16) + (21 << 8) + 17;
		defaultBiomesToSpawn.add(BiomeGenBase.extremeHills.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.extremeHillsEdge.biomeName);
	}
	
	@Override
	public void outputDataToList() {
		if(shouldExist){
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.chickenRaw), 10);
			customMobData.addLootToMob(new ItemStack(Item.feather), 8);
			if(ItemList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.SmallHeart.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.Talon.meta()), 4);
			}
			CustomEntityList.eagle = Optional.of(customMobData);	
		}
	}
}
