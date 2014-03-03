package projectzulu.common.core.entitydeclaration;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface EntityDeclaration {
	public abstract boolean shouldExist();
	
    /**
     * Returns the Entities Unique Identifier. Typically the Entity name, i.e. "Alligator" or "Red Finch"
     */
    public abstract String getIdentifier();
	public abstract void loadCreaturesFromConfig(Configuration config);
	public abstract void loadBiomesFromConfig(Configuration config);
	public abstract void loadCustomMobData(File configDirectory);
	public abstract void registerEntity();
	public abstract void registerEgg();
	public abstract void addSpawn();
	
	@SideOnly(Side.CLIENT)
	public abstract void registerModelAndRender();	
}
