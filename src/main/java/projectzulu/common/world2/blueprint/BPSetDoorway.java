package projectzulu.common.world2.blueprint;

import java.awt.Point;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.MazeCell;

public class BPSetDoorway implements BlueprintSet, Blueprint {
    private boolean hasBeenApplied = false;

    @Override
    public boolean isApplicable(MazeCell[][] cells, Point buildCoords, Random random) {
        if (random.nextInt(10) != 0) {
            return false;
        }

        if (isWestWall(cells, buildCoords)) {
            if (cells[buildCoords.x + 2][buildCoords.y].rawState == 0
                    && cells[buildCoords.x + 2][buildCoords.y + 1].rawState == 0) {
                boolean applied = hasBeenApplied;
                hasBeenApplied = true;
                return !applied;
            }
        }

        if (isEastWall(cells, buildCoords)) {
            if (cells[buildCoords.x - 2][buildCoords.y].rawState == 0
                    && cells[buildCoords.x - 2][buildCoords.y + 1].rawState == 0) {
                boolean applied = hasBeenApplied;
                hasBeenApplied = true;
                return !applied;
            }
        }

        if (isNorthWall(cells, buildCoords)) {
            if (cells[buildCoords.x][buildCoords.y + 2].rawState == 0
                    && cells[buildCoords.x + 1][buildCoords.y + 2].rawState == 0) {
                boolean applied = hasBeenApplied;
                hasBeenApplied = true;
                return !applied;
            }
        }

        if (isSouthWall(cells, buildCoords)) {
            if (cells[buildCoords.x][buildCoords.y - 2].rawState == 0
                    && cells[buildCoords.x + 1][buildCoords.y - 2].rawState == 0) {
                boolean applied = hasBeenApplied;
                hasBeenApplied = true;
                return !applied;
            }
        }

        return false;
    }

    @Override
    public boolean assignCellsWithBlueprints(MazeCell[][] cells, Point buildCoords, Random random) {
        if (isWestWall(cells, buildCoords)) {
            cells[buildCoords.x][buildCoords.y].setBuildingProperties(getIdentifier(), CellIndexDirection.NorthWall);
            cells[buildCoords.x][buildCoords.y].rawState = -1;

            cells[buildCoords.x][buildCoords.y + 1]
                    .setBuildingProperties(getIdentifier(), CellIndexDirection.SouthWall);
            cells[buildCoords.x][buildCoords.y + 1].rawState = -1;

            cells[buildCoords.x + 1][buildCoords.y]
                    .setBuildingProperties(getIdentifier(), CellIndexDirection.NorthWall);
            cells[buildCoords.x + 1][buildCoords.y].rawState = -1;

            cells[buildCoords.x + 1][buildCoords.y + 1].setBuildingProperties(getIdentifier(),
                    CellIndexDirection.SouthWall);
            cells[buildCoords.x + 1][buildCoords.y + 1].rawState = -1;
        } else if (isEastWall(cells, buildCoords)) {
            cells[buildCoords.x][buildCoords.y].setBuildingProperties(getIdentifier(), CellIndexDirection.NorthWall);
            cells[buildCoords.x][buildCoords.y].rawState = -1;

            cells[buildCoords.x][buildCoords.y + 1]
                    .setBuildingProperties(getIdentifier(), CellIndexDirection.SouthWall);
            cells[buildCoords.x][buildCoords.y + 1].rawState = -1;

            cells[buildCoords.x - 1][buildCoords.y]
                    .setBuildingProperties(getIdentifier(), CellIndexDirection.NorthWall);
            cells[buildCoords.x - 1][buildCoords.y].rawState = -1;

            cells[buildCoords.x - 1][buildCoords.y + 1].setBuildingProperties(getIdentifier(),
                    CellIndexDirection.SouthWall);
            cells[buildCoords.x - 1][buildCoords.y + 1].rawState = -1;
        } else if (isNorthWall(cells, buildCoords)) {
            cells[buildCoords.x][buildCoords.y].setBuildingProperties(getIdentifier(), CellIndexDirection.EastWall);
            cells[buildCoords.x][buildCoords.y].rawState = -1;

            cells[buildCoords.x][buildCoords.y + 1].setBuildingProperties(getIdentifier(), CellIndexDirection.EastWall);
            cells[buildCoords.x][buildCoords.y + 1].rawState = -1;

            cells[buildCoords.x + 1][buildCoords.y].setBuildingProperties(getIdentifier(), CellIndexDirection.WestWall);
            cells[buildCoords.x + 1][buildCoords.y].rawState = -1;

            cells[buildCoords.x + 1][buildCoords.y + 1].setBuildingProperties(getIdentifier(),
                    CellIndexDirection.WestWall);
            cells[buildCoords.x + 1][buildCoords.y + 1].rawState = -1;
        } else if (isSouthWall(cells, buildCoords)) {
            cells[buildCoords.x][buildCoords.y].setBuildingProperties(getIdentifier(), CellIndexDirection.EastWall);
            cells[buildCoords.x][buildCoords.y].rawState = -1;

            cells[buildCoords.x][buildCoords.y - 1].setBuildingProperties(getIdentifier(), CellIndexDirection.EastWall);
            cells[buildCoords.x][buildCoords.y - 1].rawState = -1;

            cells[buildCoords.x + 1][buildCoords.y].setBuildingProperties(getIdentifier(), CellIndexDirection.WestWall);
            cells[buildCoords.x + 1][buildCoords.y].rawState = -1;

            cells[buildCoords.x + 1][buildCoords.y - 1].setBuildingProperties(getIdentifier(),
                    CellIndexDirection.WestWall);
            cells[buildCoords.x + 1][buildCoords.y - 1].rawState = -1;
        }

        return true;
    }

    private boolean isWestWall(MazeCell[][] cells, Point buildCoords) {
        return buildCoords.x == 0 && buildCoords.y > 2 && buildCoords.y < cells[0].length - 3;
    }

    private boolean isEastWall(MazeCell[][] cells, Point buildCoords) {
        return buildCoords.x == cells.length - 1 && buildCoords.y > 2 && buildCoords.y < cells[0].length - 3;
    }

    private boolean isNorthWall(MazeCell[][] cells, Point buildCoords) {
        return buildCoords.y == 0 && buildCoords.x > 2 && buildCoords.x < cells.length - 3;
    }

    private boolean isSouthWall(MazeCell[][] cells, Point buildCoords) {
        return buildCoords.y == cells[0].length - 1 && buildCoords.x > 2 && buildCoords.x < cells.length - 3;
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight,
            CellIndexDirection cellIndexDirection, Random random, String buildingID) {
        if (cellIndexDirection == CellIndexDirection.SouthWall) {
            if (piecePos.posY == 0) {
                return new BlockWithMeta(Blocks.sandstone);
            } else if (piecePos.posZ == cellSize - 1) {
                return new BlockWithMeta(Blocks.sandstone);
            } else if (piecePos.posY == cellHeight - 1) {
                return new BlockWithMeta(Blocks.sandstone_stairs, 6);
            } else {
                return new BlockWithMeta(Blocks.air);
            }
        } else if (cellIndexDirection == CellIndexDirection.NorthWall) {
            if (piecePos.posY == 0) {
                return new BlockWithMeta(Blocks.sandstone);
            } else if (piecePos.posZ == 0) {
                return new BlockWithMeta(Blocks.sandstone);
            } else if (piecePos.posY == cellHeight - 1) {
                return new BlockWithMeta(Blocks.sandstone_stairs, 7);
            } else {
                return new BlockWithMeta(Blocks.air);
            }
        } else if (cellIndexDirection == CellIndexDirection.WestWall) {
            if (piecePos.posY == 0) {
                return new BlockWithMeta(Blocks.sandstone);
            } else if (piecePos.posX == cellSize - 1) {
                return new BlockWithMeta(Blocks.sandstone);
            } else if (piecePos.posY == cellHeight - 1) {
                return new BlockWithMeta(Blocks.sandstone_stairs, 4);
            } else {
                return new BlockWithMeta(Blocks.air);
            }
        } else if (cellIndexDirection == CellIndexDirection.EastWall) {
            if (piecePos.posY == 0) {
                return new BlockWithMeta(Blocks.sandstone);
            } else if (piecePos.posX == 0) {
                return new BlockWithMeta(Blocks.sandstone);
            } else if (piecePos.posY == cellHeight - 1) {
                return new BlockWithMeta(Blocks.sandstone_stairs, 5);
            } else {
                return new BlockWithMeta(Blocks.air);
            }
        }
        return new BlockWithMeta(Blocks.gold_block);
    }

    @Override
    public String getIdentifier() {
        return "Pyramid_Door";
    }

    @Override
    public int getWeight() {
        return 0;
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        return null;
    }
}
