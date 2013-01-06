package projectzulu.common.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class ModelGorilla extends ModelBase
{
	//fields
	ModelRenderer LEG4ROT;
	ModelRenderer LEG3ROT;
	ModelRenderer BODY2ROT;
	private ModelRenderer BODY1ROT;
	private ModelRenderer HEADROT;
	private ModelRenderer LEG1TOPROT;
	private ModelRenderer LEG1BOTROT;
	private ModelRenderer LEG2TOPROT;
	private ModelRenderer LEG2BOTROT;
	private ModelRenderer BODY1OTHROT;

	public ModelGorilla()
	{
		textureWidth = 64;
		textureHeight = 64;
		setTextureOffset("LEG4ROT.leg4", 38, 52);
		setTextureOffset("LEG3ROT.leg3", 38, 39);
		setTextureOffset("BODY2ROT.body2", 0, 22);
		setTextureOffset("BODY1ROT.body1", 0, 0);
		setTextureOffset("HEADROT.head", 38, 17);
		setTextureOffset("HEADROT.ear1", 0, 0);
		setTextureOffset("HEADROT.ear2", 0, 4);
		setTextureOffset("HEADROT.nose", 47, 32);
		setTextureOffset("LEG1TOPROT.leg1top", 0, 41);
		setTextureOffset("LEG1BOTROT.leg1top", 0, 49);
		setTextureOffset("LEG2TOPROT.leg2top", 19, 41);
		setTextureOffset("LEG2BOTROT.leg2bot", 19, 49);

		LEG4ROT = new ModelRenderer(this, "LEG4ROT");
		LEG4ROT.setRotationPoint(3F, 16F, 8F);
		setRotation(LEG4ROT, 0F, 0F, 0F);
		LEG4ROT.mirror = true;
		LEG4ROT.addBox("leg4", -2F, 0F, -2F, 4, 8, 4);
		LEG3ROT = new ModelRenderer(this, "LEG3ROT");
		LEG3ROT.setRotationPoint(-3F, 16F, 8F);
		setRotation(LEG3ROT, 0F, 0F, 0F);
		LEG3ROT.mirror = true;
		LEG3ROT.addBox("leg3", -2F, 0F, -2F, 4, 8, 4);
		BODY2ROT = new ModelRenderer(this, "BODY2ROT");
		BODY2ROT.setRotationPoint(0F, 15F, 8F);
		setRotation(BODY2ROT, 0F, 0F, 0F);
		BODY2ROT.mirror = true;
		BODY2ROT.addBox("body2", -5F, -7F, -7F, 10, 8, 10);
		BODY1ROT = new ModelRenderer(this, "BODY1ROT");
		BODY1ROT.setRotationPoint(0F, 0F, -3F);
		setRotation(BODY1ROT, 1.178097F, 0F, 0F);
		BODY1ROT.mirror = true;
		BODY1ROT.addBox("body1", -6F, -15F, -3F, 12, 12, 9);
		BODY2ROT.addChild(BODY1ROT);
		BODY1OTHROT = new ModelRenderer(this, "BODY1OTHROT");
		BODY1OTHROT.setRotationPoint(0F, 0F, -3F);
		setRotation(BODY1OTHROT, 0F, 0F, 0F);
		BODY1OTHROT.mirror = true;
		HEADROT = new ModelRenderer(this, "HEADROT");
		HEADROT.setRotationPoint(0F, -8F, -12F);
		setRotation(HEADROT, 0F, 0F, 0F);
		HEADROT.mirror = true;
		HEADROT.addBox("head", -3.5F, -7F, -3F, 7, 8, 6);
		HEADROT.addBox("ear1", -4.5F, -6F, -1F, 1, 2, 1);
		HEADROT.addBox("ear2", 3.5F, -6F, -1F, 1, 2, 1);
		HEADROT.addBox("nose", -2F, -3F, -4F, 4, 4, 1);
		BODY1OTHROT.addChild(HEADROT);
		LEG1TOPROT = new ModelRenderer(this, "LEG1TOPROT");
		LEG1TOPROT.setRotationPoint(-5F, -9F, -10F);
		setRotation(LEG1TOPROT, 0F, 0F, 0.0174533F);
		LEG1TOPROT.mirror = true;
		LEG1TOPROT.addBox("leg1top", -2.5F, 0F, -2F, 5, 8, 4);
		LEG1BOTROT = new ModelRenderer(this, "LEG1BOTROT");
		LEG1BOTROT.setRotationPoint(0F, 8F, 0F);
		setRotation(LEG1BOTROT, 0F, 0F, 0F);
		LEG1BOTROT.mirror = true;
		LEG1BOTROT.addBox("leg1top", -2.5F, 0F, -2F, 5, 10, 4);
		LEG1TOPROT.addChild(LEG1BOTROT);
		BODY1OTHROT.addChild(LEG1TOPROT);
		LEG2TOPROT = new ModelRenderer(this, "LEG2TOPROT");
		LEG2TOPROT.setRotationPoint(5F, -9F, -10F);
		setRotation(LEG2TOPROT, 0F, 0F, 0F);
		LEG2TOPROT.mirror = true;
		LEG2TOPROT.addBox("leg2top", -2.5F, 0F, -2F, 5, 8, 4);
		LEG2BOTROT = new ModelRenderer(this, "LEG2BOTROT");
		LEG2BOTROT.setRotationPoint(0F, 8F, 0F);
		setRotation(LEG2BOTROT, 0F, 0F, 0F);
		LEG2BOTROT.mirror = true;
		LEG2BOTROT.addBox("leg2bot", -2.5F, 0F, -2F, 5, 10, 4);
		LEG2TOPROT.addChild(LEG2BOTROT);
		BODY1OTHROT.addChild(LEG2TOPROT);
		BODY2ROT.addChild(BODY1OTHROT);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		float field_78145_g = 4.0f;
	    float field_78151_h = 3.0f;

		if (this.isChild){
            float var8 = 2.0F;
            GL11.glPushMatrix();
            GL11.glScalef(1.8F / var8, 1.8F / var8, 1.8F / var8);
            GL11.glTranslatef(0.0F, field_78145_g * f5, field_78151_h * f5);
//    		HEADROT.render(f5);
            GL11.glPopMatrix();
            
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
            GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
    		LEG4ROT.render(f5);
    		LEG3ROT.render(f5);
    		BODY2ROT.render(f5);
            GL11.glPopMatrix();
        }else{
    		LEG4ROT.render(f5);
    		LEG3ROT.render(f5);
    		BODY2ROT.render(f5);
        }
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity){
		super.setRotationAngles(f, f1, f2, f3, f4, f5,par7Entity);
	}


	@Override
	public void setLivingAnimations(EntityLiving par1EntityLiving, float par2, float par3, float par4) {

		//		ModelRenderer LEG4ROT;
		//		ModelRenderer LEG3ROT;
		//		ModelRenderer BODY2ROT;
		//		private ModelRenderer BODY1ROT;
		//		private ModelRenderer HEADROT;
		//		private ModelRenderer LEG1TOPROT;
		//		private ModelRenderer LEG1BOTROT;
		//		private ModelRenderer LEG2TOPROT;
		//		private ModelRenderer LEG2BOTROT;

		EntityGorilla var5 = (EntityGorilla)par1EntityLiving;

		/* Constant Animation Rotations*/

		/* State Animation Rotations*/
		/*Left Side Legs*/
		LEG1TOPROT.rotateAngleX = (float)( MathHelper.cos(par2*0.6662F + (float)Math.PI) * 1.8F * zeroIfNegative(Math.log(par3+1)) );
		LEG3ROT.rotateAngleX = (float)( MathHelper.cos(par2*0.6662F + (float)Math.PI) * 1.8F * zeroIfNegative(Math.log(par3+1)) );

		/*Right Side Legs*/
		LEG2TOPROT.rotateAngleX = -(float)( MathHelper.cos(par2*0.6662F + (float)Math.PI) * 1.8F * zeroIfNegative(Math.log(par3+1)) );
		LEG4ROT.rotateAngleX = -(float)( MathHelper.cos(par2*0.6662F + (float)Math.PI) * 1.8F * zeroIfNegative(Math.log(par3+1)) );


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
		//
		//
		//		float minTime;
		//		float maxTime;
		//
		//		if(var5.attackTime > var5.attackAnimTime*7/8){
		//			minTime = var5.attackAnimTime*7/8;
		//			maxTime = var5.attackAnimTime;
		//			ARMRIGTOPROT.rotateAngleZ = mapValueofSet1ToSet2(var5.attackTime, minTime, maxTime, (float)( -75*Math.PI/180 ),(float)( -5*Math.PI/180 ));
		//			ARMRIGTOPROT.rotateAngleX = mapValueofSet1ToSet2(var5.attackTime, minTime, maxTime, (float)( 15*Math.PI/180 ),(float)( -5*Math.PI/180 ));
		//			ARMRIGTOPROT.rotateAngleY = 0.0f;
		//			ARMRIGBOTROT.rotateAngleX = mapValueofSet1ToSet2(var5.attackTime, minTime, maxTime, (float)( -110*Math.PI/180 ),(float)( -80*Math.PI/180 ));
		//
		//			ARMLEFTOPROT.rotateAngleZ = mapValueofSet1ToSet2(var5.attackTime, minTime, maxTime, (float)( 75*Math.PI/180 ),(float)( 5*Math.PI/180 ));
		//			ARMLEFTOPROT.rotateAngleX = mapValueofSet1ToSet2(var5.attackTime, minTime, maxTime, (float)( 15*Math.PI/180 ),(float)( -5*Math.PI/180 ));
		//			ARMLEFTOPROT.rotateAngleY = 0.0f;
		//			ARMLEFBOTROT.rotateAngleX = mapValueofSet1ToSet2(var5.attackTime, minTime, maxTime, (float)( -110*Math.PI/180 ),(float)( -80*Math.PI/180 ));
		//			
		//		}else if(var5.attackTime > var5.attackAnimTime*6/8){
		//			maxTime = var5.attackAnimTime*7/8;
		//			minTime = var5.attackAnimTime*6/8;
		//			ARMRIGTOPROT.rotateAngleZ = (float)( -75*Math.PI/180 );
		//			ARMRIGTOPROT.rotateAngleX = mapValueofSet1ToSet2(var5.attackTime,minTime, maxTime,(float)( -60*Math.PI/180 ),(float)( 15*Math.PI/180 ));
		//			ARMRIGTOPROT.rotateAngleY = mapValueofSet1ToSet2(var5.attackTime,minTime, maxTime,(float)( 30*Math.PI/180 ),(float)( 0*Math.PI/180 ));
		//			ARMRIGBOTROT.rotateAngleX = mapValueofSet1ToSet2(var5.attackTime,minTime, maxTime,(float)( -80*Math.PI/180 ),(float)( -110*Math.PI/180 ));
		//
		//			ARMLEFTOPROT.rotateAngleZ = (float)( 75*Math.PI/180 );
		//			ARMLEFTOPROT.rotateAngleX = mapValueofSet1ToSet2(var5.attackTime,minTime, maxTime,(float)( -60*Math.PI/180 ),(float)( 15*Math.PI/180 ));
		//			ARMLEFTOPROT.rotateAngleY = mapValueofSet1ToSet2(var5.attackTime,minTime, maxTime,(float)( -30*Math.PI/180 ),(float)( 0*Math.PI/180 ));
		//			ARMLEFBOTROT.rotateAngleX = mapValueofSet1ToSet2(var5.attackTime,minTime, maxTime,(float)( -80*Math.PI/180 ),(float)( -110*Math.PI/180 ));
		//
		//		}else if(var5.attackTime > var5.attackAnimTime*5/8){
		//			maxTime = var5.attackAnimTime*6/8;
		//			minTime = var5.attackAnimTime*5/4;
		//
		//			ARMRIGTOPROT.rotateAngleZ = (float)( -75*Math.PI/180 );
		//			ARMRIGTOPROT.rotateAngleX = mapValueofSet1ToSet2(var5.attackTime,minTime, maxTime,(float)( 15*Math.PI/180 ),(float)( -60*Math.PI/180 ));
		//			ARMRIGTOPROT.rotateAngleY = mapValueofSet1ToSet2(var5.attackTime,minTime, maxTime,(float)( 0*Math.PI/180 ),(float)( 30*Math.PI/180 ));
		//			ARMRIGBOTROT.rotateAngleX = mapValueofSet1ToSet2(var5.attackTime,minTime, maxTime,(float)( -110*Math.PI/180 ),(float)( -80*Math.PI/180 ));
		//
		//			ARMLEFTOPROT.rotateAngleZ = (float)( 75*Math.PI/180 );
		//			ARMLEFTOPROT.rotateAngleX = mapValueofSet1ToSet2(var5.attackTime,minTime, maxTime,(float)( 15*Math.PI/180 ),(float)( -60*Math.PI/180 ));
		//			ARMLEFTOPROT.rotateAngleY = mapValueofSet1ToSet2(var5.attackTime,minTime, maxTime,(float)( 0*Math.PI/180 ),(float)( -30*Math.PI/180 ));
		//			ARMLEFBOTROT.rotateAngleX = mapValueofSet1ToSet2(var5.attackTime,minTime, maxTime,(float)( -110*Math.PI/180 ),(float)( -80*Math.PI/180 ));
		//			
		//		}else if(var5.attackTime > 0){
		//			maxTime = var5.attackAnimTime*5/4;
		//			minTime = 0;
		//
		//			ARMRIGTOPROT.rotateAngleZ = mapValueofSet1ToSet2(var5.attackTime, minTime, maxTime, (float)( -5*Math.PI/180 ),(float)( -75*Math.PI/180 ));
		//			ARMRIGTOPROT.rotateAngleX = mapValueofSet1ToSet2(var5.attackTime, minTime, maxTime, (float)( -5*Math.PI/180 ),(float)( 15*Math.PI/180 ));
		//			ARMRIGTOPROT.rotateAngleY = 0.0f;
		//			ARMRIGBOTROT.rotateAngleX = mapValueofSet1ToSet2(var5.attackTime, minTime, maxTime, (float)( -80*Math.PI/180 ),(float)( -110*Math.PI/180 ));
		//
		//			ARMLEFTOPROT.rotateAngleZ = mapValueofSet1ToSet2(var5.attackTime, minTime, maxTime, (float)( 5*Math.PI/180 ),(float)( 75*Math.PI/180 ));
		//			ARMLEFTOPROT.rotateAngleX = mapValueofSet1ToSet2(var5.attackTime, minTime, maxTime, (float)( -5*Math.PI/180 ),(float)( 15*Math.PI/180 ));
		//			ARMLEFTOPROT.rotateAngleY = 0.0f;
		//			ARMLEFBOTROT.rotateAngleX = mapValueofSet1ToSet2(var5.attackTime, minTime, maxTime, (float)( -80*Math.PI/180 ),(float)( -110*Math.PI/180 ));
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
}
