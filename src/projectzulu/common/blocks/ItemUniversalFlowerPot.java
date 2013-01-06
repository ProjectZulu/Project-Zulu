package projectzulu.common.blocks;

import net.minecraft.item.ItemBlock;

public class ItemUniversalFlowerPot extends ItemBlock{

	public ItemUniversalFlowerPot(int par1) {
		super(par1);
		setIconCoord(13, 11);
	}
	
	@Override
	public String getTextureFile() {
		return "/gui/items.png";
	}
}
