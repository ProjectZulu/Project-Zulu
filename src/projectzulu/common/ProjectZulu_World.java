package projectzulu.common;

import projectzulu.common.core.DefaultProps;
import projectzulu.common.world.terrain.CemetaryFeature;
import projectzulu.common.world.terrain.LabyrinthFeature;
import projectzulu.common.world.terrain.OasisFeature;
import projectzulu.common.world.terrain.PyramidFeature;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = DefaultProps.WorldModId, name = "Project Zulu World", version = DefaultProps.VERSION_STRING, dependencies = DefaultProps.DEPENDENCY_CORE)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class ProjectZulu_World {

	@Instance(DefaultProps.WorldModId)
	public static ProjectZulu_World modInstance;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
        ProjectZulu_Core.featureGenerator.registerStructure(new CemetaryFeature(), new LabyrinthFeature(),
                new OasisFeature(), new PyramidFeature());
	}
	
    @Init
    public void load(FMLInitializationEvent event) {
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
    }
}
