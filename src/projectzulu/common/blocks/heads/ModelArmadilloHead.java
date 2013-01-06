package projectzulu.common.blocks.heads;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelArmadilloHead extends ModelBase{

	ModelRenderer CENTERROT;
	private ModelRenderer HEADPIECE;

	public ModelArmadilloHead()
	{
		textureWidth = 64;
		textureHeight = 32;
		setTextureOffset("HEADPIECE.Ear2", 17, 0);
		setTextureOffset("HEADPIECE.Ear1", 17, 0);
		setTextureOffset("HEADPIECE.nose", 12, 9);
		setTextureOffset("HEADPIECE.chin", 0, 8);
		setTextureOffset("HEADPIECE.head", 0, 0);

		CENTERROT = new ModelRenderer(this, "CENTERROT");
		CENTERROT.setRotationPoint(0F, 21.5F, 0F);
		setRotation(CENTERROT, 0F, 0F, 0F);
		CENTERROT.mirror = true;
		HEADPIECE = new ModelRenderer(this, "HEADPIECE");
		HEADPIECE.setRotationPoint(0F, 0F, 2.5F);
		setRotation(HEADPIECE, 0F, 0F, 0F);
		HEADPIECE.mirror = true;
		HEADPIECE.addBox("Ear2", -4F, -3F, -2F, 2, 1, 1);
		HEADPIECE.addBox("Ear1", 2F, -3F, -2F, 2, 1, 1);
		HEADPIECE.addBox("nose", -0.5F, 1.5F, -7F, 1, 1, 2);
		HEADPIECE.addBox("chin", -1.5F, -0.5F, -5.5F, 3, 3, 3);
		HEADPIECE.addBox("head", -2.5F, -2.5F, -2.5F, 5, 5, 3);
		CENTERROT.addChild(HEADPIECE);
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
