package projectzulu.common;

import java.io.File;

import net.minecraftforge.common.Configuration;
import projectzulu.common.core.DefaultProps;

public class Properties {
    public static boolean tombstoneOnDeath = true;
    public static boolean replaceFlowerPot = true;

    public static boolean despawnInPeaceful = true;
    public static float namePlateScale = 0.016666668F * 1.6f * 0.5f;
    public static float namePlateOpacity = 0.85F;

    public static void loadFromConfig(File modConfigDirectoryFile) {
        Configuration config = new Configuration(new File(modConfigDirectoryFile, DefaultProps.configDirectory
                + DefaultProps.defaultConfigFile));
        config.load();
        tombstoneOnDeath = config.get("General Controls", "Drop Tombstone On Death", tombstoneOnDeath).getBoolean(
                tombstoneOnDeath);
        replaceFlowerPot = config.get("General Controls", "Replace Default Flower Pot", replaceFlowerPot).getBoolean(
                replaceFlowerPot);
        despawnInPeaceful = config.get("MOB CONTROLS", "despawnInPeaceful", despawnInPeaceful).getBoolean(
                despawnInPeaceful);

        namePlateScale = (float) config.get("MOB CONTROLS", "namePlateScale", namePlateScale).getDouble(namePlateScale);
        namePlateOpacity = (float) config.get("MOB CONTROLS", "namePlateOpacity", namePlateOpacity).getDouble(
                namePlateScale);
        config.save();
    }
}
