package projectzulu.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.potion.Potion;

public class PotionZulu extends Potion{

	protected PotionZulu(int par1, boolean par2, int par3) { 
		super(par1, par2, par3);
	}	
	
	protected PotionZulu(int par1, boolean par2, int par3, int iconX, int iconY) {
		this(par1, par2, par3);
		setIconIndex(iconX, iconY);
		setEffectiveness(0.25D);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getStatusIconIndex() {
		Minecraft mc = Minecraft.getMinecraft();
		mc.renderEngine.bindTexture(mc.renderEngine.getTexture(getTextureFile()));
		return super.getStatusIconIndex();
	}
	
	private String getTextureFile() {
		return "/Project Zulu/GUI/guielements.png";
	}
	
    /**
     * Returns true if the potion has an instant effect instead of a continuous one (eg Harming)
     */
    public boolean isInstant() {
        return false;
    }
}
