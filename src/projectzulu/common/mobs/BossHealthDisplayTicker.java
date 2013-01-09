package projectzulu.common.mobs;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLiving;
import net.minecraft.src.ModLoader;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class BossHealthDisplayTicker implements ITickHandler{
	public static EntityMummyPharaoh targetBoss;
	protected float zLevel = 0.0F;
	
	public static void registerEntityMummyPharaoh(EntityMummyPharaoh newTicker){
		targetBoss = (EntityMummyPharaoh)newTicker;
	}
	
	public static boolean validTargetPresent(EntityLiving targetBoss){
		return targetBoss != null && !targetBoss.isDead;
	}
	
	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER);
	}
	@Override
	public String getLabel() {
		return null;
	}

	public void tickStart(EnumSet<TickType> type, Object... tickData){}
	public void tickEnd(EnumSet<TickType> type, Object... tickData){
		if(validTargetPresent(targetBoss) && Minecraft.getMinecraft().thePlayer != null){
			renderBossHealthBar(targetBoss, "Pharaoh Health");
		}		
	}
	
	public void renderBossHealthBar(EntityGenericAnimal boss, String healthBarTitle){
		
        /* Get System Variables */
		Minecraft mc = Minecraft.getMinecraft();
		FontRenderer fontRenderer = mc.fontRenderer;
		
		/* Bind Health Bar Icon Image */
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("/gui/icons.png"));	
		
		/* Draw Health Bar */
		ScaledResolution var3 = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
		int screenWidth = var3.getScaledWidth();
		short fullHealthBarWidth = 182;
		int healthBarOffset = screenWidth / 2 - fullHealthBarWidth / 2;
		int currHealthBarWidth = (int)((float)boss.getDWHealth() / (float)boss.getMaxHealth() * (float)(fullHealthBarWidth + 1));

		byte healthBarHeight = 12;
		this.drawTexturedModalRect(healthBarOffset, healthBarHeight, 0, 74, healthBarOffset, 5);
		this.drawTexturedModalRect(healthBarOffset, healthBarHeight, 0, 74, healthBarOffset, 5);

		if (currHealthBarWidth > 0){
			this.drawTexturedModalRect(healthBarOffset, healthBarHeight, 0, 79, currHealthBarWidth, 5);
		}
		
		/* Draw Health Bar Title */
		fontRenderer.drawStringWithShadow(healthBarTitle, screenWidth / 2 - fontRenderer.getStringWidth(healthBarTitle) / 2, healthBarHeight - 10, 16711935);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	
	public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
		float var7 = 0.00390625F;
		float var8 = 0.00390625F;

		Tessellator var9 = Tessellator.instance;
		var9.startDrawingQuads();
		var9.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), (double)this.zLevel, (double)((float)(par3 + 0) * var7), (double)((float)(par4 + par6) * var8));
		var9.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), (double)this.zLevel, (double)((float)(par3 + par5) * var7), (double)((float)(par4 + par6) * var8));
		var9.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), (double)this.zLevel, (double)((float)(par3 + par5) * var7), (double)((float)(par4 + 0) * var8));
		var9.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)this.zLevel, (double)((float)(par3 + 0) * var7), (double)((float)(par4 + 0) * var8));
		var9.draw();        	
	}
}

