package projectzulu.common.core.terrain;

import java.io.File;
import java.util.Random;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
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
        FeatureConfiguration configuration = new FeatureConfiguration(modConfigDirectory);
        configuration.load();
        loadSettings(configuration);
        configuration.save();
    }

    protected abstract void loadDefaultSettings();

    protected void loadSettings(FeatureConfiguration config) {
        loadDefaultSettings();

        shouldSpawn = config.getFeatureProperty(this, "General", "Should Generate", shouldSpawn).getBoolean(shouldSpawn);

        minChunkDistance = config.getFeatureProperty(this, "General", "minChunkDistance", minChunkDistance).getInt(
                minChunkDistance);
        minChunkDistance = minChunkDistance < 1 ? 1 : minChunkDistance;

        chunksPerSpawn = config.getFeatureProperty(this, "General", "chunksPerSpawn", chunksPerSpawn).getInt(chunksPerSpawn);
        chunksPerSpawn = chunksPerSpawn < 1 ? 1 : chunksPerSpawn;

        printToLog = config.getFeatureProperty(this, "General", "printToLog", printToLog).getBoolean(printToLog);
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
