package projectzulu.common.blocks;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemScale extends Item {

    public ItemScale(int iD, boolean full3D, String name) {
        super(iD);
        maxStackSize = 64;
        setMaxDamage(5);
        setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        bFull3D = full3D;
        setHasSubtypes(true);
        setUnlocalizedName(DefaultProps.blockKey + ":" + name.toLowerCase());
        func_111206_d(DefaultProps.blockKey + ":" + name.toLowerCase());
    }
}