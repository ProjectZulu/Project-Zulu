package projectzulu.common.dungeon.spawner.tag.keys;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.dungeon.spawner.tag.ParsingHelper;
import projectzulu.common.dungeon.spawner.tag.TypeValuePair;
import projectzulu.common.dungeon.spawner.tag.settings.OptionalSettings.Operand;

public class KeyParserOpaqueBlock extends KeyParserBase {

    public KeyParserOpaqueBlock(Key key) {
        super(key, true, KeyType.CHAINABLE);
    }

    @Override
    public boolean parseChainable(String parseable, ArrayList<TypeValuePair> parsedChainable,
            ArrayList<Operand> operandvalue) {
        boolean isInverted = false;
        if (isInverted(parseable)) {
            isInverted = true;
        }

        String[] pieces = parseable.split(",");
        Operand operand = parseOperand(pieces);
        TypeValuePair typeValue = null;

        if (pieces.length == 2 || pieces.length == 3) {
            int rangeX, rangeY, rangeZ;
            rangeX = rangeY = rangeZ = 0;
            String[] rangePieces = pieces[1].split("/");
            if (rangePieces.length == 3) {
                rangeX = ParsingHelper.parseFilteredInteger(rangePieces[0], 0, key.key + "BlockRangeX");
                rangeY = ParsingHelper.parseFilteredInteger(rangePieces[1], 0, key.key + "BlockRangeY");
                rangeZ = ParsingHelper.parseFilteredInteger(rangePieces[2], 0, key.key + "BlockRangeZ");
            } else if (rangePieces.length == 1) {
                rangeX = ParsingHelper.parseFilteredInteger(rangePieces[0], 0, key.key + "BlockRangeX");
                rangeY = rangeX;
                rangeZ = rangeX;
            } else {
                ProjectZuluLog.severe("Error Parsing Range of %s. Invalid Argument Length of %s.", key.key,
                        rangePieces.length);
            }

            if (pieces.length == 3) {
                String[] offsetPieces = pieces[2].split("/");
                int offsetX = ParsingHelper.parseFilteredInteger(offsetPieces[0], 0, key.key + "OffsetX");
                int offsetY = ParsingHelper.parseFilteredInteger(offsetPieces[1], 0, key.key + "OffsetY");
                int offsetZ = ParsingHelper.parseFilteredInteger(offsetPieces[2], 0, key.key + "OffsetZ");
                typeValue = new TypeValuePair(key, new Object[] { isInverted, rangeX, rangeY, rangeZ, offsetX, offsetY,
                        offsetZ });
            } else {
                typeValue = new TypeValuePair(key, new Object[] { isInverted, rangeX, rangeY, rangeZ });
            }
        } else {
            ProjectZuluLog.severe("Error Parsing %s Block Parameter. Invalid Argument Length of %s.", key.key,
                    pieces.length);
            return false;
        }

        if (typeValue != null && typeValue.getValue() != null) {
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
        boolean isInverted = (Boolean) values[0];

        if (values.length == 4 || values.length == 7) {
            int rangeX = (Integer) values[1];
            int rangeY = (Integer) values[2];
            int rangeZ = (Integer) values[3];
            int offsetX, offsetY, offsetZ;
            offsetX = offsetY = offsetZ = 0;
            if (values.length == 7) {
                offsetX = (Integer) values[4];
                offsetY = (Integer) values[5];
                offsetZ = (Integer) values[6];
            }

            for (int i = -rangeX; i <= rangeX; i++) {
                for (int k = -rangeZ; k <= rangeZ; k++) {
                    for (int j = -rangeY; j <= rangeY; j++) {
                        boolean isOpaque = world.getBlock(xCoord + offsetX + i, yCoord + offsetY + j,
                                zCoord + offsetZ + k).isOpaqueCube();
                        if (!isInverted && isOpaque || isInverted && !isOpaque) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}