package projectzulu.common.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

/**
 * List of GUI Ids (TODO: Move to Enum?)
 * 0 : Tile Entity Tombstone Editor
 * 1 : Universal Flower Pot Containter and GUI
 * 2 : Entity Renaming GUI
 * @author CaseyB
 */
public class ZuluGuiHandler implements IGuiHandler{
	
	@Override
	public Object getServerGuiElement(int guiID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntityServer;
		switch (guiID) {
		case 1:
			tileEntityServer = world.getBlockTileEntity(x, y, z);
			if(tileEntityServer instanceof TileEntityUniversalFlowerPot){
				return new ContainerUniversalFlowerPot(player.inventory, (TileEntityUniversalFlowerPot) tileEntityServer);
			}
		}
		return null;

	}

	@Override
	public Object getClientGuiElement(int guiID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tileEntityClient;
		switch (guiID) {
		case 0:
			tileEntityClient = world.getBlockTileEntity(x, y, z);
			if(tileEntityClient instanceof TileEntityTombstone){
				return new GuiTombstone((TileEntityTombstone) tileEntityClient);
			}
			break;
		case 1:
			tileEntityClient = world.getBlockTileEntity(x, y, z);
			if(tileEntityClient instanceof TileEntityUniversalFlowerPot){
				return new GuiContainerUniversalFlowerPot(player.inventory, (TileEntityUniversalFlowerPot) tileEntityClient);
			}
			break;
		case 2:
			return new GuiAnimalName(world, player, x);
		}
			
		
		return null;
	}
	
}
