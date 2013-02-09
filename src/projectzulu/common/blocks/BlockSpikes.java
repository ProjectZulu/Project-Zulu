package projectzulu.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemGenerics;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSpikes extends Block{

	public BlockSpikes(int i){
		super(i, Material.iron);
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
		this.disableStats();
		this.setRequiresSelfNotify();
        this.setBlockBounds(0f, 0.0F, 0.0f, 1.0f, 0.5f, 1.0f);
        blockIndexInTexture = 33;
	}

	@SideOnly(Side.CLIENT)
	public String getTextureFile(){
		return DefaultProps.blockSpriteSheet;
	}


	@Override
	public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
		if(par2 > 11){
			return 35;
		}else if(par2 > 5){
			return 34;
		}else{
			return 33;
		}
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1iBlockAccess,
			int par2, int par3, int par4) {
        if(par1iBlockAccess.getBlockId(par2, par3-1, par4) == Block.fence.blockID || par1iBlockAccess.getBlockId(par2, par3-1, par4) == Block.netherFence.blockID ){
        	this.setBlockBounds(0.375f, 0.0F, 0.375f, 0.625f, 0.4f, 0.625f);
            
		}else if(par1iBlockAccess.getBlockId(par2, par3-1, par4) == Block.fenceIron.blockID){
			
            boolean isNegZ = canThisPaneConnectToThisBlockID( par1iBlockAccess.getBlockId(par2, par3-1, par4 - 1) );
            boolean isPosZ = canThisPaneConnectToThisBlockID( par1iBlockAccess.getBlockId(par2, par3-1, par4 + 1) );
            boolean isNegX = canThisPaneConnectToThisBlockID( par1iBlockAccess.getBlockId(par2 - 1, par3-1, par4) );
            boolean isPosX = canThisPaneConnectToThisBlockID( par1iBlockAccess.getBlockId(par2 + 1, par3-1, par4) );
            
        	float minX = !isNegX ? 0.4f : 0.0f;
        	float maxX = !isPosX ? 0.6f : 1.0f;
        	float minZ = !isNegZ ? 0.4f : 0.0f;
        	float maxZ = !isPosZ ? 0.6f : 1.0f;
        	
        	if(!isNegZ && !isPosZ && !isNegX && !isPosX){
            	this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.4f, 1.0f);
        	}else{
            	this.setBlockBounds(minX, 0.0f, minZ, maxX, 0.4f, maxZ);
        	}
			
		}else{
			int blockMeta = par1iBlockAccess.getBlockMetadata(par2, par3, par4);
			switch ( par1iBlockAccess.getBlockMetadata(par2, par3, par4) ) {
			case 0:
		        this.setBlockBounds(0f, 0.0F, 0.0f, 1.0f, 0.5f, 1.0f);
				break;
			case 1:
		        this.setBlockBounds(0.0f, 0.0F, 0.0f, 0.5f, 1.0f, 1.0f);
				break;
			case 2:
		        this.setBlockBounds(0.0f, 0.0F, 0.0f, 1.0f, 1.0f, 0.5f);
				break;
			case 3:
		        this.setBlockBounds(0.5f, 0.0F, 0.0f, 1.0f, 1.0f, 1.0f);
				break;
			case 4:
		        this.setBlockBounds(0f, 0.0F, 0.5f, 1.0f, 1.0f, 1.0f);
				break;
			case 5:
		        this.setBlockBounds(0f, 0.5F, 0.0f, 1.0f, 1.0f, 1.0f);
				break;
				
				
			case 6:
		        this.setBlockBounds(0f, 0.0F, 0.0f, 1.0f, 0.5f, 1.0f);
				break;
			case 7:
		        this.setBlockBounds(0.0f, 0.0F, 0.0f, 0.5f, 1.0f, 1.0f);
				break;
			case 8:
		        this.setBlockBounds(0.0f, 0.0F, 0.0f, 1.0f, 1.0f, 1.0f);
				break;
			case 9:
		        this.setBlockBounds(0.5f, 0.0F, 0.0f, 1.0f, 1.0f, 1.0f);
				break;
			case 10:
		        this.setBlockBounds(0f, 0.0F, 0.5f, 1.0f, 1.0f, 1.0f);
				break;
			case 11:
		        this.setBlockBounds(0f, 0.5F, 0.0f, 1.0f, 1.0f, 1.0f);
				break;

			default:
				break;
			}
		}
        
	}
	
	public int getRenderType(){
		return ProjectZulu_Core.spikeRenderID;
	}

	public boolean isOpaqueCube(){
		return false;
	}

	public boolean renderAsNormalBlock(){
		return false;
	}
	
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4){
        return null;
    }

	public int quantityDropped(Random random){
		return 1;
	}

	/**
	 * checks to see if you can place this block can be placed on that side of a block: BlockLever overrides
	 */
	public boolean canPlaceBlockOnSide(World par1World, int par2, int par3, int par4, int par5){
		return this.canPlaceBlockAt(par1World, par2, par3, par4);
	}

	/**
	 * Returns the default ambient occlusion value based on block opacity
	 */
	@Override
	public void onEntityWalking(World par1World, int par2, int par3, int par4, Entity par5Entity) {
//		ModLoader.getMinecraftInstance().thePlayer.addChatMessage("Walking");

	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3,
			int par4, Entity par5Entity) {

		if(par5Entity instanceof EntityLiving && ( 
				(par5Entity.prevPosY > par3 + this.maxY*0.9f && par5Entity.posY < par3 + this.maxY*0.9f)
				|| (par5Entity.prevPosX > par2+0.5 && par5Entity.posX < par2+0.5)
				|| (par5Entity.prevPosZ > par4+0.5 && par5Entity.posZ < par4+0.5) 
				) ){
			/* Check if Spikes are Poison, If So Also Apply Posion with Damage*/
			if(par1World.getBlockMetadata(par2, par3, par4) > 5 && par1World.getBlockMetadata(par2, par3, par4) < 12){
            	((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.poison.id, 40, 1));
			}
			if( par1World.getBlockMetadata(par2, par3, par4) > 11 ){
            	((EntityLiving)par5Entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 40, 4));
			}
			par5Entity.attackEntityFrom(DamageSource.generic, 2);
		}

		super.onEntityCollidedWithBlock(par1World, par2, par3, par4, par5Entity);
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {

		/* Check if Item is Poison Droplet, is so Make Poisonous */
		if( par5EntityPlayer.inventory.getCurrentItem() != null && ItemList.genericCraftingItems1.isPresent() && par5EntityPlayer.inventory.getCurrentItem().getItem().itemID == ItemList.genericCraftingItems1.get().itemID
				&& par5EntityPlayer.inventory.getCurrentItem().getItemDamage() == ItemGenerics.Properties.PoisonDroplet.meta() 
				&& ( par1World.getBlockMetadata(par2, par3, par4) < 6 || par1World.getBlockMetadata(par2, par3, par4) > 11 ) ){
			
			/* Consume if Not Creative */
			if(!par5EntityPlayer.capabilities.isCreativeMode){
				par5EntityPlayer.inventory.getCurrentItem().stackSize = par5EntityPlayer.inventory.getCurrentItem().stackSize - 1;
			}
			
			/* If not Poison (meta<6) increase by 6, if sticky(>11) reduce by 6*/
			if(par1World.getBlockMetadata(par2, par3, par4) < 6){
				par1World.setBlockAndMetadataWithNotify(par2, par3, par4, this.blockID, par1World.getBlockMetadata(par2, par3, par4) + 6 );
			}else{
				par1World.setBlockAndMetadataWithNotify(par2, par3, par4, this.blockID, par1World.getBlockMetadata(par2, par3, par4) - 6 );
			}
		}
		
		/* Check if Item is Sticky Droplet, is so Make Sticky */
		if( par5EntityPlayer.inventory.getCurrentItem() != null && par5EntityPlayer.inventory.getCurrentItem().getItem().itemID == Item.slimeBall.itemID
				&& ( par1World.getBlockMetadata(par2, par3, par4) < 12 ) ){
			
			/* Consume if Not Creative */
			if(!par5EntityPlayer.capabilities.isCreativeMode){
				par5EntityPlayer.inventory.getCurrentItem().stackSize = par5EntityPlayer.inventory.getCurrentItem().stackSize - 1;
			}
			
			/* If not Poison or Sticky (meta<6) increase by 12, if Poison(>6 & <11) increase by 6*/
			if( par1World.getBlockMetadata(par2, par3, par4) < 6 ){
				par1World.setBlockAndMetadataWithNotify(par2, par3, par4, this.blockID, par1World.getBlockMetadata(par2, par3, par4) + 12 );
			}else{
				par1World.setBlockAndMetadataWithNotify(par2, par3, par4, this.blockID, par1World.getBlockMetadata(par2, par3, par4) + 6 );
			}
		}
		return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer,
				par6, par7, par8, par9);
	}
	
	/**
	 * Copied from BlockPane (Iron Fence).
	 * Gets passed in the blockID of the block adjacent and supposed to return true if its allowed to connect to the
	 * type of blockID passed in. Args: blockID
	 */
	private final boolean canThisPaneConnectToThisBlockID(int par1)
    {
        return Block.opaqueCubeLookup[par1] || par1 == Block.fenceIron.blockID || par1 == Block.glass.blockID;
    }
    
	/**
	 * Used to Set MEtadata as the block is placed
	 * called before onBlockPlacedBy by ItemBlock and ItemReed
	 * was called void updateBlockMetadata
	 * was called int func_85104 ~1.4.5 - 1.4.6
	 * was called int onBlockPlaced ~1.4.7
	 */
	@Override
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9){
        int var9 = par1World.getBlockMetadata(par2, par3, par4);
        if (par5 == 1 && this.canPlaceSpikeOn(par1World, par2, par3 - 1, par4)){
            var9 = 0;
        }

        if ( par5 == 2 && this.canPlaceSpikeOn(par1World, par2, par3, par4 + 1) ){
            var9 = 4;
        }

        if (par5 == 3 && this.canPlaceSpikeOn(par1World, par2, par3, par4 - 1) ){
            var9 = 2;
        }

        if (par5 == 4 && this.canPlaceSpikeOn(par1World, par2 + 1, par3, par4) ){
            var9 = 3;
        }

        if (par5 == 5 && this.canPlaceSpikeOn(par1World, par2 - 1, par3, par4) ){
            var9 = 1;
        }
        if (par5 == 0 && this.canPlaceSpikeOn(par1World, par2, par3 + 1, par4) ){
            var9 = 5;
        }

       return var9;
    }
	
    /**
     * Determines if a torch can be placed on the top surface of this block.
     * Useful for creating your own block that torches can be on, such as fences.
     * 
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z Position
     * @return True to allow the torch to be placed
     */
    public boolean canPlaceSpikeOn(World world, int x, int y, int z)
    {
        if (world.doesBlockHaveSolidTopSurface(x, y, z)){
            return true;
        }
        else{
            int id = world.getBlockId(x, y, z);
            return id == Block.glass.blockID || id == Block.pistonBase.blockID 
            		|| id == Block.pistonExtension.blockID || id == Block.pistonStickyBase.blockID;
        }
    }

	/**
	 * Block's chance to react to an entity falling on it.
	 */
	@Override
	public void onFallenUpon(World par1World, int par2, int par3, int par4, Entity par5Entity, float par6){
//		ModLoader.getMinecraftInstance().thePlayer.addChatMessage("Fallen");
	}

}
