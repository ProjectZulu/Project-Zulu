package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class ModelBeaver extends ModelBase {
    ModelRenderer body;
    ModelRenderer leg4;
    ModelRenderer leg3;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer HEADROT;
    ModelRenderer TAILROT;

    public ModelBeaver() {
	textureWidth = 64;
	textureHeight = 32;
	setTextureOffset("HEADROT.head", 38, 14);
	setTextureOffset("HEADROT.nose", 38, 24);
	setTextureOffset("HEADROT.teeth2", 46, 24);
	setTextureOffset("HEADROT.teeth1", 46, 24);
	setTextureOffset("TAILROT.tail1", 0, 0);
	setTextureOffset("TAILROT.tail2", 0, 3);
	setTextureOffset("TAILROT.tail3", 0, 5);
	setTextureOffset("TAILROT.tail4", 12, 0);
	setTextureOffset("TAILROT.tail5", 12, 5);

	body = new ModelRenderer(this, 0, 14);
	body.addBox(-4.5F, -4F, -10F, 9, 8, 10);
	body.setRotationPoint(0F, 18F, 7F);
	body.setTextureSize(64, 32);
	body.mirror = true;
	setRotation(body, 0F, 0F, 0F);
	leg4 = new ModelRenderer(this, 8, 10);
	leg4.addBox(-1F, 0F, -1F, 2, 2, 2);
	leg4.setRotationPoint(3.5F, 22F, 6F);
	leg4.setTextureSize(64, 32);
	leg4.mirror = true;
	setRotation(leg4, 0F, 0F, 0F);
	leg3 = new ModelRenderer(this, 0, 10);
	leg3.addBox(-1F, 0F, -1F, 2, 2, 2);
	leg3.setRotationPoint(-3.5F, 22F, 6F);
	leg3.setTextureSize(64, 32);
	leg3.mirror = true;
	setRotation(leg3, 0F, 0F, 0F);
	leg1 = new ModelRenderer(this, 0, 10);
	leg1.addBox(-1F, 0F, -1F, 2, 2, 2);
	leg1.setRotationPoint(-3.5F, 22F, -2F);
	leg1.setTextureSize(64, 32);
	leg1.mirror = true;
	setRotation(leg1, 0F, 0F, 0F);
	leg2 = new ModelRenderer(this, 8, 10);
	leg2.addBox(-1F, 0F, -1F, 2, 2, 2);
	leg2.setRotationPoint(3.5F, 22F, -2F);
	leg2.setTextureSize(64, 32);
	leg2.mirror = true;
	setRotation(leg2, 0F, 0F, 0F);
	HEADROT = new ModelRenderer(this, "HEADROT");
	HEADROT.setRotationPoint(0F, 18F, -3F);
	setRotation(HEADROT, 0F, 0F, 0F);
	HEADROT.mirror = true;
	HEADROT.addBox("head", -3F, -3F, -4F, 6, 6, 4);
	HEADROT.addBox("nose", -1.5F, 1F, -5F, 3, 2, 1);
	HEADROT.addBox("teeth2", 0F, 3F, -5F, 1, 1, 1);
	HEADROT.addBox("teeth1", -1F, 3F, -5F, 1, 1, 1);
	TAILROT = new ModelRenderer(this, "TAILROT");
	TAILROT.setRotationPoint(0F, 20F, 7F);
	setRotation(TAILROT, 0F, 0F, 0F);
	TAILROT.mirror = true;
	TAILROT.addBox("tail1", -1.5F, -0.5F, 0F, 3, 1, 2);
	TAILROT.addBox("tail2", -2F, -0.5F, 2F, 4, 1, 1);
	TAILROT.addBox("tail3", -2.5F, -0.5F, 3F, 5, 1, 1);
	TAILROT.addBox("tail4", -3F, -0.5F, 4F, 6, 1, 4);
	TAILROT.addBox("tail5", -2.5F, -0.5F, 8F, 5, 1, 1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	super.render(entity, f, f1, f2, f3, f4, f5);
	setRotationAngles(f, f1, f2, f3, f4, f5, entity);

	float field_78145_g = 8.0F;
	float field_78151_h = 4.0F;

	if (this.isChild) {
	    float var8 = 2.0F;
	    GL11.glPushMatrix();
	    GL11.glTranslatef(0.0F, field_78145_g * f5, field_78151_h * f5);
	    HEADROT.render(f5);
	    GL11.glPopMatrix();
	    GL11.glPushMatrix();
	    GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
	    GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
	    body.render(f5);
	    leg4.render(f5);
	    leg3.render(f5);
	    leg1.render(f5);
	    leg2.render(f5);
	    TAILROT.render(f5);
	    GL11.glPopMatrix();
	} else {
	    body.render(f5);
	    leg4.render(f5);
	    leg3.render(f5);
	    leg1.render(f5);
	    leg2.render(f5);
	    HEADROT.render(f5);
	    TAILROT.render(f5);
	}
    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
	leg1.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f) * 1.8F * par3;
	leg2.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f) * 1.8F * par3;
	leg3.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f + (float) Math.PI) * 1.8F * par3;
	leg4.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f + (float) Math.PI) * 1.8F * par3;

	TAILROT.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f) * 1.8F * par3;
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
	HEADROT.rotateAngleX = Math.min(Math.max(f4, -30), +30) * (float) (Math.PI / 180f);
	HEADROT.rotateAngleY = Math.min(Math.max(f3, -45), +45) * (float) (Math.PI / 180f);
    }
}
