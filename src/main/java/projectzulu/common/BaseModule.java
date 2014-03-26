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

public abstract class BaseModule implements Module {

    @Override
    public void registration(ItemBlockManager manager) {
    }

    @Override
    public void registration(CustomEntityManager manager) {
    }

    @Override
    public void registration(FeatureGenerator manager) {
    }

    @Override
    public void preInit(FMLPreInitializationEvent event, File configDirectory) {
    }

    @Override
    public void init(FMLInitializationEvent event, File configDirectory) {
    }

    @Override
    public void postInit(FMLPostInitializationEvent event, File configDirectory) {
    }

    @Override
    public void serverStarting(FMLServerStartingEvent event, File configDirectory) {
    }

    @Override
    public void serverStart(FMLServerStartedEvent event, File configDirectory) {
    }
}
