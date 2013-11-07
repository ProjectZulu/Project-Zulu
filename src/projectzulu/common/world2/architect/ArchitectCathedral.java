package projectzulu.common.world2.architect;

import java.awt.Point;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.MazeCell;
import projectzulu.common.world2.blueprint.BlueprintSet;
import projectzulu.common.world2.blueprint.cathedral.BPSetCathedral;

public class ArchitectCathedral extends ArchitectBase {

    public ArchitectCathedral(World world) {
        super(world);
        stockpile.addBlueprintSet(new BPSetCathedral());
    }

    @Override
    public void assignBlueprint(MazeCell[][] cells, Point buildCoords, int pass, int maxPass) {
        BlueprintSet set;
        set = stockpile.getRandomApplicable(cells, buildCoords);

        if (set != null) {
            set.assignCellsWithBlueprints(cells, buildCoords, random);
        }
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(MazeCell cell, ChunkCoordinates piecePos) {
        BlueprintSet set = stockpile.getBlueprintSet(cell);
        return set != null ? stockpile.getBlueprintSet(cell).getBlockFromBlueprint(piecePos, cell.size,
                cell.getHeight(), cell.getDirection(), random, cell.getBuildingID()) : null;
    }
}
