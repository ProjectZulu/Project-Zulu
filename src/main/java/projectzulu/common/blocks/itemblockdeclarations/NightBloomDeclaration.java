package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.BlockNightBloom;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class NightBloomDeclaration extends BlockDeclaration {

    public NightBloomDeclaration() {
        super("NightBloom");
    }

    @Override
    protected boolean createBlock() {
        BlockList.nightBloom = Optional.of(new BlockNightBloom().setBlockName(name.toLowerCase())
                .setBlockTextureName(DefaultProps.blockKey + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.nightBloom.get();
        GameRegistry.registerBlock(block, name.toLowerCase());
    }
}
