package projectzulu.common.potion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.minecraft.potion.Potion;

public class ZuluPotionHelper{
	public static void setVanillaPotionProperties() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Potion potionToEdit = Potion.potionTypes[Potion.blindness.getId()];
		Method[] potionMethods = potionToEdit.getClass().getDeclaredMethods();
		int doubleCounter = 0;
		for (int i = 0; i < potionMethods.length; i++) {
			/* Look for setEffectiveness : It has 1 Parameter and it is a double : No other method fits that criteria */
			if(potionMethods[i].getParameterTypes().length == 1 && (potionMethods[i].getParameterTypes())[0].toString().equals("double")){
				potionMethods[i].setAccessible(true);
				potionMethods[i].invoke(potionToEdit, 0.03D);
			}
		}	
	}
}