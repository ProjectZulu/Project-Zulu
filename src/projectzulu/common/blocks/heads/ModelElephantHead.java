package projectzulu.common.blocks.heads;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelElephantHead extends ModelBase{

	ModelRenderer CENTERROT;
	private ModelRenderer HEADROT;
	private ModelRenderer EARLEFROT;
	private ModelRenderer EARRIGROT;
	private ModelRenderer TRUNK1ROT;
	private ModelRenderer TRUNK2ROT;
	private ModelRenderer TRUNK3ROT;
	private ModelRenderer TRUNK4ROT;
	private ModelRenderer TRUNK5ROT;
	private ModelRenderer TRUNK6ROT;
	private ModelRenderer TRUNK7ROT;
	private ModelRenderer TUSKRIGROT;
	private ModelRenderer TUSKLEFROT;


	public ModelElephantHead(){
		textureWidth = 128;
		textureHeight = 64;
		setTextureOffset("HEADROT.Head", 92, 0);
		setTextureOffset("HEADROT.nosebase1", 77, 20);
		setTextureOffset("HEADROT.nosebase2", 77, 33);
		setTextureOffset("HEADROT.nosebase3", 102, 33);
		setTextureOffset("HEADROT.nosebase4", 113, 43);
//		setTextureOffset("EARLEFROT.earlef", 55, 41);
//		setTextureOffset("EARRIGROT.earrig", 55, 41);
		setTextureOffset("TRUNK1ROT.trunk1", 97, 53);
		setTextureOffset("TRUNK2ROT.trunk2", 97, 51);
		setTextureOffset("TRUNK3ROT.trunk3", 97, 49);
		setTextureOffset("TRUNK4ROT.trunk4", 97, 47);
		setTextureOffset("TRUNK5ROT.trunk5", 97, 45);
		setTextureOffset("TRUNK6ROT.trunk6", 97, 43);
		setTextureOffset("TRUNK7ROT.trunk7", 97, 41);
		setTextureOffset("TUSKRIGROT.tuskrig", 60, 55);
		setTextureOffset("TUSKLEFROT.tusklef", 60, 55);

		CENTERROT = new ModelRenderer(this, "CENTERROT");
		CENTERROT.setRotationPoint(0F, 12F, 0F);
		setRotation(CENTERROT, 0F, 0F, 0F);
		CENTERROT.mirror = true;
		HEADROT = new ModelRenderer(this, "HEADROT");
		HEADROT.setRotationPoint(0F, -6F, 8F);
		setRotation(HEADROT, 0F, 0F, 0F);
		HEADROT.mirror = true;
		HEADROT.addBox("Head", -6F, -7F, -6F, 12, 14, 6);
		HEADROT.addBox("nosebase1", -6F, -3F, -8F, 12, 9, 2);
		HEADROT.addBox("nosebase2", -5F, -2F, -9F, 10, 7, 1);
		HEADROT.addBox("nosebase3", -4F, 0F, -10F, 8, 7, 1);
		HEADROT.addBox("nosebase4", -3F, 2F, -11F, 6, 7, 1);
//		EARLEFROT = new ModelRenderer(this, "EARLEFROT");
//		EARLEFROT.setRotationPoint(-7F, -0.5F, -4F);
//		setRotation(EARLEFROT, 0F, 1.22173F, 0F);
//		EARLEFROT.mirror = true;
//		EARLEFROT.addBox("earlef", -11F, -5.5F, 0F, 11, 11, 2);
//		HEADROT.addChild(EARLEFROT);
//		EARRIGROT = new ModelRenderer(this, "EARRIGROT");
//		EARRIGROT.setRotationPoint(7F, -0.5F, -4F);
//		setRotation(EARRIGROT, 0F, -1.22173F, 0F);
//		EARRIGROT.mirror = true;
//		EARRIGROT.addBox("earrig", 0F, -5.5F, 0F, 11, 11, 2);
//		HEADROT.addChild(EARRIGROT);
		TRUNK1ROT = new ModelRenderer(this, "TRUNK1ROT");
		TRUNK1ROT.setRotationPoint(0F, 4F, -10.5F);
		setRotation(TRUNK1ROT, 0F, 0F, 0F);
		TRUNK1ROT.mirror = true;
		TRUNK1ROT.addBox("trunk1", -2F, 0F, -1F, 4, 2, 2);
		TRUNK2ROT = new ModelRenderer(this, "TRUNK2ROT");
		TRUNK2ROT.setRotationPoint(0F, 2F, 0F);
		setRotation(TRUNK2ROT, 0F, 0F, 0F);
		TRUNK2ROT.mirror = true;
		TRUNK2ROT.addBox("trunk2", -2F, 0F, -1F, 4, 2, 2);
		TRUNK3ROT = new ModelRenderer(this, "TRUNK3ROT");
		TRUNK3ROT.setRotationPoint(0F, 2F, 0F);
		setRotation(TRUNK3ROT, 0F, 0F, 0F);
		TRUNK3ROT.mirror = true;
		TRUNK3ROT.addBox("trunk3", -2F, 0F, -1F, 4, 2, 2);
		TRUNK4ROT = new ModelRenderer(this, "TRUNK4ROT");
		TRUNK4ROT.setRotationPoint(0F, 2F, 0F);
		setRotation(TRUNK4ROT, 0F, 0F, 0F);
		TRUNK4ROT.mirror = true;
		TRUNK4ROT.addBox("trunk4", -2F, 0F, -1F, 4, 2, 2);
		TRUNK5ROT = new ModelRenderer(this, "TRUNK5ROT");
		TRUNK5ROT.setRotationPoint(0F, 2F, 0F);
		setRotation(TRUNK5ROT, 0F, 0F, 0F);
		TRUNK5ROT.mirror = true;
		TRUNK5ROT.addBox("trunk5", -2F, 0F, -1F, 4, 2, 2);
		TRUNK6ROT = new ModelRenderer(this, "TRUNK6ROT");
		TRUNK6ROT.setRotationPoint(0F, 2F, 0F);
		setRotation(TRUNK6ROT, 0F, 0F, 0F);
		TRUNK6ROT.mirror = true;
		TRUNK6ROT.addBox("trunk6", -2F, 0F, -1F, 4, 2, 2);
		TRUNK7ROT = new ModelRenderer(this, "TRUNK7ROT");
		TRUNK7ROT.setRotationPoint(0F, 2F, 0F);
		setRotation(TRUNK7ROT, 0F, 0F, 0F);
		TRUNK7ROT.mirror = true;
		TRUNK7ROT.addBox("trunk7", -2F, 0F, -1F, 4, 2, 2);
		TRUNK6ROT.addChild(TRUNK7ROT);
		TRUNK5ROT.addChild(TRUNK6ROT);
		TRUNK4ROT.addChild(TRUNK5ROT);
		TRUNK3ROT.addChild(TRUNK4ROT);
		TRUNK2ROT.addChild(TRUNK3ROT);
		TRUNK1ROT.addChild(TRUNK2ROT);
		HEADROT.addChild(TRUNK1ROT);
		TUSKRIGROT = new ModelRenderer(this, "TUSKRIGROT");
		TUSKRIGROT.setRotationPoint(4.5F, 2F, -8F);
		setRotation(TUSKRIGROT, 0.8028515F, 0F, 0F);
		TUSKRIGROT.mirror = true;
		TUSKRIGROT.addBox("tuskrig", 0F, 0F, -8F, 1, 1, 8);
		HEADROT.addChild(TUSKRIGROT);
		TUSKLEFROT = new ModelRenderer(this, "TUSKLEFROT");
		TUSKLEFROT.setRotationPoint(-5.5F, 2F, -8F);
		setRotation(TUSKLEFROT, 0.8028515F, 0F, 0F);
		TUSKLEFROT.mirror = true;
		TUSKLEFROT.addBox("tusklef", 0F, 0F, -8F, 1, 1, 8);
		HEADROT.addChild(TUSKLEFROT);
		CENTERROT.addChild(HEADROT);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float skullState, float f5){
		setRotationAngles(f, f1, f2, f3, skullState, f5, entity);
		CENTERROT.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}


	public void setRotationAngles(float f, float f1, float f2, float f3, float skullState, float f5, Entity par7Entity){
		CENTERROT.rotateAngleY = f3 / (180F / (float)Math.PI);
		
		TRUNK1ROT.rotateAngleX = (float) (0.0*Math.PI/180);
		TRUNK2ROT.rotateAngleX = (float) (0.0*Math.PI/180);
		TRUNK3ROT.rotateAngleX = (float) (0.0*Math.PI/180);
		TRUNK4ROT.rotateAngleX = (float) (45*Math.PI/180);
		TRUNK5ROT.rotateAngleX = (float) (45*Math.PI/180);
		TRUNK6ROT.rotateAngleX = (float) (45*Math.PI/180);
		TRUNK7ROT.rotateAngleX = (float) (45*Math.PI/180);

//		switch ((int)skullState) {
//		case 1:
//			NECKROT1.rotateAngleX = (float)(+0*Math.PI/180f);
//			NECKROT2.rotateAngleX = (float)(+30*Math.PI/180f);
//			NECKROT3.rotateAngleX = (float)(+40*Math.PI/180f);
//			HEADROT.rotateAngleX  = (float)(-60*Math.PI/180f);
//			break;
//
//		default:
//			NECKROT1.rotateAngleX = (float)(+90*Math.PI/180f);
//			NECKROT2.rotateAngleX = (float)(+10*Math.PI/180f);
//			NECKROT3.rotateAngleX = (float)(+10*Math.PI/180f);
//			HEADROT.rotateAngleX  = (float)(-80*Math.PI/180f);
//			break;
//		}

	}


}
