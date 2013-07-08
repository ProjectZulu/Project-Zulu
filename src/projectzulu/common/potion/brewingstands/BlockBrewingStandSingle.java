package projectzulu.common.potion.brewingstands;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBrewingStandSingle extends BlockBrewingStandBase {

    public BlockBrewingStandSingle(int blockID, int renderID) {
        super(blockID, renderID);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityBrewingSingle();
    }
}
