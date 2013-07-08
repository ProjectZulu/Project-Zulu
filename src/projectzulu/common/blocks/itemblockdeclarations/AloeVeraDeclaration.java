package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.BlockAloeVera;
import projectzulu.common.blocks.ItemAloeVera;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class AloeVeraDeclaration extends BlockDeclaration {

    public AloeVeraDeclaration() {
        super("AloeVera");
    }

    @Override
    protected boolean createBlock(int iD) {
        BlockList.aloeVera = Optional.of((new BlockAloeVera(iD)).setUnlocalizedName(
                DefaultProps.blockKey + ":" + name.toLowerCase()).func_111022_d(
                DefaultProps.blockKey + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    public void registerBlock() {
        Block block = BlockList.aloeVera.get();
        GameRegistry.registerBlock(block, ItemAloeVera.class, name.toLowerCase());
        LanguageRegistry.addName(new ItemStack(block, 1, OreDictionary.WILDCARD_VALUE), "Aloe Vera Plant");
    }
}
