package projectzulu.common.core.terrain;

import java.io.File;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import projectzulu.common.core.terrain.TerrainFeature.FeatureDirection;
import projectzulu.common.core.terrain.TerrainFeature.Size;
import cpw.mods.fml.common.IWorldGenerator;

public class FeatureGenerator implements IWorldGenerator {

    /** Mapping From FeatureName to feature instance. Feature name is in lower case */
    private ConcurrentHashMap<String, TerrainFeature> structures = new ConcurrentHashMap<String, TerrainFeature>();

    public void registerStructure(TerrainFeature... feature) {
        for (TerrainFeature terrainFeature : feature) {
            structures.put(terrainFeature.getFeatureName().toLowerCase(), terrainFeature);
        }
    }

    public TerrainFeature getRegisteredStructure(String featureName) {
        return structures.get(featureName.toLowerCase());
    }

    public void initialize(File modConfigDirectory) {
        for (TerrainFeature terrainFeature : structures.values()) {
            terrainFeature.initialize(modConfigDirectory);
        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
            IChunkProvider chunkProvider) {

        for (Size featureSize : Size.values()) {
            for (TerrainFeature terrainFeature : structures.values()) {
                if (terrainFeature.getFeatureSize() != featureSize) {
                    continue;
                }

                ChunkCoordinates[] coords = terrainFeature.getGenerationCoordinates(world, chunkX, chunkZ);
                boolean generated = false;
                for (ChunkCoordinates genWorldCoords : coords) {
                    if (terrainFeature.canGenerateHere(world, chunkX, chunkZ, genWorldCoords, random)) {
                        terrainFeature.generateFeature(world, chunkX, chunkZ, genWorldCoords, random, FeatureDirection.CENTERED);
                        generated = true;
                    }
                }
                if (generated) {
                    return;
                }
            }
        }
    }
}
