package projectzulu.common.world2.architect;

import java.awt.Point;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.CellHelper;
import projectzulu.common.world2.MazeCell;
import projectzulu.common.world2.blueprint.BPSetGenericCardinal;
import projectzulu.common.world2.blueprint.BPSetGenericCarved;
import projectzulu.common.world2.blueprint.BPSetGenericEdge;
import projectzulu.common.world2.blueprint.BPSetGenericLimited;
import projectzulu.common.world2.blueprint.BPSetGenericUncarved;
import projectzulu.common.world2.blueprint.BlueprintSet;
import projectzulu.common.world2.blueprints.oasis.BPOasisEdgeDiagonal;
import projectzulu.common.world2.blueprints.oasis.BPOasisEdgeRandomMid;
import projectzulu.common.world2.blueprints.oasis.BPOasisEdgeRandomPoint;
import projectzulu.common.world2.blueprints.oasis.BPOasisGrass;
import projectzulu.common.world2.blueprints.oasis.BPOasisPool;
import projectzulu.common.world2.blueprints.oasis.BPOasisSandCorner;
import projectzulu.common.world2.blueprints.oasis.BPOasisTreeGrass;

public class ArchitectOasis extends ArchitectBase {
    BlueprintSetStockpile edgeStockpile;
    BPSetGenericCarved sandCorner = new BPSetGenericCarved(new BPOasisSandCorner());

    public ArchitectOasis(World world) {
        super(world);
        edgeStockpile = new BlueprintSetStockpile(world.rand);
        edgeStockpile.addBlueprintSet(new BPSetGenericEdge(new BPOasisEdgeRandomPoint()));
        edgeStockpile.addBlueprintSet(new BPSetGenericEdge(new BPOasisEdgeDiagonal(false)));
        edgeStockpile.addBlueprintSet(new BPSetGenericEdge(new BPOasisEdgeDiagonal(true)));
        edgeStockpile.addBlueprintSet(new BPSetGenericEdge(new BPOasisEdgeRandomMid(false)));
        edgeStockpile.addBlueprintSet(new BPSetGenericEdge(new BPOasisEdgeRandomMid(true)));
        edgeStockpile.addBlueprintSet(sandCorner);
        stockpile.addBlueprintSet(new BPSetGenericUncarved(new BPOasisPool()));
        stockpile.addBlueprintSet(new BPSetGenericCardinal(new BPOasisGrass(), 1));
        stockpile.addBlueprintSet(new BPSetGenericLimited(new BPOasisTreeGrass(), 1, 3));
    }

    @Override
    public void assignBlueprint(MazeCell[][] cells, Point buildCoords, int pass, int maxPass) {
        BlueprintSet set = null;
        if (CellHelper.isCorner(cells, buildCoords)) {
            set = sandCorner;
        }
        if (set == null) {
            set = edgeStockpile.getRandomApplicable(cells, buildCoords);
        }
        if (set == null) {
            set = stockpile.getRandomApplicable(cells, buildCoords);
        }

        if (set != null) {
            set.assignCellsWithBlueprints(cells, buildCoords, random);
        }
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(MazeCell cell, ChunkCoordinates piecePos) {
        BlueprintSet set;
        set = edgeStockpile.getBlueprintSet(cell);
        if (set != null) {
            return edgeStockpile.getBlueprintSet(cell).getBlockFromBlueprint(piecePos, cell.size, cell.getHeight(),
                    cell.getDirection(), random, cell.getBuildingID());
        }
        set = stockpile.getBlueprintSet(cell);
        if (set != null) {
            return stockpile.getBlueprintSet(cell).getBlockFromBlueprint(piecePos, cell.size, cell.getHeight(),
                    cell.getDirection(), random, cell.getBuildingID());
        }
        return null;
    }
}
