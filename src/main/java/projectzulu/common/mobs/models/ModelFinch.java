package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import projectzulu.common.mobs.entity.EntityFinch;
import projectzulu.common.mobs.entity.EntityStates;

public class ModelFinch extends ModelBase {

    ModelRenderer Body;
    ModelRenderer wingrig;
    ModelRenderer winglef;
    ModelRenderer tail;
    ModelRenderer LEGRIGTOPROT;
    ModelRenderer LEGLEFTOPROT;
    ModelRenderer HEADROT;
    private ModelRenderer LEGRIGBOTROT;
    private ModelRenderer LEGLEFBOTROT;
    private ModelRenderer BEAKROT;

    public ModelFinch() {
	textureWidth = 32;
	textureHeight = 32;
	setTextureOffset("LEGRIGTOPROT.lefrigtop", 0, 12);
	setTextureOffset("LEGRIGBOTROT.legbotrig", 0, 15);
	setTextureOffset("LEGLEFTOPROT.legtoplef", 3, 12);
	setTextureOffset("LEGLEFBOTROT.legbotlef", 7, 15);
	setTextureOffset("HEADROT.Head", 0, 7);
	setTextureOffset("BEAKROT.beak", 9, 9);

	Body = new ModelRenderer(this, 0, 0);
	Body.addBox(-1F, -1F, -2F, 2, 2, 4);
	Body.setRotationPoint(0F, 21.3F, 0F);
	Body.setTextureSize(32, 32);
	Body.mirror = true;
	setRotation(Body, 0F, 0F, 0F);
	wingrig = new ModelRenderer(this, 13, 0);
	wingrig.addBox(0F, 0F, -1.5F, 1, 1, 3);
	wingrig.setRotationPoint(1F, 20.3F, 0F);
	wingrig.setTextureSize(32, 32);
	wingrig.mirror = true;
	setRotation(wingrig, 0F, 0F, 0F);
	winglef = new ModelRenderer(this, 22, 0);
	winglef.addBox(-1F, 0F, -1.5F, 1, 1, 3);
	winglef.setRotationPoint(-1F, 20.3F, 0F);
	winglef.setTextureSize(32, 32);
	winglef.mirror = true;
	setRotation(winglef, 0F, 0F, 0F);
	tail = new ModelRenderer(this, 14, 8);
	tail.addBox(-0.4666667F, 0F, 0F, 1, 1, 3);
	tail.setRotationPoint(0F, 20.8F, 1F);
	tail.setTextureSize(32, 32);
	tail.mirror = true;
	setRotation(tail, 0.3346075F, 0F, 0F);
	LEGRIGTOPROT = new ModelRenderer(this, "LEGRIGTOPROT");
	LEGRIGTOPROT.setRotationPoint(0.5F, 22.3F, 1F);
	setRotation(LEGRIGTOPROT, -0.6684611F, 0F, 0F);
	LEGRIGTOPROT.mirror = true;
	LEGRIGTOPROT.addBox("lefrigtop", -0.5F, 0F, 0F, 1, 2, 1);
	LEGRIGBOTROT = new ModelRenderer(this, "LEGRIGBOTROT");
	LEGRIGBOTROT.setRotationPoint(0F, 1F, 0F);
	setRotation(LEGRIGBOTROT, 0.6702064F, 0F, 0F);
	LEGRIGBOTROT.mirror = true;
	LEGRIGBOTROT.addBox("legbotrig", -0.5F, -0.4F, -2.3F, 1, 1, 2);
	LEGRIGTOPROT.addChild(LEGRIGBOTROT);
	LEGLEFTOPROT = new ModelRenderer(this, "LEGLEFTOPROT");
	LEGLEFTOPROT.setRotationPoint(-0.5F, 22.3F, 1F);
	setRotation(LEGLEFTOPROT, -0.6702064F, 0F, 0F);
	LEGLEFTOPROT.mirror = true;
	LEGLEFTOPROT.addBox("legtoplef", -0.5F, 0F, 0F, 1, 2, 1);
	LEGLEFBOTROT = new ModelRenderer(this, "LEGLEFBOTROT");
	LEGLEFBOTROT.setRotationPoint(0F, 1F, 0F);
	setRotation(LEGLEFBOTROT, 0.6702064F, 0F, 0F);
	LEGLEFBOTROT.mirror = true;
	LEGLEFBOTROT.addBox("legbotlef", -0.5F, -0.4F, -2.3F, 1, 1, 2);
	LEGLEFTOPROT.addChild(LEGLEFBOTROT);
	HEADROT = new ModelRenderer(this, "HEADROT");
	HEADROT.setRotationPoint(0F, 19.8F, -2F);
	setRotation(HEADROT, 0F, 0F, 0F);
	HEADROT.mirror = true;
	HEADROT.addBox("Head", -1F, -1F, -1F, 2, 2, 2);
	BEAKROT = new ModelRenderer(this, "BEAKROT");
	BEAKROT.setRotationPoint(0F, 0F, 0F);
	setRotation(BEAKROT, -0.7801622F, 0F, 0F);
	BEAKROT.mirror = true;
	BEAKROT.addBox("beak", -0.5333334F, 0.3F, -0.9F, 1, 1, 1);
	HEADROT.addChild(BEAKROT);
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
	    Body.render(f5);
	    Body.render(f5);
	    wingrig.render(f5);
	    winglef.render(f5);
	    tail.render(f5);
	    LEGRIGTOPROT.render(f5);
	    LEGLEFTOPROT.render(f5);
	    GL11.glPopMatrix();
	} else {
	    Body.render(f5);
	    wingrig.render(f5);
	    winglef.render(f5);
	    tail.render(f5);
	    LEGRIGTOPROT.render(f5);
	    LEGLEFTOPROT.render(f5);
	    HEADROT.render(f5);
	}
    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
	EntityFinch var5 = (EntityFinch) par1EntityLiving;
	if (var5.getEntityState() == EntityStates.posture) {
	    /* On Ground Idle Standing Animation */
	} else {
	    winglef.rotateAngleZ = MathHelper.cos(var5.worldObj.getWorldTime() * 0.6662F * 2f) * 1.8F * 0.5f;
	    wingrig.rotateAngleZ = MathHelper.cos(var5.worldObj.getWorldTime() * 0.6662F * 2f
		    + (float) Math.PI) * 1.8F * 0.5f;
	    tail.rotateAngleX = MathHelper.cos(var5.worldObj.getWorldTime() * 0.6662F * 2f + (float) Math.PI) * 0.3f;
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
	HEADROT.rotateAngleY = Math.min(Math.max(f3, -45), +45) * (float) (Math.PI / 180f);
    }
}
