package projectzulu.common.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import projectzulu.common.core.ItemBlockList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWateredDirt extends Block{

	public BlockWateredDirt(int par1, int par2){
        super(par1, Material.sand);
		this.setRequiresSelfNotify();
    }
	
	@SideOnly(Side.CLIENT)
	public String getTextureFile()
    {
            return "/mods/blocks_projectzulu.png";
    }

	public int getBlockTextureFromSideAndMetadata(int par1, int par2) 
	{
		if (par2 < 4) {
			this.setStepSound(Block.soundGravelFootstep);
		}else {
			this.setStepSound(Block.soundSandFootstep);
		}

		switch (par2) {
		case 0:
			return 8;
		case 1:
			return 9;
		case 2:
			return 10;
		case 3:
			return 11;

		case 4:
			return 12;
		case 5:
			return 13;
		case 6:
			return 14;
		case 7:
			return 15;

		default:
			return 8;
		}
	}

	public int damageDropped(int par1)
	{
		return par1;
	}

	public void onGameTick() {
		
		
	}
	
	public int quantityDropped(Random random){
		return 1;
	}
	

	public int idDropped(int par1, Random random, int par2){
		switch (par1) {
		case 0:
			return -1;
		case 1:
			return -1;
		case 2:
			return -1;
		case 3:
			return this.blockID;
		case 4:
			return -1;
		case 5:
			return -1;
		case 6:
			return -1;
		case 7:
			return this.blockID;
		default:
			return -1;
		}
	}
	
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 0);
    }
        
    @Override 
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if (metadata < 3){
            ret.add(new ItemStack(Block.dirt));
            return ret;
        }
        
        
        
        if(metadata == 3){
        	if(ItemBlockList.waterDroplets.isPresent()){
                ret.add(new ItemStack(ItemBlockList.waterDroplets.get()));
                ret.add(new ItemStack(ItemBlockList.waterDroplets.get()));
                ret.add(new ItemStack(ItemBlockList.waterDroplets.get()));
        	}
            ret.add(new ItemStack(Block.dirt));
            return ret;
        }
        
        if(metadata > 3 && metadata < 7){
            ret.add(new ItemStack(Block.sand));
            return ret;
        }
        
        if(metadata == 7){
        	if(ItemBlockList.waterDroplets.isPresent()){
                ret.add(new ItemStack(ItemBlockList.waterDroplets.get()));
                ret.add(new ItemStack(ItemBlockList.waterDroplets.get()));
                ret.add(new ItemStack(ItemBlockList.waterDroplets.get()));
        	}
            ret.add(new ItemStack(Block.sand));
            return ret;
        }
        return ret;
    }


    @SideOnly(Side.CLIENT)
    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass()
    {
        return 0;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
	public boolean renderAsNormalBlock(){
		return true;
	}

	public int getRenderType()
	{
	return 0;
	}
}
