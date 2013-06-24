package projectzulu.common.dungeon.spawner.tag.keys;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.dungeon.spawner.tag.ParsingHelper;
import projectzulu.common.dungeon.spawner.tag.TypeValuePair;
import projectzulu.common.dungeon.spawner.tag.settings.OptionalSettings.Operand;

public class KeyParserRandom extends KeyParserBase {

    public KeyParserRandom(Key key) {
        super(key, true, KeyType.CHAINABLE);
    }

    @Override
    public boolean parseChainable(String parseable, ArrayList<TypeValuePair> parsedChainable,
            ArrayList<Operand> operandvalue) {
        String[] pieces = parseable.split(",");
        Operand operand = parseOperand(pieces);

        if (pieces.length == 4) {
            int randInt = ParsingHelper.parseFilteredInteger(pieces[1], 16, "RandomRange " + key.key);
            int randOffset = ParsingHelper.parseFilteredInteger(pieces[2], 16, "RandomOffset " + key.key);
            int maximum = ParsingHelper.parseFilteredInteger(pieces[3], -1, "Maximum " + key.key);
            TypeValuePair typeValue = new TypeValuePair(key, new Object[] { isInverted(pieces[0]), randInt, randOffset,
                    maximum });
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

        int randInt = (Integer) values[1];
        int randOffset = (Integer) values[2];
        int maximum = (Integer) values[3];

        boolean isValid = !(world.rand.nextInt(randInt) + randOffset <= maximum);
        return isInverted ? isValid : !isValid;
    }
}
