package projectzulu.common.world.blueprints;

import java.util.Random;

import net.minecraft.block.Block;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.blockdataobjects.BlockWithMeta;

public class BlueprintLabyrinthRandomWall extends Blueprint{

	@Override
	public BlockWithMeta getBlockFromBlueprint(int cellIndex, int cellSize,
			int curHeight, int maxHeight, int xIndex, int zIndex,
			Random random, CellIndexDirection cellIndexDirection) {
		
		if(10 - random.nextInt(100) >= 0){
			return new BlockWithMeta(Block.stoneBrick.blockID, 2);
		}else if(10 - random.nextInt(100) >= 0){
			return new BlockWithMeta(Block.stoneBrick.blockID, 1);
		}else if (5 - random.nextInt(100) >= 0){
			return new BlockWithMeta(0);
		}else{
			return new BlockWithMeta(Block.stoneBrick.blockID, 0);
		}
		
	}
	
	@Override
	public int getWeight() {
		return 4;
	}
}
