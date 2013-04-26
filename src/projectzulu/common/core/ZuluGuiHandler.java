package projectzulu.common.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import projectzulu.common.blocks.ContainerUniversalFlowerPot;
import projectzulu.common.blocks.GuiAnimalName;
import projectzulu.common.blocks.GuiContainerUniversalFlowerPot;
import projectzulu.common.blocks.GuiTombstone;
import projectzulu.common.blocks.TileEntityTombstone;
import projectzulu.common.blocks.TileEntityUniversalFlowerPot;
import projectzulu.common.dungeon.GuiLimitedMobSpawner;
import projectzulu.common.dungeon.TileEntityLimitedMobSpawner;
import projectzulu.common.potion.brewingstands.ContainerBrewingStandSingle;
import projectzulu.common.potion.brewingstands.GuiBrewingStandSingle;
import projectzulu.common.potion.brewingstands.TileEntityBrewingTriple;
import cpw.mods.fml.common.network.IGuiHandler;

public class ZuluGuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int guiID, EntityPlayer player, World world, int x, int y, int z) {

        TileEntity tileEntityServer;
        switch (GuiID.getGuiIDByID(guiID)) {
        case FlowerPot:
            tileEntityServer = world.getBlockTileEntity(x, y, z);
            if (tileEntityServer instanceof TileEntityUniversalFlowerPot) {
                return new ContainerUniversalFlowerPot(player.inventory,
                        (TileEntityUniversalFlowerPot) tileEntityServer);
            }
        case BrewingStand:
            tileEntityServer = world.getBlockTileEntity(x, y, z);
            if (tileEntityServer instanceof TileEntityBrewingTriple) {
                return new ContainerBrewingStandSingle(player.inventory, (TileEntityBrewingTriple) tileEntityServer);
            }
        case Unknown:
            throw new IllegalStateException("GuiID cannot be Found" + guiID);
        default:
            break;
        }
        return null;

    }

    @Override
    public Object getClientGuiElement(int guiID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntityClient;
        switch (GuiID.getGuiIDByID(guiID)) {
        case Tombstone:
            tileEntityClient = world.getBlockTileEntity(x, y, z);
            if (tileEntityClient instanceof TileEntityTombstone) {
                return new GuiTombstone((TileEntityTombstone) tileEntityClient);
            }
            break;
        case FlowerPot:
            tileEntityClient = world.getBlockTileEntity(x, y, z);
            if (tileEntityClient instanceof TileEntityUniversalFlowerPot) {
                return new GuiContainerUniversalFlowerPot(player.inventory,
                        (TileEntityUniversalFlowerPot) tileEntityClient);
            }
            break;
        case AnimalName:
            return new GuiAnimalName(world, player, x);
        case MobSpawner:
            tileEntityClient = world.getBlockTileEntity(x, y, z);
            if (tileEntityClient instanceof TileEntityLimitedMobSpawner) {
                return new GuiLimitedMobSpawner((TileEntityLimitedMobSpawner) tileEntityClient);
            }
        case BrewingStand:
            tileEntityClient = world.getBlockTileEntity(x, y, z);
            if (tileEntityClient instanceof TileEntityBrewingTriple) {
                return new GuiBrewingStandSingle(player.inventory, (TileEntityBrewingTriple) tileEntityClient);
            }
        case Unknown:
            throw new IllegalStateException("GuiID cannot be Found" + guiID);
        }

        return null;
    }

}
