package projectzulu.common.core.entitydeclaration;

import net.minecraft.world.biome.BiomeGenBase;

public class SpawnEntry {
    public final BiomeGenBase biome;
    public final int spawnRate;
    public final int minInChunk;
    public final int maxInChunk;
    public SpawnEntry(BiomeGenBase biome, int spawnRate, int minInChunk, int maxInChunk) {
        this.biome = biome;
        this.spawnRate = spawnRate;
        this.minInChunk = minInChunk;
        this.maxInChunk = maxInChunk;
    }
}
