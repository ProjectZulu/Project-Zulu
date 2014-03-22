package projectzulu.common.world2.blueprint;

import java.awt.Point;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.MazeCell;

public class BPSetHallway implements BlueprintSet, Blueprint {

    BlockWithMeta block;
    BlockWithMeta floorblock;

    public BPSetHallway() {
        floorblock = new BlockWithMeta(Blocks.sandstone);
        block = new BlockWithMeta(Blocks.air);
    }

    @Override
    public String getIdentifier() {
        return "hallway";
    }

    @Override
    public int getWeight() {
        return 10;
    }

    @Override
    public boolean assignCellsWithBlueprints(MazeCell[][] cells, Point buildCoords, Random random) {
        cells[buildCoords.x][buildCoords.y].setBuildingProperties(getIdentifier(), CellIndexDirection.Middle);
        return true;
    }

    @Override
    public boolean isApplicable(MazeCell[][] cells, Point buildCoords, Random random) {
        return cells[buildCoords.x][buildCoords.y].rawState == 0;
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight,
            CellIndexDirection cellIndexDirection, Random random, String buildingID) {
        return getBlockFromBlueprint(piecePos, cellSize, cellHeight, random, cellIndexDirection);
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
//        if (random.nextInt(10) == 0) {
//            return new BlockWithMeta(Block.tnt.blockID);
//        } else {
//            return floorblock;
//        }
        
        if (piecePos.posY == 0) {
                return floorblock;
        } else {
            return block;
        }
    }
}
