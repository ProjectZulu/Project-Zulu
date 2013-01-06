package projectzulu.common.core;

import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import projectzulu.common.ProjectZulu_Blocks;
import projectzulu.common.mod_ProjectZulu;
import projectzulu.common.blocks.ItemZuluArmor;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * Inspired by BlockManager Classfrom ExtrabiomesXL . See Github for Original Source: https://github.com/ExtrabiomesXL
 */
//TODO: Get Rid of getID function, Replace with defalt Range. Forge auto gets Empty ID, just Need to Provide a sufficiently High Default 
// Increment to try to make sure we don't occupy Block ourself, which could casue forge to grab low ID
// Current Values USed a historical, should be changed with next Major MC Update, such that potential to break worlds if Config is lost is minimized
public enum ArmorManager {
	ScaleArmorHead{
		@Override
		protected void create(){
			ItemBlockList.scaleArmorHead = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.scaleMaterial, ProjectZulu_Blocks.scaleIndex,0)).setIconIndex(16).setItemName("Scale Helmet"));
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.scaleArmorHead.get();
			LanguageRegistry.addName(item, "Scale Helmet"); 
		}
	},
	ScaleArmorChest{

		@Override
		protected void create(){
			ItemBlockList.scaleArmorChest = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.scaleMaterial, ProjectZulu_Blocks.scaleIndex,1)).setIconIndex(17).setItemName("Scale Chestplate"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.scaleArmorChest.get();
			LanguageRegistry.addName(item, "Scale Chestplate"); 
		}
	},
	ScaleArmorLeg{
		@Override
		protected void create(){
			ItemBlockList.scaleArmorLeg = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.scaleMaterial, ProjectZulu_Blocks.scaleIndex,2)).setIconIndex(18).setItemName("Scale Leggings"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.scaleArmorLeg.get();
			LanguageRegistry.addName(item, "Scale Leggings"); 
		}
	},
	ScaleArmorBoot{
		@Override
		protected void create(){
			ItemBlockList.scaleArmorBoot = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.scaleMaterial, ProjectZulu_Blocks.scaleIndex,3)).setIconIndex(19).setItemName("Scale Boots"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.scaleArmorBoot.get();
			LanguageRegistry.addName(item, "Scale Boots"); 
		}
	},
	GoldScaleArmorHead{
		@Override
		protected void create(){
			ItemBlockList.goldScaleArmorHead = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.goldScaleMaterial, ProjectZulu_Blocks.goldScaleIndex, 0)).setIconIndex(48).setItemName("goldScale Helmet"));
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.goldScaleArmorHead.get();
			LanguageRegistry.addName(item, "Gold Scale Helmet"); 
		}
	},
	GoldScaleArmorChest{
		@Override
		protected void create(){
			ItemBlockList.goldScaleArmorChest = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.goldScaleMaterial, ProjectZulu_Blocks.goldScaleIndex, 1)).setIconIndex(49).setItemName("goldScale Chestplate"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.goldScaleArmorChest.get();
			LanguageRegistry.addName(item, "Gold Scale Chestplate"); 
		}
	},
	GoldScaleArmorLeg{
		@Override
		protected void create(){
			ItemBlockList.goldScaleArmorLeg = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.goldScaleMaterial, ProjectZulu_Blocks.goldScaleIndex, 2)).setIconIndex(50).setItemName("goldScale Leggings"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.goldScaleArmorLeg.get();
			LanguageRegistry.addName(item, "Gold Scale Leggings"); 
		}
	},
	GoldScaleArmorBoot{
		@Override
		protected void create(){
			ItemBlockList.goldScaleArmorBoot = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.goldScaleMaterial, ProjectZulu_Blocks.goldScaleIndex, 3)).setIconIndex(51).setItemName("goldScale Boots"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.goldScaleArmorBoot.get();
			LanguageRegistry.addName(item, "Gold Scale Boots"); 
		}
	},
	IronScaleArmorHead{
		@Override
		protected void create(){
			ItemBlockList.ironScaleArmorHead = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.ironScaleMaterial, ProjectZulu_Blocks.ironScaleIndex,0)).setIconIndex(52).setItemName("ironScale Helmet"));
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.ironScaleArmorHead.get();
			LanguageRegistry.addName(item, "Iron Plated Scale Helmet"); 
		}
	},
	IronScaleArmorChest{
		@Override
		protected void create(){
			ItemBlockList.ironScaleArmorChest = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.ironScaleMaterial, ProjectZulu_Blocks.ironScaleIndex,1)).setIconIndex(53).setItemName("ironScale Chestplate"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.ironScaleArmorChest.get();
			LanguageRegistry.addName(item, "Iron Plated Scale Chestplate"); 
		}
	},
	IronScaleArmorLeg{
		@Override
		protected void create(){
			ItemBlockList.ironScaleArmorLeg = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.ironScaleMaterial, ProjectZulu_Blocks.ironScaleIndex,2)).setIconIndex(54).setItemName("ironScale Leggings"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.ironScaleArmorLeg.get();
			LanguageRegistry.addName(item, "Iron Plated Scale Leggings"); 
		}
	},
	IronScaleArmorBoot{
		@Override
		protected void create(){
			ItemBlockList.ironScaleArmorBoot = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.ironScaleMaterial, ProjectZulu_Blocks.ironScaleIndex,3)).setIconIndex(55).setItemName("ironScale Boots"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.ironScaleArmorBoot.get();
			LanguageRegistry.addName(item, "Iron Plated Scale Boots"); 
		}
	},
	DiamondScaleArmorHead{
		@Override
		protected void create(){
			ItemBlockList.diamondScaleArmorHead = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.diamondScaleMaterial, ProjectZulu_Blocks.diamondScaleIndex,0)).setIconIndex(56).setItemName("diamondScale Helmet"));
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.diamondScaleArmorHead.get();
			LanguageRegistry.addName(item, "Diamond Plated Scale Helmet"); 
		}
	},
	DiamondScaleArmorChest{
		@Override
		protected void create(){
			ItemBlockList.diamondScaleArmorChest = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.diamondScaleMaterial, ProjectZulu_Blocks.diamondScaleIndex,1)).setIconIndex(57).setItemName("diamondScale Chestplate"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.diamondScaleArmorChest.get();
			LanguageRegistry.addName(item, "Diamond Plated Scale Chestplate"); 
		}
	},
	DiamondScaleArmorLeg{
		@Override
		protected void create(){
			ItemBlockList.diamondScaleArmorLeg = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.diamondScaleMaterial, ProjectZulu_Blocks.diamondScaleIndex,2)).setIconIndex(58).setItemName("diamondScale Leggings"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.diamondScaleArmorLeg.get();
			LanguageRegistry.addName(item, "Diamond Plated Scale Leggings"); 
		}
	},
	DiamondScaleArmorBoot{
		@Override
		protected void create(){
			ItemBlockList.diamondScaleArmorBoot = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.diamondScaleMaterial, ProjectZulu_Blocks.diamondScaleIndex,3)).setIconIndex(59).setItemName("diamondScale Boots"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.diamondScaleArmorBoot.get();
			LanguageRegistry.addName(item, "Diamond Plated Scale Boots"); 
		}
	},
	WhiteClothArmorHead{
		@Override
		protected void create(){
			ItemBlockList.whiteClothHead = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.desertClothMaterial, ProjectZulu_Blocks.whiteWoolIndex,0)).setIconIndex(32).setItemName("whiteCloth Helmet"));
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.whiteClothHead.get();
			LanguageRegistry.addName(item, "White Cloth Helmet"); 
		}
	},
	WhiteClothArmorChest{
		@Override
		protected void create(){
			ItemBlockList.whiteClothChest = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.desertClothMaterial, ProjectZulu_Blocks.whiteWoolIndex,1)).setIconIndex(33).setItemName("whiteCloth Chestplate"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.whiteClothChest.get();
			LanguageRegistry.addName(item, "White Cloth Chestplate"); 
		}
	},
	WhiteClothArmorLeg{
		@Override
		protected void create(){
			ItemBlockList.whiteClothLeg = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.desertClothMaterial, ProjectZulu_Blocks.whiteWoolIndex,2)).setIconIndex(34).setItemName("whiteCloth Leggings"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.whiteClothLeg.get();
			LanguageRegistry.addName(item, "White Cloth Leggings"); 
		}
	},
	WhiteClothArmorBoot{
		@Override
		protected void create(){
			ItemBlockList.whiteClothBoot = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.desertClothMaterial, ProjectZulu_Blocks.whiteWoolIndex,3)).setIconIndex(35).setItemName("whiteCloth Boots"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.whiteClothBoot.get();
			LanguageRegistry.addName(item, "White Cloth Boots"); 
		}
	},
	RedClothArmorHead{
		@Override
		protected void create(){
			ItemBlockList.redClothHead = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.desertClothMaterial, ProjectZulu_Blocks.redWoolIndex,0)).setIconIndex(36).setItemName("redCloth Helmet"));
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.redClothHead.get();
			LanguageRegistry.addName(item, "Red Cloth Helmet"); 
		}
	},
	RedClothArmorChest{
		@Override
		protected void create(){
			ItemBlockList.redClothChest = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.desertClothMaterial, ProjectZulu_Blocks.redWoolIndex,1)).setIconIndex(37).setItemName("redCloth Chestplate"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.redClothChest.get();
			LanguageRegistry.addName(item, "Red Cloth Chestplate"); 
		}
	},
	RedClothArmorLeg{
		@Override
		protected void create(){
			ItemBlockList.redClothLeg = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.desertClothMaterial, ProjectZulu_Blocks.redWoolIndex,2)).setIconIndex(38).setItemName("redCloth Leggings"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.redClothLeg.get();
			LanguageRegistry.addName(item, "Red Cloth Leggings"); 
		}
	},
	RedClothArmorBoot{
		@Override
		protected void create(){
			ItemBlockList.redClothBoot = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.desertClothMaterial, ProjectZulu_Blocks.redWoolIndex,3)).setIconIndex(39).setItemName("redCloth Boots"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.redClothBoot.get();
			LanguageRegistry.addName(item, "Red Cloth Boots"); 
		}
	},
	GreenClothArmorHead{
		@Override
		protected void create(){
			ItemBlockList.greenClothHead = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.desertClothMaterial, ProjectZulu_Blocks.greenWoolIndex,0)).setIconIndex(40).setItemName("greenCloth Helmet"));
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.greenClothHead.get();
			LanguageRegistry.addName(item, "Green Cloth Helmet"); 
		}
	},
	GreenClothArmorChest{
		@Override
		protected void create(){
			ItemBlockList.greenClothChest = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.desertClothMaterial, ProjectZulu_Blocks.greenWoolIndex,1)).setIconIndex(41).setItemName("greenCloth Chestplate"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.greenClothChest.get();
			LanguageRegistry.addName(item, "Green Cloth Chestplate"); 
		}
	},
	GreenClothArmorLeg{
		@Override
		protected void create(){
			ItemBlockList.greenClothLeg = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.desertClothMaterial, ProjectZulu_Blocks.greenWoolIndex,2)).setIconIndex(42).setItemName("greenCloth Leggings"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.greenClothLeg.get();
			LanguageRegistry.addName(item, "Green Cloth Leggings"); 
		}
	},
	GreenClothArmorBoot{
		@Override
		protected void create(){
			ItemBlockList.greenClothBoot = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.desertClothMaterial, ProjectZulu_Blocks.greenWoolIndex,3)).setIconIndex(43).setItemName("greenCloth Boots"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.greenClothBoot.get();
			LanguageRegistry.addName(item, "Green Cloth Boots"); 
		}
	},
	BlueClothArmorHead{
		@Override
		protected void create(){
			ItemBlockList.blueClothHead = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.desertClothMaterial, ProjectZulu_Blocks.blueWoolIndex,0)).setIconIndex(44).setItemName("blueCloth Helmet"));
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.blueClothHead.get();
			LanguageRegistry.addName(item, "Blue Cloth Helmet"); 
		}
	},
	BlueClothArmorChest{
		@Override
		protected void create(){
			ItemBlockList.blueClothChest = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.desertClothMaterial, ProjectZulu_Blocks.blueWoolIndex,1)).setIconIndex(45).setItemName("blueCloth Chestplate"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.blueClothChest.get();
			LanguageRegistry.addName(item, "Blue Cloth Chestplate"); 
		}
	},
	BlueClothArmorLeg{
		@Override
		protected void create(){
			ItemBlockList.blueClothLeg = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.desertClothMaterial, ProjectZulu_Blocks.blueWoolIndex,2)).setIconIndex(46).setItemName("blueCloth Leggings"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.blueClothLeg.get();
			LanguageRegistry.addName(item, "Blue Cloth Leggings"); 
		}
	},
	BlueClothArmorBoot{
		@Override
		protected void create(){
			ItemBlockList.blueClothBoot = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.desertClothMaterial, ProjectZulu_Blocks.blueWoolIndex,3)).setIconIndex(47).setItemName("blueCloth Boots"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.blueClothBoot.get();
			LanguageRegistry.addName(item, "Blue Cloth Boots"); 
		}
	},
	CactusClothArmorHead{
		@Override
		protected void create(){
			ItemBlockList.cactusArmorHead = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.scaleMaterial, ProjectZulu_Blocks.cactusIndex,0)).setIconIndex(60).setItemName("CactusCloth Helmet"));
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.cactusArmorHead.get();
			LanguageRegistry.addName(item, "Cactus Cloth Helmet"); 
		}
	},
	CactusClothArmorChest{
		@Override
		protected void create(){
			ItemBlockList.cactusArmorChest = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.scaleMaterial, ProjectZulu_Blocks.cactusIndex,1)).setIconIndex(61).setItemName("CactusCloth Chestplate"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.cactusArmorChest.get();
			LanguageRegistry.addName(item, "Cactus Cloth Chestplate"); 
		}
	},
	CactusClothArmorLeg{
		@Override
		protected void create(){
			ItemBlockList.cactusArmorLeg = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.scaleMaterial, ProjectZulu_Blocks.cactusIndex,2)).setIconIndex(62).setItemName("CactusCloth Leggings"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.cactusArmorLeg.get();
			LanguageRegistry.addName(item, "Cactus Cloth Leggings"); 
		}
	},
	CactusClothArmorBoot{
		@Override
		protected void create(){
			ItemBlockList.cactusArmorBoot = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.scaleMaterial, ProjectZulu_Blocks.cactusIndex,3)).setIconIndex(63).setItemName("CactusCloth Boots"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.cactusArmorBoot.get();
			LanguageRegistry.addName(item, "Cactus Cloth Boots"); 
		}
	},
	FurArmorHead{
		@Override
		protected void create(){
			ItemBlockList.furArmorHead = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.scaleMaterial, ProjectZulu_Blocks.furIndex,0)).setIconIndex(64).setItemName("furCloth Helmet"));
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.furArmorHead.get();
			LanguageRegistry.addName(item, "Fur Cloth Helmet"); 
		}
	},
	FurArmorChest{
		@Override
		protected void create(){
			ItemBlockList.furArmorChest = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.scaleMaterial, ProjectZulu_Blocks.furIndex,1)).setIconIndex(65).setItemName("furCloth Chestplate"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.furArmorChest.get();
			LanguageRegistry.addName(item, "Fur Cloth Chestplate"); 
		}
	},
	FurArmorLeg{
		@Override
		protected void create(){
			ItemBlockList.furArmorLeg = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.scaleMaterial, ProjectZulu_Blocks.furIndex,2)).setIconIndex(66).setItemName("furCloth Leggings"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.furArmorLeg.get();
			LanguageRegistry.addName(item, "Fur Cloth Leggings"); 
		}
	},
	FurArmorBoot{
		@Override
		protected void create(){
			ItemBlockList.furArmorBoot = Optional.of(
					(new ItemZuluArmor(blockID, mod_ProjectZulu.scaleMaterial, ProjectZulu_Blocks.furIndex,3)).setIconIndex(67).setItemName("furCloth Boots"));	
		}

		@Override
		protected void register(){
			Item item = ItemBlockList.furArmorBoot.get();
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
