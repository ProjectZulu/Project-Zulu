package projectzulu.common.world.dataobjects;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;

/* Helper Class to contain both BlockID and Meta in a single Structure */
public class BlockWithMeta extends WeightedRandom.Item {
	public final String blockID;
	public final int meta;

	public BlockWithMeta(String blockID) {
		this(blockID, 0);
	}

	public BlockWithMeta(String blockID, int meta) {
		this(blockID, meta, 1);
	}

	public BlockWithMeta(String blockID, int meta, int weight) {
		super(weight);
		this.blockID = blockID;
		this.meta = meta;
	}

	/**
	 * Helper Function to Place Block/TileEntity/Spawner. Overridden in child to
	 * make use of specific knowledge, i.e. A Chests Loot Chance
	 */
	public void placeBlock(World world, ChunkCoordinates position, Random random) {
		Block block = Block.getBlockFromName(blockID);
		if (block != null) {
			world.setBlock(position.posX, position.posY, position.posZ, block,
					meta, 3);
		}
	}
}
