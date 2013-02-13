package projectzulu.common.mobs.entitydefaults;

import net.minecraft.item.ItemStack;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.entity.EntityMinotaur;

import com.google.common.base.Optional;

public class MinotaurDefault extends DefaultWithEgg{
	
	public MinotaurDefault(){
		super("Minotaur", EntityMinotaur.class);		
		setRegistrationProperties(128, 3, true);
		
		eggColor1 = (51 << 16) + (34 << 8) + 8;			eggColor2 = (255 << 16) + (255 << 8) + 255;
	}
	
	@Override
	public void outputDataToList() {
		if(shouldExist){
			CustomMobData customMobData = new CustomMobData(mobName, reportSpawningInLog);
			if(ItemList.furPelt.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemList.furPelt.get()), 10); }
			if(ItemList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemList.genericCraftingItems1.get().itemID, 1, ItemGenerics.Properties.Ectoplasm.meta()), 4);
			}
			CustomEntityList.minotaur = Optional.of(customMobData);	
		}
	}
}
