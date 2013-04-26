package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class GenericCraftingItemsDeclaration extends ItemDeclaration {

    public GenericCraftingItemsDeclaration() {
        super("GenericCraftingItems1");
    }

    @Override
    protected boolean createItem(int iD) {
        ItemList.genericCraftingItems = Optional.of(new ItemGenerics(iD, 10).setUnlocalizedName(DefaultProps.blockKey
                + "genericItems"));
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.genericCraftingItems.get();
        for (ItemGenerics.Properties property : ItemGenerics.Properties.values()) {
            LanguageRegistry.addName(new ItemStack(item, 1, property.meta()), property.getDisplayName());
        }
        OreDictionary.registerOre("foodSalt", new ItemStack(ItemList.genericCraftingItems.get(), 1,
                ItemGenerics.Properties.Salt.meta()));
    }
}
