package projectzulu.common.blocks;

import net.minecraft.block.BlockBrewingStand;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockZuluBrewingStand extends BlockBrewingStand{

	public BlockZuluBrewingStand(int par1) {
		super(par1);
	}

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World){
        return new TileEntityZuluBrewingStand();
    }
}
