package projectzulu.common.core.itemblockdeclaration;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import projectzulu.common.ProjectZulu_Core;

public abstract class ItemDeclaration implements ItemBlockDeclaration {

    private int registerPass;
    public final String name;
    private int iD = -1;
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
    public void loadFromConfig(Configuration config, boolean readOnly) {
        if (iD != -1) {
            return;
        }

        String key = name + " ID";
        Property property = null;
        if (readOnly) {
            property = config.get(Configuration.CATEGORY_ITEM, key, (String) null);
        }
        if (property != null || !readOnly) {
            iD = config.getItem(key, ProjectZulu_Core.getNextDefaultItemID()).getInt();
        }
    }

    @Override
    public final void create() {
        if (iD > 0 && !isCreated) {
            isCreated = createItem(iD);
        }
    }

    protected abstract boolean createItem(int iD);

    @Override
    public final void register() {
        if (isCreated) {
            registerItem();
        }
    }

    protected abstract void registerItem();
}
