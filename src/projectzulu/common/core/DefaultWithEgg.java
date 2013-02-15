package projectzulu.common.core;

import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraftforge.common.Configuration;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.ProjectZulu_Mobs;

public abstract class DefaultWithEgg extends DefaultCreature{

	protected int eggColor1;
	protected int eggColor2;
	
	protected DefaultWithEgg(String mobName, Class mobClass) {
		super(mobName, mobClass);
	}
	
	@Override
	public void loadCreaturesFromConfig(Configuration config) {
		super.loadCreaturesFromConfig(config);
		eggColor1 = config.get("MOB CONTROLS."+mobName, mobName.toLowerCase()+" EggColor1", eggColor1).getInt(eggColor1);
		eggColor2 = config.get("MOB CONTROLS."+mobName, mobName.toLowerCase()+" EggColor2", eggColor2).getInt(eggColor2);
	}
	
	@Override
	public void registerEgg() {
		super.registerEgg();
		int eggID = ProjectZulu_Core.getNextDefaultEggID();
		while(EntityList.IDtoClassMapping.containsKey(eggID)){ eggID = ProjectZulu_Core.getNextDefaultEggID(); }
		EntityList.IDtoClassMapping.put(eggID, mobClass); 
		EntityList.entityEggs.put(eggID, new EntityEggInfo(eggID, eggColor1, eggColor2 ));
	}
}
