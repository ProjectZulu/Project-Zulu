package projectzulu.common.world2.architect;

import java.awt.Point;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.MazeCell;
import projectzulu.common.world2.blueprint.BPSetGenericCardinal;
import projectzulu.common.world2.blueprint.BPSetGenericCarved;
import projectzulu.common.world2.blueprint.BPSetGenericEdge;
import projectzulu.common.world2.blueprint.BPSetGenericUncarved;
import projectzulu.common.world2.blueprint.BlueprintSet;
import projectzulu.common.world2.blueprints.BPScatteredTombstonesAndFlowers;
import projectzulu.common.world2.blueprints.BlueprintCemetaryEdge;
import projectzulu.common.world2.blueprints.BlueprintCemetaryFountain;
import projectzulu.common.world2.blueprints.BlueprintCemetaryFountain2;
import projectzulu.common.world2.blueprints.BlueprintCemeteryTomb;
import projectzulu.common.world2.blueprints.BlueprintCemeteryTomb2;

public class ArchitectCemetary extends ArchitectBase {
    BlueprintSet edge = new BPSetGenericEdge(new BlueprintCemetaryEdge());

    public ArchitectCemetary(World world) {
        super(world);
        stockpile.addBlueprintSet(new BPSetGenericUncarved(new BPScatteredTombstonesAndFlowers()));
        stockpile.addBlueprintSet(new BPSetGenericCardinal(new BlueprintCemeteryTomb(), 1));
        stockpile.addBlueprintSet(new BPSetGenericCardinal(new BlueprintCemeteryTomb2(), 1));
        stockpile.addBlueprintSet(new BPSetGenericCardinal(new BlueprintCemetaryFountain(), 1));
        stockpile.addBlueprintSet(new BPSetGenericCarved(new BlueprintCemetaryFountain2()));
        stockpile.addBlueprintSet(edge);
    }

    @Override
    public void assignBlueprint(MazeCell[][] cells, Point buildCoords, int pass, int maxPass) {
        BlueprintSet set;
        if (edge.isApplicable(cells, buildCoords, random)) {
            set = edge;
        } else {
            set = stockpile.getRandomApplicable(cells, buildCoords);
        }

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
