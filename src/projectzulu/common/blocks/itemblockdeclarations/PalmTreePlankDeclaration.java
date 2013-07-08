package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.BlockPalmTreePlank;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class PalmTreePlankDeclaration extends BlockDeclaration {

    public PalmTreePlankDeclaration() {
        super("PalmTreePlank");
    }

    @Override
    protected boolean createBlock(int iD) {
        BlockList.palmTreePlank = Optional.of(new BlockPalmTreePlank(iD).setStepSound(Block.soundWoodFootstep)
                .setUnlocalizedName(DefaultProps.blockKey + ":" + name.toLowerCase())
                .func_111022_d(DefaultProps.blockKey + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.palmTreePlank.get();
        GameRegistry.registerBlock(block, name.toLowerCase());
        LanguageRegistry.addName(block, "Palm Tree Plank");
        OreDictionary.registerOre("plankWood", new ItemStack(block));
    }
}
