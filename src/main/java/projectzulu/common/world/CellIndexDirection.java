package projectzulu.common.world;

import java.util.Random;

public enum CellIndexDirection {
    Middle, Inner, NorthMiddle, SouthMiddle, WestMiddle, EastMiddle, NorthWestCorner, NorthEastCorner, SouthWestCorner, SouthEastCorner, NorthWall, SouthWall, WestWall, EastWall;

    public boolean isCorner() {
        return this == NorthWestCorner || this == NorthEastCorner || this == SouthWestCorner || this == SouthEastCorner;
    }

    public static CellIndexDirection randomCardinalDirection(Random random) {
        switch (random.nextInt(4)) {
        case 0:
            return NorthMiddle;
        case 1:
            return SouthMiddle;
        case 2:
            return WestMiddle;
        case 3:
            return EastMiddle;
        default:
            return NorthMiddle;
        }
    }

    public CellIndexDirection calcDirection(int cellIndex, int cellSize) {

        /* Check if Middle */
        if (cellSize % 2 == 1) {
            if (cellIndex == cellSize * (cellSize - 1) / 2 + (cellSize - 1) / 2) {
                return Middle;
            } else if (cellIndex == (cellSize - 1) / 2) {
                return WestMiddle;
            } else if (cellIndex == cellSize * (cellSize - 1) / 2) {
                return NorthMiddle;
            } else if (cellIndex == cellSize * (cellSize - 1) + (cellSize - 1) / 2) {
                return EastMiddle;
            } else if (cellIndex == cellSize * (cellSize - 1) / 2 + (cellSize - 1)) {
                return SouthMiddle;
            }
        }

        /* Check if Corner */
        if (cellIndex == 0) {
            return NorthWestCorner;
        } else if (cellIndex == cellSize - 1) {
            return SouthWestCorner;
        } else if (cellIndex == cellSize * cellSize - 1) {
            return SouthEastCorner;
        } else if (cellIndex == cellSize * cellSize - cellSize) {
            return NorthEastCorner;
        }

        /* Check if Outer Edge */
        if ((cellIndex % cellSize == (0))) {
            return NorthWall;
        } else if ((cellIndex % cellSize == (cellSize - 1))) {
            return SouthWall;
        } else if (cellIndex >= cellSize * (cellSize - 1) && cellIndex < cellSize * cellSize) {
            return EastWall;
        } else if (cellIndex >= 0 && cellIndex < cellSize) {
            return WestWall;
        }

        /* If Nothing Else Mark as Inner */
        return Inner;
    }
}