package projectzulu.common.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import projectzulu.common.api.ItemBlockList;
import projectzulu.common.core.ItemGenerics;
import cpw.mods.fml.common.ICraftingHandler;

public class RawFiberCraftingHandler implements ICraftingHandler{
	@Override
	public void onCrafting(EntityPlayer player, ItemStack item,
			IInventory craftMatrix) {
		/*This is Fur Pelt --> Leather + Fiber
		 * 0-1-2
		 * 3-4-5
		 * 6-7-8
		 * * * *
		 * 0-1
		 * 2-3
		 */
		/* Check if Result is leather */
		boolean isResultLeather = item.getItem().itemID == Item.leather.itemID;
		if (!isResultLeather) return;

		/* Check if Fur Pelt is the Only Thing on the Crafting Table
		 * If Anything is not fur, do Not Continue 
		 */
		boolean contsinsFur = false;
		int furInSlot = 0;
		for (int i = 0; i < craftMatrix.getSizeInventory(); i++) {
			if( craftMatrix.getStackInSlot(i) == null){
				continue;
			}
			if(craftMatrix.getStackInSlot(i).getItem().itemID == ItemBlockList.furPelt.get().itemID && !contsinsFur){
				contsinsFur = true;
				furInSlot = i;
				continue;
			}
			return;
		}

		if(!contsinsFur) return;
		
		ItemStack itemToPlace = new ItemStack(ItemBlockList.genericCraftingItems1.get(), 2, ItemGenerics.Properties.RawFiber.meta());

		if(furInSlot == 0){
			craftMatrix.setInventorySlotContents(1, itemToPlace);
		}else{
			craftMatrix.setInventorySlotContents(0, itemToPlace);
		}
	}
	
	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {}
}


