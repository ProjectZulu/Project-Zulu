package projectzulu.common.world2.blueprint;

import java.util.Random;

import net.minecraft.init.Blocks;
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
                return new BlockWithMeta(Blocks.stonebrick, 0);
            }
        } else if (piecePos.posY == botYPos) {
            return new BlockWithMeta(Blocks.stone_brick_stairs, 3);
        } else if (piecePos.posY == topYPos) {
            return new BlockWithMeta(Blocks.stonebrick, 0);
        }

        if (piecePos.posY >= botYPos && piecePos.posY <= topYPos) {
            if (piecePos.posX == 0 || piecePos.posX == cellSize - 1) {
                return new BlockWithMeta(Blocks.stonebrick, 0);
            } else {
                return new BlockWithMeta(Blocks.air);
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
