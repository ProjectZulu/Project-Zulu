package projectzulu.common.world2.buildingmanager;

import java.awt.Point;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import projectzulu.common.core.terrain.TerrainFeature.FeatureDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.MazeCell;
import projectzulu.common.world2.architect.ArchitectCathedral;
import projectzulu.common.world2.randomizer.Randomizer;
import projectzulu.common.world2.randomizer.WalledMazeRandomizer;

public class BuildingManagerCathedral extends BuildingManagerBase {

    public ArchitectCathedral architect;
    public Randomizer randomizer;

    MazeCell[][] cathedral;

    public BuildingManagerCathedral(World world, ChunkCoordinates startingPos, FeatureDirection direction) {
        super(world);
        architect = new ArchitectCathedral(world);
        randomizer = new WalledMazeRandomizer(world);

        int xCells = 7;
        int zCells = 7;
        int cellSize = 6;
        int cellHeight = 15;

        startingPos = calcTranslatedPosition(direction, startingPos, xCells * cellSize, zCells * cellSize, cellHeight);

        cathedral = new MazeCell[xCells][zCells];
        for (int i = 0; i < cathedral.length; i++) {
            for (int j = 0; j < cathedral[0].length; j++) {
                cathedral[i][j] = new MazeCell(cellSize, cellHeight, new ChunkCoordinates(startingPos.posX + cellSize
                        * i, startingPos.posY, startingPos.posZ + cellSize * j));
            }
        }
    }

    @Override
    protected void randomizeCells() {
        randomizer.randomize(cathedral);
    }

    @Override
    protected void assignBlueprints(int pass, int maxPass) {
        if (pass == maxPass) {
            for (int cellX = 0; cellX < cathedral.length; cellX++) {
                for (int cellZ = 0; cellZ < cathedral[0].length; cellZ++) {
                    architect.assignBlueprint(cathedral, new Point(cellX, cellZ), pass, maxPass);
                }
            }
        }
    }

    @Override
    protected void construct() {
        for (int cellX = 0; cellX < cathedral.length; cellX++) {
            for (int cellZ = 0; cellZ < cathedral[0].length; cellZ++) {
                MazeCell currentCell = cathedral[cellX][cellZ];
                processCellPieces(currentCell, architect);
            }
        }
    }

    protected void handleBlockPlacement(BlockWithMeta blockWithMeta, ChunkCoordinates position, Random random) {
        if (blockWithMeta == null || !world.blockExists(position.posX, position.posY, position.posZ)) {
            blockWithMeta = new BlockWithMeta(Blocks.air);
        }

        /*
         * Check if There is a Tile At This Block, if so, remove it This Doesn't Seem to Work, So Block Is only placed
         * if there is not TileEntity so as to prevent crash
         */
        TileEntity tileEntity = world.getTileEntity(position.posX, position.posY, position.posZ);
        if (tileEntity != null) {
            tileEntity.invalidate();
            world.removeTileEntity(position.posX, position.posY, position.posZ);
            world.setBlock(position.posX, position.posY, position.posZ, Block.getBlockFromName("air"));
        } else {
            /* Check Block to See How Block wants to be Placed */
            blockWithMeta.placeBlock(world, position, random);
        }

    }
}
