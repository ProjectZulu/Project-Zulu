package projectzulu.common.blocks.universalpot;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.GuiID;

public class BlockUniversalFlowerPot extends BlockContainer {

    public final int renderID;

    public BlockUniversalFlowerPot(int renderID) {
        super(Material.wood);
        this.setBlockBoundsForItemRender();
        float var1 = 0.375F;
        float var2 = var1 / 2.0F;
        this.setBlockBounds(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, var1, 0.5F + var2);
        setHardness(0.0F);
        setStepSound(Block.soundTypeStone);
        this.renderID = renderID;
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType() {
        return renderID;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube? This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    @Override
    public void setBlockBoundsForItemRender() {
        float var1 = 0.375F;
        float var2 = var1 / 2.0F;
        this.setBlockBounds(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, var1, 0.5F + var2);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityUniversalFlowerPot();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float what,
            float these, float are) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity == null || player.isSneaking()) {
            return false;
        }
        player.openGui(ProjectZulu_Core.modInstance, GuiID.FlowerPot.getID(), world, x, y, z);
        return true;
    }
}
