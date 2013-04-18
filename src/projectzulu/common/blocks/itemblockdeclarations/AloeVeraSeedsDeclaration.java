package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.api.BlockList;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemAloeVeraSeeds;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class AloeVeraSeedsDeclaration extends ItemDeclaration {

    public AloeVeraSeedsDeclaration() {
        super("AloeVeraSeeds", 1);
    }

    @Override
    protected boolean createItem(int iD) {
        if (BlockList.aloeVera.isPresent()) {
            ItemList.aloeVeraSeeds = Optional.of(new ItemAloeVeraSeeds(iD, BlockList.aloeVera.get().blockID)
                    .setUnlocalizedName(DefaultProps.blockKey + ":" + name.toLowerCase()));
            return true;
        }
        return false;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.aloeVeraSeeds.get();
        LanguageRegistry.addName(item, "Aloe Vera Seeds");
    }
}
