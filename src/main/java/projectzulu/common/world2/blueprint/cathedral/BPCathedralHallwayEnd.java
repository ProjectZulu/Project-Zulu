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

public class BPCathedralHallwayEnd implements Blueprint {

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        piecePos = applyMirror(piecePos, cellSize, cellIndexDirection);
        piecePos = applyRotation(piecePos, cellSize, cellIndexDirection);
        return getWallBlock(CellHelper.rotateCellTo(piecePos, cellSize, CellIndexDirection.NorthWall), cellSize,
                cellHeight, random, cellIndexDirection);
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
        BlockWithMeta woodenPlank = new BlockWithMeta(Blocks.planks, 1);
        BlockWithMeta woodenStair = new BlockWithMeta(Blocks.spruce_stairs);

        List<BlockWithMeta> wallBlocks = new ArrayList<BlockWithMeta>(3);
        wallBlocks.add(new BlockWithMeta(Blocks.stonebrick, 2, 8)); // Cracked
        wallBlocks.add(new BlockWithMeta(Blocks.stonebrick, 1, 8)); // Mossy
        wallBlocks.add(new BlockWithMeta(Blocks.stonebrick, 0, 100)); // Regular

        /* Ceiling */
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
        if (piecePos.posX != 0 && piecePos.posZ == 2) {
            return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
        }

        /* Mid Ceiling-Floor */
        int slope = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posZ - 3, 1,
                BoundaryPair.createPair(1, cellSize * 2 - 8), cellHeight - cellSize);
        int slopeBelow = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posZ - 2, 1,
                BoundaryPair.createPair(1, cellSize * 2 - 8), cellHeight - cellSize);
        if (slope == 0) {
            if (slope != slopeBelow) {
                return new BlockWithMeta(woodenStair.block, getStairMeta(cellIndexDirection));
            } else {
                // Outide Wall Has Different 'floor'
                if (piecePos.posX < 1) {
                    return woodenPlank;
                } else {
                    return new BlockWithMeta(Blocks.stonebrick, 5, getStairMeta(cellIndexDirection));
                }
            }
        }

        /* Upper Room */
        if (slope > 0 && piecePos.posX > 1 && piecePos.posZ > 2) {
            if (piecePos.posX == cellSize - 1 && piecePos.posZ == 3) {
                return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
            }

            if (piecePos.posZ == cellSize - 1 && piecePos.posX == 3 && slope == 1) {
                return new BlockWithMeta(woodenStair.block, getArchStairMeta(cellIndexDirection, false));
            }

            if (slope < 4 && (piecePos.posX == 2 || piecePos.posZ == 3)) {
                return new BlockWithMeta(Blocks.bookshelf);
            }
        }

        /* Arches */
        int topAarchSlope = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posZ + 0, 1,
                BoundaryPair.createPair(1, cellSize * 2), cellHeight - cellSize);
        int botAarchSlope = CellHelper.getSlopeIndex(piecePos, cellSize - piecePos.posZ + 1, 1,
                BoundaryPair.createPair(1, cellSize * 2), cellHeight - cellSize);
        if ((topAarchSlope == 0 || botAarchSlope == 0) && piecePos.posX % 3 == 1 && piecePos.posZ > cellSize * 4 / 10) {
            if (piecePos.posX > 1) {
                return new BlockWithMeta(woodenStair.block, getArchStairMeta(cellIndexDirection,
                        topAarchSlope == 0 ? true : false));
            }
        }

        /* Outer Wall */
        if (piecePos.posX == 1 && piecePos.posZ > cellSize * 4 / 10) {
            return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
        }

        /* Bottom Floor */
        if (piecePos.posY == 0 && piecePos.posX > 0 && piecePos.posZ > cellSize * 4 / 10) {
            if (piecePos.posZ == cellSize * 4 / 10 + 1) {
                return new BlockWithMeta(Blocks.cobblestone);
            } else {
                return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
            }
        }

        /* Pews */
        if (piecePos.posY == 1 && piecePos.posX > 1 && piecePos.posX % 2 == 0) {
            if (piecePos.posZ == cellSize * 4 / 10 + 2) {
                return new BlockWithMeta(Blocks.oak_stairs, getPewStairMeta(cellIndexDirection));
            } else if (piecePos.posZ > cellSize * 4 / 10 + 2) {
                return new BlockWithMeta(Blocks.wooden_slab);
            }
        }
        return new BlockWithMeta(Blocks.air);
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

    private int getArchStairMeta(CellIndexDirection cellIndexDirection, boolean top) {
        switch (cellIndexDirection) {
        case NorthWall:
        case SouthEastCorner:
            return top ? 2 : 7;
        case SouthWall:
        case NorthWestCorner:
            return top ? 3 : 6;
        case WestWall:
        case NorthEastCorner:
            return top ? 0 : 5;
        case EastWall:
        case SouthWestCorner:
            return top ? 1 : 4;
        default:
            return 0;
        }
    }

    private int getPewStairMeta(CellIndexDirection cellIndexDirection) {
        switch (cellIndexDirection) {
        case NorthWall:
        case SouthEastCorner:
            return 3;
        case SouthWall:
        case NorthWestCorner:
            return 2;
        case WestWall:
        case NorthEastCorner:
            return 1;
        case EastWall:
        case SouthWestCorner:
            return 0;
        default:
            return 0;
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