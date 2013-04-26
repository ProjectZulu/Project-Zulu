package projectzulu.common.potion.brewingstands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import projectzulu.common.potion.subitem.SubItemPotion;
import projectzulu.common.potion.subitem.SubItemPotionRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityBrewingTriple extends TileEntity implements ISidedInventory {

    protected ItemStack[] brewingItemStacks = new ItemStack[2];

    /** The itemstacks currently placed in the slots of the brewing stand */
    private int brewTime;

    /* an integer with each bit specifying whether that slot of the stand contains a potion */
    private int filledSlots;

    /* Ingredient Cache. Used to Check if an Ingredient has been Added or Removed */
    private int ingredientID;

    /**
     * Returns the name of the inventory.
     */
    @Override
    public String getInvName() {
        return "container.brewing";
    }

    /**
     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
     * language. Otherwise it will be used directly.
     */
    @Override
    public boolean isInvNameLocalized() {
        return false;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory() {
        return this.brewingItemStacks.length;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    @Override
    public void updateEntity() {
        if (this.brewTime > 0) {
            --this.brewTime;
            if (this.brewTime == 0) {
                this.brewPotions();
                this.onInventoryChanged();
            } else if (!this.canBrew()) {
                this.brewTime = 0;
                this.onInventoryChanged();
            } else if (this.ingredientID != this.brewingItemStacks[brewingItemStacks.length - 1].itemID) {
                this.brewTime = 0;
                this.onInventoryChanged();
            }
        } else if (this.canBrew()) {
            this.brewTime = 400;
            this.ingredientID = this.brewingItemStacks[brewingItemStacks.length - 1].itemID;
        }

        int i = this.getFilledSlots();

        if (i != this.filledSlots) {
            this.filledSlots = i;
            this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, i, 2);
        }
        super.updateEntity();
    }

    public int getBrewTime() {
        return this.brewTime;
    }

    private boolean canBrew() {
        if (brewingItemStacks.length > 1 && this.brewingItemStacks[brewingItemStacks.length - 1] != null
                && this.brewingItemStacks[brewingItemStacks.length - 1].stackSize > 0) {
            ItemStack ingredientStack = this.brewingItemStacks[brewingItemStacks.length - 1];

            if (!Item.itemsList[ingredientStack.itemID].isPotionIngredient()) {
                return false;
            } else {
                for (int i = 0; i < brewingItemStacks.length - 1; ++i) {
                    if (SubItemPotionRegistry.INSTANCE.isItemPotion(brewingItemStacks[i])) {
                        SubItemPotion brewingSubPotion = SubItemPotionRegistry.INSTANCE.getPotion(brewingItemStacks[i]);
                        ItemStack resultPotion = brewingSubPotion != null ? brewingSubPotion.getPotionResult(
                                ingredientStack, brewingItemStacks[i]) : null;
                        if (resultPotion != null) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void brewPotions() {
        if (this.canBrew()) {
            ItemStack ingredientStack = this.brewingItemStacks[brewingItemStacks.length - 1];
            for (int i = 0; i < brewingItemStacks.length - 1; ++i) {
                if (SubItemPotionRegistry.INSTANCE.isItemPotion(brewingItemStacks[i])) {
                    SubItemPotion brewingSubPotion = SubItemPotionRegistry.INSTANCE.getPotion(brewingItemStacks[i]);
                    ItemStack resultPotion = brewingSubPotion != null ? brewingSubPotion.getPotionResult(
                            ingredientStack, brewingItemStacks[i]) : null;
                    if (resultPotion != null) {
                        brewingItemStacks[i].itemID = resultPotion.itemID;
                        brewingItemStacks[i].setItemDamage(resultPotion.getItemDamage());
                    }
                }
            }

            if (Item.itemsList[ingredientStack.itemID].hasContainerItem()) {
                this.brewingItemStacks[brewingItemStacks.length - 1] = Item.itemsList[ingredientStack.itemID]
                        .getContainerItemStack(brewingItemStacks[brewingItemStacks.length - 1]);
            } else {
                --this.brewingItemStacks[brewingItemStacks.length - 1].stackSize;

                if (this.brewingItemStacks[brewingItemStacks.length - 1].stackSize <= 0) {
                    this.brewingItemStacks[brewingItemStacks.length - 1] = null;
                }
            }
        }
    }

    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        this.brewingItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.brewingItemStacks.length) {
                this.brewingItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.brewTime = par1NBTTagCompound.getShort("BrewTime");
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("BrewTime", (short) this.brewTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.brewingItemStacks.length; ++i) {
            if (this.brewingItemStacks[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                this.brewingItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        par1NBTTagCompound.setTag("Items", nbttaglist);
    }

    /**
     * Returns the stack in slot i
     */
    @Override
    public ItemStack getStackInSlot(int par1) {
        return par1 >= 0 && par1 < this.brewingItemStacks.length ? this.brewingItemStacks[par1] : null;
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    @Override
    public ItemStack decrStackSize(int par1, int par2) {
        if (par1 >= 0 && par1 < this.brewingItemStacks.length) {
            ItemStack itemstack = this.brewingItemStacks[par1];
            this.brewingItemStacks[par1] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
        if (par1 >= 0 && par1 < this.brewingItemStacks.length) {
            ItemStack itemstack = this.brewingItemStacks[par1];
            this.brewingItemStacks[par1] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
        if (par1 >= 0 && par1 < this.brewingItemStacks.length) {
            this.brewingItemStacks[par1] = par2ItemStack;
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    @Override
    public int getInventoryStackLimit() {
        return 2;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false
                : par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openChest() {
    }

    @Override
    public void closeChest() {
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    @Override
    public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack) {
        return par1 == 3 ? Item.itemsList[par2ItemStack.itemID].isPotionIngredient()
                : par2ItemStack.getItem() instanceof ItemPotion || par2ItemStack.itemID == Item.glassBottle.itemID;
    }

    @SideOnly(Side.CLIENT)
    public void setBrewTime(int par1) {
        this.brewTime = par1;
    }

    /**
     * returns an integer with each bit specifying wether that slot of the stand contains a potion
     */
    // TODO: Removeif we Use TileEntity to Draw Potions
    public int getFilledSlots() {
        int i = 0;
        for (int j = 0; j < brewingItemStacks.length; ++j) {
            if (this.brewingItemStacks[j] != null) {
                i |= 1 << j;
            }
        }
        return i;
    }

    /**
     * Get the size of the side inventory.
     */
    @Override
    public int[] getSizeInventorySide(int side) {
        return side == 1 ? getIngredientSlotsForSide(side) : getPotionSlotsForSide(side);
    }

    private int[] getPotionSlotsForSide(int side) {
        int[] result = new int[brewingItemStacks.length - 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }
        return result;
    }

    private int[] getIngredientSlotsForSide(int side) {
        return new int[] { brewingItemStacks.length - 1 };
    }

    @Override
    public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3) {
        return this.isStackValidForSlot(par1, par2ItemStack);
    }

    @Override
    public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3) {
        return true;
    }
}
