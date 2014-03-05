package projectzulu.common.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import projectzulu.common.api.BlockList;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCoconut extends BlockCocoa {

    public BlockCoconut() {
        super();
        setHardness(0.2F);
        setResistance(5.0F);
        setStepSound(Block.soundTypeWood);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (!this.canBlockStay(par1World, par2, par3, par4)) {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlock(par2, par3, par4, Blocks.air);
        } else if (par1World.rand.nextInt(5) == 0) {
            int var6 = par1World.getBlockMetadata(par2, par3, par4);
            int var7 = func_72219_c(var6);

            if (var7 < 2) {
                ++var7;
                par1World.setBlockMetadataWithNotify(par2, par3, par4, var7 << 2 | getDirection(var6), 3);
            }
        }
    }

    /**
     * Can this block stay at this position. Similar to canPlaceBlockAt except gets checked often with plants.
     */
    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4) {
        int var5 = getDirection(par1World.getBlockMetadata(par2, par3, par4));
        par2 += Direction.offsetX[var5];
        par4 += Direction.offsetZ[var5];
        Block var6 = par1World.getBlock(par2, par3, par4);
        Block var7 = par1World.getBlock(par2, par3 + 1, par4);
        return (BlockList.palmTreeLog.isPresent() && var6 == BlockList.palmTreeLog.get() && var7 != BlockList.palmTreeLog
                .get());
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType() {
        return 28;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube? This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        int var6 = getDirection(var5);
        int var7 = func_72219_c(var5);
        int var8 = 4 + var7 * 2;
        int var9 = 5 + var7 * 2;
        float var10 = var8 / 2.0F;

        switch (var6) {
        case 0:
            this.setBlockBounds((8.0F - var10) / 16.0F, (12.0F - var9) / 16.0F, (15.0F - var8) / 16.0F,
                    (8.0F + var10) / 16.0F, 0.75F, 0.9375F);
            break;
        case 1:
            this.setBlockBounds(0.0625F, (12.0F - var9) / 16.0F, (8.0F - var10) / 16.0F, (1.0F + var8) / 16.0F, 0.75F,
                    (8.0F + var10) / 16.0F);
            break;
        case 2:
            this.setBlockBounds((8.0F - var10) / 16.0F, (12.0F - var9) / 16.0F, 0.0625F, (8.0F + var10) / 16.0F, 0.75F,
                    (1.0F + var8) / 16.0F);
            break;
        case 3:
            this.setBlockBounds((15.0F - var8) / 16.0F, (12.0F - var9) / 16.0F, (8.0F - var10) / 16.0F, 0.9375F, 0.75F,
                    (8.0F + var10) / 16.0F);
        }
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving) {
        int var6 = ((MathHelper.floor_double(par5EntityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3) + 0) % 4;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, var6, 3);
    }

    // /**
    // * called before onBlockPlacedBy by ItemBlock and ItemReed
    // */
    // @Override
    // public void updateBlockMetadata(World par1World, int par2, int par3, int par4, int par5, float par6, float par7,
    // float par8)
    // {
    // if (par5 == 1 || par5 == 0)
    // {
    // par5 = 2;
    // }
    //
    // int var9 = Direction.footInvisibleFaceRemap[Direction.vineGrowth[par5]];
    // par1World.setBlockMetadataWithNotify(par2, par3, par4, var9, 3);
    // }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
        if (!this.canBlockStay(par1World, par2, par3, par4)) {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlock(par2, par3, par4, Blocks.air);
        }
    }

    public static int func_72219_c(int par0) {
        return (par0 & 12) >> 2;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    @Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
        if (!par1World.isRemote) {
            ArrayList<ItemStack> items = getDrops(par1World, par2, par3, par4, par5, par7);

            for (ItemStack item : items) {
                if (par1World.rand.nextFloat() <= par6) {
                    this.dropBlockAsItem(par1World, par2, par3, par4, item);
                }
            }
        }
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if (metadata >= 8) {
            if (ItemList.coconutItem.isPresent()) {
                ret.add(new ItemStack(ItemList.coconutItem.get()));
            }
            return ret;
        } else {
            if (ItemList.coconutSeed.isPresent()) {
                ret.add(new ItemStack(ItemList.coconutSeed.get()));
            }
            return ret;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public ItemStack getPickBlock(MovingObjectPosition target, World par1World, int par2, int par3, int par4) {
        return ItemList.coconutSeed.isPresent() ? new ItemStack(ItemList.coconutSeed.get()) : null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World par1World, int par2, int par3, int par4) {
        return 0;
    }
}
