package projectzulu.common.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
	
	private List lootItems = new ArrayList<WeightedItemStack>();
	public static class WeightedItemStack extends WeightedRandomItem{
		public final ItemStack itemStack;
		public WeightedItemStack(ItemStack itemStack, int weight) {
			super(weight);
			this.itemStack = itemStack;
		}
	}
	
	public CustomMobData(String mobName){
		this.mobName = mobName;
	}
	
	@SuppressWarnings("unchecked")
	public void addLootToMob(ItemStack itemStack, int weight){
		lootItems.add(new WeightedItemStack(itemStack, weight));
	}
	
	public ItemStack getLootItem(Random rand){
		if(lootItems != null && !lootItems.isEmpty()){
			return ((WeightedItemStack)WeightedRandom.getRandomItem(rand, lootItems)).itemStack;
		}
		return null;
	}
}
