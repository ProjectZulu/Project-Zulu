package projectzulu.common.world2;

import java.util.EnumSet;
import java.util.Random;

/**
 * Two Dimensional Direction
 */
public enum Direction {
    /* U = Up, D = Down, L = Left, R = Right */
    U(0, 0, 1), D(1, 0, -1), L(2, 1, 0), R(3, -1, 0), UL(4, 1, 1), UR(5, -1, 1), DL(6, 1, -1), DR(7, -1, -1);

    public final int x;
    public final int z;
    public final int id;

    Direction(int id, int x, int z) {
        this.id = id;
        this.x = x;
        this.z = z;
    }

    public static final Direction[] invert = { D, U, R, L, DR, DL, UR, UL };
    public static final Direction[] cwRotation = { UR, DL, UL, DR, U, R, L, D };
    public static final Direction[] ccwRotation = { UL, DR, DL, UR, L, U, D, R };

    public Direction invert() {
        return invert[id];
    }

    /* Rotate Direction by an Ordinal either Clockwise or CounterClockwise */
    public Direction rotateOrdinal(boolean clockwise) {
        return clockwise ? cwRotation[id] : ccwRotation[id];
    }

    public static Direction getCardinal(Random random) {
        return getCardinal(random.nextInt(4));
    }

    public static Direction getCardinal(int id) {
        id = id & 3;
        for (Direction direction : Direction.values()) {
            if (direction.id == id) {
                return direction;
            }
        }
        throw new IllegalArgumentException("ID provided " + Integer.toString(id) + " is not a Direction");
    }

    public static EnumSet<Direction> getCardinals() {
        return EnumSet.of(U, D, L, R);
    }

    public static Direction getOrdinal(Random random) {
        return getOrdinal(random.nextInt(8));
    }

    public static Direction getOrdinal(int id) {
        id = id & 7;
        for (Direction direction : Direction.values()) {
            if (direction.id == id) {
                return direction;
            }
        }
        throw new IllegalArgumentException("ID provided " + Integer.toString(id) + " is not a Direction");
    }

    public static EnumSet<Direction> getOrdinals() {
        return EnumSet.of(U, D, L, R, UL, UR, DL, DR);
    }
}
