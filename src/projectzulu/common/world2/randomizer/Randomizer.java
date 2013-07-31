package projectzulu.common.world2.randomizer;

import projectzulu.common.world2.MazeCell;

/**
 * The Randomizer is responsible for splitting the various cells between 0 and 1 (typically wall and hallway)
 */
public abstract class Randomizer {

    public abstract void randomize(MazeCell[][] cells);
}
