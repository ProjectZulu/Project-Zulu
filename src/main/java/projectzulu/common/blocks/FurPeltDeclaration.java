package projectzulu.common.blocks;

import projectzulu.common.api.ItemList;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

public class FurPeltDeclaration extends ItemDeclaration {

    public FurPeltDeclaration() {
        super("FurPelt");
    }

    @Override
    protected boolean createItem() {
        ItemList.furPelt = Optional.of(new ItemScale(false, name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
    }
}
