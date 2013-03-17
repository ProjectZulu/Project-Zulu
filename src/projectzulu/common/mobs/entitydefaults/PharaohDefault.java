package projectzulu.common.mobs.entitydefaults;

import java.io.File;

import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultCreature;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entity.EntityMummyPharaoh;

import com.google.common.base.Optional;

public class PharaohDefault extends DefaultCreature{
	public PharaohDefault(){
		super("Mummy Pharaoh", EntityMummyPharaoh.class, EnumCreatureType.monster);		
		setRegistrationProperties(128, 3, true);
		setModelAndRender("projectzulu.common.mobs.models.ModelMummyPharaoh", "projectzulu.common.mobs.renders.RenderMummyPharaoh");
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		super.outputDataToList(config, customMobData);
	}
}
