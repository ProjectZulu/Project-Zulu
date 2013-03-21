package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.item.ItemSlab;

public class ItemZuluSlab extends ItemSlab {
	
	private static Block halfBlock;
	private static Block doubleSlab;
	
	/**
	 * Setup the half/double Slab instances required by ItemSlab
	 * This is a workaround as Forge does not allow Blocks to registered with an instance of an itemClass but an Item.class which only allow itemID constructor
	 */
	public static void initialise(Block halfBlock, Block doubleSlab){
		ItemZuluSlab.halfBlock = halfBlock;
		ItemZuluSlab.doubleSlab = doubleSlab;
	}
	
	public ItemZuluSlab(int id) {
		super(id, (BlockHalfSlab)halfBlock, (BlockHalfSlab)doubleSlab, id == doubleSlab.blockID);
	}
	
}
