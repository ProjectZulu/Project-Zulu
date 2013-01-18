package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.oredict.ShapedOreRecipe;
import projectzulu.common.mod_ProjectZulu;
import projectzulu.common.api.ItemBlockList;
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
		if(ItemBlockList.palmTreeLog.isPresent()){
			GameRegistry.addSmelting(ItemBlockList.palmTreeLog.get().blockID, new ItemStack(Item.coal), 0);
		}
		if(ItemBlockList.palmTreePlank.isPresent() && ItemBlockList.palmTreeLog.isPresent()){
			GameRegistry.addShapelessRecipe(new ItemStack(ItemBlockList.palmTreePlank.get(), 4), new ItemStack(ItemBlockList.palmTreeLog.get()));
		}
		if(ItemBlockList.palmTreeDoubleSlab.isPresent() && ItemBlockList.palmTreeSlab.isPresent()){
			Block palmTreeDoubleSlab = ItemBlockList.palmTreeDoubleSlab.get();
			Block palmTreeSlab = ItemBlockList.palmTreeSlab.get();
			GameRegistry.addRecipe(new ItemStack(palmTreeDoubleSlab, 1), new Object [] {"X  ","X  ","   ", 'X', palmTreeSlab});
			GameRegistry.addRecipe(new ItemStack(palmTreeDoubleSlab, 1), new Object [] {" X "," X ","   ", 'X', palmTreeSlab});
			GameRegistry.addRecipe(new ItemStack(palmTreeDoubleSlab, 1), new Object [] {"  X","  X","   ", 'X', palmTreeSlab});
			GameRegistry.addRecipe(new ItemStack(palmTreeDoubleSlab, 1), new Object [] {"   ","X  ","X  ", 'X', palmTreeSlab});
			GameRegistry.addRecipe(new ItemStack(palmTreeDoubleSlab, 1), new Object [] {"   "," X "," X ", 'X', palmTreeSlab});
			GameRegistry.addRecipe(new ItemStack(palmTreeDoubleSlab, 1), new Object [] {"   ","  X","  X", 'X', palmTreeSlab});
		}
		if(ItemBlockList.palmTreeLog.isPresent()){
			GameRegistry.addShapelessRecipe(new ItemStack(ItemBlockList.palmTreePlank.get(), 4), new ItemStack(ItemBlockList.palmTreeLog.get()));
		}

		if(ItemBlockList.jasper.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.jasper.get(), 1), new Object [] {
				"XXX","XCX","XXX", 'C', Block.sand,'X',new ItemStack(Item.dyePowder, 1, 1)});
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.jasper.get(), 1), new Object [] {
				"XXX","XCX","XXX", 'C', Block.sand,'X',Item.redstone});
			DungeonHooks.addDungeonLoot(new ItemStack(ItemBlockList.jasper.get()), 5,1,1);
		}
		if(ItemBlockList.coconutShell.isPresent()){
			GameRegistry.addRecipe(new ItemStack(Item.bowlEmpty, 1), new Object [] {"   ","X X"," X ", 'X', ItemBlockList.coconutShell.get()});
		}
		if(ItemBlockList.coconutSeed.isPresent() && ItemBlockList.coconutItem.isPresent()){
			GameRegistry.registerCraftingHandler(coconutItemCraftingHandler);
			Item coconutItem = ItemBlockList.coconutItem.get();
			Item coconutSeed = ItemBlockList.coconutSeed.get();
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
		if(ItemBlockList.tumbleweed.isPresent()){
			GameRegistry.addRecipe(new ItemStack(Item.silk, 6), new Object [] {"C  ","C  ","C  ", 'C', ItemBlockList.tumbleweed.get()});
			GameRegistry.addRecipe(new ItemStack(Item.silk, 6), new Object [] {" C "," C "," C ", 'C', ItemBlockList.tumbleweed.get()});
			GameRegistry.addRecipe(new ItemStack(Item.silk, 6), new Object [] {"  C","  C","  C", 'C', ItemBlockList.tumbleweed.get()});
		}
		if(ItemBlockList.aloeVera.isPresent() && ItemBlockList.tumbleweed.isPresent()){
			GameRegistry.addShapelessRecipe(new ItemStack(ItemBlockList.aloeVera.get(), 2), new ItemStack(ItemBlockList.tumbleweed.get()));
		}
		
		/**
		 *  Misc Generic Craftables 
		 */
		if(ItemBlockList.genericCraftingItems1.isPresent()){
			/* 3 Tusks --> Spike */
			if(ItemBlockList.spike.isPresent()){
				GameRegistry.addRecipe(new ItemStack(ItemBlockList.spike.get()), new Object[]{
					"   ","   ","TTT", 'T', new ItemStack(ItemBlockList.genericCraftingItems1.get(), 1, ItemGenerics.Properties.Tusk.meta()) });
			}
			
			/* 4 Raw Fiber --> String  */
			GameRegistry.addShapelessRecipe(new ItemStack(Item.silk),
					new ItemStack(ItemBlockList.genericCraftingItems1.get(),1,ItemGenerics.Properties.RawFiber.meta()),
					new ItemStack(ItemBlockList.genericCraftingItems1.get(),1,ItemGenerics.Properties.RawFiber.meta()),
					new ItemStack(ItemBlockList.genericCraftingItems1.get(),1,ItemGenerics.Properties.RawFiber.meta()),
					new ItemStack(ItemBlockList.genericCraftingItems1.get(),1,ItemGenerics.Properties.RawFiber.meta()));
			
			/* Paper --> Pulp */
			GameRegistry.addShapelessRecipe(new ItemStack(ItemBlockList.genericCraftingItems1.get(),2,ItemGenerics.Properties.Pulp.meta()),
					new ItemStack(Item.paper));
			/*   Cactus + Pulp = Thorns   					:   Prickly Powder */
			GameRegistry.addShapelessRecipe(new ItemStack(ItemBlockList.genericCraftingItems1.get(),1,ItemGenerics.Properties.PricklyPowder.meta()),
					new ItemStack(Block.cactus), new ItemStack(ItemBlockList.genericCraftingItems1.get(),1,ItemGenerics.Properties.Pulp.meta()));
			/*   Milk + Pulp + Black Lichen  = Cleanse  					:   Powder Slush */
			GameRegistry.addShapelessRecipe(new ItemStack(ItemBlockList.genericCraftingItems1.get(),1,ItemGenerics.Properties.PowderSlush.meta()),
					new ItemStack(Item.bucketMilk), new ItemStack(ItemBlockList.genericCraftingItems1.get(),1,ItemGenerics.Properties.Pulp.meta()), 
					new ItemStack(ItemBlockList.genericCraftingItems1.get(),1,ItemGenerics.Properties.BlackLichen.meta()));
			/*   Milk + Pulp + Ectoplasm = Curse   			:   Glowing Goo */
			GameRegistry.addShapelessRecipe(new ItemStack(ItemBlockList.genericCraftingItems1.get(),1,ItemGenerics.Properties.GlowingGoo.meta()),
					new ItemStack(Item.bucketMilk), new ItemStack(ItemBlockList.genericCraftingItems1.get(),1,ItemGenerics.Properties.Pulp.meta()),
					new ItemStack(ItemBlockList.genericCraftingItems1.get(),1,ItemGenerics.Properties.Ectoplasm.meta()));
			/*   Powder Slush + Ectoplasm = Curse   			:   Glowing Goo */
			GameRegistry.addShapelessRecipe(new ItemStack(ItemBlockList.genericCraftingItems1.get(),1,ItemGenerics.Properties.GlowingGoo.meta()),
					new ItemStack(ItemBlockList.genericCraftingItems1.get(),1,ItemGenerics.Properties.PowderSlush.meta()),
					new ItemStack(ItemBlockList.genericCraftingItems1.get(),1,ItemGenerics.Properties.Ectoplasm.meta()));
			/*   Salt + Small heart = DigSpeef-  			:   Small Unhealthy Heart */
			GameRegistry.addShapelessRecipe(new ItemStack(ItemBlockList.genericCraftingItems1.get(),1,ItemGenerics.Properties.SmallUnhealthyHeart.meta()),
					new ItemStack(ItemBlockList.genericCraftingItems1.get(),1,ItemGenerics.Properties.SmallHeart.meta()),
					new ItemStack(ItemBlockList.genericCraftingItems1.get(),1,ItemGenerics.Properties.Salt.meta()));
			/*   Salt + Large heart = Strength-  			:   Large Unhealthy Heart */
			GameRegistry.addShapelessRecipe(new ItemStack(ItemBlockList.genericCraftingItems1.get(),1,ItemGenerics.Properties.LargeUnhealthyHeart.meta()),
					new ItemStack(ItemBlockList.genericCraftingItems1.get(),1,ItemGenerics.Properties.LargeHeart.meta()),
					new ItemStack(ItemBlockList.genericCraftingItems1.get(),1,ItemGenerics.Properties.Salt.meta()));
		}
		
		if(ItemBlockList.furPelt.isPresent()){
			if(ItemBlockList.genericCraftingItems1.isPresent()){
				GameRegistry.registerCraftingHandler(new RawFiberCraftingHandler());
			}
			GameRegistry.addShapelessRecipe(new ItemStack(Item.leather), new ItemStack(ItemBlockList.furPelt.get()));
		}
		
		if(!mod_ProjectZulu.replaceFlowerPot && ItemBlockList.universalFlowerPot.isPresent()){
			GameRegistry.addShapelessRecipe(new ItemStack(Item.flowerPot), new ItemStack(ItemBlockList.universalFlowerPot.get()));
			GameRegistry.addShapelessRecipe(new ItemStack(ItemBlockList.universalFlowerPot.get()), new ItemStack(Item.flowerPot));
		}

	}

	private static void setupExtrabiomesXLRecipies(){
		if(ItemBlockList.campfire.isPresent()){
			int campfireBlockID = ItemBlockList.campfire.get().blockID;
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
		if(ItemBlockList.campfire.isPresent()){
			int campfireBlockID = ItemBlockList.campfire.get().blockID;
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

		if(ItemBlockList.scaleItem.isPresent()){
			if(ItemBlockList.scaleArmorHead.isPresent()){
				GameRegistry.addRecipe(new ItemStack(ItemBlockList.scaleArmorHead.get()), new Object[]{"XXX","X X","   ", 'X', ItemBlockList.scaleItem.get()});
			}
			if(ItemBlockList.scaleArmorChest.isPresent()){
				GameRegistry.addRecipe(new ItemStack(ItemBlockList.scaleArmorChest.get()), new Object[]{"X X","XXX","XXX", 'X', ItemBlockList.scaleItem.get()});
			}
			if(ItemBlockList.scaleArmorLeg.isPresent()){
				GameRegistry.addRecipe(new ItemStack(ItemBlockList.scaleArmorLeg.get()), new Object[]{"XXX","X X","X X", 'X', ItemBlockList.scaleItem.get()});
			}
			if(ItemBlockList.scaleArmorBoot.isPresent()){
				GameRegistry.addRecipe(new ItemStack(ItemBlockList.scaleArmorBoot.get()), new Object[]{"   ","X X","X X", 'X', ItemBlockList.scaleItem.get()});
			}
		}

		if(ItemBlockList.goldScaleArmorHead.isPresent() && ItemBlockList.scaleArmorHead.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.goldScaleArmorHead.get()), new Object[]{"XXX","XYX","   ", 'X', Item.ingotGold,'Y',new ItemStack(ItemBlockList.scaleArmorHead.get()) });
		}
		if(ItemBlockList.goldScaleArmorChest.isPresent() && ItemBlockList.scaleArmorChest.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.goldScaleArmorChest.get()), new Object[]{"XYX","XXX","XXX", 'X', Item.ingotGold,'Y',new ItemStack(ItemBlockList.scaleArmorChest.get())} );
		}
		if(ItemBlockList.goldScaleArmorLeg.isPresent() && ItemBlockList.scaleArmorLeg.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.goldScaleArmorLeg.get()), new Object[]{"XXX","XYX","X X", 'X', Item.ingotGold,'Y',new ItemStack(ItemBlockList.scaleArmorLeg.get())} );
		}
		if(ItemBlockList.goldScaleArmorBoot.isPresent() && ItemBlockList.scaleArmorBoot.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.goldScaleArmorBoot.get()), new Object[]{"   ","XYX","X X", 'X', Item.ingotGold,'Y',new ItemStack(ItemBlockList.scaleArmorBoot.get())} );
		}

		if(ItemBlockList.ironScaleArmorHead.isPresent() && ItemBlockList.scaleArmorHead.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.ironScaleArmorHead.get()), new Object[]{"XXX","XYX","   ", 'X', Item.ingotIron,'Y',new ItemStack(ItemBlockList.scaleArmorHead.get()) });
		}
		if(ItemBlockList.ironScaleArmorChest.isPresent() && ItemBlockList.scaleArmorChest.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.ironScaleArmorChest.get()), new Object[]{"XYX","XXX","XXX", 'X', Item.ingotIron,'Y',new ItemStack(ItemBlockList.scaleArmorChest.get())} );
		}
		if(ItemBlockList.ironScaleArmorLeg.isPresent() && ItemBlockList.scaleArmorLeg.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.ironScaleArmorLeg.get()), new Object[]{"XXX","XYX","X X", 'X', Item.ingotIron,'Y',new ItemStack(ItemBlockList.scaleArmorLeg.get())} );
		}
		if(ItemBlockList.ironScaleArmorBoot.isPresent() && ItemBlockList.scaleArmorBoot.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.ironScaleArmorBoot.get()), new Object[]{"   ","XYX","X X", 'X', Item.ingotIron,'Y',new ItemStack(ItemBlockList.scaleArmorBoot.get())} );
		}

		if(ItemBlockList.diamondScaleArmorHead.isPresent() && ItemBlockList.scaleArmorHead.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.diamondScaleArmorHead.get()), new Object[]{"XXX","XYX","   ", 'X', Item.diamond,'Y',new ItemStack(ItemBlockList.scaleArmorHead.get()) });
		}
		if(ItemBlockList.diamondScaleArmorChest.isPresent() && ItemBlockList.scaleArmorChest.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.diamondScaleArmorChest.get()), new Object[]{"XYX","XXX","XXX", 'X', Item.diamond,'Y',new ItemStack(ItemBlockList.scaleArmorChest.get())} );
		}
		if(ItemBlockList.diamondScaleArmorLeg.isPresent() && ItemBlockList.scaleArmorLeg.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.diamondScaleArmorLeg.get()), new Object[]{"XXX","XYX","X X", 'X', Item.diamond,'Y',new ItemStack(ItemBlockList.scaleArmorLeg.get())} );
		}
		if(ItemBlockList.diamondScaleArmorBoot.isPresent() && ItemBlockList.scaleArmorBoot.isPresent()){
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.diamondScaleArmorBoot.get()), new Object[]{"   ","XYX","X X", 'X', Item.diamond,'Y',new ItemStack(ItemBlockList.scaleArmorBoot.get())} );
		}


		if(ItemBlockList.whiteClothHead.isPresent() ){
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.whiteClothHead.get()), new Object[]{"XXX","X X","   ", 'X', new ItemStack(Block.cloth, 1, 0)});
		}
		if(ItemBlockList.whiteClothChest.isPresent() ){
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.whiteClothChest.get()), new Object[]{"X X","XXX","XXX", 'X', new ItemStack(Block.cloth, 1, 0)});
		}
		if(ItemBlockList.whiteClothLeg.isPresent() ){
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.whiteClothLeg.get()), new Object[]{"XXX","X X","X X", 'X', new ItemStack(Block.cloth, 1, 0)});
		}
		if(ItemBlockList.whiteClothBoot.isPresent() ){
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.whiteClothBoot.get()), new Object[]{"   ","X X","X X", 'X', new ItemStack(Block.cloth, 1, 0)});
		}

		if(ItemBlockList.redClothHead.isPresent() ){
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.redClothHead.get()), new Object[]{"XXX","X X","   ", 'X', new ItemStack(Block.cloth, 1, 14)});
		}
		if(ItemBlockList.redClothChest.isPresent() ){
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.redClothChest.get()), new Object[]{"X X","XXX","XXX", 'X', new ItemStack(Block.cloth, 1, 14)});
		}
		if(ItemBlockList.redClothLeg.isPresent() ){
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.redClothLeg.get()), new Object[]{"XXX","X X","X X", 'X', new ItemStack(Block.cloth, 1, 14)});
		}
		if(ItemBlockList.redClothBoot.isPresent() ){
			GameRegistry.addRecipe(new ItemStack(ItemBlockList.redClothBoot.get()), new Object[]{"   ","X X","X X", 'X', new ItemStack(Block.cloth, 1, 14)});
		}

		if(ItemBlockList.greenClothHead.isPresent() ){
			Item greenClothHead = ItemBlockList.greenClothHead.get(); 
			GameRegistry.addRecipe(new ItemStack(greenClothHead), new Object[]{"XXX","X X","   ", 'X', new ItemStack(Block.cloth, 1, 13)});
			GameRegistry.addRecipe(new ItemStack(greenClothHead), new Object[]{"XXX","X X","   ", 'X', new ItemStack(Block.cloth, 1, 5)});
		}
		if(ItemBlockList.greenClothChest.isPresent() ){
			Item greenClothChest = ItemBlockList.greenClothChest.get(); 
			GameRegistry.addRecipe(new ItemStack(greenClothChest), new Object[]{"X X","XXX","XXX", 'X', new ItemStack(Block.cloth, 1, 13)});
			GameRegistry.addRecipe(new ItemStack(greenClothChest), new Object[]{"X X","XXX","XXX", 'X', new ItemStack(Block.cloth, 1, 5)});
		}
		if(ItemBlockList.greenClothLeg.isPresent() ){
			Item greenClothLeg = ItemBlockList.greenClothLeg.get(); 
			GameRegistry.addRecipe(new ItemStack(greenClothLeg), new Object[]{"XXX","X X","X X", 'X', new ItemStack(Block.cloth, 1, 13)});
			GameRegistry.addRecipe(new ItemStack(greenClothLeg), new Object[]{"XXX","X X","X X", 'X', new ItemStack(Block.cloth, 1, 5)});
		}
		if(ItemBlockList.greenClothBoot.isPresent() ){
			Item greenClothBoot = ItemBlockList.greenClothBoot.get(); 
			GameRegistry.addRecipe(new ItemStack(greenClothBoot), new Object[]{"   ","X X","X X", 'X', new ItemStack(Block.cloth, 1, 13)});
			GameRegistry.addRecipe(new ItemStack(greenClothBoot), new Object[]{"   ","X X","X X", 'X', new ItemStack(Block.cloth, 1, 5)});
		}

		if(ItemBlockList.blueClothHead.isPresent() ){
			Item blueClothHead = ItemBlockList.blueClothHead.get(); 
			GameRegistry.addRecipe(new ItemStack(blueClothHead), new Object[]{"XXX","X X","   ", 'X', new ItemStack(Block.cloth, 1, 11)});
			GameRegistry.addRecipe(new ItemStack(blueClothHead), new Object[]{"XXX","X X","   ", 'X', new ItemStack(Block.cloth, 1, 3)});
		}
		if(ItemBlockList.blueClothChest.isPresent() ){
			Item blueClothChest = ItemBlockList.blueClothChest.get(); 
			GameRegistry.addRecipe(new ItemStack(blueClothChest), new Object[]{"X X","XXX","XXX", 'X', new ItemStack(Block.cloth, 1, 11)});
			GameRegistry.addRecipe(new ItemStack(blueClothChest), new Object[]{"X X","XXX","XXX", 'X', new ItemStack(Block.cloth, 1, 3)});
		}
		if(ItemBlockList.blueClothLeg.isPresent() ){
			Item blueClothLeg = ItemBlockList.blueClothLeg.get(); 
			GameRegistry.addRecipe(new ItemStack(blueClothLeg), new Object[]{"XXX","X X","X X", 'X', new ItemStack(Block.cloth, 1, 11)});
			GameRegistry.addRecipe(new ItemStack(blueClothLeg), new Object[]{"XXX","X X","X X", 'X', new ItemStack(Block.cloth, 1, 3)});
		}
		if(ItemBlockList.blueClothBoot.isPresent() ){
			Item blueClothBoot = ItemBlockList.blueClothBoot.get(); 
			GameRegistry.addRecipe(new ItemStack(blueClothBoot), new Object[]{"   ","X X","X X", 'X', new ItemStack(Block.cloth, 1, 11)});
			GameRegistry.addRecipe(new ItemStack(blueClothBoot), new Object[]{"   ","X X","X X", 'X', new ItemStack(Block.cloth, 1, 3)});
		}

		if(ItemBlockList.cactusArmorHead.isPresent() ){
			Item cactusArmorHead = ItemBlockList.cactusArmorHead.get(); 
			GameRegistry.addRecipe(new ItemStack(cactusArmorHead), new Object[]{"XXX","X X","   ", 'X', Block.cactus});
		}
		if(ItemBlockList.cactusArmorChest.isPresent() ){
			Item cactusArmorChest = ItemBlockList.cactusArmorChest.get(); 
			GameRegistry.addRecipe(new ItemStack(cactusArmorChest), new Object[]{"X X","XXX","XXX", 'X', Block.cactus});
		}
		if(ItemBlockList.cactusArmorLeg.isPresent() ){
			Item cactusArmorLeg = ItemBlockList.cactusArmorLeg.get(); 
			GameRegistry.addRecipe(new ItemStack(cactusArmorLeg), new Object[]{"XXX","X X","X X", 'X', Block.cactus});
		}
		if(ItemBlockList.cactusArmorBoot.isPresent() ){
			Item cactusArmorBoot = ItemBlockList.cactusArmorBoot.get(); 
			GameRegistry.addRecipe(new ItemStack(cactusArmorBoot), new Object[]{"   ","X X","X X", 'X', Block.cactus});
		}

		if(ItemBlockList.furPelt.isPresent()){
			if(ItemBlockList.furArmorHead.isPresent() ){
				Item furArmorHead = ItemBlockList.furArmorHead.get(); 
				GameRegistry.addRecipe(new ItemStack(furArmorHead), new Object[]{"XXX","X X","   ", 'X', ItemBlockList.furPelt.get()});
			}
			if(ItemBlockList.furArmorChest.isPresent() ){
				Item furArmorChest = ItemBlockList.furArmorChest.get(); 
				GameRegistry.addRecipe(new ItemStack(furArmorChest), new Object[]{"X X","XXX","XXX", 'X', ItemBlockList.furPelt.get()});
			}
			if(ItemBlockList.furArmorLeg.isPresent() ){
				Item furArmorLeg = ItemBlockList.furArmorLeg.get(); 
				GameRegistry.addRecipe(new ItemStack(furArmorLeg), new Object[]{"XXX","X X","X X", 'X', ItemBlockList.furPelt.get()});
			}
			if(ItemBlockList.furArmorBoot.isPresent() ){
				Item furArmorBoot = ItemBlockList.furArmorBoot.get();
				GameRegistry.addRecipe(new ItemStack(furArmorBoot), new Object[]{"   ","X X","X X", 'X', ItemBlockList.furPelt.get()});
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
					&& ItemBlockList.coconutItem.isPresent() && shouldBeCoconut.getItem() == ItemBlockList.coconutItem.get()){
				//Put Sword Back
				ItemStack tempItem1 = new ItemStack(shouldBeSword.getItem(), 2, shouldBeSword.getItemDamage()+1);
				craftMatrix.setInventorySlotContents(1, tempItem1);
				//Place Coconut Milk
				if(ItemBlockList.coconutMilkFragment.isPresent()){
					ItemStack tempItem2 = new ItemStack(ItemBlockList.coconutMilkFragment.get(), 2);
					craftMatrix.setInventorySlotContents(3, tempItem2);
				}
				//Place Coconut Shell
				if(ItemBlockList.coconutShell.isPresent()){
					ItemStack tempItem3 = new ItemStack(ItemBlockList.coconutShell.get(), 2);
					craftMatrix.setInventorySlotContents(5, tempItem3);
				}
			}
		}
	};
}
