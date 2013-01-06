package projectzulu.common.blocks.heads;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLizardHead extends ModelBase{

    ModelRenderer CENTERROT;
	private ModelRenderer HEADBASE;
	private ModelRenderer FRILL3ROT;
	private ModelRenderer FRILL4ROT;

	public ModelLizardHead()
	{
		textureWidth = 64;
		textureHeight = 32;
		setTextureOffset("FRILL3ROT.Frill3", 37, 22);
		setTextureOffset("HEADBASE.head", 0, 0);
		setTextureOffset("HEADBASE.Frill1", 29, 22);
		setTextureOffset("HEADBASE.Frill2", 29, 22);
		setTextureOffset("FRILL4ROT.Frill4", 37, 22);

		CENTERROT = new ModelRenderer(this, "CENTERROT");
		CENTERROT.setRotationPoint(0F, 21F, 0F);
		setRotation(CENTERROT, 0F, 0F, 0F);
		CENTERROT.mirror = true;
		HEADBASE = new ModelRenderer(this, "HEADBASE");
		HEADBASE.setRotationPoint(0F, 0F, 3F);
		setRotation(HEADBASE, 0F, 0F, 0F);
		HEADBASE.mirror = true;
		FRILL3ROT = new ModelRenderer(this, "FRILL3ROT");
		FRILL3ROT.setRotationPoint(1F, -2F, -2.5F);
		setRotation(FRILL3ROT, 0F, 0F, 0F);
		FRILL3ROT.mirror = true;
		FRILL3ROT.addBox("Frill3", 0F, -2F, -0.5F, 4, 4, 1);
		HEADBASE.addChild(FRILL3ROT);
		HEADBASE.addBox("head", -3F, -3F, -8F, 6, 6, 8);
		HEADBASE.addBox("Frill1", 3F, -2F, -3F, 3, 5, 1);
		HEADBASE.addBox("Frill2", -6F, -2F, -3F, 3, 5, 1);
		FRILL4ROT = new ModelRenderer(this, "FRILL4ROT");
		FRILL4ROT.setRotationPoint(-1F, -2F, -2.5F);
		setRotation(FRILL4ROT, 0F, 0F, 0F);
		FRILL4ROT.mirror = true;
		FRILL4ROT.addBox("Frill4", -4F, -2F, -0.5F, 4, 4, 1);
		HEADBASE.addChild(FRILL4ROT);
		CENTERROT.addChild(HEADBASE);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		CENTERROT.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}


	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity){
		super.setRotationAngles(f, f1, f2, f3, f4, f5, par7Entity);
		CENTERROT.rotateAngleY = f3 / (180F / (float)Math.PI);
		FRILL3ROT.rotateAngleZ = (float)(  -45*Math.PI/180  );
		FRILL4ROT.rotateAngleZ = (float)(  45*Math.PI/180  );
	}


}
