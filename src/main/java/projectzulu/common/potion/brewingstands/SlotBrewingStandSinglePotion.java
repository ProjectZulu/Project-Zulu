package projectzulu.common.potion.brewingstands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import projectzulu.common.potion.subitem.SubItemPotionRegistry;

public class SlotBrewingStandSinglePotion extends Slot {
    /** The player that has this container open. */
    private EntityPlayer player;

    public SlotBrewingStandSinglePotion(EntityPlayer par1EntityPlayer, IInventory par2IInventory, int par3, int par4,
            int par5) {
        super(par2IInventory, par3, par4, par5);
        this.player = par1EntityPlayer;
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    @Override
    public boolean isItemValid(ItemStack par1ItemStack) {
        return canHoldPotion(par1ItemStack);
    }

    /**
     * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the case
     * of armor slots)
     */
    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @Override
    public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack itemStack) {
        if (SubItemPotionRegistry.INSTANCE.isItemPotion(itemStack)) {
            this.player.addStat(AchievementList.potion, 1);
        }

        super.onPickupFromSlot(par1EntityPlayer, itemStack);
    }

    /**
     * Returns true if this itemstack can be filled with a potion
     */
    public static boolean canHoldPotion(ItemStack itemStack) {
        return itemStack != null
                && (SubItemPotionRegistry.INSTANCE.isItemPotion(itemStack) || itemStack.getItem() == Items.potionitem
                        && itemStack.getItemDamage() == 0);
    }
}
