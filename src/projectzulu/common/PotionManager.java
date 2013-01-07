package projectzulu.common;

import java.lang.reflect.Field;
import java.util.HashMap;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import projectzulu.common.core.ProjectZuluLog;

import com.google.common.base.Optional;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public enum PotionManager {
	bubbling(21) {
		@Override
		protected void setupPotion(HashMap potionRequirements, HashMap field_77928_m) {
			/* Bubbling Potion */
			PotionList.bubbling = Optional.of((new PotionZulu(potionID, true, 3484199, 1, 2)).setPotionName("potion.bubbling"));
			potionRequirements.put(PotionList.bubbling.get().getId(), "!0 & !1 & !2 & !3 & 8");
		}

		@Override
		protected void registerPotion() {
			LanguageRegistry.instance().addStringLocalization("potion.bubbling.postfix", "Bubbling Potion");
			LanguageRegistry.instance().addStringLocalization("potion.bubbling", "Shiny!");
		}
	},
	incendiary(22) {
		@Override
		protected void setupPotion(HashMap potionRequirements, HashMap field_77928_m) {
			PotionList.incendiary = Optional.of((new PotionIncendiary(potionID, true, 3484199)).setPotionName("potion.incendiary"));
			potionRequirements.put(PotionList.incendiary.get().getId(), "0 & 1 & !2 & !3 & 5 & !6 & !10");
	        field_77928_m.put(Integer.valueOf(PotionList.incendiary.get().getId()), "9");
		}

		@Override
		protected void registerPotion() {
			LanguageRegistry.instance().addStringLocalization("potion.incendiary.postfix", "Incendiary Potion");
			LanguageRegistry.instance().addStringLocalization("potion.incendiary", "Incendiary");			
		}
	},
	slowfall(23) {
		@Override
		protected void setupPotion(HashMap potionRequirements, HashMap field_77928_m) {
			PotionList.slowfall = Optional.of((new PotionSlowFall(potionID, true, 3484199)).setPotionName("potion.slowfall"));
	        potionRequirements.put(Integer.valueOf(PotionList.slowfall.get().getId()), "!0 & 1 & !2 & !3 & 10 & 1+6+9+9");
	        field_77928_m.put(Integer.valueOf(PotionList.slowfall.get().getId()), "5+9");
		}

		@Override
		protected void registerPotion() {
			LanguageRegistry.instance().addStringLocalization("potion.slowfall.postfix", "Slowfall Potion");
			LanguageRegistry.instance().addStringLocalization("potion.slowfall", "Slowfall");
		}
	},
	cleansing(24) {
		@Override
		protected void setupPotion(HashMap potionRequirements, HashMap field_77928_m) {
			PotionList.cleansing = Optional.of((new PotionCleansing(potionID, true, 3484199)).setPotionName("potion.+cleansing"));
	        potionRequirements.put(Integer.valueOf(PotionList.cleansing.get().getId()), "0 & 1 & 2 & 3 & 10 & 1+6+9+9");
	        field_77928_m.put(Integer.valueOf(PotionList.cleansing.get().getId()), "5+9");
		}

		@Override
		protected void registerPotion() {
			LanguageRegistry.instance().addStringLocalization("potion.cleansing.postfix", "Cleansing Potion");
			LanguageRegistry.instance().addStringLocalization("potion.cleansing", "Cleansing");
		}
	},
	curse(25) {
		@Override
		protected void setupPotion(HashMap potionRequirements, HashMap field_77928_m) {
			PotionList.curse = Optional.of((new PotionCurse(potionID, true, 3484199)).setPotionName("potion.+curse"));
	        potionRequirements.put(Integer.valueOf(PotionList.curse.get().getId()), "!0 & 1 & 2 & 3 & 10 & 1+6+9+9");
	        field_77928_m.put(Integer.valueOf(PotionList.curse.get().getId()), "5+9");
		}

		@Override
		protected void registerPotion() {
			LanguageRegistry.instance().addStringLocalization("potion.curse.postfix", "Cursed Potion");
			LanguageRegistry.instance().addStringLocalization("potion.curse", "Curse");					
		}
	},
	thorn(26) {
		@Override
		protected void setupPotion(HashMap potionRequirements, HashMap field_77928_m) {
			PotionList.thorn = Optional.of((new PotionThorns(potionID, true, 3484199)).setPotionName("potion.+thorn"));
	        potionRequirements.put(Integer.valueOf(PotionList.thorn.get().getId()), "0 & !1 & 2 & 3 & 10 & 2+6+9+9");
	        field_77928_m.put(Integer.valueOf(PotionList.thorn.get().getId()), "5+9");
		}

		@Override
		protected void registerPotion() {
			LanguageRegistry.instance().addStringLocalization("potion.thorn.postfix", "Thorn Potion");
			LanguageRegistry.instance().addStringLocalization("potion.thorn", "Thorn");					
		}
	};
	int potionID;

	protected abstract void setupPotion(HashMap potionRequirements, HashMap field_77928_m);
	protected abstract void registerPotion();
	PotionManager(int potionID){
		this.potionID = potionID;
	}
	public static void loadSettings(Configuration config){
		for (PotionManager potion : PotionManager.values()) {
			potion.potionID = config.get("Potion Controls"+potion.toString(), "PotionID", potion.potionID).getInt(potion.potionID);
		}
	}
	
	public static void setupAndRegisterPotions(){
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
			
			for (PotionManager potion : PotionManager.values()) {
				if(potion.potionID > 0){
					potion.setupPotion(potionRequirements, field_77928_m);
					potion.registerPotion();
				}
			}
			alterVanillaPotionEffectRequriements(potionRequirements, field_77928_m);
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
		if(PotionList.cleansing.isPresent() || PotionList.thorn.isPresent()){
			MinecraftForge.EVENT_BUS.register(new PotionEventHookContainerClass());
		}
		if(PotionList.cleansing.isPresent()){
	        TickRegistry.registerTickHandler(new PotionCleansingTicker(), Side.SERVER);
		}
	}
	
	private static void alterVanillaPotionEffectRequriements(HashMap potionRequirements, HashMap field_77928_m){
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
        
		/* Fire Resistance: Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.fireResistance.getId()), "0 & 1 & !2 & !3 & !5 & !10 & 0+6+9+9");
        
		/* Swiftness/moveSpeed: Tier 1 & 2 Potion */
		potionRequirements.put(Integer.valueOf(Potion.moveSpeed.getId()), "!0 & 1 & !2 & !3 & !10 & 1+6+9+9");
		field_77928_m.put(Potion.moveSpeed.getId(), "5+9");
		
		/* Slowness : Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.moveSlowdown.getId()), "!0 & 1 & !2 & 3 & !10 & 3+6+9+9");
		field_77928_m.put(Potion.moveSlowdown.getId(), "5+9");
		
		/* Poison : Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.poison.getId()), "!0 & !1 & 2 & !3 & !10 & 2+6+9+9");
        field_77928_m.put(Integer.valueOf(Potion.poison.getId()), "5+9");
        
		/* Heal : Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.heal.getId()), "0 & !1 & 2 & !3 & !10");
        field_77928_m.put(Integer.valueOf(Potion.heal.getId()), "5+9");
        
		/* Harm : Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.harm.getId()), "!0 & !1 & 2 & 3 & !10");
        field_77928_m.put(Integer.valueOf(Potion.harm.getId()), "5+9");
        
		/* Leap : Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.jump.getId()), "0 & 1 & !2 & !3 & 10 & 1+6+9+9");
        field_77928_m.put(Integer.valueOf(Potion.jump.getId()), "5+9");
        
		/* Digsped : Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.digSpeed.getId()), "0 & 1 & 2 & !3 & 10 & 1+6+9+9");
        field_77928_m.put(Integer.valueOf(Potion.digSpeed.getId()), "5+9");
		/* Digsped : Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.digSlowdown.getId()), "!0 & 1 & 2 & !3 & 10 & 1+6+9+9");
        field_77928_m.put(Integer.valueOf(Potion.digSlowdown.getId()), "5+9");	
        
		/* Resistance : Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.resistance.getId()), "!0 & !1 & 2 & 3 & 2+6+9+9");
        field_77928_m.put(Integer.valueOf(Potion.resistance.getId()), "5+9");
        
		/* WaterBreathing : Tier 1 & 2 Potion */
        potionRequirements.put(Integer.valueOf(Potion.waterBreathing.getId()), "!0 & !1 & 2 & !3 & 10 & 2+6+9+9");
	}
}
