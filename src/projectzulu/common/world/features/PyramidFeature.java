package projectzulu.common.world.features;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.TerrainFeatureHelper;
import projectzulu.common.core.features.BiomeFeature;
import projectzulu.common.world.WorldGenPyramid;
import cpw.mods.fml.common.Loader;

public class PyramidFeature extends BiomeFeature {
    public static final String PYRAMID = "Pyramid";
    
    private List<EntityEntry> entityEntries = new ArrayList<EntityEntry>();
    public int chestLootChance;
    public int chestMaxLoot;
    
    public String getEntityEntry(Random rand) {
        if (entityEntries.isEmpty()) {
            return "EMPTY";
        }
        return ((EntityEntry) WeightedRandom.getRandomItem(rand, entityEntries)).entityname;
    }
    
    public PyramidFeature() {
        super(PYRAMID, Size.LARGE);
    }

    @Override
    protected void loadDefaultSettings() {
        minChunkDistance = 9;
        chunksPerSpawn = 100;
        chestLootChance = 15;
        chestMaxLoot = -1;
        if (Loader.isModLoaded(DefaultProps.MobsModId)) {
            entityEntries.add(new EntityEntry(DefaultProps.CoreModId.concat(".Entity Mummy"), 4));
        } else {
            entityEntries.add(new EntityEntry("Zombie", 4));
        }
    }

    @Override
    protected Collection<String> getDefaultBiomeList() {
        return Arrays.asList(new String[] { BiomeGenBase.desert.biomeName, "Mountainous Desert" });
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
        (new WorldGenPyramid(1, 1)).generate(world, random, genBlockCoords.posX, genBlockCoords.posY,
                genBlockCoords.posZ);
    }
}
