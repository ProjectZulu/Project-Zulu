package projectzulu.common.world2.blueprints;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.blueprint.Blueprint;

public class BlueprintCemetaryFountain2 implements Blueprint {

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        if (piecePos.posY == 0) {
            return new BlockWithMeta("grass");
        }

        if (piecePos.posY == 1) {
            if (piecePos.posX == 0) {
                return new BlockWithMeta(Block.stairsStoneBrick.blockID, 0);
            } else if (piecePos.posX == cellSize - 1) {
                return new BlockWithMeta(Block.stairsStoneBrick.blockID, 1);
            } else if (piecePos.posZ == 0) {
                return new BlockWithMeta(Block.stairsStoneBrick.blockID, 2);
            } else if (piecePos.posZ == cellSize - 1) {
                return new BlockWithMeta(Block.stairsStoneBrick.blockID, 3);
            } else {
                return new BlockWithMeta(Block.waterStill.blockID);
            }
        }
        return new BlockWithMeta("air");
    }

    @Override
    public int getWeight() {
        return 2;
    }

    @Override
    public String getIdentifier() {
        return "CemetaryFountain2";
    }
}