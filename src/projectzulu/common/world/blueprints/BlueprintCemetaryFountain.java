package projectzulu.common.world.blueprints;

import java.util.Random;

import net.minecraft.block.Block;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.BlockDataObjects.BlockWithMeta;

public class BlueprintCemetaryFountain extends Blueprint{

	@Override
	public BlockWithMeta getBlockFromBlueprint(int cellIndex, int cellSize,
			int curHeight, int maxHeight, int xIndex, int zIndex,
			Random random, CellIndexDirection cellIndexDirection) {
		maxHeight = 3;
		if(curHeight == 0){
			switch (cellIndexDirection.calcDirection(cellIndex, cellSize)){
			case NorthMiddle:
				return new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 2);
			case NorthEastCorner:
			case NorthWestCorner:
				return new BlockWithMeta(Block.stoneSingleSlab.blockID, 5);
			case Inner:
			case Middle:
				return new BlockWithMeta(Block.waterStill.blockID);
			default:
				return new BlockWithMeta(Block.stoneBrick.blockID, 0);
			}
		}
		
		if(curHeight < maxHeight - 2){
			switch (cellIndexDirection.calcDirection(cellIndex, cellSize)){
			case NorthMiddle:
			case NorthEastCorner:
			case NorthWestCorner:
			case Inner:
			case Middle:
				return new BlockWithMeta(0);
			default:
				return new BlockWithMeta(Block.stoneBrick.blockID, 0);
			}
		}
		
		if(curHeight == maxHeight - 2){
			switch (cellIndexDirection.calcDirection(cellIndex, cellSize)){
			case SouthEastCorner:
				return new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 1);
			case SouthWestCorner:
				return new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 0);
			case SouthWall:
			case SouthMiddle:
			case EastWall:
			case EastMiddle:
			case WestWall:
			case WestMiddle:
				return new BlockWithMeta(Block.stoneBrick.blockID, 0);
			default:
				return new BlockWithMeta(0);
			}
		}
		if(curHeight == maxHeight - 1){
			switch (cellIndexDirection.calcDirection(cellIndex, cellSize)){
			case Inner:
			case Middle:
				return new BlockWithMeta(Block.stoneSingleSlab.blockID, 5);
			default:
				return new BlockWithMeta(0);

			}

		}
		
	
	return new BlockWithMeta(0);
	}
}
