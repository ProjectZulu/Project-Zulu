package projectzulu.common.blocks;


import static net.minecraftforge.common.ForgeDirection.DOWN;
import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.UP;
import static net.minecraftforge.common.ForgeDirection.WEST;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import projectzulu.common.ProjectZulu_Blocks;
import projectzulu.common.mod_ProjectZulu;
import projectzulu.common.temperature.ITempBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCampfire extends Block implements ITempBlock{
	
    public BlockCampfire(int par1) {
    	super(par1, Material.wood);
        this.blockIndexInTexture = 17;
        this.setCreativeTab(ProjectZulu_Blocks.projectZuluCreativeTab);
        setTickRandomly(true);
        this.setBlockBounds(0f, 0.0F, 0.0f, 1.0f, 0.35f, 1.0f);
	}
    
    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    	if(par1World.getBlockMetadata(par2, par3, par4) > 1){
    		this.setLightValue(1.0f);
    	}else{
    		this.setLightValue(0);
    	}
    	super.onBlockAdded(par1World, par2, par3, par4);
    }
    
    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z){
    	if(world.getBlockMetadata(x, y, z) > 1){
    		return 15;
    	}else{
    		return 0;
    	}
    }
    
	@SideOnly(Side.CLIENT)
	public String getTextureFile(){
		return "/terrain.png";
	}
    
    @Override
    public int getRenderType() {
    	return mod_ProjectZulu.campFireRenderID;
    }
    
    public boolean isOpaqueCube(){
		return false;
	}

	public boolean renderAsNormalBlock(){
		return false;
	}
	
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4,
			Random par5Random) {
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		
		/* Handle Fire Spreading, if Stone only Upwards, if Wood adjacent and upwards */
		if (par1World.getGameRules().getGameRuleBooleanValue("doFireTick")){
			
			if(meta > 1){
				if( canBlockCatchFire(par1World, par2, par3+1, par4, ForgeDirection.UP) ){
					par1World.setBlockWithNotify(par2, par3+1, par4, Block.fire.blockID);
				}
			}
			
			if(meta == 2){
				if( 	  canBlockCatchFire(par1World, par2,   par3, par4+1, ForgeDirection.NORTH) ){
					par1World.setBlockWithNotify(	   par2,   par3, par4+1, Block.fire.blockID);
				}else if( canBlockCatchFire(par1World, par2,   par3, par4-1, ForgeDirection.SOUTH) ){
					par1World.setBlockWithNotify(	   par2,   par3, par4-1, Block.fire.blockID);
				}else if( canBlockCatchFire(par1World, par2+1, par3, par4,   ForgeDirection.WEST) ){
					par1World.setBlockWithNotify(	   par2+1, par3, par4,   Block.fire.blockID);
				}else if( canBlockCatchFire(par1World, par2-1, par3, par4,   ForgeDirection.EAST) ){
					par1World.setBlockWithNotify(	   par2-1, par3, par4,   Block.fire.blockID);
				}
			}

        }
		super.updateTick(par1World, par2, par3, par4, par5Random);
	}
	
	   /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4){
		return AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double)par2 + this.minX, (double)par3 + this.minY, (double)par4 + this.minZ, (double)par2 + this.maxX, (double)par3 + 0.3, (double)par4 + this.maxZ);
	}
	
    /**
     * checks to see if you can place this block can be placed on that side of a block: BlockLever overrides
     */
    public boolean canPlaceBlockOnSide(World par1World, int par2, int par3, int par4, int par5){
    	
    	if (par5 != 1){
            return false;
        }

    	return this.canPlaceBlockAt(par1World, par2, par3, par4);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List){
    	for (int var4 = 0; var4 < 4; ++var4){
    		par3List.add(new ItemStack(par1, 1, var4));
    	}
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3,
    		int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
    		float par8, float par9) {
    	
    	/* Make sure Player Item is not Null*/
    	if( ((EntityPlayer)par5EntityPlayer).getCurrentEquippedItem() != null){
    		/* If Fire is not Lit and Coal is in Hand, Light Fire */
    		if( par1World.getBlockMetadata(par2, par3, par4) < 2 && ( ((EntityPlayer)par5EntityPlayer).getCurrentEquippedItem().getItem().shiftedIndex == (Item.coal.shiftedIndex)) ){
    			if( !((EntityPlayer)par5EntityPlayer).capabilities.isCreativeMode){
    				((EntityPlayer)par5EntityPlayer).getCurrentEquippedItem().stackSize -= 1;
    			}
    			par1World.setBlockAndMetadata(par2, par3, par4, this.blockID, par1World.getBlockMetadata(par2, par3, par4) + 2);
    			return true;
    		}
    		
    		/* If Fire is Lit and Water is in Hand, Put out Fire */
    		if( par1World.getBlockMetadata(par2, par3, par4) > 1 && ((EntityPlayer)par5EntityPlayer).getCurrentEquippedItem().getItem().shiftedIndex == (Item.bucketWater.shiftedIndex) ){

    			if( !((EntityPlayer)par5EntityPlayer).capabilities.isCreativeMode){
    				((EntityPlayer)par5EntityPlayer).inventory.setInventorySlotContents(((EntityPlayer)par5EntityPlayer).inventory.currentItem, new ItemStack(Item.bucketEmpty));
    			}
    			par1World.setBlockAndMetadata(par2, par3, par4, this.blockID, par1World.getBlockMetadata(par2, par3, par4) - 2);
    			return true;
    		}
    	}   
    	return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
	}
    
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3,
    		int par4, Random par5Random) {

    	if(par1World.getBlockMetadata(par2, par3, par4) > 1){
    		if (par5Random.nextInt(24) == 0){
    			par1World.playSound((double)((float)par2 + 0.5F), (double)((float)par3 + 0.5F), (double)((float)par4 + 0.5F), "fire.fire", 1.0F + par5Random.nextFloat(), par5Random.nextFloat() * 0.7F + 0.3F, false);
    		}

    		int var6;
    		float var7;
    		float var8;
    		float var9;

    		if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) && !canBlockCatchFire(par1World, par2, par3 - 1, par4, UP)){
    			if (Block.fire.canBlockCatchFire(par1World, par2 - 1, par3, par4, EAST)){
    				for (var6 = 0; var6 < 2; ++var6){
    					var7 = (float)par2 + par5Random.nextFloat() * 0.1F;
    					var8 = (float)par3 + par5Random.nextFloat();
    					var9 = (float)par4 + par5Random.nextFloat();
    					par1World.spawnParticle("largesmoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
    				}
    			}

    			if (canBlockCatchFire(par1World, par2 + 1, par3, par4, WEST)){
    				for (var6 = 0; var6 < 2; ++var6){
    					var7 = (float)(par2 + 1) - par5Random.nextFloat() * 0.1F;
    					var8 = (float)par3 + par5Random.nextFloat();
    					var9 = (float)par4 + par5Random.nextFloat();
    					par1World.spawnParticle("largesmoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
    				}
    			}

    			if (canBlockCatchFire(par1World, par2, par3, par4 - 1, SOUTH)){
    				for (var6 = 0; var6 < 2; ++var6){
    					var7 = (float)par2 + par5Random.nextFloat();
    					var8 = (float)par3 + par5Random.nextFloat();
    					var9 = (float)par4 + par5Random.nextFloat() * 0.1F;
    					par1World.spawnParticle("largesmoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
    				}
    			}

    			if (canBlockCatchFire(par1World, par2, par3, par4 + 1, NORTH)){
    				for (var6 = 0; var6 < 2; ++var6){
    					var7 = (float)par2 + par5Random.nextFloat();
    					var8 = (float)par3 + par5Random.nextFloat();
    					var9 = (float)(par4 + 1) - par5Random.nextFloat() * 0.1F;
    					par1World.spawnParticle("largesmoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
    				}
    			}

    			if (canBlockCatchFire(par1World, par2, par3 + 1, par4, DOWN)){
    				for (var6 = 0; var6 < 2; ++var6){
    					var7 = (float)par2 + par5Random.nextFloat();
    					var8 = (float)(par3 + 1) - par5Random.nextFloat() * 0.1F;
    					var9 = (float)par4 + par5Random.nextFloat();
    					par1World.spawnParticle("largesmoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
    				}
    			}
    		}
    		else{
    			for (var6 = 0; var6 < 3; ++var6){
    				var7 = (float)par2 + par5Random.nextFloat();
    				var8 = (float)par3 + par5Random.nextFloat() * 0.5F + 0.5F;
    				var9 = (float)par4 + par5Random.nextFloat();
    				par1World.spawnParticle("largesmoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
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
    public boolean canBlockCatchFire(IBlockAccess world, int x, int y, int z, ForgeDirection face)
    {
        Block block = Block.blocksList[world.getBlockId(x, y, z)];
        if (block != null){
            return block.isFlammable(world, x, y, z, world.getBlockMetadata(x, y, z), face);
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
    public int getChanceToEncourageFire(World world, int x, int y, int z, int oldChance, ForgeDirection face){
        int newChance = 0;
        Block block = Block.blocksList[world.getBlockId(x, y, z)];
        if (block != null){
            newChance = block.getFireSpreadSpeed(world, x, y, z, world.getBlockMetadata(x, y, z), face);
        }
        return (newChance > oldChance ? newChance : oldChance);
    }
    
    
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3,
			int par4, Entity par5Entity) {
		if( par1World.getBlockMetadata(par2, par3, par4) > 1 && par1World.getGameRules().getGameRuleBooleanValue("doesCampfireBurn")){
			par5Entity.setFire(1);
		}
		super.onEntityCollidedWithBlock(par1World, par2, par3, par4, par5Entity);
	}
	
	@Override
	public float getAddToPlayTempByNearbyBlock(EntityPlayer player,
			int blockPosX, int blockPosY, int blockPosZ, Float playerTemp,
			float playerLocationTemp){
		return 0.0f;
	}

	@Override
	public float getAddToLocTempByNearbyBlock(EntityPlayer player,
			int blockPosX, int blockPosY, int blockPosZ, Float playerTemp,
			float playerLocationTemp){
		if(player.worldObj.getBlockMetadata(blockPosX, blockPosY, blockPosZ) > 1){
			return 0.1f;
		}
		return 0.00f;
	}

	@Override
	public float getAddToHeatTransferByBlock(EntityPlayer player,
			float playerTemp, float playerLocationTemp, float currentHeatRate){
		return 0.0f;
	}

	@Override
	public boolean getBooleanCauseFastHeatTransferByBlock(EntityPlayer player,
			float playerTemp, float playerLocationTemp, float currentHeatRate){
		if(playerLocationTemp > playerTemp){
			return true;
		}else{
			return false;
		}
	}
}
