package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemReed;
import projectzulu.common.ProjectZulu_Core;

public class ItemUniversalFlowerPot extends ItemReed {

    public ItemUniversalFlowerPot(Block block) {
        super(block);
        setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        setUnlocalizedName(block.getUnlocalizedName());
        setTextureName("flower_pot");
    }
}
