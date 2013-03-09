package projectzulu.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTumbleweed extends Block
{
	boolean prepareToSummonBoss = false;

	public BlockTumbleweed(int par1, int par2)
    {
        super(par1, Material.plants);
        //this.setTickRandomly(true);
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
    }
	
    @SideOnly(Side.CLIENT)
	public String getTextureFile()
    {
            return DefaultProps.blockSpriteSheet;
    }

	public int quantityDropped(Random random){
		return 1;
	}

	public int idDropped(int i, Random random, int j){
		
		return this.blockID;
	}

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
    	super.onBlockAdded(par1World, par2, par3, par4);
    }
    
}
