package projectzulu.common.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs{

	public CreativeTab(int par1, String par2Str) {
		super(par1, par2Str);
	}
	
	@Override
	public ItemStack getIconItemStack() {
		if(ItemBlockList.campfire.isPresent()){
			return new ItemStack(ItemBlockList.campfire.get(),1,3);
		}
		return super.getIconItemStack();
	}
}
