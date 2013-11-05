package projectzulu.common.world2.buildingmanager;

import java.awt.Point;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.terrain.TerrainFeature.FeatureDirection;
import projectzulu.common.world2.MazeCell;
import projectzulu.common.world2.architect.ArchitectLabyrinth;
import projectzulu.common.world2.randomizer.Randomizer;
import projectzulu.common.world2.randomizer.WalledMazeRandomizer;

public class BuildingManagerLabyrinth extends BuildingManagerBase {

    public ArchitectLabyrinth architect;
    public Randomizer randomizer;

    MazeCell[][] labyrinth;
    MazeCell[][] stairs;

    public BuildingManagerLabyrinth(World world, ChunkCoordinates startingPos, FeatureDirection direction) {
        super(world);
        architect = new ArchitectLabyrinth(world);
        randomizer = new WalledMazeRandomizer(world);

        int xCells = 15;
        int zCells = 15;
        int cellSize = 3;
        int cellHeight = 5;

        int stairZCells = MathHelper.ceiling_float_int(15f / cellHeight);
        int stairCellSize = cellSize + 2;
        int stairCellHeight = stairCellSize * 2;

        startingPos = calcTranslatedPosition(direction, startingPos, xCells * cellSize, zCells * cellSize
                + stairZCells * stairCellSize, cellHeight);

        stairs = new MazeCell[1][stairZCells];
        for (int i = 0; i < stairs.length; i++) {
            for (int j = 0; j < stairs[0].length; j++) {
                stairs[i][j] = new MazeCell(stairCellSize, stairCellHeight, new ChunkCoordinates(startingPos.posX - 1
                        + stairCellSize * i + cellSize * (xCells / 2), startingPos.posY + 1 + stairCellSize
                        * (stairs[0].length - 1 - j), startingPos.posZ + stairCellSize * j));
            }
        }

        labyrinth = new MazeCell[xCells][zCells];
        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth[0].length; j++) {
                labyrinth[i][j] = new MazeCell(cellSize, cellHeight, new ChunkCoordinates(startingPos.posX + cellSize
                        * i, startingPos.posY, startingPos.posZ + stairCellSize * stairs[0].length + cellSize * j));
            }
        }
    }

    @Override
    protected void randomizeCells() {
        randomizer.randomize(labyrinth);
    }

    @Override
    protected void assignBlueprints(int pass, int maxPasses) {
        if (pass == maxPasses) {
            for (int cellX = 0; cellX < labyrinth.length; cellX++) {
                for (int cellZ = 0; cellZ < labyrinth[0].length; cellZ++) {
                    architect.assignBlueprint(labyrinth, new Point(cellX, cellZ), pass, maxPasses);
                }
            }

            for (int cellX = 0; cellX < stairs.length; cellX++) {
                for (int cellZ = 0; cellZ < stairs[0].length; cellZ++) {
                    architect.assignStairs(stairs, new Point(cellX, cellZ), pass, maxPasses);
                }
            }
        }
    }

    @Override
    protected void construct() {
        ProjectZuluLog.info("construct");
        for (int cellX = 0; cellX < labyrinth.length; cellX++) {
            for (int cellZ = 0; cellZ < labyrinth[0].length; cellZ++) {
                MazeCell currentCell = labyrinth[cellX][cellZ];
                processCellPieces(currentCell, architect);
            }
        }

        for (int cellX = 0; cellX < stairs.length; cellX++) {
            for (int cellZ = 0; cellZ < stairs[0].length; cellZ++) {
                MazeCell currentCell = stairs[cellX][cellZ];
                processCellPieces(currentCell, architect);
            }
        }
    }
}
