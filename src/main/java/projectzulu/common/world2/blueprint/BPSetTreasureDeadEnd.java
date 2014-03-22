package projectzulu.common.world2.blueprint;

import java.awt.Point;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world.dataobjects.ChestWithMeta;
import projectzulu.common.world.dataobjects.MimicWithMeta;
import projectzulu.common.world.terrain.PyramidFeature;
import projectzulu.common.world2.MazeCell;
import projectzulu.common.world2.architect.ArchitectBase;

public class BPSetTreasureDeadEnd implements BlueprintSet, Blueprint {

    BlockWithMeta floorblock;

    public BPSetTreasureDeadEnd() {
        floorblock = new BlockWithMeta(Blocks.sandstone);
    }

    @Override
    public String getIdentifier() {
        return "deadend";
    }

    @Override
    public int getWeight() {
        return 50;
    }

    @Override
    public boolean assignCellsWithBlueprints(MazeCell[][] cells, Point buildCoords, Random random) {
        cells[buildCoords.x][buildCoords.y].setBuildingProperties(getIdentifier(), CellIndexDirection.Middle);
        cells[buildCoords.x][buildCoords.y].rawState = 2;
        return true;
    }

    @Override
    public boolean isApplicable(MazeCell[][] cells, Point buildCoords, Random random) {
        return cells[buildCoords.x][buildCoords.y].rawState == 0 && ArchitectBase.isDeadEnd(cells, buildCoords, 1, -1);
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight,
            CellIndexDirection cellIndexDirection, Random random, String buildingID) {
        return getBlockFromBlueprint(piecePos, cellSize, cellHeight, random, cellIndexDirection);
    }

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        if (piecePos.posY == 0) {
            return floorblock;
        } else if (piecePos.posY == 1) {
            if (random.nextInt(5) == 0) {
                PyramidFeature terrainFeature = (PyramidFeature) ProjectZulu_Core.featureGenerator
                        .getRegisteredStructure(PyramidFeature.PYRAMID);
                return new ChestWithMeta(Blocks.chest, 0, new TileEntityChest(), terrainFeature.chestLootChance,
                        terrainFeature.chestMaxLoot);

            } else if (random.nextInt(4) == 0) {
                return new MimicWithMeta();
            }
        }
        return new BlockWithMeta(Blocks.air);
    }
}