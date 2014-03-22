package projectzulu.common.world.terrain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.TerrainFeatureHelper;
import projectzulu.common.core.terrain.BiomeFeature;
import projectzulu.common.core.terrain.FeatureConfiguration;
import projectzulu.common.world2.buildingmanager.BuildingManagerLabyrinth;
import cpw.mods.fml.common.Loader;

public class LabyrinthFeature extends BiomeFeature {
    public static final String LABYRINTH = "Labyrinth";

    private List<EntityEntry> entityEntries = new ArrayList<EntityEntry>();
    public int chestLootChance;
    public int chestMaxLoot;

    public String getEntityEntry(Random rand) {
        if (entityEntries.isEmpty()) {
            return "EMPTY";
        }
        return ((EntityEntry) WeightedRandom.getRandomItem(rand, entityEntries)).entityname;
    }

    public LabyrinthFeature() {
        super(LABYRINTH, Size.LARGE);
    }

    @Override
    protected void loadDefaultSettings() {
        minChunkDistance = 9;
        chunksPerSpawn = 100;
        chestLootChance = 20;
        chestMaxLoot = -1;
        entityEntries.add(new EntityEntry("EMPTY", 4));
        if (Loader.isModLoaded(DefaultProps.MobsModId)) {
            if (CustomEntityList.HAUNTEDARMOR.modData.isPresent()) {
                entityEntries.add(new EntityEntry(DefaultProps.CoreModId.concat(".").concat(
                        CustomEntityList.HAUNTEDARMOR.modData.get().mobName), 3));
            }
            if (CustomEntityList.MINOTAUR.modData.isPresent()) {
                entityEntries.add(new EntityEntry(DefaultProps.CoreModId.concat(".").concat(
                        CustomEntityList.MINOTAUR.modData.get().mobName), 1));
            }
        } else {
            entityEntries.add(new EntityEntry("Zombie", 4));
        }
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
    protected void loadSettings(FeatureConfiguration config) {
        super.loadSettings(config);
        chestMaxLoot = config.getFeatureProperty(this, "general", "Chest Max Loot", chestMaxLoot).getInt(chestMaxLoot);
        chestLootChance = config.getFeatureProperty(this, "general", "Chest Loot Chance", chestLootChance).getInt(
                chestLootChance);

        String entries = "";
        Iterator<EntityEntry> iterator = entityEntries.iterator();
        while (iterator.hasNext()) {
            EntityEntry entityEntry = iterator.next();
            entries = entries.concat(entityEntry.entityname).concat("-")
                    .concat(Integer.toString(entityEntry.itemWeight));
            if (iterator.hasNext()) {
                entries = entries.concat(",");
            }
        }

        entries = config.getFeatureProperty(this, "general", "SpawnerEntities", entries,
                "Entities that appear in Spawner. Format: Entityname-Weight,").getString();
        entityEntries.clear();

        for (String entry : entries.split(",")) {
            if (entry.trim().equals("")) {
                continue;
            }

            String[] pair = entry.trim().split("-");
            if (pair.length != 2) {
                ProjectZuluLog.severe("Skipping Entity parsing for TF %s. %s has Invalid Number of Arguments.",
                        getFeatureName(), entries);
            }
            try {
                EntityEntry entityEntry = new EntityEntry(pair[0].trim(), Integer.parseInt(pair[1]));
                entityEntries.add(entityEntry);
            } catch (IllegalFormatException e) {
                ProjectZuluLog.severe("Skipping Entity parsing for TF %s. %s has invalid format.", getFeatureName(),
                        entries);
                continue;
            } catch (IllegalArgumentException e) {
                ProjectZuluLog.severe("Skipping Entity parsing for TF %s. %s has invalid format.", getFeatureName(),
                        entries);
                continue;
            }
        }
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
            if (world.getBlock(genBlockCoords.posX, genBlockCoords.posY, genBlockCoords.posZ).getMaterial() != Material.water) {
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
    public void generateFeature(World world, int chunkX, int chunkZ, ChunkCoordinates genBlockCoords, Random random,
            FeatureDirection direction) {
        new BuildingManagerLabyrinth(world, new ChunkCoordinates(genBlockCoords.posX, genBlockCoords.posY - 16,
                genBlockCoords.posZ), direction).generate();
    }
}
