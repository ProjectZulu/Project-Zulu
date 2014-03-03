package projectzulu.common.world2.buildingmanager;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import projectzulu.common.core.terrain.TerrainFeature.FeatureDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world2.MazeCell;
import projectzulu.common.world2.architect.Architect;

public abstract class BuildingManagerBase implements BuildingManager {
    public World world;
    protected int blueprintPasses = 1;

    public BuildingManagerBase(World world) {
        this.world = world;

    }

    @Override
    public final void generate() {
        randomizeCells();

        for (int i = 1; i <= blueprintPasses; i++) {
            assignBlueprints(i, blueprintPasses);
        }

        construct();
    }

    protected ChunkCoordinates calcTranslatedPosition(FeatureDirection direction, ChunkCoordinates startingPos,
            int xWidth, int zWidth, int height) {
        if (direction == null) {
            direction = FeatureDirection.CENTERED;
        }

        switch (direction) {
        case NORTH:
            return new ChunkCoordinates(startingPos.posX - xWidth / 2, startingPos.posY, startingPos.posZ - zWidth);
        case SOUTH:
            return new ChunkCoordinates(startingPos.posX - xWidth / 2, startingPos.posY, startingPos.posZ + 1);
        case WEST:
            return new ChunkCoordinates(startingPos.posX - xWidth, startingPos.posY, startingPos.posZ - zWidth / 2);
        case EAST:
            return new ChunkCoordinates(startingPos.posX + 1, startingPos.posY, startingPos.posZ - zWidth / 2);
        case CENTERED:
        default:
            return new ChunkCoordinates(startingPos.posX - xWidth / 2, startingPos.posY, startingPos.posZ - zWidth / 2);
        }
    }

    /**
     * Method to Perform processing on the Cell array. Typically this invloves invoking the Randomizer
     */
    protected abstract void randomizeCells();

    /**
     * Method to Assign Blueprints to cells. Multiple can be peformed. The lastCall boolean indicates when the final
     * pass is being performed, during which all cells should receive blueprints
     */
    protected abstract void assignBlueprints(int pass, int maxPass);

    protected abstract void construct();

    protected void processCellPieces(MazeCell currentCell, Architect architect) {
        ChunkCoordinates pieceCoords = new ChunkCoordinates(0, 0, 0);
        for (int pieceX = 0; pieceX < currentCell.size; pieceX++) {
            for (int pieceZ = 0; pieceZ < currentCell.size; pieceZ++) {
                for (int pieceY = 0; pieceY < currentCell.getHeight(); pieceY++) {

                    /* Set Local Piece coordinates */
                    pieceCoords.set(pieceX, pieceY, pieceZ);
                    BlockWithMeta block = architect.getBlockFromBlueprint(currentCell,
                            new ChunkCoordinates(pieceCoords));

                    /* Set Absolute Piece coordinates */
                    pieceCoords.set(currentCell.initialPos.posX + pieceX, currentCell.initialPos.posY + pieceY,
                            currentCell.initialPos.posZ + pieceZ);
                    handleBlockPlacement(block, pieceCoords, world.rand);
                }
            }
        }
    }

    protected void handleBlockPlacement(BlockWithMeta blockWithMeta, ChunkCoordinates position, Random random) {
        if (blockWithMeta == null || !world.blockExists(position.posX, position.posY, position.posZ)) {
            return;
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
