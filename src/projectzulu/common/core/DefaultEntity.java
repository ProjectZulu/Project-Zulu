package projectzulu.common.core;

import java.io.File;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface DefaultEntity {
	public abstract boolean shouldExist();
	
	public abstract void loadCreaturesFromConfig(Configuration config);
	public abstract void loadBiomesFromConfig(Configuration config);
	public abstract void loadCustomMobData(File configDirectory);
	public abstract void registerEntity();
	public abstract void registerEgg();
	public abstract void addSpawn();
	
	@SideOnly(Side.CLIENT)
	public abstract void registerModelAndRender();	
}
