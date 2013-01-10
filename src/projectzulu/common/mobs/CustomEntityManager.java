package projectzulu.common.mobs;

import java.io.File;
import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.ProjectZulu_Mobs;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemBlockList;
import projectzulu.common.blocks.EntityCreeperBlossomPrimed;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemGenerics;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public enum CustomEntityManager {
	
	Armadillo{
		@Override
		public void loadDefaultProperties() {
			mobName = "Armadillo";
			mobClass = EntityArmadillo.class;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;

			spawnRate = 10;
			secondarySpawnRate = 100;
			minInChunk = 2;
			maxInChunk = 4;
			enumCreatureType = EnumCreatureType.creature;
			trackingRange = 128;
			updateFrequency = 3;
			sendsVelocityUpdates = true;

			eggColor1 = (116 << 16) + (64 << 8) + 33;					eggColor2 = (60 << 16) + (51 << 8) + 10;
			defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);	defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
			defaultBiomesToSpawn.add("Mountainous Desert");				defaultBiomesToSpawn.add("Savanna");
			defaultBiomesToSpawn.add("Mountain Ridge");
		}

		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			if(ItemBlockList.scaleItem.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scaleItem.get()), 10); }
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.SmallHeart.meta()), 4);
			}
			CustomEntityList.instance.armadillo = Optional.of(customMobData);	
		}
	},
	Sandworm{
		@Override
		public void loadDefaultProperties() {
			mobName = "SandWorm";
			mobClass = EntitySandWorm.class;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;

			spawnRate = 1;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 1;
			enumCreatureType = EnumCreatureType.creature;
			trackingRange = 128;
			updateFrequency = 3;
			sendsVelocityUpdates = true;

			eggColor1 = (24 << 16) + (0 << 8) + 8;						eggColor2 = (49 << 16) + (16 << 8) + 8;
			defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);	defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
			defaultBiomesToSpawn.add("Mountainous Desert");
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.SmallHeart.meta()), 4);
			}
			CustomEntityList.instance.sandworm = Optional.of(customMobData);	
		}
	},
	Lizard{
		@Override
		public void loadDefaultProperties() {
			mobName = "Lizard";
			mobClass = EntityLizard.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;

			spawnRate = 10;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 1;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;

			eggColor1 = (114 << 16) + (102 << 8) + 74;					eggColor2 = (181 << 16) + (171 << 8) + 146;
			defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);	defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
			defaultBiomesToSpawn.add("Mountainous Desert");				defaultBiomesToSpawn.add("Savanna");
			defaultBiomesToSpawn.add("Mountain Ridge");
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			if(ItemBlockList.scaleItem.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scaleItem.get()), 10); }
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.SmallHeart.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.PoisonDroplet.meta()), 4);
			}
			CustomEntityList.instance.lizard = Optional.of(customMobData);	
		}
	},
	LizardSpit{
		@Override
		public void loadDefaultProperties() {
			mobName = "Lizard Spit";
			mobClass = EntityLizardSpit.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = false;			shouldExist = true;
			shouldHaveEgg = false;			allowConfigOptions = false;
		}
		@Override
		public void outputDataToList() {}
	},
	Pharaoh{
		@Override
		public void loadDefaultProperties() {
			mobName = "Mummy Pharaoh";
			mobClass = EntityMummyPharaoh.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = false;			shouldExist = true;
			shouldHaveEgg = false;			allowConfigOptions = true;
		}
		@Override
		public void outputDataToList() {
			CustomEntityList.instance.pharaoh = Optional.of(new CustomMobData(mobName, reportSpawningInLog));	
		}
	},
	Mummy{
		@Override
		public void loadDefaultProperties() {
			mobName = "Mummy";
			mobClass = EntityMummy.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 5;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 2;
			enumCreatureType = EnumCreatureType.monster;
			sendsVelocityUpdates = true;

			eggColor1 = (255 << 16) + (255 << 8) + 255;					eggColor2 = (255 << 16) + (255 << 8) + 255;
			defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);	defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
		}
		@Override
		public void outputDataToList() {
			CustomEntityList.instance.mummy = Optional.of(new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog));	
		}
	},
	Vulture{
		@Override
		public void loadDefaultProperties() {
			mobName = "Vulture";
			mobClass = EntityVulture.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;

			spawnRate = 2;
			secondarySpawnRate = 5;
			minInChunk = 1;
			maxInChunk = 3;
			enumCreatureType = EnumCreatureType.monster;
			sendsVelocityUpdates = true;

			eggColor1 = (78 << 16) + (72 << 8) + 56;					eggColor2 = (120 << 16) + (110 << 8) + 86;
			defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);	defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
			defaultBiomesToSpawn.add("Mountainous Desert"); 			defaultBiomesToSpawn.add("Savanna");
			defaultBiomesToSpawn.add("Mountain Ridge"); 				defaultBiomesToSpawn.add("Wasteland");

		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.feather), 8);
			customMobData.addLootToMob(new ItemStack(Item.chickenRaw), 10);
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.SmallHeart.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.Talon.meta()), 4);
			}
			CustomEntityList.instance.vulture = Optional.of(customMobData);	
		}
	},
	TreeEnt{
		@Override
		public void loadDefaultProperties() {
			mobName = "TreeEnt";
			mobClass = EntityTreeEnt.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;

			spawnRate = 1;
			secondarySpawnRate = 7;
			minInChunk = 1;
			maxInChunk = 1;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 = (17 << 16) + (6 << 8) + 3;						eggColor2 = (83 << 16) + (56 << 8) + 29;
			defaultBiomesToSpawn.add(BiomeGenBase.taiga.biomeName);		defaultBiomesToSpawn.add(BiomeGenBase.taigaHills.biomeName);
			defaultBiomesToSpawn.add("Alpine"); 						defaultBiomesToSpawn.add("Mountain Taiga");
			defaultBiomesToSpawn.add("Snow Forest"); 					defaultBiomesToSpawn.add("Snowy Rainforest");

		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Block.wood.blockID, 1, 3), 15);
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.Bark.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.BlackLichen.meta()), 4);
			}
			CustomEntityList.instance.treeEnt = Optional.of(customMobData);	
		}
	},
	Mammoth{
		@Override
		public void loadDefaultProperties() {
			mobName = "Mammoth";
			mobClass = EntityMammoth.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;

			spawnRate = 1;
			secondarySpawnRate = 7;
			minInChunk = 1;
			maxInChunk = 3;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 = (20 << 16) + (12 << 8) + 0;							eggColor2 = (69 << 16) + (42 << 8) + 0;
			defaultBiomesToSpawn.add(BiomeGenBase.icePlains.biomeName);		defaultBiomesToSpawn.add(BiomeGenBase.taigaHills.biomeName);
			defaultBiomesToSpawn.add("Tundra"); 							defaultBiomesToSpawn.add("Ice Wasteland");
			defaultBiomesToSpawn.add("Glacier");

		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 15);
			if(ItemBlockList.furPelt.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.furPelt.get()), 10); }
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.Tusk.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
			}
			CustomEntityList.instance.mammoth = Optional.of(customMobData);	
		}
	},
	Fox{
		@Override
		public void loadDefaultProperties() {
			mobName = "Fox";
			mobClass = EntityFox.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 10;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 3;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 = (204 << 16) + (132 << 8) + 22;					eggColor2 = (224 << 16) + (224 << 8) + 224;
			defaultBiomesToSpawn.add(BiomeGenBase.taiga.biomeName);		defaultBiomesToSpawn.add(BiomeGenBase.taigaHills.biomeName);
			defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName);	defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);
			defaultBiomesToSpawn.add("Birch Forest"); 					defaultBiomesToSpawn.add("Forested Island");
			defaultBiomesToSpawn.add("Forested Hills"); 				defaultBiomesToSpawn.add("Green Hills");
			defaultBiomesToSpawn.add("Mountain Taiga"); 				defaultBiomesToSpawn.add("Pine Forest");
			defaultBiomesToSpawn.add("Rainforest"); 					defaultBiomesToSpawn.add("Redwood Forest");
			defaultBiomesToSpawn.add("Lush Redwoods"); 					defaultBiomesToSpawn.add("Snow Forest");
			defaultBiomesToSpawn.add("Snowy Rainforest"); 				defaultBiomesToSpawn.add("Temperate Rainforest");
			defaultBiomesToSpawn.add("Woodlands");
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 5);
			if(ItemBlockList.furPelt.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.furPelt.get()), 10); }
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 15); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.SmallHeart.meta()), 4);
			}
			CustomEntityList.instance.fox = Optional.of(customMobData);	
		}
	},
	Boar{
		@Override
		public void loadDefaultProperties() {
			mobName = "Boar";
			mobClass = EntityBoar.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 10;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 3;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 = (122 << 16) + (77 << 8) + 32;					eggColor2 = (158 << 16) + (99 << 8) + 42;
			defaultBiomesToSpawn.add(BiomeGenBase.taiga.biomeName);		defaultBiomesToSpawn.add(BiomeGenBase.taigaHills.biomeName);
			defaultBiomesToSpawn.add("Alpine"); 						defaultBiomesToSpawn.add("Mountain Taiga");
			defaultBiomesToSpawn.add("Snowy Rainforest");
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 2);
			if(ItemBlockList.furPelt.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.furPelt.get()), 10); }
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.SmallHeart.meta()), 5);
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.Tusk.meta()), 10);

			}
			CustomEntityList.instance.boar = Optional.of(customMobData);	
		}
	},
	Mimic{
		@Override
		public void loadDefaultProperties() {
			mobName = "Mimic";
			mobClass = EntityMimic.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = false;			shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			eggColor1 = (171 << 16) + (121 << 8) + 45;					eggColor2 = (143 << 16) + (105 << 8) + 29;
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.Ectoplasm.meta()), 5);
			}
			CustomEntityList.instance.mimic = Optional.of(customMobData);	
		}
	},
	CreeperBlossomPrimed{
		@Override
		public void loadDefaultProperties() {
			mobName = "CreeperBlossomPrimed";
			mobClass = EntityCreeperBlossomPrimed.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = false;			shouldExist = true;
			shouldHaveEgg = false;			allowConfigOptions = false;	
		}
		@Override
		public void outputDataToList() {
			CustomEntityList.instance.lizard = Optional.of(new CustomMobData(mobName, reportSpawningInLog));	
		}
	},
	Alligator{
		@Override
		public void loadDefaultProperties() {
			mobName = "Alligator";
			mobClass = EntityCrocodile.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 10;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 2;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 = (32 << 16) + (39 << 8) + 33;						eggColor2 = (52 << 16) + (65 << 8) + 54;
			defaultBiomesToSpawn.add(BiomeGenBase.swampland.biomeName);		defaultBiomesToSpawn.add(BiomeGenBase.river.biomeName);
			defaultBiomesToSpawn.add("Green Swamplands"); 					defaultBiomesToSpawn.add("Marsh");
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			if(ItemBlockList.scaleItem.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scaleItem.get()), 10); }
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.Gill.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
			}
			CustomEntityList.instance.crocodile = Optional.of(customMobData);	
		}
	},
	Frog{
		@Override
		public void loadDefaultProperties() {
			mobName = "Frog";
			mobClass = EntityFrog.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 10;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 3;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 = (95 << 16) + (186 << 8) + 50;						eggColor2 = (105 << 16) + (203 << 8) + 67;
			defaultBiomesToSpawn.add(BiomeGenBase.swampland.biomeName);		
			defaultBiomesToSpawn.add("Green Swamplands"); 					defaultBiomesToSpawn.add("Marsh");
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.Gill.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.FrogLegs.meta()), 4);
			}
			CustomEntityList.instance.frog = Optional.of(customMobData);	
		}
	},
	Penguin{
		@Override
		public void loadDefaultProperties() {
			mobName = "Penguin";
			mobClass = EntityPenguin.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 10;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 3;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 = (22 << 16) + (16 << 8) + 13;						eggColor2 = (235 << 16) + (235 << 8) + 235;
			defaultBiomesToSpawn.add(BiomeGenBase.icePlains.biomeName);		defaultBiomesToSpawn.add("Ice Wasteland");
			defaultBiomesToSpawn.add("Glacier");
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.feather), 8);
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.SmallHeart.meta()), 4);
			}
			CustomEntityList.instance.penguin = Optional.of(customMobData);	
		}
	},
	Beaver{
		@Override
		public void loadDefaultProperties() {
			mobName = "Beaver";
			mobClass = EntityBeaver.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 10;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 2;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 = (54 << 16) + (36 << 8) + 9;							eggColor2 = (67 << 16) + (45 << 8) + 11;
			defaultBiomesToSpawn.add(BiomeGenBase.taiga.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.taigaHills.biomeName);	
			defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);	
			defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");
			defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");
			defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");
			defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");
			defaultBiomesToSpawn.add("Woodlands");
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			if(ItemBlockList.furPelt.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.furPelt.get()), 8); }
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.BlackLichen.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.SmallHeart.meta()), 4);
			}
			CustomEntityList.instance.beaver = Optional.of(customMobData);	
		}
	},
	BlackBear{
		@Override
		public void loadDefaultProperties() {
			mobName = "Black Bear";
			mobClass = EntityBlackBear.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 10;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 2;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 = (0 << 16) + (0 << 8) + 0;							eggColor2 = (23 << 16) + (17 << 8) + 17;
			defaultBiomesToSpawn.add(BiomeGenBase.taiga.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.taigaHills.biomeName);	
			defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);	
			defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");
			defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");
			defaultBiomesToSpawn.add("Mountain Taiga");						defaultBiomesToSpawn.add("Redwood Forest");
			defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Snow Forest");						
			defaultBiomesToSpawn.add("Temperate Rainforest");				defaultBiomesToSpawn.add("Woodlands");			
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			if(ItemBlockList.furPelt.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.furPelt.get()), 8); }
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.BlackLichen.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.SmallHeart.meta()), 4);
			}
			CustomEntityList.instance.blackBear = Optional.of(customMobData);	
		}
	},
	BrownBear{
		@Override
		public void loadDefaultProperties() {
			mobName = "Brown Bear";
			mobClass = EntityBrownBear.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 10;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 2;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 = (51 << 16) + (34 << 8) + 8;							eggColor2 = (63 << 16) + (42 << 8) + 10;
			defaultBiomesToSpawn.add(BiomeGenBase.icePlains.biomeName); 	defaultBiomesToSpawn.add(BiomeGenBase.iceMountains.biomeName);	
			defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);	
			defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");
			defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");
			defaultBiomesToSpawn.add("Mountain Taiga");						defaultBiomesToSpawn.add("Redwood Forest");
			defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Snow Forest");						
			defaultBiomesToSpawn.add("Temperate Rainforest");				defaultBiomesToSpawn.add("Woodlands");			
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			if(ItemBlockList.furPelt.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.furPelt.get()), 8); }
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.BlackLichen.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
			}
			CustomEntityList.instance.brownBear = Optional.of(customMobData);	
		}
	},
	PolarBear{
		@Override
		public void loadDefaultProperties() {
			mobName = "Polar Bear";
			mobClass = EntityPolarBear.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 10;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 2;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 = (255 << 16) + (255 << 8) + 255;						eggColor2 = (201 << 16) + (201 << 8) + 201;
			defaultBiomesToSpawn.add(BiomeGenBase.icePlains.biomeName); 		
			defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);	
			defaultBiomesToSpawn.add("Ice Wasteland");						defaultBiomesToSpawn.add("Glacier");		
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			if(ItemBlockList.furPelt.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.furPelt.get()), 8); }
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.BlackLichen.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
			}
			CustomEntityList.instance.polarBear = Optional.of(customMobData);	
		}
	},
	Ostrich{
		@Override
		public void loadDefaultProperties() {
			mobName = "Ostrich";
			mobClass = EntityOstrich.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 10;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 2;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 = (25 << 16) + (18 << 8) + 14;						eggColor2 = (232 << 16) + (107 << 8) + 101;
			defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName); 
			defaultBiomesToSpawn.add("Meadow");								defaultBiomesToSpawn.add("Savanna");		
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			customMobData.addLootToMob(new ItemStack(Item.feather), 8);
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.Talon.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.SmallHeart.meta()), 4);
			}
			CustomEntityList.instance.ostrich = Optional.of(customMobData);	
		}
	},
	Rhino{
		@Override
		public void loadDefaultProperties() {
			mobName = "Rhino";
			mobClass = EntityRhino.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 10;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 2;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 = (95 << 16) + (93 << 8) + 94;						eggColor2 = (173 << 16) + (170 << 8) + 172;
			defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName); 
			defaultBiomesToSpawn.add("Savanna");		
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.Tusk.meta()), 8);
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
			}
			CustomEntityList.instance.rhino = Optional.of(customMobData);	
		}
	},
	Rabbit{
		@Override
		public void loadDefaultProperties() {
			mobName = "Rabbit";
			mobClass = EntityRabbit.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 15;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 2;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 = (239 << 16) + (179 << 8) + 83;						eggColor2 = (237 << 16) + (208 << 8) + 166;
			defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 
			defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName); 
			defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");	
			defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");		
			defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");		
			defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");		
			defaultBiomesToSpawn.add("Woodlands");	
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.RabbitsFoot.meta()), 8);
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.SmallHeart.meta()), 4);
			}
			CustomEntityList.instance.rabbit = Optional.of(customMobData);	
		}
	},
	RedFinch{
		@Override
		public void loadDefaultProperties() {
			mobName = "Red Finch";
			mobClass = EntityRedFinch.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 10;
			secondarySpawnRate = 5;
			minInChunk = 1;
			maxInChunk = 1;
			enumCreatureType = EnumCreatureType.monster;
			sendsVelocityUpdates = true;
			
			eggColor1 = (255 << 16) + (29 << 8) + 0;						eggColor2 = (255 << 16) + (203 << 8) + 186;
			defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 
			defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName); 
			defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");	
			defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");		
			defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");		
			defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");		
			defaultBiomesToSpawn.add("Woodlands");	
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.feather), 8);
			CustomEntityList.instance.redFinch = Optional.of(customMobData);	
		}
	},
	GreenFinch{
		@Override
		public void loadDefaultProperties() {
			mobName = "Green Finch";
			mobClass = EntityGreenFinch.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 10;
			secondarySpawnRate = 5;
			minInChunk = 1;
			maxInChunk = 1;
			enumCreatureType = EnumCreatureType.monster;
			sendsVelocityUpdates = true;
			
			eggColor1 =  (30 << 16) + (130 << 8) + 0;						eggColor2 = (164 << 16) + (234 << 8) + 143;
			defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 
			defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName); 
			defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");	
			defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");		
			defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");		
			defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");		
			defaultBiomesToSpawn.add("Woodlands");	
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.feather), 8);
			CustomEntityList.instance.greenFinch = Optional.of(customMobData);	
		}
	},
	BlueFinch{
		@Override
		public void loadDefaultProperties() {
			mobName = "Blue Finch";
			mobClass = EntityBlueFinch.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 10;
			secondarySpawnRate = 5;
			minInChunk = 1;
			maxInChunk = 1;
			enumCreatureType = EnumCreatureType.monster;
			sendsVelocityUpdates = true;
			
			eggColor1 =  (38 << 16) + (103 << 8) + 255;						eggColor2 = (224 << 16) + (233 << 8) + 255;
			defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 
			defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName); 
			defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");	
			defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");		
			defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");		
			defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");		
			defaultBiomesToSpawn.add("Woodlands");	
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.feather), 8);
			CustomEntityList.instance.blueFinch = Optional.of(customMobData);	
		}
	},
	Gorilla{
		@Override
		public void loadDefaultProperties() {
			mobName = "Gorilla";
			mobClass = EntityGorilla.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 10;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 1;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 =  (25 << 16) + (25 << 8) + 25;						eggColor2 = (93 << 16) + (93 << 8) + 93;
			defaultBiomesToSpawn.add(BiomeGenBase.jungle.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.jungleHills.biomeName); 
			defaultBiomesToSpawn.add("Mini Jungle");						defaultBiomesToSpawn.add("Extreme Jungle");	
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.furPelt.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.furPelt.get()), 8); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
			}
			CustomEntityList.instance.gorilla = Optional.of(customMobData);	
		}
	},
	Giraffe{
		@Override
		public void loadDefaultProperties() {
			mobName = "Giraffe";
			mobClass = EntityGiraffe.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 10;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 2;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 =  (239 << 16) + (228 << 8) + 109;					eggColor2 = (91 << 16) + (87 << 8) + 41;
			defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		
			defaultBiomesToSpawn.add("Savanna");
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.BlackLichen.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
			}
			CustomEntityList.instance.giraffe = Optional.of(customMobData);	
		}
	},
	Elephant{
		@Override
		public void loadDefaultProperties() {
			mobName = "Elephant";
			mobClass = EntityElephant.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 10;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 2;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 =  (88 << 16) + (67 << 8) + 50;				eggColor2 = (190 << 16) + (165 << 8) + 145;
			defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		
			defaultBiomesToSpawn.add("Savanna");
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.Tusk.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
			}
			CustomEntityList.instance.elephant = Optional.of(customMobData);	
		}
	},
	HorseBeige{
		@Override
		public void loadDefaultProperties() {
			mobName = "Horse Beige";
			mobClass = EntityHorseBeige.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 5;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 2;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 =  (245 << 16) + (245 << 8) + 245;					eggColor2 = (51 << 16) + (51 << 8) + 51;
			defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 		
			defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName); 		
			defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");
			defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");
			defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");
			defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");
			defaultBiomesToSpawn.add("Woodlands");
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
			}
			CustomEntityList.instance.horseBeige = Optional.of(customMobData);	
		}
	},
	HorseBlack{
		@Override
		public void loadDefaultProperties() {
			mobName = "Horse Black";
			mobClass = EntityHorseBlack.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 5;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 2;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 =  (17 << 16) + (17 << 8) + 17;						eggColor2 = (186 << 16) + (186 << 8) + 186;
			defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 		
			defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName); 		
			defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");
			defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");
			defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");
			defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");
			defaultBiomesToSpawn.add("Woodlands");
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
			}
			CustomEntityList.instance.horseBlack = Optional.of(customMobData);	
		}
	},
	HorseBrown{
		@Override
		public void loadDefaultProperties() {
			mobName = "Horse Brown";
			mobClass = EntityHorseBrown.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 5;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 2;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 =  (137 << 16) + (78 << 8) + 0;						eggColor2 = (81 << 16) + (46 << 8) + 0;
			defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 		
			defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName); 		
			defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");
			defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");
			defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");
			defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");
			defaultBiomesToSpawn.add("Woodlands");
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
			}
			CustomEntityList.instance.horseBrown = Optional.of(customMobData);	
		}
	},
	HorseDarkBlack{
		@Override
		public void loadDefaultProperties() {
			mobName = "Horse Dark Black";
			mobClass = EntityHorseDarkBlack.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 5;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 2;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 =  (17 << 16) + (17 << 8) + 17;						eggColor2 = (51 << 16) + (51 << 8) + 51;
			defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 		
			defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName); 		
			defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");
			defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");
			defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");
			defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");
			defaultBiomesToSpawn.add("Woodlands");
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
			}
			CustomEntityList.instance.horseDarkBlack = Optional.of(customMobData);	
		}
	},
	HorseDarkBrown{
		@Override
		public void loadDefaultProperties() {
			mobName = "Horse Dark Brown";
			mobClass = EntityHorseDarkBrown.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 5;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 2;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 =  (102 << 16) + (73 << 8) + 34;						eggColor2 = (60 << 16) + (43 << 8) + 20;
			defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 		
			defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName); 		
			defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");
			defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");
			defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");
			defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");
			defaultBiomesToSpawn.add("Woodlands");
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
			}
			CustomEntityList.instance.horseDarkBrown = Optional.of(customMobData);	
		}
	},
	HorseGrey{
		@Override
		public void loadDefaultProperties() {
			mobName = "Horse Grey";
			mobClass = EntityHorseGrey.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 5;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 2;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 =  (102 << 16) + (73 << 8) + 34;						eggColor2 = (60 << 16) + (43 << 8) + 20;
			defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 		
			defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName); 		
			defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");
			defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");
			defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");
			defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");
			defaultBiomesToSpawn.add("Woodlands");
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
			}
			CustomEntityList.instance.horseGrey = Optional.of(customMobData);	
		}
	},
	HorseWhite{
		@Override
		public void loadDefaultProperties() {
			mobName = "Horse White";
			mobClass = EntityHorseWhite.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 5;
			secondarySpawnRate = 100;
			minInChunk = 1;
			maxInChunk = 2;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 =  (245 << 16) + (245 << 8) + 245;					eggColor2 = (51 << 16) + (51 << 8) + 51;
			defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 		
			defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName); 		
			defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");
			defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");
			defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");
			defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");
			defaultBiomesToSpawn.add("Woodlands");
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
			}
			CustomEntityList.instance.horseWhite = Optional.of(customMobData);	
		}
	},
	Eagle{
		@Override
		public void loadDefaultProperties() {
			mobName = "Eagle";
			mobClass = EntityEagle.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 5;
			secondarySpawnRate = 5;
			minInChunk = 1;
			maxInChunk = 1;
			enumCreatureType = EnumCreatureType.monster;
			sendsVelocityUpdates = true;
			
			eggColor1 =  (224 << 16) + (224 << 8) + 224;						eggColor2 = (28 << 16) + (21 << 8) + 17;
			defaultBiomesToSpawn.add(BiomeGenBase.extremeHills.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.extremeHillsEdge.biomeName);
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.chickenRaw), 10);
			customMobData.addLootToMob(new ItemStack(Item.feather), 8);
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.SmallHeart.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.Talon.meta()), 4);
			}
			CustomEntityList.instance.eagle = Optional.of(customMobData);	
		}
	},
	HornBill{
		@Override
		public void loadDefaultProperties() {
			mobName = "Horn Bill";
			mobClass = EntityHornBill.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 10;
			secondarySpawnRate = 25;
			minInChunk = 1;
			maxInChunk = 1;
			enumCreatureType = EnumCreatureType.monster;
			sendsVelocityUpdates = true;
			
			eggColor1 =  (26 << 16) + (19 << 8) + 15;						eggColor2 = (199 << 16) + (33 << 8) + 14;
			defaultBiomesToSpawn.add(BiomeGenBase.jungle.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.jungleHills.biomeName); 			
			defaultBiomesToSpawn.add("Mini Jungle");						defaultBiomesToSpawn.add("Extreme Jungle");
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.chickenRaw), 10);
			customMobData.addLootToMob(new ItemStack(Item.feather), 8);
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.SmallHeart.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.Talon.meta()), 4);
			}
			CustomEntityList.instance.hornBill = Optional.of(customMobData);	
		}
	},
	Pelican{
		@Override
		public void loadDefaultProperties() {
			mobName = "Pelican";
			mobClass = EntityPelican.class;
			trackingRange = 128;
			updateFrequency = 3;
			sendsVelocityUpdates = true;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 7;
			secondarySpawnRate = 5;
			minInChunk = 1;
			maxInChunk = 1;
			enumCreatureType = EnumCreatureType.monster;
			
			eggColor1 =  (214 << 16) + (214 << 8) + 214;					eggColor2 = (168 << 16) + (62 << 8) + 10;
			defaultBiomesToSpawn.add(BiomeGenBase.river.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.beach.biomeName);
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.chickenRaw), 10);
			customMobData.addLootToMob(new ItemStack(Item.feather), 8);
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.Talon.meta()), 4);
			}
			CustomEntityList.instance.pelican = Optional.of(customMobData);	
		}
	},
	Minotaur{
		@Override
		public void loadDefaultProperties() {
			mobName = "Minotaur";
			mobClass = EntityMinotaur.class;
			trackingRange = 128;
			updateFrequency = 3;
			sendsVelocityUpdates = true;
			shouldSpawn = false;			shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;
			eggColor1 = (51 << 16) + (34 << 8) + 8;			eggColor2 = (255 << 16) + (255 << 8) + 255;
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			if(ItemBlockList.furPelt.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.furPelt.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.Ectoplasm.meta()), 4);
			}
			CustomEntityList.instance.minotaur = Optional.of(customMobData);	
		}
	},
	HauntedArmor{
		@Override
		public void loadDefaultProperties() {
			mobName = "Haunted Armor";
			mobClass = EntityHauntedArmor.class;
			trackingRange = 128;
			updateFrequency = 3;
			sendsVelocityUpdates = true;
			shouldSpawn = false;			shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;			
			eggColor1 = (194 << 16) + (194 << 8) + 194;		eggColor2 = (251 << 16) + (246 << 8) + 36;
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.Ectoplasm.meta()), 4);
			}
			CustomEntityList.instance.hauntedArmor = Optional.of(customMobData);	
		}
	},
	Centipede{
		@Override
		public void loadDefaultProperties() {
			mobName = "Centipede";
			mobClass = EntityCentipede.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = true;
			shouldHaveEgg = true;			allowConfigOptions = true;			
			
			spawnRate = 1;
			secondarySpawnRate = 35;
			minInChunk = 1;
			maxInChunk = 1;
			enumCreatureType = EnumCreatureType.monster;
			sendsVelocityUpdates = true;
			
			eggColor1 = (77 << 16) + (22 << 8) + 17;						eggColor2 = (212 << 16) + (97 << 8) + 38;
			defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.extremeHills.biomeName);
			defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.taiga.biomeName);
			defaultBiomesToSpawn.add(BiomeGenBase.swampland.biomeName); 	defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);
			defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");
			defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");
			defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");
			defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");
			defaultBiomesToSpawn.add("Woodlands");
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 15); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.Antennae.meta()), 1);
			}
			CustomEntityList.instance.centipede = Optional.of(customMobData);	
		}
	},
	Follower{
		@Override
		public void loadDefaultProperties() {
			mobName = "Follower";
			mobClass = EntityFollower.class;
			trackingRange = 128;
			updateFrequency = 3;
			sendsVelocityUpdates = true;
			shouldSpawn = false;			shouldExist = true;
			shouldHaveEgg = false;			allowConfigOptions = false;			
		}
		@Override
		public void outputDataToList() {}
	},
	HorseRandom{
		@Override
		public void loadDefaultProperties() {
			mobName = "HorseRandom";
			mobClass = EntityHorseRandom.class;
			trackingRange = 128;
			updateFrequency = 3;
			shouldSpawn = true;				shouldExist = false;
			shouldHaveEgg = true;			allowConfigOptions = true;
			
			spawnRate = 5;
			secondarySpawnRate = 100;
			minInChunk = 3;
			maxInChunk = 4;
			enumCreatureType = EnumCreatureType.creature;
			sendsVelocityUpdates = true;
			
			eggColor1 =  (200 << 16) + (245 << 8) + 245;					eggColor2 = (51 << 16) + (51 << 8) + 51;
			defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 		
			defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName); 		
			defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");
			defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");
			defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");
			defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");
			defaultBiomesToSpawn.add("Woodlands");
		}
		@Override
		public void outputDataToList() {
			CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
			customMobData.addLootToMob(new ItemStack(Item.beefRaw), 10);
			if(ItemBlockList.scrapMeat.isPresent()){ customMobData.addLootToMob(new ItemStack(ItemBlockList.scrapMeat.get()), 10); }
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				customMobData.addLootToMob(new ItemStack(ItemBlockList.genericCraftingItems1.get().shiftedIndex, 1, ItemGenerics.Properties.LargeHeart.meta()), 4);
			}
			CustomEntityList.instance.horseRandom = Optional.of(customMobData);	
		}
	},
	;
	
	
	/* Name of the Entity */
	protected String mobName;
	/* Class this Entity Belongs To */
	protected Class mobClass;
	/* Determines Whether the Entity Spawns in the World or is Declared */
	protected boolean shouldSpawn = true;
	protected boolean shouldExist = true;
	protected boolean shouldHaveEgg = true;
	protected boolean allowConfigOptions = true;
	
	protected boolean reportSpawningInLog = false;

	protected int trackingRange = 80;
	protected int updateFrequency = 3;
	protected boolean sendsVelocityUpdates = true;
	protected int spawnRate = 0;
	protected int secondarySpawnRate = 0;
	protected int eggColor1 = 0;
	protected int eggColor2 = 0;
	protected int minInChunk = 1;
	protected int maxInChunk = 1;
	protected EnumCreatureType enumCreatureType;
	protected ArrayList<BiomeGenBase> biomesToSpawn = new ArrayList();
	protected ArrayList<String> defaultBiomesToSpawn = new ArrayList();
	
	public abstract void loadDefaultProperties();
	/* Used To Mark Entity Present in List and Store Extra Data such as Secondary Spawn Rate for Access Elsewhere (If Desired) */
	public abstract void outputDataToList();

	public static void loadSettings(File configDirectory){
		/* Load Individual Mob Properties and Store them in the Mob General Config */
		Configuration mobConfig = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		mobConfig.load();
		
		/* Load Configuration Settings */
		for (final CustomEntityManager mob : CustomEntityManager.values()) {
			
			/* Load Default Properties */
			mob.loadDefaultProperties();
			
			if(!mob.allowConfigOptions){
				continue;
			}
			/* Load Variables that are User Configurable, only on Entities that ShouldSpawn by default.
			 *  Non-User Spawnable entities, like Lizard Spit or Pharoah, should be Skipped */
			mob.shouldExist = mobConfig.get("MOB CONTROLS."+mob.mobName, mob.mobName.toLowerCase()+" shouldExist", mob.shouldExist).getBoolean(mob.shouldExist);
			mob.reportSpawningInLog = mobConfig.get("MOB CONTROLS."+mob.mobName, mob.mobName.toLowerCase()+" reportSpawningInLog", mob.reportSpawningInLog).getBoolean(mob.reportSpawningInLog);
			mob.updateFrequency = mobConfig.get("MOB CONTROLS."+mob.mobName, mob.mobName.toLowerCase()+" UpdateFrequency", mob.updateFrequency).getInt(mob.updateFrequency);
			
			if(mob.shouldSpawn || mob.shouldHaveEgg){
				mob.eggColor1 = mobConfig.get("MOB CONTROLS."+mob.mobName, mob.mobName.toLowerCase()+" EggColor1", mob.eggColor1).getInt(mob.eggColor1);
				mob.eggColor2 = mobConfig.get("MOB CONTROLS."+mob.mobName, mob.mobName.toLowerCase()+" EggColor2", mob.eggColor2).getInt(mob.eggColor2);
			}
			
			if(mob.shouldSpawn){
				mob.spawnRate = mobConfig.get("MOB CONTROLS."+mob.mobName, mob.mobName.toLowerCase()+" SpawnRate", mob.spawnRate).getInt(mob.spawnRate);
				mob.secondarySpawnRate = mobConfig.get("MOB CONTROLS."+mob.mobName, mob.mobName.toLowerCase()+" SecondarySpawnRate",mob.secondarySpawnRate).getInt(mob.secondarySpawnRate);
				mob.minInChunk = mobConfig.get("MOB CONTROLS."+mob.mobName, mob.mobName.toLowerCase()+" minInChunk", mob.minInChunk).getInt(mob.minInChunk);
				mob.maxInChunk = mobConfig.get("MOB CONTROLS."+mob.mobName, mob.mobName.toLowerCase()+" maxInChunk", mob.maxInChunk).getInt(mob.maxInChunk);
			}
			if(mob.shouldExist){
				mob.outputDataToList();
			}
		}
		mobConfig.save();
		
		/* Load Individual Mob Biome Properties and Store them in the Mob Spaw Config */
		Configuration biomeConfig = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		biomeConfig.load();
		for (final CustomEntityManager mob : CustomEntityManager.values()) {
			/* If mob shouldn't spawn, it doesn't need to be in Biome Config */
			if(!mob.shouldSpawn){
				continue;
			}
			for (int i = 0; i < BiomeGenBase.biomeList.length; i++) {
				if(BiomeGenBase.biomeList[i] == null){
					continue;
				}
				if(biomeConfig.get("Mob Spawn Biome Controls."+mob.mobName, mob.mobName.toLowerCase()+" in "+BiomeGenBase.biomeList[i].biomeName, mob.defaultBiomesToSpawn.contains(BiomeGenBase.biomeList[i].biomeName)).getBoolean(false)){
					mob.biomesToSpawn.add(BiomeGenBase.biomeList[i]);
				}
			}
		}
		biomeConfig.save();
		
	}
	
	public static void setupMobs(){
		for (final CustomEntityManager mob : CustomEntityManager.values()) {
			if(mob.shouldExist){
				EntityRegistry.registerModEntity(mob.mobClass,  mob.mobName, ProjectZulu_Mobs.getNextDefaultMobID(),
						ProjectZulu_Core.modInstance, mob.trackingRange, mob.updateFrequency, true);
				LanguageRegistry.instance().addStringLocalization("entity.".concat(DefaultProps.CoreModId).concat(".").concat(mob.mobName).concat(".name"), "en_US", mob.mobName);

				/* if Should Exist, should not Have Egg and Not Spawn (i.e. Lizard Spit) Skip to Next Mob */
				if(!mob.shouldHaveEgg){
					continue;
				}
				
				int eggID = ProjectZulu_Mobs.getNextDefaultEggID();
				while(EntityList.IDtoClassMapping.containsKey(eggID)){ eggID = ProjectZulu_Mobs.getNextDefaultEggID(); }
				EntityList.IDtoClassMapping.put(eggID, mob.mobClass); 
				EntityList.entityEggs.put(eggID, new EntityEggInfo(eggID, mob.eggColor1, mob.eggColor2 ));
				
				/* If Should Exist and Not Spawn (i.e. Lizard Spit) Skip to Next Mob */
				if(!mob.shouldSpawn){
					continue;
				}
				for (int i = 0; i < mob.biomesToSpawn.size(); i++){
					EntityRegistry.addSpawn(mob.mobClass, mob.spawnRate, mob.minInChunk, mob.maxInChunk, mob.enumCreatureType, mob.biomesToSpawn.get(i));
				}
			}
		}
	}
}
