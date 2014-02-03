package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import projectzulu.common.mobs.entity.EntitySandWorm;
import projectzulu.common.mobs.entity.EntityStates;

public class ModelSandWorm extends ModelBase {
    ModelRenderer HEADBASE;
    ModelRenderer head;

    ModelRenderer REARROT;
    ModelRenderer LEFMOROT;
    ModelRenderer lefmo8;
    ModelRenderer lefmo6;
    ModelRenderer lefmo4;
    ModelRenderer lefmo2;
    ModelRenderer lefmot1;
    ModelRenderer lefmot2;

    ModelRenderer RIGMOROT;
    ModelRenderer rigmo8;
    ModelRenderer rigmo6;
    ModelRenderer rigmo4;
    ModelRenderer rigmo2;
    ModelRenderer rigmot1;
    ModelRenderer rigmot2;

    ModelRenderer TOPMOROT;
    ModelRenderer topmo8;
    ModelRenderer topmo6;
    ModelRenderer topmo4;
    ModelRenderer topmo2;
    ModelRenderer topmot1;
    ModelRenderer topmot2;

    ModelRenderer BOTMOROT;
    ModelRenderer botmo8;
    ModelRenderer botmo6;
    ModelRenderer botmo4;
    ModelRenderer botmo2;
    ModelRenderer botmot1;
    ModelRenderer botmot2;

    ModelRenderer body1;

    ModelRenderer REARROT2;
    ModelRenderer body2;
    ModelRenderer REARROT3;
    ModelRenderer body3;
    ModelRenderer REARROT4;
    ModelRenderer body4;
    ModelRenderer REARROT5;
    ModelRenderer body5;
    ModelRenderer REARROT6;
    ModelRenderer body6;
    ModelRenderer REARROT7;
    ModelRenderer body7;

    public ModelSandWorm() {
	textureWidth = 64;
	textureHeight = 32;
	float heightToRaise = 12.0f;
	// setTextureOffset("HEADBASE.dltfold5", 0, 0);
	// setTextureOffset("REARROT.dltfold6", 0, 0);

	HEADBASE = new ModelRenderer(this, "HEADBASE");
	HEADBASE.setRotationPoint(0F, 20F - heightToRaise, -6F);
	setRotation(HEADBASE, 0F, 0F, 0F);
	HEADBASE.mirror = true;
	head = new ModelRenderer(this, 18, 0);
	head.addBox(-4F, -4F, -4F, 8, 8, 4);
	HEADBASE.addChild(head);
	head.setRotationPoint(0F, 0F, 0F);
	head.setTextureSize(64, 32);
	head.mirror = true;
	setRotation(head, 0F, 0F, 0F);
	LEFMOROT = new ModelRenderer(this, "LEFMOROT");
	HEADBASE.addChild(LEFMOROT);
	LEFMOROT.setRotationPoint(-3F, 0F, -4F);
	setRotation(LEFMOROT, 0F, 0F, 0F);
	LEFMOROT.mirror = true;
	lefmo8 = new ModelRenderer(this, 0, 10);
	lefmo8.addBox(0F, 0F, 0F, 1, 8, 1);
	LEFMOROT.addChild(lefmo8);
	lefmo8.setRotationPoint(-1F, -4F, -1F);
	lefmo8.setTextureSize(64, 32);
	lefmo8.mirror = true;
	setRotation(lefmo8, 0F, 0F, 0F);
	lefmo6 = new ModelRenderer(this, 4, 10);
	lefmo6.addBox(0F, 0F, 0F, 1, 6, 1);
	LEFMOROT.addChild(lefmo6);
	lefmo6.setRotationPoint(0F, -3F, -1F);
	lefmo6.setTextureSize(64, 32);
	lefmo6.mirror = true;
	setRotation(lefmo6, 0F, 0F, 0F);
	lefmo4 = new ModelRenderer(this, 8, 10);
	lefmo4.addBox(0F, 0F, 0F, 1, 4, 1);
	LEFMOROT.addChild(lefmo4);
	lefmo4.setRotationPoint(1F, -2F, -1F);
	lefmo4.setTextureSize(64, 32);
	lefmo4.mirror = true;
	setRotation(lefmo4, 0F, 0F, 0F);
	lefmo2 = new ModelRenderer(this, 12, 10);
	lefmo2.addBox(0F, 0F, 0F, 1, 2, 1);
	LEFMOROT.addChild(lefmo2);
	lefmo2.setRotationPoint(2F, -1F, 0F);
	lefmo2.setTextureSize(64, 32);
	lefmo2.mirror = true;
	setRotation(lefmo2, 0F, 0F, 0F);
	lefmot2 = new ModelRenderer(this, 12, 13);
	lefmot2.addBox(0F, 0F, 0F, 1, 1, 1);
	LEFMOROT.addChild(lefmot2);
	lefmot2.setRotationPoint(1F, -2F, 0F);
	lefmot2.setTextureSize(64, 32);
	lefmot2.mirror = true;
	setRotation(lefmot2, 0F, 0F, 0F);
	lefmot1 = new ModelRenderer(this, 12, 13);
	lefmot1.addBox(0F, 0F, 0F, 1, 1, 1);
	LEFMOROT.addChild(lefmot1);
	lefmot1.setRotationPoint(1F, 1F, 0F);
	lefmot1.setTextureSize(64, 32);
	lefmot1.mirror = true;
	setRotation(lefmot1, 0F, 0F, 0F);
	// dltfold5.addChildModelRenderer(LEFMOROT);
	RIGMOROT = new ModelRenderer(this, "RIGMOROT");
	HEADBASE.addChild(RIGMOROT);
	RIGMOROT.setRotationPoint(3F, 0F, -4F);
	setRotation(RIGMOROT, 0F, 0F, 0F);
	RIGMOROT.mirror = true;
	rigmo8 = new ModelRenderer(this, 0, 10);
	rigmo8.addBox(0F, 0F, 0F, 1, 8, 1);
	RIGMOROT.addChild(rigmo8);
	rigmo8.setRotationPoint(0F, -4F, -1F);
	rigmo8.setTextureSize(64, 32);
	rigmo8.mirror = true;
	setRotation(rigmo8, 0F, 0F, 0F);
	rigmo6 = new ModelRenderer(this, 4, 10);
	rigmo6.addBox(0F, 0F, 0F, 1, 6, 1);
	rigmo6.setRotationPoint(-1F, -3F, -1F);
	RIGMOROT.addChild(rigmo6);
	rigmo6.setTextureSize(64, 32);
	rigmo6.mirror = true;
	setRotation(rigmo6, 0F, 0F, 0F);
	rigmo4 = new ModelRenderer(this, 8, 10);
	rigmo4.addBox(0F, 0F, 0F, 1, 4, 1);
	rigmo4.setRotationPoint(-2F, -2F, -1F);
	RIGMOROT.addChild(rigmo4);
	rigmo4.setTextureSize(64, 32);
	rigmo4.mirror = true;
	setRotation(rigmo4, 0F, 0F, 0F);
	rigmo2 = new ModelRenderer(this, 12, 10);
	rigmo2.addBox(0F, 0F, 0F, 1, 2, 1);
	RIGMOROT.addChild(rigmo2);
	rigmo2.setRotationPoint(-3F, -1F, -1F);
	rigmo2.setTextureSize(64, 32);
	rigmo2.mirror = true;
	setRotation(rigmo2, 0F, 0F, 0F);
	rigmot1 = new ModelRenderer(this, 12, 13);
	rigmot1.addBox(0F, 0F, 0F, 1, 1, 1);
	RIGMOROT.addChild(rigmot1);
	rigmot1.setRotationPoint(-2F, -2F, 0F);
	rigmot1.setTextureSize(64, 32);
	rigmot1.mirror = true;
	setRotation(rigmot1, 0F, 0F, 0F);
	rigmot2 = new ModelRenderer(this, 12, 13);
	rigmot2.addBox(0F, 0F, 0F, 1, 1, 1);
	RIGMOROT.addChild(rigmot2);
	rigmot2.setRotationPoint(-2F, 1F, 0F);
	rigmot2.setTextureSize(64, 32);
	rigmot2.mirror = true;
	setRotation(rigmot2, 0F, 0F, 0F);
	// dltfold5.addChildModelRenderer(RIGMOROT);
	TOPMOROT = new ModelRenderer(this, "TOPMOROT");
	HEADBASE.addChild(TOPMOROT);
	TOPMOROT.setRotationPoint(0F, -3F, -4F);
	setRotation(TOPMOROT, 0F, 0F, 0F);
	TOPMOROT.mirror = true;
	topmo8 = new ModelRenderer(this, 0, 19);
	topmo8.addBox(0F, -1F, 0F, 8, 1, 1);
	TOPMOROT.addChild(topmo8);
	topmo8.setRotationPoint(-4F, 0F, -1F);
	topmo8.setTextureSize(64, 32);
	topmo8.mirror = true;
	setRotation(topmo8, 0F, 0F, 0F);
	topmo6 = new ModelRenderer(this, 0, 21);
	topmo6.addBox(0F, 0F, 0F, 6, 1, 1);
	TOPMOROT.addChild(topmo6);
	topmo6.setRotationPoint(-3F, 0F, -1F);
	topmo6.setTextureSize(64, 32);
	topmo6.mirror = true;
	setRotation(topmo6, 0F, 0F, 0F);
	topmo4 = new ModelRenderer(this, 0, 23);
	topmo4.addBox(0F, 0F, 0F, 4, 1, 1);
	TOPMOROT.addChild(topmo4);
	topmo4.setRotationPoint(-2F, 1F, -1F);
	topmo4.setTextureSize(64, 32);
	topmo4.mirror = true;
	setRotation(topmo4, 0F, 0F, 0F);
	topmo2 = new ModelRenderer(this, 0, 25);
	topmo2.addBox(0F, 0F, 0F, 2, 1, 1);
	TOPMOROT.addChild(topmo2);
	topmo2.setRotationPoint(-1F, 2F, -1F);
	topmo2.setTextureSize(64, 32);
	topmo2.mirror = true;
	setRotation(topmo2, 0F, 0F, 0F);
	topmot1 = new ModelRenderer(this, 7, 25);
	topmot1.addBox(0F, 0F, 0F, 1, 1, 1);
	TOPMOROT.addChild(topmot1);
	topmot1.setRotationPoint(-2F, 1F, 0F);
	topmot1.setTextureSize(64, 32);
	topmot1.mirror = true;
	setRotation(topmot1, 0F, 0F, 0F);
	topmot2 = new ModelRenderer(this, 7, 25);
	topmot2.addBox(0F, 0F, 0F, 1, 1, 1);
	TOPMOROT.addChild(topmot2);
	topmot2.setRotationPoint(1F, 1F, 0F);
	topmot2.setTextureSize(64, 32);
	topmot2.mirror = true;
	setRotation(topmot2, 0F, 0F, 0F);
	// dltfold5.addChildModelRenderer(TOPMOROT);
	BOTMOROT = new ModelRenderer(this, "BOTMOROT");
	HEADBASE.addChild(BOTMOROT);
	BOTMOROT.setRotationPoint(0F, 3F, -4F);
	setRotation(BOTMOROT, 0F, 0F, 0F);
	BOTMOROT.mirror = true;
	botmo4 = new ModelRenderer(this, 0, 23);
	botmo4.addBox(0F, 0F, 0F, 4, 1, 1);
	BOTMOROT.addChild(botmo4);
	botmo4.setRotationPoint(-2F, -2F, -1F);
	botmo4.setTextureSize(64, 32);
	botmo4.mirror = true;
	setRotation(botmo4, 0F, 0F, 0F);
	botmo6 = new ModelRenderer(this, 0, 21);
	botmo6.addBox(0F, 0F, 0F, 6, 1, 1);
	BOTMOROT.addChild(botmo6);
	botmo6.setRotationPoint(-3F, -1F, -1F);
	botmo6.setTextureSize(64, 32);
	botmo6.mirror = true;
	setRotation(botmo6, 0F, 0F, 0F);
	botmo2 = new ModelRenderer(this, 0, 25);
	botmo2.addBox(0F, 0F, 0F, 2, 1, 1);
	BOTMOROT.addChild(botmo2);
	botmo2.setRotationPoint(-1F, -3F, -1F);
	botmo2.setTextureSize(64, 32);
	botmo2.mirror = true;
	setRotation(botmo2, 0F, 0F, 0F);
	botmo8 = new ModelRenderer(this, 0, 19);
	botmo8.addBox(0F, 0F, 0F, 8, 1, 1);
	BOTMOROT.addChild(botmo8);
	botmo8.setRotationPoint(-4F, 0F, -1F);
	botmo8.setTextureSize(64, 32);
	botmo8.mirror = true;
	setRotation(botmo8, 0F, 0F, 0F);
	botmot1 = new ModelRenderer(this, 7, 25);
	botmot1.addBox(0F, 0F, 0F, 1, 1, 1);
	BOTMOROT.addChild(botmot1);
	botmot1.setRotationPoint(-2F, -2F, 0F);
	botmot1.setTextureSize(64, 32);
	botmot1.mirror = true;
	setRotation(botmot1, 0F, 0F, 0F);
	botmot2 = new ModelRenderer(this, 7, 25);
	botmot2.addBox(0F, 0F, 0F, 1, 1, 1);
	BOTMOROT.addChild(botmot2);
	botmot2.setRotationPoint(1F, -2F, 0F);
	botmot2.setTextureSize(64, 32);
	botmot2.mirror = true;
	setRotation(botmot2, 0F, 0F, 0F);
	// dltfold5.addChildModelRenderer(BOTMOROT);

	REARROT = new ModelRenderer(this, "REARROT");
	REARROT.setRotationPoint(0F, 20F - heightToRaise, -6F);
	setRotation(REARROT, 0F, 0F, 0F);
	REARROT.mirror = true;
	body1 = new ModelRenderer(this, 14, 20);
	body1.addBox(-4F, -4F, -2F, 8, 8, 4);
	REARROT.addChild(body1);
	body1.setRotationPoint(0F, 0F, 2F);
	body1.setTextureSize(64, 32);
	body1.mirror = true;
	setRotation(body1, 0F, 0F, 0F);

	REARROT2 = new ModelRenderer(this, "REARROT2");
	REARROT.addChild(REARROT2);
	REARROT2.setRotationPoint(0F, 0F, 4F);
	setRotation(REARROT2, 0F, 0F, 0F);
	REARROT2.mirror = true;
	body2 = new ModelRenderer(this, 14, 20);
	body2.addBox(-4F, -4F, -2F, 8, 8, 4);
	REARROT2.addChild(body2);
	body2.setRotationPoint(0F, 0F, 2F);
	body2.setTextureSize(64, 32);
	body2.mirror = true;
	setRotation(body2, 0F, 0F, 0F);

	REARROT3 = new ModelRenderer(this, "REARROT3");
	REARROT2.addChild(REARROT3);
	REARROT3.setRotationPoint(0F, 0F, 4F);
	setRotation(REARROT3, 0F, 0F, 0F);
	REARROT3.mirror = true;
	body3 = new ModelRenderer(this, 14, 20);
	body3.addBox(-4F, -4F, -2F, 8, 8, 4);
	REARROT3.addChild(body3);
	body3.setRotationPoint(0F, 0F, 2F);
	body3.setTextureSize(64, 32);
	body3.mirror = true;
	setRotation(body3, 0F, 0F, 0F);

	REARROT4 = new ModelRenderer(this, "REARROT4");
	REARROT3.addChild(REARROT4);
	REARROT4.setRotationPoint(0F, 0F, 4F);
	setRotation(REARROT4, 0F, 0F, 0F);
	REARROT4.mirror = true;
	body4 = new ModelRenderer(this, 14, 20);
	body4.addBox(-4F, -4F, -2F, 8, 8, 4);
	REARROT4.addChild(body4);
	body4.setRotationPoint(0F, 0F, 2F);
	body4.setTextureSize(64, 32);
	body4.mirror = true;
	setRotation(body4, 0F, 0F, 0F);

	REARROT5 = new ModelRenderer(this, "REARROT5");
	REARROT4.addChild(REARROT5);
	REARROT5.setRotationPoint(0F, 0F, 4F);
	setRotation(REARROT5, 0F, 0F, 0F);
	REARROT5.mirror = true;
	body5 = new ModelRenderer(this, 39, 20);
	body5.addBox(-4F, -4F, -2F, 8, 8, 4);
	REARROT5.addChild(body5);
	body5.setRotationPoint(0F, 0F, 2F);
	body5.setTextureSize(64, 32);
	body5.mirror = true;
	setRotation(body5, 0F, 0F, 0F);

	REARROT6 = new ModelRenderer(this, "REARROT6");
	REARROT5.addChild(REARROT6);
	REARROT6.setRotationPoint(0F, 0F, 4F);
	setRotation(REARROT6, 0F, 0F, 0F);
	REARROT6.mirror = true;
	body6 = new ModelRenderer(this, 39, 20);
	body6.addBox(-4F, -4F, -2F, 8, 8, 4);
	REARROT6.addChild(body6);
	body6.setRotationPoint(0F, 0F, 2F);
	body6.setTextureSize(64, 32);
	body6.mirror = true;
	setRotation(body6, 0F, 0F, 0F);

	REARROT7 = new ModelRenderer(this, "REARROT7");
	REARROT6.addChild(REARROT7);
	REARROT7.setRotationPoint(0F, 0F, 4F);
	setRotation(REARROT7, 0F, 0F, 0F);
	REARROT7.mirror = true;
	body7 = new ModelRenderer(this, 39, 20);
	body7.addBox(-4F, -4F, -2F, 8, 8, 4);
	REARROT7.addChild(body7);
	body7.setRotationPoint(0F, 0F, 2F);
	body7.setTextureSize(64, 32);
	body7.mirror = true;
	setRotation(body7, 0F, 0F, 0F);

    }

    @Override
    public void render(Entity entity, float f1, float f2, float f3, float f4, float f5, float f6) {
	super.render(entity, f1, f2, f3, f4, f5, f6);
	setRotationAngles(f1, f2, f3, f4, f5, f6, entity);
	float field_78145_g = 8.0F;
	float field_78151_h = 4.0F;

	if (this.isChild) {
	    float var8 = 2.0F;
	    GL11.glPushMatrix();
	    GL11.glTranslatef(0.0F, field_78145_g * f5, field_78151_h * f5);
	    HEADBASE.render(2 * f6);
	    GL11.glPopMatrix();
	    GL11.glPushMatrix();
	    GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
	    GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
	    REARROT.render(2 * f6);
	    GL11.glPopMatrix();
	} else {
	    HEADBASE.render(2 * f6);
	    REARROT.render(2 * f6);
	}

    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
	EntitySandWorm var5 = (EntitySandWorm) par1EntityLiving;
	if (var5.getEntityState() == EntityStates.idle) {
	    /* Mandables are at Position 0 when Hiding */
	    TOPMOROT.rotateAngleX = 0.0f;
	    LEFMOROT.rotateAngleY = 0.0f;
	    RIGMOROT.rotateAngleY = 0.0f;
	    BOTMOROT.rotateAngleX = 0.0f;

	    REARROT.rotateAngleY = 0.0f;
	    REARROT2.rotateAngleY = 0.0f;
	    REARROT3.rotateAngleY = 0.0f;
	    REARROT4.rotateAngleY = 0.0f;
	    REARROT5.rotateAngleY = 0.0f;
	    REARROT6.rotateAngleY = 0.0f;
	    REARROT7.rotateAngleY = 0.0f;
	} else {
	    float animSpeed = 1.0f;
	    TOPMOROT.rotateAngleX = (float) (MathHelper.cos(animSpeed * par2 * 0.6662F - (float) Math.PI) * 2.0F * par3 - Math.PI / 2f);
	    LEFMOROT.rotateAngleY = (float) (MathHelper.cos(animSpeed * par2 * 0.6662F - (float) Math.PI) * 2.0F * par3 + Math.PI / 2f);
	    RIGMOROT.rotateAngleY = (float) (MathHelper.cos(animSpeed * par2 * 0.6662F) * 2.0F * par3 - Math.PI / 2f);
	    BOTMOROT.rotateAngleX = (float) (MathHelper.cos(animSpeed * par2 * 0.6662F) * 2.0F * par3 + Math.PI / 2f);

	    REARROT.rotateAngleY = MathHelper.cos(animSpeed * par2 * 0.6662F) * 0.5F * par3;
	    REARROT2.rotateAngleY = MathHelper.cos(animSpeed * par2 * 0.6662F - (float) Math.PI / 3) * 0.5F * par3;
	    REARROT3.rotateAngleY = MathHelper.cos(animSpeed * par2 * 0.6662F - (float) Math.PI * 2 / 3) * 0.5F * par3;
	    REARROT4.rotateAngleY = MathHelper.cos(animSpeed * par2 * 0.6662F - (float) Math.PI) * 0.5F * par3;
	    REARROT5.rotateAngleY = MathHelper.cos(animSpeed * par2 * 0.6662F - (float) Math.PI * 4 / 3) * 0.5F * par3;
	    REARROT6.rotateAngleY = MathHelper.cos(animSpeed * par2 * 0.6662F - (float) Math.PI * 5 / 3) * 0.5F * par3;
	    REARROT7.rotateAngleY = MathHelper.cos(animSpeed * par2 * 0.6662F - (float) Math.PI * 6 / 3) * 0.5F * par3;
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
    }
}
