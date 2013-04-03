package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.BlockList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.entitydeclaration.CreatureDeclaration;
import projectzulu.common.mobs.entity.EntityMummyPharaoh;

public class PharaohDeclaration extends CreatureDeclaration{
	public PharaohDeclaration(){
		super("Mummy Pharaoh", EntityMummyPharaoh.class, EnumCreatureType.monster);		
		setRegistrationProperties(128, 3, true);
        setDropAmount(0, 3);

		setModelAndRender("projectzulu.common.mobs.models.ModelMummyPharaoh", "projectzulu.common.mobs.renders.RenderMummyPharaoh");
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.ingotIron, 0, 40);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, BlockList.jasper, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.ankh, 0, 10);
		super.outputDataToList(config, customMobData);
	}
}
