package projectzulu.common.world2.blueprint.cathedral;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.BoundaryPair;
import projectzulu.common.world2.CellHelper;
import projectzulu.common.world2.blueprint.Blueprint;

public class BPCathedralHallwayEnd implements Blueprint {

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        piecePos = applyMirror(piecePos, cellSize, cellIndexDirection);
        piecePos = applyRotation(piecePos, cellSize, cellIndexDirection);
        return getWallBlock(CellHelper.rotateCellTo(piecePos, cellSize, CellIndexDirection.NorthWall), cellSize, cellHeight,
                random, cellIndexDirection);
    }

    private ChunkCoordinates applyMirror(ChunkCoordinates piecePos, int cellSize, CellIndexDirection cellIndexDirection) {
        if (cellIndexDirection == CellIndexDirection.NorthWestCorner
                || cellIndexDirection == CellIndexDirection.NorthEastCorner
                || cellIndexDirection == CellIndexDirection.SouthEastCorner
                || cellIndexDirection == CellIndexDirection.SouthWestCorner) {
            piecePos = CellHelper.mirrorCellTo(piecePos, cellSize, CellIndexDirection.SouthWestCorner);
        }
        return piecePos;
    }

    private ChunkCoordinates applyRotation(ChunkCoordinates piecePos, int cellSize,
            CellIndexDirection cellIndexDirection) {
        if (cellIndexDirection == CellIndexDirection.NorthWestCorner) {
            piecePos = CellHelper.rotateCellTo(piecePos, cellSize, CellIndexDirection.NorthWall);
        } else if (cellIndexDirection == CellIndexDirection.NorthEastCorner) {
            piecePos = CellHelper.rotateCellTo(piecePos, cellSize, CellIndexDirection.WestWall);
        } else if (cellIndexDirection == CellIndexDirection.SouthEastCorner) {
            piecePos = CellHelper.rotateCellTo(piecePos, cellSize, CellIndexDirection.SouthWall);
        } else if (cellIndexDirection == CellIndexDirection.SouthWestCorner) {
            piecePos = CellHelper.rotateCellTo(piecePos, cellSize, CellIndexDirection.EastWall);
        } else {
            piecePos = CellHelper.rotateCellTo(piecePos, cellSize, cellIndexDirection);
        }
        return piecePos;
    }

    public BlockWithMeta getWallBlock(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        if (piecePos.posY > cellHeight - cellSize) {
            int slope = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posZ - 3, 1,
                    BoundaryPair.createPair(0, cellSize * 2 - 8), cellHeight - cellSize / 3);
            int slopeBelow = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posZ - 2, 1,
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

        if (piecePos.posX != 0 && piecePos.posZ == cellSize * 4 / 10) {
            return new BlockWithMeta(Block.stoneBrick.blockID, 0);
        }

        if (piecePos.posY > cellHeight - 2 * cellSize) {
            int slope = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posZ - 3, 1,
                    BoundaryPair.createPair(1, cellSize * 2 - 8), cellHeight - cellSize);
            int slopeBelow = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posZ - 2, 1,
                    BoundaryPair.createPair(1, cellSize * 2 - 8), cellHeight - cellSize);
            if (slope == 0) {
                if (slope != slopeBelow) {
                    return new BlockWithMeta(Block.stairsStoneBrick.blockID, getStairMeta(cellIndexDirection));
                } else {
                    return new BlockWithMeta(Block.stoneBrick.blockID, 5, getStairMeta(cellIndexDirection));
                }
            }
        }
        
        if (piecePos.posX == 1 && piecePos.posZ > cellSize * 4 / 10) {
            return new BlockWithMeta(Block.stoneBrick.blockID, 0);
        }
        
        if (piecePos.posX > 0 && piecePos.posZ > cellSize * 4 / 10 && piecePos.posY == 0) {
            if(piecePos.posZ == cellSize * 4 / 10 + 1) { 
                return new BlockWithMeta(Block.cobblestone.blockID);
            } else {
                return new BlockWithMeta(Block.stoneBrick.blockID);
            }
        }
        return new BlockWithMeta(0);
    }

    private int getStairMeta(CellIndexDirection cellIndexDirection) {
        switch (cellIndexDirection) {
        case WestWall:
        case NorthEastCorner:
            return 0;
        case EastWall:
        case SouthWestCorner:
            return 1;
        case SouthWall:
        case NorthWestCorner:
            return 3;
        case NorthWall:
        case SouthEastCorner:
        default:
            return 2;
        }
    }

    @Override
    public String getIdentifier() {
        return "BPCathedralHallwayEnd";
    }

    @Override
    public int getWeight() {
        return 0;
    }
}