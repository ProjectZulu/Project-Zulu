package projectzulu.common.blocks;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import net.minecraftforge.oredict.OreDictionary;
import projectzulu.common.ProjectZulu_Blocks;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.BlockList;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.heads.BlockMobHeads;
import projectzulu.common.blocks.heads.ItemMobHeads;
import projectzulu.common.blocks.heads.TileEntityMobHeads;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.ProjectZuluLog;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * Inspired by BlockManager Class from ExtrabiomesXL by : See Github for Original Source: https://github.com/ExtrabiomesXL
 */
//TODO: Split Item and Blocks into Seperate Manager Files. Will be More imortant as more blocks/items are addeded. 
public enum ItemBlockManager {
	AloeVera{
		@Override
		protected boolean isBlock() { return true;	}
		
		@Override
		protected void create(){
			BlockList.aloeVera = Optional.of(
					(new BlockAloeVera(blockID, 0)).setHardness(0.0f).setStepSound(Block.soundGrassFootstep).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()) );
		}

		@Override
		protected void register(){
			Block block = BlockList.aloeVera.get();
			GameRegistry.registerBlock(block, ItemAloeVera.class, this.toString().toLowerCase());
			LanguageRegistry.instance().addStringLocalization("tile.Aloe Vera.base_1.name", "Aloe Vera Plant");
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}

	},
	WateredDirt{
		@Override
		protected boolean isBlock() { return true;	}

		@Override
		protected void create() {
			BlockList.wateredDirt = Optional.of((new BlockWateredDirt(blockID, 8)).setHardness(0.5f).setResistance(1.0f).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
		}

		@Override
		protected void register() {
			Block block = BlockList.wateredDirt.get();
			GameRegistry.registerBlock(block, ItemWateredDirt.class, this.toString().toLowerCase());
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}

	},
	Tumblweed{
		@Override
		protected boolean isBlock() { return true;	}

		@Override
		protected void create() {
			BlockList.tumbleweed = Optional.of(
					(new BlockTumbleweed(blockID, 8)).setHardness(0.7F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
		}

		@Override
		protected void register() {
			Block block = BlockList.tumbleweed.get();
			GameRegistry.registerBlock(block, this.toString().toLowerCase()); LanguageRegistry.addName(block, "Tumbleweed");			
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}

	},
	Jasper{
		@Override
		protected boolean isBlock() { return true;	}

		@Override
		protected void create() {
			BlockList.jasper = Optional.of((new BlockJasper(blockID, 32)).setHardness(1.0f).setResistance(1.0f).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
		}

		@Override
		protected void register() {
			Block block = BlockList.jasper.get();
			GameRegistry.registerBlock(block, this.toString().toLowerCase()); LanguageRegistry.addName(block, "Jasper Block");
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	PalmTreeLog{
		@Override
		protected boolean isBlock() { return true;	}

		@Override
		protected void create() {
			BlockList.palmTreeLog = Optional.of(
					new BlockPalmTreeLog(blockID, 17).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
		}

		@Override
		protected void register() {
			Block block = BlockList.palmTreeLog.get();
			GameRegistry.registerBlock(block, this.toString().toLowerCase()); LanguageRegistry.addName(block, "Palm Tree Log");
			OreDictionary.registerOre("log", new ItemStack(block));
			OreDictionary.registerOre("logWood", new ItemStack(block));
			OreDictionary.registerOre("logPalm", new ItemStack(block));
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}

	},
	PalmTreePlank{
		@Override
		protected boolean isBlock() { return true;	}

		@Override
		protected void create() {
			BlockList.palmTreePlank = Optional.of(
					new BlockPalmTreePlank(blockID, 18).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));			
		}

		@Override
		protected void register() {
			Block block = BlockList.palmTreePlank.get();
			GameRegistry.registerBlock(block, this.toString().toLowerCase()); LanguageRegistry.addName(block, "Palm Tree Plank");
			OreDictionary.registerOre("plankWood", new ItemStack(block));
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}

	},
	PalmTreeDoubleSlab{
		@Override
		protected boolean isBlock() { return true;	}


		@Override
		protected void create() {
			BlockList.palmTreeDoubleSlab = Optional.of(
					(BlockZuluHalfSlab)new BlockZuluHalfStep(blockID, true).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
		}

		@Override
		protected void register() {
			Block block = BlockList.palmTreeDoubleSlab.get();
			GameRegistry.registerBlock(block, this.toString().toLowerCase()); LanguageRegistry.addName(block, "Palm Double Slab");
			OreDictionary.registerOre("slabWood", new ItemStack(block));
			OreDictionary.registerOre("slabPalm", new ItemStack(block));
		}


		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	PalmTreeSlab{
		@Override
		protected boolean isBlock() { return true;	}

		@Override
		protected void create() {
			BlockList.palmTreeSlab = Optional.of(
					(BlockZuluHalfSlab)(new BlockZuluHalfStep(blockID, false)).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setLightOpacity(0).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
		}

		@Override
		protected void register() {
			Block block = BlockList.palmTreeSlab.get();
			GameRegistry.registerBlock(block, this.toString().toLowerCase()); LanguageRegistry.addName(block, "Palm Single Slab");
			OreDictionary.registerOre("slabWood", new ItemStack(block));
			OreDictionary.registerOre("slabPalm", new ItemStack(block));
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	PalmTreeStairs{
		@Override
		protected boolean isBlock() { return true;	}

		@Override
		protected void create() {
			if(BlockList.palmTreePlank.isPresent()){
				BlockList.palmTreeStairs = Optional.of(
						new BlockZuluStairs(blockID, BlockList.palmTreePlank.get(), 0).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()).setLightOpacity(0));
			}else{
				ProjectZuluLog.warning("Palm Tree Planks were not Enabled. Disabling Palm Tree Stairs. You have done bad, and You should feel bad.");
			}
		}

		@Override
		protected void register() {
			if(BlockList.palmTreeStairs.isPresent()){
				Block block = BlockList.palmTreeStairs.get();
				GameRegistry.registerBlock(block, this.toString().toLowerCase()); LanguageRegistry.addName(block, "Palm Tree Stairs");
				OreDictionary.registerOre("stairsWood", new ItemStack(block));
				OreDictionary.registerOre("stairsPalm", new ItemStack(block));
			}
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	PalmTreeLeaves{
		@Override
		protected boolean isBlock() { return true;	}

		@Override
		protected void create() {
			BlockList.palmTreeLeaves = Optional.of(
					(BlockPalmTreeLeaves)(new BlockPalmTreeLeaves(blockID, 19)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
		}

		@Override
		protected void register() {
			Block block = BlockList.palmTreeLeaves.get();
			GameRegistry.registerBlock(block, this.toString().toLowerCase()); LanguageRegistry.addName(block, "Palm Tree Leaves");
			OreDictionary.registerOre("leaves", new ItemStack(block));
			OreDictionary.registerOre("leavesPalm", new ItemStack(block));
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}

	},
	PalmTreeSapling{
		@Override
		protected boolean isBlock() { return true;	}

		@Override
		protected void create() {
			BlockList.palmTreeSapling = Optional.of(
					new BlockPalmTreeSapling(blockID, 16).setUnlocalizedName(DefaultProps.blockKey+DefaultProps.blockKey+toString().toLowerCase()));
		}

		@Override
		protected void register() {
			Block block = BlockList.palmTreeSapling.get();
			GameRegistry.registerBlock(block, this.toString().toLowerCase()); LanguageRegistry.addName(block, "Palm Tree Sapling");
			OreDictionary.registerOre("sapling", new ItemStack(block));
			OreDictionary.registerOre("saplingPalm", new ItemStack(block));
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	Coconut{
		@Override
		protected boolean isBlock() { return true;	}

		@Override
		protected void create() {
			BlockList.coconut = Optional.of(
					new BlockCoconut(blockID).setHardness(0.2F).setResistance(5.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
		}

		@Override
		protected void register() {
			Block block = BlockList.coconut.get();
			GameRegistry.registerBlock(block, this.toString().toLowerCase()); LanguageRegistry.addName(block, "Coconut");
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	QuickSand{
		@Override
		protected boolean isBlock() { return true;	}

		@Override
		protected void create() {
			BlockList.quickSand = Optional.of(
					new BlockQuickSand(blockID, 112).setHardness(1.2F).setStepSound(Block.soundSandFootstep).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
		}

		@Override
		protected void register() {
			Block block = BlockList.quickSand.get();
			GameRegistry.registerBlock(block, this.toString().toLowerCase()); LanguageRegistry.addName(block, "QuickSand");
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	NightBloom{
		@Override
		protected boolean isBlock() { return true;	}

		@Override
		protected void create() {
			BlockList.nightBloom = Optional.of(
					new BlockNightBloom(blockID, 96).setHardness(0.5F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
		}

		@Override
		protected void register() {
			Block block = BlockList.nightBloom.get();
			GameRegistry.registerBlock(block, this.toString().toLowerCase()); LanguageRegistry.addName(block, "NightBloom");
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	CreeperBlossom{
		@Override
		protected boolean isBlock() { return true;	}

		@Override
		protected void create() {
			BlockList.creeperBlossom = Optional.of(
					new BlockCreeperBlossom(blockID, 96).setHardness(0.5F).setStepSound(Block.soundGrassFootstep).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
			}

		@Override
		protected void register() {
			Block block = BlockList.creeperBlossom.get();
			GameRegistry.registerBlock(block, this.toString().toLowerCase()); LanguageRegistry.addName(block, "Creeper Blosson");
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	Spikes{
		@Override
		protected boolean isBlock() { return true;	}

		@Override
		protected void create() {
			BlockList.spike = Optional.of(
					 new BlockSpikes(blockID).setHardness(0.5F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
			}

		@Override
		protected void register() {
			Block block = BlockList.spike.get();
			GameRegistry.registerBlock(block, this.toString().toLowerCase()); LanguageRegistry.addName(block, "Ivory Spikes");
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	Campire{
		@Override
		protected boolean isBlock() { return true;	}
		
		@Override
		protected void create() {
			BlockList.campfire = Optional.of(
					new BlockCampfire(blockID).setHardness(0.5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
		}
		
		@Override
		protected void register() {
			Block block = BlockList.campfire.get();
			GameRegistry.registerBlock(block, ItemCampFire.class, this.toString().toLowerCase()); LanguageRegistry.addName(block, "Campfire");
			LanguageRegistry.instance().addStringLocalization("tile.campfire.base_1.name", "Log Campfire");
			LanguageRegistry.instance().addStringLocalization("tile.campfire.base_2.name", "Stone Campfire");
			LanguageRegistry.instance().addStringLocalization("tile.campfire.base_3.name", "Lit Log Campfire");
			LanguageRegistry.instance().addStringLocalization("tile.campfire.base_4.name", "Lit Stone Campfire");
			

		}
		
		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	MobSkulls{
		@Override
		protected boolean isBlock() { return true;	}
		
		@Override
		protected void create() {
			BlockList.mobHeads = Optional.of(
					new BlockMobHeads(blockID, 1, TileEntityMobHeads.class).setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
		}
		
		@Override
		protected void register() {
			Block block = BlockList.mobHeads.get();
			GameRegistry.registerBlock(block, ItemMobHeads.class, this.toString().toLowerCase()); LanguageRegistry.addName(block, "mobHeads");
			LanguageRegistry.instance().addStringLocalization("tile.mobHeads.base_0.name", "Stuffed Finch");
			LanguageRegistry.instance().addStringLocalization("tile.mobHeads.base_1.name", "Alligator Head");
			LanguageRegistry.instance().addStringLocalization("tile.mobHeads.base_2.name", "Armadillo Head");
			LanguageRegistry.instance().addStringLocalization("tile.mobHeads.base_3.name", "Black Bear Head");
			LanguageRegistry.instance().addStringLocalization("tile.mobHeads.base_4.name", "Brown Bear Head");
			LanguageRegistry.instance().addStringLocalization("tile.mobHeads.base_5.name", "Polar Bear Head");

			LanguageRegistry.instance().addStringLocalization("tile.mobHeads.base_6.name", "Beaver Head");
			LanguageRegistry.instance().addStringLocalization("tile.mobHeads.base_7.name", "Boar Head");
			LanguageRegistry.instance().addStringLocalization("tile.mobHeads.base_8.name", "Giraffe Head");
			LanguageRegistry.instance().addStringLocalization("tile.mobHeads.base_9.name", "Gorilla Head");
			LanguageRegistry.instance().addStringLocalization("tile.mobHeads.base_10.name", "Lizard Head");
			LanguageRegistry.instance().addStringLocalization("tile.mobHeads.base_11.name", "Mammoth Head");
			
			LanguageRegistry.instance().addStringLocalization("tile.mobHeads.base_12.name", "Ostrich Head");
			LanguageRegistry.instance().addStringLocalization("tile.mobHeads.base_13.name", "Penguin Head");
			LanguageRegistry.instance().addStringLocalization("tile.mobHeads.base_14.name", "Rhino Head");
			LanguageRegistry.instance().addStringLocalization("tile.mobHeads.base_15.name", "TreeEnt Head");
			LanguageRegistry.instance().addStringLocalization("tile.mobHeads.base_16.name", "Vulture Head");
			LanguageRegistry.instance().addStringLocalization("tile.mobHeads.base_17.name", "Elephant Head");
			
	        GameRegistry.registerTileEntity(TileEntityMobHeads.class, "TileEntityMobHead");   
			ProjectZulu_Core.proxy.registerTileEntitySpecialRender(TileEntityMobHeads.class, "projectzulu.common.blocks.heads.TileEntityMobHeadsRenderer");
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	Tombstone{
		@Override
		protected boolean isBlock() { return true;	}
		
		@Override
		protected void create() {
			BlockList.tombstone = Optional.of(
					new BlockTombstone(blockID, TileEntityTombstone.class).setHardness(0.5F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
		}
		
		@Override
		protected void register() {
			Block block = BlockList.tombstone.get();
			GameRegistry.registerBlock(block, this.toString().toLowerCase()); LanguageRegistry.addName(block, "Tombstone");
			/* Register TileEntity Render */
			Configuration tempConfig = new Configuration(  new File(ProjectZulu_Core.modConfigDirectoryFile, DefaultProps.configDirectory + DefaultProps.tempConfigFile));
			tempConfig.load();
			Property property = tempConfig.get("TempSettings.Tombstone", "useAlterantiveTileEntityName", false);
			if(!property.getBoolean(false)){
				try{
			        GameRegistry.registerTileEntity(TileEntityTombstone.class, "TileEntityTombstone");
				}catch (IllegalArgumentException e){
			        GameRegistry.registerTileEntity(TileEntityTombstone.class, "PZTileEntityTombstone");
				}
			}else{
		        GameRegistry.registerTileEntity(TileEntityTombstone.class, "PZTileEntityTombstone");
				property.set(true);
			}
			tempConfig.save();
			ProjectZulu_Core.proxy.registerTileEntitySpecialRender(TileEntityTombstone.class, "projectzulu.common.blocks.TileEntityTombstoneRenderer");			
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	UniversalFlowerPot{
		@Override
		protected boolean isBlock() { return true;	}
		
		@Override
		protected void create() {
			if(ProjectZulu_Core.replaceFlowerPot){
				Block.blocksList[Block.flowerPot.blockID] = null;
				BlockList.universalFlowerPot = Optional.of(
						new BlockUniversalFlowerPot(Block.flowerPot.blockID).setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("Flower Pot")); //TODO: Needs to be Identical to Vanilla? Flower Pot Name?
			}else{
				BlockList.universalFlowerPot = Optional.of(
						new BlockUniversalFlowerPot(blockID).setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab).setUnlocalizedName(DefaultProps.blockKey+"Universal Flower Pot"));
			}
		}
		
		@Override
		protected void register() {
			if(!ProjectZulu_Core.replaceFlowerPot){
				Block block = BlockList.universalFlowerPot.get();
				GameRegistry.registerBlock(block, ItemUniversalFlowerPot.class, this.toString().toLowerCase()); LanguageRegistry.addName(block, "Universal Flower Pot");			
			}
	        GameRegistry.registerTileEntity(TileEntityUniversalFlowerPot.class, "TileEntityUniversalFlowerPot");   
			ProjectZulu_Core.proxy.registerTileEntitySpecialRender(TileEntityUniversalFlowerPot.class, "projectzulu.common.blocks.TileEntityUniversalFlowerPotRenderer");

		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	Ankh{
		@Override
		protected boolean isBlock() { return false;	}
		
		@Override
		protected void create() {
			ItemList.ankh = Optional.of(
					new ItemAnkh(blockID).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
			}
		
		@Override
		protected void register() {
			Item item = ItemList.ankh.get();
			LanguageRegistry.addName(item, "Ankh");
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	AloeVeraSeeds{
		@Override
		protected boolean isBlock() { return false;	}
		
		@Override
		protected void create() {
			if(BlockList.aloeVera.isPresent()){
				ItemList.aloeVeraSeeds = Optional.of(
						new ItemAloeVeraSeeds(blockID, BlockList.aloeVera.get().blockID).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
				}
			}
		
		@Override
		protected void register() {
			if(BlockList.aloeVera.isPresent()){
				Item item = ItemList.aloeVeraSeeds.get();
				LanguageRegistry.addName(item, "Aloe Vera Seeds");
			}
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	WaterDroplets{
		@Override
		protected boolean isBlock() { return false;	}
		
		@Override
		protected void create() {
			ItemList.waterDroplets = Optional.of(
					new ItemFoodProjectZulu(blockID, 1, 0.6f, false, 142).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
			}
		
		@Override
		protected void register() {
			Item item = ItemList.waterDroplets.get();
			LanguageRegistry.addName(item, "Water Droplets");
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	CoconutMilkFragment{
		@Override
		protected boolean isBlock() { return false;	}
		
		@Override
		protected void create() {
			ItemList.coconutMilkFragment = Optional.of(
					new ItemFoodProjectZulu(blockID, 2, 2.4f, false, 8).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
			}
		
		@Override
		protected void register() {
			Item item = ItemList.coconutMilkFragment.get();
			LanguageRegistry.addName(item, "Coconut Milk Fragment");
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	CoconutItem{
		@Override
		protected boolean isBlock() { return false;	}
		
		@Override
		protected void create() {
			ItemList.coconutItem = Optional.of(
					new ItemCoconutItem(blockID, 4, false).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
			}
		
		@Override
		protected void register() {
			Item item = ItemList.coconutItem.get();
			LanguageRegistry.addName(item, "Whole Coconut");
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	CoconutSeed{
		@Override
		protected boolean isBlock() { return false;	}
		
		@Override
		protected void create() {
			ItemList.coconutSeed = Optional.of(
					new ItemCoconutSeed(blockID, 6, false).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
			}
		
		@Override
		protected void register() {
			Item item = ItemList.coconutSeed.get();
			LanguageRegistry.addName(item, "Coconut Seed");
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	CoconutShell{
		@Override
		protected boolean isBlock() { return false;	}
		
		@Override
		protected void create() {
			ItemList.coconutShell = Optional.of(
					new ItemCoconutShell(blockID, 7, false).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
			}
		
		@Override
		protected void register() {
			Item item = ItemList.coconutShell.get();
			LanguageRegistry.addName(item, "Coconut Shell");
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	ScaleItem{
		@Override
		protected boolean isBlock() { return false;	}
		
		@Override
		protected void create() {
			ItemList.scaleItem = Optional.of(
					new ItemScale(blockID, 1, false).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
			}
		
		@Override
		protected void register() {
			Item item = ItemList.scaleItem.get();
			LanguageRegistry.addName(item, "Scale");
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	FurPelt{
		@Override
		protected boolean isBlock() { return false;	}
		
		@Override
		protected void create() {
			ItemList.furPelt = Optional.of(
					new ItemScale(blockID, 23, false).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
			}
		
		@Override
		protected void register() {
			Item item = ItemList.furPelt.get();
			LanguageRegistry.addName(item, "Fur Pelt");
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	ScrapMeat{
		@Override
		protected boolean isBlock() { return false;	}
		
		@Override
		protected void create() {
			ItemList.scrapMeat = Optional.of(
					new ItemFoodProjectZulu(blockID, 1, 1.0f, false, 11).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
			}
		
		@Override
		protected void register() {
			Item item = ItemList.scrapMeat.get();
			LanguageRegistry.addName(item, "Scrap Meat");
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	GenericCraftingItems1{
		@Override
		protected boolean isBlock() { return false;	}
		
		@Override
		protected void create() {
			ItemList.genericCraftingItems1 = Optional.of(
					new ItemGenerics(blockID, 10).setUnlocalizedName(DefaultProps.blockKey+"genericItems"));
		}
		
		@Override
		protected void register() {
			Item item = ItemList.genericCraftingItems1.get();	        
			for (ItemGenerics.Properties property : ItemGenerics.Properties.values()) {
				LanguageRegistry.addName(new ItemStack(item, 1, property.meta()), property.getDisplayName());
			}
			OreDictionary.registerOre("foodSalt", new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.Salt.meta()));
		}
		
		@Override
		protected void loadCustomConfig(Configuration config) {}
	},
	StructurePlacer{
		@Override
		protected boolean isBlock() { return false;	}
		
		@Override
		protected void create() {
			ItemList.structurePlacer = Optional.of(
					new ItemStructurePlacer(blockID).setUnlocalizedName(DefaultProps.blockKey+toString().toLowerCase()));
		}
		
		@Override
		protected void register() {
			Item item = ItemList.structurePlacer.get();
	        LanguageRegistry.addName(new ItemStack(item,1,0), "Oasis Structure Placer");
	        LanguageRegistry.addName(new ItemStack(item,1,1), "Pyramid Structure Placer");
	        LanguageRegistry.addName(new ItemStack(item,1,2), "Labyrinth Structure Placer");
	        LanguageRegistry.addName(new ItemStack(item,1,3), "Cemetary Structure Placer");
		}

		@Override
		protected void loadCustomConfig(Configuration config) {}
	}
	;

	protected int blockID;
	private boolean	blockCreated = false;

	public static void preInit(Configuration config) 
			throws Exception{
		loadSettings(config);
		createBlocks();
	}

	private static void loadSettings(Configuration config){
		
		/* Load Configuration Settings */
		for (final ItemBlockManager block : ItemBlockManager.values()) {
			block.blockID = block.isBlock() ? 
					config.getBlock(block.toString()+" ID", ProjectZulu_Blocks.getNextDefaultBlockID()).getInt() 
					: config.getItem(block.toString()+" ID", ProjectZulu_Blocks.getNextDefaultItemID()).getInt();
			block.loadCustomConfig(config);
		}
	}
	protected abstract boolean isBlock();
	protected abstract void loadCustomConfig(Configuration config);

	private static void createBlocks() throws Exception {
		for (final ItemBlockManager block : ItemBlockManager.values())
			if (block.blockID > 0) {
				try {
					block.create();
				} catch (final Exception e) {
					throw e;
				}
				block.blockCreated = true;
			}
	}
	
	protected abstract void create();
	
	public static void init() throws InstantiationException,
	IllegalAccessException{
		for (final ItemBlockManager block : values())
			if (block.blockCreated) block.register();
	}
	protected abstract void register();	

}
