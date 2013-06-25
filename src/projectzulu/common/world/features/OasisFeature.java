package projectzulu.common.world.features;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.api.BlockList;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.TerrainFeatureHelper;
import projectzulu.common.core.features.BiomeFeature;
import projectzulu.common.world.WorldGenOasis;

public class OasisFeature extends BiomeFeature {
    public static final String OASIS = "Oasis";

    public OasisFeature() {
        super(OASIS, Size.SMALL);
    }

    @Override
    protected void loadDefaultSettings() {
        minChunkDistance = 3;
        chunksPerSpawn = 400;
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

        int xCoord = chunkX + random.nextInt(16);
        int zCoord = chunkZ + random.nextInt(16);
        return new ChunkCoordinates[] { new ChunkCoordinates(xCoord, world.getTopSolidOrLiquidBlock(xCoord, zCoord),
                zCoord) };
    }
    
    @Override
    public boolean canGenerateHere(World world, int chunkX, int chunkZ, ChunkCoordinates genBlockCoords, Random random) {
        if (super.canGenerateHere(world, chunkX, chunkZ, genBlockCoords, random)) {
            if (!BlockList.palmTreeSapling.isPresent() || !BlockList.palmTreeLog.isPresent()
                    || !BlockList.palmTreeLeaves.isPresent()) {
                return false;
            }

            if (world.getBlockMaterial(genBlockCoords.posX, genBlockCoords.posY, genBlockCoords.posZ) != Material.water) {
                if (!TerrainFeatureHelper.doesTerrainFluctuate(world, genBlockCoords.posX, genBlockCoords.posY,
                        genBlockCoords.posZ, 3, 6)) {
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
        (new WorldGenOasis())
                .generate(world, random, genBlockCoords.posX, genBlockCoords.posY - 1, genBlockCoords.posZ);
    }
}
