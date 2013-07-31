package projectzulu.common.world2.blueprint;

import java.awt.Point;
import java.util.Random;

import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.MazeCell;

public class BPSetGenericCardinal implements BlueprintSet {
    Blueprint blueprint;
    int validRawState;

    public BPSetGenericCardinal(Blueprint blueprint, int validRawState) {
        this.blueprint = blueprint;
        this.validRawState = validRawState;
    }

    @Override
    public boolean assignCellsWithBlueprints(MazeCell[][] cells, Point buildCoords, Random random) {
        cells[buildCoords.x][buildCoords.y].setBuildingProperties(getIdentifier(),
                CellIndexDirection.randomCardinalDirection(random));
        return true;
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight,
            CellIndexDirection cellIndexDirection, Random random, String buildingID) {
        return blueprint.getBlockFromBlueprint(piecePos, cellSize, cellHeight, random, cellIndexDirection);
    }

    @Override
    public boolean isApplicable(MazeCell[][] cells, Point buildCoords, Random random) {
        return cells[buildCoords.x][buildCoords.y].rawState == validRawState ? true : false;
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
