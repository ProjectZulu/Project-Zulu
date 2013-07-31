package projectzulu.common.world2.blueprint;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world.dataobjects.ChestWithMeta;
import projectzulu.common.world.dataobjects.MobSpawnerWithMeta;
import projectzulu.common.world.terrain.LabyrinthFeature;

public class BlueprintLabyrinthHiddenWall implements Blueprint {

    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        if ((piecePos.posY == 0 || piecePos.posY == cellHeight - 1)) {
            return new BlockWithMeta(Block.stoneBrick.blockID, 0);
        }

        if (piecePos.posX == cellSize / 2 && piecePos.posZ == cellSize / 2) {
            if (piecePos.posY == 1) {
                LabyrinthFeature feature = (LabyrinthFeature) ProjectZulu_Core.featureGenerator
                        .getRegisteredStructure(LabyrinthFeature.LABYRINTH);
                String entityName = feature.getEntityEntry(random);
                if (EntityList.stringToClassMapping.containsKey(entityName)) {
                    return new MobSpawnerWithMeta(entityName);
                } else {
                    if (!entityName.equalsIgnoreCase("EMPTY")) {
                        ProjectZuluLog.severe("Entity with name %s does not seem to exist.", entityName);
                    }
                    return new BlockWithMeta(Block.stoneBrick.blockID, 2);
                }
            } else if (piecePos.posY == 2) {
                LabyrinthFeature feature = (LabyrinthFeature) ProjectZulu_Core.featureGenerator
                        .getRegisteredStructure(LabyrinthFeature.LABYRINTH);
                return new ChestWithMeta(Block.chest.blockID, 0, new TileEntityChest(), feature.chestLootChance,
                        feature.chestMaxLoot);
            } else {
                return new BlockWithMeta(0);
            }
        }

        if (piecePos.posX == 0 || piecePos.posX == cellSize - 1 || piecePos.posZ == 0 || piecePos.posZ == cellSize - 1) {
            return new BlockWithMeta(Block.stoneBrick.blockID, 2);
        }
        return new BlockWithMeta(0);
    }

    @Override
    public int getWeight() {
        return 1;
    }

    @Override
    public String getIdentifier() {
        return "labyrinthhiddenwall";
    }
}
