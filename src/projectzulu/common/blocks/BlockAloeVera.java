package projectzulu.common.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import projectzulu.common.core.ItemBlockList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class BlockAloeVera extends BlockFlower
{
	private int tickInterval = 4;
	public int getTickInterval(){
		return tickInterval;
	}

	public BlockAloeVera(int i, int j){
		super(i, j, Material.plants);
		setTickRandomly(true);
		//this.setCreativeTab(CreativeTabs.tabFood);
		this.disableStats();
		this.setRequiresSelfNotify();
	}
	
    @SideOnly(Side.CLIENT)
	public String getTextureFile()
    {
            return "/mods/blocks_projectzulu.png";
    }
    
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4,
			Random par5Random) {

    	//See if Aloe Vera Should Grow
		//Control Bottom Alor Vera
		int tempAVMeta = par1World.getBlockMetadata(par2, par3, par4);	
		int waterRate = waterGrowthRate(par1World, par2, par3, par4);
		int weedRate = weedGrowthRate(par1World, par2, par3, par4);
		int probabilityOutOf = 200;
		
		if (tempAVMeta < 3) {
			int holdRand = par5Random.nextInt(probabilityOutOf);
			//Growth is proportional to the lightlevel, more light more likely to advance
			if( par1World.getLightBrightness(par2, par3, par4) - holdRand >= 0 ){
				par1World.setBlockAndMetadataWithNotify(par2, par3, par4, ItemBlockList.aloeVera.get().blockID, tempAVMeta+1);
			}
		}
		
		//Final Stage of Bottom AloeVera, Created the flower block above
		if(tempAVMeta == 3 && waterRate > 1){
			//If Meta Data is at 3 or then we check to see if we can grow flower above and tumbleweed
			//Check if Air is above and the block below is not aloeVera. If So, place the top 'Flower Aloe Vera' above
			if(par1World.getBlockId(par2, par3+1, par4) == 0 && par1World.getBlockId(par2, par3-1, par4) != ItemBlockList.aloeVera.get().blockID){
				int holdRand = par5Random.nextInt(probabilityOutOf);
				if( par1World.getLightBrightness(par2, par3+1, par4) - holdRand >= 0 ){
					par1World.setBlockAndMetadataWithNotify(par2, par3+1, par4, ItemBlockList.aloeVera.get().blockID, 4);
				}
			}
		}
		
		//Following Section of Code deals with Flower block, but WaterRate needs to be recalculate based on water level not to
		//to Flower block coords but to base coords
		waterRate = waterGrowthRate(par1World, par2, par3-1, par4);
		weedRate = weedGrowthRate(par1World, par2, par3-1, par4);
		
		
		//Deals with the flower block, if its 3-8 and is above an Aloe Vera Block (The Bottom) then it needs to grow
		if(tempAVMeta > 3 && tempAVMeta < 8 && waterRate > 1 && par1World.getBlockId(par2, par3-1, par4) == ItemBlockList.aloeVera.get().blockID){
			int holdRand = par5Random.nextInt(probabilityOutOf);
			//Growth is proportional to the lightlevel, more light more likely to advance
			if( par1World.getLightBrightness(par2, par3, par4) - holdRand >= 0 ){
				par1World.setBlockAndMetadataWithNotify(par2, par3, par4, ItemBlockList.aloeVera.get().blockID, tempAVMeta+1);
			}			
		}

		//If Flower is at final stage, it needs to spawn tumbleweed and destroy itself
		if(tempAVMeta == 8 && par1World.getBlockId(par2, par3, par4) == ItemBlockList.aloeVera.get().blockID && ItemBlockList.tumbleweed.isPresent()){
			int holdRand = par5Random.nextInt(probabilityOutOf);
			if(weedRate - holdRand >= 0){
				//Check in 3*3 square For air to place tumbleweed, cannot be placed over water

				Vec3 suitableBlockLoc = findSuitableBlockLoc(par1World, par2, par3-1, par4);
				//Place Tumbleweed
				if (suitableBlockLoc != null){
					//Place Tumbleweed at that desired location
					par1World.setBlockWithNotify((int)suitableBlockLoc.xCoord, (int)suitableBlockLoc.yCoord, (int)suitableBlockLoc.zCoord, ItemBlockList.tumbleweed.get().blockID);
					//Remove Flower as Tumbleweed is dead plant I guess
					par1World.setBlockWithNotify(par2, par3, par4, 0);
				}
			}
		}
		
		//Water Food Growth
		//Check That Block Below is Sand and if Random replace with watered dirt stage 1
		int holdRand = par5Random.nextInt(probabilityOutOf/2);
		if(par1World.getBlockId(par2, par3-1, par4) == Block.dirt.blockID && waterRate - holdRand >= 0){
			if( ItemBlockList.wateredDirt.isPresent() ){
				par1World.setBlockAndMetadataWithNotify(par2, par3-1, par4, ItemBlockList.wateredDirt.get().blockID, 0);
			}
		}
		if(par1World.getBlockId(par2, par3-1, par4) == Block.sand.blockID && waterRate - holdRand >= 0){
			if( ItemBlockList.wateredDirt.isPresent() ){
				par1World.setBlockAndMetadataWithNotify(par2, par3-1, par4, ItemBlockList.wateredDirt.get().blockID, 4);
			}
		}

		
		if(ItemBlockList.wateredDirt.isPresent() && par1World.getBlockId(par2, par3-1, par4) == ItemBlockList.wateredDirt.get().blockID && waterRate - holdRand >= 0){
			int tempMeta = par1World.getBlockMetadata(par2, par3-1, par4);
			if(tempMeta != 3 &&  tempMeta != 7){
				par1World.setBlockAndMetadataWithNotify(par2, par3-1, par4, ItemBlockList.wateredDirt.get().blockID, tempMeta+1);
			}
		}
		super.updateTick(par1World, par2, par3, par4, par5Random);
		par1World.scheduleBlockUpdate(par2, par3, par4, ItemBlockList.aloeVera.get().blockID, 225/2);
	}

	private Vec3 findSuitableBlockLoc(World par1World, int par2, int par3, int par4){
		for(int i = -1; i < 2; i++){
			//Will Only Place Tumbleweed on same level or down one
			for (int j = -1; j < 1; j++) {
				for (int k = -1; k < 2 ; k++) {
					
					if ( par1World.getBlockId(par2+i, par3+j, par4+k) == 0 
							&& par1World.getBlockId(par2+i, par3+j-1, par4+k) != Block.waterMoving.blockID
							&& par1World.getBlockId(par2+i, par3+j-1, par4+k) != Block.waterStill.blockID
							&& par1World.getBlockId(par2+i, par3+j-1, par4+k) != 0){
						return Vec3.createVectorHelper(par2+i, par3+j, par4+k);
					}
				}
			}
		}
		return null;
	}
		
	public int getBlockTextureFromSideAndMetadata(int par1, int par2) 
	{
		switch(par2) {
		case 0: {
			return 0;
		}
		case 1: {
			return 1;
		}
		case 2: {
			return 2;
		}
		case 3: {
			return 3;
		}
		case 4: {
			return 24;
		}
		case 5: {
			return 25;
		}
		case 6: {
			return 26;
		}
		case 7: {
			return 27;
		}
		case 8: {
			return 28;
		}
		default: return 0;
		}
	}
	
//    @SideOnly(Side.CLIENT)
//    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
//    {
//        for (int var4 = 0; var4 < 9; ++var4)
//        {
//            par3List.add(new ItemStack(par1, 1, var4));
//        }
//    }
	
	
	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		par1World.scheduleBlockUpdate(par2, par3, par4, ItemBlockList.aloeVera.get().blockID, 4);
		
		super.onBlockAdded(par1World, par2, par3, par4);
	}

	public int waterGrowthRate(World par1World, int par2, int par3, int par4){
		//Blocks to Check
		//Check Blocks in a 5x5 Grid centered on Block
		int waterGrowthRate = 1;
		for(int i = -4; i < 5; i++){
			for (int j = -4; j < 5; j++) {
				//y axis
				for(int k = -1; k<1;k++){
					int iD = par1World.getBlockId(par2+i, par3+k, par4+j);
					if(iD == Block.waterMoving.blockID || iD == Block.waterStill.blockID){
						waterGrowthRate +=1;
						break;
					}
				}
			}
		}
		return Math.min(waterGrowthRate, 30);
	}
    public int weedGrowthRate(World par1World, int par2, int par3, int par4){
    	//Blocks to Check
    	//Check Blocks in a 5x5 Grid centered on Block
    	int weedGrowthRate = 0;
    	for(int i = -4; i < 5; i++){
    		for (int j = -4; j < 5; j++) {
    			//y axis
    			for(int k = -1; k<1;k++){

    				int iD = par1World.getBlockId(par2+i, par3+k, par4+j);
    				//Nearby Water Speeds Up Growth
    				if(iD == Block.waterMoving.blockID || iD == Block.waterStill.blockID){
    					weedGrowthRate +=1;
    					break;
    				}
    			}
    		}
    	}
    	
    	return Math.min(weedGrowthRate, 30);
    }
	
	public int idDropped(int par1, Random par2Random, int par3){
		
		switch(par1) {
		case 0: {
			return ItemBlockList.aloeVera.get().blockID;
		}
		case 1: {
			return ItemBlockList.aloeVera.get().blockID;
		}
		case 2: {
			return ItemBlockList.aloeVera.get().blockID;
		}
		case 3: {
			return ItemBlockList.aloeVera.get().blockID;
		}
		case 4: {
			return Item.dyePowder.shiftedIndex;
		}
		case 5: {
			return Item.silk.shiftedIndex;
		}
		case 6: {
			return Item.dyePowder.shiftedIndex;
		}
		case 7: {
			return Item.dyePowder.shiftedIndex;
		}
		case 8: {
			return Item.dyePowder.shiftedIndex;
		}
		default: return super.idDropped(par1, par2Random, par3);
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
        	if(ItemBlockList.aloeVeraSeeds.isPresent()){
                ret.add(new ItemStack(ItemBlockList.aloeVeraSeeds.get()));
        	}
            return ret;
        }
        
        if(metadata == 3){
            ret.add(new ItemStack(this));
        	if(ItemBlockList.aloeVeraSeeds.isPresent()){
                ret.add(new ItemStack(ItemBlockList.aloeVeraSeeds.get()));
                ret.add(new ItemStack(ItemBlockList.aloeVeraSeeds.get()));
        	}
            return ret;
        }
        
        if(metadata == 8){
        	return super.getBlockDropped(world, x, y, z, metadata, fortune);
        }
        

        return ret;
    }

	
	@Override
	public int damageDropped(int par1) {
		switch(par1) {
		case 0: {
			return 0;
		}
		case 1: {
			return 0;
		}
		case 2: {
			return 0;
		}
		case 3: {
			return 0;
		}
		case 4: {
			return 11;
		}
		case 5: {
			return 11;
		}
		case 6: {
			return 11;
		}
		case 7: {
			return 11;
		}
		case 8: {
			return 11;
		}
		default: return 0;
		}
	}
	

	
	public int quantityDropped(Random random){
		return 1;
	}
	
    public boolean isOpaqueCube(){
            return false;
    }

    public boolean renderAsNormalBlock(){
            return false;
    }

    public int getRenderType(){
            return 1;
    }
        
    protected boolean canThisPlantGrowOnThisBlockID(int i){
    	if(ItemBlockList.wateredDirt.isPresent() && i == ItemBlockList.wateredDirt.get().blockID){
    		return true;
    	}
    	return i == Block.grass.blockID || i == Block.dirt.blockID || i == Block.tilledField.blockID 
            		|| i == Block.sand.blockID || i == ItemBlockList.aloeVera.get().blockID;
    }
    
    public boolean canSpawnOnThisBlockID(int i){
    	if(ItemBlockList.wateredDirt.isPresent() && i == ItemBlockList.wateredDirt.get().blockID){
    		return true;
    	}
        return i == Block.grass.blockID || i == Block.dirt.blockID || i == Block.sand.blockID;
    }


	
    
    //Read From NBT
    public void readFromNBT(NBTTagCompound par1NBTTagCompound){
//    	position.xCoord = par1NBTTagCompound.getDouble("PosX");
//    	position.yCoord = par1NBTTagCompound.getDouble("PosY");
//    	position.zCoord = par1NBTTagCompound.getDouble("PosZ");
    }
    
    //Write to NBT
    public void writeToNBT(NBTTagCompound par1NBTTagCompound){
//    	par1NBTTagCompound.setDouble("PosX", position.xCoord);
//    	par1NBTTagCompound.setDouble("PosY", position.yCoord);
//    	par1NBTTagCompound.setDouble("PosZ", position.zCoord);
    }
}
