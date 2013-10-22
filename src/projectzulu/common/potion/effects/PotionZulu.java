package projectzulu.common.potion.effects;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PotionZulu extends Potion {

    public ResourceLocation resourceLocation = new ResourceLocation(DefaultProps.coreKey, "gui/guielements.png");

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

    private ResourceLocation getTextureFile() {
        return resourceLocation;
    }

    /**
     * Returns true if the potion has an instant effect instead of a continuous one (eg Harming)
     */
    @Override
    public boolean isInstant() {
        return false;
    }
}
