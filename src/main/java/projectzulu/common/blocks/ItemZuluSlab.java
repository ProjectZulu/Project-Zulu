package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

public class ItemZuluSlab extends ItemSlab {

    private static BlockSlab halfBlock;
    private static BlockSlab doubleSlab;
    private final boolean isFullBlock;

    /**
     * Setup the half/double Slab instances required by ItemSlab This is a workaround as Forge does not allow Blocks to
     * registered with an instance of an itemClass but an Item.class which only allow itemID constructor
     */
    public static void initialise(BlockSlab halfBlock, BlockSlab doubleSlab) {
        ItemZuluSlab.halfBlock = halfBlock;
        ItemZuluSlab.doubleSlab = doubleSlab;
    }

    public ItemZuluSlab(Block block) {
        super(block, (BlockSlab) halfBlock, (BlockSlab) doubleSlab, block == doubleSlab);
        isFullBlock = block == doubleSlab;
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return isFullBlock ? doubleSlab.func_150002_b(par1ItemStack.getItemDamage()) : super
                .getUnlocalizedName(par1ItemStack);
    }
}
