package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.BlockPalmTreePlank;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class PalmTreePlankDeclaration extends BlockDeclaration {

    public PalmTreePlankDeclaration() {
        super("PalmTreePlank");
    }

    @Override
    protected boolean createBlock() {
        BlockList.palmTreePlank = Optional
                .of(new BlockPalmTreePlank().setStepSound(Block.soundTypeWood).setBlockName(name.toLowerCase())
                        .setBlockTextureName(DefaultProps.blockKey + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.palmTreePlank.get();
        GameRegistry.registerBlock(block, name.toLowerCase());
        OreDictionary.registerOre("plankWood", new ItemStack(block));
        Blocks.fire.setFireInfo(block, 5, 20);
    }
}
