package projectzulu.common.world2.blueprint;

import java.awt.Point;
import java.util.Random;

import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.MazeCell;

public class BPSetGenericCarved implements BlueprintSet {

    Blueprint blueprint;

    public BPSetGenericCarved(Blueprint blueprint) {
        this.blueprint = blueprint;
    }

    @Override
    public boolean assignCellsWithBlueprints(MazeCell[][] cells, Point buildCoords, Random random) {
        cells[buildCoords.x][buildCoords.y].setBuildingProperties(getIdentifier(), CellIndexDirection.Middle);
        return true;
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight,
            CellIndexDirection cellIndexDirection, Random random, String buildingID) {
        return blueprint.getBlockFromBlueprint(piecePos, cellSize, cellHeight, random, cellIndexDirection);
    }

    @Override
    public boolean isApplicable(MazeCell[][] cells, Point buildCoords, Random random) {
        return cells[buildCoords.x][buildCoords.y].rawState == 1 ? true : false;
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
