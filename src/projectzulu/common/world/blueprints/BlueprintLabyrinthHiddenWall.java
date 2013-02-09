package projectzulu.common.world.blueprints;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityChest;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world.dataobjects.ChestWithMeta;
import projectzulu.common.world.dataobjects.MobSpawnerWithMeta;
import cpw.mods.fml.common.Loader;

public class BlueprintLabyrinthHiddenWall extends Blueprint{

	@Override
	public BlockWithMeta getBlockFromBlueprint(int cellIndex, int cellSize,
			int curHeight, int maxHeight, int xIndex, int zIndex,
			Random random, CellIndexDirection cellIndexDirection) {

		switch ( cellIndexDirection.calcDirection(cellIndex, cellSize) ) {
		/* Inner area is filled with air*/
		case Inner:
			return new BlockWithMeta(0);

		/* Inner middle is Chest */
		case Middle:
			if(curHeight == 0){
				if(random.nextInt(2) == 0){
					return new BlockWithMeta(Block.stoneBrick.blockID, 2);
				}else if( Loader.isModLoaded("ProjectZulu|Mobs") && random.nextInt(4) == 0){
					return new MobSpawnerWithMeta( DefaultProps.CoreModId.concat(".Minotaur") );
				}else if( Loader.isModLoaded("ProjectZulu|Mobs") ){
					return new MobSpawnerWithMeta( DefaultProps.CoreModId.concat(".Haunted Armor") );
				}else{
					return new MobSpawnerWithMeta( "Zombie" );
				}
			}else if(curHeight == 1){
				return new ChestWithMeta(Block.chest.blockID, 0, new TileEntityChest(), 25);
			}
			break;
			
		/* Outer Area is Brick */
		case NorthWestCorner:
		case NorthEastCorner:
		case SouthWestCorner:
		case SouthEastCorner:
		case NorthMiddle:
		case SouthMiddle:
		case NorthWall:
		case SouthWall:
		case WestWall:
		case EastWall:
			return new BlockWithMeta(Block.stoneBrick.blockID, 2);
		default:
			return new BlockWithMeta(Block.stoneBrick.blockID, 2);
		}

		return new BlockWithMeta(0);
	}

}
