package projectzulu.common;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import projectzulu.common.core.CreativePZGeneralTab;
import projectzulu.common.core.CreativePZPotionTab;
import projectzulu.common.core.CustomEntityManager;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.EventHookContainerClass;
import projectzulu.common.core.ItemBlockManager;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.ZuluGuiHandler;
import projectzulu.common.core.ZuluPacketHandler;
import projectzulu.common.core.terrain.FeatureGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/*Useful OpenSource reference to Look at: ExtrabiomesXL, Gaurdsman*/
@Mod(modid = DefaultProps.CoreModId, name = "Project Zulu Core", version = DefaultProps.VERSION_STRING, dependencies = DefaultProps.DesiredBefore)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = { "Channel_Zulu" }, packetHandler = ZuluPacketHandler.class)
public class ProjectZulu_Core {

    @Instance(DefaultProps.CoreModId)
    public static ProjectZulu_Core modInstance;

    private static int defaultEntityID = 0;
    private static int defaultBlockID = 1200;
    private static int defaultItemID = 9000;
    private static int defaulteggID = 300;

    public static int getNextDefaultEntityID() {
        return defaultEntityID++;
    }

    public static int getNextDefaultBlockID() {
        return defaultBlockID++;
    }

    public static int getNextDefaultItemID() {
        return defaultItemID++;
    }

    public static int getNextDefaultEggID() {
        return defaulteggID++;
    }

    public static final CreativeTabs projectZuluCreativeTab = new CreativePZGeneralTab(
            CreativeTabs.creativeTabArray.length, "projectZuluTab");
    public static final CreativeTabs projectZuluPotionTab = new CreativePZPotionTab(
            CreativeTabs.creativeTabArray.length, "projectZuluPotionTab");

    public static boolean enableTestBlock = false;
    public static boolean enableTemperature = false;

    public static int testBlockID = 2540;
    public static Block testBlock;

    /* Material Declarations */
    public static final EnumArmorMaterial desertClothMaterial = EnumHelper.addArmorMaterial("Desert Cloth Material", 2,
            new int[] { 1, 2, 1, 1 }, 15);
    public static final EnumArmorMaterial scaleMaterial = EnumHelper.addArmorMaterial("Scale Material", 5, new int[] {
            1, 3, 2, 1 }, 15);
    public static final EnumArmorMaterial furMaterial = EnumHelper.addArmorMaterial("Fur Material", 3, new int[] { 1,
            3, 2, 1 }, 13);
    public static final EnumArmorMaterial goldScaleMaterial = EnumHelper.addArmorMaterial("Gold Scale Material", 7,
            new int[] { 2, 5, 3, 1 }, 25);
    public static final EnumArmorMaterial ironScaleMaterial = EnumHelper.addArmorMaterial("Iron Scale Material", 15,
            new int[] { 2, 6, 5, 2 }, 9);
    public static final EnumArmorMaterial diamondScaleMaterial = EnumHelper.addArmorMaterial("Diamond Scale Material",
            33, new int[] { 3, 8, 6, 3 }, 10);

    public static File modConfigDirectoryFile;

    public static final FeatureGenerator featureGenerator = new FeatureGenerator();

    @SidedProxy(clientSide = "projectzulu.common.ClientProxyProjectZulu", serverSide = "projectzulu.common.CommonProxyProjectZulu")
    public static CommonProxyProjectZulu proxy;

    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        Minecraft.getMinecraft().func_110434_K();
        modConfigDirectoryFile = event.getModConfigurationDirectory();

        ProjectZuluLog.configureLogging(modConfigDirectoryFile);
        Configuration zuluConfig = new Configuration(new File(event.getModConfigurationDirectory(),
                DefaultProps.configDirectory + DefaultProps.defaultConfigFile));
        Properties.loadFromConfig(modConfigDirectoryFile);
        zuluConfig.load();
        enableTemperature = zuluConfig.get("General Controls", "enableTemperature", enableTemperature).getBoolean(
                enableTemperature);
        zuluConfig.save();

        proxy.bossHealthTicker();
        ProjectZulu_Core.proxy.registerAudioLoader();

        ProjectZuluLog.info("Load Entity Models and Render");
        ProjectZulu_Core.proxy.registerModelsAndRender();

        ProjectZuluLog.info("Load Entity Properties");
        CustomEntityManager.INSTANCE.loadCreaturesFromConfig(modConfigDirectoryFile);

        ProjectZuluLog.info("Starting ItemBlock Setup");
        ItemBlockManager.INSTANCE.createBlocks(modConfigDirectoryFile);

        ProjectZuluLog.info("Starting ItemBlock Registration");
        ItemBlockManager.INSTANCE.registerBlocks();

        ProjectZuluLog.info("Registering Entites");
        CustomEntityManager.INSTANCE.registerEntities(modConfigDirectoryFile);
    }

    @Init
    public void load(FMLInitializationEvent event) {
        NetworkRegistry.instance().registerGuiHandler(ProjectZulu_Core.modInstance, new ZuluGuiHandler());
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
        BiomeDictionary.registerAllBiomes();

        ProjectZuluLog.info("Registering Events");
        MinecraftForge.EVENT_BUS.register(new EventHookContainerClass());

        ProjectZuluLog.info("Load Entity Biomes");
        CustomEntityManager.INSTANCE.loadBiomesFromConfig(modConfigDirectoryFile);
        ProjectZuluLog.info("Register Entity Spawns");
        CustomEntityManager.INSTANCE.addSpawns();

        ProjectZuluLog.info("Initializing TerrainFeatures");
        featureGenerator.initialize(modConfigDirectoryFile);
        GameRegistry.registerWorldGenerator(featureGenerator);
    }
}
