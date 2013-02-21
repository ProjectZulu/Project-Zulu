package projectzulu.common.mobs.entitydefaults;

import java.io.File;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.DefaultWithEgg;
import projectzulu.common.core.ItemGenerics;
import net.minecraftforge.common.Configuration;
import projectzulu.common.mobs.entity.EntityMimic;
import projectzulu.common.mobs.models.ModelMimic;

import com.google.common.base.Optional;

public class MimicDefault extends DefaultWithEgg{
	
	public MimicDefault(){
		super("Mimic", EntityMimic.class);		
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelMimic.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 = (171 << 16) + (121 << 8) + 45;
		eggColor2 = (143 << 16) + (105 << 8) + 29;
	}
	
	@Override
	public void outputDataToList(File configDirectory) {
		Configuration config = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		config.load();
		CustomMobData customMobData = new CustomMobData(mobName, reportSpawningInLog);
		customMobData.shouldDespawn = config.get("MOB CONTROLS."+mobName, mobName+" Should Despawn", false).getBoolean(true);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.Ectoplasm.meta(), 5);
		config.save();
		CustomEntityList.MIMIC.modData = Optional.of(customMobData);
	}
}
