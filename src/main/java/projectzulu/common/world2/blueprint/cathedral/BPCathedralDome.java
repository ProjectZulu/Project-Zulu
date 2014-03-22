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

public class BPCathedralDome implements Blueprint {
    List<BlockWithMeta> wallBlocks = new ArrayList<BlockWithMeta>(3);

    public BPCathedralDome() {
        wallBlocks.add(new BlockWithMeta(Blocks.stonebrick, 2, 5));
        wallBlocks.add(new BlockWithMeta(Blocks.stonebrick, 1, 10));
        wallBlocks.add(new BlockWithMeta(Blocks.stonebrick, 0, 100));
    }

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
        return new BlockWithMeta(Blocks.air);
    }

    private BlockWithMeta getDomeBlock(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        int diagonalIndex = piecePos.posZ + piecePos.posX;
        int domeFloor = cellHeight - (cellSize + 1);
        BlockWithMeta woodenPlank = new BlockWithMeta(Blocks.planks, 1);
        if (piecePos.posY > domeFloor) {
            int slope = CellHelper.getSlopeIndex(piecePos, 2 * cellSize - diagonalIndex - 1, 2,
                    BoundaryPair.createPair(cellSize - 4, cellSize * 2), cellHeight);
            if (slope == 0) {
                return woodenPlank;
            } else if (slope > 0) {
                return new BlockWithMeta(Blocks.air);
            }

            if (piecePos.posX == 0) {
                return piecePos.posZ % 2 == 0 ? (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks) : new BlockWithMeta(Blocks.air);
            }

            if (piecePos.posZ == 0) {
                return piecePos.posX % 2 == 0 ? (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks) : new BlockWithMeta(Blocks.air);
            }
        }

        if (piecePos.posY == domeFloor && diagonalIndex > 2 && diagonalIndex < cellSize * 2 - 3) {
            return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
        } else if (piecePos.posY == domeFloor + 1 && diagonalIndex == cellSize * 2 - 4) {
            return new BlockWithMeta(Blocks.iron_bars);
        } else if (piecePos.posY == domeFloor + 1 && diagonalIndex == cellSize * 2 - 5 && piecePos.posX != cellSize - 1
                && piecePos.posZ != cellSize - 1) {
            return new BlockWithMeta(Blocks.iron_bars);
        }

        if (diagonalIndex == 2) {
            return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
        }

        if (piecePos.posY == 0 && diagonalIndex > 2) {
            if (diagonalIndex == 3) {
                return new BlockWithMeta(Blocks.cobblestone);
            } else {
                return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
            }
        }

        if (diagonalIndex > 6) {
            if (piecePos.posY == 1) {
                return (BlockWithMeta) WeightedRandom.getRandomItem(random, wallBlocks);
            } else if (piecePos.posY == 2) {
                if (cellIndexDirection == CellIndexDirection.SouthEastCorner) {
                    if (piecePos.posZ >= cellSize - 3 && piecePos.posX == cellSize - 2) {
                        return new BlockWithMeta(Blocks.stone_brick_stairs, 1);
                    } else if (piecePos.posZ == cellSize - 3 && piecePos.posX > cellSize - 2) {
                        return new BlockWithMeta(Blocks.stone_brick_stairs, 3);
                    }
                } else if (cellIndexDirection == CellIndexDirection.SouthWestCorner) {
                    if (piecePos.posX >= cellSize - 3 && piecePos.posZ == cellSize - 2) {
                        return new BlockWithMeta(Blocks.stone_brick_stairs, 0);
                    } else if (piecePos.posX == cellSize - 3 && piecePos.posZ > cellSize - 2) {
                        return new BlockWithMeta(Blocks.stone_brick_stairs, 3);
                    }
                }
                return new BlockWithMeta(Blocks.stone_slab, 5);
            } else if (piecePos.posY == 3) {
                if (cellIndexDirection == CellIndexDirection.SouthEastCorner) {
                    if (piecePos.posZ == cellSize - 2 && piecePos.posX > cellSize - 2) {
                        return new BlockWithMeta(Blocks.stone_slab, 5);
                    }
                } else if (cellIndexDirection == CellIndexDirection.SouthWestCorner) {
                    if (piecePos.posX == cellSize - 2 && piecePos.posZ > cellSize - 2) {
                        return new BlockWithMeta(Blocks.stone_slab, 5);
                    }
                }
            }
        }

        if (piecePos.posY == 1) {
            if (diagonalIndex == 4 + 2 && piecePos.posZ == cellSize - 1
                    && cellIndexDirection == CellIndexDirection.NorthEastCorner || diagonalIndex == 4 + 2
                    && piecePos.posX == cellSize - 1 && cellIndexDirection == CellIndexDirection.NorthWestCorner) {
                return new BlockWithMeta(Blocks.stone_brick_stairs, 2);
            } else if (diagonalIndex > 4) {
                return new BlockWithMeta(Blocks.carpet, 14);
            }
        }
        return new BlockWithMeta(Blocks.air);
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
