package projectzulu.common.dungeon.spawner.tag.keys;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.dungeon.spawner.tag.ParsingHelper;
import projectzulu.common.dungeon.spawner.tag.TypeValuePair;
import projectzulu.common.dungeon.spawner.tag.settings.OptionalSettings.Operand;

public abstract class KeyParserRange extends KeyParserBase {

    public KeyParserRange(Key key) {
        super(key, true, KeyType.CHAINABLE);
    }

    @Override
    public boolean parseChainable(String parseable, ArrayList<TypeValuePair> parsedChainable,
            ArrayList<Operand> operandvalue) {
        String[] pieces = parseable.split(",");
        Operand operand = parseOperand(pieces);

        if (pieces.length == 3) {
            int min = ParsingHelper.parseFilteredInteger(pieces[1], 16, "1st " + key.key);
            int max = ParsingHelper.parseFilteredInteger(pieces[2], -1, "2nd " + key.key);
            TypeValuePair typeValue = new TypeValuePair(key, new Object[] { isInverted(pieces[0]), min, max });
            parsedChainable.add(typeValue);
            operandvalue.add(operand);
            return true;
        } else {
            ProjectZuluLog.severe("Error Parsing %s Parameter. Invalid Argument Length.", key.key);
            return false;
        }
    }

    @Override
    public boolean parseValue(String parseable, HashMap<String, Object> valueCache) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isValidLocation(World world, EntityLiving entity, int xCoord, int yCoord, int zCoord,
            TypeValuePair typeValuePair, HashMap<String, Object> valueCache) {
        Object[] values = (Object[]) typeValuePair.getValue();
        boolean isInverted = (Boolean) values[0];

        int current = getCurrent(world, entity, xCoord, yCoord, zCoord, typeValuePair, valueCache);
        int minRange = (Integer) values[1];
        int maxRange = (Integer) values[2];

        boolean isValid = !(current <= maxRange && current >= minRange);
        if (minRange <= maxRange) {
            isValid = (current <= maxRange && current >= minRange);
        } else {
            isValid = !(current < minRange && current > maxRange);
        }
        return isInverted ? isValid : !isValid;
    }

    abstract int getCurrent(World world, EntityLiving entity, int xCoord, int yCoord, int zCoord,
            TypeValuePair typeValuePair, HashMap<String, Object> valueCache);
}
