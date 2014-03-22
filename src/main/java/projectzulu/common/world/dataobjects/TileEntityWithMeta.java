package projectzulu.common.world.dataobjects;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class TileEntityWithMeta extends BlockWithMeta {

    public TileEntity tileEntity;

    public TileEntityWithMeta(Block block, int meta, TileEntity tileEntity) {
        super(block, meta);
        this.tileEntity = tileEntity;
    }

    public TileEntityWithMeta(Block block, TileEntity tileEntity) {
        this(block, 0, tileEntity);
    }

    @Override
    public void placeBlock(World world, ChunkCoordinates position, Random random) {
        world.setBlock(position.posX, position.posY, position.posZ, block, meta, 3);
        world.setTileEntity(position.posX, position.posY, position.posZ, tileEntity);
    }
}
