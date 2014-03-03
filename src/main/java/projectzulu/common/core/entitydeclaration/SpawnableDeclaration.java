package projectzulu.common.core.entitydeclaration;

import java.util.ArrayList;
import java.util.HashSet;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.config.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.ProjectZuluLog;
import cpw.mods.fml.common.registry.EntityRegistry;

public abstract class SpawnableDeclaration extends EggableDeclaration {

    protected int spawnRate;
    protected boolean useGlobalSpawn = false;
    protected int secondarySpawnRate;
    protected int minInChunk;
    protected int maxInChunk;
    protected EnumCreatureType spawnType;

    private ArrayList<SpawnEntry> biomesToSpawn = new ArrayList<SpawnEntry>();

    protected SpawnableDeclaration(String mobName, int entityID, Class mobClass, EnumCreatureType creatureType) {
        super(mobName, entityID, mobClass, creatureType);
        spawnType = creatureType;
    }

    protected void setSpawnProperties(int spawnRate, int secondarySpawnRate, int minInChunk, int maxInChunk) {
        this.spawnRate = spawnRate;
        this.secondarySpawnRate = secondarySpawnRate;
        this.minInChunk = minInChunk;
        this.maxInChunk = maxInChunk;
    }

    @Override
    public void loadCreaturesFromConfig(Configuration config) {
        super.loadCreaturesFromConfig(config);
        spawnRate = config.get("MOB CONTROLS." + mobName, mobName.toLowerCase() + " SpawnRate", spawnRate).getInt(
                spawnRate);
        secondarySpawnRate = config.get("MOB CONTROLS." + mobName, mobName.toLowerCase() + " SecondarySpawnRate",
                secondarySpawnRate).getInt(secondarySpawnRate);
        minInChunk = config.get("MOB CONTROLS." + mobName, mobName.toLowerCase() + " minInChunk", minInChunk).getInt(
                minInChunk);
        maxInChunk = config.get("MOB CONTROLS." + mobName, mobName.toLowerCase() + " maxInChunk", maxInChunk).getInt(
                maxInChunk);
        maxSpawnInChunk = config.get("MOB CONTROLS." + mobName, "Max Pack Size", maxSpawnInChunk).getInt(
                maxSpawnInChunk);
        spawnType = ConfigHelper.configGetCreatureType(config, "MOB CONTROLS." + mobName, "Spawn List Type", spawnType);
        useGlobalSpawn = config.get("MOB CONTROLS." + mobName, "Use Global Spawn Rates", useGlobalSpawn).getBoolean(
                useGlobalSpawn);
    }

    @Override
    public void loadBiomesFromConfig(Configuration config) {
        HashSet<String> defaultBiomesToSpawn = getDefaultBiomesToSpawn();
        for (int i = 0; i < BiomeGenBase.getBiomeGenArray().length; i++) {
            if (BiomeGenBase.getBiomeGenArray()[i] == null) {
                continue;
            }

            boolean defaultShouldSpawn = defaultBiomesToSpawn.contains(BiomeGenBase.getBiomeGenArray()[i].biomeName);
            SpawnEntry spawnEntry = ConfigHelper.configGetSpawnEntry(config, "Mob Spawn Biome Controls." + mobName,
                    BiomeGenBase.getBiomeGenArray()[i], defaultShouldSpawn, spawnRate, minInChunk, maxInChunk);
            if (spawnEntry != null) {
                biomesToSpawn.add(spawnEntry);
            }
        }
    }

    public abstract HashSet<String> getDefaultBiomesToSpawn();

    /*
     * Create loadCustomMobData() method which calls outputData to List. loadCustom contains calls that are the same for
     * all creatures
     */
    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        super.outputDataToList(config, customMobData);
        customMobData.secondarySpawnRate = secondarySpawnRate;
        customMobData.spawnType = spawnType;
    }

    @Override
    public void addSpawn() {
        if (spawnType == null) {
            return;
        }

        for (int i = 0; i < biomesToSpawn.size(); i++) {
            if (useGlobalSpawn) {
                if (spawnRate > 0) {
                    EntityRegistry.addSpawn(mobClass, spawnRate, minInChunk, maxInChunk, spawnType,
                            biomesToSpawn.get(i).biome);
                }
            } else {
                if (biomesToSpawn.get(i).spawnRate > 0) {
                    EntityRegistry.addSpawn(mobClass, biomesToSpawn.get(i).spawnRate, biomesToSpawn.get(i).minInChunk,
                            biomesToSpawn.get(i).maxInChunk, spawnType, biomesToSpawn.get(i).biome);
                }
            }
            if (reportSpawningInLog) {
                ProjectZuluLog.info("Registering %s to biome %s at rates of %s,%s,%s", mobClass.getSimpleName(),
                        biomesToSpawn.get(i).biome.biomeName, biomesToSpawn.get(i).spawnRate,
                        biomesToSpawn.get(i).minInChunk, biomesToSpawn.get(i).maxInChunk);
            }
        }
    }

    protected HashSet<String> typeToArray(BiomeDictionary.Type type) {
        BiomeGenBase[] biomes = BiomeDictionary.getBiomesForType(type);
        HashSet<String> names = new HashSet<String>(15);
        for (BiomeGenBase biome : biomes) {
            names.add(biome.biomeName);
        }
        return names;
    }
}
