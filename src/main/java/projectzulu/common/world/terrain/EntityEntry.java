package projectzulu.common.world.terrain;

import net.minecraft.util.WeightedRandom;

public class EntityEntry extends WeightedRandom.Item {

    public final String entityname;

    public EntityEntry(String entityname, int spawnWeight) {
        super(spawnWeight);
        if (entityname == null || entityname.trim() == "") {
            throw new IllegalArgumentException("Entity Name Cannot be " + entityname == null ? "null" : "blank");
        }
        this.entityname = entityname.trim();
    }
}
