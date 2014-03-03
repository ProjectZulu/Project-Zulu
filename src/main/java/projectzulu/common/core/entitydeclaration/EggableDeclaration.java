package projectzulu.common.core.entitydeclaration;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.common.config.Configuration;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.CustomMobData;

public abstract class EggableDeclaration extends CreatureDeclaration {

    protected int eggColor1;
    protected int eggColor2;

    protected EggableDeclaration(String mobName, int entityID, Class mobClass, EnumCreatureType creatureType) {
        super(mobName, entityID, mobClass, creatureType);
    }

    @Override
    public void loadCreaturesFromConfig(Configuration config) {
        super.loadCreaturesFromConfig(config);
        eggColor1 = config.get("MOB CONTROLS." + mobName, mobName.toLowerCase() + " EggColor1", eggColor1).getInt(
                eggColor1);
        eggColor2 = config.get("MOB CONTROLS." + mobName, mobName.toLowerCase() + " EggColor2", eggColor2).getInt(
                eggColor2);
    }

    /*
     * Create loadCustomMobData() method which calls outputData to List. loadCustom contains calls that are the same for
     * all creatures
     */
    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        super.outputDataToList(config, customMobData);
    }

    @Override
    public void registerEgg() {
        super.registerEgg();
        int eggID = ProjectZulu_Core.getNextDefaultEggID();
        while (EntityList.IDtoClassMapping.containsKey(eggID)) {
            eggID = ProjectZulu_Core.getNextDefaultEggID();
        }
        EntityList.IDtoClassMapping.put(eggID, mobClass);
        EntityList.entityEggs.put(eggID, new EntityEggInfo(eggID, eggColor1, eggColor2));
    }
}
