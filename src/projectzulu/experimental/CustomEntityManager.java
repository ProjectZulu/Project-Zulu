package projectzulu.experimental;

import java.io.File;
import java.util.ArrayList;

import net.minecraftforge.common.Configuration;
import projectzulu.common.core.DefaultProps;
import projectzulu.experimental.defaultentities.AlligatorDefault;
import projectzulu.experimental.defaultentities.ArmadilloDefault;
import projectzulu.experimental.defaultentities.BearBlackDefault;
import projectzulu.experimental.defaultentities.BearBrownDefault;
import projectzulu.experimental.defaultentities.BearPolarDefault;
import projectzulu.experimental.defaultentities.BeaverDefault;
import projectzulu.experimental.defaultentities.BlueFinchDefault;
import projectzulu.experimental.defaultentities.BoarDefault;
import projectzulu.experimental.defaultentities.CentipedeDefault;
import projectzulu.experimental.defaultentities.CreeperBlossomPrimedDefault;
import projectzulu.experimental.defaultentities.DefaultEntity;
import projectzulu.experimental.defaultentities.EagleDefault;
import projectzulu.experimental.defaultentities.ElephantDefault;
import projectzulu.experimental.defaultentities.FollowerDefault;
import projectzulu.experimental.defaultentities.FoxDefault;
import projectzulu.experimental.defaultentities.FrogDefault;
import projectzulu.experimental.defaultentities.GiraffeDefault;
import projectzulu.experimental.defaultentities.GorillaDefault;
import projectzulu.experimental.defaultentities.GreenFinchDefault;
import projectzulu.experimental.defaultentities.HauntedArmorDefault;
import projectzulu.experimental.defaultentities.HornbillDefault;
import projectzulu.experimental.defaultentities.HorseBeigeDefault;
import projectzulu.experimental.defaultentities.HorseBlackDefault;
import projectzulu.experimental.defaultentities.HorseBrownDefault;
import projectzulu.experimental.defaultentities.HorseDarkBlackDefault;
import projectzulu.experimental.defaultentities.HorseDarkBrownDefault;
import projectzulu.experimental.defaultentities.HorseGreyDefault;
import projectzulu.experimental.defaultentities.HorseRandomDefault;
import projectzulu.experimental.defaultentities.HorseWhiteDefault;
import projectzulu.experimental.defaultentities.LizardDefault;
import projectzulu.experimental.defaultentities.LizardSpitDefault;
import projectzulu.experimental.defaultentities.MammothDefault;
import projectzulu.experimental.defaultentities.MimicDefault;
import projectzulu.experimental.defaultentities.MinotaurDefault;
import projectzulu.experimental.defaultentities.MummyDefault;
import projectzulu.experimental.defaultentities.OstrichDefault;
import projectzulu.experimental.defaultentities.PelicanDefault;
import projectzulu.experimental.defaultentities.PenguinDefault;
import projectzulu.experimental.defaultentities.PharaohDefault;
import projectzulu.experimental.defaultentities.RabbitDefault;
import projectzulu.experimental.defaultentities.RedFinchDefault;
import projectzulu.experimental.defaultentities.RhinoDefault;
import projectzulu.experimental.defaultentities.SandwormDefault;
import projectzulu.experimental.defaultentities.TreeEntDefault;
import projectzulu.experimental.defaultentities.VultureDefault;

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
