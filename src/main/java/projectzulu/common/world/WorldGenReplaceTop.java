package projectzulu.common.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenReplaceTop extends WorldGenerator
{

    /** The ID of the plant block used in this plant generator. */
    private int genID;
    private int[] genReplace;
    BiomeGenBase[] genBiomes;
    

    public WorldGenReplaceTop(int par1, int[] par2, BiomeGenBase... biomes)
    {
        this.genID = par1;
        this.genReplace = par2;
        this.genBiomes = biomes;
        
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
    	for (int i = 0; i < 16; i++) {
    		for (int k = 0; k < 16; k++) {
    			int var7 = par3 + i;
    			int var9 = par5 + k;
    			int var8 = par1World.getTopSolidOrLiquidBlock(var7, var9);
    			BiomeGenBase biome = par1World.getBiomeGenForCoords(var7, var9);

    			boolean areWeThere = false;
    			for (int j = 0; j < genBiomes.length; j++) {
    				if(biome == genBiomes[j]){
    					areWeThere = true;
    				}
    			}
    			
    			if(areWeThere){
    				for (int j = 0; j < 2; j++) {
    					for (int j2 = 0; j2 < genReplace.length; j2++) {
    						if(par1World.getBlockId(var7, var8+j, var9) == genReplace[j2] ){
    							par1World.setBlock(var7, var8+j, var9, genID);
    						}
    					}
    				}
    			}
    		}
    	}
    	return true;
    }
}
