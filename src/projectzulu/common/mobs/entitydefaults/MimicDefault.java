package projectzulu.common.mobs.entitydefaults;

import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultWithEgg;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.entity.EntityMimic;
import projectzulu.common.mobs.models.ModelMimic;

public class MimicDefault extends DefaultWithEgg{
	
	public MimicDefault(){
		super("Mimic", EntityMimic.class, null);		
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelMimic.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 = (171 << 16) + (121 << 8) + 45;
		eggColor2 = (143 << 16) + (105 << 8) + 29;
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.Ectoplasm.meta(), 5);
		super.outputDataToList(config, customMobData);
	}
}
