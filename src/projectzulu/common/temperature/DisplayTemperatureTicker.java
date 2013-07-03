//package projectzulu.common.temperature;
//
//import java.util.EnumSet;
//
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.FontRenderer;
//import net.minecraft.client.gui.ScaledResolution;
//import net.minecraft.client.renderer.Tessellator;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.src.ModLoader;
//import net.minecraft.util.MathHelper;
//
//import org.lwjgl.opengl.GL11;
//
//import cpw.mods.fml.common.ITickHandler;
//import cpw.mods.fml.common.TickType;
//
//public class DisplayTemperatureTicker implements ITickHandler{
//
//	protected float zLevel = 10.0F;
//	public static long inGameTicks = 0;
//	private float playerTemp = 0;
//	private float enviroTemp = 0;
//	@Override
//	public void tickStart(EnumSet<TickType> type, Object... tickData) {}
//
//	@Override
//	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
//		
//		/* Handle Display of Temperature on Client, TickType = Render*/
//		if(ModLoader.getMinecraftInstance().thePlayer != null){
//			
//			Minecraft mc = ModLoader.getMinecraftInstance();
//			EntityPlayer player = ModLoader.getMinecraftInstance().thePlayer;
//			
//			FontRenderer var2 = mc.fontRenderer;
//			ScaledResolution var3 = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
//			String textureLocation = "/mods/icons/temperature_icon.png";
//			int scalewidth = var3.getScaledWidth();
//			int scaleHeight = var3.getScaledHeight();
//
//			if( TemperatureTicker.getPlayerTemperature(player) != null && inGameTicks % 80 == 0){
//				playerTemp = TemperatureTicker.getPlayerTemperature(player);
//			}
//			if(TemperatureTicker.getPlayerLocTemperature(player) != null && inGameTicks % 80 == 0){
//				enviroTemp = TemperatureTicker.getPlayerLocTemperature(player);
//			}
//			int playerTempIconValue = (int) mapValueofSet1ToSet2(playerTemp, 3.5f, -2.5f, 0, 120);
//			int playerTempHumanReadable = 80 - playerTempIconValue;
//			int verticalOffsetFromTopY = 120;
//			int horizontalOffsetFromRightX = 0;
//			int iconXCoord = 0;
//			int iconYCoord = 0;
//			
//			GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture( textureLocation ));
//
//			scalewidth = 0;
//			scaleHeight = scaleHeight/2;
//			horizontalOffsetFromRightX = -(26+10);
//			verticalOffsetFromTopY -= 50;
//			if( (120 - playerTempIconValue) < 20){
//				/* Draw Very Cold */
//				/* Draw Blue Frame */
//				iconXCoord = 150;
//				iconYCoord = 105;
//				this.drawTexturedModalRect(scalewidth-(16+horizontalOffsetFromRightX+10), scaleHeight-(8+verticalOffsetFromTopY), iconXCoord, iconYCoord, 25, 120);
//
//				/* Draw Blue Inside */
//				iconXCoord = 175;
//				iconYCoord = 105;
//				this.drawTexturedModalRect(scalewidth-(16+horizontalOffsetFromRightX+10), scaleHeight-(8+verticalOffsetFromTopY-playerTempIconValue), iconXCoord, iconYCoord+playerTempIconValue, 25, 120-playerTempIconValue);
//				
//				/* Draw Ice Overlay */
//				if(mc.currentScreen == null){
//					scalewidth = var3.getScaledWidth();
//					scaleHeight = var3.getScaledHeight();
//					GL11.glDisable(GL11.GL_DEPTH_TEST);
//					GL11.glDepthMask(false);
//					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//					GL11.glDisable(GL11.GL_ALPHA_TEST);
//					GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("%blur%/mods/icons/IceBlur.png"));
//					Tessellator tessellator = Tessellator.instance;
//					tessellator.startDrawingQuads();
//					tessellator.addVertexWithUV(0.0D, (double)scaleHeight, -90.0D, 0.0D, 1.0D);
//					tessellator.addVertexWithUV((double)scalewidth, (double)scaleHeight, -90.0D, 1.0D, 1.0D);
//					tessellator.addVertexWithUV((double)scalewidth, 0.0D, -90.0D, 1.0D, 0.0D);
//					tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
//					tessellator.draw();
//					GL11.glDepthMask(true);
//					GL11.glEnable(GL11.GL_DEPTH_TEST);
//					GL11.glEnable(GL11.GL_ALPHA_TEST);
//					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//				}
//
//				
//			}else if( (120 - playerTempIconValue) < 30 ){
//				/* Draw Medium Cold */
//				/* Draw Blue Frame */
//				iconXCoord = 125;
//				iconYCoord = 105;
//				this.drawTexturedModalRect(scalewidth-(16+horizontalOffsetFromRightX+10), scaleHeight-(8+verticalOffsetFromTopY), iconXCoord, iconYCoord, 25, 120);
//
//				/* Draw Blue Inside */
//				iconXCoord = 175;
//				iconYCoord = 105;
//				this.drawTexturedModalRect(scalewidth-(16+horizontalOffsetFromRightX+10), scaleHeight-(8+verticalOffsetFromTopY-playerTempIconValue), iconXCoord, iconYCoord+playerTempIconValue, 25, 120-playerTempIconValue);
//
//				/* Draw Ice Overlay */
//				if(mc.currentScreen == null){
//					scalewidth = var3.getScaledWidth();
//					scaleHeight = var3.getScaledHeight();
//					GL11.glDisable(GL11.GL_DEPTH_TEST);
//					GL11.glDepthMask(false);
//					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//					GL11.glDisable(GL11.GL_ALPHA_TEST);
//					GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("%blur%/mods/icons/IceBlur2.png"));
//					Tessellator tessellator = Tessellator.instance;
//					tessellator.startDrawingQuads();
//					tessellator.addVertexWithUV(0.0D, (double)scaleHeight, -90.0D, 0.0D, 1.0D);
//					tessellator.addVertexWithUV((double)scalewidth, (double)scaleHeight, -90.0D, 1.0D, 1.0D);
//					tessellator.addVertexWithUV((double)scalewidth, 0.0D, -90.0D, 1.0D, 0.0D);
//					tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
//					tessellator.draw();
//					GL11.glDepthMask(true);
//					GL11.glEnable(GL11.GL_DEPTH_TEST);
//					GL11.glEnable(GL11.GL_ALPHA_TEST);
//					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//				}
//
//			}else if ( (120 - playerTempIconValue) < 40 ){
//				/* Draw Little Cold */
//				/* Draw Blue Frame */
//				iconXCoord = 100;
//				iconYCoord = 105;
//				this.drawTexturedModalRect(scalewidth-(16+horizontalOffsetFromRightX+10), scaleHeight-(8+verticalOffsetFromTopY), iconXCoord, iconYCoord, 25, 120);
//
//				/* Draw Blue Inside */
//				iconXCoord = 175;
//				iconYCoord = 105;
//				this.drawTexturedModalRect(scalewidth-(16+horizontalOffsetFromRightX+10), scaleHeight-(8+verticalOffsetFromTopY-playerTempIconValue), iconXCoord, iconYCoord+playerTempIconValue, 25, 120-playerTempIconValue);
//
//			}else if( (120 - playerTempIconValue) > 100 ){
//				/* Draw High Heat */
//				/* Draw Fire Frame */
//				iconXCoord = 50;
//				iconYCoord = 105;
//				this.drawTexturedModalRect(scalewidth-(16+horizontalOffsetFromRightX+10), scaleHeight-(8+verticalOffsetFromTopY), iconXCoord, iconYCoord, 25, 120);
//
//				/* Draw Fire Red Inside */
//				iconXCoord = 75;
//				iconYCoord = 105;
//				this.drawTexturedModalRect(scalewidth-(16+horizontalOffsetFromRightX+10), scaleHeight-(8+verticalOffsetFromTopY-playerTempIconValue), iconXCoord, iconYCoord+playerTempIconValue, 25, 120-playerTempIconValue);
//
//			}else if( (120 - playerTempIconValue) > 90 ){
//				/* Draw Medium Heat */
//				/* Draw Fire Frame */
//				iconXCoord = 25;
//				iconYCoord = 105;
//				this.drawTexturedModalRect(scalewidth-(16+horizontalOffsetFromRightX+10), scaleHeight-(8+verticalOffsetFromTopY), iconXCoord, iconYCoord, 25, 120);
//
//				/* Draw Fire Red Inside */
//				iconXCoord = 75;
//				iconYCoord = 105;
//				this.drawTexturedModalRect(scalewidth-(16+horizontalOffsetFromRightX+10), scaleHeight-(8+verticalOffsetFromTopY-playerTempIconValue), iconXCoord, iconYCoord+playerTempIconValue, 25, 120-playerTempIconValue);
//
//			}else if( (120 - playerTempIconValue) > 80 ){
//				/* Draw Little Heat */
//				/* Draw Fire Frame */
//				iconXCoord = 0;
//				iconYCoord = 105;
//				this.drawTexturedModalRect(scalewidth-(16+horizontalOffsetFromRightX+10), scaleHeight-(8+verticalOffsetFromTopY), iconXCoord, iconYCoord, 25, 120);
//
//				/* Draw Fire Red Inside */
//				iconXCoord = 75;
//				iconYCoord = 105;
//				this.drawTexturedModalRect(scalewidth-(16+horizontalOffsetFromRightX+10), scaleHeight-(8+verticalOffsetFromTopY-playerTempIconValue), iconXCoord, iconYCoord+playerTempIconValue, 25, 120-playerTempIconValue);
//
//			}else{
//				/* Draw Frame */
//				iconXCoord = 0;
//				iconYCoord = 0;
//				this.drawTexturedModalRect(scalewidth-(16+horizontalOffsetFromRightX+10), scaleHeight-(8+verticalOffsetFromTopY-12), iconXCoord, iconYCoord, 15, 105);
//				
//				/* Draw Red Inside */
//				iconXCoord = 200;
//				iconYCoord = 105;
//				this.drawTexturedModalRect(scalewidth-(16+horizontalOffsetFromRightX+10), scaleHeight-(8+verticalOffsetFromTopY-playerTempIconValue), iconXCoord, iconYCoord+playerTempIconValue, 25, 120-playerTempIconValue);
//			}
//			
//			/* Draw Marker */
//			iconXCoord = 30;
//			iconYCoord = 94;
//			int playerLocTemp = MathHelper.floor_float((mapValueofSet1ToSet2(enviroTemp, 3.5f, -2.5f, 0, 120)) );
//			playerLocTemp = playerLocTemp > 116 ? 116 : playerLocTemp < 14 ? 14 : playerLocTemp;
//			
//			this.drawTexturedModalRect(scalewidth-(14+horizontalOffsetFromRightX+0), scaleHeight-(8+verticalOffsetFromTopY-playerLocTemp+4), iconXCoord, iconYCoord, 4, 8);
//			scalewidth = var3.getScaledWidth();
//			horizontalOffsetFromRightX = 0;
//
//
//			
//			
//			/* Write Temp String TO Top Of Screen, Mostly For Debugging For Now*/
//			byte var8 = 12;
//			String var9 = "Current Temp: ".concat(Float.toString(120- mapValueofSet1ToSet2(playerTemp, 3.5f, -2.5f, 0, 120)));
//			String var10 = "Max Temp: ".concat(Float.toString(120- mapValueofSet1ToSet2(enviroTemp, 3.5f, -2.5f, 0, 120)));
//
////			var2.drawStringWithShadow(var10, scalewidth / 2 - var2.getStringWidth(var10) / 2, var8 - 10, 16711935);
////			var2.drawStringWithShadow(var9, scalewidth / 2 - var2.getStringWidth(var9) / 2, var8 + 10, 16711935);
//			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//
//			inGameTicks++;
//		}
//
//	}
//
//	private void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6){
//		float var7 = 0.00390625F;
//		float var8 = 0.00390625F;
//
//		Tessellator var9 = Tessellator.instance;
//		var9.startDrawingQuads();
//		var9.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), (double)this.zLevel, (double)((float)(par3 + 0) * var7), (double)((float)(par4 + par6) * var8));
//		var9.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), (double)this.zLevel, (double)((float)(par3 + par5) * var7), (double)((float)(par4 + par6) * var8));
//		var9.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), (double)this.zLevel, (double)((float)(par3 + par5) * var7), (double)((float)(par4 + 0) * var8));
//		var9.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)this.zLevel, (double)((float)(par3 + 0) * var7), (double)((float)(par4 + 0) * var8));
//		var9.draw();        	
//	}
//
//	private float mapValueofSet1ToSet2(float value, float set1min, float set1max, float set2min, float set2max){
//		return (value - set1min)*( (set2max - set2min) / (set1max - set1min) ) + set2min;
//	}
//	
//	@Override
//	public EnumSet<TickType> ticks() {
//		return EnumSet.of( TickType.RENDER);	}
//
//	@Override
//	public String getLabel() {
//		return null;
//	}
//
//}
