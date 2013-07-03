package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import projectzulu.common.mobs.entity.EntityPelican;
import projectzulu.common.mobs.entity.EntityStates;

public class ModelPelican extends ModelBase {

    float heightToRaise = 10.0f;

    ModelRenderer BODYROT;
    private ModelRenderer HEADROT;
    private ModelRenderer TAILBASEROT;
    private ModelRenderer TAILFEATHER1ROT;
    private ModelRenderer TAILFEATHER3ROT;
    private ModelRenderer TAILFEATHER5ROT;
    private ModelRenderer WINGLEFROT1;
    private ModelRenderer WINGLEFROT2;
    private ModelRenderer WINGLEFROT3;
    private ModelRenderer WINGLEFROT4;
    private ModelRenderer WINGLEFROT5;
    private ModelRenderer WINGLEFROT6;
    private ModelRenderer WINGLEFROT7;
    private ModelRenderer WINGLEFROT8;
    private ModelRenderer WINGLEFROT9;
    private ModelRenderer WINGLEFROT10;
    private ModelRenderer WINGLEFROT11;
    private ModelRenderer WINGLEFROT12;
    private ModelRenderer WINGRIGROT1;
    private ModelRenderer WINGRIGROT2;
    private ModelRenderer WINGRIGROT3;
    private ModelRenderer WINGRIGROT4;
    private ModelRenderer WINGRIGROT5;
    private ModelRenderer WINGRIGROT6;
    private ModelRenderer WINGRIGROT7;
    private ModelRenderer WINGRIGROT8;
    private ModelRenderer WINGRIGROT9;
    private ModelRenderer WINGRIGROT10;
    private ModelRenderer WINGRIGROT11;
    private ModelRenderer WINGRIGROT12;
    private ModelRenderer LEGLEFTOPROT;
    private ModelRenderer LEGLEFBOTROT;
    private ModelRenderer FOOTLEF1ROT;
    private ModelRenderer FOOTLEF2ROT;
    private ModelRenderer FOOTLEF3ROT;
    private ModelRenderer LEGRIGTOPROT;
    private ModelRenderer LEGRIGBOTROT;
    private ModelRenderer FOOTRIG1ROT;
    private ModelRenderer FOOTRIG2ROT;
    private ModelRenderer FOOTRIG3ROT;
    private ModelRenderer NECK1ROT;
    private ModelRenderer NECK2ROT;
    private ModelRenderer NECK3ROT;
    private ModelRenderer NECK4ROT;
    private ModelRenderer NECK5ROT;
    private ModelRenderer NECK6ROT;
    private ModelRenderer HORN2ROT;
    private ModelRenderer TAILFEATHER2ROT;
    private ModelRenderer TAILFEATHER4ROT;
    private ModelRenderer NECK7ROT;
    private ModelRenderer NECK8ROT;
    private ModelRenderer NECK9ROT;
    private ModelRenderer NECK10ROT;

    public ModelPelican() {
	textureWidth = 64;
	textureHeight = 32;
	setTextureOffset("BODYROT.body", 0, 20);
	setTextureOffset("BODYROT.headfrill", 0, 14);
	setTextureOffset("BODYROT.tailfrill", 0, 3);
	setTextureOffset("BODYROT.tailfrill2", 0, 8);
	setTextureOffset("TAILBASEROT.tailbase2", 8, 8);
	setTextureOffset("TAILFEATHER3ROT.tailfeather3", 9, 5);
	setTextureOffset("TAILFEATHER1ROT.tailfeather1", 9, 5);
	setTextureOffset("TAILFEATHER5ROT.tailfeather5", 9, 5);
	setTextureOffset("TAILFEATHER2ROT.tailfeather2", 9, 5);
	setTextureOffset("TAILFEATHER4ROT.tailfeather4", 9, 5);
	setTextureOffset("NECK1ROT.neck1", 41, 0);
	setTextureOffset("NECK2ROT.neck2", 37, 4);
	setTextureOffset("NECK3ROT.neck3", 38, 4);
	setTextureOffset("NECK4ROT.neck4", 39, 4);
	setTextureOffset("NECK5ROT.neck5", 40, 4);
	setTextureOffset("NECK6ROT.neck6", 37, 4);
	setTextureOffset("NECK7ROT.neck7", 38, 4);
	setTextureOffset("NECK8ROT.neck8", 39, 4);
	setTextureOffset("NECK9ROT.neck9", 40, 4);
	setTextureOffset("NECK10ROT.neck10", 37, 4);
	setTextureOffset("HEADROT.head", 10, 12);
	setTextureOffset("HEADROT.beaktop", 46, 8);
	setTextureOffset("HEADROT.beakbot3", 56, 26);
	setTextureOffset("HEADROT.beakbot1", 48, 15);
	setTextureOffset("HEADROT.beakbot2", 52, 21);
	setTextureOffset("WINGLEFROT1.winglef1", 22, 24);
	setTextureOffset("WINGLEFROT2.winglef2", 23, 24);
	setTextureOffset("WINGLEFROT3.winglef3", 24, 24);
	setTextureOffset("WINGLEFROT4.winglef4", 25, 24);
	setTextureOffset("WINGLEFROT5.winglef5", 22, 24);
	setTextureOffset("WINGLEFROT6.winglef6", 23, 24);
	setTextureOffset("WINGLEFROT7.winglef7", 24, 24);
	setTextureOffset("WINGLEFROT8.winglef8", 25, 24);
	setTextureOffset("WINGLEFROT9.winglef9", 23, 6);
	setTextureOffset("WINGLEFROT10.winglef10", 23, 14);
	setTextureOffset("WINGLEFROT11.winglef11", 23, 6);
	setTextureOffset("WINGLEFROT12.winglef12", 23, 14);
	setTextureOffset("WINGRIGROT1.wingrig1", 22, 24);
	setTextureOffset("WINGRIGROT2.wingrig2", 23, 24);
	setTextureOffset("WINGRIGROT3.wingrig3", 24, 24);
	setTextureOffset("WINGRIGROT4.wingrig4", 25, 24);
	setTextureOffset("WINGRIGROT5.wingrig5", 22, 24);
	setTextureOffset("WINGRIGROT6.wingrig6", 23, 24);
	setTextureOffset("WINGRIGROT7.wingrig7", 24, 24);
	setTextureOffset("WINGRIGROT8.wingrig8", 25, 24);
	setTextureOffset("WINGRIGROT9.wingrig9", 23, 6);
	setTextureOffset("WINGRIGROT10.wingrig10", 23, 14);
	setTextureOffset("WINGRIGROT11.wingrig11", 23, 6);
	setTextureOffset("WINGRIGROT12.wingrig12", 23, 14);
	setTextureOffset("LEGRIGTOPROT.legrigtop", 28, 0);
	setTextureOffset("LEGRIGBOTROT.legrigbot", 32, 0);
	setTextureOffset("FOOTRIG1ROT.footrig13", 36, 2);
	setTextureOffset("FOOTRIG1ROT.footrig12", 36, 0);
	setTextureOffset("FOOTRIG1ROT.footrig11", 36, 0);
	setTextureOffset("FOOTRIG2ROT.footrig23", 36, 2);
	setTextureOffset("FOOTRIG2ROT.footrig22", 36, 0);
	setTextureOffset("FOOTRIG2ROT.footrig21", 36, 0);
	setTextureOffset("FOOTRIG3ROT.footrig33", 36, 2);
	setTextureOffset("FOOTRIG3ROT.footrig31", 36, 0);
	setTextureOffset("FOOTRIG3ROT.footrig32", 36, 0);
	setTextureOffset("LEGLEFTOPROT.legleftop", 28, 0);
	setTextureOffset("LEGLEFBOTROT.leglefbot", 32, 0);
	setTextureOffset("FOOTLEF1ROT.footlef13", 36, 2);
	setTextureOffset("FOOTLEF1ROT.footlef12", 36, 0);
	setTextureOffset("FOOTLEF1ROT.footlef11", 36, 0);
	setTextureOffset("FOOTLEF2ROT.footlef23", 36, 2);
	setTextureOffset("FOOTLEF2ROT.footlef22", 36, 0);
	setTextureOffset("FOOTLEF2ROT.footlef21", 36, 0);
	setTextureOffset("FOOTLEF3ROT.footlef33", 36, 2);
	setTextureOffset("FOOTLEF3ROT.footlef31", 36, 0);
	setTextureOffset("FOOTLEF3ROT.footlef32", 36, 0);

	BODYROT = new ModelRenderer(this, "BODYROT");
	BODYROT.setRotationPoint(0F, 18F - heightToRaise, 2.5F);
	setRotation(BODYROT, 0F, 0F, 0F);
	BODYROT.mirror = true;
	BODYROT.addBox("body", -2F, -5F, -5.5F, 4, 5, 7);
	BODYROT.addBox("headfrill", -2F, -5F, -6.5F, 4, 4, 1);
	BODYROT.addBox("tailfrill", -2F, -5F, 1.5F, 4, 4, 1);
	BODYROT.addBox("tailfrill2", -1.5F, -5F, 2.5F, 3, 3, 1);
	TAILBASEROT = new ModelRenderer(this, "TAILBASEROT");
	TAILBASEROT.setRotationPoint(0F, -3.5F, 3.5F);
	setRotation(TAILBASEROT, 0F, 0F, 0F);
	TAILBASEROT.mirror = true;
	TAILBASEROT.addBox("tailbase2", -1F, -1F, 0F, 2, 2, 1);
	TAILFEATHER3ROT = new ModelRenderer(this, "TAILFEATHER3ROT");
	TAILFEATHER3ROT.setRotationPoint(0F, 0F, 1F);
	setRotation(TAILFEATHER3ROT, 0F, 0F, 0F);
	TAILFEATHER3ROT.mirror = true;
	TAILFEATHER3ROT.addBox("tailfeather3", -0.5F, -0.5F, 0F, 1, 1, 6);
	TAILBASEROT.addChild(TAILFEATHER3ROT);
	TAILFEATHER1ROT = new ModelRenderer(this, "TAILFEATHER1ROT");
	TAILFEATHER1ROT.setRotationPoint(1F, 0F, 1F);
	setRotation(TAILFEATHER1ROT, 0F, 0.7853982F, 0F);
	TAILFEATHER1ROT.mirror = true;
	TAILFEATHER1ROT.addBox("tailfeather1", -0.5F, -0.5F, 0F, 1, 1, 6);
	TAILBASEROT.addChild(TAILFEATHER1ROT);
	TAILFEATHER5ROT = new ModelRenderer(this, "TAILFEATHER5ROT");
	TAILFEATHER5ROT.setRotationPoint(-1F, 0F, 1F);
	setRotation(TAILFEATHER5ROT, 0F, -0.7853982F, 0F);
	TAILFEATHER5ROT.mirror = true;
	TAILFEATHER5ROT.addBox("tailfeather5", -0.5F, -0.5F, 0F, 1, 1, 6);
	TAILBASEROT.addChild(TAILFEATHER5ROT);
	TAILFEATHER2ROT = new ModelRenderer(this, "TAILFEATHER2ROT");
	TAILFEATHER2ROT.setRotationPoint(0F, 0F, 1F);
	setRotation(TAILFEATHER2ROT, 0.5235988F, 0F, 0F);
	TAILFEATHER2ROT.mirror = true;
	TAILFEATHER2ROT.addBox("tailfeather2", -0.5F, -0.5F, 0F, 1, 1, 6);
	TAILBASEROT.addChild(TAILFEATHER2ROT);
	TAILFEATHER4ROT = new ModelRenderer(this, "TAILFEATHER4ROT");
	TAILFEATHER4ROT.setRotationPoint(0F, 0F, 1F);
	setRotation(TAILFEATHER4ROT, -0.5235988F, 0F, 0F);
	TAILFEATHER4ROT.mirror = true;
	TAILFEATHER4ROT.addBox("tailfeather4", -0.5F, -0.5F, 0F, 1, 1, 6);
	TAILBASEROT.addChild(TAILFEATHER4ROT);
	BODYROT.addChild(TAILBASEROT);
	NECK1ROT = new ModelRenderer(this, "NECK1ROT");
	NECK1ROT.setRotationPoint(0F, -3.5F, -6.5F);
	setRotation(NECK1ROT, 0F, 0F, 0F);
	NECK1ROT.mirror = true;
	NECK1ROT.addBox("neck1", -1.5F, -1.5F, -1F, 3, 3, 1);
	NECK2ROT = new ModelRenderer(this, "NECK2ROT");
	NECK2ROT.setRotationPoint(0F, 0F, -1F);
	setRotation(NECK2ROT, 0F, 0F, 0F);
	NECK2ROT.mirror = true;
	NECK2ROT.addBox("neck2", -1.5F, -1.5F, -1F, 3, 2, 1);
	NECK3ROT = new ModelRenderer(this, "NECK3ROT");
	NECK3ROT.setRotationPoint(0F, 0F, -1F);
	setRotation(NECK3ROT, 0F, 0F, 0F);
	NECK3ROT.mirror = true;
	NECK3ROT.addBox("neck3", -1.5F, -1.5F, -1F, 3, 2, 1);
	NECK4ROT = new ModelRenderer(this, "NECK4ROT");
	NECK4ROT.setRotationPoint(0F, 0F, -1F);
	setRotation(NECK4ROT, 0F, 0F, 0F);
	NECK4ROT.mirror = true;
	NECK4ROT.addBox("neck4", -1.5F, -1.5F, -1F, 3, 2, 1);
	NECK5ROT = new ModelRenderer(this, "NECK5ROT");
	NECK5ROT.setRotationPoint(0F, 0F, -1F);
	setRotation(NECK5ROT, 0F, 0F, 0F);
	NECK5ROT.mirror = true;
	NECK5ROT.addBox("neck5", -1.5F, -1.5F, -1F, 3, 2, 1);
	NECK6ROT = new ModelRenderer(this, "NECK6ROT");
	NECK6ROT.setRotationPoint(0F, 0F, -1F);
	setRotation(NECK6ROT, 0F, 0F, 0F);
	NECK6ROT.mirror = true;
	NECK6ROT.addBox("neck6", -1.5F, -1.5F, -1F, 3, 2, 1);
	NECK7ROT = new ModelRenderer(this, "NECK7ROT");
	NECK7ROT.setRotationPoint(0F, 0F, -1F);
	setRotation(NECK7ROT, 0F, 0F, 0F);
	NECK7ROT.mirror = true;
	NECK7ROT.addBox("neck7", -1.5F, -1.5F, -1F, 3, 2, 1);
	NECK8ROT = new ModelRenderer(this, "NECK8ROT");
	NECK8ROT.setRotationPoint(0F, 0F, -1F);
	setRotation(NECK8ROT, 0F, 0F, 0F);
	NECK8ROT.mirror = true;
	NECK8ROT.addBox("neck8", -1.5F, -1.5F, -1F, 3, 2, 1);
	NECK9ROT = new ModelRenderer(this, "NECK9ROT");
	NECK9ROT.setRotationPoint(0F, 0F, -1F);
	setRotation(NECK9ROT, 0F, 0F, 0F);
	NECK9ROT.mirror = true;
	NECK9ROT.addBox("neck9", -1.5F, -1.5F, -1F, 3, 2, 1);
	NECK10ROT = new ModelRenderer(this, "NECK10ROT");
	NECK10ROT.setRotationPoint(0F, 0F, -1F);
	setRotation(NECK10ROT, 0F, 0F, 0F);
	NECK10ROT.mirror = true;
	NECK10ROT.addBox("neck10", -1.5F, -1.5F, -1F, 3, 2, 1);
	HEADROT = new ModelRenderer(this, "HEADROT");
	HEADROT.setRotationPoint(0F, -1F, -2F);
	setRotation(HEADROT, 0F, 0F, 0F);
	HEADROT.mirror = true;
	HEADROT.addBox("head", -2F, -1F, -2F, 4, 4, 3);
	HEADROT.addBox("beaktop", -1.5F, -0.5F, -8F, 3, 1, 6);
	HEADROT.addBox("beakbot3", -0.5F, 2.5F, -5F, 1, 1, 3);
	HEADROT.addBox("beakbot1", -1.5F, 0.5F, -7F, 3, 1, 5);
	HEADROT.addBox("beakbot2", -1F, 1.5F, -6F, 2, 1, 4);
	HORN2ROT = new ModelRenderer(this, "HORN2ROT");
	HORN2ROT.setRotationPoint(0F, -2.5F, -3F);
	setRotation(HORN2ROT, -0.2617994F, 0F, 0F);
	HORN2ROT.mirror = true;
	HEADROT.addChild(HORN2ROT);
	NECK10ROT.addChild(HEADROT);
	NECK9ROT.addChild(NECK10ROT);
	NECK8ROT.addChild(NECK9ROT);
	NECK7ROT.addChild(NECK8ROT);
	NECK6ROT.addChild(NECK7ROT);
	NECK5ROT.addChild(NECK6ROT);
	NECK4ROT.addChild(NECK5ROT);
	NECK3ROT.addChild(NECK4ROT);
	NECK2ROT.addChild(NECK3ROT);
	NECK1ROT.addChild(NECK2ROT);
	BODYROT.addChild(NECK1ROT);
	WINGLEFROT1 = new ModelRenderer(this, "WINGLEFROT1");
	WINGLEFROT1.setRotationPoint(-2.5F, -3.5F, -5.5F);
	setRotation(WINGLEFROT1, 0F, 0F, 0F);
	WINGLEFROT1.mirror = true;
	WINGLEFROT1.addBox("winglef1", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGLEFROT2 = new ModelRenderer(this, "WINGLEFROT2");
	WINGLEFROT2.setRotationPoint(-1F, 0F, 0F);
	setRotation(WINGLEFROT2, 0F, 0F, 0F);
	WINGLEFROT2.mirror = true;
	WINGLEFROT2.addBox("winglef2", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGLEFROT3 = new ModelRenderer(this, "WINGLEFROT3");
	WINGLEFROT3.setRotationPoint(-1F, 0F, 0F);
	setRotation(WINGLEFROT3, 0F, 0F, 0F);
	WINGLEFROT3.mirror = true;
	WINGLEFROT3.addBox("winglef3", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGLEFROT4 = new ModelRenderer(this, "WINGLEFROT4");
	WINGLEFROT4.setRotationPoint(-1F, 0F, 0F);
	setRotation(WINGLEFROT4, 0F, 0.0698132F, 0F);
	WINGLEFROT4.mirror = true;
	WINGLEFROT4.addBox("winglef4", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGLEFROT5 = new ModelRenderer(this, "WINGLEFROT5");
	WINGLEFROT5.setRotationPoint(-1F, 0F, 0F);
	setRotation(WINGLEFROT5, 0F, 0F, 0F);
	WINGLEFROT5.mirror = true;
	WINGLEFROT5.addBox("winglef5", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGLEFROT6 = new ModelRenderer(this, "WINGLEFROT6");
	WINGLEFROT6.setRotationPoint(-1F, 0F, 0F);
	setRotation(WINGLEFROT6, 0F, 0F, 0F);
	WINGLEFROT6.mirror = true;
	WINGLEFROT6.addBox("winglef6", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGLEFROT7 = new ModelRenderer(this, "WINGLEFROT7");
	WINGLEFROT7.setRotationPoint(-1F, 0F, 0F);
	setRotation(WINGLEFROT7, 0F, 0F, 0F);
	WINGLEFROT7.mirror = true;
	WINGLEFROT7.addBox("winglef7", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGLEFROT8 = new ModelRenderer(this, "WINGLEFROT8");
	WINGLEFROT8.setRotationPoint(-1F, 0F, 0F);
	setRotation(WINGLEFROT8, 0F, 0F, 0F);
	WINGLEFROT8.mirror = true;
	WINGLEFROT8.addBox("winglef8", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGLEFROT9 = new ModelRenderer(this, "WINGLEFROT9");
	WINGLEFROT9.setRotationPoint(-1F, 0F, 0F);
	setRotation(WINGLEFROT9, 0F, 0F, 0F);
	WINGLEFROT9.mirror = true;
	WINGLEFROT9.addBox("winglef9", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGLEFROT10 = new ModelRenderer(this, "WINGLEFROT10");
	WINGLEFROT10.setRotationPoint(-1F, 0F, 0F);
	setRotation(WINGLEFROT10, 0F, 0F, 0F);
	WINGLEFROT10.mirror = true;
	WINGLEFROT10.addBox("winglef10", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGLEFROT11 = new ModelRenderer(this, "WINGLEFROT11");
	WINGLEFROT11.setRotationPoint(-1F, 0F, 0F);
	setRotation(WINGLEFROT11, 0F, 0F, 0F);
	WINGLEFROT11.mirror = true;
	WINGLEFROT11.addBox("winglef11", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGLEFROT12 = new ModelRenderer(this, "WINGLEFROT12");
	WINGLEFROT12.setRotationPoint(-1F, 0F, 0F);
	setRotation(WINGLEFROT12, 0F, 0F, 0F);
	WINGLEFROT12.mirror = true;
	WINGLEFROT12.addBox("winglef12", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGLEFROT11.addChild(WINGLEFROT12);
	WINGLEFROT10.addChild(WINGLEFROT11);
	WINGLEFROT9.addChild(WINGLEFROT10);
	WINGLEFROT8.addChild(WINGLEFROT9);
	WINGLEFROT7.addChild(WINGLEFROT8);
	WINGLEFROT6.addChild(WINGLEFROT7);
	WINGLEFROT5.addChild(WINGLEFROT6);
	WINGLEFROT4.addChild(WINGLEFROT5);
	WINGLEFROT3.addChild(WINGLEFROT4);
	WINGLEFROT2.addChild(WINGLEFROT3);
	WINGLEFROT1.addChild(WINGLEFROT2);
	BODYROT.addChild(WINGLEFROT1);
	WINGRIGROT1 = new ModelRenderer(this, "WINGRIGROT1");
	WINGRIGROT1.setRotationPoint(2.5F, -3.5F, -5.5F);
	setRotation(WINGRIGROT1, 0F, 0F, 0F);
	WINGRIGROT1.mirror = true;
	WINGRIGROT1.addBox("wingrig1", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGRIGROT2 = new ModelRenderer(this, "WINGRIGROT2");
	WINGRIGROT2.setRotationPoint(1F, 0F, 0F);
	setRotation(WINGRIGROT2, 0F, 0F, 0F);
	WINGRIGROT2.mirror = true;
	WINGRIGROT2.addBox("wingrig2", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGRIGROT3 = new ModelRenderer(this, "WINGRIGROT3");
	WINGRIGROT3.setRotationPoint(1F, 0F, 0F);
	setRotation(WINGRIGROT3, 0F, 0F, 0F);
	WINGRIGROT3.mirror = true;
	WINGRIGROT3.addBox("wingrig3", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGRIGROT4 = new ModelRenderer(this, "WINGRIGROT4");
	WINGRIGROT4.setRotationPoint(1F, 0F, 0F);
	setRotation(WINGRIGROT4, 0F, 0.0698132F, 0F);
	WINGRIGROT4.mirror = true;
	WINGRIGROT4.addBox("wingrig4", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGRIGROT5 = new ModelRenderer(this, "WINGRIGROT5");
	WINGRIGROT5.setRotationPoint(1F, 0F, 0F);
	setRotation(WINGRIGROT5, 0F, 0F, 0F);
	WINGRIGROT5.mirror = true;
	WINGRIGROT5.addBox("wingrig5", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGRIGROT6 = new ModelRenderer(this, "WINGRIGROT6");
	WINGRIGROT6.setRotationPoint(1F, 0F, 0F);
	setRotation(WINGRIGROT6, 0F, 0F, 0F);
	WINGRIGROT6.mirror = true;
	WINGRIGROT6.addBox("wingrig6", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGRIGROT7 = new ModelRenderer(this, "WINGRIGROT7");
	WINGRIGROT7.setRotationPoint(1F, 0F, 0F);
	setRotation(WINGRIGROT7, 0F, 0F, 0F);
	WINGRIGROT7.mirror = true;
	WINGRIGROT7.addBox("wingrig7", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGRIGROT8 = new ModelRenderer(this, "WINGRIGROT8");
	WINGRIGROT8.setRotationPoint(1F, 0F, 0F);
	setRotation(WINGRIGROT8, 0F, 0F, 0F);
	WINGRIGROT8.mirror = true;
	WINGRIGROT8.addBox("wingrig8", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGRIGROT9 = new ModelRenderer(this, "WINGRIGROT9");
	WINGRIGROT9.setRotationPoint(1F, 0F, 0F);
	setRotation(WINGRIGROT9, 0F, 0F, 0F);
	WINGRIGROT9.mirror = true;
	WINGRIGROT9.addBox("wingrig9", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGRIGROT10 = new ModelRenderer(this, "WINGRIGROT10");
	WINGRIGROT10.setRotationPoint(1F, 0F, 0F);
	setRotation(WINGRIGROT10, 0F, 0F, 0F);
	WINGRIGROT10.mirror = true;
	WINGRIGROT10.addBox("wingrig10", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGRIGROT11 = new ModelRenderer(this, "WINGRIGROT11");
	WINGRIGROT11.setRotationPoint(1F, 0F, 0F);
	setRotation(WINGRIGROT11, 0F, 0F, 0F);
	WINGRIGROT11.mirror = true;
	WINGRIGROT11.addBox("wingrig11", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGRIGROT12 = new ModelRenderer(this, "WINGRIGROT12");
	WINGRIGROT12.setRotationPoint(1F, 0F, 0F);
	setRotation(WINGRIGROT12, 0F, 0F, 0F);
	WINGRIGROT12.mirror = true;
	WINGRIGROT12.addBox("wingrig12", -0.5F, -0.5F, 0F, 1, 1, 7);
	WINGRIGROT11.addChild(WINGRIGROT12);
	WINGRIGROT10.addChild(WINGRIGROT11);
	WINGRIGROT9.addChild(WINGRIGROT10);
	WINGRIGROT8.addChild(WINGRIGROT9);
	WINGRIGROT7.addChild(WINGRIGROT8);
	WINGRIGROT6.addChild(WINGRIGROT7);
	WINGRIGROT5.addChild(WINGRIGROT6);
	WINGRIGROT4.addChild(WINGRIGROT5);
	WINGRIGROT3.addChild(WINGRIGROT4);
	WINGRIGROT2.addChild(WINGRIGROT3);
	WINGRIGROT1.addChild(WINGRIGROT2);
	BODYROT.addChild(WINGRIGROT1);
	LEGRIGTOPROT = new ModelRenderer(this, "LEGRIGTOPROT");
	LEGRIGTOPROT.setRotationPoint(1.5F, 1F, 0F);
	setRotation(LEGRIGTOPROT, 0F, 0F, 0F);
	LEGRIGTOPROT.mirror = true;
	LEGRIGTOPROT.addBox("legrigtop", -0.5F, -1F, -0.5F, 1, 3, 1);
	LEGRIGBOTROT = new ModelRenderer(this, "LEGRIGBOTROT");
	LEGRIGBOTROT.setRotationPoint(0F, 2F, 0F);
	setRotation(LEGRIGBOTROT, 0F, 0F, 0F);
	LEGRIGBOTROT.mirror = true;
	LEGRIGBOTROT.addBox("legrigbot", -0.5F, 0F, -0.5F, 1, 3, 1);
	FOOTRIG1ROT = new ModelRenderer(this, "FOOTRIG1ROT");
	FOOTRIG1ROT.setRotationPoint(0F, 2.5F, -0.5F);
	setRotation(FOOTRIG1ROT, 0F, 0.4363323F, 0F);
	FOOTRIG1ROT.mirror = true;
	FOOTRIG1ROT.addBox("footrig13", -0.5F, -0.5F, -3F, 1, 1, 1);
	FOOTRIG1ROT.addBox("footrig12", -0.5F, -0.5F, -2F, 1, 1, 1);
	FOOTRIG1ROT.addBox("footrig11", -0.5F, -0.5F, -1F, 1, 1, 1);
	LEGRIGBOTROT.addChild(FOOTRIG1ROT);
	FOOTRIG2ROT = new ModelRenderer(this, "FOOTRIG2ROT");
	FOOTRIG2ROT.setRotationPoint(0F, 2.5F, -0.5F);
	setRotation(FOOTRIG2ROT, 0F, -0.4363323F, 0F);
	FOOTRIG2ROT.mirror = true;
	FOOTRIG2ROT.addBox("footrig23", -0.5F, -0.5F, -3F, 1, 1, 1);
	FOOTRIG2ROT.addBox("footrig22", -0.5F, -0.5F, -2F, 1, 1, 1);
	FOOTRIG2ROT.addBox("footrig21", -0.5F, -0.5F, -1F, 1, 1, 1);
	LEGRIGBOTROT.addChild(FOOTRIG2ROT);
	FOOTRIG3ROT = new ModelRenderer(this, "FOOTRIG3ROT");
	FOOTRIG3ROT.setRotationPoint(0F, 2.5F, 0.5F);
	setRotation(FOOTRIG3ROT, 0F, 0F, 0F);
	FOOTRIG3ROT.mirror = true;
	FOOTRIG3ROT.addBox("footrig33", -0.5F, -0.5F, 2F, 1, 1, 1);
	FOOTRIG3ROT.addBox("footrig31", -0.5F, -0.5F, 0F, 1, 1, 1);
	FOOTRIG3ROT.addBox("footrig32", -0.5F, -0.5F, 1F, 1, 1, 1);
	LEGRIGBOTROT.addChild(FOOTRIG3ROT);
	LEGRIGTOPROT.addChild(LEGRIGBOTROT);
	BODYROT.addChild(LEGRIGTOPROT);
	LEGLEFTOPROT = new ModelRenderer(this, "LEGLEFTOPROT");
	LEGLEFTOPROT.setRotationPoint(-1.5F, 0F, 0F);
	setRotation(LEGLEFTOPROT, 0F, 0F, 0F);
	LEGLEFTOPROT.mirror = true;
	LEGLEFTOPROT.addBox("legleftop", -0.5F, 0F, -0.5F, 1, 3, 1);
	LEGLEFBOTROT = new ModelRenderer(this, "LEGLEFBOTROT");
	LEGLEFBOTROT.setRotationPoint(0F, 3F, 0F);
	setRotation(LEGLEFBOTROT, 0F, 0F, 0F);
	LEGLEFBOTROT.mirror = true;
	LEGLEFBOTROT.addBox("leglefbot", -0.5F, 0F, -0.5F, 1, 3, 1);
	FOOTLEF1ROT = new ModelRenderer(this, "FOOTLEF1ROT");
	FOOTLEF1ROT.setRotationPoint(0F, 2.5F, -0.5F);
	setRotation(FOOTLEF1ROT, 0F, 0.4363323F, 0F);
	FOOTLEF1ROT.mirror = true;
	FOOTLEF1ROT.addBox("footlef13", -0.5F, -0.5F, -3F, 1, 1, 1);
	FOOTLEF1ROT.addBox("footlef12", -0.5F, -0.5F, -2F, 1, 1, 1);
	FOOTLEF1ROT.addBox("footlef11", -0.5F, -0.5F, -1F, 1, 1, 1);
	LEGLEFBOTROT.addChild(FOOTLEF1ROT);
	FOOTLEF2ROT = new ModelRenderer(this, "FOOTLEF2ROT");
	FOOTLEF2ROT.setRotationPoint(0F, 2.5F, -0.5F);
	setRotation(FOOTLEF2ROT, 0F, -0.4363323F, 0F);
	FOOTLEF2ROT.mirror = true;
	FOOTLEF2ROT.addBox("footlef23", -0.5F, -0.5F, -3F, 1, 1, 1);
	FOOTLEF2ROT.addBox("footlef22", -0.5F, -0.5F, -2F, 1, 1, 1);
	FOOTLEF2ROT.addBox("footlef21", -0.5F, -0.5F, -1F, 1, 1, 1);
	LEGLEFBOTROT.addChild(FOOTLEF2ROT);
	FOOTLEF3ROT = new ModelRenderer(this, "FOOTLEF3ROT");
	FOOTLEF3ROT.setRotationPoint(0F, 2.5F, 0.5F);
	setRotation(FOOTLEF3ROT, 0F, 0F, 0F);
	FOOTLEF3ROT.mirror = true;
	FOOTLEF3ROT.addBox("footlef33", -0.5F, -0.5F, 2F, 1, 1, 1);
	FOOTLEF3ROT.addBox("footlef31", -0.5F, -0.5F, 0F, 1, 1, 1);
	FOOTLEF3ROT.addBox("footlef32", -0.5F, -0.5F, 1F, 1, 1, 1);
	LEGLEFBOTROT.addChild(FOOTLEF3ROT);
	LEGLEFTOPROT.addChild(LEGLEFBOTROT);
	BODYROT.addChild(LEGLEFTOPROT);

    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
	super.render(entity, f, f1, f2, f3, f4, f5);
	float scale = 1.6f * f5;
	setRotationAngles(f, f1, f2, f3, f4, scale, entity);
	float field_78145_g = 8.0F;
	float field_78151_h = 4.0F;

	if (this.isChild) {
	    float var8 = 2.0F;
	    GL11.glPushMatrix();
	    GL11.glTranslatef(0.0F, field_78145_g * scale, field_78151_h * scale);
	    // HEADROT.render(scale);
	    GL11.glPopMatrix();
	    GL11.glPushMatrix();
	    GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
	    GL11.glTranslatef(0.0F, 24.0F * scale, 0.0F);
	    BODYROT.render(scale);
	    GL11.glPopMatrix();
	} else {
	    BODYROT.render(scale);
	}
    }

    @Override
    public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
	EntityPelican var5 = (EntityPelican) par1EntityLiving;

	if (var5.isEntityGrounded()) {
	    HEADROT.rotateAngleX = (float) (+30 * Math.PI / 180 - 10 * 2 * par3 * Math.PI / 180
		    * MathHelper.sin(par2 * 0.6662F * 1.0f));
	    BODYROT.rotateAngleX = (float) (-30 * Math.PI / 180);
	    WINGLEFROT1.rotateAngleX = (float) (+10 * Math.PI / 180);
	    WINGRIGROT1.rotateAngleX = (float) (+10 * Math.PI / 180);

	    NECK1ROT.rotateAngleX = (float) (+10 * Math.PI / 180 + 10 * 2 * par3 * Math.PI / 180
		    * MathHelper.sin(par2 * 0.6662F * 1.0f));
	    NECK2ROT.rotateAngleX = (float) (-40 * Math.PI / 180);
	    NECK3ROT.rotateAngleX = (float) (-40 * Math.PI / 180);
	    NECK4ROT.rotateAngleX = (float) (-30 * Math.PI / 180);
	    NECK5ROT.rotateAngleX = (float) (+10 * Math.PI / 180);
	    NECK6ROT.rotateAngleX = (float) (+10 * Math.PI / 180);
	    NECK7ROT.rotateAngleX = (float) (+25 * Math.PI / 180);
	    NECK8ROT.rotateAngleX = (float) (+35 * Math.PI / 180);
	    NECK9ROT.rotateAngleX = (float) (+45 * Math.PI / 180);
	    NECK10ROT.rotateAngleX = (float) (0 * Math.PI / 180);

	    LEGLEFTOPROT.rotateAngleX = (float) (+10 * Math.PI / 180 + 20 * 2 * par3 * Math.PI / 180
		    * MathHelper.sin(par2 * 0.6662F * 1.0f));
	    LEGLEFTOPROT.rotateAngleY = (float) (+0 * Math.PI / 180);
	    LEGLEFBOTROT.rotateAngleX = (float) (+20 * Math.PI / 180 + 10 * 2 * par3 * Math.PI / 180
		    * MathHelper.sin(par2 * 0.6662F * 1.0f));
	    FOOTLEF1ROT.rotateAngleX = (float) (0 * Math.PI / 180);
	    FOOTLEF2ROT.rotateAngleX = (float) (0 * Math.PI / 180);
	    FOOTLEF3ROT.rotateAngleX = (float) (-0 * Math.PI / 180);

	    LEGRIGTOPROT.rotateAngleX = (float) (+10 * Math.PI / 180 + 20 * par3 * Math.PI / 180
		    * MathHelper.sin((float) (par2 * 0.6662F * 1.0f + Math.PI)));
	    LEGRIGTOPROT.rotateAngleY = (float) (0 * Math.PI / 180);
	    LEGRIGBOTROT.rotateAngleX = (float) (+20 * Math.PI / 180 + 10 * par3 * Math.PI / 180
		    * MathHelper.sin((float) (par2 * 0.6662F * 1.0f + Math.PI)));
	    FOOTRIG1ROT.rotateAngleX = (float) (0 * Math.PI / 180);
	    FOOTRIG2ROT.rotateAngleX = (float) (0 * Math.PI / 180);
	    FOOTRIG3ROT.rotateAngleX = (float) (-0 * Math.PI / 180);

	    /* Left Wing */
	    WINGLEFROT1.rotateAngleZ = (float) (+90 * Math.PI / 180);
	    WINGLEFROT2.rotateAngleZ = (float) (0 * Math.PI / 180);
	    WINGLEFROT3.rotateAngleZ = (float) (-25 * Math.PI / 180);
	    WINGLEFROT4.rotateAngleZ = (float) (-25 * Math.PI / 180);
	    WINGLEFROT5.rotateAngleZ = (float) (-25 * Math.PI / 180);
	    WINGLEFROT6.rotateAngleZ = (float) (-60 * Math.PI / 180);
	    WINGLEFROT7.rotateAngleZ = (float) (-60 * Math.PI / 180);
	    WINGLEFROT8.rotateAngleZ = (float) (-60 * Math.PI / 180);
	    WINGLEFROT8.rotateAngleZ = (float) (-0 * Math.PI / 180);
	    WINGLEFROT10.rotateAngleZ = (float) (-0 * Math.PI / 180);
	    WINGLEFROT11.rotateAngleZ = (float) (-00 * Math.PI / 180);
	    WINGLEFROT12.rotateAngleZ = (float) (-0 * Math.PI / 180);

	    WINGLEFROT1.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGLEFROT2.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGLEFROT3.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGLEFROT4.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGLEFROT5.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGLEFROT6.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGLEFROT7.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGLEFROT8.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGLEFROT8.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGLEFROT10.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGLEFROT11.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGLEFROT12.rotateAngleX = (float) (+5 * Math.PI / 180);

	    WINGLEFROT5.rotateAngleY = (float) (+0 * Math.PI / 180);
	    WINGLEFROT8.rotateAngleY = (float) (+0 * Math.PI / 180);
	    WINGLEFROT11.rotateAngleY = (float) (+0 * Math.PI / 180);

	    /* Right Wing */
	    WINGRIGROT1.rotateAngleZ = (float) (-90 * Math.PI / 180);
	    WINGRIGROT2.rotateAngleZ = (float) (0 * Math.PI / 180);
	    WINGRIGROT3.rotateAngleZ = (float) (+25 * Math.PI / 180);
	    WINGRIGROT4.rotateAngleZ = (float) (+25 * Math.PI / 180);
	    WINGRIGROT5.rotateAngleZ = (float) (+25 * Math.PI / 180);
	    WINGRIGROT6.rotateAngleZ = (float) (+60 * Math.PI / 180);
	    WINGRIGROT7.rotateAngleZ = (float) (+60 * Math.PI / 180);
	    WINGRIGROT8.rotateAngleZ = (float) (+60 * Math.PI / 180);
	    WINGRIGROT8.rotateAngleZ = (float) (+0 * Math.PI / 180);
	    WINGRIGROT10.rotateAngleZ = (float) (+0 * Math.PI / 180);
	    WINGRIGROT11.rotateAngleZ = (float) (+0 * Math.PI / 180);
	    WINGRIGROT12.rotateAngleZ = (float) (+0 * Math.PI / 180);

	    WINGRIGROT1.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGRIGROT2.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGRIGROT3.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGRIGROT4.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGRIGROT5.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGRIGROT6.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGRIGROT7.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGRIGROT8.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGRIGROT8.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGRIGROT10.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGRIGROT11.rotateAngleX = (float) (+5 * Math.PI / 180);
	    WINGRIGROT12.rotateAngleX = (float) (+5 * Math.PI / 180);

	    WINGRIGROT1.rotateAngleY = (float) (-0 * Math.PI / 180);
	    WINGRIGROT5.rotateAngleY = (float) (+0 * Math.PI / 180);
	    WINGRIGROT8.rotateAngleY = (float) (+0 * Math.PI / 180);
	    WINGRIGROT11.rotateAngleY = (float) (+0 * Math.PI / 180);

	    TAILFEATHER1ROT.rotateAngleY = (float) (30 * Math.PI / 180 - 10 * Math.PI / 180 * par3);
	    TAILFEATHER5ROT.rotateAngleY = (float) (-30 * Math.PI / 180 + 10 * Math.PI / 180 * par3);

	} else {
	    if (var5.getEntityState() == EntityStates.attacking) {
		BODYROT.rotateAngleX = (float) (-30 * Math.PI / 180);
		WINGLEFROT1.rotateAngleX = (float) (+10 * Math.PI / 180);
		WINGRIGROT1.rotateAngleX = (float) (+10 * Math.PI / 180);

		NECK1ROT.rotateAngleX = (float) (+15 * Math.PI / 180);
		NECK2ROT.rotateAngleX = (float) (+15 * Math.PI / 180);
		NECK3ROT.rotateAngleX = (float) (+15 * Math.PI / 180);
		NECK4ROT.rotateAngleX = (float) (+15 * Math.PI / 180);
		NECK5ROT.rotateAngleX = (float) (-15 * Math.PI / 180);
		NECK6ROT.rotateAngleX = (float) (-15 * Math.PI / 180);
		NECK7ROT.rotateAngleX = (float) (+0 * Math.PI / 180);
		NECK8ROT.rotateAngleX = (float) (+0 * Math.PI / 180);
		NECK9ROT.rotateAngleX = (float) (+0 * Math.PI / 180);
		NECK10ROT.rotateAngleX = (float) (0 * Math.PI / 180);

		LEGLEFTOPROT.rotateAngleX = (float) (-20 * Math.PI / 180);
		LEGLEFTOPROT.rotateAngleY = (float) (0 * Math.PI / 180);
		LEGLEFBOTROT.rotateAngleX = (float) (+60 * Math.PI / 180);
		FOOTLEF1ROT.rotateAngleX = (float) (45 * Math.PI / 180 + 10 * Math.PI / 180
			* MathHelper.sin((float) (var5.worldObj.getWorldTime() * 0.6662F * 0.5f + Math.PI * 0 / 2)));
		FOOTLEF2ROT.rotateAngleX = (float) (45 * Math.PI / 180 + 10 * Math.PI / 180
			* MathHelper.sin((float) (var5.worldObj.getWorldTime() * 0.6662F * 0.5f + Math.PI * 0 / 2)));
		FOOTLEF3ROT.rotateAngleX = (float) (-45 * Math.PI / 180 - 10 * Math.PI / 180
			* MathHelper.sin((float) (var5.worldObj.getWorldTime() * 0.6662F * 0.5f + Math.PI * 0 / 2)));

		LEGRIGTOPROT.rotateAngleX = (float) (-20 * Math.PI / 180);
		LEGRIGTOPROT.rotateAngleY = (float) (0 * Math.PI / 180);
		LEGRIGBOTROT.rotateAngleX = (float) (60 * Math.PI / 180);
		FOOTRIG1ROT.rotateAngleX = (float) (45 * Math.PI / 180 + 10 * Math.PI / 180
			* MathHelper.sin((float) (var5.worldObj.getWorldTime() * 0.6662F * 0.5f + Math.PI * 0 / 2)));
		FOOTRIG2ROT.rotateAngleX = (float) (45 * Math.PI / 180 + 10 * Math.PI / 180
			* MathHelper.sin((float) (var5.worldObj.getWorldTime() * 0.6662F * 0.5f + Math.PI * 0 / 2)));
		FOOTRIG3ROT.rotateAngleX = (float) (-45 * Math.PI / 180 - 10 * Math.PI / 180
			* MathHelper.sin((float) (var5.worldObj.getWorldTime() * 0.6662F * 0.5f + Math.PI * 0 / 2)));

		/* Left Wing */
		WINGLEFROT1.rotateAngleZ = (float) (40 * Math.PI / 180 * MathHelper.sin((float) (var5.worldObj
			.getWorldTime() * 0.6662F * 0.5f + Math.PI * 0 / 2)));
		WINGLEFROT2.rotateAngleZ = 0;
		WINGLEFROT3.rotateAngleZ = 0;
		WINGLEFROT4.rotateAngleZ = 0;
		WINGLEFROT5.rotateAngleZ = 0;
		WINGLEFROT6.rotateAngleZ = (float) (-20 * Math.PI / 180 * Math.abs(MathHelper
			.sin((float) (var5.worldObj.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));
		WINGLEFROT7.rotateAngleZ = 0;
		WINGLEFROT8.rotateAngleZ = 0;
		WINGLEFROT8.rotateAngleZ = 0;
		WINGLEFROT10.rotateAngleZ = (float) (-25 * Math.PI / 180 * Math.abs(MathHelper
			.sin((float) (var5.worldObj.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));
		WINGLEFROT11.rotateAngleZ = 0;
		WINGLEFROT12.rotateAngleZ = 0;

		WINGLEFROT1.rotateAngleX = 0;
		WINGLEFROT2.rotateAngleX = 0;
		WINGLEFROT3.rotateAngleX = 0;
		WINGLEFROT4.rotateAngleX = 0;
		WINGLEFROT5.rotateAngleX = 0;
		WINGLEFROT6.rotateAngleX = 0;
		WINGLEFROT7.rotateAngleX = 0;
		WINGLEFROT8.rotateAngleX = 0;
		WINGLEFROT8.rotateAngleX = 0;
		WINGLEFROT10.rotateAngleX = 0;
		WINGLEFROT11.rotateAngleX = 0;
		WINGLEFROT12.rotateAngleX = 0;

		WINGLEFROT1.rotateAngleY = (float) (-4 * Math.PI / 180 * Math.abs(MathHelper.sin((float) (var5.worldObj
			.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));
		WINGLEFROT5.rotateAngleY = (float) (-5 * Math.PI / 180 * Math.abs(MathHelper.sin((float) (var5.worldObj
			.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));
		WINGLEFROT8.rotateAngleY = (float) (-6 * Math.PI / 180 * Math.abs(MathHelper.sin((float) (var5.worldObj
			.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));
		WINGLEFROT11.rotateAngleY = (float) (-7 * Math.PI / 180 * Math.abs(MathHelper
			.sin((float) (var5.worldObj.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));

		/* Right Wing */
		WINGRIGROT1.rotateAngleZ = (float) (-40 * Math.PI / 180 * MathHelper.sin((float) (var5.worldObj
			.getWorldTime() * 0.6662F * 0.5f + Math.PI * 0 / 2)));
		WINGRIGROT2.rotateAngleZ = 0;
		WINGRIGROT3.rotateAngleZ = 0;
		WINGRIGROT4.rotateAngleZ = 0;
		WINGRIGROT5.rotateAngleZ = 0;
		WINGRIGROT6.rotateAngleZ = (float) (+20 * Math.PI / 180 * Math.abs(MathHelper
			.sin((float) (var5.worldObj.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));
		WINGRIGROT7.rotateAngleZ = 0;
		WINGRIGROT8.rotateAngleZ = 0;
		WINGRIGROT8.rotateAngleZ = 0;
		WINGRIGROT10.rotateAngleZ = (float) (+25 * Math.PI / 180 * Math.abs(MathHelper
			.sin((float) (var5.worldObj.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));
		WINGRIGROT11.rotateAngleZ = 0;
		WINGRIGROT12.rotateAngleZ = 0;

		WINGRIGROT1.rotateAngleX = 0;
		WINGRIGROT2.rotateAngleX = 0;
		WINGRIGROT3.rotateAngleX = 0;
		WINGRIGROT4.rotateAngleX = 0;
		WINGRIGROT5.rotateAngleX = 0;
		WINGRIGROT6.rotateAngleX = 0;
		WINGRIGROT7.rotateAngleX = 0;
		WINGRIGROT8.rotateAngleX = 0;
		WINGRIGROT8.rotateAngleX = 0;
		WINGRIGROT10.rotateAngleX = 0;
		WINGRIGROT11.rotateAngleX = 0;
		WINGRIGROT12.rotateAngleX = 0;

		WINGRIGROT1.rotateAngleY = (float) (+4 * Math.PI / 180 * Math.abs(MathHelper.sin((float) (var5.worldObj
			.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));
		WINGRIGROT5.rotateAngleY = (float) (+5 * Math.PI / 180 * Math.abs(MathHelper.sin((float) (var5.worldObj
			.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));
		WINGRIGROT8.rotateAngleY = (float) (+6 * Math.PI / 180 * Math.abs(MathHelper.sin((float) (var5.worldObj
			.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));
		WINGRIGROT11.rotateAngleY = (float) (+7 * Math.PI / 180 * Math.abs(MathHelper
			.sin((float) (var5.worldObj.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));

		TAILFEATHER1ROT.rotateAngleY = (float) (45 * Math.PI / 180 - 10 * Math.PI / 180 * par3);
		TAILFEATHER5ROT.rotateAngleY = (float) (-45 * Math.PI / 180 + 10 * Math.PI / 180 * par3);
	    } else {
		BODYROT.rotateAngleX = (float) (0 * Math.PI / 180);
		WINGLEFROT1.rotateAngleX = (float) (0 * Math.PI / 180);
		WINGRIGROT1.rotateAngleX = (float) (0 * Math.PI / 180);

		NECK1ROT.rotateAngleX = (float) (-20 * Math.PI / 180);
		NECK2ROT.rotateAngleX = (float) (-50 * Math.PI / 180);
		NECK3ROT.rotateAngleX = (float) (-50 * Math.PI / 180);
		NECK4ROT.rotateAngleX = (float) (-40 * Math.PI / 180);
		NECK5ROT.rotateAngleX = (float) (+20 * Math.PI / 180);
		NECK6ROT.rotateAngleX = (float) (+20 * Math.PI / 180);
		NECK7ROT.rotateAngleX = (float) (+35 * Math.PI / 180);
		NECK8ROT.rotateAngleX = (float) (+45 * Math.PI / 180);
		NECK9ROT.rotateAngleX = (float) (+55 * Math.PI / 180);
		NECK10ROT.rotateAngleX = (float) (0 * Math.PI / 180);

		LEGLEFTOPROT.rotateAngleX = (float) (60 * Math.PI / 180);
		LEGLEFTOPROT.rotateAngleY = (float) (5 * Math.PI / 180);
		LEGLEFBOTROT.rotateAngleX = (float) (50 * Math.PI / 180);
		FOOTLEF1ROT.rotateAngleX = (float) (75 * Math.PI / 180);
		FOOTLEF2ROT.rotateAngleX = (float) (75 * Math.PI / 180);
		FOOTLEF3ROT.rotateAngleX = (float) (-75 * Math.PI / 180);

		LEGRIGTOPROT.rotateAngleX = (float) (60 * Math.PI / 180);
		LEGRIGTOPROT.rotateAngleY = (float) (-5 * Math.PI / 180);
		LEGRIGBOTROT.rotateAngleX = (float) (50 * Math.PI / 180);
		FOOTRIG1ROT.rotateAngleX = (float) (75 * Math.PI / 180);
		FOOTRIG2ROT.rotateAngleX = (float) (75 * Math.PI / 180);
		FOOTRIG3ROT.rotateAngleX = (float) (-75 * Math.PI / 180);

		/* Left Wing */
		WINGLEFROT1.rotateAngleZ = (float) (40 * Math.PI / 180 * MathHelper.sin((float) (var5.worldObj
			.getWorldTime() * 0.6662F * 0.5f + Math.PI * 0 / 2)));
		WINGLEFROT2.rotateAngleZ = 0;
		WINGLEFROT3.rotateAngleZ = 0;
		WINGLEFROT4.rotateAngleZ = 0;
		WINGLEFROT5.rotateAngleZ = 0;
		WINGLEFROT6.rotateAngleZ = (float) (-20 * Math.PI / 180 * Math.abs(MathHelper
			.sin((float) (var5.worldObj.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));
		WINGLEFROT7.rotateAngleZ = 0;
		WINGLEFROT8.rotateAngleZ = 0;
		WINGLEFROT8.rotateAngleZ = 0;
		WINGLEFROT10.rotateAngleZ = (float) (-25 * Math.PI / 180 * Math.abs(MathHelper
			.sin((float) (var5.worldObj.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));
		WINGLEFROT11.rotateAngleZ = 0;
		WINGLEFROT12.rotateAngleZ = 0;

		WINGLEFROT1.rotateAngleX = 0;
		WINGLEFROT2.rotateAngleX = 0;
		WINGLEFROT3.rotateAngleX = 0;
		WINGLEFROT4.rotateAngleX = 0;
		WINGLEFROT5.rotateAngleX = 0;
		WINGLEFROT6.rotateAngleX = 0;
		WINGLEFROT7.rotateAngleX = 0;
		WINGLEFROT8.rotateAngleX = 0;
		WINGLEFROT8.rotateAngleX = 0;
		WINGLEFROT10.rotateAngleX = 0;
		WINGLEFROT11.rotateAngleX = 0;
		WINGLEFROT12.rotateAngleX = 0;

		WINGLEFROT1.rotateAngleY = (float) (-4 * Math.PI / 180 * Math.abs(MathHelper.sin((float) (var5.worldObj
			.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));
		WINGLEFROT5.rotateAngleY = (float) (-5 * Math.PI / 180 * Math.abs(MathHelper.sin((float) (var5.worldObj
			.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));
		WINGLEFROT8.rotateAngleY = (float) (-6 * Math.PI / 180 * Math.abs(MathHelper.sin((float) (var5.worldObj
			.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));
		WINGLEFROT11.rotateAngleY = (float) (-7 * Math.PI / 180 * Math.abs(MathHelper
			.sin((float) (var5.worldObj.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));

		/* Right Wing */
		WINGRIGROT1.rotateAngleZ = (float) (-40 * Math.PI / 180 * MathHelper.sin((float) (var5.worldObj
			.getWorldTime() * 0.6662F * 0.5f + Math.PI * 0 / 2)));
		WINGRIGROT2.rotateAngleZ = 0;
		WINGRIGROT3.rotateAngleZ = 0;
		WINGRIGROT4.rotateAngleZ = 0;
		WINGRIGROT5.rotateAngleZ = 0;
		WINGRIGROT6.rotateAngleZ = (float) (+20 * Math.PI / 180 * Math.abs(MathHelper
			.sin((float) (var5.worldObj.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));
		WINGRIGROT7.rotateAngleZ = 0;
		WINGRIGROT8.rotateAngleZ = 0;
		WINGRIGROT8.rotateAngleZ = 0;
		WINGRIGROT10.rotateAngleZ = (float) (+25 * Math.PI / 180 * Math.abs(MathHelper
			.sin((float) (var5.worldObj.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));
		WINGRIGROT11.rotateAngleZ = 0;
		WINGRIGROT12.rotateAngleZ = 0;

		WINGRIGROT1.rotateAngleX = 0;
		WINGRIGROT2.rotateAngleX = 0;
		WINGRIGROT3.rotateAngleX = 0;
		WINGRIGROT4.rotateAngleX = 0;
		WINGRIGROT5.rotateAngleX = 0;
		WINGRIGROT6.rotateAngleX = 0;
		WINGRIGROT7.rotateAngleX = 0;
		WINGRIGROT8.rotateAngleX = 0;
		WINGRIGROT8.rotateAngleX = 0;
		WINGRIGROT10.rotateAngleX = 0;
		WINGRIGROT11.rotateAngleX = 0;
		WINGRIGROT12.rotateAngleX = 0;

		WINGRIGROT1.rotateAngleY = (float) (+4 * Math.PI / 180 * Math.abs(MathHelper.sin((float) (var5.worldObj
			.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));
		WINGRIGROT5.rotateAngleY = (float) (+5 * Math.PI / 180 * Math.abs(MathHelper.sin((float) (var5.worldObj
			.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));
		WINGRIGROT8.rotateAngleY = (float) (+6 * Math.PI / 180 * Math.abs(MathHelper.sin((float) (var5.worldObj
			.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));
		WINGRIGROT11.rotateAngleY = (float) (+7 * Math.PI / 180 * Math.abs(MathHelper
			.sin((float) (var5.worldObj.getWorldTime() * 0.6662F * 0.5f / 2 + Math.PI * 3 / 4))));

		TAILFEATHER1ROT.rotateAngleY = (float) (45 * Math.PI / 180 - 10 * Math.PI / 180 * par3);
		TAILFEATHER5ROT.rotateAngleY = (float) (-45 * Math.PI / 180 + 10 * Math.PI / 180 * par3);
	    }
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
	EntityPelican var5 = (EntityPelican) par7Entity;
	float lookingDirX = Math.min(Math.max(f4, -30), +30) * (float) (Math.PI / 180f);
	if (var5.isEntityGrounded()) {
	    HEADROT.rotateAngleX = (float) (+30 * Math.PI / 180 - 10 * 2 * f1 * Math.PI / 180
		    * MathHelper.sin(f * 0.6662F * 1.0f))
		    + lookingDirX;
	} else {
	    if (var5.getEntityState() == EntityStates.attacking) {
		HEADROT.rotateAngleX = (float) (+30 * Math.PI / 180) + lookingDirX;
	    } else {
		HEADROT.rotateAngleX = (float) (0 * Math.PI / 180) + lookingDirX;
	    }
	}
	HEADROT.rotateAngleY = Math.min(Math.max(f3, -30), +30) * (float) (Math.PI / 180f);
    }
}
