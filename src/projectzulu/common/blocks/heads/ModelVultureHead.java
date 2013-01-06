package projectzulu.common.blocks.heads;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelVultureHead extends ModelBase{

	ModelRenderer BODYROT;
	private ModelRenderer NECKROT1;
	private ModelRenderer NECKROT2;
	private ModelRenderer NECKROT3;
	private ModelRenderer HEADROT;


	public ModelVultureHead(){
		textureWidth = 64;
		textureHeight = 32;
		setTextureOffset("NECKROT1.neck1", 8, 10);
		setTextureOffset("NECKROT2.neck2", 8, 10);
		setTextureOffset("NECKROT3.neck3", 8, 10);
		setTextureOffset("HEADROT.mouthbot", 0, 25);
		setTextureOffset("HEADROT.mouthtal", 6, 25);
		setTextureOffset("HEADROT.mouthtop", 0, 21);
		setTextureOffset("HEADROT.head", 0, 16);

		BODYROT = new ModelRenderer(this, "BODYROT");
		BODYROT.setRotationPoint(0.5F, 24.5F, 0F);
		setRotation(BODYROT, 0F, 0F, 0F);
		BODYROT.mirror = true;
		NECKROT1 = new ModelRenderer(this, "NECKROT1");
		NECKROT1.setRotationPoint(0F, 0F, 1F);
		setRotation(NECKROT1, 0F, 0F, 0F);
		NECKROT1.mirror = true;
		NECKROT1.addBox("neck1", -0.5F, -2F, -0.5F, 1, 2, 1);
		NECKROT2 = new ModelRenderer(this, "NECKROT2");
		NECKROT2.setRotationPoint(0F, -2F, 0F);
		setRotation(NECKROT2, 0F, 0F, 0F);
		NECKROT2.mirror = true;
		NECKROT2.addBox("neck2", -0.5F, -2F, -0.5F, 1, 2, 1);
		NECKROT3 = new ModelRenderer(this, "NECKROT3");
		NECKROT3.setRotationPoint(0F, -2F, 0F);
		setRotation(NECKROT3, 0F, 0F, 0F);
		NECKROT3.mirror = true;
		NECKROT3.addBox("neck3", -0.5F, -2F, -0.5F, 1, 2, 1);
		HEADROT = new ModelRenderer(this, "HEADROT");
		HEADROT.setRotationPoint(0F, -2F, 0F);
		setRotation(HEADROT, 0F, 0F, 0F);
		HEADROT.mirror = true;
		HEADROT.addBox("mouthbot", -0.5F, 0.5F, -4F, 1, 1, 2);
		HEADROT.addBox("mouthtal", -1F, 0.5F, -5F, 2, 2, 1);
		HEADROT.addBox("mouthtop", -1F, -0.5F, -5F, 2, 1, 3);
		HEADROT.addBox("head", -1.5F, -1.5F, -2F, 3, 3, 2);
		NECKROT3.addChild(HEADROT);
		NECKROT2.addChild(NECKROT3);
		NECKROT1.addChild(NECKROT2);
		BODYROT.addChild(NECKROT1);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float skullState, float f5){
		setRotationAngles(f, f1, f2, f3, skullState, f5, entity);
		BODYROT.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}


	public void setRotationAngles(float f, float f1, float f2, float f3, float skullState, float f5, Entity par7Entity){
		BODYROT.rotateAngleY = f3 / (180F / (float)Math.PI);
		switch ((int)skullState) {
		case 1:
			NECKROT1.rotateAngleX = (float)(+0*Math.PI/180f);
			NECKROT2.rotateAngleX = (float)(+30*Math.PI/180f);
			NECKROT3.rotateAngleX = (float)(+40*Math.PI/180f);
			HEADROT.rotateAngleX  = (float)(-60*Math.PI/180f);
			break;

		default:
			NECKROT1.rotateAngleX = (float)(+90*Math.PI/180f);
			NECKROT2.rotateAngleX = (float)(+10*Math.PI/180f);
			NECKROT3.rotateAngleX = (float)(+10*Math.PI/180f);
			HEADROT.rotateAngleX  = (float)(-80*Math.PI/180f);
			break;
		}

	}


}
