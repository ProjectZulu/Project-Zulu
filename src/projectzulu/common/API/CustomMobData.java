package projectzulu.common.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import projectzulu.common.core.entitydeclaration.EntityProperties;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandomItem;

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

    public static class WeightedItemStack extends WeightedRandomItem {
        public final ItemStack itemStack;

        public WeightedItemStack(ItemStack itemStack, int weight) {
            super(weight);
            this.itemStack = itemStack;
        }
    }

    public CustomMobData(String mobName) {
        this.mobName = mobName;
    }

    public void addLootToMob(ItemStack itemStack, int weight) {
        lootItems.add(new WeightedItemStack(itemStack, weight));
    }

    public ItemStack getLootItem(Random rand) {
        if (lootItems != null && !lootItems.isEmpty()) {
            return ((WeightedItemStack) WeightedRandom.getRandomItem(rand, lootItems)).itemStack.copy();
        }
        return null;
    }

    public Collection<ItemStack> getLoot(Random rand, int extraDrops) {
        final int numberOfItems = minDropNum + rand.nextInt(1 + maxDropNum - minDropNum + extraDrops);
        List<ItemStack> items = new ArrayList<ItemStack>(numberOfItems);
        for (int i = 0; i < numberOfItems; i++) {
            items.add(getLootItem(rand));
        }
        return items;
    }
}
