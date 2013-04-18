package projectzulu.common.core.itemblockdeclaration;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import projectzulu.common.ProjectZulu_Core;

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
    public void loadFromConfig(Configuration config, boolean readOnly) {
        /* ID Not -1 indicates ItemBlock is already loaded */
        if (iD != -1) {
            return;
        }
        String key = name + " ID";
        Property property = null;
        if (readOnly) {
            property = config.get(Configuration.CATEGORY_BLOCK, key, (String) null);
        }
        if (property != null || !readOnly) {
            iD = config.getBlock(key, ProjectZulu_Core.getNextDefaultBlockID()).getInt();
        }
    }

    @Override
    public final void create() {
        if (iD > 0 && !isCreated) {
            isCreated = createBlock(iD);
        }
    }

    protected abstract boolean createBlock(int iD);

    @Override
    public final void register() {
        if (isCreated) {
            registerBlock();
        }
    }

    protected abstract void registerBlock();
}
