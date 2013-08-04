package projectzulu.common.blocks;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.DefaultProps;

public class ItemCoconutItem extends Item {

    public ItemCoconutItem(int itemID, boolean full3D, String name) {
        super(itemID);
        maxStackSize = 1;
        setMaxDamage(5);
        setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        bFull3D = full3D;
        setHasSubtypes(true);
        setUnlocalizedName(name);
        func_111206_d(DefaultProps.blockKey + ":" + name);
    }
}
