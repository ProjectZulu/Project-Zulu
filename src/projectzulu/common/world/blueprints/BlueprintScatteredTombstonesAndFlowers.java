package projectzulu.common.world.blueprints;

import java.util.Random;

import net.minecraft.block.Block;
import projectzulu.common.api.ItemBlockList;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.blockdataobjects.BlockWithMeta;
import projectzulu.common.world.blockdataobjects.HauntedArmorWithMeta;

public class BlueprintScatteredTombstonesAndFlowers extends Blueprint{
	@Override
	public BlockWithMeta getBlockFromBlueprint(int cellIndex, int cellSize, int curHeight, int maxHeight,
			int xIndex, int zIndex, Random random, CellIndexDirection cellIndexDirection) {
		if(curHeight == 0){
			if( random.nextInt(90) == 0 ){
				return new HauntedArmorWithMeta();
			}
			
			if( (xIndex % 2 == 0 && zIndex % 2 == 0) || (xIndex % 2 == 1 && zIndex % 2 == 1) ){
				if( 20 - random.nextInt(100) >= 0 && ((cellIndex % 2*cellSize) == cellSize ||  (cellIndex % 2*cellSize) == 2*cellSize-1 ) ){
					return ItemBlockList.tombstone.isPresent() ? new BlockWithMeta(ItemBlockList.tombstone.get().blockID) : new BlockWithMeta(0);
				}
			}
			
			
			else{
				if( 20 - random.nextInt(100) >= 0 && (cellIndex % 2*cellSize) == 0 ||  (cellIndex % 2*cellSize) == cellSize-1 ){
					return ItemBlockList.tombstone.isPresent() ? new BlockWithMeta(ItemBlockList.tombstone.get().blockID) : new BlockWithMeta(0);
				}else if(5 - random.nextInt(100) >= 0){
					return new BlockWithMeta(Block.plantRed.blockID,0);
				}else if(5 - random.nextInt(100) >= 0){
					return new BlockWithMeta(Block.plantYellow.blockID,0);
				}else if(40 - random.nextInt(100) >= 0){
					return new BlockWithMeta(Block.tallGrass.blockID,1);
				}
				return new BlockWithMeta(0);
			}
		}
		
		return new BlockWithMeta(0);
		
	}

}
