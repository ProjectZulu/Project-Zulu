package projectzulu.common.world.terrain;

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
import projectzulu.common.core.terrain.BiomeFeature;
import projectzulu.common.world2.buildingmanager.BuildingManagerOasis;

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

        int xCoord = chunkX * 16 + random.nextInt(16);
        int zCoord = chunkZ * 16 + random.nextInt(16);
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

            if (world.getBlock(genBlockCoords.posX, genBlockCoords.posY, genBlockCoords.posZ).getMaterial() != Material.water) {
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
    public void generateFeature(World world, int chunkX, int chunkZ, ChunkCoordinates genBlockCoords, Random random,
            FeatureDirection direction) {
        new BuildingManagerOasis(world, direction, new ChunkCoordinates(genBlockCoords.posX, genBlockCoords.posY - 3,
                genBlockCoords.posZ), 6, 8, 3).generate();
    }
}
