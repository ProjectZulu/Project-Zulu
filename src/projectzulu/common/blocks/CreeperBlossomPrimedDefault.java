package projectzulu.common.blocks;

import java.io.File;

import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.core.DefaultCreature;

import com.google.common.base.Optional;

public class CreeperBlossomPrimedDefault extends DefaultCreature{

	public CreeperBlossomPrimedDefault() {
		super("CreeperBlossomPrimed", EntityCreeperBlossomPrimed.class, null);
		setRegistrationProperties(128, 3, true);
		setModelAndRender("", "projectzulu.common.blocks.RenderCreeperBlossomPrimed");
	}
	
	@Override
	public void loadCreaturesFromConfig(Configuration config) {}
	
	@Override
	public void outputDataToList(File configDirectory) {
		if(shouldExist){
			CustomEntityList.CREEPERBLOSSONPRIMED.modData = Optional.of(new CustomMobData(mobName, reportSpawningInLog));	
		}
	}

}
