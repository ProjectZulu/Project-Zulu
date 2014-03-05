package projectzulu.common.potion.brewingstands;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBrewingStandTriple extends BlockBrewingStandBase {

    public BlockBrewingStandTriple(int renderID) {
        super(renderID);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par2) {
        return new TileEntityBrewingTriple();
    }
}
