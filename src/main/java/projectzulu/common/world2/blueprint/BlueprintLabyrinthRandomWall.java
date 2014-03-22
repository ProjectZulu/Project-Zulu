package projectzulu.common.world2.blueprint;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;

public class BlueprintLabyrinthRandomWall implements Blueprint {

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {

        if ((piecePos.posY == 0)) {// || piecePos.posY == cellHeight - 1)) {
         return new BlockWithMeta(Blocks.stonebrick, 0);
        } else if (10 - random.nextInt(100) >= 0) {
            return new BlockWithMeta(Blocks.stonebrick, 2);
        } else if (10 - random.nextInt(100) >= 0) {
            return new BlockWithMeta(Blocks.stonebrick, 1);
        } else if (5 - random.nextInt(100) >= 0) {
            return new BlockWithMeta(Blocks.air);
        } else {
            return new BlockWithMeta(Blocks.stonebrick, 0);
        }
    }

    @Override
    public int getWeight() {
        return 4;
    }

    @Override
    public String getIdentifier() {
        return "labyrinthrandomwall";
    }
}