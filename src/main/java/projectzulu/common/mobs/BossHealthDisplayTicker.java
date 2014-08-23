package projectzulu.common.mobs;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLiving;

import org.lwjgl.opengl.GL11;

import projectzulu.common.mobs.entity.EntityGenericAnimal;
import projectzulu.common.mobs.entity.EntityMummyPharaoh;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class BossHealthDisplayTicker {
       
    public static EntityMummyPharaoh targetBoss;
    protected float zLevel = 0.0F;

    public static void registerEntityMummyPharaoh(EntityMummyPharaoh newTicker) {
        targetBoss = newTicker;
    }

    public static boolean validTargetPresent(EntityLiving targetBoss) {
        return targetBoss != null && !targetBoss.isDead;
    }
    
    @SubscribeEvent
    public void TickEvent(RenderTickEvent event) {
        if (event.phase == Phase.END) {
            if (validTargetPresent(targetBoss) && Minecraft.getMinecraft().thePlayer != null) {
                renderBossHealthBar(targetBoss, "Pharaoh Health");
            }
        }
    }

    public void renderBossHealthBar(EntityGenericAnimal boss, String healthBarTitle) {
        Minecraft mc = Minecraft.getMinecraft();
        FontRenderer fontRenderer = mc.fontRenderer;

        /* Draw Title */
        ScaledResolution var3 = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int screenWidth = var3.getScaledWidth();
        byte healthBarHeight = 12;
        fontRenderer.drawStringWithShadow(healthBarTitle, screenWidth / 2 - fontRenderer.getStringWidth(healthBarTitle)
                / 2, healthBarHeight - 10, 16711935);

        /* Draw Health Bar */
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture(Gui.icons).getGlTextureId());
        short fullHealthBarWidth = 182;
        int healthBarOffset = screenWidth / 2 - fullHealthBarWidth / 2;
        int currHealthBarWidth = (int) ((float) boss.getHealth() / (float) boss.getMaxHealth() * (fullHealthBarWidth + 1));

        this.drawTexturedModalRect(healthBarOffset, healthBarHeight, 0, 74, fullHealthBarWidth, 5);
        if (currHealthBarWidth > 0) {
            this.drawTexturedModalRect(healthBarOffset, healthBarHeight, 0, 79, currHealthBarWidth, 5);
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
        float var7 = 0.00390625F;
        float var8 = 0.00390625F;

        Tessellator var9 = Tessellator.instance;
        var9.startDrawingQuads();
        var9.addVertexWithUV(par1 + 0, par2 + par6, this.zLevel, (par3 + 0) * var7, (par4 + par6) * var8);
        var9.addVertexWithUV(par1 + par5, par2 + par6, this.zLevel, (par3 + par5) * var7, (par4 + par6) * var8);
        var9.addVertexWithUV(par1 + par5, par2 + 0, this.zLevel, (par3 + par5) * var7, (par4 + 0) * var8);
        var9.addVertexWithUV(par1 + 0, par2 + 0, this.zLevel, (par3 + 0) * var7, (par4 + 0) * var8);
        var9.draw();
    }
}
