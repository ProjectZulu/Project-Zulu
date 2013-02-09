package projectzulu.common.world.blueprints;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityChest;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world.dataobjects.ChestWithMeta;

public class BlueprintCemeteryTomb extends Blueprint{

	@Override
	public BlockWithMeta getBlockFromBlueprint(int cellIndex, int cellSize,
			int curHeight, int maxHeight, int xIndex, int zIndex, Random random, CellIndexDirection cellIndexDirection) {

		/* Place Chest */
		if(curHeight == 0 && cellIndexDirection.calcDirection(cellIndex, cellSize).equals(CellIndexDirection.Middle)){
			return new ChestWithMeta(Block.chest.blockID, 1, new TileEntityChest(), 15);
		}

		/* Place Door */
		if( curHeight == 0 && cellIndexDirection.calcDirection(cellIndex, cellSize).equals(cellIndexDirection) ){
			switch (cellIndexDirection){
			case NorthMiddle:
				return new BlockWithMeta(Block.doorSteel.blockID, 3);
			case SouthMiddle:
				return new BlockWithMeta(Block.doorSteel.blockID, 4);
			case EastMiddle:
				return new BlockWithMeta(Block.doorSteel.blockID, 5);
			case WestMiddle:
				return new BlockWithMeta(Block.doorSteel.blockID, 2);
			default:
				break;
			}
		}else if( curHeight == 1 && cellIndexDirection.calcDirection(cellIndex, cellSize).equals(cellIndexDirection) ){
			switch (cellIndexDirection){
			case NorthMiddle:
				return new BlockWithMeta(Block.doorSteel.blockID, 11);
			case SouthMiddle:
				return new BlockWithMeta(Block.doorSteel.blockID, 12);
			case EastMiddle:
				return new BlockWithMeta(Block.doorSteel.blockID, 13);
			case WestMiddle:
				return new BlockWithMeta(Block.doorSteel.blockID, 10);
			default:
				break;
			}
		}else if(curHeight < maxHeight){
			/* Place Ceiling */
			if(curHeight == maxHeight - 1 ){
				switch (cellIndexDirection.calcDirection(cellIndex, cellSize)){
				case Middle:
				case Inner:
					return new BlockWithMeta(Block.stoneSingleSlab.blockID, 5);
				default:
					break;
				}

				/* Place Main Body */
			}else if(curHeight % 2 == 0){
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
				default:
					break;
				}
			}else{
				switch (cellIndexDirection.calcDirection(cellIndex, cellSize)){
				case NorthWall:
				case NorthMiddle:
					return new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 2+4);
				case SouthWall:
				case SouthMiddle:
					return new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 3+4);
				case EastWall:
				case NorthEastCorner:
				case SouthEastCorner:
				case EastMiddle:
					return new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 1+4);
				case WestWall:
				case NorthWestCorner:
				case SouthWestCorner:
				case WestMiddle:
					return new BlockWithMeta(Block.stairsStoneBrickSmooth.blockID, 0+4);
				default:
					break;
				}
			}

		}
		return new BlockWithMeta(0);
	}

	@Override
	public int getWeight() {
		return 2;
	}
}
