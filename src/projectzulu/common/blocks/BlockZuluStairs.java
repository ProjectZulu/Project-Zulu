package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockZuluStairs extends BlockStairs
{
    public BlockZuluStairs(int par1, Block par2Block, int par3){
        super(par1, par2Block, par3);
        setLightOpacity(0);
    }

    @SideOnly(Side.CLIENT)
	public String getTextureFile(){
            return "/mods/blocks_projectzulu.png";
    }
    
}
