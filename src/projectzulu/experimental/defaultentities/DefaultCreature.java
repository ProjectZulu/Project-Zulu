package projectzulu.experimental.defaultentities;

import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.common.Configuration;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.ProjectZulu_Mobs;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public abstract class DefaultCreature implements DefaultEntity{
	String mobName;
	Class mobClass;
	
	boolean shouldExist = true;
	boolean reportSpawningInLog = false;
	
	int trackingRange;
	int updateFrequency;
	boolean sendsVelocityUpdates;
		
	protected DefaultCreature(String mobName, Class mobClass){
		this.mobName = mobName;
		this.mobClass = mobClass;
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
	public void loadCreatureFromConfig(Configuration config) {		
		shouldExist = config.get("MOB CONTROLS."+mobName, mobName.toLowerCase()+" shouldExist", shouldExist).getBoolean(shouldExist);
		reportSpawningInLog = config.get("MOB CONTROLS."+mobName, mobName.toLowerCase()+" reportSpawningInLog", reportSpawningInLog).getBoolean(reportSpawningInLog);
		updateFrequency = config.get("MOB CONTROLS."+mobName, mobName.toLowerCase()+" UpdateFrequency", updateFrequency).getInt(updateFrequency);
	}
	
	@Override
	public void loadBiomeFromConfig(Configuration config) {}

	@Override
	public void registerEntity() {
		EntityRegistry.registerModEntity(mobClass, mobName, ProjectZulu_Mobs.getNextDefaultMobID(),
				ProjectZulu_Core.modInstance, trackingRange, updateFrequency, true);
		LanguageRegistry.instance().addStringLocalization("entity.".concat(DefaultProps.CoreModId).concat(".").concat(mobName).concat(".name"), "en_US", mobName);		
	}

	@Override
	public void registerEgg() {}
	
	@Override
	public void addSpawn() {}
}
