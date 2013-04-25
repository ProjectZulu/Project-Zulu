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
import projectzulu.common.dungeon.PotionEvents;
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

@Mod(modid = DefaultProps.BlocksModId, name = "Project Zulu Block and Items", version = DefaultProps.VERSION_STRING, dependencies = DefaultProps.DEPENDENCY_CORE)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ProjectZulu_Blocks {

    @Instance(DefaultProps.BlocksModId)
    public static ProjectZulu_Blocks modInstance;

    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        Configuration zuluConfig = new Configuration(new File(event.getModConfigurationDirectory(),
                DefaultProps.configDirectory + DefaultProps.defaultConfigFile));
        zuluConfig.load();
        ProjectZuluLog.info("Starting Potion Init ");
        PotionManager.loadSettings(zuluConfig);
        ProjectZuluLog.info("Finsished Potion Init ");
        zuluConfig.save();

        declareModuleEntities();
        declareModuleItemBlocks();
    }

    @Init
    public void load(FMLInitializationEvent event) {
        
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
        ItemBlockRecipeManager.setupBlockModuleRecipies();

        if (!PotionManager.potionModuleEnabled) {
            ProjectZuluLog.info("Skipping Potion Setup, Potion Module Disabled");
        } else {
            ProjectZuluLog.info("Starting Potion Setup ");
            PotionManager.setupAndRegisterPotions();
            ProjectZuluLog.info("Finsished Potion Setup ");
            MinecraftForge.EVENT_BUS.register(new PotionEvents());
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
        ItemBlockManager.INSTANCE.addItemBlock(new PZExtraPotionDeclaration(), new PZVanillaPotionDeclaration());

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

        ItemBlockManager.INSTANCE.addItemBlock(
                new ScaleArmorDeclaration(ProjectZulu_Core.proxy.addArmor("scaleArmor")),
                new GoldScaleArmorDeclaration(ProjectZulu_Core.proxy.addArmor("goldscale")),
                new IronScaleArmorDeclaration(ProjectZulu_Core.proxy.addArmor("ironscale")),
                new DiamondScaleArmorDeclaration(ProjectZulu_Core.proxy.addArmor("diamondscale")), new WhiteClothArmor(
                        ProjectZulu_Core.proxy.addArmor("whitedesertcloth")), new RedClothArmorDeclaration(
                        ProjectZulu_Core.proxy.addArmor("reddesertcloth")), new GreenClothArmorDeclaration(
                        ProjectZulu_Core.proxy.addArmor("greendesertcloth")), new BlueClothArmorDeclaration(
                        ProjectZulu_Core.proxy.addArmor("bluedesertcloth")), new CactusArmorDeclaration(
                        ProjectZulu_Core.proxy.addArmor("cactusarmor")),
                new FurArmorDeclaration(ProjectZulu_Core.proxy.addArmor("mammothfur")));
    }
}
