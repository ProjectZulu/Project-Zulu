package projectzulu.common.blocks.heads;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGorillaHead extends ModelBase{

	ModelRenderer HEADROT;

	public ModelGorillaHead()
	{
		textureWidth = 64;
		textureHeight = 64;
		setTextureOffset("HEADROT.head", 38, 17);
		setTextureOffset("HEADROT.ear1", 0, 0);
		setTextureOffset("HEADROT.ear2", 0, 4);
		setTextureOffset("HEADROT.nose", 47, 32);

		HEADROT = new ModelRenderer(this, "HEADROT");
		HEADROT.setRotationPoint(0F, 23F, 0F);
		setRotation(HEADROT, 0F, 0F, 0F);
		HEADROT.mirror = true;
		HEADROT.addBox("head", -3.5F, -7F, -3F, 7, 8, 6);
		HEADROT.addBox("ear1", -4.5F, -6F, -1F, 1, 2, 1);
		HEADROT.addBox("ear2", 3.5F, -6F, -1F, 1, 2, 1);
		HEADROT.addBox("nose", -2F, -3F, -4F, 4, 4, 1);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		HEADROT.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}


	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity){
		super.setRotationAngles(f, f1, f2, f3, f4, f5, par7Entity);
		HEADROT.rotateAngleY = f3 / (180F / (float)Math.PI);
	}


}
