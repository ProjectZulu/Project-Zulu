package projectzulu.common.blocks.heads;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCrocodileHead extends ModelBase{
	
	ModelRenderer CENTERROT;
	private ModelRenderer MOUTHBOTROT;
	private ModelRenderer MOUTHTOPROT;
	private ModelRenderer EYEBALLLEFROT;
	private ModelRenderer EYEBALLRIGROT;

	public ModelCrocodileHead()
	{
		textureWidth = 128;
		textureHeight = 64;
		setTextureOffset("MOUTHTOPROT.headtop1", 98, 23);
		setTextureOffset("MOUTHTOPROT.headtop2", 107, 34);
		setTextureOffset("MOUTHTOPROT.headtop3", 113, 42);
		setTextureOffset("MOUTHTOPROT.headtopdecor9", 94, 23);
		setTextureOffset("MOUTHTOPROT.headtop4", 105, 42);
		setTextureOffset("MOUTHTOPROT.teethtop2", 24, 2);
		setTextureOffset("MOUTHTOPROT.teethtop6", 24, 0);
		setTextureOffset("MOUTHTOPROT.teethtop3", 24, 2);
		setTextureOffset("MOUTHTOPROT.teethtop4", 24, 2);
		setTextureOffset("MOUTHTOPROT.teethtop5", 24, 2);
		setTextureOffset("MOUTHTOPROT.teethtop1", 24, 2);
		setTextureOffset("MOUTHTOPROT.teethtop1", 24, 2);
		setTextureOffset("MOUTHTOPROT.teethtop2", 24, 2);
		setTextureOffset("MOUTHTOPROT.teethtop3", 24, 2);
		setTextureOffset("MOUTHTOPROT.teethtop4", 24, 2);
		setTextureOffset("MOUTHTOPROT.teethtop5", 24, 2);
		setTextureOffset("MOUTHTOPROT.teethtop6", 24, 0);
		setTextureOffset("EYEBALLLEFROT.headtopdecor1", 98, 19);
		setTextureOffset("EYEBALLLEFROT.headtopdecor2", 98, 21);
		setTextureOffset("EYEBALLLEFROT.headtopdecor4", 98, 21);
		setTextureOffset("EYEBALLLEFROT.headtopdecor3", 98, 21);
		setTextureOffset("EYEBALLRIGROT.headtopdecor5", 98, 19);
		setTextureOffset("EYEBALLRIGROT.headtopdecor6", 98, 21);
		setTextureOffset("EYEBALLRIGROT.headtopdecor7", 98, 21);
		setTextureOffset("EYEBALLRIGROT.headtopdecor8", 98, 21);
		setTextureOffset("MOUTHBOTROT.headbot1", 98, 0);
		setTextureOffset("MOUTHBOTROT.headbot4", 109, 18);
		setTextureOffset("MOUTHBOTROT.headbot2", 107, 10);
		setTextureOffset("MOUTHBOTROT.headbot3", 115, 18);
		setTextureOffset("MOUTHBOTROT.teethbot6", 24, 0);
		setTextureOffset("MOUTHBOTROT.teethbot7", 24, 0);
		setTextureOffset("MOUTHBOTROT.teethbot10", 24, 0);
		setTextureOffset("MOUTHBOTROT.teethbot8", 24, 0);
		setTextureOffset("MOUTHBOTROT.teethbot1", 24, 0);
		setTextureOffset("MOUTHBOTROT.teethbot2", 24, 0);
		setTextureOffset("MOUTHBOTROT.teethbot3", 24, 0);
		setTextureOffset("MOUTHBOTROT.teethbot5", 24, 0);

		CENTERROT = new ModelRenderer(this, "CENTERROT");
		CENTERROT.setRotationPoint(0F, 21F, 0F);
		setRotation(CENTERROT, 0F, 0F, 0F);
		CENTERROT.mirror = true;
		MOUTHTOPROT = new ModelRenderer(this, "MOUTHTOPROT");
		MOUTHTOPROT.setRotationPoint(0F, 0F, 7F);
		setRotation(MOUTHTOPROT, 0F, 0F, 0F);
		MOUTHTOPROT.mirror = true;
		MOUTHTOPROT.addBox("headtop1", -4.5F, -4F, -7F, 9, 4, 7);
		MOUTHTOPROT.addBox("headtop2", -3.5F, -3F, -12F, 7, 3, 5);
		MOUTHTOPROT.addBox("headtop3", -2.5F, -2F, -15F, 5, 2, 3);
		MOUTHTOPROT.addBox("headtopdecor9", -1.5F, -5F, -3.5F, 3, 1, 2);
		MOUTHTOPROT.addBox("headtop4", -1.5F, -1F, -16F, 3, 1, 1);
		MOUTHTOPROT.addBox("teethtop2", -4.5F, 0F, -4F, 1, 2, 1);
		MOUTHTOPROT.addBox("teethtop6", -2.5F, 0F, -14F, 1, 1, 1);
		MOUTHTOPROT.addBox("teethtop3", -4.5F, 0F, -6F, 1, 2, 1);
		MOUTHTOPROT.addBox("teethtop4", -3.5F, 0F, -9F, 1, 2, 1);
		MOUTHTOPROT.addBox("teethtop5", -3.5F, 0F, -11F, 1, 2, 1);
		MOUTHTOPROT.addBox("teethtop1", -4.5F, 0F, -2F, 1, 2, 1);
		MOUTHTOPROT.addBox("teethtop1", 3.5F, 0F, -2F, 1, 2, 1);
		MOUTHTOPROT.addBox("teethtop2", 3.5F, 0F, -4F, 1, 2, 1);
		MOUTHTOPROT.addBox("teethtop3", 3.5F, 0F, -6F, 1, 2, 1);
		MOUTHTOPROT.addBox("teethtop4", 2.5F, 0F, -9F, 1, 2, 1);
		MOUTHTOPROT.addBox("teethtop5", 2.5F, 0F, -11F, 1, 2, 1);
		MOUTHTOPROT.addBox("teethtop6", 1.5F, 0F, -14F, 1, 1, 1);
		EYEBALLLEFROT = new ModelRenderer(this, "EYEBALLLEFROT");
		EYEBALLLEFROT.setRotationPoint(-2.5F, -4.5F, -2.5F);
		setRotation(EYEBALLLEFROT, 0F, 0F, 0F);
		EYEBALLLEFROT.mirror = true;
		EYEBALLLEFROT.addBox("headtopdecor1", -1.5F, -0.5F, -0.5F, 2, 1, 1);
		EYEBALLLEFROT.addBox("headtopdecor2", -1F, -0.5F, 0.5F, 2, 1, 1);
		EYEBALLLEFROT.addBox("headtopdecor4", -1F, -1.5F, -0.5F, 2, 1, 1);
		EYEBALLLEFROT.addBox("headtopdecor3", -1F, -0.5F, -1.5F, 2, 1, 1);
		MOUTHTOPROT.addChild(EYEBALLLEFROT);
		EYEBALLRIGROT = new ModelRenderer(this, "EYEBALLRIGROT");
		EYEBALLRIGROT.setRotationPoint(2.5F, -4.5F, -2.5F);
		setRotation(EYEBALLRIGROT, 0F, 0F, 0F);
		EYEBALLRIGROT.mirror = true;
		EYEBALLRIGROT.addBox("headtopdecor5", -0.5F, -0.5F, -0.5F, 2, 1, 1);
		EYEBALLRIGROT.addBox("headtopdecor6", -1F, -0.5F, -1.5F, 2, 1, 1);
		EYEBALLRIGROT.addBox("headtopdecor7", -1F, -0.5F, 0.5F, 2, 1, 1);
		EYEBALLRIGROT.addBox("headtopdecor8", -1F, -1.5F, -0.5F, 2, 1, 1);
		MOUTHTOPROT.addChild(EYEBALLRIGROT);
		CENTERROT.addChild(MOUTHTOPROT);
		MOUTHBOTROT = new ModelRenderer(this, "MOUTHBOTROT");
		MOUTHBOTROT.setRotationPoint(0F, 0F, 7F);
		setRotation(MOUTHBOTROT, 0F, 0F, 0F);
		MOUTHBOTROT.mirror = true;
		MOUTHBOTROT.addBox("headbot1", -4F, 0F, -7F, 8, 3, 7);
		MOUTHBOTROT.addBox("headbot4", -1F, 0F, -16F, 2, 1, 1);
		MOUTHBOTROT.addBox("headbot2", -3F, 0F, -12F, 6, 3, 5);
		MOUTHBOTROT.addBox("headbot3", -2F, 0F, -15F, 4, 2, 3);
		MOUTHBOTROT.addBox("teethbot6", 3F, -1F, -5F, 1, 1, 1);
		MOUTHBOTROT.addBox("teethbot7", 2F, -1F, -8F, 1, 1, 1);
		MOUTHBOTROT.addBox("teethbot10", 1F, -1F, -14F, 1, 1, 1);
		MOUTHBOTROT.addBox("teethbot8", 2F, -1F, -10F, 1, 1, 1);
		MOUTHBOTROT.addBox("teethbot1", -4F, -1F, -5F, 1, 1, 1);
		MOUTHBOTROT.addBox("teethbot2", -3F, -1F, -8F, 1, 1, 1);
		MOUTHBOTROT.addBox("teethbot3", -3F, -1F, -10F, 1, 1, 1);
		MOUTHBOTROT.addBox("teethbot5", -2F, -1F, -14F, 1, 1, 1);
		CENTERROT.addChild(MOUTHBOTROT);
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
