package projectzulu.common.world2.buildingmanager;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.terrain.TerrainFeature.FeatureDirection;
import projectzulu.common.world2.MazeCell;
import projectzulu.common.world2.architect.ArchitectPyramid;
import projectzulu.common.world2.randomizer.Randomizer;
import projectzulu.common.world2.randomizer.WalledMazeRandomizer;

public class BuildingManagerPyramid extends BuildingManagerBase {

    int outside_height = 25;
    int floor_height = 3;

    public ArchitectPyramid architect;
    public Randomizer randomizer;

    List<MazeCell[][]> pyramidCells = new ArrayList<MazeCell[][]>();

    public BuildingManagerPyramid(World world, ChunkCoordinates startingPos, FeatureDirection direction) {
        super(world);
        architect = new ArchitectPyramid(world);
        randomizer = new WalledMazeRandomizer(world);

        int xCells = 20;
        int zCells = 20;
        int cellSize = 2;
        int cellHeight = 2 * cellSize;
        int floors = (xCells > zCells ? xCells : zCells) / cellHeight + 1;
        startingPos = calcTranslatedPosition(direction, startingPos, xCells * cellSize, zCells * cellSize, cellHeight);

        Point numCells = new Point(xCells, zCells);

        for (int i = 0; i < floors; i++) {
            if (numCells.x - i * 4 >= 2) {
                pyramidCells.add(new MazeCell[numCells.x - i * 4][numCells.y - i * 4]);
            }
        }

        for (int i = 0; i < pyramidCells.size(); i++) {
            initializeMazeCell(pyramidCells.get(i), new ChunkCoordinates(startingPos.posX + 2 * cellSize * i,
                    startingPos.posY + cellHeight * i, startingPos.posZ + 2 * cellSize * i), cellSize, cellHeight);
        }
    }

    private void initializeMazeCell(MazeCell[][] cells, ChunkCoordinates startingPos, int cellSize, int cellHeight) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new MazeCell(cellSize, cellHeight, new ChunkCoordinates(startingPos.posX + cellSize * i,
                        startingPos.posY, startingPos.posZ + cellSize * j));
            }
        }
    }

    @Override
    protected void randomizeCells() {
        for (int i = 0; i < pyramidCells.size(); i++) {
            markEdgesAsWalls(pyramidCells.get(i), 2);
            randomizer.randomize(pyramidCells.get(i));
        }
    }

    private void markEdgesAsWalls(MazeCell[][] cells, int depth) {
        /* Along X */
        for (int i = 0; i < cells.length; i++) {
            for (int k = 0; k < depth; k++) {
                cells[i][k].rawState = 1;
            }

            for (int k = cells[0].length - depth; k < cells[0].length; k++) {
                cells[i][k].rawState = 1;
            }
        }

        /* Along Z */
        for (int k = 0; k < cells[0].length; k++) {
            for (int i = 0; i < depth; i++) {
                cells[i][k].rawState = 1;
            }

            for (int i = cells.length - depth; i < cells.length; i++) {
                cells[i][k].rawState = 1;
            }
        }
    }

    @Override
    protected void assignBlueprints(int pass, int maxPasses) {
        if (pass == maxPasses) {
            ProjectZuluLog.info("assignBlueprints");
            for (int i = 0; i < pyramidCells.size(); i++) {
                assignBlueprints(pyramidCells.get(i), pass, maxPasses);
                if (i > 0) {
                    architect.assignStairs(pyramidCells.get(i - 1), pyramidCells.get(i));
                }
            }
        }
    }

    private void assignBlueprints(MazeCell[][] cells, int pass, int maxPasses) {
        for (int cellX = 0; cellX < cells.length; cellX++) {
            for (int cellZ = 0; cellZ < cells[0].length; cellZ++) {
                architect.assignBlueprint(cells, new Point(cellX, cellZ), pass, maxPasses);
            }
        }
    }

    @Override
    protected void construct() {
        ProjectZuluLog.info("construct");
        for (int i = 0; i < pyramidCells.size(); i++) {
            construct(pyramidCells.get(i));
        }
    }

    private void construct(MazeCell[][] cells) {
        for (int cellX = 0; cellX < cells.length; cellX++) {
            for (int cellZ = 0; cellZ < cells[0].length; cellZ++) {
                MazeCell currentCell = cells[cellX][cellZ];
                processCellPieces(currentCell, architect);
            }
        }
    }
}