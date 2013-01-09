package projectzulu.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Blocks;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCreeperBlossom extends BlockFlower{

	public BlockCreeperBlossom(int par1, int par2)
	{
		super(par1, par2, Material.tnt);
        this.setCreativeTab(ProjectZulu_Blocks.projectZuluCreativeTab);
	}

	@SideOnly(Side.CLIENT)
	public String getTextureFile()
	{
		return DefaultProps.blockSpriteSheet;
	}

	/**
	 * Returns the block texture based on the side being looked at.  Args: side
	 */
	public int getBlockTextureFromSide(int par1)
	{
		return 101;
	}

//	/**
//	 * Called whenever the block is added into the world. Args: world, x, y, z
//	 */
//	public void onBlockAdded(World par1World, int par2, int par3, int par4)
//	{
//		super.onBlockAdded(par1World, par2, par3, par4);
//
//		if (par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
//		{
//			this.onBlockDestroyedByPlayer(par1World, par2, par3, par4, 1);
//			par1World.setBlockWithNotify(par2, par3, par4, 0);
//		}
//	}



	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
	 * their own) Args: x, y, z, neighbor blockID
	 */
//	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
//	{
//		if (par5 > 0 && Block.blocksList[par5].canProvidePower() && par1World.isBlockIndirectlyGettingPowered(par2, par3, par4))
//		{
//			this.onBlockDestroyedByPlayer(par1World, par2, par3, par4, 1);
//			par1World.setBlockWithNotify(par2, par3, par4, 0);
//		}
//	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int quantityDropped(Random par1Random)
	{
		return 1;
	}

	/**
	 * Called upon the block being destroyed by an explosion
	 */
	public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4)
	{
		if (!par1World.isRemote)
		{
			EntityCreeperBlossomPrimed var5 = new EntityCreeperBlossomPrimed(par1World, (double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F));
			var5.fuse = par1World.rand.nextInt(var5.fuse / 4) + var5.fuse / 8;
			par1World.spawnEntityInWorld(var5);
		}
	}

	/**
	 * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
	 */
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		
		if(par5Entity instanceof EntityPlayer && !par1World.isRemote && !((EntityPlayer)par5Entity).isSneaking()){
			EntityCreeperBlossomPrimed var6 = new EntityCreeperBlossomPrimed(par1World, (double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F));
			par1World.spawnEntityInWorld(var6);
			par1World.playSoundAtEntity(var6, "random.fuse", 1.0F, 1.0F);
			par1World.setBlockWithNotify(par2, par3, par4, 0);
		}
	}


//	/**
//	 * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
//	 */
//	public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5)
//	{
////		if (!par1World.isRemote)
////		{
////			//            if ((par5 & 1) == 1)
////			//            {
////			EntityTNTPrimed var6 = new EntityTNTPrimed(par1World, (double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F));
////			par1World.spawnEntityInWorld(var6);
////			par1World.playSoundAtEntity(var6, "random.fuse", 1.0F, 1.0F);
////			//            }
////		}
//		super.onBlockDestroyedByPlayer(par1World, par2, par3, par4, par5);
//	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int par1)
	{
		return par1 == Block.grass.blockID || par1 == Block.dirt.blockID || par1 == Block.tilledField.blockID || par1 == Block.wood.blockID;
	}

	/**
	 * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
	 * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
	 */
	protected ItemStack createStackedBlock(int par1)
	{
		return null;
	}

}
