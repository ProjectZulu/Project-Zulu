package projectzulu.common.core.itemblockdeclaration;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import projectzulu.common.ProjectZulu_Core;
import cpw.mods.fml.relauncher.Side;

public abstract class ItemSetDeclaration implements ItemBlockDeclaration {

    private int registerPass;
    public final String[] name;
    private boolean[] isEnabled;
    private boolean[] isCreated;

    public ItemSetDeclaration(String[] name) {
        this(name, 0);
    }

    public ItemSetDeclaration(String[] name, int registerPass) {
        this.registerPass = registerPass;
        this.name = name;

        isEnabled = new boolean[name.length];
        isCreated = new boolean[name.length];
        for (int i = 0; i < name.length; i++) {
            isEnabled[i] = true;
            isCreated[i] = false;
        }
    }

    @Override
    public Type getType() {
        return Type.Item;
    }

    @Override
    public int getRegisterPass() {
        return registerPass;
    }

    @Override
    public final void createWithConfig(Configuration config, boolean readOnly) {
        if (!readOnly) {
            for (int i = 0; i < name.length; i++) {
                Property property = config.get("item", name[i] + " isEnabled", isEnabled[i]);
                isEnabled[i] = property.getBoolean(isEnabled[i]);
                if (isEnabled[i]) {
                    preCreateLoadConfig(config);
                    isCreated[i] = createItem(i);
                    postCreateLoadConfig(config);
                }
            }
        }
    }

    protected void preCreateLoadConfig(Configuration config) {

    }

    protected void postCreateLoadConfig(Configuration config) {

    }

    protected abstract boolean createItem(int partIndex);

    @Override
    public final void register(Side side) {
        for (int i = 0; i < name.length; i++) {
            if (isCreated[i]) {
                registerItem(i);
                if (!side.isServer()) {
                    clientRegisterBlock(i);
                }
            }
        }
    }

    protected abstract void registerItem(int partIndex);

    protected void clientRegisterBlock(int partIndex) {

    }
}
