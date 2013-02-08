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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.scaleIndex,0)).setIconIndex(16).setItemName("Scale Helmet"));
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.scaleIndex,1)).setIconIndex(17).setItemName("Scale Chestplate"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.scaleIndex,2)).setIconIndex(18).setItemName("Scale Leggings"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.scaleIndex,3)).setIconIndex(19).setItemName("Scale Boots"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.goldScaleMaterial, ProjectZulu_Blocks.goldScaleIndex, 0)).setIconIndex(48).setItemName("goldScale Helmet"));
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.goldScaleMaterial, ProjectZulu_Blocks.goldScaleIndex, 1)).setIconIndex(49).setItemName("goldScale Chestplate"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.goldScaleMaterial, ProjectZulu_Blocks.goldScaleIndex, 2)).setIconIndex(50).setItemName("goldScale Leggings"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.goldScaleMaterial, ProjectZulu_Blocks.goldScaleIndex, 3)).setIconIndex(51).setItemName("goldScale Boots"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.ironScaleMaterial, ProjectZulu_Blocks.ironScaleIndex,0)).setIconIndex(52).setItemName("ironScale Helmet"));
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.ironScaleMaterial, ProjectZulu_Blocks.ironScaleIndex,1)).setIconIndex(53).setItemName("ironScale Chestplate"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.ironScaleMaterial, ProjectZulu_Blocks.ironScaleIndex,2)).setIconIndex(54).setItemName("ironScale Leggings"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.ironScaleMaterial, ProjectZulu_Blocks.ironScaleIndex,3)).setIconIndex(55).setItemName("ironScale Boots"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.diamondScaleMaterial, ProjectZulu_Blocks.diamondScaleIndex,0)).setIconIndex(56).setItemName("diamondScale Helmet"));
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.diamondScaleMaterial, ProjectZulu_Blocks.diamondScaleIndex,1)).setIconIndex(57).setItemName("diamondScale Chestplate"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.diamondScaleMaterial, ProjectZulu_Blocks.diamondScaleIndex,2)).setIconIndex(58).setItemName("diamondScale Leggings"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.diamondScaleMaterial, ProjectZulu_Blocks.diamondScaleIndex,3)).setIconIndex(59).setItemName("diamondScale Boots"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.whiteWoolIndex,0)).setIconIndex(32).setItemName("whiteCloth Helmet"));
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.whiteWoolIndex,1)).setIconIndex(33).setItemName("whiteCloth Chestplate"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.whiteWoolIndex,2)).setIconIndex(34).setItemName("whiteCloth Leggings"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.whiteWoolIndex,3)).setIconIndex(35).setItemName("whiteCloth Boots"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.redWoolIndex,0)).setIconIndex(36).setItemName("redCloth Helmet"));
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.redWoolIndex,1)).setIconIndex(37).setItemName("redCloth Chestplate"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.redWoolIndex,2)).setIconIndex(38).setItemName("redCloth Leggings"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.redWoolIndex,3)).setIconIndex(39).setItemName("redCloth Boots"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.greenWoolIndex,0)).setIconIndex(40).setItemName("greenCloth Helmet"));
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.greenWoolIndex,1)).setIconIndex(41).setItemName("greenCloth Chestplate"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.greenWoolIndex,2)).setIconIndex(42).setItemName("greenCloth Leggings"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.greenWoolIndex,3)).setIconIndex(43).setItemName("greenCloth Boots"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.blueWoolIndex,0)).setIconIndex(44).setItemName("blueCloth Helmet"));
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.blueWoolIndex,1)).setIconIndex(45).setItemName("blueCloth Chestplate"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.blueWoolIndex,2)).setIconIndex(46).setItemName("blueCloth Leggings"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.desertClothMaterial, ProjectZulu_Blocks.blueWoolIndex,3)).setIconIndex(47).setItemName("blueCloth Boots"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.cactusIndex,0)).setIconIndex(60).setItemName("CactusCloth Helmet"));
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.cactusIndex,1)).setIconIndex(61).setItemName("CactusCloth Chestplate"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.cactusIndex,2)).setIconIndex(62).setItemName("CactusCloth Leggings"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.cactusIndex,3)).setIconIndex(63).setItemName("CactusCloth Boots"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.furIndex,0)).setIconIndex(64).setItemName("furCloth Helmet"));
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.furIndex,1)).setIconIndex(65).setItemName("furCloth Chestplate"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.furIndex,2)).setIconIndex(66).setItemName("furCloth Leggings"));	
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
					(new ItemZuluArmor(blockID, ProjectZulu_Core.scaleMaterial, ProjectZulu_Blocks.furIndex,3)).setIconIndex(67).setItemName("furCloth Boots"));	
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
