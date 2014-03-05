package projectzulu.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.BlockList;

public class BlockPalmTreeSapling extends BlockBush {
    public BlockPalmTreeSapling() {
        super(Material.plants);
        float var3 = 0.4F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (!par1World.isRemote) {
            super.updateTick(par1World, par2, par3, par4, par5Random);

            if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(7) == 0) {
                this.growTree(par1World, par2, par3, par4, par5Random);
            }
        }
    }

    /**
     * Attempts to grow a sapling into a tree
     */
    public void growTree(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (!par1World.isRemote && BlockList.palmTreeLeaves.isPresent() && BlockList.palmTreeLog.isPresent()) {
            int height = par5Random.nextInt(3) + 4;

            // Check if there is water nearby 9x9

            // As loop finds water it incremement direction towards it,
            // The favored direction at the end of the loop will be towards the side with more water
            int favoredDirectionX = 0;
            int favoredDirectionZ = 0;

            for (int i = -4; i <= 4; i++) {
                for (int k = -4; k <= 4; k++) {
                    for (int j = -4; j <= 4; j++) {
                        Block ID = par1World.getBlock(par2 + i, par3 + j, par4 + k);
                        if (ID == Blocks.flowing_water || ID == Blocks.water) {
                            // Add +/- 1 to favored Direcion indicating the direction water is in
                            if (i != 0) {
                                favoredDirectionX += i / Math.abs(i);
                            }
                            if (k != 0) {
                                favoredDirectionZ += k / Math.abs(k);
                            }
                        }
                    }
                }
            }
            // TBD: If favoredDirectionX and Z are almost the same, greater than 2 and Rare chance, spawn multiple
            // trees.

            // Set FavoredDirection that is less to zero, as we don't want to grow a tree in that direction
            if (Math.abs(favoredDirectionX) - Math.abs(favoredDirectionZ) >= 0) {
                favoredDirectionZ = 0;
            } else {
                favoredDirectionX = 0;
            }

            // Temp variables used in placing log blocks, work wrt global coordinats of block
            int localX = 0;
            int localY = 0;
            int localZ = 0;

            // Adjusts the 'cost' of placing a block horizontally, higher means less horizontal variance
            // Does not affect vertical, which is set by height
            int horizontalFactor = par5Random.nextInt(10) + 20;
            Block palmTreeLogID = BlockList.palmTreeLog.get();
            while (localY <= height) {
                // Place Log above by 1
                localY++;
                par1World.setBlock(par2 + localX, par3 + localY, par4 + localZ, palmTreeLogID);

                if (favoredDirectionX > 0) {
                    localX++;
                    favoredDirectionX = Math.max(favoredDirectionX - horizontalFactor, 0);
                    par1World.setBlock(par2 + localX, par3 + localY, par4 + localZ, palmTreeLogID);

                } else if (favoredDirectionX < 0) {
                    localX--;
                    favoredDirectionX = Math.min(favoredDirectionX + horizontalFactor, 0);
                    par1World.setBlock(par2 + localX, par3 + localY, par4 + localZ, palmTreeLogID);

                }

                if (favoredDirectionZ > 0) {
                    localZ++;
                    favoredDirectionZ = Math.max(favoredDirectionZ - horizontalFactor, 0);
                    par1World.setBlock(par2 + localX, par3 + localY, par4 + localZ, palmTreeLogID);

                } else if (favoredDirectionZ < 0) {
                    localZ--;
                    favoredDirectionZ = Math.min(favoredDirectionZ + horizontalFactor, 0);
                    par1World.setBlock(par2 + localX, par3 + localY, par4 + localZ, palmTreeLogID);
                }

                if (localY + 1 == height) {
                    localY++;
                    par1World.setBlock(par2 + localX, par3 + localY, par4 + localZ, palmTreeLogID);
                    localY++;
                    spawnLeaves(par1World, par2 + localX, par3 + localY, par4 + localZ, par5Random, height);
                    // Place block at original sapling locations
                    par1World.setBlock(par2, par3, par4, palmTreeLogID);
                }
            }
        }
    }

    public void spawnLeaves(World par1World, int par2, int par3, int par4, Random par5Random, int height) {
        Block palmTreeLeavesID = BlockList.palmTreeLeaves.get();

        // TODO: Add more Leave Spawn Templates
        if (height + 1 >= 7) {
            par1World.setBlock(par2, par3, par4, palmTreeLeavesID);
            par1World.setBlock(par2 - 1, par3, par4, palmTreeLeavesID);
            par1World.setBlock(par2 - 2, par3, par4, palmTreeLeavesID);
            par1World.setBlock(par2 - 3, par3, par4, palmTreeLeavesID);
            par1World.setBlock(par2 - 4, par3 - 1, par4, palmTreeLeavesID);

            par1World.setBlock(par2 + 1, par3, par4, palmTreeLeavesID);
            par1World.setBlock(par2 + 2, par3, par4, palmTreeLeavesID);
            par1World.setBlock(par2 + 3, par3, par4, palmTreeLeavesID);
            par1World.setBlock(par2 + 3, par3 - 1, par4, palmTreeLeavesID);

            par1World.setBlock(par2, par3, par4 - 1, palmTreeLeavesID);
            par1World.setBlock(par2, par3, par4 - 2, palmTreeLeavesID);
            par1World.setBlock(par2, par3, par4 - 3, palmTreeLeavesID);
            par1World.setBlock(par2, par3 - 1, par4 - 3, palmTreeLeavesID);

            par1World.setBlock(par2, par3, par4 + 1, palmTreeLeavesID);
            par1World.setBlock(par2, par3, par4 + 2, palmTreeLeavesID);
            par1World.setBlock(par2, par3, par4 + 3, palmTreeLeavesID);
            par1World.setBlock(par2, par3 - 1, par4 + 3, palmTreeLeavesID);

        } else {
            par1World.setBlock(par2, par3, par4, palmTreeLeavesID);
            par1World.setBlock(par2 - 1, par3, par4, palmTreeLeavesID);
            par1World.setBlock(par2 - 2, par3, par4, palmTreeLeavesID);
            par1World.setBlock(par2 - 2, par3 - 1, par4, palmTreeLeavesID);

            par1World.setBlock(par2 + 1, par3, par4, palmTreeLeavesID);
            par1World.setBlock(par2 + 2, par3, par4, palmTreeLeavesID);
            par1World.setBlock(par2 + 2, par3 - 1, par4, palmTreeLeavesID);

            par1World.setBlock(par2, par3, par4 - 1, palmTreeLeavesID);
            par1World.setBlock(par2, par3, par4 - 2, palmTreeLeavesID);
            par1World.setBlock(par2, par3 - 1, par4 - 2, palmTreeLeavesID);

            par1World.setBlock(par2, par3, par4 + 1, palmTreeLeavesID);
            par1World.setBlock(par2, par3, par4 + 2, palmTreeLeavesID);
            par1World.setBlock(par2, par3 - 1, par4 + 2, palmTreeLeavesID);
        }

    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
            int par6, float par7, float par8, float par9) {
        ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();
        if (itemstack != null && itemstack.getItem() == Items.dye) {
            if (itemstack.getItemDamage() == 15) {
                growTree(par1World, par2, par3, par4, par1World.rand);
                if (!par5EntityPlayer.capabilities.isCreativeMode) {
                    itemstack.stackSize--;
                }
            }
        }
        super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
        return true;
    }

    @Override
    protected boolean canPlaceBlockOn(Block block) {
        return block == Blocks.sand || block == Blocks.dirt || block == Blocks.grass;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    @Override
    public int damageDropped(int par1) {
        return par1;
    }
}
