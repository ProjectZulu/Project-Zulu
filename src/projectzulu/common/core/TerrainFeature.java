package projectzulu.common.core;

import java.io.File;
import java.util.Random;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public interface TerrainFeature {

    enum Size {
        /* Feature is larger than a single chunk */
        LARGE,
        /* Is appriximately the size of a chunk */
        MEDIUM,
        /* Size is less than a chunk, but more than a block */
        SMALL,
        /* Spans a single or a couple blocks */
        TINY
    }

    public abstract String getFeatureName();

    public abstract boolean isEnabled();

    public abstract Size getFeatureSize();

    public abstract void initialize(File modConfigDirectory);

    /**
     * From the World and Chunk. Generates an array of positions to attempt to generate the feature at.
     * 
     * Each Point should be able to spawn
     * 
     * @param world World to generate in
     * @param chunkX X Coordinate of Chunk to generate in
     * @param chunkZ Z Coordinate of Chunk to generate in
     * @return
     */
    public abstract ChunkCoordinates[] getGenerationCoordinates(World world, int chunkX, int chunkZ);

    /**
     * Determines if the Feature can generate at the specified coordinates
     * 
     * @param world World to generate in
     * @param chunkX X Coordinate of Chunk to generate in
     * @param chunkZ Z Coordinate of Chunk to generate in
     * @param genBlockCoords Coordinates to generate at
     * @param random
     * @return
     */
    public abstract boolean canGenerateHere(World world, int chunkX, int chunkZ, ChunkCoordinates genBlockCoords,
            Random random);

    public abstract boolean isStructureHere(World world, int chunkX, int chunkZ, ChunkCoordinates genBlockCoords,
            Random random);

    public abstract void generateFeature(World world, int chunkX, int chunkZ, ChunkCoordinates genBlockCoords,
            Random random);
}
