package projectzulu.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import projectzulu.common.mod_ProjectZulu;

public class BlockUniversalFlowerPot extends BlockContainer{
	
	protected BlockUniversalFlowerPot(int par1) {
		super(par1, Material.wood);
        this.blockIndexInTexture = 186;
        this.setBlockBoundsForItemRender();
//        this.setCreativeTab(ProjectZulu_Blocks.projectZuluCreativeTab);

        float var1 = 0.375F;
        float var2 = var1 / 2.0F;
        this.setBlockBounds(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, var1, 0.5F + var2);
	}
	
	@Override
	public String getTextureFile() {
//		return "/gui/items.png";
		return super.getTextureFile();
	}
	
    /**
     * The type of render function that is called for this block
     */
    public int getRenderType(){
        return mod_ProjectZulu.universalFlowerPotRenderID;
    }
	
    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube(){
        return false;
    }
	
    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock(){
        return false;
    }
	
    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender(){
        float var1 = 0.375F;
        float var2 = var1 / 2.0F;
        this.setBlockBounds(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, var1, 0.5F + var2);
    }
	
	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityUniversalFlowerPot();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int idk, float what, float these, float are) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}
		//opens gui, to be implemented later
		player.openGui(mod_ProjectZulu.modInstance, 1, world, x, y, z);
		return true;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		super.breakBlock(world, x, y, z, par5, par6);
	}
}
