package projectzulu.common.mobs.entitydefaults;

import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.core.entitydeclaration.CreatureDeclaration;
import projectzulu.common.mobs.entity.EntityLizardSpit;

public class LizardSpitDeclaration extends CreatureDeclaration{

	public LizardSpitDeclaration() {
		super("Lizard Spit", EntityLizardSpit.class, null);
		setRegistrationProperties(128, 3, true);
		setModelAndRender("", "projectzulu.common.mobs.renders.RenderLizardSpit");
	}
	
	@Override
	public void loadCreaturesFromConfig(Configuration config) {}

	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {}
}
