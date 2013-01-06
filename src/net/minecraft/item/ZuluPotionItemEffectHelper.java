package net.minecraft.item;

import net.minecraft.item.Item;


public class ZuluPotionItemEffectHelper {
	
	public static void setVanillaPotionEffects(){
//		Item.fermentedSpiderEye.setPotionEffect("-0+3&13-4+13");
//		Item.fermentedSpiderEye.setPotionEffect("-0&10-4+10");
//		Item.fermentedSpiderEye.setPotionEffect("-0+3&13-4+13");
		
//		"0+1+2-3+10&4-4" DigSpeed -- > Fatique
//		"0+1-2-3+10&4-4" Jump --> SlowFall
//		"0+1+2+3+10&4-4" Cleanse -- > Curse
//		Item.fermentedSpiderEye.setPotionEffect("-0&-2&10-4+10");
		
		Item.fermentedSpiderEye.setPotionEffect("-0&10-4+10");
	}
}
