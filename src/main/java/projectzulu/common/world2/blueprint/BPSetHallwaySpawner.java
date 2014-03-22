package projectzulu.common.world2.blueprint;

import java.awt.Point;
import java.util.Random;

import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world.dataobjects.MobSpawnerWithMeta;
import projectzulu.common.world.terrain.PyramidFeature;
import projectzulu.common.world2.MazeCell;

public class BPSetHallwaySpawner implements BlueprintSet, Blueprint {

    BlockWithMeta floorblock;

    public BPSetHallwaySpawner() {
        floorblock = new BlockWithMeta(Blocks.sandstone);
    }

    @Override
    public String getIdentifier() {
        return "hallway_spawner";
    }

    @Override
    public int getWeight() {
        return 1;
    }

    @Override
    public boolean assignCellsWithBlueprints(MazeCell[][] cells, Point buildCoords, Random random) {
        cells[buildCoords.x][buildCoords.y].setBuildingProperties(getIdentifier(), CellIndexDirection.Middle);
        return true;
    }

    @Override
    public boolean isApplicable(MazeCell[][] cells, Point buildCoords, Random random) {
        return cells[buildCoords.x][buildCoords.y].rawState == 0;
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight,
            CellIndexDirection cellIndexDirection, Random random, String buildingID) {
        return getBlockFromBlueprint(piecePos, cellSize, cellHeight, random, cellIndexDirection);
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        if (piecePos.posY == 0) {
            return floorblock;
        } else if (piecePos.posY == 1 && piecePos.posX == 0 && piecePos.posZ == 0 && random.nextInt(2) == 0) {
            PyramidFeature feature = (PyramidFeature) ProjectZulu_Core.featureGenerator
                    .getRegisteredStructure(PyramidFeature.PYRAMID);
            String entityName = feature.getEntityEntry(random);
            if (EntityList.stringToClassMapping.containsKey(entityName)) {
                return new MobSpawnerWithMeta(entityName);
            } else {
                if (!entityName.equalsIgnoreCase("EMPTY")) {
                    ProjectZuluLog.severe("Entity with name %s does not seem to exist.", entityName);
                }
                return new BlockWithMeta(Blocks.air);
            }
        }
        return new BlockWithMeta(Blocks.air);
    }
}
