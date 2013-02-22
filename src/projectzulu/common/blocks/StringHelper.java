package projectzulu.common.blocks;

public class StringHelper {
	
    public static String simplifyStringNameForDisplay(String string, int characterLimit, String splitString){
    	/* Remove Surpluous Creature Data (ProjectZulu.Armadillo) */
    	String[] stringParts = string.split(splitString);
    	String displayName;
    	/* Shorten Name to Declared Number of Character */
    	if(stringParts.length > 0){
    		displayName = stringParts[stringParts.length-1];
    	}else{
    		displayName = string;
    	}
    	
    	if(displayName.length() > characterLimit){
    		displayName = displayName.substring(0, characterLimit);
    	}
    	return displayName;
    }
    
    public static String toTitleCase(String string){
    	return string.toUpperCase().replace(string.toUpperCase().substring(1), string.substring(1).toLowerCase());
    }
    
    
}
