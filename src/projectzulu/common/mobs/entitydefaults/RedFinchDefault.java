package projectzulu.common.mobs.entitydefaults;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.mobs.entity.EntityRedFinch;

import com.google.common.base.Optional;

public class RedFinchDefault extends DefaultSpawnable{
	
	public RedFinchDefault(){
		super("Red Finch", EntityRedFinch.class);		
		setSpawnProperties(EnumCreatureType.monster, 10, 5, 1, 1);
		setRegistrationProperties(128, 3, true);
		
		eggColor1 = (255 << 16) + (29 << 8) + 0;						eggColor2 = (255 << 16) + (203 << 8) + 186;
		
		defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 
		defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName); 
		defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");	
		defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");		
		defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");		
		defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");		
		defaultBiomesToSpawn.add("Woodlands");	
	}
	
	@Override
	public void outputDataToList() {
		if(shouldExist){
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.feather), 8);
			CustomEntityList.redFinch = Optional.of(customMobData);	
		}
	}
}
