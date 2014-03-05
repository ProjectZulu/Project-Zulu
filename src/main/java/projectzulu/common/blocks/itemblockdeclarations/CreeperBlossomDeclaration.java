package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.BlockCreeperBlossom;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class CreeperBlossomDeclaration extends BlockDeclaration {

    public CreeperBlossomDeclaration() {
        super("CreeperBlossom");
    }

    @Override
    protected boolean createBlock() {
        BlockList.creeperBlossom = Optional.of(new BlockCreeperBlossom().setBlockName(name.toLowerCase())
                .setBlockTextureName(DefaultProps.blockKey + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.creeperBlossom.get();
        GameRegistry.registerBlock(block, name.toLowerCase());
    }
}
