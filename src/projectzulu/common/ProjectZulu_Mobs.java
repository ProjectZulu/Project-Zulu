package projectzulu.common;

import java.io.File;

import projectzulu.common.core.CustomEntityManager;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entitydefaults.AlligatorDefault;
import projectzulu.common.mobs.entitydefaults.ArmadilloDefault;
import projectzulu.common.mobs.entitydefaults.BearBlackDefault;
import projectzulu.common.mobs.entitydefaults.BearBrownDefault;
import projectzulu.common.mobs.entitydefaults.BearPolarDefault;
import projectzulu.common.mobs.entitydefaults.BeaverDefault;
import projectzulu.common.mobs.entitydefaults.BlueFinchDefault;
import projectzulu.common.mobs.entitydefaults.BoarDefault;
import projectzulu.common.mobs.entitydefaults.CentipedeDefault;
import projectzulu.common.mobs.entitydefaults.EagleDefault;
import projectzulu.common.mobs.entitydefaults.ElephantDefault;
import projectzulu.common.mobs.entitydefaults.FollowerDefault;
import projectzulu.common.mobs.entitydefaults.FoxDefault;
import projectzulu.common.mobs.entitydefaults.FrogDefault;
import projectzulu.common.mobs.entitydefaults.GiraffeDefault;
import projectzulu.common.mobs.entitydefaults.GorillaDefault;
import projectzulu.common.mobs.entitydefaults.GreenFinchDefault;
import projectzulu.common.mobs.entitydefaults.HauntedArmorDefault;
import projectzulu.common.mobs.entitydefaults.HornbillDefault;
import projectzulu.common.mobs.entitydefaults.HorseBeigeDefault;
import projectzulu.common.mobs.entitydefaults.HorseBlackDefault;
import projectzulu.common.mobs.entitydefaults.HorseBrownDefault;
import projectzulu.common.mobs.entitydefaults.HorseDarkBlackDefault;
import projectzulu.common.mobs.entitydefaults.HorseDarkBrownDefault;
import projectzulu.common.mobs.entitydefaults.HorseGreyDefault;
import projectzulu.common.mobs.entitydefaults.HorseRandomDefault;
import projectzulu.common.mobs.entitydefaults.HorseWhiteDefault;
import projectzulu.common.mobs.entitydefaults.LizardDefault;
import projectzulu.common.mobs.entitydefaults.LizardSpitDefault;
import projectzulu.common.mobs.entitydefaults.MammothDefault;
import projectzulu.common.mobs.entitydefaults.MimicDefault;
import projectzulu.common.mobs.entitydefaults.MinotaurDefault;
import projectzulu.common.mobs.entitydefaults.MummyDefault;
import projectzulu.common.mobs.entitydefaults.OstrichDefault;
import projectzulu.common.mobs.entitydefaults.PelicanDefault;
import projectzulu.common.mobs.entitydefaults.PenguinDefault;
import projectzulu.common.mobs.entitydefaults.PharaohDefault;
import projectzulu.common.mobs.entitydefaults.RabbitDefault;
import projectzulu.common.mobs.entitydefaults.RedFinchDefault;
import projectzulu.common.mobs.entitydefaults.RhinoDefault;
import projectzulu.common.mobs.entitydefaults.SandwormDefault;
import projectzulu.common.mobs.entitydefaults.TreeEntDefault;
import projectzulu.common.mobs.entitydefaults.VultureDefault;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = DefaultProps.MobsModId, name = "Project Zulu Mobs", version = DefaultProps.VERSION_STRING, dependencies = DefaultProps.DEPENDENCY_CORE)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class ProjectZulu_Mobs {
	
	private static int defaultmobID = 0;
	public static int getNextDefaultMobID(){ return defaultmobID++; }
	
	private static int defaulteggID = 300;
	public static int getNextDefaultEggID(){ return defaulteggID++; }

	private static int id = 300;
	private static int eggID = 300;
	
	@Instance(DefaultProps.MobsModId)
	public static ProjectZulu_Mobs modInstance;
	File modConfigDirectory;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event){
		modConfigDirectory = event.getModConfigurationDirectory();
		declareModuleEntities();
	}

	@Init
	public void load(FMLInitializationEvent event){
		ProjectZulu_Core.proxy.registerMobSoundEvent();
	}
	
	@PostInit
	public void load(FMLPostInitializationEvent event){}
	
	private void declareModuleEntities(){
		CustomEntityManager.instance.addEntity(
			new ArmadilloDefault(), 	new SandwormDefault(), 		new LizardDefault(), 		new LizardSpitDefault(),
			new PharaohDefault(),		new MummyDefault(),			new VultureDefault(), 		new TreeEntDefault(),
			new MammothDefault(), 		new FoxDefault(),			new BoarDefault(), 			new MimicDefault(),
			new AlligatorDefault(), 	new FrogDefault(),			new PenguinDefault(),		new BeaverDefault(),
			new BearBlackDefault(),		new BearBrownDefault(),		new BearPolarDefault(),		new OstrichDefault(),
			new RhinoDefault(),			new RabbitDefault(),		new RedFinchDefault(),		new GreenFinchDefault(),
			new BlueFinchDefault(),		new GorillaDefault(),		new GiraffeDefault(),		new ElephantDefault(),
			new HorseBeigeDefault(),	new HorseBlackDefault(),	new HorseBrownDefault(),	new HorseDarkBlackDefault(),
			new HorseDarkBrownDefault(),new HorseGreyDefault(),		new HorseWhiteDefault(),	new EagleDefault(),
			new HornbillDefault(),		new PelicanDefault(),		new MinotaurDefault(),		new HauntedArmorDefault(),
			new CentipedeDefault(),		new FollowerDefault(),		new HorseRandomDefault());
	}
}
