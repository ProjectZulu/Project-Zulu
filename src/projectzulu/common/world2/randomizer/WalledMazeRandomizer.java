package projectzulu.common.world2.randomizer;

import java.util.EnumSet;
import java.util.Random;

import net.minecraft.world.World;
import projectzulu.common.world2.Direction;
import projectzulu.common.world2.MazeCell;

public class WalledMazeRandomizer extends Randomizer {

    Random random;

    public WalledMazeRandomizer(World world) {
        this.random = world.rand;
    }

    @Override
    public void randomize(MazeCell[][] allCells) {
        /* Mark Outer Cells As Walls (X axis) */
        for (int i = 0; i < allCells.length; i++) {
            int k = 0;
            allCells[i][k].rawState = 1;
            k = allCells[0].length - 1;
            allCells[i][k].rawState = 1;
        }

        /* Mark Outer Cells As Walls (Z axis) */
        for (int k = 0; k < allCells[0].length; k++) {
            int i = 0;
            allCells[i][k].rawState = 1;
            i = allCells.length - 1;
            allCells[i][k].rawState = 1;
        }

        /* Perform X wall placement attempts, then Assume that the maze is sufficiently random/complete */
        int attemptsSinceLastPlaced = 0;
        while (attemptsSinceLastPlaced < 800) {

            /* Decide Random Length, Random Direction to try Placing Walls */
            int randLength = random.nextInt(6) + 1;
            Direction direction = Direction.getCardinal(random);

            /* Select Wall to Try to Place at */
            int selectedX = random.nextInt(allCells.length);
            int selectedZ = random.nextInt(allCells[0].length);
            while (allCells[selectedX][selectedZ].rawState != 1) {
                selectedX = random.nextInt(allCells.length);
                selectedZ = random.nextInt(allCells[0].length);
            }

            if (isCellWallValid(allCells, selectedX, selectedZ, direction, randLength)) {
                for (int i = 1; i <= randLength; i++) {
                    int tempX = selectedX + direction.x * i;
                    int tempZ = selectedZ + direction.z * i;
                    allCells[tempX][tempZ].rawState = 1;
                }
                attemptsSinceLastPlaced = 0;
            } else {
                attemptsSinceLastPlaced++;
            }
        }
    }

    /**
     * Evaluates if a wall of cells (of length randLength in direction) without touching another wall cell
     * 
     * @param allCells
     * @param selectedX,Z Starting Wall Cell that Wall is Placed off of
     * @param direction Direction to generate wall of cells
     * @param randLength Length of wall of cells to place
     * @return
     */
    protected boolean isCellWallValid(MazeCell[][] allCells, int selectedX, int selectedZ, Direction direction,
            int randLength) {

        for (int i = 1; i <= randLength; i++) {
            int cellToPlaceX = selectedX + direction.x * i;
            int cellToPlaceZ = selectedZ + direction.z * i;

            if (isCellOutOfBounds(allCells, cellToPlaceX, cellToPlaceZ)
                    || allCells[cellToPlaceX][cellToPlaceZ].rawState == 1) {
                return false;
            }

            /*
             * Never check previous cell, it should be a wall. Ignore maze edge by only check the 'Forward Hemisphere'
             * (+- 90o from Direction) on edge cells.
             */
            EnumSet<Direction> neighbourDirections = Direction.getOrdinals();
            neighbourDirections.remove(direction.invert());
            neighbourDirections.remove(direction.invert().rotateOrdinal(true));
            neighbourDirections.remove(direction.invert().rotateOrdinal(false));

            for (Direction cellDirection : neighbourDirections) {
                int neighbourCellX = cellToPlaceX + cellDirection.x;
                int neighbourCellZ = cellToPlaceZ + cellDirection.z;

                if (isCellOutOfBounds(allCells, neighbourCellX, neighbourCellZ)) {
                    continue;
                }

                if (allCells[neighbourCellX][neighbourCellZ].rawState == 1) {
                    return false;
                }

                if (isCellSurrounded(allCells, neighbourCellX, neighbourCellZ)) {
                    return false;
                }

            }
        }
        return true;
    }

    /**
     * Evaluates if the Provided Cells X,Z is surrounded by other walls
     * 
     * @param allCells
     * @param currentX
     * @param currentZ
     * @return
     */
    protected boolean isCellSurrounded(MazeCell[][] allCells, int currentX, int currentZ) {
        int numberOfSidesOccupied = 0;

        for (Direction direction : Direction.getCardinals()) {
            int neighbourX = currentX + direction.x;
            int neighbourZ = currentZ + direction.z;

            if (isCellOutOfBounds(allCells, neighbourX, neighbourZ)) {
                continue;
            }

            if (allCells[neighbourX][neighbourZ].rawState == 1) {
                numberOfSidesOccupied++;
            }
        }

        return numberOfSidesOccupied < 4 ? false : true;
    }

    /**
     * Evaluates if the Cell is Out of Bounds
     * 
     * @param allCells
     * @param currentX
     * @param currentZ
     * @return
     */
    private boolean isCellOutOfBounds(MazeCell[][] allCells, int currentX, int currentZ) {
        return currentX < 0 || currentX >= allCells.length || currentZ < 0 || currentZ >= allCells[0].length;
    }
}