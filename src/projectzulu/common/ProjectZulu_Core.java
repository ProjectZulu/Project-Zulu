package projectzulu.common;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import projectzulu.common.core.CreativeTab;
import projectzulu.common.core.CustomEntityManager;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.EventHookContainerClass;
import projectzulu.common.core.ProjectZuluGenerator;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.ZuluGuiHandler;
import projectzulu.common.core.ZuluPacketHandler;
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

/*Useful OpenSource reference to Look at: Gaurdsman*/
@Mod(modid = DefaultProps.CoreModId, name = "Project Zulu Core", version = DefaultProps.VERSION_STRING, dependencies = DefaultProps.DesiredBefore)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels={"Channel_Zulu"}, packetHandler = ZuluPacketHandler.class)

public class ProjectZulu_Core{
	
	private static int defaultEntityID = 0;
	public static int getNextDefaultEntityID(){ return defaultEntityID++; }
	private static int defaulteggID = 300;
	public static int getNextDefaultEggID(){ return defaulteggID++; }
	
	@Instance(DefaultProps.CoreModId)
	public static ProjectZulu_Core modInstance;
	public static final CreativeTabs projectZuluCreativeTab = new CreativeTab(CreativeTabs.creativeTabArray.length, "projectZuluTab");

	public static boolean enableTestBlock = false ; 
	public static boolean enableTemperature = false; 
	public static boolean tombstoneOnDeath = true; 
	public static boolean replaceFlowerPot = true;

	public static int spikeRenderID = -1;
	public static int campFireRenderID = -1;
	public static int universalFlowerPotRenderID = -1;
	
	/* Mob Spawn Controls */
	public static boolean despawnInPeaceful = true; 
	public static float namePlateScale = 0.016666668F * 1.6f * 0.5f; 
	public static float namePlateOpacity = 0.85F; 
	
	public static int testBlockID = 2540;
	public static Block testBlock;
	
	/* Material Declarations */
	public static final EnumArmorMaterial desertClothMaterial = EnumHelper.addArmorMaterial("Desert Cloth Material", 2, new int[]{1, 2, 1, 1}, 15);
	public static final EnumArmorMaterial scaleMaterial = EnumHelper.addArmorMaterial("Scale Material", 5, new int[]{1, 3, 2, 1}, 15);
	public static final EnumArmorMaterial furMaterial = EnumHelper.addArmorMaterial("Fur Material", 3, new int[]{1, 3, 2, 1}, 13);
	public static final EnumArmorMaterial goldScaleMaterial = EnumHelper.addArmorMaterial("Gold Scale Material", 7, new int[]{2, 5, 3, 1}, 25);
	public static final EnumArmorMaterial ironScaleMaterial = EnumHelper.addArmorMaterial("Iron Scale Material", 15, new int[]{2, 6, 5, 2}, 9);
	public static final EnumArmorMaterial diamondScaleMaterial = EnumHelper.addArmorMaterial("Diamond Scale Material", 33, new int[]{3, 8, 6, 3}, 10);
	
	public static File modConfigDirectoryFile;
	
	@SidedProxy(clientSide = "projectzulu.common.ClientProxyProjectZulu", serverSide = "projectzulu.common.CommonProxyProjectZulu")
	public static CommonProxyProjectZulu proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event){
		/* Save COnfig Directory For Later Use */
		modConfigDirectoryFile = event.getModConfigurationDirectory();

		ProjectZuluLog.configureLogging();
		Configuration zuluConfig = new Configuration(  new File(event.getModConfigurationDirectory(), DefaultProps.configDirectory + DefaultProps.defaultConfigFile));
		
		zuluConfig.load();
        enableTestBlock = zuluConfig.get("Developer Debug Variables", "enableTestBlock", enableTestBlock).getBoolean(enableTestBlock);  
//        enableTemperature = zuluConfig.get("General Controls", "enableTemperature", enableTemperature).getBoolean(enableTemperature);
        
        tombstoneOnDeath = zuluConfig.get("General Controls", "Drop Tombstone On Death", tombstoneOnDeath).getBoolean(tombstoneOnDeath);
        replaceFlowerPot = zuluConfig.get("General Controls", "Replace Default Flower Pot", replaceFlowerPot).getBoolean(replaceFlowerPot);
        despawnInPeaceful = zuluConfig.get("MOB CONTROLS", "despawnInPeaceful", despawnInPeaceful).getBoolean(despawnInPeaceful);
        
        namePlateScale = (float) zuluConfig.get("MOB CONTROLS", "namePlateScale", namePlateScale).getDouble(namePlateScale);
        namePlateOpacity = (float) zuluConfig.get("MOB CONTROLS", "namePlateOpacity", namePlateOpacity).getDouble(namePlateScale);
    	
        spikeRenderID = zuluConfig.get("Do Not Touch", "Spike Render ID", campFireRenderID).getInt(campFireRenderID);
        campFireRenderID = zuluConfig.get("Do Not Touch", "Campfire Render ID", campFireRenderID).getInt(campFireRenderID);
        universalFlowerPotRenderID = zuluConfig.get("Do Not Touch", "Universal Flower Pot Render ID", universalFlowerPotRenderID).getInt(universalFlowerPotRenderID);
        zuluConfig.save();
        
        /* Should Enable Temperature System ? */
//        if(enableTemperature){
//            TemperatureTicker temperatureTicker = proxy.initializeTempTicker();
//            GameRegistry.registerPlayerTracker(temperatureTicker);
//        }
		proxy.bossHealthTicker();
		ProjectZulu_Core.proxy.registerMobSounds();
	}
	
	@Init
	public void load(FMLInitializationEvent event){
		proxy.registerRenderThings();
		
		if(enableTestBlock){
			testBlock = (new BlockTestBlock(testBlockID, 32)).setHardness(1.0f).setResistance(1.0f).setUnlocalizedName("testBlock");
			GameRegistry.registerBlock(testBlock, "testZuluBlock"); LanguageRegistry.addName(testBlock, "Test block");
		}
        NetworkRegistry.instance().registerGuiHandler(ProjectZulu_Core.modInstance, new ZuluGuiHandler());
        
		ProjectZulu_Core.proxy.registerModelsAndRender();
		ProjectZuluLog.info("Load Entity Properties");
		CustomEntityManager.INSTANCE.loadCreaturesFromConfig(modConfigDirectoryFile);
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event){
		ProjectZuluLog.info("Registering Entites");
		CustomEntityManager.INSTANCE.registerEntities(modConfigDirectoryFile);
		
		ProjectZuluLog.info("Registering Events");
		MinecraftForge.EVENT_BUS.register(new EventHookContainerClass());
		GameRegistry.registerWorldGenerator(new ProjectZuluGenerator());
		
		ProjectZuluLog.info("Load Entity Biomes");
		CustomEntityManager.INSTANCE.loadBiomesFromConfig(modConfigDirectoryFile);
		ProjectZuluLog.info("Register Entity Spawns");
		CustomEntityManager.INSTANCE.addSpawns();
	}
	
}
