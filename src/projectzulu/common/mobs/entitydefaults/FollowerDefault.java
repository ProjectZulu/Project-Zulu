package projectzulu.common.mobs.entitydefaults;

import net.minecraftforge.common.Configuration;
import projectzulu.common.mobs.entity.EntityFollower;

public class FollowerDefault extends DefaultCreature{

	public FollowerDefault(){
		super("Follower", EntityFollower.class);		
		setRegistrationProperties(128, 3, true);
	}
	
	@Override
	public void loadCreatureFromConfig(Configuration config) {}
	
	@Override
	public void outputDataToList() {}
}
