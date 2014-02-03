package projectzulu.common.blocks.itemblockdeclarations;

import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemFoodProjectZulu;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

public class CoconutMilkFragmentDeclaration extends ItemDeclaration {

    public CoconutMilkFragmentDeclaration() {
        super("CoconutMilkFragment");
    }

    @Override
    protected boolean createItem(int iD) {
        ItemList.coconutMilkFragment = Optional.of(new ItemFoodProjectZulu(iD, 2, 2.4f, false, name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
    }
}
