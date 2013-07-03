package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import projectzulu.common.core.ModelHelper;

public class ModelOstrich extends ModelBase {
    ModelRenderer tail1;
    ModelRenderer tail3;
    ModelRenderer tail2;
    ModelRenderer tail4;
    ModelRenderer BODYROT;
    ModelRenderer WINGLEFROT;
    ModelRenderer WINGRIGROT;
    private ModelRenderer NECK1ROT;
    private ModelRenderer NECK2ROT;
    private ModelRenderer NECK3ROT;
    private ModelRenderer NECK4ROT;
    private ModelRenderer NECK5ROT;
    private ModelRenderer NECK6ROT;
    private ModelRenderer NECK7ROT;
    private ModelRenderer NECK8ROT;
    private ModelRenderer HEADROT;
    private ModelRenderer LEGTOPROT1;
    private ModelRenderer LEGBOTROT1;
    private ModelRenderer FOOTROT1;
    private ModelRenderer TOE12ROT;
    private ModelRenderer TOE13ROT;
    private ModelRenderer TOE11ROT;
    private ModelRenderer LEGTOPROT2;
    private ModelRenderer LEGBOTROT2;
    private ModelRenderer FOOTROT2;
    private ModelRenderer TOE22ROT;
    private ModelRenderer TOE23ROT;
    private ModelRenderer TOE21ROT;

    public ModelOstrich() {
	textureWidth = 64;
	textureHeight = 64;
	setTextureOffset("BODYROT.body1", 12, 38);
	setTextureOffset("BODYROT.body2", 12, 30);
	setTextureOffset("BODYROT.body3", 12, 25);
	setTextureOffset("NECK1ROT.neck1", 22, 26);
	setTextureOffset("NECK2ROT.neck2", 22, 26);
	setTextureOffset("NECK3ROT.neck3", 22, 26);
	setTextureOffset("NECK4ROT.neck4", 22, 26);
	setTextureOffset("NECK5ROT.neck5", 22, 26);
	setTextureOffset("NECK6ROT.neck6", 22, 26);
	setTextureOffset("NECK7ROT.neck7", 22, 26);
	setTextureOffset("NECK8ROT.neck8", 22, 26);
	setTextureOffset("HEADROT.head", 48, 0);
	setTextureOffset("HEADROT.beaktop", 36, 0);
	setTextureOffset("HEADROT.beakbot", 38, 5);
	setTextureOffset("LEGTOPROT1.legtop1", 0, 42);
	setTextureOffset("LEGBOTROT1.legbot1", 0, 51);
	setTextureOffset("TOE12ROT.toe12", 0, 61);
	setTextureOffset("TOE13ROT.toe13", 6, 61);
	setTextureOffset("TOE11ROT.toe11", 0, 61);
	setTextureOffset("LEGTOPROT2.legtop2", 0, 42);
	setTextureOffset("LEGBOTROT2.legbot2", 0, 51);
	setTextureOffset("TOE22ROT.toe22", 0, 61);
	setTextureOffset("TOE23ROT.toe23", 6, 61);
	setTextureOffset("TOE21ROT.toe21", 0, 61);
	setTextureOffset("WINGLEFROT.winglef", 32, 14);
	setTextureOffset("WINGRIGROT.wingrig", 32, 14);

	tail1 = new ModelRenderer(this, 12, 8);
	tail1.addBox(-5F, -5F, 0F, 10, 5, 0);
	tail1.setRotationPoint(-4F, 2F, 10F);
	tail1.setTextureSize(64, 32);
	tail1.mirror = true;
	setRotation(tail1, -0.1487144F, 1.041001F, 0.4461433F);
	tail3 = new ModelRenderer(this, 12, 19);
	tail3.addBox(-5F, -5F, 0F, 10, 5, 0);
	tail3.setRotationPoint(-3F, 8F, 10F);
	tail3.setTextureSize(64, 32);
	tail3.mirror = true;
	setRotation(tail3, 0.2059489F, 1.343904F, 0.1919862F);
	tail2 = new ModelRenderer(this, 12, 3);
	tail2.addBox(-5F, -5F, 0F, 10, 5, 0);
	tail2.setRotationPoint(4F, 2F, 10F);
	tail2.setTextureSize(64, 32);
	tail2.mirror = true;
	setRotation(tail2, -0.1487144F, -1.041001F, -0.6320364F);
	tail4 = new ModelRenderer(this, 12, 14);
	tail4.addBox(-5F, -5F, 0F, 10, 5, 0);
	tail4.setRotationPoint(3F, 8F, 10F);
	tail4.setTextureSize(64, 32);
	tail4.mirror = true;
	setRotation(tail4, 0.2234021F, -1.343904F, -0.1919862F);
	BODYROT = new ModelRenderer(this, "BODYROT");
	BODYROT.setRotationPoint(0F, 4F, -7F);
	setRotation(BODYROT, 0F, 0F, 0F);
	BODYROT.mirror = true;
	BODYROT.addBox("body1", -5F, -5F, 0F, 10, 10, 16);
	BODYROT.addBox("body2", -3F, -4F, -2F, 6, 6, 2);
	BODYROT.addBox("body3", -1.5F, -3.5F, -4F, 3, 3, 2);
	NECK1ROT = new ModelRenderer(this, "NECK1ROT");
	NECK1ROT.setRotationPoint(0F, -2F, -4F);
	setRotation(NECK1ROT, 0F, 0F, 0F);
	NECK1ROT.mirror = true;
	NECK1ROT.addBox("neck1", -1F, -1F, -2F, 2, 2, 2);
	NECK2ROT = new ModelRenderer(this, "NECK2ROT");
	NECK2ROT.setRotationPoint(0F, 0F, -2F);
	setRotation(NECK2ROT, 0F, 0F, 0F);
	NECK2ROT.mirror = true;
	NECK2ROT.addBox("neck2", -1F, -1F, -2F, 2, 2, 2);
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
	setRotation(HEADROT, 0F, 0F, 0F);
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
	NECK2ROT.addChild(NECK3ROT);
	NECK1ROT.addChild(NECK2ROT);
	BODYROT.addChild(NECK1ROT);
	LEGTOPROT1 = new ModelRenderer(this, "LEGTOPROT1");
	LEGTOPROT1.setRotationPoint(-4F, 5F, 9F);
	setRotation(LEGTOPROT1, 0F, 0F, 0F);
	LEGTOPROT1.mirror = true;
	LEGTOPROT1.addBox("legtop1", -1F, 0F, -1F, 2, 7, 2);
	LEGBOTROT1 = new ModelRenderer(this, "LEGBOTROT1");
	LEGBOTROT1.setRotationPoint(0F, 7F, 0F);
	setRotation(LEGBOTROT1, 0F, 0F, 0F);
	LEGBOTROT1.mirror = true;
	LEGBOTROT1.addBox("legbot1", -1F, 0F, -1F, 2, 8, 2);
	FOOTROT1 = new ModelRenderer(this, "FOOTROT1");
	FOOTROT1.setRotationPoint(0F, 7.5F, 0F);
	setRotation(FOOTROT1, 0F, 0F, 0F);
	FOOTROT1.mirror = true;
	TOE12ROT = new ModelRenderer(this, "TOE12ROT");
	TOE12ROT.setRotationPoint(-0.5F, 0F, -1F);
	setRotation(TOE12ROT, 0F, 0F, 0F);
	TOE12ROT.mirror = true;
	TOE12ROT.addBox("toe12", -0.5F, -0.5F, -2F, 1, 1, 2);
	FOOTROT1.addChild(TOE12ROT);
	TOE13ROT = new ModelRenderer(this, "TOE13ROT");
	TOE13ROT.setRotationPoint(0F, 0F, 1F);
	setRotation(TOE13ROT, 0F, 0F, 0F);
	TOE13ROT.mirror = true;
	TOE13ROT.addBox("toe13", -0.5F, -0.5F, 0F, 1, 1, 2);
	FOOTROT1.addChild(TOE13ROT);
	TOE11ROT = new ModelRenderer(this, "TOE11ROT");
	TOE11ROT.setRotationPoint(0.5F, 0F, -1F);
	setRotation(TOE11ROT, 0F, 0F, 0F);
	TOE11ROT.mirror = true;
	TOE11ROT.addBox("toe11", -0.5F, -0.5F, -2F, 1, 1, 2);
	FOOTROT1.addChild(TOE11ROT);
	LEGBOTROT1.addChild(FOOTROT1);
	LEGTOPROT1.addChild(LEGBOTROT1);
	BODYROT.addChild(LEGTOPROT1);
	LEGTOPROT2 = new ModelRenderer(this, "LEGTOPROT2");
	LEGTOPROT2.setRotationPoint(4F, 5F, 9F);
	setRotation(LEGTOPROT2, 0F, 0F, 0F);
	LEGTOPROT2.mirror = true;
	LEGTOPROT2.addBox("legtop2", -1F, 0F, -1F, 2, 7, 2);
	LEGBOTROT2 = new ModelRenderer(this, "LEGBOTROT2");
	LEGBOTROT2.setRotationPoint(0F, 7F, 0F);
	setRotation(LEGBOTROT2, 0F, 0F, 0F);
	LEGBOTROT2.mirror = true;
	LEGBOTROT2.addBox("legbot2", -1F, 0F, -1F, 2, 8, 2);
	FOOTROT2 = new ModelRenderer(this, "FOOTROT2");
	FOOTROT2.setRotationPoint(0F, 7.5F, 0F);
	setRotation(FOOTROT2, 0F, 0F, 0F);
	FOOTROT2.mirror = true;
	TOE22ROT = new ModelRenderer(this, "TOE22ROT");
	TOE22ROT.setRotationPoint(-0.5F, 0F, -1F);
	setRotation(TOE22ROT, 0F, 0F, 0F);
	TOE22ROT.mirror = true;
	TOE22ROT.addBox("toe22", -0.5F, -0.5F, -2F, 1, 1, 2);
	FOOTROT2.addChild(TOE22ROT);
	TOE23ROT = new ModelRenderer(this, "TOE23ROT");
	TOE23ROT.setRotationPoint(0F, 0F, 1F);
	setRotation(TOE23ROT, 0F, 0F, 0F);
	TOE23ROT.mirror = true;
	TOE23ROT.addBox("toe23", -0.5F, -0.5F, 0F, 1, 1, 2);
	FOOTROT2.addChild(TOE23ROT);
	TOE21ROT = new ModelRenderer(this, "TOE21ROT");
	TOE21ROT.setRotationPoint(0.5F, 0F, -1F);
	setRotation(TOE21ROT, 0F, 0F, 0F);
	TOE21ROT.mirror = true;
	TOE21ROT.addBox("toe21", -0.5F, -0.5F, -2F, 1, 1, 2);
	FOOTROT2.addChild(TOE21ROT);
	LEGBOTROT2.addChild(FOOTROT2);
	LEGTOPROT2.addChild(LEGBOTROT2);
	BODYROT.addChild(LEGTOPROT2);
	WINGLEFROT = new ModelRenderer(this, "WINGLEFROT");
	WINGLEFROT.setRotationPoint(-5F, 4.5F, -7F);
	setRotation(WINGLEFROT, 0F, 0F, 0F);
	WINGLEFROT.mirror = true;
	WINGLEFROT.addBox("winglef", -1F, -5F, 0F, 1, 9, 15);
	WINGRIGROT = new ModelRenderer(this, "WINGRIGROT");
	WINGRIGROT.setRotationPoint(5F, 4.5F, -7F);
	setRotation(WINGRIGROT, 0F, 0F, 0F);
	WINGRIGROT.mirror = true;
	WINGRIGROT.addBox("wingrig", 0F, -5F, 0F, 1, 9, 15);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	super.render(entity, f, f1, f2, f3, f4, f5);
	setRotationAngles(f, f1, f2, f3, f4, f5, entity);

	float field_78145_g = 8.0F;
	float field_78151_h = 4.0F;

	if (this.isChild) {
	    float var8 = 2.0F;
	    GL11.glPushMatrix();
	    GL11.glTranslatef(0.0F, field_78145_g * f5, field_78151_h * f5);
	    // HEADROT.render(f5);
	    GL11.glPopMatrix();
	    GL11.glPushMatrix();
	    GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
	    GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
	    tail1.render(f5);
	    tail3.render(f5);
	    tail2.render(f5);
	    tail4.render(f5);
	    BODYROT.render(f5);
	    WINGLEFROT.render(f5);
	    WINGRIGROT.render(f5);
	    GL11.glPopMatrix();
	} else {
	    tail1.render(f5);
	    tail3.render(f5);
	    tail2.render(f5);
	    tail4.render(f5);
	    BODYROT.render(f5);
	    WINGLEFROT.render(f5);
	    WINGRIGROT.render(f5);
	}
    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
	TOE22ROT.rotateAngleY = (float) (45 * Math.PI / 180);
	TOE21ROT.rotateAngleY = (float) (-45 * Math.PI / 180);

	TOE12ROT.rotateAngleY = (float) (45 * Math.PI / 180);
	TOE11ROT.rotateAngleY = (float) (-45 * Math.PI / 180);

	float angle = par2 * 0.6625F * 2f;
	NECK1ROT.rotateAngleX = (float) ((-22.5 + MathHelper.cos(0)) * Math.PI / 180);
	NECK2ROT.rotateAngleX = (float) ((-22.5 + MathHelper.cos(angle) + 1) * Math.PI / 180);
	NECK3ROT.rotateAngleX = (float) ((-22.5 + MathHelper.cos(angle) + 1) * Math.PI / 180);
	NECK4ROT.rotateAngleX = (float) ((-22.5 + MathHelper.cos(angle) + 1) * Math.PI / 180);
	NECK5ROT.rotateAngleX = (float) ((-5.0 + MathHelper.cos(angle) + 1) * Math.PI / 180);
	NECK6ROT.rotateAngleX = (float) ((-1.0 + MathHelper.cos(angle) + 1) * Math.PI / 180);
	NECK7ROT.rotateAngleX = (float) ((-1.0 + MathHelper.cos(angle) + 1) * Math.PI / 180 * 1.2 * par3);
	NECK8ROT.rotateAngleX = (float) ((-1.0 + MathHelper.cos(angle) + 1) * Math.PI / 180);

	WINGLEFROT.rotateAngleY = (float) (-45.5 * Math.abs(MathHelper.cos(par2 * 0.6625f * 2f)) * Math.PI / 180);
	WINGRIGROT.rotateAngleY = (float) (+45.5 * Math.abs(MathHelper.cos(par2 * 0.6625f * 2f)) * Math.PI / 180);

	LEGTOPROT1.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F / 2 + (float) Math.PI) * 2.4F * ModelHelper.abs(Math
		.log(par3 + 1)));
	LEGBOTROT1.rotateAngleX = (float) Math.abs(MathHelper.cos(par2 * 0.6662F / 2 * 2 + (float) Math.PI / 2) * 2.4F
		* ModelHelper.abs(Math.log(par3 + 1)));

	LEGTOPROT2.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F / 2) * 2.4F * ModelHelper.abs(Math
		.log(par3 + 1)));
	LEGBOTROT2.rotateAngleX = (float) Math.abs(MathHelper.cos(par2 * 0.6662F / 2 * 2) * 2.4F
		* ModelHelper.abs(Math.log(par3 + 1)));

	super.setLivingAnimations(par1EntityLiving, par2, par3, par4);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
	model.rotateAngleX = x;
	model.rotateAngleY = y;
	model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {
	super.setRotationAngles(f, f1, f2, f3, f4, f5, par7Entity);
	float angle = f1 * 0.6625F * 2f;
	HEADROT.rotateAngleX = (float) ((+105 + MathHelper.cos(angle)) * Math.PI / 180)
		+ Math.min(Math.max(f4, -15), +15) * (float) (Math.PI / 180f);
	HEADROT.rotateAngleY = Math.min(Math.max(f3, -45), +45) * (float) (Math.PI / 180f);
    }
}
