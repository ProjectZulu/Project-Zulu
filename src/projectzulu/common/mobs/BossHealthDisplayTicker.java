package projectzulu.common.mobs;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.src.ModLoader;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class BossHealthDisplayTicker implements ITickHandler{
	public static long inGameTicks = 0;
	public static EntityMummyPharaoh entityMummyPharaoh;
	protected float zLevel = 0.0F;
	

	public static void registerEntityMummyPharaoh(EntityMummyPharaoh newTicker){
		entityMummyPharaoh = (EntityMummyPharaoh)newTicker;

	}
	
	public static boolean isEntityNull(){
		return entityMummyPharaoh == null;

	}


	@Override
	public EnumSet<TickType> ticks() {
		// TODO Auto-generated method stub
		return EnumSet.of(TickType.RENDER);
	}
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
	}
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		boolean checkIfRan = false;
		if(entityMummyPharaoh != null && !entityMummyPharaoh.isDead && ModLoader.getMinecraftInstance().thePlayer != null){

		        Minecraft mc = ModLoader.getMinecraftInstance();
				EntityMummyPharaoh var1 = entityMummyPharaoh;
//				RenderMummyPharaoh.entityMummyPharaoh = null;
				FontRenderer var2 = mc.fontRenderer;
				//Need instance of render engine
//				this.loadTexture("/mods/items_projectzulu.png");
				   GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("/gui/icons.png"));			

				ScaledResolution var3 = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
				int var4 = var3.getScaledWidth();
				short var5 = 182;
				int var6 = var4 / 2 - var5 / 2;
				int var7 = (int)((float)var1.getCurrentHealth() / (float)var1.getMaxHealth() * (float)(var5 + 1));
				
//				if(ModLoader.getMinecraftInstance().thePlayer!= null){
//					ModLoader.getMinecraftInstance().thePlayer.addChatMessage(Integer.toString(var1.getCurrentHealth()));
//				}
				
				byte var8 = 12;
				this.drawTexturedModalRect(var6, var8, 0, 74, var5, 5);
				this.drawTexturedModalRect(var6, var8, 0, 74, var5, 5);

				if (var7 > 0)
				{
					this.drawTexturedModalRect(var6, var8, 0, 79, var7, 5);
				}

				String var9 = "Pharaoh health";
				var2.drawStringWithShadow(var9, var4 / 2 - var2.getStringWidth(var9) / 2, var8 - 10, 16711935);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//				GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("/gui/icons.png"));
				checkIfRan = true;
		}		
		if(checkIfRan == false && entityMummyPharaoh != null){
			entityMummyPharaoh = null;
		}
		inGameTicks++;
	}
	public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6)
	{
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

