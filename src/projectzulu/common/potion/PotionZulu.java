package projectzulu.common.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PotionZulu extends Potion {

    protected PotionZulu(int par1, boolean par2, int par3) {
        super(par1, par2, par3);
    }

    public PotionZulu(int par1, boolean par2, int par3, int iconX, int iconY) {
        this(par1, par2, par3);
        setIconIndex(iconX, iconY);
        setEffectiveness(0.25D);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex() {
        Minecraft mc = Minecraft.getMinecraft();
        mc.renderEngine.bindTexture(getTextureFile());
        return super.getStatusIconIndex();
    }

    private String getTextureFile() {
        return DefaultProps.coreDiretory + "gui/guielements.png";
    }

    /**
     * Returns true if the potion has an instant effect instead of a continuous one (eg Harming)
     */
    @Override
    public boolean isInstant() {
        return false;
    }
}
