package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import projectzulu.common.core.ModelHelper;

public class ModelElephant extends ModelBase {

    private static float heightToRaise = 12f;

    ModelRenderer body;
    ModelRenderer tail;
    ModelRenderer LEG4TOPROT;
    ModelRenderer LEG3TOPROT;
    ModelRenderer LEG1TOPROT;
    ModelRenderer LEG2TOPROT;
    ModelRenderer HEADROT;
    private ModelRenderer LEG4BOTROT;
    private ModelRenderer LEG3BOTROT;
    private ModelRenderer LEG1BOTROT;
    private ModelRenderer LEG2BOTROT;
    private ModelRenderer EARLEFROT;
    private ModelRenderer EARRIGROT;
    private ModelRenderer TRUNK1ROT;
    private ModelRenderer TRUNK2ROT;
    private ModelRenderer TRUNK4ROT;
    private ModelRenderer TRUNK3ROT;
    private ModelRenderer TRUNK5ROT;
    private ModelRenderer TRUNK6ROT;
    private ModelRenderer TRUNK7ROT;
    private ModelRenderer TUSKRIGROT;
    private ModelRenderer TUSKLEFROT;

    public ModelElephant() {
	textureWidth = 128;
	textureHeight = 64;
	setTextureOffset("LEG4TOPROT.leg4top", 0, 40);
	setTextureOffset("LEG4BOTROT.leg4bot", 0, 52);
	setTextureOffset("LEG3TOPROT.leg3top", 0, 40);
	setTextureOffset("LEG3BOTROT.leg3bot", 0, 52);
	setTextureOffset("LEG1TOPROT.leg1top", 0, 40);
	setTextureOffset("LEG1BOTROT.leg1bot", 0, 52);
	setTextureOffset("LEG2TOPROT.leg2top", 0, 40);
	setTextureOffset("LEG2BOTROT.leg2bot", 0, 52);
	setTextureOffset("HEADROT.Head", 92, 0);
	setTextureOffset("HEADROT.nosebase1", 77, 20);
	setTextureOffset("HEADROT.nosebase2", 77, 33);
	setTextureOffset("HEADROT.nosebase3", 102, 33);
	setTextureOffset("HEADROT.nosebase4", 113, 43);
	setTextureOffset("EARLEFROT.earlef", 55, 41);
	setTextureOffset("EARRIGROT.earrig", 55, 41);
	setTextureOffset("TRUNK1ROT.trunk1", 97, 53);
	setTextureOffset("TRUNK2ROT.trunk2", 97, 51);
	setTextureOffset("TRUNK3ROT.trunk3", 97, 49);
	setTextureOffset("TRUNK4ROT.trunk4", 97, 47);
	setTextureOffset("TRUNK5ROT.trunk5", 97, 45);
	setTextureOffset("TRUNK6ROT.trunk6", 97, 43);
	setTextureOffset("TRUNK7ROT.trunk7", 97, 41);
	setTextureOffset("TUSKRIGROT.tuskrig", 60, 55);
	setTextureOffset("TUSKLEFROT.tusklef", 60, 55);

	body = new ModelRenderer(this, 0, 0);
	body.addBox(0F, 0F, 0F, 14, 12, 24);
	body.setRotationPoint(-7F, 0F - heightToRaise, -12F);
	body.setTextureSize(128, 64);
	body.mirror = true;
	setRotation(body, 0F, 0F, 0F);
	tail = new ModelRenderer(this, 46, 46);
	tail.addBox(0F, 0F, 0F, 2, 16, 2);
	tail.setRotationPoint(-1F, 4F - heightToRaise, 11F);
	tail.setTextureSize(128, 64);
	tail.mirror = true;
	setRotation(tail, 0F, 0F, 0F);
	LEG4TOPROT = new ModelRenderer(this, "LEG4TOPROT");
	LEG4TOPROT.setRotationPoint(4F, 12F - heightToRaise, 9F);
	setRotation(LEG4TOPROT, 0F, 0F, 0F);
	LEG4TOPROT.mirror = true;
	LEG4TOPROT.addBox("leg4top", -3F, 0F, -3F, 6, 6, 6);
	LEG4BOTROT = new ModelRenderer(this, "LEG4BOTROT");
	LEG4BOTROT.setRotationPoint(0F, 6F, 0F);
	setRotation(LEG4BOTROT, 0F, 0F, 0F);
	LEG4BOTROT.mirror = true;
	LEG4BOTROT.addBox("leg4bot", -3F, 0F, -3F, 6, 6, 6);
	LEG4TOPROT.addChild(LEG4BOTROT);
	LEG3TOPROT = new ModelRenderer(this, "LEG3TOPROT");
	LEG3TOPROT.setRotationPoint(-4F, 12F - heightToRaise, 9F);
	setRotation(LEG3TOPROT, 0F, 0F, 0F);
	LEG3TOPROT.mirror = true;
	LEG3TOPROT.addBox("leg3top", -3F, 0F, -3F, 6, 6, 6);
	LEG3BOTROT = new ModelRenderer(this, "LEG3BOTROT");
	LEG3BOTROT.setRotationPoint(0F, 6F, 0F);
	setRotation(LEG3BOTROT, 0F, 0F, 0F);
	LEG3BOTROT.mirror = true;
	LEG3BOTROT.addBox("leg3bot", -3F, 0F, -3F, 6, 6, 6);
	LEG3TOPROT.addChild(LEG3BOTROT);
	LEG1TOPROT = new ModelRenderer(this, "LEG1TOPROT");
	LEG1TOPROT.setRotationPoint(-4F, 12F - heightToRaise, -9F);
	setRotation(LEG1TOPROT, 0F, 0F, 0F);
	LEG1TOPROT.mirror = true;
	LEG1TOPROT.addBox("leg1top", -3F, 0F, -3F, 6, 6, 6);
	LEG1BOTROT = new ModelRenderer(this, "LEG1BOTROT");
	LEG1BOTROT.setRotationPoint(0F, 6F, 0F);
	setRotation(LEG1BOTROT, 0F, 0F, 0F);
	LEG1BOTROT.mirror = true;
	LEG1BOTROT.addBox("leg1bot", -3F, 0F, -3F, 6, 6, 6);
	LEG1TOPROT.addChild(LEG1BOTROT);
	LEG2TOPROT = new ModelRenderer(this, "LEG2TOPROT");
	LEG2TOPROT.setRotationPoint(4F, 12F - heightToRaise, -9F);
	setRotation(LEG2TOPROT, 0F, 0F, 0F);
	LEG2TOPROT.mirror = true;
	LEG2TOPROT.addBox("leg2top", -3F, 0F, -3F, 6, 6, 6);
	LEG2BOTROT = new ModelRenderer(this, "LEG2BOTROT");
	LEG2BOTROT.setRotationPoint(0F, 6F, 0F);
	setRotation(LEG2BOTROT, 0F, 0F, 0F);
	LEG2BOTROT.mirror = true;
	LEG2BOTROT.addBox("leg2bot", -3F, 0F, -3F, 6, 6, 6);
	LEG2TOPROT.addChild(LEG2BOTROT);
	HEADROT = new ModelRenderer(this, "HEADROT");
	HEADROT.setRotationPoint(0F, 6F - heightToRaise, -12F);
	setRotation(HEADROT, 0F, 0F, 0F);
	HEADROT.mirror = true;
	HEADROT.addBox("Head", -6F, -7F, -6F, 12, 14, 6);
	HEADROT.addBox("nosebase1", -6F, -3F, -8F, 12, 9, 2);
	HEADROT.addBox("nosebase2", -5F, -2F, -9F, 10, 7, 1);
	HEADROT.addBox("nosebase3", -4F, 0F, -10F, 8, 7, 1);
	HEADROT.addBox("nosebase4", -3F, 2F, -11F, 6, 7, 1);
	EARLEFROT = new ModelRenderer(this, "EARLEFROT");
	EARLEFROT.setRotationPoint(-7F, -0.5F, -4F);
	setRotation(EARLEFROT, 0F, 1.22173F, 0F);
	EARLEFROT.mirror = true;
	EARLEFROT.addBox("earlef", -11F, -5.5F, 0F, 11, 11, 2);
	HEADROT.addChild(EARLEFROT);
	EARRIGROT = new ModelRenderer(this, "EARRIGROT");
	EARRIGROT.setRotationPoint(7F, -0.5F, -4F);
	setRotation(EARRIGROT, 0F, -1.22173F, 0F);
	EARRIGROT.mirror = true;
	EARRIGROT.addBox("earrig", 0F, -5.5F, 0F, 11, 11, 2);
	HEADROT.addChild(EARRIGROT);
	TRUNK1ROT = new ModelRenderer(this, "TRUNK1ROT");
	TRUNK1ROT.setRotationPoint(0F, 4F, -10.5F);
	setRotation(TRUNK1ROT, 0F, 0F, 0F);
	TRUNK1ROT.mirror = true;
	TRUNK1ROT.addBox("trunk1", -2F, 0F, -1F, 4, 2, 2);
	TRUNK2ROT = new ModelRenderer(this, "TRUNK2ROT");
	TRUNK2ROT.setRotationPoint(0F, 2F, 0F);
	setRotation(TRUNK2ROT, 0F, 0F, 0F);
	TRUNK2ROT.mirror = true;
	TRUNK2ROT.addBox("trunk2", -2F, 0F, -1F, 4, 2, 2);
	TRUNK3ROT = new ModelRenderer(this, "TRUNK3ROT");
	TRUNK3ROT.setRotationPoint(0F, 2F, 0F);
	setRotation(TRUNK3ROT, 0F, 0F, 0F);
	TRUNK3ROT.mirror = true;
	TRUNK3ROT.addBox("trunk3", -2F, 0F, -1F, 4, 2, 2);
	TRUNK4ROT = new ModelRenderer(this, "TRUNK4ROT");
	TRUNK4ROT.setRotationPoint(0F, 2F, 0F);
	setRotation(TRUNK4ROT, 0F, 0F, 0F);
	TRUNK4ROT.mirror = true;
	TRUNK4ROT.addBox("trunk4", -2F, 0F, -1F, 4, 2, 2);
	TRUNK5ROT = new ModelRenderer(this, "TRUNK5ROT");
	TRUNK5ROT.setRotationPoint(0F, 2F, 0F);
	setRotation(TRUNK5ROT, 0F, 0F, 0F);
	TRUNK5ROT.mirror = true;
	TRUNK5ROT.addBox("trunk5", -2F, 0F, -1F, 4, 2, 2);
	TRUNK6ROT = new ModelRenderer(this, "TRUNK6ROT");
	TRUNK6ROT.setRotationPoint(0F, 2F, 0F);
	setRotation(TRUNK6ROT, 0F, 0F, 0F);
	TRUNK6ROT.mirror = true;
	TRUNK6ROT.addBox("trunk6", -2F, 0F, -1F, 4, 2, 2);
	TRUNK7ROT = new ModelRenderer(this, "TRUNK7ROT");
	TRUNK7ROT.setRotationPoint(0F, 2F, 0F);
	setRotation(TRUNK7ROT, 0F, 0F, 0F);
	TRUNK7ROT.mirror = true;
	TRUNK7ROT.addBox("trunk7", -2F, 0F, -1F, 4, 2, 2);
	TRUNK6ROT.addChild(TRUNK7ROT);
	TRUNK5ROT.addChild(TRUNK6ROT);
	TRUNK4ROT.addChild(TRUNK5ROT);
	TRUNK3ROT.addChild(TRUNK4ROT);
	TRUNK2ROT.addChild(TRUNK3ROT);
	TRUNK1ROT.addChild(TRUNK2ROT);
	HEADROT.addChild(TRUNK1ROT);
	TUSKRIGROT = new ModelRenderer(this, "TUSKRIGROT");
	TUSKRIGROT.setRotationPoint(4.5F, 2F, -8F);
	setRotation(TUSKRIGROT, 0.8028515F, 0F, 0F);
	TUSKRIGROT.mirror = true;
	TUSKRIGROT.addBox("tuskrig", 0F, 0F, -8F, 1, 1, 8);
	HEADROT.addChild(TUSKRIGROT);
	TUSKLEFROT = new ModelRenderer(this, "TUSKLEFROT");
	TUSKLEFROT.setRotationPoint(-5.5F, 2F, -8F);
	setRotation(TUSKLEFROT, 0.8028515F, 0F, 0F);
	TUSKLEFROT.mirror = true;
	TUSKLEFROT.addBox("tusklef", 0F, 0F, -8F, 1, 1, 8);
	HEADROT.addChild(TUSKLEFROT);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	super.render(entity, f, f1, f2, f3, f4, f5);
	setRotationAngles(f, f1, f2, f3, f4, f5, entity);

	if (this.isChild) {
	    float field_78145_g = 1.0f;
	    float field_78151_h = 0.0f;

	    float var8 = 2.0F;
	    GL11.glPushMatrix();
	    GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
	    GL11.glTranslatef(0.0F, field_78145_g * f5, field_78151_h * f5);
	    HEADROT.render(f5 * 1.7f);
	    GL11.glPopMatrix();

	    GL11.glPushMatrix();
	    GL11.glScalef(0.8F / var8, 0.8F / var8, 0.8F / var8);
	    GL11.glTranslatef(0.0F, 14F * f5, 0.0F);
	    body.render(f5 * 2);
	    tail.render(f5 * 2);
	    LEG4TOPROT.render(f5 * 2);
	    LEG3TOPROT.render(f5 * 2);
	    LEG1TOPROT.render(f5 * 2);
	    LEG2TOPROT.render(f5 * 2);
	    GL11.glPopMatrix();
	} else {
	    body.render(f5 * 2);
	    tail.render(f5 * 2);
	    LEG4TOPROT.render(f5 * 2);
	    LEG3TOPROT.render(f5 * 2);
	    LEG1TOPROT.render(f5 * 2);
	    LEG2TOPROT.render(f5 * 2);
	    HEADROT.render(f5 * 2);
	}
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

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
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

	tail.rotateAngleZ = (float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper.abs(Math
		.log(par3 + 1)));

	EARLEFROT.rotateAngleY = (float) (70 * Math.PI / 180 + MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.2F
		* ModelHelper.abs(Math.log(par3 + 1)));
	EARRIGROT.rotateAngleY = (float) (-70 * Math.PI / 180 - MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.2F
		* ModelHelper.abs(Math.log(par3 + 1)));

	TRUNK1ROT.rotateAngleX = (float) (0.0 * Math.PI / 180 * MathHelper.cos((par2) * 0.6662F + (float) Math.PI / 2));
	TRUNK2ROT.rotateAngleX = (float) (0.0 * Math.PI / 180 * MathHelper.cos(par2 * 0.6662F));
	TRUNK3ROT.rotateAngleX = (float) (0.0 * Math.PI / 180 * MathHelper.cos(par2 * 0.6662F));
	TRUNK4ROT.rotateAngleX = (float) (Math.abs(45 * Math.PI / 180
		* MathHelper.cos(par2 * 0.6662F / 4 + (float) Math.PI / 2)));
	TRUNK5ROT.rotateAngleX = (float) (Math.abs(45 * Math.PI / 180
		* MathHelper.cos(par2 * 0.6662F / 4 + (float) Math.PI / 2)));
	TRUNK6ROT.rotateAngleX = (float) (Math.abs(45 * Math.PI / 180
		* MathHelper.cos(par2 * 0.6662F / 4 + (float) Math.PI / 2)));
	TRUNK7ROT.rotateAngleX = (float) (Math.abs(45 * Math.PI / 180
		* MathHelper.cos(par2 * 0.6662F / 4 + (float) Math.PI / 2)));

	super.setLivingAnimations(par1EntityLiving, par2, par3, par4);
    }
}
