package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;

public class BlockQuickSand extends Block {
    public BlockQuickSand() {
        super(Material.sand);
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        setHardness(1.2F);
        setStepSound(Block.soundTypeSand);
    }

    @Override
    public boolean isOpaqueCube() {
        return true;
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
        par5Entity.setInWeb();
    }
}
