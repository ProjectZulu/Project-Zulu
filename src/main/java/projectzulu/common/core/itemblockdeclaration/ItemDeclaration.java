package projectzulu.common.core.itemblockdeclaration;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import com.google.common.base.Optional;

import cpw.mods.fml.relauncher.Side;

public abstract class ItemDeclaration implements ItemBlockDeclaration {

    private int registerPass;
    public final String name;
    private boolean isEnabled = true;
    private boolean isCreated = false;

    public ItemDeclaration(String name) {
        this(name, 0);
    }

    public ItemDeclaration(String name, int registerPass) {
        this.name = name;
        this.registerPass = registerPass;
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
    public void createWithConfig(Configuration config, boolean readOnly) {
        if (!readOnly) {
            Property property = config.get("item", name + " isEnabled", isEnabled);
            isEnabled = property.getBoolean(isEnabled);
            if (isEnabled) {
                preCreateLoadConfig(config);
                isCreated = createItem();
                postCreateLoadConfig(config);
            }
        }
    }

    protected void preCreateLoadConfig(Configuration config) {

    }

    protected void postCreateLoadConfig(Configuration config) {

    }

    protected abstract boolean createItem();

    @Override
    public final void register(Side side) {
        if (isCreated) {
            registerItem();
            if (!side.isServer()) {
                clientRegisterItem();
            }
        }
    }

    protected abstract void registerItem();

    protected void clientRegisterItem() {

    }
}
