package projectzulu.common.blocks;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCoconutShell extends Item {

    public ItemCoconutShell(int iD, boolean full3D, String name) {
        super(iD);
        maxStackSize = 12;
        setMaxDamage(5);
        this.setCreativeTab(ProjectZulu_Core.projectZuluCreativeTab);
        bFull3D = full3D;
        setUnlocalizedName(DefaultProps.blockKey + ":" + name.toLowerCase());
        func_111206_d(DefaultProps.blockKey + ":" + name.toLowerCase());
    }

    public int getMetadata(int par1) {
        return par1;
    }

    @SideOnly(Side.CLIENT)
    public String getTextureFile() {
        return DefaultProps.itemSpriteSheet;
    }

}
