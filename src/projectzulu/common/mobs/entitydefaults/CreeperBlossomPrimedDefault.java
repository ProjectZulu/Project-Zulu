package projectzulu.common.mobs.entitydefaults;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.EntityCreeperBlossomPrimed;
import projectzulu.common.core.ItemGenerics;

import com.google.common.base.Optional;

public class CreeperBlossomPrimedDefault extends DefaultCreature{

	public CreeperBlossomPrimedDefault() {
		super("CreeperBlossomPrimed", EntityCreeperBlossomPrimed.class);
		setRegistrationProperties(128, 3, true);
	}
	
	@Override
	public void loadCreatureFromConfig(Configuration config) {}
	
	@Override
	public void outputDataToList() {
		if(shouldExist){
			CustomEntityList.creeperBlossom = Optional.of(new CustomMobData(mobName, reportSpawningInLog));	
		}
	}

}
