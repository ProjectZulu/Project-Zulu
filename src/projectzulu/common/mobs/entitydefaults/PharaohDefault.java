package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.entity.EntityArmadillo;
import projectzulu.common.mobs.entity.EntityMummyPharaoh;

import com.google.common.base.Optional;

public class PharaohDefault extends DefaultCreature{
	public PharaohDefault(){
		super("Mummy Pharaoh", EntityMummyPharaoh.class);		
		setRegistrationProperties(128, 3, true);
	}
	
	@Override
	public void outputDataToList() {
		if(shouldExist){
			CustomEntityList.pharaoh = Optional.of(new CustomMobData(mobName, reportSpawningInLog));	
		}
	}
}
