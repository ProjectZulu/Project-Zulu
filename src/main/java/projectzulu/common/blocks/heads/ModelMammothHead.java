package projectzulu.common.blocks.heads;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMammothHead extends ModelBase{

	ModelRenderer CENTERROT;
	private ModelRenderer HEADROT;
	private ModelRenderer TUSKLEFROT;
	private ModelRenderer TUSKRIGROT;
	private ModelRenderer NOSEROT1;
	private ModelRenderer NOSEROT2;
	private ModelRenderer NOSEROT3;
	private ModelRenderer NOSEROT4;
	private ModelRenderer NOSEROT5;
	private ModelRenderer NOSEROT6;
	private ModelRenderer NOSEROT7;
	private ModelRenderer NOSEROT8;
	private ModelRenderer NOSEROT9;
	private ModelRenderer EARRIGROT;
	private ModelRenderer EARLEFROT;
	
	public ModelMammothHead()
	{
		textureWidth = 128;
		textureHeight = 64;
		setTextureOffset("HEADROT.headhair", 58, 48);
		setTextureOffset("HEADROT.head", 34, 48);
		setTextureOffset("HEADROT.eyebrowrig", 64, 53);
		setTextureOffset("HEADROT.eyebrowlef", 64, 53);
		setTextureOffset("TUSKLEFROT.tuskleft", 44, 15);
		setTextureOffset("TUSKLEFROT.tuskleft3", 52, 14);
		setTextureOffset("TUSKLEFROT.tuskleft2", 44, 21);
		setTextureOffset("TUSKRIGROT.tuskrig", 44, 15);
		setTextureOffset("TUSKRIGROT.tuskrig2", 44, 21);
		setTextureOffset("TUSKRIGROT.tuskrig3", 52, 14);
		setTextureOffset("NOSEROT1.nose1", 0, 0);
		setTextureOffset("NOSEROT2.nose2", 0, 3);
		setTextureOffset("NOSEROT3.nose3", 0, 0);
		setTextureOffset("NOSEROT4.nose4", 0, 3);
		setTextureOffset("NOSEROT5.nose5", 0, 0);
		setTextureOffset("NOSEROT6.nose6", 0, 3);
		setTextureOffset("NOSEROT7.nose7", 0, 0);
		setTextureOffset("NOSEROT8.nose8", 0, 3);
		setTextureOffset("NOSEROT9.nose9", 0, 0);
		setTextureOffset("EARRIGROT.earrig", 58, 53);
		setTextureOffset("EARLEFROT.earlef", 58, 53);

		CENTERROT = new ModelRenderer(this, "CENTERROT");
		CENTERROT.setRotationPoint(0F, 16F, 0F);
		setRotation(CENTERROT, 0F, 0F, 0F);
		CENTERROT.mirror = true;
		HEADROT = new ModelRenderer(this, "HEADROT");
		HEADROT.setRotationPoint(0F, 0F, 5F);
		setRotation(HEADROT, 0F, 0F, 0F);
		HEADROT.mirror = true;
		HEADROT.addBox("headhair", -2.5F, -6F, -3.5F, 5, 2, 3);
		HEADROT.addBox("head", -4F, -4F, -4F, 8, 8, 4);
		HEADROT.addBox("eyebrowrig", -3F, -2F, -5F, 2, 1, 1);
		HEADROT.addBox("eyebrowlef", 1F, -2F, -5F, 2, 1, 1);
		TUSKLEFROT = new ModelRenderer(this, "TUSKLEFROT");
		TUSKLEFROT.setRotationPoint(-3F, 4F, -2F);
		setRotation(TUSKLEFROT, 0F, 0F, 0F);
		TUSKLEFROT.mirror = true;
		TUSKLEFROT.addBox("tuskleft", -1F, 0F, -1F, 2, 4, 2);
		TUSKLEFROT.addBox("tuskleft3", -1F, -3F, -6F, 2, 5, 2);
		TUSKLEFROT.addBox("tuskleft2", -1F, 2F, -6F, 2, 2, 5);
		HEADROT.addChild(TUSKLEFROT);
		TUSKRIGROT = new ModelRenderer(this, "TUSKRIGROT");
		TUSKRIGROT.setRotationPoint(3F, 4F, -2F);
		setRotation(TUSKRIGROT, 0F, 0F, 0F);
		TUSKRIGROT.mirror = true;
		TUSKRIGROT.addBox("tuskrig", -1F, 0F, -1F, 2, 4, 2);
		TUSKRIGROT.addBox("tuskrig2", -1F, 2F, -6F, 2, 2, 5);
		TUSKRIGROT.addBox("tuskrig3", -1F, -3F, -6F, 2, 5, 2);
		HEADROT.addChild(TUSKRIGROT);
		NOSEROT1 = new ModelRenderer(this, "NOSEROT1");
		NOSEROT1.setRotationPoint(0F, 3F, -4F);
		setRotation(NOSEROT1, 0F, 0F, 0F);
		NOSEROT1.mirror = true;
		NOSEROT1.addBox("nose1", -1.5F, -1F, -1F, 3, 2, 1);
		NOSEROT2 = new ModelRenderer(this, "NOSEROT2");
		NOSEROT2.setRotationPoint(0F, 0F, -1F);
		setRotation(NOSEROT2, 0F, 0F, 0F);
		NOSEROT2.mirror = true;
		NOSEROT2.addBox("nose2", -1.5F, -1F, -1F, 3, 2, 1);
		NOSEROT3 = new ModelRenderer(this, "NOSEROT3");
		NOSEROT3.setRotationPoint(0F, 0F, -1F);
		setRotation(NOSEROT3, 0F, 0F, 0F);
		NOSEROT3.mirror = true;
		NOSEROT3.addBox("nose3", -1.5F, -1F, -1F, 3, 2, 1);
		NOSEROT4 = new ModelRenderer(this, "NOSEROT4");
		NOSEROT4.setRotationPoint(0F, 0F, -1F);
		setRotation(NOSEROT4, 0F, 0F, 0F);
		NOSEROT4.mirror = true;
		NOSEROT4.addBox("nose4", -1.5F, -1F, -1F, 3, 2, 1);
		NOSEROT5 = new ModelRenderer(this, "NOSEROT5");
		NOSEROT5.setRotationPoint(0F, 0F, -1F);
		setRotation(NOSEROT5, 0F, 0F, 0F);
		NOSEROT5.mirror = true;
		NOSEROT5.addBox("nose5", -1.5F, -1F, -1F, 3, 2, 1);
		NOSEROT6 = new ModelRenderer(this, "NOSEROT6");
		NOSEROT6.setRotationPoint(0F, 0F, -1F);
		setRotation(NOSEROT6, 0F, 0F, 0F);
		NOSEROT6.mirror = true;
		NOSEROT6.addBox("nose6", -1.5F, -1F, -1F, 3, 2, 1);
		NOSEROT7 = new ModelRenderer(this, "NOSEROT7");
		NOSEROT7.setRotationPoint(0F, 0F, -1F);
		setRotation(NOSEROT7, 0F, 0F, 0F);
		NOSEROT7.mirror = true;
		NOSEROT7.addBox("nose7", -1.5F, -1F, -1F, 3, 2, 1);
		NOSEROT8 = new ModelRenderer(this, "NOSEROT8");
		NOSEROT8.setRotationPoint(0F, 0F, -1F);
		setRotation(NOSEROT8, 0F, 0F, 0F);
		NOSEROT8.mirror = true;
		NOSEROT8.addBox("nose8", -1.5F, -1F, -1F, 3, 2, 1);
		NOSEROT9 = new ModelRenderer(this, "NOSEROT9");
		NOSEROT9.setRotationPoint(0F, 0F, -1F);
		setRotation(NOSEROT9, 0F, 0F, 0F);
		NOSEROT9.mirror = true;
		NOSEROT9.addBox("nose9", -1.5F, -1F, -1F, 3, 2, 1);
		NOSEROT8.addChild(NOSEROT9);
		NOSEROT7.addChild(NOSEROT8);
		NOSEROT6.addChild(NOSEROT7);
		NOSEROT5.addChild(NOSEROT6);
		NOSEROT4.addChild(NOSEROT5);
		NOSEROT3.addChild(NOSEROT4);
		NOSEROT2.addChild(NOSEROT3);
		NOSEROT1.addChild(NOSEROT2);
		HEADROT.addChild(NOSEROT1);
		EARRIGROT = new ModelRenderer(this, "EARRIGROT");
		EARRIGROT.setRotationPoint(4F, -4F, -2F);
		setRotation(EARRIGROT, 0F, 0F, 0F);
		EARRIGROT.mirror = true;
		EARRIGROT.addBox("earrig", 0F, 0F, 0F, 1, 3, 2);
		HEADROT.addChild(EARRIGROT);
		EARLEFROT = new ModelRenderer(this, "EARLEFROT");
		EARLEFROT.setRotationPoint(-5F, -4F, -2F);
		setRotation(EARLEFROT, 0F, 0F, 0F);
		EARLEFROT.mirror = true;
		EARLEFROT.addBox("earlef", 0F, 0F, 0F, 1, 3, 2);
		HEADROT.addChild(EARLEFROT);
		CENTERROT.addChild(HEADROT);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float skullState, float f5)
	{
		setRotationAngles(f, f1, f2, f3, skullState, f5, entity);
		CENTERROT.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}


	public void setRotationAngles(float f, float f1, float f2, float f3, float skullState, float f5, Entity par7Entity){
		CENTERROT.rotateAngleY = f3 / (180F / (float)Math.PI);
		switch ((int)skullState) {
		case 1:
			NOSEROT1.rotateAngleX = (float) (00.0*Math.PI/180);
			NOSEROT2.rotateAngleX = (float) (00.0*Math.PI/180);
			NOSEROT3.rotateAngleX = (float) (0.0*Math.PI/180);
			NOSEROT4.rotateAngleX = (float) (22.5*Math.PI/180);
			NOSEROT5.rotateAngleX = (float) (22.5*Math.PI/180);
			NOSEROT6.rotateAngleX = (float) (22.5*Math.PI/180);
			NOSEROT7.rotateAngleX = (float) (22.5*Math.PI/180);
			NOSEROT8.rotateAngleX = (float) (0*Math.PI/180);
			NOSEROT9.rotateAngleX = (float) (0*Math.PI/180);
			break;
		default:
			NOSEROT1.rotateAngleX = (float) (22.5*Math.PI/180);
			NOSEROT2.rotateAngleX = (float) (22.5*Math.PI/180);
			NOSEROT3.rotateAngleX = (float) (22.5*Math.PI/180);
			NOSEROT4.rotateAngleX = (float) (22.5*Math.PI/180);
			NOSEROT5.rotateAngleX = (float) (0*Math.PI/180);
			NOSEROT6.rotateAngleX = (float) (0*Math.PI/180);
			NOSEROT7.rotateAngleX = (float) (0*Math.PI/180);
			NOSEROT8.rotateAngleX = (float) (0*Math.PI/180);
			NOSEROT9.rotateAngleX = (float) (0*Math.PI/180);
			break;
		}
		
		TUSKRIGROT.rotateAngleY = (float) (-22.5*Math.PI/180);
		TUSKLEFROT.rotateAngleY = (float) (22.5*Math.PI/180);

	}


}
