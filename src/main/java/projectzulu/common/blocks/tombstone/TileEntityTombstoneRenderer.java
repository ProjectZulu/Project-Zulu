package projectzulu.common.blocks.tombstone;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import projectzulu.common.blocks.ModelTombstone;
import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityTombstoneRenderer extends TileEntitySpecialRenderer {
    /** The ModelSign instance used by the TileEntityTombstoneRenderer */
    private ModelTombstone modelSign = new ModelTombstone();
    public static final ResourceLocation TOMBSTONE = new ResourceLocation(DefaultProps.blockKey, "Tombstone.png");
    public static final ResourceLocation BEACON = new ResourceLocation("textures/entity/beacon_beam.png");

    public void renderTileEntityTombstoneAt(TileEntityTombstone par1TileEntityTombstone, double par2, double par4,
            double par6, float par8) {
        int meta;
        float rotation;
        if (par8 == -1) {
            meta = 4;
            rotation = meta * 360 / 8;
        } else {
            meta = par1TileEntityTombstone.getBlockMetadata();
            rotation = par1TileEntityTombstone.getBlockMetadata() * 360 / 8;
        }
        this.bindTexture(TOMBSTONE);

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glRotatef(0.0f, 0.0f, 1.0F, 0.0f);
        GL11.glTranslatef((float) par2 + 0.5f, (float) par4 + 0.88f, (float) par6 + 0.5f);
        GL11.glScalef(-0.9f, -0.8F, 0.9F);
        this.modelSign.render1((Entity) null, 0, 0, 0, 0, 0, 0.0459f, -rotation);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glRotatef(0.0f, 0.0f, 1.0F, 0.0f);
        GL11.glTranslatef((float) par2 + 0.5f, (float) par4 + 0.88f, (float) par6 + 0.5f);
        GL11.glScalef(-0.899f, -0.8F, 0.899F);
        this.modelSign.render2((Entity) null, 0, 0, 0, 0, 0, 0.0459f, -rotation);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        float var10 = 0.6666667F;
        GL11.glTranslatef((float) par2 + 0.5F, (float) par4 + 0.5F, (float) par6 + 0.5F);
        GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F); // Var11 is getMetaData Rotation

        FontRenderer var17 = this.func_147498_b();
        float var12 = 0.016666668F * 0.8f * var10;
        float xOffset = 0.0f;
        float zOffset = 0.15f;
        float yOffset = -0.32f;
        GL11.glTranslatef(0.0F + xOffset, 0.5F * var10 + yOffset, 0.07F * var10 + zOffset);
        GL11.glScalef(var12, -var12, var12);
        GL11.glNormal3f(0.0F, 0.0F, -1.0F * var12);
        GL11.glDepthMask(false);
        byte var13 = 0;

        for (int var14 = 0; var14 < par1TileEntityTombstone.signText.length; ++var14) {
            String var15 = par1TileEntityTombstone.signText[var14];
            if (var14 == par1TileEntityTombstone.lineBeingEdited) {
                var15 = "> " + var15 + " <";
                var17.drawString(var15, -var17.getStringWidth(var15) / 2, var14 * 10
                        - par1TileEntityTombstone.signText.length * 5, var13);
            } else {
                var17.drawString(var15, -var17.getStringWidth(var15) / 2, var14 * 10
                        - par1TileEntityTombstone.signText.length * 5, var13);
            }
        }
        GL11.glDepthMask(true);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();

        if (par1TileEntityTombstone.getEntityOrb() != null) {
            GL11.glPushMatrix();
            long time = par1TileEntityTombstone.getWorldObj().getWorldInfo().getWorldTotalTime();
            float orbOrbitRadius = 0.3f;
            float periodScale = 6.0f;
            GL11.glTranslatef((float) par2 + 0.5f, (float) (par4 + 1.1f + 0.05f * Math.cos(time / 20f)),
                    (float) par6 + 0.5F);
            for (int orb = 1; orb <= 3; orb++) {
                float xTrans = getOrbitTransformationX(time, orb, 3, orbOrbitRadius, periodScale)
                        - getOrbitTransformationX(time, orb - 1, 3, orbOrbitRadius, periodScale);
                float zTrans = getOrbitTransformationZ(time, orb, 3, orbOrbitRadius, periodScale)
                        - getOrbitTransformationZ(time, orb - 1, 3, orbOrbitRadius, periodScale);
                GL11.glTranslatef(xTrans, 0.0F, zTrans);
                RenderManager.instance.renderEntityWithPosYaw(par1TileEntityTombstone.getEntityOrb(), 0.0D, 0.0D, 0.0D,
                        0.0F, par8);
            }
            GL11.glPopMatrix();
            renderBeacon(par1TileEntityTombstone.getWorldObj(), par2, par4, par6, par8);
        }
    }

    public void renderBeacon(World world, double par2, double par4, double par6, float par8) {
        float f1 = 1.0f;
        Tessellator tessellator = Tessellator.instance;
        this.bindTexture(BEACON);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, 10497.0F);
        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, 10497.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        float f2 = (float) world.getTotalWorldTime() + par8;
        float f3 = -f2 * 0.2F - (float) MathHelper.floor_float(-f2 * 0.1F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDepthMask(false);
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(255, 255, 255, 32);
        double d18 = 0.01D;
        double d19 = 0.01D;
        double d20 = 0.99D;
        double d21 = 0.01D;
        double d22 = 0.01D;
        double d23 = 0.99D;
        double d24 = 0.99D;
        double d25 = 0.99D;
        double d26 = (double) (256.0F * f1);
        double d27 = 0.0D;
        double d28 = 1.0D;
        double d29 = (double) (-1.0F + f3);
        double d30 = (double) (256.0F * f1) + d29;
        tessellator.addVertexWithUV(par2 + d18, par4 + d26, par6 + d19, d28, d30);
        tessellator.addVertexWithUV(par2 + d18, par4, par6 + d19, d28, d29);
        tessellator.addVertexWithUV(par2 + d20, par4, par6 + d21, d27, d29);
        tessellator.addVertexWithUV(par2 + d20, par4 + d26, par6 + d21, d27, d30);
        tessellator.addVertexWithUV(par2 + d24, par4 + d26, par6 + d25, d28, d30);
        tessellator.addVertexWithUV(par2 + d24, par4, par6 + d25, d28, d29);
        tessellator.addVertexWithUV(par2 + d22, par4, par6 + d23, d27, d29);
        tessellator.addVertexWithUV(par2 + d22, par4 + d26, par6 + d23, d27, d30);
        tessellator.addVertexWithUV(par2 + d20, par4 + d26, par6 + d21, d28, d30);
        tessellator.addVertexWithUV(par2 + d20, par4, par6 + d21, d28, d29);
        tessellator.addVertexWithUV(par2 + d24, par4, par6 + d25, d27, d29);
        tessellator.addVertexWithUV(par2 + d24, par4 + d26, par6 + d25, d27, d30);
        tessellator.addVertexWithUV(par2 + d22, par4 + d26, par6 + d23, d28, d30);
        tessellator.addVertexWithUV(par2 + d22, par4, par6 + d23, d28, d29);
        tessellator.addVertexWithUV(par2 + d18, par4, par6 + d19, d27, d29);
        tessellator.addVertexWithUV(par2 + d18, par4 + d26, par6 + d19, d27, d30);
        tessellator.draw();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(true);
    }

    public String reverse(String s) {
        return new StringBuffer(s).reverse().toString();
    }

    /**
     * 
     * @param time
     * @param orb Current Orb being Rendered. Index 1.
     * @param maxOrbs Maximum Orbs in a single orbit. Used to calculate offset within orbit.
     * @param orbitRadius Radius of Orb orbit
     * @param orbitPeriod Scaling factor that effects period of orbit.
     * @return
     */
    private float getOrbitTransformationX(float time, int orb, int maxOrbs, float orbitRadius, float orbitPeriod) {
        if (orb <= 0) {
            return 0f;
        } else {
            return orbitRadius * (float) Math.cos(time / orbitPeriod + orb * Math.PI * 2 / maxOrbs);
        }
    }

    private float getOrbitTransformationZ(float time, int orb, int maxOrbs, float orbitRadius, float orbitPeriod) {
        if (orb <= 0) {
            return 0;
        } else {
            return orbitRadius * (float) Math.sin(time / orbitPeriod + orb * Math.PI * 2 / maxOrbs);
        }
    }

    @Override
    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityTombstoneAt((TileEntityTombstone) par1TileEntity, par2, par4, par6, par8);
    }
}
