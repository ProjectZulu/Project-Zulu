package projectzulu.common.blocks;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class ZuluFuelManager implements IFuelHandler{
	
	private int itemID;
	private int fuelValue;
	
	public ZuluFuelManager(int itemID, int fuelValue){
		this.itemID = itemID;
		this.fuelValue = fuelValue;
	}
	
	@Override
	public int getBurnTime(ItemStack fuel){
		if(itemID == fuel.getItem().shiftedIndex){
			return fuelValue;
		}
		return 0;
	}

}
