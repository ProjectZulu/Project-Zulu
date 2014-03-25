package projectzulu.common.blocks.itemblockdeclarations;

import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemAnkh;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class AnkhDeclaration extends ItemDeclaration {

    public AnkhDeclaration() {
        super("Ankh");
    }

    @Override
    protected boolean createItem() {
        ItemList.ankh = Optional.of(new ItemAnkh(name));
        return true;
    }

    @Override
    protected void registerItem() {
        GameRegistry.registerItem(ItemList.ankh.get(), name);
    }
}
