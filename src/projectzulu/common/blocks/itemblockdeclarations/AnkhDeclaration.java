package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemAnkh;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class AnkhDeclaration extends ItemDeclaration {

    public AnkhDeclaration() {
        super("Ankh");
    }

    @Override
    protected boolean createItem(int iD) {
        ItemList.ankh = Optional.of(new ItemAnkh(iD).setUnlocalizedName(DefaultProps.blockKey + ":"
                + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.ankh.get();
        LanguageRegistry.addName(item, "Ankh");
    }

}
