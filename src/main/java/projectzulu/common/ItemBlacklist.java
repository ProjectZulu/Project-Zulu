package projectzulu.common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import projectzulu.common.blocks.util.StringHelper;

public class ItemBlacklist {

    private List<Rule> blacklist;

    private static interface Rule {
        public boolean isMatch(ItemStack itemStack);
    }

    private static class ItemIdMatch implements Rule {
        public final String itemId;

        public ItemIdMatch(String itemId) {
            this.itemId = itemId;
        }

        @Override
        public boolean isMatch(ItemStack itemStack) {
            if (itemStack == null) {
                return false;
            }
            return itemId.equals(Item.itemRegistry.getNameForObject(itemStack.getItem()));
        }
    }

    private static class ItemMetaMatch extends ItemIdMatch {
        public final int minItemDamage;
        public final int maxItemDamage;

        public ItemMetaMatch(String itemId, int minItemDamage) {
            this(itemId, minItemDamage, minItemDamage);
        }

        public ItemMetaMatch(String itemId, int minItemDamage, int maxItemDamage) {
            super(itemId);
            this.minItemDamage = minItemDamage;
            this.maxItemDamage = maxItemDamage;
        }

        @Override
        public boolean isMatch(ItemStack itemStack) {
            if (super.isMatch(itemStack)) {
                if (minItemDamage <= maxItemDamage) {
                    return (itemStack.getItemDamage() <= maxItemDamage && itemStack.getItemDamage() >= minItemDamage);
                } else {
                    return !(itemStack.getItemDamage() < minItemDamage && itemStack.getItemDamage() > maxItemDamage);
                }
            }
            return false;
        }
    }

    public void loadFromConfig(Configuration config) {
        blacklist = new ArrayList<Rule>();
        Property property = config.get("General Controls", "Tombstone drop blacklist", "");
        String[] itemStringEntries = property.getString().split(",");
        for (String stringEntry : itemStringEntries) {
            // <itemRange [Inclusive]>:<damage range[Inclusive]>
            String[] entryParts = stringEntry.split(":");
            if (entryParts.length < 1 || stringEntry.trim().equals("")) {
                continue;
            }
            String itemID = entryParts[0];
            if (entryParts.length >= 2) {
                String[] metaRangeParts = entryParts[1].split("-");
                if (metaRangeParts.length == 1) {
                    if ("*".equals(metaRangeParts[0])) {
                        blacklist.add(new ItemIdMatch(itemID));
                    } else {
                        int minMeta = StringHelper.parseInteger(metaRangeParts[0], "0123456789");
                        blacklist.add(new ItemMetaMatch(itemID, minMeta));
                    }
                } else {
                    int minMeta = StringHelper.parseInteger(metaRangeParts[0], "0123456789");
                    int maxMeta = StringHelper.parseInteger(metaRangeParts[1], "0123456789");
                    blacklist.add(new ItemMetaMatch(itemID, minMeta, maxMeta));
                }
            } else {
                blacklist.add(new ItemIdMatch(itemID));
            }
        }
    }

    public boolean isItemBlacklisted(ItemStack stack) {
        for (Rule rule : blacklist) {
            if (rule.isMatch(stack)) {
                return true;
            }
        }
        return false;
    }
}
