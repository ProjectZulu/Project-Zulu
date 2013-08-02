package projectzulu.common.world2.blueprints.oasis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.WeightedRandom;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.CellHelper;
import projectzulu.common.world2.blueprint.Blueprint;

public abstract class BPOasisEdge implements Blueprint {

    BlockWithMeta sandstone = new BlockWithMeta(Block.sandStone.blockID);
    BlockWithMeta sand = new BlockWithMeta(Block.sand.blockID);
    BlockWithMeta grass = new BlockWithMeta(Block.grass.blockID);
    BlockWithMeta air = new BlockWithMeta(0);
    
    List<BlockWithMeta> flowers = new ArrayList<>();
    boolean inverted;

    public BPOasisEdge(boolean inverted) {
        this.inverted = inverted;
        flowers.add(new BlockWithMeta(Block.plantRed.blockID, 0, 1));
        flowers.add(new BlockWithMeta(Block.plantYellow.blockID, 0, 1));
        flowers.add(new BlockWithMeta(Block.tallGrass.blockID, 1, 10));
        flowers.add(new BlockWithMeta(0, 0, 6));
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        if (piecePos.posY == 0) {
            return sandstone;
        } else if (piecePos.posY == 1) {
            return sand;
        } else if (piecePos.posY == 2) {
            if (isGrass(CellHelper.rotateCellTo(piecePos, cellSize, cellIndexDirection), cellSize, cellHeight, random)) {
                return grass;
            } else {
                return sand;
            }
        } else if (piecePos.posY == 3) {
            if (isGrass(CellHelper.rotateCellTo(piecePos, cellSize, cellIndexDirection), cellSize, cellHeight, random)) {
                return (BlockWithMeta) WeightedRandom.getRandomItem(random, flowers);
            } else {
                return air;
            }
        }
        return air;
    }

    public abstract boolean isGrass(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random);

    @Override
    public final String getIdentifier() {
        return "OasisEdge".concat(Boolean.toString(inverted)).concat(childIdentifier());
    }

    public abstract String childIdentifier();
}