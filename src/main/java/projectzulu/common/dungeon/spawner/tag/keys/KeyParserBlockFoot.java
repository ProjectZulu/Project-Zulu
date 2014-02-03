package projectzulu.common.dungeon.spawner.tag.keys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import projectzulu.common.dungeon.spawner.tag.OptionalParser;
import projectzulu.common.dungeon.spawner.tag.TypeValuePair;
import projectzulu.common.dungeon.spawner.tag.settings.OptionalSettings.Operand;

import com.google.common.collect.ListMultimap;

public class KeyParserBlockFoot extends KeyParserBase {

    public KeyParserBlockFoot(Key key) {
        super(key, false, KeyType.CHAINABLE);
    }

    @Override
    public boolean parseChainable(String parseable, ArrayList<TypeValuePair> parsedChainable,
            ArrayList<Operand> operandvalue) {
        String[] pieces = parseable.split(",");

        Operand operand = parseOperand(pieces);

        TypeValuePair typeValue = new TypeValuePair(key, OptionalParser.parseBlock(pieces));

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
        @SuppressWarnings("unchecked")
        ListMultimap<Integer, Integer> iDMetas = (ListMultimap<Integer, Integer>) typeValuePair.getValue();
        int blockID = world.getBlockId(xCoord, yCoord - 1, zCoord);
        int meta = world.getBlockMetadata(xCoord, yCoord - 1, zCoord);
        boolean foundMatch = false;
        for (Entry<Integer, Integer> iDMetaEntry : iDMetas.entries()) {
            if (blockID == iDMetaEntry.getKey() && meta == iDMetaEntry.getValue()) {
                foundMatch = true;
                break;
            }
        }
        return foundMatch ? false : true;
    }
}