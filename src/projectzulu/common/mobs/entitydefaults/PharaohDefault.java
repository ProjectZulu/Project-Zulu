package projectzulu.common.mobs.entitydefaults;

import java.io.File;

import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.core.DefaultCreature;
import projectzulu.common.mobs.entity.EntityMummyPharaoh;

import com.google.common.base.Optional;

public class PharaohDefault extends DefaultCreature{
	public PharaohDefault(){
		super("Mummy Pharaoh", EntityMummyPharaoh.class);		
		setRegistrationProperties(128, 3, true);
		setModelAndRender("projectzulu.common.mobs.models.ModelMummyPharaoh", "projectzulu.common.mobs.renders.RenderMummyPharaoh");
	}
	
	@Override
	public void outputDataToList(File configDirectory) {
		if(shouldExist){
			CustomEntityList.pharaoh = Optional.of(new CustomMobData(mobName, reportSpawningInLog));	
		}
	}
}
