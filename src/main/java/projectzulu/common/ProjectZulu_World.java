package projectzulu.common;

import projectzulu.common.blocks.itemblockdeclarations.StructurePlacerDeclaration;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemBlockManager;
import projectzulu.common.world.terrain.CathedralFeature;
import projectzulu.common.world.terrain.CemetaryFeature;
import projectzulu.common.world.terrain.LabyrinthFeature;
import projectzulu.common.world.terrain.OasisFeature;
import projectzulu.common.world.terrain.PyramidFeature;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = DefaultProps.WorldModId, name = "Project Zulu World", version = DefaultProps.VERSION_STRING, dependencies = DefaultProps.DEPENDENCY_CORE)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ProjectZulu_World {

    @Instance(DefaultProps.WorldModId)
    public static ProjectZulu_World modInstance;

    public ProjectZulu_World() {
        ItemBlockManager.INSTANCE.addItemBlock(new StructurePlacerDeclaration());
    }

    static {
        ProjectZulu_Core.featureGenerator.registerStructure(new PyramidFeature(), new LabyrinthFeature(),
                new CemetaryFeature(), new OasisFeature(), new CathedralFeature());
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @EventHandler
    public void load(FMLInitializationEvent event) {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}
