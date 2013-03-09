package projectzulu.common.blocks.heads;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMobHeads extends BlockContainer{

	public BlockMobHeads(int par1, int par2, Class class1){
		super(par1, Material.circuits);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	public int quantityDropped(Random par1Random){
		return 1;
	}
	// if you want your block to drop itself more then once, change it to an higher number.



	/* where and what to render */
	public int getRenderType(){
		return -1;
	} 

	/* make it opaque cube, or else you will be able to see trough the world ! */
	public boolean isOpaqueCube() {
		return false;
	} 

	/* offcourse, because it's not a 1x1x1 block */
	public boolean renderAsNormalBlock() {
		return false;
	} 


	/**
	 * Determines the damage on the item the block drops. Used in cloth and wood.
	 */
	public int damageDropped(int par1){
		return par1;
	}

	/**
	 * Drops the block items with a specified chance of dropping the specified items
	 */
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {}

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 7;

        switch (var5)
        {
            case 1:
            default:
                this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
                break;
            case 2:
                this.setBlockBounds(0.25F, 0.25F, 0.5F, 0.75F, 0.75F, 1.0F);
                break;
            case 3:
                this.setBlockBounds(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 0.5F);
                break;
            case 4:
                this.setBlockBounds(0.5F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
                break;
            case 5:
                this.setBlockBounds(0.0F, 0.25F, 0.25F, 0.5F, 0.75F, 0.75F);
        }
    }
	
	/**
	 * Called when the block is attempted to be harvested
	 */
    @Override
	public void onBlockHarvested(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer){
		if (par6EntityPlayer.capabilities.isCreativeMode){
			par5 |= 8;
			par1World.setBlockMetadataWithNotify(par2, par3, par4, par5, 3);
		}

		super.onBlockHarvested(par1World, par2, par3, par4, par5, par6EntityPlayer);
	}

	/**
	 * ejects contained items into the world, and notifies neighbours of an update, as appropriate
	 */
    @Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
	{
		if (!par1World.isRemote)
		{
			if ((par6 & 8) == 0)
			{
				this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(this.blockID, 1, this.getDamageValue(par1World, par2, par3, par4)));
			}

			super.breakBlock(par1World, par2, par3, par4, par5, par6);
		}
	}

	/**
	 * Called when the block is placed in the world.
	 */
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack){
		int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, var6, 3);
	}

	@Override
	public TileEntity createNewTileEntity(World var1){
		return new TileEntityMobHeads();

	}

	/**
	 * Get the block's damage value (for use with pick block).
	 */
	public int getDamageValue(World par1World, int par2, int par3, int par4){
		TileEntity var5 = par1World.getBlockTileEntity(par2, par3, par4);
		return var5 != null && var5 instanceof TileEntityMobHeads ? ((TileEntityMobHeads)var5).getSkullType() : super.getDamageValue(par1World, par2, par3, par4);
	}
}
