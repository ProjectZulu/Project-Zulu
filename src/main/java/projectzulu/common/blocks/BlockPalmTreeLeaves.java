package projectzulu.common.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.BlockList;
import projectzulu.common.api.ItemList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Used ExtrabiomesXL Leaf to Deobfuscate names of Leaf Properties https://github.com/ExtrabiomesXL/ExtrabiomesXL
 */
public class BlockPalmTreeLeaves extends BlockLeavesBase implements IShearable {
    public static final String[] imageSuffix = new String[] { "_opaque", "" };
    int[] adjacentTreeBlocks;

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    private int iconIndex;
    private static final int METADATA_BITMASK = 0x3;
    private static final int METADATA_USERPLACEDBIT = 0x4;
    private static final int METADATA_DECAYBIT = 0x8;
    private static final int METADATA_CLEARDECAYBIT = -METADATA_DECAYBIT - 1;

    public BlockPalmTreeLeaves() {
        super(Material.leaves, false);
        this.setTickRandomly(true);
        setHardness(0.2F);
        setLightOpacity(1);
        setStepSound(Block.soundTypeGrass);
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
    }

    private static int clearDecayOnMetadata(int metadata) {
        return metadata & METADATA_CLEARDECAYBIT;
    }

    private static boolean isDecaying(int metadata) {
        return (metadata & METADATA_DECAYBIT) != 0;
    }

    private static boolean isUserPlaced(int metadata) {
        return (metadata & METADATA_USERPLACEDBIT) != 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        return icons[iconIndex];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.icons = new IIcon[imageSuffix.length];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = par1IconRegister.registerIcon(getTextureName() + imageSuffix[i]);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor() {
        double var1 = 0.5D;
        double var3 = 1.0D;
        return ColorizerFoliage.getFoliageColor(var1, var3);
    }

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int par1) {
        return ColorizerFoliage.getFoliageColorBasic();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
        setGraphicsLevel(Minecraft.getMinecraft().gameSettings.fancyGraphics);
        return super.shouldSideBeRendered(par1iBlockAccess, par2, par3, par4, par5);
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int var6 = 0;
        int var7 = 0;
        int var8 = 0;

        for (int var9 = -1; var9 <= 1; ++var9) {
            for (int var10 = -1; var10 <= 1; ++var10) {
                int var11 = par1IBlockAccess.getBiomeGenForCoords(par2 + var10, par4 + var9).getBiomeFoliageColor(
                        par2 + var10, par3, par4 + var9);
                var6 += (var11 & 16711680) >> 16;
                var7 += (var11 & 65280) >> 8;
                var8 += var11 & 255;
            }
        }
        return (var6 / 9 & 255) << 16 | (var7 / 9 & 255) << 8 | var8 / 9 & 255;
    }

    /**
     * Notidy Nearby Leaves to Begin Decaying
     */
    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        byte decayRadius = 1;
        int chunkCheckRadius = decayRadius + 1;

        /* Chck that Chunk is Loaded */
        if (par1World.checkChunksExist(par2 - chunkCheckRadius, par3 - chunkCheckRadius, par4 - chunkCheckRadius, par2
                + chunkCheckRadius, par3 + chunkCheckRadius, par4 + chunkCheckRadius)) {
            for (int var9 = -decayRadius; var9 <= decayRadius; ++var9) {
                for (int var10 = -decayRadius; var10 <= decayRadius; ++var10) {
                    for (int var11 = -decayRadius; var11 <= decayRadius; ++var11) {

                        Block nearBlockID = par1World.getBlock(par2 + var9, par3 + var10, par4 + var11);
                        if (nearBlockID != null) {
                            nearBlockID.beginLeavesDecay(par1World, par2 + var9, par3 + var10, par4 + var11);
                        }
                    }
                }
            }
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (par1World.isRemote)
            return;

        int metadata = par1World.getBlockMetadata(par2, par3, par4);
        if (isUserPlaced(metadata) || !isDecaying(metadata))
            return;

        byte rangeWood = 4;
        int rangeCheckChunk = rangeWood + 1; // rangeCheckChunk
        byte var9 = 32;
        int var10 = var9 * var9;
        int var11 = var9 / 2;

        if (this.adjacentTreeBlocks == null) {
            this.adjacentTreeBlocks = new int[var9 * var9 * var9];
        }

        int var12;
        if (par1World.checkChunksExist(par2 - rangeCheckChunk, par3 - rangeCheckChunk, par4 - rangeCheckChunk, par2
                + rangeCheckChunk, par3 + rangeCheckChunk, par4 + rangeCheckChunk)) {
            int var13;
            int var14;

            for (var12 = -rangeWood; var12 <= rangeWood; ++var12) {
                for (var13 = -rangeWood; var13 <= rangeWood; ++var13) {
                    for (var14 = -rangeWood; var14 <= rangeWood; ++var14) {
                        Block block = par1World.getBlock(par2 + var12, par3 + var13, par4 + var14);
                        if (block != null
                                && block.canSustainLeaves(par1World, par2 + var12, par3 + var13, par4 + var14)) {
                            this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = 0;
                        } else if (block != null && block.isLeaves(par1World, par2 + var12, par3 + var13, par4 + var14)) {
                            this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -2;
                        } else {
                            this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -1;
                        }
                    }
                }
            }

            int var15;
            for (var12 = 1; var12 <= 4; ++var12) {
                for (var13 = -rangeWood; var13 <= rangeWood; ++var13) {
                    for (var14 = -rangeWood; var14 <= rangeWood; ++var14) {
                        for (var15 = -rangeWood; var15 <= rangeWood; ++var15) {
                            if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15
                                    + var11] == var12 - 1) {
                                if (this.adjacentTreeBlocks[(var13 + var11 - 1) * var10 + (var14 + var11) * var9
                                        + var15 + var11] == -2) {
                                    this.adjacentTreeBlocks[(var13 + var11 - 1) * var10 + (var14 + var11) * var9
                                            + var15 + var11] = var12;
                                }

                                if (this.adjacentTreeBlocks[(var13 + var11 + 1) * var10 + (var14 + var11) * var9
                                        + var15 + var11] == -2) {
                                    this.adjacentTreeBlocks[(var13 + var11 + 1) * var10 + (var14 + var11) * var9
                                            + var15 + var11] = var12;
                                }

                                if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 - 1) * var9
                                        + var15 + var11] == -2) {
                                    this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 - 1) * var9
                                            + var15 + var11] = var12;
                                }

                                if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 + 1) * var9
                                        + var15 + var11] == -2) {
                                    this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 + 1) * var9
                                            + var15 + var11] = var12;
                                }

                                if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9
                                        + (var15 + var11 - 1)] == -2) {
                                    this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9
                                            + (var15 + var11 - 1)] = var12;
                                }

                                if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15
                                        + var11 + 1] == -2) {
                                    this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15
                                            + var11 + 1] = var12;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (adjacentTreeBlocks[var11 * var10 + var11 * var9 + var11] >= 0) {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, clearDecayOnMetadata(metadata), 3);
        } else {
            removeLeaves(par1World, par2, par3, par4);
        }
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (par1World.canLightningStrikeAt(par2, par3 + 1, par4)
                && !World.doesBlockHaveSolidTopSurface(par1World, par2, par3 - 1, par4) && par5Random.nextInt(15) == 1) {
            double var6 = par2 + par5Random.nextFloat();
            double var8 = par3 - 0.05D;
            double var10 = par4 + par5Random.nextFloat();
            par1World.spawnParticle("dripWater", var6, var8, var10, 0.0D, 0.0D, 0.0D);
        }
    }

    private void removeLeaves(World par1World, int par2, int par3, int par4) {
        this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
        par1World.setBlock(par2, par3, par4, Blocks.air);
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        int chance = world.rand.nextInt(100);
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        if (10 - chance >= 0) {
            if (ItemList.coconutItem.isPresent()) {
                ret.add(new ItemStack(ItemList.coconutItem.get()));
            }
            return ret;
        }
        if (30 - chance >= 0) {
            if (BlockList.palmTreeSapling.isPresent()) {
                ret.add(new ItemStack(BlockList.palmTreeSapling.get()));
                return ret;
            }
        }
        return ret;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube? This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * Pass true to draw this block using fancy graphics, or false for fast graphics.
     */
    @SideOnly(Side.CLIENT)
    public void setGraphicsLevel(boolean par1) {
        this.field_150121_P = par1;
        this.iconIndex = par1 ? 0 : 1;
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 3));
        return ret;
    }

    @Override
    public void beginLeavesDecay(World world, int x, int y, int z) {
        world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 3);
    }

    @Override
    public boolean isLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }
}
