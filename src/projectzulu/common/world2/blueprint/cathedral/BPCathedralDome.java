package projectzulu.common.world2.blueprint.cathedral;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.BoundaryPair;
import projectzulu.common.world2.CellHelper;
import projectzulu.common.world2.blueprint.Blueprint;

public class BPCathedralDome implements Blueprint {

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        if (cellIndexDirection == CellIndexDirection.NorthWestCorner) {
            return getDomeBlock(CellHelper.rotateCellTo(piecePos, cellSize, CellIndexDirection.NorthWall), cellSize,
                    cellHeight, random, cellIndexDirection);
        } else if (cellIndexDirection == CellIndexDirection.NorthEastCorner) {
            return getDomeBlock(CellHelper.rotateCellTo(piecePos, cellSize, CellIndexDirection.EastWall), cellSize,
                    cellHeight, random, cellIndexDirection);
        } else if (cellIndexDirection == CellIndexDirection.SouthWestCorner) {
            return getDomeBlock(CellHelper.rotateCellTo(piecePos, cellSize, CellIndexDirection.WestWall), cellSize,
                    cellHeight, random, cellIndexDirection);
        } else if (cellIndexDirection == CellIndexDirection.SouthEastCorner) {
            return getDomeBlock(CellHelper.rotateCellTo(piecePos, cellSize, CellIndexDirection.SouthWall), cellSize,
                    cellHeight, random, cellIndexDirection);
        }
        return new BlockWithMeta(0);
    }

    private BlockWithMeta getDomeBlock(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        int diagonalIndex = piecePos.posZ + piecePos.posX;
        if (piecePos.posY > cellHeight - cellSize) {
            int slope = CellHelper.getSlopeIndex(piecePos, 2 * cellSize - diagonalIndex - 1, 2,
                    BoundaryPair.createPair(cellSize - 4, cellSize * 2), cellHeight);
            if (slope == 0) {
                return new BlockWithMeta(Block.stoneBrick.blockID, 0);
            } else if (slope > 0) {
                return new BlockWithMeta(0);
            }

            if (piecePos.posX == 0) {
                return piecePos.posZ % 2 == 0 ? new BlockWithMeta(Block.stoneBrick.blockID, 0) : new BlockWithMeta(0);
            }

            if (piecePos.posZ == 0) {
                return piecePos.posX % 2 == 0 ? new BlockWithMeta(Block.stoneBrick.blockID, 0) : new BlockWithMeta(0);
            }
        }
        if (diagonalIndex == 2) {
            return new BlockWithMeta(Block.stoneBrick.blockID, 0);
        }
        return new BlockWithMeta(0);
    }

    @Override
    public String getIdentifier() {
        return "CathedralDome";
    }

    @Override
    public int getWeight() {
        return 0;
    }
}
