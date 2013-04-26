package projectzulu.common.potion.brewingstands;

import net.minecraft.item.ItemStack;

public class TileEntityBrewingSingle extends TileEntityBrewingTriple {
    
    public TileEntityBrewingSingle() {
        brewingItemStacks = new ItemStack[2];
    }
}