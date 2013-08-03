package projectzulu.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCreeperBlossom extends BlockFlower {

    public BlockCreeperBlossom(int par1) {
        super(par1, Material.tnt);
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        setHardness(0.5F);
        setStepSound(Block.soundGrassFootstep);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
        return super.getBlockTexture(par1iBlockAccess, par2, par3, par4, par5);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random par1Random) {
        return 1;
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4) {
        if (!par1World.isRemote) {
            EntityCreeperBlossomPrimed var5 = new EntityCreeperBlossomPrimed(par1World, par2 + 0.5F, par3 + 0.5F,
                    par4 + 0.5F);
            var5.fuse = par1World.rand.nextInt(var5.fuse / 4) + var5.fuse / 8;
            par1World.spawnEntityInWorld(var5);
        }
    }

    /**
     * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
     */
    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {

        if (par5Entity instanceof EntityPlayer && !par1World.isRemote && !((EntityPlayer) par5Entity).isSneaking()) {
            EntityCreeperBlossomPrimed var6 = new EntityCreeperBlossomPrimed(par1World, par2 + 0.5F, par3 + 0.5F,
                    par4 + 0.5F);
            par1World.spawnEntityInWorld(var6);
            par1World.playSoundAtEntity(var6, "random.fuse", 1.0F, 1.0F);
            par1World.setBlock(par2, par3, par4, 0);
        }
    }

    @Override
    protected boolean canThisPlantGrowOnThisBlockID(int par1) {
        return par1 == Block.grass.blockID || par1 == Block.dirt.blockID || par1 == Block.tilledField.blockID
                || par1 == Block.wood.blockID;
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    @Override
    protected ItemStack createStackedBlock(int par1) {
        return null;
    }

}
