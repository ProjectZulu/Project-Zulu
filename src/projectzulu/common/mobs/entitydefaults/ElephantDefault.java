package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.DefaultSpawnable;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.entity.EntityElephant;
import projectzulu.common.mobs.models.ModelElephant;

import com.google.common.base.Optional;

public class ElephantDefault extends DefaultSpawnable{
	
	public ElephantDefault(){
		super("Elephant", EntityElephant.class);		
		setSpawnProperties(EnumCreatureType.creature, 10, 100, 1, 2);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelElephant.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 =  (88 << 16) + (67 << 8) + 50;				eggColor2 = (190 << 16) + (165 << 8) + 145;
		
		defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		
		defaultBiomesToSpawn.add("Savanna");
	}
	
	@Override
	public void outputDataToList() {
		if(shouldExist){
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			if(ItemList.scaleItem.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.scaleItem.get()), 10); }
			if(ItemList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.scrapMeat.get()), 10); }
			if(ItemList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.SmallHeart.meta()), 4);
			}
			CustomEntityList.armadillo = Optional.of(customMobData);
		}
	}
}
