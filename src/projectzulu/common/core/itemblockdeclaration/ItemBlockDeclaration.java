package projectzulu.common.core.itemblockdeclaration;

import net.minecraftforge.common.Configuration;

public interface ItemBlockDeclaration {
    public enum Type {
        Block, Item;
    }

    public abstract Type getType();

    public abstract int getRegisterPass();

    public abstract void loadFromConfig(Configuration config, boolean readOnly);

    /**
     * Creates the ItemBlock and registers it in the Item/Block Lists
     * 
     * Called Twice, can be called twice once for Blocks already in Config, then a Second time for newly added blocks.
     */
    public abstract void create();

    /**
     * Registers the Created Block with the Environment
     * 
     * i.e GameRegistry and LanguageRegistry
     */
    public abstract void register();
}
