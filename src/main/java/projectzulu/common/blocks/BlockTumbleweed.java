package projectzulu.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTumbleweed extends Block {

    public BlockTumbleweed() {
        super(Material.plants);
        setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        setHardness(0.7F);
        setStepSound(Block.soundTypeWood);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
        super.onBlockAdded(par1World, par2, par3, par4);
    }
}
