package projectzulu.common.core;

import java.util.NoSuchElementException;
import java.util.Scanner;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.blocks.util.StringHelper;
import projectzulu.common.core.entitydeclaration.SpawnEntry;

import com.google.common.base.Optional;

public class ConfigHelper {
	
    public static SpawnEntry configGetSpawnEntry(Configuration config, String category, BiomeGenBase biome,
            boolean shouldSpawn, int spawnRate, int minInChunk, int maxInChunk) {
        Property spawnProperty = config.get(category, biome.getClass().getName() + "." + biome.biomeName,
                Boolean.toString(shouldSpawn) + ":" + Integer.toString(spawnRate) + ":" + Integer.toString(minInChunk)
                        + ":" + Integer.toString(maxInChunk));
        String[] spawnProperties = spawnProperty.getString().split(":");
        if (spawnProperties.length != 4) {
            ProjectZuluLog.severe("Error Parseing %s as String %s is does not have the requried number of parameters",
                    biome.biomeName, spawnProperty.getString());
            return null;
        }
        Scanner scanner = new Scanner(spawnProperty.getString());
        scanner.useDelimiter(":");
        try {
            shouldSpawn = scanner.hasNextBoolean() ? scanner.nextBoolean() : shouldSpawn;
            spawnRate = scanner.hasNextInt() ? scanner.nextInt() : spawnRate;
            minInChunk = scanner.hasNextInt() ? scanner.nextInt() : minInChunk;
            maxInChunk = scanner.hasNextInt() ? scanner.nextInt() : maxInChunk;
        } catch (NoSuchElementException e) {
            ProjectZuluLog
                    .severe("Error Parsing %s as the parameters in String %s are not in a parseable format. The Format is shouldSpawn:spawnRate:MinInChunk:MaxInChunk",
                            biome.biomeName, spawnProperty.getString());
            spawnProperty.set(Boolean.toString(shouldSpawn) + ":" + Integer.toString(spawnRate) + ":"
                    + Integer.toString(minInChunk) + ":" + Integer.toString(maxInChunk));
        } finally {
            scanner.close();
        }

        if (shouldSpawn == true) {
            return new SpawnEntry(biome, spawnRate, minInChunk, maxInChunk);
        }
        return null;
    }
    
	public static EnumCreatureType configGetCreatureType(Configuration config, String category, String key, EnumCreatureType creatureType){
		Property creatureProperty = config.get(category, key, creatureType != null ? creatureType.toString() : "None");
		for (EnumCreatureType enumCreatureType : EnumCreatureType.values()) {
			if(enumCreatureType.toString().toLowerCase().equals(creatureProperty.getString().toLowerCase())){
				return enumCreatureType;
			}
		}
		
		if(!creatureProperty.getString().toLowerCase().equals("none")){
			ProjectZuluLog.severe("Error Parsing Entity Config entry %s for EnumCreatureType. Entity will be assumed not to have Type.", creatureProperty.getString());
		}
		return null;
	}
	
	public static void configDropToMobData(Configuration config, String category, CustomMobData customMobData, Item item, int meta, int weightChance){
		configItemStackToMobData(config, category, customMobData, new ItemStack(item, 1, meta), weightChance);
	}
	
	public static void configDropToMobData(Configuration config, String category, CustomMobData customMobData, Block block, int meta, int weightChance){
		configItemStackToMobData(config, category, customMobData, new ItemStack(block, 1, meta), weightChance);
	}
	
	public static void configDropToMobData(Configuration config, String category, CustomMobData customMobData, Optional<?> itemBlock, int meta, int weightChance){
		if(itemBlock.isPresent()){
			if(itemBlock.get() instanceof Item ){
				configItemStackToMobData(config, category, customMobData, new ItemStack((Item)itemBlock.get(), 1, meta), weightChance);
			}else if(itemBlock.get() instanceof Block){
				configItemStackToMobData(config, category, customMobData, new ItemStack((Block)itemBlock.get(), 1, meta), weightChance);
			}
		}
	}
	
	private static void configItemStackToMobData(Configuration config, String category, CustomMobData customMobData, ItemStack itemStack, int weightChance){
		int stackSize = config.get(category,"Item "+itemStack.getUnlocalizedName()+" Quantity:", 1).getInt();
		itemStack.stackSize = stackSize;
		int weight = config.get(category, "Item "+itemStack.getUnlocalizedName()+" Weight:", weightChance).getInt();
		if(weight > 0){
			customMobData.addLootToMob(itemStack, weight);
		}
	}
	
	public static void userItemConfigRangeToMobData(Configuration config, String category, CustomMobData customMobData){
		Property property = config.get(category,"Item User Custom Drop", "0-0:0:3:4, 0:0:1:2");
		String[] itemStringEntries = property.getString().split(",");
		for (String stringEntry : itemStringEntries){
			String[] entryParts = stringEntry.split(":");
			if(entryParts.length == 4){
				// <itemRange [Inclusive]>:<meta/damage>:<Weight>:<Quantity>
				int meta = StringHelper.parseInteger(entryParts[1], "0123456789");
				int weight = StringHelper.parseInteger(entryParts[2], "0123456789");
				int quantity = StringHelper.parseInteger(entryParts[3], "0123456789");
				
                String itemId = entryParts[0] != null ? entryParts[0].trim() : "";
                customMobData.addLootToMob(itemId, meta, quantity, weight);
			}
		}
	}
}
