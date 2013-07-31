package projectzulu.common.world2.blueprints.oasis;

import java.util.Random;

import net.minecraft.util.ChunkCoordinates;

public class BPOasisEdgeRandomPoint extends BPOasisEdge {
    boolean inverted;

    public BPOasisEdgeRandomPoint() {
        super(false);
    }

    public boolean isGrass(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random) {
        if (piecePos.posZ > 0 || random.nextInt(3) == 0) {
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
        return "randompoint";
    }
}
