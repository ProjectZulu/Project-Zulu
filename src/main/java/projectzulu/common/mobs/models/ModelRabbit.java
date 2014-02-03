package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import projectzulu.common.mobs.entity.EntityRabbit;

public class ModelRabbit extends ModelBase {
    ModelRenderer body;
    ModelRenderer queue;
    ModelRenderer neck;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer LEGRIGROT;
    ModelRenderer LEGLEFROT;
    ModelRenderer HEADROT;
    private ModelRenderer EARLEFROT;
    private ModelRenderer EARRIGROT;

    public ModelRabbit() {
	textureWidth = 32;
	textureHeight = 32;
	setTextureOffset("LEGRIGROT.thigh2", 22, 18);
	setTextureOffset("LEGRIGROT.leg4", 20, 26);
	setTextureOffset("LEGLEFROT.thigh1", 8, 18);
	setTextureOffset("LEGLEFROT.leg3", 8, 26);
	setTextureOffset("HEADROT.head", 19, 5);
	setTextureOffset("HEADROT.nose", 23, 11);
	setTextureOffset("EARLEFROT.ear1", 27, 1);
	setTextureOffset("EARRIGROT.ear2", 19, 1);

	body = new ModelRenderer(this, 0, 0);
	body.addBox(-2F, -2F, -4F, 4, 4, 5);
	body.setRotationPoint(0F, 21F, 3F);
	body.setTextureSize(32, 32);
	body.mirror = true;
	setRotation(body, 0F, 0F, 0F);
	queue = new ModelRenderer(this, 0, 18);
	queue.addBox(-1F, -1F, 0F, 2, 2, 1);
	queue.setRotationPoint(0F, 22F, 4F);
	queue.setTextureSize(32, 32);
	queue.mirror = true;
	setRotation(queue, 0F, 0F, 0F);
	neck = new ModelRenderer(this, 0, 11);
	neck.addBox(-2F, -2F, -6F, 4, 2, 2);
	neck.setRotationPoint(0F, 21F, 3F);
	neck.setTextureSize(32, 32);
	neck.mirror = true;
	setRotation(neck, 0F, 0F, 0F);
	leg1 = new ModelRenderer(this, 0, 28);
	leg1.addBox(-2F, 0F, -1F, 1, 3, 1);
	leg1.setRotationPoint(0F, 21F, -2F);
	leg1.setTextureSize(32, 32);
	leg1.mirror = true;
	setRotation(leg1, 0F, 0F, 0F);
	leg2 = new ModelRenderer(this, 4, 28);
	leg2.addBox(1F, 0F, -1F, 1, 3, 1);
	leg2.setRotationPoint(0F, 21F, -2F);
	leg2.setTextureSize(32, 32);
	leg2.mirror = true;
	setRotation(leg2, 0F, 0F, 0F);
	LEGRIGROT = new ModelRenderer(this, "LEGRIGROT");
	LEGRIGROT.setRotationPoint(2.5F, 21F, 2.5F);
	setRotation(LEGRIGROT, 0F, 0F, 0F);
	LEGRIGROT.mirror = true;
	LEGRIGROT.addBox("thigh2", -0.5F, -1F, -2.5F, 1, 3, 4);
	LEGRIGROT.addBox("leg4", -0.5F, 2F, -3.5F, 1, 1, 5);
	LEGLEFROT = new ModelRenderer(this, "LEGLEFROT");
	LEGLEFROT.setRotationPoint(-2.5F, 21F, 2.5F);
	setRotation(LEGLEFROT, 0F, 0F, 0F);
	LEGLEFROT.mirror = true;
	LEGLEFROT.addBox("thigh1", -0.5F, -1F, -2.5F, 1, 3, 4);
	LEGLEFROT.addBox("leg3", -0.5F, 2F, -3.5F, 1, 1, 5);
	HEADROT = new ModelRenderer(this, "HEADROT");
	HEADROT.setRotationPoint(0F, 19F, -2.5F);
	setRotation(HEADROT, 0F, 0F, 0F);
	HEADROT.mirror = true;
	HEADROT.addBox("head", -1.5F, -3F, -1F, 3, 3, 3);
	HEADROT.addBox("nose", -0.5F, -1F, -1.5F, 1, 1, 1);
	EARLEFROT = new ModelRenderer(this, "EARLEFROT");
	EARLEFROT.setRotationPoint(0F, 0F, 0F);
	setRotation(EARLEFROT, -0.5585054F, -0.4363323F, 0F);
	EARLEFROT.mirror = true;
	EARLEFROT.addBox("ear1", -0.5F, -6F, -0.5F, 1, 3, 1);
	HEADROT.addChild(EARLEFROT);
	EARRIGROT = new ModelRenderer(this, "EARRIGROT");
	EARRIGROT.setRotationPoint(0F, 0F, 0F);
	setRotation(EARRIGROT, -0.5585054F, 0.4363323F, 0F);
	EARRIGROT.mirror = true;
	EARRIGROT.addBox("ear2", -0.6F, -6F, -0.5F, 1, 3, 1);
	HEADROT.addChild(EARRIGROT);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	super.render(entity, f, f1, f2, f3, f4, f5);
	setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	if (this.isChild) {
	    float field_78145_x = 0.0f;
	    float field_78145_j = 4.0F;
	    float field_78145_z = 0.0F;

	    float var8 = 2.0F;
	    float head_scale_Render = 0.95f;

	    GL11.glPushMatrix();
	    GL11.glTranslatef(field_78145_x * f5, field_78145_j * f5, field_78145_z * f5);
	    HEADROT.render(f5 * head_scale_Render);
	    GL11.glPopMatrix();

	    GL11.glPushMatrix();
	    GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
	    GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
	    body.render(f5);
	    queue.render(f5);
	    neck.render(f5);
	    leg1.render(f5);
	    leg2.render(f5);
	    LEGRIGROT.render(f5);
	    LEGLEFROT.render(f5);
	    GL11.glPopMatrix();

	} else {
	    body.render(f5);
	    queue.render(f5);
	    neck.render(f5);
	    leg1.render(f5);
	    leg2.render(f5);
	    LEGRIGROT.render(f5);
	    LEGLEFROT.render(f5);
	    HEADROT.render(f5);
	}
    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
	EntityRabbit var5 = (EntityRabbit) par1EntityLiving;

	if (!var5.onGround) {

	} else {
	    /* Walking On The Ground */
	    leg1.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f) * 1.8F * par3;
	    leg2.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f + (float) Math.PI) * 1.8F * par3;

	    LEGLEFROT.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f) * 1.8F * par3;
	    LEGLEFROT.rotateAngleX = MathHelper.cos(par2 * 0.6662F * 2f) * 1.8F * par3;
	}
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
