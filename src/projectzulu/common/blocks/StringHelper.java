package projectzulu.common.blocks;

public class StringHelper {
	
    public static String simplifyCreatureNameForDisplay(String string, int characterLimit){
    	/* Remove Surpluous Creature Data (ProjectZulu.Armadillo) */
    	String[] stringParts = string.split("\\.");
    	for (String string2 : stringParts) {
		}
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
}
