package projectzulu.common.core;

public class DefaultProps {

    /* ModIDs, Dependencies, and Version */
    public static final String DesiredBefore = "after:ExtrabiomesXL@";
    public static final String VERSION_STRING = "1.1.7.3";
    public static final String CoreModId = "ProjectZulu|Core";
    public static final String BlocksModId = "ProjectZulu|Block";
    public static final String MobsModId = "ProjectZulu|Mob";
    public static final String WorldModId = "ProjectZulu|World";
    public static final String DungeonModId = "ProjectZulu|Dungeon";
    public static final String DEPENDENCY_CORE = "required-after:" + CoreModId + "@";

    /* Packet Channels */
    public static final String defaultChannel = "Channel_Zulu";

    /* Module Directory Keys */
    public static final String coreKey = "projectzulucore";
    public static final String mobKey = "projectzulumob";
    public static final String blockKey = "projectzulublock";
    public static final String worldKey = "projectzuluworld";
    public static final String dungeonKey = "projectzuludungeon";

    /* Module Resource Directories */
    public static final String coreDiretory = "/mods/" + coreKey + "/";
    public static final String mobDiretory = "/mods/" + mobKey + "/";
    public static final String blockDiretory = "/mods/" + blockKey + "/";
    public static final String worldDiretory = "/mods/" + worldKey + "/";
    public static final String dungeonDiretory = "/mods/" + dungeonKey + "/";
    public static final String entitySoundDir = "mob/";
    public static final String entitySounds = entitySoundDir.replace("/", ".");

    /* Common Resource Sub-Directories */
    public static final String itemSubDir = "textures/blocks/";
    public static final String blockSubDir = "textures/blocks/";
//    public static final String blockSpriteSheet = blockDiretory + "blocks_projectzulu.png"; // TODO: Delete
//    public static final String itemSpriteSheet = blockDiretory + "items_projectzulu.png"; // TODO: Delete

    /* Config Directories */
    public static final String configDirectory = "/Project Zulu/";
    public static final String customResourcesDirectory = "CustomResources/";
    public static final String streamingResourcesDirectory = "streaming/";
    public static final String soundResourcesDirectory = "sound/";
    public static final String defaultConfigFile = "ProjectZuluConfig.cfg";
    public static final String moduleConfigFile = "ProjectZuluModules.cfg";
    public static final String tempConfigFile = "tempSettingsConfig.cfg";
    public static final String mobBiomeSpawnConfigFile = "ProjectZuluMobBiomeConfig.cfg";
    public static final String structureBiomeConfigFile = "ProjectZuluTerrainFeature.cfg";
}