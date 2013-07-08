package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemCoconutItem;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class CoconutItem extends ItemDeclaration {

    public CoconutItem() {
        super("CoconutItem");
    }

    @Override
    protected boolean createItem(int iD) {
        ItemList.coconutItem = Optional.of(new ItemCoconutItem(iD, false, name));
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.coconutItem.get();
        LanguageRegistry.addName(item, "Whole Coconut");
    }
}
