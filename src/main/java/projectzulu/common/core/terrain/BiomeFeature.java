package projectzulu.common.core.terrain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * Terrain Feature that is Restricted by Biome
 */
public abstract class BiomeFeature extends BaseFeature {

    /** List of Biome Package Names that this Feature can Spawn in */
    protected ArrayList<String> biomesToSpawn = new ArrayList<String>();

    public BiomeFeature(String featureName, Size size) {
        super(featureName, size);
    }

    @Override
    public boolean canGenerateHere(World world, int chunkX, int chunkZ, ChunkCoordinates genBlockCoords, Random random) {
        if (super.canGenerateHere(world, chunkX, chunkZ, genBlockCoords, random)) {
            return biomesToSpawn.contains(getBiomePackageName(world.getBiomeGenForCoords(genBlockCoords.posX,
                    genBlockCoords.posZ)));
        }
        return false;
    }

    protected abstract Collection<String> getDefaultBiomeList();

    @Override
    protected void loadSettings(FeatureConfiguration config) {
        super.loadSettings(config);
        Collection<String> defaultBiomesToSpawn = getDefaultBiomeList();
        for (int i = 0; i < BiomeGenBase.getBiomeGenArray().length; i++) {
            if (BiomeGenBase.getBiomeGenArray()[i] == null) {
                continue;
            }
            if (config.getFeatureProperty(this, "GeneratingBiomes",
                    getBiomePackageName(BiomeGenBase.getBiomeGenArray()[i]),
                    defaultBiomesToSpawn.contains(BiomeGenBase.getBiomeGenArray()[i].biomeName)).getBoolean(false)) {
                biomesToSpawn.add(getBiomePackageName(BiomeGenBase.getBiomeGenArray()[i]));
            }
        }
    }

    private String getBiomePackageName(BiomeGenBase biome) {
        return biome.getClass().getName() + "." + biome.biomeName;
    }
}
