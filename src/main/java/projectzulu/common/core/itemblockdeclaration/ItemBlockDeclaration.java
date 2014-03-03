package projectzulu.common.core.itemblockdeclaration;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.relauncher.Side;

public interface ItemBlockDeclaration {

    public enum Type {
        Block, Item;
    }

    public abstract Type getType();

    public abstract int getRegisterPass();

    /**
     * Loads ands saves the ItemBlock from the configuration filed.
     * 
     * Called twice. Once for ItemBlocks already in the Config and again for new Items. Pass is indicated by readOnly
     * boolean.
     * 
     * @param config Configuration file to read save from
     * @param readOnly Boolean indicating this is the pass for ItemBlock already in the Configuration file
     */
    public abstract void createWithConfig(Configuration config, boolean readOnly);

    /**
     * Registers the Created Block with the Environment
     * 
     * i.e GameRegistry
     */
    public abstract void register(Side side);
}
