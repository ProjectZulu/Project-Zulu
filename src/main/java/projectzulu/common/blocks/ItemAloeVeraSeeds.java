package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.DefaultProps;

public class ItemAloeVeraSeeds extends Item {

    /**
     * The ItemID of block this seed turns into (wheat or pumpkin stems for instance)
     */
    private Block blockType;

    public ItemAloeVeraSeeds(Block turnIntoID, String unlocalizedName) {
        super();
        blockType = turnIntoID;
        setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        setUnlocalizedName(unlocalizedName);
        setTextureName(DefaultProps.blockKey + ":" + unlocalizedName);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int xCoord, int yCoord, int zCoord,
            int par7, float par8, float par9, float par10) {
        if (par7 != 1) {
            return false;
        } else if (player.canPlayerEdit(xCoord, yCoord, zCoord, par7, itemStack)
                && player.canPlayerEdit(xCoord, yCoord + 1, zCoord, par7, itemStack)) {
            Block block = world.getBlock(xCoord, yCoord, zCoord);

            if ((block == Blocks.dirt || block == Blocks.sand || block == Blocks.grass)
                    && world.isAirBlock(xCoord, yCoord + 1, zCoord)) {
                world.setBlock(xCoord, yCoord + 1, zCoord, this.blockType);
                --itemStack.stackSize;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
