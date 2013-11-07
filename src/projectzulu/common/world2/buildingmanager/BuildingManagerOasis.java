package projectzulu.common.world2.buildingmanager;

import java.awt.Point;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import projectzulu.common.core.terrain.TerrainFeature.FeatureDirection;
import projectzulu.common.world2.MazeCell;
import projectzulu.common.world2.architect.ArchitectOasis;
import projectzulu.common.world2.randomizer.EdgeRandomizer;
import projectzulu.common.world2.randomizer.Randomizer;

public class BuildingManagerOasis extends BuildingManagerBase {

    public ArchitectOasis architect;
    public Randomizer randomizer;
    MazeCell[][] oasis;

    public BuildingManagerOasis(World world, FeatureDirection direction, ChunkCoordinates startingPos, int xCells,
            int zCells, int cellSize) {
        super(world);

        int cellHeight = 4;
        randomizer = new EdgeRandomizer(world, 2);
        architect = new ArchitectOasis(world);
        startingPos = calcTranslatedPosition(direction, startingPos, xCells * cellSize, zCells * cellSize, cellHeight);

        oasis = new MazeCell[xCells][zCells];
        for (int i = 0; i < oasis.length; i++) {
            for (int j = 0; j < oasis[0].length; j++) {
                oasis[i][j] = new MazeCell(cellSize, cellHeight, new ChunkCoordinates(startingPos.posX + cellSize * i,
                        startingPos.posY, startingPos.posZ + cellSize * j));
            }
        }
    }

    @Override
    protected void randomizeCells() {
        randomizer.randomize(oasis);
    }

    @Override
    protected void assignBlueprints(int pass, int maxPass) {
        if (pass == maxPass) {
            for (int cellX = 0; cellX < oasis.length; cellX++) {
                for (int cellZ = 0; cellZ < oasis[0].length; cellZ++) {
                    architect.assignBlueprint(oasis, new Point(cellX, cellZ), pass, maxPass);
                }
            }
        }
    }

    @Override
    protected void construct() {
        for (int cellX = 0; cellX < oasis.length; cellX++) {
            for (int cellZ = 0; cellZ < oasis[0].length; cellZ++) {
                MazeCell currentCell = oasis[cellX][cellZ];
                processCellPieces(currentCell, architect);
            }
        }
    }
}
