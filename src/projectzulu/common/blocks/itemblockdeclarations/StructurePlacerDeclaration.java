package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemStructurePlacer;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class StructurePlacerDeclaration extends ItemDeclaration {

    public StructurePlacerDeclaration() {
        super("StructurePlacer");
    }

    @Override
    protected boolean createItem(int iD) {
        ItemList.structurePlacer = Optional.of(new ItemStructurePlacer(iD).setUnlocalizedName(DefaultProps.blockKey
                + ":" + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.structurePlacer.get();
        LanguageRegistry.addName(new ItemStack(item, 1, 0), "Oasis Structure Placer");
        LanguageRegistry.addName(new ItemStack(item, 1, 1), "Pyramid Structure Placer");
        LanguageRegistry.addName(new ItemStack(item, 1, 2), "Labyrinth Structure Placer");
        LanguageRegistry.addName(new ItemStack(item, 1, 3), "Cemetary Structure Placer");
    }
}
