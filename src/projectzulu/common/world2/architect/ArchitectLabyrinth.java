package projectzulu.common.world2.architect;

import java.awt.Point;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.MazeCell;
import projectzulu.common.world2.blueprint.BPSetGenericCarved;
import projectzulu.common.world2.blueprint.BPSetGenericDeadEnd;
import projectzulu.common.world2.blueprint.BPSetGenericUncarved;
import projectzulu.common.world2.blueprint.BlueprintDeadEndChest;
import projectzulu.common.world2.blueprint.BlueprintLabyrinthCobweb;
import projectzulu.common.world2.blueprint.BlueprintLabyrinthHiddenWall;
import projectzulu.common.world2.blueprint.BlueprintLabyrinthRandomWall;
import projectzulu.common.world2.blueprint.BlueprintLabyrinthStair;
import projectzulu.common.world2.blueprint.BlueprintSet;

public class ArchitectLabyrinth extends ArchitectBase {

    BlueprintSet stair = new BPSetGenericCarved(new BlueprintLabyrinthStair());
    BlueprintSet room = new BPSetGenericUncarved(new BlueprintLabyrinthCobweb());
    BlueprintSet wall = new BPSetGenericCarved(new BlueprintLabyrinthRandomWall());

    public ArchitectLabyrinth(World world) {
        super(world);
        stockpile.addBlueprintSet(new BPSetGenericDeadEnd(new BlueprintDeadEndChest(), 0, 1));
        stockpile.addBlueprintSet(room);
        stockpile.addBlueprintSet(new BPSetGenericCarved(new BlueprintLabyrinthHiddenWall()));
        stockpile.addBlueprintSet(wall);
        stockpile.addBlueprintSet(stair);
    }

    @Override
    public void assignBlueprint(MazeCell[][] cells, Point buildCoords, int pass, int maxPass) {
        BlueprintSet set;
        /* Entrance is Manually place entrance at X == length / 2 and is cells long along z axis */
        if (buildCoords.x == cells.length / 2 && buildCoords.y <= 3) {
            set = room;
        } else if (isEdge(cells, buildCoords)) {
            set = wall;
        } else {
            set = stockpile.getRandomApplicable(cells, buildCoords);
        }

        if (set != null) {
            set.assignCellsWithBlueprints(cells, buildCoords, random);
        }
    }

    public void assignStairs(MazeCell[][] cells, Point buildCoords, int pass, int maxPass) {
        stair.assignCellsWithBlueprints(cells, buildCoords, random);
    }

    private boolean isEdge(MazeCell[][] cells, Point buildCoords) {
        if (buildCoords.x == 0 || buildCoords.y == 0 || buildCoords.x == cells.length - 1
                || buildCoords.y == cells[0].length - 1) {
            return true;
        }
        return false;
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(MazeCell cell, ChunkCoordinates piecePos) {
        BlueprintSet set = stockpile.getBlueprintSet(cell);
        return set != null ? stockpile.getBlueprintSet(cell).getBlockFromBlueprint(piecePos, cell.size,
                cell.getHeight(), cell.getDirection(), random, cell.getBuildingID()) : null;
    }
}
