package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import projectzulu.common.mobs.entity.EntityMummy;

public class MummyDeclaration extends SpawnableDeclaration{
	
	public MummyDeclaration(){
		super("Mummy", EntityMummy.class, EnumCreatureType.monster);		
		setSpawnProperties(5, 100, 1, 2);
		setRegistrationProperties(128, 3, true);
		setModelAndRender("projectzulu.common.mobs.models.ModelMummy", "projectzulu.common.mobs.renders.RenderGenericLiving");
        setDropAmount(0, 2);

		eggColor1 = (255 << 16) + (255 << 8) + 255;	
		eggColor2 = (255 << 16) + (255 << 8) + 255;
		
		defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);
		defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		super.outputDataToList(config, customMobData);
	}
}
