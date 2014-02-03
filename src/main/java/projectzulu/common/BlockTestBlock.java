package projectzulu.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.world.buildingmanager.BuildingManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTestBlock extends Block
{
	BuildingManager buildingManager;
	Random classRandom = new Random();
	int classRand = classRandom.nextInt(100);
	
	public BlockTestBlock(int par1, int par2){
        super(par1, Material.rock);
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
	
	public int quantityDropped(Random random) {
		return 1;
	}

	public int idDropped(int i, Random random, int j) {
		return this.blockID;
	}

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World world, int par2, int par3, int par4){
    	
//    	new MazeGenerator(new BuildingManagerCemetary(par1World), 1, 4, 3, 11, 100, 100).generate(par1World, par1World.rand, par2, par3, par4);
//    	int Xcoord = par2;
//		int Ycoord = world.getTopSolidOrLiquidBlock(par2, par4);
//		int Zcoord = par4;
//		
//			if(world.getBlockMaterial(Xcoord, Ycoord-1, Zcoord) != Material.ice && world.getBlockMaterial(Xcoord, Ycoord, Zcoord) != Material.water 
//					&& world.getBlockMaterial(Xcoord, Ycoord-1, Zcoord) != Material.sand
//					&& doesTerrainFluctuateTooMuch(world, Xcoord, Ycoord, Zcoord, 2, 6)){
//				if(new MazeGenerator(new BuildingManagerCemetary(world), 1, 4, 3, 11, 1, 1).generate(world, world.rand, Xcoord, Ycoord-1, Zcoord) ){
//					ProjectZuluLog.info("Generating Cemetary at %s, %s, %s", Xcoord, Ycoord, Zcoord);
//					return;
//				}
//			}
    	
    }
    
    
    public boolean doesTerrainFluctuateTooMuch(World world, int Xcoord, int Ycoord, int Zcoord, int maxDifference, int squareLength){
		if( Math.abs( world.getTopSolidOrLiquidBlock(Xcoord+squareLength, Zcoord+squareLength) - Ycoord) < maxDifference 
				&& Math.abs( world.getTopSolidOrLiquidBlock(Xcoord-squareLength, Zcoord-squareLength) - Ycoord ) < maxDifference 
				&& Math.abs( world.getTopSolidOrLiquidBlock(Xcoord-squareLength, Zcoord+squareLength) - Ycoord ) < maxDifference 
				&& Math.abs( world.getTopSolidOrLiquidBlock(Xcoord+squareLength, Zcoord-squareLength) - Ycoord ) < maxDifference){
			return true;
		}
		System.out.println(Math.abs( world.getTopSolidOrLiquidBlock(Xcoord+squareLength, Zcoord+squareLength) - Ycoord ));
		System.out.println(Math.abs( world.getTopSolidOrLiquidBlock(Xcoord-squareLength, Zcoord-squareLength) - Ycoord ));
		System.out.println(Math.abs( world.getTopSolidOrLiquidBlock(Xcoord-squareLength, Zcoord+squareLength) - Ycoord ));
		System.out.println(Math.abs( world.getTopSolidOrLiquidBlock(Xcoord+squareLength, Zcoord-squareLength) - Ycoord ));

		return false;
	}
}
