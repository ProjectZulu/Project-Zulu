package projectzulu.common.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;

import com.google.common.base.Optional;

public class ConfigHelper {
	public static void configDropToMobData(Configuration config, String category, CustomMobData customMobData, Item item, int meta, int weightChance){
		configItemStackToMobData(config, category, customMobData, new ItemStack(item, 1, meta), weightChance);
	}
	
	public static void configDropToMobData(Configuration config, String category, CustomMobData customMobData, Block block, int meta, int weightChance){
		configItemStackToMobData(config, category, customMobData, new ItemStack(block, 1, meta), weightChance);
	}
	
	public static void configDropToMobData(Configuration config, String category, CustomMobData customMobData, Optional<? extends Item> item, int meta, int weightChance){
		if(item.isPresent()){
			configItemStackToMobData(config, category, customMobData, new ItemStack(item.get(), 1, meta), weightChance);
		}
	}
	
	private static void configItemStackToMobData(Configuration config, String category, CustomMobData customMobData, ItemStack itemStack, int weightChance){
		int stackSize = config.get(category,"Item "+itemStack.getItemName()+" Quantity:", 1).getInt();
		itemStack.stackSize = stackSize;
		customMobData.addLootToMob(itemStack, config.get(category, "Item "+itemStack.getItemName()+" Weight:", weightChance).getInt());
	}
}
