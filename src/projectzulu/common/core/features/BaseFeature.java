package projectzulu.common.core.features;

import java.io.File;
import java.util.Random;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;

public abstract class BaseFeature implements TerrainFeature {
    private boolean shouldSpawn = true;
    protected int minChunkDistance;
    protected int chunksPerSpawn;
    protected boolean printToLog;

    private Size size;
    private String featureName;

    public BaseFeature(String featureName, Size size) {
        this.featureName = featureName.toLowerCase();
        this.size = size;
    }

    @Override
    public String getFeatureName() {
        return featureName;
    }

    @Override
    public boolean isEnabled() {
        return shouldSpawn;
    }

    @Override
    public Size getFeatureSize() {
        return size;
    }

    @Override
    public final void initialize(File modConfigDirectory) {
        Configuration structureBiomeConfig = new Configuration(new File(modConfigDirectory,
                DefaultProps.configDirectory + DefaultProps.structureBiomeConfigFile));
        structureBiomeConfig.load();
        loadSettings(structureBiomeConfig);
        structureBiomeConfig.save();
    }

    protected abstract void loadDefaultSettings();

    protected void loadSettings(Configuration config) {
        loadDefaultSettings();
        shouldSpawn = config.get("Feature." + featureName + ".General", getFeatureName() + " Should Generate",
                shouldSpawn).getBoolean(shouldSpawn);
        
        minChunkDistance = config.get("Feature." + featureName + ".General",
                featureName.toLowerCase() + " minChunkDistance", minChunkDistance).getInt(minChunkDistance);
        minChunkDistance = minChunkDistance < 1 ? 1 : minChunkDistance;
        
        chunksPerSpawn = config.get("Feature." + featureName + ".General",
                featureName.toLowerCase() + " chunksPerSpawn", chunksPerSpawn).getInt(chunksPerSpawn);
        chunksPerSpawn = chunksPerSpawn < 1 ? 1 : chunksPerSpawn;
        
        printToLog = config.get("Feature." + featureName + ".General", featureName.toLowerCase() + " printToLog",
                printToLog).getBoolean(printToLog);
    }

    @Override
    public boolean canGenerateHere(World world, int chunkX, int chunkZ, ChunkCoordinates genBlockCoords, Random random) {
        if (shouldSpawn && (chunkX % minChunkDistance == 0 || chunkZ % minChunkDistance == 0)) {
            if (random.nextInt(chunksPerSpawn) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isStructureHere(World world, int chunkX, int chunkZ, ChunkCoordinates genBlockCoords, Random random) {
        throw new UnsupportedOperationException("Reverse lookup feature has not been implemented yet.");
    }

    protected void logGeneration(ChunkCoordinates genBlockCoords) {
        if (printToLog) {
            ProjectZuluLog.info("Generating %s at %s, %s, %s", getFeatureName(), genBlockCoords.posX,
                    genBlockCoords.posY, genBlockCoords.posZ);
        }
    }
}
