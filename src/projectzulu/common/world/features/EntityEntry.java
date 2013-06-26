package projectzulu.common.world.features;

import net.minecraft.util.WeightedRandomItem;

public class EntityEntry extends WeightedRandomItem {

    public final String entityname;

    public EntityEntry(String entityname, int spawnWeight) {
        super(spawnWeight);
        if (entityname == null || entityname.trim() == "") {
            throw new IllegalArgumentException("Entity Name Cannot be " + entityname == null ? "null" : "blank");
        }
        this.entityname = entityname.trim();
    }
}
