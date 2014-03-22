package projectzulu.common.core;

import java.io.File;
import java.util.ArrayList;

import net.minecraftforge.common.config.Configuration;
import projectzulu.common.core.entitydeclaration.EntityDeclaration;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public enum CustomEntityManager {
	INSTANCE;
	private ArrayList<EntityDeclaration> entities = new ArrayList<EntityDeclaration>();	
	
	private CustomEntityManager(){}
	
    public void addEntity(EntityDeclaration... entity) {
        for (EntityDeclaration entityDeclaration : entity) {
            entities.add(entityDeclaration);
        }
    }
	
	public void loadCreaturesFromConfig(File configDirectory){
		Configuration config = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		config.load();
		for (EntityDeclaration entity : entities) {
			entity.loadCreaturesFromConfig(config);
		}
		config.save();
	}
	
	public void loadBiomesFromConfig(File configDirectory){
		Configuration config = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		config.load();
		for (EntityDeclaration entity : entities) {
			entity.loadBiomesFromConfig(config);
		}
		config.save();
	}
	
	public void registerEntities(File configDirectory){
		for (EntityDeclaration entity : entities) {
			if(entity.shouldExist()){
				entity.registerEntity();
				entity.registerEgg();
				entity.loadCustomMobData(configDirectory);
			}
		}
	}
	
	public void addSpawns(){
		for (EntityDeclaration entity : entities) {
			if(entity.shouldExist()){
				entity.addSpawn();
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void registerModelsAndRender(){
		for (EntityDeclaration entity : entities) {
			if(entity.shouldExist()){
				entity.registerModelAndRender();
			}
		}
	}
}
