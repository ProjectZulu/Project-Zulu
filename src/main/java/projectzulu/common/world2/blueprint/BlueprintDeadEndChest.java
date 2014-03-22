package projectzulu.common.world2.blueprint;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChunkCoordinates;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.world.CellIndexDirection;
import projectzulu.common.world.dataobjects.BlockWithMeta;
import projectzulu.common.world.dataobjects.ChestWithMeta;
import projectzulu.common.world.dataobjects.MimicWithMeta;
import projectzulu.common.world.terrain.LabyrinthFeature;

public class BlueprintDeadEndChest implements Blueprint {
    @Override
    public BlockWithMeta getBlockFromBlueprint(ChunkCoordinates piecePos, int cellSize, int cellHeight, Random random,
            CellIndexDirection cellIndexDirection) {
        if ((piecePos.posY == 0 || piecePos.posY == cellHeight - 1)) {
            return new BlockWithMeta(Blocks.stonebrick, 0);
        }

        if (piecePos.posY == 1) {
            if (random.nextInt(8) == 0) {
                LabyrinthFeature feature = (LabyrinthFeature) ProjectZulu_Core.featureGenerator
                        .getRegisteredStructure(LabyrinthFeature.LABYRINTH);
                return new ChestWithMeta(Blocks.chest, 0, new TileEntityChest(), feature.chestLootChance,
                        feature.chestMaxLoot);
            } else if (random.nextInt(8) == 1) {
                return new MimicWithMeta();
            }
        }

        return new BlockWithMeta(Blocks.air);
    }

    @Override
    public int getWeight() {
        return 1;
    }

    @Override
    public String getIdentifier() {
        return "deadend";
    }
}
