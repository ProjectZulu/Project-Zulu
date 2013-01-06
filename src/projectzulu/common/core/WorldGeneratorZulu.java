package projectzulu.common.core;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import projectzulu.common.world.StructureManager;
import projectzulu.common.world.WorldGenOnSurface;
import projectzulu.common.world.WorldGenReplaceTop;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Loader;

public class WorldGeneratorZulu implements IWorldGenerator {
	//MadhatdragoN
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		switch (world.provider.dimensionId){
		case -1: generateNether(world, random, chunkX*16, chunkZ*16);
		case 0: generateSurface(world, random, chunkX*16, chunkZ*16);
		}
	}

	private void generateSurface(World world, Random random, int blockX, int blockZ){
		int Xcoord;
		int Ycoord;
		int Zcoord;
		
		if(Loader.isModLoaded(DefaultProps.WorldModId)){
			Xcoord = blockX + random.nextInt(16);
			Zcoord = blockZ + random.nextInt(16);
			Ycoord = world.getTopSolidOrLiquidBlock(Xcoord, Zcoord);
			if(StructureManager.tryToGenerateStructure(world, blockX, blockZ, Xcoord, Ycoord, Zcoord, random)){
				return;
			}
		}

		Xcoord = blockX;
		Ycoord = random.nextInt(60);
		Zcoord = blockZ;
		int[] blocksToReplace = new int[2];
		blocksToReplace[0] = Block.waterStill.blockID;
		blocksToReplace[1] = Block.waterMoving.blockID;
		(new WorldGenReplaceTop(Block.sand.blockID, blocksToReplace, BiomeGenBase.desert, BiomeGenBase.desertHills)).generate(world, random, Xcoord, Ycoord, Zcoord);

		/*These Blocks Only Spawn if Blocks/Item Package is Installed*/
		if(Loader.isModLoaded(DefaultProps.BlocksModId)){
			//Aloe Vera Generation
			Xcoord = blockX + random.nextInt(16);
			Ycoord = random.nextInt(60);
			Zcoord = blockZ + random.nextInt(16);
			if(ItemBlockList.aloeVera.isPresent()){
				(new WorldGenOnSurface(ItemBlockList.aloeVera.get().blockID, 3, 5)).generate(world, random, Xcoord, Ycoord, Zcoord);
			}

			//NightBloom Generation
			Xcoord = blockX + random.nextInt(16);
			Ycoord = random.nextInt(60);
			Zcoord = blockZ + random.nextInt(16);
			if(ItemBlockList.nightBloom.isPresent()){
				(new WorldGenOnSurface(ItemBlockList.nightBloom.get().blockID, 2, 85)).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
			//Creeper Blossom Generation
			Xcoord = blockX + random.nextInt(16);
			Ycoord = random.nextInt(60);
			Zcoord = blockZ + random.nextInt(16);
			if(ItemBlockList.creeperBlossom.isPresent()){
				(new WorldGenOnSurface(ItemBlockList.creeperBlossom.get().blockID, 1, 5)).generate(world, random, Xcoord, Ycoord, Zcoord);
			}

			//Palm Tree Sapling Generation (For River, Not Oasis)
			Xcoord = blockX + random.nextInt(16);
			Ycoord = random.nextInt(60);
			Zcoord = blockZ + random.nextInt(16);
			if(ItemBlockList.palmTreeSapling.isPresent()){
				(new WorldGenOnSurface(ItemBlockList.palmTreeSapling.get().blockID, 6, 5)).generate(world, random, Xcoord, Ycoord, Zcoord);
			}
		}
	}

	private boolean doesTerrainFluctuateTooMuch(World world, int Xcoord, int Ycoord, int Zcoord, int maxDifference, int squareLength){
		if( Math.abs( world.getTopSolidOrLiquidBlock(Xcoord+squareLength, Zcoord+squareLength) - Ycoord) < maxDifference 
				&& Math.abs( world.getTopSolidOrLiquidBlock(Xcoord-squareLength, Zcoord-squareLength) - Ycoord ) < maxDifference 
				&& Math.abs( world.getTopSolidOrLiquidBlock(Xcoord-squareLength, Zcoord+squareLength) - Ycoord ) < maxDifference 
				&& Math.abs( world.getTopSolidOrLiquidBlock(Xcoord+squareLength, Zcoord-squareLength) - Ycoord ) < maxDifference){
			return true;
		}

		return false;
	}

	private void generateNether(World world, Random random, int blockX, int blockZ){

	}
}
