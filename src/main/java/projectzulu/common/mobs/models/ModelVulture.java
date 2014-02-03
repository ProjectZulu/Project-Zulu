package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import org.lwjgl.opengl.GL11;

import projectzulu.common.mobs.entity.EntityStates;
import projectzulu.common.mobs.entity.EntityVulture;

public class ModelVulture extends ModelBase {
    ModelRenderer FEETROT;
    ModelRenderer BODYROT;

    ModelRenderer rightfootfronttal2;
    ModelRenderer rightfootfronttal1;
    ModelRenderer rigfootbacktal;
    ModelRenderer rightfootbase;
    ModelRenderer rightfoottoe1;
    ModelRenderer rightfoottoe2;
    ModelRenderer rightleg;

    ModelRenderer leftfootfronttal2;
    ModelRenderer leftfootfronttal1;
    ModelRenderer leftfootbacktal;
    ModelRenderer leftfootbase;
    ModelRenderer leftfoottoe1;
    ModelRenderer leftfoottoe2;
    ModelRenderer leftleg;

    ModelRenderer LEFTWING;
    ModelRenderer wingleft4;
    ModelRenderer wingleft3;
    ModelRenderer wingleft2;
    ModelRenderer wingleft1;

    ModelRenderer RIGTHWING;
    ModelRenderer wingrig4;
    ModelRenderer wingrig3;
    ModelRenderer wingrig2;
    ModelRenderer wingrig1;

    ModelRenderer NECKROT1;
    ModelRenderer NECKROT2;
    ModelRenderer NECKROT3;

    ModelRenderer neck1;
    ModelRenderer neck2;
    ModelRenderer neck3;

    ModelRenderer HEADROT;
    ModelRenderer mouthbot;
    ModelRenderer mouthtop;
    ModelRenderer mouthtal;
    ModelRenderer head;

    ModelRenderer TAILROT;
    ModelRenderer tailbase1;
    ModelRenderer tailbase2;

    ModelRenderer neckfrill;
    ModelRenderer chestpuff;
    ModelRenderer Body;

    public ModelVulture() {
	textureWidth = 64;
	textureHeight = 32;
	float xCorrection = -1.0f;

	FEETROT = new ModelRenderer(this, "FEETROT");
	FEETROT.setRotationPoint(0.5F - xCorrection, 18F, -6F);
	setRotation(FEETROT, 0F, 0F, 0F);
	FEETROT.mirror = true;
	rightfootfronttal2 = new ModelRenderer(this, 39, 4);
	rightfootfronttal2.addBox(-2F, -7F, -1.5F, 1, 1, 1);
	FEETROT.addChild(rightfootfronttal2);
	rightfootfronttal2.setRotationPoint(2.5F, 12F, -1F);
	rightfootfronttal2.setTextureSize(64, 32);
	rightfootfronttal2.mirror = true;
	setRotation(rightfootfronttal2, 0F, 0F, 0F);
	rightfootfronttal1 = new ModelRenderer(this, 39, 4);
	rightfootfronttal1.addBox(-2F, -7F, -1.5F, 1, 1, 1);
	FEETROT.addChild(rightfootfronttal1);
	rightfootfronttal1.setRotationPoint(4.5F, 12F, -1F);
	rightfootfronttal1.setTextureSize(64, 32);
	rightfootfronttal1.mirror = true;
	setRotation(rightfootfronttal1, 0F, 0F, 0F);
	leftfootfronttal2 = new ModelRenderer(this, 39, 4);
	leftfootfronttal2.addBox(-2F, -7F, -1.5F, 1, 1, 1);
	FEETROT.addChild(leftfootfronttal2);
	leftfootfronttal2.setRotationPoint(-1.5F, 12F, -1F);
	leftfootfronttal2.setTextureSize(64, 32);
	leftfootfronttal2.mirror = true;
	setRotation(leftfootfronttal2, 0F, 0F, 0F);
	leftfootfronttal1 = new ModelRenderer(this, 39, 4);
	leftfootfronttal1.addBox(-2F, -7F, -1.5F, 1, 1, 1);
	FEETROT.addChild(leftfootfronttal1);
	leftfootfronttal1.setRotationPoint(0.5F, 12F, -1F);
	leftfootfronttal1.setTextureSize(64, 32);
	leftfootfronttal1.mirror = true;
	setRotation(leftfootfronttal1, 0F, 0F, 0F);
	leftfootbacktal = new ModelRenderer(this, 39, 4);
	leftfootbacktal.addBox(-2F, -7F, -1.5F, 1, 1, 1);
	FEETROT.addChild(leftfootbacktal);
	leftfootbacktal.setRotationPoint(-0.5F, 11F, 2F);
	leftfootbacktal.setTextureSize(64, 32);
	leftfootbacktal.mirror = true;
	setRotation(leftfootbacktal, 0F, 0F, 0F);
	rightfoottoe2 = new ModelRenderer(this, 32, 3);
	rightfoottoe2.addBox(-2F, -7F, -1.5F, 1, 1, 1);
	FEETROT.addChild(rightfoottoe2);
	rightfoottoe2.setRotationPoint(2.5F, 11F, -1F);
	rightfoottoe2.setTextureSize(64, 32);
	rightfoottoe2.mirror = true;
	setRotation(rightfoottoe2, 0F, 0F, 0F);
	leftfoottoe2 = new ModelRenderer(this, 32, 3);
	leftfoottoe2.addBox(-2F, -7F, -1.5F, 1, 1, 1);
	FEETROT.addChild(leftfoottoe2);
	leftfoottoe2.setRotationPoint(-1.5F, 11F, -1F);
	leftfoottoe2.setTextureSize(64, 32);
	leftfoottoe2.mirror = true;
	setRotation(leftfoottoe2, 0F, 0F, 0F);
	leftfootbase = new ModelRenderer(this, 32, 0);
	leftfootbase.addBox(-2F, -7F, -1.5F, 3, 1, 2);
	FEETROT.addChild(leftfootbase);
	leftfootbase.setRotationPoint(-1.5F, 11F, 0F);
	leftfootbase.setTextureSize(64, 32);
	leftfootbase.mirror = true;
	setRotation(leftfootbase, 0F, 0F, 0F);
	leftfoottoe1 = new ModelRenderer(this, 32, 3);
	leftfoottoe1.addBox(-2F, -7F, -1.5F, 1, 1, 1);
	FEETROT.addChild(leftfoottoe1);
	leftfoottoe1.setRotationPoint(0.5F, 11F, -1F);
	leftfoottoe1.setTextureSize(64, 32);
	leftfoottoe1.mirror = true;
	setRotation(leftfoottoe1, 0F, 0F, 0F);
	rigfootbacktal = new ModelRenderer(this, 39, 4);
	rigfootbacktal.addBox(-2F, -7F, -1.5F, 1, 1, 1);
	FEETROT.addChild(rigfootbacktal);
	rigfootbacktal.setRotationPoint(3.5F, 11F, 2F);
	rigfootbacktal.setTextureSize(64, 32);
	rigfootbacktal.mirror = true;
	setRotation(rigfootbacktal, 0F, 0F, 0F);
	rightfoottoe1 = new ModelRenderer(this, 32, 3);
	rightfoottoe1.addBox(-2F, -7F, -1.5F, 1, 1, 1);
	FEETROT.addChild(rightfoottoe1);
	rightfoottoe1.setRotationPoint(4.5F, 11F, -1F);
	rightfoottoe1.setTextureSize(64, 32);
	rightfoottoe1.mirror = true;
	setRotation(rightfoottoe1, 0F, 0F, 0F);
	rightfootbase = new ModelRenderer(this, 32, 0);
	rightfootbase.addBox(-2F, -7F, -1.5F, 3, 1, 2);
	FEETROT.addChild(rightfootbase);
	rightfootbase.setRotationPoint(2.5F, 11F, 0F);
	rightfootbase.setTextureSize(64, 32);
	rightfootbase.mirror = true;
	setRotation(rightfootbase, 0F, 0F, 0F);
	rightleg = new ModelRenderer(this, 20, 27);
	rightleg.addBox(-2F, -7F, -1.5F, 1, 4, 1);
	FEETROT.addChild(rightleg);
	rightleg.setRotationPoint(3.5F, 7F, 1F);
	rightleg.setTextureSize(64, 32);
	rightleg.mirror = true;
	setRotation(rightleg, 0F, 0F, 0F);
	leftleg = new ModelRenderer(this, 16, 27);
	leftleg.addBox(-2F, -7F, -1.5F, 1, 4, 1);
	FEETROT.addChild(leftleg);
	leftleg.setRotationPoint(-0.5F, 7F, 1F);
	leftleg.setTextureSize(64, 32);
	leftleg.mirror = true;
	setRotation(leftleg, 0F, 0F, 0F);
	BODYROT = new ModelRenderer(this, "BODYROT");
	BODYROT.setRotationPoint(0.5F - xCorrection, 17.5F, -6F);
	setRotation(BODYROT, 0F, 0F, 0F);
	BODYROT.mirror = true;

	LEFTWING = new ModelRenderer(this, "LEFTWING");
	LEFTWING.setRotationPoint(-1.5F, -6F, 0F);
	BODYROT.addChild(LEFTWING);
	setRotation(LEFTWING, 0F, 0F, 0F);
	LEFTWING.mirror = true;
	wingleft4 = new ModelRenderer(this, 16, 18);
	wingleft4.addBox(-2F, -7F, -1.5F, 1, 2, 6);
	LEFTWING.addChild(wingleft4);
	wingleft4.setRotationPoint(0F, 12.5F, 0F);
	wingleft4.setTextureSize(64, 32);
	wingleft4.mirror = true;
	setRotation(wingleft4, 0F, 0F, 0F);
	wingleft3 = new ModelRenderer(this, 16, 11);
	wingleft3.addBox(-2F, -7F, -1.5F, 1, 2, 5);
	LEFTWING.addChild(wingleft3);
	wingleft3.setRotationPoint(0F, 10.5F, 0F);
	wingleft3.setTextureSize(64, 32);
	wingleft3.mirror = true;
	setRotation(wingleft3, 0F, 0F, 0F);
	wingleft2 = new ModelRenderer(this, 16, 5);
	wingleft2.addBox(-2F, -7F, -1.5F, 1, 2, 4);
	LEFTWING.addChild(wingleft2);
	wingleft2.setRotationPoint(0F, 8.5F, 0F);
	wingleft2.setTextureSize(64, 32);
	wingleft2.mirror = true;
	setRotation(wingleft2, 0F, 0F, 0F);
	wingleft1 = new ModelRenderer(this, 16, 0);
	wingleft1.addBox(-2F, -7F, -1.5F, 1, 2, 3);
	LEFTWING.addChild(wingleft1);
	wingleft1.setRotationPoint(0F, 6.5F, 0F);
	wingleft1.setTextureSize(64, 32);
	wingleft1.mirror = true;
	setRotation(wingleft1, 0F, 0F, 0F);

	RIGTHWING = new ModelRenderer(this, "RIGTHWING");
	RIGTHWING.setRotationPoint(2F, -6F, 0F);
	BODYROT.addChild(RIGTHWING);
	setRotation(RIGTHWING, 0F, 0F, 0F);
	RIGTHWING.mirror = true;
	wingrig4 = new ModelRenderer(this, 16, 18);
	wingrig4.addBox(-2F, -7F, -1.5F, 1, 2, 6);
	RIGTHWING.addChild(wingrig4);
	wingrig4.setRotationPoint(2.5F, 12.5F, 0F);
	wingrig4.setTextureSize(64, 32);
	wingrig4.mirror = true;
	setRotation(wingrig4, 0F, 0F, 0F);
	wingrig3 = new ModelRenderer(this, 16, 11);
	wingrig3.addBox(-2F, -7F, -1.5F, 1, 2, 5);
	RIGTHWING.addChild(wingrig3);
	wingrig3.setRotationPoint(2.5F, 10.5F, 0F);
	wingrig3.setTextureSize(64, 32);
	wingrig3.mirror = true;
	setRotation(wingrig3, 0F, 0F, 0F);
	wingrig2 = new ModelRenderer(this, 16, 5);
	wingrig2.addBox(-2F, -7F, -1.5F, 1, 2, 4);
	RIGTHWING.addChild(wingrig2);
	wingrig2.setRotationPoint(2.5F, 8.5F, 0F);
	wingrig2.setTextureSize(64, 32);
	wingrig2.mirror = true;
	setRotation(wingrig2, 0F, 0F, 0F);
	wingrig1 = new ModelRenderer(this, 16, 0);
	wingrig1.addBox(-2F, -7F, -1.5F, 1, 2, 3);
	RIGTHWING.addChild(wingrig1);
	wingrig1.setRotationPoint(2.5F, 6.5F, 0F);
	wingrig1.setTextureSize(64, 32);
	wingrig1.mirror = true;
	setRotation(wingrig1, 0F, 0F, 0F);
	NECKROT1 = new ModelRenderer(this, "NECKROT1");
	NECKROT1.setRotationPoint(0F, -7.5F, 0F);
	BODYROT.addChild(NECKROT1);
	setRotation(NECKROT1, 0F, 0F, 0F);
	NECKROT1.mirror = true;
	NECKROT2 = new ModelRenderer(this, "NECKROT2");
	NECKROT2.setRotationPoint(0F, -2F, 0F);
	NECKROT1.addChild(NECKROT2);
	setRotation(NECKROT2, 0F, 0F, 0F);
	NECKROT2.mirror = true;
	neck2 = new ModelRenderer(this, 8, 10);
	neck2.addBox(-0.5F, -3F, -0.5F, 1, 2, 1);
	NECKROT2.addChild(neck2);
	neck2.setRotationPoint(0F, 1F, 0F);
	neck2.setTextureSize(64, 32);
	neck2.mirror = true;
	setRotation(neck2, 0F, 0F, 0F);
	NECKROT3 = new ModelRenderer(this, "NECKROT3");
	NECKROT3.setRotationPoint(0F, -2F, 0F);
	NECKROT2.addChild(NECKROT3);
	setRotation(NECKROT3, 0F, 0F, 0F);
	NECKROT3.mirror = true;
	HEADROT = new ModelRenderer(this, "HEADROT");
	HEADROT.setRotationPoint(0F, -2F, 0F);
	NECKROT3.addChild(HEADROT);
	setRotation(HEADROT, 0F, 0F, 0F);
	HEADROT.mirror = true;
	mouthbot = new ModelRenderer(this, 0, 25);
	mouthbot.addBox(-0.5F, -0.5F, -2F, 1, 1, 2);
	HEADROT.addChild(mouthbot);
	mouthbot.setRotationPoint(0F, 1F, -2F);
	mouthbot.setTextureSize(64, 32);
	mouthbot.mirror = true;
	setRotation(mouthbot, 0F, 0F, 0F);
	mouthtal = new ModelRenderer(this, 6, 25);
	mouthtal.addBox(-1F, 0F, -0.5F, 2, 2, 1);
	HEADROT.addChild(mouthtal);
	mouthtal.setRotationPoint(0F, 0.5F, -4.5F);
	mouthtal.setTextureSize(64, 32);
	mouthtal.mirror = true;
	setRotation(mouthtal, 0F, 0F, 0F);
	mouthtop = new ModelRenderer(this, 0, 21);
	mouthtop.addBox(-1F, -1.5F, -3F, 2, 1, 3);
	HEADROT.addChild(mouthtop);
	mouthtop.setRotationPoint(0F, 1F, -2F);
	mouthtop.setTextureSize(64, 32);
	mouthtop.mirror = true;
	setRotation(mouthtop, 0F, 0F, 0F);
	head = new ModelRenderer(this, 0, 16);
	head.addBox(-1.5F, -1.5F, -2F, 3, 3, 2);
	HEADROT.addChild(head);
	head.setRotationPoint(0F, 0F, 0F);
	head.setTextureSize(64, 32);
	head.mirror = true;
	setRotation(head, 0F, 0F, 0F);
	neck3 = new ModelRenderer(this, 8, 10);
	neck3.addBox(-0.5F, -2F, -0.5F, 1, 2, 1);
	NECKROT3.addChild(neck3);
	neck3.setRotationPoint(0F, 0F, 0F);
	neck3.setTextureSize(64, 32);
	neck3.mirror = true;
	setRotation(neck3, 0F, 0F, 0F);
	neck1 = new ModelRenderer(this, 8, 10);
	neck1.addBox(-0.5F, -3F, -0.5F, 1, 2, 1);
	NECKROT1.addChild(neck1);
	neck1.setRotationPoint(0F, 1F, 0F);
	neck1.setTextureSize(64, 32);
	neck1.mirror = true;
	setRotation(neck1, 0F, 0F, 0F);
	TAILROT = new ModelRenderer(this, "TAILROT");
	TAILROT.setRotationPoint(0F, 0F, 1F);
	BODYROT.addChild(TAILROT);
	setRotation(TAILROT, 0F, 0F, 0F);
	TAILROT.mirror = true;
	// tailbase1.mirror = true;
	tailbase1 = new ModelRenderer(this, 32, 8);
	tailbase1.addBox(-1F, -0.5F, 0F, 2, 1, 2);
	TAILROT.addChild(tailbase1);
	tailbase1.setRotationPoint(0F, 0F, 0F);
	tailbase1.setTextureSize(64, 32);
	tailbase1.mirror = true;
	setRotation(tailbase1, 0F, 0F, 0F);
	tailbase1.mirror = false;
	tailbase2 = new ModelRenderer(this, 32, 11);
	tailbase2.addBox(-1.5F, -0.5F, 0F, 3, 1, 2);
	TAILROT.addChild(tailbase2);
	tailbase2.setRotationPoint(0F, 0F, 2F);
	tailbase2.setTextureSize(64, 32);
	tailbase2.mirror = true;
	setRotation(tailbase2, 0F, 0F, 0F);
	neckfrill = new ModelRenderer(this, 0, 28);
	neckfrill.addBox(0F, 0F, 0F, 2, 1, 2);
	neckfrill.setRotationPoint(-1F, -7.5F, -1F);
	BODYROT.addChild(neckfrill);
	neckfrill.setTextureSize(64, 32);
	neckfrill.mirror = true;
	setRotation(neckfrill, 0F, 0F, 0F);
	chestpuff = new ModelRenderer(this, 0, 10);
	chestpuff.addBox(-2F, -7F, -1.5F, 3, 5, 1);
	chestpuff.setRotationPoint(0.5F, 0.5F, -1F);
	BODYROT.addChild(chestpuff);
	chestpuff.setTextureSize(64, 32);
	chestpuff.mirror = true;
	setRotation(chestpuff, 0F, 0F, 0F);
	Body = new ModelRenderer(this, 0, 0);
	Body.addBox(-2F, -7F, -1.5F, 5, 7, 3);
	BODYROT.addChild(Body);
	Body.setRotationPoint(-0.5F, 0.5F, 0F);
	Body.setTextureSize(64, 32);
	Body.mirror = true;
	setRotation(Body, 0F, 0F, 0F);
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
	    FEETROT.render(f5);
	    BODYROT.render(f5);
	    GL11.glPopMatrix();
	} else {
	    FEETROT.render(f5);
	    BODYROT.render(f5);
	}
    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
	EntityVulture var5 = (EntityVulture) par1EntityLiving;

	if (var5.eFEETROT.isSetup == false) {
	    var5.eFEETROT.setup(FEETROT);
	}
	if (var5.eBODYROT.isSetup == false) {
	    var5.eBODYROT.setup(BODYROT);
	}

	if (var5.eLEFTWING.isSetup == false) {
	    var5.eLEFTWING.setup(LEFTWING);
	}
	if (var5.ewingleft4.isSetup == false) {
	    var5.ewingleft4.setup(wingleft4);
	}
	if (var5.ewingleft3.isSetup == false) {
	    var5.ewingleft3.setup(wingleft3);
	}
	if (var5.ewingleft2.isSetup == false) {
	    var5.ewingleft2.setup(wingleft2);
	}
	if (var5.ewingleft1.isSetup == false) {
	    var5.ewingleft1.setup(wingleft1);
	}

	if (var5.eRIGTHWING.isSetup == false) {
	    var5.eRIGTHWING.setup(RIGTHWING);
	}
	if (var5.ewingrig4.isSetup == false) {
	    var5.ewingrig4.setup(wingrig4);
	}
	if (var5.ewingrig3.isSetup == false) {
	    var5.ewingrig3.setup(wingrig3);
	}
	if (var5.ewingrig2.isSetup == false) {
	    var5.ewingrig2.setup(wingrig2);
	}
	if (var5.ewingrig1.isSetup == false) {
	    var5.ewingrig1.setup(wingrig1);
	}

	if (var5.eNECKROT1.isSetup == false) {
	    var5.eNECKROT1.setup(NECKROT1);
	}
	if (var5.eNECKROT2.isSetup == false) {
	    var5.eNECKROT2.setup(NECKROT2);
	}
	if (var5.eNECKROT3.isSetup == false) {
	    var5.eNECKROT3.setup(NECKROT3);
	}

	if (var5.eHEADROT.isSetup == false) {
	    var5.eHEADROT.setup(HEADROT);
	}
	if (var5.eTAILROT.isSetup == false) {
	    var5.eTAILROT.setup(TAILROT);
	}

	if (var5.onGround) {
	    float animSpeed = 2.0f;
	    /* On Ground Idle Standing Animation */
	    FEETROT.rotateAngleX = var5.eFEETROT.rotateX(+0.09f * animSpeed, (float) (-90 * Math.PI / 180f), 0f);
	    NECKROT1.rotateAngleX = var5.eNECKROT1.rotateX(+0.09f * animSpeed, (float) (-30 * Math.PI / 180f),
		    (float) (+30 * Math.PI / 180f));
	    NECKROT2.rotateAngleX = var5.eNECKROT2.rotateX(-0.09f * animSpeed, (float) (+15 * Math.PI / 180f),
		    (float) (+40 * Math.PI / 180f));
	    NECKROT3.rotateAngleX = var5.eNECKROT3.rotateX(-0.09f * animSpeed, (float) (+35 * Math.PI / 180f),
		    (float) (+60 * Math.PI / 180f));

	    LEFTWING.rotateAngleZ = var5.eLEFTWING.rotateZ(-0.09f * animSpeed, (float) (0 * Math.PI / 180f),
		    (float) (90 * Math.PI / 180f), 0);
	    RIGTHWING.rotateAngleZ = var5.eRIGTHWING.rotateZ(+0.09f * animSpeed, (float) (-90 * Math.PI / 180f),
		    (float) (0 * Math.PI / 180f), 0);

	    BODYROT.rotateAngleX = var5.eBODYROT.rotateX(-0.09f * animSpeed, (float) (0 * Math.PI / 180f),
		    (float) (15 * Math.PI / 180f));

	} else if (var5.getEntityState() == EntityStates.attacking || var5.getEntityState() == EntityStates.following) {
	    /* Attacking Flying Animation */
	    float animSpeed = 2.0f;
	    FEETROT.rotateAngleX = var5.eFEETROT.rotateX(-0.09f * animSpeed, (float) (-90 * Math.PI / 180f),
		    (float) (45 * Math.PI / 180f));
	    NECKROT1.rotateAngleX = var5.eNECKROT1.rotateX(-0.09f * animSpeed, (float) (-30 * Math.PI / 180f),
		    (float) (+30 * Math.PI / 180f));
	    NECKROT2.rotateAngleX = var5.eNECKROT2.rotateX(+0.09f * animSpeed, (float) (+20 * Math.PI / 180f),
		    (float) (+40 * Math.PI / 180f));
	    NECKROT3.rotateAngleX = var5.eNECKROT3.rotateX(+0.09f * animSpeed, (float) (+40 * Math.PI / 180f),
		    (float) (+60 * Math.PI / 180f));

	    LEFTWING.rotateAngleZ = var5.eLEFTWING.rotateZ(+0.09f * animSpeed, (float) (0 * Math.PI / 180f),
		    (float) (90 * Math.PI / 180f), 2);
	    RIGTHWING.rotateAngleZ = var5.eRIGTHWING.rotateZ(-0.09f * animSpeed, (float) (-90 * Math.PI / 180f),
		    (float) (0 * Math.PI / 180f), 2);

	    BODYROT.rotateAngleX = var5.eBODYROT.rotateX(-0.09f * animSpeed, (float) (15 * Math.PI / 180f),
		    (float) (40 * Math.PI / 180f));
	} else if (var5.getEntityState() == EntityStates.idle) {
	    /* Idle Flying Animation */
	    float animSpeed = 2.0f;
	    FEETROT.rotateAngleX = var5.eFEETROT.rotateX(+0.09f * animSpeed, (float) (-90 * Math.PI / 180f),
		    (float) (55 * Math.PI / 180f));
	    NECKROT1.rotateAngleX = var5.eNECKROT1.rotateX(+0.09f * animSpeed, (float) (-30 * Math.PI / 180f),
		    (float) (+30 * Math.PI / 180f));
	    NECKROT2.rotateAngleX = var5.eNECKROT2.rotateX(-0.09f * animSpeed, (float) (+5 * Math.PI / 180f),
		    (float) (+40 * Math.PI / 180f));
	    NECKROT3.rotateAngleX = var5.eNECKROT3.rotateX(-0.09f * animSpeed, (float) (+10 * Math.PI / 180f),
		    (float) (+60 * Math.PI / 180f));

	    LEFTWING.rotateAngleZ = var5.eLEFTWING.rotateZ(+0.09f * animSpeed, (float) (0 * Math.PI / 180f),
		    (float) (90 * Math.PI / 180f), 2);
	    RIGTHWING.rotateAngleZ = var5.eRIGTHWING.rotateZ(-0.09f * animSpeed, (float) (-90 * Math.PI / 180f),
		    (float) (0 * Math.PI / 180f), 2);

	    BODYROT.rotateAngleX = var5.eBODYROT.rotateX(+0.09f * animSpeed, (float) (0 * Math.PI / 180f),
		    (float) (60 * Math.PI / 180f));
	}
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
	model.rotateAngleX = x;
	model.rotateAngleY = y;
	model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {
	super.setRotationAngles(f, f1, f2, f3, f4, f5, par7Entity);
	EntityVulture var5 = (EntityVulture) par7Entity;
	float lookingDirectionX = Math.min(Math.max(f4, -15), +15) * (float) (Math.PI / 180f);
	if (var5.onGround) {
	    HEADROT.rotateAngleX = (float) (-70 * Math.PI / 180f) + lookingDirectionX;
	} else if (var5.getEntityState() == EntityStates.attacking || var5.getEntityState() == EntityStates.following) {
	    HEADROT.rotateAngleX = (float) (-60 * Math.PI / 180f) + lookingDirectionX;
	} else if (var5.getEntityState() == EntityStates.idle) {
	    HEADROT.rotateAngleX = (float) (-80 * Math.PI / 180f) + lookingDirectionX;
	}
	HEADROT.rotateAngleY = Math.min(Math.max(f3, -45), +45) * (float) (Math.PI / 180f);
    }
}
