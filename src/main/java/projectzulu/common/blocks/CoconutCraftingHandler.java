package projectzulu.common.blocks;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import projectzulu.common.api.ItemList;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class CoconutCraftingHandler {

    @SubscribeEvent
    public void onCrafting(ItemCraftedEvent event) {
        ItemStack item = event.crafting;
        IInventory craftMatrix = event.craftMatrix;
        // FMLCommonHandler.instance().bus().register(this);
        /*
         * This onCrafting Handle is for Sword and Coconut Recipe 0-1-2 3-4-5 6-7-8
         */
        /* Check if Result is leather */
        boolean isResultDesired = item.getItem() == ItemList.coconutSeed.get();
        if (!isResultDesired)
            return;

        boolean inventoryValidSize = craftMatrix.getSizeInventory() > 4;
        if (!inventoryValidSize)
            return;

        ItemStack shouldBeSword = craftMatrix.getStackInSlot(1);
        ItemStack shouldBeCoconut = craftMatrix.getStackInSlot(4);

        if (shouldBeSword != null
                && shouldBeCoconut != null
                && (shouldBeSword.getItem() == Items.wooden_sword || shouldBeSword.getItem() == Items.stone_sword
                        || shouldBeSword.getItem() == Items.iron_sword || shouldBeSword.getItem() == Items.golden_sword || shouldBeSword
                        .getItem() == Items.diamond_sword) && ItemList.coconutItem.isPresent()
                && shouldBeCoconut.getItem() == ItemList.coconutItem.get()) {
            /* Stacksize of placed must not be 1, as the 'recipe' will consume 1 of whatever item is present in matrix */
            /* Increase Sword */
            shouldBeSword.setItemDamage(shouldBeSword.getItemDamage() + 1);
            shouldBeSword.stackSize += 1;

            /* Place Coconut Milk */
            if (ItemList.coconutMilkFragment.isPresent()) {
                ItemStack tempMilk = new ItemStack(ItemList.coconutMilkFragment.get(), 2);
                craftMatrix.setInventorySlotContents(3, tempMilk);
            }
            /* Place Coconut Shell */
            if (ItemList.coconutShell.isPresent()) {
                ItemStack tempShell = new ItemStack(ItemList.coconutShell.get(), 2);
                craftMatrix.setInventorySlotContents(5, tempShell);
            }
        }
    }
}
