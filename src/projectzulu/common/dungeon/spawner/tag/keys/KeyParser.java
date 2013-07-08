package projectzulu.common.dungeon.spawner.tag.keys;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import projectzulu.common.dungeon.spawner.tag.TypeValuePair;
import projectzulu.common.dungeon.spawner.tag.settings.OptionalSettings.Operand;

public abstract class KeyParser {

    public enum KeyType {
        CHAINABLE, VALUE, PARENT, NONE;
    }

    public abstract boolean isInvertable();

    public abstract boolean isInverted(String parseable);

    public abstract Operand parseOperand(String parseable);

    public abstract boolean isMatch(String title);

    public abstract KeyType getKeyType();

    public abstract boolean parseChainable(String parseable, ArrayList<TypeValuePair> parsedChainable,
            ArrayList<Operand> operandvalue);

    public abstract boolean parseValue(String parseable, HashMap<String, Object> valueCache);

    /**
     * Evaluate Function that evaluates whether the Key Parsed is Valid as part of a Chain
     * 
     * @param world World being evaluated
     * @param entity Entity being processed. May be Null where not applicable.
     * @param xCoord X coord location in the world
     * @param yCoord Y coord location in the world
     * @param zCoord Z coord location in the world
     * @param typeValuePair Pair containing parsed Key and Values
     * @param valueCache Map of values from the parent tag
     * @return
     */
    public abstract boolean isValidLocation(World world, EntityLiving entity, int xCoord, int yCoord,
            int zCoord, TypeValuePair typeValuePair, HashMap<String, Object> valueCache);
}
