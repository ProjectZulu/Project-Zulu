package projectzulu.common.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


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
	
	public static boolean isUnObfuscated(Class<?> regularClass, String regularClassName){
	    return regularClass.getSimpleName().equals(regularClassName);
	}
	
    public static Object invokeMethod(String eclipseName, String seargeName, Class<?> containingClass,
            Object containterInstance, Object... args) {
        try {
            Method method;
            method = getIntanceOfMethod(eclipseName, seargeName, containterInstance);
            if (method == null) {
                throw new NoSuchMethodException();
            }
            method.setAccessible(true);
            return method.invoke(containterInstance, args);
        } catch (NoSuchMethodException e) {
            ProjectZuluLog.severe("Obfuscation needs updating to access method %s. Notify modmaker.", eclipseName);
            e.printStackTrace();
        } catch (SecurityException e) {
            ProjectZuluLog.severe("Obfuscation needs updating to access method %s. Notify modmaker.", eclipseName);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            ProjectZuluLog.severe("Obfuscation needs updating to access method %s. Notify modmaker.", eclipseName);
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            ProjectZuluLog.severe("Obfuscation needs updating to access method %s. Notify modmaker.", eclipseName);
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            ProjectZuluLog.severe("Obfuscation needs updating to access method %s. Notify modmaker.", eclipseName);
            e.printStackTrace();
        }
        return null;
    }
    
    private static Method getIntanceOfMethod(String eclipseName, String seargeName, Object containterInstance, Class<?>... topClassToLook) {
        Class<?> classToSearch = containterInstance.getClass();
        while (classToSearch.getSuperclass() != null) {
            for (Method method : classToSearch.getDeclaredMethods()) {
                if (eclipseName.equalsIgnoreCase(method.getName()) || seargeName.equalsIgnoreCase(method.getName())) {
                    return method;
                }
            }
            classToSearch = classToSearch.getSuperclass();
        }
        return null;
    }
	
	/**
	 * Helper method to Perform Reflection to Get Static Field of Provided Type. Field is assumed Private.
	 * @param fieldName
	 * @param type
	 * @return
	 */
	public static <T> T getFieldFromReflection(String fieldName, Class<?> containingClass, Class<T> type){
		try {
			Field desiredField = containingClass.getDeclaredField(fieldName);
			desiredField.setAccessible(true);
			return type.cast(desiredField.get(null));
		}catch (NoSuchFieldException e) {
			ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s %s. Please notify modmaker Immediately.", fieldName, type.getSimpleName());
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s %s. Please notify modmaker Immediately.", fieldName, type.getSimpleName());
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s %s. Please notify modmaker Immediately.", fieldName, type.getSimpleName());
			e.printStackTrace();
		}catch (SecurityException e) {
			ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s %s. Please notify modmaker Immediately.", fieldName, type.getSimpleName());
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
	    return getFieldFromReflection(fieldName, containterInstance.getClass(), containterInstance, type);
	}
	
    /**
     * Get Non-Static Field
     */
    public static <T> T getFieldFromReflection(String srgName, String sourceName, Object containterInstance,
            Class<T> type) {
        try {
            return getCatchableFieldFromReflection(srgName, containterInstance.getClass(), containterInstance, type);
        } catch (NoSuchFieldException e) {
            return getFieldFromReflection(sourceName, containterInstance.getClass(), containterInstance, type);
        }
    }

    public static <T> T getFieldFromReflection(String fieldName, Class<?> containingClass, Object containterInstance,
            Class<T> type) {
        try {
            return getCatchableFieldFromReflection(fieldName, containingClass, containterInstance, type);
        } catch (NoSuchFieldException e) {
            ProjectZuluLog.severe(
                    "Obfuscation needs to be updated to access the %s %s. Please notify modmaker Immediately.",
                    fieldName, type.getSimpleName());
            e.printStackTrace();
        }
        return null;
    }
	
    /**
     * Helper method to Perform Reflection to Get non-static Field of Provided Type. Field is assumed Private.
     * 
     * @param fieldName
     * @param type
     * @return
     */
    public static <T> T getCatchableFieldFromReflection(String fieldName, Object containterInstance, Class<T> type)
            throws NoSuchFieldException {
        return getCatchableFieldFromReflection(fieldName, containterInstance.getClass(), containterInstance, type);
    }
    
    /**
     * Helper method to Perform Reflection to Get non-static Field of Provided Type. Field is assumed Private.
     * 
     * @param fieldName
     * @param type
     * @return
     */
    public static <T> T getCatchableFieldFromReflection(String fieldName, Class<?> containingClass, Object containterInstance, Class<T> type)
            throws NoSuchFieldException {
        try {
            Field desiredField = containingClass.getDeclaredField(fieldName);
            desiredField.setAccessible(true);
            return type.cast(desiredField.get(containterInstance));
        } catch (IllegalArgumentException e) {
            ProjectZuluLog.severe(
                    "Obfuscation needs to be updated to access the %s. Please notify modmaker Immediately.", fieldName);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            ProjectZuluLog.severe(
                    "Obfuscation needs to be updated to access the %s. Please notify modmaker Immediately.", fieldName);
            e.printStackTrace();
        } catch (SecurityException e) {
            ProjectZuluLog.severe(
                    "Obfuscation needs to be updated to access the %s. Please notify modmaker Immediately.", fieldName);
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Helper method to Perform Reflection to Set non-static Field of Provided Type. Field is assumed Private.
     * 
     * @param fieldName
     * @param containingClass Class that contains desired field containerInstance should be castable to it. Required to
     *            get fields from parent classes
     * @param containterInstance Instance of the Object to get the non-static Field
     * @param isPrivate Whether the field is private and requires setAccessible(true)
     * @param type
     * @param value
     * @return
     * @throws NoSuchFieldException 
     */
    public static <T> void setCatchableFieldUsingReflection(String fieldName, Class<?> containingClass, Object containterInstance, boolean isPrivate, T value) throws NoSuchFieldException{
        try {
            Field desiredField = containingClass.getDeclaredField(fieldName);
            if (isPrivate) {
                desiredField.setAccessible(true);
            }
            desiredField.set(containterInstance, value);
        }catch (IllegalArgumentException e) {
            ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s. Please notify modmaker Immediately.", fieldName);
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s. Please notify modmaker Immediately.", fieldName);
            e.printStackTrace();
        }catch (SecurityException e) {
            ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s. Please notify modmaker Immediately.", fieldName);
            e.printStackTrace();
        }
    }
	
    /**
     * Helper method to Perform Reflection to Set non-static Field of Provided Type. Field is assumed Private.
     * 
     * @param fieldName
     * @param containingClass Class that contains desired field containerInstance should be castable to it. Required to
     *            get fields from parent classes
     * @param containterInstance Instance of the Object to get the non-static Field
     * @param isPrivate Whether the field is private and requires setAccessible(true)
     * @param type
     * @param value
     * @return
     */
    public static <T> void setFieldUsingReflection(String fieldName, Class<?> containingClass, Object containterInstance, boolean isPrivate, T value){
        try {
            Field desiredField = containingClass.getDeclaredField(fieldName);
            if (isPrivate) {
                desiredField.setAccessible(true);
            }
            desiredField.set(containterInstance, value);
        }catch (NoSuchFieldException e) {
            ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s. Please notify modmaker Immediately.", fieldName);
            e.printStackTrace();
        }catch (IllegalArgumentException e) {
            ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s. Please notify modmaker Immediately.", fieldName);
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s. Please notify modmaker Immediately.", fieldName);
            e.printStackTrace();
        }catch (SecurityException e) {
            ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s. Please notify modmaker Immediately.", fieldName);
            e.printStackTrace();
        }
    }
    
    /**
     * Helper method to Perform Reflection to Set non-static Field of Provided Type. Field is assumed Private.
     * 
     * @param fieldName
     * @param containingClass Class that contains desired field containerInstance should be castable to it. Required to
     *            get fields from parent classes
     * @param containterInstance Instance of the Object to get the non-static Field
     * @param isPrivate Whether the field is private and requires setAccessible(true)
     * @param type
     * @param value
     * @return
     */
    public static <T> void setFieldUsingReflection(String fieldName, Class<?> containingClass,
            Object containterInstance, boolean isPrivate, boolean isFinal, T value) {
        try {
            Field desiredField = containingClass.getDeclaredField(fieldName);
            if (isPrivate) {
                desiredField.setAccessible(true);
            }
            if (isFinal || isPrivate) {
                Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.set(desiredField, desiredField.getModifiers() & ~Modifier.FINAL);
            }
            desiredField.set(containterInstance, value);
        }catch (NoSuchFieldException e) {
            ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s. Please notify modmaker Immediately.", fieldName);
            e.printStackTrace();
        }catch (IllegalArgumentException e) {
            ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s. Please notify modmaker Immediately.", fieldName);
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s. Please notify modmaker Immediately.", fieldName);
            e.printStackTrace();
        }catch (SecurityException e) {
            ProjectZuluLog.severe("Obfuscation needs to be updated to access the %s. Please notify modmaker Immediately.", fieldName);
            e.printStackTrace();
        }
    }
    
    /**
     * Helper method to Perform Reflection to Set non-static Field of Provided Type. Field is assumed Private.
     * 
     * @param fieldName
     * @param containingClass Class that contains desired field containerInstance should be castable to it. Required to
     *            get fields from parent classes
     * @param containterInstance Instance of the Object to get the non-static Field
     * @param isPrivate Whether the field is private and requires setAccessible(true)
     * @param type
     * @param value
     * @return
     * @throws NoSuchFieldException 
     */
    public static <T> void setCatchableFieldUsingReflection(String fieldName, Class<?> containingClass,
            Object containterInstance, boolean isPrivate, boolean isFinal, T value) throws NoSuchFieldException {
        try {
            Field desiredField = containingClass.getDeclaredField(fieldName);
            if (isPrivate) {
                desiredField.setAccessible(true);
            }
            if (isFinal || isPrivate) {
                Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.set(desiredField, desiredField.getModifiers() & ~Modifier.FINAL);
            }
            desiredField.set(containterInstance, value);
        } catch (IllegalArgumentException e) {
            ProjectZuluLog.severe(
                    "Obfuscation needs to be updated to access the %s. Please notify modmaker Immediately.", fieldName);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            ProjectZuluLog.severe(
                    "Obfuscation needs to be updated to access the %s. Please notify modmaker Immediately.", fieldName);
            e.printStackTrace();
        } catch (SecurityException e) {
            ProjectZuluLog.severe(
                    "Obfuscation needs to be updated to access the %s. Please notify modmaker Immediately.", fieldName);
            e.printStackTrace();
        }
    }
    
    
	public static Class<?> getDeclaredClass(String className, Class<?> containingClass){
		for (Class<?> declaredClass : containingClass.getDeclaredClasses()) {
			if(declaredClass.getSimpleName().equals(className)){
				return declaredClass;
			}
		}
		return null;
	}
}
