package projectzulu.common.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import projectzulu.common.api.BlockList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativePZGeneralTab extends CreativeTabs {

    public CreativePZGeneralTab(int par1, String par2Str) {
        super(par1, par2Str);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getTranslatedTabLabel() {
        return "Project Zulu General";
    }

    @Override
    public Item getTabIconItem() {
        return Item.getItemFromBlock(BlockList.campfire.isPresent() ? BlockList.campfire.get() : Blocks.grass);
    }

    @Override
    public int func_151243_f() {
        return 3;
    }
}
