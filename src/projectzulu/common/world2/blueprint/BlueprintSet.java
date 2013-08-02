package projectzulu.common.world2.blueprint;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import net.minecraft.util.ChunkCoordinates;

import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.MazeCell;

public interface BlueprintSet {
    ArrayList<Blueprint> blueprints = new ArrayList<Blueprint>();

    /**
     * Determined if the BlueprintSet should be applied {@link#assignCellsWithBlueprints} to the cell. It is not used to
     * determine if {@link#getBlockFromBlueprint} should function.
     */
    public abstract boolean isApplicable(MazeCell[][] cells, Point buildCoords, Random random);

    public abstract boolean assignCellsWithBlueprints(MazeCell[][] cells, Point buildCoords, Random random);

    public abstract BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight,
            CellIndexDirection cellIndexDirection, Random random, String buildingID);

    public abstract String getIdentifier();

    public abstract int getWeight();
}