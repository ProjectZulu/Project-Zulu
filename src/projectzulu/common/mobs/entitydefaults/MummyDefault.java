package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.entity.EntityArmadillo;
import projectzulu.common.mobs.entity.EntityMummy;

import com.google.common.base.Optional;

public class MummyDefault extends DefaultSpawnable{
	
	public MummyDefault(){
		super("Mummy", EntityMummy.class);		
		setSpawnProperties(EnumCreatureType.creature, 5, 100, 1, 2);
		setRegistrationProperties(128, 3, true);

		eggColor1 = (255 << 16) + (255 << 8) + 255;	
		eggColor2 = (255 << 16) + (255 << 8) + 255;
		
		defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);
		defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
	}
	
	@Override
	public void outputDataToList() {
		if(shouldExist){
			CustomEntityList.mummy = Optional.of(new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog));	
		}
	}
}
