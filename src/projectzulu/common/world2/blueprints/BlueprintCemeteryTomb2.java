package projectzulu.common.world2.blueprints;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world.dataobjects.ChestWithMeta;
import projectzulu.common.world2.blueprint.Blueprint;

public class BlueprintCemeteryTomb2 implements Blueprint {

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        if (piecePos.posY == 0) {
            return new BlockWithMeta(Block.grass.blockID);
        }

        if (piecePos.posY == 1 && piecePos.posX == cellSize / 2 && piecePos.posZ == cellSize / 2) {
            return new ChestWithMeta(Block.chest.blockID, 1, new TileEntityChest(), 15);
        }

        if (cellIndexDirection == CellIndexDirection.NorthMiddle && piecePos.posX == cellSize / 2 && piecePos.posZ == 0) {
            if (piecePos.posY == 1) {
                return new BlockWithMeta(Block.doorIron.blockID, 3);
            } else if (piecePos.posY == 2) {
                return new BlockWithMeta(Block.doorIron.blockID, 11);
            }
        } else if (cellIndexDirection == CellIndexDirection.SouthMiddle && piecePos.posX == cellSize / 2
                && piecePos.posZ == cellSize - 1) {
            if (piecePos.posY == 1) {
                return new BlockWithMeta(Block.doorIron.blockID, 1);
            } else if (piecePos.posY == 2) {
                return new BlockWithMeta(Block.doorIron.blockID, 9);
            }
        } else if (cellIndexDirection == CellIndexDirection.WestMiddle && piecePos.posX == 0
                && piecePos.posZ == cellSize / 2) {
            if (piecePos.posY == 1) {
                return new BlockWithMeta(Block.doorIron.blockID, 2);
            } else if (piecePos.posY == 2) {
                return new BlockWithMeta(Block.doorIron.blockID, 10);
            }
        } else if (cellIndexDirection == CellIndexDirection.EastMiddle && piecePos.posX == cellSize - 1
                && piecePos.posZ == cellSize / 2) {
            if (piecePos.posY == 1) {
                return new BlockWithMeta(Block.doorIron.blockID, 0);
            } else if (piecePos.posY == 2) {
                return new BlockWithMeta(Block.doorIron.blockID, 8);
            }
        }

        if (piecePos.posY < cellHeight) {
            if (piecePos.posY == cellHeight - 1) {
                if (piecePos.posX == cellSize / 2 && piecePos.posZ == cellSize / 2) {
                    return new BlockWithMeta(Block.stoneSingleSlab.blockID, 5);
                }
            } else if (piecePos.posY == cellHeight - 2) {
                if (piecePos.posX == 0) {
                    return new BlockWithMeta(Block.stairsStoneBrick.blockID, 0);
                } else if (piecePos.posX == cellSize - 1) {
                    return new BlockWithMeta(Block.stairsStoneBrick.blockID, 1);
                } else if (piecePos.posZ == 0) {
                    return new BlockWithMeta(Block.stairsStoneBrick.blockID, 2);
                } else if (piecePos.posZ == cellSize - 1) {
                    return new BlockWithMeta(Block.stairsStoneBrick.blockID, 3);
                }
            } else if (piecePos.posY % 2 == 1) {
                if (isCorner(piecePos, cellSize)) {
                    return new BlockWithMeta(Block.stoneBrick.blockID, 0);
                } else if (piecePos.posX == 0) {
                    return new BlockWithMeta(Block.stairsStoneBrick.blockID, 0);
                } else if (piecePos.posX == cellSize - 1) {
                    return new BlockWithMeta(Block.stairsStoneBrick.blockID, 1);
                } else if (piecePos.posZ == 0) {
                    return new BlockWithMeta(Block.stairsStoneBrick.blockID, 2);
                } else if (piecePos.posZ == cellSize - 1) {
                    return new BlockWithMeta(Block.stairsStoneBrick.blockID, 3);
                }
            } else {
                if (isCorner(piecePos, cellSize)) {
                    return new BlockWithMeta(Block.stoneBrick.blockID, 0);
                } else if (piecePos.posX == 0) {
                    return new BlockWithMeta(Block.stairsStoneBrick.blockID, 0 + 4);
                } else if (piecePos.posX == cellSize - 1) {
                    return new BlockWithMeta(Block.stairsStoneBrick.blockID, 1 + 4);
                } else if (piecePos.posZ == 0) {
                    return new BlockWithMeta(Block.stairsStoneBrick.blockID, 2 + 4);
                } else if (piecePos.posZ == cellSize - 1) {
                    return new BlockWithMeta(Block.stairsStoneBrick.blockID, 3);
                }
            }
        }
        return new BlockWithMeta(0);
    }

    private boolean isCorner(ChunkCoordinates piecePos, int cellSize) {
        if ((piecePos.posX == 0 || piecePos.posX == cellSize - 1)
                && (piecePos.posZ == 0 || piecePos.posZ == cellSize - 1)) {
            return true;
        }
        return false;
    }

    @Override
    public int getWeight() {
        return 4;
    }

    @Override
    public String getIdentifier() {
        return "cemeterytomb2";
    }
}