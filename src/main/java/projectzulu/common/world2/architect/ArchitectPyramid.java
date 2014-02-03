package projectzulu.common.world2.architect;

import java.awt.Point;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.MazeCell;
import projectzulu.common.world2.blueprint.BPSetDoorway;
import projectzulu.common.world2.blueprint.BPSetHallway;
import projectzulu.common.world2.blueprint.BPSetHallwaySpawner;
import projectzulu.common.world2.blueprint.BPSetPyramidEdge;
import projectzulu.common.world2.blueprint.BPSetStairs;
import projectzulu.common.world2.blueprint.BPSetTreasureDeadEnd;
import projectzulu.common.world2.blueprint.BPSetWall;
import projectzulu.common.world2.blueprint.BlueprintSet;

public class ArchitectPyramid extends ArchitectBase {

    public final BPSetStairs stairSet = new BPSetStairs();
    public final BPSetPyramidEdge edge = new BPSetPyramidEdge();
    public final BPSetDoorway door = new BPSetDoorway();

    public ArchitectPyramid(World world) {
        super(world);
        stockpile.addBlueprintSet(new BPSetWall());
        stockpile.addBlueprintSet(new BPSetTreasureDeadEnd());
        stockpile.addBlueprintSet(new BPSetHallway());
        stockpile.addBlueprintSet(new BPSetHallwaySpawner());
        stockpile.addBlueprintSet(stairSet);
        stockpile.addBlueprintSet(edge);
        stockpile.addBlueprintSet(door);
    }

    @Override
    public void assignBlueprint(MazeCell[][] cells, Point buildCoords, int pass, int maxPass) {
        /* If is Wall, use Wall Cell */
        BlueprintSet set;
        if (door.isApplicable(cells, buildCoords, random)) {
            set = door;
        } else if (edge.isApplicable(cells, buildCoords, random)) {
            set = edge;
        } else {
            set = stockpile.getRandomApplicable(cells, buildCoords);
        }

        if (set != null) {
            set.assignCellsWithBlueprints(cells, buildCoords, random);
        }
    }

    public void assignStairs(MazeCell[][] bottomCells, MazeCell[][] topCells) {
        for (int topX = 0; topX < topCells.length; topX++) {
            for (int topZ = 0; topZ < topCells[0].length; topZ++) {
                Point buildCoords = new Point(topX, topZ);
                if (stairSet.attemptAssignBlueprint(bottomCells, topCells, buildCoords, random)) {
                    return;
                }
            }
        }
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(MazeCell cell, ChunkCoordinates piecePos) {
        BlueprintSet set = stockpile.getBlueprintSet(cell);
        return set != null ? stockpile.getBlueprintSet(cell).getBlockFromBlueprint(piecePos, cell.size,
                cell.getHeight(), cell.getDirection(), random, cell.getBuildingID()) : null;
    }
}
