package projectzulu.common.core;

import java.lang.reflect.Field;
import java.util.HashMap;

public class ObfuscationHelper {
	
	/**
	 * Helper used to See if we are unObfuscated by checking for a known non-Obfuscated name
	 * return true if unObfuscated (eclipse), false if obfuscated (Minecraft)
	 * @param list
	 */
	public static boolean isUnObfuscated(Field[] fieldList, String desired){
		for (int i = 0; i < fieldList.length; i++) {
			if(fieldList[i].getName().equals(desired)){
				return true;
			}
		}			
		return false;
	}
	
	/**
	 * Helper method to Perform Reflection to Get Static Field of Provided Type. Field is assumed Private.
	 * @param fieldName
	 * @param type
	 * @return
	 */
	public static <T> T getFieldFromReflection(String fieldName, Class containingClass, Class<T> type){
		try {
			Field desiredField = containingClass.getDeclaredField(fieldName);
			desiredField.setAccessible(true);
			return type.cast(desiredField.get(null));
		}catch (NoSuchFieldException e) {
			ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s %s. Please notify modmaker Immediately.", fieldName, type.getSimpleName());
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s %s. Please notify modmaker Immediately.");
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s %s. Please notify modmaker Immediately.");
			e.printStackTrace();
		}catch (SecurityException e) {
			ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s %s. Please notify modmaker Immediately.");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Helper method to Perform Reflection to Get non-static Field of Provided Type. Field is assumed Private.
	 * @param fieldName
	 * @param type
	 * @return
	 */
	public static <T> T getFieldFromReflection(String fieldName, Object containterInstance, Class<T> type){
		try {
			Field desiredField = containterInstance.getClass().getDeclaredField(fieldName);
			desiredField.setAccessible(true);
			return type.cast(desiredField.get(containterInstance));
		}catch (NoSuchFieldException e) {
			ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s %s. Please notify modmaker Immediately.", fieldName, type.getSimpleName());
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s %s. Please notify modmaker Immediately.");
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s %s. Please notify modmaker Immediately.");
			e.printStackTrace();
		}catch (SecurityException e) {
			ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s %s. Please notify modmaker Immediately.");
			e.printStackTrace();
		}
		return null;
	}
}
