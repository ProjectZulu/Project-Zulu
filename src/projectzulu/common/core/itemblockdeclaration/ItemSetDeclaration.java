package projectzulu.common.core.itemblockdeclaration;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import projectzulu.common.ProjectZulu_Core;
import cpw.mods.fml.relauncher.Side;

public abstract class ItemSetDeclaration implements ItemBlockDeclaration {

    private int registerPass;
    public final String[] name;
    private int[] iD;
    private boolean[] isCreated;

    public ItemSetDeclaration(String[] name) {
        this(name, 0);
    }

    public ItemSetDeclaration(String[] name, int registerPass) {
        this.registerPass = registerPass;
        this.name = name;

        iD = new int[name.length];
        isCreated = new boolean[name.length];
        for (int i = 0; i < name.length; i++) {
            iD[i] = -1;
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
    public final void loadFromConfig(Configuration config, boolean readOnly) {
        for (int i = 0; i < name.length; i++) {
            if (iD[i] != -1) {
                continue;
            }

            String key = name[i] + " ID";
            Property property = null;
            if (readOnly) {
                property = config.get(Configuration.CATEGORY_ITEM, key, (String) null);
            }
            if (property != null || !readOnly) {
                iD[i] = config.getItem(key, ProjectZulu_Core.getNextDefaultItemID()).getInt();

                if (iD[i] > 0 && !isCreated[i]) {
                    isCreated[i] = createItem(iD[i], i);
                }
            }
        }
    }

    @Override
    public void create() {

    }

    protected abstract boolean createItem(int iD, int partIndex);

    @Override
    public final void register(Side side) {
        for (int i = 0; i < name.length; i++) {
            if (isCreated[i]) {
                registerItem(i);
            }
        }
    }

    protected abstract void registerItem(int partIndex);
}
