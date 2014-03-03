package projectzulu.common;

import java.util.ArrayList;
import java.util.List;

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
        public final int minItemId;
        public final int maxItemId;

        public ItemIdMatch(int minItemId, int maxItemId) {
            this.minItemId = minItemId;
            this.maxItemId = maxItemId;
        }

        @Override
        public boolean isMatch(ItemStack itemStack) {
            if (itemStack == null) {
                return false;
            }
            if (minItemId <= maxItemId) {
                return (itemStack.itemID <= maxItemId && itemStack.itemID >= minItemId);
            } else {
                return !(itemStack.itemID < minItemId && itemStack.itemID > maxItemId);
            }
        }
    }

    private static class ItemMetaMatch extends ItemIdMatch {
        public final int minItemDamage;
        public final int maxItemDamage;

        public ItemMetaMatch(int minItemId, int maxItemId, int itemDamage) {
            this(minItemId, maxItemId, itemDamage, itemDamage);
        }

        public ItemMetaMatch(int minItemId, int maxItemId, int minItemDamage, int maxItemDamage) {
            super(minItemId, maxItemId);
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
            int minItemID;
            int maxItemID;
            String[] itemRangeParts = entryParts[0].split("-");
            if (itemRangeParts.length == 1) {
                minItemID = StringHelper.parseInteger(itemRangeParts[0], "0123456789");
                maxItemID = minItemID;
            } else {
                minItemID = StringHelper.parseInteger(itemRangeParts[0], "0123456789");
                maxItemID = StringHelper.parseInteger(itemRangeParts[1], "0123456789");
            }

            if (entryParts.length >= 2) {
                String[] metaRangeParts = entryParts[1].split("-");
                if (metaRangeParts.length == 1) {
                    if ("*".equals(metaRangeParts[0])) {
                        blacklist.add(new ItemIdMatch(minItemID, maxItemID));
                    } else {
                        int minMeta = StringHelper.parseInteger(metaRangeParts[0], "0123456789");
                        blacklist.add(new ItemMetaMatch(minItemID, maxItemID, minMeta));
                    }
                } else {
                    int minMeta = StringHelper.parseInteger(metaRangeParts[0], "0123456789");
                    int maxMeta = StringHelper.parseInteger(metaRangeParts[1], "0123456789");
                    blacklist.add(new ItemMetaMatch(minItemID, maxItemID, minMeta, maxMeta));
                }
            } else {
                blacklist.add(new ItemIdMatch(minItemID, maxItemID));
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
