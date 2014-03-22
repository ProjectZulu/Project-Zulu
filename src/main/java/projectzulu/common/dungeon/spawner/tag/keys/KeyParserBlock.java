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
import projectzulu.common.dungeon.spawner.tag.settings.OptionalSettingsBase;

import com.google.common.collect.ListMultimap;

public class KeyParserBlock extends KeyParserBase {

    public KeyParserBlock(Key key) {
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
        return isValidBlock(world, xCoord, yCoord, zCoord, typeValuePair, valueCache);
    }

    /**
     * Represents Restriction on isValidBlock.
     * 
     * @return True if Operation should continue as normal, False if it should be disallowed
     */
    private boolean isValidBlock(World world, int xCoord, int yCoord, int zCoord, TypeValuePair typeValuePair,
            HashMap<String, Object> valueCache) {
        @SuppressWarnings("unchecked")
        ListMultimap<String, Integer> iDMetas = (ListMultimap<String, Integer>) typeValuePair.getValue();
        Integer xRange = (Integer) valueCache.get(Key.blockRangeX.key);
        Integer yRange = (Integer) valueCache.get(Key.blockRangeY.key);
        Integer zRange = (Integer) valueCache.get(Key.blockRangeZ.key);

        xRange = xRange == null ? OptionalSettingsBase.defaultBlockRange : xRange;
        yRange = yRange == null ? OptionalSettingsBase.defaultBlockRange : yRange;
        zRange = zRange == null ? OptionalSettingsBase.defaultBlockRange : zRange;

        for (String blockKey : iDMetas.keySet()) {
            Block searchBlock = Block.getBlockFromName(blockKey);
            if (searchBlock == null) {
                continue;
            }
            List<Integer> metas = iDMetas.get(blockKey);
            for (Integer metaValue : metas) {
                for (int i = -xRange; i <= xRange; i++) {
                    for (int k = -zRange; k <= zRange; k++) {
                        for (int j = -yRange; j <= yRange; j++) {
                            Block blockID = world.getBlock(xCoord + i, yCoord + j, zCoord + k);
                            int meta = world.getBlockMetadata(xCoord + i, yCoord + j, zCoord + k);
                            if (blockID == searchBlock && metaValue.equals(meta)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
