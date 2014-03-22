package projectzulu.common.potion.subitem;

import java.util.Collection;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.potion.PotionParser;

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
 * Only Reserved Bits are the First 4 bits for SubID bit 13 for Splash
 * 
 * Note this is NOT Potion Effects (See {@link Potion}). A Reference to the relevent PoionID(s) is typically kept.
 */
public enum SubItemPotionRegistry {
    INSTANCE;
    private Table<Item, Integer, SubItemPotion> subPotions = HashBasedTable.create(2, 16);
    private Table<Item, String, Integer> itemAndNameToSubIDMap = HashBasedTable.create(2, 16);

    public void addSubPotions(SubItemPotion... potionSubItems) {
        for (SubItemPotion subItemPotion : potionSubItems) {
            if (subPotions.row(subItemPotion.item).keySet().contains(subItemPotion.subID)) {
                ProjectZuluLog.info("Potion Conflict: Replacing Potion at %s:%s with %s", subItemPotion.item,
                        subItemPotion.subID, subItemPotion.baseName);
            }
            subPotions.put(subItemPotion.item, subItemPotion.subID, subItemPotion);
            itemAndNameToSubIDMap.put(subItemPotion.item, subItemPotion.baseName, subItemPotion.subID);
        }
    }

    public Collection<SubItemPotion> getPotions(Item item) {
        return subPotions.row(item).values();
    }

    public SubItemPotion getPotion(ItemStack itemStack) {
        return getPotion(itemStack.getItem(), PotionParser.readID(itemStack.getItemDamage()));
    }

    public SubItemPotion getPotion(Item itemID, int subID) {
        return subPotions.get(itemID, subID);
    }

    public SubItemPotion getPotion(int itemID, String name) {
        Integer subID = itemAndNameToSubIDMap.get(itemID, name);
        return subID != null ? subPotions.get(itemID, subID) : null;
    }

    public boolean isItemPotion(ItemStack itemStack) {
        return itemStack != null ? subPotions.get(itemStack.getItem(), PotionParser.readID(itemStack.getItemDamage())) != null
                : false;
    }

    public boolean isItemPotion(Item itemID) {
        return !subPotions.row(itemID).isEmpty();
    }
}
