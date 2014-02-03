package projectzulu.common.world;

import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import projectzulu.common.world.buildingmanager.BuildingManager;

public class MazeGenerator extends WorldGenerator{
	
	BuildingManager buildingManager;
	int numberOfFloors;
	int floorheight;
	int width;
	int cellSize;
	int chancePerChunk;
	int chanceOutOf;

	public MazeGenerator(BuildingManager buildingManager,int numberOfFloors, int floorHeight, int cellSize, int width, int chancePerChunk, int chanceOutOf){
		this.buildingManager = buildingManager;
		this.numberOfFloors = numberOfFloors;
		this.floorheight = floorHeight;
		this.cellSize = cellSize;
		this.width = width;
		this.chancePerChunk = chancePerChunk;
		this.chanceOutOf = chanceOutOf;
	}
	
	/**
	 * Evaluates if Structure should Generate at location
	 */
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5){
		
		if (chancePerChunk - par2Random.nextInt(chanceOutOf) >= 0){
			doGeneration(par1World, par2Random, par3, par4, par5);
			return true;
		}
		return false;
	}
	
	/**
	 * Handles Actual Generation when generate method actually Decides to Generate at Location
	 */
	public void doGeneration(World par1World, Random par2Random, int par3, int par4, int par5){
		Vec3 startingPos = Vec3.createVectorHelper(par3, par4, par5);
		/* Generate Floors */
		for (int j = 0; j < numberOfFloors; j++) {
			if( j  == 0){
				
				/* Create Floor */
				buildingManager.createFloor(startingPos, width, floorheight, j, cellSize);
				
				/* Generate Maze */
				generateMaze(par1World, startingPos, j, floorheight, cellSize, -(width - 3), (width - 3), -(width - 3), (width - 3), buildingManager, par2Random);
				
				/* Create Special Strucures, i.e. Entrances */
				buildingManager.createSpecial(startingPos, width, floorheight, j, cellSize);

			}
		}
	}
	
    public void generateMaze(World par1World, Vec3 startingPos, int floorYcoord, int floorHeight, int cell_width, int lowerX, int upperX, int lowerZ, int upperZ, BuildingManager buildingManager, Random classRandom){
    	
//    	int cell_width = 3;
    	int numCellsX = MathHelper.ceiling_double_int( (Math.abs(lowerX - upperX) + 1 ) / cell_width); 
    	int numCellsZ = MathHelper.ceiling_double_int( (Math.abs(lowerZ - upperZ) + 1 ) / cell_width); 

    	/* What the hell does this do? */
    	int mazeStartX = cell_width*2;
    	int mazeStartZ = cell_width*2;
    	int mazeFinishX = numCellsX - cell_width*2;
    	int mazeFinishZ = numCellsZ - cell_width*2;
    	
    	/* Array of All Cells */
    	MazeCell[][] allCells = new MazeCell[numCellsX][numCellsZ];
    	
    	/* Run Constructor of Each Array */
    	for (int i = 0; i < numCellsX; i++){
    		for (int k = 0; k < numCellsZ; k++) {
    			allCells[i][k] = new MazeCell(cell_width);
    		}
		}
    	
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
    	for (int i = 0; i < numCellsZ; i++){
    		int k = 0;
    		allCells[i][k].setCellType(1);
    		allCells[i][k].addCellSubType(CellType.NorthWall);
    		k = numCellsZ-1;
    		allCells[i][k].setCellType(1);
    		allCells[i][k].addCellSubType(CellType.SoutherWall);
		}
    	
    	/* Mark Outer Cells As Walls (Z axis) */
    	for (int k = 0; k < numCellsX; k++){
    		int i = 0;
    		allCells[i][k].setCellType(1);
    		allCells[i][k].addCellSubType(CellType.WestWall);
    		i = numCellsX-1;
    		allCells[i][k].setCellType(1);
    		allCells[i][k].addCellSubType(CellType.EastWall);
    	}
		
    	/* Perform X wall placement attempts, then Assume that the maze is sufficienty random/comlpete */
    	int attemptsSinceLastPlaced = 0;
    	while(attemptsSinceLastPlaced < 800){
    		
        	/* Decide Random Length, Random Direction*/
        	int randLength = classRandom.nextInt(3)+1;
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
            	
            	for (int i = 1; i <= randLength; i++){
            		int tempX = selectedX + directionsX[randDirection]*i;
            		int tempZ = selectedZ + directionsZ[randDirection]*i;
            		allCells[tempX][tempZ].setCellType(1);
            		buildingManager.evaluateCarvedCells(allCells, tempX, tempZ, numCellsX, numCellsZ, classRandom);
				}
            	attemptsSinceLastPlaced = 0;
        	}else{
        		attemptsSinceLastPlaced++;
        	}
		}
    	
    	/* Evaluate cellType 0 cells and decide if any should be graduated to Chest or Spawner. */
    	for (int i = 0; i < numCellsX; i++) {
			for (int k = 0; k < numCellsZ; k++) {
				if(allCells[i][k].getCellType() == 0 && buildingManager.evaluateUnCarvedCells(allCells, i, k, numCellsX, numCellsZ, classRandom)){
					allCells[i][k].setCellType(2);
				}
			}
		}
 
    	/* If Any Cell Types 0 are left, mark them as 3, so that they fill with air */
    	for (int i = 0; i < numCellsX; i++) {
    		for (int k = 0; k < numCellsZ; k++) {
    			if( allCells[i][k].getCellType() == 0 ){
    				allCells[i][k].setCellType(3);
            		allCells[i][k].addCellSubType(CellType.AirCell);
    			}
    		}
    	}
    	
    	/* Add to world WallBlocks at Locations in Wall Cells */
    	for (int i = 0; i < numCellsX; i++) {
    		for (int k = 0; k < numCellsZ; k++) {
    			buildingManager.createBuilding(allCells, i, k, floorHeight, classRandom);
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
    
    
    

}
