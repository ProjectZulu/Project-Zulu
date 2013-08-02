package projectzulu.common.world2.blueprint.cathedral;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.BoundaryPair;
import projectzulu.common.world2.CellHelper;
import projectzulu.common.world2.blueprint.Blueprint;

public class BPCathedralEastTower implements Blueprint {

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        if (cellIndexDirection == CellIndexDirection.NorthWestCorner) {
            return getTowerBlock(CellHelper.rotateCellTo(piecePos, cellSize, CellIndexDirection.NorthWall), cellSize,
                    cellHeight, random, cellIndexDirection);
        } else if (cellIndexDirection == CellIndexDirection.NorthEastCorner) {
            return getTowerBlock(CellHelper.rotateCellTo(piecePos, cellSize, CellIndexDirection.EastWall), cellSize,
                    cellHeight, random, cellIndexDirection);
        } else if (cellIndexDirection == CellIndexDirection.SouthWestCorner) {
            return getTowerBlock(CellHelper.rotateCellTo(piecePos, cellSize, CellIndexDirection.WestWall), cellSize,
                    cellHeight, random, cellIndexDirection);
        } else if (cellIndexDirection == CellIndexDirection.SouthEastCorner) {
            return getTowerBlock(CellHelper.rotateCellTo(piecePos, cellSize, CellIndexDirection.SouthWall), cellSize,
                    cellHeight, random, cellIndexDirection);
        }
        return null;
    }

    public BlockWithMeta getTowerBlock(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        int diagonalIndex = piecePos.posZ + piecePos.posX;
        int stairSegmentHeight = 2;

        /* Ceiling */
        if (piecePos.posY > cellHeight - cellSize * 2) {
            int slope = CellHelper.getSlopeIndex(piecePos, cellSize - diagonalIndex - 1, 1,
                    BoundaryPair.createPair(1, (cellSize) * 2), cellHeight);
            if (slope == 0) {
                return new BlockWithMeta(Block.stoneBrick.blockID, 0);
            } else if (slope > 0) {
                return new BlockWithMeta(0);
            }

            if (piecePos.posX == 0) {
                int slot = piecePos.posY % 4 == 0 ? 0 : 1;
                return piecePos.posZ % 2 == slot ? new BlockWithMeta(Block.stoneBrick.blockID, 0)
                        : new BlockWithMeta(0);
            }

            if (piecePos.posZ == 0) {
                int slot = piecePos.posY % 4 == 0 ? 0 : 1;
                return piecePos.posX % 2 == slot ? new BlockWithMeta(Block.stoneBrick.blockID, 0)
                        : new BlockWithMeta(0);
            }
        }

        /* Create Outside Walls */
        if (diagonalIndex == 1) {
            return new BlockWithMeta(Block.stoneBrick.blockID, 0);
        }

        /* Create Stairs */
        if (0 < piecePos.posZ && piecePos.posZ < 3) {
            int slope = CellHelper.getSlopeIndex(piecePos, piecePos.posX - 2, 1,
                    BoundaryPair.createPair(1, stairSegmentHeight), getStairSegmentTop(piecePos, cellIndexDirection));
            int slopeBelow = CellHelper.getSlopeIndex(piecePos, piecePos.posX - 1, 1,
                    BoundaryPair.createPair(1, stairSegmentHeight), getStairSegmentTop(piecePos, cellIndexDirection));
            if (slope == 0) {
                return slope != slopeBelow ? new BlockWithMeta(Block.stairsStoneBrick.blockID, getStairMeta(
                        cellIndexDirection, false)) : new BlockWithMeta(Block.stoneBrick.blockID, 0);
            }
        } else if (0 < piecePos.posX && piecePos.posX < 3) {
            int slope = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posZ - 1, 1,
                    BoundaryPair.createPair(0, stairSegmentHeight - 1),
                    getStairSegmentTop(piecePos, cellIndexDirection));
            int slopeBelow = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posZ - 0, 1,
                    BoundaryPair.createPair(0, stairSegmentHeight - 1),
                    getStairSegmentTop(piecePos, cellIndexDirection));
            if (slope == 0) {
                return slope != slopeBelow ? new BlockWithMeta(Block.stairsStoneBrick.blockID, getStairMeta(
                        cellIndexDirection, true)) : new BlockWithMeta(Block.stoneBrick.blockID, 0);
            }
        }

        if (piecePos.posY % cellSize == 5 && (piecePos.posZ < 1 || piecePos.posX < 1)) {
            return new BlockWithMeta(Block.stoneBrick.blockID);
        }
        
        /* Tower Rooms */
        if (piecePos.posX > 2 && piecePos.posZ > 2) {
            if (piecePos.posY % (stairSegmentHeight * 2) == 3) {
                return new BlockWithMeta(Block.stoneBrick.blockID, 0);
            }

            /* Ensure the Door doesn't generate into the floor */
            if (piecePos.posY != 0 && piecePos.posY - 1 != 0) {
                if (cellIndexDirection == CellIndexDirection.NorthEastCorner && piecePos.posX == 3
                        && piecePos.posZ == 4) {
                    if (piecePos.posY % (stairSegmentHeight * 4) == 4) {
                        return new BlockWithMeta(Block.doorWood.blockID, 1);
                    } else if (piecePos.posY % (stairSegmentHeight * 4) == 5) {
                        return new BlockWithMeta(Block.doorWood.blockID, 9);
                    }
                } else if (cellIndexDirection == CellIndexDirection.SouthWestCorner && piecePos.posX == 3
                        && piecePos.posZ == 4) {
                    if (piecePos.posY % (stairSegmentHeight * 4) == 0) {
                        return new BlockWithMeta(Block.doorWood.blockID, 3);
                    } else if (piecePos.posY % (stairSegmentHeight * 4) == 1) {
                        return new BlockWithMeta(Block.doorWood.blockID, 11);
                    }
                }
            }

            if (piecePos.posX == 3 || piecePos.posZ == 3) {
                return new BlockWithMeta(Block.stoneBrick.blockID, 0);
            }
        }

        if (piecePos.posY == 0) {
            return new BlockWithMeta(Block.stoneBrick.blockID, 0);
        }
        return new BlockWithMeta(0);
    }

    private int getStairSegmentTop(ChunkCoordinates piecePos, CellIndexDirection cellIndexDirection) {
        switch (cellIndexDirection) {
        case NorthWestCorner:
        default:
            return (int) (6 + Math.floor(piecePos.posY / 8) * 8);
        case NorthEastCorner:
            return (int) (4 + Math.floor(piecePos.posY / 8) * 8);
        case SouthEastCorner:
            return (int) (2 + Math.floor(piecePos.posY / 8) * 8);
        case SouthWestCorner:
            return (int) (8 + Math.floor(piecePos.posY / 8.01D) * 8);
        }
    }

    private int getStairMeta(CellIndexDirection cellIndexDirection, boolean high) {
        switch (cellIndexDirection) {
        case NorthWestCorner:
        default:
            return high ? 2 : 1;
        case NorthEastCorner:
            return high ? 1 : 3;
        case SouthEastCorner:
            return high ? 3 : 0;
        case SouthWestCorner:
            return high ? 0 : 2;
        }
    }

    @Override
    public String getIdentifier() {
        return "CathedralEastTower";
    }

    @Override
    public int getWeight() {
        return 0;
    }
}
