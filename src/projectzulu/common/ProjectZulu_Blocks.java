package projectzulu.common;

import java.io.File;

import net.minecraft.world.GameRules;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import projectzulu.common.blocks.CoconutShellDeclaration;
import projectzulu.common.blocks.CreeperBlossomPrimedDefault;
import projectzulu.common.blocks.FurPeltDeclaration;
import projectzulu.common.blocks.GenericCraftingItemsDeclaration;
import projectzulu.common.blocks.ItemBlockRecipeManager;
import projectzulu.common.blocks.ScaleItemDeclaration;
import projectzulu.common.blocks.StructurePlacerDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.AloeVeraDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.AloeVeraSeedsDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.AnkhDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.BlueClothArmorDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.CactusArmorDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.CampfireDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.CoconutDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.CoconutItem;
import projectzulu.common.blocks.itemblockdeclarations.CoconutMilkFragmentDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.CoconutSeedDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.CreeperBlossomDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.DiamondScaleArmorDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.FurArmorDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.GoldScaleArmorDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.GreenClothArmorDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.IronScaleArmorDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.JasperDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.MobSkullsDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.NightBloomDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.PalmTreeDoubleSlab;
import projectzulu.common.blocks.itemblockdeclarations.PalmTreeLeavesDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.PalmTreeLogDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.PalmTreePlankDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.PalmTreeSapling;
import projectzulu.common.blocks.itemblockdeclarations.PalmTreeSlabDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.PalmTreeStairsDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.QuickSandDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.RedClothArmorDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.ScaleArmorDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.SpikesDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.TombstoneDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.TumbleweedDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.UniversalFlowerPotDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.WaterDropletDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.WateredDirtDeclaration;
import projectzulu.common.blocks.itemblockdeclarations.WhiteClothArmor;
import projectzulu.common.core.CustomEntityManager;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemBlockManager;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.potion.EventHandleNullPotions;
import projectzulu.common.potion.PotionManager;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = DefaultProps.BlocksModId, name = "Project Zulu Block and Items", version = DefaultProps.VERSION_STRING, dependencies = DefaultProps.DEPENDENCY_CORE)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ProjectZulu_Blocks {

    private static int defaultBlockID = 1200;

    public static int getNextDefaultBlockID() {
        return defaultBlockID++;
    }

    private static int defaultItemID = 9000;

    public static int getNextDefaultItemID() {
        return defaultItemID++;
    }

    /* Armor Indexes */
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

    @Instance(DefaultProps.BlocksModId)
    public static ProjectZulu_Blocks modInstance;

    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        Configuration zuluConfig = new Configuration(new File(event.getModConfigurationDirectory(),
                DefaultProps.configDirectory + DefaultProps.defaultConfigFile));
        zuluConfig.load();

        scaleIndex = ProjectZulu_Core.proxy.addArmor("scaleArmor");
        goldScaleIndex = ProjectZulu_Core.proxy.addArmor("goldscale");
        ironScaleIndex = ProjectZulu_Core.proxy.addArmor("ironscale");
        diamondScaleIndex = ProjectZulu_Core.proxy.addArmor("diamondscale");

        whiteWoolIndex = ProjectZulu_Core.proxy.addArmor("whitedesertcloth");
        redWoolIndex = ProjectZulu_Core.proxy.addArmor("reddesertcloth");
        greenWoolIndex = ProjectZulu_Core.proxy.addArmor("greendesertcloth");
        blueWoolIndex = ProjectZulu_Core.proxy.addArmor("bluedesertcloth");
        cactusIndex = ProjectZulu_Core.proxy.addArmor("cactusarmor");
        furIndex = ProjectZulu_Core.proxy.addArmor("mammothfur");

        ProjectZuluLog.info("Starting ItemBlock Init ");
        try {
            // ItemBlockManager.preInit(zuluConfig);
//            ArmorManager.preInit(zuluConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ProjectZuluLog.info("Finished ItemBlock Init ");
        ProjectZuluLog.info("Starting Potion Init ");
        PotionManager.loadSettings(zuluConfig);
        ProjectZuluLog.info("Finsished Potion Init ");
        zuluConfig.save();

        declareModuleEntities();
        declareModuleItemBlocks();
    }

    @Init
    public void load(FMLInitializationEvent event) {

//        ProjectZuluLog.info("Starting ItemBlock Setup ");
//        try {
            // ItemBlockManager.init();
//            ArmorManager.init();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        ProjectZuluLog.info("Finished ItemBlock Setup ");

        ProjectZulu_Core.proxy.registerSimpleBlockRenderingHandlers();
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
        ItemBlockRecipeManager.setupBlockModuleRecipies();
        LanguageRegistry.instance().addStringLocalization("itemGroup.projectZuluTab", "en_US", "Project Zulu");

        if (!PotionManager.potionModuleEnabled) {
            ProjectZuluLog.info("Skipping Potion Setup, Potion Module Disabled");
        } else {
            ProjectZuluLog.info("Starting Potion Setup ");
            PotionManager.setupAndRegisterPotions();
            ProjectZuluLog.info("Finsished Potion Setup ");
        }

        /* Turn on NullPotionHandler */
        if (PotionManager.enableNullPotionHandler) {
            MinecraftForge.EVENT_BUS.register(new EventHandleNullPotions());
        }
    }

    @ServerStarting
    public void serverStart(FMLServerStartingEvent event) {
        /* Add Custom GameRules */
        GameRules gameRule = event.getServer().worldServerForDimension(0).getGameRules();
        /* Add Does Campfire Burn GameRule: Only if not Present */
        String ruleName = "doesCampfireBurn";
        if (gameRule.hasRule(ruleName)) {
        } else {
            gameRule.addGameRule(ruleName, "false");
        }
    }

    private void declareModuleEntities() {
        CustomEntityManager.INSTANCE.addEntity(new CreeperBlossomPrimedDefault());
    }

    private void declareModuleItemBlocks() {
        ItemBlockManager.INSTANCE.addItemBlock(new AloeVeraDeclaration(), new WateredDirtDeclaration(),
                new TumbleweedDeclaration(), new JasperDeclaration(), new PalmTreeLogDeclaration(),
                new PalmTreePlankDeclaration(), new PalmTreeSlabDeclaration(), new PalmTreeDoubleSlab(),
                new PalmTreeStairsDeclaration(), new PalmTreeLeavesDeclaration(), new PalmTreeSapling(),
                new CoconutDeclaration(), new QuickSandDeclaration(), new NightBloomDeclaration(),
                new CreeperBlossomDeclaration(), new SpikesDeclaration(), new CampfireDeclaration(),
                new MobSkullsDeclaration(), new TombstoneDeclaration(), new UniversalFlowerPotDeclaration());

        ItemBlockManager.INSTANCE.addItemBlock(new AnkhDeclaration(), new AloeVeraSeedsDeclaration(),
                new WaterDropletDeclaration(), new CoconutMilkFragmentDeclaration(), new CoconutSeedDeclaration(),
                new CoconutShellDeclaration(), new ScaleItemDeclaration(), new FurPeltDeclaration(),
                new GenericCraftingItemsDeclaration(), new StructurePlacerDeclaration(), new CoconutItem());

        ItemBlockManager.INSTANCE.addItemBlock(new ScaleArmorDeclaration(), new GoldScaleArmorDeclaration(),
                new IronScaleArmorDeclaration(), new DiamondScaleArmorDeclaration(), new WhiteClothArmor(),
                new RedClothArmorDeclaration(), new GreenClothArmorDeclaration(), new BlueClothArmorDeclaration(),
                new CactusArmorDeclaration(), new FurArmorDeclaration());
    }
}
