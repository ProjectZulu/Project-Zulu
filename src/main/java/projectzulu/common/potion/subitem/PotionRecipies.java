package projectzulu.common.potion.subitem;

import java.util.HashMap;

import projectzulu.common.api.SubItemPotionList;

public class PotionRecipies {
    /*
     * Ingredient String Format is ItemID.Meta Star ("*") should be used in place of Meta if Damage Shouldn't Matter A
     * Meta Result Is Found Before a WildCard Result is found
     */
    private static HashMap<String, SubItemPotion> ingredientToPotionResult = new HashMap<String, SubItemPotion>();

    public void addResultPotion(int itemID, SubItemPotionList resultPotion) {
        if (resultPotion.isPresent()) {
            ingredientToPotionResult.put(Integer.toString(itemID) + ".*", resultPotion.get());
        }
    }

    public void addResultPotion(int itemID, int meta, SubItemPotionList resultPotion) {
        if (resultPotion.isPresent()) {
            ingredientToPotionResult.put(Integer.toString(itemID) + "." + Integer.toString(meta), resultPotion.get());
        }
    }

    public SubItemPotion getResulingPotion(int itemID, int meta) {
        SubItemPotion result = ingredientToPotionResult.get(Integer.toString(itemID) + "." + Integer.toString(meta));
        if (result == null) {
            result = ingredientToPotionResult.get(Integer.toString(itemID) + ".*");
        }
        return result;
    }
}
