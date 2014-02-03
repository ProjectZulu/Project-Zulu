package projectzulu.common.world;

import net.minecraftforge.common.ForgeDirection;

public class BlockHelper {

    public int stairMeta(ForgeDirection direction, boolean upwards) {
        switch (direction) {
        case NORTH:
        default:
            return upwards ? 2 : 6;
        case SOUTH:
            return upwards ? 3 : 7;
        case EAST:
            return upwards ? 1 : 4;
        case WEST:
            return upwards ? 0 : 5;
        }
    }

}
