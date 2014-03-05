package projectzulu.common.blocks.itemblockdeclarations;

import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemCoconutSeed;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

public class CoconutSeedDeclaration extends ItemDeclaration {

    public CoconutSeedDeclaration() {
        super("CoconutSeed");
    }

    @Override
    protected boolean createItem() {
        ItemList.coconutSeed = Optional.of(new ItemCoconutSeed(6, false, name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
    }
}
