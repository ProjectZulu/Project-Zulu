package projectzulu.common.core;

import java.io.File;
import java.util.ArrayList;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public enum CustomEntityManager {
	INSTANCE;
	private ArrayList<DefaultEntity> entities = new ArrayList<DefaultEntity>();	
	
	private CustomEntityManager(){}
	
	public void addEntity(DefaultEntity... entity){
		for (DefaultEntity defaultEntity : entity) {
			entities.add(defaultEntity);
		}
	}
	
	public void loadCreaturesFromConfig(File configDirectory){
		Configuration config = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		config.load();
		for (DefaultEntity entity : entities) {
			entity.loadCreaturesFromConfig(config);
		}
		config.save();
	}
	
	public void loadBiomesFromConfig(File configDirectory){
		Configuration config = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		config.load();
		for (DefaultEntity entity : entities) {
			entity.loadBiomesFromConfig(config);
		}
		config.save();
	}
	
	public void registerEntities(File configDirectory){
		for (DefaultEntity entity : entities) {
			if(entity.shouldExist()){
				entity.registerEntity();
				entity.registerEgg();
				entity.outputDataToList(configDirectory); //TODO Create loadCustomMobData() method which calls outputData to List. loadCustom contains calls that are the same for all creatures
			}
		}
	}
	
	public void addSpawns(){
		for (DefaultEntity entity : entities) {
			if(entity.shouldExist()){
				entity.addSpawn();
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void registerModelsAndRender(){
		for (DefaultEntity entity : entities) {
			if(entity.shouldExist()){
				entity.registerModelAndRender();
			}
		}
	}
}
