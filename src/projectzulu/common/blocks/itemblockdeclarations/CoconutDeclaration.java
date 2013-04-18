package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.BlockCoconut;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CoconutDeclaration extends BlockDeclaration {

    public CoconutDeclaration() {
        super("Coconut");
    }

    @Override
    protected boolean createBlock(int iD) {
        BlockList.coconut = Optional.of(new BlockCoconut(iD).setUnlocalizedName(DefaultProps.blockKey + ":"
                + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.coconut.get();
        GameRegistry.registerBlock(block, name.toLowerCase());
        LanguageRegistry.addName(block, "Coconut");
    }
}
