package projectzulu.common.mobs.renders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import projectzulu.common.Properties;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.mobs.entity.EntityGenericAnimal;
import projectzulu.common.mobs.entity.EntityGenericTameable;
import projectzulu.common.mobs.entity.EntityStates;

public class RenderTameable extends RenderGenericLiving {

    protected float zLevel = 0.1F;

    public final static ResourceLocation TAMING_GUI = new ResourceLocation(DefaultProps.coreKey, "gui/guielements.png");

    public RenderTameable(ModelBase modelBase, float shadowSize, ResourceLocation textureLocation) {
        super(modelBase, shadowSize, textureLocation);
    }

    public void renderTameable(EntityGenericAnimal par1EntityFox, double par2, double par4, double par6, float par8,
            float par9) {
        if (par1EntityFox instanceof EntityGenericTameable) {
            EntityGenericAnimal tameable = par1EntityFox;
            if (tameable.isTamed()) {
                renderLivingNamePlate(tameable, tameable.getUsername(), par2, par4, par6, 64);
            }
        }
        super.doRender(par1EntityFox, par2, par4, par6, par8, par9);
    }

    @Override
    public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8,
            float par9) {
        this.renderTameable((EntityGenericAnimal) par1EntityLiving, par2, par4, par6, par8, par9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderTameable((EntityGenericAnimal) par1Entity, par2, par4, par6, par8, par9);
    }

    protected void renderLivingNamePlate(EntityGenericAnimal tameableEntity, String par2Str, double par3, double par5,
            double par7, int par9) {

        double var10 = tameableEntity.getDistanceSqToEntity(this.renderManager.livingPlayer);
        if (var10 <= par9 * par9) {
            FontRenderer var12 = this.getFontRendererFromRenderManager();
            float var14 = Properties.namePlateScale;
            GL11.glPushMatrix();
            GL11.glTranslatef((float) par3 + 0.0F, (float) par5 + tameableEntity.height, (float) par7);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(-var14, -var14, var14);
            GL11.glDisable(GL11.GL_LIGHTING);

            /* Draw String */
            byte var16 = 0;
            int var17 = var12.getStringWidth(par2Str) / 2;
            var12.drawString(par2Str, -17, var16, 553648127);
            GL11.glEnable(GL11.GL_DEPTH_TEST);

            /* Set Name Plate Opacity */
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, Properties.namePlateOpacity);

            /* Draw Background */
            Minecraft.getMinecraft().renderEngine.bindTexture(TAMING_GUI);
            int iconXCoord = 0;
            int iconYCoord = 0;
            iconXCoord = 16;
            iconYCoord = 0;
            this.drawTexturedModalRect(-76 / 2 + 16, -20 / 8, iconXCoord, iconYCoord, 76 - 16, 20);

            /* Draw Action Icon */
            if (tameableEntity.getEntityState() == EntityStates.sitting) {
                /* Sitting */
                iconXCoord = 18;
                iconYCoord = 21;
                this.drawTexturedModalRect(-76 / 2, -2, iconXCoord, iconYCoord, 17, 20);
            } else if (tameableEntity.getEntityState() == EntityStates.attacking
                    || tameableEntity.getEntityState() == EntityStates.looking) {
                /* Attacking */
                iconXCoord = 0;
                iconYCoord = 63;
                this.drawTexturedModalRect(-76 / 2, -2, iconXCoord, iconYCoord, 17, 20);
            } else if (tameableEntity.getAITarget() != null || tameableEntity.getAttackTarget() != null) {
                /* Defending */
                iconXCoord = 18;
                iconYCoord = 42;
                this.drawTexturedModalRect(-76 / 2, -2, iconXCoord, iconYCoord, 17, 20);
            } else if (tameableEntity.getEntityState() == EntityStates.following) {
                /* Following */
                iconXCoord = 0;
                iconYCoord = 21;
                this.drawTexturedModalRect(-76 / 2, -2, iconXCoord, iconYCoord, 17, 20);
            } else if (tameableEntity.getEntityState() == EntityStates.idle) {
                /* Idle */
                iconXCoord = 0;
                iconYCoord = 42;
                this.drawTexturedModalRect(-76 / 2, -2, iconXCoord, iconYCoord, 17, 20);
            } else if (tameableEntity.getEntityState() == EntityStates.inLove) {
                /* Love */
                iconXCoord = 18;
                iconYCoord = 63;
                this.drawTexturedModalRect(-76 / 2, -2, iconXCoord, iconYCoord, 17, 20);
            } else {
                /* Unknown */
                iconXCoord = 18;
                iconYCoord = 85;
                this.drawTexturedModalRect(-76 / 2, -2, iconXCoord, iconYCoord, 17, 20);

            }

            /* Draw Health Background */
            int maxHealth = (int) tameableEntity.getMaxHealth(); // MaxHealth
            zLevel = 0.2f;
            int screenLocationX = -18 - 7;
            int screenLocationY = 11;
            for (int i = 0; i < maxHealth; i++) {
                if (i > 0 && i % 16 == 0) {
                    screenLocationX = -18 - 7;
                    screenLocationY += 7;
                }
                if (i % 2 == 0) {
                    iconXCoord = 83; // 77
                    iconYCoord = 8;
                    screenLocationX += 7;
                    screenLocationY += 0;
                    this.drawTexturedModalRect(screenLocationX, screenLocationY, iconXCoord, iconYCoord, 7, 7); // 5
                }
            }

            /* Draw Health Icon(s) */
            int curHealth = MathHelper.ceiling_float_int(tameableEntity.getHealth());
            zLevel = 0.1f;
            screenLocationX = -18 - 3;
            screenLocationY = 11;
            for (int i = 0; i < curHealth; i++) {
                if (i > 0 && i % 16 == 0) {
                    screenLocationX = -18 - 3;
                    screenLocationY += 7;
                }
                if (i % 2 == 0) {
                    iconXCoord = 83; // 77
                    iconYCoord = 0;
                    screenLocationX += 3;
                    screenLocationY += 0;
                    this.drawTexturedModalRect(screenLocationX, screenLocationY, iconXCoord, iconYCoord, 4, 7); // 5
                } else {
                    iconXCoord = 87; // 77
                    iconYCoord = 0;
                    if (i == 1) {
                        screenLocationX += 0;
                    }
                    screenLocationX += 4;
                    screenLocationY += 0;
                    this.drawTexturedModalRect(screenLocationX, screenLocationY, iconXCoord, iconYCoord, 3, 7); // 5
                }
            }
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
        }
    }

    private void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
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
