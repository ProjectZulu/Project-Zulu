package projectzulu.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;

public class BlockPalmTreeLog extends Block {

    public BlockPalmTreeLog() {
        super(Material.wood);
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        setHardness(2.0F);
        setStepSound(Block.soundTypeWood);
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType() {
        return 0;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random par1Random) {
        return 1;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    @Override
    public int damageDropped(int par1) {
        return par1;
    }

    /**
     * ejects contained items into the world, and notifies neighbors of an update, as appropriate
     */
    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        byte var7 = 4;
        int var8 = var7 + 1;

        if (par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8)) {
            for (int var9 = -var7; var9 <= var7; ++var9) {
                for (int var10 = -var7; var10 <= var7; ++var10) {
                    for (int var11 = -var7; var11 <= var7; ++var11) {
                        Block var12 = par1World.getBlock(par2 + var9, par3 + var10, par4 + var11);

                        if (var12 != null) {
                            var12.beginLeavesDecay(par1World, par2 + var9, par3 + var10, par4 + var11);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @Override
    public boolean isWood(IBlockAccess world, int x, int y, int z) {
        return true;
    }
}
