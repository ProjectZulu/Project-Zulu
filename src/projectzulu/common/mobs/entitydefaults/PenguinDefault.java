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
import projectzulu.common.mobs.entity.EntityPenguin;

import com.google.common.base.Optional;

public class PenguinDefault extends DefaultSpawnable{
	
	public PenguinDefault(){
		super("Penguin", EntityPenguin.class);		
		setSpawnProperties(EnumCreatureType.creature, 10, 100, 1, 3);
		setRegistrationProperties(128, 3, true);

		eggColor1 = (22 << 16) + (16 << 8) + 13;
		eggColor2 = (235 << 16) + (235 << 8) + 235;
		
		defaultBiomesToSpawn.add(BiomeGenBase.icePlains.biomeName);	
		defaultBiomesToSpawn.add("Ice Wasteland");
		defaultBiomesToSpawn.add("Glacier");
	}
	
	@Override
	public void outputDataToList() {
		if(shouldExist){
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.feather), 8);
			if(ItemList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.scrapMeat.get()), 10); }
			if(ItemList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.SmallHeart.meta()), 4);
			}
			CustomEntityList.penguin = Optional.of(customMobData);	
		}
	}
}