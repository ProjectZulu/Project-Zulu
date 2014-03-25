package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemFoodProjectZulu;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

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
        Item item = ItemList.scrapMeat.get();
        GameRegistry.registerItem(item, name);
    }
}
