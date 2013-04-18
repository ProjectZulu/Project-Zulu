package projectzulu.common.dungeon.itemblockdeclaration;

import net.minecraft.block.Block;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.BlockList;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;
import projectzulu.common.dungeon.BlockLimitedMobSpawner;
import projectzulu.common.dungeon.TileEntityLimitedMobSpawner;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LimitedMobSpawnerDeclaration extends BlockDeclaration {

    public LimitedMobSpawnerDeclaration() {
        super("LimitedMobSpawner");
    }

    @Override
    protected boolean createBlock(int iD) {
        BlockList.limitedMobSpawner = Optional.of(new BlockLimitedMobSpawner(iD).setHardness(0.5F)
                .setStepSound(Block.soundMetalFootstep).setUnlocalizedName("mobSpawner"));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.limitedMobSpawner.get();
        GameRegistry.registerBlock(block, this.toString().toLowerCase());
        LanguageRegistry.addName(block, "Limited Mob Spawner");

        GameRegistry.registerTileEntity(TileEntityLimitedMobSpawner.class, "TileEntityLimitedMobSpawner");
        ProjectZulu_Core.proxy.registerTileEntitySpecialRender(TileEntityLimitedMobSpawner.class,
                "projectzulu.common.dungeon.TileEntityLimitedMobSpawnerRenderer");
    }
}
