package projectzulu.common.potion.brewingstands;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBrewingStandSingle extends BlockBrewingStandBase {

    public BlockBrewingStandSingle(int renderID) {
        super(renderID);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityBrewingSingle();
    }
}
