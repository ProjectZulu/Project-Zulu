package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemCoconutSeed;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class CoconutSeedDeclaration extends ItemDeclaration {

    public CoconutSeedDeclaration() {
        super("CoconutSeed");
    }

    @Override
    protected boolean createItem(int iD) {
        ItemList.coconutSeed = Optional.of(new ItemCoconutSeed(iD, 6, false, name));
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.coconutSeed.get();
        LanguageRegistry.addName(item, "Coconut Seed");
    }
}
