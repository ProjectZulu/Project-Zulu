package projectzulu.common;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;
import projectzulu.common.core.CustomEntityManager;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemBlockManager;
import projectzulu.common.core.terrain.FeatureGenerator;
import projectzulu.common.dungeon.commands.CommandPlaceBlock;
import projectzulu.common.dungeon.commands.CommandPlaySound;
import projectzulu.common.dungeon.commands.CommandSpawnEntity;
import projectzulu.common.dungeon.commands.CommandStreamSound;
import projectzulu.common.dungeon.itemblockdeclaration.LimitedMobSpawnerDeclaration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class ProjectZulu_Dungeon extends BaseModule {

    @Override
    public String getIdentifier() {
        return DefaultProps.DungeonModId;
    }

    @Override
    public void registration(ItemBlockManager manager) {
        ItemBlockManager.INSTANCE.addItemBlock(new LimitedMobSpawnerDeclaration());
    }

    @Override
    public void preInit(FMLPreInitializationEvent event, File configDirectory) {
        MinecraftForge.EVENT_BUS.register(new DeathGamerules().loadConfiguration(event.getModConfigurationDirectory()));
    }

    @Override
    public void serverStarting(FMLServerStartingEvent event, File configDirectory) {
        event.registerServerCommand(new CommandPlaySound());
        event.registerServerCommand(new CommandStreamSound());
        event.registerServerCommand(new CommandSpawnEntity());
        event.registerServerCommand(new CommandPlaceBlock());
    }
}