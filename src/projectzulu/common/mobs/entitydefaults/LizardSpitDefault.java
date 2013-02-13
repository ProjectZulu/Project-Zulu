package projectzulu.common.mobs.entitydefaults;

import net.minecraftforge.common.Configuration;
import projectzulu.common.mobs.entity.EntityLizardSpit;

public class LizardSpitDefault extends DefaultCreature{

	public LizardSpitDefault() {
		super("Lizard Spit", EntityLizardSpit.class);
		setRegistrationProperties(128, 3, true);
	}
	
	@Override
	public void loadCreatureFromConfig(Configuration config) {}

	@Override
	public void outputDataToList() {}

}
