package projectzulu.common.world2.blueprints.oasis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.WeightedRandom;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.blueprint.Blueprint;

public class BPOasisGrass implements Blueprint {

    BlockWithMeta sandstone = new BlockWithMeta(Block.sandStone.blockID);
    BlockWithMeta sand = new BlockWithMeta(Block.sand.blockID);
    BlockWithMeta grass = new BlockWithMeta(Block.grass.blockID);
    BlockWithMeta air = new BlockWithMeta(0);
    List<BlockWithMeta> flowers = new ArrayList<>();

    public BPOasisGrass() {
        flowers.add(new BlockWithMeta(Block.plantRed.blockID, 0, 1));
        flowers.add(new BlockWithMeta(Block.plantYellow.blockID, 0, 1));
        flowers.add(new BlockWithMeta(Block.tallGrass.blockID, 1, 20));
        flowers.add(new BlockWithMeta(0, 0, 4));
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        if (piecePos.posY == 0) {
            return sandstone;
        } else if (piecePos.posY == 1) {
            return sand;
        } else if (piecePos.posY == 2) {
            return grass;
        } else if (piecePos.posY == 3) {
            return (BlockWithMeta) WeightedRandom.getRandomItem(random, flowers);
        }
        return air;
    }

    @Override
    public int getWeight() {
        return 100;
    }

    @Override
    public String getIdentifier() {
        return "OasisGrass";
    }
}
