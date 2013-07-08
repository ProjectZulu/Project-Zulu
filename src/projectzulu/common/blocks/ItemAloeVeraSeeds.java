package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAloeVeraSeeds extends Item {

    /**
     * The ItemID of block this seed turns into (wheat or pumpkin stems for instance)
     */
    private int blockType;

    public ItemAloeVeraSeeds(int itemID, int turnIntoID, String name) {
        super(itemID);
        blockType = turnIntoID;
        setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        setUnlocalizedName(name);
        func_111206_d(name);
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4,
            int par5, int par6, int par7, float par8, float par9, float par10) {
        if (par7 != 1) {
            return false;
        } else if (par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)
                && par2EntityPlayer.canPlayerEdit(par4, par5 + 1, par6, par7, par1ItemStack)) {
            int var11 = par3World.getBlockId(par4, par5, par6);

            if ((var11 == Block.dirt.blockID || var11 == Block.sand.blockID || var11 == Block.grass.blockID)
                    && par3World.isAirBlock(par4, par5 + 1, par6)) {
                par3World.setBlock(par4, par5 + 1, par6, this.blockType);
                --par1ItemStack.stackSize;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
