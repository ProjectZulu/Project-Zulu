package projectzulu.common.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import projectzulu.common.api.ItemList;
import cpw.mods.fml.common.ICraftingHandler;

public class CoconutCraftingHandler implements ICraftingHandler{
	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {}

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item,
			IInventory craftMatrix) {

		/*
		 * This onCrafting Handle is for Sword and Coconut Recipe
		 * 0-1-2
		 * 3-4-5
		 * 6-7-8
		 */
		/* Check if Result is leather */
		boolean isResultDesired = item.getItem().itemID == ItemList.coconutItem.get().itemID;
		if (!isResultDesired) return;
		
		boolean inventoryValidSize = craftMatrix.getSizeInventory() > 4;
		if (!inventoryValidSize) return;
		
		ItemStack shouldBeSword = craftMatrix.getStackInSlot(1);
		ItemStack shouldBeCoconut = craftMatrix.getStackInSlot(4);

		if(shouldBeSword != null && shouldBeCoconut != null 
				&& (shouldBeSword.getItem().itemID == Item.swordWood.itemID || shouldBeSword.getItem().itemID == Item.swordStone.itemID 
				|| shouldBeSword.getItem().itemID == Item.swordIron.itemID || shouldBeSword.getItem().itemID == Item.swordGold.itemID 
				|| shouldBeSword.getItem().itemID == Item.swordDiamond.itemID) 
				&& ItemList.coconutItem.isPresent() && shouldBeCoconut.getItem() == ItemList.coconutItem.get()){
			//Put Sword Back
			ItemStack tempItem1 = new ItemStack(shouldBeSword.getItem(), 2, shouldBeSword.getItemDamage()+1);
			craftMatrix.setInventorySlotContents(1, tempItem1);
			//Place Coconut Milk
			if(ItemList.coconutMilkFragment.isPresent()){
				ItemStack tempItem2 = new ItemStack(ItemList.coconutMilkFragment.get(), 2);
				craftMatrix.setInventorySlotContents(3, tempItem2);
			}
			//Place Coconut Shell
			if(ItemList.coconutShell.isPresent()){
				ItemStack tempItem3 = new ItemStack(ItemList.coconutShell.get(), 2);
				craftMatrix.setInventorySlotContents(5, tempItem3);
			}
		}
	}
}
