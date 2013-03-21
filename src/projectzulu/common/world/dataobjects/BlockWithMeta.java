package projectzulu.common.world.dataobjects;

import java.util.Random;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;


/* Helper Class to contain both BlockID and Meta in a single Structure
 * 13/11/2012: Created so that both BlockID and Meta could be passed as varArg in BuildingManager */
public class BlockWithMeta {
	public int blockID;
	public int meta;
	
	public BlockWithMeta(int blockID){
		this(blockID, 0);
	}
	public BlockWithMeta(int blockID, int meta){
		this.blockID = blockID;
		this.meta = meta;
	}	
	
	/**
	 * Helper Function to Place Block/TileEntity/Spawner. Overriden in Child to make use of specific knowleadge, i.e. A Chests Loot Chance
	 */
	public void placeBlock(World world, ChunkCoordinates position, Random random){
		world.setBlock(position.posX, position.posY, position.posZ, blockID, meta, 3);
	}
}
