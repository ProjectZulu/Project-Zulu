package projectzulu.common.potion;

import net.minecraft.client.Minecraft;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PotionThorns extends PotionZulu{
	
	protected PotionThorns(int par1, boolean par2, int par3) { 
		super(par1, par2, par3);
		setIconIndex(3, 0);
		setEffectiveness(0.25D);
	}	
	
	@Override
	@SideOnly(Side.CLIENT)
	public int getStatusIconIndex() {
		Minecraft mc = Minecraft.getMinecraft();
//		mc.renderEngine.bindTexture(mc.renderEngine.getTexture(getTextureFile())); //TODO: Commented
		return super.getStatusIconIndex();
	}
	
	private String getTextureFile() {
		return DefaultProps.coreDiretory + "gui/guielements.png";
	}
}
