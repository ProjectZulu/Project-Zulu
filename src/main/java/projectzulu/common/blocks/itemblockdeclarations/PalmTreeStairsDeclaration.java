package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.BlockZuluStairs;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class PalmTreeStairsDeclaration extends BlockDeclaration {

    public PalmTreeStairsDeclaration() {
        super("PalmTreeStairs", 1);
    }

    @Override
    protected boolean createBlock(int iD) {
        if (BlockList.palmTreePlank.isPresent()) {
            BlockList.palmTreeStairs = Optional.of(new BlockZuluStairs(iD, BlockList.palmTreePlank.get(), 0)
                    .setUnlocalizedName(name.toLowerCase()).setTextureName(
                            DefaultProps.blockKey + ":" + name.toLowerCase()));
            return true;
        }
        return false;
    }

    @Override
    protected void registerBlock() {
        if (BlockList.palmTreeStairs.isPresent()) {
            Block block = BlockList.palmTreeStairs.get();
            GameRegistry.registerBlock(block, name.toLowerCase());
            OreDictionary.registerOre("stairsWood", new ItemStack(block));
            OreDictionary.registerOre("stairsPalm", new ItemStack(block));
        }
    }
}
