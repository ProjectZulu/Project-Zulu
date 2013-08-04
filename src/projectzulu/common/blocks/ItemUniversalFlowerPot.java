package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemReed;
import projectzulu.common.ProjectZulu_Core;

public class ItemUniversalFlowerPot extends ItemReed {

    public ItemUniversalFlowerPot(int par1, Block block) {
        super(par1, block);
        setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        setUnlocalizedName(block.getUnlocalizedName());
        func_111206_d("flower_pot");
    }
}
