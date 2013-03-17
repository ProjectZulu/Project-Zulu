package projectzulu.common.core;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import com.google.common.base.Optional;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.common.Configuration;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
//TODO: Think of Better Keyword than Default, Possibly InitialAlligator, AlligatorDeclaration
public abstract class DefaultCreature implements DefaultEntity{
	protected String mobName;
	protected Class mobClass;
	protected String modelClass;
	protected String renderClass;
	protected EnumCreatureType enumCreatureType;
	protected boolean shouldExist = true;
	protected boolean reportSpawningInLog = false;
	protected int maxSpawnInChunk = 4;

	protected int trackingRange;
	protected int updateFrequency;
	protected boolean sendsVelocityUpdates;
		
	protected DefaultCreature(String mobName, Class mobClass, EnumCreatureType creatureType){
		this.mobName = mobName;
		this.mobClass = mobClass;
		this.enumCreatureType = creatureType;
	}
	
	protected void setModelAndRender(String modelClass, String renderClass){
		this.modelClass = modelClass;
		this.renderClass = renderClass;
	}
	
	protected void setModelAndRender(Class modelClass, String renderClass){
		setModelAndRender(modelClass.getName(), renderClass);
	}
	
	protected void setRegistrationProperties(int trackingRange, int updateFrequency, boolean sendsVelocityUpdates){
		this.trackingRange = trackingRange;
		this.updateFrequency = updateFrequency;
		this.sendsVelocityUpdates = sendsVelocityUpdates;
	}
	
	@Override
	public boolean shouldExist() {
		return shouldExist;
	}
	
	@Override
	public void loadCreaturesFromConfig(Configuration config) {
		shouldExist = config.get("MOB CONTROLS."+mobName, mobName.toLowerCase()+" shouldExist", shouldExist).getBoolean(shouldExist);
		reportSpawningInLog = config.get("MOB CONTROLS."+mobName, mobName.toLowerCase()+" reportSpawningInLog", reportSpawningInLog).getBoolean(reportSpawningInLog);
		updateFrequency = config.get("MOB CONTROLS."+mobName, mobName.toLowerCase()+" UpdateFrequency", updateFrequency).getInt(updateFrequency);
		maxSpawnInChunk = config.get("MOB CONTROLS."+mobName, "MaxSpawnInChunk", maxSpawnInChunk).getInt(maxSpawnInChunk);
	}
	
	@Override
	public void loadBiomesFromConfig(Configuration config) {}

	@Override
	public void registerEntity() {
		EntityRegistry.registerModEntity(mobClass, mobName, ProjectZulu_Core.getNextDefaultEntityID(),
				ProjectZulu_Core.modInstance, trackingRange, updateFrequency, true);
		LanguageRegistry.instance().addStringLocalization("entity.".concat(DefaultProps.CoreModId).concat(".").concat(mobName).concat(".name"), "en_US", mobName);		
	}
	
	@Override
	public void loadCustomMobData(File configDirectory) {
		Configuration config = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		config.load();
		CustomMobData customMobData = new CustomMobData(mobName);
		outputDataToList(config, customMobData);	
		CustomEntityList customEntity = CustomEntityList.getByName(mobName);
		if(customEntity != null)
			customEntity.modData = Optional.of(customMobData);
		else
			ProjectZuluLog.severe("Entity %s does not have an Entry in the CustomEntityList", mobName);
				
		config.save();
	}
	/* Create loadCustomMobData() method which calls outputData to List. loadCustom contains calls that are the same for all creatures */
	public void outputDataToList(Configuration config, CustomMobData customMobData){
		customMobData.reportSpawningInLog = reportSpawningInLog;
		customMobData.creatureType = ConfigHelper.configGetCreatureType(config, "MOB CONTROLS."+mobName, "Creature Type", enumCreatureType);
		customMobData.shouldDespawn = config.get("MOB CONTROLS."+mobName, mobName+" Should Despawn", enumCreatureType == EnumCreatureType.creature ? false : true).getBoolean(true);
		ConfigHelper.userItemConfigRangeToMobData(config, "MOB CONTROLS."+mobName, customMobData);
		customMobData.maxSpawnInChunk = maxSpawnInChunk;
	}
	
	@Override
	public void registerEgg() {}
	
	@Override
	public void addSpawn() {}
	
	@Override
	public void registerModelAndRender() {
		try {
			if(modelClass != null && modelClass.length() > 0){
				RenderingRegistry.registerEntityRenderingHandler(mobClass, (Render)Class.forName(renderClass).getConstructor(ModelBase.class, Float.TYPE).newInstance(Class.forName(modelClass).newInstance(), 0.5f));
			}else{
				RenderingRegistry.registerEntityRenderingHandler(mobClass, (Render)Class.forName(renderClass).getConstructor(Float.TYPE).newInstance(0.5f));
			}
		}catch (InstantiationException e){
			ProjectZuluLog.severe("Error Registering Model and Render of %s due to %s: %s",this.getClass().getSimpleName(), e.getClass().getSimpleName(),e.getMessage());
			e.printStackTrace();
		}catch (IllegalAccessException e){
			ProjectZuluLog.severe("Error Registering Model and Render of %s due to %s: %s",this.getClass().getSimpleName(), e.getClass().getSimpleName(),e.getMessage());
			e.printStackTrace();
		}catch (IllegalArgumentException e){
			ProjectZuluLog.severe("Error Registering Model and Render of %s due to %s: %s",this.getClass().getSimpleName(), e.getClass().getSimpleName(),e.getMessage());
			e.printStackTrace();
		}catch (InvocationTargetException e){
			ProjectZuluLog.severe("Error Registering Model and Render of %s due to %s: %s",this.getClass().getSimpleName(), e.getClass().getSimpleName(),e.getMessage());
			e.printStackTrace();
		}catch (NoSuchMethodException e){
			ProjectZuluLog.severe("Error Registering Model and Render of %s due to %s: %s",this.getClass().getSimpleName(), e.getClass().getSimpleName(),e.getMessage());
			e.printStackTrace();
		}catch (SecurityException e){
			ProjectZuluLog.severe("Error Registering Model and Render of %s due to %s: %s",this.getClass().getSimpleName(), e.getClass().getSimpleName(),e.getMessage());
			e.printStackTrace();
		}catch (ClassNotFoundException e){
			ProjectZuluLog.severe("Error Registering Model and Render of %s due to %s: %s",this.getClass().getSimpleName(), e.getClass().getSimpleName(),e.getMessage());
			e.printStackTrace();
		}
	}
}
