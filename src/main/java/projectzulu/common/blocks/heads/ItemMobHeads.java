package projectzulu.common.blocks.heads;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import projectzulu.common.api.BlockList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMobHeads extends ItemBlock {

    public ItemMobHeads(Block mobHeads) {
        super(mobHeads);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        setMaxDamage(0);
        setHasSubtypes(true);
        maxStackSize = 8;
    }

    // @Override
    // @SideOnly(Side.CLIENT)
    // public IIcon getIconFromDamage(int par1) {
    // return Block.blocksList[this.itemID].getIcon(2, par1);
    // }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4,
            int par5, int par6, int par7, float par8, float par9, float par10) {
        if (par7 == 0) {
            return false;
        } else if (!par3World.getBlock(par4, par5, par6).getMaterial().isSolid()) {
            return false;
        } else {
            if (par7 == 1) {
                ++par5;
            }

            if (par7 == 2) {
                --par6;
            }

            if (par7 == 3) {
                ++par6;
            }

            if (par7 == 4) {
                --par4;
            }

            if (par7 == 5) {
                ++par4;
            }

            if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)) {
                return false;
            } else if (!BlockList.mobHeads.get().canPlaceBlockAt(par3World, par4, par5, par6)) {
                return false;
            } else if (BlockList.mobHeads.isPresent()) {
                par3World.setBlock(par4, par5, par6, BlockList.mobHeads.get(), par7, 3);
                int var11 = 0;

                if (par7 == 1) {
                    var11 = MathHelper.floor_double((double) (par2EntityPlayer.rotationYaw * 16.0F / 360.0F) + 0.5D) & 15;
                }

                TileEntity var12 = par3World.getTileEntity(par4, par5, par6);

                if (var12 != null && var12 instanceof TileEntityMobHeads) {
                    String var13 = "";

                    if (par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey("SkullOwner")) {
                        var13 = par1ItemStack.getTagCompound().getString("SkullOwner");
                    }

                    ((TileEntityMobHeads) var12).setSkullType(par1ItemStack.getItemDamage(), var13);
                    ((TileEntityMobHeads) var12).setRotation(var11);
                    /* Was Used to Summon wither For Block Skulls */
                    // ((BlockSkull)Block.field_82512_cj).func_82529_a(par3World, par4, par5, par6,
                    // (TileEntitySkull)var12);
                }

                --par1ItemStack.stackSize;
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int par1) {
        return par1;
    }

    /*
     * for every block, you need a name. it doesn't matter, really. its just so all your blocks wont have the same name.
     * delete this part and all your blocks have the same name.
     */
    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return itemstack.getItem().getUnlocalizedName() + "."
                + BlockMobHeads.Head.getByMeta(itemstack.getItemDamage()).toString().toLowerCase();
    }

    @Override
    public IIcon getIconFromDamage(int par1) {
        return field_150939_a.getIcon(1, par1);
    }
}
