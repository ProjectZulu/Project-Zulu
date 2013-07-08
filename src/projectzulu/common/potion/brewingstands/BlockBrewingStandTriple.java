package projectzulu.common.potion.brewingstands;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBrewingStandTriple extends BlockBrewingStandBase {

    public BlockBrewingStandTriple(int blockID, int renderID) {
        super(blockID, renderID);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityBrewingTriple();
    }
}
