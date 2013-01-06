package projectzulu.common.blocks.heads;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPenguinHead extends ModelBase{

	ModelRenderer BODYROT;
	private ModelRenderer HEADROT;

	public ModelPenguinHead(){
		textureWidth = 64;
		textureHeight = 32;
		setTextureOffset("HEADROT.head", 0, 0);
		setTextureOffset("HEADROT.beaktop", 24, 0);
		setTextureOffset("HEADROT.beakbot", 24, 5);

		BODYROT = new ModelRenderer(this, "BODYROT");
		BODYROT.setRotationPoint(0F, 24F, 0F);
		setRotation(BODYROT, 0F, 0F, 0F);
		BODYROT.mirror = true;
		HEADROT = new ModelRenderer(this, "HEADROT");
		HEADROT.setRotationPoint(0F, 0F, 1F);
		setRotation(HEADROT, 0F, 0F, 0F);
		HEADROT.mirror = true;
		HEADROT.addBox("head", -3F, -5F, -3F, 6, 5, 6);
		HEADROT.addBox("beaktop", -1F, -2.5F, -7F, 2, 1, 4);
		HEADROT.addBox("beakbot", -0.5F, -1.5F, -6F, 1, 1, 3);
		BODYROT.addChild(HEADROT);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		BODYROT.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}


	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity){
		super.setRotationAngles(f, f1, f2, f3, f4, f5, par7Entity);
		BODYROT.rotateAngleY = f3 / (180F / (float)Math.PI);
	}


}
