package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemAloeVera extends ItemBlock {

    public ItemAloeVera(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    public int getMetadata(int par1) {
        return par1;
    }

    public String getUnlocalizedName(ItemStack itemstack) {
        String name = "";
        switch (itemstack.getItemDamage()) {
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
        case 4: {
            name = "flower_1";
            break;
        }
        case 5: {
            name = "flower_2";
            break;
        }
        case 6: {
            name = "flower_3";
            break;
        }
        case 7: {
            name = "flower_4";
            break;
        }
        case 8: {
            name = "flower_5";
            break;
        }
        default:
            name = "base_1";
        }
        return getUnlocalizedName() + "." + name;
    }

}
