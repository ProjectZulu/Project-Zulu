package projectzulu.common.core;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import projectzulu.common.api.BlockList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTab extends CreativeTabs {

    private static int[] commonShifts = new int[] { 0, 32, 64, 96, 512, 544, 576, 608 };

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

    @Override
    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    public void displayAllReleventItems(List par1List) {
        super.displayAllReleventItems(par1List);

        /* Leaping */
        addPotionVariants(Item.potion.itemID, 15763, par1List);
        addPotionVariants(Item.potion.itemID, 32147, par1List);
        /* Slow fall */
        addPotionVariants(Item.potion.itemID, 15762, par1List);
        addPotionVariants(Item.potion.itemID, 32146, par1List);
        /* Haste */
        addPotionVariants(Item.potion.itemID, 15764, par1List);
        addPotionVariants(Item.potion.itemID, 32151, par1List);
        /* Fatique */
        addPotionVariants(Item.potion.itemID, 15766, par1List);
        addPotionVariants(Item.potion.itemID, 32150, par1List);
        /* Cleansing */
        addPotionVariants(Item.potion.itemID, 15775, par1List);
        addPotionVariants(Item.potion.itemID, 32159, par1List);
        /* Curse */
        addPotionVariants(Item.potion.itemID, 15774, par1List);
        addPotionVariants(Item.potion.itemID, 32158, par1List);
        /* Thorns */
        addPotionVariants(Item.potion.itemID, 15773, par1List);
        addPotionVariants(Item.potion.itemID, 32157, par1List);
        /* Resistance */
        addPotionVariants(Item.potion.itemID, 15772, par1List);
        addPotionVariants(Item.potion.itemID, 32156, par1List);

        /* Incindiary */
        par1List.add(new ItemStack(Item.potion.itemID, 1, 14771));
        par1List.add(new ItemStack(Item.potion.itemID, 1, 15283));
        par1List.add(new ItemStack(Item.potion.itemID, 1, 31155));
        par1List.add(new ItemStack(Item.potion.itemID, 1, 31667));
    }

    private void addPotionVariants(int potionID, Integer baseId, List par1List) {
        for (int shift : commonShifts) {
            par1List.add(new ItemStack(potionID, 1, baseId + shift));
        }
    }
}
