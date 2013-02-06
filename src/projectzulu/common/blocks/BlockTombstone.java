package projectzulu.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Blocks;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTombstone extends BlockContainer{

    private Class signEntityClass;
	
	public BlockTombstone(int par1, Class par2Class) {
		super(par1, Material.rock);
        this.setCreativeTab(ProjectZulu_Blocks.projectZuluCreativeTab);
        blockIndexInTexture = 52;
        signEntityClass = par2Class;
        
        float var4 = 0.4F;
        float var5 = 1.0F;
        this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var5, 0.5F + var4);
	}
	
	@SideOnly(Side.CLIENT)
	public String getTextureFile(){
            return DefaultProps.blockSpriteSheet;
    }
	
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4){
        return null;
    }
    
    /**
     * The type of render function that is called for this block
     */
    public int getRenderType(){
        return -1;
    }
    
    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock(){
        return false;
    }

    public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4){
        return true;
    }
    
    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube(){
        return false;
    }
    
    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World){
        try{
            return (TileEntity)this.signEntityClass.newInstance();
        }
        catch (Exception var3){
            throw new RuntimeException(var3);
        }
    }

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving) {
		int var6 = (Math.round(-par5EntityLiving.rotationYaw / 45f) + 4) & 7;
		
		par1World.setBlockMetadataWithNotify(par2, par3, par4, var6);
		
		
		if(par5EntityLiving instanceof EntityPlayer){
			((EntityPlayer)par5EntityLiving).openGui(ProjectZulu_Core.modInstance, 0, par1World, par2, par3, par4);
		} 
		
		super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLiving);
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		if(par5EntityPlayer instanceof EntityPlayer && ((EntityPlayer)par5EntityPlayer).inventory.getCurrentItem() != null
				&& isValidItemForEditing( ((EntityPlayer)par5EntityPlayer).inventory.getCurrentItem().getItem().itemID ) ){
			((EntityPlayer)par5EntityPlayer).openGui(ProjectZulu_Core.modInstance, 0, par1World, par2, par3, par4);
			return true;
		}
		return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
	}
	
	private boolean isValidItemForEditing(int itemID){		
		if(itemID == Item.pickaxeWood.itemID || itemID == Item.pickaxeStone.itemID || itemID == Item.pickaxeGold.itemID 
				|| itemID == Item.pickaxeSteel.itemID || itemID == Item.pickaxeDiamond.itemID){
			return true;
		}
		return false;
	}
}
