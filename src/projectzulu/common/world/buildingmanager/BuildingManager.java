package projectzulu.common.world.buildingmanager;

import java.awt.Point;
import java.util.Random;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import projectzulu.common.world.BlockWithMeta;
import projectzulu.common.world.MazeCell;
import projectzulu.common.world.architects.Architect;

public abstract class BuildingManager {
	World world;
	Architect architect;
	public BuildingManager(World world){
		this.world = world;
		architect = getArchitect();
	}
	
	/**
	 * Used to Set the Architect for this building manager, Overriden in Child Classes for requried architect
	 */
	abstract Architect getArchitect();
	
	/**
	 * Used to Place Blocks Just below the Maze
	 */
	public abstract void createFloor(Vec3 startingPos, int width, int floorHeight, int floorNumber, int cellSize);

	public abstract boolean evaluateCarvedCells(MazeCell[][] cellList, int xIndex, int zIndex, int numCellsX, int numCellsZ, Random random);
	
	public abstract boolean evaluateUnCarvedCells(MazeCell[][] cellList, int xIndex, int zIndex, int numCellsX, int numCellsZ, Random random);

	public abstract void createBuilding(MazeCell[][] cellList, int xIndex, int zIndex, int floorHeight, Random random);
	
	/**
	 * Used for the Create of Post Maze Structures such as Entrances 
	 */
	public abstract void createSpecial(Vec3 startingPos, int width, int floorHeight, int floorNumber, int cellSize);

	protected void HandleBlockPlacement(BlockWithMeta blockWithMeta, ChunkCoordinates position, Random random){
		
		/* Check if There is a Tile At This Block, if so, remove it
		 * This Doesn't Seem to Work, So Block Is only placed if there is not TileEntity so as to prevent crash
		 */
		TileEntity tileEntity = world.getBlockTileEntity(position.posX, position.posY, position.posZ);
		if(tileEntity != null){
			tileEntity.invalidate();
			world.removeBlockTileEntity(position.posX, position.posY, position.posZ);
		}else{
			
			/* Check Block to See How Block wants to be Placed */
			blockWithMeta.placeBlock(world, position, random);
		}
		
	}
	
	/**
	 * Method of Randomly Place a Block
	 * @param position: Position To Place Block
	 * @param random
	 * @param blockWithMeta: Custom Object To Store Both BlockID and MetaData
	 */
	protected void placeRandomBlock(ChunkCoordinates position, Random random, int probability, int probabilityOutOf, BlockWithMeta... blockWithMeta){		
		for (int i = 0; i < blockWithMeta.length; i++){
			if(probability - random.nextInt(probabilityOutOf) >= 0 && (i + 1) < blockWithMeta.length){
				HandleBlockPlacement(new BlockWithMeta(blockWithMeta[i].blockID, blockWithMeta[i].meta),
						position, random);
				break;
			}else{
				HandleBlockPlacement(new BlockWithMeta(blockWithMeta[i].blockID, blockWithMeta[i].meta), position, random);
			}
		}
	}

	
	/** Returns whether or not the passed in XZ has  */
    protected Boolean isDeadEnd(MazeCell[][] allCells, int selectedX, int selectedZ, int maxCellsX, int maxCellsZ){
    	Point[] directions = new Point[4];
    	for (int i = 0; i < directions.length; i++) {
    		directions[i] = new Point();
		}
    	directions[0].setLocation(1,0);
    	directions[1].setLocation(0,1);
    	directions[2].setLocation(-1,0);
    	directions[3].setLocation(0,-1);
    	
    	int wallsNearby = 0;
    	for (int i = 0; i < directions.length; i++){
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
