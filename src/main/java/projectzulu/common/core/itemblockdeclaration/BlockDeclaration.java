package projectzulu.common.core.itemblockdeclaration;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import projectzulu.common.ProjectZulu_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockDeclaration implements ItemBlockDeclaration {

    private int registerPass;
    public final String name;
    private int iD = -1;
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
        /* ID Not -1 indicates ItemBlock is already loaded */
        if (iD != -1) {
            return;
        }
        String key = name + " ID";
        Property property = null;
        if (readOnly) {
            property = config.get(Configuration.CATEGORY_BLOCK, key, (String) null);
            if (property != null && property.getInt() >= 0) {
                iD = property.getInt();
                preCreateLoadConfig(config);
                if (iD > 0 && !isCreated) {
                    isCreated = createBlock(iD);
                }
                postCreateLoadConfig(config);
            }
        } else {
            iD = config.getBlock(key, ProjectZulu_Core.getNextDefaultBlockID()).getInt();
            preCreateLoadConfig(config);
            if (iD > 0 && !isCreated) {
                isCreated = createBlock(iD);
            }
            postCreateLoadConfig(config);
        }
    }

    protected void preCreateLoadConfig(Configuration config) {

    }

    protected void postCreateLoadConfig(Configuration config) {

    }

    protected abstract boolean createBlock(int iD);

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
