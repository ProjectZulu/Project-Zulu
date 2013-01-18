package projectzulu.common.world.blockdataobjects;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class MobSpawnerWithMeta extends BlockWithMeta{
	
	TileEntityMobSpawner tileEntityMobSpawner;
	String mobName;
	public MobSpawnerWithMeta(String mobName) {
		super(Block.mobSpawner.blockID);
		this.mobName = mobName;
	}
	
	@Override
	public void placeBlock(World world, ChunkCoordinates position, Random random) {
		/* Create Mob Spawner */
		world.setBlockWithNotify( 
				position.posX,
				position.posY,
				position.posZ,
				blockID);
		TileEntityMobSpawner tileEntityMobSpawner = (TileEntityMobSpawner)world.getBlockTileEntity(
				position.posX,
				position.posY,
				position.posZ);

//		if (tileEntityMobSpawner != null){
			tileEntityMobSpawner.setMobID( mobName);
//		}
	}

}
