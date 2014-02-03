package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class ModelRhino extends ModelBase {
    float heightToRaise = 9f;
    float renderScale = 1.6f;

    ModelRenderer LEG3ROT;
    ModelRenderer LEG4ROT;
    ModelRenderer LEG2ROT;
    ModelRenderer HEADROT;
    ModelRenderer LEG1ROT;
    ModelRenderer TAILROT;
    ModelRenderer BODYROT;

    public ModelRhino() {
	textureWidth = 128;
	textureHeight = 64;
	setTextureOffset("LEG3ROT.leg3", 48, 13);
	setTextureOffset("LEG3ROT.toe31", 0, 0);
	setTextureOffset("LEG3ROT.toe32", 0, 0);
	setTextureOffset("LEG3ROT.toe33", 0, 0);
	setTextureOffset("LEG4ROT.leg4", 68, 13);
	setTextureOffset("LEG4ROT.toe41", 0, 0);
	setTextureOffset("LEG4ROT.toe42", 0, 0);
	setTextureOffset("LEG4ROT.toe43", 0, 0);
	setTextureOffset("LEG2ROT.leg2", 68, 0);
	setTextureOffset("LEG2ROT.toe21", 0, 0);
	setTextureOffset("LEG2ROT.toe22", 0, 0);
	setTextureOffset("LEG2ROT.toe23", 0, 0);
	setTextureOffset("HEADROT.earrig", 102, 15);
	setTextureOffset("HEADROT.earlef", 96, 15);
	setTextureOffset("HEADROT.horn1", 96, 0);
	setTextureOffset("HEADROT.horn2", 96, 5);
	setTextureOffset("HEADROT.horn3", 96, 10);
	setTextureOffset("HEADROT.head1", 108, 0);
	setTextureOffset("HEADROT.head2", 109, 12);
	setTextureOffset("HEADROT.head3", 109, 23);
	setTextureOffset("HEADROT.head4", 111, 34);
	setTextureOffset("LEG1ROT.toe11", 0, 0);
	setTextureOffset("LEG1ROT.toe12", 0, 0);
	setTextureOffset("LEG1ROT.toe13", 0, 0);
	setTextureOffset("LEG1ROT.leg1", 48, 0);
	setTextureOffset("TAILROT.tail", 0, 25);
	setTextureOffset("BODYROT.leg1dec", 20, 25);
	setTextureOffset("BODYROT.leg2dec", 20, 25);
	setTextureOffset("BODYROT.body", 0, 32);

	LEG3ROT = new ModelRenderer(this, "LEG3ROT");
	LEG3ROT.setRotationPoint(-4.5F, 15F - heightToRaise, 9.5F);
	setRotation(LEG3ROT, 0F, 0F, 0F);
	LEG3ROT.mirror = true;
	LEG3ROT.addBox("leg3", -2.5F, 0F, -2.5F, 5, 9, 5);
	LEG3ROT.addBox("toe31", -2.5F, 8F, -3.5F, 1, 1, 1);
	LEG3ROT.addBox("toe32", -0.5F, 8F, -3.5F, 1, 1, 1);
	LEG3ROT.addBox("toe33", 1.5F, 8F, -3.5F, 1, 1, 1);
	LEG4ROT = new ModelRenderer(this, "LEG4ROT");
	LEG4ROT.setRotationPoint(4.5F, 15F - heightToRaise, 9.5F);
	setRotation(LEG4ROT, 0F, 0F, 0F);
	LEG4ROT.mirror = true;
	LEG4ROT.addBox("leg4", -2.5F, 0F, -2.5F, 5, 9, 5);
	LEG4ROT.addBox("toe41", -2.5F, 8F, -3.5F, 1, 1, 1);
	LEG4ROT.addBox("toe42", -0.5F, 8F, -3.5F, 1, 1, 1);
	LEG4ROT.addBox("toe43", 1.5F, 8F, -3.5F, 1, 1, 1);
	LEG2ROT = new ModelRenderer(this, "LEG2ROT");
	LEG2ROT.setRotationPoint(4F, 15F - heightToRaise, -5F);
	setRotation(LEG2ROT, 0F, 0F, 0F);
	LEG2ROT.mirror = true;
	LEG2ROT.addBox("leg2", -2.5F, 1F, -2.5F, 5, 8, 5);
	LEG2ROT.addBox("toe21", -2.5F, 8F, -3.5F, 1, 1, 1);
	LEG2ROT.addBox("toe22", -0.5F, 8F, -3.5F, 1, 1, 1);
	LEG2ROT.addBox("toe23", 1.5F, 8F, -3.5F, 1, 1, 1);
	HEADROT = new ModelRenderer(this, "HEADROT");
	HEADROT.setRotationPoint(0F, 9F - heightToRaise, -8F);
	setRotation(HEADROT, 0F, 0F, 0F);
	HEADROT.mirror = true;
	HEADROT.addBox("earrig", 3F, -6F, -1.5F, 2, 3, 1);
	HEADROT.addBox("earlef", -5F, -6F, -1.5F, 2, 3, 1);
	HEADROT.addBox("horn1", -1.5F, -1F, -13F, 3, 2, 3);
	HEADROT.addBox("horn2", -1F, -4F, -13F, 2, 3, 2);
	HEADROT.addBox("horn3", -0.5F, -8F, -13F, 1, 4, 1);
	HEADROT.addBox("head1", -4F, -4F, -2F, 8, 10, 2);
	HEADROT.addBox("head2", -3.5F, -2F, -5F, 7, 8, 3);
	HEADROT.addBox("head3", -3F, -1F, -9F, 6, 7, 4);
	HEADROT.addBox("head4", -2.5F, 1F, -13F, 5, 5, 4);
	LEG1ROT = new ModelRenderer(this, "LEG1ROT");
	LEG1ROT.setRotationPoint(-4F, 15F - heightToRaise, -5F);
	setRotation(LEG1ROT, 0F, 0F, 0F);
	LEG1ROT.mirror = true;
	LEG1ROT.addBox("toe11", -2.5F, 8F, -3F, 1, 1, 1);
	LEG1ROT.addBox("toe12", -0.5F, 8F, -3F, 1, 1, 1);
	LEG1ROT.addBox("toe13", 1.5F, 8F, -3F, 1, 1, 1);
	LEG1ROT.addBox("leg1", -2.5F, 1F, -2F, 5, 8, 5);
	TAILROT = new ModelRenderer(this, "TAILROT");
	TAILROT.setRotationPoint(0F, 9F - heightToRaise, 12F);
	setRotation(TAILROT, 0F, 0F, 0F);
	TAILROT.mirror = true;
	TAILROT.addBox("tail", -0.5F, -0.5F, 0F, 1, 1, 6);
	BODYROT = new ModelRenderer(this, "BODYROT");
	BODYROT.setRotationPoint(0F, 10F - heightToRaise, -8F);
	setRotation(BODYROT, 0F, 0F, 0F);
	BODYROT.mirror = true;
	BODYROT.addBox("leg1dec", -7F, 5F, 0F, 6, 1, 6);
	BODYROT.addBox("leg2dec", 1F, 5F, 0F, 6, 1, 6);
	BODYROT.addBox("body", -7F, -7F, 0F, 14, 12, 20);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	super.render(entity, f, f1, f2, f3, f4, f5);
	setRotationAngles(f, f1, f2, f3, f4, f5, entity);

	float field_78145_g = 8.0F;
	float field_78151_h = 6.0F;

	if (this.isChild) {
	    float var8 = 2.0F;
	    GL11.glPushMatrix();
	    GL11.glTranslatef(0.0F, field_78145_g * f5, field_78151_h * f5);
	    HEADROT.render(renderScale * f5);
	    GL11.glPopMatrix();
	    GL11.glPushMatrix();
	    GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
	    GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
	    LEG3ROT.render(renderScale * f5);
	    LEG4ROT.render(renderScale * f5);
	    LEG2ROT.render(renderScale * f5);
	    LEG1ROT.render(renderScale * f5);
	    TAILROT.render(renderScale * f5);
	    BODYROT.render(renderScale * f5);
	    GL11.glPopMatrix();
	} else {
	    LEG3ROT.render(renderScale * f5);
	    LEG4ROT.render(renderScale * f5);
	    LEG2ROT.render(renderScale * f5);
	    HEADROT.render(renderScale * f5);
	    LEG1ROT.render(renderScale * f5);
	    TAILROT.render(renderScale * f5);
	    BODYROT.render(renderScale * f5);
	}
    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
	/* Constant Animation Rotations */
	LEG1ROT.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f) * 1.2F * par3;
	LEG3ROT.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f) * 1.2F * par3;
	LEG2ROT.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f + (float) Math.PI) * 1.2F * par3;
	LEG4ROT.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f + (float) Math.PI) * 1.2F * par3;
	TAILROT.rotateAngleX = (float) (-90 * Math.PI / 180);
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
	HEADROT.rotateAngleX = Math.min(Math.max(f4, -14), +15) * (float) (Math.PI / 180f);
	HEADROT.rotateAngleY = Math.min(Math.max(f3, -15), +15) * (float) (Math.PI / 180f);
    }
}
