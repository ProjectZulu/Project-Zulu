package projectzulu.common.potion.brewingstands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerBrewingStandSingle extends Container {

    private TileEntityBrewingBase tileBrewingStand;

    /** Instance of Slot. */
    private final Slot theSlot;
    private int brewTime = 0;
    public final int potionSlots;

    public ContainerBrewingStandSingle(InventoryPlayer par1InventoryPlayer, TileEntityBrewingBase brewingStand) {
        this.tileBrewingStand = brewingStand;
        potionSlots = tileBrewingStand.getSizeInventory() - 1;
        int slotID = 0;
        if (potionSlots >= 1) {
            addSlotToContainer(new SlotBrewingStandSinglePotion(par1InventoryPlayer.player, brewingStand, slotID++, 79,
                    53));
        }

        if (potionSlots >= 3) {
            addSlotToContainer(new SlotBrewingStandSinglePotion(par1InventoryPlayer.player, brewingStand, slotID++, 56,
                    46));
            addSlotToContainer(new SlotBrewingStandSinglePotion(par1InventoryPlayer.player, brewingStand, slotID++,
                    102, 46));
        }
        this.theSlot = this.addSlotToContainer(new SlotBrewingStandSingleIngredient(this, brewingStand, slotID++, 79,
                17));
        int i;

        /* Add Inventory Slots */
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        /* Add Hot Bar Slots */
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.tileBrewingStand.getBrewTime());
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

            if (this.brewTime != this.tileBrewingStand.getBrewTime()) {
                icrafting.sendProgressBarUpdate(this, 0, this.tileBrewingStand.getBrewTime());
            }
        }
        this.brewTime = this.tileBrewingStand.getBrewTime();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2) {
        if (par1 == 0) {
            this.tileBrewingStand.setBrewTime(par2);
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.tileBrewingStand.isUseableByPlayer(par1EntityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        int potionMin = 0;
        int potionMax = potionSlots + 1; // 4;
        int hotBarMin = potionSlots + 1 + 3 * 9; // 31;
        int hotBarMax = potionSlots + 1 + 3 * 9 + 9; // 40;
        int inventMin = potionSlots + 1; // 4;
        int inventMax = potionSlots + 1 + 3 * 9;// 31;

        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if ((par2 < 0 || par2 > potionMax)) {
                if (!this.theSlot.getHasStack() && this.theSlot.isItemValid(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, potionMax - 1, potionMax, false)) {
                        return null;
                    }
                } else if (SlotBrewingStandSinglePotion.canHoldPotion(itemstack)) {
                    if (!this.mergeItemStack(itemstack1, potionMin, potionMax - 1, false)) {
                        return null;
                    }
                } else if (par2 >= inventMin && par2 < inventMax) {
                    if (!this.mergeItemStack(itemstack1, hotBarMin, hotBarMax, false)) {
                        return null;
                    }
                } else if (par2 >= hotBarMin && par2 < hotBarMax) {
                    if (!this.mergeItemStack(itemstack1, inventMin, inventMax, false)) {
                        return null;
                    }
                } else if (!this.mergeItemStack(itemstack1, inventMin, hotBarMax, false)) {
                    return null;
                }
            } else {
                if (!this.mergeItemStack(itemstack1, inventMin, hotBarMax, true)) {
                    return null;
                }
                slot.onSlotChange(itemstack1, itemstack);
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }
        return itemstack;
    }
}
