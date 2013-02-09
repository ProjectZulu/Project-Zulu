package projectzulu.common.world.blueprints;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityChest;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world.dataobjects.ChestWithMeta;
import projectzulu.common.world.dataobjects.MimicWithMeta;

public class BlueprintDeadEndChest extends Blueprint{

	@Override
	public BlockWithMeta getBlockFromBlueprint(int cellIndex, int cellSize,
			int curHeight, int maxHeight, int xIndex, int zIndex,
			Random random, CellIndexDirection cellIndexDirection) {
		if(curHeight == 0 && random.nextInt(8) == 0){
			return new ChestWithMeta(Block.chest.blockID, 0, new TileEntityChest(), 15);
		}else 
			if(curHeight == 0 && random.nextInt(8) == 1){
			return new MimicWithMeta();
		}
		
		return new BlockWithMeta(0);
	}
	
	
	@Override
	public int getWeight() {
		return 0;
	}
	
	@Override
	public String getIdentifier() {
		return "deadend"; 
	}
	
}
