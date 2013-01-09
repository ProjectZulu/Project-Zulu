package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Blocks;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockQuickSand extends Block
{
	private double sinkHeight = 0.0;
    public BlockQuickSand(int par1, int par2){
        super(par1, par2, Material.sand);
        this.setCreativeTab(ProjectZulu_Blocks.projectZuluCreativeTab);
    }
    
	@SideOnly(Side.CLIENT)
	@Override
	public String getTextureFile(){
            return DefaultProps.blockSpriteSheet;
    }

	@Override
	public boolean isOpaqueCube() {
		return true;
	}
    
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4){
    	return null;
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity){
    	
        par5Entity.setInWeb();

//    	par5Entity.motionX *= 0.2D;
//    	par5Entity.motionZ *= 0.2D;
//    	par5Entity.motionY *= 0.20D;
//    	if(par5Entity instanceof EntityPlayer){
//    		((EntityPlayer)par5Entity).jumpMovementFactor *= 0.05;
//    	}
//    	this.blockIndexInTexture = 113;
//    	par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, 2);
    }
}

