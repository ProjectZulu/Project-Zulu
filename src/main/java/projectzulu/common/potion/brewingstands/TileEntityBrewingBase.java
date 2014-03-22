package projectzulu.common.potion.brewingstands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import projectzulu.common.api.ItemList;
import projectzulu.common.api.SubItemPotionList;
import projectzulu.common.core.ItemGenerics.Properties;
import projectzulu.common.potion.subitem.SubItemPotion;
import projectzulu.common.potion.subitem.SubItemPotionRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityBrewingBase extends TileEntity implements ISidedInventory {

    protected ItemStack[] brewingItemStacks = new ItemStack[4];

    /** The itemstacks currently placed in the slots of the brewing stand */
    private int brewTime;

    /* an integer with each bit specifying whether that slot of the stand contains a potion */
    private int filledSlots;

    /* Ingredient Cache. Used to Check if an Ingredient has been Added or Removed */
    private Item ingredientID;

    /**
     * Returns the name of the inventory.
     */
    @Override
    public String getInventoryName() {
        return "container.brewing";
    }

    /**
     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
     * language. Otherwise it will be used directly.
     */
    @Override
    public boolean hasCustomInventoryName() {
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
                this.markDirty();
            } else if (!this.canBrew()) {
                this.brewTime = 0;
                this.markDirty();
            } else if (this.ingredientID != this.brewingItemStacks[brewingItemStacks.length - 1].getItem()) {
                this.brewTime = 0;
                this.markDirty();
            }
        } else if (this.canBrew()) {
            this.brewTime = 400;
            this.ingredientID = this.brewingItemStacks[brewingItemStacks.length - 1].getItem();
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

            if (!PotionIngredients.isPotionIngredient(ingredientStack)) {
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
                    } else if (isWaterBottleOverride(ingredientStack, brewingItemStacks[i])) {
                        return true;
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
                        brewingItemStacks[i].func_150996_a(resultPotion.getItem());
                        brewingItemStacks[i].setItemDamage(resultPotion.getItemDamage());
                    }
                } else if (isWaterBottleOverride(ingredientStack, brewingItemStacks[i])) {
                    brewingItemStacks[i].func_150996_a(SubItemPotionList.BUBBLING.get().item);
                    brewingItemStacks[i].setItemDamage(SubItemPotionList.BUBBLING.get().subID);
                }
            }

            if (ingredientStack.getItem().hasContainerItem()) {
                this.brewingItemStacks[brewingItemStacks.length - 1] = ingredientStack.getItem().getContainerItem(
                        brewingItemStacks[brewingItemStacks.length - 1]);
            } else {
                --this.brewingItemStacks[brewingItemStacks.length - 1].stackSize;

                if (this.brewingItemStacks[brewingItemStacks.length - 1].stackSize <= 0) {
                    this.brewingItemStacks[brewingItemStacks.length - 1] = null;
                }
            }
        }
    }

    /**
     * Determines if the base regular Non-Potion Item that gets converted into a base Potion is present
     * 
     * @param ingredient
     * @param brewingStack
     * @return
     */
    private boolean isWaterBottleOverride(ItemStack ingredient, ItemStack brewingStack) {
        if (brewingStack == null || ingredient == null || !ItemList.genericCraftingItems.isPresent()) {
            return false;
        }

        if (brewingStack.getItem() == Items.potionitem && brewingStack.getItemDamage() == 0) {
            if (ingredient.getItem() == ItemList.genericCraftingItems.get()
                    && ingredient.getItemDamage() == Properties.ShinyBauble.meta) {
                if (SubItemPotionList.BUBBLING.isPresent()) {
                    return true;
                }
            }
        }
        return false;
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
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false
                : par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    @Override
    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
        return par1 == 3 ? PotionIngredients.isPotionIngredient(par2ItemStack)
                : par2ItemStack.getItem() instanceof ItemPotion || par2ItemStack.getItem() == Items.glass_bottle;
    }

    @SideOnly(Side.CLIENT)
    public void setBrewTime(int par1) {
        this.brewTime = par1;
    }

    /**
     * Get the size of the inventory relative to a specific side (integer) of the block. Related to Hopper.
     */
    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
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

    /**
     * Related to Hopper?
     */
    @Override
    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
        return this.isItemValidForSlot(par1, par2ItemStack);
    }

    /**
     * Related to Hopper?
     */
    @Override
    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
        return true;
    }

    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);
        this.brewingItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
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
     * Overriden in a sign to provide the text.
     */
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound var1 = new NBTTagCompound();
        this.writeToNBT(var1);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 4, var1);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound tagCompound = pkt.func_148857_g();
        this.readFromNBT(tagCompound);
    }
}
