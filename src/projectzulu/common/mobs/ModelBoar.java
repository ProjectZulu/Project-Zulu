package projectzulu.common.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class ModelBoar extends ModelBase
{
	public enum listStates {
		idle(0), 
		looking(1),
		attacking(2),
		attackAnim(3),
		attackAnim2(4);

		public final int index;
		listStates(int index) {
			this.index = index;
		}
		public int index() { 
			return index; 
		}
	}

	ModelRenderer HEADROT;
	ModelRenderer BODYROT;
	private ModelRenderer LEGROT1;
	private ModelRenderer LEGROT2;
	private ModelRenderer LEGROT3;
	private ModelRenderer LEGROT4;


	public ModelBoar()
	{
		textureWidth = 64;
		textureHeight = 32;
		setTextureOffset("HEADROT.head", 0, 0);
		setTextureOffset("HEADROT.nose2", 28, 0);
		setTextureOffset("HEADROT.nose1", 42, 0);
		setTextureOffset("HEADROT.tusklef1", 0, 29);
		setTextureOffset("HEADROT.tusklef2", 0, 27);
		setTextureOffset("HEADROT.tuskrig1", 0, 29);
		setTextureOffset("HEADROT.tuskrig2", 0, 27);
		setTextureOffset("HEADROT.tusklef3", 0, 29);
		setTextureOffset("HEADROT.tusklef4", 0, 27);
		setTextureOffset("HEADROT.tuskrig3", 0, 29);
		setTextureOffset("HEADROT.tuskrig4", 0, 27);
		setTextureOffset("BODYROT.body", 12, 8);
		setTextureOffset("LEGROT1.leg1", 0, 13);
		setTextureOffset("LEGROT2.leg2", 0, 13);
		setTextureOffset("LEGROT3.leg3", 0, 13);
		setTextureOffset("LEGROT4.leg4", 0, 13);

		HEADROT = new ModelRenderer(this, "HEADROT");
		HEADROT.setRotationPoint(0F, 14F, -5F);
		setRotation(HEADROT, 0F, 0F, 0F);
		HEADROT.mirror = true;
		HEADROT.addBox("head", -4F, -3.5F, -5F, 8, 7, 6);
		HEADROT.addBox("nose2", -2.5F, -0.5F, -7F, 5, 4, 2);
		HEADROT.addBox("nose1", -2F, 0.5F, -9F, 4, 3, 2);
		HEADROT.addBox("tusklef1", -4F, 2.5F, -9F, 2, 1, 1);
		HEADROT.addBox("tusklef2", -4F, 1.5F, -9F, 1, 1, 1);
		HEADROT.addBox("tuskrig1", 2F, 2.5F, -9F, 2, 1, 1);
		HEADROT.addBox("tuskrig2", 3F, 1.5F, -9F, 1, 1, 1);
		HEADROT.addBox("tusklef3", -4.5F, 2.5F, -7F, 2, 1, 1);
		HEADROT.addBox("tusklef4", -4.5F, 1.5F, -7F, 1, 1, 1);
		HEADROT.addBox("tuskrig3", 2.5F, 2.5F, -7F, 2, 1, 1);
		HEADROT.addBox("tuskrig4", 3.5F, 1.5F, -7F, 1, 1, 1);
		BODYROT = new ModelRenderer(this, "BODYROT");
		BODYROT.setRotationPoint(0F, 14.5F, -4F);
		setRotation(BODYROT, 0F, 0F, 0F);
		BODYROT.mirror = true;
		BODYROT.addBox("body", -5F, -4.5F, 0F, 10, 8, 16);
		LEGROT1 = new ModelRenderer(this, "LEGROT1");
		LEGROT1.setRotationPoint(-3F, 3.5F, 14F);
		setRotation(LEGROT1, 0F, 0F, 0F);
		LEGROT1.mirror = true;
		LEGROT1.addBox("leg1", -2F, 0F, -2F, 4, 6, 4);
		BODYROT.addChild(LEGROT1);
		LEGROT2 = new ModelRenderer(this, "LEGROT2");
		LEGROT2.setRotationPoint(3F, 3.5F, 14F);
		setRotation(LEGROT2, 0F, 0F, 0F);
		LEGROT2.mirror = true;
		LEGROT2.addBox("leg2", -2F, 0F, -2F, 4, 6, 4);
		BODYROT.addChild(LEGROT2);
		LEGROT3 = new ModelRenderer(this, "LEGROT3");
		LEGROT3.setRotationPoint(-3F, 3.5F, 2F);
		setRotation(LEGROT3, 0F, 0F, 0F);
		LEGROT3.mirror = true;
		LEGROT3.addBox("leg3", -2F, 0F, -2F, 4, 6, 4);
		BODYROT.addChild(LEGROT3);
		LEGROT4 = new ModelRenderer(this, "LEGROT4");
		LEGROT4.setRotationPoint(3F, 3.5F, 2F);
		setRotation(LEGROT4, 0F, 0F, 0F);
		LEGROT4.mirror = true;
		LEGROT4.addBox("leg4", -2F, 0F, -2F, 4, 6, 4);
		BODYROT.addChild(LEGROT4);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		float field_78145_x = 0.0f;
		float field_78145_g = 2.2F;
	    float field_78151_h = 3.4F;

        if (this.isChild){
            float var8 = 2.0F;
            GL11.glPushMatrix();
            GL11.glScalef(1.8F / var8, 1.8F / var8, 1.8F / var8);
            GL11.glTranslatef(field_78145_x, field_78145_g * f5, field_78151_h * f5);
    		HEADROT.render(f5);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
            GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
    		BODYROT.render(f5);
            GL11.glPopMatrix();
        }else{
    		HEADROT.render(f5);
    		BODYROT.render(f5);
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

		EntityBoar var5 = (EntityBoar)par1EntityLiving;


		/* Constant Animation Rotations */



		/* State Based Animations */

		//	    BODYROT;
		//	    HEADROT;

		//		TAILROT.rotateAngleX = (float) (-23*Math.PI/180);
		LEGROT1.rotateAngleX = (float)( MathHelper.cos(par2*0.6662F*2f			   	 ) * 1.8F * par3 );
		LEGROT3.rotateAngleX = (float)( MathHelper.cos(par2*0.6662F*2f			   	 ) * 1.8F * par3 );
		LEGROT2.rotateAngleX = (float)( MathHelper.cos(par2*0.6662F*2f + (float)Math.PI) * 1.8F * par3 );
		LEGROT4.rotateAngleX = (float)( MathHelper.cos(par2*0.6662F*2f + (float)Math.PI) * 1.8F * par3 );

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
