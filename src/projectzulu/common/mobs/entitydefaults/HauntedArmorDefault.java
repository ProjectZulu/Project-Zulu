package projectzulu.common.mobs.entitydefaults;

import java.io.File;

import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.DefaultWithEgg;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.entity.EntityHauntedArmor;
import projectzulu.common.mobs.models.ModelHauntedArmor;

import com.google.common.base.Optional;

public class HauntedArmorDefault extends DefaultWithEgg{
	
	public HauntedArmorDefault(){
		super("Haunted Armor", EntityHauntedArmor.class, EnumCreatureType.monster);		
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelHauntedArmor.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 = (194 << 16) + (194 << 8) + 194;		eggColor2 = (251 << 16) + (246 << 8) + 36;
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.Ectoplasm.meta(), 4);
		super.outputDataToList(config, customMobData);
	}
}
