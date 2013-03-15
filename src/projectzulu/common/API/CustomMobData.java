package projectzulu.common.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;

public class CustomMobData {
	public String mobName = "";
	public int secondarySpawnRate = 0;
	public boolean reportSpawningInLog = false;
	public boolean shouldDespawn = true;
	public EnumCreatureType creatureType;
	
	private List lootItems = new ArrayList<ItemStack>();
	private List lootWeights = new ArrayList<Integer>();
	private int totalWeight = 0;
	
	public CustomMobData(String mobName, int secondarySpawnRate, boolean reportSpawningInLog){
		this.mobName = mobName;
		this.secondarySpawnRate = secondarySpawnRate;
		this.mobName = mobName;
	}
	public CustomMobData(String mobName, boolean reportSpawningInLog){
		this(mobName, 0, reportSpawningInLog);
	}
	public CustomMobData(String mobName, int secondarySpawnRate){
		this(mobName, secondarySpawnRate, false);
	}
	public CustomMobData(String mobName){
		this(mobName, 0);
	}
	
	public void addLootToMob(ItemStack itemStack, int weight){
		lootItems.add(itemStack);
		lootWeights.add(weight);
		totalWeight += weight;
	}
	
	public ItemStack getLootItem(Random rand){
		int lootChance = totalWeight > 0 ? rand.nextInt(totalWeight) : 0;
		ItemStack lootItem = null;
		for (int i = 0; i < lootWeights.size(); i++) {
			if(lootChance - (Integer)lootWeights.get(i) <= 0){
				lootItem = (ItemStack) lootItems.get(i);
				break;
			}else{
				lootChance -= (Integer)lootWeights.get(i);
			}
		}
		return lootItem != null ? lootItem.copy() : null;
	}
}
