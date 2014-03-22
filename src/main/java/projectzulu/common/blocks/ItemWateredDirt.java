package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemWateredDirt extends ItemBlock {

    public ItemWateredDirt(Block block) {
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
            name = "soil_1";
            break;
        }
        case 1: {
            name = "soil_2";
            break;
        }
        case 2: {
            name = "soil_3";
            break;
        }
        case 3: {
            name = "soil_4";
            break;
        }
        case 4: {
            name = "sand_1";
            break;
        }
        case 5: {
            name = "sand_2";
            break;
        }
        case 6: {
            name = "sand_3";
            break;
        }
        case 7: {
            name = "sand_4";
            break;
        }
        default:
            name = "ore";
        }
        return getUnlocalizedName() + "." + name;
    }

}
