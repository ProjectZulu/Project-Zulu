package projectzulu.common.blocks;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCampFire extends ItemBlock {

	public ItemCampFire(int par1) {
		super(par1);
		setHasSubtypes(true);
	}

	public int getMetadata(int par1)
	{
		return par1;
	}
	
    @Override
    @SideOnly(Side.CLIENT)
	public String getTextureFile()
    {
            return "/mods/blocks_projectzulu.png";
    }
	
	@Override
	public int getIconFromDamage(int par1) {
		
		switch (par1) {
		case 0:
			return 48;
		case 1:

			return 49;
		case 2:

			return 50;
		case 3:

			return 51;
		default:
			return super.getIconFromDamage(par1);
		}
	}
	
	public String getItemNameIS(ItemStack itemstack) 
	{
		String name = "";
		switch(itemstack.getItemDamage()) {
		case 0: {
			name = "base_1";
			break;
		}
		case 1: { 
			name = "base_2"; 
			break;
		}
		case 2: { 
			name = "base_3"; 
			break;
		}
		case 3: { 
			name = "base_4"; 
			break;
		}
		default: name = "base_1";
		}
		return getItemName() + "." + name;
	}


}
