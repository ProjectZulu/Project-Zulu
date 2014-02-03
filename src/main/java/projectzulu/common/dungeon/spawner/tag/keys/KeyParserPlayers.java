package projectzulu.common.dungeon.spawner.tag.keys;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.dungeon.spawner.tag.ParsingHelper;
import projectzulu.common.dungeon.spawner.tag.TypeValuePair;
import projectzulu.common.dungeon.spawner.tag.settings.OptionalSettings.Operand;

public class KeyParserPlayers extends KeyParserBase {

    public KeyParserPlayers(Key key) {
        super(key, true, KeyType.CHAINABLE);
    }

    @Override
    public boolean parseChainable(String parseable, ArrayList<TypeValuePair> parsedChainable,
            ArrayList<Operand> operandvalue) {
        String[] pieces = parseable.split(",");
        Operand operand = parseOperand(pieces);

        if (pieces.length == 5) {
            int minSearchRange = ParsingHelper.parseFilteredInteger(pieces[1], 32, "1st " + key.key);
            int maxSearchRange = ParsingHelper.parseFilteredInteger(pieces[2], 32, "1st " + key.key);
            int min = ParsingHelper.parseFilteredInteger(pieces[3], 16, "2st " + key.key);
            int max = ParsingHelper.parseFilteredInteger(pieces[4], -1, "3nd " + key.key);
            TypeValuePair typeValue = new TypeValuePair(key, new Object[] { isInverted(pieces[0]), minSearchRange,
                    maxSearchRange, min, max });
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
        int minSearch = (Integer) values[1];
        int maxSearch = (Integer) values[2];

        int current = countNearbyPlayers(world, xCoord, yCoord, zCoord, minSearch, maxSearch);
        int minRange = (Integer) values[3];
        int maxRange = (Integer) values[4];

        boolean isValid;
        if (minRange <= maxRange) {
            isValid = (current <= maxRange && current >= minRange);
        } else {
            isValid = !(current < minRange && current > maxRange);
        }
        return isInverted ? isValid : !isValid;
    }

    private int countNearbyPlayers(World world, int xCoord, int yCoord, int zCoord, int minRange, int maxRange) {
        int count = 0;
        for (int i = 0; i < world.playerEntities.size(); ++i) {
            EntityPlayer player = (EntityPlayer) world.playerEntities.get(i);
            if (player.isEntityAlive()) {
                int distance = (int) Math.sqrt(player.getDistanceSq(xCoord, yCoord, zCoord));
                if (maxRange >= minRange && distance >= minRange && distance <= maxRange) {
                    count++;
                    continue;
                } else if (maxRange < minRange && !(distance < minRange && distance > maxRange)) {
                    count++;
                    continue;
                }
            }
        }
        return count;
    }
}