package projectzulu.experimental.defaultentities;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.mobs.EntityBlueFinch;

import com.google.common.base.Optional;

public class BlueFinchDefault extends DefaultSpawnable{
	
	public BlueFinchDefault(){
		super("Blue Finch", EntityBlueFinch.class);		
		setSpawnProperties(EnumCreatureType.monster, 10, 5, 1, 1);
		setRegistrationProperties(128, 3, true);
				
		eggColor1 =  (38 << 16) + (103 << 8) + 255;						eggColor2 = (224 << 16) + (233 << 8) + 255;
		
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
			CustomEntityList.blueFinch = Optional.of(customMobData);	
		}
	}
}