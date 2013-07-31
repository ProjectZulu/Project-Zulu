package projectzulu.common.world2.blueprint;

import java.awt.Point;
import java.util.Random;

import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.MazeCell;
import projectzulu.common.world2.architect.ArchitectBase;

public class BPSetGenericDeadEnd implements BlueprintSet {

    Blueprint blueprint;
    int baseState;
    int[] surroundingRawStates;

    public BPSetGenericDeadEnd(Blueprint blueprint, int baseState, int... surroundingRawStates) {
        this.blueprint = blueprint;
        if (surroundingRawStates != null && surroundingRawStates.length > 0) {
            this.surroundingRawStates = surroundingRawStates;
        } else {
            surroundingRawStates = new int[] { baseState };
        }
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
        return cells[buildCoords.x][buildCoords.y].rawState == baseState
                && ArchitectBase.isDeadEnd(cells, buildCoords, surroundingRawStates) ? true : false;
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
