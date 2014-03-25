package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemCoconutItem;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class CoconutItem extends ItemDeclaration {

    public CoconutItem() {
        super("CoconutItem");
    }

    @Override
    protected boolean createItem() {
        ItemList.coconutItem = Optional.of(new ItemCoconutItem(false, name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.coconutItem.get();
        GameRegistry.registerItem(item, name);
    }
}
