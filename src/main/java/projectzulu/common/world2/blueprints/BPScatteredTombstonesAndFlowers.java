package projectzulu.common.world2.blueprints;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.api.BlockList;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world.dataobjects.HauntedArmorWithMeta;
import projectzulu.common.world2.blueprint.Blueprint;

public class BPScatteredTombstonesAndFlowers implements Blueprint {

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {

        if (piecePos.posY == 0) {
            return new BlockWithMeta(Block.grass.blockID);
        } else if (piecePos.posY == 1) {
            if (random.nextInt(90) == 0) {
                 return new HauntedArmorWithMeta();
            }

            if (piecePos.posX % 2 == 1 ^ piecePos.posZ % 2 == 1 && random.nextInt(100) <= 20) {
                return BlockList.tombstone.isPresent() ? new BlockWithMeta(BlockList.tombstone.get().blockID)
                        : new BlockWithMeta(0);
            } else if (5 - random.nextInt(100) >= 0) {
                return new BlockWithMeta(Block.plantRed.blockID, 0);
            } else if (5 - random.nextInt(100) >= 0) {
                return new BlockWithMeta(Block.plantYellow.blockID, 0);
            } else if (50 - random.nextInt(100) >= 0) {
                return new BlockWithMeta(Block.tallGrass.blockID, 1);
            }
        }
        return new BlockWithMeta(0);
    }

    @Override
    public int getWeight() {
        return 1;
    }

    @Override
    public String getIdentifier() {
        return "tombstonesandflowers";
    }
}