package projectzulu.common.world2.randomizer;

import java.util.Random;

import projectzulu.common.world2.MazeCell;

import net.minecraft.world.World;

public class EdgeRandomizer extends Randomizer {
    Random random;
    int edgeDepth;

    public EdgeRandomizer(World world, int edgeDepth) {
        this.random = world.rand;
        this.edgeDepth = edgeDepth;
    }

    @Override
    public void randomize(MazeCell[][] cells) {
        /* Mark Outer Cells As Walls (X axis) */
        for (int i = 0; i < cells.length; i++) {
            for (int k = 0; k < edgeDepth; k++) {
                cells[i][k].rawState = 1;
            }
            for (int k = cells[0].length - 1; k > cells[0].length - 1 - edgeDepth; k--) {
                cells[i][k].rawState = 1;
            }
        }

        /* Mark Outer Cells As Walls (Z axis) */
        for (int k = 0; k < cells[0].length; k++) {
            for (int i = 0; i < edgeDepth; i++) {
                cells[i][k].rawState = 1;
            }

            for (int i = cells.length - 1; i > cells.length - 1 - edgeDepth; i--) {
                cells[i][k].rawState = 1;
            }
        }
    }
}
