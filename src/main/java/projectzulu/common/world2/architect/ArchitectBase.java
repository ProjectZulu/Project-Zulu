package projectzulu.common.world2.architect;

import java.awt.Point;
import java.util.Random;

import net.minecraft.world.World;
import projectzulu.common.world2.Direction;
import projectzulu.common.world2.MazeCell;

public abstract class ArchitectBase implements Architect {
    protected BlueprintSetStockpile stockpile;
    protected Random random;

    public ArchitectBase(World world) {
        this.random = world.rand;
        stockpile = new BlueprintSetStockpile(world.rand);
    }

    /**
     * Checks if the selected cell has walls in 3 cardinal directions
     */
    public static Boolean isDeadEnd(MazeCell[][] allCells, Point buildCoords, int... rawState) {
        int wallsNearby = 0;

        for (Direction direction : Direction.getCardinals()) {
            int neighbourX = buildCoords.x + direction.x;
            int neighbourZ = buildCoords.y + direction.z;
            if (ArchitectBase.isCellOutOfBounds(allCells, neighbourX, neighbourZ)) {
                continue;
            }
            for (int state : rawState) {
                if (allCells[neighbourX][neighbourZ].rawState == state) {
                    wallsNearby++;
                    break;
                }
            }
        }
        return wallsNearby == 3 ? true : false;
    }

    /**
     * Checks if the selected cell is touching a valid number of valid cells
     */
    public static Boolean isTouchingAmount(MazeCell[][] allCells, Point buildCoords, int minTouching, int maxTouching,
            boolean countEdges, int... rawState) {
        int wallsNearby = 0;

        for (Direction direction : Direction.getCardinals()) {
            int neighbourX = buildCoords.x + direction.x;
            int neighbourZ = buildCoords.y + direction.z;
            if (ArchitectBase.isCellOutOfBounds(allCells, neighbourX, neighbourZ)) {
                if (countEdges) {
                    wallsNearby++;
                }
                continue;
            }
            for (int state : rawState) {
                if (allCells[neighbourX][neighbourZ].rawState == state) {
                    wallsNearby++;
                    break;
                }
            }
        }
        return wallsNearby >= minTouching && wallsNearby <= maxTouching ? true : false;
    }

    /**
     * Evaluates if the Cell is Out of Bounds
     * 
     * @param allCells
     * @param currentX
     * @param currentZ
     * @return
     */
    public static boolean isCellOutOfBounds(MazeCell[][] allCells, int currentX, int currentZ) {
        return currentX < 0 || currentX >= allCells.length || currentZ < 0 || currentZ >= allCells[0].length;
    }
}
