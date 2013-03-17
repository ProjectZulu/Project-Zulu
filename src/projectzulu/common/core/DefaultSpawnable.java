package projectzulu.common.core;

import java.util.ArrayList;

import projectzulu.common.api.CustomMobData;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.registry.EntityRegistry;

public abstract class DefaultSpawnable extends DefaultWithEgg{

	protected int spawnRate;
	protected int secondarySpawnRate;
	protected int minInChunk;
	protected int maxInChunk;

	protected ArrayList<String> defaultBiomesToSpawn = new ArrayList<String>();	
	ArrayList<BiomeGenBase> biomesToSpawn = new ArrayList();
	
	protected DefaultSpawnable(String mobName, Class mobClass, EnumCreatureType creatureType) {
		super(mobName, mobClass, creatureType);
	}
	
	protected void setSpawnProperties(int spawnRate, int secondarySpawnRate, int minInChunk, int maxInChunk){
		this.spawnRate = spawnRate;
		this.secondarySpawnRate = secondarySpawnRate;
		this.minInChunk = minInChunk;
		this.maxInChunk = maxInChunk;
	}
	
	@Override
	public void loadCreaturesFromConfig(Configuration config) {
		super.loadCreaturesFromConfig(config);
		spawnRate = config.get("MOB CONTROLS."+mobName, mobName.toLowerCase()+" SpawnRate", spawnRate).getInt(spawnRate);
		secondarySpawnRate = config.get("MOB CONTROLS."+mobName, mobName.toLowerCase()+" SecondarySpawnRate",secondarySpawnRate).getInt(secondarySpawnRate);
		minInChunk = config.get("MOB CONTROLS."+mobName, mobName.toLowerCase()+" minInChunk", minInChunk).getInt(minInChunk);
		maxInChunk = config.get("MOB CONTROLS."+mobName, mobName.toLowerCase()+" maxInChunk", maxInChunk).getInt(maxInChunk);
	}
	
	@Override
	public void loadBiomesFromConfig(Configuration config) {
		for (int i = 0; i < BiomeGenBase.biomeList.length; i++) {
			if(BiomeGenBase.biomeList[i] == null){
				continue;
			}
			if(config.get("Mob Spawn Biome Controls."+mobName, mobName.toLowerCase()+" in "+BiomeGenBase.biomeList[i].biomeName, defaultBiomesToSpawn.contains(BiomeGenBase.biomeList[i].biomeName)).getBoolean(false)){
				biomesToSpawn.add(BiomeGenBase.biomeList[i]);
			}
		}
	}
	
	/* Create loadCustomMobData() method which calls outputData to List. loadCustom contains calls that are the same for all creatures */
	public void outputDataToList(Configuration config, CustomMobData customMobData){
		customMobData.secondarySpawnRate = secondarySpawnRate;
	}
	
	@Override
	public void addSpawn() {
		for (int i = 0; i < biomesToSpawn.size(); i++){
			EntityRegistry.addSpawn(mobClass, spawnRate, minInChunk, maxInChunk, enumCreatureType, biomesToSpawn.get(i));
			if(reportSpawningInLog){
				ProjectZuluLog.info("Registering %s to biome %s at rates of %s,%s,%s",
						mobClass.getSimpleName(), biomesToSpawn.get(i).biomeName, spawnRate, minInChunk, maxInChunk);
			}
		}
	}
}
