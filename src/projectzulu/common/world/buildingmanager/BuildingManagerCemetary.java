package projectzulu.common.world.buildingmanager;

import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import projectzulu.common.api.BlockList;
import projectzulu.common.world.CellType;
import projectzulu.common.world.MazeCell;
import projectzulu.common.world.architects.Architect;
import projectzulu.common.world.architects.ArchitectCemetary;
import projectzulu.common.world.dataobjects.BlockWithMeta;

public class BuildingManagerCemetary extends BuildingManager {
	
	public BuildingManagerCemetary(World world){
		super(world);
	}
	
	@Override
	Architect getArchitect() {
		return new ArchitectCemetary();
	}
	
	@Override
	public void createFloor(Vec3 startingPos, int width, int floorHeight, int floorNumber, int cellSize) {
		if(floorNumber % floorHeight == 0){
			/* Create The Floor at Given j */
			for (int i = -(width-3); i < (width-3); i++) {
				for (int k = -(width-3); k < (width-3); k++) {
					HandleBlockPlacement(new BlockWithMeta(Block.grass.blockID),
							new ChunkCoordinates((int)startingPos.xCoord+i, (int)startingPos.yCoord+floorNumber*floorHeight, (int)startingPos.zCoord+k), new Random());
				}
			}
		}
	}
	
	@Override
	public void createSpecial(Vec3 startingPos, int width, int floorHeight,
			int floorNumber, int cellSize) {
		
	}
	
	@Override
	public boolean evaluateCarvedCells(MazeCell[][] cellList, int xIndex, int yIndex, int numCellsX, int numCellsZ, Random random){
		if(!cellList[xIndex][yIndex].ContainedInCellSubType(CellType.Wall) ){
			
			if(cellList[xIndex+1][yIndex].ContainedInCellSubType(CellType.InnerWall) || cellList[xIndex-1][yIndex].ContainedInCellSubType(CellType.InnerWall)
					|| cellList[xIndex][yIndex+1].ContainedInCellSubType(CellType.InnerWall) || cellList[xIndex][yIndex-1].ContainedInCellSubType(CellType.InnerWall)){
				cellList[xIndex][yIndex].addCellSubType(CellType.RandomUnCarved);
			}else{
				if(random.nextInt(4) < 3){
					cellList[xIndex][yIndex].addCellSubType(CellType.RandomUnCarved);
				}else{
					cellList[xIndex][yIndex].addCellSubType(CellType.InnerWall);
				}
			}

			return true;
		}
		return false;
	}

	@Override
	public boolean evaluateUnCarvedCells(MazeCell[][] cellList, int xIndex, int yIndex, int numCellsX, int numCellsZ, Random random){
		if(!cellList[xIndex][yIndex].ContainedInCellSubType(CellType.Wall)){
			cellList[xIndex][yIndex].addCellSubType(CellType.RandomUnCarved);
			return true;
		}
		
		return false;
		
		
//		if( cellList[xIndex][yIndex].getCellType() == 0 && isDeadEnd(cellList, xIndex, yIndex, numCellsX, numCellsZ) ){
//			cellList[xIndex][yIndex].setCellType(2);
//			cellList[xIndex][yIndex].addCellSubType(CellType.DeadEnd);
//    	}else if( cellList[xIndex][yIndex].getCellType() == 0){
//    		int holdRand = random.nextInt(100);
//    		if( 1 - holdRand >= 0 ){
//    			cellList[xIndex][yIndex].setCellType(4);
//    			cellList[xIndex][yIndex].addCellSubType(CellType.RandomUnCarved);
//    		}
//    	}
	}
	
    @Override
	public void createBuilding(MazeCell[][] cellList, int xIndex, int zIndex, int floorHeight, Random random){
    	
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
					/* Only Perform Action on Outer Portion
					 * The Check is based on the Knowleadge that each internal block is incremented along X by row then by column */
					for (int j = 0; j < floorHeight; j++) {
						if(j == 0){
							if( (cellIndex % cellSize == (0)) ){
								if(random.nextInt(4)==0){
									HandleBlockPlacement(new BlockWithMeta(0),
											new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
								}else{
									placeRandomBlockWall(new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
								}
							}else {
								if(5 - random.nextInt(100) >= 0 && BlockList.tombstone.isPresent()){
									HandleBlockPlacement(new BlockWithMeta(BlockList.tombstone.get().blockID),
											new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
								}else{
									HandleBlockPlacement(new BlockWithMeta(0), new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
								}
							}
						}else{
							HandleBlockPlacement(new BlockWithMeta(0), new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
						}
					}
					
					break;
				case SoutherWall:
					/* Only Perform Action on Outer Portion
					 * The Check is based on the Knowleadge that each internal block is incremented along X by row then by column */					
					for (int j = 0; j < floorHeight; j++) {
						if(j == 0){
							if( (cellIndex % cellSize == (cellSize-1)) ){
								if(random.nextInt(4)==0){
									HandleBlockPlacement(new BlockWithMeta(0),
											new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
								}else{
									placeRandomBlockWall(new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
								}
							}else {
								if(5 - random.nextInt(100) >= 0 && BlockList.tombstone.isPresent()){
									HandleBlockPlacement(new BlockWithMeta(BlockList.tombstone.get().blockID),
											new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
								}else{
									HandleBlockPlacement(new BlockWithMeta(0), new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
								}
							}
						}else{
							HandleBlockPlacement(new BlockWithMeta(0), new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
						}
					}
					
					break;
				case EastWall:
					/* Only Perform Action on Outer Portion
					 * The Check is based on the Knowleadge that each internal block is assigned Across Z axis for Each X first */
					for (int j = 0; j < floorHeight; j++) {
						if(j == 0){
							if( cellIndex >= cellSize*(cellSize-1) && cellIndex < cellSize*cellSize ){
								if(random.nextInt(4)==0){
									HandleBlockPlacement(new BlockWithMeta(0),
											new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
								}else{
									placeRandomBlockWall(new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
								}
							}else {
								if(5 - random.nextInt(100) >= 0 && BlockList.tombstone.isPresent()){
									HandleBlockPlacement(new BlockWithMeta(BlockList.tombstone.get().blockID),
											new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
								}else{
									HandleBlockPlacement(new BlockWithMeta(0), new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
								}
							}
						}else{
							HandleBlockPlacement(new BlockWithMeta(0), new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
						}
					}
					break;
				case WestWall:
					/* Only Perform Action on Outer Portion
					 * The Check is based on the Knowleadge that each internal block is incremented along X by row then by column */
					for (int j = 0; j < floorHeight; j++) {
						if(j == 0){
							if( cellIndex >= 0 && cellIndex < cellSize ){
								if(random.nextInt(4)==0){
									HandleBlockPlacement(new BlockWithMeta(0),
											new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
								}else{
									placeRandomBlockWall(new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
								}
							}else {
								if(5 - random.nextInt(100) >= 0 && BlockList.tombstone.isPresent()){
									HandleBlockPlacement(new BlockWithMeta(BlockList.tombstone.get().blockID),
											new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
								}else{
									HandleBlockPlacement(new BlockWithMeta(0), new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
								}
							}
						}else{
							HandleBlockPlacement(new BlockWithMeta(0), new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
						}
					}
					break;
				case InnerWall:
					/* Place Walls Vertically Until Size of Floor */
					for (int j = 0; j < floorHeight; j++) {
						HandleBlockPlacement(architect.getCarvedBlock(cellIndex, cellSize, j, floorHeight, xIndex, zIndex, random),
								new ChunkCoordinates(position.posX, position.posY+j, position.posZ), random);
					}
					break;
				case DeadEnd:

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
    protected void HandleBlockPlacement(BlockWithMeta blockWithMeta,
    		ChunkCoordinates position, Random random) {
    	if(blockWithMeta.blockID == 0 && world.getBlockId(position.posX, position.posY, position.posZ) == Block.snow.blockID){
    		return;
    	}
    	
    	if(world.getBlockMaterial(position.posX, position.posY, position.posZ).equals(Material.wood) 
    			|| world.getBlockMaterial(position.posX, position.posY, position.posZ).equals(Material.leaves) ){
    		return;
    	}
    	
    	super.HandleBlockPlacement(blockWithMeta, position, random);
    }
    
	/**
	 * Helper Method For Placing Wall Blocks
	 * @param position
	 * @param random
	 */
	private void placeRandomBlockWall(ChunkCoordinates position, Random random){
		placeRandomBlock(new ChunkCoordinates(position.posX, position.posY,position.posZ), random, 50, 100, 
				new BlockWithMeta(Block.cobblestoneWall.blockID, 1), new BlockWithMeta(Block.cobblestoneWall.blockID, 0) );
	}

	/**
	 * Primary Method Of Deciding Which Block to Place, calls placeRandomBlockInternally
	 * @param position
	 * @param random
	 */
	private void placeRandomBlock(ChunkCoordinates position, Random random){
		placeRandomBlock(position, random, 5, 100, new BlockWithMeta(Block.stoneBrick.blockID, 2), new BlockWithMeta(Block.stoneBrick.blockID, 1),
				new BlockWithMeta(Block.stoneBrick.blockID, 0) );
	}
}
