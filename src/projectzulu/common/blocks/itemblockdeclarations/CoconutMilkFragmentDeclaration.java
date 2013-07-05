package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemFoodProjectZulu;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class CoconutMilkFragmentDeclaration extends ItemDeclaration {

    public CoconutMilkFragmentDeclaration() {
        super("CoconutMilkFragment");
    }

    @Override
    protected boolean createItem(int iD) {
        ItemList.coconutMilkFragment = Optional.of(new ItemFoodProjectZulu(iD, 2, 2.4f, false, name));
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.coconutMilkFragment.get();
        LanguageRegistry.addName(item, "Coconut Milk Fragment");
    }
}
