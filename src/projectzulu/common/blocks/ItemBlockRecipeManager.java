package projectzulu.common.blocks;

import static net.minecraftforge.common.ChestGenHooks.DUNGEON_CHEST;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.oredict.ShapedOreRecipe;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.BlockList;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ItemGenerics;
import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemBlockRecipeManager {

	public static void setupBlockModuleRecipies(){
		if( Loader.isModLoaded("ExtrabiomesXL") ){
			setupExtrabiomesXLRecipies();
		}
		setupCampfireRecipies();
		setupArmorRecipies();

		/* Palm Tree Recipies */
		if(BlockList.palmTreeLog.isPresent()){
			GameRegistry.addSmelting(BlockList.palmTreeLog.get().blockID, new ItemStack(Item.coal), 0);
		}
		if(BlockList.palmTreePlank.isPresent() && BlockList.palmTreeLog.isPresent()){
			GameRegistry.addShapelessRecipe(new ItemStack(BlockList.palmTreePlank.get(), 4), new ItemStack(BlockList.palmTreeLog.get()));
		}
		if(BlockList.palmTreeDoubleSlab.isPresent() && BlockList.palmTreeSlab.isPresent()){
			Block palmTreeDoubleSlab = BlockList.palmTreeDoubleSlab.get();
			Block palmTreeSlab = BlockList.palmTreeSlab.get();
			GameRegistry.addRecipe(new ItemStack(palmTreeDoubleSlab, 1), new Object [] {"X  ","X  ","   ", 'X', palmTreeSlab});
			GameRegistry.addRecipe(new ItemStack(palmTreeDoubleSlab, 1), new Object [] {" X "," X ","   ", 'X', palmTreeSlab});
			GameRegistry.addRecipe(new ItemStack(palmTreeDoubleSlab, 1), new Object [] {"  X","  X","   ", 'X', palmTreeSlab});
			GameRegistry.addRecipe(new ItemStack(palmTreeDoubleSlab, 1), new Object [] {"   ","X  ","X  ", 'X', palmTreeSlab});
			GameRegistry.addRecipe(new ItemStack(palmTreeDoubleSlab, 1), new Object [] {"   "," X "," X ", 'X', palmTreeSlab});
			GameRegistry.addRecipe(new ItemStack(palmTreeDoubleSlab, 1), new Object [] {"   ","  X","  X", 'X', palmTreeSlab});
		}
		if(BlockList.palmTreeLog.isPresent()){
			GameRegistry.addShapelessRecipe(new ItemStack(BlockList.palmTreePlank.get(), 4), new ItemStack(BlockList.palmTreeLog.get()));
		}

		if(BlockList.jasper.isPresent()){
			GameRegistry.addRecipe(new ItemStack(BlockList.jasper.get(), 1), new Object [] {
				"XXX","XCX","XXX", 'C', Block.sand,'X',new ItemStack(Item.dyePowder, 1, 1)});
			GameRegistry.addRecipe(new ItemStack(BlockList.jasper.get(), 1), new Object [] {
				"XXX","XCX","XXX", 'C', Block.sand,'X',Item.redstone});
			ChestGenHooks.getInfo(DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(BlockList.jasper.get()), 5,1,1));
		}
		if(ItemList.coconutShell.isPresent()){
			GameRegistry.addRecipe(new ItemStack(Item.bowlEmpty, 1), new Object [] {"   ","X X"," X ", 'X', ItemList.coconutShell.get()});
		}
		if(ItemList.coconutSeed.isPresent() && ItemList.coconutItem.isPresent()){
			GameRegistry.registerCraftingHandler(coconutItemCraftingHandler);
			Item coconutItem = ItemList.coconutItem.get();
			Item coconutSeed = ItemList.coconutSeed.get();
			GameRegistry.addRecipe(new ItemStack(coconutSeed), new Object[]{
				" X "," Y ","   ", 'X', new ItemStack(Item.swordWood,1,-1), 'Y', coconutItem });
			GameRegistry.addRecipe(new ItemStack(coconutSeed), new Object[]{
				" X "," Y ","   ", 'X', new ItemStack(Item.swordStone,1,-1), 'Y', coconutItem});
			GameRegistry.addRecipe(new ItemStack(coconutSeed), new Object[]{
				" X "," Y ","   ", 'X', new ItemStack(Item.swordSteel,1,-1), 'Y', coconutItem});
			GameRegistry.addRecipe(new ItemStack(coconutSeed), new Object[]{
				" X "," Y ","   ", 'X', new ItemStack(Item.swordGold,1,-1), 'Y', coconutItem});
			GameRegistry.addRecipe(new ItemStack(coconutSeed), new Object[]{
				" X "," Y ","   ", 'X', new ItemStack(Item.swordDiamond,1,-1), 'Y', coconutItem});
		}
		
		/*Aloe Vera */
		if(BlockList.tumbleweed.isPresent()){
			GameRegistry.addRecipe(new ItemStack(Item.silk, 6), new Object [] {"C  ","C  ","C  ", 'C', BlockList.tumbleweed.get()});
			GameRegistry.addRecipe(new ItemStack(Item.silk, 6), new Object [] {" C "," C "," C ", 'C', BlockList.tumbleweed.get()});
			GameRegistry.addRecipe(new ItemStack(Item.silk, 6), new Object [] {"  C","  C","  C", 'C', BlockList.tumbleweed.get()});
		}
		if(BlockList.aloeVera.isPresent() && BlockList.tumbleweed.isPresent()){
			GameRegistry.addShapelessRecipe(new ItemStack(BlockList.aloeVera.get(), 2), new ItemStack(BlockList.tumbleweed.get()));
		}
		
		/**
		 *  Misc Generic Craftables 
		 */
		if(ItemList.genericCraftingItems1.isPresent()){
			/* 3 Tusks --> Spike */
			if(BlockList.spike.isPresent()){
				GameRegistry.addRecipe(new ItemStack(BlockList.spike.get()), new Object[]{
					"   ","   ","TTT", 'T', new ItemStack(ItemList.genericCraftingItems1.get(), 1, ItemGenerics.Properties.Tusk.meta()) });
			}
			
			/* 4 Raw Fiber --> String  */
			GameRegistry.addShapelessRecipe(new ItemStack(Item.silk),
					new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.RawFiber.meta()),
					new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.RawFiber.meta()),
					new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.RawFiber.meta()),
					new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.RawFiber.meta()));
			
			/* Paper --> Pulp */
			GameRegistry.addShapelessRecipe(new ItemStack(ItemList.genericCraftingItems1.get(),2,ItemGenerics.Properties.Pulp.meta()),
					new ItemStack(Item.paper));
			/*   Cactus + Pulp = Thorns   					:   Prickly Powder */
			GameRegistry.addShapelessRecipe(new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.PricklyPowder.meta()),
					new ItemStack(Block.cactus), new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.Pulp.meta()));
			/*   Milk + Pulp + Black Lichen  = Cleanse  					:   Powder Slush */
			GameRegistry.addShapelessRecipe(new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.PowderSlush.meta()),
					new ItemStack(Item.bucketMilk), new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.Pulp.meta()), 
					new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.BlackLichen.meta()));
			/*   Milk + Pulp + Ectoplasm = Curse   			:   Glowing Goo */
			GameRegistry.addShapelessRecipe(new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.GlowingGoo.meta()),
					new ItemStack(Item.bucketMilk), new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.Pulp.meta()),
					new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.Ectoplasm.meta()));
			/*   Powder Slush + Ectoplasm = Curse   			:   Glowing Goo */
			GameRegistry.addShapelessRecipe(new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.GlowingGoo.meta()),
					new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.PowderSlush.meta()),
					new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.Ectoplasm.meta()));
			/*   Salt + Small heart = DigSpeef-  			:   Small Unhealthy Heart */
			GameRegistry.addShapelessRecipe(new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.SmallUnhealthyHeart.meta()),
					new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.SmallHeart.meta()),
					new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.Salt.meta()));
			/*   Salt + Large heart = Strength-  			:   Large Unhealthy Heart */
			GameRegistry.addShapelessRecipe(new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.LargeUnhealthyHeart.meta()),
					new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.LargeHeart.meta()),
					new ItemStack(ItemList.genericCraftingItems1.get(),1,ItemGenerics.Properties.Salt.meta()));
		}
		
		if(ItemList.furPelt.isPresent()){
			if(ItemList.genericCraftingItems1.isPresent()){
				GameRegistry.registerCraftingHandler(new RawFiberCraftingHandler());
			}
			GameRegistry.addShapelessRecipe(new ItemStack(Item.leather), new ItemStack(ItemList.furPelt.get()));
		}
		
		if(!ProjectZulu_Core.replaceFlowerPot && BlockList.universalFlowerPot.isPresent()){
			GameRegistry.addShapelessRecipe(new ItemStack(Item.flowerPot), new ItemStack(BlockList.universalFlowerPot.get()));
			GameRegistry.addShapelessRecipe(new ItemStack(BlockList.universalFlowerPot.get()), new ItemStack(Item.flowerPot));
		}

	}

	private static void setupExtrabiomesXLRecipies(){
		if(BlockList.campfire.isPresent()){
			int campfireBlockID = BlockList.campfire.get().blockID;
			/* Red Cobblestone Versions of Campfire Recipies*/
			/* No Fire Stone Campfire*/	
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(campfireBlockID,1,1), true, new Object[]{
				"   ","LLL","CCC", 
				Character.valueOf('L'), "log",
				Character.valueOf('C'), "cobbleRed" }));		

			/* On Fire Log Campfire*/
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(campfireBlockID,1,2), true, new Object[]{
				" S ","LLL","   ", 
				Character.valueOf('L'), "log",
				Character.valueOf('C'), "cobbleRed",
				Character.valueOf('S'), Item.stick }));

			/* On Fire Stone Campfire*/
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(campfireBlockID,1,3), true, new Object[]{
				" S ","LLL","CCC", 
				Character.valueOf('L'), "log",
				Character.valueOf('C'), "cobbleRed",
				Character.valueOf('S'), Item.stick }));
		}
	}

	private static void setupCampfireRecipies() {
		if(BlockList.campfire.isPresent()){
			int campfireBlockID = BlockList.campfire.get().blockID;
			/* No Fire Log Campfire*/
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(campfireBlockID,1,0), true, new Object[]{
				"   ","LLL","   ", 
				Character.valueOf('L'), new ItemStack(Block.wood, 1, -1) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(campfireBlockID,1,0), true, new Object[]{
				"   ","LLL","   ", 
				Character.valueOf('L'), "log" }));

			/* No Fire Stone Campfire*/
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(campfireBlockID,1,1), true, new Object[]{
				"   ","LLL","CCC", 
				Character.valueOf('L'), new ItemStack(Block.wood, 1, -1),
				Character.valueOf('C'), new ItemStack(Block.cobblestone) }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(campfireBlockID,1,1), true, new Object[]{
				"   ","LLL","CCC", 
				Character.valueOf('L'), "log",
				Character.valueOf('C'), new ItemStack(Block.cobblestone) }));

			/* On Fire Log Campfire*/
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(campfireBlockID,1,2), true, new Object[]{
				" S ","LLL","   ", 
				Character.valueOf('L'), new ItemStack(Block.wood, 1, -1),
				Character.valueOf('C'), new ItemStack(Block.cobblestone),
				Character.valueOf('S'), Item.stick }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(campfireBlockID,1,2), true, new Object[]{
				" S ","LLL","   ", 
				Character.valueOf('L'), "log",
				Character.valueOf('C'), new ItemStack(Block.cobblestone),
				Character.valueOf('S'), Item.stick }));

			/* On Fire Stone Campfire*/
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(campfireBlockID,1,3), true, new Object[]{
				" S ","LLL","CCC", 
				Character.valueOf('L'), new ItemStack(Block.wood, 1, -1),
				Character.valueOf('C'), new ItemStack(Block.cobblestone),
				Character.valueOf('S'), Item.stick }));
			CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(campfireBlockID,1,3), true, new Object[]{
				" S ","LLL","CCC", 
				Character.valueOf('L'), "log",
				Character.valueOf('C'), new ItemStack(Block.cobblestone),
				Character.valueOf('S'), Item.stick }));
		}
	}

	private static void setupArmorRecipies(){

		if(ItemList.scaleItem.isPresent()){
			if(ItemList.scaleArmorHead.isPresent()){
				GameRegistry.addRecipe(new ItemStack(ItemList.scaleArmorHead.get()), new Object[]{"XXX","X X","   ", 'X', ItemList.scaleItem.get()});
			}
			if(ItemList.scaleArmorChest.isPresent()){
				GameRegistry.addRecipe(new ItemStack(ItemList.scaleArmorChest.get()), new Object[]{"X X","XXX","XXX", 'X', ItemList.scaleItem.get()});
			}
			if(ItemList.scaleArmorLeg.isPresent()){
				GameRegistry.addRecipe(new ItemStack(ItemList.scaleArmorLeg.get()), new Object[]{"XXX","X X","X X", 'X', ItemList.scaleItem.get()});
			}
			if(ItemList.scaleArmorBoot.isPresent()){
				GameRegistry.addRecipe(new ItemStack(ItemList.scaleArmorBoot.get()), new Object[]{"   ","X X","X X", 'X', ItemList.scaleItem.get()});
			}
		}

		if(ItemList.goldScaleArmorHead.isPresent() && ItemList.scaleArmorHead.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemList.goldScaleArmorHead.get()), new Object[]{"XXX","XYX","   ", 'X', Item.ingotGold,'Y',new ItemStack(ItemList.scaleArmorHead.get()) });
		}
		if(ItemList.goldScaleArmorChest.isPresent() && ItemList.scaleArmorChest.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemList.goldScaleArmorChest.get()), new Object[]{"XYX","XXX","XXX", 'X', Item.ingotGold,'Y',new ItemStack(ItemList.scaleArmorChest.get())} );
		}
		if(ItemList.goldScaleArmorLeg.isPresent() && ItemList.scaleArmorLeg.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemList.goldScaleArmorLeg.get()), new Object[]{"XXX","XYX","X X", 'X', Item.ingotGold,'Y',new ItemStack(ItemList.scaleArmorLeg.get())} );
		}
		if(ItemList.goldScaleArmorBoot.isPresent() && ItemList.scaleArmorBoot.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemList.goldScaleArmorBoot.get()), new Object[]{"   ","XYX","X X", 'X', Item.ingotGold,'Y',new ItemStack(ItemList.scaleArmorBoot.get())} );
		}

		if(ItemList.ironScaleArmorHead.isPresent() && ItemList.scaleArmorHead.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemList.ironScaleArmorHead.get()), new Object[]{"XXX","XYX","   ", 'X', Item.ingotIron,'Y',new ItemStack(ItemList.scaleArmorHead.get()) });
		}
		if(ItemList.ironScaleArmorChest.isPresent() && ItemList.scaleArmorChest.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemList.ironScaleArmorChest.get()), new Object[]{"XYX","XXX","XXX", 'X', Item.ingotIron,'Y',new ItemStack(ItemList.scaleArmorChest.get())} );
		}
		if(ItemList.ironScaleArmorLeg.isPresent() && ItemList.scaleArmorLeg.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemList.ironScaleArmorLeg.get()), new Object[]{"XXX","XYX","X X", 'X', Item.ingotIron,'Y',new ItemStack(ItemList.scaleArmorLeg.get())} );
		}
		if(ItemList.ironScaleArmorBoot.isPresent() && ItemList.scaleArmorBoot.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemList.ironScaleArmorBoot.get()), new Object[]{"   ","XYX","X X", 'X', Item.ingotIron,'Y',new ItemStack(ItemList.scaleArmorBoot.get())} );
		}

		if(ItemList.diamondScaleArmorHead.isPresent() && ItemList.scaleArmorHead.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemList.diamondScaleArmorHead.get()), new Object[]{"XXX","XYX","   ", 'X', Item.diamond,'Y',new ItemStack(ItemList.scaleArmorHead.get()) });
		}
		if(ItemList.diamondScaleArmorChest.isPresent() && ItemList.scaleArmorChest.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemList.diamondScaleArmorChest.get()), new Object[]{"XYX","XXX","XXX", 'X', Item.diamond,'Y',new ItemStack(ItemList.scaleArmorChest.get())} );
		}
		if(ItemList.diamondScaleArmorLeg.isPresent() && ItemList.scaleArmorLeg.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemList.diamondScaleArmorLeg.get()), new Object[]{"XXX","XYX","X X", 'X', Item.diamond,'Y',new ItemStack(ItemList.scaleArmorLeg.get())} );
		}
		if(ItemList.diamondScaleArmorBoot.isPresent() && ItemList.scaleArmorBoot.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemList.diamondScaleArmorBoot.get()), new Object[]{"   ","XYX","X X", 'X', Item.diamond,'Y',new ItemStack(ItemList.scaleArmorBoot.get())} );
		}


		if(ItemList.whiteClothHead.isPresent() ){
			GameRegistry.addRecipe(new ItemStack(ItemList.whiteClothHead.get()), new Object[]{"XXX","X X","   ", 'X', new ItemStack(Block.cloth, 1, 0)});
		}
		if(ItemList.whiteClothChest.isPresent() ){
			GameRegistry.addRecipe(new ItemStack(ItemList.whiteClothChest.get()), new Object[]{"X X","XXX","XXX", 'X', new ItemStack(Block.cloth, 1, 0)});
		}
		if(ItemList.whiteClothLeg.isPresent() ){
			GameRegistry.addRecipe(new ItemStack(ItemList.whiteClothLeg.get()), new Object[]{"XXX","X X","X X", 'X', new ItemStack(Block.cloth, 1, 0)});
		}
		if(ItemList.whiteClothBoot.isPresent() ){
			GameRegistry.addRecipe(new ItemStack(ItemList.whiteClothBoot.get()), new Object[]{"   ","X X","X X", 'X', new ItemStack(Block.cloth, 1, 0)});
		}

		if(ItemList.redClothHead.isPresent() ){
			GameRegistry.addRecipe(new ItemStack(ItemList.redClothHead.get()), new Object[]{"XXX","X X","   ", 'X', new ItemStack(Block.cloth, 1, 14)});
		}
		if(ItemList.redClothChest.isPresent() ){
			GameRegistry.addRecipe(new ItemStack(ItemList.redClothChest.get()), new Object[]{"X X","XXX","XXX", 'X', new ItemStack(Block.cloth, 1, 14)});
		}
		if(ItemList.redClothLeg.isPresent() ){
			GameRegistry.addRecipe(new ItemStack(ItemList.redClothLeg.get()), new Object[]{"XXX","X X","X X", 'X', new ItemStack(Block.cloth, 1, 14)});
		}
		if(ItemList.redClothBoot.isPresent() ){
			GameRegistry.addRecipe(new ItemStack(ItemList.redClothBoot.get()), new Object[]{"   ","X X","X X", 'X', new ItemStack(Block.cloth, 1, 14)});
		}

		if(ItemList.greenClothHead.isPresent() ){
			Item greenClothHead = ItemList.greenClothHead.get(); 
			GameRegistry.addRecipe(new ItemStack(greenClothHead), new Object[]{"XXX","X X","   ", 'X', new ItemStack(Block.cloth, 1, 13)});
			GameRegistry.addRecipe(new ItemStack(greenClothHead), new Object[]{"XXX","X X","   ", 'X', new ItemStack(Block.cloth, 1, 5)});
		}
		if(ItemList.greenClothChest.isPresent() ){
			Item greenClothChest = ItemList.greenClothChest.get(); 
			GameRegistry.addRecipe(new ItemStack(greenClothChest), new Object[]{"X X","XXX","XXX", 'X', new ItemStack(Block.cloth, 1, 13)});
			GameRegistry.addRecipe(new ItemStack(greenClothChest), new Object[]{"X X","XXX","XXX", 'X', new ItemStack(Block.cloth, 1, 5)});
		}
		if(ItemList.greenClothLeg.isPresent() ){
			Item greenClothLeg = ItemList.greenClothLeg.get(); 
			GameRegistry.addRecipe(new ItemStack(greenClothLeg), new Object[]{"XXX","X X","X X", 'X', new ItemStack(Block.cloth, 1, 13)});
			GameRegistry.addRecipe(new ItemStack(greenClothLeg), new Object[]{"XXX","X X","X X", 'X', new ItemStack(Block.cloth, 1, 5)});
		}
		if(ItemList.greenClothBoot.isPresent() ){
			Item greenClothBoot = ItemList.greenClothBoot.get(); 
			GameRegistry.addRecipe(new ItemStack(greenClothBoot), new Object[]{"   ","X X","X X", 'X', new ItemStack(Block.cloth, 1, 13)});
			GameRegistry.addRecipe(new ItemStack(greenClothBoot), new Object[]{"   ","X X","X X", 'X', new ItemStack(Block.cloth, 1, 5)});
		}

		if(ItemList.blueClothHead.isPresent() ){
			Item blueClothHead = ItemList.blueClothHead.get(); 
			GameRegistry.addRecipe(new ItemStack(blueClothHead), new Object[]{"XXX","X X","   ", 'X', new ItemStack(Block.cloth, 1, 11)});
			GameRegistry.addRecipe(new ItemStack(blueClothHead), new Object[]{"XXX","X X","   ", 'X', new ItemStack(Block.cloth, 1, 3)});
		}
		if(ItemList.blueClothChest.isPresent() ){
			Item blueClothChest = ItemList.blueClothChest.get(); 
			GameRegistry.addRecipe(new ItemStack(blueClothChest), new Object[]{"X X","XXX","XXX", 'X', new ItemStack(Block.cloth, 1, 11)});
			GameRegistry.addRecipe(new ItemStack(blueClothChest), new Object[]{"X X","XXX","XXX", 'X', new ItemStack(Block.cloth, 1, 3)});
		}
		if(ItemList.blueClothLeg.isPresent() ){
			Item blueClothLeg = ItemList.blueClothLeg.get(); 
			GameRegistry.addRecipe(new ItemStack(blueClothLeg), new Object[]{"XXX","X X","X X", 'X', new ItemStack(Block.cloth, 1, 11)});
			GameRegistry.addRecipe(new ItemStack(blueClothLeg), new Object[]{"XXX","X X","X X", 'X', new ItemStack(Block.cloth, 1, 3)});
		}
		if(ItemList.blueClothBoot.isPresent() ){
			Item blueClothBoot = ItemList.blueClothBoot.get(); 
			GameRegistry.addRecipe(new ItemStack(blueClothBoot), new Object[]{"   ","X X","X X", 'X', new ItemStack(Block.cloth, 1, 11)});
			GameRegistry.addRecipe(new ItemStack(blueClothBoot), new Object[]{"   ","X X","X X", 'X', new ItemStack(Block.cloth, 1, 3)});
		}

		if(ItemList.cactusArmorHead.isPresent() ){
			Item cactusArmorHead = ItemList.cactusArmorHead.get(); 
			GameRegistry.addRecipe(new ItemStack(cactusArmorHead), new Object[]{"XXX","X X","   ", 'X', Block.cactus});
		}
		if(ItemList.cactusArmorChest.isPresent() ){
			Item cactusArmorChest = ItemList.cactusArmorChest.get(); 
			GameRegistry.addRecipe(new ItemStack(cactusArmorChest), new Object[]{"X X","XXX","XXX", 'X', Block.cactus});
		}
		if(ItemList.cactusArmorLeg.isPresent() ){
			Item cactusArmorLeg = ItemList.cactusArmorLeg.get(); 
			GameRegistry.addRecipe(new ItemStack(cactusArmorLeg), new Object[]{"XXX","X X","X X", 'X', Block.cactus});
		}
		if(ItemList.cactusArmorBoot.isPresent() ){
			Item cactusArmorBoot = ItemList.cactusArmorBoot.get(); 
			GameRegistry.addRecipe(new ItemStack(cactusArmorBoot), new Object[]{"   ","X X","X X", 'X', Block.cactus});
		}

		if(ItemList.furPelt.isPresent()){
			if(ItemList.furArmorHead.isPresent() ){
				Item furArmorHead = ItemList.furArmorHead.get(); 
				GameRegistry.addRecipe(new ItemStack(furArmorHead), new Object[]{"XXX","X X","   ", 'X', ItemList.furPelt.get()});
			}
			if(ItemList.furArmorChest.isPresent() ){
				Item furArmorChest = ItemList.furArmorChest.get(); 
				GameRegistry.addRecipe(new ItemStack(furArmorChest), new Object[]{"X X","XXX","XXX", 'X', ItemList.furPelt.get()});
			}
			if(ItemList.furArmorLeg.isPresent() ){
				Item furArmorLeg = ItemList.furArmorLeg.get(); 
				GameRegistry.addRecipe(new ItemStack(furArmorLeg), new Object[]{"XXX","X X","X X", 'X', ItemList.furPelt.get()});
			}
			if(ItemList.furArmorBoot.isPresent() ){
				Item furArmorBoot = ItemList.furArmorBoot.get();
				GameRegistry.addRecipe(new ItemStack(furArmorBoot), new Object[]{"   ","X X","X X", 'X', ItemList.furPelt.get()});
			}
		}
	}

	static ICraftingHandler coconutItemCraftingHandler = new ICraftingHandler() {
		@Override
		public void onSmelting(EntityPlayer player, ItemStack item) {}

		@Override
		public void onCrafting(EntityPlayer player, ItemStack item,
				IInventory craftMatrix) {

			/*
			 * This onCrafting Handle is for Sword and Coconut Recipe
			 * 0-1-2
			 * 3-4-5
			 * 6-7-8
			 */
			ItemStack shouldBeSword = craftMatrix.getStackInSlot(1);
			ItemStack shouldBeCoconut = craftMatrix.getStackInSlot(4);

			if(shouldBeSword != null && shouldBeCoconut != null 
					&& (shouldBeSword.getItem().itemID == Item.swordWood.itemID || shouldBeSword.getItem().itemID == Item.swordStone.itemID 
					|| shouldBeSword.getItem().itemID == Item.swordSteel.itemID || shouldBeSword.getItem().itemID == Item.swordGold.itemID 
					|| shouldBeSword.getItem().itemID == Item.swordDiamond.itemID) 
					&& ItemList.coconutItem.isPresent() && shouldBeCoconut.getItem() == ItemList.coconutItem.get()){
				//Put Sword Back
				ItemStack tempItem1 = new ItemStack(shouldBeSword.getItem(), 2, shouldBeSword.getItemDamage()+1);
				craftMatrix.setInventorySlotContents(1, tempItem1);
				//Place Coconut Milk
				if(ItemList.coconutMilkFragment.isPresent()){
					ItemStack tempItem2 = new ItemStack(ItemList.coconutMilkFragment.get(), 2);
					craftMatrix.setInventorySlotContents(3, tempItem2);
				}
				//Place Coconut Shell
				if(ItemList.coconutShell.isPresent()){
					ItemStack tempItem3 = new ItemStack(ItemList.coconutShell.get(), 2);
					craftMatrix.setInventorySlotContents(5, tempItem3);
				}
			}
		}
	};
}
