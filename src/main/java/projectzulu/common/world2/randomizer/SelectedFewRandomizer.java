package projectzulu.common.world2.randomizer;

import java.awt.Point;
import java.util.Random;

import projectzulu.common.world2.MazeCell;
import projectzulu.common.world2.architect.ArchitectBase;

public class SelectedFewRandomizer extends Randomizer {

    Random random;
    int maxSelected;
    int selectionChance;
    boolean allowTouching;

    public SelectedFewRandomizer(Random random, int maxSelected, int selectionChance, boolean allowTouching) {
        this.random = random;
        this.maxSelected = maxSelected;
        this.selectionChance = selectionChance;
        this.allowTouching = allowTouching;
    }

    @Override
    public void randomize(MazeCell[][] cells) {
        int[] indexes = new int[cells.length * cells[0].length];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = i;
        }
        shuffleArray(indexes);

        int count = 0;
        for (int index : indexes) {
            int i = (int) (index / cells[0].length);
            int k = index % cells[0].length;
            if (random.nextInt(100) <= selectionChance
                    && (allowTouching || ArchitectBase.isTouchingAmount(cells, new Point(i, k), 0, 0, true, 1))) {
                cells[i][k].rawState = 1;
                count++;
                if (maxSelected > -1 && count >= maxSelected) {
                    return;
                }
            }
        }
    }

    private void shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i >= 0; i--) {
            int index = random.nextInt(i + 1);
            /* Simple swap */
            int tempValue = array[index];
            array[index] = array[i];
            array[i] = tempValue;
        }
    }
}
