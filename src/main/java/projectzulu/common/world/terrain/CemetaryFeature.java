package projectzulu.common.world.terrain;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.TerrainFeatureHelper;
import projectzulu.common.core.terrain.BiomeFeature;
import projectzulu.common.world2.buildingmanager.BuildingManagerCemetary;

public class CemetaryFeature extends BiomeFeature {
    public static final String CEMETARY = "Cemetary";

    public CemetaryFeature() {
        super(CEMETARY, Size.MEDIUM);
    }

    @Override
    protected void loadDefaultSettings() {
        minChunkDistance = 3;
        chunksPerSpawn = 100;
    }

    @Override
    protected Collection<String> getDefaultBiomeList() {
        return Arrays.asList(new String[] { BiomeGenBase.plains.biomeName, BiomeGenBase.extremeHills.biomeName,
                BiomeGenBase.forest.biomeName, BiomeGenBase.jungleHills.biomeName, BiomeGenBase.taiga.biomeName,
                BiomeGenBase.swampland.biomeName, BiomeGenBase.icePlains.biomeName, BiomeGenBase.jungle.biomeName,
                BiomeGenBase.iceMountains.biomeName, BiomeGenBase.desertHills.biomeName,
                BiomeGenBase.forestHills.biomeName, BiomeGenBase.taigaHills.biomeName,
                BiomeGenBase.extremeHillsEdge.biomeName, "Birch Forest", "Forested Island", "Forested Hills",
                "Green Hills", "Mountain Taiga", "Pine Forest", "Rainforest", "Redwood Forest", "Lush Redwoods",
                "Snow Forest", "Snowy Rainforest", "Temperate Rainforest", "Woodlands" });
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
            if (world.getBlock(genBlockCoords.posX, genBlockCoords.posY, genBlockCoords.posZ).getMaterial() != Material.water
                    && world.getBlock(genBlockCoords.posX, genBlockCoords.posY - 1, genBlockCoords.posZ).getMaterial() != Material.ice
                    && world.getBlock(genBlockCoords.posX, genBlockCoords.posY, genBlockCoords.posZ).getMaterial() != Material.sand) {
                if (!TerrainFeatureHelper.doesTerrainFluctuate(world, genBlockCoords.posX, genBlockCoords.posY,
                        genBlockCoords.posZ, 2, 6)) {
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
        new BuildingManagerCemetary(world, new ChunkCoordinates(genBlockCoords.posX, genBlockCoords.posY - 1,
                genBlockCoords.posZ), direction).generate();
    }
}
