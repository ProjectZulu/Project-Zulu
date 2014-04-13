package projectzulu.common.blocks.itemblockdeclarations;

import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemStructurePlacer;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class StructurePlacerDeclaration extends ItemDeclaration {

    public StructurePlacerDeclaration() {
        super("StructurePlacer");
    }

    @Override
    protected boolean createItem() {
        ItemList.structurePlacer = Optional.of(new ItemStructurePlacer(name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
        GameRegistry.registerItem(ItemList.structurePlacer.get(), name);
    }
}
