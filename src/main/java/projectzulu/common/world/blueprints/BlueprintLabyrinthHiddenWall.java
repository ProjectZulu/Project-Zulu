package projectzulu.common.world.blueprints;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.tileentity.TileEntityChest;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world.dataobjects.ChestWithMeta;
import projectzulu.common.world.dataobjects.MobSpawnerWithMeta;
import projectzulu.common.world.terrain.LabyrinthFeature;

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
                LabyrinthFeature feature = (LabyrinthFeature) ProjectZulu_Core.featureGenerator
                        .getRegisteredStructure(LabyrinthFeature.LABYRINTH);
                String entityName = feature.getEntityEntry(random);
                if (EntityList.stringToClassMapping.containsKey(entityName)) {
                    return new MobSpawnerWithMeta(entityName);
                } else {
                    if (!entityName.equalsIgnoreCase("EMPTY")) {
                        ProjectZuluLog.severe("Entity with name %s does not seem to exist.", entityName);
                    }
                    return new BlockWithMeta(Block.stoneBrick.blockID, 2);
                }
			}else if(curHeight == 1){
                LabyrinthFeature feature = (LabyrinthFeature) ProjectZulu_Core.featureGenerator
                        .getRegisteredStructure(LabyrinthFeature.LABYRINTH);
                return new ChestWithMeta(Block.chest.blockID, 0, new TileEntityChest(), feature.chestLootChance,
                        feature.chestMaxLoot);
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
