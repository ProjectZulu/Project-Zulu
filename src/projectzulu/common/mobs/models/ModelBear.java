package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import projectzulu.common.mobs.entity.EntityBear;
import projectzulu.common.mobs.entity.EntityBlackBear;
import projectzulu.common.mobs.entity.EntityBrownBear;

public class ModelBear extends ModelBase {
    ModelRenderer HEADROT;
    ModelRenderer BODYROT;
    private ModelRenderer LEG1ROT2;
    private ModelRenderer LEG1ROT;
    private ModelRenderer LEG2ROT2;
    private ModelRenderer LEG2ROT;
    private ModelRenderer LEG3ROT2;
    private ModelRenderer LEG3ROT;
    private ModelRenderer LEG4ROT2;
    private ModelRenderer LEG4ROT;
    private ModelRenderer TAILROT;

    public ModelBear(float heightToRaise) {
	textureWidth = 128;
	textureHeight = 64;
	setTextureOffset("HEADROT.head", 58, 0);
	setTextureOffset("HEADROT.cuff", 58, 16);
	setTextureOffset("HEADROT.earl", 69, 12);
	setTextureOffset("HEADROT.earr", 75, 12);
	setTextureOffset("HEADROT.nose", 58, 12);
	setTextureOffset("BODYROT.body", 2, 0);
	setTextureOffset("LEG1ROT2.leg1top", 0, 29);
	setTextureOffset("LEG1ROT.leg1", 16, 29);
	setTextureOffset("LEG2ROT2.leg2top", 0, 29);
	setTextureOffset("LEG2ROT.leg2", 16, 29);
	setTextureOffset("LEG3ROT2.leg3top", 0, 29);
	setTextureOffset("LEG3ROT.leg3", 16, 29);
	setTextureOffset("LEG4ROT2.leg4top", 0, 29);
	setTextureOffset("LEG4ROT.leg4", 16, 29);
	setTextureOffset("TAILROT.tail", 32, 29);

	HEADROT = new ModelRenderer(this, "HEADROT");
	HEADROT.setRotationPoint(1F, 9F - heightToRaise, -6F);
	setRotation(HEADROT, 0F, 0F, 0F);
	HEADROT.mirror = true;
	HEADROT.addBox("head", -3.5F, -3F, -5F, 7, 6, 5);
	HEADROT.addBox("cuff", -2F, 3F, -3F, 4, 1, 3);
	HEADROT.addBox("earl", -4F, -5F, -3F, 2, 2, 1);
	HEADROT.addBox("earr", 2F, -5F, -3F, 2, 2, 1);
	HEADROT.addBox("nose", -1.5F, 0F, -7F, 3, 2, 2);
	BODYROT = new ModelRenderer(this, "BODYROT");
	BODYROT.setRotationPoint(0F, 9F - heightToRaise, -7F);
	setRotation(BODYROT, 0F, 0F, 0F);
	BODYROT.mirror = true;
	BODYROT.addBox("body", -5F, -5F, 1F, 12, 12, 16);
	LEG1ROT2 = new ModelRenderer(this, "LEG1ROT2");
	LEG1ROT2.setRotationPoint(-3F, 7F, 15F);
	setRotation(LEG1ROT2, 0F, 0F, 0F);
	LEG1ROT2.mirror = true;
	LEG1ROT2.addBox("leg1top", -2F, 0F, -2F, 4, 3, 4);
	LEG1ROT = new ModelRenderer(this, "LEG1ROT");
	LEG1ROT.setRotationPoint(0F, 3F, 0F);
	setRotation(LEG1ROT, 0F, 0F, 0F);
	LEG1ROT.mirror = true;
	LEG1ROT.addBox("leg1", -2F, 0F, -2F, 4, 5, 4);
	LEG1ROT2.addChild(LEG1ROT);
	BODYROT.addChild(LEG1ROT2);
	LEG2ROT2 = new ModelRenderer(this, "LEG2ROT2");
	LEG2ROT2.setRotationPoint(5F, 7F, 15F);
	setRotation(LEG2ROT2, 0F, 0F, 0F);
	LEG2ROT2.mirror = true;
	LEG2ROT2.addBox("leg2top", -2F, 0F, -2F, 4, 3, 4);
	LEG2ROT = new ModelRenderer(this, "LEG2ROT");
	LEG2ROT.setRotationPoint(0F, 3F, 0F);
	setRotation(LEG2ROT, 0F, 0F, 0F);
	LEG2ROT.mirror = true;
	LEG2ROT.addBox("leg2", -2F, 0F, -2F, 4, 5, 4);
	LEG2ROT2.addChild(LEG2ROT);
	BODYROT.addChild(LEG2ROT2);
	LEG3ROT2 = new ModelRenderer(this, "LEG3ROT2");
	LEG3ROT2.setRotationPoint(-3F, 7F, 3F);
	setRotation(LEG3ROT2, 0F, 0F, 0F);
	LEG3ROT2.mirror = true;
	LEG3ROT2.addBox("leg3top", -2F, 0F, -2F, 4, 3, 4);
	LEG3ROT = new ModelRenderer(this, "LEG3ROT");
	LEG3ROT.setRotationPoint(0F, 3F, 0F);
	setRotation(LEG3ROT, 0F, 0F, 0F);
	LEG3ROT.mirror = true;
	LEG3ROT.addBox("leg3", -2F, 0F, -2F, 4, 5, 4);
	LEG3ROT2.addChild(LEG3ROT);
	BODYROT.addChild(LEG3ROT2);
	LEG4ROT2 = new ModelRenderer(this, "LEG4ROT2");
	LEG4ROT2.setRotationPoint(5F, 7F, 3F);
	setRotation(LEG4ROT2, 0F, 0F, 0F);
	LEG4ROT2.mirror = true;
	LEG4ROT2.addBox("leg4top", -2F, 0F, -2F, 4, 3, 4);
	LEG4ROT = new ModelRenderer(this, "LEG4ROT");
	LEG4ROT.setRotationPoint(0F, 3F, 0F);
	setRotation(LEG4ROT, 0F, 0F, 0F);
	LEG4ROT.mirror = true;
	LEG4ROT.addBox("leg4", -2F, 0F, -2F, 4, 5, 4);
	LEG4ROT2.addChild(LEG4ROT);
	BODYROT.addChild(LEG4ROT2);
	TAILROT = new ModelRenderer(this, "TAILROT");
	TAILROT.setRotationPoint(1F, 2F, 17F);
	setRotation(TAILROT, 0F, 0F, 0F);
	TAILROT.mirror = true;
	TAILROT.addBox("tail", -1.5F, -1.5F, 0F, 3, 3, 1);
	BODYROT.addChild(TAILROT);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	super.render(entity, f, f1, f2, f3, f4, f5);
	setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	if (this.isChild) {
	    float field_78145_g = 8.0F;
	    float field_78151_h = 4.0F;
	    float field_78145_j = 0.0f;
	    float var8 = 2.0F;
	    float var9 = 1.0f;

	    if (entity instanceof EntityBlackBear) {
		var8 = 2.0F;
		var9 = 1.2f;
		field_78145_g = 4.0f;
		field_78151_h = 5.0f;
		field_78145_j = -0.8f;
	    } else if (entity instanceof EntityBrownBear) {
		var8 = 2.5F;
		field_78145_j = -0.55f;
	    } else {
		var8 = 3.0F;
		var9 = 0.7f;
		field_78145_j = -0.3f;
	    }
	    GL11.glPushMatrix();
	    GL11.glTranslatef(field_78145_j * f5, field_78145_g * f5, field_78151_h * f5);
	    HEADROT.render(f5 * var9);
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
	EntityBear var5 = (EntityBear) par1EntityLiving;
	LEG1ROT2.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f) * 1.2F * par3;
	LEG3ROT2.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f) * 1.2F * par3;
	LEG2ROT2.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f + (float) Math.PI) * 1.2F * par3;
	LEG4ROT2.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f + (float) Math.PI) * 1.2F * par3;
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
	HEADROT.rotateAngleY = Math.min(Math.max(f3, -45), +45) * (float) (Math.PI / 180f);
    }
}
