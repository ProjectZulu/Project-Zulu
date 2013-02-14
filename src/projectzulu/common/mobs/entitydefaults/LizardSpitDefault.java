package projectzulu.common.mobs.entitydefaults;

import net.minecraftforge.common.Configuration;
import projectzulu.common.core.DefaultCreature;
import projectzulu.common.mobs.entity.EntityLizardSpit;

public class LizardSpitDefault extends DefaultCreature{

	public LizardSpitDefault() {
		super("Lizard Spit", EntityLizardSpit.class);
		setRegistrationProperties(128, 3, true);
		setModelAndRender("", "projectzulu.common.mobs.renders.RenderLizardSpit");
	}
	
	@Override
	public void loadCreaturesFromConfig(Configuration config) {}

	@Override
	public void outputDataToList() {}

}
