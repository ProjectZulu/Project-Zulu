package projectzulu.common.potion;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import projectzulu.common.api.BlockList;
import projectzulu.common.api.PotionList;
import projectzulu.common.blocks.BlockZuluBrewingStand;
import projectzulu.common.blocks.TileEntityZuluBrewingStand;
import projectzulu.common.core.ObfuscationHelper;
import projectzulu.common.core.ProjectZuluLog;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public enum PotionManager {
	bubbling(21) {
		@Override
		protected void setupPotion(HashMap potionRequirements, HashMap potionAmplifiers) {
			/* Bubbling Potion */
			PotionList.bubbling = Optional.of((new PotionZulu(potionID, true, (165 << 16) + (131 << 8) + 70, 1, 2)).setPotionName("potion.shining"));
			potionRequirements.put(PotionList.bubbling.get().getId(), "!0 & !1 & !2 & !3 & 8");
		}

		@Override
		protected void registerPotion() {
			LanguageRegistry.instance().addStringLocalization("potion.shining.postfix", "Shining Potion");
			LanguageRegistry.instance().addStringLocalization("potion.shining", "Shiny!");
		}
	},
	incendiary(22) {
		@Override
		protected void setupPotion(HashMap potionRequirements, HashMap potionAmplifiers) {
			PotionList.incendiary = Optional.of((new PotionIncendiary(potionID, true, (133 << 16) + (69 << 8) + 26 )).setPotionName("potion.incendiary"));
			potionRequirements.put(PotionList.incendiary.get().getId(), "0 & 1 & !2 & !3 & 5 & !6 & !10");
	        potionAmplifiers.put(Integer.valueOf(PotionList.incendiary.get().getId()), "9");
		}

		@Override
		protected void registerPotion() {
			LanguageRegistry.instance().addStringLocalization("potion.incendiary.postfix", "Incendiary Potion");
			LanguageRegistry.instance().addStringLocalization("potion.incendiary", "Incendiary");			
		}
	},
	slowfall(23) {
		@Override
		protected void setupPotion(HashMap potionRequirements, HashMap potionAmplifiers) {
			PotionList.slowfall = Optional.of((new PotionSlowFall(potionID, true, (214 << 16) + (214 << 8) + 214)).setPotionName("potion.slowfall"));
	        potionRequirements.put(Integer.valueOf(PotionList.slowfall.get().getId()), "!0 & 1 & !2 & !3 & 10 & 1+6+9+9");
	        potionAmplifiers.put(Integer.valueOf(PotionList.slowfall.get().getId()), "5+9");
		}

		@Override
		protected void registerPotion() {
			LanguageRegistry.instance().addStringLocalization("potion.slowfall.postfix", "Slowfall Potion");
			LanguageRegistry.instance().addStringLocalization("potion.slowfall", "Slowfall");
		}
	},
	cleansing(24) {
		@Override
		protected void setupPotion(HashMap potionRequirements, HashMap potionAmplifiers) {
			PotionList.cleansing = Optional.of((new PotionCleansing(potionID, true, (141 << 16) + (153 << 8) + 79)).setPotionName("potion.cleansing"));
	        potionRequirements.put(Integer.valueOf(PotionList.cleansing.get().getId()), "0 & 1 & 2 & 3 & 10 & 1+6+9+9");
	        potionAmplifiers.put(Integer.valueOf(PotionList.cleansing.get().getId()), "5+9");
		}

		@Override
		protected void registerPotion() {
			LanguageRegistry.instance().addStringLocalization("potion.cleansing.postfix", "Cleansing Potion");
			LanguageRegistry.instance().addStringLocalization("potion.cleansing", "Cleansing");
		}
	},
	curse(25) {
		@Override
		protected void setupPotion(HashMap potionRequirements, HashMap potionAmplifiers) {
			PotionList.curse = Optional.of((new PotionCurse(potionID, true, (114 << 16) + (160 << 8) + 52)).setPotionName("potion.curse"));
	        potionRequirements.put(Integer.valueOf(PotionList.curse.get().getId()), "!0 & 1 & 2 & 3 & 10 & 1+6+9+9");
	        potionAmplifiers.put(Integer.valueOf(PotionList.curse.get().getId()), "5+9");
		}

		@Override
		protected void registerPotion() {
			LanguageRegistry.instance().addStringLocalization("potion.curse.postfix", "Cursed Potion");
			LanguageRegistry.instance().addStringLocalization("potion.curse", "Curse");					
		}
	},
	thorn(26) {
		@Override
		protected void setupPotion(HashMap potionRequirements, HashMap potionAmplifiers) {
			PotionList.thorn = Optional.of((new PotionThorns(potionID, true, (18 << 16) + (133 << 8) + 34)).setPotionName("potion.thorn"));
	        potionRequirements.put(Integer.valueOf(PotionList.thorn.get().getId()), "0 & !1 & 2 & 3 & 10 & 2+6+9+9");
	        potionAmplifiers.put(Integer.valueOf(PotionList.thorn.get().getId()), "5+9");
		}

		@Override
		protected void registerPotion() {
			LanguageRegistry.instance().addStringLocalization("potion.thorn.postfix", "Thorn Potion");
			LanguageRegistry.instance().addStringLocalization("potion.thorn", "Thorn");					
		}
	};
	int potionID;
	static boolean replaceVanillaBrewingStand = true;
	static boolean alterVanillaPotionRequirements = true;
	public static boolean potionModuleEnabled = true;
	public static boolean enableNullPotionHandler = true;

	protected abstract void setupPotion(HashMap potionRequirements, HashMap potionAmplifiers);
	protected abstract void registerPotion();
	PotionManager(int potionID){
		this.potionID = potionID;
	}
	public static void loadSettings(Configuration config){
		potionModuleEnabled = config.get("Potion Controls", "Potion Module Enabled", potionModuleEnabled).getBoolean(potionModuleEnabled);
		replaceVanillaBrewingStand = config.get("Potion Controls", "Replace Vanilla Brewing Stand", replaceVanillaBrewingStand).getBoolean(replaceVanillaBrewingStand);
		alterVanillaPotionRequirements = config.get("Potion Controls", "Alter Vanilla Potion Requirements", alterVanillaPotionRequirements).getBoolean(alterVanillaPotionRequirements);
		enableNullPotionHandler = config.get("Potion Controls", "Enable Null Potion Handler", enableNullPotionHandler).getBoolean(enableNullPotionHandler);
		for (PotionManager potion : PotionManager.values()) {
			potion.potionID = config.get("Potion Controls."+potion.toString(), "PotionID", potion.potionID).getInt(potion.potionID);
		}
	}
	
	public static void setupAndRegisterPotions() {		
		/* Add Potion Properties to potionRequirements and potionAmplifiers in PotionHelper*/
		Field fieldPotionRequirement = null;
		HashMap potionRequirements;
		Field fieldpotionAmplifiers = null;
		HashMap potionAmplifiers;
		try {			
			if(ObfuscationHelper.isUnObfuscated(PotionHelper.class.getDeclaredFields().clone(), "potionRequirements")){
				/* Grab "potionRequirements" : OBFSC: "m" : potionRequirements --> fields.csv --> joined.srg --> m */
				fieldPotionRequirement = PotionHelper.class.getDeclaredField("potionRequirements");
				/* Grab "potionAmplifiers" : OBFSC: n */
				fieldpotionAmplifiers = PotionHelper.class.getDeclaredField("potionAmplifiers");	
			}else{
				/* Grab "potionRequirements" : OBFSC: "m" */
				fieldPotionRequirement = PotionHelper.class.getDeclaredField("m");
				/* Grab "potionAmplifiers" : OBFSC: n */
				fieldpotionAmplifiers = PotionHelper.class.getDeclaredField("n");	
			}
			
			if(fieldPotionRequirement != null && fieldpotionAmplifiers != null){
				/* Grab PotionRequirements Hashap, static so doesn't need Instance */
				fieldPotionRequirement.setAccessible(true);
				potionRequirements = (HashMap) fieldPotionRequirement.get(null);	
				
				/* Grab potionAmplifiers Hashap, static so doesn't need Instance */
				fieldpotionAmplifiers.setAccessible(true);
				potionAmplifiers = (HashMap) fieldpotionAmplifiers.get(null);
				
				for (PotionManager potion : PotionManager.values()) {
					if(potion.potionID > 0){
						potion.setupPotion(potionRequirements, potionAmplifiers);
						potion.registerPotion();
					}
				}
				if(alterVanillaPotionRequirements){
					alterVanillaPotionEffectRequriements(potionRequirements, potionAmplifiers);
					ZuluPotionHelper.setVanillaPotionProperties();
				}
			}
			
		}catch (NoSuchFieldException e) {
			ProjectZuluLog.severe("Obfuscation needs to be updated to access the PotionRequirement Hashmap. Please notify modmaker Immediately.");
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			ProjectZuluLog.severe("Obfuscation needs to be updated to access the PotionRequirement Hashmap. Please notify modmaker Immediately.");
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			ProjectZuluLog.severe("Obfuscation needs to be updated to access the PotionRequirement Hashmap. Please notify modmaker Immediately.");
			e.printStackTrace();
		}catch (SecurityException e) {
			ProjectZuluLog.severe("Obfuscation needs to be updated to access the PotionRequirement Hashmap. Please notify modmaker Immediately.");
			e.printStackTrace();
		}catch (InvocationTargetException e) {
			ProjectZuluLog.severe("Obfuscation needs to be updated to access the PotionRequirement Hashmap. Please notify modmaker Immediately.");
			e.printStackTrace();
		}
		
		/** Replace Vanilla Brewing Stand TileEntity
		 * Note That this utilizing most of the Vanilla Features, just Changes enough to introduce custom TileEntity */
		if(replaceVanillaBrewingStand){
			/* Remove Vanilla Brewing Stand and Tile Entity */
			Block.blocksList[Block.brewingStand.blockID] = null;
			try {				
				Field fieldnameToClassMap = null;
				if(ObfuscationHelper.isUnObfuscated(TileEntity.class.getDeclaredFields().clone(), "nameToClassMap")){
					/* Grab "nameToClassMap" : OBFSC: "a" */
					fieldnameToClassMap = TileEntity.class.getDeclaredField("nameToClassMap");
				}else{
					/* Grab "nameToClassMap" : OBFSC: "a" */
					fieldnameToClassMap = TileEntity.class.getDeclaredField("a");
				}
				
				if(fieldnameToClassMap != null){
					/* Grab nameToClassMap Hashap, static so doesn't need Instance */
					fieldnameToClassMap.setAccessible(true);
					HashMap nameToClassMap = (HashMap) fieldnameToClassMap.get(null);
					if(nameToClassMap.containsKey("Cauldron")){
						nameToClassMap.remove("Cauldron");
					}
				}
				
			} catch (Exception e) {
				ProjectZuluLog.warning("Bad Things Are Happening tryin to Access TileEntityNameMap Hashmap public");
				e.printStackTrace();
			}

			/* Sub in Our Own Brewing Stand and Tile Entity */
			BlockList.customBrewingStand = Optional.of(
					(new BlockZuluBrewingStand(117)).setHardness(0.5F).setLightValue(0.125F).setBlockName("brewingStand").setRequiresSelfNotify()
					);
			GameRegistry.registerTileEntity(TileEntityZuluBrewingStand.class, "TileEntityZuluBrewingStand");   
		}
		
		/** Register Events and Tickers Responsible for PotionEffect if appropriate potionEffects are declared */
		if(PotionList.cleansing.isPresent() || PotionList.thorn.isPresent()){
			MinecraftForge.EVENT_BUS.register(new PotionEventHookContainerClass());
		}
	}
	
	private static void alterVanillaPotionEffectRequriements(HashMap potionRequirements, HashMap potionAmplifiers){
		/* Night Vision: Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.nightVision.getId()), "!0 & 1 & 2 & !3 & !5 & !10 & 2+6+9+9");
        
        /* Invisibility: Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.invisibility.getId()), "!0 & 1 & 2 & !3 & 5 & !10 & 5+9");
        
		/* Blindness: Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.blindness.getId()), "!0 & 1 & 2 & 3 & !10 & 2+6+9+9");
        
		/* Regeneration: Tier 1 & 2 Potion */
		potionRequirements.put(Integer.valueOf(Potion.regeneration.getId()), "0 & !1 & !2 & !3 & !10 & 0+6+9+9");
		potionAmplifiers.put(Potion.regeneration.getId(), "5+9");
		
		/* Strength / DamageBoost: Tier 1 & 2 Potion */
		potionRequirements.put(Integer.valueOf(Potion.damageBoost.getId()), "0 & !1 & !2 & 3 & !10 & 3+6+9+9");
		potionAmplifiers.put(Potion.damageBoost.getId(), "5+9");
		
		/* Weakness: Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.weakness.getId()), "!0 & !1 & !2 & 3 & !10 & 3+6+9+9");
        
		/* Fire Resistance: Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.fireResistance.getId()), "0 & 1 & !2 & !3 & !5 & !10 & 0+6+9+9");
        
		/* Swiftness/moveSpeed: Tier 1 & 2 Potion */
		potionRequirements.put(Integer.valueOf(Potion.moveSpeed.getId()), "!0 & 1 & !2 & !3 & !10 & 1+6+9+9");
		potionAmplifiers.put(Potion.moveSpeed.getId(), "5+9");
		
		/* Slowness : Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.moveSlowdown.getId()), "!0 & 1 & !2 & 3 & !10 & 3+6+9+9");
		potionAmplifiers.put(Potion.moveSlowdown.getId(), "5+9");
		
		/* Poison : Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.poison.getId()), "!0 & !1 & 2 & !3 & !10 & 2+6+9+9");
        potionAmplifiers.put(Integer.valueOf(Potion.poison.getId()), "5+9");
        
		/* Heal : Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.heal.getId()), "0 & !1 & 2 & !3 & !10");
        potionAmplifiers.put(Integer.valueOf(Potion.heal.getId()), "5+9");
        
		/* Harm : Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.harm.getId()), "!0 & !1 & 2 & 3 & !10");
        potionAmplifiers.put(Integer.valueOf(Potion.harm.getId()), "5+9");
        
		/* Leap : Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.jump.getId()), "0 & 1 & !2 & !3 & 10 & 1+6+9+9");
        potionAmplifiers.put(Integer.valueOf(Potion.jump.getId()), "5+9");
        
		/* Digsped : Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.digSpeed.getId()), "0 & 1 & 2 & !3 & 10 & 1+6+9+9");
        potionAmplifiers.put(Integer.valueOf(Potion.digSpeed.getId()), "5+9");
		/* Digsped : Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.digSlowdown.getId()), "!0 & 1 & 2 & !3 & 10 & 1+6+9+9");
        potionAmplifiers.put(Integer.valueOf(Potion.digSlowdown.getId()), "5+9");	
        
		/* Resistance : Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.resistance.getId()), "!0 & !1 & 2 & 3 & 10 & 2+6+9+9");
        potionAmplifiers.put(Integer.valueOf(Potion.resistance.getId()), "5+9");
        
		/* WaterBreathing : Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.waterBreathing.getId()), "!0 & !1 & 2 & !3 & 10 & 2+6+9+9");
	}
	
//	/**
//	 * Helper used to See if we are unObfuscated by checking for a known non-Obfuscated name
//	 * return true if unObfuscated (eclipse), false if obfuscated (Minecraft)
//	 * @param list
//	 */
//	private static boolean isUnObfuscated(Field[] fieldList, String desired){
//		for (int i = 0; i < fieldList.length; i++) {
//			if(fieldList[i].getName().equals(desired)){
//				return true;
//			}
//		}			
//		return false;
//	}
//	
//	/**
//	 * Helper used to See What the obfuscated names have changed to, so they can be set. Requires Compile + run in minecraft outside Eclipse
//	 * Is not usually used, just lookup in MCP, useful for confirming or exploring to make sure it's right
//	 * @param list
//	 */
//	private static void printFieldNamesandType(Field[] fieldList, String title){
//		System.out.println("******");
//		System.out.println("Searching " + title +" found "+ fieldList.length + " properties");
//		for (int i = 0; i < fieldList.length; i++) {
//			System.out.println("******");
//			System.out.println("Field "+ i +" is named " + fieldList[i].getName() + " of type " + fieldList[i].getGenericType());
//		}			
//		System.out.println("******");
//	}
}
