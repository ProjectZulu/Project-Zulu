package projectzulu.common.world.blueprints;

import java.util.Random;

import net.minecraft.block.Block;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.blockdataobjects.BlockWithMeta;

public class BlueprintLabyrinthCobweb extends Blueprint{

	@Override
	public BlockWithMeta getBlockFromBlueprint(int cellIndex, int cellSize,
			int curHeight, int maxHeight, int xIndex, int zIndex,
			Random random, CellIndexDirection cellIndexDirection) {
		
		/* Cobweb if on Floor or Ceiling */
		if( (curHeight == 0 || curHeight == maxHeight - 1) && 10 - random.nextInt(100) >= 0 ){
			return new BlockWithMeta(Block.web.blockID, 0);
		}else{
			return new BlockWithMeta(0);
		}
	}

}
