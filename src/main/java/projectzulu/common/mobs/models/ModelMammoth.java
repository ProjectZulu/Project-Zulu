package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import projectzulu.common.core.ModelHelper;
import projectzulu.common.mobs.entity.EntityGenericCreature;
import projectzulu.common.mobs.entity.EntityStates;

public class ModelMammoth extends ModelBase {
    float heightToRais = 18;
    ModelRenderer body3;
    ModelRenderer body2;
    ModelRenderer body1;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer head;
    ModelRenderer headhair;
    ModelRenderer TUSKRIGROT;
    ModelRenderer NOSEROT1;
    ModelRenderer TUSKLEFROT;

    ModelRenderer NOSEROT2;
    ModelRenderer NOSEROT3;
    ModelRenderer NOSEROT4;
    ModelRenderer NOSEROT5;
    ModelRenderer NOSEROT6;
    ModelRenderer NOSEROT7;
    ModelRenderer NOSEROT8;
    ModelRenderer NOSEROT9;
    ModelRenderer HEADROT;
    ModelRenderer EARRIGROT;
    ModelRenderer EARLEFROT;

    private ModelRenderer SADDLEROT;

    public ModelMammoth() {
	textureWidth = 128;
	textureHeight = 64;
	setTextureOffset("HEADROT.headhair", 58, 48);
	setTextureOffset("HEADROT.head", 34, 48);
	setTextureOffset("HEADROT.eyebrowrig", 64, 53);
	setTextureOffset("HEADROT.eyebrowlef", 64, 53);
	setTextureOffset("TUSKLEFROT.tuskleft", 44, 15);
	setTextureOffset("TUSKLEFROT.tuskleft3", 52, 14);
	setTextureOffset("TUSKLEFROT.tuskleft2", 44, 21);
	setTextureOffset("TUSKRIGROT.tuskrig", 44, 15);
	setTextureOffset("TUSKRIGROT.tuskrig2", 44, 21);
	setTextureOffset("TUSKRIGROT.tuskrig3", 52, 14);
	setTextureOffset("NOSEROT1.nose1", 0, 0);
	setTextureOffset("NOSEROT2.nose2", 0, 3);
	setTextureOffset("NOSEROT3.nose3", 0, 0);
	setTextureOffset("NOSEROT4.nose4", 0, 3);
	setTextureOffset("NOSEROT5.nose5", 0, 0);
	setTextureOffset("NOSEROT6.nose6", 0, 3);
	setTextureOffset("NOSEROT7.nose7", 0, 0);
	setTextureOffset("NOSEROT8.nose8", 0, 3);
	setTextureOffset("NOSEROT9.nose9", 0, 0);
	setTextureOffset("EARRIGROT.earrig", 58, 53);
	setTextureOffset("EARLEFROT.earlef", 58, 53);
	setTextureOffset("SADDLEROT.chairsup10", 74, 7);
	setTextureOffset("SADDLEROT.chairsup1", 74, 7);
	setTextureOffset("SADDLEROT.chairsup2", 74, 7);
	setTextureOffset("SADDLEROT.chairsup3", 74, 7);
	setTextureOffset("SADDLEROT.chairsup4", 74, 7);
	setTextureOffset("SADDLEROT.chairsup5", 74, 7);
	setTextureOffset("SADDLEROT.chairsup6", 74, 7);
	setTextureOffset("SADDLEROT.chairsup7", 74, 7);
	setTextureOffset("SADDLEROT.chairsup8", 74, 7);
	setTextureOffset("SADDLEROT.chairsup9", 74, 7);
	setTextureOffset("SADDLEROT.chairrail5", 74, 17);
	setTextureOffset("SADDLEROT.chairsup5", 74, 7);
	setTextureOffset("SADDLEROT.chairsup4", 74, 7);
	setTextureOffset("SADDLEROT.chairsup7", 74, 7);
	setTextureOffset("SADDLEROT.SaddleBase", 74, 0);
	setTextureOffset("SADDLEROT.chairrail1", 74, 7);
	setTextureOffset("SADDLEROT.chairrail4", 74, 15);
	setTextureOffset("SADDLEROT.chairrail2", 74, 7);
	setTextureOffset("SADDLEROT.Saddlestrap3", 114, 9);
	setTextureOffset("SADDLEROT.Saddlestrap", 74, 19);
	setTextureOffset("SADDLEROT.Saddlestrap4", 114, 9);
	setTextureOffset("SADDLEROT.Saddlestrap2", 74, 19);
	setTextureOffset("SADDLEROT.SaddleBaseFront", 96, 7);
	setTextureOffset("SADDLEROT.SaddleBaseRear3", 96, 9);
	setTextureOffset("SADDLEROT.SaddleBaseRear1", 96, 11);
	setTextureOffset("SADDLEROT.SaddleBaseRear2", 96, 11);
	setTextureOffset("SADDLEROT.chairrail3", 74, 13);

	body3 = new ModelRenderer(this, 0, 48);
	body3.addBox(-4F, 0F, -2F, 13, 10, 4);
	body3.setRotationPoint(-2.5F, 10F - heightToRais, 3F);
	body3.setTextureSize(128, 64);
	body3.mirror = true;
	setRotation(body3, 0F, 0F, 0F);
	body2 = new ModelRenderer(this, 0, 31);
	body2.addBox(-5.5F, 0F, -2F, 15, 11, 5);
	body2.setRotationPoint(-2F, 9F - heightToRais, -2F);
	body2.setTextureSize(128, 64);
	body2.mirror = true;
	setRotation(body2, 0F, 0F, 0F);
	body1 = new ModelRenderer(this, 0, 15);
	body1.addBox(-2F, 0F, -2F, 16, 11, 4);
	body1.setRotationPoint(-6F, 8F - heightToRais, -6F);
	body1.setTextureSize(128, 64);
	body1.mirror = true;
	setRotation(body1, 0F, 0F, 0F);
	leg1 = new ModelRenderer(this, 40, 34);
	leg1.addBox(-2F, 0F, -2F, 4, 5, 4);
	leg1.setRotationPoint(-6F, 19F - heightToRais, -6F);
	leg1.setTextureSize(128, 64);
	leg1.mirror = true;
	setRotation(leg1, 0F, 0F, 0F);
	leg2 = new ModelRenderer(this, 40, 34);
	leg2.addBox(-2F, 0F, -2F, 4, 5, 4);
	leg2.setRotationPoint(6F, 19F - heightToRais, -6F);
	leg2.setTextureSize(128, 64);
	leg2.mirror = true;
	setRotation(leg2, 0F, 0F, 0F);
	leg3 = new ModelRenderer(this, 56, 34);
	leg3.addBox(-2F, 0F, -2F, 4, 4, 4);
	leg3.setRotationPoint(-4.5F, 20F - heightToRais, 3F);
	leg3.setTextureSize(128, 64);
	leg3.mirror = true;
	setRotation(leg3, 0F, 0F, 0.0174533F);
	leg4 = new ModelRenderer(this, 56, 34);
	leg4.addBox(-2F, 0F, -2F, 4, 4, 4);
	leg4.setRotationPoint(4.5F, 20F - heightToRais, 3F);
	leg4.setTextureSize(128, 64);
	leg4.mirror = true;
	setRotation(leg4, 0F, 0F, 0.0174533F);
	HEADROT = new ModelRenderer(this, "HEADROT");
	HEADROT.setRotationPoint(0F, 13F - heightToRais, -8F);
	setRotation(HEADROT, 0F, 0F, 0F);
	HEADROT.mirror = true;
	HEADROT.addBox("headhair", -2.5F, -6F, -3.5F, 5, 2, 3);
	HEADROT.addBox("head", -4F, -4F, -4F, 8, 8, 4);
	HEADROT.addBox("eyebrowrig", -3F, -2F, -5F, 2, 1, 1);
	HEADROT.addBox("eyebrowlef", 1F, -2F, -5F, 2, 1, 1);
	TUSKLEFROT = new ModelRenderer(this, "TUSKLEFROT");
	TUSKLEFROT.setRotationPoint(-3F, 4F, -2F);
	setRotation(TUSKLEFROT, 0F, 0F, 0F);
	TUSKLEFROT.mirror = true;
	TUSKLEFROT.addBox("tuskleft", -1F, 0F, -1F, 2, 4, 2);
	TUSKLEFROT.addBox("tuskleft3", -1F, -3F, -6F, 2, 5, 2);
	TUSKLEFROT.addBox("tuskleft2", -1F, 2F, -6F, 2, 2, 5);
	HEADROT.addChild(TUSKLEFROT);
	TUSKRIGROT = new ModelRenderer(this, "TUSKRIGROT");
	TUSKRIGROT.setRotationPoint(3F, 4F, -2F);
	setRotation(TUSKRIGROT, 0F, 0F, 0F);
	TUSKRIGROT.mirror = true;
	TUSKRIGROT.addBox("tuskrig", -1F, 0F, -1F, 2, 4, 2);
	TUSKRIGROT.addBox("tuskrig2", -1F, 2F, -6F, 2, 2, 5);
	TUSKRIGROT.addBox("tuskrig3", -1F, -3F, -6F, 2, 5, 2);
	HEADROT.addChild(TUSKRIGROT);
	NOSEROT1 = new ModelRenderer(this, "NOSEROT1");
	NOSEROT1.setRotationPoint(0F, 3F, -4F);
	setRotation(NOSEROT1, 0F, 0F, 0F);
	NOSEROT1.mirror = true;
	NOSEROT1.addBox("nose1", -1.5F, -1F, -1F, 3, 2, 1);
	NOSEROT2 = new ModelRenderer(this, "NOSEROT2");
	NOSEROT2.setRotationPoint(0F, 0F, -1F);
	setRotation(NOSEROT2, 0F, 0F, 0F);
	NOSEROT2.mirror = true;
	NOSEROT2.addBox("nose2", -1.5F, -1F, -1F, 3, 2, 1);
	NOSEROT3 = new ModelRenderer(this, "NOSEROT3");
	NOSEROT3.setRotationPoint(0F, 0F, -1F);
	setRotation(NOSEROT3, 0F, 0F, 0F);
	NOSEROT3.mirror = true;
	NOSEROT3.addBox("nose3", -1.5F, -1F, -1F, 3, 2, 1);
	NOSEROT4 = new ModelRenderer(this, "NOSEROT4");
	NOSEROT4.setRotationPoint(0F, 0F, -1F);
	setRotation(NOSEROT4, 0F, 0F, 0F);
	NOSEROT4.mirror = true;
	NOSEROT4.addBox("nose4", -1.5F, -1F, -1F, 3, 2, 1);
	NOSEROT5 = new ModelRenderer(this, "NOSEROT5");
	NOSEROT5.setRotationPoint(0F, 0F, -1F);
	setRotation(NOSEROT5, 0F, 0F, 0F);
	NOSEROT5.mirror = true;
	NOSEROT5.addBox("nose5", -1.5F, -1F, -1F, 3, 2, 1);
	NOSEROT6 = new ModelRenderer(this, "NOSEROT6");
	NOSEROT6.setRotationPoint(0F, 0F, -1F);
	setRotation(NOSEROT6, 0F, 0F, 0F);
	NOSEROT6.mirror = true;
	NOSEROT6.addBox("nose6", -1.5F, -1F, -1F, 3, 2, 1);
	NOSEROT7 = new ModelRenderer(this, "NOSEROT7");
	NOSEROT7.setRotationPoint(0F, 0F, -1F);
	setRotation(NOSEROT7, 0F, 0F, 0F);
	NOSEROT7.mirror = true;
	NOSEROT7.addBox("nose7", -1.5F, -1F, -1F, 3, 2, 1);
	NOSEROT8 = new ModelRenderer(this, "NOSEROT8");
	NOSEROT8.setRotationPoint(0F, 0F, -1F);
	setRotation(NOSEROT8, 0F, 0F, 0F);
	NOSEROT8.mirror = true;
	NOSEROT8.addBox("nose8", -1.5F, -1F, -1F, 3, 2, 1);
	NOSEROT9 = new ModelRenderer(this, "NOSEROT9");
	NOSEROT9.setRotationPoint(0F, 0F, -1F);
	setRotation(NOSEROT9, 0F, 0F, 0F);
	NOSEROT9.mirror = true;
	NOSEROT9.addBox("nose9", -1.5F, -1F, -1F, 3, 2, 1);
	NOSEROT8.addChild(NOSEROT9);
	NOSEROT7.addChild(NOSEROT8);
	NOSEROT6.addChild(NOSEROT7);
	NOSEROT5.addChild(NOSEROT6);
	NOSEROT4.addChild(NOSEROT5);
	NOSEROT3.addChild(NOSEROT4);
	NOSEROT2.addChild(NOSEROT3);
	NOSEROT1.addChild(NOSEROT2);
	HEADROT.addChild(NOSEROT1);
	EARRIGROT = new ModelRenderer(this, "EARRIGROT");
	EARRIGROT.setRotationPoint(4F, -4F, -2F);
	setRotation(EARRIGROT, 0F, 0F, 0F);
	EARRIGROT.mirror = true;
	EARRIGROT.addBox("earrig", 0F, 0F, 0F, 1, 3, 2);
	HEADROT.addChild(EARRIGROT);
	EARLEFROT = new ModelRenderer(this, "EARLEFROT");
	EARLEFROT.setRotationPoint(-5F, -4F, -2F);
	setRotation(EARLEFROT, 0F, 0F, 0F);
	EARLEFROT.mirror = true;
	EARLEFROT.addBox("earlef", 0F, 0F, 0F, 1, 3, 2);
	HEADROT.addChild(EARLEFROT);
	SADDLEROT = new ModelRenderer(this, "SADDLEROT");
	SADDLEROT.setRotationPoint(0F, 8F - heightToRais, 0F);
	setRotation(SADDLEROT, 0F, 0F, 0F);
	SADDLEROT.mirror = true;
	SADDLEROT.addBox("chairsup10", 4F, -4F, -4.5F, 1, 3, 1);
	SADDLEROT.addBox("chairsup1", -5F, -4F, -4.5F, 1, 3, 1);
	SADDLEROT.addBox("chairsup2", -5F, -4F, -2.5F, 1, 3, 1);
	SADDLEROT.addBox("chairsup3", -5F, -4F, -0.5F, 1, 3, 1);
	SADDLEROT.addBox("chairsup4", -5F, -4F, 0.5F, 1, 3, 1);
	SADDLEROT.addBox("chairsup5", -2F, -4F, 0.5F, 1, 3, 1);
	SADDLEROT.addBox("chairsup6", 1F, -4F, 0.5F, 1, 3, 1);
	SADDLEROT.addBox("chairsup7", 4F, -4F, 0.5F, 1, 3, 1);
	SADDLEROT.addBox("chairsup8", 4F, -4F, -0.5F, 1, 3, 1);
	SADDLEROT.addBox("chairsup9", 4F, -4F, -2.5F, 1, 3, 1);
	SADDLEROT.addBox("chairrail5", -3F, -7F, 0.5F, 6, 1, 1);
	SADDLEROT.addBox("chairsup5", -0.5F, -4F, 0.5F, 1, 3, 1);
	SADDLEROT.addBox("chairsup4", -3.5F, -4F, 0.5F, 1, 3, 1);
	SADDLEROT.addBox("chairsup7", 2.5F, -4F, 0.5F, 1, 3, 1);
	SADDLEROT.addBox("SaddleBase", -5F, -1F, -4F, 10, 1, 6);
	SADDLEROT.addBox("chairrail1", -5F, -5F, -4.5F, 1, 1, 5);
	SADDLEROT.addBox("chairrail4", -4F, -6F, 0.5F, 8, 1, 1);
	SADDLEROT.addBox("chairrail2", 4F, -5F, -4.5F, 1, 1, 5);
	SADDLEROT.addBox("Saddlestrap3", -8.5F, 1F, -3.5F, 1, 11, 3);
	SADDLEROT.addBox("Saddlestrap", -8.5F, 0F, -3.5F, 17, 1, 3);
	SADDLEROT.addBox("Saddlestrap4", 7.5F, 1F, -3.5F, 1, 11, 3);
	SADDLEROT.addBox("Saddlestrap2", -8.5F, 12F, -3.5F, 17, 1, 3);
	SADDLEROT.addBox("SaddleBaseFront", -6F, -1F, -5F, 12, 1, 1);
	SADDLEROT.addBox("SaddleBaseRear3", -4F, 1F, 1F, 8, 1, 1);
	SADDLEROT.addBox("SaddleBaseRear1", -5F, 0F, 1F, 1, 2, 1);
	SADDLEROT.addBox("SaddleBaseRear2", 4F, 0F, 1F, 1, 2, 1);
	SADDLEROT.addBox("chairrail3", -5F, -5F, 0.5F, 10, 1, 1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	super.render(entity, f, f1, f2, f3, f4, f5);
	setRotationAngles(f, f1, f2, f3, f4, f5, entity);

	float field_78145_g = 11.0f;
	float field_78151_h = 9.0f;
	float scale = 4;

	if (this.isChild) {
	    float var8 = 2.0F;
	    GL11.glPushMatrix();
	    GL11.glScalef(1.3F / var8, 1.3F / var8, 1.3F / var8);
	    GL11.glTranslatef(0.0F, field_78145_g * f5, field_78151_h * f5);
	    HEADROT.render(scale * f5);
	    GL11.glPopMatrix();
	    GL11.glPushMatrix();
	    GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
	    GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
	    body3.render(scale * f5);
	    body2.render(scale * f5);
	    body1.render(scale * f5);
	    leg1.render(scale * f5);
	    leg2.render(scale * f5);
	    leg3.render(scale * f5);
	    leg4.render(scale * f5);
	    SADDLEROT.render(scale * f5);
	    GL11.glPopMatrix();
	} else {
	    body3.render(scale * f5);
	    body2.render(scale * f5);
	    body1.render(scale * f5);
	    leg1.render(scale * f5);
	    leg2.render(scale * f5);
	    leg3.render(scale * f5);
	    leg4.render(scale * f5);

	    HEADROT.render(scale * f5);
	    SADDLEROT.render(scale * f5);
	}
    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
	TUSKRIGROT.rotateAngleY = (float) (-22.5 * Math.PI / 180);
	TUSKLEFROT.rotateAngleY = (float) (22.5 * Math.PI / 180);

	float angle = 0;
	NOSEROT1.rotateAngleX = (float) (22.5 * Math.PI / 180 * MathHelper.cos(angle));
	NOSEROT2.rotateAngleX = (float) (22.5 * Math.PI / 180 * MathHelper.cos(angle));
	NOSEROT3.rotateAngleX = (float) (22.5 * Math.PI / 180 * MathHelper.cos(angle));
	NOSEROT4.rotateAngleX = (float) (22.5 * Math.PI / 180 * MathHelper.cos(angle));
	NOSEROT5.rotateAngleX = (float) (0 * Math.PI / 180);
	NOSEROT6.rotateAngleX = (float) (0 * Math.PI / 180);
	NOSEROT7.rotateAngleX = (float) (0 * Math.PI / 180);
	NOSEROT8.rotateAngleX = (float) (0 * Math.PI / 180);
	NOSEROT9.rotateAngleX = (float) (0 * Math.PI / 180);

	/* Left Side Legs */
	leg1.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper.abs(Math
		.log(par3 + 1)));
	leg3.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper.abs(Math
		.log(par3 + 1)));

	/* Right Side Legs */
	leg2.rotateAngleX = -(float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper.abs(Math
		.log(par3 + 1)));
	leg4.rotateAngleX = -(float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper.abs(Math
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
	EntityGenericCreature var5 = (EntityGenericCreature) par7Entity;
	if (var5.getEntityState() == EntityStates.attacking && var5.getAnimTime() > 0) {
	    HEADROT.rotateAngleX = Math.min(Math.max(f4, -15), +15) * (float) (Math.PI / 180f)
		    + (float) (MathHelper.cos(f1 * 0.6662F / 2f + (float) Math.PI) * Math.PI / 180 * (20));
	} else {
	    HEADROT.rotateAngleX = Math.min(Math.max(f4, -15), +15) * (float) (Math.PI / 180f);
	}
	HEADROT.rotateAngleY = Math.min(Math.max(f3, -45), +45) * (float) (Math.PI / 180f);
    }
}
