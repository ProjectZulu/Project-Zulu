package projectzulu.common;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import projectzulu.common.core.ProjectZuluLog;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * This allows Registry of PotionItem readers.
 * 
 * Each SubID represents a "Family" of Upgradeable Potions "i.e. SlowFall, Slowfall Extended, Slowfall Extended II".
 * Used for determining Display Name, if an Ingredient is applicable, etc. Uniquely defined by SubID.
 * 
 * For ItemPotions, ItemDamage (32bit Integer) is Parsed for properties.
 * 
 * {0000 0000} SubID - {000} Level - {00} Duration - {00} Power - {0} Splash
 * 
 * Note this is NOT Potion Effects (See {@link Potion}). A Reference to the relevent PoionID(s) is typically kept.
 */
public enum SubItemPotionRegistry {
    INSTANCE;
    private Table<Integer, Integer, SubItemPotion> subPotions = HashBasedTable.create(2, 256);
    private Table<Integer, String, Integer> itemAndNameToSubIDMap = HashBasedTable.create(2, 256);

    public void addSubPotions(SubItemPotion... potionSubItems) {
        for (SubItemPotion subItemPotion : potionSubItems) {
            if (subPotions.row(subItemPotion.itemID).keySet().contains(subItemPotion.subID)) {
                ProjectZuluLog.info("Potion Conflict: Replacing Potion at %s:%s with %s", subItemPotion.itemID,
                        subItemPotion.subID, subItemPotion.baseName);
            }
            subPotions.put(subItemPotion.itemID, subItemPotion.subID, subItemPotion);
            itemAndNameToSubIDMap.put(subItemPotion.itemID, subItemPotion.baseName, subItemPotion.subID);
        }
    }

    public SubItemPotion getPotion(ItemStack itemStack) {
        return getPotion(itemStack.itemID, PotionParser.readID(itemStack.getItemDamage()));
    }

    public SubItemPotion getPotion(int itemID, int subID) {
        return subPotions.get(itemID, subID);
    }

    public SubItemPotion getPotion(int itemID, String name) {
        Integer subID = itemAndNameToSubIDMap.get(itemID, name);
        return subID != null ? subPotions.get(itemID, subID) : null;
    }

    public boolean isItemPotion(ItemStack itemStack) {
        return subPotions.get(itemStack.itemID, PotionParser.readID(itemStack.itemID)) != null;
    }

    public boolean isItemPotion(int itemID) {
        return !subPotions.row(itemID).isEmpty();
    }
}
