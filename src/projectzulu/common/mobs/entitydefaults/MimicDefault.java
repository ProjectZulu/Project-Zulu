package projectzulu.common.mobs.entitydefaults;

import net.minecraft.item.ItemStack;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.DefaultWithEgg;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.entity.EntityMimic;
import projectzulu.common.mobs.models.ModelMimic;

import com.google.common.base.Optional;

public class MimicDefault extends DefaultWithEgg{
	
	public MimicDefault(){
		super("Mimic", EntityMimic.class);		
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelMimic.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

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
