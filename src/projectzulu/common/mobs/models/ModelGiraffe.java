package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import projectzulu.common.core.ModelHelper;
import projectzulu.common.mobs.entity.EntityGiraffe;

public class ModelGiraffe extends ModelBase {
    ModelRenderer body;
    ModelRenderer body2;
    ModelRenderer body3;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer tail;
    ModelRenderer NECKROT1;
    private ModelRenderer NECKROT2;
    private ModelRenderer NECKROT3;
    private ModelRenderer NECKROT4;
    private ModelRenderer NECKROT5;
    private ModelRenderer NECKROT6;
    private ModelRenderer NECKROT7;
    private ModelRenderer HEADROT;

    public ModelGiraffe() {
	textureWidth = 128;
	textureHeight = 64;
	setTextureOffset("NECKROT1.neck1", 103, 36);
	setTextureOffset("NECKROT2.neck2", 103, 33);
	setTextureOffset("NECKROT3.neck3", 103, 30);
	setTextureOffset("NECKROT4.neck4", 103, 27);
	setTextureOffset("NECKROT5.neck5", 103, 24);
	setTextureOffset("NECKROT6.neck6", 103, 21);
	setTextureOffset("NECKROT7.neck7", 103, 18);
	setTextureOffset("HEADROT.head", 81, 46);
	setTextureOffset("HEADROT.horn1", 82, 52);
	setTextureOffset("HEADROT.horn2", 115, 52);

	body = new ModelRenderer(this, 0, 24);
	body.addBox(-7F, -16F, -23F, 14, 16, 24);
	body.setRotationPoint(0F, 5F, 11F);
	body.setTextureSize(128, 64);
	body.mirror = true;
	setRotation(body, 0F, 0F, 0F);
	body2 = new ModelRenderer(this, 56, 12);
	body2.addBox(-5F, -28F, 2F, 10, 21, 12);
	body2.setRotationPoint(0F, 5F, 11F);
	body2.setTextureSize(128, 64);
	body2.mirror = true;
	setRotation(body2, 1.047198F, 0F, 0F);
	body3 = new ModelRenderer(this, 92, 0);
	body3.addBox(-4.5F, -26F, -23F, 11, 10, 6);
	body3.setRotationPoint(-1F, 5F, 11F);
	body3.setTextureSize(128, 64);
	body3.mirror = true;
	setRotation(body3, 0F, 0F, 0F);
	leg1 = new ModelRenderer(this, 0, 0);
	leg1.addBox(-2F, 0F, -2F, 4, 19, 4);
	leg1.setRotationPoint(-5F, 5F, -9F);
	leg1.setTextureSize(128, 64);
	leg1.mirror = true;
	setRotation(leg1, 0F, 0F, 0F);
	leg2 = new ModelRenderer(this, 18, 0);
	leg2.addBox(-2F, 0F, -2F, 4, 19, 4);
	leg2.setRotationPoint(5F, 5F, -9F);
	leg2.setTextureSize(128, 64);
	leg2.mirror = true;
	setRotation(leg2, 0F, 0F, 0F);
	leg3 = new ModelRenderer(this, 36, 0);
	leg3.addBox(-2F, 0F, -2F, 4, 19, 4);
	leg3.setRotationPoint(-5F, 5F, 9F);
	leg3.setTextureSize(128, 64);
	leg3.mirror = true;
	setRotation(leg3, 0F, 0F, 0F);
	leg4 = new ModelRenderer(this, 0, 24);
	leg4.addBox(-2F, 0F, -2F, 4, 19, 4);
	leg4.setRotationPoint(5F, 5F, 9F);
	leg4.setTextureSize(128, 64);
	leg4.mirror = true;
	setRotation(leg4, 0F, 0F, 0F);
	tail = new ModelRenderer(this, 54, 0);
	tail.addBox(-1F, 0F, 0F, 2, 17, 2);
	tail.setRotationPoint(0F, -8F, 12F);
	tail.setTextureSize(128, 64);
	tail.mirror = true;
	setRotation(tail, 0F, 0F, 0F);
	NECKROT1 = new ModelRenderer(this, "NECKROT1");
	NECKROT1.setRotationPoint(0F, -21F, -9F);
	setRotation(NECKROT1, 0F, 0F, 0F);
	NECKROT1.mirror = true;
	NECKROT1.addBox("neck1", -3F, -3F, -3F, 6, 3, 6);
	NECKROT2 = new ModelRenderer(this, "NECKROT2");
	NECKROT2.setRotationPoint(0F, -3F, 0F);
	setRotation(NECKROT2, 0F, 0F, 0F);
	NECKROT2.mirror = true;
	NECKROT2.addBox("neck2", -3F, -3F, -3F, 6, 3, 6);
	NECKROT3 = new ModelRenderer(this, "NECKROT3");
	NECKROT3.setRotationPoint(0F, -3F, 0F);
	setRotation(NECKROT3, 0F, 0F, 0F);
	NECKROT3.mirror = true;
	NECKROT3.addBox("neck3", -3F, -3F, -3F, 6, 3, 6);
	NECKROT4 = new ModelRenderer(this, "NECKROT4");
	NECKROT4.setRotationPoint(0F, -3F, 0F);
	setRotation(NECKROT4, 0F, 0F, 0F);
	NECKROT4.mirror = true;
	NECKROT4.addBox("neck4", -3F, -3F, -3F, 6, 3, 6);
	NECKROT5 = new ModelRenderer(this, "NECKROT5");
	NECKROT5.setRotationPoint(0F, -3F, 0F);
	setRotation(NECKROT5, 0F, 0F, 0F);
	NECKROT5.mirror = true;
	NECKROT5.addBox("neck5", -3F, -3F, -3F, 6, 3, 6);
	NECKROT6 = new ModelRenderer(this, "NECKROT6");
	NECKROT6.setRotationPoint(0F, -3F, 0F);
	setRotation(NECKROT6, 0F, 0F, 0F);
	NECKROT6.mirror = true;
	NECKROT6.addBox("neck6", -3F, -3F, -3F, 6, 3, 6);
	NECKROT7 = new ModelRenderer(this, "NECKROT7");
	NECKROT7.setRotationPoint(0F, -3F, 0F);
	setRotation(NECKROT7, 0F, 0F, 0F);
	NECKROT7.mirror = true;
	NECKROT7.addBox("neck7", -3F, -3F, -3F, 6, 3, 6);
	HEADROT = new ModelRenderer(this, "HEADROT");
	HEADROT.setRotationPoint(0F, -1F, 1F);
	setRotation(HEADROT, 0F, 0F, 0F);
	HEADROT.mirror = true;
	HEADROT.addBox("head", -3.5F, -6F, -9F, 7, 6, 12);
	HEADROT.addBox("horn1", -2F, -8F, 1F, 1, 2, 1);
	HEADROT.addBox("horn2", 1F, -8F, 1F, 1, 2, 1);
	NECKROT7.addChild(HEADROT);
	NECKROT6.addChild(NECKROT7);
	NECKROT5.addChild(NECKROT6);
	NECKROT4.addChild(NECKROT5);
	NECKROT3.addChild(NECKROT4);
	NECKROT2.addChild(NECKROT3);
	NECKROT1.addChild(NECKROT2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	super.render(entity, f, f1, f2, f3, f4, f5);
	setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	body.render(f5);
	body2.render(f5);
	body3.render(f5);
	leg1.render(f5);
	leg2.render(f5);
	leg3.render(f5);
	leg4.render(f5);
	tail.render(f5);
	NECKROT1.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
	model.rotateAngleX = x;
	model.rotateAngleY = y;
	model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {
	super.setRotationAngles(f, f1, f2, f3, f4, f5, par7Entity);
	HEADROT.rotateAngleX = Math.min(Math.max(f4, -45), +30) * (float) (Math.PI / 180f);
	HEADROT.rotateAngleY = Math.min(Math.max(f3, -45), +45) * (float) (Math.PI / 180f);
    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
	EntityGiraffe var5 = (EntityGiraffe) par1EntityLiving;
	/* State Animation Rotations */
	tail.rotateAngleZ = (float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper.abs(Math
		.log(par3 + 1)));

	/* Left Side Legs */
	leg1.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper.abs(Math
		.log(par3 + 1)));
	leg1.rotateAngleZ = (float) (0 * Math.PI / 180);
	leg3.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper.abs(Math
		.log(par3 + 1)));

	/* Right Side Legs */
	leg2.rotateAngleX = -(float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper.abs(Math
		.log(par3 + 1)));
	leg2.rotateAngleZ = (float) (0 * Math.PI / 180);
	leg4.rotateAngleX = -(float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper.abs(Math
		.log(par3 + 1)));

	float minTime;
	float maxTime;
	if (var5.getAnimTime() > 0) {
	    minTime = var5.maxAnimTime * 0;
	    maxTime = var5.maxAnimTime;

	    /* Left Side Legs */
	    leg1.rotateAngleX = ModelHelper.mapValueofSet1ToSet2(var5.getAnimTime(), minTime, maxTime,
		    (float) (0 * Math.PI / 180), (float) (-130 * Math.PI / 180));
	    leg1.rotateAngleZ = ModelHelper.mapValueofSet1ToSet2(var5.getAnimTime(), minTime, maxTime,
		    (float) (0 * Math.PI / 180), (float) (-30 * Math.PI / 180));

	    /* Right Side Legs */
	    leg2.rotateAngleX = ModelHelper.mapValueofSet1ToSet2(var5.getAnimTime(), minTime, maxTime,
		    (float) (0 * Math.PI / 180), (float) (-130 * Math.PI / 180));
	    leg2.rotateAngleZ = ModelHelper.mapValueofSet1ToSet2(var5.getAnimTime(), minTime, maxTime,
		    (float) (0 * Math.PI / 180), (float) (30 * Math.PI / 180));
	}
	super.setLivingAnimations(par1EntityLiving, par2, par3, par4);
    }
}
