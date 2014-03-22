package projectzulu.common.world.dataobjects;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class MobSpawnerWithMeta extends BlockWithMeta {

    String mobName;

    public MobSpawnerWithMeta(String mobName) {
        super(Blocks.mob_spawner);
        this.mobName = mobName;
    }

    @Override
    public void placeBlock(World world, ChunkCoordinates position, Random random) {
        /* Create Mob Spawner */
        world.setBlock(position.posX, position.posY, position.posZ, block);
        TileEntityMobSpawner tileEntityMobSpawner = (TileEntityMobSpawner) world.getTileEntity(position.posX,
                position.posY, position.posZ);

        if (tileEntityMobSpawner != null) {
            tileEntityMobSpawner.func_145881_a().setEntityName(mobName);
        }
    }

}
