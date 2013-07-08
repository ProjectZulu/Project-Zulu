package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import projectzulu.common.core.ModelHelper;

public class ModelGorilla extends ModelBase {
    ModelRenderer LEG4ROT;
    ModelRenderer LEG3ROT;
    ModelRenderer BODY2ROT;
    private ModelRenderer BODY1ROT;
    private ModelRenderer HEADROT;
    private ModelRenderer LEG1TOPROT;
    private ModelRenderer LEG1BOTROT;
    private ModelRenderer LEG2TOPROT;
    private ModelRenderer LEG2BOTROT;
    private ModelRenderer BODY1OTHROT;

    public ModelGorilla() {
	textureWidth = 64;
	textureHeight = 64;
	setTextureOffset("LEG4ROT.leg4", 38, 52);
	setTextureOffset("LEG3ROT.leg3", 38, 39);
	setTextureOffset("BODY2ROT.body2", 0, 22);
	setTextureOffset("BODY1ROT.body1", 0, 0);
	setTextureOffset("HEADROT.head", 38, 17);
	setTextureOffset("HEADROT.ear1", 0, 0);
	setTextureOffset("HEADROT.ear2", 0, 4);
	setTextureOffset("HEADROT.nose", 47, 32);
	setTextureOffset("LEG1TOPROT.leg1top", 0, 41);
	setTextureOffset("LEG1BOTROT.leg1top", 0, 49);
	setTextureOffset("LEG2TOPROT.leg2top", 19, 41);
	setTextureOffset("LEG2BOTROT.leg2bot", 19, 49);

	LEG4ROT = new ModelRenderer(this, "LEG4ROT");
	LEG4ROT.setRotationPoint(3F, 16F, 8F);
	setRotation(LEG4ROT, 0F, 0F, 0F);
	LEG4ROT.mirror = true;
	LEG4ROT.addBox("leg4", -2F, 0F, -2F, 4, 8, 4);
	LEG3ROT = new ModelRenderer(this, "LEG3ROT");
	LEG3ROT.setRotationPoint(-3F, 16F, 8F);
	setRotation(LEG3ROT, 0F, 0F, 0F);
	LEG3ROT.mirror = true;
	LEG3ROT.addBox("leg3", -2F, 0F, -2F, 4, 8, 4);
	BODY2ROT = new ModelRenderer(this, "BODY2ROT");
	BODY2ROT.setRotationPoint(0F, 15F, 8F);
	setRotation(BODY2ROT, 0F, 0F, 0F);
	BODY2ROT.mirror = true;
	BODY2ROT.addBox("body2", -5F, -7F, -7F, 10, 8, 10);
	BODY1ROT = new ModelRenderer(this, "BODY1ROT");
	BODY1ROT.setRotationPoint(0F, 0F, -3F);
	setRotation(BODY1ROT, 1.178097F, 0F, 0F);
	BODY1ROT.mirror = true;
	BODY1ROT.addBox("body1", -6F, -15F, -3F, 12, 12, 9);
	BODY2ROT.addChild(BODY1ROT);
	BODY1OTHROT = new ModelRenderer(this, "BODY1OTHROT");
	BODY1OTHROT.setRotationPoint(0F, 0F, -3F);
	setRotation(BODY1OTHROT, 0F, 0F, 0F);
	BODY1OTHROT.mirror = true;
	HEADROT = new ModelRenderer(this, "HEADROT");
	HEADROT.setRotationPoint(0F, -8F, -12F);
	setRotation(HEADROT, 0F, 0F, 0F);
	HEADROT.mirror = true;
	HEADROT.addBox("head", -3.5F, -7F, -3F, 7, 8, 6);
	HEADROT.addBox("ear1", -4.5F, -6F, -1F, 1, 2, 1);
	HEADROT.addBox("ear2", 3.5F, -6F, -1F, 1, 2, 1);
	HEADROT.addBox("nose", -2F, -3F, -4F, 4, 4, 1);
	BODY1OTHROT.addChild(HEADROT);
	LEG1TOPROT = new ModelRenderer(this, "LEG1TOPROT");
	LEG1TOPROT.setRotationPoint(-5F, -9F, -10F);
	setRotation(LEG1TOPROT, 0F, 0F, 0.0174533F);
	LEG1TOPROT.mirror = true;
	LEG1TOPROT.addBox("leg1top", -2.5F, 0F, -2F, 5, 8, 4);
	LEG1BOTROT = new ModelRenderer(this, "LEG1BOTROT");
	LEG1BOTROT.setRotationPoint(0F, 8F, 0F);
	setRotation(LEG1BOTROT, 0F, 0F, 0F);
	LEG1BOTROT.mirror = true;
	LEG1BOTROT.addBox("leg1top", -2.5F, 0F, -2F, 5, 10, 4);
	LEG1TOPROT.addChild(LEG1BOTROT);
	BODY1OTHROT.addChild(LEG1TOPROT);
	LEG2TOPROT = new ModelRenderer(this, "LEG2TOPROT");
	LEG2TOPROT.setRotationPoint(5F, -9F, -10F);
	setRotation(LEG2TOPROT, 0F, 0F, 0F);
	LEG2TOPROT.mirror = true;
	LEG2TOPROT.addBox("leg2top", -2.5F, 0F, -2F, 5, 8, 4);
	LEG2BOTROT = new ModelRenderer(this, "LEG2BOTROT");
	LEG2BOTROT.setRotationPoint(0F, 8F, 0F);
	setRotation(LEG2BOTROT, 0F, 0F, 0F);
	LEG2BOTROT.mirror = true;
	LEG2BOTROT.addBox("leg2bot", -2.5F, 0F, -2F, 5, 10, 4);
	LEG2TOPROT.addChild(LEG2BOTROT);
	BODY1OTHROT.addChild(LEG2TOPROT);
	BODY2ROT.addChild(BODY1OTHROT);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	super.render(entity, f, f1, f2, f3, f4, f5);
	setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	float field_78145_g = 4.0f;
	float field_78151_h = 3.0f;

	if (this.isChild) {
	    float var8 = 2.0F;
	    GL11.glPushMatrix();
	    GL11.glScalef(1.8F / var8, 1.8F / var8, 1.8F / var8);
	    GL11.glTranslatef(0.0F, field_78145_g * f5, field_78151_h * f5);
	    // HEADROT.render(f5);
	    GL11.glPopMatrix();

	    GL11.glPushMatrix();
	    GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
	    GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
	    LEG4ROT.render(f5);
	    LEG3ROT.render(f5);
	    BODY2ROT.render(f5);
	    GL11.glPopMatrix();
	} else {
	    LEG4ROT.render(f5);
	    LEG3ROT.render(f5);
	    BODY2ROT.render(f5);
	}
    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
	/* Left Side Legs */
	LEG1TOPROT.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper
		.abs(Math.log(par3 + 1)));
	LEG3ROT.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper.abs(Math
		.log(par3 + 1)));
	
	/* Right Side Legs */
	LEG2TOPROT.rotateAngleX = -(float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper
		.abs(Math.log(par3 + 1)));
	LEG4ROT.rotateAngleX = -(float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper.abs(Math
		.log(par3 + 1)));
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
	HEADROT.rotateAngleX = Math.min(Math.max(f4, -90), +90) * (float) (Math.PI / 180f);
	HEADROT.rotateAngleY = Math.min(Math.max(f3, -45), +45) * (float) (Math.PI / 180f);
    }
}
