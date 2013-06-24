package projectzulu.common.dungeon.spawner.tag.keys;

import java.util.HashMap;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import projectzulu.common.dungeon.spawner.tag.TypeValuePair;

public class KeyParserTorchLight extends KeyParserRange {

    public KeyParserTorchLight(Key key) {
        super(key);
    }

    @Override
    int getCurrent(World world, EntityLiving entity, int xCoord, int yCoord, int zCoord, TypeValuePair typeValuePair,
            HashMap<String, Object> valueCache) {
        return world.getSavedLightValue(EnumSkyBlock.Block, xCoord, yCoord, zCoord);
    }
}