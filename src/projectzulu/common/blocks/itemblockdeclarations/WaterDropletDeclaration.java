package projectzulu.common.blocks.itemblockdeclarations;

import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemFoodProjectZulu;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

public class WaterDropletDeclaration extends ItemDeclaration {

    public WaterDropletDeclaration() {
        super("WaterDroplet");
    }

    @Override
    protected boolean createItem(int iD) {
        ItemList.waterDroplets = Optional.of(new ItemFoodProjectZulu(iD, 1, 0.6f, false, name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
    }
}
