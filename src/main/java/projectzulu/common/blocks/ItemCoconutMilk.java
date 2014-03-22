package projectzulu.common.blocks;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCoconutMilk extends Item {

    public ItemCoconutMilk(int par2, boolean par3bool) {
        super();
        maxStackSize = 12;
        setMaxDamage(5);
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        bFull3D = par3bool;
    }

    public int getMetadata(int par1) {
        return par1;
    }

}
