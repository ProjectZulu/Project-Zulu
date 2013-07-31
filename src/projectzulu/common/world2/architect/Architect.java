package projectzulu.common.world2.architect;

import java.awt.Point;

import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.MazeCell;

public interface Architect {

    /**
     * Assigns a Blueprint to the relevant MazeCells. Should only set one BlueprintSet per call, but should assign that
     * set to multiple cells if applicable.
     * 
     * @param cells MazeCell array
     * @param buildCoords Current cell being evaluated
     * @param Current Pass Number
     * @param Maximum number of Passes
     */
    public abstract void assignBlueprint(MazeCell[][] cells, Point buildCoords, int pass, int maxPass);

    public abstract BlockWithMeta getBlockFromBlueprint(MazeCell cell, ChunkCoordinates piecePos);
}