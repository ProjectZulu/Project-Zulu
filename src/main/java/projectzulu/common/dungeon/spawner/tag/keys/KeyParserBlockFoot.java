package projectzulu.common.dungeon.spawner.tag.keys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.block.Block;
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
        ListMultimap<String, Integer> iDMetas = (ListMultimap<String, Integer>) typeValuePair.getValue();
        Block blockID = world.getBlock(xCoord, yCoord - 1, zCoord);
        int meta = world.getBlockMetadata(xCoord, yCoord - 1, zCoord);
        boolean foundMatch = false;
        for (String blockKey : iDMetas.keySet()) {
            Block searchBlock = Block.getBlockFromName(blockKey);
            if (searchBlock == null) {
                continue;
            }
            List<Integer> metas = iDMetas.get(blockKey);
            for (Integer metaValue : metas) {
                if (blockID == searchBlock && metaValue.equals(meta)) {
                    foundMatch = true;
                    break;
                }
            }
        }
        return foundMatch ? false : true;
    }
}