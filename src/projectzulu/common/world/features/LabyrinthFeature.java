package projectzulu.common.world.features;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.TerrainFeatureHelper;
import projectzulu.common.core.features.BiomeFeature;
import projectzulu.common.world.MazeGenerator;
import projectzulu.common.world.buildingmanager.BuildingManagerLabyrinth;

public class LabyrinthFeature extends BiomeFeature {
    public static final String LABYRINTH = "Labyrinth";

    public LabyrinthFeature() {
        super(LABYRINTH, Size.LARGE);
    }

    @Override
    protected void loadDefaultSettings() {
        minChunkDistance = 9;
        chunksPerSpawn = 100;
    }

    @Override
    protected Collection<String> getDefaultBiomeList() {
        return Arrays.asList(new String[] { BiomeGenBase.plains.biomeName, BiomeGenBase.desert.biomeName,
                BiomeGenBase.extremeHills.biomeName, BiomeGenBase.forest.biomeName, BiomeGenBase.taiga.biomeName,
                BiomeGenBase.swampland.biomeName, BiomeGenBase.river.biomeName, BiomeGenBase.icePlains.biomeName,
                BiomeGenBase.iceMountains.biomeName, BiomeGenBase.desertHills.biomeName,
                BiomeGenBase.forestHills.biomeName, BiomeGenBase.taigaHills.biomeName,
                BiomeGenBase.extremeHillsEdge.biomeName, BiomeGenBase.jungle.biomeName, BiomeGenBase.desert.biomeName,
                BiomeGenBase.jungleHills.biomeName, "Birch Forest", "Forested Island", "Forested Hills", "Green Hills",
                "Mountain Taiga", "Pine Forest", "Rainforest", "Redwood Forest", "Lush Redwoods", "Snow Forest",
                "Snowy Rainforest", "Temperate Rainforest", "Woodlands", "Mountainous Desert" });
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
            if (world.getBlockMaterial(genBlockCoords.posX, genBlockCoords.posY, genBlockCoords.posZ) != Material.water) {
                if (!TerrainFeatureHelper.doesTerrainFluctuate(world, genBlockCoords.posX, genBlockCoords.posY,
                        genBlockCoords.posZ, 5, 12)) {
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
        new MazeGenerator(new BuildingManagerLabyrinth(world), 1, 4, 3, 24, 1, 1).generate(world, random,
                genBlockCoords.posX, genBlockCoords.posY - (11), genBlockCoords.posZ);
    }
}
