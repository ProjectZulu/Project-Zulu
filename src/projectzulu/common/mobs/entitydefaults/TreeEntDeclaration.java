package projectzulu.common.mobs.entitydefaults;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import projectzulu.common.mobs.entity.EntityTreeEnt;
import projectzulu.common.mobs.models.ModelTreeEnt;

public class TreeEntDeclaration extends SpawnableDeclaration{
	
	public TreeEntDeclaration(){
		super("TreeEnt", EntityTreeEnt.class, EnumCreatureType.creature);		
		setSpawnProperties(1, 7, 1, 1);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelTreeEnt.class, "projectzulu.common.mobs.renders.RenderGenericLiving");
        setDropAmount(0, 2);

		eggColor1 = (17 << 16) + (6 << 8) + 3;
		eggColor2 = (83 << 16) + (56 << 8) + 29;
		
		defaultBiomesToSpawn.add(BiomeGenBase.taiga.biomeName);
		defaultBiomesToSpawn.add(BiomeGenBase.taigaHills.biomeName);
		defaultBiomesToSpawn.add("Alpine");
		defaultBiomesToSpawn.add("Mountain Taiga");
		defaultBiomesToSpawn.add("Snow Forest");
		defaultBiomesToSpawn.add("Snowy Rainforest");
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Block.wood, 1, 8);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.Bark.meta(), 4);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.BlackLichen.meta(), 4);
		super.outputDataToList(config, customMobData);
	}
}