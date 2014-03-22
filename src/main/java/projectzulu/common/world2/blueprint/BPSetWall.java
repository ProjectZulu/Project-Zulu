package projectzulu.common.world2.blueprint;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.WeightedRandom;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.MazeCell;

public class BPSetWall implements BlueprintSet, Blueprint {

    final List<BlockWithMeta> blocks = new ArrayList<BlockWithMeta>();

    public BPSetWall() {
        blocks.add(new BlockWithMeta(Blocks.sandstone, 0, 5));
        blocks.add(new BlockWithMeta(Blocks.sandstone, 1, 5));
        blocks.add(new BlockWithMeta(Blocks.sandstone, 2, 5));
        blocks.add(new BlockWithMeta(Blocks.sandstone, 3, 5));
    }

    @Override
    public String getIdentifier() {
        return "wall";
    }

    @Override
    public int getWeight() {
        return 1;
    }

    @Override
    public boolean assignCellsWithBlueprints(MazeCell[][] cells, Point buildCoords, Random random) {
        cells[buildCoords.x][buildCoords.y].setBuildingProperties(getIdentifier(), CellIndexDirection.Middle);
        return true;
    }

    @Override
    public boolean isApplicable(MazeCell[][] cells, Point buildCoords, Random random) {
        return cells[buildCoords.x][buildCoords.y].rawState == 1;
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight,
            CellIndexDirection cellIndexDirection, Random random, String buildingID) {
        return getBlockFromBlueprint(piecePos, cellSize, cellHeight, random, cellIndexDirection);
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        return (BlockWithMeta) WeightedRandom.getRandomItem(random, blocks);
    }
}