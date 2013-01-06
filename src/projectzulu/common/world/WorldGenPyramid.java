package projectzulu.common.world;

import java.awt.Point;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.DungeonHooks;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.mobs.EntityMimic;
import cpw.mods.fml.common.Loader;

public class WorldGenPyramid extends WorldGenerator
{
	private int chancePerChunk = 0;
	private int chanceOutOf = 0;
	/** The ID of the plant block used in this plant generator. */
	private int plantBlockId;

	Random classRandom = new Random();
	int outside_height = 25;
	int floor_height = 3;

	public WorldGenPyramid(int par3, int par4){
		this.chancePerChunk = par3;
		this.chanceOutOf = par4;
	}

	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5){
		//C is the number of tries to palce desert in the provided chunk
		for (int c = 0; c < 1; c++) {
			if (chancePerChunk - par2Random.nextInt(chanceOutOf) >= 0){
				int TempY = par1World.getTopSolidOrLiquidBlock(par3, par5);

				Vec3 startingPos = Vec3.createVectorHelper(par3, TempY-1, par5);
				
				/* Generate Floors below Pyramid 
				 * Only replace Air and Water Blocks (unlike rest of pyramid), used to simulate 'supports' for the pyramid on even terrain or hills 
				 */
				for (int j = 0; j >= -(outside_height/2-1); j--) {

					/* Wall Along X */

					for (int i = -(outside_height + j*2); i < (outside_height + j*2); i++) {
						for (int k = -(outside_height + j*2); k < (outside_height + j*2); k++) {
							if(par1World.isAirBlock((int)startingPos.xCoord+i, (int)startingPos.yCoord+j, (int)startingPos.zCoord+k)){
								par1World.setBlockWithNotify( (int)startingPos.xCoord+i, (int)startingPos.yCoord+j, (int)startingPos.zCoord+k, Block.sandStone.blockID);
							}
						}
					}

				}

				/* Generate Floors */
				for (int j = 0; j <= outside_height; j++) {
					if(j==0){
						/* Create The Floor at Given j */
						for (int i = -(outside_height - j); i < (outside_height - j); i++) {
							for (int k = -(outside_height - j); k < (outside_height - j); k++) {
								par1World.setBlockWithNotify( (int)startingPos.xCoord+i, (int)startingPos.yCoord+j, (int)startingPos.zCoord+k, Block.sandStone.blockID);
							}
						}

						generateMaze(par1World, startingPos, j, floor_height, -(outside_height - j - 3), (outside_height - j - 3), -(outside_height - j - 3), (outside_height - j - 3));    	    	

					}
					else if( j % (floor_height + 1)  == 0){

						/* Create The Floor at Given j */
						for (int i = -(outside_height - j); i < (outside_height - j); i++) {
							for (int k = -(outside_height - j); k < (outside_height - j); k++) {
								par1World.setBlock( (int)startingPos.xCoord+i, (int)startingPos.yCoord+j, (int)startingPos.zCoord+k, Block.sandStone.blockID);
							}
						}

						/* Generate Maze of this Floor */
						generateMaze(par1World, startingPos, j, floor_height, -(outside_height - j - 3), (outside_height - j - 3), -(outside_height - j - 3), (outside_height - j - 3));

						/* Generate Staircase from This Floor to the one Below*/
						generateStairs(par1World, startingPos, j, floor_height, -(outside_height - j - 3), (outside_height - j - 3), -(outside_height - j - 3), (outside_height - j - 3));

					}
				}

				/* Generate Outside Walls */
				for (int j = 1; j <= outside_height; j++) {

					/* Wall Along X */
					for (int i = -(outside_height - j); i <= (outside_height - j); i++) {

						int k = -(outside_height - j);
						par1World.setBlockWithNotify( (int)startingPos.xCoord+i, (int)startingPos.yCoord+j, (int)startingPos.zCoord+k, Block.sandStone.blockID);

						k = (outside_height - j);
						par1World.setBlockWithNotify( (int)startingPos.xCoord+i, (int)startingPos.yCoord+j, (int)startingPos.zCoord+k, Block.sandStone.blockID);
					}

					/* Wall Along Z */
					for (int k = -(outside_height - j); k <= (outside_height - j); k++) {

						int i = -(outside_height - j);
						par1World.setBlockWithNotify( (int)startingPos.xCoord+i, (int)startingPos.yCoord+j, (int)startingPos.zCoord+k, Block.sandStone.blockID);

						i = (outside_height - j);
						par1World.setBlockWithNotify( (int)startingPos.xCoord+i, (int)startingPos.yCoord+j, (int)startingPos.zCoord+k, Block.sandStone.blockID);
					}
				}

				/* Generate Entrance */
				for (int i = -2; i <= 2; i++) {
					for (int k = -(outside_height - 8); k > -outside_height; k--) {
						for (int j = 1; j < 5; j++) {
							if(j == 4 || i == -2 || i == 2){
								par1World.setBlockWithNotify((int)startingPos.xCoord+i, (int)startingPos.yCoord+j, (int)startingPos.zCoord+k, Block.sandStone.blockID);
							}else{
								par1World.setBlockWithNotify((int)startingPos.xCoord+i, (int)startingPos.yCoord+j, (int)startingPos.zCoord+k, 0);
							}
						}
					}
				}

				ProjectZuluLog.info("Pyramid Succesfully Generated");
				return true;
			}
		}

		return false;
	}


	public void generateMaze(World par1World, Vec3 startingPos, int floorYcoord, int floorHeight, int lowerX, int upperX, int lowerZ, int upperZ){



		/* Each Cell is 2x2 */
		int cell_width = 2;
		int numCellsX = MathHelper.ceiling_double_int( (Math.abs(lowerX - upperX) + 1 ) / cell_width); 
		int numCellsZ = MathHelper.ceiling_double_int( (Math.abs(lowerZ - upperZ) + 1 ) / cell_width); 

		int mazeStartX = 4;
		int mazeStartZ = 4;
		int mazeFinishX = numCellsX - 4;
		int mazeFinishZ = numCellsZ - 4;

		/* Array of All Cells */
		MazeCell[][] allCells = new MazeCell[numCellsX][numCellsZ];

		/* Run Constructor of Each Array */
		for (int i = 0; i < numCellsZ; i++) {
			for (int k = 0; k < numCellsX; k++) {
				allCells[i][k] = new MazeCell(cell_width);
			}
		}

		//    	ModLoader.getMinecraftInstance().thePlayer.addChatMessage(Integer.toString(lowerZ));
		//    	ModLoader.getMinecraftInstance().thePlayer.addChatMessage(Integer.toString(numCellsX));
		//    	ModLoader.getMinecraftInstance().thePlayer.addChatMessage(Integer.toString( (int)Math.abs(upperX - lowerX) ));

		/* Assign Minecraft Locations to MazeCellArray */
		int xCell = 0; int xCounter = 1;
		int zCell = 0; int zCounter = 1;
		for (int i = lowerX; i < upperX; i++) {
			zCell = 0;
			zCounter = 1;
			for (int k = lowerZ; k < upperZ; k ++) {

				if(xCell < numCellsX && zCell < numCellsZ){
					allCells[xCell][zCell].addLocation(Vec3.createVectorHelper(
							startingPos.xCoord + i,
							startingPos.yCoord + floorYcoord+1,
							startingPos.zCoord + k) );
				}
				if(zCounter % cell_width == 0){
					zCell++;
				}
				zCounter++;
			}
			if(xCounter % cell_width == 0){
				xCell++;
			}
			xCounter++;
		}

		/* Mark Outer Cells As Walls (X axis) */
		for (int i = 0; i < numCellsZ; i++) {
			int k = 0;
			allCells[i][k].setCellType(1);
			k = numCellsZ-1;
			allCells[i][k].setCellType(1);
		}

		/* Mark Outer Cells As Walls (Z axis) */
		for (int k = 0; k < numCellsX; k++) {
			int i = 0;
			allCells[i][k].setCellType(1);
			i = numCellsX-1;
			allCells[i][k].setCellType(1);
		}

		/* Perform X wall placement attempts, then Assume that the maze is sufficienty random/comlpete */
		for (int counter = 0; counter < 8000; counter++) {

			/* Decide Random Length, Random Direction*/
			int randLength = classRandom.nextInt(1)+1;
			//        	int randLength = 1;
			int randDirection = classRandom.nextInt(4);

			/* Select Wall to Try to Place at */
			int selectedX = classRandom.nextInt(numCellsX);
			int selectedZ = classRandom.nextInt(numCellsZ);
			while( allCells[selectedX][selectedZ].getCellType() != 1 ){

				selectedX = classRandom.nextInt(numCellsX);
				selectedZ = classRandom.nextInt(numCellsZ);
			}

			/* Evalute if it is touching The Maze in a different direction that it is placed */
			/* Determine If It would go outside bounds, or touch ** Wall nodes */
			if( !isCellTouching(allCells, selectedX, selectedZ, randDirection, randLength, numCellsX, numCellsZ,
					mazeStartX,mazeStartZ,mazeFinishX, mazeFinishZ) ){
				int[] directionsX = new int[4];
				directionsX[0] = 0;
				directionsX[1] = 1;
				directionsX[2] = 0;
				directionsX[3] = -1;
				int[] directionsZ = new int[4];
				directionsZ[0] = 1;
				directionsZ[1] = 0;
				directionsZ[2] = -1;
				directionsZ[3] = 0;

				for (int i = 1; i <= randLength; i++) {
					int tempX = selectedX + directionsX[randDirection]*i;
					int tempZ = selectedZ + directionsZ[randDirection]*i;
					allCells[tempX][tempZ].setCellType(1);
				}


			}
		}

		//    	int tempRand = classRandom.nextInt(100);
		/* Evaluate cellType 0 cells and decide if any should be graduated to Chest or Spawner. */
		for (int i = 0; i < numCellsX; i++) {
			for (int k = 0; k < numCellsZ; k++) {
				if( allCells[i][k].getCellType() == 0 && isDeadEnd(allCells, i, k, numCellsX, numCellsZ) ){
					allCells[i][k].setCellType(2);
				}else if( allCells[i][k].getCellType() == 0){
					int holdRand = classRandom.nextInt(100);
					if( 1 - holdRand >= 0 ){
						allCells[i][k].setCellType(4);
					}
				}
			}
		}

		/* If Any Cell Types 0 are left, mark them as 3, so that they fill with air */
		for (int i = 0; i < numCellsX; i++) {
			for (int k = 0; k < numCellsZ; k++) {
				if( allCells[i][k].getCellType() == 0 ){
					allCells[i][k].setCellType(3);
				}
			}
		}

		/* Add to world WallBlocks at Locations in Wall Cells */
		for (int i = 0; i < numCellsX; i++) {
			for (int k = 0; k < numCellsZ; k++) {
				/* Get All Locations Out of Cell (Size * Size ) */
				for (int m = 0; m < (cell_width*cell_width); m++) {

					if( allCells[i][k].getLocation(m) != null && allCells[i][k].getCellType() == 1){
						/* Place Walls Vertically Until Size of Flow */
						for (int j = 0; j < floorHeight; j++) {

							if( 5 - classRandom.nextInt(100) >= 0 ){
								par1World.setBlockAndMetadataWithNotify( 
										(int)allCells[i][k].getLocation(m).xCoord,
										(int)allCells[i][k].getLocation(m).yCoord+j,
										(int)allCells[i][k].getLocation(m).zCoord,
										Block.sandStone.blockID, 3);
							}else if( 5 - classRandom.nextInt(100) >= 0 ){
								par1World.setBlockAndMetadataWithNotify( 
										(int)allCells[i][k].getLocation(m).xCoord,
										(int)allCells[i][k].getLocation(m).yCoord+j,
										(int)allCells[i][k].getLocation(m).zCoord,
										Block.sandStone.blockID, 2);
							}else if( 5 - classRandom.nextInt(100) >= 0 ){
								par1World.setBlockAndMetadataWithNotify( 
										(int)allCells[i][k].getLocation(m).xCoord,
										(int)allCells[i][k].getLocation(m).yCoord+j,
										(int)allCells[i][k].getLocation(m).zCoord,
										Block.sandStone.blockID, 1);
							}else{
								par1World.setBlockAndMetadataWithNotify( 
										(int)allCells[i][k].getLocation(m).xCoord,
										(int)allCells[i][k].getLocation(m).yCoord+j,
										(int)allCells[i][k].getLocation(m).zCoord,
										Block.sandStone.blockID, 0);
							}

						}
					}

					/* Cell Type 2 Generate Chest */
					if( allCells[i][k].getLocation(m) != null && allCells[i][k].getCellType() == 2){
						for (int j = 0; j < floorHeight; j++) {
							if(j == 0 && 20 - classRandom.nextInt(100) >= 0 ){
								int holdRand = classRandom.nextInt(2);

								if( classRandom.nextInt(5) == 0 ){
									/* Spawn Chest */
									TileEntityChest chest = new TileEntityChest();

									par1World.setBlockWithNotify( 
											(int)allCells[i][k].getLocation(m).xCoord,
											(int)allCells[i][k].getLocation(m).yCoord+j,
											(int)allCells[i][k].getLocation(m).zCoord,
											Block.chest.blockID);
									par1World.setBlockTileEntity((int)allCells[i][k].getLocation(m).xCoord,
											(int)allCells[i][k].getLocation(m).yCoord+j,
											(int)allCells[i][k].getLocation(m).zCoord,
											chest);
									for (int slot = 0; slot < chest.getSizeInventory(); slot++)
									{
										if( 15 - classRandom.nextInt(100) >= 0 ){
											chest.setInventorySlotContents(slot, DungeonHooks.getRandomDungeonLoot(classRandom));
										}
									}

								}else if(!par1World.isRemote){
									/* Place Air cause we don't want to spawn Inside something*/
									par1World.setBlockWithNotify( 
											(int)allCells[i][k].getLocation(m).xCoord,
											(int)allCells[i][k].getLocation(m).yCoord+j,
											(int)allCells[i][k].getLocation(m).zCoord,
											0);

									/* Spawn Mimic */
									EntityMimic var17 = new EntityMimic(
											par1World, 
											allCells[i][k].getLocation(m).xCoord+0.5,
											allCells[i][k].getLocation(m).yCoord+j,
											allCells[i][k].getLocation(m).zCoord+0.5, true);
									par1World.spawnEntityInWorld(var17);

								}

							}else{
								par1World.setBlockWithNotify( 
										(int)allCells[i][k].getLocation(m).xCoord,
										(int)allCells[i][k].getLocation(m).yCoord+j,
										(int)allCells[i][k].getLocation(m).zCoord,
										0);
							}

						}
					}

					/*Cell Type 3 Generate Air*/
					if( allCells[i][k].getLocation(m) != null && allCells[i][k].getCellType() == 3){
						for (int j = 0; j < floorHeight; j++) {

							par1World.setBlockWithNotify( 
									(int)allCells[i][k].getLocation(m).xCoord,
									(int)allCells[i][k].getLocation(m).yCoord+j,
									(int)allCells[i][k].getLocation(m).zCoord,
									0);
						}
					}

					/*Cell Type 4 Generate Spawner*/
					if( allCells[i][k].getLocation(m) != null && allCells[i][k].getCellType() == 4){
						for (int j = 0; j < floorHeight; j++) {
							if(j == 0 && m == 0){

								/* Create Mob Spawner */
								par1World.setBlockWithNotify( 
										(int)allCells[i][k].getLocation(m).xCoord,
										(int)allCells[i][k].getLocation(m).yCoord+j,
										(int)allCells[i][k].getLocation(m).zCoord,
										Block.mobSpawner.blockID);
								TileEntityMobSpawner var19 = (TileEntityMobSpawner)par1World.getBlockTileEntity(
										(int)allCells[i][k].getLocation(m).xCoord,
										(int)allCells[i][k].getLocation(m).yCoord+j,
										(int)allCells[i][k].getLocation(m).zCoord);

								if (var19 != null){
									if( Loader.isModLoaded("ProjectZulu|Mobs") ){
										var19.setMobID( DefaultProps.CoreModId.concat(".Entity Mummy") );
									}else{
										var19.setMobID( "Zombie" );
									}
								}
							}else{
								par1World.setBlockWithNotify( 
										(int)allCells[i][k].getLocation(m).xCoord,
										(int)allCells[i][k].getLocation(m).yCoord+j,
										(int)allCells[i][k].getLocation(m).zCoord,
										0);
							}
						}
					}


				}
			}
		}
	}

	public boolean isCellTouching(MazeCell[][] allCells, int selectedX, int selectedZ, int randDirection, int randLength, int maxCellsX, int maxCellsZ,
			int mazeStartX, int mazeStartZ, int mazeFinishX, int mazeFinishZ){

		int[] directionsX = new int[8];
		directionsX[0] = 0;
		directionsX[1] = 1;
		directionsX[2] = 0;
		directionsX[3] = -1;
		directionsX[4] = -1;
		directionsX[5] = -1;
		directionsX[6] = +1;
		directionsX[7] = +1;
		int[] directionsZ = new int[8];
		directionsZ[0] = 1;
		directionsZ[1] = 0;
		directionsZ[2] = -1;
		directionsZ[3] = 0;
		directionsZ[4] = -1;
		directionsZ[5] = +1;
		directionsZ[6] = -1;
		directionsZ[7] = +1;

		int tempX = 0;
		int tempZ = 0;

		for (int i = 1; i <= randLength; i++) {

			/* Determine the Square we are Looking At*/
			tempX = selectedX + directionsX[randDirection]*i;
			tempZ = selectedZ + directionsZ[randDirection]*i;

			/* If out of Range */
			if(tempX < 0 || tempX > maxCellsX-1 || tempZ < 0 || tempZ > maxCellsZ-1){
				return true;
			}

			/* See if this is Wall */
			if( allCells[tempX][tempZ].getCellType() == 1 ){
				return true;
			}

			/* See if any of the Adjacent Areas are Wall */
			for (int j = 0; j < 8; j++) {

				/* If out of Range, skip this iteration */
				if( tempX+directionsX[j] < 0 || tempX+directionsX[j] > maxCellsX-1 || tempZ+directionsZ[j] < 0 || tempZ+directionsZ[j] > maxCellsZ-1 ){
					continue;
				}

				/* If Block is the Original Block or near it,, skip it (As we know about it already) */
				if( selectedX == tempX+directionsX[j] && 
						(selectedZ == tempZ+directionsZ[j] 
								|| selectedZ == tempZ+directionsZ[j]-1 
								|| selectedZ == tempZ+directionsZ[j]+1) ){
					continue;
				}

				/* If Block is the Original Block or near it, skip it (As we know about it already) */
				if( selectedZ == tempZ+directionsZ[j] && 
						(selectedX == tempX+directionsX[j] 
								|| selectedX == tempX+directionsX[j]-1 
								|| selectedX == tempX+directionsX[j]+1) ){
					continue;
				}

				/* If Placing this Block would make Start or Finish Cell Sourrounded, don't) */
				if( wouldMazeStartBeSurrounded(allCells,tempX+directionsX[j],tempZ+directionsZ[j], mazeStartX, mazeStartZ, maxCellsX, maxCellsZ) ){
					return true;
				}

				if( wouldMazeStartBeSurrounded(allCells,tempX+directionsX[j],tempZ+directionsZ[j], mazeFinishX, mazeFinishZ, maxCellsX, maxCellsZ) ){
					return true;
				}

				/* If Block is wall, we know we're "touhing" and should return true*/
				if(allCells[ tempX+directionsX[j] ][ tempZ+directionsZ[j] ].getCellType() == 1){
					return true;
				}
			}
		}
		return false;
	}

	private boolean wouldMazeStartBeSurrounded(MazeCell[][] allCells, int currentX, int currentZ, int mazeStartX, int mazeStartZ, int maxCellsX, int maxCellsZ){
		int[] directionsX = new int[4];
		directionsX[0] = 0;
		directionsX[1] = 1;
		directionsX[2] = 0;
		directionsX[3] = -1;
		int[] directionsZ = new int[4];
		directionsZ[0] = 1;
		directionsZ[1] = 0;
		directionsZ[2] = -1;
		directionsZ[3] = 0;

		int tempX = 0;
		int tempZ = 0;
		int numberOfSidesOccupied = 0;

		/* If the Current Square is being placed at the Desired Point Exit return Surrounded */
		if( mazeStartX == currentX && mazeStartZ == currentZ ){

			/* If the Current Square is being placed around the Desired Point add to numberSideOccupied */
		}else if(Math.abs(mazeStartX - currentX) <= 1 && Math.abs(mazeStartZ - currentZ) <= 1){
			/* If Its Already a Wall we don't add to numberOfSides because it will be counted below*/
			if( allCells[ currentX ][ currentZ ].getCellType() == 0) {
				numberOfSidesOccupied++;
			}

		}else{
			/* If this is not being added near the evaluation point it cannot effect it*/
			return false;
		}

		/* Evaluate if Neaby Squares to start are filled*/
		for (int j = 0; j < 4; j++) {
			tempX = mazeStartX + directionsX[j];
			tempZ = mazeStartZ + directionsZ[j];

			/* If out of Range, skip this iteration */
			if( tempX+directionsX[j] < 0 || tempX+directionsX[j] > maxCellsX-1 || tempZ+directionsZ[j] < 0 || tempZ+directionsZ[j] > maxCellsZ-1 ){
				continue;
			}

			if( allCells[ tempX+directionsX[j] ][ tempZ+directionsZ[j] ].getCellType() == 1 ){
				numberOfSidesOccupied++;
			}
		}



		if(numberOfSidesOccupied < 4){
			return false;
		}else{
			return true;
		}

	}

	public void generateStairs(World par1World, Vec3 startingPos, int floorYcoord, int floorHeight, int lowerX, int upperX, int lowerZ, int upperZ){

		/* Staircase if a 2x6 Length */
		Point[] stairCaseLocRef = new Point[2*3];
		for (int i = 0; i < stairCaseLocRef.length; i++) {
			stairCaseLocRef[i] = new Point();
		}
		stairCaseLocRef[0].setLocation(0, 0);
		stairCaseLocRef[1].setLocation(0, 1);
		stairCaseLocRef[2].setLocation(0, 2);
		stairCaseLocRef[3].setLocation(1, 0);
		stairCaseLocRef[4].setLocation(1, 1);
		stairCaseLocRef[5].setLocation(1, 2);

		Point stairCasePlacement = new Point();
		int counter = 0;
		boolean isValidStairCase = false;
		for (int i = lowerX; i < upperX; i++) {
			for (int k = lowerZ; k < upperZ; k ++) {

				/* Assume That its valid, if reason is found for it not to be mark it as such*/
				isValidStairCase = true;

				/* Check if This is a valid spot for a staircase  */
				for (int l = 0; l < stairCaseLocRef.length; l++) {
					int tempX = (int) (startingPos.xCoord) + i + stairCaseLocRef[l].x;
					int tempY = (int) (startingPos.yCoord) + floorYcoord;
					int tempZ = (int) (startingPos.zCoord) + k + stairCaseLocRef[l].y;

					/* Check if Staircase would be out of bounds of pyramid */
					if(i + stairCaseLocRef[l].x <=  lowerX || i + stairCaseLocRef[l].x > upperX 
							|| k + stairCaseLocRef[l].y > upperZ || k + stairCaseLocRef[l].y <= lowerZ){
						isValidStairCase = false;
					}

					for (int j = -3; j <= 3; j++) {
						if(j != 0 && par1World.getBlockId(tempX, tempY+j, tempZ) == 0 ){
							isValidStairCase = false;
						}
					}

					counter++;
					if(isValidStairCase == false){
						break;
					}
				}

				/* If We've evaluated the current iterationa and found that its valid staircase, we're done should leave */
				if(isValidStairCase == true){
					//					ModLoader.getMinecraftInstance().thePlayer.addChatMessage( Integer.toString(i + stairCaseLocRef[0].x)  );
					stairCasePlacement.setLocation(i, k);
					break;
				}

			}

			/* If We've evaluated the current iteration and found that its valid staircase, we're done and should leave */
			if(isValidStairCase == true){
				break;
			}
		}

		//		ModLoader.getMinecraftInstance().thePlayer.addChatMessage("Exit loop".concat(Boolean.toString(isValidStairCase)));
		//    	ModLoader.getMinecraftInstance().thePlayer.addChatMessage("Exit loop".concat(Integer.toString(counter)));
		//    	ModLoader.getMinecraftInstance().thePlayer.addChatMessage("Exit loop".concat(Integer.toString((int) (startingPos.yCoord) + floorYcoord)));
		//    	ModLoader.getMinecraftInstance().thePlayer.addChatMessage("Exit loop".concat(Integer.toString((int) (startingPos.yCoord) + floorYcoord - floorHeight)));

		/* Actual Stair Generation */
		if(isValidStairCase){
			/* Clear Extra Space for Stairs */
			for (int tempX = 0; tempX < 4; tempX++) {
				for (int tempZ = 0; tempZ < 4; tempZ++) {

					for (int tempY = 0; tempY <= floorHeight*2; tempY++) {
						if(tempY != floorHeight){
							par1World.setBlockWithNotify(
									(int)(startingPos.xCoord) + stairCasePlacement.x + tempX,
									(int)(startingPos.yCoord) + floorYcoord - floorHeight + tempY,
									(int)(startingPos.zCoord) + stairCasePlacement.y + tempZ,
									0);

						}
					}
				}
			}


			/* Place Staircase at stairCasePlacement */
			for (int j = 0; j < floorHeight + 1; j++) {


				for (int i = (stairCaseLocRef.length-1-j); i >= (stairCaseLocRef.length / 2); i--) {

					if( i == (stairCaseLocRef.length-1-j) ){
						par1World.setBlockAndMetadataWithNotify(
								(int)(startingPos.xCoord) + stairCasePlacement.x + stairCaseLocRef[i].x,
								(int)(startingPos.yCoord) + floorYcoord - floorHeight + j,
								(int)(startingPos.zCoord) + stairCasePlacement.y + stairCaseLocRef[i].y,
								Block.stairsSandStone.blockID,3);
					}else{
						par1World.setBlockWithNotify(
								(int)(startingPos.xCoord) + stairCasePlacement.x + stairCaseLocRef[i].x,
								(int)(startingPos.yCoord) + floorYcoord - floorHeight + j,
								(int)(startingPos.zCoord) + stairCasePlacement.y + stairCaseLocRef[i].y,
								Block.sandStone.blockID);    				
					}
					//        	    	ModLoader.getMinecraftInstance().thePlayer.addChatMessage("PlacedGold)");
				}

				if(j == floorHeight){

					for (int i = 0; i < stairCaseLocRef.length; i++) {
						par1World.setBlockWithNotify(
								(int)(startingPos.xCoord) + stairCasePlacement.x + stairCaseLocRef[i].x,
								(int)(startingPos.yCoord) + floorYcoord - floorHeight + j,
								(int)(startingPos.zCoord) + stairCasePlacement.y + stairCaseLocRef[i].y,
								0);

					}

					for (int i = 0; i < stairCaseLocRef.length / 2; i++) {
						if(i == 0){
							par1World.setBlockAndMetadataWithNotify(
									(int)(startingPos.xCoord) + stairCasePlacement.x + stairCaseLocRef[i].x,
									(int)(startingPos.yCoord) + floorYcoord - floorHeight + j,
									(int)(startingPos.zCoord) + stairCasePlacement.y + stairCaseLocRef[i].y,
									Block.stairsSandStone.blockID,1);

						}else{
							par1World.setBlockWithNotify(
									(int)(startingPos.xCoord) + stairCasePlacement.x + stairCaseLocRef[i].x,
									(int)(startingPos.yCoord) + floorYcoord - floorHeight + j,
									(int)(startingPos.zCoord) + stairCasePlacement.y + stairCaseLocRef[i].y,
									Block.sandStone.blockID);
						}
					}

				}

			}    	

		}

//		System.out.println("Running");


	}

	/** Returns whether or not the passed in XZ has  */
	private Boolean isDeadEnd(MazeCell[][] allCells, int selectedX, int selectedZ, int maxCellsX, int maxCellsZ){
		Point[] directions = new Point[4];
		for (int i = 0; i < directions.length; i++) {
			directions[i] = new Point();
		}
		directions[0].setLocation(1,0);
		directions[1].setLocation(0,1);
		directions[2].setLocation(-1,0);
		directions[3].setLocation(0,-1);

		int wallsNearby = 0;
		for (int i = 0; i < directions.length; i++) {
			int tempX = selectedX + directions[i].x;
			int tempZ = selectedZ + directions[i].y;

			if(tempX < 0 || tempX > maxCellsX-1 || tempZ < 0 || tempZ > maxCellsZ-1){
				continue;
			}
			if( allCells[tempX][tempZ].getCellType() == 1 ){
				wallsNearby++;
			}
		}

		if(wallsNearby == 3){
			return true;
		}else{
			return false;
		}

	}



}
