package projectzulu.common.blocks.itemblockdeclarations;

import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemStructurePlacer;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

public class StructurePlacerDeclaration extends ItemDeclaration {

    public StructurePlacerDeclaration() {
        super("StructurePlacer");
    }

    @Override
    protected boolean createItem(int iD) {
        ItemList.structurePlacer = Optional.of(new ItemStructurePlacer(iD, name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
    }
}
