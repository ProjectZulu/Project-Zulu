package projectzulu.common.world2.blueprints.oasis;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.blueprint.Blueprint;

public class BPOasisPool implements Blueprint {

    BlockWithMeta sandstone = new BlockWithMeta(Blocks.sandstone);
    BlockWithMeta sand = new BlockWithMeta(Blocks.sand);
    BlockWithMeta water = new BlockWithMeta(Blocks.water);
    BlockWithMeta air = new BlockWithMeta(Blocks.air);

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        if (piecePos.posY == 0) {
            return sandstone;
        } else if (piecePos.posY == 1) {
            return sand;
        } else if (piecePos.posY == 2) {
            return water;
        }
        return air;
    }

    @Override
    public int getWeight() {
        return 100;
    }

    @Override
    public String getIdentifier() {
        return "OasisPool";
    }
}
