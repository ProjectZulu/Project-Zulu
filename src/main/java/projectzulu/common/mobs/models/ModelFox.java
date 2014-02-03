package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import projectzulu.common.core.ModelHelper;
import projectzulu.common.mobs.entity.EntityFox;
import projectzulu.common.mobs.entity.EntityStates;

public class ModelFox extends ModelBase {
    ModelRenderer BODYROT;
    ModelRenderer HEADROT;
    private ModelRenderer TAILROT;
    private ModelRenderer LEG4ROT;
    private ModelRenderer LEG3ROT;
    private ModelRenderer LEG1ROT;
    private ModelRenderer LEG2ROT;
    private ModelRenderer EARROTL;
    private ModelRenderer EARROTR;

    public ModelFox() {
	textureWidth = 64;
	textureHeight = 32;
	setTextureOffset("BODYROT.Body", 0, 9);
	setTextureOffset("TAILROT.Tail", 0, 22);
	setTextureOffset("LEG4ROT.Leg4", 42, 22);
	setTextureOffset("LEG3ROT.Leg3", 34, 22);
	setTextureOffset("LEG1ROT.Leg1", 18, 22);
	setTextureOffset("LEG2ROT.Leg2", 26, 22);
	setTextureOffset("HEADROT.Head", 0, 0);
	setTextureOffset("HEADROT.Nose", 16, 0);
	setTextureOffset("EARROTL.Ear1", 22, 4);
	setTextureOffset("EARROTR.Ear2", 16, 4);

	BODYROT = new ModelRenderer(this, "BODYROT");
	BODYROT.setRotationPoint(0F, 17.5F, -5F);
	setRotation(BODYROT, 0F, 0F, 0F);
	BODYROT.mirror = true;
	BODYROT.addBox("Body", -2.5F, -2.5F, 0F, 5, 5, 8);
	TAILROT = new ModelRenderer(this, "TAILROT");
	TAILROT.setRotationPoint(0F, -1F, 7.5F);
	setRotation(TAILROT, 0F, 0F, 0F);
	TAILROT.mirror = true;
	TAILROT.addBox("Tail", -1F, -1F, 0F, 2, 2, 7);
	BODYROT.addChild(TAILROT);
	LEG4ROT = new ModelRenderer(this, "LEG4ROT");
	LEG4ROT.setRotationPoint(1.5F, 2.5F, 0F);
	setRotation(LEG4ROT, 0F, 0F, 0F);
	LEG4ROT.mirror = true;
	LEG4ROT.addBox("Leg4", -1F, 0F, -1F, 2, 4, 2);
	BODYROT.addChild(LEG4ROT);
	LEG3ROT = new ModelRenderer(this, "LEG3ROT");
	LEG3ROT.setRotationPoint(-1.5F, 2.5F, 0F);
	setRotation(LEG3ROT, 0F, 0F, 0F);
	LEG3ROT.mirror = true;
	LEG3ROT.addBox("Leg3", -1F, 0F, -1F, 2, 4, 2);
	BODYROT.addChild(LEG3ROT);
	LEG1ROT = new ModelRenderer(this, "LEG1ROT");
	LEG1ROT.setRotationPoint(-1.5F, 2.5F, 7F);
	setRotation(LEG1ROT, 0F, 0F, 0F);
	LEG1ROT.mirror = true;
	LEG1ROT.addBox("Leg1", -1F, 0F, -1F, 2, 4, 2);
	BODYROT.addChild(LEG1ROT);
	LEG2ROT = new ModelRenderer(this, "LEG2ROT");
	LEG2ROT.setRotationPoint(1.5F, 2.5F, 7F);
	setRotation(LEG2ROT, 0F, 0F, 0F);
	LEG2ROT.mirror = true;
	LEG2ROT.addBox("Leg2", -1F, 0F, -1F, 2, 4, 2);
	BODYROT.addChild(LEG2ROT);
	HEADROT = new ModelRenderer(this, "HEADROT");
	HEADROT.setRotationPoint(0F, 17.5F, -5F);
	setRotation(HEADROT, 0F, 0F, 0F);
	HEADROT.mirror = true;
	HEADROT.addBox("Head", -2.5F, -2.5F, -3F, 5, 5, 3);
	HEADROT.addBox("Nose", -1F, 0F, -5F, 2, 2, 2);
	EARROTL = new ModelRenderer(this, "EARROTL");
	EARROTL.setRotationPoint(-0.5F, -2.5F, -2F);
	setRotation(EARROTL, 0F, 0F, 0F);
	EARROTL.mirror = true;
	EARROTL.addBox("Ear1", -2F, -3F, -0.5F, 2, 3, 1);
	HEADROT.addChild(EARROTL);
	EARROTR = new ModelRenderer(this, "EARROTR");
	EARROTR.setRotationPoint(0.5F, -2.5F, -2F);
	setRotation(EARROTR, 0F, 0F, 0F);
	EARROTR.mirror = true;
	EARROTR.addBox("Ear2", 0F, -3F, -0.5F, 2, 3, 1);
	HEADROT.addChild(EARROTR);
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
	    HEADROT.render(f5);
	    GL11.glPopMatrix();

	    GL11.glPushMatrix();
	    GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
	    GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
	    BODYROT.render(f5);
	    GL11.glPopMatrix();
	} else {
	    if (((EntityFox) entity).getEntityState() == EntityStates.sitting) {
		HEADROT.render(f5 * 1.1f);
		BODYROT.render(f5);
	    } else {
		HEADROT.render(f5);
		BODYROT.render(f5);
	    }
	}
    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
	super.setLivingAnimations(par1EntityLiving, par2, par3, par4);
	EntityFox var5 = (EntityFox) par1EntityLiving;

	if (var5.getEntityState() == EntityStates.sitting) {
	    TAILROT.rotateAngleX = (float) (10 * Math.PI / 180);

	    LEG1ROT.rotateAngleX = (float) (-55 * Math.PI / 180);
	    LEG1ROT.setRotationPoint(-1.4F, 2.5F, 7F);
	    LEG2ROT.rotateAngleX = (float) (-55 * Math.PI / 180);
	    LEG2ROT.setRotationPoint(1.4F, 2.5F, 7F);

	    LEG3ROT.rotateAngleX = (float) (20 * Math.PI / 180);
	    LEG3ROT.setRotationPoint(-1.5F, 3.0F, 1.0F);
	    LEG4ROT.rotateAngleX = (float) (20 * Math.PI / 180);
	    LEG4ROT.setRotationPoint(1.5F, 3.0F, 1.0F);

	    BODYROT.rotateAngleX = (float) (-35 * Math.PI / 180);
	    BODYROT.setRotationPoint(0F, 17f, -5F);

	} else {
	    TAILROT.rotateAngleX = (float) (-23 * Math.PI / 180);
	    LEG1ROT.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F) * 1.8F * ModelHelper.abs(Math.log(par3 + 1)));
	    LEG1ROT.setRotationPoint(-1.5F, 2.5F, 7F);
	    LEG2ROT.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper
		    .abs(Math.log(par3 + 1)));
	    LEG2ROT.setRotationPoint(1.5F, 2.5F, 7F);

	    LEG3ROT.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F) * 1.8F * ModelHelper.abs(Math.log(par3 + 1)));
	    LEG3ROT.setRotationPoint(-1.5F, 2.5F, 0F);
	    LEG4ROT.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper
		    .abs(Math.log(par3 + 1)));
	    LEG4ROT.setRotationPoint(1.5F, 2.5F, 0F);

	    BODYROT.rotateAngleX = (float) (0 * Math.PI / 180);
	    BODYROT.setRotationPoint(0F, 17.5F, -5F);
	}
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
	model.rotateAngleX = x;
	model.rotateAngleY = y;
	model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6,
	    Entity par7Entity) {
	super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
	this.HEADROT.rotateAngleX = par5 / (180F / (float) Math.PI);
	this.HEADROT.rotateAngleY = par4 / (180F / (float) Math.PI);
    }
}
