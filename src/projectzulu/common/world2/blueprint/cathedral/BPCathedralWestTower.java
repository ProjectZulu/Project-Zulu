package projectzulu.common.world2.blueprint.cathedral;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.BoundaryPair;
import projectzulu.common.world2.CellHelper;
import projectzulu.common.world2.blueprint.Blueprint;

public class BPCathedralWestTower implements Blueprint {

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
        BlockWithMeta woodenPlank = new BlockWithMeta(5, 1);

        int diagonalIndex = piecePos.posZ + piecePos.posX;
        int stairSegmentHeight = 2;

        /* Tower Bell */
        if (piecePos.posY > cellHeight - 6 && piecePos.posX > cellSize - 3 && piecePos.posZ > cellSize - 3) {
            if (piecePos.posY == cellHeight - 2 && piecePos.posX == cellSize - 1 && piecePos.posZ == cellSize - 1) {
                return new BlockWithMeta(Block.stoneBrick.blockID);
            }

            if (piecePos.posY < cellHeight - 2) {
                if (piecePos.posX == cellSize - 1 && piecePos.posZ == cellSize - 1) {
                    return new BlockWithMeta(Block.blockIron.blockID);
                }

                if (piecePos.posY == cellHeight - 5) {
                    return new BlockWithMeta(Block.stoneBrick.blockID);
                } else if (piecePos.posX == cellSize - 1 || piecePos.posZ == cellSize - 1) {
                    return new BlockWithMeta(Block.stoneBrick.blockID);
                }
            }
        }

        /* Ceiling */
        if (piecePos.posY > cellHeight - cellSize * 2) {
            int slope = CellHelper.getSlopeIndex(piecePos, cellSize - diagonalIndex - 1, 1,
                    BoundaryPair.createPair(1, (cellSize) * 2), cellHeight);
            if (slope == 0) {
                return woodenPlank;
            } else if (slope > 0) {
                return new BlockWithMeta(0);
            }
        }
        
        if (piecePos.posX == 0 && piecePos.posZ == 0) {
            return new BlockWithMeta(0);
        }   

        /* Create Outside Walls */
        if (piecePos.posX == 0 || piecePos.posZ == 0) {
            if (piecePos.posX == 1 || piecePos.posZ == 1) {
                return new BlockWithMeta(Block.stoneBrick.blockID);
            }

            if (piecePos.posX == 0) {
                int slot = piecePos.posY % 4 == 0 ? 0 : 1;
                if (cellIndexDirection == CellIndexDirection.SouthEastCorner && piecePos.posY > 0
                        && piecePos.posY <= 10) {
                    if (piecePos.posY % 5 == 0 || piecePos.posZ <= 1) {
                        return new BlockWithMeta(Block.stoneBrick.blockID);
                    }
                } else if (piecePos.posY == 0 || piecePos.posY == 1) {
                    return new BlockWithMeta(Block.stoneBrick.blockID);
                } else if (piecePos.posZ == 2 || piecePos.posZ == cellSize - 1 || piecePos.posZ % 2 == slot) {
                    return new BlockWithMeta(Block.stoneBrick.blockID);
                }
                return new BlockWithMeta(0);
            }

            if (piecePos.posZ == 0) {
                int slot = piecePos.posY % 4 == 0 ? 0 : 1;
                if (cellIndexDirection == CellIndexDirection.NorthEastCorner && piecePos.posY > 0
                        && piecePos.posY <= 10) {
                    if (piecePos.posY % 5 == 0 || piecePos.posX <= 1) {
                        return new BlockWithMeta(Block.stoneBrick.blockID);
                    }
                } else if (piecePos.posY == 0 || piecePos.posY == 1) {
                    return new BlockWithMeta(Block.stoneBrick.blockID);
                } else if (piecePos.posX == 2 || piecePos.posX == cellSize - 1 || piecePos.posX % 2 == slot) {
                    return new BlockWithMeta(Block.stoneBrick.blockID);
                }
                return new BlockWithMeta(0);

            }
        }

        /* Create Stairs */
        if (0 < piecePos.posZ && piecePos.posZ < 3) {
            /* Rising East-West */
            int slope = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posX - 1, 1,
                    BoundaryPair.createPair(0, stairSegmentHeight - 1),
                    getStairSegmentTop(piecePos, cellIndexDirection));
            int slopeBelow = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posX + 0, 1,
                    BoundaryPair.createPair(0, stairSegmentHeight - 1),
                    getStairSegmentTop(piecePos, cellIndexDirection));
            if (slope == 0) {
                return slope != slopeBelow ? new BlockWithMeta(Block.stairsStoneBrick.blockID, getStairMeta(
                        cellIndexDirection, true)) : new BlockWithMeta(Block.stoneBrick.blockID, 0);
            }
        } else if (0 < piecePos.posX && piecePos.posX < 3) {
            int slope = CellHelper.getSlopeIndex(piecePos, piecePos.posZ - 2, 1,
                    BoundaryPair.createPair(1, stairSegmentHeight), getStairSegmentTop(piecePos, cellIndexDirection));
            int slopeBelow = CellHelper.getSlopeIndex(piecePos, piecePos.posZ - 1, 1,
                    BoundaryPair.createPair(1, stairSegmentHeight), getStairSegmentTop(piecePos, cellIndexDirection));
            if (slope == 0) {
                return slope != slopeBelow ? new BlockWithMeta(Block.stairsStoneBrick.blockID, getStairMeta(
                        cellIndexDirection, false)) : new BlockWithMeta(Block.stoneBrick.blockID, 0);
            }
        }

        /* Tower-MidCathEntrance Floor */
        if (piecePos.posY % cellSize == 5 && (piecePos.posZ < 1 || piecePos.posX < 1)) {
            return new BlockWithMeta(Block.stoneBrick.blockID);
        }

        /* Tower Rooms */
        if (isRoomForRoom(piecePos, cellSize, cellHeight) && piecePos.posX > 2 && piecePos.posZ > 2
                && piecePos.posY > 0) {
            if (piecePos.posY % (stairSegmentHeight * 2) == 3) {
                return new BlockWithMeta(Block.stoneBrick.blockID, 0);
            }

            /* Ensure the Door doesn't generate into the floor */
            if (piecePos.posY != 0 && piecePos.posY - 1 != 0) {
                if (cellIndexDirection == CellIndexDirection.NorthWestCorner && piecePos.posZ == 3
                        && piecePos.posX == 4) {
                    if (piecePos.posY % (stairSegmentHeight * 4) == 4) {
                        return new BlockWithMeta(Block.doorWood.blockID, 1);
                    } else if (piecePos.posY % (stairSegmentHeight * 4) == 5) {
                        return new BlockWithMeta(Block.doorWood.blockID, 9);
                    }
                } else if (cellIndexDirection == CellIndexDirection.SouthEastCorner && piecePos.posZ == 3
                        && piecePos.posX == 4) {
                    if (piecePos.posY % (stairSegmentHeight * 4) == 0) {
                        return new BlockWithMeta(Block.doorWood.blockID, 3);
                    } else if (piecePos.posY % (stairSegmentHeight * 4) == 1) {
                        return new BlockWithMeta(Block.doorWood.blockID, 11);
                    }
                }
            }

            /* Room Walls */
            if (piecePos.posX == 3 || piecePos.posZ == 3) {
                return new BlockWithMeta(Block.stoneBrick.blockID);
            }

            /* Room Contents: Bed */
            if (cellIndexDirection == CellIndexDirection.SouthWestCorner) {
                if ((piecePos.posX == 4 || piecePos.posZ == 4)) {
                    if (piecePos.posY % (stairSegmentHeight * 2) == 0
                            || (piecePos.posY % (stairSegmentHeight * 2) == 1 && random.nextInt(3) == 0)) {
                        return new BlockWithMeta(Block.bookShelf.blockID);
                    }
                }
            }

            /* Room Contents: Bookshelf */
            if (cellIndexDirection == CellIndexDirection.NorthEastCorner) {
                if (piecePos.posY % (stairSegmentHeight * 2) == 0) {
                    if (piecePos.posZ == 4 && (piecePos.posX == 4 || piecePos.posX == 5)) {
                        return piecePos.posX == 5 ? new BlockWithMeta(Block.bed.blockID, 2) : new BlockWithMeta(
                                Block.bed.blockID, 10);
                    }
                }
            }
        }

        /* Tower Floor */
        if (piecePos.posY == 0) {
            return new BlockWithMeta(Block.stoneBrick.blockID);
        }

        return new BlockWithMeta(0);
    }

    private boolean isRoomForRoom(ChunkCoordinates piecePos, int cellSize, int cellHeight) {
        int roomHeight = 2 * 2;
        int ceilingClearance = 8;
        for (int i = 0; i < roomHeight; i++) {
            if ((piecePos.posY + i) % roomHeight == 3) {
                if (piecePos.posY + i + ceilingClearance <= cellHeight) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    private int getStairSegmentTop(ChunkCoordinates piecePos, CellIndexDirection cellIndexDirection) {
        switch (cellIndexDirection) {
        case NorthWestCorner:
        default:
            return (int) (4 + Math.floor(piecePos.posY / 8) * 8);
        case NorthEastCorner:
            return (int) (6 + Math.floor(piecePos.posY / 8) * 8);
        case SouthEastCorner:
            return (int) (8 + Math.floor(piecePos.posY / 8.01D) * 8);
        case SouthWestCorner:
            return (int) (2 + Math.floor(piecePos.posY / 8) * 8);
        }
    }

    private int getStairMeta(CellIndexDirection cellIndexDirection, boolean high) {
        switch (cellIndexDirection) {
        case NorthWestCorner:
        default:
            return high ? 0 : 3;
        case NorthEastCorner:
            return high ? 2 : 0;
        case SouthEastCorner:
            return high ? 1 : 2;
        case SouthWestCorner:
            return high ? 3 : 1;
        }
    }

    @Override
    public String getIdentifier() {
        return "CathedralWestTower";
    }

    @Override
    public int getWeight() {
        return 0;
    }
}
