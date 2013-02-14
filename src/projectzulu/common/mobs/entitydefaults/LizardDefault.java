package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.DefaultSpawnable;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.entity.EntityLizard;
import projectzulu.common.mobs.models.ModelLizard;

import com.google.common.base.Optional;

public class LizardDefault extends DefaultSpawnable{
	public LizardDefault(){
		super("Lizard", EntityLizard.class);		
		setSpawnProperties(EnumCreatureType.creature, 10, 100, 1, 1);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelLizard.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 = (114 << 16) + (102 << 8) + 74;
		eggColor2 = (181 << 16) + (171 << 8) + 146;
		
		defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);
		defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
		defaultBiomesToSpawn.add("Mountainous Desert");
		defaultBiomesToSpawn.add("Savanna");
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
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.PoisonDroplet.meta()), 4);
			}
			CustomEntityList.lizard = Optional.of(customMobData);	
		}
	}
}
