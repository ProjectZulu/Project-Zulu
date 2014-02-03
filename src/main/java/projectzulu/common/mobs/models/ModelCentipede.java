package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class ModelCentipede extends ModelBase {

    ModelRenderer sidebody2;
    ModelRenderer body;
    ModelRenderer sidebody3;
    ModelRenderer sidebody1;
    ModelRenderer ANTENLEFROT1;
    ModelRenderer ANTENRIGROT1;
    ModelRenderer LEG11ROT;
    ModelRenderer LEG21ROT;
    ModelRenderer LEG31ROT;
    ModelRenderer LEG41ROT;
    private ModelRenderer ANTENLEFROT2;
    private ModelRenderer ANTENLEFROT3;
    private ModelRenderer ANTENRIGROT2;
    private ModelRenderer ANTENRIGROT3;
    private ModelRenderer LEG12ROT;
    private ModelRenderer LEG22ROT;
    private ModelRenderer LEG32ROT;
    private ModelRenderer LEG42ROT;

    public ModelCentipede() {
	textureWidth = 64;
	textureHeight = 32;
	setTextureOffset("ANTENLEFROT1.antenlef1", 54, 0);
	setTextureOffset("ANTENLEFROT2.antenlef2", 50, 0);
	setTextureOffset("ANTENLEFROT3.antenlef3", 47, 0);
	setTextureOffset("ANTENRIGROT1.antenrig1", 54, 0);
	setTextureOffset("ANTENRIGROT2.antenrig2", 50, 0);
	setTextureOffset("ANTENRIGROT3.antenrig3", 47, 0);
	setTextureOffset("LEG11ROT.leg11", 34, 0);
	setTextureOffset("LEG12ROT.leg12", 34, 3);
	setTextureOffset("LEG21ROT.leg21", 34, 0);
	setTextureOffset("LEG22ROT.leg22", 34, 3);
	setTextureOffset("LEG31ROT.leg31", 34, 0);
	setTextureOffset("LEG32ROT.leg32", 34, 3);
	setTextureOffset("LEG41ROT.leg41", 34, 0);
	setTextureOffset("LEG42ROT.leg42", 34, 3);

	sidebody2 = new ModelRenderer(this, 42, 17);
	sidebody2.addBox(0F, -2.5F, -5F, 1, 5, 10);
	sidebody2.setRotationPoint(4.5F, 18.5F, 0F);
	sidebody2.setTextureSize(64, 32);
	sidebody2.mirror = true;
	setRotation(sidebody2, 0F, 0F, 0F);
	body = new ModelRenderer(this, 0, 14);
	body.addBox(-4.5F, -3F, -12F, 9, 6, 12);
	body.setRotationPoint(0F, 18.5F, 6F);
	body.setTextureSize(64, 32);
	body.mirror = true;
	setRotation(body, 0F, 0F, 0F);
	sidebody3 = new ModelRenderer(this, 0, 2);
	sidebody3.addBox(-3F, 0F, -5.5F, 6, 1, 11);
	sidebody3.setRotationPoint(0F, 21.5F, 0F);
	sidebody3.setTextureSize(64, 32);
	sidebody3.mirror = true;
	setRotation(sidebody3, 0F, 0F, 0F);
	sidebody1 = new ModelRenderer(this, 42, 17);
	sidebody1.addBox(-1F, -2.5F, -5F, 1, 5, 10);
	sidebody1.setRotationPoint(-4.5F, 18.5F, 0F);
	sidebody1.setTextureSize(64, 32);
	sidebody1.mirror = true;
	setRotation(sidebody1, 0F, 0F, 0F);
	ANTENLEFROT1 = new ModelRenderer(this, "ANTENLEFROT1");
	ANTENLEFROT1.setRotationPoint(-3F, 17.5F, -6F);
	setRotation(ANTENLEFROT1, -0.5235988F, 0.5235988F, 0F);
	ANTENLEFROT1.mirror = true;
	ANTENLEFROT1.addBox("antenlef1", -0.5F, -0.5F, -4F, 1, 1, 4);
	ANTENLEFROT2 = new ModelRenderer(this, "ANTENLEFROT2");
	ANTENLEFROT2.setRotationPoint(0F, 0F, -4F);
	setRotation(ANTENLEFROT2, -0.5235988F, 0F, 0F);
	ANTENLEFROT2.mirror = true;
	ANTENLEFROT2.addBox("antenlef2", -0.5F, -0.5F, -3F, 1, 1, 3);
	ANTENLEFROT3 = new ModelRenderer(this, "ANTENLEFROT3");
	ANTENLEFROT3.setRotationPoint(0F, 0F, -3F);
	setRotation(ANTENLEFROT3, -0.5235988F, 0F, 0F);
	ANTENLEFROT3.mirror = true;
	ANTENLEFROT3.addBox("antenlef3", -0.5F, -0.5F, -2F, 1, 1, 2);
	ANTENLEFROT2.addChild(ANTENLEFROT3);
	ANTENLEFROT1.addChild(ANTENLEFROT2);
	ANTENRIGROT1 = new ModelRenderer(this, "ANTENRIGROT1");
	ANTENRIGROT1.setRotationPoint(3F, 17.5F, -6F);
	setRotation(ANTENRIGROT1, -0.5235988F, -0.5235988F, 0F);
	ANTENRIGROT1.mirror = true;
	ANTENRIGROT1.addBox("antenrig1", -0.5F, -0.5F, -4F, 1, 1, 4);
	ANTENRIGROT2 = new ModelRenderer(this, "ANTENRIGROT2");
	ANTENRIGROT2.setRotationPoint(0F, 0F, -4F);
	setRotation(ANTENRIGROT2, -0.5235988F, 0F, 0F);
	ANTENRIGROT2.mirror = true;
	ANTENRIGROT2.addBox("antenrig2", -0.5F, -0.5F, -3F, 1, 1, 3);
	ANTENRIGROT3 = new ModelRenderer(this, "ANTENRIGROT3");
	ANTENRIGROT3.setRotationPoint(0F, 0F, -3F);
	setRotation(ANTENRIGROT3, -0.5235988F, 0F, 0F);
	ANTENRIGROT3.mirror = true;
	ANTENRIGROT3.addBox("antenrig3", -0.5F, -0.5F, -2F, 1, 1, 2);
	ANTENRIGROT2.addChild(ANTENRIGROT3);
	ANTENRIGROT1.addChild(ANTENRIGROT2);
	LEG11ROT = new ModelRenderer(this, "LEG11ROT");
	LEG11ROT.setRotationPoint(-3F, 22F, -3F);
	setRotation(LEG11ROT, 0F, 0F, -0.1745329F);
	LEG11ROT.mirror = true;
	LEG11ROT.addBox("leg11", -4F, -0.5F, -1F, 4, 1, 2);
	LEG12ROT = new ModelRenderer(this, "LEG12ROT");
	LEG12ROT.setRotationPoint(-4F, 0F, 0F);
	setRotation(LEG12ROT, 0F, 0F, -0.7853982F);
	LEG12ROT.mirror = true;
	LEG12ROT.addBox("leg12", -2F, -0.5F, -1F, 2, 1, 2);
	LEG11ROT.addChild(LEG12ROT);
	LEG21ROT = new ModelRenderer(this, "LEG21ROT");
	LEG21ROT.setRotationPoint(3F, 22F, -3F);
	setRotation(LEG21ROT, 0F, 0F, 0.1745329F);
	LEG21ROT.mirror = true;
	LEG21ROT.addBox("leg21", 0F, -0.5F, -1F, 4, 1, 2);
	LEG22ROT = new ModelRenderer(this, "LEG22ROT");
	LEG22ROT.setRotationPoint(4F, 0F, 0F);
	setRotation(LEG22ROT, 0F, 0F, 0.7853982F);
	LEG22ROT.mirror = true;
	LEG22ROT.addBox("leg22", 0F, -0.5F, -1F, 2, 1, 2);
	LEG21ROT.addChild(LEG22ROT);
	LEG31ROT = new ModelRenderer(this, "LEG31ROT");
	LEG31ROT.setRotationPoint(-3F, 22F, 3F);
	setRotation(LEG31ROT, 0F, 0F, -0.1745329F);
	LEG31ROT.mirror = true;
	LEG31ROT.addBox("leg31", -4F, -0.5F, -1F, 4, 1, 2);
	LEG32ROT = new ModelRenderer(this, "LEG32ROT");
	LEG32ROT.setRotationPoint(-4F, 0F, 0F);
	setRotation(LEG32ROT, 0F, 0F, -0.7853982F);
	LEG32ROT.mirror = true;
	LEG32ROT.addBox("leg32", -2F, -0.5F, -1F, 2, 1, 2);
	LEG31ROT.addChild(LEG32ROT);
	LEG41ROT = new ModelRenderer(this, "LEG41ROT");
	LEG41ROT.setRotationPoint(3F, 22F, 3F);
	setRotation(LEG41ROT, 0F, 0F, 0.1745329F);
	LEG41ROT.mirror = true;
	LEG41ROT.addBox("leg41", 0F, -0.5F, -1F, 4, 1, 2);
	LEG42ROT = new ModelRenderer(this, "LEG42ROT");
	LEG42ROT.setRotationPoint(4F, 0F, 0F);
	setRotation(LEG42ROT, 0F, 0F, 0.7853982F);
	LEG42ROT.mirror = true;
	LEG42ROT.addBox("leg42", 0F, -0.5F, -1F, 2, 1, 2);
	LEG41ROT.addChild(LEG42ROT);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	super.render(entity, f, f1, f2, f3, f4, f5);
	setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	sidebody2.render(f5);
	body.render(f5);
	sidebody3.render(f5);
	sidebody1.render(f5);
	ANTENLEFROT1.render(f5);
	ANTENRIGROT1.render(f5);
	LEG11ROT.render(f5);
	LEG21ROT.render(f5);
	LEG31ROT.render(f5);
	LEG41ROT.render(f5);
    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
	super.setLivingAnimations(par1EntityLiving, par2, par3, par4);

	ANTENLEFROT2.rotateAngleX = (float) (-15 * Math.PI / 180 + 20 * Math.PI / 180
		* MathHelper.cos(par2 * 0.6662F + (float) Math.PI));
	ANTENLEFROT3.rotateAngleX = (float) (-15 * Math.PI / 180 + 20 * Math.PI / 180
		* MathHelper.cos(par2 * 0.6662F + (float) Math.PI));

	ANTENRIGROT2.rotateAngleX = (float) (-15 * Math.PI / 180 + 20 * Math.PI / 180
		* MathHelper.cos(par2 * 0.6662F + (float) Math.PI));
	ANTENRIGROT3.rotateAngleX = (float) (-15 * Math.PI / 180 + 20 * Math.PI / 180
		* MathHelper.cos(par2 * 0.6662F + (float) Math.PI));

	LEG11ROT.rotateAngleY = MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.4F * par3;
	LEG21ROT.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
	LEG31ROT.rotateAngleY = MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.4F * par3;
	LEG41ROT.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
	model.rotateAngleX = x;
	model.rotateAngleY = y;
	model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {
	super.setRotationAngles(f, f1, f2, f3, f4, f5, par7Entity);
    }
}
