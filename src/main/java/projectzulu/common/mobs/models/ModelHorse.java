package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import projectzulu.common.core.ModelHelper;

public class ModelHorse extends ModelBase {

    float heightToRaise = 8;

    ModelRenderer body1;
    ModelRenderer tail;
    ModelRenderer body2;
    ModelRenderer LEG1TOPROT;
    ModelRenderer LEG2TOPROT;
    ModelRenderer LEG3TOPROT;
    ModelRenderer LEG4TOPROT;
    ModelRenderer NECKROT;

    private ModelRenderer LEG1BOTROT;
    private ModelRenderer LEG2BOTROT;
    private ModelRenderer LEG3BOTROT;
    private ModelRenderer LEG4BOTROT;
    private ModelRenderer HEADROT;

    private ModelRenderer EARROT;

    public ModelHorse() {
	textureWidth = 64;
	textureHeight = 64;
	setTextureOffset("LEG1TOPROT.leg1top", 0, 48);
	setTextureOffset("LEG1BOTROT.leg1bot", 0, 53);
	setTextureOffset("LEG2TOPROT.leg2top", 14, 48);
	setTextureOffset("LEG2BOTROT.leg2bot", 14, 53);
	setTextureOffset("LEG3TOPROT.leg3top", 28, 48);
	setTextureOffset("LEG3BOTROT.leg3bot", 28, 53);
	setTextureOffset("LEG4TOPROT.leg4top", 42, 48);
	setTextureOffset("LEG4BOTROT.leg4bot", 42, 53);
	setTextureOffset("NECKROT.neck", 48, 0);
	setTextureOffset("NECKROT.maneneck", 46, 21);
	setTextureOffset("EARROT.earrig", 53, 18);
	setTextureOffset("EARROT.earlef", 58, 18);
	setTextureOffset("HEADROT.Head", 33, 0);
	setTextureOffset("HEADROT.manehead", 46, 17);

	body1 = new ModelRenderer(this, 0, 0);
	body1.addBox(-3.5F, -2F, -10F, 7, 10, 15);
	body1.setRotationPoint(0F, 6F - heightToRaise, 3F);
	body1.setTextureSize(64, 64);
	body1.mirror = true;
	setRotation(body1, 0F, 0F, 0F);
	tail = new ModelRenderer(this, 0, 25);
	tail.addBox(-1F, -1F, 0F, 2, 10, 2);
	tail.setRotationPoint(0F, 6F - heightToRaise, 8F);
	tail.setTextureSize(64, 64);
	tail.mirror = true;
	setRotation(tail, 0F, 0F, 0F);
	body2 = new ModelRenderer(this, 0, 25);
	body2.addBox(-4.5F, -1.1F, -9F, 9, 8, 13);
	body2.setRotationPoint(0F, 6F - heightToRaise, 3F);
	body2.setTextureSize(64, 64);
	body2.mirror = true;
	setRotation(body2, 0F, 0F, 0F);
	LEG1TOPROT = new ModelRenderer(this, "LEG1TOPROT");
	LEG1TOPROT.setRotationPoint(-2F, 14F - heightToRaise, -4.5F);
	setRotation(LEG1TOPROT, 0F, 0F, 0F);
	LEG1TOPROT.mirror = true;
	LEG1TOPROT.addBox("leg1top", -1.5F, 0F, -1.5F, 3, 4, 3);
	LEG1BOTROT = new ModelRenderer(this, "LEG1BOTROT");
	LEG1BOTROT.setRotationPoint(0F, 4F, 0F);
	setRotation(LEG1BOTROT, 0F, 0F, 0F);
	LEG1BOTROT.mirror = true;
	LEG1BOTROT.addBox("leg1bot", -1.5F, 0F, -1.5F, 3, 6, 3);
	LEG1TOPROT.addChild(LEG1BOTROT);
	LEG2TOPROT = new ModelRenderer(this, "LEG2TOPROT");
	LEG2TOPROT.setRotationPoint(2F, 14F - heightToRaise, -4.5F);
	setRotation(LEG2TOPROT, 0F, 0F, 0F);
	LEG2TOPROT.mirror = true;
	LEG2TOPROT.addBox("leg2top", -1.5F, 0F, -1.5F, 3, 4, 3);
	LEG2BOTROT = new ModelRenderer(this, "LEG2BOTROT");
	LEG2BOTROT.setRotationPoint(0F, 4F, 0F);
	setRotation(LEG2BOTROT, 0F, 0F, 0F);
	LEG2BOTROT.mirror = true;
	LEG2BOTROT.addBox("leg2bot", -1.5F, 0F, -1.5F, 3, 6, 3);
	LEG2TOPROT.addChild(LEG2BOTROT);
	LEG3TOPROT = new ModelRenderer(this, "LEG3TOPROT");
	LEG3TOPROT.setRotationPoint(-2F, 14F - heightToRaise, 5.5F);
	setRotation(LEG3TOPROT, 0F, 0F, 0F);
	LEG3TOPROT.mirror = true;
	LEG3TOPROT.addBox("leg3top", -1.5F, 0F, -1.5F, 3, 4, 3);
	LEG3BOTROT = new ModelRenderer(this, "LEG3BOTROT");
	LEG3BOTROT.setRotationPoint(0F, 4F, 0F);
	setRotation(LEG3BOTROT, 0F, 0F, 0F);
	LEG3BOTROT.mirror = true;
	LEG3BOTROT.addBox("leg3bot", -1.5F, 0F, -1.5F, 3, 6, 3);
	LEG3TOPROT.addChild(LEG3BOTROT);
	LEG4TOPROT = new ModelRenderer(this, "LEG4TOPROT");
	LEG4TOPROT.setRotationPoint(2F, 14F - heightToRaise, 5.5F);
	setRotation(LEG4TOPROT, 0F, 0F, 0F);
	LEG4TOPROT.mirror = true;
	LEG4TOPROT.addBox("leg4top", -1F, 0F, -1F, 3, 4, 3);
	LEG4BOTROT = new ModelRenderer(this, "LEG4BOTROT");
	LEG4BOTROT.setRotationPoint(0F, 4F, 0F);
	setRotation(LEG4BOTROT, 0F, 0F, 0F);
	LEG4BOTROT.mirror = true;
	LEG4BOTROT.addBox("leg4bot", -1F, 0F, -1F, 3, 6, 3);
	LEG4TOPROT.addChild(LEG4BOTROT);
	NECKROT = new ModelRenderer(this, "NECKROT");
	NECKROT.setRotationPoint(0F, 3F - heightToRaise, -6F);
	setRotation(NECKROT, 0.7853982F, 0F, 0F);
	NECKROT.mirror = true;
	NECKROT.addBox("neck", -1.5F, -5.5F, -2.5F, 3, 9, 5);
	NECKROT.addBox("maneneck", -1F, -5.5F, 2.5F, 2, 7, 1);
	EARROT = new ModelRenderer(this, "EARROT");
	EARROT.setRotationPoint(0F, -5.5F, 2.5F);
	setRotation(EARROT, -0.7853982F, 0F, 0F);
	EARROT.mirror = true;
	EARROT.addBox("earrig", 1F, -1F, -0.5F, 1, 2, 1);
	EARROT.addBox("earlef", -2F, -1F, -0.5F, 1, 2, 1);
	NECKROT.addChild(EARROT);
	HEADROT = new ModelRenderer(this, "HEADROT");
	HEADROT.setRotationPoint(0F, -3F, -0F);
	setRotation(HEADROT, -1.570796F, 0F, 0F);
	HEADROT.mirror = true;
	HEADROT.addBox("Head", -2F, -1F, -2F, 4, 8, 3);
	HEADROT.addBox("manehead", -1F, -1.5F, -3F, 2, 3, 1);
	NECKROT.addChild(HEADROT);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	float scale = 1.5f * f5;
	super.render(entity, f, f1, f2, f3, f4, scale);
	setRotationAngles(f, f1, f2, f3, f4, scale, entity);

	float field_78145_g = 11.0f;
	float field_78151_h = 9.0f;

	if (this.isChild) {
	    float var8 = 2.0F;
	    GL11.glPushMatrix();
	    GL11.glScalef(1.3F / var8, 1.3F / var8, 1.3F / var8);
	    GL11.glTranslatef(0.0F, field_78145_g * scale, field_78151_h * scale);
	    NECKROT.render(scale);
	    GL11.glPopMatrix();
	    GL11.glPushMatrix();
	    GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
	    GL11.glTranslatef(0.0F, 12.0F * scale, +10.0F * scale);
	    body1.render(scale);
	    tail.render(scale);
	    body2.render(scale);
	    LEG1TOPROT.render(scale);
	    LEG2TOPROT.render(scale);
	    LEG3TOPROT.render(scale);
	    LEG4TOPROT.render(scale);
	    GL11.glPopMatrix();
	} else {
	    body1.render(scale);
	    tail.render(scale);
	    body2.render(scale);
	    LEG1TOPROT.render(scale);
	    LEG2TOPROT.render(scale);
	    LEG3TOPROT.render(scale);
	    LEG4TOPROT.render(scale);
	    NECKROT.render(scale);
	}
    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
	/* Tail Rotation */
	NECKROT.rotateAngleX = (float) (45 * Math.PI / 180 + 7 * Math.PI / 180
		* MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper.abs(Math.log(par3 + 1)));
	/* Tail Rotation */
	tail.rotateAngleZ = (float) (0.5f * MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper
		.abs(Math.log(par3 + 1)));
	/* Leg Animation */
	LEG1TOPROT.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper
		.abs(Math.log(par3 + 1)));
	LEG2TOPROT.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F) * 1.8F * ModelHelper.abs(Math.log(par3 + 1)));
	LEG1BOTROT.rotateAngleX = (float) Math.abs(MathHelper.cos(par2 * 0.6662F / 2 + (float) Math.PI) * 1.8F
		* ModelHelper.abs(Math.log(par3 + 1)));
	LEG2BOTROT.rotateAngleX = (float) Math.abs(MathHelper.cos(par2 * 0.6662F / 2) * 1.8F
		* ModelHelper.abs(Math.log(par3 + 1)));

	LEG3TOPROT.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper
		.abs(Math.log(par3 + 1)));
	LEG4TOPROT.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F) * 1.8F * ModelHelper.abs(Math.log(par3 + 1)));
	LEG3BOTROT.rotateAngleX = (float) Math.abs(MathHelper.cos(par2 * 0.6662F / 2 + (float) Math.PI) * 1.8F
		* ModelHelper.abs(Math.log(par3 + 1)));
	LEG4BOTROT.rotateAngleX = (float) Math.abs(MathHelper.cos(par2 * 0.6662F / 2) * 1.8F
		* ModelHelper.abs(Math.log(par3 + 1)));

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
    }
}
