package projectzulu.common.blocks;

import static net.minecraftforge.common.ChestGenHooks.DUNGEON_CHEST;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import projectzulu.common.Properties;
import projectzulu.common.api.BlockList;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ItemGenerics;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemBlockRecipeManager {
	
	public static void setupBlockModuleRecipies(){
		if( Loader.isModLoaded("ExtrabiomesXL") ){
			setupExtrabiomesXLRecipies();
		}
		setupCampfireRecipies();
		setupArmorRecipies();
		
        /* Tombstone */
        addRecipe(new OptionalItemStack(BlockList.tombstone), new String[] { "CCC", "CSC", "CCC" }, new char[] { 'C',
                'S' }, new OptionalItemStack(Block.cobblestone), new OptionalItemStack(Item.sign));

		/* Palm Tree Recipies */
        addSmelting(new OptionalItemStack(Item.coal, 1, 1), 0, new OptionalItemStack(BlockList.palmTreeLog));
		addShapelessRecipe(new OptionalItemStack(BlockList.palmTreePlank,4), new OptionalItemStack(BlockList.palmTreeLog));
		addRecipe(new OptionalItemStack(BlockList.palmTreeDoubleSlab), new String[]{"X  ","X  ","   "}, 'X', new OptionalItemStack(BlockList.palmTreeSlab));
		addRecipe(new OptionalItemStack(BlockList.palmTreeDoubleSlab), new String[]{" X "," X ","   "}, 'X', new OptionalItemStack(BlockList.palmTreeSlab));
		addRecipe(new OptionalItemStack(BlockList.palmTreeDoubleSlab), new String[]{"  X","  X","   "}, 'X', new OptionalItemStack(BlockList.palmTreeSlab));
		addRecipe(new OptionalItemStack(BlockList.palmTreeDoubleSlab), new String[]{"   ","X  ","X  "}, 'X', new OptionalItemStack(BlockList.palmTreeSlab));
		addRecipe(new OptionalItemStack(BlockList.palmTreeDoubleSlab), new String[]{"   "," X "," X "}, 'X', new OptionalItemStack(BlockList.palmTreeSlab));
		addRecipe(new OptionalItemStack(BlockList.palmTreeDoubleSlab), new String[]{"   ","  X","  X"}, 'X', new OptionalItemStack(BlockList.palmTreeSlab));

		/* Jasper */
		if(BlockList.jasper.isPresent()){
			ChestGenHooks.getInfo(DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(BlockList.jasper.get()), 1, 1, 5));
		}
		addRecipe(new OptionalItemStack(BlockList.jasper), new String[]{"XXX","XCX","XXX"}, new char[]{'C','X'},
				new OptionalItemStack(Block.sand), new OptionalItemStack(Item.dyePowder,1,1) );
		addRecipe(new OptionalItemStack(BlockList.jasper), new String[]{"XXX","XCX","XXX"}, new char[]{'C','X'}, 
				new OptionalItemStack(Block.sand), new OptionalItemStack(Item.redstone) );
		
		/* Coconut */
		if(ItemList.coconutSeed.isPresent() && ItemList.coconutItem.isPresent()){
			GameRegistry.registerCraftingHandler(new CoconutCraftingHandler());
		}
		addRecipe(new OptionalItemStack(Item.bowlEmpty), new String[]{"   ","X X"," X "}, 'X', new OptionalItemStack(ItemList.coconutShell));
		addRecipe(new OptionalItemStack(ItemList.coconutSeed), new String[]{" X "," Y ","   "}, 
				new char[]{'X','Y'}, new OptionalItemStack(Item.swordWood,1,OreDictionary.WILDCARD_VALUE), new OptionalItemStack(ItemList.coconutItem));
		addRecipe(new OptionalItemStack(ItemList.coconutSeed), new String[]{" X "," Y ","   "}, 
				new char[]{'X','Y'}, new OptionalItemStack(Item.swordStone,1,OreDictionary.WILDCARD_VALUE), new OptionalItemStack(ItemList.coconutItem));
		addRecipe(new OptionalItemStack(ItemList.coconutSeed), new String[]{" X "," Y ","   "}, 
				new char[]{'X','Y'}, new OptionalItemStack(Item.swordIron,1,OreDictionary.WILDCARD_VALUE), new OptionalItemStack(ItemList.coconutItem));
		addRecipe(new OptionalItemStack(ItemList.coconutSeed), new String[]{" X "," Y ","   "}, 
				new char[]{'X','Y'}, new OptionalItemStack(Item.swordGold,1,OreDictionary.WILDCARD_VALUE), new OptionalItemStack(ItemList.coconutItem));
		addRecipe(new OptionalItemStack(ItemList.coconutSeed), new String[]{" X "," Y ","   "}, 
				new char[]{'X','Y'}, new OptionalItemStack(Item.swordDiamond,1,OreDictionary.WILDCARD_VALUE), new OptionalItemStack(ItemList.coconutItem));
		
		/*Aloe Vera */
		addRecipe(new OptionalItemStack(Item.silk,6), new String[]{"C  ","C  ","C  "}, 'C', new OptionalItemStack(BlockList.tumbleweed));
		addRecipe(new OptionalItemStack(Item.silk,6), new String[]{" C "," C "," C "}, 'C', new OptionalItemStack(BlockList.tumbleweed));
		addRecipe(new OptionalItemStack(Item.silk,6), new String[]{"  C","  C","  C"}, 'C', new OptionalItemStack(BlockList.tumbleweed));
		addShapelessRecipe(new OptionalItemStack(BlockList.aloeVera,2), new OptionalItemStack(BlockList.tumbleweed));
		
		/* Brewing Stand */
        shapedOreRecipe(new OptionalItemStack(BlockList.brewingStandSingle), true,
                new String[] { "GGG", "CLC", "LLL" }, new char[] { 'G', 'C', 'L' }, new OptionalItemStack(Block.glass),
                new OptionalItemStack(Block.cobblestone), new OptionalItemStack("log"));
        shapedOreRecipe(new OptionalItemStack(BlockList.brewingStandSingle), true,
                new String[] { "GGG", "CLC", "LLL" }, new char[] { 'G', 'C', 'L' }, new OptionalItemStack(Block.glass),
                new OptionalItemStack("cobbleRed"), new OptionalItemStack("log"));
        shapedOreRecipe(new OptionalItemStack(BlockList.brewingStandTriple), true,
                new String[] { "GGG", "LNL", "NNN" }, new char[] { 'G', 'L', 'N' }, new OptionalItemStack(Block.glass),
                new OptionalItemStack(Item.lightStoneDust), new OptionalItemStack(Block.netherBrick));
        
		/* Misc Generic Craftables */
		if(ItemList.genericCraftingItems.isPresent()){
			ChestGenHooks.getInfo(DUNGEON_CHEST).addItem(new WeightedRandomChestContent(
					new ItemStack(ItemList.genericCraftingItems.get(), 1, ItemGenerics.Properties.ShinyBauble.meta()), 3, 8, 35));		
		}
        addRecipe(new OptionalItemStack(ItemList.genericCraftingItems, 1, ItemGenerics.Properties.ShinyBauble.meta()),
                new String[] { " G ", "GNG", " G " }, new char[] { 'G', 'N' }, new OptionalItemStack(
                        Item.lightStoneDust), new OptionalItemStack(Item.netherStalkSeeds));
		addShapelessRecipe(new OptionalItemStack(ItemList.genericCraftingItems,2,ItemGenerics.Properties.Salt.meta()),
				new OptionalItemStack(Item.gunpowder));
		addRecipe(new OptionalItemStack(BlockList.spike), new String[]{"   ","   ","TTT"}, 'T',
				new OptionalItemStack(ItemList.genericCraftingItems,1,ItemGenerics.Properties.Tusk.meta()) );
		addShapelessRecipe(new OptionalItemStack(Item.silk),
				new OptionalItemStack(ItemList.genericCraftingItems,1,ItemGenerics.Properties.RawFiber.meta()),
				new OptionalItemStack(ItemList.genericCraftingItems,1,ItemGenerics.Properties.RawFiber.meta()),
				new OptionalItemStack(ItemList.genericCraftingItems,1,ItemGenerics.Properties.RawFiber.meta()),
				new OptionalItemStack(ItemList.genericCraftingItems,1,ItemGenerics.Properties.RawFiber.meta()) ); //4 RawFib --> 1 String
		
		addShapelessRecipe(new OptionalItemStack(ItemList.genericCraftingItems,2,ItemGenerics.Properties.Pulp.meta()), 
				new OptionalItemStack(Item.paper));
		addShapelessRecipe(new OptionalItemStack(ItemList.genericCraftingItems,1,ItemGenerics.Properties.PricklyPowder.meta()),
				new OptionalItemStack(Block.cactus), new OptionalItemStack(ItemList.genericCraftingItems,1,ItemGenerics.Properties.Pulp.meta()));
		
		addShapelessRecipe(new OptionalItemStack(ItemList.genericCraftingItems,1,ItemGenerics.Properties.PowderSlush.meta()),
				new OptionalItemStack(Item.bucketMilk), 
				new OptionalItemStack(ItemList.genericCraftingItems,1,ItemGenerics.Properties.Pulp.meta()),
				new OptionalItemStack(ItemList.genericCraftingItems,1,ItemGenerics.Properties.BlackLichen.meta()));
		addShapelessRecipe(new OptionalItemStack(ItemList.genericCraftingItems,1,ItemGenerics.Properties.GlowingGoo.meta()),
				new OptionalItemStack(Item.bucketMilk), 
				new OptionalItemStack(ItemList.genericCraftingItems,1,ItemGenerics.Properties.Pulp.meta()),
				new OptionalItemStack(ItemList.genericCraftingItems,1,ItemGenerics.Properties.Ectoplasm.meta()));
		addShapelessRecipe(new OptionalItemStack(ItemList.genericCraftingItems,1,ItemGenerics.Properties.GlowingGoo.meta()),
				new OptionalItemStack(ItemList.genericCraftingItems,1,ItemGenerics.Properties.PowderSlush.meta()),
				new OptionalItemStack(ItemList.genericCraftingItems,1,ItemGenerics.Properties.Ectoplasm.meta()));
		shapelessOreRecipe(new OptionalItemStack(ItemList.genericCraftingItems,1,ItemGenerics.Properties.SmallUnhealthyHeart.meta()),
				new OptionalItemStack(ItemList.genericCraftingItems,1,ItemGenerics.Properties.SmallHeart.meta()),
				new OptionalItemStack("foodSalt") );
		shapelessOreRecipe(new OptionalItemStack(ItemList.genericCraftingItems,1,ItemGenerics.Properties.LargeUnhealthyHeart.meta()),
				new OptionalItemStack(ItemList.genericCraftingItems,1,ItemGenerics.Properties.LargeHeart.meta()),
				new OptionalItemStack("foodSalt") );
		
		addShapelessRecipe(new OptionalItemStack(Item.leather), new OptionalItemStack(ItemList.furPelt));
		if(ItemList.furPelt.isPresent() && ItemList.genericCraftingItems.isPresent()){
				GameRegistry.registerCraftingHandler(new RawFiberCraftingHandler());
		}
		
		/* Flower Pot */
		if(!Properties.replaceFlowerPot){
			addShapelessRecipe(new OptionalItemStack(Item.flowerPot), new OptionalItemStack(BlockList.universalFlowerPot) );
			addShapelessRecipe(new OptionalItemStack(BlockList.universalFlowerPot), new OptionalItemStack(Item.flowerPot) );
		}
	}

	private static void setupExtrabiomesXLRecipies(){
		shapedOreRecipe(new OptionalItemStack(BlockList.campfire,1,1), true, new String[]{"   ","LLL","CCC"}, new char[]{'L','C'},
				new OptionalItemStack("log"), new OptionalItemStack("cobbleRed") );
		
		shapedOreRecipe(new OptionalItemStack(BlockList.campfire,1,2), true, new String[]{" S ","LLL","   "}, new char[]{'L','C','S'},
				new OptionalItemStack("log"), new OptionalItemStack("cobbleRed"), new OptionalItemStack(Item.stick) );

		shapedOreRecipe(new OptionalItemStack(BlockList.campfire,1,3), true, new String[]{" S ","LLL","CCC"}, new char[]{'L','C','S'},
				new OptionalItemStack("log"), new OptionalItemStack("cobbleRed"), new OptionalItemStack(Item.stick) );		
	}

	private static void setupCampfireRecipies() {
		
		shapedOreRecipe(new OptionalItemStack(BlockList.campfire,1,0), true, new String[]{"   ","LLL","   "}, new char[]{'L'}, 
				new OptionalItemStack(Block.wood,1,OreDictionary.WILDCARD_VALUE));
		shapedOreRecipe(new OptionalItemStack(BlockList.campfire,1,0), true, new String[]{"   ","LLL","   "}, new char[]{'L'},
				new OptionalItemStack("log"));
		
		shapedOreRecipe(new OptionalItemStack(BlockList.campfire,1,1), true, new String[]{"   ","LLL","CCC"}, new char[]{'L','C'},
				new OptionalItemStack(Block.wood,1,OreDictionary.WILDCARD_VALUE), new OptionalItemStack(Block.cobblestone) );		
		shapedOreRecipe(new OptionalItemStack(BlockList.campfire,1,1), true, new String[]{"   ","LLL","CCC"}, new char[]{'L','C'},
				new OptionalItemStack("log"), new OptionalItemStack(Block.cobblestone) );

		shapedOreRecipe(new OptionalItemStack(BlockList.campfire,1,2), true, new String[]{" S ","LLL","   "}, new char[]{'L','C','S'},
				new OptionalItemStack(Block.wood,1,OreDictionary.WILDCARD_VALUE), new OptionalItemStack(Block.cobblestone), new OptionalItemStack(Item.stick) );
		shapedOreRecipe(new OptionalItemStack(BlockList.campfire,1,2), true, new String[]{" S ","LLL","   "}, new char[]{'L','C','S'},
				new OptionalItemStack("log"), new OptionalItemStack(Block.cobblestone), new OptionalItemStack(Block.cobblestone) );

		shapedOreRecipe(new OptionalItemStack(BlockList.campfire,1,3), true, new String[]{" S ","LLL","CCC"}, new char[]{'L','C','S'},
				new OptionalItemStack(Block.wood,1,OreDictionary.WILDCARD_VALUE), new OptionalItemStack(Block.cobblestone), new OptionalItemStack(Item.stick) );
		shapedOreRecipe(new OptionalItemStack(BlockList.campfire,1,3), true, new String[]{" S ","LLL","CCC"}, new char[]{'L','C','S'},
				new OptionalItemStack("log"), new OptionalItemStack(Block.cobblestone), new OptionalItemStack(Block.cobblestone) );
	}

	private static void setupArmorRecipies(){

		addRecipe(new OptionalItemStack(ItemList.scaleArmorHead), new String[]{"XXX","X X","   "}, 'X', new OptionalItemStack(ItemList.scaleItem));
		addRecipe(new OptionalItemStack(ItemList.scaleArmorChest), new String[]{"X X","XXX","XXX"}, 'X', new OptionalItemStack(ItemList.scaleItem));
		addRecipe(new OptionalItemStack(ItemList.scaleArmorLeg), new String[]{"XXX","X X","X X"}, 'X', new OptionalItemStack(ItemList.scaleItem));
		addRecipe(new OptionalItemStack(ItemList.scaleArmorBoots), new String[]{"   ","X X","X X"}, 'X', new OptionalItemStack(ItemList.scaleItem));

		addRecipe(new OptionalItemStack(ItemList.goldScaleArmorHead), new String[]{"XXX","XYX","   "}, new char[]{'X','Y'},new OptionalItemStack(Item.ingotGold), new OptionalItemStack(ItemList.scaleArmorHead) );
		addRecipe(new OptionalItemStack(ItemList.goldScaleArmorChest), new String[]{"XYX","XXX","XXX"}, new char[]{'X','Y'},new OptionalItemStack(Item.ingotGold), new OptionalItemStack(ItemList.scaleArmorChest) );
		addRecipe(new OptionalItemStack(ItemList.goldScaleArmorLeg), new String[]{"XXX","XYX","X X"}, new char[]{'X','Y'},new OptionalItemStack(Item.ingotGold), new OptionalItemStack(ItemList.scaleArmorLeg) );
		addRecipe(new OptionalItemStack(ItemList.goldScaleArmorBoots), new String[]{"   ","XYX","X X"}, new char[]{'X','Y'},new OptionalItemStack(Item.ingotGold), new OptionalItemStack(ItemList.scaleArmorBoots) );
		
		addRecipe(new OptionalItemStack(ItemList.ironScaleArmorHead), new String[]{"XXX","XYX","   "}, new char[]{'X','Y'},new OptionalItemStack(Item.ingotIron), new OptionalItemStack(ItemList.scaleArmorHead) );
		addRecipe(new OptionalItemStack(ItemList.ironScaleArmorChest), new String[]{"XYX","XXX","XXX"}, new char[]{'X','Y'},new OptionalItemStack(Item.ingotIron), new OptionalItemStack(ItemList.scaleArmorChest) );
		addRecipe(new OptionalItemStack(ItemList.ironScaleArmorLeg), new String[]{"XXX","XYX","X X"}, new char[]{'X','Y'},new OptionalItemStack(Item.ingotIron), new OptionalItemStack(ItemList.scaleArmorLeg) );
		addRecipe(new OptionalItemStack(ItemList.ironScaleArmorBoots), new String[]{"   ","XYX","X X"}, new char[]{'X','Y'},new OptionalItemStack(Item.ingotIron), new OptionalItemStack(ItemList.scaleArmorBoots) );

		addRecipe(new OptionalItemStack(ItemList.diamondScaleArmorHead), new String[]{"XXX","XYX","   "}, new char[]{'X','Y'},new OptionalItemStack(Item.diamond), new OptionalItemStack(ItemList.scaleArmorHead) );
		addRecipe(new OptionalItemStack(ItemList.diamondScaleArmorChest), new String[]{"XYX","XXX","XXX"}, new char[]{'X','Y'},new OptionalItemStack(Item.diamond), new OptionalItemStack(ItemList.scaleArmorChest) );
		addRecipe(new OptionalItemStack(ItemList.diamondScaleArmorLeg), new String[]{"XXX","XYX","X X"}, new char[]{'X','Y'},new OptionalItemStack(Item.diamond), new OptionalItemStack(ItemList.scaleArmorLeg) );
		addRecipe(new OptionalItemStack(ItemList.diamondScaleArmorBoots), new String[]{"   ","XYX","X X"}, new char[]{'X','Y'},new OptionalItemStack(Item.diamond), new OptionalItemStack(ItemList.scaleArmorBoots) );

		addRecipe(new OptionalItemStack(ItemList.redClothHead), new String[]{"XXX","X X","   "}, new char[]{'X'}, new OptionalItemStack(Block.cloth,1,14) );
		addRecipe(new OptionalItemStack(ItemList.redClothChest), new String[]{"X X","XXX","XXX"}, new char[]{'X'},new OptionalItemStack(Block.cloth,1,14) );
		addRecipe(new OptionalItemStack(ItemList.redClothLeg), new String[]{"XXX","X X","X X"}, new char[]{'X'},new OptionalItemStack(Block.cloth,1,14) );
		addRecipe(new OptionalItemStack(ItemList.redClothBoots), new String[]{"   ","X X","X X"}, new char[]{'X'},new OptionalItemStack(Block.cloth,1,14) );

		addRecipe(new OptionalItemStack(ItemList.greenClothHead), new String[]{"XXX","X X","   "}, new char[]{'X'}, new OptionalItemStack(Block.cloth,1,13) );
		addRecipe(new OptionalItemStack(ItemList.greenClothChest), new String[]{"X X","XXX","XXX"}, new char[]{'X'},new OptionalItemStack(Block.cloth,1,13) );
		addRecipe(new OptionalItemStack(ItemList.greenClothLeg), new String[]{"XXX","X X","X X"}, new char[]{'X'},new OptionalItemStack(Block.cloth,1,13) );
		addRecipe(new OptionalItemStack(ItemList.greenClothBoots), new String[]{"   ","X X","X X"}, new char[]{'X'},new OptionalItemStack(Block.cloth,1,13) );
		addRecipe(new OptionalItemStack(ItemList.greenClothHead), new String[]{"XXX","X X","   "}, new char[]{'X'}, new OptionalItemStack(Block.cloth,1,5) );
		addRecipe(new OptionalItemStack(ItemList.greenClothChest), new String[]{"X X","XXX","XXX"}, new char[]{'X'},new OptionalItemStack(Block.cloth,1,5) );
		addRecipe(new OptionalItemStack(ItemList.greenClothLeg), new String[]{"XXX","X X","X X"}, new char[]{'X'},new OptionalItemStack(Block.cloth,1,5) );
		addRecipe(new OptionalItemStack(ItemList.greenClothBoots), new String[]{"   ","X X","X X"}, new char[]{'X'},new OptionalItemStack(Block.cloth,1,5) );
		
		addRecipe(new OptionalItemStack(ItemList.blueClothHead), new String[]{"XXX","X X","   "}, new char[]{'X'}, new OptionalItemStack(Block.cloth,1,11) );
		addRecipe(new OptionalItemStack(ItemList.blueClothChest), new String[]{"X X","XXX","XXX"}, new char[]{'X'},new OptionalItemStack(Block.cloth,1,11) );
		addRecipe(new OptionalItemStack(ItemList.blueClothLeg), new String[]{"XXX","X X","X X"}, new char[]{'X'},new OptionalItemStack(Block.cloth,1,11) );
		addRecipe(new OptionalItemStack(ItemList.blueClothBoots), new String[]{"   ","X X","X X"}, new char[]{'X'},new OptionalItemStack(Block.cloth,1,11) );
		addRecipe(new OptionalItemStack(ItemList.blueClothHead), new String[]{"XXX","X X","   "}, new char[]{'X'}, new OptionalItemStack(Block.cloth,1,3) );
		addRecipe(new OptionalItemStack(ItemList.blueClothChest), new String[]{"X X","XXX","XXX"}, new char[]{'X'},new OptionalItemStack(Block.cloth,1,3) );
		addRecipe(new OptionalItemStack(ItemList.blueClothLeg), new String[]{"XXX","X X","X X"}, new char[]{'X'},new OptionalItemStack(Block.cloth,1,3) );
		addRecipe(new OptionalItemStack(ItemList.blueClothBoots), new String[]{"   ","X X","X X"}, new char[]{'X'},new OptionalItemStack(Block.cloth,1,3) );
		
		addRecipe(new OptionalItemStack(ItemList.cactusArmorHead), new String[]{"XXX","X X","   "}, new char[]{'X'}, new OptionalItemStack(Block.cactus) );
		addRecipe(new OptionalItemStack(ItemList.cactusArmorChest), new String[]{"X X","XXX","XXX"}, new char[]{'X'},new OptionalItemStack(Block.cactus) );
		addRecipe(new OptionalItemStack(ItemList.cactusArmorLeg), new String[]{"XXX","X X","X X"}, new char[]{'X'},new OptionalItemStack(Block.cactus) );
		addRecipe(new OptionalItemStack(ItemList.cactusArmorBoots), new String[]{"   ","X X","X X"}, new char[]{'X'},new OptionalItemStack(Block.cactus) );
		
		addRecipe(new OptionalItemStack(ItemList.furArmorHead), new String[]{"XXX","X X","   "}, new char[]{'X'}, new OptionalItemStack(ItemList.furPelt));
		addRecipe(new OptionalItemStack(ItemList.furArmorChest), new String[]{"X X","XXX","XXX"}, new char[]{'X'},new OptionalItemStack(ItemList.furPelt) );
		addRecipe(new OptionalItemStack(ItemList.furArmorLeg), new String[]{"XXX","X X","X X"}, new char[]{'X'},new OptionalItemStack(ItemList.furPelt) );
		addRecipe(new OptionalItemStack(ItemList.furArmorBoots), new String[]{"   ","X X","X X"}, new char[]{'X'},new OptionalItemStack(ItemList.furPelt) );
	}

	public static void shapelessOreRecipe(OptionalItemStack result, OptionalItemStack... component){
		if(!result.isPresent()){
			return;
		}
		for (OptionalItemStack pairCharParam : component) {
			if(!pairCharParam.isPresent()){
				return;
			}
		}
		Object[] objectComp = new Object[component.length];
		for (int i = 0; i < objectComp.length; i++) {
			objectComp[i] = component[i].createRecipeObject();
		}
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(result.createItemStack(), objectComp));
	}
	
	public static void shapedOreRecipe(OptionalItemStack result, boolean mirrored, String[] craftingRecipe, char[] compChar, OptionalItemStack... component){
		if(!result.isPresent()){
			return;
		}
		for (OptionalItemStack pairCharParam : component) {
			if(!pairCharParam.isPresent()){
				return;
			}
		}
		
		Object[] objectComponents = new Object[craftingRecipe.length + compChar.length*2];
		for (int i = 0; i < craftingRecipe.length; i++) {
			objectComponents[i] = craftingRecipe[i];
		}
		for (int i = 0; i < component.length; i++) {
			objectComponents[i*2+craftingRecipe.length] = compChar[i];
			objectComponents[i*2+craftingRecipe.length+1] = component[i].createRecipeObject();
		}
		
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(result.createItemStack(), mirrored, objectComponents));
	}
	
	public static void addSmelting(OptionalItemStack result, int xp, OptionalItemStack component){
		if(!result.isPresent() || !component.isPresent()){
			return;
		}
		GameRegistry.addSmelting(component.getID(), result.createItemStack(), xp);
	}
	
	public static void addShapelessRecipe(OptionalItemStack result, OptionalItemStack... component){
		if(!result.isPresent()){
			return;
		}
		for (OptionalItemStack optionalItemStack : component) {
			if(!optionalItemStack.isPresent()){
				return;
			}
		}
		Object[] itemStackComp = new ItemStack[component.length];
		for (int i = 0; i < itemStackComp.length; i++) {
			itemStackComp[i] = component[i].createItemStack();
		}
		GameRegistry.addShapelessRecipe(result.createItemStack(), itemStackComp);
	}
	
	public static void addRecipe(OptionalItemStack result, String[] craftingRecipe, char compChar, OptionalItemStack component){
		if(!result.isPresent() || !component.isPresent()){
			return;
		}
		addRecipe(result.createItemStack(), craftingRecipe,	new char[]{compChar}, component.createItemStack());
	}
	
	public static void addRecipe(OptionalItemStack result, String[] craftingRecipe, char[] compChar, OptionalItemStack... component){
		if(!result.isPresent()){
			return;
		}
		for (OptionalItemStack pairCharParam : component) {
			if(!pairCharParam.isPresent())
				return;
		}
		ItemStack[] itemStackComponents = new ItemStack[component.length];
		for (int i = 0; i < component.length; i++) {
			itemStackComponents[i] = component[i].createItemStack();
		}
		addRecipe(result.createItemStack(), craftingRecipe, compChar, itemStackComponents);
	}
	
	public static void addRecipe(ItemStack itemResult, String[] craftingRecipe, char[] compChar, ItemStack... component){
		Object[] componentList = new Object[craftingRecipe.length + compChar.length*2];
		for (int i = 0; i < craftingRecipe.length; i++) {
			componentList[i] = craftingRecipe[i];
		}
		for (int i = 0; i < compChar.length; i++) {
			componentList[i*2+craftingRecipe.length] = compChar[i];
			componentList[i*2+craftingRecipe.length+1] = component[i];
		}
		GameRegistry.addRecipe(itemResult, componentList);
	}
}