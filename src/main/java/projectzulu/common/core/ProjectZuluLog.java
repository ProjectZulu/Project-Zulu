package projectzulu.common.core;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraftforge.common.config.Configuration;

public class ProjectZuluLog {

    private static Logger myLog;
    public static boolean isSetup;

    public enum LogType {
        SPAWNING, DEBUG;
        public boolean isEnabled = false;
    }

    public static void configureLogging(File configDirectory) {
        if (!isSetup) {
            isSetup = true;
            myLog = Logger.getLogger("ProjectZulu|Core");
            myLog.setParent(Logger.getLogger("ForgeModLoader"));
            Configuration config = new Configuration(new File(configDirectory, DefaultProps.configDirectory
                    + DefaultProps.defaultConfigFile));
            config.load();
            for (LogType type : LogType.values()) {
                if (type == LogType.DEBUG) {
                    type.isEnabled = config.get("Properties.Logging", type.toString() + " Logging", false,
                            "Master Switch For All Debug Printing (Not Fully Implemented)").getBoolean(false);
                } else {
                    type.isEnabled = config.get("Properties.Logging", type.toString() + " Logging", true,
                            "Enables " + type + " Logging").getBoolean(true);
                }
            }
            config.save();

        }
    }

    public static void log(Level level, String format, Object... data) {
        myLog.log(level, String.format(format, data));
    }

    public static void info(String format, Object... data) {
        log(Level.INFO, format, data);
    }

    public static void warning(String format, Object... data) {
        log(Level.WARNING, format, data);
    }

    public static void severe(String format, Object... data) {
        log(Level.SEVERE, format, data);
    }

    public static void debug(Level level, String format, Object... data) {
        if (LogType.DEBUG.isEnabled) {
            log(level, format, data);
        }
    }

    public static void log(LogType type, Level level, String format, Object... data) {
        if (type.isEnabled || LogType.DEBUG.isEnabled) {
            log(level, format, data);
        }
    }
}
