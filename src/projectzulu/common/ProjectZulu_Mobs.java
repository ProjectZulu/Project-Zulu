package projectzulu.common;

import java.io.File;

import net.minecraft.network.packet.Packet;
import projectzulu.common.core.CustomEntityManager;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "ProjectZulu|Mobs", name = "Project Zulu Mobs", version = DefaultProps.VERSION_STRING, dependencies = DefaultProps.DEPENDENCY_CORE)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class ProjectZulu_Mobs {
	
	private static int defaultmobID = 0;
	public static int getNextDefaultMobID(){ return defaultmobID++; }
	private static int defaulteggID = 300;
	public static int getNextDefaultEggID(){ return defaulteggID++; }

	private static int id = 300;
	private static int eggID = 300;
	
	@Instance(DefaultProps.MobsModId)
	public static ProjectZulu_Mobs modInstance;
	File modConfigDirectory;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event){
		modConfigDirectory = event.getModConfigurationDirectory();
	}

	@Init
	public void load(FMLInitializationEvent event){
		mod_ProjectZulu.proxy.registerMobRenders();
		mod_ProjectZulu.proxy.registerMobSoundEvent();
	}
	
	@PostInit
	public void load(FMLPostInitializationEvent event){
		ProjectZuluLog.info("Starting Mob Init ");
		CustomEntityManager.loadSettings(modConfigDirectory);
		ProjectZuluLog.info("Finished Mob Init ");
		ProjectZuluLog.info("Starting Mob Setup ");
		CustomEntityManager.setupMobs();
		ProjectZuluLog.info("Finished Mob Setup ");
	}
}
