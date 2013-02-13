package projectzulu.experimental.defaultentities;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.EntityArmadillo;
import projectzulu.common.mobs.EntitySandWorm;

import com.google.common.base.Optional;

public class SandwormDefault extends DefaultSpawnable{
	
	public SandwormDefault(){
		super("SandWorm", EntitySandWorm.class);
		
		setSpawnProperties(EnumCreatureType.creature, 1, 100, 1, 1);
		setRegistrationProperties(128, 3, true);

		eggColor1 = (24 << 16) + (0 << 8) + 8;
		eggColor2 = (49 << 16) + (16 << 8) + 8;
		
		defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);
		defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
		defaultBiomesToSpawn.add("Mountainous Desert");
	}
	
	@Override
	public void outputDataToList() {
		if(shouldExist){
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			if(ItemList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.scrapMeat.get()), 10); }
			if(ItemList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.SmallHeart.meta()), 4);
			}
			CustomEntityList.sandworm = Optional.of(customMobData);	
		}
	}
}
