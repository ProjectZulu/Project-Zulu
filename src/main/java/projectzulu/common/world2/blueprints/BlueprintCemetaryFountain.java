package projectzulu.common.world2.blueprints;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.blueprint.Blueprint;

public class BlueprintCemetaryFountain implements Blueprint {

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        if (piecePos.posY == 0) {
            return new BlockWithMeta(Blocks.grass);
        }

        if (piecePos.posY == 1) {
            if (cellIndexDirection == CellIndexDirection.NorthMiddle
                    || cellIndexDirection == CellIndexDirection.SouthMiddle) {
                boolean isNorth = cellIndexDirection == CellIndexDirection.NorthMiddle;
                if (piecePos.posZ == (isNorth ? 0 : cellSize - 1)) {
                    if (piecePos.posX == 0 || piecePos.posX == cellSize - 1) {
                        return new BlockWithMeta(Blocks.stone_brick_stairs, isNorth ? 2 : 3);
                    } else if (piecePos.posX == cellSize / 2) {
                        return new BlockWithMeta(Blocks.stone_slab, 5);
                    }
                }
            } else {
                boolean isWest = cellIndexDirection == CellIndexDirection.WestMiddle;
                if (piecePos.posX == (isWest ? 0 : cellSize - 1)) {
                    if (piecePos.posZ == 0 || piecePos.posZ == cellSize - 1) {
                        return new BlockWithMeta(Blocks.stone_brick_stairs, isWest ? 0 : 1);
                    } else if (piecePos.posZ == cellSize / 2) {
                        return new BlockWithMeta(Blocks.stone_slab, 5);
                    }
                }
            }

            if (piecePos.posX > 0 && piecePos.posX < cellSize - 1 && piecePos.posZ > 0 && piecePos.posZ < cellSize - 1) {
                return new BlockWithMeta(Blocks.water);
            } else {
                return new BlockWithMeta(Blocks.stonebrick, 0);
            }
        }

        if (piecePos.posY == 2) {
            if (cellIndexDirection == CellIndexDirection.NorthMiddle
                    || cellIndexDirection == CellIndexDirection.SouthMiddle) {
                int zStart = cellIndexDirection == CellIndexDirection.NorthMiddle ? 0 : cellSize - 1;
                int zEnd = cellIndexDirection == CellIndexDirection.NorthMiddle ? cellSize - 1 : 0;
                if (piecePos.posZ == zEnd) {
                    if (piecePos.posX == 0) {
                        return new BlockWithMeta(Blocks.stone_brick_stairs, 0);
                    } else if (piecePos.posX == cellSize - 1) {
                        return new BlockWithMeta(Blocks.stone_brick_stairs, 1);
                    } else {
                        return new BlockWithMeta(Blocks.stonebrick, 0);
                    }
                }

                if ((piecePos.posX == 0 || piecePos.posX == cellSize - 1) && piecePos.posZ != zStart) {
                    return new BlockWithMeta(Blocks.stonebrick, 0);
                }
            } else {
                int xStart = cellIndexDirection == CellIndexDirection.WestMiddle ? 0 : cellSize - 1;
                int xEnd = cellIndexDirection == CellIndexDirection.WestMiddle ? cellSize - 1 : 0;
                if (piecePos.posX == xEnd) {
                    if (piecePos.posZ == 0) {
                        return new BlockWithMeta(Blocks.stone_brick_stairs, 2);
                    } else if (piecePos.posZ == cellSize - 1) {
                        return new BlockWithMeta(Blocks.stone_brick_stairs, 3);
                    } else {
                        return new BlockWithMeta(Blocks.stonebrick, 0);
                    }
                }

                if ((piecePos.posZ == 0 || piecePos.posZ == cellSize - 1) && piecePos.posX != xStart) {
                    return new BlockWithMeta(Blocks.stonebrick, 0);
                }
            }
        }

        if (piecePos.posY == 3) {
            if (piecePos.posX != 0 && piecePos.posX != cellSize - 1 && piecePos.posZ != 0
                    && piecePos.posZ != cellSize - 1) {
                return new BlockWithMeta(Blocks.stone_slab, 5);
            } else {
                return new BlockWithMeta(Blocks.air);
            }
        }
        return new BlockWithMeta(Blocks.air);
    }

    @Override
    public int getWeight() {
        return 1;
    }

    @Override
    public String getIdentifier() {
        return "CemetaryFountain";
    }
}