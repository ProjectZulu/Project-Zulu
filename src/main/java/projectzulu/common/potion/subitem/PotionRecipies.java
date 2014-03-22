package projectzulu.common.potion.subitem;

import java.util.HashMap;

import net.minecraft.item.Item;
import projectzulu.common.api.SubItemPotionList;

public class PotionRecipies {
    /*
     * Ingredient String Format is ItemID.Meta Star ("*") should be used in place of Meta if Damage Shouldn't Matter A
     * Meta Result Is Found Before a WildCard Result is found
     */
    private static HashMap<String, SubItemPotion> ingredientToPotionResult = new HashMap<String, SubItemPotion>();

    public void addResultPotion(Item item, SubItemPotionList resultPotion) {
        if (resultPotion.isPresent()) {
            String name = Item.itemRegistry.getNameForObject(item);
            ingredientToPotionResult.put(name + ".*", resultPotion.get());
        }
    }

    public void addResultPotion(Item item, int meta, SubItemPotionList resultPotion) {
        if (resultPotion.isPresent()) {
            String name = Item.itemRegistry.getNameForObject(item);
            ingredientToPotionResult.put(name + "." + Integer.toString(meta), resultPotion.get());
        }
    }

    public SubItemPotion getResulingPotion(Item item, int meta) {
        String name = Item.itemRegistry.getNameForObject(item);
        SubItemPotion result = ingredientToPotionResult.get(name + "." + Integer.toString(meta));
        if (result == null) {
            result = ingredientToPotionResult.get(name + ".*");
        }
        return result;
    }
}
