package projectzulu.common.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import projectzulu.common.api.BlockList;

public class WorldGenOnSurface extends WorldGenerator
{
	private int numberPerChunk = 0;
	private int chancePerChunk = 0;
    /** The ID of the plant block used in this plant generator. */
    private int plantBlockId;

    public WorldGenOnSurface(int par1, int par2, int par3)
    {
        this.plantBlockId = par1;
        this.numberPerChunk = par2;
        this.chancePerChunk = par3;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
    	if (chancePerChunk - par2Random.nextInt(100) >= 0) {

    		for (int var6 = 0; var6 < numberPerChunk; ++var6)
    		{
    			int var7 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
    			//int var8 = par2Random.nextInt(20)+55;
    			int var9 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);
    			int var8 = par1World.getTopSolidOrLiquidBlock(var7, var9);
    			int i = par1World.getBlockId(var7, var8-1, var9);
    			BiomeGenBase biome = par1World.getBiomeGenForCoords(var7, var9);
    			if(par1World.isAirBlock(var7, var8, var9)){
    				
    				if(BlockList.aloeVera.isPresent() && plantBlockId == BlockList.aloeVera.get().blockID && (i == Block.grass.blockID || i == Block.dirt.blockID || i == Block.sand.blockID)
    						&& (biome == BiomeGenBase.desert || biome == BiomeGenBase.desertHills) ){
    					par1World.setBlock(var7, var8, var9, this.plantBlockId);
    				} 
    				
    				if(BlockList.palmTreeSapling.isPresent() && plantBlockId == BlockList.palmTreeSapling.get().blockID && (i == Block.sand.blockID || i == Block.dirt.blockID || i == Block.grass.blockID)
    						&& (biome==BiomeGenBase.beach || biome==BiomeGenBase.river) ){
    					par1World.setBlock(var7, var8, var9, this.plantBlockId);
    				} 
    				if(BlockList.nightBloom.isPresent() && plantBlockId == BlockList.nightBloom.get().blockID && (i == Block.tilledField.blockID || i == Block.dirt.blockID 
    						|| i == Block.grass.blockID || (BlockList.aloeVera.isPresent() && i == BlockList.aloeVera.get().blockID))
    						&& (biome==BiomeGenBase.plains || biome==BiomeGenBase.river) ){
    					par1World.setBlock(var7, var8, var9, this.plantBlockId);
    				} 
    				if(BlockList.creeperBlossom.isPresent() && plantBlockId == BlockList.creeperBlossom.get().blockID && (i == Block.dirt.blockID || i == Block.grass.blockID)
    						&& (biome==BiomeGenBase.forest || biome==BiomeGenBase.forestHills) ){
    					par1World.setBlock(var7, var8, var9, this.plantBlockId);
    				}
    			}
    		}
    	}

        return true;
    }
}
