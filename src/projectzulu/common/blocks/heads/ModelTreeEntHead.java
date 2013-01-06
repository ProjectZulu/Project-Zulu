package projectzulu.common.blocks.heads;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;



public class ModelTreeEntHead extends ModelBase{

	ModelRenderer CENTERROT;
	private ModelRenderer BODYROT;
	private ModelRenderer HEADROT;
	private ModelRenderer NOSEROT;
	private ModelRenderer BACKTREESTEM5;
	private ModelRenderer BACKTREESTEM6;

	public ModelTreeEntHead()
	{
		textureWidth = 128;
		textureHeight = 64;
		setTextureOffset("BODYROT.mosscb2", 0, 28);
		setTextureOffset("BODYROT.mosscb", 0, 20);
		setTextureOffset("HEADROT.head", 88, 0);
		setTextureOffset("HEADROT.brow5", 98, 18);
		setTextureOffset("HEADROT.brow4", 98, 18);
		setTextureOffset("HEADROT.brow3", 98, 18);
		setTextureOffset("HEADROT.brow2", 106, 17);
		setTextureOffset("HEADROT.brow1", 106, 13);
		setTextureOffset("NOSEROT.nose1", 90, 16);
		setTextureOffset("NOSEROT.nose2", 94, 20);
		setTextureOffset("NOSEROT.nose3", 90, 22);
		setTextureOffset("BACKTREESTEM5.backtreestem5", 32, 28);
		setTextureOffset("BACKTREESTEM5.backtreesleaves5", 23, 42);
		setTextureOffset("BACKTREESTEM6.backtreesleaves6", 23, 42);
		setTextureOffset("BACKTREESTEM6.backtreestem6", 32, 28);

		CENTERROT = new ModelRenderer(this, "CENTERROT");
		CENTERROT.setRotationPoint(0F, 24F, 0F);
		setRotation(CENTERROT, 0F, 0F, 0F);
		CENTERROT.mirror = true;
		BODYROT = new ModelRenderer(this, "BODYROT");
		BODYROT.setRotationPoint(0F, 0F, 2.5F);
		setRotation(BODYROT, 0F, 0F, 0F);
		BODYROT.mirror = true;
		BODYROT.addBox("mosscb2", -3.5F, -3F, 1F, 7, 3, 4);
		BODYROT.addBox("mosscb", -4.5F, -3F, -3F, 9, 3, 4);
		HEADROT = new ModelRenderer(this, "HEADROT");
		HEADROT.setRotationPoint(0F, -3F, -4.5F);
		setRotation(HEADROT, 0F, 0F, 0F);
		HEADROT.mirror = true;
		HEADROT.addBox("head", -2.5F, -9F, -2.5F, 5, 12, 4);
		HEADROT.addBox("brow5", 1.5F, -5.5F, -3.5F, 1, 2, 1);
		HEADROT.addBox("brow4", -0.5F, -5.5F, -3.5F, 1, 2, 1);
		HEADROT.addBox("brow3", -2.5F, -5.5F, -3.5F, 1, 2, 1);
		HEADROT.addBox("brow2", -2.5F, -3.5F, -3.5F, 5, 3, 1);
		HEADROT.addBox("brow1", -2.5F, -7.5F, -3.5F, 5, 2, 1);
		NOSEROT = new ModelRenderer(this, "NOSEROT");
		NOSEROT.setRotationPoint(0F, -3F, -3F);
		setRotation(NOSEROT, 0F, 0F, 0F);
		NOSEROT.mirror = true;
		NOSEROT.addBox("nose1", -0.5F, 0F, -2.5F, 1, 1, 2);
		NOSEROT.addBox("nose2", -0.5F, 1F, -2.5F, 1, 1, 1);
		NOSEROT.addBox("nose3", -1F, 2F, -3F, 2, 2, 2);
		HEADROT.addChild(NOSEROT);
		BODYROT.addChild(HEADROT);
		BACKTREESTEM5 = new ModelRenderer(this, "BACKTREESTEM5");
		BACKTREESTEM5.setRotationPoint(3F, -3F, 0.5F);
		setRotation(BACKTREESTEM5, 0F, 0F, 0F);
		BACKTREESTEM5.mirror = true;
		BACKTREESTEM5.addBox("backtreestem5", -0.5F, -4F, -0.5F, 1, 4, 1);
		BACKTREESTEM5.addBox("backtreesleaves5", -3F, -7F, -3F, 6, 3, 6);
		BODYROT.addChild(BACKTREESTEM5);
		BACKTREESTEM6 = new ModelRenderer(this, "BACKTREESTEM6");
		BACKTREESTEM6.setRotationPoint(-3F, -3F, 0.5F);
		setRotation(BACKTREESTEM6, 0F, 0F, 0F);
		BACKTREESTEM6.mirror = true;
		BACKTREESTEM6.addBox("backtreesleaves6", -3F, -7F, -3F, 6, 3, 6);
		BACKTREESTEM6.addBox("backtreestem6", -0.5F, -4F, -0.5F, 1, 4, 1);
		BODYROT.addChild(BACKTREESTEM6);
		CENTERROT.addChild(BODYROT);
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
		BACKTREESTEM5.rotateAngleX = (float)(-10*Math.PI/180f);
		BACKTREESTEM5.rotateAngleZ = (float)(+20*Math.PI/180f);

		BACKTREESTEM6.rotateAngleX = (float)(-10*Math.PI/180f);
		BACKTREESTEM6.rotateAngleZ = (float)(-20*Math.PI/180f);

		CENTERROT.rotateAngleY = f3 / (180F / (float)Math.PI);
	}


}
