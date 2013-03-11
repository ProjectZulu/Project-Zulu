package projectzulu.common.blocks;

import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import projectzulu.common.ProjectZulu_Blocks;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.DefaultProps;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * Inspired by BlockManager Class from ExtrabiomesXL . See Github for Original Source: https://github.com/ExtrabiomesXL
 */
public enum ArmorManager {
	ScaleHelmet{
		@Override
		protected void create(){
			ItemList.scaleArmorHead = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.scaleIndex,0)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));
		}

		@Override
		protected void register(){
			Item item = ItemList.scaleArmorHead.get();
			LanguageRegistry.addName(item, "Scale Helmet"); 
		}
	},
	ScaleChest{

		@Override
		protected void create(){
			ItemList.scaleArmorChest = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.scaleIndex,1)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.scaleArmorChest.get();
			LanguageRegistry.addName(item, "Scale Chestplate"); 
		}
	},
	ScaleLegs{
		@Override
		protected void create(){
			ItemList.scaleArmorLeg = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.scaleIndex,2)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.scaleArmorLeg.get();
			LanguageRegistry.addName(item, "Scale Leggings"); 
		}
	},
	ScaleBoots{
		@Override
		protected void create(){
			ItemList.scaleArmorBoots = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.scaleIndex,3)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.scaleArmorBoots.get();
			LanguageRegistry.addName(item, "Scale Boots"); 
		}
	},
	GoldScaleHelmet{
		@Override
		protected void create(){
			ItemList.goldScaleArmorHead = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.goldScaleMaterial, ProjectZulu_Blocks.goldScaleIndex, 0)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));
		}

		@Override
		protected void register(){
			Item item = ItemList.goldScaleArmorHead.get();
			LanguageRegistry.addName(item, "Gold Scale Helmet"); 
		}
	},
	GoldScaleChest{
		@Override
		protected void create(){
			ItemList.goldScaleArmorChest = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.goldScaleMaterial, ProjectZulu_Blocks.goldScaleIndex, 1)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.goldScaleArmorChest.get();
			LanguageRegistry.addName(item, "Gold Scale Chestplate"); 
		}
	},
	GoldScaleLegs{
		@Override
		protected void create(){
			ItemList.goldScaleArmorLeg = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.goldScaleMaterial, ProjectZulu_Blocks.goldScaleIndex, 2)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.goldScaleArmorLeg.get();
			LanguageRegistry.addName(item, "Gold Scale Leggings"); 
		}
	},
	GoldScaleBoots{
		@Override
		protected void create(){
			ItemList.goldScaleArmorBoots = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.goldScaleMaterial, ProjectZulu_Blocks.goldScaleIndex, 3)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.goldScaleArmorBoots.get();
			LanguageRegistry.addName(item, "Gold Scale Boots"); 
		}
	},
	IronScaleHelmet{
		@Override
		protected void create(){
			ItemList.ironScaleArmorHead = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.ironScaleMaterial, ProjectZulu_Blocks.ironScaleIndex,0)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));
		}

		@Override
		protected void register(){
			Item item = ItemList.ironScaleArmorHead.get();
			LanguageRegistry.addName(item, "Iron Plated Scale Helmet"); 
		}
	},
	IronScaleChest{
		@Override
		protected void create(){
			ItemList.ironScaleArmorChest = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.ironScaleMaterial, ProjectZulu_Blocks.ironScaleIndex,1)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.ironScaleArmorChest.get();
			LanguageRegistry.addName(item, "Iron Plated Scale Chestplate"); 
		}
	},
	IronScaleLegs{
		@Override
		protected void create(){
			ItemList.ironScaleArmorLeg = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.ironScaleMaterial, ProjectZulu_Blocks.ironScaleIndex,2)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.ironScaleArmorLeg.get();
			LanguageRegistry.addName(item, "Iron Plated Scale Leggings"); 
		}
	},
	IronScaleBoots{
		@Override
		protected void create(){
			ItemList.ironScaleArmorBoots = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.ironScaleMaterial, ProjectZulu_Blocks.ironScaleIndex,3)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.ironScaleArmorBoots.get();
			LanguageRegistry.addName(item, "Iron Plated Scale Boots"); 
		}
	},
	DiamondScaleHelmet{
		@Override
		protected void create(){
			ItemList.diamondScaleArmorHead = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.diamondScaleMaterial, ProjectZulu_Blocks.diamondScaleIndex,0)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));
		}

		@Override
		protected void register(){
			Item item = ItemList.diamondScaleArmorHead.get();
			LanguageRegistry.addName(item, "Diamond Plated Scale Helmet"); 
		}
	},
	DiamondScaleChest{
		@Override
		protected void create(){
			ItemList.diamondScaleArmorChest = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.diamondScaleMaterial, ProjectZulu_Blocks.diamondScaleIndex,1)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.diamondScaleArmorChest.get();
			LanguageRegistry.addName(item, "Diamond Plated Scale Chestplate"); 
		}
	},
	DiamondScaleLegs{
		@Override
		protected void create(){
			ItemList.diamondScaleArmorLeg = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.diamondScaleMaterial, ProjectZulu_Blocks.diamondScaleIndex,2)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.diamondScaleArmorLeg.get();
			LanguageRegistry.addName(item, "Diamond Plated Scale Leggings"); 
		}
	},
	DiamondScaleBoots{
		@Override
		protected void create(){
			ItemList.diamondScaleArmorBoots = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.diamondScaleMaterial, ProjectZulu_Blocks.diamondScaleIndex,3)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.diamondScaleArmorBoots.get();
			LanguageRegistry.addName(item, "Diamond Plated Scale Boots"); 
		}
	},
	WhiteClothHelmet{
		@Override
		protected void create(){
			ItemList.whiteClothHead = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.whiteWoolIndex,0)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));
		}

		@Override
		protected void register(){
			Item item = ItemList.whiteClothHead.get();
			LanguageRegistry.addName(item, "White Cloth Helmet"); 
		}
	},
	WhiteClothChest{
		@Override
		protected void create(){
			ItemList.whiteClothChest = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.whiteWoolIndex,1)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.whiteClothChest.get();
			LanguageRegistry.addName(item, "White Cloth Chestplate"); 
		}
	},
	WhiteClothLegs{
		@Override
		protected void create(){
			ItemList.whiteClothLeg = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.whiteWoolIndex,2)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.whiteClothLeg.get();
			LanguageRegistry.addName(item, "White Cloth Leggings"); 
		}
	},
	WhiteClothBoots{
		@Override
		protected void create(){
			ItemList.whiteClothBoots = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.whiteWoolIndex,3)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.whiteClothBoots.get();
			LanguageRegistry.addName(item, "White Cloth Boots"); 
		}
	},
	RedClothHelmet{
		@Override
		protected void create(){
			ItemList.redClothHead = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.redWoolIndex,0)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));
		}

		@Override
		protected void register(){
			Item item = ItemList.redClothHead.get();
			LanguageRegistry.addName(item, "Red Cloth Helmet"); 
		}
	},
	RedClothChest{
		@Override
		protected void create(){
			ItemList.redClothChest = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.redWoolIndex,1)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.redClothChest.get();
			LanguageRegistry.addName(item, "Red Cloth Chestplate"); 
		}
	},
	RedClothLegs{
		@Override
		protected void create(){
			ItemList.redClothLeg = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.redWoolIndex,2)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.redClothLeg.get();
			LanguageRegistry.addName(item, "Red Cloth Leggings"); 
		}
	},
	RedClothBoots{
		@Override
		protected void create(){
			ItemList.redClothBoots = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.redWoolIndex,3)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.redClothBoots.get();
			LanguageRegistry.addName(item, "Red Cloth Boots"); 
		}
	},
	GreenClothHelmet{
		@Override
		protected void create(){
			ItemList.greenClothHead = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.greenWoolIndex,0)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));
		}

		@Override
		protected void register(){
			Item item = ItemList.greenClothHead.get();
			LanguageRegistry.addName(item, "Green Cloth Helmet"); 
		}
	},
	GreenClothChest{
		@Override
		protected void create(){
			ItemList.greenClothChest = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.greenWoolIndex,1)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.greenClothChest.get();
			LanguageRegistry.addName(item, "Green Cloth Chestplate"); 
		}
	},
	GreenClothLegs{
		@Override
		protected void create(){
			ItemList.greenClothLeg = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.greenWoolIndex,2)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.greenClothLeg.get();
			LanguageRegistry.addName(item, "Green Cloth Leggings"); 
		}
	},
	GreenClothBoots{
		@Override
		protected void create(){
			ItemList.greenClothBoots = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.greenWoolIndex,3)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.greenClothBoots.get();
			LanguageRegistry.addName(item, "Green Cloth Boots"); 
		}
	},
	BlueClothHelmet{
		@Override
		protected void create(){
			ItemList.blueClothHead = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.blueWoolIndex,0)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));
		}

		@Override
		protected void register(){
			Item item = ItemList.blueClothHead.get();
			LanguageRegistry.addName(item, "Blue Cloth Helmet"); 
		}
	},
	BlueClothChest{
		@Override
		protected void create(){
			ItemList.blueClothChest = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.blueWoolIndex,1)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.blueClothChest.get();
			LanguageRegistry.addName(item, "Blue Cloth Chestplate"); 
		}
	},
	BlueClothLegs{
		@Override
		protected void create(){
			ItemList.blueClothLeg = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.blueWoolIndex,2)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.blueClothLeg.get();
			LanguageRegistry.addName(item, "Blue Cloth Leggings"); 
		}
	},
	BlueClothBoots{
		@Override
		protected void create(){
			ItemList.blueClothBoots = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.blueWoolIndex,3)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.blueClothBoots.get();
			LanguageRegistry.addName(item, "Blue Cloth Boots"); 
		}
	},
	CactusHelmet{
		@Override
		protected void create(){
			ItemList.cactusArmorHead = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.cactusIndex,0)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));
		}

		@Override
		protected void register(){
			Item item = ItemList.cactusArmorHead.get();
			LanguageRegistry.addName(item, "Cactus Cloth Helmet"); 
		}
	},
	CactusChest{
		@Override
		protected void create(){
			ItemList.cactusArmorChest = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.cactusIndex,1)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.cactusArmorChest.get();
			LanguageRegistry.addName(item, "Cactus Cloth Chestplate"); 
		}
	},
	CactusLegs{
		@Override
		protected void create(){
			ItemList.cactusArmorLeg = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.cactusIndex,2)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.cactusArmorLeg.get();
			LanguageRegistry.addName(item, "Cactus Cloth Leggings"); 
		}
	},
	CactusBoots{
		@Override
		protected void create(){
			ItemList.cactusArmorBoots = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.cactusIndex,3)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.cactusArmorBoots.get();
			LanguageRegistry.addName(item, "Cactus Cloth Boots"); 
		}
	},
	FurHelmet{
		@Override
		protected void create(){
			ItemList.furArmorHead = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.furIndex,0)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));
		}

		@Override
		protected void register(){
			Item item = ItemList.furArmorHead.get();
			LanguageRegistry.addName(item, "Fur Cloth Helmet"); 
		}
	},
	FurChest{
		@Override
		protected void create(){
			ItemList.furArmorChest = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.furIndex,1)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.furArmorChest.get();
			LanguageRegistry.addName(item, "Fur Cloth Chestplate"); 
		}
	},
	FurLegs{
		@Override
		protected void create(){
			ItemList.furArmorLeg = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.furIndex,2)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.furArmorLeg.get();
			LanguageRegistry.addName(item, "Fur Cloth Leggings"); 
		}
	},
	FurBoots{
		@Override
		protected void create(){
			ItemList.furArmorBoots = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.furIndex,3)).setUnlocalizedName(DefaultProps.blockKey+":"+toString().toLowerCase()));	
		}

		@Override
		protected void register(){
			Item item = ItemList.furArmorBoots.get();
			LanguageRegistry.addName(item, "Fur Cloth Boots"); 
		}
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
		for (final ArmorManager block : ArmorManager.values()) {
			block.blockID = config.getItem(block.toString()+" ID", ProjectZulu_Blocks.getNextDefaultItemID()+100).getInt();			
		}
	}

	private static void createBlocks() throws Exception {
		for (final ArmorManager block : ArmorManager.values())
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
		for (final ArmorManager block : values())
			if (block.blockCreated) block.register();
	}
	protected abstract void register();	

}
