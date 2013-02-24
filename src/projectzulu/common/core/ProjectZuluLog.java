package projectzulu.common.core;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectZuluLog {
	
    private static Logger myLog;
	private static boolean isSetup;
//    ProjectZuluLog.severe("$ Spawming: $ %s $ %s $ %s $ %s $ %s",var35.toString(), var39.getEntityName(), var39.posX, var39.posY, var39.posZ);

	public static void configureLogging() {
		if (!isSetup){
			isSetup = true;
			myLog = Logger.getLogger("ProjectZulu|Core");
			myLog.setParent(Logger.getLogger("ForgeModLoader"));
		}
	}
	
	public static void log(Level level, String format, Object... data){
    	myLog.log(level, String.format(format, data));
    }
	
	public static void info(String format, Object... data){
        log(Level.INFO, format, data);
    }
	
	public static void warning(String format, Object... data){
        log(Level.WARNING, format, data);
    }
	
	public static void severe(String format, Object... data){
        log(Level.SEVERE, format, data);
    }

}
