package projectzulu.common.world;

import java.util.Random;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class TileEntityWithMeta extends BlockWithMeta{
	
	public TileEntity tileEntity;
	
	public TileEntityWithMeta(int blockID, int meta, TileEntity tileEntity) {
		super(blockID, meta);
		this.tileEntity = tileEntity;
	}

	public TileEntityWithMeta(int blockID, TileEntity tileEntity) {
		this(blockID, 0, tileEntity);
	}
	
	@Override
	public void placeBlock(World world, ChunkCoordinates position, Random random) {
		world.setBlockAndMetadata(position.posX, position.posY,position.posZ, blockID, meta);
		world.setBlockTileEntity(position.posX, position.posY,position.posZ, tileEntity);
	}
}
