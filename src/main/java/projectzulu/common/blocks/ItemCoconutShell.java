package projectzulu.common.blocks;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.DefaultProps;

public class ItemCoconutShell extends Item {

    public ItemCoconutShell(boolean full3D, String name) {
        super();
        maxStackSize = 12;
        setMaxDamage(5);
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        bFull3D = full3D;
        setUnlocalizedName(name.toLowerCase());
        setTextureName(DefaultProps.blockKey + ":" + name.toLowerCase());
    }

    public int getMetadata(int par1) {
        return par1;
    }
}
