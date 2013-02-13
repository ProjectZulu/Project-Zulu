package projectzulu.common.mobs;

import java.io.File;
import java.util.ArrayList;

import net.minecraftforge.common.Configuration;
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
import projectzulu.common.mobs.entitydefaults.CreeperBlossomPrimedDefault;
import projectzulu.common.mobs.entitydefaults.DefaultEntity;
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

public class CustomEntityManager {
	public static CustomEntityManager instance = new CustomEntityManager();
	private ArrayList<DefaultEntity> entities = new ArrayList<DefaultEntity>();	
	private CustomEntityManager(){
		addEntity(new ArmadilloDefault());
		addEntity(new SandwormDefault());
		addEntity(new LizardDefault());
		addEntity(new LizardSpitDefault());
		addEntity(new PharaohDefault());
		
		addEntity(new MummyDefault());
		addEntity(new VultureDefault());
		addEntity(new TreeEntDefault());
		addEntity(new MammothDefault());
		
		addEntity(new FoxDefault());
		addEntity(new BoarDefault());
		addEntity(new MimicDefault());
		addEntity(new CreeperBlossomPrimedDefault());
		addEntity(new AlligatorDefault());
		
		addEntity(new FrogDefault());
		addEntity(new PenguinDefault());
		addEntity(new BeaverDefault());
		addEntity(new BearBlackDefault());
		
		addEntity(new BearBrownDefault());
		addEntity(new BearPolarDefault());
		addEntity(new OstrichDefault());
		addEntity(new RhinoDefault());
		addEntity(new RabbitDefault());
		
		addEntity(new RedFinchDefault());
		addEntity(new GreenFinchDefault());
		addEntity(new BlueFinchDefault());
		addEntity(new GorillaDefault());
		
		addEntity(new GiraffeDefault());
		addEntity(new ElephantDefault());
		addEntity(new HorseBeigeDefault());
		addEntity(new HorseBlackDefault());
		addEntity(new HorseBrownDefault());
		
		addEntity(new HorseDarkBlackDefault());
		addEntity(new HorseDarkBrownDefault());
		addEntity(new HorseGreyDefault());
		addEntity(new HorseWhiteDefault());
		
		addEntity(new EagleDefault());
		addEntity(new HornbillDefault());
		addEntity(new PelicanDefault());
		addEntity(new MinotaurDefault());
		addEntity(new HauntedArmorDefault());
		
		addEntity(new CentipedeDefault());
		addEntity(new FollowerDefault());
		addEntity(new HorseRandomDefault());
	}
	
	public void addEntity(DefaultEntity entity){
		entities.add(entity);
	}
	
	public void loadCreatureFromConfig(File configDirectory){
		Configuration config = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		config.load();
		for (DefaultEntity entity : entities) {
			entity.loadCreatureFromConfig(config);
		}
		config.save();
	}
	
	public void loadBiomeFromConfig(File configDirectory){
		Configuration config = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		config.load();
		for (DefaultEntity entity : entities) {
			entity.loadBiomeFromConfig(config);
		}
		config.save();
	}
	
	public void registerEntity(){
		for (DefaultEntity entity : entities) {
			if(entity.shouldExist()){
				entity.registerEntity();
				entity.registerEgg();
				entity.outputDataToList();
			}
		}
	}
	
	public void addSpawn(){
		for (DefaultEntity entity : entities) {
			if(entity.shouldExist()){
				entity.addSpawn();
			}
		}
	}
}
