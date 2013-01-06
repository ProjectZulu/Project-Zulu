package projectzulu.common.blocks.heads;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRhinoHead extends ModelBase{

	ModelRenderer CENTERROT;
	private ModelRenderer BODYROT;
	private ModelRenderer HEADROT;

	public ModelRhinoHead(){
		textureWidth = 128;
		textureHeight = 64;
		setTextureOffset("HEADROT.earrig", 102, 15);
		setTextureOffset("HEADROT.earlef", 96, 15);
		setTextureOffset("HEADROT.horn1", 96, 0);
		setTextureOffset("HEADROT.horn2", 96, 5);
		setTextureOffset("HEADROT.horn3", 96, 10);
		setTextureOffset("HEADROT.head1", 108, 0);
		setTextureOffset("HEADROT.head2", 109, 12);
		setTextureOffset("HEADROT.head3", 109, 23);
		setTextureOffset("HEADROT.head4", 111, 34);

		CENTERROT = new ModelRenderer(this, "CENTERROT");
		CENTERROT.setRotationPoint(0F, 18F, 0F);
		setRotation(CENTERROT, 0F, 0F, 0F);
		CENTERROT.mirror = true;
		HEADROT = new ModelRenderer(this, "HEADROT");
		HEADROT.setRotationPoint(0F, 0F, 6.5F);
		setRotation(HEADROT, 0F, 0F, 0F);
		HEADROT.mirror = true;
		HEADROT.addBox("earrig", 3F, -6F, -1.5F, 2, 3, 1);
		HEADROT.addBox("earlef", -5F, -6F, -1.5F, 2, 3, 1);
		HEADROT.addBox("horn1", -1.5F, -1F, -13F, 3, 2, 3);
		HEADROT.addBox("horn2", -1F, -4F, -13F, 2, 3, 2);
		HEADROT.addBox("horn3", -0.5F, -8F, -13F, 1, 4, 1);
		HEADROT.addBox("head1", -4F, -4F, -2F, 8, 10, 2);
		HEADROT.addBox("head2", -3.5F, -2F, -5F, 7, 8, 3);
		HEADROT.addBox("head3", -3F, -1F, -9F, 6, 7, 4);
		HEADROT.addBox("head4", -2.5F, 1F, -13F, 5, 5, 4);
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
