package projectzulu.common.blocks.itemblockdeclarations;

import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemFoodProjectZulu;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

public class ScrapMeatDeclaration extends ItemDeclaration {

    public ScrapMeatDeclaration() {
        super("ScrapMeat");
    }

    @Override
    protected boolean createItem() {
        ItemList.scrapMeat = Optional.of(new ItemFoodProjectZulu(1, 1.0f, false, name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
    }
}
