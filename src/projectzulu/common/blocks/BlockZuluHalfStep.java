package projectzulu.common.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import projectzulu.common.core.ItemBlockList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockZuluHalfStep extends BlockZuluHalfSlab {
    /** The type of tree this slab came from. */
    //public static final String[] woodType = new String[] {"oak", "spruce", "birch", "jungle"};
    public static final String[] woodType = new String[] {"oak2"};
    
    public BlockZuluHalfStep(int par1, boolean par2) {
        super(par1, par2, Material.wood);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
    
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
        	return 18;
    }
    
    @SideOnly(Side.CLIENT)
	public String getTextureFile() {
            return "/mods/blocks_projectzulu.png";
    }


    /**
     * Returns the block texture based on the side being looked at.  Args: side
     */
    public int getBlockTextureFromSide(int par1) {
        return this.getBlockTextureFromSideAndMetadata(par1, 0);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3){
    	if(ItemBlockList.palmTreeSlab.isPresent()) {
    		return ItemBlockList.palmTreeSlab.get().blockID;
    	}else{
    		return 0;
    	}
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int par1){
    	return new ItemStack(ItemBlockList.palmTreeSlab.get().blockID, 2, par1 & 7);
    }

    /**
     * Returns the slab block name with step type.
     */
    public String getFullSlabName(int par1) {
        if (par1 < 0 || par1 >= woodType.length)
        {
            par1 = 0;
        }

        return super.getBlockName() + "." + woodType[par1];
    }

//    @SideOnly(Side.CLIENT)
//
//    /**
//     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
//     */
//    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
//    {
//        if (par1 != Block.woodDoubleSlab.blockID)
//        {
//            for (int var4 = 0; var4 < 4; ++var4)
//            {
//                par3List.add(new ItemStack(par1, 1, var4));
//            }
//        }
//    }
}
