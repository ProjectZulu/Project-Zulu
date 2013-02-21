package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import projectzulu.common.mobs.entity.EntityFrog;


public class ModelFrog extends ModelBase
{
	//fields
	ModelRenderer BODYROT;
	private ModelRenderer	LEG2TOPROT;
	private ModelRenderer	LEG2BOTROT;
	private ModelRenderer	FOOTROT2;
	private ModelRenderer	LEG1TOPROT;
	private ModelRenderer	LEG1BOTROT;
	private ModelRenderer	FOOTROT1;
	private ModelRenderer	LEG3TOPROT;
	private ModelRenderer	LEG3BOTROT;
	private ModelRenderer	FOOTROT3;
	private ModelRenderer	HEADROT;
	private ModelRenderer	MOUTHTOPRPT;
	private ModelRenderer	MOUTHBOTROT;
	private ModelRenderer	LEG4TOPROT;
	private ModelRenderer	LEG4BOTROT;
	private ModelRenderer	FOOTROT4;
	private float heightToRaise = -30;
	public ModelFrog()
	{
		textureWidth = 64;
		textureHeight = 32;
		setTextureOffset("BODYROT.body", 0, 18);
		setTextureOffset("BODYROT.backdeco2", 0, 12);
		setTextureOffset("LEG2TOPROT.legtop2", 32, 18);
		setTextureOffset("LEG2BOTROT.legbot2", 32, 24);
		setTextureOffset("FOOTROT2.backfoot2", 38, 18);
		setTextureOffset("FOOTROT2.toe21", 38, 21);
		setTextureOffset("FOOTROT2.toe22", 38, 23);
		setTextureOffset("FOOTROT2.toe23", 38, 21);
		setTextureOffset("LEG1TOPROT.legtop1", 32, 18);
		setTextureOffset("LEG1BOTROT.legbot1", 32, 24);
		setTextureOffset("FOOTROT1.backfoot1", 38, 18);
		setTextureOffset("FOOTROT1.toe11", 38, 21);
		setTextureOffset("FOOTROT1.toe12", 38, 23);
		setTextureOffset("FOOTROT1.toe13", 38, 21);
		setTextureOffset("LEG3TOPROT.legtop3", 32, 18);
		setTextureOffset("LEG3BOTROT.legbot3", 32, 24);
		setTextureOffset("FOOTROT3.backfoot3", 38, 18);
		setTextureOffset("FOOTROT3.toe31", 38, 21);
		setTextureOffset("FOOTROT3.toe32", 38, 23);
		setTextureOffset("FOOTROT3.toe33", 38, 21);
		setTextureOffset("HEADROT.eyelef", 0, 0);
		setTextureOffset("HEADROT.eyerig", 6, 0);
		setTextureOffset("HEADROT.headdecomid", 28, 0);
		setTextureOffset("HEADROT.headdecolef1", 12, 0);
		setTextureOffset("HEADROT.backdeco1", 24, 7);
		setTextureOffset("HEADROT.headdecorig1", 20, 0);
		setTextureOffset("MOUTHTOPRPT.mouthtop0", 46, 0);
		setTextureOffset("MOUTHTOPRPT.mouthtop1", 50, 4);
		setTextureOffset("MOUTHTOPRPT.mouthtop2", 52, 8);
		setTextureOffset("MOUTHTOPRPT.mouthtop3", 54, 11);
		setTextureOffset("MOUTHTOPRPT.mouthtop4", 56, 14);
		setTextureOffset("MOUTHBOTROT.mouthbot0", 46, 16);
		setTextureOffset("MOUTHBOTROT.mouthbot1", 50, 21);
		setTextureOffset("MOUTHBOTROT.mouthbot2", 52, 24);
		setTextureOffset("MOUTHBOTROT.mouthbot3", 54, 27);
		setTextureOffset("MOUTHBOTROT.mouthbot4", 46, 29);
		setTextureOffset("LEG4TOPROT.legtop4", 32, 18);
		setTextureOffset("LEG4BOTROT.legbot4", 32, 24);
		setTextureOffset("FOOTROT4.backfoot4", 38, 18);
		setTextureOffset("FOOTROT4.toe41", 38, 21);
		setTextureOffset("FOOTROT4.toe42", 38, 23);
		setTextureOffset("FOOTROT4.toe43", 38, 21);

		BODYROT = new ModelRenderer(this, "BODYROT");
		BODYROT.setRotationPoint(0F, 15F-heightToRaise, 7F);
		setRotation(BODYROT, 0F, 0F, 0F);
		BODYROT.mirror = true;
		BODYROT.addBox("body", -3.5F, -3F, -9F, 7, 5, 9);
		BODYROT.addBox("backdeco2", -2.5F, -4F, -7F, 5, 1, 5);
		LEG2TOPROT = new ModelRenderer(this, "LEG2TOPROT");
		LEG2TOPROT.setRotationPoint(4F, 0F, -2F);
		setRotation(LEG2TOPROT, 0F, 0F, 0F);
		LEG2TOPROT.mirror = true;
		LEG2TOPROT.addBox("legtop2", -0.5F, 0F, -1F, 1, 4, 2);
		LEG2BOTROT = new ModelRenderer(this, "LEG2BOTROT");
		LEG2BOTROT.setRotationPoint(0F, 4F, 0F);
		setRotation(LEG2BOTROT, 0F, 0F, 0F);
		LEG2BOTROT.mirror = true;
		LEG2BOTROT.addBox("legbot2", -0.5F, 0F, -1F, 1, 4, 2);
		FOOTROT2 = new ModelRenderer(this, "FOOTROT2");
		FOOTROT2.setRotationPoint(0F, 4.5F, 0F);
		setRotation(FOOTROT2, 0F, 0F, 0F);
		FOOTROT2.mirror = true;
		FOOTROT2.addBox("backfoot2", -1F, -0.5F, 0F, 2, 1, 2);
		FOOTROT2.addBox("toe21", -2F, -0.5F, -0.5F, 2, 1, 1);
		FOOTROT2.addBox("toe22", -0.5F, -0.5F, -2F, 1, 1, 2);
		FOOTROT2.addBox("toe23", 0F, -0.5F, -0.5F, 2, 1, 1);
		LEG2BOTROT.addChild(FOOTROT2);
		LEG2TOPROT.addChild(LEG2BOTROT);
		BODYROT.addChild(LEG2TOPROT);
		LEG1TOPROT = new ModelRenderer(this, "LEG1TOPROT");
		LEG1TOPROT.setRotationPoint(-4F, 0F, -2F);
		setRotation(LEG1TOPROT, 0F, 0F, 0F);
		LEG1TOPROT.mirror = true;
		LEG1TOPROT.addBox("legtop1", -0.5F, 0F, -1F, 1, 4, 2);
		LEG1BOTROT = new ModelRenderer(this, "LEG1BOTROT");
		LEG1BOTROT.setRotationPoint(0F, 4F, 0F);
		setRotation(LEG1BOTROT, 0F, 0F, 0F);
		LEG1BOTROT.mirror = true;
		LEG1BOTROT.addBox("legbot1", -0.5F, 0F, -1F, 1, 4, 2);
		FOOTROT1 = new ModelRenderer(this, "FOOTROT1");
		FOOTROT1.setRotationPoint(0F, 4.5F, 0F);
		setRotation(FOOTROT1, 0F, 0F, 0F);
		FOOTROT1.mirror = true;
		FOOTROT1.addBox("backfoot1", -1F, -0.5F, 0F, 2, 1, 2);
		FOOTROT1.addBox("toe11", -2F, -0.5F, -0.5F, 2, 1, 1);
		FOOTROT1.addBox("toe12", -0.5F, -0.5F, -2F, 1, 1, 2);
		FOOTROT1.addBox("toe13", 0F, -0.5F, -0.5F, 2, 1, 1);
		LEG1BOTROT.addChild(FOOTROT1);
		LEG1TOPROT.addChild(LEG1BOTROT);
		BODYROT.addChild(LEG1TOPROT);
		LEG3TOPROT = new ModelRenderer(this, "LEG3TOPROT");
		LEG3TOPROT.setRotationPoint(3F, 0F, -8F);
		setRotation(LEG3TOPROT, 0F, 0F, 0F);
		LEG3TOPROT.mirror = true;
		LEG3TOPROT.addBox("legtop3", -0.5F, 0F, -1F, 1, 4, 2);
		LEG3BOTROT = new ModelRenderer(this, "LEG3BOTROT");
		LEG3BOTROT.setRotationPoint(0F, 4F, 0F);
		setRotation(LEG3BOTROT, 0F, 0F, 0F);
		LEG3BOTROT.mirror = true;
		LEG3BOTROT.addBox("legbot3", -0.5F, 0F, -1F, 1, 4, 2);
		FOOTROT3 = new ModelRenderer(this, "FOOTROT3");
		FOOTROT3.setRotationPoint(0F, 4.5F, 0F);
		setRotation(FOOTROT3, 0F, 0F, 0F);
		FOOTROT3.mirror = true;
		FOOTROT3.addBox("backfoot3", -1F, -0.5F, 0F, 2, 1, 2);
		FOOTROT3.addBox("toe31", -2F, -0.5F, -0.5F, 2, 1, 1);
		FOOTROT3.addBox("toe32", -0.5F, -0.5F, -2F, 1, 1, 2);
		FOOTROT3.addBox("toe33", 0F, -0.5F, -0.5F, 2, 1, 1);
		LEG3BOTROT.addChild(FOOTROT3);
		LEG3TOPROT.addChild(LEG3BOTROT);
		BODYROT.addChild(LEG3TOPROT);
		HEADROT = new ModelRenderer(this, "HEADROT");
		HEADROT.setRotationPoint(0F, -1F, -9F);
		setRotation(HEADROT, 0F, 0F, 0F);
		HEADROT.mirror = true;
		HEADROT.addBox("eyelef", -2.5F, -5F, -2F, 1, 3, 2);
		HEADROT.addBox("eyerig", 1.5F, -5F, -2F, 1, 3, 2);
		HEADROT.addBox("headdecomid", -0.5F, -4F, -2F, 1, 2, 4);
		HEADROT.addBox("headdecolef1", -1.5F, -4.5F, -2F, 1, 3, 3);
		HEADROT.addBox("backdeco1", -2.5F, -3F, 0F, 5, 1, 2);
		HEADROT.addBox("headdecorig1", 0.5F, -4.5F, -2F, 1, 3, 3);
		MOUTHTOPRPT = new ModelRenderer(this, "MOUTHTOPRPT");
		MOUTHTOPRPT.setRotationPoint(0F, 0F, 0F);
		setRotation(MOUTHTOPRPT, 0F, 0F, 0F);
		MOUTHTOPRPT.mirror = true;
		MOUTHTOPRPT.addBox("mouthtop0", -3.5F, -2F, -2F, 7, 2, 2);
		MOUTHTOPRPT.addBox("mouthtop1", -3F, -1.5F, -3F, 6, 3, 1);
		MOUTHTOPRPT.addBox("mouthtop2", -2.5F, -1F, -4F, 5, 2, 1);
		MOUTHTOPRPT.addBox("mouthtop3", -2F, -0.5F, -5F, 4, 2, 1);
		MOUTHTOPRPT.addBox("mouthtop4", -1.5F, 0F, -6F, 3, 1, 1);
		HEADROT.addChild(MOUTHTOPRPT);
		MOUTHBOTROT = new ModelRenderer(this, "MOUTHBOTROT");
		MOUTHBOTROT.setRotationPoint(0F, 0F, 0F);
		setRotation(MOUTHBOTROT, 0F, 0F, 0F);
		MOUTHBOTROT.mirror = true;
		MOUTHBOTROT.addBox("mouthbot0", -3.5F, 0F, -2F, 7, 3, 2);
		MOUTHBOTROT.addBox("mouthbot1", -3F, 1F, -3F, 6, 2, 1);
		MOUTHBOTROT.addBox("mouthbot2", -2.5F, 1F, -4F, 5, 2, 1);
		MOUTHBOTROT.addBox("mouthbot3", -2F, 1F, -5F, 4, 2, 1);
		MOUTHBOTROT.addBox("mouthbot4", -1.5F, 1F, -6F, 3, 2, 1);
		HEADROT.addChild(MOUTHBOTROT);
		BODYROT.addChild(HEADROT);
		LEG4TOPROT = new ModelRenderer(this, "LEG4TOPROT");
		LEG4TOPROT.setRotationPoint(-3F, 0F, -8F);
		setRotation(LEG4TOPROT, 0F, 0F, 0F);
		LEG4TOPROT.mirror = true;
		LEG4TOPROT.addBox("legtop4", -0.5F, 0F, -1F, 1, 4, 2);
		LEG4BOTROT = new ModelRenderer(this, "LEG4BOTROT");
		LEG4BOTROT.setRotationPoint(0F, 4F, 0F);
		setRotation(LEG4BOTROT, 0F, 0F, 0F);
		LEG4BOTROT.mirror = true;
		LEG4BOTROT.addBox("legbot4", -0.5F, 0F, -1F, 1, 4, 2);
		FOOTROT4 = new ModelRenderer(this, "FOOTROT4");
		FOOTROT4.setRotationPoint(0F, 4.5F, 0F);
		setRotation(FOOTROT4, 0F, 0F, 0F);
		FOOTROT4.mirror = true;
		FOOTROT4.addBox("backfoot4", -1F, -0.5F, 0F, 2, 1, 2);
		FOOTROT4.addBox("toe41", -2F, -0.5F, -0.5F, 2, 1, 1);
		FOOTROT4.addBox("toe42", -0.5F, -0.5F, -2F, 1, 1, 2);
		FOOTROT4.addBox("toe43", 0F, -0.5F, -0.5F, 2, 1, 1);
		LEG4BOTROT.addChild(FOOTROT4);
		LEG4TOPROT.addChild(LEG4BOTROT);
		BODYROT.addChild(LEG4TOPROT);
	}


	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    float field_78145_g = 8.0F;
	    float field_78151_h = 4.0F;

        if (this.isChild){
            float var8 = 2.0F;
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, field_78145_g * f5, field_78151_h * f5);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
            GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
    		BODYROT.render(0.5f*f5);
            GL11.glPopMatrix();
        }else{
    		BODYROT.render(0.5f*f5);
        }
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5,par7Entity);
	}

	@Override
	public void setLivingAnimations(EntityLiving par1EntityLiving, float par2, float par3, float par4) {

		EntityFrog var5 = (EntityFrog)par1EntityLiving;


		/* Constant Animation Rotations */

		//State 1 = Sitting
		if(!var5.onGround){
			/* Jumping In Air */
			BODYROT.rotateAngleX = (float)( -0*Math.PI/180);
			LEG2TOPROT.rotateAngleX = (float)( +60*Math.PI/180);
			LEG2TOPROT.rotateAngleY = (float)( -0*Math.PI/180);
			LEG2TOPROT.rotateAngleZ = (float)( +60*Math.PI/180);
			LEG2BOTROT.rotateAngleX = (float)( 45*Math.PI/180);
			LEG2BOTROT.rotateAngleY = (float)( 0*Math.PI/180);

			LEG1TOPROT.rotateAngleX = (float)( +60*Math.PI/180);
			LEG1TOPROT.rotateAngleY = (float)( -0*Math.PI/180);
			LEG1TOPROT.rotateAngleZ = (float)( +60*Math.PI/180);
			LEG1BOTROT.rotateAngleX = (float)( 45*Math.PI/180);
			LEG1BOTROT.rotateAngleY = (float)( 0*Math.PI/180);


			LEG3TOPROT.rotateAngleX = (float)( -60*Math.PI/180);
			LEG3BOTROT.rotateAngleX = (float)( 0*Math.PI/180);

			LEG4TOPROT.rotateAngleX = (float)( -60*Math.PI/180);
			LEG4BOTROT.rotateAngleX = (float)( 0*Math.PI/180);

			HEADROT.rotateAngleX = (float)( 0*Math.PI/180);
		}else{
			/* Standing On Ground */

			BODYROT.rotateAngleX = (float)( -45*Math.PI/180);
			LEG2TOPROT.rotateAngleX = (float)( -65*Math.PI/180);
			LEG2TOPROT.rotateAngleY = (float)( -30*Math.PI/180);
			LEG2TOPROT.rotateAngleZ = (float)( 0*Math.PI/180);

			LEG2BOTROT.rotateAngleX = (float)( 110*Math.PI/180);
			LEG2BOTROT.rotateAngleY = (float)( 25*Math.PI/180);


			LEG1TOPROT.rotateAngleX = (float)( -65*Math.PI/180);
			LEG1TOPROT.rotateAngleY = (float)( 30*Math.PI/180);
			LEG1TOPROT.rotateAngleZ = (float)( +0*Math.PI/180);
			LEG1BOTROT.rotateAngleX = (float)( 110*Math.PI/180);
			LEG1BOTROT.rotateAngleY = (float)( -25*Math.PI/180);


			LEG3TOPROT.rotateAngleX = (float)( 10*Math.PI/180);
			LEG3BOTROT.rotateAngleX = (float)( 35*Math.PI/180);

			LEG4TOPROT.rotateAngleX = (float)( 10*Math.PI/180);
			LEG4BOTROT.rotateAngleX = (float)( 35*Math.PI/180);

			HEADROT.rotateAngleX = (float)( 45*Math.PI/180);
		}




		//		LEG4TOPROT.rotateAngleX = (float)( 12*Math.PI/180);
		//		LEG1TOPROT.rotateAngleX = (float)( 12*Math.PI/180);
		//		LEG3TOPROT.rotateAngleX = (float)( 12*Math.PI/180);



		/* State Based Animations */

		//	    BODYROT;
		//	    HEADROT;

		//		TAILROT.rotateAngleX = (float) (-23*Math.PI/180);
		//		LEGROT1.rotateAngleX = (float)( MathHelper.cos(par2*0.6662F*2f			   	 ) * 1.8F * par3 );
		//		LEGROT3.rotateAngleX = (float)( MathHelper.cos(par2*0.6662F*2f			   	 ) * 1.8F * par3 );
		//		LEGROT2.rotateAngleX = (float)( MathHelper.cos(par2*0.6662F*2f + (float)Math.PI) * 1.8F * par3 );
		//		LEGROT4.rotateAngleX = (float)( MathHelper.cos(par2*0.6662F*2f + (float)Math.PI) * 1.8F * par3 );

		//		EARROTL;
		//		EARROTR;



		//		float animSpeed;
		//		animSpeed = 1.0f;
		//		LEGRIGTOPROT.rotateAngleX = var5.eLEGRIGTOPROT.rotateAngleX = (float)( MathHelper.cos(par2*0.6662F + (float)Math.PI) * 1.8F * zeroIfNegative(Math.log(par3+1)) );
		//		LEGLEFTTOPROT.rotateAngleX = var5.eLEGLEFTTOPROT.rotateAngleX = (float)( MathHelper.cos(par2*0.6662F			   	 ) * 1.8F * zeroIfNegative(Math.log(par3+1)) );
		//		LEGRIGBOTROT.rotateAngleX = var5.eLEGRIGBOTROT.rotateAngleX = (float) Math.abs( MathHelper.cos(par2*0.6662F/2 + (float)Math.PI) * 1.8F * zeroIfNegative(Math.log(par3+1)) );
		//		LEGLEFBOTROT.rotateAngleX = var5.eLEGLEFBOTROT.rotateAngleX = (float) Math.abs( MathHelper.cos(par2*0.6662F/2			   	 ) * 1.8F * zeroIfNegative(Math.log(par3+1)) );
		//
		//		if(var5.entityState == listStates.idle.index ||  var5.entityState == listStates.looking.index){
		//			
		//			ARMLEFTOPROT.rotateAngleX = var5.eARMRIGTOPROT.rotateAngleX = (float)( MathHelper.cos(par2*0.6662F + (float)Math.PI) * 1.8F * zeroIfNegative(Math.log(par3+1)) );
		//			ARMRIGTOPROT.rotateAngleX = var5.eARMLEFTOPROT.rotateAngleX = (float)( MathHelper.cos(par2*0.6662F			   	 ) * 1.8F * zeroIfNegative(Math.log(par3+1)) );
		//
		//			ARMLEFBOTROT.rotateAngleX = var5.eARMRIGBOTROT.rotateAngleX = (float) -Math.abs( MathHelper.cos(par2*0.6662F/2 + (float)Math.PI) * 1.8F * zeroIfNegative(Math.log(par3+1)) );
		//			ARMRIGBOTROT.rotateAngleX = var5.eARMLEFBOTROT.rotateAngleX = (float) -Math.abs( MathHelper.cos(par2*0.6662F/2			   	 ) * 1.8F * zeroIfNegative(Math.log(par3+1)) );
		//
		//		}else if(var5.entityState == listStates.attacking.index){
		//			ARMRIGTOPROT.rotateAngleZ = var5.eARMRIGTOPROT.rotateAngleZ = (float)( -5*Math.PI/180 );
		//			ARMRIGTOPROT.rotateAngleX = var5.eARMRIGTOPROT.rotateAngleX = (float)(Math.PI/180)*( -5 + 8*MathHelper.cos(par2*0.6662F) );
		//			ARMRIGTOPROT.rotateAngleY = var5.eARMRIGTOPROT.rotateAngleY = (float)( 0*Math.PI/180 );
		//			ARMRIGBOTROT.rotateAngleX = var5.eARMRIGBOTROT.rotateAngleX = (float)(Math.PI/180)*( -80 + 8*MathHelper.cos(par2*0.6662F) );
		//
		//			ARMLEFTOPROT.rotateAngleZ = var5.eARMLEFTOPROT.rotateAngleZ = (float)( 5*Math.PI/180 );
		//			ARMLEFTOPROT.rotateAngleX = var5.eARMLEFTOPROT.rotateAngleX = (float)(Math.PI/180)*( -5 + 8*MathHelper.cos(par2*0.6662F+(float)Math.PI) );
		//			ARMLEFTOPROT.rotateAngleY = var5.eARMLEFTOPROT.rotateAngleY = (float)( -0*Math.PI/180 );
		//			ARMLEFBOTROT.rotateAngleX = var5.eARMLEFBOTROT.rotateAngleX = (float)(Math.PI/180)*( -80 + 8*MathHelper.cos(par2*0.6662F+(float)Math.PI) );			
		//						
		//		}

		super.setLivingAnimations(par1EntityLiving, par2, par3, par4);
	}

	private double zeroIfNegative(double value){
		if(value < 0){
			return 0f;
		}else{
			return value;
		}
	}

	private float mapValueofSet1ToSet2(float value, float set1min, float set1max, float set2min, float set2max){
		return (value - set1min)*( (set2max - set2min) / (set1max - set1min) ) + set2min;
	}

	private float mapValueWithClamp(float value, float set1min, float set1max, float set2min, float set2max){
		float value2 = (value - set1min)*( (set2max - set2min) / (set1max - set1min) ) + set2min;
		value2 = MathHelper.clamp_float(value2, set2min, set2max);
		return value2;
	}


	private float func_78172_a(float par1, float par2)
	{
		return (Math.abs(par1 % par2 - par2 * 0.5F) - par2 * 0.25F) / (par2 * 0.25F);
	}


}