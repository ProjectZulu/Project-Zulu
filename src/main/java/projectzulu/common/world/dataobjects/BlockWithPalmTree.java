package projectzulu.common.world.dataobjects;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.BlockPalmTreeSapling;

public class BlockWithPalmTree extends BlockWithMeta {

    public BlockWithPalmTree() {
        super(Blocks.air, 0, 1);
    }

    @Override
    public void placeBlock(World world, ChunkCoordinates position, Random random) {
        if (BlockList.palmTreeSapling.isPresent()) {
            ((BlockPalmTreeSapling) BlockList.palmTreeSapling.get()).growTree(world, position.posX, position.posY,
                    position.posZ, random);
        }
    }
}