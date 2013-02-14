package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.DefaultSpawnable;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.entity.EntityPolarBear;
import projectzulu.common.mobs.models.ModelPolarBear;

import com.google.common.base.Optional;

public class BearPolarDefault extends DefaultSpawnable{
	
	public BearPolarDefault(){
		super("Polar Bear", EntityPolarBear.class);		
		setSpawnProperties(EnumCreatureType.creature, 10, 100, 1, 2);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelPolarBear.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 = (255 << 16) + (255 << 8) + 255;						eggColor2 = (201 << 16) + (201 << 8) + 201;
		defaultBiomesToSpawn.add(BiomeGenBase.icePlains.biomeName); 		
		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);	
		defaultBiomesToSpawn.add("Ice Wasteland");						defaultBiomesToSpawn.add("Glacier");		
		
	}
	
	@Override
	public void outputDataToList() {
		if(shouldExist){
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			if(ItemList.furPelt.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.furPelt.get()), 8); }
			if(ItemList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.scrapMeat.get()), 10); }
			if(ItemList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.BlackLichen.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
			}
			CustomEntityList.polarBear = Optional.of(customMobData);	
		}
	}
}
