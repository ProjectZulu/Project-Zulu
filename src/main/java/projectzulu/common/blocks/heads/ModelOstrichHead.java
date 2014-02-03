package projectzulu.common.blocks.heads;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelOstrichHead extends ModelBase{

	ModelRenderer BODYROT;
	private ModelRenderer NECK1ROT;
	private ModelRenderer NECK2ROT;
	private ModelRenderer NECK3ROT;
	private ModelRenderer NECK4ROT;
	private ModelRenderer NECK5ROT;
	private ModelRenderer NECK6ROT;
	private ModelRenderer NECK7ROT;
	private ModelRenderer NECK8ROT;
	private ModelRenderer HEADROT;

	public ModelOstrichHead(){
		textureWidth = 64;
		textureHeight = 64;
		setTextureOffset("BODYROT.body3", 12, 25);
		setTextureOffset("NECK1ROT.neck1", 22, 26);
		setTextureOffset("NECK3ROT.neck3", 22, 26);
		setTextureOffset("NECK4ROT.neck4", 22, 26);
		setTextureOffset("NECK5ROT.neck5", 22, 26);
		setTextureOffset("NECK6ROT.neck6", 22, 26);
		setTextureOffset("NECK7ROT.neck7", 22, 26);
		setTextureOffset("NECK8ROT.neck8", 22, 26);
		setTextureOffset("HEADROT.head", 48, 0);
		setTextureOffset("HEADROT.beaktop", 36, 0);
		setTextureOffset("HEADROT.beakbot", 38, 5);

		BODYROT = new ModelRenderer(this, "BODYROT");
		BODYROT.setRotationPoint(0F, 26F, -1F);
		setRotation(BODYROT, -1.570796F, 0F, 0F);
		BODYROT.mirror = true;
		BODYROT.addBox("body3", -1.5F, -3.5F, -4F, 3, 3, 2);
		NECK1ROT = new ModelRenderer(this, "NECK1ROT");
		NECK1ROT.setRotationPoint(0F, -2F, -4F);
		setRotation(NECK1ROT, 0F, 0F, 0F);
		NECK1ROT.mirror = true;
		NECK1ROT.addBox("neck1", -1F, -1F, -2F, 2, 2, 2);
		NECK3ROT = new ModelRenderer(this, "NECK3ROT");
		NECK3ROT.setRotationPoint(0F, 0F, -2F);
		setRotation(NECK3ROT, 0F, 0F, 0F);
		NECK3ROT.mirror = true;
		NECK3ROT.addBox("neck3", -1F, -1F, -2F, 2, 2, 2);
		NECK4ROT = new ModelRenderer(this, "NECK4ROT");
		NECK4ROT.setRotationPoint(0F, 0F, -2F);
		setRotation(NECK4ROT, 0F, 0F, 0F);
		NECK4ROT.mirror = true;
		NECK4ROT.addBox("neck4", -1F, -1F, -2F, 2, 2, 2);
		NECK5ROT = new ModelRenderer(this, "NECK5ROT");
		NECK5ROT.setRotationPoint(0F, 0F, -2F);
		setRotation(NECK5ROT, 0F, 0F, 0F);
		NECK5ROT.mirror = true;
		NECK5ROT.addBox("neck5", -1F, -1F, -2F, 2, 2, 2);
		NECK6ROT = new ModelRenderer(this, "NECK6ROT");
		NECK6ROT.setRotationPoint(0F, 0F, -2F);
		setRotation(NECK6ROT, 0F, 0F, 0F);
		NECK6ROT.mirror = true;
		NECK6ROT.addBox("neck6", -1F, -1F, -2F, 2, 2, 2);
		NECK7ROT = new ModelRenderer(this, "NECK7ROT");
		NECK7ROT.setRotationPoint(0F, 0F, -2F);
		setRotation(NECK7ROT, 0F, 0F, 0F);
		NECK7ROT.mirror = true;
		NECK7ROT.addBox("neck7", -1F, -1F, -2F, 2, 2, 2);
		NECK8ROT = new ModelRenderer(this, "NECK8ROT");
		NECK8ROT.setRotationPoint(0F, 0F, -2F);
		setRotation(NECK8ROT, 0F, 0F, 0F);
		NECK8ROT.mirror = true;
		NECK8ROT.addBox("neck8", -1F, -1F, -2F, 2, 2, 2);
		HEADROT = new ModelRenderer(this, "HEADROT");
		HEADROT.setRotationPoint(0F, 0F, -2F);
		setRotation(HEADROT, 1.570796F, 0F, 0F);
		HEADROT.mirror = true;
		HEADROT.addBox("head", -2F, -2.5F, -4F, 4, 4, 4);
		HEADROT.addBox("beaktop", -1F, -1F, -8F, 2, 1, 4);
		HEADROT.addBox("beakbot", -1F, 0F, -7F, 2, 1, 3);
		NECK8ROT.addChild(HEADROT);
		NECK7ROT.addChild(NECK8ROT);
		NECK6ROT.addChild(NECK7ROT);
		NECK5ROT.addChild(NECK6ROT);
		NECK4ROT.addChild(NECK5ROT);
		NECK3ROT.addChild(NECK4ROT);
		NECK1ROT.addChild(NECK3ROT);
		BODYROT.addChild(NECK1ROT);
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
			BODYROT.rotateAngleX  = (float) (-90*Math.PI/180);
			NECK1ROT.rotateAngleX = (float) (-5*Math.PI/180);
			NECK3ROT.rotateAngleX = (float) (-5*Math.PI/180);
			NECK4ROT.rotateAngleX = (float) (-10*Math.PI/180);
			NECK5ROT.rotateAngleX = (float) (25*Math.PI/180);
			NECK6ROT.rotateAngleX = (float) (30*Math.PI/180);
			NECK7ROT.rotateAngleX = (float) (40*Math.PI/180);
			NECK8ROT.rotateAngleX = (float) (30*Math.PI/180);
			HEADROT.rotateAngleX = (float) (20*Math.PI/180);
			break;
		default:
			BODYROT.rotateAngleX  = (float) (0*Math.PI/180);
			NECK1ROT.rotateAngleX = (float) (-35*Math.PI/180);
			NECK3ROT.rotateAngleX = (float) (-30*Math.PI/180);
			NECK4ROT.rotateAngleX = (float) (-25*Math.PI/180);
			NECK5ROT.rotateAngleX = (float) (-20*Math.PI/180);
			NECK6ROT.rotateAngleX = (float) (+25*Math.PI/180);
			NECK7ROT.rotateAngleX = (float) (+30*Math.PI/180);
			NECK8ROT.rotateAngleX = (float) (+35*Math.PI/180);
			HEADROT.rotateAngleX  = (float) (+50*Math.PI/180);
			break;
		}
		
	}


}
