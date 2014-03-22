package projectzulu.common.mobs.renders;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import projectzulu.common.api.ItemList;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.mobs.entity.EntityLizardSpit;

public class RenderLizardSpit extends Render implements RenderWrapper {
    private float field_77002_a;

    public RenderLizardSpit(float par1) {
        this.field_77002_a = par1;
    }

    public void doRenderLizardSpit(EntityLizardSpit par1EntityLizardSpit, double par2, double par4, double par6,
            float par8, float par9) {

        IIcon icon;
        if (ItemList.genericCraftingItems.isPresent()) {
            icon = ItemGenerics.Properties.LizardSpit.getIcon();
        } else {
            icon = Items.fire_charge.getIconFromDamage(0);
        }
        if (icon != null) { // Icon only null if ItemGenerics is disabled, TODO more elegant solution
            GL11.glPushMatrix();
            this.bindEntityTexture(par1EntityLizardSpit);
            GL11.glTranslatef((float) par2, (float) par4, (float) par6);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            float var10 = this.field_77002_a;
            GL11.glScalef(var10 / 1.0F, var10 / 1.0F, var10 / 1.0F);

            Tessellator var12 = Tessellator.instance;
            float var13 = icon.getMinU();
            float var14 = icon.getMaxU();
            float var15 = icon.getMinV();
            float var16 = icon.getMaxV();
            float var17 = 1.0F;
            float var18 = 0.5F;
            float var19 = 0.25F;
            GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            var12.startDrawingQuads();
            var12.setNormal(0.0F, 1.0F, 0.0F);
            var12.addVertexWithUV((double) (0.0F - var18), (double) (0.0F - var19), 0.0D, (double) var13,
                    (double) var16);
            var12.addVertexWithUV((double) (var17 - var18), (double) (0.0F - var19), 0.0D, (double) var14,
                    (double) var16);
            var12.addVertexWithUV((double) (var17 - var18), (double) (1.0F - var19), 0.0D, (double) var14,
                    (double) var15);
            var12.addVertexWithUV((double) (0.0F - var18), (double) (1.0F - var19), 0.0D, (double) var13,
                    (double) var15);
            var12.draw();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
        }
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderLizardSpit((EntityLizardSpit) par1Entity, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TextureMap.locationItemsTexture;
    }

    @Override
    public Render getRender() {
        return this;
    }
}
