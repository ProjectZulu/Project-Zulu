package projectzulu.common.core;

import java.io.File;
import java.util.Random;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public interface TerrainFeature {

    enum Size {
        LARGE, MEDIUM, SMALL, TINY
    }

    public abstract String getFeatureName();

    public abstract boolean isEnabled();

    public abstract Size getFeatureSize();

    public abstract void initialize(File modConfigDirectory);

    public abstract boolean canGenerateHere(World world, int chunkX, int chunkZ, ChunkCoordinates genBlockCoords,
            Random random);

    public abstract boolean isStructureHere(World world, int chunkX, int chunkZ, ChunkCoordinates genBlockCoords,
            Random random);

    public abstract void generateFeature(World world, int chunkX, int chunkZ, ChunkCoordinates genBlockCoords,
            Random random);
}
