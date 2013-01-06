package projectzulu.common;

import java.io.File;
import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.WorldGeneratorZulu;
import projectzulu.common.core.ZuluPacketHandler;
import projectzulu.common.temperature.TemperatureTicker;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/*Useful OpenSource reference to Look at: Gaurdsman*/
@Mod(modid = DefaultProps.CoreModId, name = "Project Zulu Core", version = DefaultProps.VERSION_STRING)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels={"Channel_Zulu"}, packetHandler = ZuluPacketHandler.class)

public class mod_ProjectZulu{	
	
	@Instance(DefaultProps.CoreModId)
	public static mod_ProjectZulu modInstance;
	
	/*
	 * General Declarations
	 */
	public static ArrayList<ItemStack> tier1DesertArmor = new ArrayList<ItemStack>();
	public static ArrayList<ItemStack> tier2DesertArmor = new ArrayList<ItemStack>();
	public static ArrayList<ItemStack> tier3DesertArmor = new ArrayList<ItemStack>();
	public static ArrayList<ItemStack> tier4DesertArmor = new ArrayList<ItemStack>();
	public static ArrayList<ItemStack> tier5DesertArmor = new ArrayList<ItemStack>();
	
	public static int scaleIndex;
	public static int whiteWoolIndex;
	public static int redWoolIndex;
	public static int greenWoolIndex;
	public static int blueWoolIndex;
	public static int goldScaleIndex;
	public static int ironScaleIndex;
	public static int diamondScaleIndex;
	public static int cactusIndex;
	public static int furIndex;
	
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

	
	
	TemperatureTicker temperatureTicker;
	public static int testBlockID = 2540;
	public static Block testBlock;
	
	/* Material Declarations */
	public static final EnumArmorMaterial desertClothMaterial = EnumHelper.addArmorMaterial("Desert Cloth Material", 2, new int[]{1, 2, 1, 1}, 15);
	public static final EnumArmorMaterial scaleMaterial = EnumHelper.addArmorMaterial("Scale Material", 3, new int[]{1, 3, 2, 1}, 13);
	public static final EnumArmorMaterial furMaterial = EnumHelper.addArmorMaterial("Fur Material", 3, new int[]{1, 3, 2, 1}, 13);
	public static final EnumArmorMaterial goldScaleMaterial = EnumHelper.addArmorMaterial("Gold Scale Material", 5, new int[]{2, 4, 3, 1}, 23);
	public static final EnumArmorMaterial ironScaleMaterial = EnumHelper.addArmorMaterial("Iron Scale Material", 12, new int[]{2, 5, 5, 2}, 7);
	public static final EnumArmorMaterial diamondScaleMaterial = EnumHelper.addArmorMaterial("Diamond Scale Material", 30, new int[]{3, 7, 6, 3}, 8);
	
	public static File modConfigDirectoryFile;

	
	@SidedProxy(clientSide = "projectzulu.client.ClientProxyProjectZulu", serverSide = "projectzulu.common.CommonProxyProjectZulu")
	public static CommonProxyProjectZulu proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event){
		/* Save COnfig Directory For Later Use */
		modConfigDirectoryFile = event.getModConfigurationDirectory();

		ProjectZuluLog.configureLogging();
		Configuration zuluConfig = new Configuration(  new File(event.getModConfigurationDirectory(), DefaultProps.ConfigDirectory + DefaultProps.defaultConfigFile));
        // loading the configuration from its file
        zuluConfig.load();
        
        /* General Declarations */
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
    	
        /* Saving the configuration to its file */
        zuluConfig.save();
        
        /* Should Enable Temperature System ? */
//        if(enableTemperature){
//            temperatureTicker = proxy.initializeTempTicker();
//            GameRegistry.registerPlayerTracker(temperatureTicker);
//        }
        
		proxy.registerTickers();
		mod_ProjectZulu.proxy.registerMobSounds();
	}
	
	@Init
	public void load(FMLInitializationEvent event){
		//Client
		proxy.registerRenderThings();

		//Client
		proxy.clientEventHooks();
		
		if(enableTestBlock){
			testBlock = (new BlockTestBlock(testBlockID, 32)).setHardness(1.0f).setResistance(1.0f).setBlockName("testBlock");
			GameRegistry.registerBlock(testBlock); LanguageRegistry.addName(testBlock, "Test block");
		}
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event){
		MinecraftForge.EVENT_BUS.register(new EventHookContainerClass());
		GameRegistry.registerWorldGenerator(new WorldGeneratorZulu());
	}
	
}
