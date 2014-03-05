package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.BlockPalmTreeLeaves;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class PalmTreeLeavesDeclaration extends BlockDeclaration {

    public PalmTreeLeavesDeclaration() {
        super("PalmTreeLeaves");
    }

    @Override
    protected boolean createBlock() {
        BlockList.palmTreeLeaves = Optional.of((new BlockPalmTreeLeaves()).setBlockName(name.toLowerCase())
                .setBlockTextureName(DefaultProps.blockKey + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.palmTreeLeaves.get();
        GameRegistry.registerBlock(block, name.toLowerCase());
        OreDictionary.registerOre("leaves", new ItemStack(block));
        OreDictionary.registerOre("leavesPalm", new ItemStack(block));
    }
}
