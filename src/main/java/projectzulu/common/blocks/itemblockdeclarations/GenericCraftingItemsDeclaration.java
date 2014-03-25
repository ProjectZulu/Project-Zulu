package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;
import projectzulu.common.potion.brewingstands.PotionIngredients;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;

public class GenericCraftingItemsDeclaration extends ItemDeclaration {

    public GenericCraftingItemsDeclaration() {
        super("GenericCraftingItems1");
    }

    @Override
    protected boolean createItem() {
        ItemGenerics itemGenerics = (ItemGenerics) new ItemGenerics();
        ItemList.genericCraftingItems = Optional.of(itemGenerics);
        PotionIngredients.addIngredientProperties(itemGenerics, itemGenerics);
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.genericCraftingItems.get();
        OreDictionary.registerOre("foodSalt", new ItemStack(item, 1, ItemGenerics.Properties.Salt.meta()));
        GameRegistry.registerItem(item, name);
    }
}