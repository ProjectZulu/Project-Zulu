package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.BlockTumbleweed;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class TumbleweedDeclaration extends BlockDeclaration {

    public TumbleweedDeclaration() {
        super("Tumbleweed");
    }

    @Override
    protected boolean createBlock() {
        BlockList.tumbleweed = Optional.of((new BlockTumbleweed()).setBlockName(name.toLowerCase())
                .setBlockTextureName(DefaultProps.blockKey + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.tumbleweed.get();
        GameRegistry.registerBlock(block, name.toLowerCase());
    }
}
