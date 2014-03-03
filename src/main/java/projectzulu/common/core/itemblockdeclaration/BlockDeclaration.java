package projectzulu.common.core.itemblockdeclaration;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import projectzulu.common.ProjectZulu_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockDeclaration implements ItemBlockDeclaration {

    private int registerPass;
    public final String name;
    private boolean isEnabled = true;
    private boolean isCreated = false;

    public BlockDeclaration(String name) {
        this(name, 0);
    }

    public BlockDeclaration(String name, int registerPass) {
        this.name = name;
        this.registerPass = registerPass;
    }

    @Override
    public Type getType() {
        return Type.Block;
    }

    @Override
    public int getRegisterPass() {
        return registerPass;
    }

    @Override
    public final void createWithConfig(Configuration config, boolean readOnly) {
        if (!readOnly) {
            Property property = config.get("block", name + " isEnabled", isEnabled);
            isEnabled = property.getBoolean(isEnabled);
            if (isEnabled) {
                preCreateLoadConfig(config);
                isCreated = createBlock();
                postCreateLoadConfig(config);
            }
        }
    }

    protected void preCreateLoadConfig(Configuration config) {

    }

    protected void postCreateLoadConfig(Configuration config) {

    }

    protected abstract boolean createBlock();

    @Override
    public final void register(Side side) {
        if (isCreated) {
            registerBlock();
            if (!side.isServer()) {
                clientRegisterBlock();
            }
        }
    }

    protected abstract void registerBlock();

    @SideOnly(Side.CLIENT)
    protected void clientRegisterBlock() {

    }
}
