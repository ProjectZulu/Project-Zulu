package projectzulu.experimental.defaultentities;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.EntityArmadillo;
import projectzulu.common.mobs.EntityMimic;

import com.google.common.base.Optional;

public class MimicDefault extends DefaultWithEgg{
	
	public MimicDefault(){
		super("Mimic", EntityMimic.class);		
		setRegistrationProperties(128, 3, true);
		
		eggColor1 = (171 << 16) + (121 << 8) + 45;
		eggColor2 = (143 << 16) + (105 << 8) + 29;
	}
	
	@Override
	public void outputDataToList() {
		if(shouldExist){
			CustomMobData customMobData = new CustomMobData(mobName, reportSpawningInLog);
			if(ItemList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.Ectoplasm.meta()), 5);
			}
			CustomEntityList.mimic = Optional.of(customMobData);	
		}
	}
}
