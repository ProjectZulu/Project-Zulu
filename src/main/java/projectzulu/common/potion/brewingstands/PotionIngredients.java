package projectzulu.common.potion.brewingstands;

import java.util.HashMap;

import net.minecraft.init.Items;
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

    private static HashMap<Item, IngredientProperty> ingredientProperties = new HashMap<Item, IngredientProperty>();

    static {
        addIngredientProperties(Items.feather, new OverrideIngredient());
    }

    public static void addIngredientProperties(Item item, IngredientProperty ingredientProperty) {
        ingredientProperties.put(item, ingredientProperty);
    }

    public static boolean isPotionIngredient(ItemStack ingredient) {
        if (ingredient == null) {
            return false;
        }

        IngredientProperty property = ingredientProperties.get(ingredient.getItem());
        if (property != null) {
            return property.isIngredient(ingredient);
        } else {
            return ingredient != null ? ingredient.getItem().isPotionIngredient(ingredient) : false;
        }
    }
}
