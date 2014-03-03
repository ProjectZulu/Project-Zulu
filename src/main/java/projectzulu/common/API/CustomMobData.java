package projectzulu.common.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;
import projectzulu.common.core.entitydeclaration.EntityProperties;

public class CustomMobData {
    public String mobName = "";
    public final HashMap<String, Object> customData = new HashMap<String, Object>();
    public int secondarySpawnRate = 100;
    public boolean reportSpawningInLog = false;
    public boolean shouldDespawn = true;
    public int maxSpawnInChunk = 4;
    public EnumCreatureType creatureType;
    public EnumCreatureType spawnType;
    public EntityProperties entityProperties;

    public int minDropNum = 0;
    public int maxDropNum = 0;

    private List<WeightedItemStack> lootItems = new ArrayList<WeightedItemStack>();

    public static class WeightedItemStack extends WeightedRandom.Item {
        public final String itemID;
        public final int itemDamage;
        public final int stackSize;

        public WeightedItemStack(String itemID, int itemDamage, int stackSize, int weight) {
            super(weight);
            this.itemID = itemID;
            this.itemDamage = itemDamage;
            this.stackSize = stackSize;
        }
    }

    public CustomMobData(String mobName) {
        this.mobName = mobName;
    }

    public void addLootToMob(ItemStack itemStack, int weight) {
        lootItems.add(new WeightedItemStack(Item.itemRegistry.getNameForObject(itemStack.getItem()), itemStack
                .getItemDamage(), itemStack.stackSize, weight));
    }

    public void addLootToMob(String itemID, int itemDamage, int stackSize, int weight) {
        lootItems.add(new WeightedItemStack(itemID, itemDamage, stackSize, weight));
    }

    public ItemStack getLootItem(Random rand) {
        if (lootItems != null && !lootItems.isEmpty()) {
            WeightedItemStack stack = ((WeightedItemStack) WeightedRandom.getRandomItem(rand, lootItems));
            Item item = (Item) Item.itemRegistry.getObject(stack.itemID);
            if (item != null) {
                return new ItemStack(item, stack.stackSize, stack.itemDamage);
            }
        }
        return null;
    }

    public Collection<ItemStack> getLoot(Random rand, int extraDrops) {
        final int numberOfItems = minDropNum + rand.nextInt(1 + maxDropNum - minDropNum + extraDrops);
        List<ItemStack> items = new ArrayList<ItemStack>(numberOfItems);
        for (int i = 0; i < numberOfItems; i++) {
            ItemStack stack = getLootItem(rand);
            if (stack != null) {
                items.add(stack);
            }
        }
        return items;
    }
}
