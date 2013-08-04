package projectzulu.common.blocks.itemblockdeclarations;

import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemCoconutItem;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

public class CoconutItem extends ItemDeclaration {

    public CoconutItem() {
        super("CoconutItem");
    }

    @Override
    protected boolean createItem(int iD) {
        ItemList.coconutItem = Optional.of(new ItemCoconutItem(iD, false, name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
    }
}
