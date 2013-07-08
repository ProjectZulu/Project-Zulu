package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.BlockQuickSand;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class QuickSandDeclaration extends BlockDeclaration {

    public QuickSandDeclaration() {
        super("QuickSand");
    }

    @Override
    protected boolean createBlock(int iD) {
        BlockList.quickSand = Optional.of(new BlockQuickSand(iD, 112).setUnlocalizedName(
                DefaultProps.blockKey + ":" + name.toLowerCase()).func_111022_d(
                DefaultProps.blockKey + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.quickSand.get();
        GameRegistry.registerBlock(block, name.toLowerCase());
        LanguageRegistry.addName(block, "QuickSand");
    }
}
