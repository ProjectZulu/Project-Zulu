package projectzulu.common.world2.blueprints.oasis;

import java.util.Random;

import net.minecraft.util.ChunkCoordinates;

public class BPOasisEdgeDiagonal extends BPOasisEdge {
    boolean inverted;

    public BPOasisEdgeDiagonal(boolean inverted) {
        super(inverted);
    }

    /**
     * BPOasisEdgeDiagonal G GG GGG
     */
    public boolean isGrass(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random) {
        if (piecePos.posZ - (!inverted ? piecePos.posX : cellSize - 1 - piecePos.posX) >= 0) {
            return true;
        }
        return false;
    }

    @Override
    public int getWeight() {
        return 1;
    }

    @Override
    public String childIdentifier() {
        return "diagonal";
    }
}
