package projectzulu.common.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.temperature.ITempBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCampfire extends Block implements ITempBlock {
    public enum Type {
        Wood(0, "Wood Campfire"), Stone(1, "Stone Campfire"), WoodFire(2, "Lit Campfire"), StoneFire(3,
                "Lit Stone Campfire");

        private int meta;
        private final String displayName;
        @SideOnly(Side.CLIENT)
        private IIcon icon;

        Type(int meta, String displayName) {
            this.meta = meta;
            this.displayName = displayName;
        }

        public int meta() {
            return meta;
        }

        public String displayName() {
            return displayName;
        }

        public void setIcon(IIcon icon) {
            this.icon = icon;
        }

        public IIcon getIcon() {
            return icon;
        }

        public static Type getTypeByMeta(int meta) {
            for (Type type : Type.values()) {
                if (type.meta == meta) {
                    return type;
                }
            }
            return null;
        }
    }

    public final int renderID;

    public BlockCampfire(int renderID) {
        super(Material.wood);
        setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        setTickRandomly(true);
        setBlockBounds(0f, 0.0F, 0.0f, 1.0f, 0.35f, 1.0f);
        setHardness(0.5F);
        setStepSound(Block.soundTypeStone);
        this.renderID = renderID;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        return BlockCampfire.Type.getTypeByMeta(par2).getIcon();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        for (Type type : Type.values()) {
            type.setIcon(par1IconRegister.registerIcon(getTextureName() + "_" + type.toString().toLowerCase()));
        }
    }

    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
        if (par1World.getBlockMetadata(par2, par3, par4) > 1) {
            this.setLightLevel(1.0f);
        } else {
            this.setLightLevel(0);
        }
        super.onBlockAdded(par1World, par2, par3, par4);
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        if (world.getBlockMetadata(x, y, z) > 1) {
            return 15;
        } else {
            return 0;
        }
    }

    @Override
    public int getRenderType() {
        return renderID;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        int meta = par1World.getBlockMetadata(par2, par3, par4);

        /* Handle Fire Spreading, if Stone only Upwards, if Wood adjacent and upwards */
        if (par1World.getGameRules().getGameRuleBooleanValue("doFireTick")) {

            if (meta > 1) {
                if (canBlockCatchFire(par1World, par2, par3 + 1, par4, ForgeDirection.UP)) {
                    par1World.setBlock(par2, par3 + 1, par4, Blocks.fire);
                }
            }

            if (meta == 2) {
                if (canBlockCatchFire(par1World, par2, par3, par4 + 1, ForgeDirection.NORTH)) {
                    par1World.setBlock(par2, par3, par4 + 1, Blocks.fire);
                } else if (canBlockCatchFire(par1World, par2, par3, par4 - 1, ForgeDirection.SOUTH)) {
                    par1World.setBlock(par2, par3, par4 - 1, Blocks.fire);
                } else if (canBlockCatchFire(par1World, par2 + 1, par3, par4, ForgeDirection.WEST)) {
                    par1World.setBlock(par2 + 1, par3, par4, Blocks.fire);
                } else if (canBlockCatchFire(par1World, par2 - 1, par3, par4, ForgeDirection.EAST)) {
                    par1World.setBlock(par2 - 1, par3, par4, Blocks.fire);
                }
            }

        }
        super.updateTick(par1World, par2, par3, par4, par5Random);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return AxisAlignedBB.getBoundingBox(par2 + this.minX, par3 + this.minY, par4 + this.minZ,
                par2 + this.maxX, par3 + 0.3, par4 + this.maxZ);
    }

    /**
     * checks to see if you can place this block can be placed on that side of a block: BlockLever overrides
     */
    @Override
    public boolean canPlaceBlockOnSide(World par1World, int par2, int par3, int par4, int par5) {

        if (par5 != 1) {
            return false;
        }

        return this.canPlaceBlockAt(par1World, par2, par3, par4);
    }

    @SideOnly(Side.CLIENT)
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (Type type : Type.values()) {
            par3List.add(new ItemStack(this, 1, type.meta));
        }
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
            int par6, float par7, float par8, float par9) {

        /* Make sure Player Item is not Null */
        if (par5EntityPlayer.getCurrentEquippedItem() != null) {
            /* If Fire is not Lit and Coal is in Hand, Light Fire */
            if (par1World.getBlockMetadata(par2, par3, par4) < 2
                    && (par5EntityPlayer.getCurrentEquippedItem().getItem() == Items.coal)) {
                if (!par5EntityPlayer.capabilities.isCreativeMode) {
                    par5EntityPlayer.getCurrentEquippedItem().stackSize -= 1;
                }
                par1World.setBlockMetadataWithNotify(par2, par3, par4,
                        par1World.getBlockMetadata(par2, par3, par4) + 2, 3);
                return true;
            }

            /* If Fire is Lit and Water is in Hand, Put out Fire */
            if (par1World.getBlockMetadata(par2, par3, par4) > 1
                    && par5EntityPlayer.getCurrentEquippedItem().getItem() == Items.water_bucket) {

                if (!par5EntityPlayer.capabilities.isCreativeMode) {
                    par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem,
                            new ItemStack(Items.bucket));
                }
                par1World.setBlockMetadataWithNotify(par2, par3, par4,
                        par1World.getBlockMetadata(par2, par3, par4) - 2, 3);
                return true;
            }
        }
        return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {

        if (par1World.getBlockMetadata(par2, par3, par4) > 1) {
            if (par5Random.nextInt(24) == 0) {
                par1World.playSound(par2 + 0.5F, par3 + 0.5F, par4 + 0.5F, "fire.fire", 1.0F + par5Random.nextFloat(),
                        par5Random.nextFloat() * 0.7F + 0.3F, false);
            }

            int var6;
            float var7;
            float var8;
            float var9;
            if (!World.doesBlockHaveSolidTopSurface(par1World, par2, par3 - 1, par4)
                    && !canBlockCatchFire(par1World, par2, par3 - 1, par4, ForgeDirection.UP)) {
                if (Blocks.fire.canCatchFire(par1World, par2 - 1, par3, par4, ForgeDirection.UP)) {
                    for (var6 = 0; var6 < 2; ++var6) {
                        var7 = par2 + par5Random.nextFloat() * 0.1F;
                        var8 = par3 + par5Random.nextFloat();
                        var9 = par4 + par5Random.nextFloat();
                        par1World.spawnParticle("largesmoke", var7, var8, var9, 0.0D, 0.0D, 0.0D);
                    }
                }

                if (canBlockCatchFire(par1World, par2 + 1, par3, par4, ForgeDirection.WEST)) {
                    for (var6 = 0; var6 < 2; ++var6) {
                        var7 = par2 + 1 - par5Random.nextFloat() * 0.1F;
                        var8 = par3 + par5Random.nextFloat();
                        var9 = par4 + par5Random.nextFloat();
                        par1World.spawnParticle("largesmoke", var7, var8, var9, 0.0D, 0.0D, 0.0D);
                    }
                }

                if (canBlockCatchFire(par1World, par2, par3, par4 - 1, ForgeDirection.SOUTH)) {
                    for (var6 = 0; var6 < 2; ++var6) {
                        var7 = par2 + par5Random.nextFloat();
                        var8 = par3 + par5Random.nextFloat();
                        var9 = par4 + par5Random.nextFloat() * 0.1F;
                        par1World.spawnParticle("largesmoke", var7, var8, var9, 0.0D, 0.0D, 0.0D);
                    }
                }

                if (canBlockCatchFire(par1World, par2, par3, par4 + 1, ForgeDirection.NORTH)) {
                    for (var6 = 0; var6 < 2; ++var6) {
                        var7 = par2 + par5Random.nextFloat();
                        var8 = par3 + par5Random.nextFloat();
                        var9 = par4 + 1 - par5Random.nextFloat() * 0.1F;
                        par1World.spawnParticle("largesmoke", var7, var8, var9, 0.0D, 0.0D, 0.0D);
                    }
                }

                if (canBlockCatchFire(par1World, par2, par3 + 1, par4, ForgeDirection.DOWN)) {
                    for (var6 = 0; var6 < 2; ++var6) {
                        var7 = par2 + par5Random.nextFloat();
                        var8 = par3 + 1 - par5Random.nextFloat() * 0.1F;
                        var9 = par4 + par5Random.nextFloat();
                        par1World.spawnParticle("largesmoke", var7, var8, var9, 0.0D, 0.0D, 0.0D);
                    }
                }
            } else {
                for (var6 = 0; var6 < 3; ++var6) {
                    var7 = par2 + par5Random.nextFloat();
                    var8 = par3 + par5Random.nextFloat() * 0.5F + 0.5F;
                    var9 = par4 + par5Random.nextFloat();
                    par1World.spawnParticle("largesmoke", var7, var8, var9, 0.0D, 0.0D, 0.0D);
                }
            }

        }

        super.randomDisplayTick(par1World, par2, par3, par4, par5Random);
    }

    /**
     * Side sensitive version that calls the block function.
     * 
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z Position
     * @param face The side the fire is coming from
     * @return True if the face can catch fire.
     */
    public boolean canBlockCatchFire(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        Block block = world.getBlock(x, y, z);
        if (block != null) {
            return block.isFlammable(world, x, y, z, face);
        }
        return false;
    }

    /**
     * Side sensitive version that calls the block function.
     * 
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z Position
     * @param oldChance The previous maximum chance.
     * @param face The side the fire is coming from
     * @return The chance of the block catching fire, or oldChance if it is higher
     */
    public int getChanceToEncourageFire(World world, int x, int y, int z, int oldChance, ForgeDirection face) {
        int newChance = 0;
        Block block = world.getBlock(x, y, z);
        if (block != null) {
            newChance = block.getFireSpreadSpeed(world, x, y, z, face);
        }
        return (newChance > oldChance ? newChance : oldChance);
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
        if (par1World.getBlockMetadata(par2, par3, par4) > 1
                && par1World.getGameRules().getGameRuleBooleanValue("doesCampfireBurn")) {
            par5Entity.setFire(1);
        }
        super.onEntityCollidedWithBlock(par1World, par2, par3, par4, par5Entity);
    }

    @Override
    public float getAddToPlayTempByNearbyBlock(EntityPlayer player, int blockPosX, int blockPosY, int blockPosZ,
            Float playerTemp, float playerLocationTemp) {
        return 0.0f;
    }

    @Override
    public float getAddToLocTempByNearbyBlock(EntityPlayer player, int blockPosX, int blockPosY, int blockPosZ,
            Float playerTemp, float playerLocationTemp) {
        if (player.worldObj.getBlockMetadata(blockPosX, blockPosY, blockPosZ) > 1) {
            return 0.1f;
        }
        return 0.00f;
    }

    @Override
    public float getAddToHeatTransferByBlock(EntityPlayer player, float playerTemp, float playerLocationTemp,
            float currentHeatRate) {
        return 0.0f;
    }

    @Override
    public boolean getBooleanCauseFastHeatTransferByBlock(EntityPlayer player, float playerTemp,
            float playerLocationTemp, float currentHeatRate) {
        if (playerLocationTemp > playerTemp) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, metadata));
        return ret;
    }
}
