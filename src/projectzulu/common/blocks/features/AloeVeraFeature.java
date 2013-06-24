package projectzulu.common.blocks.features;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.BlockList;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.features.BiomeFeature;

public class AloeVeraFeature extends BiomeFeature {
    private int density = 3;

    public AloeVeraFeature() {
        super("Aloe Vera", Size.TINY);
    }

    @Override
    public boolean isEnabled() {
        return BlockList.aloeVera.isPresent() && super.isEnabled();
    }

    @Override
    protected void loadDefaultSettings() {
        minChunkDistance = 1;
        chunksPerSpawn = 20;
    }

    @Override
    protected void loadSettings(Configuration config) {
        super.loadSettings(config);
        density = config.get("Feature." + getFeatureName() + ".General", "Density", density).getInt(density);
    }

    @Override
    protected Collection<String> getDefaultBiomeList() {
        return Arrays.asList(new String[] { BiomeGenBase.desert.biomeName, BiomeGenBase.desertHills.biomeName });
    }

    @Override
    public ChunkCoordinates[] getGenerationCoordinates(World world, int chunkX, int chunkZ) {
        Random random = new Random(world.getSeed());
        long randomFactor1 = random.nextLong() / 2L * 2L + 1L;
        long randomFactor2 = random.nextLong() / 2L * 2L + 1L;
        random.setSeed(chunkX * 16 * randomFactor1 + chunkZ * 16 * randomFactor2 ^ world.getSeed());

        ChunkCoordinates[] coordinates = new ChunkCoordinates[3];
        for (int i = 0; i < density; i++) {
            int xCoord = chunkX + random.nextInt(16);
            int zCoord = chunkZ + random.nextInt(16);
            coordinates[i] = new ChunkCoordinates(xCoord, world.getTopSolidOrLiquidBlock(xCoord, zCoord), zCoord);
        }
        return coordinates;
    }

    @Override
    public boolean canGenerateHere(World world, int chunkX, int chunkZ, ChunkCoordinates genBlockCoords, Random random) {
        if (super.canGenerateHere(world, chunkX, chunkZ, genBlockCoords, random)) {
            if (world.isAirBlock(genBlockCoords.posX, genBlockCoords.posY, genBlockCoords.posZ)) {
                int iDBelow = world.getBlockId(genBlockCoords.posX, genBlockCoords.posY - 1, genBlockCoords.posZ);
                if (iDBelow == Block.grass.blockID || iDBelow == Block.dirt.blockID || iDBelow == Block.sand.blockID) {
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
    public void generateFeature(World world, int chunkX, int chunkZ, ChunkCoordinates genBlockCoords, Random random) {
        world.setBlock(genBlockCoords.posX, genBlockCoords.posY, genBlockCoords.posZ, BlockList.aloeVera.get().blockID);
    }
}
