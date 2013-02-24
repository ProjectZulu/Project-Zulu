package projectzulu.common.mobs.entitydefaults;

import java.io.File;

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
		super("Mummy Pharaoh", EntityMummyPharaoh.class);		
		setRegistrationProperties(128, 3, true);
		setModelAndRender("projectzulu.common.mobs.models.ModelMummyPharaoh", "projectzulu.common.mobs.renders.RenderMummyPharaoh");
	}
	
	@Override
	public void outputDataToList(File configDirectory) {
		Configuration config = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		config.load();
		CustomMobData customMobData = new CustomMobData(mobName, reportSpawningInLog);
		ConfigHelper.userItemConfigRangeToMobData(config, "MOB CONTROLS."+mobName, customMobData);
		config.save();
		CustomEntityList.MUMMYPHARAOH.modData = Optional.of(customMobData);
	}
}
