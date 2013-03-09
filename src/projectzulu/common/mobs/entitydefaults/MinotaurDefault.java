package projectzulu.common.mobs.entitydefaults;

import java.io.File;

import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.DefaultWithEgg;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.entity.EntityMinotaur;
import projectzulu.common.mobs.models.ModelMinotaur;

import com.google.common.base.Optional;

public class MinotaurDefault extends DefaultWithEgg{
	
	public MinotaurDefault(){
		super("Minotaur", EntityMinotaur.class);		
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelMinotaur.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 = (51 << 16) + (34 << 8) + 8;			eggColor2 = (255 << 16) + (255 << 8) + 255;
	}
	
	@Override
	public void outputDataToList(File configDirectory) {
		Configuration config = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		config.load();
		CustomMobData customMobData = new CustomMobData(mobName, reportSpawningInLog);
		customMobData.shouldDespawn = config.get("MOB CONTROLS."+mobName, mobName+" Should Despawn", true).getBoolean(true);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.furPelt, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scrapMeat, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.LargeHeart.meta(), 4);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.Ectoplasm.meta(), 4);
		ConfigHelper.userItemConfigRangeToMobData(config, "MOB CONTROLS."+mobName, customMobData);
		config.save();
		CustomEntityList.MINOTAUR.modData = Optional.of(customMobData);	
	}
}
