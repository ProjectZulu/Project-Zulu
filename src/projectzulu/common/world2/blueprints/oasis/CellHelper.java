package projectzulu.common.world2.blueprints.oasis;

import java.awt.Point;

import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world2.MazeCell;

public class CellHelper {

    /**
     * Returns the Cardinal Rotation of Cell if Rotated from North along CellIndexDirection NorthWall(North, Unaltered),
     * WestWall(West),EastWall(East),SouthWall(South)
     * 
     * @param piecePos Current Piece Position
     * @param cellSize Size of Cell
     * @param cellIndexDirection Direction Cell should be facing
     * @return
     */
    public static ChunkCoordinates rotateCellTo(ChunkCoordinates piecePos, int cellSize,
            CellIndexDirection cellIndexDirection) {
        if (cellIndexDirection == CellIndexDirection.NorthWall) {
            return new ChunkCoordinates(piecePos.posX, piecePos.posY, piecePos.posZ);
        } else if (cellIndexDirection == CellIndexDirection.SouthWall) {
            return new ChunkCoordinates(cellSize - 1 - piecePos.posX, piecePos.posY, cellSize - 1 - piecePos.posZ);
        } else if (cellIndexDirection == CellIndexDirection.WestWall) {
            return new ChunkCoordinates(cellSize - 1 - piecePos.posZ, piecePos.posY, piecePos.posX);
        } else if (cellIndexDirection == CellIndexDirection.EastWall) {
            return new ChunkCoordinates(piecePos.posZ, piecePos.posY, cellSize - 1 - piecePos.posX);
        }
        return piecePos;
    }

    public static boolean isCorner(MazeCell[][] cells, Point buildCoords) {
        if (buildCoords.x == 0 && (buildCoords.y == 0 || buildCoords.y == cells[0].length - 1)) {
            return true;
        } else if (buildCoords.x == cells.length - 1 && (buildCoords.y == 0 || buildCoords.y == cells[0].length - 1)) {
            return true;
        }
        return false;
    }
}
