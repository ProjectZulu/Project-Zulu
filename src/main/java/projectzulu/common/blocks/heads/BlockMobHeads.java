package projectzulu.common.blocks.heads;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMobHeads extends BlockContainer {
    public enum Head {
        RedFinch(0, "Stuffed Finch"), Crocodile(1, "Alligator Head"), Armadillo(2, "Armadillo Head"), BlackBear(3,
                "Black Bear Head"), BrownBear(4, "Brown Bear Head"), PolarBear(5, "Polar Bear Head"), Beaver(6,
                "Beaver Head"), Boar(7, "Boar Head"), Giraffe(8, "Giraffe Head"), Gorilla(9, "Gorilla Head"), Lizard(
                10, "Lizard Head"), Mammoth(11, "Mammoth Head"), Ostrich(12, "Ostrich Head"), Penguin(13,
                "Penguin Head"), Rhino(14, "Rhino Head"), TreeEnt(15, "TreeEnt Head"), Vulture(16, "Vulture Head"), Elephant(
                17, "Elephant Head");

        private final int meta;

        public int meta() {
            return meta;
        }

        private final String displayName;

        public String displayName() {
            return displayName;
        }

        private IIcon icon;

        private Head(int meta, String displayName) {
            this.meta = meta;
            this.displayName = displayName;
        }

        public static Head getByMeta(int meta) {
            for (Head head : Head.values()) {
                if (head.meta == meta)
                    return head;
            }
            return null;
        }
    }

    public BlockMobHeads() {
        super(Material.circuits);
        setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        setHardness(1.0F);
        setStepSound(Block.soundTypeStone);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        return Head.getByMeta(par2).icon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        for (Head head : Head.values()) {
            head.icon = par1IconRegister.registerIcon(getTextureName() + "_" + head.toString().toLowerCase());
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tabs, List items) {
        for (Head head : Head.values()) {
            items.add(new ItemStack(this, 1, head.meta));
        }
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return 1;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    @Override
    public int damageDropped(int par1) {
        return par1;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    @Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 7;

        switch (var5) {
        case 1:
        default:
            this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
            break;
        case 2:
            this.setBlockBounds(0.25F, 0.25F, 0.5F, 0.75F, 0.75F, 1.0F);
            break;
        case 3:
            this.setBlockBounds(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 0.5F);
            break;
        case 4:
            this.setBlockBounds(0.5F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
            break;
        case 5:
            this.setBlockBounds(0.0F, 0.25F, 0.25F, 0.5F, 0.75F, 0.75F);
        }
    }

    /**
     * Called when the block is attempted to be harvested
     */
    @Override
    public void onBlockHarvested(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
        if (par6EntityPlayer.capabilities.isCreativeMode) {
            par5 |= 8;
            par1World.setBlockMetadataWithNotify(par2, par3, par4, par5, 3);
        }
        super.onBlockHarvested(par1World, par2, par3, par4, par5, par6EntityPlayer);
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        if (!par1World.isRemote) {
            if ((par6 & 8) == 0) {
                this.dropBlockAsItem(par1World, par2, par3, par4,
                        new ItemStack(this, 1, this.getDamageValue(par1World, par2, par3, par4)));
            }
            super.breakBlock(par1World, par2, par3, par4, par5, par6);
        }
    }

    /**
     * Called when the block is placed in the world.
     */
    @Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving,
            ItemStack par6ItemStack) {
        int var6 = MathHelper.floor_double(par5EntityLiving.rotationYaw * 4.0F / 360.0F + 2.5D) & 3;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, var6, 3);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityMobHeads();
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    @Override
    public int getDamageValue(World par1World, int par2, int par3, int par4) {
        TileEntity var5 = par1World.getTileEntity(par2, par3, par4);
        return var5 != null && var5 instanceof TileEntityMobHeads ? ((TileEntityMobHeads) var5).getSkullType() : super
                .getDamageValue(par1World, par2, par3, par4);
    }
}
