package projectzulu.common.blocks.itemblockdeclarations;

import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemCoconutShell;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

public class CoconutShellDeclaration extends ItemDeclaration {

    public CoconutShellDeclaration() {
        super("CoconutShell");
    }

    @Override
    protected boolean createItem() {
        ItemList.coconutShell = Optional.of(new ItemCoconutShell(false, name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
    }
}
