package projectzulu.common.blocks;

import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.core.entitydeclaration.CreatureDeclaration;

public class CreeperBlossomPrimedDefault extends CreatureDeclaration{

	public CreeperBlossomPrimedDefault() {
		super("CreeperBlossomPrimed", EntityCreeperBlossomPrimed.class, null);
		setRegistrationProperties(128, 3, true);
		setModelAndRender("", "projectzulu.common.blocks.RenderCreeperBlossomPrimed");
	}
	
	@Override
	public void loadCreaturesFromConfig(Configuration config) {}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		super.outputDataToList(config, customMobData);
	}

}
