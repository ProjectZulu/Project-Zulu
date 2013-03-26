package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import projectzulu.common.mobs.entity.EntityPenguin;
import projectzulu.common.mobs.models.ModelPenguin;

public class PenguinDeclaration extends SpawnableDeclaration{
	
	public PenguinDeclaration(){
		super("Penguin", EntityPenguin.class, EnumCreatureType.creature);		
		setSpawnProperties(10, 100, 1, 3);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelPenguin.class, "projectzulu.common.mobs.renders.RenderGenericLiving");
        setDropAmount(0, 2);

		eggColor1 = (22 << 16) + (16 << 8) + 13;
		eggColor2 = (235 << 16) + (235 << 8) + 235;
		
		defaultBiomesToSpawn.add(BiomeGenBase.icePlains.biomeName);	
		defaultBiomesToSpawn.add("Ice Wasteland");
		defaultBiomesToSpawn.add("Glacier");
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.feather, 0, 8);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scrapMeat, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.SmallHeart.meta(), 4);
		super.outputDataToList(config, customMobData);
	}
}