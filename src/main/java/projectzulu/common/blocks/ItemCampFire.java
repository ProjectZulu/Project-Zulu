package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCampFire extends ItemBlock {

    public ItemCampFire(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int par1) {
        return par1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
        return Block.getBlockFromItem(this).getIcon(2, par1);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return "tile."
                + BlockCampfire.Type.getTypeByMeta(itemstack.getItemDamage()).displayName().toLowerCase()
                        .replaceAll("\\s", "");
    }
}
