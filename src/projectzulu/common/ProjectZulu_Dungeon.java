package projectzulu.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraftforge.common.Configuration;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.Sounds;
import projectzulu.common.dungeon.ItemBlockManager;
import projectzulu.common.dungeon.commands.CommandPlaceBlock;
import projectzulu.common.dungeon.commands.CommandPlaySound;
import projectzulu.common.dungeon.commands.CommandSpawnEntity;
import projectzulu.common.dungeon.commands.CommandStreamSound;

import com.google.common.collect.ObjectArrays;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = DefaultProps.DungeonModId, name = "Project Zulu Mobs", version = DefaultProps.VERSION_STRING, dependencies = DefaultProps.DEPENDENCY_CORE)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class ProjectZulu_Dungeon {
	private static int defaultBlockID = 1300;
	public static int getNextDefaultBlockID(){ return defaultBlockID++; }
	private static int defaultItemID = 10000;
	public static int getNextDefaultItemID(){ return defaultItemID++; }

	@Instance(DefaultProps.DungeonModId)
	public static ProjectZulu_Dungeon modInstance;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event){
		Configuration zuluConfig = new Configuration(  new File(event.getModConfigurationDirectory(), DefaultProps.configDirectory + DefaultProps.defaultConfigFile) );
        zuluConfig.load();        
		ProjectZuluLog.info("Starting Dungeon Module ItemBlock Init ");
        try {
			ItemBlockManager.preInit(zuluConfig);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ProjectZuluLog.info("Finished Dungeon Module ItemBlock Init ");
        zuluConfig.save();
        
		ProjectZuluLog.info("Searching For Sound Files");
		File customResourceDir = new File(event.getModConfigurationDirectory(), DefaultProps.configDirectory + DefaultProps.customResourcesDirectory);
		File streamingDir = new File(event.getModConfigurationDirectory(), DefaultProps.configDirectory + DefaultProps.customResourcesDirectory + DefaultProps.streamingResourcesDirectory);
		File soundDir = new File(event.getModConfigurationDirectory(), DefaultProps.configDirectory + DefaultProps.customResourcesDirectory + DefaultProps.soundResourcesDirectory);
		streamingDir.mkdir();
		soundDir.mkdir();
		List<File> sounds = getFileListingNoSort(streamingDir);
		sounds.addAll(getFileListingNoSort(soundDir));
		for (File file : sounds) {
			ProjectZuluLog.info("Found sound %s", file.getName());
			Sounds.addSound(file, customResourceDir);
		}
	}
	
	public File[] finder(File directory){
		directory.mkdirs();
    	return directory.listFiles(new FilenameFilter() { 
    	         public boolean accept(File dir, String filename)
    	              { return filename.endsWith(".ogg"); }
    	} );
    }
	
	private List<File> getFileListingNoSort(File directory) {
		List<File> result = new ArrayList<File>();
		List<File> filesDirs = Arrays.asList(directory.listFiles());
		for (File file : filesDirs) {
			if (!file.isFile()) {
				List<File> deeperList = getFileListingNoSort(file);
				result.addAll(deeperList);
			}else{
				result.add(file);
			}
		}
		return result;
	}
	
	@Init
	public void load(FMLInitializationEvent event){
		ProjectZuluLog.info("Starting Dungeon Module ItemBlock Setup ");
			try {
				ItemBlockManager.init();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		ProjectZuluLog.info("Finished Dungeon Module ItemBlock Setup ");
	}

	
	@ServerStarting
	public void serverStart(FMLServerStartingEvent event){		
		/* Add Custom Commands */
		event.registerServerCommand(new CommandPlaySound());
		LanguageRegistry.instance().addStringLocalization("commands.playsound.usage", "/playsound [targetPlayer] [fileName] <range> <x> <y> <z>");
		event.registerServerCommand(new CommandStreamSound());
		LanguageRegistry.instance().addStringLocalization("commands.streamsound.usage", "/streamsound [targetPlayer] [fileName] <range> <x> <y> <z>");
		event.registerServerCommand(new CommandSpawnEntity());
		LanguageRegistry.instance().addStringLocalization("commands.spawnentity.usage", "/spawnentity [targetPlayer] [entityName] <yaw> <pitch> <x> <y> <z>");
		event.registerServerCommand(new CommandPlaceBlock());
		LanguageRegistry.instance().addStringLocalization("commands.placeblock.usage", "/placeblock [targetPlayer] [blockID] <meta> <x> <y> <z>");
	}
	
}
