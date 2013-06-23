package projectzulu.common.potion.brewingstands;

import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PotionIngredients {

    public static interface IngredientProperty {
        public abstract boolean isIngredient(ItemStack ingredient);
    }

    public static class OverrideIngredient implements IngredientProperty {
        boolean isIngredient = true;

        @Override
        public boolean isIngredient(ItemStack ingredient) {
            return isIngredient;
        }
    }

    private static HashMap<Integer, IngredientProperty> ingredientProperties = new HashMap<Integer, IngredientProperty>();

    static {
        addIngredientProperties(Item.feather.itemID, new OverrideIngredient());
    }

    public static void addIngredientProperties(int itemID, IngredientProperty ingredientProperty) {
        ingredientProperties.put(itemID, ingredientProperty);
    }

    public static boolean isPotionIngredient(ItemStack ingredient) {
        if (ingredient == null) {
            return false;
        }

        IngredientProperty property = ingredientProperties.get(ingredient.getItem().itemID);
        if (property != null) {
            return property.isIngredient(ingredient);
        } else {
            return ingredient != null ? Item.itemsList[ingredient.itemID].isPotionIngredient(ingredient) : false;
        }
    }
}
