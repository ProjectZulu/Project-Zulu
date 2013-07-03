package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import projectzulu.common.core.ModelHelper;
import projectzulu.common.mobs.entity.EntityMinotaur;
import projectzulu.common.mobs.entity.EntityStates;

public class ModelMinotaur extends ModelBase {
    ModelRenderer BODYROT;
    ModelRenderer LEGLEFTOPROT;
    ModelRenderer BACKBELT2ROT;
    ModelRenderer FRONTBELT2ROT;
    ModelRenderer LEGRIGTOPROT;

    private ModelRenderer HEADROT;
    private ModelRenderer ARMTOPRIGROT;
    private ModelRenderer ARMTOPLEFROT;
    private ModelRenderer BACKBELT3ROT;
    private ModelRenderer FRONTBELT3ROT;
    private ModelRenderer HORNLEF1ROT;
    private ModelRenderer HORNLEF2ROT;
    private ModelRenderer HORNLEF3ROT;
    private ModelRenderer HORNRIG1ROT;
    private ModelRenderer HORNRIG2ROT;
    private ModelRenderer HORNRIG3ROT;
    private ModelRenderer ARMBOTRIGROT;
    private ModelRenderer HANDROT;
    private ModelRenderer FINRIG2ROT;
    private ModelRenderer FINRIG1ROT;
    private ModelRenderer FINRIGROT3;
    private ModelRenderer ARMBOTLEFROT;
    private ModelRenderer HAMMERROT;
    private ModelRenderer AXEBLADE2;
    private ModelRenderer AXEBLADE3;
    private ModelRenderer HANDLEFROT;
    private ModelRenderer FINLEF2ROT;
    private ModelRenderer FINLEF1ROT;
    private ModelRenderer FINLEFROT3;
    private ModelRenderer FRONTBELT4ROT;
    private ModelRenderer LEGLEFBOTROT;
    private ModelRenderer LEGRIGBOTROT;

    public ModelMinotaur() {
	textureWidth = 128;
	textureHeight = 64;
	setTextureOffset("HEADROT.head", 0, 49);
	setTextureOffset("HEADROT.nose1", 30, 49);
	setTextureOffset("HEADROT.nose2", 30, 56);
	setTextureOffset("HORNLEF1ROT.hornlef1", 54, 58);
	setTextureOffset("HORNLEF2ROT.hornlef2", 50, 53);
	setTextureOffset("HORNLEF3ROT.hornlef3", 42, 60);
	setTextureOffset("HORNRIG1ROT.hornrig1", 54, 58);
	setTextureOffset("HORNRIG2ROT.hornrig2", 50, 53);
	setTextureOffset("HORNRIG3ROT.hornrig3", 42, 60);
	setTextureOffset("ARMTOPRIGROT.armrigtop", 36, 22);
	setTextureOffset("ARMBOTRIGROT.armrigbot", 36, 36);
	setTextureOffset("HANDROT.handrigbase", 38, 15);
	setTextureOffset("FINRIG2ROT.fingerrig2", 56, 22);
	setTextureOffset("FINRIG1ROT.fingerrig1", 56, 22);
	setTextureOffset("FINRIGROT3.fingerrig3", 54, 16);
	setTextureOffset("ARMTOPLEFROT.armleftop", 36, 22);
	setTextureOffset("ARMTOPLEFROT.armorshoulder1", 44, 8);
	setTextureOffset("ARMTOPLEFROT.armorshoulder2", 31, 7);
	setTextureOffset("ARMTOPLEFROT.armorshoulder3", 44, 8);
	setTextureOffset("ARMTOPLEFROT.armorshoulder4", 38, 0);
	setTextureOffset("ARMBOTLEFROT.armlefbot", 36, 36);
	setTextureOffset("HAMMERROT.axeback", 64, 0);
	setTextureOffset("HAMMERROT.axefront1", 64, 7);
	setTextureOffset("HAMMERROT.axefront2", 64, 14);
	setTextureOffset("HAMMERROT.axeblade1", 67, 22);
	setTextureOffset("HAMMERROT.axebase", 43, 30);
	setTextureOffset("AXEBLADE2.axeblade2", 67, 34);
	setTextureOffset("AXEBLADE3.axeblade3", 67, 34);
	setTextureOffset("HANDLEFROT.handlefbase", 38, 15);
	setTextureOffset("FINLEF2ROT.fingerlef2", 56, 22);
	setTextureOffset("FINLEF1ROT.fingerlef1", 56, 22);
	setTextureOffset("FINLEFROT3.fingerlef3", 54, 16);
	setTextureOffset("BODYROT.body", 0, 28);
	setTextureOffset("BODYROT.belt1", 14, 0);
	setTextureOffset("BODYROT.belt4", 0, 0);
	setTextureOffset("BODYROT.belt2", 0, 0);
	setTextureOffset("BODYROT.belt3", 14, 0);
	setTextureOffset("LEGLEFTOPROT.legleftop", 0, 17);
	setTextureOffset("LEGLEFBOTROT.footleffront1", 0, 11);
	setTextureOffset("LEGLEFBOTROT.footlefbase", 20, 20);
	setTextureOffset("LEGLEFBOTROT.leglefbot", 0, 17);
	setTextureOffset("LEGLEFBOTROT.footleffront2", 3, 12);
	setTextureOffset("LEGLEFBOTROT.footleffront3", 3, 12);
	setTextureOffset("LEGLEFBOTROT.footleffront4", 0, 11);
	setTextureOffset("LEGLEFBOTROT.footlefback2", 11, 14);
	setTextureOffset("LEGLEFBOTROT.footlefback1", 8, 11);
	setTextureOffset("BACKBELT2ROT.belt32", 16, 3);
	setTextureOffset("BACKBELT3ROT.belt33", 18, 6);
	setTextureOffset("FRONTBELT2ROT.belt12", 16, 3);
	setTextureOffset("FRONTBELT3ROT.belt13", 18, 6);
	setTextureOffset("FRONTBELT4ROT.belt14", 20, 9);
	setTextureOffset("LEGRIGTOPROT.legrigtop", 0, 17);
	setTextureOffset("LEGRIGBOTROT.footrigfront1", 0, 11);
	setTextureOffset("LEGRIGBOTROT.footrigbase", 20, 20);
	setTextureOffset("LEGRIGBOTROT.legrigbot", 0, 17);
	setTextureOffset("LEGRIGBOTROT.footrigfront2", 3, 12);
	setTextureOffset("LEGRIGBOTROT.footrigfront3", 3, 12);
	setTextureOffset("LEGRIGBOTROT.footrigfront4", 0, 11);
	setTextureOffset("LEGRIGBOTROT.footrigback2", 11, 14);
	setTextureOffset("LEGRIGBOTROT.footrigback1", 8, 11);

	BODYROT = new ModelRenderer(this, "BODYROT");
	BODYROT.setRotationPoint(0F, 10F, 0F);
	setRotation(BODYROT, 0F, 0F, 0F);
	BODYROT.mirror = true;
	HEADROT = new ModelRenderer(this, "HEADROT");
	HEADROT.setRotationPoint(0F, -15F, -1F);
	setRotation(HEADROT, 0F, 0F, 0F);
	HEADROT.mirror = true;
	HEADROT.addBox("head", -4F, -7F, -3F, 8, 8, 7);
	HEADROT.addBox("nose1", -2.5F, -3.5F, -6F, 5, 4, 3);
	HEADROT.addBox("nose2", -2F, -2.5F, -8F, 4, 3, 2);
	HORNLEF1ROT = new ModelRenderer(this, "HORNLEF1ROT");
	HORNLEF1ROT.setRotationPoint(-4F, -3F, 0.5F);
	setRotation(HORNLEF1ROT, 0F, 0F, 0F);
	HORNLEF1ROT.mirror = true;
	HORNLEF1ROT.addBox("hornlef1", -2F, -1.5F, -1.5F, 2, 3, 3);
	HORNLEF2ROT = new ModelRenderer(this, "HORNLEF2ROT");
	HORNLEF2ROT.setRotationPoint(-2F, 1.5F, 0F);
	setRotation(HORNLEF2ROT, 0F, 0F, 0.7853982F);
	HORNLEF2ROT.mirror = true;
	HORNLEF2ROT.addBox("hornlef2", -4F, -2F, -1.5F, 4, 2, 3);
	HORNLEF3ROT = new ModelRenderer(this, "HORNLEF3ROT");
	HORNLEF3ROT.setRotationPoint(-4F, 0F, 0F);
	setRotation(HORNLEF3ROT, 0F, 0F, 0.7504916F);
	HORNLEF3ROT.mirror = true;
	HORNLEF3ROT.addBox("hornlef3", -4F, -2F, -1F, 4, 2, 2);
	HORNLEF2ROT.addChild(HORNLEF3ROT);
	HORNLEF1ROT.addChild(HORNLEF2ROT);
	HEADROT.addChild(HORNLEF1ROT);
	HORNRIG1ROT = new ModelRenderer(this, "HORNRIG1ROT");
	HORNRIG1ROT.setRotationPoint(4F, -3F, 0.5F);
	setRotation(HORNRIG1ROT, 0F, 0F, 0F);
	HORNRIG1ROT.mirror = true;
	HORNRIG1ROT.addBox("hornrig1", 0F, -1.5F, -1.5F, 2, 3, 3);
	HORNRIG2ROT = new ModelRenderer(this, "HORNRIG2ROT");
	HORNRIG2ROT.setRotationPoint(2F, 1.5F, 0F);
	setRotation(HORNRIG2ROT, 0F, 0F, -0.7853982F);
	HORNRIG2ROT.mirror = true;
	HORNRIG2ROT.addBox("hornrig2", 0F, -2F, -1.5F, 4, 2, 3);
	HORNRIG3ROT = new ModelRenderer(this, "HORNRIG3ROT");
	HORNRIG3ROT.setRotationPoint(4F, 0F, 0F);
	setRotation(HORNRIG3ROT, 0F, 0F, -0.7504916F);
	HORNRIG3ROT.mirror = true;
	HORNRIG3ROT.addBox("hornrig3", 0F, -2F, -1F, 4, 2, 2);
	HORNRIG2ROT.addChild(HORNRIG3ROT);
	HORNRIG1ROT.addChild(HORNRIG2ROT);
	HEADROT.addChild(HORNRIG1ROT);
	BODYROT.addChild(HEADROT);
	ARMTOPRIGROT = new ModelRenderer(this, "ARMTOPRIGROT");
	ARMTOPRIGROT.setRotationPoint(8F, -11F, 0F);
	setRotation(ARMTOPRIGROT, 0F, 0F, 0F);
	ARMTOPRIGROT.mirror = true;
	ARMTOPRIGROT.addBox("armrigtop", -2.5F, -2F, -2.5F, 5, 9, 5);
	ARMBOTRIGROT = new ModelRenderer(this, "ARMBOTRIGROT");
	ARMBOTRIGROT.setRotationPoint(0F, 7F, 0F);
	setRotation(ARMBOTRIGROT, 0F, 0F, 0F);
	ARMBOTRIGROT.mirror = true;
	ARMBOTRIGROT.addBox("armrigbot", -2.5F, 0F, -2.5F, 5, 8, 5);
	HANDROT = new ModelRenderer(this, "HANDROT");
	HANDROT.setRotationPoint(1.5F, 8F, 0F);
	setRotation(HANDROT, 0F, 0F, 0F);
	HANDROT.mirror = true;
	HANDROT.addBox("handrigbase", -1F, 0F, -2.5F, 2, 2, 5);
	FINRIG2ROT = new ModelRenderer(this, "FINRIG2ROT");
	FINRIG2ROT.setRotationPoint(0F, 3F, 0F);
	setRotation(FINRIG2ROT, 0F, 0F, 0F);
	FINRIG2ROT.mirror = true;
	FINRIG2ROT.addBox("fingerrig2", -1F, -1F, 0.5F, 2, 3, 2);
	HANDROT.addChild(FINRIG2ROT);
	FINRIG1ROT = new ModelRenderer(this, "FINRIG1ROT");
	FINRIG1ROT.setRotationPoint(0F, 3F, 0F);
	setRotation(FINRIG1ROT, 0F, 0F, 0F);
	FINRIG1ROT.mirror = true;
	FINRIG1ROT.addBox("fingerrig1", -1F, -1F, -2.5F, 2, 3, 2);
	HANDROT.addChild(FINRIG1ROT);
	ARMBOTRIGROT.addChild(HANDROT);
	FINRIGROT3 = new ModelRenderer(this, "FINRIGROT3");
	FINRIGROT3.setRotationPoint(-2F, 8F, 0F);
	setRotation(FINRIGROT3, 0F, 0F, 0F);
	FINRIGROT3.mirror = true;
	FINRIGROT3.addBox("fingerrig3", -0.5F, 0F, -2F, 1, 2, 4);
	ARMBOTRIGROT.addChild(FINRIGROT3);
	ARMTOPRIGROT.addChild(ARMBOTRIGROT);
	BODYROT.addChild(ARMTOPRIGROT);
	ARMTOPLEFROT = new ModelRenderer(this, "ARMTOPLEFROT");
	ARMTOPLEFROT.setRotationPoint(-8F, -11F, 0F);
	setRotation(ARMTOPLEFROT, 0F, 0F, 0F);
	ARMTOPLEFROT.mirror = true;
	ARMTOPLEFROT.addBox("armleftop", -2.5F, -2F, -2.5F, 5, 9, 5);
	ARMTOPLEFROT.addBox("armorshoulder1", -3.5F, -2F, -3.5F, 6, 4, 1);
	ARMTOPLEFROT.addBox("armorshoulder2", -3.5F, -2F, -2.5F, 1, 4, 5);
	ARMTOPLEFROT.addBox("armorshoulder3", -3.5F, -2F, 2.5F, 6, 4, 1);
	ARMTOPLEFROT.addBox("armorshoulder4", -3.5F, -3F, -3.5F, 6, 1, 7);
	ARMBOTLEFROT = new ModelRenderer(this, "ARMBOTLEFROT");
	ARMBOTLEFROT.setRotationPoint(0F, 7F, 0F);
	setRotation(ARMBOTLEFROT, 0F, 0F, 0F);
	ARMBOTLEFROT.mirror = true;
	ARMBOTLEFROT.addBox("armlefbot", -2.5F, 0F, -2.5F, 5, 8, 5);
	HAMMERROT = new ModelRenderer(this, "HAMMERROT");
	HAMMERROT.setRotationPoint(0.5F, 9F, 0F);
	setRotation(HAMMERROT, 0F, 0F, 0F);
	HAMMERROT.mirror = true;
	HAMMERROT.addBox("axeback", -1.5F, -3F, -15.5F, 3, 2, 5);
	HAMMERROT.addBox("axefront1", -1F, 1F, -15.5F, 2, 2, 5);
	HAMMERROT.addBox("axefront2", -1.5F, 3F, -16F, 3, 2, 6);
	HAMMERROT.addBox("axeblade1", -0.5F, 5F, -18F, 1, 2, 10);
	HAMMERROT.addBox("axebase", -1F, -1F, -16F, 2, 2, 20);
	AXEBLADE2 = new ModelRenderer(this, "AXEBLADE2");
	AXEBLADE2.setRotationPoint(0F, 6.5F, -18F);
	setRotation(AXEBLADE2, -0.5235988F, 0F, 0F);
	AXEBLADE2.mirror = true;
	AXEBLADE2.addBox("axeblade2", -0.5F, -1.5F, -4F, 1, 2, 4);
	HAMMERROT.addChild(AXEBLADE2);
	AXEBLADE3 = new ModelRenderer(this, "AXEBLADE3");
	AXEBLADE3.setRotationPoint(0F, 6.5F, -8.5F);
	setRotation(AXEBLADE3, 0.5235988F, 0F, 0F);
	AXEBLADE3.mirror = true;
	AXEBLADE3.addBox("axeblade3", -0.5F, -1.5F, 0F, 1, 2, 4);
	HAMMERROT.addChild(AXEBLADE3);
	ARMBOTLEFROT.addChild(HAMMERROT);
	HANDLEFROT = new ModelRenderer(this, "HANDLEFROT");
	HANDLEFROT.setRotationPoint(-1.5F, 8F, 0F);
	setRotation(HANDLEFROT, 0F, 0F, 0F);
	HANDLEFROT.mirror = true;
	HANDLEFROT.addBox("handlefbase", -1F, 0F, -2.5F, 2, 2, 5);
	FINLEF2ROT = new ModelRenderer(this, "FINLEF2ROT");
	FINLEF2ROT.setRotationPoint(0F, 3F, 0F);
	setRotation(FINLEF2ROT, 0F, 0F, 0F);
	FINLEF2ROT.mirror = true;
	FINLEF2ROT.addBox("fingerlef2", -1F, -1F, 0.5F, 2, 3, 2);
	HANDLEFROT.addChild(FINLEF2ROT);
	FINLEF1ROT = new ModelRenderer(this, "FINLEF1ROT");
	FINLEF1ROT.setRotationPoint(0F, 3F, 0F);
	setRotation(FINLEF1ROT, 0F, 0F, 0F);
	FINLEF1ROT.mirror = true;
	FINLEF1ROT.addBox("fingerlef1", -1F, -1F, -2.5F, 2, 3, 2);
	HANDLEFROT.addChild(FINLEF1ROT);
	ARMBOTLEFROT.addChild(HANDLEFROT);
	FINLEFROT3 = new ModelRenderer(this, "FINLEFROT3");
	FINLEFROT3.setRotationPoint(2F, 8F, 0F);
	setRotation(FINLEFROT3, 0F, 0F, 0F);
	FINLEFROT3.mirror = true;
	FINLEFROT3.addBox("fingerlef3", -0.5F, 0F, -2F, 1, 2, 4);
	ARMBOTLEFROT.addChild(FINLEFROT3);
	ARMTOPLEFROT.addChild(ARMBOTLEFROT);
	BODYROT.addChild(ARMTOPLEFROT);
	BODYROT.addBox("body", -5.5F, -14F, -3.5F, 11, 14, 7);
	BODYROT.addBox("belt1", -5.5F, -2F, -4.5F, 11, 2, 1);
	BODYROT.addBox("belt4", 5.5F, -2F, -4.5F, 1, 2, 9);
	BODYROT.addBox("belt2", -6.5F, -2F, -4.5F, 1, 2, 9);
	BODYROT.addBox("belt3", -5.5F, -2F, 3.5F, 11, 2, 1);
	LEGLEFTOPROT = new ModelRenderer(this, "LEGLEFTOPROT");
	LEGLEFTOPROT.setRotationPoint(-3F, 10F, 0F);
	setRotation(LEGLEFTOPROT, 0F, 0F, 0F);
	LEGLEFTOPROT.mirror = true;
	LEGLEFTOPROT.addBox("legleftop", -2.5F, 0F, -2.5F, 5, 6, 5);
	LEGLEFBOTROT = new ModelRenderer(this, "LEGLEFBOTROT");
	LEGLEFBOTROT.setRotationPoint(0F, 6F, 0F);
	setRotation(LEGLEFBOTROT, 0F, 0F, 0F);
	LEGLEFBOTROT.mirror = true;
	LEGLEFBOTROT.addBox("footleffront1", -2.5F, 7F, -4.5F, 1, 1, 2);
	LEGLEFBOTROT.addBox("footlefbase", -2.5F, 6F, -2.5F, 5, 2, 5);
	LEGLEFBOTROT.addBox("leglefbot", -2.5F, 0F, -2.5F, 5, 6, 5);
	LEGLEFBOTROT.addBox("footleffront2", -1.5F, 6F, -5F, 1, 2, 3);
	LEGLEFBOTROT.addBox("footleffront3", 0.5F, 6F, -5F, 1, 2, 3);
	LEGLEFBOTROT.addBox("footleffront4", 1.5F, 7F, -4.5F, 1, 1, 2);
	LEGLEFBOTROT.addBox("footlefback2", -1.5F, 7F, 3.5F, 3, 1, 1);
	LEGLEFBOTROT.addBox("footlefback1", -2F, 6F, 2.5F, 4, 2, 1);
	LEGLEFTOPROT.addChild(LEGLEFBOTROT);
	BACKBELT2ROT = new ModelRenderer(this, "BACKBELT2ROT");
	BACKBELT2ROT.setRotationPoint(0F, 10F, 4F);
	setRotation(BACKBELT2ROT, 0F, 0F, 0F);
	BACKBELT2ROT.mirror = true;
	BACKBELT2ROT.addBox("belt32", -4.5F, 0F, -0.5F, 9, 2, 1);
	BACKBELT3ROT = new ModelRenderer(this, "BACKBELT3ROT");
	BACKBELT3ROT.setRotationPoint(0F, 2F, 0F);
	setRotation(BACKBELT3ROT, 0F, 0F, 0F);
	BACKBELT3ROT.mirror = true;
	BACKBELT3ROT.addBox("belt33", -3.5F, 0F, -0.5F, 7, 2, 1);
	BACKBELT2ROT.addChild(BACKBELT3ROT);
	FRONTBELT2ROT = new ModelRenderer(this, "FRONTBELT2ROT");
	FRONTBELT2ROT.setRotationPoint(0F, 10F, -4F);
	setRotation(FRONTBELT2ROT, 0F, 0F, 0F);
	FRONTBELT2ROT.mirror = true;
	FRONTBELT2ROT.addBox("belt12", -4.5F, 0F, -0.5F, 9, 2, 1);
	FRONTBELT3ROT = new ModelRenderer(this, "FRONTBELT3ROT");
	FRONTBELT3ROT.setRotationPoint(0F, 2F, 0F);
	setRotation(FRONTBELT3ROT, 0F, 0F, 0F);
	FRONTBELT3ROT.mirror = true;
	FRONTBELT3ROT.addBox("belt13", -3.5F, 0F, -0.5F, 7, 2, 1);
	FRONTBELT4ROT = new ModelRenderer(this, "FRONTBELT4ROT");
	FRONTBELT4ROT.setRotationPoint(0F, 2F, 0F);
	setRotation(FRONTBELT4ROT, 0F, 0F, 0F);
	FRONTBELT4ROT.mirror = true;
	FRONTBELT4ROT.addBox("belt14", -2.5F, 0F, -0.5F, 5, 2, 1);
	FRONTBELT3ROT.addChild(FRONTBELT4ROT);
	FRONTBELT2ROT.addChild(FRONTBELT3ROT);
	LEGRIGTOPROT = new ModelRenderer(this, "LEGRIGTOPROT");
	LEGRIGTOPROT.setRotationPoint(3F, 10F, 0F);
	setRotation(LEGRIGTOPROT, 0F, 0F, 0F);
	LEGRIGTOPROT.mirror = true;
	LEGRIGTOPROT.addBox("legrigtop", -2.5F, 0F, -2.5F, 5, 6, 5);
	LEGRIGBOTROT = new ModelRenderer(this, "LEGRIGBOTROT");
	LEGRIGBOTROT.setRotationPoint(0F, 6F, 0F);
	setRotation(LEGRIGBOTROT, 0F, 0F, 0F);
	LEGRIGBOTROT.mirror = true;
	LEGRIGBOTROT.addBox("footrigfront1", -2.5F, 7F, -4.5F, 1, 1, 2);
	LEGRIGBOTROT.addBox("footrigbase", -2.5F, 6F, -2.5F, 5, 2, 5);
	LEGRIGBOTROT.addBox("legrigbot", -2.5F, 0F, -2.5F, 5, 6, 5);
	LEGRIGBOTROT.addBox("footrigfront2", -1.5F, 6F, -5F, 1, 2, 3);
	LEGRIGBOTROT.addBox("footrigfront3", 0.5F, 6F, -5F, 1, 2, 3);
	LEGRIGBOTROT.addBox("footrigfront4", 1.5F, 7F, -4.5F, 1, 1, 2);
	LEGRIGBOTROT.addBox("footrigback2", -1.5F, 7F, 3.5F, 3, 1, 1);
	LEGRIGBOTROT.addBox("footrigback1", -2F, 6F, 2.5F, 4, 2, 1);
	LEGRIGTOPROT.addChild(LEGRIGBOTROT);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	super.render(entity, f, f1, f2, f3, f4, f5);
	setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	float field_78145_g = 11.0f;
	float field_78151_h = 9.0f;

	if (this.isChild) {
	    float var8 = 2.0F;
	    GL11.glPushMatrix();
	    GL11.glScalef(1.3F / var8, 1.3F / var8, 1.3F / var8);
	    GL11.glTranslatef(0.0F, field_78145_g * f5, field_78151_h * f5);
	    //
	    GL11.glPopMatrix();
	    GL11.glPushMatrix();
	    GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
	    GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
	    BODYROT.render(f5);
	    LEGLEFTOPROT.render(f5);
	    BACKBELT2ROT.render(f5);
	    FRONTBELT2ROT.render(f5);
	    LEGRIGTOPROT.render(f5);
	    GL11.glPopMatrix();
	} else {
	    BODYROT.render(f5);
	    LEGLEFTOPROT.render(f5);
	    BACKBELT2ROT.render(f5);
	    FRONTBELT2ROT.render(f5);
	    LEGRIGTOPROT.render(f5);
	}

    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {

	EntityMinotaur var5 = (EntityMinotaur) par1EntityLiving;

	int max = 30;
	int attackCounter = max - var5.ticksExisted % max;
	/* Constant Animation Rotations */

	/* State Based Animations */
	LEGLEFTOPROT.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper
		.abs(Math.log(par3 + 1)));
	LEGLEFBOTROT.rotateAngleX = (float) Math.abs(MathHelper.cos(par2 * 0.6662F / 2 + (float) Math.PI) * 1.8F
		* ModelHelper.abs(Math.log(par3 + 1)));

	LEGRIGTOPROT.rotateAngleX = (float) (MathHelper.cos(par2 * 0.6662F) * 1.8F * ModelHelper
		.abs(Math.log(par3 + 1)));
	LEGRIGBOTROT.rotateAngleX = (float) Math.abs(MathHelper.cos(par2 * 0.6662F / 2) * 1.8F
		* ModelHelper.abs(Math.log(par3 + 1)));

	FRONTBELT2ROT.rotateAngleX = Math.min(Math.min(LEGLEFTOPROT.rotateAngleX, LEGRIGTOPROT.rotateAngleX), 0);
	BACKBELT2ROT.rotateAngleX = Math.max(Math.max(LEGLEFTOPROT.rotateAngleX, LEGRIGTOPROT.rotateAngleX), 0);

	FINRIG2ROT.rotateAngleZ = (float) (90 * Math.PI / 180);
	FINRIG1ROT.rotateAngleZ = (float) (90 * Math.PI / 180);
	FINLEF1ROT.rotateAngleZ = (float) (-90 * Math.PI / 180);
	FINLEF2ROT.rotateAngleZ = (float) (-90 * Math.PI / 180);

	BODYROT.rotateAngleX = (float) (5 * Math.PI / 180);

	if (var5.getEntityState() == EntityStates.attacking) {
	    ARMTOPLEFROT.rotateAngleX = (float) (-90 * Math.PI / 180 * MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * par3);
	    ARMTOPLEFROT.rotateAngleY = (float) (0 * Math.PI / 180);
	    ARMTOPLEFROT.rotateAngleZ = (float) (6 * Math.PI / 180);

	    ARMBOTLEFROT.rotateAngleX = (float) (-40 * Math.PI / 180);
	    ARMBOTLEFROT.rotateAngleY = (float) (0 * Math.PI / 180);
	    ARMBOTLEFROT.rotateAngleZ = (float) (0 * Math.PI / 180);

	    ARMTOPRIGROT.rotateAngleX = (float) (-90 * Math.PI / 180 * MathHelper.cos(par2 * 0.6662F) * par3);
	    ARMTOPRIGROT.rotateAngleY = (float) (0 * Math.PI / 180);
	    ARMTOPRIGROT.rotateAngleZ = (float) (-10 * Math.PI / 180);

	    ARMBOTRIGROT.rotateAngleX = (float) (-30 * Math.PI / 180);
	    ARMBOTRIGROT.rotateAngleY = (float) (0 * Math.PI / 180);
	    ARMBOTRIGROT.rotateAngleZ = (float) (0 * Math.PI / 180);
	} else {
	    ARMTOPLEFROT.rotateAngleX = (float) (90 * Math.PI / 180 * MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * par3);
	    ARMTOPLEFROT.rotateAngleY = (float) (0 * Math.PI / 180);
	    ARMTOPLEFROT.rotateAngleZ = (float) (10 * Math.PI / 180);

	    ARMBOTLEFROT.rotateAngleX = (float) (-30 * Math.PI / 180);
	    ARMBOTLEFROT.rotateAngleY = (float) (0 * Math.PI / 180);
	    ARMBOTLEFROT.rotateAngleZ = (float) (0 * Math.PI / 180);

	    ARMTOPRIGROT.rotateAngleX = (float) (90 * Math.PI / 180 * MathHelper.cos(par2 * 0.6662F) * par3);
	    ARMTOPRIGROT.rotateAngleY = (float) (0 * Math.PI / 180);
	    ARMTOPRIGROT.rotateAngleZ = (float) (-10 * Math.PI / 180);

	    ARMBOTRIGROT.rotateAngleX = (float) (-30 * Math.PI / 180);
	    ARMBOTRIGROT.rotateAngleY = (float) (0 * Math.PI / 180);
	    ARMBOTRIGROT.rotateAngleZ = (float) (0 * Math.PI / 180);
	}

	if (var5.getAnimTime() > 0 && var5.getEntityState() == (EntityStates.attacking)) {
	    int animState = 0;
	    if (animState == 0) {
		/* Two Handed Attack Anim */
		BODYROT.rotateAngleX = ModelHelper.mapValueWithClamp(var5.getAnimTime(), var5.maxAnimTime, 10,
			(float) (5 * Math.PI / 180), (float) (20 * Math.PI / 180));
		ARMTOPLEFROT.rotateAngleX = ModelHelper.mapValueWithClamp(var5.getAnimTime(), var5.maxAnimTime, 10,
			(float) (165 * Math.PI / 180), (float) (350 * Math.PI / 180));
		ARMTOPLEFROT.rotateAngleY = (float) (0 * Math.PI / 180);
		ARMTOPLEFROT.rotateAngleZ = (float) (5 * Math.PI / 180);

		ARMBOTLEFROT.rotateAngleX = (float) (0 * Math.PI / 180);
		ARMBOTLEFROT.rotateAngleY = (float) (0 * Math.PI / 180);
		ARMBOTLEFROT.rotateAngleZ = (float) (-30 * Math.PI / 180);

		ARMTOPRIGROT.rotateAngleX = ModelHelper.mapValueWithClamp(var5.getAnimTime(), var5.maxAnimTime, 10,
			(float) (165 * Math.PI / 180), (float) (350 * Math.PI / 180));
		ARMTOPRIGROT.rotateAngleY = (float) (0 * Math.PI / 180);
		ARMTOPRIGROT.rotateAngleZ = (float) (-5 * Math.PI / 180);

		ARMBOTRIGROT.rotateAngleX = (float) (0 * Math.PI / 180);
		ARMBOTRIGROT.rotateAngleY = (float) (0 * Math.PI / 180);
		ARMBOTRIGROT.rotateAngleZ = (float) (+30 * Math.PI / 180);

		HAMMERROT.rotateAngleX = (float) (0 * Math.PI / 180);
		HAMMERROT.rotateAngleY = (float) (0 * Math.PI / 180);
		HAMMERROT.rotateAngleZ = (float) (+20 * Math.PI / 180);
	    } else {
		ARMTOPLEFROT.rotateAngleX = ModelHelper.mapValueWithClamp(attackCounter, max, 10, (float) (-60
			* Math.PI / 180), (float) (-10 * Math.PI / 180));
		ARMTOPLEFROT.rotateAngleY = ModelHelper.mapValueWithClamp(attackCounter, max, 0,
			(float) (0 * Math.PI / 180), (float) (0 * Math.PI / 180));
		ARMTOPLEFROT.rotateAngleZ = ModelHelper.mapValueWithClamp(attackCounter, max, 0,
			(float) (0 * Math.PI / 180), (float) (0 * Math.PI / 180));

		ARMBOTLEFROT.rotateAngleX = ModelHelper.mapValueWithClamp(attackCounter, max, 10, (float) (-90
			* Math.PI / 180), (float) (0 * Math.PI / 180));
		ARMBOTLEFROT.rotateAngleY = ModelHelper.mapValueWithClamp(attackCounter, max, 0,
			(float) (0 * Math.PI / 180), (float) (0 * Math.PI / 180));
		ARMBOTLEFROT.rotateAngleZ = ModelHelper.mapValueWithClamp(attackCounter, max, 0,
			(float) (0 * Math.PI / 180), (float) (0 * Math.PI / 180));

	    }

	}

	/*
	 * Blocking Animation ARMTOPLEFROT.rotateAngleX = (float) (-20*Math.PI/180); ARMTOPLEFROT.rotateAngleY = (float)
	 * (+10*Math.PI/180); ARMTOPLEFROT.rotateAngleZ = (float) (15*Math.PI/180);
	 * 
	 * ARMBOTLEFROT.rotateAngleX = (float) (-80*Math.PI/180); ARMBOTLEFROT.rotateAngleY = (float) (-30*Math.PI/180);
	 * ARMBOTLEFROT.rotateAngleZ = (float) (+40*Math.PI/180);
	 * 
	 * ARMTOPRIGROT.rotateAngleX = (float) (-30*Math.PI/180); ARMTOPRIGROT.rotateAngleY = (float) (-15*Math.PI/180);
	 * ARMTOPRIGROT.rotateAngleZ = (float) (5*Math.PI/180);
	 * 
	 * ARMBOTRIGROT.rotateAngleX = (float) (-60*Math.PI/180); ARMBOTRIGROT.rotateAngleY = (float) (+45*Math.PI/180);
	 * ARMBOTRIGROT.rotateAngleZ = (float) (+30*Math.PI/180);
	 */
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
	HEADROT.rotateAngleX = Math.min(Math.max(f4, -15), +15) * (float) (Math.PI / 180f);
	HEADROT.rotateAngleY = Math.min(Math.max(f3, -45), +45) * (float) (Math.PI / 180f);
    }
}
