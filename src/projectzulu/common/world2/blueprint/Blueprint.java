package projectzulu.common.world2.blueprint;

import java.util.Random;

import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;

public interface Blueprint {

    /**
     * 
     * @param pieceLoc X,Y,Z Coordinate of Piece within Cell
     * @param cellSize
     * @param maxHeight
     * @param xIndex
     * @param zIndex
     * @param random
     * @param cellIndexDirection
     * @return
     */
    public abstract BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight,
            Random random, CellIndexDirection cellIndexDirection);

    /**
     * Used to Search Architect list for a specific Building Type
     * 
     * Type insensitive. Should be unique.
     */
    public abstract String getIdentifier();

    public abstract int getWeight();
}
