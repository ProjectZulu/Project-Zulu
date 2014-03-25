package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.init.Blocks;

public class BlockZuluStairs extends BlockStairs {
    public BlockZuluStairs(Block par2Block, int par3) {
        super(par2Block, par3);
        setLightOpacity(0);
    }
}
