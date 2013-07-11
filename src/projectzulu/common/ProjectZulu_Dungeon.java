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
import cpw.mods.fml.common.registry.LanguageRegistry;

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
        /* Add Custom Commands */
        event.registerServerCommand(new CommandPlaySound());
        LanguageRegistry.instance().addStringLocalization("commands.playsound.usage",
                "/playsound [targetPlayer] [fileName] <range> <x> <y> <z>");
        event.registerServerCommand(new CommandStreamSound());
        LanguageRegistry.instance().addStringLocalization("commands.streamsound.usage",
                "/streamsound [targetPlayer] [fileName] <range> <x> <y> <z>");
        event.registerServerCommand(new CommandSpawnEntity());
        LanguageRegistry.instance().addStringLocalization("commands.spawnentity.usage",
                "/spawnentity [targetPlayer] [entityName] <yaw> <pitch> <x> <y> <z>");
        event.registerServerCommand(new CommandPlaceBlock());
        LanguageRegistry.instance().addStringLocalization("commands.placeblock.usage",
                "/placeblock [targetPlayer] [blockID] <meta> <x> <y> <z>");
        LanguageRegistry.instance().addStringLocalization("commands.placeblock.noblock",
                "/placeblock blockID does not exist");
    }

    private static void declareModuleItemBlocks() {
        ItemBlockManager.INSTANCE.addItemBlock(new LimitedMobSpawnerDeclaration());
    }
}
