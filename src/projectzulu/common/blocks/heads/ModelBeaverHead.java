package projectzulu.common.blocks.heads;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBeaverHead extends ModelBase{

	ModelRenderer CENTERROT;
	private ModelRenderer HEADROT;

	public ModelBeaverHead()
	{
		textureWidth = 64;
		textureHeight = 32;
		setTextureOffset("HEADROT.head", 38, 14);
		setTextureOffset("HEADROT.nose", 38, 24);
		setTextureOffset("HEADROT.teeth2", 46, 24);
		setTextureOffset("HEADROT.teeth1", 46, 24);

		CENTERROT = new ModelRenderer(this, "CENTERROT");
		CENTERROT.setRotationPoint(0F, 21F, 0F);
		setRotation(CENTERROT, 0F, 0F, 0F);
		CENTERROT.mirror = true;
		HEADROT = new ModelRenderer(this, "HEADROT");
		HEADROT.setRotationPoint(0F, 0F, 2F);
		setRotation(HEADROT, 0F, 0F, 0F);
		HEADROT.mirror = true;
		HEADROT.addBox("head", -3F, -3F, -4F, 6, 6, 4);
		HEADROT.addBox("nose", -1.5F, 0F, -5F, 3, 2, 1);
		HEADROT.addBox("teeth2", 0F, 2F, -5F, 1, 1, 1);
		HEADROT.addBox("teeth1", -1F, 2F, -5F, 1, 1, 1);
		CENTERROT.addChild(HEADROT);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		CENTERROT.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}


	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity){
		super.setRotationAngles(f, f1, f2, f3, f4, f5, par7Entity);
		CENTERROT.rotateAngleY = f3 / (180F / (float)Math.PI);
	}


}
