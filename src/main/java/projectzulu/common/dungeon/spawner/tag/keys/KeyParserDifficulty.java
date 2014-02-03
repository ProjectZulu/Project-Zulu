package projectzulu.common.dungeon.spawner.tag.keys;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.dungeon.spawner.tag.ParsingHelper;
import projectzulu.common.dungeon.spawner.tag.TypeValuePair;
import projectzulu.common.dungeon.spawner.tag.settings.OptionalSettings.Operand;

public class KeyParserDifficulty extends KeyParserBase {

    public KeyParserDifficulty(Key key) {
        super(key, true, KeyType.CHAINABLE);
    }

    @Override
    public boolean parseChainable(String parseable, ArrayList<TypeValuePair> parsedChainable,
            ArrayList<Operand> operandvalue) {
        String[] pieces = parseable.split(",");

        Operand operand = parseOperand(pieces);

        boolean isInverted = false;
        if (isInverted(parseable)) {
            isInverted = true;
        }

        int difficulty = ParsingHelper.parseFilteredInteger(pieces[1], 0, key.key);
        if (difficulty < 0 || difficulty > 3) {
            ProjectZuluLog.info("Difficulty must be between 0 (Peaceful) and 3 (Hard)");
            return false;
        }

        TypeValuePair typeValue = new TypeValuePair(key, new Object[] { isInverted, difficulty });

        if (typeValue.getValue() != null) {
            parsedChainable.add(typeValue);
            operandvalue.add(operand);
            return true;
        }
        return false;
    }

    @Override
    public boolean parseValue(String parseable, HashMap<String, Object> valueCache) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isValidLocation(World world, EntityLiving entity, int xCoord, int yCoord, int zCoord,
            TypeValuePair typeValuePair, HashMap<String, Object> valueCache) {
        Object[] values = (Object[]) typeValuePair.getValue();
        Boolean isInverted = (Boolean) values[0];
        Integer difficulty = (Integer) values[1];
        if ((!isInverted && difficulty.equals(world.difficultySetting))
                || (isInverted && !difficulty.equals(world.difficultySetting))) {
            return false;
        }
        return true;
    }
}