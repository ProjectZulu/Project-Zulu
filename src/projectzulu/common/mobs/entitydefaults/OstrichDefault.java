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
import projectzulu.common.mobs.entity.EntityOstrich;
import projectzulu.common.mobs.models.ModelOstrich;

import com.google.common.base.Optional;

public class OstrichDefault extends DefaultSpawnable{
	
	public OstrichDefault(){
		super("Ostrich", EntityOstrich.class);		
		setSpawnProperties(EnumCreatureType.creature, 10, 100, 1, 2);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelOstrich.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 = (25 << 16) + (18 << 8) + 14;						eggColor2 = (232 << 16) + (107 << 8) + 101;
		
		defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName); 
		defaultBiomesToSpawn.add("Meadow");								defaultBiomesToSpawn.add("Savanna");		
	}
	
	@Override
	public void outputDataToList() {
		if(shouldExist){
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			customMobData.addLootToMob(new ItemStack(Item.feather), 8);
			if(ItemList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.scrapMeat.get()), 10); }
			if(ItemList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.Talon.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.SmallHeart.meta()), 4);
			}
			CustomEntityList.ostrich = Optional.of(customMobData);	
		}
	}
}
