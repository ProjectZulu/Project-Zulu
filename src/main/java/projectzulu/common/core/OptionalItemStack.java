package projectzulu.common.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.google.common.base.Optional;

public class OptionalItemStack {
    enum OptionalType {
        BLOCK, ITEM, OREDIC;
    }

    private OptionalType type = OptionalType.BLOCK;
    private Optional itemBlock;
    private int meta;
    private int stackSize;

    public OptionalItemStack(Optional<?> itemBlock) {
        this(itemBlock, 1, 0);
    }

    public OptionalItemStack(Optional<?> itemBlock, int stackSize) {
        this(itemBlock, stackSize, 0);
    }

    public OptionalItemStack(Optional<?> itemBlock, int stackSize, int meta) {
        this.itemBlock = itemBlock;
        this.meta = meta;
        this.stackSize = stackSize;

        if (itemBlock.isPresent()) {
            if (itemBlock.get() instanceof Item) {
                type = OptionalType.ITEM;
            } else if (itemBlock.get() instanceof Block) {
                type = OptionalType.BLOCK;
            } else if (itemBlock.get() instanceof String) {
                type = OptionalType.OREDIC;
            }
        } else {
            itemBlock = Optional.absent();
        }
    }

    /* Item */
    public OptionalItemStack(Item itemBlock) {
        this(itemBlock, 1, 0);
    }

    public OptionalItemStack(Item itemBlock, int stackSize) {
        this(itemBlock, stackSize, 0);
    }

    public OptionalItemStack(Item itemBlock, int stackSize, int meta) {
        this(Optional.of(itemBlock), stackSize, meta, OptionalType.ITEM);
    }

    /* Block */
    public OptionalItemStack(Block itemBlock) {
        this(itemBlock, 1, 0);
    }

    public OptionalItemStack(Block itemBlock, int stackSize) {
        this(itemBlock, stackSize, 0);
    }

    public OptionalItemStack(Block itemBlock, int stackSize, int meta) {
        this(Optional.of(itemBlock), stackSize, meta, OptionalType.BLOCK);
    }

    /* Ore Dictionary String */
    public OptionalItemStack(String itemBlock) {
        this(itemBlock, 1, 0);
    }

    public OptionalItemStack(String itemBlock, int stackSize) {
        this(itemBlock, stackSize, 0);
    }

    public OptionalItemStack(String itemBlock, int stackSize, int meta) {
        this(Optional.of(itemBlock), stackSize, meta, OptionalType.OREDIC);
    }

    /** Optional Parameter With Supplied Type */
    public OptionalItemStack(Optional<?> itemBlock, OptionalType type) {
        this(itemBlock, 1, 0, type);
    }

    public OptionalItemStack(Optional<?> itemBlock, int stackSize, OptionalType type) {
        this(itemBlock, stackSize, 0, type);
    }

    public OptionalItemStack(Optional<?> itemBlock, int stackSize, int meta, OptionalType type) {
        this.itemBlock = itemBlock;
        this.meta = meta;
        this.stackSize = stackSize;
        this.type = type;
    }

    public boolean isPresent() {
        return itemBlock != null && itemBlock.isPresent();
    }

    public ItemStack createItemStack() {
        switch (type) {
        case BLOCK:
            return new ItemStack((Block) (itemBlock.get()), stackSize, meta);
        case ITEM:
            return new ItemStack((Item) (itemBlock.get()), stackSize, meta);
        default:
            throw new IllegalStateException("Cannot create ItemStack for OptionalType");
        }
    }

    public Object createRecipeObject() {
        switch (type) {
        case BLOCK:
        case ITEM:
            return createItemStack();
        case OREDIC:
            return itemBlock.get();
        default:
            throw new IllegalStateException();
        }
    }
}
