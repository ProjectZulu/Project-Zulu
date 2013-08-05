package projectzulu.common.world2.blueprint.cathedral;

import java.awt.Point;
import java.util.Random;

import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.MazeCell;
import projectzulu.common.world2.blueprint.Blueprint;
import projectzulu.common.world2.blueprint.BlueprintSet;

public class BPSetCathedral implements BlueprintSet {
    boolean isAssigned = false;

    Blueprint dome = new BPCathedralDome();
    Blueprint hallway = new BPCathedralHallway();
    Blueprint hallwayEnd = new BPCathedralHallwayEnd();
    Blueprint hallwayEnt = new BPCathedralHallwayEntrance();
    Blueprint entrance = new BPCathedralEntrance();
    Blueprint west_tower = new BPCathedralWestTower();
    Blueprint east_tower = new BPCathedralEastTower();

    @Override
    public boolean isApplicable(MazeCell[][] cells, Point buildCoords, Random random) {
        return !isAssigned;
    }

    @Override
    public boolean assignCellsWithBlueprints(MazeCell[][] cells, Point buildCoords, Random random) {
        applyBlueprintToCell(cells, buildCoords.x + 2, buildCoords.y + 2, dome.getIdentifier(),
                CellIndexDirection.NorthWestCorner);
        applyBlueprintToCell(cells, buildCoords.x + 3, buildCoords.y + 2, dome.getIdentifier(),
                CellIndexDirection.NorthEastCorner);
        applyBlueprintToCell(cells, buildCoords.x + 2, buildCoords.y + 3, dome.getIdentifier(),
                CellIndexDirection.SouthWestCorner);
        applyBlueprintToCell(cells, buildCoords.x + 3, buildCoords.y + 3, dome.getIdentifier(),
                CellIndexDirection.SouthEastCorner);

        applyBlueprintToCell(cells, buildCoords.x + 2, buildCoords.y + 4, hallwayEnt.getIdentifier(),
                CellIndexDirection.WestWall);
        applyBlueprintToCell(cells, buildCoords.x + 3, buildCoords.y + 4, hallwayEnt.getIdentifier(),
                CellIndexDirection.EastWall);
        applyBlueprintToCell(cells, buildCoords.x + 2, buildCoords.y + 1, hallway.getIdentifier(),
                CellIndexDirection.WestWall);
        applyBlueprintToCell(cells, buildCoords.x + 3, buildCoords.y + 1, hallway.getIdentifier(),
                CellIndexDirection.EastWall);

        applyBlueprintToCell(cells, buildCoords.x + 1, buildCoords.y + 3, hallwayEnd.getIdentifier(),
                CellIndexDirection.NorthWestCorner);
        applyBlueprintToCell(cells, buildCoords.x + 1, buildCoords.y + 2, hallwayEnd.getIdentifier(),
                CellIndexDirection.NorthWall);
        applyBlueprintToCell(cells, buildCoords.x + 2, buildCoords.y + 0, hallwayEnd.getIdentifier(),//
                CellIndexDirection.NorthEastCorner);
        applyBlueprintToCell(cells, buildCoords.x + 3, buildCoords.y + 0, hallwayEnd.getIdentifier(),//
                CellIndexDirection.EastWall);
        applyBlueprintToCell(cells, buildCoords.x + 4, buildCoords.y + 2, hallwayEnd.getIdentifier(),
                CellIndexDirection.SouthEastCorner);
        applyBlueprintToCell(cells, buildCoords.x + 4, buildCoords.y + 3, hallwayEnd.getIdentifier(),
                CellIndexDirection.SouthWall);

        applyBlueprintToCell(cells, buildCoords.x + 2, buildCoords.y + 5, entrance.getIdentifier(),// entrance
                CellIndexDirection.NorthWestCorner);
        applyBlueprintToCell(cells, buildCoords.x + 3, buildCoords.y + 5, entrance.getIdentifier(),
                CellIndexDirection.NorthEastCorner);
        applyBlueprintToCell(cells, buildCoords.x + 2, buildCoords.y + 6, entrance.getIdentifier(),
                CellIndexDirection.SouthWestCorner);
        applyBlueprintToCell(cells, buildCoords.x + 3, buildCoords.y + 6, entrance.getIdentifier(),
                CellIndexDirection.SouthEastCorner);

        applyBlueprintToCell(cells, buildCoords.x + 0, buildCoords.y + 5, west_tower.getIdentifier(), // west_tower
                CellIndexDirection.NorthWestCorner, cells[buildCoords.x + 0][buildCoords.y + 5].getHeight());
        applyBlueprintToCell(cells, buildCoords.x + 1, buildCoords.y + 5, west_tower.getIdentifier(),
                CellIndexDirection.NorthEastCorner, cells[buildCoords.x + 1][buildCoords.y + 5].getHeight());
        applyBlueprintToCell(cells, buildCoords.x + 0, buildCoords.y + 6, west_tower.getIdentifier(),
                CellIndexDirection.SouthWestCorner, cells[buildCoords.x + 0][buildCoords.y + 6].getHeight());
        applyBlueprintToCell(cells, buildCoords.x + 1, buildCoords.y + 6, west_tower.getIdentifier(),
                CellIndexDirection.SouthEastCorner, cells[buildCoords.x + 1][buildCoords.y + 6].getHeight());

        applyBlueprintToCell(cells, buildCoords.x + 4, buildCoords.y + 5, east_tower.getIdentifier(), // east_tower
                CellIndexDirection.NorthWestCorner, cells[buildCoords.x + 4][buildCoords.y + 5].getHeight());
        applyBlueprintToCell(cells, buildCoords.x + 5, buildCoords.y + 5, east_tower.getIdentifier(),
                CellIndexDirection.NorthEastCorner, cells[buildCoords.x + 5][buildCoords.y + 5].getHeight());
        applyBlueprintToCell(cells, buildCoords.x + 4, buildCoords.y + 6, east_tower.getIdentifier(),
                CellIndexDirection.SouthWestCorner, cells[buildCoords.x + 4][buildCoords.y + 6].getHeight());
        applyBlueprintToCell(cells, buildCoords.x + 5, buildCoords.y + 6, east_tower.getIdentifier(),
                CellIndexDirection.SouthEastCorner, cells[buildCoords.x + 5][buildCoords.y + 6].getHeight());
        isAssigned = true;
        return true;
    }

    private void applyBlueprintToCell(MazeCell[][] cellList, int xCell, int zCell, String subBuildingID,
            CellIndexDirection cellDirection) {
        applyBlueprintToCell(cellList, xCell, zCell, subBuildingID, cellDirection, 0);
    }

    private void applyBlueprintToCell(MazeCell[][] cellList, int xCell, int zCell, String subBuildingID,
            CellIndexDirection cellDirection, int heightIncrease) {
        cellList[xCell][zCell].setBuildingProperties(getIdentifier().concat("-").concat(subBuildingID), cellDirection);
        cellList[xCell][zCell].rawState = -1;
        cellList[xCell][zCell].setHeight(cellList[xCell][zCell].getHeight() + heightIncrease);
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight,
            CellIndexDirection cellIndexDirection, Random random, String buildingID) {
        String childBuildingID = buildingID.split("-")[1];
        if (childBuildingID != null) {
            if (childBuildingID.equals(dome.getIdentifier())) {
                return dome.getBlockFromBlueprint(piecePos, cellSize, cellHeight, random, cellIndexDirection);
            } else if (childBuildingID.equals(hallway.getIdentifier())) {
                return hallway.getBlockFromBlueprint(piecePos, cellSize, cellHeight, random, cellIndexDirection);
            } else if (childBuildingID.equals(hallwayEnd.getIdentifier())) {
                return hallwayEnd.getBlockFromBlueprint(piecePos, cellSize, cellHeight, random, cellIndexDirection);
            } else if (childBuildingID.equals(hallwayEnt.getIdentifier())) {
                return hallwayEnt.getBlockFromBlueprint(piecePos, cellSize, cellHeight, random, cellIndexDirection);
            } else if (childBuildingID.equals(entrance.getIdentifier())) {
                return entrance.getBlockFromBlueprint(piecePos, cellSize, cellHeight, random, cellIndexDirection);
            } else if (childBuildingID.equals(west_tower.getIdentifier())) {
                return west_tower.getBlockFromBlueprint(piecePos, cellSize, cellHeight, random, cellIndexDirection);
            } else if (childBuildingID.equals(east_tower.getIdentifier())) {
                return east_tower.getBlockFromBlueprint(piecePos, cellSize, cellHeight, random, cellIndexDirection);
            }
        }
        return null;
    }

    @Override
    public String getIdentifier() {
        return "Cathedral";
    }

    @Override
    public int getWeight() {
        return 20;
    }
}
