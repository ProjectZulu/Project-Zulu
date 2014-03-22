package projectzulu.common.world2.buildingmanager;

import java.awt.Point;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import projectzulu.common.core.terrain.TerrainFeature.FeatureDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.MazeCell;
import projectzulu.common.world2.architect.ArchitectCemetary;
import projectzulu.common.world2.randomizer.Randomizer;
import projectzulu.common.world2.randomizer.SelectedFewRandomizer;

public class BuildingManagerCemetary extends BuildingManagerBase {

    public ArchitectCemetary architect;
    public Randomizer randomizer;

    MazeCell[][] cemetary;

    public BuildingManagerCemetary(World world, ChunkCoordinates startingPos, FeatureDirection direction) {
        super(world);
        architect = new ArchitectCemetary(world);
        randomizer = new SelectedFewRandomizer(world.rand, 3, 10, false);

        int xCells = 6;
        int zCells = 6;
        int cellSize = 3;
        int cellHeight = 5;
        startingPos = calcTranslatedPosition(direction, startingPos, xCells * cellSize, zCells * cellSize, cellHeight);

        cemetary = new MazeCell[xCells][zCells];
        for (int i = 0; i < cemetary.length; i++) {
            for (int j = 0; j < cemetary[0].length; j++) {
                cemetary[i][j] = new MazeCell(cellSize, cellHeight, new ChunkCoordinates(startingPos.posX + cellSize
                        * i, startingPos.posY, startingPos.posZ + cellSize * j));
            }
        }
    }

    @Override
    protected void randomizeCells() {
        randomizer.randomize(cemetary);
    }

    @Override
    protected void assignBlueprints(int pass, int maxPass) {
        if (pass == maxPass) {
            for (int cellX = 0; cellX < cemetary.length; cellX++) {
                for (int cellZ = 0; cellZ < cemetary[0].length; cellZ++) {
                    architect.assignBlueprint(cemetary, new Point(cellX, cellZ), pass, maxPass);
                }
            }
        }
    }

    @Override
    protected void construct() {
        for (int cellX = 0; cellX < cemetary.length; cellX++) {
            for (int cellZ = 0; cellZ < cemetary[0].length; cellZ++) {
                MazeCell currentCell = cemetary[cellX][cellZ];
                processCellPieces(currentCell, architect);
            }
        }
    }

    @Override
    protected void handleBlockPlacement(BlockWithMeta blockWithMeta, ChunkCoordinates position, Random random) {
        Block blockAt = world.getBlock(position.posX, position.posY, position.posZ);
        if (blockWithMeta.block.equals(Blocks.air) && blockAt.equals(Block.getBlockFromName("snow"))) {
            return;
        }
        if (blockAt.getMaterial().equals(Material.wood) || blockAt.getMaterial().equals(Material.leaves)) {
            return;
        }
        super.handleBlockPlacement(blockWithMeta, position, random);
    }
}
