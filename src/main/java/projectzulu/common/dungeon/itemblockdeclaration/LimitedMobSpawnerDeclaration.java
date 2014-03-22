package projectzulu.common.dungeon.itemblockdeclaration;

import net.minecraft.block.Block;
import projectzulu.common.api.BlockList;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;
import projectzulu.common.dungeon.BlockLimitedMobSpawner;
import projectzulu.common.dungeon.TileEntityLimitedMobSpawner;
import projectzulu.common.dungeon.TileEntityLimitedMobSpawnerRenderer;

import com.google.common.base.Optional;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LimitedMobSpawnerDeclaration extends BlockDeclaration {

    public LimitedMobSpawnerDeclaration() {
        super("LimitedMobSpawner");
    }

    @Override
    protected boolean createBlock() {
        BlockList.limitedMobSpawner = Optional
                .of(new BlockLimitedMobSpawner().setHardness(0.5F).setStepSound(Block.soundTypeMetal)
                        .setBlockName(name.toLowerCase()).setBlockTextureName("mob_spawner"));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.limitedMobSpawner.get();
        GameRegistry.registerBlock(block, name.toLowerCase());
        GameRegistry.registerTileEntity(TileEntityLimitedMobSpawner.class, "TileEntityLimitedMobSpawner");
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected void clientRegisterBlock() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLimitedMobSpawner.class,
                new TileEntityLimitedMobSpawnerRenderer());
    }
}
