package projectzulu.common.world2.blueprint.cathedral;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.BoundaryPair;
import projectzulu.common.world2.CellHelper;
import projectzulu.common.world2.blueprint.Blueprint;

public class BPCathedralEntrance implements Blueprint {

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        return getWallBlock(CellHelper.mirrorCellTo(piecePos, cellSize, cellIndexDirection), cellSize, cellHeight,
                random, cellIndexDirection);
    }

    public BlockWithMeta getWallBlock(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        /* Ceiling Building "Roof Floor" */
        if (piecePos.posY > cellHeight - cellSize) {
            int slope = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posX - 3, 1,
                    BoundaryPair.createPair(0, cellSize * 2 - 8), cellHeight - cellSize / 3);
            int slopeBelow = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posX - 2, 1,
                    BoundaryPair.createPair(0, cellSize * 2 - 8), cellHeight - cellSize / 3);
            if (slope == 0) {
                if (slope != slopeBelow) {
                    return new BlockWithMeta(Block.stairsStoneBrick.blockID, getStairMeta(cellIndexDirection));
                } else {
                    return new BlockWithMeta(Block.stoneBrick.blockID, 5, getStairMeta(cellIndexDirection));
                }
            } else if (slope > 0) {
                return new BlockWithMeta(0);
            }
        }

        /* Outer Walls and Floor */
        if (piecePos.posX == cellSize * 4 / 10) {
            return new BlockWithMeta(Block.stoneBrick.blockID, 0);
        } else if (piecePos.posY == 0 && piecePos.posX > cellSize * 4 / 10) {
            return new BlockWithMeta(Block.stoneBrick.blockID, 0);
        }

        if (cellIndexDirection == CellIndexDirection.SouthWestCorner
                || cellIndexDirection == CellIndexDirection.SouthEastCorner) {
            if (piecePos.posX > 2 && piecePos.posZ == 1) {
                return new BlockWithMeta(Block.stoneBrick.blockID, 0);
            }
        }

        /* Mid Building "Roof Floor" */
        if (piecePos.posY > cellHeight - 2 * cellSize) {
            int slope = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posX - 3, 1,
                    BoundaryPair.createPair(1, cellSize * 2 - 8), cellHeight - cellSize);
            int slopeBelow = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posX - 2, 1,
                    BoundaryPair.createPair(1, cellSize * 2 - 8), cellHeight - cellSize);
            if (slope == 0) {
                if (slope != slopeBelow) {
                    return new BlockWithMeta(Block.stairsStoneBrick.blockID, getStairMeta(cellIndexDirection));
                } else {
                    return new BlockWithMeta(Block.stoneBrick.blockID, 5, getStairMeta(cellIndexDirection));
                }
            }
        }

        return new BlockWithMeta(0);
    }

    private int getStairMeta(CellIndexDirection cellIndexDirection) {
        switch (cellIndexDirection) {
        case NorthWestCorner:
        case SouthWestCorner:
            return 0;
        case NorthEastCorner:
        case SouthEastCorner:
            return 1;
        default:
            return 0;
        }
    }

    @Override
    public String getIdentifier() {
        return "CathedralEntrance";
    }

    @Override
    public int getWeight() {
        return 0;
    }
}
