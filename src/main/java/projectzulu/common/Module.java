package projectzulu.common;

import java.io.File;

import projectzulu.common.core.CustomEntityManager;
import projectzulu.common.core.ItemBlockManager;
import projectzulu.common.core.terrain.FeatureGenerator;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public interface Module {
    public abstract String getIdentifier();

    public abstract void registration(ItemBlockManager manager);

    public abstract void registration(CustomEntityManager manager);

    public abstract void registration(FeatureGenerator manager);

    public abstract void preInit(FMLPreInitializationEvent event, File configDirectory);

    public abstract void init(FMLInitializationEvent event, File configDirectory);

    public abstract void postInit(FMLPostInitializationEvent event, File configDirectory);
    
    public abstract void serverStarting(FMLServerStartingEvent event, File configDirectory);

    public abstract void serverStart(FMLServerStartedEvent event, File configDirectory);
}
