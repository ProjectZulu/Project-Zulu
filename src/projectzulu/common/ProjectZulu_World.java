package projectzulu.common;

import java.io.File;

import net.minecraftforge.common.Configuration;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.world.StructureManager;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = DefaultProps.WorldModId, name = "Project Zulu World", version = DefaultProps.VERSION_STRING, dependencies = DefaultProps.DEPENDENCY_CORE)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class ProjectZulu_World {

	@Instance(DefaultProps.WorldModId)
	public static ProjectZulu_World modInstance;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Configuration structureConfig = new Configuration(  new File(mod_ProjectZulu.modConfigDirectoryFile, DefaultProps.ConfigDirectory + DefaultProps.defaultConfigFile) );
		structureConfig.load();
		StructureManager.loadGeneralSettings(structureConfig);
		structureConfig.save();
	}
	
	@Init
	public void load(FMLInitializationEvent event) {}

	@PostInit
	public void load(FMLPostInitializationEvent event) {
		Configuration structureBiomeConfig = new Configuration(  new File(mod_ProjectZulu.modConfigDirectoryFile, DefaultProps.ConfigDirectory + DefaultProps.structureBiomeConfigFile) );
		structureBiomeConfig.load();
		StructureManager.loadBiomeSettings(structureBiomeConfig);
		structureBiomeConfig.save();
	}
	
}
