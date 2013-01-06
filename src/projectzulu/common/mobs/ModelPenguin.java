package projectzulu.common.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class ModelPenguin extends ModelBase
{
  //fields
    ModelRenderer BODYROT;
private ModelRenderer	TAILROT;
private ModelRenderer	HEADROT;
private ModelRenderer	WINGRIGROT;
private ModelRenderer	WINGLEFROT;
private ModelRenderer	LEGLEFROT;
private ModelRenderer	FOOTLEFROT;
private ModelRenderer	TOELEF2ROT;
private ModelRenderer	TOELEF1ROT;
private ModelRenderer	TOELEF3ROT;
private ModelRenderer	LEGRIGROT;
private ModelRenderer	FOOTRIGROT;
private ModelRenderer	TOERIG2ROT;
private ModelRenderer	TOERIG1ROT;
private ModelRenderer	TOERIG3ROT;
  
  public ModelPenguin()
  {
    textureWidth = 64;
    textureHeight = 32;
    setTextureOffset("BODYROT.body", 0, 13);
    setTextureOffset("BODYROT.bodydec", 37, 0);
    setTextureOffset("HEADROT.head", 0, 0);
    setTextureOffset("HEADROT.beaktop", 24, 0);
    setTextureOffset("HEADROT.beakbot", 24, 5);
    setTextureOffset("TAILROT.tail3", 50, 11);
    setTextureOffset("TAILROT.tail2", 40, 11);
    setTextureOffset("TAILROT.tail1", 30, 11);
    setTextureOffset("WINGRIGROT.wingrig", 44, 15);
    setTextureOffset("WINGLEFROT.winglef", 30, 15);
    setTextureOffset("LEGLEFROT.leglef", 52, 0);
    setTextureOffset("TOELEF2ROT.toelefbase2", 56, 7);
    setTextureOffset("TOELEF2ROT.toelefclaw2", 60, 19);
    setTextureOffset("TOELEF2ROT.toeleftop2", 58, 16);
    setTextureOffset("TOELEF1ROT.toelefbase1", 56, 7);
    setTextureOffset("TOELEF1ROT.toelefclaw1", 60, 19);
    setTextureOffset("TOELEF1ROT.toeleftop1", 58, 16);
    setTextureOffset("TOELEF3ROT.toelefbase3", 56, 7);
    setTextureOffset("TOELEF3ROT.toelefclaw3", 60, 19);
    setTextureOffset("TOELEF3ROT.toeleftop3", 58, 16);
    setTextureOffset("LEGRIGROT.legrig", 52, 0);
    setTextureOffset("TOERIG2ROT.toerigbase2", 56, 7);
    setTextureOffset("TOERIG2ROT.toerigclaw2", 60, 19);
    setTextureOffset("TOERIG2ROT.toerigtop2", 58, 16);
    setTextureOffset("TOERIG1ROT.toerigbase1", 56, 7);
    setTextureOffset("TOERIG1ROT.toerigclaw1", 60, 19);
    setTextureOffset("TOERIG1ROT.toerigtop1", 58, 16);
    setTextureOffset("TOERIG3ROT.toerigbase3", 56, 7);
    setTextureOffset("TOERIG3ROT.toerigclaw3", 60, 19);
    setTextureOffset("TOERIG3ROT.toerigtop3", 58, 16);
    
    BODYROT = new ModelRenderer(this, "BODYROT");
    BODYROT.setRotationPoint(0F, 17F, 0F);
    setRotation(BODYROT, 0F, 0F, 0F);
    BODYROT.mirror = true;
      BODYROT.addBox("body", -4.5F, -10F, -4F, 9, 13, 6);
      BODYROT.addBox("bodydec", -3F, -7.5F, -5F, 6, 9, 1);
    HEADROT = new ModelRenderer(this, "HEADROT");
    HEADROT.setRotationPoint(0F, -10F, -1.5F);
    setRotation(HEADROT, 0F, 0F, 0F);
    HEADROT.mirror = true;
      HEADROT.addBox("head", -3F, -5F, -3F, 6, 5, 6);
      HEADROT.addBox("beaktop", -1F, -2.5F, -7F, 2, 1, 4);
      HEADROT.addBox("beakbot", -0.5F, -1.5F, -6F, 1, 1, 3);
      BODYROT.addChild(HEADROT);
    TAILROT = new ModelRenderer(this, "TAILROT");
    TAILROT.setRotationPoint(0F, 0F, 3F);
    setRotation(TAILROT, 0F, 0F, 0F);
    TAILROT.mirror = true;
//      tail3.mirror = true;
      TAILROT.addBox("tail3", -1F, 1F, 2F, 2, 1, 3);
//      tail3.mirror = false;
      TAILROT.addBox("tail2", -1.5F, 0F, 0F, 3, 2, 2);
      TAILROT.addBox("tail1", -2F, -1F, -1F, 4, 3, 1);
      BODYROT.addChild(TAILROT);
    WINGRIGROT = new ModelRenderer(this, "WINGRIGROT");
    WINGRIGROT.setRotationPoint(4.5F, -10F, -1F);
    setRotation(WINGRIGROT, 0F, 0F, 0F);
    WINGRIGROT.mirror = true;
      WINGRIGROT.addBox("wingrig", 0F, 0F, -3F, 1, 11, 6);
      BODYROT.addChild(WINGRIGROT);
    WINGLEFROT = new ModelRenderer(this, "WINGLEFROT");
    WINGLEFROT.setRotationPoint(-4.5F, -10F, -1F);
    setRotation(WINGLEFROT, 0F, 0F, 0F);
    WINGLEFROT.mirror = true;
      WINGLEFROT.addBox("winglef", -1F, 0F, -3F, 1, 11, 6);
      BODYROT.addChild(WINGLEFROT);
    LEGLEFROT = new ModelRenderer(this, "LEGLEFROT");
    LEGLEFROT.setRotationPoint(-3F, 3F, -0.5F);
    setRotation(LEGLEFROT, 0F, 0F, 0F);
    LEGLEFROT.mirror = true;
      LEGLEFROT.addBox("leglef", -1.5F, 0F, -1.5F, 3, 4, 3);
    FOOTLEFROT = new ModelRenderer(this, "FOOTLEFROT");
    FOOTLEFROT.setRotationPoint(0F, 3.5F, -1F);
    setRotation(FOOTLEFROT, 0F, 0F, 0F);
    FOOTLEFROT.mirror = true;
    TOELEF2ROT = new ModelRenderer(this, "TOELEF2ROT");
    TOELEF2ROT.setRotationPoint(0F, 0F, -0.5F);
    setRotation(TOELEF2ROT, 0F, 0F, 0F);
    TOELEF2ROT.mirror = true;
      TOELEF2ROT.addBox("toelefbase2", -1F, -0.5F, -2F, 2, 1, 2);
      TOELEF2ROT.addBox("toelefclaw2", -0.5F, -0.5F, -3F, 1, 1, 1);
      TOELEF2ROT.addBox("toeleftop2", -0.5F, -1.5F, -2F, 1, 1, 2);
      FOOTLEFROT.addChild(TOELEF2ROT);
    TOELEF1ROT = new ModelRenderer(this, "TOELEF1ROT");
    TOELEF1ROT.setRotationPoint(-2F, 0F, 0F);
    setRotation(TOELEF1ROT, 0F, 0F, 0F);
    TOELEF1ROT.mirror = true;
      TOELEF1ROT.addBox("toelefbase1", -1F, -0.5F, -2F, 2, 1, 2);
      TOELEF1ROT.addBox("toelefclaw1", -0.5F, -0.5F, -3F, 1, 1, 1);
      TOELEF1ROT.addBox("toeleftop1", -0.5F, -1.5F, -2F, 1, 1, 2);
      FOOTLEFROT.addChild(TOELEF1ROT);
    TOELEF3ROT = new ModelRenderer(this, "TOELEF3ROT");
    TOELEF3ROT.setRotationPoint(2F, 0F, 0F);
    setRotation(TOELEF3ROT, 0F, 0F, 0F);
    TOELEF3ROT.mirror = true;
      TOELEF3ROT.addBox("toelefbase3", -1F, -0.5F, -2F, 2, 1, 2);
      TOELEF3ROT.addBox("toelefclaw3", -0.5F, -0.5F, -3F, 1, 1, 1);
      TOELEF3ROT.addBox("toeleftop3", -0.5F, -1.5F, -2F, 1, 1, 2);
      FOOTLEFROT.addChild(TOELEF3ROT);
      LEGLEFROT.addChild(FOOTLEFROT);
      BODYROT.addChild(LEGLEFROT);
    LEGRIGROT = new ModelRenderer(this, "LEGRIGROT");
    LEGRIGROT.setRotationPoint(3F, 3F, -0.5F);
    setRotation(LEGRIGROT, 0F, 0F, 0F);
    LEGRIGROT.mirror = true;
      LEGRIGROT.addBox("legrig", -1.5F, 0F, -1.5F, 3, 4, 3);
    FOOTRIGROT = new ModelRenderer(this, "FOOTRIGROT");
    FOOTRIGROT.setRotationPoint(0F, 3.5F, -1F);
    setRotation(FOOTRIGROT, 0F, 0F, 0F);
    FOOTRIGROT.mirror = true;
    TOERIG2ROT = new ModelRenderer(this, "TOERIG2ROT");
    TOERIG2ROT.setRotationPoint(0F, 0F, -0.5F);
    setRotation(TOERIG2ROT, 0F, 0F, 0F);
    TOERIG2ROT.mirror = true;
      TOERIG2ROT.addBox("toerigbase2", -1F, -0.5F, -2F, 2, 1, 2);
      TOERIG2ROT.addBox("toerigclaw2", -0.5F, -0.5F, -3F, 1, 1, 1);
      TOERIG2ROT.addBox("toerigtop2", -0.5F, -1.5F, -2F, 1, 1, 2);
      FOOTRIGROT.addChild(TOERIG2ROT);
    TOERIG1ROT = new ModelRenderer(this, "TOERIG1ROT");
    TOERIG1ROT.setRotationPoint(-2F, 0F, 0F);
    setRotation(TOERIG1ROT, 0F, 0F, 0F);
    TOERIG1ROT.mirror = true;
      TOERIG1ROT.addBox("toerigbase1", -1F, -0.5F, -2F, 2, 1, 2);
      TOERIG1ROT.addBox("toerigclaw1", -0.5F, -0.5F, -3F, 1, 1, 1);
      TOERIG1ROT.addBox("toerigtop1", -0.5F, -1.5F, -2F, 1, 1, 2);
      FOOTRIGROT.addChild(TOERIG1ROT);
    TOERIG3ROT = new ModelRenderer(this, "TOERIG3ROT");
    TOERIG3ROT.setRotationPoint(2F, 0F, 0F);
    setRotation(TOERIG3ROT, 0F, 0F, 0F);
    TOERIG3ROT.mirror = true;
      TOERIG3ROT.addBox("toerigbase3", -1F, -0.5F, -2F, 2, 1, 2);
      TOERIG3ROT.addBox("toerigclaw3", -0.5F, -0.5F, -3F, 1, 1, 1);
      TOERIG3ROT.addBox("toerigtop3", -0.5F, -1.5F, -2F, 1, 1, 2);
      FOOTRIGROT.addChild(TOERIG3ROT);
      LEGRIGROT.addChild(FOOTRIGROT);
      BODYROT.addChild(LEGRIGROT);
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
//		HEADROT.render(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
        GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
        BODYROT.render(f5);
        GL11.glPopMatrix();
    }else{
        BODYROT.render(f5);
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

		EntityPenguin var5 = (EntityPenguin)par1EntityLiving;


		/* Constant Animation Rotations */



		/* State Based Animations */

		LEGRIGROT.rotateAngleX = (float)( MathHelper.cos(par2*0.6662F*2f			   	 ) * 1.8F * par3 );
		LEGLEFROT.rotateAngleX = (float)( MathHelper.cos(par2*0.6662F*2f + (float)Math.PI) * 1.8F * par3 );

		BODYROT.rotateAngleZ = (float)( MathHelper.cos(par2*0.6662F*2f			   	 ) * 0.1F * par3 );
		
		TAILROT.rotateAngleX = (float) (-20*Math.PI/180 +( MathHelper.cos(par2*0.6662F*2f			   	 ) * 0.5F * par3 ));
		
		WINGRIGROT.rotateAngleZ = -Math.abs( MathHelper.cos(par2*0.6662F*2f			   	 ) * 1.0F * par3 );
		WINGLEFROT.rotateAngleZ = Math.abs( MathHelper.cos(par2*0.6662F*2f			   	 ) * 1.0F * par3 );
		
		TOELEF1ROT.rotateAngleY = (float)(10*Math.PI/180);
		TOERIG3ROT.rotateAngleY = (float)(-10*Math.PI/180);
//	    ModelRenderer BODYROT;
//	    private ModelRenderer	TAILROT;
//	    private ModelRenderer	HEADROT;
//	    private ModelRenderer	WINGRIGROT;
//	    private ModelRenderer	WINGLEFROT;
//	    private ModelRenderer	LEGLEFROT;
//	    private ModelRenderer	FOOTLEFROT;
//	    private ModelRenderer	TOELEF2ROT;
//	    private ModelRenderer	TOELEF1ROT;
//	    private ModelRenderer	TOELEF3ROT;
//	    private ModelRenderer	LEGRIGROT;
//	    private ModelRenderer	FOOTRIGROT;
//	    private ModelRenderer	TOERIG2ROT;
//	    private ModelRenderer	TOERIG1ROT;
//	    private ModelRenderer	TOERIG3ROT;
		
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
