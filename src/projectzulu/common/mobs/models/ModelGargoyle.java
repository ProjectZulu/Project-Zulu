package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public class ModelGargoyle extends ModelBase {

    ModelRenderer WHOLEROT;

    ModelRenderer HEADROT;
    ModelRenderer HEADTOPROT;
    ModelRenderer HEADBOTROT;
    ModelRenderer HORNLEF1ROT;
    ModelRenderer HORNLEF2ROT;
    ModelRenderer HORNLEF3ROT;
    ModelRenderer HORNRIG1ROT;
    ModelRenderer HORNRIG2ROT;
    ModelRenderer HORNRIG3ROT;

    ModelRenderer NECK1ROT;
    ModelRenderer NECK2ROT;
    ModelRenderer NECK3ROT;
    ModelRenderer NECK4ROT;

    ModelRenderer BODYBOTROT;
    ModelRenderer BODYTOPROT;

    ModelRenderer WINGLEF1ROT;
    ModelRenderer WINGLEF2ROT;
    ModelRenderer WINGLEF3ROT;
    ModelRenderer WINGLEF4ROT;
    ModelRenderer WINGLEF5ROT;

    ModelRenderer LEGLEFTOP;
    ModelRenderer LEGLEFBOT;
    ModelRenderer TALONLEF2;
    ModelRenderer TALONLEF1;
    ModelRenderer TALONLEF3;

    ModelRenderer ARMLEFTOPROT;
    ModelRenderer ARMLEFBOTROT;
    ModelRenderer CLAWLEF1ROT;
    ModelRenderer CLAWLEF2ROT;
    ModelRenderer CLAWLEF3ROT;

    public ModelGargoyle() {
        textureWidth = 128;
        textureHeight = 64;
        setTextureOffset("LEGLEFTOP.LegLefTop", 0, 30);
        setTextureOffset("LEGLEFBOT.LegLefBot", 0, 43);
        setTextureOffset("TALONLEF2.TalonLef2", 0, 56);
        setTextureOffset("TALONLEF2.TalonToeLef2", 0, 61);
        setTextureOffset("TALONLEF1.TalonLef", 0, 56);
        setTextureOffset("TALONLEF1.TalonToeLef1", 0, 61);
        setTextureOffset("TALONLEF3.TalonLef3", 0, 56);
        setTextureOffset("TALONLEF3.TalonToeLef3", 0, 61);
        setTextureOffset("BODYBOTROT.BodyBot", 0, 13);
        setTextureOffset("BODYTOPROT.WingLefBase", 36, 23);
        setTextureOffset("BODYTOPROT.BodyTop", 0, 0);
        setTextureOffset("ARMLEFTOPROT.ArmLefTop", 16, 30);
        setTextureOffset("ARMLEFBOTROT.ArmLefBot", 16, 38);
        setTextureOffset("CLAWLEF1ROT.ClawLef1", 16, 46);
        setTextureOffset("CLAWLEF2ROT.ClawLef2", 16, 48);
        setTextureOffset("CLAWLEF3ROT.ClawLef3", 16, 50);
        setTextureOffset("WINGLEF1ROT.WingLefBone1", 109, 19);
        setTextureOffset("WINGLEF1ROT.WingTopLef1", 36, 0);
        setTextureOffset("WINGLEF1ROT.WingLef1", 54, 0);
        setTextureOffset("WINGLEF2ROT.WingTopLef2", 36, 8);
        setTextureOffset("WINGLEF2ROT.WingLef2", 66, 0);
        setTextureOffset("WINGLEF2ROT.WingLefBone2", 113, 19);
        setTextureOffset("WINGLEF3ROT.WingLef3", 78, 0);
        setTextureOffset("WINGLEF3ROT.WingLefBone3", 117, 19);
        setTextureOffset("WINGLEF3ROT.WingTopLef3", 36, 0);
        setTextureOffset("WINGLEF4ROT.WingLef4", 90, 0);
        setTextureOffset("WINGLEF4ROT.WingTopLef4", 36, 8);
        setTextureOffset("WINGLEF4ROT.WingTopLef4", 121, 19);
        setTextureOffset("WINGLEF5ROT.WingLef5", 102, 0);
        setTextureOffset("WINGLEF5ROT.WingTopLef5", 36, 16);
        setTextureOffset("NECK1ROT.Neck1", 42, 30);
        setTextureOffset("NECK2ROT.Neck2", 42, 30);
        setTextureOffset("NECK3ROT.Neck3", 42, 30);
        setTextureOffset("NECK4ROT.Neck4", 42, 30);
        setTextureOffset("HEADTOPROT.HeadTop1", 40, 38);
        setTextureOffset("HEADTOPROT.HeadTop2", 40, 47);
        setTextureOffset("HEADTOPROT.HeadTop3", 40, 52);
        setTextureOffset("HEADTOPROT.Teeth1", 40, 55);
        setTextureOffset("HEADTOPROT.Teeth2", 40, 55);
        setTextureOffset("HEADBOTROT.HeadBot1", 64, 38);
        setTextureOffset("HEADBOTROT.HeadBot2", 64, 46);
        setTextureOffset("HEADBOTROT.HeadBot3", 64, 51);
        setTextureOffset("HORNLEF1ROT.HornLef1", 58, 30);
        setTextureOffset("HORNLEF2ROT.HornLef2", 68, 30);
        setTextureOffset("HORNLEF3ROT.HornLef3", 82, 30);
        setTextureOffset("HORNRIG1ROT.HornRig1", 58, 30);
        setTextureOffset("HORNRIG2ROT.HornRig2", 68, 30);
        setTextureOffset("HORNRIG3ROT.HornRig3", 82, 30);

        WHOLEROT = new ModelRenderer(this, "WHOLEROT");
        WHOLEROT.setRotationPoint(0F, 7F, 0F);
        setRotation(WHOLEROT, 0F, 0F, 0F);
        WHOLEROT.mirror = true;
        LEGLEFTOP = new ModelRenderer(this, "LEGLEFTOP");
        LEGLEFTOP.setRotationPoint(4F, -2F, 0F);
        setRotation(LEGLEFTOP, 0F, 0F, 0F);
        LEGLEFTOP.mirror = true;
        LEGLEFTOP.addBox("LegLefTop", -2F, 0F, -2F, 4, 9, 4);
        LEGLEFBOT = new ModelRenderer(this, "LEGLEFBOT");
        LEGLEFBOT.setRotationPoint(0F, 9F, -2F);
        setRotation(LEGLEFBOT, 0F, 0F, 0F);
        LEGLEFBOT.mirror = true;
        LEGLEFBOT.addBox("LegLefBot", -2F, 0F, 0F, 4, 9, 4);
        TALONLEF2 = new ModelRenderer(this, "TALONLEF2");
        TALONLEF2.setRotationPoint(-1F, 8F, 0F);
        setRotation(TALONLEF2, 0F, 0F, 0F);
        TALONLEF2.mirror = true;
        TALONLEF2.addBox("TalonLef2", -0.5F, 0F, -4F, 1, 1, 4);
        TALONLEF2.addBox("TalonToeLef2", -0.5F, 1F, -4F, 1, 1, 1);
        LEGLEFBOT.addChild(TALONLEF2);
        TALONLEF1 = new ModelRenderer(this, "TALONLEF1");
        TALONLEF1.setRotationPoint(1F, 8F, 0F);
        setRotation(TALONLEF1, 0F, 0F, 0F);
        TALONLEF1.mirror = true;
        TALONLEF1.addBox("TalonLef", -0.5F, 0F, -4F, 1, 1, 4);
        TALONLEF1.addBox("TalonToeLef1", -0.5F, 1F, -4F, 1, 1, 1);
        LEGLEFBOT.addChild(TALONLEF1);
        TALONLEF3 = new ModelRenderer(this, "TALONLEF3");
        TALONLEF3.setRotationPoint(0F, 8F, 4F);
        setRotation(TALONLEF3, 0F, 0F, 0F);
        TALONLEF3.mirror = true;
        TALONLEF3.addBox("TalonLef3", -0.5F, 0F, 0F, 1, 1, 4);
        TALONLEF3.addBox("TalonToeLef3", -0.5F, 1F, 3F, 1, 1, 1);
        LEGLEFBOT.addChild(TALONLEF3);
        LEGLEFTOP.addChild(LEGLEFBOT);
        WHOLEROT.addChild(LEGLEFTOP);
        BODYBOTROT = new ModelRenderer(this, "BODYBOTROT");
        BODYBOTROT.setRotationPoint(0F, 0F, 0F);
        setRotation(BODYBOTROT, 0F, 0F, 0F);
        BODYBOTROT.mirror = true;
        BODYBOTROT.addBox("BodyBot", -5.5F, -10F, -3.5F, 11, 10, 7);
        BODYTOPROT = new ModelRenderer(this, "BODYTOPROT");
        BODYTOPROT.setRotationPoint(0F, -10F, 3F);
        setRotation(BODYTOPROT, 0F, 0F, 0F);
        BODYTOPROT.mirror = true;
        BODYTOPROT.addBox("WingLefBase", 1.5F, -4.5F, 0.5F, 3, 5, 1);
        BODYTOPROT.addBox("BodyTop", -5.5F, -6F, -6.5F, 11, 6, 7);
        ARMLEFTOPROT = new ModelRenderer(this, "ARMLEFTOPROT");
        ARMLEFTOPROT.setRotationPoint(5.5F, -2F, -1.5F);
        setRotation(ARMLEFTOPROT, 0F, 0F, 0F);
        ARMLEFTOPROT.mirror = true;
        ARMLEFTOPROT.addBox("ArmLefTop", 0F, -2F, -4F, 9, 4, 4);
        ARMLEFBOTROT = new ModelRenderer(this, "ARMLEFBOTROT");
        ARMLEFBOTROT.setRotationPoint(9F, 0F, 0F);
        setRotation(ARMLEFBOTROT, 0F, 0F, 0F);
        ARMLEFBOTROT.mirror = true;
        ARMLEFBOTROT.addBox("ArmLefBot", 0F, -2F, -4F, 8, 4, 4);
        CLAWLEF1ROT = new ModelRenderer(this, "CLAWLEF1ROT");
        CLAWLEF1ROT.setRotationPoint(8F, -1F, -1F);
        setRotation(CLAWLEF1ROT, 0F, 0F, 0F);
        CLAWLEF1ROT.mirror = true;
        CLAWLEF1ROT.addBox("ClawLef1", 0F, -0.5F, -0.5F, 3, 1, 1);
        ARMLEFBOTROT.addChild(CLAWLEF1ROT);
        CLAWLEF2ROT = new ModelRenderer(this, "CLAWLEF2ROT");
        CLAWLEF2ROT.setRotationPoint(8F, 1F, -1F);
        setRotation(CLAWLEF2ROT, 0F, 0F, 0F);
        CLAWLEF2ROT.mirror = true;
        CLAWLEF2ROT.addBox("ClawLef2", 0F, -0.5F, -0.5F, 3, 1, 1);
        ARMLEFBOTROT.addChild(CLAWLEF2ROT);
        CLAWLEF3ROT = new ModelRenderer(this, "CLAWLEF3ROT");
        CLAWLEF3ROT.setRotationPoint(8F, 0F, -3F);
        setRotation(CLAWLEF3ROT, 0F, 0F, 0F);
        CLAWLEF3ROT.mirror = true;
        CLAWLEF3ROT.addBox("ClawLef3", 0F, -0.5F, -0.5F, 1, 1, 1);
        ARMLEFBOTROT.addChild(CLAWLEF3ROT);
        ARMLEFTOPROT.addChild(ARMLEFBOTROT);
        BODYTOPROT.addChild(ARMLEFTOPROT);
        WINGLEF1ROT = new ModelRenderer(this, "WINGLEF1ROT");
        WINGLEF1ROT.setRotationPoint(3F, -3.5F, 1.5F);
        setRotation(WINGLEF1ROT, 0F, 0F, 0F);
        WINGLEF1ROT.mirror = true;
        WINGLEF1ROT.addBox("WingLefBone1", -0.5F, 0F, 6F, 1, 9, 1);
        WINGLEF1ROT.addBox("WingTopLef1", -1F, -1F, 0F, 2, 1, 7);
        WINGLEF1ROT.addBox("WingLef1", 0F, 0F, 0F, 0, 9, 6);
        WINGLEF2ROT = new ModelRenderer(this, "WINGLEF2ROT");
        WINGLEF2ROT.setRotationPoint(0F, 0F, 7F);
        setRotation(WINGLEF2ROT, 0F, 0F, 0F);
        WINGLEF2ROT.mirror = true;
        WINGLEF2ROT.addBox("WingTopLef2", -1F, -1F, 0F, 2, 1, 7);
        WINGLEF2ROT.addBox("WingLef2", 0.01F, 0F, 0F, 0, 11, 6);
        WINGLEF2ROT.addBox("WingLefBone2", -0.5F, 0F, 6F, 1, 11, 1);
        WINGLEF3ROT = new ModelRenderer(this, "WINGLEF3ROT");
        WINGLEF3ROT.setRotationPoint(0F, 0F, 7F);
        setRotation(WINGLEF3ROT, 0F, 0F, 0F);
        WINGLEF3ROT.mirror = true;
        WINGLEF3ROT.addBox("WingLef3", 0.02F, 0F, 0F, 0, 13, 6);
        WINGLEF3ROT.addBox("WingLefBone3", -0.5F, 0F, 6F, 1, 13, 1);
        WINGLEF3ROT.addBox("WingTopLef3", -1F, -1F, 0F, 2, 1, 7);
        WINGLEF4ROT = new ModelRenderer(this, "WINGLEF4ROT");
        WINGLEF4ROT.setRotationPoint(0F, 0F, 7F);
        setRotation(WINGLEF4ROT, 0F, 0F, 0F);
        WINGLEF4ROT.mirror = true;
        WINGLEF4ROT.addBox("WingLef4", -0.01F, 0F, 0F, 0, 12, 6);
        WINGLEF4ROT.addBox("WingTopLef4", -1F, -1F, 0F, 2, 1, 7);
        WINGLEF4ROT.addBox("WingTopLef4", -0.5F, 0F, 6F, 1, 12, 1);
        WINGLEF5ROT = new ModelRenderer(this, "WINGLEF5ROT");
        WINGLEF5ROT.setRotationPoint(0F, 0F, 7F);
        setRotation(WINGLEF5ROT, 0F, 0F, 0F);
        WINGLEF5ROT.mirror = true;
        WINGLEF5ROT.addBox("WingLef5", -0.02F, 0F, 0F, 0, 10, 6);
        WINGLEF5ROT.addBox("WingTopLef5", -1F, -1F, 0F, 2, 1, 6);
        WINGLEF4ROT.addChild(WINGLEF5ROT);
        WINGLEF3ROT.addChild(WINGLEF4ROT);
        WINGLEF2ROT.addChild(WINGLEF3ROT);
        WINGLEF1ROT.addChild(WINGLEF2ROT);
        BODYTOPROT.addChild(WINGLEF1ROT);
        NECK1ROT = new ModelRenderer(this, "NECK1ROT");
        NECK1ROT.setRotationPoint(0F, -6F, -2.5F);
        setRotation(NECK1ROT, 0F, 0F, 0F);
        NECK1ROT.mirror = true;
        NECK1ROT.addBox("Neck1", -2F, -3F, -2F, 4, 3, 4);
        NECK2ROT = new ModelRenderer(this, "NECK2ROT");
        NECK2ROT.setRotationPoint(0F, -3F, 0F);
        setRotation(NECK2ROT, 0F, 0F, 0F);
        NECK2ROT.mirror = true;
        NECK2ROT.addBox("Neck2", -2F, -3F, -2F, 4, 3, 4);
        NECK3ROT = new ModelRenderer(this, "NECK3ROT");
        NECK3ROT.setRotationPoint(0F, -3F, 0F);
        setRotation(NECK3ROT, 0F, 0F, 0F);
        NECK3ROT.mirror = true;
        NECK3ROT.addBox("Neck3", -2F, -3F, -2F, 4, 3, 4);
        NECK4ROT = new ModelRenderer(this, "NECK4ROT");
        NECK4ROT.setRotationPoint(0F, -3F, 0F);
        setRotation(NECK4ROT, 0F, 0F, 0F);
        NECK4ROT.mirror = true;
        NECK4ROT.addBox("Neck4", -2F, -3F, -2F, 4, 3, 4);
        HEADROT = new ModelRenderer(this, "HEADROT");
        HEADROT.setRotationPoint(0F, -3F, -0.5F);
        setRotation(HEADROT, 0F, 0F, 0F);
        HEADROT.mirror = true;
        HEADTOPROT = new ModelRenderer(this, "HEADTOPROT");
        HEADTOPROT.setRotationPoint(0F, 0F, 0F);
        setRotation(HEADTOPROT, 0F, 0F, 0F);
        HEADTOPROT.mirror = true;
        HEADTOPROT.addBox("HeadTop1", -4F, -5F, 0F, 8, 5, 4);
        HEADTOPROT.addBox("HeadTop2", -3.5F, -8F, 0F, 7, 3, 2);
        HEADTOPROT.addBox("HeadTop3", -2F, -10F, 0F, 4, 2, 1);
        HEADTOPROT.addBox("Teeth1", -1.5F, -10F, -2F, 1, 1, 2);
        HEADTOPROT.addBox("Teeth2", 0.5F, -10F, -2F, 1, 1, 2);
        HEADROT.addChild(HEADTOPROT);
        HEADBOTROT = new ModelRenderer(this, "HEADBOTROT");
        HEADBOTROT.setRotationPoint(0F, 0F, 0F);
        setRotation(HEADBOTROT, 0F, 0F, 0F);
        HEADBOTROT.mirror = true;
        HEADBOTROT.addBox("HeadBot1", -3.5F, -5F, -3F, 7, 5, 3);
        HEADBOTROT.addBox("HeadBot2", -2.5F, -8F, -2F, 5, 3, 2);
        HEADBOTROT.addBox("HeadBot3", -1.5F, -9F, -1F, 3, 1, 1);
        HEADROT.addChild(HEADBOTROT);
        HORNLEF1ROT = new ModelRenderer(this, "HORNLEF1ROT");
        HORNLEF1ROT.setRotationPoint(4F, -2F, 4F);
        setRotation(HORNLEF1ROT, 0F, 0F, 0F);
        HORNLEF1ROT.mirror = true;
        HORNLEF1ROT.addBox("HornLef1", -1.5F, -1.5F, -1F, 3, 3, 2);
        HORNLEF2ROT = new ModelRenderer(this, "HORNLEF2ROT");
        HORNLEF2ROT.setRotationPoint(0F, -1.5F, 1F);
        setRotation(HORNLEF2ROT, 0F, 0F, 0F);
        HORNLEF2ROT.mirror = true;
        HORNLEF2ROT.addBox("HornLef2", -1.5F, 0F, 0F, 3, 2, 4);
        HORNLEF3ROT = new ModelRenderer(this, "HORNLEF3ROT");
        HORNLEF3ROT.setRotationPoint(0F, 0F, 4F);
        setRotation(HORNLEF3ROT, 0F, 0F, 0F);
        HORNLEF3ROT.mirror = true;
        HORNLEF3ROT.addBox("HornLef3", -1F, 0F, 0F, 2, 1, 4);
        HORNLEF2ROT.addChild(HORNLEF3ROT);
        HORNLEF1ROT.addChild(HORNLEF2ROT);
        HEADROT.addChild(HORNLEF1ROT);
        HORNRIG1ROT = new ModelRenderer(this, "HORNRIG1ROT");
        HORNRIG1ROT.setRotationPoint(-4F, -2F, 4F);
        setRotation(HORNRIG1ROT, 0F, 0F, 0F);
        HORNRIG1ROT.mirror = true;
        HORNRIG1ROT.addBox("HornRig1", -1.5F, -1.5F, -1F, 3, 3, 2);
        HORNRIG2ROT = new ModelRenderer(this, "HORNRIG2ROT");
        HORNRIG2ROT.setRotationPoint(0F, -1.5F, 1F);
        setRotation(HORNRIG2ROT, 0F, 0F, 0F);
        HORNRIG2ROT.mirror = true;
        HORNRIG2ROT.addBox("HornRig2", -1.5F, 0F, 0F, 3, 2, 4);
        HORNRIG3ROT = new ModelRenderer(this, "HORNRIG3ROT");
        HORNRIG3ROT.setRotationPoint(0F, 0F, 4F);
        setRotation(HORNRIG3ROT, 0F, 0F, 0F);
        HORNRIG3ROT.mirror = true;
        HORNRIG3ROT.addBox("HornRig3", -1F, 0F, 0F, 2, 1, 4);
        HORNRIG2ROT.addChild(HORNRIG3ROT);
        HORNRIG1ROT.addChild(HORNRIG2ROT);
        HEADROT.addChild(HORNRIG1ROT);
        NECK4ROT.addChild(HEADROT);
        NECK3ROT.addChild(NECK4ROT);
        NECK2ROT.addChild(NECK3ROT);
        NECK1ROT.addChild(NECK2ROT);
        BODYTOPROT.addChild(NECK1ROT);
        BODYBOTROT.addChild(BODYTOPROT);
        WHOLEROT.addChild(BODYBOTROT);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        WHOLEROT.render(f5);
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6,
            Entity par7Entity) {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
        HEADROT.rotateAngleX = (float) (10 * Math.PI / 180);

        HORNRIG1ROT.rotateAngleX = (float) (-30 * Math.PI / 180);
        HORNRIG1ROT.rotateAngleY = (float) (-30 * Math.PI / 180);
        HORNRIG1ROT.rotateAngleZ = (float) (0 * Math.PI / 180);

        HORNRIG2ROT.rotateAngleX = (float) (-25 * Math.PI / 180);
        HORNRIG3ROT.rotateAngleX = (float) (-20 * Math.PI / 180);

        HORNLEF1ROT.rotateAngleX = (float) (-30 * Math.PI / 180);
        HORNLEF1ROT.rotateAngleY = (float) (+30 * Math.PI / 180);
        HORNLEF1ROT.rotateAngleZ = (float) (0 * Math.PI / 180);

        HORNLEF2ROT.rotateAngleX = (float) (-25 * Math.PI / 180);
        HORNLEF3ROT.rotateAngleX = (float) (-20 * Math.PI / 180);

        BODYTOPROT.rotateAngleX = (float) (20 * Math.PI / 180);
        BODYBOTROT.rotateAngleX = (float) (30 * Math.PI / 180);
        LEGLEFTOP.rotateAngleX = (float) (-60 * Math.PI / 180);
        LEGLEFBOT.rotateAngleX = (float) (60 * Math.PI / 180);

        NECK1ROT.rotateAngleX = (float) (0 * Math.PI / 180);
        NECK2ROT.rotateAngleX = (float) (5 * Math.PI / 180);
        NECK3ROT.rotateAngleX = (float) (15 * Math.PI / 180);
        NECK4ROT.rotateAngleX = (float) (20 * Math.PI / 180);

        ARMLEFTOPROT.rotateAngleY = (float) (45 * Math.PI / 180);
        ARMLEFBOTROT.rotateAngleY = (float) (45 * Math.PI / 180);

        WINGLEF1ROT.rotateAngleY = (float) (40 * Math.PI / 180);
        WINGLEF1ROT.rotateAngleX = (float) (5 * Math.PI / 180);
        WINGLEF2ROT.rotateAngleY = (float) (5 * Math.PI / 180);
        WINGLEF3ROT.rotateAngleY = (float) (30 * Math.PI / 180);
        WINGLEF4ROT.rotateAngleY = (float) (30 * Math.PI / 180);
        WINGLEF5ROT.rotateAngleY = (float) (0 * Math.PI / 180);
    }

    public void setLivingAnimations(EntityLiving par1EntityLiving, float par2, float par3, float par4) {
        super.setLivingAnimations(par1EntityLiving, par2, par3, par4);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}