package projectzulu.common.blocks.heads;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFoxHead extends ModelBase{

	ModelRenderer CENTERROT;
	private ModelRenderer HEADROT;
	private ModelRenderer EARROTL;
	private ModelRenderer EARROTR;

	public ModelFoxHead()
	{
		textureWidth = 64;
		textureHeight = 32;
		setTextureOffset("HEADROT.Head", 0, 0);
		setTextureOffset("HEADROT.Nose", 16, 0);
		setTextureOffset("EARROTL.Ear1", 22, 4);
		setTextureOffset("EARROTR.Ear2", 16, 4);

		CENTERROT = new ModelRenderer(this, "CENTERROT");
		CENTERROT.setRotationPoint(0F, 21.5F, 0F);
		setRotation(CENTERROT, 0F, 0F, 0F);
		CENTERROT.mirror = true;
		HEADROT = new ModelRenderer(this, "HEADROT");
		HEADROT.setRotationPoint(0F, 0F, 1F);
		setRotation(HEADROT, 0F, 0F, 0F);
		HEADROT.mirror = true;
		HEADROT.addBox("Head", -2.5F, -2.5F, -3F, 5, 5, 3);
		HEADROT.addBox("Nose", -1F, 0F, -5F, 2, 2, 2);
		EARROTL = new ModelRenderer(this, "EARROTL");
		EARROTL.setRotationPoint(-0.5F, -2.5F, -2F);
		setRotation(EARROTL, 0F, 0F, 0F);
		EARROTL.mirror = true;
		EARROTL.addBox("Ear1", -2F, -3F, -0.5F, 2, 3, 1);
		HEADROT.addChild(EARROTL);
		EARROTR = new ModelRenderer(this, "EARROTR");
		EARROTR.setRotationPoint(0.5F, -2.5F, -2F);
		setRotation(EARROTR, 0F, 0F, 0F);
		EARROTR.mirror = true;
		EARROTR.addBox("Ear2", 0F, -3F, -0.5F, 2, 3, 1);
		HEADROT.addChild(EARROTR);
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
