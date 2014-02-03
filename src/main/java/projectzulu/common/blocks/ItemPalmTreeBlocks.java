package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemPalmTreeBlocks extends ItemBlock{

	public ItemPalmTreeBlocks(int par1, Block block) {
		super(par1);
		setHasSubtypes(true);
	}

	public int getMetadata(int par1)
	{
		return par1;
	}
	
	public String getUnlocalizedName(ItemStack itemstack) 
	{
		String name = "";
		switch(itemstack.getItemDamage()) 
		{
		case 0: 
		{
			name = "log";
			break;
		}
		case 1: 
		{ 
			name = "leaves"; 
			break;
		}
		case 2: 
		{ 
			name = "plank"; 
			break;
		}
		case 3: 
		{ 
			name = "logholo"; 
			break;
		}
		case 4: 
		{ 
			name = "invisiblelogholo"; 
			break;
		}
		case 5: 
		{ 
			name = "leavesholo"; 
			break;
		}
		case 6: 
		{ 
			name = "invisibleleavesholo"; 
			break;
		}
		case 7: 
		{ 
			name = "plankHolo"; 
			break;
		}
		case 8: 
		{ 
			name = "invisibleplankholo"; 
			break;
		}
		case 9: 
		{ 
			name = "waterholo"; 
			break;
		}
		case 10: 
		{ 
			name = "invisiblewaterholo"; 
			break;
		}
		default: name = "ore";
		}
		return getUnlocalizedName() + "." + name;
	}
		
}
