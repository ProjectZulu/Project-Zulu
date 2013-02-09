package projectzulu.common.world.blueprints;

import java.util.Random;

import net.minecraft.block.Block;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;

public class BlueprintCemetaryFountain2 extends Blueprint{

	@Override
	public BlockWithMeta getBlockFromBlueprint(int cellIndex, int cellSize,
			int curHeight, int maxHeight, int xIndex, int zIndex,
			Random random, CellIndexDirection cellIndexDirection) {
		maxHeight = 3;
		if(curHeight == 0){

			switch (cellIndexDirection.calcDirection(cellIndex, cellSize)){
			case NorthWall:
			case NorthMiddle:
				return new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 2);
			case SouthWall:
			case SouthMiddle:
				return new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 3);
			case EastWall:
			case NorthEastCorner:
			case SouthEastCorner:
			case EastMiddle:
				return new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 1);
			case WestWall:
			case NorthWestCorner:
			case SouthWestCorner:
			case WestMiddle:
				return new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 0);
			case Inner:
			case Middle:
				return new BlockWithMeta(Block.waterStill.blockID);
			default:
				break;
			}

		}
		return new BlockWithMeta(0);
	}

	@Override
	public int getWeight() {
		return 2;
	}
}
