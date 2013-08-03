package projectzulu.common.blocks;

import net.minecraft.block.BlockHalfSlab;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

public class ItemZuluSlab extends ItemSlab {

    private static BlockHalfSlab halfBlock;
    private static BlockHalfSlab doubleSlab;

    /**
     * Setup the half/double Slab instances required by ItemSlab This is a workaround as Forge does not allow Blocks to
     * registered with an instance of an itemClass but an Item.class which only allow itemID constructor
     */
    public static void initialise(BlockHalfSlab halfBlock, BlockHalfSlab doubleSlab) {
        ItemZuluSlab.halfBlock = halfBlock;
        ItemZuluSlab.doubleSlab = doubleSlab;
    }

    public ItemZuluSlab(int id) {
        super(id, (BlockHalfSlab) halfBlock, (BlockHalfSlab) doubleSlab, id == doubleSlab.blockID);
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return par1ItemStack.itemID == ItemZuluSlab.doubleSlab.blockID ? doubleSlab.getFullSlabName(par1ItemStack
                .getItemDamage()) : super.getUnlocalizedName(par1ItemStack);
    }
}
