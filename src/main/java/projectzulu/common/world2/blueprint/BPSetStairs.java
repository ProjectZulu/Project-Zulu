package projectzulu.common.world2.blueprint;

import java.awt.Point;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.MazeCell;

public class BPSetStairs implements BlueprintSet, Blueprint {

    BlockWithMeta floorblock;

    public BPSetStairs() {
        floorblock = new BlockWithMeta(Blocks.sandstone);
    }

    @Override
    public String getIdentifier() {
        return "stairs";
    }

    @Override
    public int getWeight() {
        return 0;
    }

    public boolean attemptAssignBlueprint(MazeCell[][] bottomFloor, MazeCell[][] topFloor, Point buildCoords,
            Random rand) {
        if (topFloor[buildCoords.x][buildCoords.y].rawState == 0
                && topFloor[buildCoords.x][buildCoords.y + 1].rawState >= 0
                ) { 
            Point botCoords = convertCellCoords(buildCoords.x, buildCoords.y, topFloor, bottomFloor);
            if (bottomFloor[botCoords.x][botCoords.y - 1].rawState == 0
                    && bottomFloor[botCoords.x][botCoords.y].rawState == 1
                    && bottomFloor[botCoords.x][botCoords.y + 1].rawState == 1) {
                if (rand.nextInt(1) == 0) {
                    topFloor[buildCoords.x][buildCoords.y].setBuildingProperties(getIdentifier() + "-top_1",
                            CellIndexDirection.Middle);
                    topFloor[buildCoords.x][buildCoords.y].rawState = -1;
                    topFloor[buildCoords.x][buildCoords.y + 1].setBuildingProperties(getIdentifier() + "-top_2",
                            CellIndexDirection.Middle);
                    topFloor[buildCoords.x][buildCoords.y + 1].rawState = -1;

                    bottomFloor[botCoords.x][botCoords.y].setBuildingProperties(getIdentifier() + "-bottom_1",
                            CellIndexDirection.Middle);
                    bottomFloor[botCoords.x][botCoords.y].rawState = -1;
                    bottomFloor[botCoords.x][botCoords.y + 1].setBuildingProperties(getIdentifier() + "-bottom_2",
                            CellIndexDirection.Middle);
                    bottomFloor[botCoords.x][botCoords.y + 1].rawState = -1;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets the Cell coords in the DestinationCells that coorspond to the same physical location as the in the origin
     * cell. Only considers XZ Plane.
     * 
     * @param coords
     * @param originCells
     * @param destinCells
     * @return
     */
    private Point convertCellCoords(int originX, int originY, MazeCell[][] originCells, MazeCell[][] destinCells) {
        ChunkCoordinates chunkCoords = originCells[originX][originY].initialPos;
        for (int i = 0; i < destinCells.length; i++) {
            for (int j = 0; j < destinCells[0].length; j++) {
                if (chunkCoords.posX == destinCells[i][j].initialPos.posX
                        && chunkCoords.posZ == destinCells[i][j].initialPos.posZ) {
                    return new Point(i, j);
                }
            }
        }
        return new Point(-1, -1);
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight,
            CellIndexDirection cellIndexDirection, Random random, String buildingID) {
        String[] subIndex = buildingID.split("-")[1].split("_");

        if (subIndex[0].equalsIgnoreCase("bottom")) {
            return getBottomStairBlock(piecePos, cellSize, cellHeight, cellIndexDirection, random,
                    Integer.parseInt(subIndex[1]));
        } else if (subIndex[0].equalsIgnoreCase("top")) {
            return getTopStairBlock(piecePos, cellSize, cellHeight, cellIndexDirection, random,
                    Integer.parseInt(subIndex[1]));
        }
        return new BlockWithMeta(Blocks.air);
    }

    public BlockWithMeta getBottomStairBlock(ChunkCoordinates piecePos, int cellSize, int cellHeight,
            CellIndexDirection cellIndexDirection, Random random, int index) {
        index = index - 1;
        if (piecePos.posY == 0) {
            return new BlockWithMeta(Blocks.sandstone);
        }

        if (piecePos.posX == 0) {
            if (piecePos.posY > (piecePos.posZ + 1) + index * cellSize) {
                return new BlockWithMeta(Blocks.air);
            } else if (piecePos.posY == (piecePos.posZ + 1) + index * cellSize) {
                return new BlockWithMeta(Blocks.sandstone_stairs, 2);
            } else {
                return new BlockWithMeta(Blocks.sandstone);
            }
        }
        return new BlockWithMeta(Blocks.air);
    }

    public BlockWithMeta getTopStairBlock(ChunkCoordinates piecePos, int cellSize, int cellHeight,
            CellIndexDirection cellIndexDirection, Random random, int index) {
        boolean requiresStairs = index != 1;
        index = index + (cellSize - cellHeight - 2);
        if (piecePos.posY == 0) {
            if (piecePos.posX != 0) {
                return new BlockWithMeta(Blocks.sandstone);
            } else if (requiresStairs && piecePos.posY == (piecePos.posZ + 1) + index) {
                return new BlockWithMeta(Blocks.sandstone_stairs, 2);
            } else if (requiresStairs && piecePos.posY < (piecePos.posZ + 1) + index) {
                return new BlockWithMeta(Blocks.sandstone);
            }
        }
        return new BlockWithMeta(Blocks.air);
    }

    @Override
    public boolean assignCellsWithBlueprints(MazeCell[][] cells, Point buildCoords, Random random) {
        return false;
    }

    @Override
    public boolean isApplicable(MazeCell[][] cells, Point buildCoords, Random random) {
        return false;
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        throw new UnsupportedOperationException();
    }
}
