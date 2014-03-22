package projectzulu.common.world2.blueprint.cathedral;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.WeightedRandom;
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
        BlockWithMeta woodenPlank = new BlockWithMeta(Blocks.planks, 1);
        BlockWithMeta carvedStone = new BlockWithMeta(Blocks.stonebrick, 3);

        List<BlockWithMeta> wallBlocks = new ArrayList<BlockWithMeta>(3);
        wallBlocks.add(new BlockWithMeta(Blocks.stonebrick, 2, 8)); // Cracked
        wallBlocks.add(new BlockWithMeta(Blocks.stonebrick, 1, 8)); // Mossy
        wallBlocks.add(new BlockWithMeta(Blocks.stonebrick, 0, 100)); // Regular

        int diagonalIndex = piecePos.posZ + piecePos.posX;
        int stairSegmentHeight = 2;

        /* Tower Bell */
        if (piecePos.posY > cellHeight - 6 && piecePos.posX > cellSize - 3 && piecePos.posZ > cellSize - 3) {
            if (piecePos.posY == cellHeight - 2 && piecePos.posX == cellSize - 1 && piecePos.posZ == cellSize - 1) {
                return carvedStone;
            }

            if (piecePos.posY < cellHeight - 2) {
                if (piecePos.posX == cellSize - 1 && piecePos.posZ == cellSize - 1) {
                    return new BlockWithMeta(Blocks.iron_block);
                }

                if (piecePos.posY == cellHeight - 5) {
                    return carvedStone;
                } else if (piecePos.posX == cellSize - 1 || piecePos.posZ == cellSize - 1) {
                    return carvedStone;
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
                return new BlockWithMeta(Blocks.air);
            }
        }

        if (piecePos.posX == 0 && piecePos.posZ == 0) {
            return new BlockWithMeta(Blocks.air);
        }

        /* Create Outside Walls */
        if (piecePos.posX == 0 || piecePos.posZ == 0) {
            if (piecePos.posX == 1 || piecePos.posZ == 1) {
                return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
            }

            if (piecePos.posX == 0) {
                if (cellIndexDirection == CellIndexDirection.NorthWestCorner && piecePos.posY > 0
                        && piecePos.posY <= 10) {
                    if (piecePos.posY % 5 == 0 || piecePos.posZ <= 1) {
                        return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
                    }
                } else if (piecePos.posY == 0 || piecePos.posY == 1) {
                    return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
                } else if (piecePos.posZ == 2 || piecePos.posZ == 3) {
                    return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
                } else if (piecePos.posZ > 3 || piecePos.posZ == cellSize - 2) {
                    if (piecePos.posZ == cellSize - 1) {
                        return new BlockWithMeta(Blocks.stone_brick_stairs, getWindowMeta(cellIndexDirection,
                                true, true, piecePos.posY % 4));
                    } else {
                        return new BlockWithMeta(Blocks.stone_brick_stairs, getWindowMeta(cellIndexDirection,
                                true, false, piecePos.posY % 4));
                    }
                }
                return new BlockWithMeta(Blocks.air);
            }

            if (piecePos.posZ == 0) {
                if (cellIndexDirection == CellIndexDirection.SouthWestCorner && piecePos.posY > 0
                        && piecePos.posY <= 10) {
                    if (piecePos.posY % 5 == 0 || piecePos.posX <= 1) {
                        return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
                    }
                } else if (piecePos.posY == 0 || piecePos.posY == 1) {
                    return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
                } else if (piecePos.posX == 2 || piecePos.posX == 3) {
                    return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
                } else if (piecePos.posX > 3 || piecePos.posX == cellSize - 2) {
                    if (piecePos.posX == cellSize - 1) {
                        return new BlockWithMeta(Blocks.stone_brick_stairs, getWindowMeta(cellIndexDirection,
                                false, true, piecePos.posY % 4));
                    } else {
                        return new BlockWithMeta(Blocks.stone_brick_stairs, getWindowMeta(cellIndexDirection,
                                false, false, piecePos.posY % 4));
                    }
                }
                return new BlockWithMeta(Blocks.air);
            }
        }

        /* Create Stairs */
        if (0 < piecePos.posZ && piecePos.posZ < 3) {
            /* Rising West-East */
            int slope = CellHelper.getSlopeIndex(piecePos, piecePos.posX - 2, 1,
                    BoundaryPair.createPair(1, stairSegmentHeight), getStairSegmentTop(piecePos, cellIndexDirection));
            int slopeBelow = CellHelper.getSlopeIndex(piecePos, piecePos.posX - 1, 1,
                    BoundaryPair.createPair(1, stairSegmentHeight), getStairSegmentTop(piecePos, cellIndexDirection));
            if (slope == 0) {
                return slope != slopeBelow ? new BlockWithMeta(Blocks.stone_brick_stairs, getStairMeta(
                        cellIndexDirection, false)) : (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
            }
        } else if (0 < piecePos.posX && piecePos.posX < 3) {
            int slope = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posZ - 1, 1,
                    BoundaryPair.createPair(0, stairSegmentHeight - 1),
                    getStairSegmentTop(piecePos, cellIndexDirection));
            int slopeBelow = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posZ - 0, 1,
                    BoundaryPair.createPair(0, stairSegmentHeight - 1),
                    getStairSegmentTop(piecePos, cellIndexDirection));
            if (slope == 0) {
                return slope != slopeBelow ? new BlockWithMeta(Blocks.stone_brick_stairs, getStairMeta(
                        cellIndexDirection, true)) : (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
            }
        }

        /* Tower-MidCathEntrance Floor */
        if (piecePos.posY % cellSize == 5 && (piecePos.posZ < 1 || piecePos.posX < 1)) {
            return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
        }

        /* Tower Rooms */
        if (isRoomForRoom(piecePos, cellSize, cellHeight) && piecePos.posX > 2 && piecePos.posZ > 2
                && piecePos.posY > 0) {
            if (piecePos.posY % (stairSegmentHeight * 2) == 3) {
                return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
            }

            /* Ensure the Door doesn't generate into the floor */
            if (piecePos.posY != 0 && piecePos.posY - 1 != 0) {
                if (cellIndexDirection == CellIndexDirection.NorthEastCorner && piecePos.posX == 3
                        && piecePos.posZ == 4) {
                    if (piecePos.posY % (stairSegmentHeight * 4) == 4) {
                        return new BlockWithMeta(Blocks.wooden_door, 1);
                    } else if (piecePos.posY % (stairSegmentHeight * 4) == 5) {
                        return new BlockWithMeta(Blocks.wooden_door, 9);
                    }
                } else if (cellIndexDirection == CellIndexDirection.SouthWestCorner && piecePos.posX == 3
                        && piecePos.posZ == 4) {
                    if (piecePos.posY % (stairSegmentHeight * 4) == 0) {
                        return new BlockWithMeta(Blocks.wooden_door, 3);
                    } else if (piecePos.posY % (stairSegmentHeight * 4) == 1) {
                        return new BlockWithMeta(Blocks.wooden_door, 11);
                    }
                }
            }

            /* Room Walls */
            if (piecePos.posX == 3 || piecePos.posZ == 3) {
                return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
            }

            /* Room Contents: Bed */
            if (cellIndexDirection == CellIndexDirection.SouthEastCorner) {
                if ((piecePos.posX == 4 || piecePos.posZ == 4)) {
                    if (piecePos.posY % (stairSegmentHeight * 2) == 0
                            || (piecePos.posY % (stairSegmentHeight * 2) == 1 && random.nextInt(3) == 0)) {
                        return new BlockWithMeta(Blocks.bookshelf);
                    }
                }
            }

            /* Room Contents: Bookshelf */
            if (cellIndexDirection == CellIndexDirection.NorthWestCorner) {
                if (piecePos.posY % (stairSegmentHeight * 2) == 0) {
                    if (piecePos.posX == 4 && (piecePos.posZ == 4 || piecePos.posZ == 5)) {
                        return piecePos.posZ == 5 ? new BlockWithMeta(Blocks.bed, 2) : new BlockWithMeta(
                                Blocks.bed, 10);
                    }
                }
            }
        }

        /* Tower Floor */
        if (piecePos.posY == 0) {
            return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
        }

        return new BlockWithMeta(Blocks.air);
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

    private int getWindowMeta(CellIndexDirection cellIndexDirection, boolean otherWall, boolean edge, int height) {
        if (otherWall) {
            switch (cellIndexDirection) {
            case NorthWestCorner:
            default:
                return getWindowMeta(CellIndexDirection.NorthEastCorner, edge, height);
            case NorthEastCorner:
                return getWindowMeta(CellIndexDirection.SouthEastCorner, edge, height);
            case SouthEastCorner:
                return getWindowMeta(CellIndexDirection.SouthWestCorner, edge, height);
            case SouthWestCorner:
                return getWindowMeta(CellIndexDirection.NorthWestCorner, edge, height);
            }
        } else {
            return getWindowMeta(cellIndexDirection, edge, height);
        }
    }

    private int getWindowMeta(CellIndexDirection cellIndexDirection, boolean edge, int height) {
        switch (cellIndexDirection) {
        case NorthWestCorner:
        default:
            if (edge) {
                return height == 0 ? 5 : height == 1 ? 4 : height == 2 ? 0 : 1;
            } else {
                return height % 2 == 0 ? 1 : 5;
            }
        case NorthEastCorner:
            if (edge) {
                return height == 0 ? 7 : height == 1 ? 6 : height == 2 ? 2 : 3;
            } else {
                return height % 2 == 0 ? 3 : 7;
            }
        case SouthEastCorner:
            if (edge) {
                return height == 0 ? 4 : height == 1 ? 5 : height == 2 ? 1 : 0;
            } else {
                return height % 2 == 0 ? 0 : 4;
            }
        case SouthWestCorner:
            if (edge) {
                return height == 0 ? 6 : height == 1 ? 7 : height == 2 ? 3 : 2;
            } else {
                return height % 2 == 0 ? 2 : 6;
            }
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
