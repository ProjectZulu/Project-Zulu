package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;
import projectzulu.common.potion.brewingstands.PotionIngredients;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class GenericCraftingItemsDeclaration extends ItemDeclaration {

    public GenericCraftingItemsDeclaration() {
        super("GenericCraftingItems1");
    }

    @Override
    protected boolean createItem(int iD) {
        ItemGenerics itemGenerics = (ItemGenerics) new ItemGenerics(iD, 10).setUnlocalizedName(DefaultProps.blockKey
                + "genericItems");
        ItemList.genericCraftingItems = Optional.of(itemGenerics);
        PotionIngredients.addIngredientProperties(itemGenerics.itemID, itemGenerics);
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.genericCraftingItems.get();
        for (ItemGenerics.Properties property : ItemGenerics.Properties.values()) {
            LanguageRegistry.addName(new ItemStack(item, 1, property.meta()), property.displayName);
        }
        OreDictionary.registerOre("foodSalt", new ItemStack(ItemList.genericCraftingItems.get(), 1,
                ItemGenerics.Properties.Salt.meta()));
    }
}