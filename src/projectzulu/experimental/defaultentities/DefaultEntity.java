package projectzulu.experimental.defaultentities;

import net.minecraftforge.common.Configuration;

public interface DefaultEntity {
	public abstract boolean shouldExist();
	
	public abstract void loadCreatureFromConfig(Configuration config);
	public abstract void loadBiomeFromConfig(Configuration config);
	public abstract void outputDataToList();
	public abstract void registerEntity();
	public abstract void registerEgg();
	public abstract void addSpawn();
}
