package projectzulu.common.world.dataobjects;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;

/* Helper Class to contain both BlockID and Meta in a single Structure */
public class BlockWithMeta extends WeightedRandom.Item {
    public final Block block;
    public final int meta;

    public BlockWithMeta(Block block) {
        this(block, 0);
    }

    public BlockWithMeta(Block block, int meta) {
        this(block, meta, 1);
    }

    public BlockWithMeta(Block block, int meta, int weight) {
        super(weight);
        this.block = block;
        this.meta = meta;
    }

    /**
     * Helper Function to Place Block/TileEntity/Spawner. Overridden in child to make use of specific knowledge, i.e. A
     * Chests Loot Chance
     */
    public void placeBlock(World world, ChunkCoordinates position, Random random) {
        if (block != null) {
            world.setBlock(position.posX, position.posY, position.posZ, block, meta, 3);
        }
    }
}
