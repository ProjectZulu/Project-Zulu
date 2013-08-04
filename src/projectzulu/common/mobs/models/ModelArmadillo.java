package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import projectzulu.common.mobs.entity.EntityArmadillo;
import projectzulu.common.mobs.entity.EntityStates;

public class ModelArmadillo extends ModelBase {
    public ModelRenderer WHOLE;
    public ModelRenderer HEADPIECE;
    public ModelRenderer REARRTR1;
    public ModelRenderer REARRTR2;
    public ModelRenderer REARRTR3;

    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer body2;
    public ModelRenderer tail;
    public ModelRenderer body1;
    public ModelRenderer body3;
    public ModelRenderer body4;

    public ModelRenderer chin;
    public ModelRenderer head;
    public ModelRenderer nose;

    public ModelArmadillo() {
        textureWidth = 64;
        textureHeight = 32;
        setTextureOffset("WHOLE.dltfold0", 0, 0);

        WHOLE = new ModelRenderer(this, "WHOLE");
        WHOLE.setRotationPoint(0F, 18F, 0F);
        // WHOLE.setRotationPoint(0F, 0F, 0F);
        WHOLE.mirror = true;
        setRotation(WHOLE, 0F, 0F, 0F);

        REARRTR1 = new ModelRenderer(this, "REARRTR1");
        WHOLE.addChild(REARRTR1);
        REARRTR1.setRotationPoint(0F, -3F, -2F);
        setRotation(REARRTR1, 0F, 0F, 0F);
        REARRTR1.mirror = true;
        body2 = new ModelRenderer(this, 32, 0);
        body2.addBox(-4.5F, -4F, -2F, 9, 8, 4);
        REARRTR1.addChild(body2);
        body2.setRotationPoint(0F, 3F, 2F);
        body2.setTextureSize(64, 32);
        body2.mirror = true;
        setRotation(body2, 0F, 0F, 0F);
        REARRTR2 = new ModelRenderer(this, "REARRTR2");
        REARRTR1.addChild(REARRTR2);
        REARRTR2.setRotationPoint(0F, -1F, 4F);
        setRotation(REARRTR2, 0F, 0F, 0F);
        REARRTR2.mirror = true;
        body3 = new ModelRenderer(this, 32, 0);
        body3.addBox(-4.5F, -4F, -2F, 9, 8, 4);
        REARRTR2.addChild(body3);
        body3.setRotationPoint(0F, 4F, 2F);
        body3.setTextureSize(64, 32);
        body3.mirror = true;
        setRotation(body3, 0F, 0F, 0F);
        REARRTR3 = new ModelRenderer(this, "REARRTR3");
        REARRTR2.addChild(REARRTR3);
        REARRTR3.setRotationPoint(0F, 0F, 4F);
        setRotation(REARRTR3, 0F, 0F, 0F);
        REARRTR3.mirror = true;
        tail = new ModelRenderer(this, 8, 14);
        tail.addBox(-0.5F, -0.5F, 0F, 1, 1, 6);
        REARRTR3.addChild(tail);
        tail.setRotationPoint(0F, 6F, 4F);
        tail.setTextureSize(64, 32);
        tail.mirror = true;
        setRotation(tail, 0F, 0F, 0F);
        body4 = new ModelRenderer(this, 32, 0);
        body4.addBox(-4.5F, -4F, -2F, 9, 8, 4);
        REARRTR3.addChild(body4);
        body4.setRotationPoint(0F, 4F, 2F);
        body4.setTextureSize(64, 32);
        body4.mirror = true;
        setRotation(body4, 0F, 0F, 0F);
        // dltfold3.addChildModelRenderer(REARRTR3);
        // dltfold2.addChildModelRenderer(REARRTR2);
        // dltfold0.addChildModelRenderer(REARRTR1);
        HEADPIECE = new ModelRenderer(this, "HEADPIECE");
        WHOLE.addChild(HEADPIECE);
        HEADPIECE.setRotationPoint(0F, 1F, -6F);
        setRotation(HEADPIECE, 0F, 0F, 0F);
        HEADPIECE.mirror = true;
        nose = new ModelRenderer(this, 12, 9);
        nose.addBox(-0.5F, -0.5F, -2F, 1, 1, 2);
        HEADPIECE.addChild(nose);
        nose.setRotationPoint(0F, 2F, -5F);
        nose.setTextureSize(64, 32);
        nose.mirror = true;
        setRotation(nose, 0F, 0F, 0F);
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-2.5F, -2.5F, -1.5F, 5, 5, 3);
        HEADPIECE.addChild(head);
        head.setRotationPoint(0F, 0F, -1F);
        head.setTextureSize(64, 32);
        head.mirror = true;
        setRotation(head, 0F, 0F, 0F);
        chin = new ModelRenderer(this, 0, 8);
        chin.addBox(-1.5F, -1.5F, -2.5F, 3, 3, 3);
        HEADPIECE.addChild(chin);
        chin.setRotationPoint(0F, 1F, -3F);
        chin.setTextureSize(64, 32);
        chin.mirror = true;
        setRotation(chin, 0F, 0F, 0F);
        // dltfold0.addChildModelRenderer(HEADPIECE);
        body1 = new ModelRenderer(this, 32, 0);
        body1.addBox(-4.5F, -4F, -2F, 9, 8, 4);
        WHOLE.addChild(body1);
        body1.setRotationPoint(0F, 0F, -4F);
        body1.setTextureSize(64, 32);
        body1.mirror = true;
        setRotation(body1, 0F, 0F, 0F);
        leg4 = new ModelRenderer(this, 0, 15);
        leg4.addBox(-1F, 0F, -1F, 2, 2, 2);
        WHOLE.addChild(leg4);
        leg4.setRotationPoint(3F, 4F, 5F);
        leg4.setTextureSize(64, 32);
        leg4.mirror = true;
        setRotation(leg4, 0F, 0F, 0F);
        leg3 = new ModelRenderer(this, 0, 15);
        leg3.addBox(-1F, 0F, -1F, 2, 2, 2);
        WHOLE.addChild(leg3);
        leg3.setRotationPoint(-3F, 4F, 5F);
        leg3.setTextureSize(64, 32);
        leg3.mirror = true;
        setRotation(leg3, 0F, 0F, 0F);
        leg2 = new ModelRenderer(this, 0, 15);
        leg2.addBox(-1F, 0F, -1F, 2, 2, 2);
        WHOLE.addChild(leg2);
        leg2.setRotationPoint(3F, 4F, -4F);
        leg2.setTextureSize(64, 32);
        leg2.mirror = true;
        setRotation(leg2, 0F, 0F, 0F);
        leg1 = new ModelRenderer(this, 0, 15);
        leg1.addBox(-1F, 0F, -1F, 2, 2, 2);
        WHOLE.addChild(leg1);
        leg1.setRotationPoint(-3F, 4F, -4F);
        leg1.setTextureSize(64, 32);
        leg1.mirror = true;
        setRotation(leg1, 0F, 0F, 0F);

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
            // Something Should GO here
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
            GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
            WHOLE.render(f5);
            GL11.glPopMatrix();
        } else {
            WHOLE.render(f5);
        }
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6,
            Entity par7Entity) {
        EntityArmadillo var5 = (EntityArmadillo) par7Entity;
        if (var5.getEntityState() == EntityStates.inCover || var5.isCharging() == true) {
            HEADPIECE.rotateAngleX = var5.eHEADPIECE.rotateX(+0.05f * 2.0f, 0f, (float) (145 * Math.PI / 180f));
        } else if (var5.getEntityState() != EntityStates.inCover && var5.isCharging() == false) {
            // HEADPIECE.rotateAngleX =
            // var5.eHEADPIECE.rotateX(-0.05f*animSpeed,0f,(float)(145*Math.PI/180f));
            this.HEADPIECE.rotateAngleX = var5.eHEADPIECE.rotateAngleX = Math.min(Math.max(par5, -180), +180)
                    * (float) (Math.PI / 180f);
            this.HEADPIECE.rotateAngleY = var5.eHEADPIECE.rotateAngleY = Math.min(Math.max(par4, -30), +30)
                    * (float) (Math.PI / 180f);
        }
    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {

        EntityArmadillo var5 = (EntityArmadillo) par1EntityLiving;

        if (var5.eWHOLE.isSetup == false) {
            var5.eWHOLE.setup(WHOLE);
        }
        if (var5.eHEADPIECE.isSetup == false) {
            var5.eHEADPIECE.setup(HEADPIECE);
        }
        if (var5.eREARRTR1.isSetup == false) {
            var5.eREARRTR1.setup(REARRTR1);
        }
        if (var5.eREARRTR2.isSetup == false) {
            var5.eREARRTR2.setup(REARRTR2);
        }
        if (var5.eREARRTR3.isSetup == false) {
            var5.eREARRTR3.setup(REARRTR3);
        }
        if (var5.etail.isSetup == false) {
            var5.etail.setup(tail);
        }
        if (var5.eleg3.isSetup == false) {
            var5.eleg3.setup(leg3);
        }
        if (var5.eleg4.isSetup == false) {
            var5.eleg4.setup(leg4);
        }

        if (var5.getEntityState() == EntityStates.inCover || var5.isCharging() == true) {
            float animSpeed = 2.0f;
            if (var5.isCharging() == true) {
                animSpeed = 5.0f;
                /* Make Complete Body Circle */
                if (var5.eWHOLE.rotateAngleX == (float) ((390 + 360) * Math.PI / 180f)) {
                    var5.eWHOLE.rotateAngleX -= 360 * Math.PI / 180;
                }
                WHOLE.rotateAngleX = var5.eWHOLE
                        .rotateX(+0.09f * animSpeed, 0f, (float) ((390 + 360) * Math.PI / 180f));
            } else {
                animSpeed = 2.0f;
                /* Make Complete Body Circle */
                WHOLE.rotateAngleX = var5.eWHOLE.rotateX(+0.09f * animSpeed, 0f, (float) (390 * Math.PI / 180f));
            }
            /* Rear Segments (square) fold to form circle */
            REARRTR1.rotateAngleX = var5.eREARRTR1.rotateX(-0.01f * animSpeed, (float) (-44 * Math.PI / 180f), 0f);
            REARRTR2.rotateAngleX = var5.eREARRTR2.rotateX(-0.01f * animSpeed, (float) (-44 * Math.PI / 180f), 0f);
            REARRTR3.rotateAngleX = var5.eREARRTR3.rotateX(-0.01f * animSpeed, (float) (-44 * Math.PI / 180f), 0f);
            tail.rotateAngleX = var5.etail.rotateX(+0.1f, (float) (0 * Math.PI / 180f), (float) (90 * Math.PI / 180f));
            tail.rotationPointZ = var5.etail.translateZ(-0.5f * animSpeed, 2.0f, 4.0f);
            leg3.rotationPointZ = var5.eleg3.translateZ(-0.5f * animSpeed, -4f, 4f);
            leg4.rotationPointZ = var5.eleg4.translateZ(-0.5f * animSpeed, -4f, 4f);
        } else if (var5.getEntityState() != EntityStates.inCover && var5.isCharging() == false) {
            float animSpeed = 2.0f;
            /* Make Complete Body Circle */
            WHOLE.rotateAngleX = var5.eWHOLE.rotateX(-0.09f * animSpeed, 0f, (float) ((390 + 360) * Math.PI / 180f));
            /* Rotate Face Inward */
            /* Rear Segments (square) fold to form circle */
            REARRTR1.rotateAngleX = var5.eREARRTR1.rotateX(+0.01f * animSpeed, (float) (-44 * Math.PI / 180f), 0f);
            REARRTR2.rotateAngleX = var5.eREARRTR2.rotateX(+0.01f * animSpeed, (float) (-44 * Math.PI / 180f), 0f);
            REARRTR3.rotateAngleX = var5.eREARRTR3.rotateX(+0.01f * animSpeed, (float) (-44 * Math.PI / 180f), 0f);
            tail.rotateAngleX = var5.etail.rotateX(-0.1f * animSpeed, (float) (-15 * Math.PI / 180f),
                    (float) (30 * Math.PI / 180f));
            tail.rotationPointZ = var5.etail.translateZ(+0.5f * animSpeed, 0.0f, 4.0f);
            leg3.rotationPointZ = var5.eleg3.translateZ(+0.5f * animSpeed, -4f, 4f);
            leg4.rotationPointZ = var5.eleg4.translateZ(+0.5f * animSpeed, -4f, 4f);

            leg1.rotateAngleX = (float) (MathHelper.cos(4 * par2 * 0.6662F + (float) Math.PI) * 1.4F * par3);
            leg2.rotateAngleX = (float) (MathHelper.cos(4 * par2 * 0.6662F) * 1.4F * par3);
            leg3.rotateAngleX = (float) (MathHelper.cos(4 * par2 * 0.6662F + (float) Math.PI) * 1.4F * par3);
            leg4.rotateAngleX = (float) (MathHelper.cos(4 * par2 * 0.6662F) * 1.4F * par3);
        }
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
