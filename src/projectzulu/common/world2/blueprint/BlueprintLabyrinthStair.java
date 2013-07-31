package projectzulu.common.world2.blueprint;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;

public class BlueprintLabyrinthStair implements Blueprint {

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        int index = 0;
        int botYPos = (cellSize - 1) - piecePos.posZ + index * cellSize;
        int topYPos = (cellSize - 1) - piecePos.posZ + (index + 1) * cellSize;
        if (piecePos.posX == 0 || piecePos.posX == cellSize - 1) {
            if (piecePos.posY >= botYPos && piecePos.posY <= topYPos) {
                return new BlockWithMeta(Block.stoneBrick.blockID, 0);
            }
        } else if (piecePos.posY == botYPos) {
            return new BlockWithMeta(Block.stairsStoneBrick.blockID, 3);
        } else if (piecePos.posY == topYPos) {
            return new BlockWithMeta(Block.stoneBrick.blockID, 0);
        }

        if (piecePos.posY >= botYPos && piecePos.posY <= topYPos) {
            if (piecePos.posX == 0 || piecePos.posX == cellSize - 1) {
                return new BlockWithMeta(Block.stoneBrick.blockID, 0);
            } else {
                return new BlockWithMeta(0);
            }
        } else {
            return null;
        }
    }

    @Override
    public int getWeight() {
        return 0;
    }

    @Override
    public String getIdentifier() {
        return "labyrinthentrancestair";
    }
}
