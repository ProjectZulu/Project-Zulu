package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemCoconutShell;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class CoconutShellDeclaration extends ItemDeclaration {

    public CoconutShellDeclaration() {
        super("CoconutShell");
    }

    @Override
    protected boolean createItem(int iD) {
        ItemList.coconutShell = Optional.of(new ItemCoconutShell(iD, false, name));
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.coconutShell.get();
        LanguageRegistry.addName(item, "Coconut Shell");
    }
}
