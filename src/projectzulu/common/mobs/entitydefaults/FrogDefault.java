package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.entity.EntityArmadillo;
import projectzulu.common.mobs.entity.EntityFrog;

import com.google.common.base.Optional;

public class FrogDefault extends DefaultSpawnable{
	
	public FrogDefault(){
		super("Frog", EntityFrog.class);		
		setSpawnProperties(EnumCreatureType.creature, 10, 100, 1, 3);
		setRegistrationProperties(128, 3, true);
		
		eggColor1 = (95 << 16) + (186 << 8) + 50;
		eggColor2 = (105 << 16) + (203 << 8) + 67;
		defaultBiomesToSpawn.add(BiomeGenBase.swampland.biomeName);		
		defaultBiomesToSpawn.add("Green Swamplands"); 
		defaultBiomesToSpawn.add("Marsh");
	}
	
	@Override
	public void outputDataToList() {
		if(shouldExist){
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			if(ItemList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.scrapMeat.get()), 10); }
			if(ItemList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.Gill.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.FrogLegs.meta()), 4);
			}
			CustomEntityList.frog = Optional.of(customMobData);	
		}
	}
}