package projectzulu.common.blocks.heads;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBirdHead extends ModelBase{
	ModelRenderer Body;
	ModelRenderer wingrig;
	ModelRenderer winglef;
	ModelRenderer tail;
	ModelRenderer LEGRIGTOPROT;
	ModelRenderer LEGLEFTOPROT;
	ModelRenderer HEADROT;
	private ModelRenderer LEGRIGBOTROT;
	private ModelRenderer LEGLEFBOTROT;
	private ModelRenderer BEAKROT;
	private ModelRenderer BODY;
	private ModelRenderer WINGLEFROT;
	private ModelRenderer WINGRIGROT;
	private ModelRenderer TAILROT;

	public ModelBirdHead(){
		textureWidth = 32;
		textureHeight = 32;
		setTextureOffset("BODY.Body", 0, 0);
		setTextureOffset("WINGLEFROT.winglef", 22, 0);
		setTextureOffset("WINGRIGROT.wingrig", 13, 0);
		setTextureOffset("TAILROT.tail", 14, 8);
		setTextureOffset("HEADROT.Head", 0, 7);
		setTextureOffset("BEAKROT.beak", 9, 9);
		setTextureOffset("LEGLEFTOPROT.legtoplef", 3, 12);
		setTextureOffset("LEGLEFBOTROT.legbotlef", 7, 15);
		setTextureOffset("LEGRIGTOPROT.lefrigtop", 0, 12);
		setTextureOffset("LEGRIGBOTROT.legbotrig", 0, 15);

		BODY = new ModelRenderer(this, "BODY");
		BODY.setRotationPoint(0F, 21F, 0F);
		setRotation(BODY, 0F, 0F, 0F);
		BODY.mirror = true;
		BODY.addBox("Body", -1F, -1F, -2F, 2, 2, 4);
		WINGLEFROT = new ModelRenderer(this, "WINGLEFROT");
		WINGLEFROT.setRotationPoint(-1F, -0.7F, 0F);
		setRotation(WINGLEFROT, 0F, 0F, 0F);
		WINGLEFROT.mirror = true;
		WINGLEFROT.addBox("winglef", -1F, 0F, -1.5F, 1, 1, 3);
		BODY.addChild(WINGLEFROT);
		WINGRIGROT = new ModelRenderer(this, "WINGRIGROT");
		WINGRIGROT.setRotationPoint(1F, -0.7F, 0F);
		setRotation(WINGRIGROT, 0F, 0F, 0F);
		WINGRIGROT.mirror = true;
		WINGRIGROT.addBox("wingrig", 0F, 0F, -1.5F, 1, 1, 3);
		BODY.addChild(WINGRIGROT);
		TAILROT = new ModelRenderer(this, "TAILROT");
		TAILROT.setRotationPoint(0F, -0.2F, 1F);
		setRotation(TAILROT, 0.3346145F, 0F, 0F);
		TAILROT.mirror = true;
		TAILROT.addBox("tail", -0.4666667F, 0F, 0F, 1, 0, 3);
		BODY.addChild(TAILROT);
		HEADROT = new ModelRenderer(this, "HEADROT");
		HEADROT.setRotationPoint(0F, -1.2F, -2F);
		setRotation(HEADROT, 0F, 0F, 0F);
		HEADROT.mirror = true;
		HEADROT.addBox("Head", -1F, -1F, -1F, 2, 2, 2);
		BEAKROT = new ModelRenderer(this, "BEAKROT");
		BEAKROT.setRotationPoint(0F, 0F, 0F);
		setRotation(BEAKROT, -0.7801622F, 0F, 0F);
		BEAKROT.mirror = true;
		BEAKROT.addBox("beak", -0.5333334F, 0.3F, -0.9F, 1, 1, 1);
		HEADROT.addChild(BEAKROT);
		BODY.addChild(HEADROT);
		LEGLEFTOPROT = new ModelRenderer(this, "LEGLEFTOPROT");
		LEGLEFTOPROT.setRotationPoint(-0.5F, 1.3F, 1F);
		setRotation(LEGLEFTOPROT, -0.6702064F, 0F, 0F);
		LEGLEFTOPROT.mirror = true;
		LEGLEFTOPROT.addBox("legtoplef", -0.5F, 0F, 0F, 1, 2, 0);
		LEGLEFBOTROT = new ModelRenderer(this, "LEGLEFBOTROT");
		LEGLEFBOTROT.setRotationPoint(0F, 2F, 0F);
		setRotation(LEGLEFBOTROT, 0.6702064F, 0F, 0F);
		LEGLEFBOTROT.mirror = true;
		LEGLEFBOTROT.addBox("legbotlef", -0.5F, -0.4F, -2.3F, 1, 0, 2);
		LEGLEFTOPROT.addChild(LEGLEFBOTROT);
		BODY.addChild(LEGLEFTOPROT);
		LEGRIGTOPROT = new ModelRenderer(this, "LEGRIGTOPROT");
		LEGRIGTOPROT.setRotationPoint(0.5F, 1.3F, 1F);
		setRotation(LEGRIGTOPROT, -0.6684611F, 0F, 0F);
		LEGRIGTOPROT.mirror = true;
		LEGRIGTOPROT.addBox("lefrigtop", -0.5F, 0F, 0F, 1, 2, 0);
		LEGRIGBOTROT = new ModelRenderer(this, "LEGRIGBOTROT");
		LEGRIGBOTROT.setRotationPoint(0F, 2F, 0F);
		setRotation(LEGRIGBOTROT, 0.6702064F, 0F, 0F);
		LEGRIGBOTROT.mirror = true;
		LEGRIGBOTROT.addBox("legbotrig", -0.5F, -0.4F, -2.3F, 1, 0, 2);
		LEGRIGTOPROT.addChild(LEGRIGBOTROT);
		BODY.addChild(LEGRIGTOPROT);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
		//		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		//		float field_78145_g = 8.0F;
		//		float field_78151_h = 4.0F;
		//
		//		if (this.isChild){
		//			float var8 = 2.0F;
		//			GL11.glPushMatrix();
		//			GL11.glTranslatef(0.0F, field_78145_g * f5, field_78151_h * f5);
		//			HEADROT.render(f5);
		//			GL11.glPopMatrix();
		//			GL11.glPushMatrix();
		//			GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
		//			GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
		//			Body.render(f5);
		//			Body.render(f5);
		//			wingrig.render(f5);
		//			winglef.render(f5);
		//			tail.render(f5);
		//			LEGRIGTOPROT.render(f5);
		//			LEGLEFTOPROT.render(f5);
		//			GL11.glPopMatrix();
		//		}else{
//		Body.render(f5);
//		wingrig.render(f5);
//		winglef.render(f5);
//		tail.render(f5);
//		LEGRIGTOPROT.render(f5);
//		LEGLEFTOPROT.render(f5);
//		HEADROT.render(f5);
		BODY.render(f5);
		//		}
		//		
	}

	public void renderHead(float f5){
		Body.render(f5);
		wingrig.render(f5);
		winglef.render(f5);
		tail.render(f5);
		LEGRIGTOPROT.render(f5);
		LEGLEFTOPROT.render(f5);
		HEADROT.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, par7Entity);
		BODY.rotateAngleY = f3 / (180F / (float)Math.PI);

//		Body.rotateAngleY = f3 / (180F / (float)Math.PI);
//		wingrig.rotateAngleY = f3 / (180F / (float)Math.PI);
//		winglef.rotateAngleY = f3 / (180F / (float)Math.PI);
//		tail.rotateAngleY = f3 / (180F / (float)Math.PI);
//		LEGRIGTOPROT.rotateAngleY = f3 / (180F / (float)Math.PI);
//		LEGLEFTOPROT.rotateAngleY = f3 / (180F / (float)Math.PI);
//		HEADROT.rotateAngleY = f3 / (180F / (float)Math.PI);
	}


}
