package projectzulu.common.world.buildingmanager;

import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import projectzulu.common.world.BlockWithMeta;
import projectzulu.common.world.CellType;
import projectzulu.common.world.MazeCell;
import projectzulu.common.world.architects.Architect;
import projectzulu.common.world.architects.ArchitectLabyrinth;

public class BuildingManagerLabyrinth extends BuildingManager{

	public BuildingManagerLabyrinth(World world) {
		super(world);
	}

	@Override
	Architect getArchitect() {
		return new ArchitectLabyrinth();
	}

	@Override
	public void createFloor(Vec3 startingPos, int width, int floorHeight,
			int floorNumber, int cellSize) {
		/* Create The Floor at Given j */
		for (int i = -(width-cellSize); i < (width-cellSize); i++) {
			for (int k = -(width-3); k < (width-3); k++) {
				HandleBlockPlacement(
						new BlockWithMeta(Block.stoneBrick.blockID, 0),
						new ChunkCoordinates((int)startingPos.xCoord+i, (int)startingPos.yCoord+floorNumber*(floorHeight+1), (int)startingPos.zCoord+k),
						new Random());
			}
		}	
	}

	@Override
	public boolean evaluateCarvedCells(MazeCell[][] cellList, int xIndex,
			int yIndex, int numCellsX, int numCellsZ, Random random) {
		if(!cellList[xIndex][yIndex].ContainedInCellSubType(CellType.Wall) ){
			cellList[xIndex][yIndex].addCellSubType(CellType.InnerWall);
			return true;
		}
		return false;
	}

	@Override
	public boolean evaluateUnCarvedCells(MazeCell[][] cellList, int xIndex,
			int zIndex, int numCellsX, int numCellsZ, Random random) {		
		if(!cellList[xIndex][zIndex].ContainedInCellSubType(CellType.Wall)){

			if(isDeadEnd(cellList, xIndex, zIndex, numCellsX, numCellsZ) && cellList[xIndex][zIndex].getCellType() == 0 ){
				cellList[xIndex][zIndex].addCellSubType(CellType.DeadEnd);
				cellList[xIndex][zIndex].setBuildingSchematic(architect.searchUncarvedFor("deadend"));
				return true;
			}

			cellList[xIndex][zIndex].addCellSubType(CellType.RandomUnCarved);
			return true;
		}
		return false;
	}

	@Override
	public void createBuilding(MazeCell[][] cellList, int xIndex, int zIndex,
			int floorHeight, Random random) {

		int cellSize = cellList[xIndex][zIndex].getSize();

		/* Randomise the Architects State for this cell, Used to Determine what should be built */
		architect.randomiseState(random);

		for (int cellIndex = 0; cellIndex < (cellSize*cellSize); cellIndex++){
			/* Get Important Properties From Cell */
			Set<CellType> cellSubType = cellList[xIndex][zIndex].getCellSubType();
			ChunkCoordinates position = new ChunkCoordinates(
					(int)cellList[xIndex][zIndex].getLocation(cellIndex).xCoord,
					(int)cellList[xIndex][zIndex].getLocation(cellIndex).yCoord,
					(int)cellList[xIndex][zIndex].getLocation(cellIndex).zCoord);

			for (CellType cellType : cellSubType){

				switch (cellType){
				case NorthWall:
				case SoutherWall:
				case EastWall:
				case WestWall:
					/* Place Walls Vertically Until Size of Floor */
					for (int j = 0; j < floorHeight; j++) {
						HandleBlockPlacement(new BlockWithMeta(Block.stoneBrick.blockID, 0),
								new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
					}
					break;
				case InnerWall:
					/* Place Walls Vertically Until Size of Floor */
					for (int j = 0; j < floorHeight; j++) {
						HandleBlockPlacement(
								architect.getCarvedBlock(cellIndex, cellSize, j, floorHeight, xIndex, zIndex, random),
								new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
					}
					break;
				case DeadEnd:
					for (int j = 0; j < floorHeight; j++) {
						HandleBlockPlacement(architect.getUnCarvedBlock(cellIndex, cellSize, j, floorHeight, xIndex, zIndex, random,
								cellList[xIndex][zIndex].getCellIndexDirection(), cellList[xIndex][zIndex].getBuildingSchematic()),
								new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
					}
					break;
				case RandomUnCarved:
					for (int j = 0; j < floorHeight; j++) {
						HandleBlockPlacement(architect.getUnCarvedBlock(cellIndex, cellSize, j, floorHeight, xIndex, zIndex, random),
								new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
					}
					break;
				case AirCell:
					for (int j = 0; j < floorHeight; j++) {
						HandleBlockPlacement(new BlockWithMeta(0), new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
					}
					break;
				default:
					break;
				}
			}
		}


	}

	@Override
	public void createSpecial(Vec3 startingPos, int width, int floorHeight,
			int floorNumber, int cellSize) {
		/* Clear Out Cells In Maze Closest to Entrance */
		for (int k = 0; k < cellSize*2; k++) {
			for (int i = 0; i < cellSize; i++) {
				for (int j = 1; j <= floorHeight; j++) {
					HandleBlockPlacement(new BlockWithMeta(0),
							new ChunkCoordinates((int)startingPos.xCoord+i, (int)startingPos.yCoord+j, (int)startingPos.zCoord-k+(width-cellSize)), new Random());
				}
			}
		}
		
		/* Construct Ceiling */
		createFloor(startingPos, width, floorHeight, floorNumber+1, cellSize);
		for (int i = 0; i < cellSize; i++) {
			HandleBlockPlacement(new BlockWithMeta(0),
					new ChunkCoordinates((int)startingPos.xCoord+i, (int)startingPos.yCoord+floorHeight, (int)startingPos.zCoord+(width-cellSize)), new Random());
		}
		
		
		/* Create Staircase Entrance going from maze to Surface */
		int yHeight = 1;
		for (int k = 0; k < 10; k++) {
			for (int j = yHeight; j <= yHeight+5; j++) {
				for (int i = -1; i <= cellSize; i++) {
					if(i >= 0 && i < cellSize && j == yHeight){
						HandleBlockPlacement(new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 2), 
								new ChunkCoordinates((int)startingPos.xCoord+i, (int)startingPos.yCoord+j, (int)startingPos.zCoord+k+(width-cellSize)),
								new Random());
					}else if(i >= 0 && i < cellSize && j == yHeight+5){
						HandleBlockPlacement(new BlockWithMeta(Block.stoneBrick.blockID, 0), 
								new ChunkCoordinates((int)startingPos.xCoord+i, (int)startingPos.yCoord+j, (int)startingPos.zCoord+k+(width-cellSize)),
								new Random());
					}else if(i >= 0 && i < cellSize && j > yHeight && j < yHeight+5){
						HandleBlockPlacement(new BlockWithMeta(0), 
								new ChunkCoordinates((int)startingPos.xCoord+i, (int)startingPos.yCoord+j, (int)startingPos.zCoord+k+(width-cellSize)),
								new Random());
					}else{
						HandleBlockPlacement(new BlockWithMeta(Block.stoneBrick.blockID, 0), 
								new ChunkCoordinates((int)startingPos.xCoord+i, (int)startingPos.yCoord+j, (int)startingPos.zCoord+k+(width-cellSize)),
								new Random());
					}
				}
			}
			yHeight++;
		}	
	}


}
