package projectzulu.common.world.dataobjects;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import projectzulu.common.mobs.entity.EntityMimic;

public class MimicWithMeta extends BlockWithMeta {

    public MimicWithMeta() {
        super(Blocks.air);
    }

    @Override
    public void placeBlock(World world, ChunkCoordinates position, Random random) {
        if (!world.isRemote) {
            /* Place Air cause we don't want to spawn Inside something */
            world.setBlock(position.posX, position.posY, position.posZ, block);

            /* Spawn Mimic */
            EntityMimic entityMimic = new EntityMimic(world, position.posX + 0.5, position.posY, position.posZ + 0.5,
                    true);
            world.spawnEntityInWorld(entityMimic);
        }
    }
}
