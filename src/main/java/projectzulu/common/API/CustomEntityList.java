package projectzulu.common.api;


import java.util.EnumSet;
import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;

import com.google.common.base.CharMatcher;
import com.google.common.base.Optional;

public enum CustomEntityList {
    CREEPERBLOSSOMPRIMED, ARMADILLO, SANDWORM, LIZARD, MUMMYPHARAOH, MUMMY, VULTURE, TREEENT, MAMMOTH, FOX, BOAR,
    MIMIC, ALLIGATOR, FROG, PENGUIN, BEAVER, BLACKBEAR, BROWNBEAR, POLARBEAR, OSTRICH, RHINO, RABBIT,
    REDFINCH, BLUEFINCH, GREENFINCH, GORILLA, GIRAFFE, ELEPHANT, HORSEBEIGE, HORSEBLACK, HORSEBROWN,
    HORSEDARKBLACK, HORSEDARKBROWN, HORSEGREY, HORSEWHITE, EAGLE, HORNBILL, PELICAN, MINOTAUR, HAUNTEDARMOR, 
    CENTIPEDE, HORSERANDOM, LIZARDSPIT, FOLLOWER;

    public Optional<? extends CustomMobData> modData = Optional.absent();
    private static final HashMap<String, CustomEntityList> lookupEnum = new HashMap<String, CustomEntityList>();
    static {
        for (CustomEntityList entry : EnumSet.allOf(CustomEntityList.class))
            lookupEnum.put(entry.toString().toLowerCase(), entry);
    }

    public static CustomEntityList getByName(String mobName) {
        if (mobName == null) {
            return null;
        }
        
        String[] nameParts = CharMatcher.anyOf(" ").removeFrom(mobName).toLowerCase().split("\\.");
        CustomEntityList result;
        if (nameParts.length > 1) {
            result = lookupEnum.get(nameParts[nameParts.length - 1]);
        } else {
            result = lookupEnum.get(nameParts[0]);
        }
        if (result == null) {
//            ProjectZuluLog.info("Custom Entity Lookup Failed %s Does not Seem to Exist", CharMatcher.anyOf(" ")
//                    .removeFrom(mobName).toLowerCase());
        }
        return result;
    }

    public static CustomEntityList getByEntity(Entity entity) {
        String mobName = EntityList.getEntityString(entity);
        if(mobName != null){
            return getByName(mobName);
        }else{
            return null;
        }
    }
}
