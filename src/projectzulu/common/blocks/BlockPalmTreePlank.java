package projectzulu.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPalmTreePlank extends Block
{
    /** The type of tree this block came from. */
    public BlockPalmTreePlank(int par1, int par2)
    {
        super(par1, par2, Material.wood);
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
    }
    
    @SideOnly(Side.CLIENT)
	public String getTextureFile()
    {
            return DefaultProps.blockSpriteSheet;
    }

    
    @Override
    public int idDropped(int par1, Random par2Random, int par3) {
    	return this.blockID;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random) {
    	return 1;
    }
    
    @Override
    public boolean isOpaqueCube() {
    	return true;
    }
    
    public float getHardness(){
    	return blockHardness;
    }
    
    public float getResistance(){
    	return blockResistance;
    }
}
