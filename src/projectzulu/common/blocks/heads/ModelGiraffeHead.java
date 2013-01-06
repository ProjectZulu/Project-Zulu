package projectzulu.common.blocks.heads;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGiraffeHead extends ModelBase{

	private ModelRenderer HEADROT;
	private ModelRenderer EARROTL;
	private ModelRenderer EARROTR;
	ModelRenderer NECKROT1;
	private ModelRenderer NECKROT2;
	private ModelRenderer NECKROT3;
	private ModelRenderer NECKROT4;
	private ModelRenderer NECKROT5;
	private ModelRenderer NECKROT6;
	private ModelRenderer NECKROT7;

	public ModelGiraffeHead(){
		textureWidth = 128;
		textureHeight = 64;
		setTextureOffset("NECKROT2.neck2", 103, 33);
		setTextureOffset("NECKROT3.neck3", 103, 30);
		setTextureOffset("NECKROT4.neck4", 103, 27);
		setTextureOffset("NECKROT5.neck5", 103, 24);
		setTextureOffset("NECKROT6.neck6", 103, 21);
		setTextureOffset("NECKROT7.neck7", 103, 18);
		setTextureOffset("HEADROT.head", 81, 46);
		setTextureOffset("HEADROT.horn1", 82, 52);
		setTextureOffset("HEADROT.horn2", 115, 52);

		NECKROT2 = new ModelRenderer(this, "NECKROT2");
		NECKROT2.setRotationPoint(0F, 24F, 0F);
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

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float skullState, float f5){
		setRotationAngles(f, f1, f2, f3, skullState, f5, entity);
		NECKROT2.render(f5);
	}
//	null, 0.0F, 0.0F, 0.0F, rotation, skullState, scale
	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}


	public void setRotationAngles(float f, float f1, float f2, float f3, float skullState, float f5, Entity par7Entity){
		//		NECKROT1.rotateAngleX = (float) (75*Math.PI/180);
		switch ((int)skullState) {
		case 0:
			NECKROT2.rotateAngleX = (float) (90*Math.PI/180);
			NECKROT3.rotateAngleX = (float) ( 0*Math.PI/180);
			NECKROT4.rotateAngleX = (float) ( -30*Math.PI/180);
			NECKROT5.rotateAngleX = (float) ( -30*Math.PI/180);
			NECKROT6.rotateAngleX = (float) ( -30*Math.PI/180);
			NECKROT7.rotateAngleX = (float) ( 0*Math.PI/180);
			HEADROT.rotateAngleX = (float) (0*Math.PI/180);
			break;
		case 1:
			NECKROT2.rotateAngleX = (float) ( 0*Math.PI/180);
			NECKROT3.rotateAngleX = (float) ( 0*Math.PI/180);
			NECKROT4.rotateAngleX = (float) ( 0*Math.PI/180);
			NECKROT5.rotateAngleX = (float) ( 0*Math.PI/180);
			NECKROT6.rotateAngleX = (float) ( 0*Math.PI/180);
			NECKROT7.rotateAngleX = (float) ( 0*Math.PI/180);
			HEADROT.rotateAngleX  = (float) ( 5*Math.PI/180);
			break;
		default:
			NECKROT2.rotateAngleX = (float) (90*Math.PI/180);
			NECKROT3.rotateAngleX = (float) ( 0*Math.PI/180);
			NECKROT4.rotateAngleX = (float) ( -30*Math.PI/180);
			NECKROT5.rotateAngleX = (float) ( -30*Math.PI/180);
			NECKROT6.rotateAngleX = (float) ( -30*Math.PI/180);
			NECKROT7.rotateAngleX = (float) ( 0*Math.PI/180);
			HEADROT.rotateAngleX = (float) (0*Math.PI/180);
			break;
		}
		NECKROT2.rotateAngleY = f3 / (180F / (float)Math.PI);

	}


}
