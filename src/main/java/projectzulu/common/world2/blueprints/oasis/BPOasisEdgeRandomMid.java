package projectzulu.common.world2.blueprints.oasis;

import java.util.Random;

import net.minecraft.util.ChunkCoordinates;

public class BPOasisEdgeRandomMid extends BPOasisEdge {
    boolean inverted;

    public BPOasisEdgeRandomMid(boolean inverted) {
        super(inverted);
    }

    public boolean isGrass(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random) {
        if (inverted) {
            if (piecePos.posZ > 1 || piecePos.posZ == 1 && random.nextInt(piecePos.posX + 1) == 0) {
                return true;
            }
        } else {
            if (piecePos.posZ > 1 || piecePos.posZ == 1 && random.nextInt(cellSize - 1 - piecePos.posX + 1) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getWeight() {
        return 1;
    }

    @Override
    public String childIdentifier() {
        return "randomMid";
    }
}
