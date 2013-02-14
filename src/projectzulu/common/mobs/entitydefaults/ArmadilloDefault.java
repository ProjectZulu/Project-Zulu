package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.DefaultSpawnable;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.entity.EntityArmadillo;
import projectzulu.common.mobs.models.ModelArmadillo;

import com.google.common.base.Optional;

public class ArmadilloDefault extends DefaultSpawnable{
	
	public ArmadilloDefault(){
		super("Armadillo", EntityArmadillo.class);		
		setSpawnProperties(EnumCreatureType.creature, 10, 100, 2, 4);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelArmadillo.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 = (116 << 16) + (64 << 8) + 33;
		eggColor2 = (60 << 16) + (51 << 8) + 10;
		
		defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);			defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
		defaultBiomesToSpawn.add("Mountainous Desert");						defaultBiomesToSpawn.add("Savanna");
		defaultBiomesToSpawn.add("Mountain Ridge");	
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
