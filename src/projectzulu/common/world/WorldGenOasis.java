package projectzulu.common.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import projectzulu.common.API.ItemBlockList;

public class WorldGenOasis extends WorldGenerator
{

    public WorldGenOasis(){}

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {

    	int palmTreeLogID = ItemBlockList.palmTreeLog.get().blockID;


    	int var7 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
    	int var9 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);
    	int var8 = par1World.getTopSolidOrLiquidBlock(var7, var9);
    	//For the Height value use average age height of all blocks. Reminder that this is integer division so extra is truncated
    	var8 = (par1World.getTopSolidOrLiquidBlock(var7, var9) +
    			par1World.getTopSolidOrLiquidBlock(var7-1, var9) + par1World.getTopSolidOrLiquidBlock(var7, var9-1) + 
    			par1World.getTopSolidOrLiquidBlock(var7-1, var9-1) + par1World.getTopSolidOrLiquidBlock(var7+1, var9-1) +
    			par1World.getTopSolidOrLiquidBlock(var7+1, var9) + par1World.getTopSolidOrLiquidBlock(var7, var9+1) +
    			par1World.getTopSolidOrLiquidBlock(var7-1, var9+1) + par1World.getTopSolidOrLiquidBlock(var7+1, var9+1))/9;

    	//int i = par1World.getBlockId(var7, var8-1, var9);
    	BiomeGenBase biome = par1World.getBiomeGenForCoords(var7, var9);
    	//Biome In Other Directions, used to Ensure oasis doesn't generate on Edge of Biome
    	BiomeGenBase biome1 = par1World.getBiomeGenForCoords(var7-10, var9);
    	BiomeGenBase biome2 = par1World.getBiomeGenForCoords(var7+10, var9);
    	BiomeGenBase biome3 = par1World.getBiomeGenForCoords(var7, var9-10);
    	BiomeGenBase biome4 = par1World.getBiomeGenForCoords(var7, var9+10);
    	//Checks if at Surface (@ Ground Block, block above is Air) as well that the land height change is not greater than two in a small area
    	//Note that it only checks discrete points, and then places airblocks above to be sure
    	//    			if(par1World.isAirBlock(var7, var8, var9) && par1World.getBlockId(var7, var8-1, var9) == Block.sand.blockID && (biome == BiomeGenBase.desert || biome == BiomeGenBase.desertHills)
    	//    					&& (biome1 == BiomeGenBase.desert || biome1 == BiomeGenBase.desertHills) && (biome2 == BiomeGenBase.desert || biome2 == BiomeGenBase.desertHills)
    	//    					&& (biome3 == BiomeGenBase.desert || biome3 == BiomeGenBase.desertHills) && (biome4 == BiomeGenBase.desert || biome4 == BiomeGenBase.desertHills)){    			

    	if(par1World.isAirBlock(var7, var8, var9) && par1World.getBlockMaterial(var7, var8-1, var9) != Material.water 
//    			&& (biome == BiomeGenBase.desert || biome == BiomeGenBase.desertHills)
//    			&& (biome1 == BiomeGenBase.desert || biome1 == BiomeGenBase.desertHills) && (biome2 == BiomeGenBase.desert || biome2 == BiomeGenBase.desertHills)
//    			&& (biome3 == BiomeGenBase.desert || biome3 == BiomeGenBase.desertHills) && (biome4 == BiomeGenBase.desert || biome4 == BiomeGenBase.desertHills)
    			){    			
    		//Generate Structure
    		int width = par2Random.nextInt(4)+2;
    		int width2 = par2Random.nextInt(4)+2;

    		//Fill Area With Sand
    		//DistOassis represents the distance from the edge of the pool extra sand should be placed
    		int distOasis;

    		//    				//For Bottom
    		//    				distOasis = 5+par2Random.nextInt(2);
    		//    				for (int i = -width-distOasis;  i < width+distOasis; i++) {
    		//    					int distOasis2 = 12+par2Random.nextInt(2);
    		//    					for (int k = -width2-distOasis2; k < width2+distOasis2; k++) {
    		//    						if( par1World.isAirBlock(var7+i, var8+0, var9+k) ){
    		//    							par1World.setBlock(var7+i, var8-2, var9+k, Block.sand.blockID);
    		//    						}
    		//    					}
    		//    				}
    		//    				//For Mid.
    		//    				distOasis = 4+par2Random.nextInt(2);
    		//    				for (int k = -width2-distOasis; k < width2+distOasis; k++) {
    		//    					int distOasis2 = 11+par2Random.nextInt(2);
    		//    					for (int i = -width-distOasis2;  i < width+distOasis2; i++) {
    		//    						if( par1World.isAirBlock(var7+i, var8+0, var9+k) ){
    		//    							par1World.setBlock(var7+i, var8-1, var9+k, Block.sand.blockID);
    		//    						}
    		//    					}
    		//    				}
    		//    				//For Top
    		//    				distOasis = 3+par2Random.nextInt(2);
    		//    				for (int i = -width-distOasis;  i < width+distOasis; i++) {
    		//    					int distOasis2 = 10+par2Random.nextInt(2);
    		//    					for (int k = -width2-distOasis2; k < width2+distOasis2; k++) {
    		//    						if( par1World.isAirBlock(var7+i, var8+0, var9+k) ){
    		//    							par1World.setBlock(var7+i, var8+0, var9+k, Block.sand.blockID);
    		//    						}
    		//    					}
    		//    				}



    		//Create Water Pool
    		for (int i = -width; i <= width; i++) {
    			for (int k = -width2; k <= width2; k++) {
    				int depth = par2Random.nextInt(1)+1;
    				for (int j = 0; j >= -depth; j--) {
    					par1World.setBlockWithNotify(var7+i, var8+j, var9+k, Block.waterStill.blockID);
    				}
    			}
    		}

    		//Generate Grass Around Edges (And Place Air above so that grass is not covered with sand)
    		//'Top'
    		int rngGrassAway = 3;
    		int minGrassAway = (int)( (width+0.2*width2)/2 )+1;
    		for (int i = -width-1; i <= width+1; i++) {
    			int numGrassAway = par2Random.nextInt(rngGrassAway)+minGrassAway;
    			for (int k = width2; k <= width2+numGrassAway; k++) {
    				par1World.setBlockWithNotify(var7+i, var8, var9+k, Block.grass.blockID);
    				for (int j = 1; j < 3; j++) {
    					par1World.setBlockWithNotify(var7+i, var8+j, var9+k, 0);
    				}
    			}
    			for (int k = width2+numGrassAway+1; k <= width2+numGrassAway+3; k++) {
    				//par1World.setBlock(var7+i, var8, var9+k, Block.grass.blockID);
    				for (int j = 0; j > -5; j--) {
    					par1World.setBlockWithNotify(var7+i, var8+j, var9+k, Block.sand.blockID);
    				}
    			}
    		}

    		//'Bottom'
    		for (int i = -width-1; i <= width+1; i++) {
    			int numGrassAway = par2Random.nextInt(rngGrassAway)+minGrassAway;
    			for (int k = -width2; k >= -(width2+numGrassAway); k--) {
    				par1World.setBlockWithNotify(var7+i, var8, var9+k, Block.grass.blockID);
    				for (int j = 1; j < 3; j++) {
    					par1World.setBlockWithNotify(var7+i, var8+j, var9+k, 0);
    				}
    			}
    			for (int k = -(width2+numGrassAway+1); k >= -(width2+numGrassAway+3); k--) {
    				for (int j = 0; j > -5; j--) {
    					par1World.setBlockWithNotify(var7+i, var8+j, var9+k, Block.sand.blockID);
    				}
    			}
    		}

    		//'Right'
    		minGrassAway =  (int)( (width2+0.2*width)/2 )+1;
    		for (int k = -width2-1; k <= width2+1; k++) {
    			int numGrassAway = par2Random.nextInt(rngGrassAway)+minGrassAway;
    			for (int i = width; i <= width+numGrassAway; i++) {
    				par1World.setBlockWithNotify(var7+i, var8, var9+k, Block.grass.blockID);
    				for (int j = 1; j < 3; j++) {
    					par1World.setBlockWithNotify(var7+i, var8+j, var9+k, 0);
    				}
    			}
    			for (int i = (width+numGrassAway+1); i <= (width+numGrassAway+3); i++) {
    				for (int j = 0; j > -5; j--) {
    					par1World.setBlockWithNotify(var7+i, var8+j, var9+k, Block.sand.blockID);
    				}
    			}
    		}

    		//'Left'
    		for (int k = -width2-1; k <= width2+1; k++) {
    			int numGrassAway = par2Random.nextInt(rngGrassAway)+minGrassAway;
    			for (int i = -width-1; i >= -width-numGrassAway; i--) {
    				par1World.setBlockWithNotify(var7+i, var8, var9+k, Block.grass.blockID);
    				for (int j = 1; j < 3; j++) {
    					par1World.setBlockWithNotify(var7+i, var8+j, var9+k, 0);
    				}
    			}
    			for (int i = -(width+numGrassAway+1); i >= -(width+numGrassAway+3); i--) {
    				//par1World.setBlock(var7+i, var8, var9+k, Block.grass.blockID);
    				for (int j = 0; j > -5; j--) {
    					par1World.setBlockWithNotify(var7+i, var8+j, var9+k, Block.sand.blockID);
    				}
    			}
    		}
    		//Fill Corners With Sand
    		for (int j = 0; j > -3; j--) {
    			int blockIDToPlace = Block.sand.blockID;
    			par1World.setBlockWithNotify(var7+(width+2), var8+j, var9+(width2+2), blockIDToPlace);
    			par1World.setBlockWithNotify(var7+(width+2+0), var8+j, var9+(width2+2+1), blockIDToPlace);
    			par1World.setBlockWithNotify(var7+(width+2+0), var8+j, var9+(width2+2+2), blockIDToPlace);
    			par1World.setBlockWithNotify(var7+(width+2+1), var8+j, var9+(width2+2+0), blockIDToPlace);
    			par1World.setBlockWithNotify(var7+(width+2+2), var8+j, var9+(width2+2+0), blockIDToPlace);
    			par1World.setBlockWithNotify(var7+(width+2+1), var8+j, var9+(width2+2+1), blockIDToPlace);

    			par1World.setBlockWithNotify(var7-(width+2), var8+j, var9+(width2+2), blockIDToPlace);
    			par1World.setBlockWithNotify(var7-(width+2+0), var8+j, var9+(width2+2+1), blockIDToPlace);
    			par1World.setBlockWithNotify(var7-(width+2+0), var8+j, var9+(width2+2+2), blockIDToPlace);
    			par1World.setBlockWithNotify(var7-(width+2+1), var8+j, var9+(width2+2+0), blockIDToPlace);
    			par1World.setBlockWithNotify(var7-(width+2+2), var8+j, var9+(width2+2+0), blockIDToPlace);
    			par1World.setBlockWithNotify(var7-(width+2+1), var8+j, var9+(width2+2+1), blockIDToPlace);

    			par1World.setBlockWithNotify(var7+(width+2), var8+j, var9-(width2+2), blockIDToPlace);
    			par1World.setBlockWithNotify(var7+(width+2+0), var8+j, var9-(width2+2+1), blockIDToPlace);
    			par1World.setBlockWithNotify(var7+(width+2+0), var8+j, var9-(width2+2+2), blockIDToPlace);
    			par1World.setBlockWithNotify(var7+(width+2+1), var8+j, var9-(width2+2+0), blockIDToPlace);
    			par1World.setBlockWithNotify(var7+(width+2+2), var8+j, var9-(width2+2+0), blockIDToPlace);
    			par1World.setBlockWithNotify(var7+(width+2+1), var8+j, var9-(width2+2+1), blockIDToPlace);

    			par1World.setBlockWithNotify(var7-(width+2), var8+j, var9-(width2+2), blockIDToPlace);
    			par1World.setBlockWithNotify(var7-(width+2+0), var8+j, var9-(width2+2+1), blockIDToPlace);
    			par1World.setBlockWithNotify(var7-(width+2+0), var8+j, var9-(width2+2+2), blockIDToPlace);
    			par1World.setBlockWithNotify(var7-(width+2+1), var8+j, var9-(width2+2+0), blockIDToPlace);
    			par1World.setBlockWithNotify(var7-(width+2+2), var8+j, var9-(width2+2+0), blockIDToPlace);
    			par1World.setBlockWithNotify(var7-(width+2+1), var8+j, var9-(width2+2+1), blockIDToPlace);
    		}

    		//Generate Trees
    		int numTrees = par2Random.nextInt((width+width2)/2-1)+1;
    		while(numTrees > 0){
    			int xCoord = par2Random.nextInt(2*(width+2))-(width+2);
    			int zCoord = par2Random.nextInt(2*(width2+2))-(width2+2);

    			if(par1World.getBlockId(var7+xCoord, var8, var9+zCoord) == Block.grass.blockID 
    					&& par1World.getBlockId(var7+xCoord-1, var8+1, var9+zCoord) != palmTreeLogID
    					&& par1World.getBlockId(var7+xCoord+1, var8+1, var9+zCoord) != palmTreeLogID
    					&& par1World.getBlockId(var7+xCoord, var8+1, var9+zCoord-1) != palmTreeLogID
    					&& par1World.getBlockId(var7+xCoord, var8+1, var9+zCoord+1) != palmTreeLogID){
    				growTree(par1World, var7+xCoord, var8+1, var9+zCoord, par2Random);
    				numTrees--;
    			}
    		}
    	}
    	return true;
    }
    
    public void growTree(World par1World, int par2, int par3, int par4, Random par5Random){
    	int palmTreeLogID = ItemBlockList.palmTreeLog.get().blockID;
    	
    	if(!par1World.isRemote){
    		int height = par5Random.nextInt(3)+4;
    		//Check if there is water nearby 9x9

    		//As loop finds water it incremement direction towards it, 
    		//The favored direction at the end of the loop will be towards the side with more water
    		int favoredDirectionX = 0;
    		int favoredDirectionZ = 0;

    		for (int i = -4; i <= 4; i++) {
    			for (int k = -4; k <= 4; k++) {
    				for (int j = -4; j <= 4; j++) {
    					int ID = par1World.getBlockId(par2+i, par3+j, par4+k);
    					if(ID == Block.waterMoving.blockID || ID == Block.waterStill.blockID){
    						//Add +/- 1 to favored Direcion indicating the direction water is in
    						if (i != 0) {
    							favoredDirectionX += i/Math.abs(i);
    						}
    						if(k != 0){
    							favoredDirectionZ += k/Math.abs(k);
    						}					
    					}
    				}
    			}
    		}

    		//TBD: If favoredDirectionX and Z are almost the same, greater than 2 and Rare chance, spawn multiple trees.

    		//Set FavoredDirection that is less to zero, as we don't want to grow a tree in that direction
    		if(Math.abs(favoredDirectionX) - Math.abs(favoredDirectionZ) >= 0){
    			favoredDirectionZ = 0;
    		}else{
    			favoredDirectionX = 0;
    		}
    		
    		//Temp variables used in placing log blocks, work wrt global coordinats of block
    		int localX = 0;
    		int localY = 0;
    		int localZ = 0;
    		
    		//Adjusts the 'cost' of placing a block horizontally, higher means less horizontal variance
    		//Does not affect vertical, which is set by height
    		int horizontalFactor = par5Random.nextInt(10)+20;

    		while(localY <= height){
    			//Place Log above by 1
    			localY++;
    			par1World.setBlockWithNotify(par2+localX, par3+localY, par4+localZ, palmTreeLogID);

    			if(favoredDirectionX > 0){
    				localX++;    		
    				favoredDirectionX = Math.max(favoredDirectionX-horizontalFactor,0);
    				par1World.setBlockWithNotify(par2+localX, par3+localY, par4+localZ, palmTreeLogID);

    			}else if(favoredDirectionX < 0){
    				localX--;    		
    				favoredDirectionX = Math.min(favoredDirectionX+horizontalFactor,0);
    				par1World.setBlockWithNotify(par2+localX, par3+localY, par4+localZ, palmTreeLogID);

    			}

    			if(favoredDirectionZ > 0){
    				localZ++;
    				favoredDirectionZ = Math.max(favoredDirectionZ-horizontalFactor,0);
    				par1World.setBlockWithNotify(par2+localX, par3+localY, par4+localZ, palmTreeLogID);

    			}else if(favoredDirectionZ < 0){
    				localZ--;
    				favoredDirectionZ = Math.min(favoredDirectionZ+horizontalFactor,0);
    				par1World.setBlockWithNotify(par2+localX, par3+localY, par4+localZ, palmTreeLogID);    			
    			}

    			if(localY+1 == height){
    				localY++;
    				par1World.setBlockWithNotify(par2+localX, par3+localY, par4+localZ,palmTreeLogID);
    				localY++;
    				spawnLeaves(par1World, par2+localX, par3+localY, par4+localZ, par5Random, height);
    				//Place block at original sapling locations
    				par1World.setBlockWithNotify(par2, par3, par4, palmTreeLogID);
    			}
    		}
    	}
    }
    
    public void spawnLeaves(World par1World, int par2, int par3, int par4, Random par5Random, int height){
    	int palmTreeLeavesID = ItemBlockList.palmTreeLeaves.get().blockID;
    	
    	//TODO: Add more Leave Spawn Templates
    	if (height + 1 >= 7 ) {
    		par1World.setBlock(par2, par3, par4, palmTreeLeavesID);
	    	par1World.setBlock(par2-1, par3, par4, palmTreeLeavesID);
	    	par1World.setBlock(par2-2, par3, par4, palmTreeLeavesID);
	    	par1World.setBlock(par2-3, par3, par4, palmTreeLeavesID);
	    	par1World.setBlock(par2-4, par3-1, par4, palmTreeLeavesID);
	    	
	    	par1World.setBlock(par2+1, par3, par4, palmTreeLeavesID);
	    	par1World.setBlock(par2+2, par3, par4, palmTreeLeavesID);
	    	par1World.setBlock(par2+3, par3, par4, palmTreeLeavesID);
	    	par1World.setBlock(par2+3, par3-1, par4, palmTreeLeavesID);
	    	
	    	par1World.setBlock(par2, par3, par4-1, palmTreeLeavesID);
	    	par1World.setBlock(par2, par3, par4-2, palmTreeLeavesID);
	    	par1World.setBlock(par2, par3, par4-3, palmTreeLeavesID);
	    	par1World.setBlock(par2, par3-1, par4-3, palmTreeLeavesID);
	    	
	    	par1World.setBlock(par2, par3, par4+1, palmTreeLeavesID);
	    	par1World.setBlock(par2, par3, par4+2, palmTreeLeavesID);
	    	par1World.setBlock(par2, par3, par4+3, palmTreeLeavesID);
	    	par1World.setBlock(par2, par3-1, par4+3, palmTreeLeavesID);
			
		}else{
			par1World.setBlock(par2, par3, par4, palmTreeLeavesID);
	    	par1World.setBlock(par2-1, par3, par4, palmTreeLeavesID);
	    	par1World.setBlock(par2-2, par3, par4, palmTreeLeavesID);
	    	par1World.setBlock(par2-2, par3-1, par4, palmTreeLeavesID);
	    	
	    	par1World.setBlock(par2+1, par3, par4, palmTreeLeavesID);
	    	par1World.setBlock(par2+2, par3, par4, palmTreeLeavesID);
	    	par1World.setBlock(par2+2, par3-1, par4, palmTreeLeavesID);
	    	
	    	par1World.setBlock(par2, par3, par4-1, palmTreeLeavesID);
	    	par1World.setBlock(par2, par3, par4-2, palmTreeLeavesID);
	    	par1World.setBlock(par2, par3-1, par4-2, palmTreeLeavesID);
	    	
	    	par1World.setBlock(par2, par3, par4+1, palmTreeLeavesID);
	    	par1World.setBlock(par2, par3, par4+2, palmTreeLeavesID);
	    	par1World.setBlock(par2, par3-1, par4+2, palmTreeLeavesID);
		}
    	
    }


}
