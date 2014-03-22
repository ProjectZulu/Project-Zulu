package projectzulu.common.dungeon.spawner.tag;

import java.util.HashMap;
import java.util.logging.Level;

import net.minecraft.block.Block;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.dungeon.spawner.tag.keys.Key;
import projectzulu.common.dungeon.spawner.tag.settings.OptionalSettingsBase;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

//TODO: most MEthods in this Class should be refactored the appropriate KeyParser
public class OptionalParser {

    public static Integer parseSingleInteger(String[] values, Integer defaultInt, String parameter) {
        if (values.length == 2) {
            return ParsingHelper.parseFilteredInteger(values[1], defaultInt, parameter);
        } else {
            ProjectZuluLog.severe("Error Parsing %s Parameter. Invalid Argument Length.", parameter);
            return null;
        }
    }

    public static int[] parseDoubleInteger(String[] values, int[] defaultInts, String parameter) {
        if (values.length == 3) {
            int[] integers = new int[2];
            integers[0] = ParsingHelper.parseFilteredInteger(values[1], defaultInts[0], "1st " + parameter);
            integers[1] = ParsingHelper.parseFilteredInteger(values[2], defaultInts[1], "2nd " + parameter);
            return integers;
        } else {
            ProjectZuluLog.severe("Error Parsing %s Parameter. Invalid Argument Length.", parameter);
            return null;
        }
    }

    /**
     * Parses the Light Tag.
     * 
     * Format [0] Tag, [1] MinLightLevel, [2] MaxLightLevel.
     * 
     * @param values Values to be Used for Parsing
     * @param valueCache Cache used by OptionalSettings to hold values
     */
    public static int[] parseLight(String[] values) {
        if (values.length == 3) {
            int[] lights = new int[2];
            lights[0] = ParsingHelper.parseFilteredInteger(values[1], 16, "Min " + Key.light.key);
            lights[1] = ParsingHelper.parseFilteredInteger(values[2], 16, "Max " + Key.light.key);
            return lights;
        } else {
            ProjectZuluLog.severe("Error Parsing deSpawn Light Parameter. Invalid Argument Length.");
            return null;
        }
    }

    /**
     * Parses the Block Tag.
     * 
     * @param values Values to be Used for Parsing
     * @param valueCache Cache used by OptionalSettings to hold values
     * @return Returns a ArrayListMultimap mapping BlockID to Meta values
     */
    public static ListMultimap<String, Integer> parseBlock(String[] values) {
        ListMultimap<String, Integer> blockMeta = ArrayListMultimap.create();

        for (int j = 1; j < values.length; j++) {
            int minMeta = 0;
            int maxMeta = 0;
            /* Parse Scenario: NAME-1>2 ADDS (Block,Meta)(NAME, 1)(NAME, 2) */
            /* Parse Scenario: 2>4-1>2 ADDS (Block,Meta)(2,1)(2,2)(3,1)(3,2)(4,1)(4,2) */
            String[] idMetaParts = values[j].split("-");
            String blockID = idMetaParts[0];
            for (int k = 0; k < idMetaParts.length; k++) {
                if (k == 0) {
                } else if (k == 1) {
                    String[] rangeParts = idMetaParts[k].split(">");
                    for (int l = 0; l < rangeParts.length; l++) {
                        if (l == 0) {
                            minMeta = ParsingHelper.parseFilteredInteger(rangeParts[l], minMeta, "parseMinMetaID");
                        } else if (l == 1) {
                            maxMeta = ParsingHelper.parseFilteredInteger(rangeParts[l], maxMeta, "parseMaxMetaID");
                        } else {
                            ProjectZuluLog.warning("Block entry %s contains too many > elements.", values[j]);
                        }
                    }
                } else {
                    ProjectZuluLog.warning("Block entry %s contains too many - elements.", values[j]);
                }
            }

            /* Gaurantee Max > Min. Auxillary Purpose: Gaurantees max is not -1 if only min is Set */
            maxMeta = minMeta > maxMeta ? minMeta : maxMeta;

            for (int meta = minMeta; meta <= maxMeta; meta++) {
                ProjectZuluLog.debug(Level.INFO, "Would be adding (%s,%s)", blockID, meta);
                blockMeta.put(blockID, meta);
            }
        }
        return !blockMeta.isEmpty() ? blockMeta : null;
    }

    /**
     * Parses the BlockRange Tag.
     * 
     * @param values Values to be Used for Parsing
     * @param valueCache Cache used by OptionalSettings to hold values
     */
    public static void parseBlockRange(String[] values, HashMap<String, Object> valueCache) {
        if (values.length == 4) {
            valueCache.put(Key.blockRangeX.key, ParsingHelper.parseFilteredInteger(values[1],
                    OptionalSettingsBase.defaultBlockRange, "blockRangeX"));
            valueCache.put(Key.blockRangeY.key, ParsingHelper.parseFilteredInteger(values[2],
                    OptionalSettingsBase.defaultBlockRange, "blockRangeY"));
            valueCache.put(Key.blockRangeZ.key, ParsingHelper.parseFilteredInteger(values[3],
                    OptionalSettingsBase.defaultBlockRange, "blockRangeZ"));
        } else if (values.length == 2) {
            valueCache.put(Key.blockRangeX.key, ParsingHelper.parseFilteredInteger(values[1],
                    OptionalSettingsBase.defaultBlockRange, "blockRangeX"));
            valueCache.put(Key.blockRangeY.key, ParsingHelper.parseFilteredInteger(values[1],
                    OptionalSettingsBase.defaultBlockRange, "blockRangeY"));
            valueCache.put(Key.blockRangeZ.key, ParsingHelper.parseFilteredInteger(values[1],
                    OptionalSettingsBase.defaultBlockRange, "blockRangeZ"));
        } else {
            ProjectZuluLog.severe("Error Parsing deSpawn block search range Parameter. Invalid Argument Length.");
        }
    }

    /**
     * Parses the SpawnRate Tag.
     * 
     * @param values Values to be Used for Parsing
     * @param valueCache Cache used by OptionalSettings to hold values
     */
    public static void parseSpawnRate(String[] values, HashMap<String, Object> valueCache) {
        if (values.length == 2) {
            valueCache.put(Key.spawnRate.key, ParsingHelper.parseFilteredInteger(values[1],
                    OptionalSettingsBase.defaultSpawnRate, Key.spawnRate.key));
        } else {
            ProjectZuluLog.severe("Error Parsing deSpawn spawn rate Parameter. Invalid Argument Length.");
        }
    }

    /**
     * Parses the SpawnRange Tag.
     * 
     * @param values Values to be Used for Parsing
     * @param valueCache Cache used by OptionalSettings to hold values
     */
    public static void parseSpawnRange(String[] values, HashMap<String, Object> valueCache) {
        if (values.length == 2) {
            valueCache.put(Key.spawnRange.key, ParsingHelper.parseFilteredInteger(values[1], 32, Key.spawnRange.key));
        } else {
            ProjectZuluLog.severe("Error Parsing spawnRange parameter. Invalid Argument Length.");
        }
    }

    @Deprecated
    public static Boolean parseSky(String[] values) {
        if (values.length == 1) {
            if (Key.sky.key.equalsIgnoreCase(values[0])) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } else {
            ProjectZuluLog.severe("Error Parsing Needs Sky parameter. Invalid Argument Length.");
            return null;
        }
    }

    public static void parseEntityCap(String[] values, HashMap<String, Object> valueCache) {
        if (values.length == 2) {
            valueCache.put(Key.entityCap.key, ParsingHelper.parseFilteredInteger(values[1], 0, Key.entityCap.key));
        } else {
            ProjectZuluLog.severe("Error Parsing Needs EntityCap parameter. Invalid Argument Length.");
        }
    }

    public static void parseDespawnAge(String[] values, HashMap<String, Object> valueCache) {
        if (values.length == 2) {
            valueCache.put(Key.despawnAge.key, ParsingHelper.parseFilteredInteger(values[1], 600, Key.despawnAge.key));
        } else {
            ProjectZuluLog.severe("Error Parsing Needs EntityCap parameter. Invalid Argument Length.");
        }
    }

    public static Integer parseMinSpawnHeight(String[] values) {
        if (values.length == 2) {
            return ParsingHelper.parseFilteredInteger(values[1], 256, Key.minSpawnHeight.key);
        } else {
            ProjectZuluLog.severe("Error Parsing Min Spawn Height parameter. Invalid Argument Length.");
            return null;
        }
    }

    public static Integer parseMaxSpawnHeight(String[] values) {
        if (values.length == 2) {
            return ParsingHelper.parseFilteredInteger(values[1], -1, Key.maxSpawnHeight.key);
        } else {
            ProjectZuluLog.severe("Error Parsing Max Spawn Height parameter. Invalid Argument Length.");
            return null;
        }
    }
}
