package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.BlockAloeVera;
import projectzulu.common.blocks.ItemAloeVera;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class AloeVeraDeclaration extends BlockDeclaration {

    public AloeVeraDeclaration() {
        super("AloeVera");
    }

    @Override
    protected boolean createBlock() {
        BlockList.aloeVera = Optional.of((new BlockAloeVera()).setBlockName(name.toLowerCase()).setBlockTextureName(
                DefaultProps.blockKey + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    public void registerBlock() {
        Block block = BlockList.aloeVera.get();
        GameRegistry.registerBlock(block, ItemAloeVera.class, name.toLowerCase());
    }
}