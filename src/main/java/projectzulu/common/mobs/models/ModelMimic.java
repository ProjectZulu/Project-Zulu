package projectzulu.common.mobs.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import projectzulu.common.core.ModelHelper;
import projectzulu.common.mobs.entity.EntityGenericAnimal;
import projectzulu.common.mobs.entity.EntityStates;
import projectzulu.common.mobs.renders.RenderMimic;

public class ModelMimic extends ModelBase {
    ModelRenderer botchest;
    ModelRenderer BOTTEETHROT;
    ModelRenderer LEG1TOPROT;
    ModelRenderer LEG2TOPROT;
    ModelRenderer LEG4TOPROT;
    ModelRenderer LEG3TOPROT;
    ModelRenderer TOPCHESTROT;
    private ModelRenderer LEG1BOTROT;
    private ModelRenderer LEG2BOTROT;
    private ModelRenderer LEG4BOTROT;
    private ModelRenderer LEG3BOTROT;

    public ModelMimic() {
        textureWidth = 64;
        textureHeight = 64;
        setTextureOffset("BOTTEETHROT.TEETH7", 0, 0);
        setTextureOffset("BOTTEETHROT.TEETH5", 0, 0);
        setTextureOffset("BOTTEETHROT.TEETH6", 0, 0);
        setTextureOffset("BOTTEETHROT.TEETHR1", 0, 0);
        setTextureOffset("BOTTEETHROT.TEETHL3", 0, 0);
        setTextureOffset("BOTTEETHROT.TEETHR3", 0, 0);
        setTextureOffset("BOTTEETHROT.TEETHL1", 0, 0);
        setTextureOffset("LEG1TOPROT.leg1bot", 0, 20);
        setTextureOffset("LEG1BOTROT.leg1top", 0, 7);
        setTextureOffset("LEG2TOPROT.leg2bot", 0, 20);
        setTextureOffset("LEG2BOTROT.leg2top", 0, 7);
        setTextureOffset("LEG4TOPROT.leg4bot", 0, 20);
        setTextureOffset("LEG4BOTROT.leg4top", 0, 7);
        setTextureOffset("LEG3TOPROT.leg3bot", 0, 20);
        setTextureOffset("LEG3BOTROT.leg3top", 0, 7);
        setTextureOffset("TOPCHESTROT.topchest", 0, 0);
        setTextureOffset("TOPCHESTROT.chestnose", 0, 0);
        setTextureOffset("TOPCHESTROT.TEETH4", 0, 0);
        setTextureOffset("TOPCHESTROT.TEETH1", 0, 0);
        setTextureOffset("TOPCHESTROT.TEETH3", 0, 0);
        setTextureOffset("TOPCHESTROT.TEETH2", 0, 0);
        setTextureOffset("TOPCHESTROT.TEETHR2", 0, 0);
        setTextureOffset("TOPCHESTROT.TEETHR4", 0, 0);
        setTextureOffset("TOPCHESTROT.TEETHL2", 0, 0);
        setTextureOffset("TOPCHESTROT.TEETHL4", 0, 0);

        botchest = new ModelRenderer(this, 0, 19);
        botchest.addBox(-7.5F, -9.5F, -7.5F, 14, 10, 14);
        botchest.setRotationPoint(0F, 14.53333F, 0F);
        botchest.setTextureSize(128, 64);
        botchest.mirror = true;
        setRotation(botchest, 0F, 0F, 0F);
        BOTTEETHROT = new ModelRenderer(this, "BOTTEETHROT");
        BOTTEETHROT.setRotationPoint(0F, 0.5F, 6.5F);
        setRotation(BOTTEETHROT, 0F, 0F, 0F);
        BOTTEETHROT.mirror = true;
        BOTTEETHROT.addBox("TEETH7", -1F, 3F, -13F, 2, 2, 1);
        BOTTEETHROT.addBox("TEETH5", -4.5F, 3F, -13F, 1, 2, 1);
        BOTTEETHROT.addBox("TEETH6", 3F, 3F, -13F, 1, 2, 1);
        BOTTEETHROT.addBox("TEETHR1", 4.5F, 3F, -11F, 1, 2, 1);
        BOTTEETHROT.addBox("TEETHL3", -6.5F, 4F, -7F, 1, 1, 1);
        BOTTEETHROT.addBox("TEETHR3", 4.5F, 4F, -7F, 1, 1, 1);
        BOTTEETHROT.addBox("TEETHL1", -6.5F, 3F, -11F, 1, 2, 1);
        LEG1TOPROT = new ModelRenderer(this, "LEG1TOPROT");
        LEG1TOPROT.setRotationPoint(4F, 14.5F, -5F);
        setRotation(LEG1TOPROT, 0F, 0F, 0F);
        LEG1TOPROT.mirror = true;
        LEG1TOPROT.addBox("leg1bot", -1F, 0F, -1F, 2, 3, 2);
        LEG1BOTROT = new ModelRenderer(this, "LEG1BOTROT");
        LEG1BOTROT.setRotationPoint(0F, 3F, 0F);
        setRotation(LEG1BOTROT, 0F, 0F, 0F);
        LEG1BOTROT.mirror = true;
        LEG1BOTROT.addBox("leg1top", -1F, 0F, -1F, 2, 5, 2);
        LEG1TOPROT.addChild(LEG1BOTROT);
        LEG2TOPROT = new ModelRenderer(this, "LEG2TOPROT");
        LEG2TOPROT.setRotationPoint(-5F, 14.5F, -5F);
        setRotation(LEG2TOPROT, 0F, 0F, 0F);
        LEG2TOPROT.mirror = true;
        LEG2TOPROT.addBox("leg2bot", -1F, 0F, -1F, 2, 3, 2);
        LEG2BOTROT = new ModelRenderer(this, "LEG2BOTROT");
        LEG2BOTROT.setRotationPoint(0F, 3F, 0F);
        setRotation(LEG2BOTROT, 0F, 0.0174533F, 0F);
        LEG2BOTROT.mirror = true;
        LEG2BOTROT.addBox("leg2top", -1F, 0F, -1F, 2, 5, 2);
        LEG2TOPROT.addChild(LEG2BOTROT);
        LEG4TOPROT = new ModelRenderer(this, "LEG4TOPROT");
        LEG4TOPROT.setRotationPoint(-5F, 14.5F, 4F);
        setRotation(LEG4TOPROT, 0F, 0F, 0F);
        LEG4TOPROT.mirror = true;
        LEG4TOPROT.addBox("leg4bot", -1F, 0F, -1F, 2, 3, 2);
        LEG4BOTROT = new ModelRenderer(this, "LEG4BOTROT");
        LEG4BOTROT.setRotationPoint(0F, 3F, 0F);
        setRotation(LEG4BOTROT, 0F, 0F, 0F);
        LEG4BOTROT.mirror = true;
        LEG4BOTROT.addBox("leg4top", -1F, 0F, -1F, 2, 5, 2);
        LEG4TOPROT.addChild(LEG4BOTROT);
        LEG3TOPROT = new ModelRenderer(this, "LEG3TOPROT");
        LEG3TOPROT.setRotationPoint(4F, 14.5F, 4F);
        setRotation(LEG3TOPROT, 0F, 0F, 0F);
        LEG3TOPROT.mirror = true;
        LEG3TOPROT.addBox("leg3bot", -1F, 0F, -1F, 2, 3, 2);
        LEG3BOTROT = new ModelRenderer(this, "LEG3BOTROT");
        LEG3BOTROT.setRotationPoint(0F, 3F, 0F);
        setRotation(LEG3BOTROT, 0F, 0F, 0F);
        LEG3BOTROT.mirror = true;
        LEG3BOTROT.addBox("leg3top", -1F, 0F, -1F, 2, 5, 2);
        LEG3TOPROT.addChild(LEG3BOTROT);
        TOPCHESTROT = new ModelRenderer(this, "TOPCHESTROT");
        TOPCHESTROT.setRotationPoint(0F, 5F, 6.5F);
        setRotation(TOPCHESTROT, 0F, 0F, 0F);
        TOPCHESTROT.mirror = true;
        TOPCHESTROT.addBox("topchest", -7.5F, -5F, -14F, 14, 5, 14);
        TOPCHESTROT.addBox("chestnose", -1F, -1.5F, -15.5F, 2, 3, 1);
        TOPCHESTROT.addBox("TEETH4", 4.5F, 0F, -13F, 1, 2, 1);
        TOPCHESTROT.addBox("TEETH1", -6.5F, 0F, -13F, 1, 2, 1);
        TOPCHESTROT.addBox("TEETH3", 1.5F, 0F, -13F, 1, 2, 1);
        TOPCHESTROT.addBox("TEETH2", -2.5F, 0F, -13F, 1, 2, 1);
        TOPCHESTROT.addBox("TEETHR2", 4.5F, 0F, -9F, 1, 2, 1);
        TOPCHESTROT.addBox("TEETHR4", 4.5F, 0F, -5F, 1, 1, 1);
        TOPCHESTROT.addBox("TEETHL2", -6.5F, 0F, -9F, 1, 2, 1);
        TOPCHESTROT.addBox("TEETHL4", -6.5F, 0F, -5F, 1, 1, 1);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        LEG1TOPROT.render(f5);
        LEG2TOPROT.render(f5);
        LEG4TOPROT.render(f5);
        LEG3TOPROT.render(f5);
        Minecraft.getMinecraft().renderEngine.bindTexture(RenderMimic.normalChest);
        botchest.render(f5);
        BOTTEETHROT.render(f5);
        TOPCHESTROT.render(f5);
    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {

        EntityGenericAnimal var5 = (EntityGenericAnimal) par1EntityLiving;

        /* State Based Animations */
        if (var5.getEntityState() != EntityStates.idle) {
            float heightToRaise = 0.0f;
            botchest.rotationPointY = 14.5333f - heightToRaise;
            BOTTEETHROT.rotationPointY = 0.5f - heightToRaise;
            TOPCHESTROT.rotationPointY = 5f - heightToRaise;
            LEG1TOPROT.rotationPointY = 14.5f - heightToRaise;
            LEG2TOPROT.rotationPointY = 14.5f - heightToRaise;
            LEG3TOPROT.rotationPointY = 14.5f - heightToRaise;
            LEG4TOPROT.rotationPointY = 14.5f - heightToRaise;

            LEG1TOPROT.rotateAngleX = (float) (-10 * Math.PI / 180)
                    + (float) (MathHelper.cos(par2 * 0.6662F) * 1.8F * ModelHelper.abs(Math.log(par3 + 1)));
            LEG1TOPROT.rotateAngleZ = (float) (-50 * Math.PI / 180);
            LEG1BOTROT.rotateAngleZ = (float) (50 * Math.PI / 180);

            LEG3TOPROT.rotateAngleX = (float) (+10 * Math.PI / 180)
                    + (float) (MathHelper.cos(par2 * 0.6662F) * 1.8F * ModelHelper.abs(Math.log(par3 + 1)));
            LEG3TOPROT.rotateAngleZ = (float) (-50 * Math.PI / 180);
            LEG3BOTROT.rotateAngleZ = (float) (50 * Math.PI / 180);

            LEG2TOPROT.rotateAngleX = (float) (-10 * Math.PI / 180)
                    + (float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper.abs(Math
                            .log(par3 + 1)));
            LEG2TOPROT.rotateAngleZ = (float) (+50 * Math.PI / 180);
            LEG2BOTROT.rotateAngleZ = (float) (-50 * Math.PI / 180);

            LEG4TOPROT.rotateAngleX = (float) (+10 * Math.PI / 180)
                    + (float) (MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.8F * ModelHelper.abs(Math
                            .log(par3 + 1)));
            LEG4TOPROT.rotateAngleZ = (float) (+50 * Math.PI / 180);
            LEG4BOTROT.rotateAngleZ = (float) (-50 * Math.PI / 180);

            TOPCHESTROT.rotateAngleX = (float) (Math.abs(MathHelper.cos(par2 * 0.6662F)) * -60 * Math.PI / 180 * par3);

        } else {
            float heightToRaise = -9f;
            botchest.rotationPointY = 14.5333f - heightToRaise;
            BOTTEETHROT.rotationPointY = 0.5f - heightToRaise;
            TOPCHESTROT.rotationPointY = 5f - (heightToRaise - 1f);
            LEG1TOPROT.rotationPointY = 14.5f - heightToRaise;
            LEG2TOPROT.rotationPointY = 14.5f - heightToRaise;
            LEG3TOPROT.rotationPointY = 14.5f - heightToRaise;
            LEG4TOPROT.rotationPointY = 14.5f - heightToRaise;

            LEG1TOPROT.rotateAngleX = (float) (+30 * Math.PI / 180);
            LEG1TOPROT.rotateAngleZ = (float) (+90 * Math.PI / 180);
            LEG3TOPROT.rotateAngleX = (float) (-30 * Math.PI / 180);
            LEG3TOPROT.rotateAngleZ = (float) (+90 * Math.PI / 180);

            LEG2TOPROT.rotateAngleX = (float) (+30 * Math.PI / 180);
            LEG2TOPROT.rotateAngleZ = (float) (-90 * Math.PI / 180);
            LEG4TOPROT.rotateAngleX = (float) (-30 * Math.PI / 180);
            LEG4TOPROT.rotateAngleZ = (float) (-90 * Math.PI / 180);

            TOPCHESTROT.rotateAngleX = 0f;
        }
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
    }
}
