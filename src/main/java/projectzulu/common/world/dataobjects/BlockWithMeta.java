package projectzulu.common.world.dataobjects;

import java.util.Random;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.WeightedRandomItem;
import net.minecraft.world.World;

/* Helper Class to contain both BlockID and Meta in a single Structure */
public class BlockWithMeta extends WeightedRandomItem {
    public final int blockID;
    public final int meta;
    
    public BlockWithMeta(int blockID) {
        this(blockID, 0);
    }

    public BlockWithMeta(int blockID, int meta) {
        this(blockID, meta, 1);
    }

    public BlockWithMeta(int blockID, int meta, int weight) {
        super(weight);
        this.blockID = blockID;
        this.meta = meta;
    }

    /**
     * Helper Function to Place Block/TileEntity/Spawner. Overridden in child to make use of specific knowledge, i.e. A
     * Chests Loot Chance
     */
    public void placeBlock(World world, ChunkCoordinates position, Random random) {
        world.setBlock(position.posX, position.posY, position.posZ, blockID, meta, 3);
    }
}
