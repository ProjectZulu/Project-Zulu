package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class ModelBoar extends ModelBase {
    ModelRenderer HEADROT;
    ModelRenderer BODYROT;
    private ModelRenderer LEGROT1;
    private ModelRenderer LEGROT2;
    private ModelRenderer LEGROT3;
    private ModelRenderer LEGROT4;

    public ModelBoar() {
        textureWidth = 64;
        textureHeight = 32;
        setTextureOffset("HEADROT.head", 0, 0);
        setTextureOffset("HEADROT.nose2", 28, 0);
        setTextureOffset("HEADROT.nose1", 42, 0);
        setTextureOffset("HEADROT.tusklef1", 0, 29);
        setTextureOffset("HEADROT.tusklef2", 0, 27);
        setTextureOffset("HEADROT.tuskrig1", 0, 29);
        setTextureOffset("HEADROT.tuskrig2", 0, 27);
        setTextureOffset("HEADROT.tusklef3", 0, 29);
        setTextureOffset("HEADROT.tusklef4", 0, 27);
        setTextureOffset("HEADROT.tuskrig3", 0, 29);
        setTextureOffset("HEADROT.tuskrig4", 0, 27);
        setTextureOffset("BODYROT.body", 12, 8);
        setTextureOffset("LEGROT1.leg1", 0, 13);
        setTextureOffset("LEGROT2.leg2", 0, 13);
        setTextureOffset("LEGROT3.leg3", 0, 13);
        setTextureOffset("LEGROT4.leg4", 0, 13);

        HEADROT = new ModelRenderer(this, "HEADROT");
        HEADROT.setRotationPoint(0F, 14F, -5F);
        setRotation(HEADROT, 0F, 0F, 0F);
        HEADROT.mirror = true;
        HEADROT.addBox("head", -4F, -3.5F, -5F, 8, 7, 6);
        HEADROT.addBox("nose2", -2.5F, -0.5F, -7F, 5, 4, 2);
        HEADROT.addBox("nose1", -2F, 0.5F, -9F, 4, 3, 2);
        HEADROT.addBox("tusklef1", -4F, 2.5F, -9F, 2, 1, 1);
        HEADROT.addBox("tusklef2", -4F, 1.5F, -9F, 1, 1, 1);
        HEADROT.addBox("tuskrig1", 2F, 2.5F, -9F, 2, 1, 1);
        HEADROT.addBox("tuskrig2", 3F, 1.5F, -9F, 1, 1, 1);
        HEADROT.addBox("tusklef3", -4.5F, 2.5F, -7F, 2, 1, 1);
        HEADROT.addBox("tusklef4", -4.5F, 1.5F, -7F, 1, 1, 1);
        HEADROT.addBox("tuskrig3", 2.5F, 2.5F, -7F, 2, 1, 1);
        HEADROT.addBox("tuskrig4", 3.5F, 1.5F, -7F, 1, 1, 1);
        BODYROT = new ModelRenderer(this, "BODYROT");
        BODYROT.setRotationPoint(0F, 14.5F, -4F);
        setRotation(BODYROT, 0F, 0F, 0F);
        BODYROT.mirror = true;
        BODYROT.addBox("body", -5F, -4.5F, 0F, 10, 8, 16);
        LEGROT1 = new ModelRenderer(this, "LEGROT1");
        LEGROT1.setRotationPoint(-3F, 3.5F, 14F);
        setRotation(LEGROT1, 0F, 0F, 0F);
        LEGROT1.mirror = true;
        LEGROT1.addBox("leg1", -2F, 0F, -2F, 4, 6, 4);
        BODYROT.addChild(LEGROT1);
        LEGROT2 = new ModelRenderer(this, "LEGROT2");
        LEGROT2.setRotationPoint(3F, 3.5F, 14F);
        setRotation(LEGROT2, 0F, 0F, 0F);
        LEGROT2.mirror = true;
        LEGROT2.addBox("leg2", -2F, 0F, -2F, 4, 6, 4);
        BODYROT.addChild(LEGROT2);
        LEGROT3 = new ModelRenderer(this, "LEGROT3");
        LEGROT3.setRotationPoint(-3F, 3.5F, 2F);
        setRotation(LEGROT3, 0F, 0F, 0F);
        LEGROT3.mirror = true;
        LEGROT3.addBox("leg3", -2F, 0F, -2F, 4, 6, 4);
        BODYROT.addChild(LEGROT3);
        LEGROT4 = new ModelRenderer(this, "LEGROT4");
        LEGROT4.setRotationPoint(3F, 3.5F, 2F);
        setRotation(LEGROT4, 0F, 0F, 0F);
        LEGROT4.mirror = true;
        LEGROT4.addBox("leg4", -2F, 0F, -2F, 4, 6, 4);
        BODYROT.addChild(LEGROT4);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        float field_78145_x = 0.0f;
        float field_78145_g = 2.2F;
        float field_78151_h = 3.4F;

        if (this.isChild) {
            float var8 = 2.0F;
            GL11.glPushMatrix();
            GL11.glScalef(1.8F / var8, 1.8F / var8, 1.8F / var8);
            GL11.glTranslatef(field_78145_x, field_78145_g * f5, field_78151_h * f5);
            HEADROT.render(f5);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
            GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
            BODYROT.render(f5);
            GL11.glPopMatrix();
        } else {
            HEADROT.render(f5);
            BODYROT.render(f5);
        }
    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
        LEGROT1.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f) * 1.8F * par3;
        LEGROT3.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f) * 1.8F * par3;
        LEGROT2.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f + (float) Math.PI) * 1.8F * par3;
        LEGROT4.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f + (float) Math.PI) * 1.8F * par3;
        super.setLivingAnimations(par1EntityLiving, par2, par3, par4);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, par7Entity);
        HEADROT.rotateAngleX = Math.min(Math.max(f4, -15), +15) * (float) (Math.PI / 180f);
        HEADROT.rotateAngleY = Math.min(Math.max(f3, -30), +30) * (float) (Math.PI / 180f);
    }
}
