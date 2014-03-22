package projectzulu.common.world2.blueprint;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;

public class BlueprintLabyrinthCobweb implements Blueprint {

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        if ((piecePos.posY == 0 || piecePos.posY == cellHeight - 1)) {
            return new BlockWithMeta(Blocks.stonebrick, 0);
        } else if ((piecePos.posY == 1 || piecePos.posY == cellHeight - 2) && 10 - random.nextInt(100) >= 0) {
            return new BlockWithMeta(Blocks.web, 0);
        } else {
            return new BlockWithMeta(Blocks.air);
        }
    }

    @Override
    public int getWeight() {
        return 1;
    }

    @Override
    public String getIdentifier() {
        return "labyrinthcobweb";
    }
}
