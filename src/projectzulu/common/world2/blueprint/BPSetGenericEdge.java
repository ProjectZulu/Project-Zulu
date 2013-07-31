package projectzulu.common.world2.blueprint;

import java.awt.Point;
import java.util.Random;

import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.MazeCell;

public class BPSetGenericEdge implements BlueprintSet {

    Blueprint blueprint;
    public BPSetGenericEdge(Blueprint blueprint) {
        this.blueprint = blueprint;
    }

    @Override
    public boolean assignCellsWithBlueprints(MazeCell[][] cells, Point buildCoords, Random random) {
        if (buildCoords.y == 0) {
            if (buildCoords.x == 0) {
                cells[buildCoords.x][buildCoords.y].setBuildingProperties(getIdentifier(),
                        CellIndexDirection.NorthWestCorner);
            } else if (buildCoords.x == cells.length - 1) {
                cells[buildCoords.x][buildCoords.y].setBuildingProperties(getIdentifier(),
                        CellIndexDirection.NorthEastCorner);
            } else {
                cells[buildCoords.x][buildCoords.y]
                        .setBuildingProperties(getIdentifier(), CellIndexDirection.NorthWall);
            }
            return true;
        } else if (buildCoords.y == cells[0].length - 1) {
            if (buildCoords.x == 0) {
                cells[buildCoords.x][buildCoords.y].setBuildingProperties(getIdentifier(),
                        CellIndexDirection.SouthWestCorner);
            } else if (buildCoords.x == cells.length - 1) {
                cells[buildCoords.x][buildCoords.y].setBuildingProperties(getIdentifier(),
                        CellIndexDirection.SouthEastCorner);
            } else {
                cells[buildCoords.x][buildCoords.y]
                        .setBuildingProperties(getIdentifier(), CellIndexDirection.SouthWall);
            }
            return true;
        }

        if (buildCoords.x == 0) {
            cells[buildCoords.x][buildCoords.y].setBuildingProperties(getIdentifier(), CellIndexDirection.WestWall);
            return true;
        } else if (buildCoords.x == cells.length - 1) {
            cells[buildCoords.x][buildCoords.y].setBuildingProperties(getIdentifier(), CellIndexDirection.EastWall);
            return true;
        }
        return false;
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight,
            CellIndexDirection cellIndexDirection, Random random, String buildingID) {
        return blueprint.getBlockFromBlueprint(piecePos, cellSize, cellHeight, random, cellIndexDirection);
    }

    @Override
    public boolean isApplicable(MazeCell[][] cells, Point buildCoords, Random random) {
        return buildCoords.x == 0 || buildCoords.x == cells.length - 1 || buildCoords.y == 0
                || buildCoords.y == cells[0].length - 1;
    }

    @Override
    public String getIdentifier() {
        return blueprint.getIdentifier();
    }

    @Override
    public int getWeight() {
        return blueprint.getWeight();
    }
}
