package projectzulu.common.blocks.heads;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBearHead extends ModelBase{

	ModelRenderer CENTERROT;
	private ModelRenderer HEADROT;

	public ModelBearHead(){
		textureWidth = 128;
		textureHeight = 64;
		setTextureOffset("HEADROT.head", 58, 0);
		setTextureOffset("HEADROT.cuff", 58, 16);
		setTextureOffset("HEADROT.earl", 69, 12);
		setTextureOffset("HEADROT.earr", 75, 12);
		setTextureOffset("HEADROT.nose", 58, 12);

		CENTERROT = new ModelRenderer(this, "CENTERROT");
		CENTERROT.setRotationPoint(0F, 20F, 0F);
		setRotation(CENTERROT, 0F, 0F, 0F);
		CENTERROT.mirror = true;
		HEADROT = new ModelRenderer(this, "HEADROT");
		HEADROT.setRotationPoint(0F, 0F, 2F);
		setRotation(HEADROT, 0F, 0F, 0F);
		HEADROT.mirror = true;
		HEADROT.addBox("head", -3.5F, -3F, -5F, 7, 6, 5);
		HEADROT.addBox("cuff", -2F, 3F, -3F, 4, 1, 3);
		HEADROT.addBox("earl", -4F, -5F, -3F, 2, 2, 1);
		HEADROT.addBox("earr", 2F, -5F, -3F, 2, 2, 1);
		HEADROT.addBox("nose", -1.5F, 0F, -7F, 3, 2, 2);
		CENTERROT.addChild(HEADROT);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		CENTERROT.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity){
		super.setRotationAngles(f, f1, f2, f3, f4, f5, par7Entity);
		CENTERROT.rotateAngleY = f3 / (180F / (float)Math.PI);
	}


}
