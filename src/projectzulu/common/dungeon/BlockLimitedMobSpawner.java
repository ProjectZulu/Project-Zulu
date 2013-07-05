package projectzulu.common.dungeon;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.GuiID;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLimitedMobSpawner extends BlockContainer {
	
    public BlockLimitedMobSpawner(int blockID){
        super(blockID, Material.rock);
        setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
    }
    
    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World){
        return new TileEntityLimitedMobSpawner();
    }
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3){
        return 0;
    }
    
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random){
        return 0;
    }
    
    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7){
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);
        int var8 = 15 + par1World.rand.nextInt(15) + par1World.rand.nextInt(15);
        this.dropXpOnBlockBreak(par1World, par2, par3, par4, var8);
    }
    
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9) {
		
		if(player instanceof EntityPlayer && ((EntityPlayer)player).capabilities.isCreativeMode ){
			((EntityPlayer)player).openGui(ProjectZulu_Core.modInstance, GuiID.MobSpawner.getID(), par1World, par2, par3, par4);
			return true;
		}
		return super.onBlockActivated(par1World, par2, par3, par4, player, par6, par7, par8, par9);
	}
	
    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube(){
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4){
        return 0;
    }
}
