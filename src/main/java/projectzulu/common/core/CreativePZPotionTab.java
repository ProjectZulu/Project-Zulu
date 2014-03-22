package projectzulu.common.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativePZPotionTab extends CreativeTabs {

    public CreativePZPotionTab(int par1, String par2Str) {
        super(par1, par2Str);
    }

    @Override
    public Item getTabIconItem() {
        return Items.potionitem;
    }

    @Override
    public int func_151243_f() {
        return 3;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getTranslatedTabLabel() {
        return "Project Zulu Potions";
    }

}
