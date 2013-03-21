package projectzulu.common.mobs.entitydefaults;

import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.core.DefaultCreature;
import projectzulu.common.mobs.entity.EntityFollower;
import projectzulu.common.mobs.models.ModelFollower;

public class FollowerDefault extends DefaultCreature{

	public FollowerDefault(){
		super("Follower", EntityFollower.class, null);		
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelFollower.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

	}
	
	@Override
	public void loadCreaturesFromConfig(Configuration config) {}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {}
}
