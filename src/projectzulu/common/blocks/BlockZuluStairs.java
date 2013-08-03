package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockZuluStairs extends BlockStairs {
    public BlockZuluStairs(int par1, Block par2Block, int par3) {
        super(par1, par2Block, par3);
        Block.setBurnProperties(this.blockID, 5, 20);
        setLightOpacity(0);
    }
}
