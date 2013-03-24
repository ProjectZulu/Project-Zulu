package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.entitydeclaration.EggableDeclaration;
import projectzulu.common.mobs.entity.EntityMinotaur;
import projectzulu.common.mobs.models.ModelMinotaur;

public class MinotaurDeclaration extends EggableDeclaration{
	
	public MinotaurDeclaration(){
		super("Minotaur", EntityMinotaur.class, EnumCreatureType.monster);		
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelMinotaur.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 = (51 << 16) + (34 << 8) + 8;			eggColor2 = (255 << 16) + (255 << 8) + 255;
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.furPelt, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scrapMeat, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.LargeHeart.meta(), 4);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.Ectoplasm.meta(), 4);
		super.outputDataToList(config, customMobData);
	}
}
