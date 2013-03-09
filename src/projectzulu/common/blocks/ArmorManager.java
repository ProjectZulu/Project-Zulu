package projectzulu.common.blocks;

import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import projectzulu.common.ProjectZulu_Blocks;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * Inspired by BlockManager Classfrom ExtrabiomesXL . See Github for Original Source: https://github.com/ExtrabiomesXL
 */
public enum ArmorManager {
	ScaleArmorHead{
		@Override
		protected void create(){
			ItemList.scaleArmorHead = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.scaleIndex,0)).setUnlocalizedName("Scale Helmet"));
		}

		@Override
		protected void register(){
			Item item = ItemList.scaleArmorHead.get();
			LanguageRegistry.addName(item, "Scale Helmet"); 
		}
	},
	ScaleArmorChest{

		@Override
		protected void create(){
			ItemList.scaleArmorChest = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.scaleIndex,1)).setUnlocalizedName("Scale Chestplate"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.scaleArmorChest.get();
			LanguageRegistry.addName(item, "Scale Chestplate"); 
		}
	},
	ScaleArmorLeg{
		@Override
		protected void create(){
			ItemList.scaleArmorLeg = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.scaleIndex,2)).setUnlocalizedName("Scale Leggings"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.scaleArmorLeg.get();
			LanguageRegistry.addName(item, "Scale Leggings"); 
		}
	},
	ScaleArmorBoot{
		@Override
		protected void create(){
			ItemList.scaleArmorBoot = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.scaleIndex,3)).setUnlocalizedName("Scale Boots"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.scaleArmorBoot.get();
			LanguageRegistry.addName(item, "Scale Boots"); 
		}
	},
	GoldScaleArmorHead{
		@Override
		protected void create(){
			ItemList.goldScaleArmorHead = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.goldScaleMaterial, ProjectZulu_Blocks.goldScaleIndex, 0)).setUnlocalizedName("goldScale Helmet"));
		}

		@Override
		protected void register(){
			Item item = ItemList.goldScaleArmorHead.get();
			LanguageRegistry.addName(item, "Gold Scale Helmet"); 
		}
	},
	GoldScaleArmorChest{
		@Override
		protected void create(){
			ItemList.goldScaleArmorChest = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.goldScaleMaterial, ProjectZulu_Blocks.goldScaleIndex, 1)).setUnlocalizedName("goldScale Chestplate"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.goldScaleArmorChest.get();
			LanguageRegistry.addName(item, "Gold Scale Chestplate"); 
		}
	},
	GoldScaleArmorLeg{
		@Override
		protected void create(){
			ItemList.goldScaleArmorLeg = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.goldScaleMaterial, ProjectZulu_Blocks.goldScaleIndex, 2)).setUnlocalizedName("goldScale Leggings"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.goldScaleArmorLeg.get();
			LanguageRegistry.addName(item, "Gold Scale Leggings"); 
		}
	},
	GoldScaleArmorBoot{
		@Override
		protected void create(){
			ItemList.goldScaleArmorBoot = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.goldScaleMaterial, ProjectZulu_Blocks.goldScaleIndex, 3)).setUnlocalizedName("goldScale Boots"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.goldScaleArmorBoot.get();
			LanguageRegistry.addName(item, "Gold Scale Boots"); 
		}
	},
	IronScaleArmorHead{
		@Override
		protected void create(){
			ItemList.ironScaleArmorHead = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.ironScaleMaterial, ProjectZulu_Blocks.ironScaleIndex,0)).setUnlocalizedName("ironScale Helmet"));
		}

		@Override
		protected void register(){
			Item item = ItemList.ironScaleArmorHead.get();
			LanguageRegistry.addName(item, "Iron Plated Scale Helmet"); 
		}
	},
	IronScaleArmorChest{
		@Override
		protected void create(){
			ItemList.ironScaleArmorChest = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.ironScaleMaterial, ProjectZulu_Blocks.ironScaleIndex,1)).setUnlocalizedName("ironScale Chestplate"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.ironScaleArmorChest.get();
			LanguageRegistry.addName(item, "Iron Plated Scale Chestplate"); 
		}
	},
	IronScaleArmorLeg{
		@Override
		protected void create(){
			ItemList.ironScaleArmorLeg = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.ironScaleMaterial, ProjectZulu_Blocks.ironScaleIndex,2)).setUnlocalizedName("ironScale Leggings"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.ironScaleArmorLeg.get();
			LanguageRegistry.addName(item, "Iron Plated Scale Leggings"); 
		}
	},
	IronScaleArmorBoot{
		@Override
		protected void create(){
			ItemList.ironScaleArmorBoot = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.ironScaleMaterial, ProjectZulu_Blocks.ironScaleIndex,3)).setUnlocalizedName("ironScale Boots"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.ironScaleArmorBoot.get();
			LanguageRegistry.addName(item, "Iron Plated Scale Boots"); 
		}
	},
	DiamondScaleArmorHead{
		@Override
		protected void create(){
			ItemList.diamondScaleArmorHead = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.diamondScaleMaterial, ProjectZulu_Blocks.diamondScaleIndex,0)).setUnlocalizedName("diamondScale Helmet"));
		}

		@Override
		protected void register(){
			Item item = ItemList.diamondScaleArmorHead.get();
			LanguageRegistry.addName(item, "Diamond Plated Scale Helmet"); 
		}
	},
	DiamondScaleArmorChest{
		@Override
		protected void create(){
			ItemList.diamondScaleArmorChest = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.diamondScaleMaterial, ProjectZulu_Blocks.diamondScaleIndex,1)).setUnlocalizedName("diamondScale Chestplate"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.diamondScaleArmorChest.get();
			LanguageRegistry.addName(item, "Diamond Plated Scale Chestplate"); 
		}
	},
	DiamondScaleArmorLeg{
		@Override
		protected void create(){
			ItemList.diamondScaleArmorLeg = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.diamondScaleMaterial, ProjectZulu_Blocks.diamondScaleIndex,2)).setUnlocalizedName("diamondScale Leggings"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.diamondScaleArmorLeg.get();
			LanguageRegistry.addName(item, "Diamond Plated Scale Leggings"); 
		}
	},
	DiamondScaleArmorBoot{
		@Override
		protected void create(){
			ItemList.diamondScaleArmorBoot = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.diamondScaleMaterial, ProjectZulu_Blocks.diamondScaleIndex,3)).setUnlocalizedName("diamondScale Boots"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.diamondScaleArmorBoot.get();
			LanguageRegistry.addName(item, "Diamond Plated Scale Boots"); 
		}
	},
	WhiteClothArmorHead{
		@Override
		protected void create(){
			ItemList.whiteClothHead = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.whiteWoolIndex,0)).setUnlocalizedName("whiteCloth Helmet"));
		}

		@Override
		protected void register(){
			Item item = ItemList.whiteClothHead.get();
			LanguageRegistry.addName(item, "White Cloth Helmet"); 
		}
	},
	WhiteClothArmorChest{
		@Override
		protected void create(){
			ItemList.whiteClothChest = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.whiteWoolIndex,1)).setUnlocalizedName("whiteCloth Chestplate"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.whiteClothChest.get();
			LanguageRegistry.addName(item, "White Cloth Chestplate"); 
		}
	},
	WhiteClothArmorLeg{
		@Override
		protected void create(){
			ItemList.whiteClothLeg = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.whiteWoolIndex,2)).setUnlocalizedName("whiteCloth Leggings"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.whiteClothLeg.get();
			LanguageRegistry.addName(item, "White Cloth Leggings"); 
		}
	},
	WhiteClothArmorBoot{
		@Override
		protected void create(){
			ItemList.whiteClothBoot = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.whiteWoolIndex,3)).setUnlocalizedName("whiteCloth Boots"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.whiteClothBoot.get();
			LanguageRegistry.addName(item, "White Cloth Boots"); 
		}
	},
	RedClothArmorHead{
		@Override
		protected void create(){
			ItemList.redClothHead = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.redWoolIndex,0)).setUnlocalizedName("redCloth Helmet"));
		}

		@Override
		protected void register(){
			Item item = ItemList.redClothHead.get();
			LanguageRegistry.addName(item, "Red Cloth Helmet"); 
		}
	},
	RedClothArmorChest{
		@Override
		protected void create(){
			ItemList.redClothChest = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.redWoolIndex,1)).setUnlocalizedName("redCloth Chestplate"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.redClothChest.get();
			LanguageRegistry.addName(item, "Red Cloth Chestplate"); 
		}
	},
	RedClothArmorLeg{
		@Override
		protected void create(){
			ItemList.redClothLeg = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.redWoolIndex,2)).setUnlocalizedName("redCloth Leggings"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.redClothLeg.get();
			LanguageRegistry.addName(item, "Red Cloth Leggings"); 
		}
	},
	RedClothArmorBoot{
		@Override
		protected void create(){
			ItemList.redClothBoot = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.redWoolIndex,3)).setUnlocalizedName("redCloth Boots"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.redClothBoot.get();
			LanguageRegistry.addName(item, "Red Cloth Boots"); 
		}
	},
	GreenClothArmorHead{
		@Override
		protected void create(){
			ItemList.greenClothHead = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.greenWoolIndex,0)).setUnlocalizedName("greenCloth Helmet"));
		}

		@Override
		protected void register(){
			Item item = ItemList.greenClothHead.get();
			LanguageRegistry.addName(item, "Green Cloth Helmet"); 
		}
	},
	GreenClothArmorChest{
		@Override
		protected void create(){
			ItemList.greenClothChest = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.greenWoolIndex,1)).setUnlocalizedName("greenCloth Chestplate"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.greenClothChest.get();
			LanguageRegistry.addName(item, "Green Cloth Chestplate"); 
		}
	},
	GreenClothArmorLeg{
		@Override
		protected void create(){
			ItemList.greenClothLeg = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.greenWoolIndex,2)).setUnlocalizedName("greenCloth Leggings"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.greenClothLeg.get();
			LanguageRegistry.addName(item, "Green Cloth Leggings"); 
		}
	},
	GreenClothArmorBoot{
		@Override
		protected void create(){
			ItemList.greenClothBoot = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.greenWoolIndex,3)).setUnlocalizedName("greenCloth Boots"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.greenClothBoot.get();
			LanguageRegistry.addName(item, "Green Cloth Boots"); 
		}
	},
	BlueClothArmorHead{
		@Override
		protected void create(){
			ItemList.blueClothHead = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.blueWoolIndex,0)).setUnlocalizedName("blueCloth Helmet"));
		}

		@Override
		protected void register(){
			Item item = ItemList.blueClothHead.get();
			LanguageRegistry.addName(item, "Blue Cloth Helmet"); 
		}
	},
	BlueClothArmorChest{
		@Override
		protected void create(){
			ItemList.blueClothChest = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.blueWoolIndex,1)).setUnlocalizedName("blueCloth Chestplate"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.blueClothChest.get();
			LanguageRegistry.addName(item, "Blue Cloth Chestplate"); 
		}
	},
	BlueClothArmorLeg{
		@Override
		protected void create(){
			ItemList.blueClothLeg = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.blueWoolIndex,2)).setUnlocalizedName("blueCloth Leggings"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.blueClothLeg.get();
			LanguageRegistry.addName(item, "Blue Cloth Leggings"); 
		}
	},
	BlueClothArmorBoot{
		@Override
		protected void create(){
			ItemList.blueClothBoot = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.blueWoolIndex,3)).setUnlocalizedName("blueCloth Boots"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.blueClothBoot.get();
			LanguageRegistry.addName(item, "Blue Cloth Boots"); 
		}
	},
	CactusClothArmorHead{
		@Override
		protected void create(){
			ItemList.cactusArmorHead = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.cactusIndex,0)).setUnlocalizedName("CactusCloth Helmet"));
		}

		@Override
		protected void register(){
			Item item = ItemList.cactusArmorHead.get();
			LanguageRegistry.addName(item, "Cactus Cloth Helmet"); 
		}
	},
	CactusClothArmorChest{
		@Override
		protected void create(){
			ItemList.cactusArmorChest = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.cactusIndex,1)).setUnlocalizedName("CactusCloth Chestplate"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.cactusArmorChest.get();
			LanguageRegistry.addName(item, "Cactus Cloth Chestplate"); 
		}
	},
	CactusClothArmorLeg{
		@Override
		protected void create(){
			ItemList.cactusArmorLeg = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.cactusIndex,2)).setUnlocalizedName("CactusCloth Leggings"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.cactusArmorLeg.get();
			LanguageRegistry.addName(item, "Cactus Cloth Leggings"); 
		}
	},
	CactusClothArmorBoot{
		@Override
		protected void create(){
			ItemList.cactusArmorBoot = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.cactusIndex,3)).setUnlocalizedName("CactusCloth Boots"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.cactusArmorBoot.get();
			LanguageRegistry.addName(item, "Cactus Cloth Boots"); 
		}
	},
	FurArmorHead{
		@Override
		protected void create(){
			ItemList.furArmorHead = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.furIndex,0)).setUnlocalizedName("furCloth Helmet"));
		}

		@Override
		protected void register(){
			Item item = ItemList.furArmorHead.get();
			LanguageRegistry.addName(item, "Fur Cloth Helmet"); 
		}
	},
	FurArmorChest{
		@Override
		protected void create(){
			ItemList.furArmorChest = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.furIndex,1)).setUnlocalizedName("furCloth Chestplate"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.furArmorChest.get();
			LanguageRegistry.addName(item, "Fur Cloth Chestplate"); 
		}
	},
	FurArmorLeg{
		@Override
		protected void create(){
			ItemList.furArmorLeg = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.furIndex,2)).setUnlocalizedName("furCloth Leggings"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.furArmorLeg.get();
			LanguageRegistry.addName(item, "Fur Cloth Leggings"); 
		}
	},
	FurArmorBoot{
		@Override
		protected void create(){
			ItemList.furArmorBoot = Optional.of(
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.furIndex,3)).setUnlocalizedName("furCloth Boots"));	
		}

		@Override
		protected void register(){
			Item item = ItemList.furArmorBoot.get();
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
