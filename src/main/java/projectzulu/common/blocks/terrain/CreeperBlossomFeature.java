package projectzulu.common.blocks.terrain;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.api.BlockList;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.terrain.BiomeFeature;
import projectzulu.common.core.terrain.FeatureConfiguration;

public class CreeperBlossomFeature extends BiomeFeature {
    private int density = 1;

    public CreeperBlossomFeature() {
        super("Creeper Blossom", Size.TINY);
    }

    @Override
    public boolean isEnabled() {
        return BlockList.creeperBlossom.isPresent() && super.isEnabled();
    }

    @Override
    protected void loadDefaultSettings() {
        minChunkDistance = 1;
        chunksPerSpawn = 20;
    }

    @Override
    protected void loadSettings(FeatureConfiguration config) {
        super.loadSettings(config);
        density = config.get("Feature." + getFeatureSize() + "." + getFeatureName() + ".General", "Density", density)
                .getInt(density);
    }

    @Override
    protected Collection<String> getDefaultBiomeList() {
        return Arrays.asList(new String[] { BiomeGenBase.forest.biomeName, BiomeGenBase.forestHills.biomeName });
    }

    @Override
    public ChunkCoordinates[] getGenerationCoordinates(World world, int chunkX, int chunkZ) {
        Random random = new Random(world.getSeed());
        long randomFactor1 = random.nextLong() / 2L * 2L + 1L;
        long randomFactor2 = random.nextLong() / 2L * 2L + 1L;
        random.setSeed(chunkX * 16 * randomFactor1 + chunkZ * 16 * randomFactor2 ^ world.getSeed());

        ChunkCoordinates[] coordinates = new ChunkCoordinates[density];
        for (int i = 0; i < density; i++) {
            int xCoord = chunkX * 16 + random.nextInt(16);
            int zCoord = chunkZ * 16 + random.nextInt(16);
            coordinates[i] = new ChunkCoordinates(xCoord, world.getTopSolidOrLiquidBlock(xCoord, zCoord), zCoord);
        }
        return coordinates;
    }

    @Override
    public boolean canGenerateHere(World world, int chunkX, int chunkZ, ChunkCoordinates genBlockCoords, Random random) {
        if (super.canGenerateHere(world, chunkX, chunkZ, genBlockCoords, random)) {
            if (world.isAirBlock(genBlockCoords.posX, genBlockCoords.posY, genBlockCoords.posZ)) {
                Block iDBelow = world.getBlock(genBlockCoords.posX, genBlockCoords.posY - 1, genBlockCoords.posZ);
                if (iDBelow == Blocks.dirt || iDBelow == Blocks.grass) {
                    if (printToLog) {
                        ProjectZuluLog.info("Generating %s at %s, %s, %s", getFeatureName(), genBlockCoords.posX,
                                genBlockCoords.posY, genBlockCoords.posZ);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void generateFeature(World world, int chunkX, int chunkZ, ChunkCoordinates genBlockCoords, Random random,
            FeatureDirection direction) {
        world.setBlock(genBlockCoords.posX, genBlockCoords.posY, genBlockCoords.posZ, BlockList.creeperBlossom.get());
    }
}