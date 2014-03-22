package projectzulu.common.world2.blueprint.cathedral;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.WeightedRandom;
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
        BlockWithMeta woodenPlank = new BlockWithMeta(Blocks.planks, 1);
        BlockWithMeta woodenStair = new BlockWithMeta(Blocks.spruce_stairs);

        List<BlockWithMeta> wallBlocks = new ArrayList<BlockWithMeta>(3);
        wallBlocks.add(new BlockWithMeta(Blocks.stonebrick, 2, 8)); // Cracked
        wallBlocks.add(new BlockWithMeta(Blocks.stonebrick, 1, 8)); // Mossy
        wallBlocks.add(new BlockWithMeta(Blocks.stonebrick, 0, 100)); // Regular

        /* Ceiling Building "Roof Floor" */
        if (piecePos.posY > cellHeight - cellSize) {
            int slope = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posX - 3, 1,
                    BoundaryPair.createPair(0, cellSize * 2 - 8), cellHeight - cellSize / 3);
            int slopeBelow = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posX - 2, 1,
                    BoundaryPair.createPair(0, cellSize * 2 - 8), cellHeight - cellSize / 3);
            if (slope == 0) {
                if (slope != slopeBelow) {
                    return new BlockWithMeta(woodenStair.block, getStairMeta(cellIndexDirection));
                } else {
                    return woodenPlank;
                }
            } else if (slope > 0) {
                return new BlockWithMeta(Blocks.air);
            }
        }

        if (cellIndexDirection == CellIndexDirection.SouthWestCorner
                || cellIndexDirection == CellIndexDirection.SouthEastCorner) {
            /* 'Front' Wall */
            if (piecePos.posZ == 1 && piecePos.posX > 2) {
                /* Front Wall Door */
                if (piecePos.posX == cellSize - 1 && piecePos.posY > 0 && piecePos.posY < 4) {
                    return new BlockWithMeta(Blocks.air);
                } else {
                    return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
                }
            }

            int slope = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posX - 3, 1,
                    BoundaryPair.createPair(1, cellSize * 2 - 7), cellHeight - cellSize);
            if (slope < 0 && piecePos.posZ == 0) {
                if (piecePos.posX == cellSize * 4 / 10) {
                    if (piecePos.posY % 2 == 0) {
                        return new BlockWithMeta(Blocks.stone_brick_stairs,
                                cellIndexDirection == CellIndexDirection.SouthWestCorner ? 0 : 1);
                    } else {
                        return new BlockWithMeta(Blocks.stone_brick_stairs,
                                cellIndexDirection == CellIndexDirection.SouthWestCorner ? 4 : 5);
                    }
                } else if (piecePos.posX == cellSize * 4 / 10 + 1) {
                    if (piecePos.posY % 2 == 0) {
                        return new BlockWithMeta(Blocks.stone_brick_stairs,
                                cellIndexDirection == CellIndexDirection.SouthWestCorner ? 1 : 0);
                    } else {
                        return new BlockWithMeta(Blocks.stone_brick_stairs,
                                cellIndexDirection == CellIndexDirection.SouthWestCorner ? 5 : 4);
                    }
                } else if (piecePos.posX > cellSize * 4 / 10 && piecePos.posY == 0) {
                    return new BlockWithMeta(Blocks.stone_brick_stairs, 3);
                }
            }

            /* Air In Front of Doorway */
            if (piecePos.posZ == 0 && piecePos.posX > 2 && piecePos.posY < 4) {
                return new BlockWithMeta(Blocks.air);
            }
        }

        if (piecePos.posX <= 2 && piecePos.posZ == 1) {
            return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
        }

        /* Mid Building "Roof Floor" */
        if (piecePos.posY > cellHeight - 2 * cellSize) {
            int slope = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posX - 3, 1,
                    BoundaryPair.createPair(1, cellSize * 2 - 7), cellHeight - cellSize);
            int slopeBelow = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posX - 2, 1,
                    BoundaryPair.createPair(1, cellSize * 2 - 7), cellHeight - cellSize);

            if (slope == 0) {
                if (slope != slopeBelow) {
                    if (piecePos.posZ <= 1) {
                        return new BlockWithMeta(woodenStair.block, getStairMeta(cellIndexDirection));
                    } else {
                        return new BlockWithMeta(Blocks.stone_brick_stairs, getStairMeta(cellIndexDirection));
                    }
                } else {
                    if (piecePos.posZ <= 1
                            && (cellIndexDirection == CellIndexDirection.SouthWestCorner || cellIndexDirection == CellIndexDirection.SouthEastCorner)) {
                        return woodenPlank;
                    } else {
                        return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
                    }
                }
            }

            if (piecePos.posZ > 1 && slope > 0 && slope <= 2) {
                return new BlockWithMeta(Blocks.air);
            }

            /* Arches */
            int topAarchSlope = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posX + 0, 1,
                    BoundaryPair.createPair(1, cellSize * 2), cellHeight - cellSize);
            int botAarchSlope = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posX + 1, 1,
                    BoundaryPair.createPair(1, cellSize * 2), cellHeight - cellSize);
            if ((topAarchSlope == 0 || botAarchSlope == 0) && piecePos.posZ % 3 == 1
                    && piecePos.posX > cellSize * 4 / 10) {
                return new BlockWithMeta(woodenStair.block, getArchStairMeta(cellIndexDirection,
                        topAarchSlope == 0 ? true : false));

            }
        }
        /* Outer Walls */
        if (piecePos.posX == cellSize * 4 / 10 && (piecePos.posZ <= 3 || piecePos.posY >= 5)) {
            // Exclude Door side of wall
            if (piecePos.posZ >= 1
                    || (cellIndexDirection != CellIndexDirection.SouthWestCorner && cellIndexDirection != CellIndexDirection.SouthEastCorner)) {
                return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
            } else {
            }
        }

        /* Red Carpet */
        if (piecePos.posY == 1 && (piecePos.posX == cellSize - 1 || piecePos.posZ == cellSize - 1)) {
            return new BlockWithMeta(Blocks.carpet, 14);
        }

        /* Floors */
        if (piecePos.posY == 0) {
            if (piecePos.posX == cellSize * 4 / 10 + 1 && (piecePos.posZ <= 3 || piecePos.posY >= 5)) {
                return new BlockWithMeta(Blocks.cobblestone, 0);
            }

            /* Floor of Entrance */
            if (piecePos.posX > cellSize * 4 / 10) {
                return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
            }
            /* Floor Connecting To tower */
            if (piecePos.posZ > 0) {
                return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
            }
        }
        return new BlockWithMeta(Blocks.air);
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

    public int getArchStairMeta(CellIndexDirection cellIndexDirection, boolean top) {
        switch (cellIndexDirection) {
        case NorthWestCorner:
        case SouthWestCorner:
            return top ? 0 : 5;
        case NorthEastCorner:
        case SouthEastCorner:
            return top ? 1 : 4;
        default:
            return 0;
        }
    }

    public int getDoorPillarStairMeta(CellIndexDirection cellIndexDirection, boolean top, boolean left) {
        switch (cellIndexDirection) {
        case SouthWestCorner:
            if (top) {
                return left ? 0 : 5;
            } else {
                return left ? 4 : 5;
            }
        case SouthEastCorner:
            if (top) {
                return left ? 0 : 5;
            } else {
                return left ? 0 : 5;
            }
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
