package projectzulu.common.core;

import net.minecraft.world.World;

public class TerrainFeatureHelper {
    
    /**
     * Method to evaluate the terrain variation. Compares the height along each point of an X to the center. 
     * If greater than provided difference return false;
     * @param world
     * @param Xcoord
     * @param Ycoord
     * @param Zcoord
     * @param maxDifference
     * @param squareLength
     * @return
     */
    public static boolean doesTerrainFluctuate(World world, int Xcoord, int Ycoord, int Zcoord, int maxDifference, int squareLength){
        if( Math.abs( world.getTopSolidOrLiquidBlock(Xcoord+squareLength, Zcoord+squareLength) - Ycoord) < maxDifference 
                && Math.abs( world.getTopSolidOrLiquidBlock(Xcoord-squareLength, Zcoord-squareLength) - Ycoord ) < maxDifference 
                && Math.abs( world.getTopSolidOrLiquidBlock(Xcoord-squareLength, Zcoord+squareLength) - Ycoord ) < maxDifference 
                && Math.abs( world.getTopSolidOrLiquidBlock(Xcoord+squareLength, Zcoord-squareLength) - Ycoord ) < maxDifference){
            return false;
        }

        return true;
    }
}
