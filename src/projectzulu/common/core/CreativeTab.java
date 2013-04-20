package projectzulu.common.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import projectzulu.common.api.BlockList;

public class CreativeTab extends CreativeTabs {

    public CreativeTab(int par1, String par2Str) {
        super(par1, par2Str);
    }

    @Override
    public ItemStack getIconItemStack() {
        if (BlockList.campfire.isPresent()) {
            return new ItemStack(BlockList.campfire.get(), 1, 3);
        }
        return super.getIconItemStack();
    }
}
