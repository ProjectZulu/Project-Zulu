package projectzulu.common.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import projectzulu.common.api.BlockList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativePZGeneralTab extends CreativeTabs {

    public CreativePZGeneralTab(int par1, String par2Str) {
        super(par1, par2Str);
    }

    @Override
    public ItemStack getIconItemStack() {
        if (BlockList.campfire.isPresent()) {
            return new ItemStack(BlockList.campfire.get(), 1, 3);
        }
        return super.getIconItemStack();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public String getTranslatedTabLabel() {
        return "Project Zulu General";
    }
}
