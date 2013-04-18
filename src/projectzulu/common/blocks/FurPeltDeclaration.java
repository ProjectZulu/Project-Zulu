package projectzulu.common.blocks;

import net.minecraft.item.Item;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class FurPeltDeclaration extends ItemDeclaration {

    public FurPeltDeclaration() {
        super("FurPelt");
    }

    @Override
    protected boolean createItem(int iD) {
        ItemList.furPelt = Optional.of(new ItemScale(iD, 23, false).setUnlocalizedName(DefaultProps.blockKey + ":"
                + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.furPelt.get();
        LanguageRegistry.addName(item, "Fur Pelt");
    }
}
