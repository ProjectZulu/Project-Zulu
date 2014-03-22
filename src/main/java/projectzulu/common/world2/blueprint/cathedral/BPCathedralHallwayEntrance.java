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

public class BPCathedralHallwayEntrance implements Blueprint {

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        piecePos = applyRotation(piecePos, cellSize, cellIndexDirection);
        piecePos = applyMirror(piecePos, cellSize, cellIndexDirection);

        return getWallBlock(piecePos, cellSize, cellHeight, random, cellIndexDirection);
    }

    private ChunkCoordinates applyRotation(ChunkCoordinates piecePos, int cellSize,
            CellIndexDirection cellIndexDirection) {
        return CellHelper.rotateCellTo(piecePos, cellSize, CellIndexDirection.WestWall);
    }

    private ChunkCoordinates applyMirror(ChunkCoordinates piecePos, int cellSize, CellIndexDirection cellIndexDirection) {
        if (cellIndexDirection == CellIndexDirection.WestWall) {
            piecePos = CellHelper.mirrorCellTo(piecePos, cellSize, CellIndexDirection.NorthWestCorner);
        } else if (cellIndexDirection == CellIndexDirection.EastWall) {
            return CellHelper.mirrorCellTo(piecePos, cellSize, CellIndexDirection.SouthWestCorner);
        }
        return piecePos;
    }

    public BlockWithMeta getWallBlock(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        BlockWithMeta woodenPlank = new BlockWithMeta(Blocks.planks, 1);
        BlockWithMeta woodenStair = new BlockWithMeta(Blocks.spruce_stairs);

        List<BlockWithMeta> wallBlocks = new ArrayList<BlockWithMeta>(3);
        wallBlocks.add(new BlockWithMeta(Blocks.stonebrick, 2, 8)); // Cracked
        wallBlocks.add(new BlockWithMeta(Blocks.stonebrick, 1, 8)); // Mossy
        wallBlocks.add(new BlockWithMeta(Blocks.stonebrick, 0, 100)); // Regular

        /* Ceiling Top */
        if (piecePos.posY > cellHeight - cellSize) {
            int slope = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posZ - 3, 1,
                    BoundaryPair.createPair(0, cellSize * 2 - 8), cellHeight - cellSize / 3);
            int slopeBelow = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posZ - 2, 1,
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

        /* Outer Wall */
        if (piecePos.posZ == cellSize * 4 / 10) {
            return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
        }

        /* Mid Ceiling */
        if (piecePos.posY > cellHeight - cellSize * 2) {
            int slope = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posZ - 3, 1,
                    BoundaryPair.createPair(1, cellSize * 2 - 8), cellHeight - cellSize);
            int slopeBelow = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posZ - 2, 1,
                    BoundaryPair.createPair(1, cellSize * 2 - 8), cellHeight - cellSize);
            if (slope == 0) {
                if (slope != slopeBelow) {
                    return new BlockWithMeta(woodenStair.block, getStairMeta(cellIndexDirection));
                } else {
                    return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
                }
            }

            /* Arches */
            int topAarchSlope = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posZ + 0, 1,
                    BoundaryPair.createPair(1, cellSize * 2), cellHeight - cellSize);
            if (topAarchSlope == 0 && piecePos.posX % 3 == 1) {
                if (piecePos.posZ > cellSize * 4 / 10) {
                    return new BlockWithMeta(woodenStair.block, getArchStairMeta(cellIndexDirection, true));
                }
            }

            int botAarchSlope = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posZ + 1, 1,
                    BoundaryPair.createPair(1, cellSize * 2), cellHeight - cellSize);
            if (botAarchSlope == 0 && piecePos.posX % 3 == 1) {
                if (piecePos.posZ > cellSize * 4 / 10) {
                    return new BlockWithMeta(woodenStair.block, getArchStairMeta(cellIndexDirection, false));
                }
            }
        }

        /* Red Carpet */
        if (piecePos.posY == 1 && piecePos.posZ == cellSize - 1) {
            return new BlockWithMeta(Blocks.carpet, 14);
        }

        /* Pews */
        if (piecePos.posY == 1 && piecePos.posX % 2 == 0) {
            if (piecePos.posZ == cellSize * 4 / 10 + 1 || piecePos.posZ == cellSize - 2) {
                return new BlockWithMeta(Blocks.oak_stairs, getPewStairMeta(cellIndexDirection,
                        piecePos.posZ == cellSize - 2));
            }
        }

        if (piecePos.posZ > cellSize * 4 / 10 && piecePos.posY == 0) {
            if (piecePos.posZ == cellSize * 4 / 10 + 1) {
                return new BlockWithMeta(Blocks.cobblestone);
            } else {
                return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
            }
        }
        return new BlockWithMeta(Blocks.air);
    }

    private int getStairMeta(CellIndexDirection cellIndexDirection) {
        switch (cellIndexDirection) {
        case WestWall:
            return 0;
        case EastWall:
            return 1;
        case SouthWall:
            return 3;
        case NorthWall:
        default:
            return 2;
        }
    }

    public int getArchStairMeta(CellIndexDirection cellIndexDirection, boolean top) {
        switch (cellIndexDirection) {
        case WestWall:
            return top ? 0 : 5;
        case EastWall:
            return top ? 1 : 4;
        case SouthWall:
            return 2;
        case NorthWall:
        default:
            return 2;
        }
    }

    private int getPewStairMeta(CellIndexDirection cellIndexDirection, boolean onRight) {
        switch (cellIndexDirection) {
        case WestWall:
            return onRight ? 0 : 1;
        case EastWall:
            return onRight ? 1 : 0;
        default:
            return 0;
        }
    }

    @Override
    public String getIdentifier() {
        return "BPCathedralHallwayEntrance";
    }

    @Override
    public int getWeight() {
        return 0;
    }
}