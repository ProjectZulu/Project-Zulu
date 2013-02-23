package projectzulu.common.api;


import java.util.EnumSet;
import java.util.HashMap;

import projectzulu.common.core.PacketIDs;
import projectzulu.common.core.ProjectZuluLog;

import com.google.common.base.CharMatcher;
import com.google.common.base.Optional;

public enum CustomEntityList {
	CREEPERBLOSSONPRIMED, ARMADILLO, SANDWORM, LIZARD, MUMMYPHARAOH, MUMMY, VULTURE, TREEENT, MAMMOTH, FOX, BOAR,
	MIMIC, ALLIGATOR, FROG, PENGUIN, BEAVER, BLACKBEAR, BROWNBEAR, POLARBEAR, OSTRICH, RHINO, RABBIT,
	REDFINCH, BLUEFINCH, GREENFINCH, GORILLA, GIRAFFE, ELEPHANT, HORSEBEIGE, HORSEBLACK, HORSEBROWN,
	HORSEDARKBLACK, HORSEDARKBROWN, HORSEGREY, HORSEWHITE, EAGLE, HORNBILL, PELICAN, MINOTAUR, HAUNTEDARMOR, 
	CENTIPEDE, HORSERANDOM;
	
	public Optional<? extends CustomMobData> modData = Optional.absent();
	private static final HashMap<String, CustomEntityList> lookupEnum = new HashMap<String, CustomEntityList>();
	static {
        for(CustomEntityList entry : EnumSet.allOf(CustomEntityList.class))
        	lookupEnum.put(entry.toString().toLowerCase(), entry);
    }
	
	public static CustomEntityList getByName(String mobName){
		String[] nameParts = CharMatcher.anyOf(" ").removeFrom(mobName).toLowerCase().split("\\.");
		CustomEntityList result = lookupEnum.get(nameParts[nameParts.length-1]);
		if(result == null){
			ProjectZuluLog.info("Custom Entity Lookup Failed %s Does not Seem to Exist", CharMatcher.anyOf(" ").removeFrom(mobName).toLowerCase());
		}
		return result;
	}
}
