package projectzulu.common.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTombstone extends ModelBase
{
	//fields
	ModelRenderer baserig;
	ModelRenderer body4;
	ModelRenderer topcenter;
	ModelRenderer baselef;
	ModelRenderer body3;
	ModelRenderer body2;
	ModelRenderer body1;
	private ModelRenderer CENTERROT;
	private ModelRenderer TOPLEFROT;
	private ModelRenderer TOPRIGROT;

	public ModelTombstone(){
		textureWidth = 64;
		textureHeight = 32;
		setTextureOffset("TOPLEFROT.topleft", 0, 17);
		setTextureOffset("TOPRIGROT.topright", 0, 17);

		body4 = new ModelRenderer(this, 28, 15);
		body4.addBox(0F, -18F, -4.5F, 9, 8, 9);
		body4.setRotationPoint(0F, 28F, 0F);
		body4.setTextureSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0F, 0F, 0F);
		topcenter = new ModelRenderer(this, 28, 16);
		topcenter.addBox(-4.5F, -4F, -4.5F, 9, 4, 9);
		topcenter.setRotationPoint(0F, 2F, 0F);
		topcenter.setTextureSize(64, 32);
		topcenter.mirror = true;
		setRotation(topcenter, 0F, 0F, 0F);
		body3 = new ModelRenderer(this, 28, 15);
		body3.addBox(-9F, -18F, -4.5F, 9, 8, 9);
		body3.setRotationPoint(0F, 28F, 0F);
		body3.setTextureSize(64, 32);
		body3.mirror = true;
		body3.mirror = true;
		setRotation(body3, 0F, 0F, 0F);
		body3.mirror = false;
		body2 = new ModelRenderer(this, 28, 15);
		body2.addBox(0F, -18F, -4.5F, 9, 8, 9);
		body2.setRotationPoint(0F, 20F, 0F);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		body2.mirror = true;
		setRotation(body2, 0F, 0F, 0F);
		body2.mirror = false;
		body1 = new ModelRenderer(this, 28, 15);
		body1.addBox(-9F, -18F, -4.5F, 9, 8, 9);
		body1.setRotationPoint(0F, 20F, 0F);
		body1.setTextureSize(64, 32);
		body1.mirror = true;
		setRotation(body1, 0F, 0F, 0F);
		baselef = new ModelRenderer(this, 0, 0);
		baselef.addBox(-12F, -3F, -6F, 12, 6, 12);
		baselef.setRotationPoint(0F, 21F, 0F);
		baselef.setTextureSize(64, 32);
		baselef.mirror = true;
		baselef.mirror = true;
		setRotation(baselef, 0F, 0F, 0F);
		baselef.mirror = false;
		baserig = new ModelRenderer(this, 0, 0);
		baserig.addBox(0F, -3F, -6F, 12, 6, 12);
		baserig.setRotationPoint(0F, 21F, 0F);
		baserig.setTextureSize(64, 32);
		baserig.mirror = true;
		baserig.mirror = true;
		setRotation(baserig, 0F, 0F, 0F);
		baserig.mirror = false;
		CENTERROT = new ModelRenderer(this, "CENTERROT");
		CENTERROT.setRotationPoint(0F, 1.2F, 0F);
		setRotation(CENTERROT, 0F, 0F, 0F);
		CENTERROT.mirror = true;
		TOPLEFROT = new ModelRenderer(this, "TOPLEFROT");
		TOPLEFROT.setRotationPoint(-5.8F, 0F, 0F);
		setRotation(TOPLEFROT, 0F, 0F, 0.837758F);
		TOPLEFROT.mirror = true;
		TOPLEFROT.addBox("topleft", -1.5F, -3F, -4.5F, 3, 6, 9);
		CENTERROT.addChild(TOPLEFROT);
		TOPRIGROT = new ModelRenderer(this, "TOPRIGROT");
		TOPRIGROT.setRotationPoint(5.8F, 0F, 0F);
		setRotation(TOPRIGROT, 0F, 0F, -0.837758F);
		TOPRIGROT.mirror = true;
		TOPRIGROT.addBox("topright", -1.5F, -3F, -4.5F, 3, 6, 9);
		CENTERROT.addChild(TOPRIGROT);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		baserig.render(f5);
		body4.render(f5);
		topcenter.render(f5);
		baselef.render(f5);
		body3.render(f5);
		body2.render(f5);
		body1.render(f5);
		
	    CENTERROT.render(f5);

	}

	public void render1(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, float angle){
		body4.rotateAngleY = (float) (angle*Math.PI/180);
	    body4.render(f5);
		topcenter.rotateAngleY = (float) (angle*Math.PI/180);
	    topcenter.render(f5);
	    
		body3.rotateAngleY = (float) (angle*Math.PI/180);
	    body3.render(f5);
		body2.rotateAngleY = (float) (angle*Math.PI/180);
	    body2.render(f5);
		body1.rotateAngleY = (float) (angle*Math.PI/180);
	    body1.render(f5);
	    
		baselef.rotateAngleY = (float) (angle*Math.PI/180);
	    baselef.render(f5);
		baserig.rotateAngleY = (float) (angle*Math.PI/180);
	    baserig.render(f5);	
	}

	public void render2(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, float angle){
	    CENTERROT.rotateAngleY = (float) (angle*Math.PI/180);
	    CENTERROT.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity){
		super.setRotationAngles(f, f1, f2, f3, f4, f5, par7Entity);
	}

}

