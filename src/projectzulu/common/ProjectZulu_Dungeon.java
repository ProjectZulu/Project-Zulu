package projectzulu.common;

import net.minecraftforge.common.MinecraftForge;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemBlockManager;
import projectzulu.common.dungeon.commands.CommandPlaceBlock;
import projectzulu.common.dungeon.commands.CommandPlaySound;
import projectzulu.common.dungeon.commands.CommandSpawnEntity;
import projectzulu.common.dungeon.commands.CommandStreamSound;
import projectzulu.common.dungeon.itemblockdeclaration.LimitedMobSpawnerDeclaration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = DefaultProps.DungeonModId, name = "Project Zulu Dungeon", version = DefaultProps.VERSION_STRING, dependencies = DefaultProps.DEPENDENCY_CORE)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ProjectZulu_Dungeon {

    @Instance(DefaultProps.DungeonModId)
    public static ProjectZulu_Dungeon modInstance;

    static {
        declareModuleItemBlocks();
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new DeathGamerules().loadConfiguration(event.getModConfigurationDirectory()));
    }

    @EventHandler
    public void load(FMLInitializationEvent event) {

    }

    @EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandPlaySound());
        event.registerServerCommand(new CommandStreamSound());
        event.registerServerCommand(new CommandSpawnEntity());
        event.registerServerCommand(new CommandPlaceBlock());
    }

    private static void declareModuleItemBlocks() {
        ItemBlockManager.INSTANCE.addItemBlock(new LimitedMobSpawnerDeclaration());
    }
}