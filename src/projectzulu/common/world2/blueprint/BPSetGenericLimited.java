package projectzulu.common.world2.blueprint;

import java.awt.Point;
import java.util.Random;

import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.MazeCell;

public class BPSetGenericLimited implements BlueprintSet {

    Blueprint blueprint;
    int validState;
    final int maxToAssign;
    private int assigned = 0;
    public BPSetGenericLimited(Blueprint blueprint, int validState, int maxToAssign) {
        this.blueprint = blueprint;
        this.validState = validState;
        this.maxToAssign = maxToAssign;
    }

    @Override
    public boolean assignCellsWithBlueprints(MazeCell[][] cells, Point buildCoords, Random random) {
        cells[buildCoords.x][buildCoords.y].setBuildingProperties(getIdentifier(), CellIndexDirection.Middle);
        assigned++;
        return true;
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight,
            CellIndexDirection cellIndexDirection, Random random, String buildingID) {
        return blueprint.getBlockFromBlueprint(piecePos, cellSize, cellHeight, random, cellIndexDirection);
    }

    @Override
    public boolean isApplicable(MazeCell[][] cells, Point buildCoords, Random random) {
        return cells[buildCoords.x][buildCoords.y].rawState == validState && assigned < maxToAssign;
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
