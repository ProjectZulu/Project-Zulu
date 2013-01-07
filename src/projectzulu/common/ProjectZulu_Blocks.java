package projectzulu.common;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.ZuluPotionHelper;
import net.minecraft.world.GameRules;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import projectzulu.common.blocks.ItemBlockManager;
import projectzulu.common.blocks.ItemBlockRecipeManager;
import projectzulu.common.blocks.RenderCampFire;
import projectzulu.common.blocks.RenderSpike;
import projectzulu.common.blocks.RenderUniversalFlowerPot;
import projectzulu.common.blocks.ZuluGuiHandler;
import projectzulu.common.core.ArmorManager;
import projectzulu.common.core.CreativeTab;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemBlockList;
import projectzulu.common.core.ProjectZuluLog;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
@Mod(modid = DefaultProps.BlocksModId, name = "Project Zulu Block and Items", version = DefaultProps.VERSION_STRING, dependencies = DefaultProps.DEPENDENCY_CORE)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class ProjectZulu_Blocks {
	
	private static int defaultBlockID = 1200;
	public static int getNextDefaultBlockID(){ return defaultBlockID++; }
	private static int defaultItemID = 9000;
	public static int getNextDefaultItemID(){ return defaultItemID++; }
	public static final CreativeTabs projectZuluCreativeTab = new CreativeTab(CreativeTabs.creativeTabArray.length, "projectZuluTab");

	/*Armor Indexes*/
	public static int scaleIndex;
	public static int whiteWoolIndex;
	public static int redWoolIndex;
	public static int greenWoolIndex;
	public static int blueWoolIndex;
	public static int goldScaleIndex;
	public static int ironScaleIndex;
	public static int diamondScaleIndex;
	public static int cactusIndex;
	public static int furIndex;
	
	@Instance(DefaultProps.BlocksModId)
	public static ProjectZulu_Blocks modInstance;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Configuration zuluConfig = new Configuration(  new File(event.getModConfigurationDirectory(), DefaultProps.ConfigDirectory + DefaultProps.defaultConfigFile) );
        zuluConfig.load();
        
		scaleIndex = mod_ProjectZulu.proxy.addArmor("Armor Sets/scaleArmor");
		goldScaleIndex = mod_ProjectZulu.proxy.addArmor("Armor Sets/goldscale");
		ironScaleIndex = mod_ProjectZulu.proxy.addArmor("Armor Sets/ironscale");
		diamondScaleIndex = mod_ProjectZulu.proxy.addArmor("Armor Sets/diamondscale");
		
		whiteWoolIndex = mod_ProjectZulu.proxy.addArmor("Armor Sets/whitedesertcloth");
		redWoolIndex = mod_ProjectZulu.proxy.addArmor("Armor Sets/reddesertcloth");
		greenWoolIndex = mod_ProjectZulu.proxy.addArmor("Armor Sets/greendesertcloth");
		blueWoolIndex = mod_ProjectZulu.proxy.addArmor("Armor Sets/bluedesertcloth");
		cactusIndex = mod_ProjectZulu.proxy.addArmor("Armor Sets/cactusarmor");
		furIndex = mod_ProjectZulu.proxy.addArmor("Armor Sets/mammothfur");
        
		ProjectZuluLog.info("Starting ItemBlock Init ");
        try {
			ItemBlockManager.preInit(zuluConfig);
			ArmorManager.preInit(zuluConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ProjectZuluLog.info("Finished ItemBlock Init ");

        zuluConfig.save();
	}
	
	@Init
	public void load(FMLInitializationEvent event){
		
		ProjectZuluLog.info("Starting ItemBlock Setup ");
		try {
			ItemBlockManager.init();
			ArmorManager.init();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		ProjectZuluLog.info("Finished ItemBlock Setup ");

		if(ItemBlockList.spike.isPresent()){
			mod_ProjectZulu.spikeRenderID = mod_ProjectZulu.spikeRenderID == -1 ? RenderingRegistry.getNextAvailableRenderId() : mod_ProjectZulu.spikeRenderID;
			RenderingRegistry.registerBlockHandler(mod_ProjectZulu.spikeRenderID, new RenderSpike() );
			ProjectZuluLog.info("Spike Render ID Registed to %s", mod_ProjectZulu.spikeRenderID);
		}
		
		if(ItemBlockList.campfire.isPresent()){
			mod_ProjectZulu.campFireRenderID = mod_ProjectZulu.campFireRenderID == -1 ? RenderingRegistry.getNextAvailableRenderId() : mod_ProjectZulu.campFireRenderID;
			RenderingRegistry.registerBlockHandler(mod_ProjectZulu.campFireRenderID, new RenderCampFire() );
			ProjectZuluLog.info("Campfire Render ID Registed to %s", mod_ProjectZulu.campFireRenderID);
		}
		
		if(ItemBlockList.universalFlowerPot.isPresent()){
			mod_ProjectZulu.universalFlowerPotRenderID = mod_ProjectZulu.universalFlowerPotRenderID == -1 ? RenderingRegistry.getNextAvailableRenderId() : mod_ProjectZulu.universalFlowerPotRenderID;
			RenderingRegistry.registerBlockHandler(mod_ProjectZulu.universalFlowerPotRenderID, new RenderUniversalFlowerPot() );
			ProjectZuluLog.info("Universal Flower Pot Render ID Registed to %s", mod_ProjectZulu.universalFlowerPotRenderID);
		}

		mod_ProjectZulu.proxy.registerBlockRenders();
        NetworkRegistry.instance().registerGuiHandler(mod_ProjectZulu.modInstance, new ZuluGuiHandler());
        		
		/* War Axe Declaration */
//		TickRegistry.registerScheduledTickHandler(new RenderCustomArmorTicker(), Side.CLIENT);
//		goldScaleIndex = mod_ProjectZulu.proxy.addArmor("Armor Sets/goldscale");
//		vikingArmorHead = (new ItemVikingHelmet(vikingHelmetID, mod_ProjectZulu.goldScaleMaterial,goldScaleIndex,0)).setIconIndex(48).setItemName("vikingArmorHead");
//		LanguageRegistry.addName(vikingArmorHead, "vikingArmorHead"); 
//		MinecraftForgeClient.registerItemRenderer(vikingHelmetID+256, new ItemRendererVikingHelmet());
	}
	
	public static final Potion bubbling = (new PotionZulu(21, true, 3484199, 1, 2)).setPotionName("potion.bubbling");
	public static final Potion incendiary = (new PotionIncendiary(22, true, 3484199)).setPotionName("potion.incendiary");
	public static final Potion slowfall = (new PotionSlowFall(23, true, 3484199)).setPotionName("potion.slowfall");
	public static final Potion cleansing = (new PotionCleansing(24, true, 3484199)).setPotionName("potion.cleansing");
	public static final Potion curse = (new PotionCurse(25, true, 3484199)).setPotionName("potion.curse");
	public static final Potion thorn = (new PotionThorns(26, true, 3484199)).setPotionName("potion.thorn");
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event){
		ItemBlockRecipeManager.setupBlockModuleRecipies();
		LanguageRegistry.instance().addStringLocalization("itemGroup.projectZuluTab", "en_US", "Project Zulu");
		
//		/**
//		 * Setup Potions
//		 */
		MinecraftForge.EVENT_BUS.register(new PotionEventHookContainerClass());
        TickRegistry.registerTickHandler(new PotionCleansingTicker(), Side.SERVER);
		ZuluPotionHelper.setVanillaPotionProperties();
		
		LanguageRegistry.instance().addStringLocalization("potion.bubbling.postfix", "Bubbling Potion");
		LanguageRegistry.instance().addStringLocalization("potion.bubbling", "Shiny!");
		
		LanguageRegistry.instance().addStringLocalization("potion.incendiary.postfix", "Incendiary Potion");
		LanguageRegistry.instance().addStringLocalization("potion.incendiary", "Incendiary");
		
		LanguageRegistry.instance().addStringLocalization("potion.slowfall.postfix", "Slowfall Potion");
		LanguageRegistry.instance().addStringLocalization("potion.slowfall", "Slowfall");
		
		LanguageRegistry.instance().addStringLocalization("potion.cleansing.postfix", "Cleansing Potion");
		LanguageRegistry.instance().addStringLocalization("potion.cleansing", "Cleansing");
		
		LanguageRegistry.instance().addStringLocalization("potion.curse.postfix", "Cursed Potion");
		LanguageRegistry.instance().addStringLocalization("potion.curse", "Curse");		
		
		LanguageRegistry.instance().addStringLocalization("potion.thorn.postfix", "Thorn Potion");
		LanguageRegistry.instance().addStringLocalization("potion.thorn", "Thorn");		

		/* Add additional Potions */
		Field fieldPotionRequirement;
		HashMap potionRequirements;
		Field fieldField_77928_m;
		HashMap field_77928_m;
		try {
			fieldPotionRequirement = PotionHelper.class.getDeclaredField("potionRequirements");
			fieldPotionRequirement.setAccessible(true);
			fieldField_77928_m = PotionHelper.class.getDeclaredField("field_77928_m");
			fieldField_77928_m.setAccessible(true);
			
			potionRequirements = (HashMap) fieldPotionRequirement.get(PotionHelper.class);	
			field_77928_m = (HashMap) fieldField_77928_m.get(PotionHelper.class);
			
			/* Bubbling Potion */
			potionRequirements.put(bubbling.getId(), "!0 & !1 & !2 & !3 & 8");
			
			/**
			 * Base Potions, b13
			 */
			/* Custom Potion: incindiary */
			potionRequirements.put(incendiary.getId(), "0 & 1 & !2 & !3 & 5 & !6 & !10");
	        field_77928_m.put(Integer.valueOf(incendiary.getId()), "9");	
			/* Fire Resistance: Tier 1 & 2 Potion */
	        potionRequirements.put(Integer.valueOf(Potion.fireResistance.getId()), "0 & 1 & !2 & !3 & !5 & !10 & 0+6+9+9");
			/* Swiftness/moveSpeed: Tier 1 & 2 Potion */
			potionRequirements.put(Integer.valueOf(Potion.moveSpeed.getId()), "!0 & 1 & !2 & !3 & !10 & 1+6+9+9");
			field_77928_m.put(Potion.moveSpeed.getId(), "5+9");
			/* Slowness : Tier 1 & 2 Potion */
	        potionRequirements.put(Integer.valueOf(Potion.moveSlowdown.getId()), "!0 & 1 & !2 & 3 & !10 & 3+6+9+9");
			field_77928_m.put(Potion.moveSlowdown.getId(), "5+9");
			
			
			/* Tier 2 Vanilla Potions */
			/* Night Vision: Tier 1 & 2 Potion */
	        potionRequirements.put(Integer.valueOf(Potion.nightVision.getId()), "!0 & 1 & 2 & !3 & !5 & !10 & 2+6+9+9");
	        /* Invisibility: Tier 1 & 2 Potion */
	        potionRequirements.put(Integer.valueOf(Potion.invisibility.getId()), "!0 & 1 & 2 & !3 & 5 & !10 & 5+9");
			/* Blindness: Tier 1 & 2 Potion */
	        potionRequirements.put(Integer.valueOf(Potion.blindness.getId()), "!0 & 1 & 2 & 3 & !10 & 2+6+9+9");
			/* Regeneration: Tier 1 & 2 Potion */
			potionRequirements.put(Integer.valueOf(Potion.regeneration.getId()), "0 & !1 & !2 & !3 & !10 & 0+6+9+9");
			field_77928_m.put(Potion.regeneration.getId(), "5+9");
			/* Strength / DamageBoost: Tier 1 & 2 Potion */
			potionRequirements.put(Integer.valueOf(Potion.damageBoost.getId()), "0 & !1 & !2 & 3 & !10 & 3+6+9+9");
			field_77928_m.put(Potion.damageBoost.getId(), "5+9");
			/* Weakness: Tier 1 & 2 Potion */
	        potionRequirements.put(Integer.valueOf(Potion.weakness.getId()), "!0 & !1 & !2 & 3 & !10 & 3+6+9+9");
	        
	        
			/* Poison : Tier 1 & 2 Potion */
	        potionRequirements.put(Integer.valueOf(Potion.poison.getId()), "!0 & !1 & 2 & !3 & !10 & 2+6+9+9");
	        field_77928_m.put(Integer.valueOf(Potion.poison.getId()), "5+9");
			/* Heal : Tier 1 & 2 Potion */
	        potionRequirements.put(Integer.valueOf(Potion.heal.getId()), "0 & !1 & 2 & !3 & !10");
	        field_77928_m.put(Integer.valueOf(Potion.heal.getId()), "5+9");
			/* Harm : Tier 1 & 2 Potion */
	        potionRequirements.put(Integer.valueOf(Potion.harm.getId()), "!0 & !1 & 2 & 3 & !10");
	        field_77928_m.put(Integer.valueOf(Potion.harm.getId()), "5+9");
	        
	        /**
	         * Extra Potions, b10
	         */
			/* Leap : Tier 1 & 2 Potion */
	        potionRequirements.put(Integer.valueOf(Potion.jump.getId()), "0 & 1 & !2 & !3 & 10 & 1+6+9+9");
	        field_77928_m.put(Integer.valueOf(Potion.jump.getId()), "5+9");
			/* slowfall : Tier 1 & 2 Potion */
	        potionRequirements.put(Integer.valueOf(slowfall.getId()), "!0 & 1 & !2 & !3 & 10 & 1+6+9+9");
	        field_77928_m.put(Integer.valueOf(slowfall.getId()), "5+9");
	        
			/* Digsped : Tier 1 & 2 Potion */
	        potionRequirements.put(Integer.valueOf(Potion.digSpeed.getId()), "0 & 1 & 2 & !3 & 10 & 1+6+9+9");
	        field_77928_m.put(Integer.valueOf(Potion.digSpeed.getId()), "5+9");
			/* Digsped : Tier 1 & 2 Potion */
	        potionRequirements.put(Integer.valueOf(Potion.digSlowdown.getId()), "!0 & 1 & 2 & !3 & 10 & 1+6+9+9");
	        field_77928_m.put(Integer.valueOf(Potion.digSlowdown.getId()), "5+9");	
	        
			/* Cleansing : Tier 1 & 2 Potion */
	        potionRequirements.put(Integer.valueOf(cleansing.getId()), "0 & 1 & 2 & 3 & 10 & 1+6+9+9");
	        field_77928_m.put(Integer.valueOf(cleansing.getId()), "5+9");
			/* Curse : Tier 1 & 2 Potion */
	        potionRequirements.put(Integer.valueOf(curse.getId()), "!0 & 1 & 2 & 3 & 10 & 1+6+9+9");
	        field_77928_m.put(Integer.valueOf(curse.getId()), "5+9");
	        
			/* Thorn : Tier 1 & 2 Potion */
	        potionRequirements.put(Integer.valueOf(thorn.getId()), "0 & !1 & 2 & 3 & 10 & 2+6+9+9");
	        field_77928_m.put(Integer.valueOf(thorn.getId()), "5+9");
			/* Resistance : Tier 1 & 2 Potion */
	        potionRequirements.put(Integer.valueOf(Potion.resistance.getId()), "!0 & !1 & 2 & 3 & 2+6+9+9");
	        field_77928_m.put(Integer.valueOf(Potion.resistance.getId()), "5+9");
	        
			/* WaterBreathing : Tier 1 & 2 Potion */
	        potionRequirements.put(Integer.valueOf(Potion.waterBreathing.getId()), "!0 & !1 & 2 & !3 & 10 & 2+6+9+9");
//	        field_77928_m.put(Integer.valueOf(Potion.waterBreathing.getId()), "5+9");	        
		} catch (IllegalArgumentException e) {
			ProjectZuluLog.warning("Bad Things Are Happening Accessing PotionRequirement Hashmap: IllegalArgumentException");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			ProjectZuluLog.warning("Bad Things Are Happening Accessing PotionRequirement Hashmap : IllegalAccessException");
			e.printStackTrace();
		} catch (NoSuchFieldException e1) {
			ProjectZuluLog.warning("Bad Things Are Happening setting PotionRequirement Hashmap public : NoSuchFieldException");
			e1.printStackTrace();
		} catch (SecurityException e1) {
			ProjectZuluLog.warning("Bad Things Are Happening setting PotionRequirement Hashmap public : SecurityException");
			e1.printStackTrace();
		}
	}
	
	
	@ServerStarting
	public void serverStart(FMLServerStartingEvent event){
		/* Add Custom GameRules */
		GameRules gameRule = event.getServer().worldServerForDimension(0).getGameRules();
		/* Add Does Campfire Burn GameRule: Only if not Present */
		String ruleName = "doesCampfireBurn";
		if(gameRule.hasRule(ruleName)){
		}else {
			gameRule.addGameRule(ruleName, "false");
		}
	}
}
