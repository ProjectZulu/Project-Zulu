package projectzulu.common.world.dataobjects;

import static net.minecraftforge.common.ChestGenHooks.DUNGEON_CHEST;

import java.util.Random;

import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

public class ChestWithMeta extends BlockWithMeta {

    int lootChance;
    int maxLoot;
    TileEntityChest tileEntityChest;

    public ChestWithMeta(int blockID, int meta, TileEntityChest tileEntityChest, int lootChance) {
        this(blockID, meta, tileEntityChest, lootChance, -1);
    }

    public ChestWithMeta(int blockID, int meta, TileEntityChest tileEntityChest, int lootChance, int maxLoot) {
        super(blockID, meta);
        this.tileEntityChest = tileEntityChest;
        this.lootChance = lootChance;
        this.maxLoot = maxLoot;
    }

    @Override
    public void placeBlock(World world, ChunkCoordinates position, Random random) {
        world.setBlock(position.posX, position.posY, position.posZ, blockID, meta, 3);
        world.setBlockTileEntity(position.posX, position.posY, position.posZ, tileEntityChest);
        int amountOfLoot = 0;
        for (int slot = 0; slot < tileEntityChest.getSizeInventory(); slot++) {
            if (lootChance - random.nextInt(100) >= 0) {
                tileEntityChest.setInventorySlotContents(slot, ChestGenHooks.getOneItem(DUNGEON_CHEST, random));
                amountOfLoot++;
                if (maxLoot > 0 && amountOfLoot >= maxLoot) {
                    break;
                }
            }
        }
    }
}
