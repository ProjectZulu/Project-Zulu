package projectzulu.common.dungeon.spawner.tag.keys;

import java.util.HashMap;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import projectzulu.common.dungeon.spawner.tag.TypeValuePair;

public class KeyParserOrigin extends KeyParserRange {

    public KeyParserOrigin(Key key) {
        super(key);
    }

    @Override
    int getCurrent(World world, EntityLiving entity, int xCoord, int yCoord, int zCoord, TypeValuePair typeValuePair,
            HashMap<String, Object> valueCache) {
        return (int) Math.sqrt(world.getSpawnPoint().getDistanceSquared(xCoord, yCoord, zCoord));
    }
}