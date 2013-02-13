package projectzulu.common.mobs.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.ForgeHooksClient;

import org.lwjgl.opengl.GL11;

import projectzulu.common.mobs.entity.EntityHauntedArmor;
import projectzulu.common.mobs.entity.EntityStates;

public class ModelHauntedArmor extends ModelBase{
	ModelRenderer swordhand;

	public ModelRenderer bipedHead;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;
	
    public ModelRenderer bipedBody2;
    public ModelRenderer bipedRightLeg2;
    public ModelRenderer bipedLeftLeg2;

	public ModelHauntedArmor(){
		this(0.0f, 0.0f);
	}

	public ModelHauntedArmor(float par1, float par2){
		super();

		int textureWidth = 64;
		int textureHeight = 32;

//        this.heldItemLeft = 0;
//        this.heldItemRight = 0;
//        this.isSneak = false;
//        this.aimedBow = false;
        
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, par1);
        this.bipedHead.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, par1);
        this.bipedBody.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, par1);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + par2, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, par1);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + par2, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F + par2, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1);
        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F + par2, 0.0F);
        
        this.bipedBody2 = new ModelRenderer(this, 16, 16);
        this.bipedBody2.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, par1);
        this.bipedBody2.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.bipedRightLeg2 = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg2.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1);
        this.bipedRightLeg2.setRotationPoint(-1.9F, 12.0F + par2, 0.0F);
        this.bipedLeftLeg2 = new ModelRenderer(this, 0, 16);
        this.bipedLeftLeg2.mirror = true;
        this.bipedLeftLeg2.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1);
        this.bipedLeftLeg2.setRotationPoint(1.9F, 12.0F + par2, 0.0F);
        
		swordhand = new ModelRenderer(this, 0, 0);
		swordhand.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1);
		swordhand.setRotationPoint(0F, 9F, -7F);
		swordhand.setTextureSize(textureWidth, textureHeight);
		swordhand.mirror = true;
		setRotation(swordhand, 0F, 0F, 0F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
    public static String[] bipedArmorFilenamePrefix = new String[] {"cloth", "chain", "iron", "diamond", "gold"};

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4,
			float par5, float par6, float par7) {
		if (this.isChild){
			float var8 = 2.0F;
			GL11.glPushMatrix();
			GL11.glScalef(1.5F / var8, 1.5F / var8, 1.5F / var8);
			GL11.glTranslatef(0.0F, 16.0F * par7, 0.0F);
			this.bipedHead.render(par7);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
			GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
			this.bipedBody.render(par7);
			this.bipedRightArm.render(par7);
			this.bipedLeftArm.render(par7);
			this.bipedRightLeg.render(par7);
			this.bipedLeftLeg.render(par7);
			GL11.glPopMatrix();
		}
		else{
            String textureLocation;
            ItemStack var4;
            float scale;
            
			textureLocation ="/armor/iron_2.png";
			EntityHauntedArmor var5 = (EntityHauntedArmor)par1Entity;
			var4 = var5.getCurrentArmor(3-2);
			if(var4 != null && var4.getItem() instanceof ItemArmor){
				  ItemArmor var6 = (ItemArmor)var4.getItem();
					  textureLocation = ForgeHooksClient.getArmorTexture(var4, "/armor/" + RenderBiped.bipedArmorFilenamePrefix[var6.renderIndex] + "_" + 2 + ".png");
			}
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, ModLoader.getMinecraftInstance().renderEngine.getTexture( textureLocation ));
			scale = par7*0.85f;
			this.bipedRightLeg2.render(scale);
			this.bipedLeftLeg2.render(scale);
			
			textureLocation ="/armor/iron_1.png";
			var4 = var5.getCurrentArmor(3-1);
			if(var4 != null && var4.getItem() instanceof ItemArmor){
				  ItemArmor var6 = (ItemArmor)var4.getItem();
				  textureLocation = ForgeHooksClient.getArmorTexture(var4, "/armor/" + RenderBiped.bipedArmorFilenamePrefix[var6.renderIndex] + "_" + 1 + ".png");
			}
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, ModLoader.getMinecraftInstance().renderEngine.getTexture( textureLocation ));
			this.bipedHead.render(par7);
			this.bipedBody.render(par7);
			this.bipedRightArm.render(par7);
			this.bipedLeftArm.render(par7);
			this.bipedRightLeg.render(par7);
			this.bipedLeftLeg.render(par7);
		}

		//swordhand.render(par7);
	}
	@Override
	public void setRotationAngles(float par1, float par2, float par3,
			float par4, float par5, float par6, Entity par7Entity) {

	}

	@Override
	public void setLivingAnimations(EntityLiving par1EntityLiving, float par2,
			float par3, float par4) {
		
		EntityHauntedArmor var5 = (EntityHauntedArmor)par1EntityLiving;

		/* Sleeping Animation */
		if(var5.getEntityState() == EntityStates.idle){
			bipedHead.setRotationPoint(-4f, 15.9f, 0f);
			setRotation(bipedHead, 0, 0, (float) (90f*Math.PI/180));
			
			bipedRightArm.setRotationPoint(-6f, 17.8f, 0f);
			setRotation(bipedRightArm, (float) (-66f*Math.PI/180), 0, 0);
			bipedLeftArm.setRotationPoint(7f, 23.0f, 4.0f);
			setRotation(bipedLeftArm, 0, 0, (float) (-90f*Math.PI/180));
			
			bipedBody.setRotationPoint(0.0f, 22.0f, 9.5f);
			setRotation(bipedBody, (float) (-90f*Math.PI/180), 0, 0);
			bipedBody2.setRotationPoint(0.0f, 26f, 9.5f);
			setRotation(bipedBody2, (float) (-90f*Math.PI/180), 0, 0);
			
			bipedRightLeg.setRotationPoint(-2.0f, 12.0f, -7f);
			setRotation(bipedRightLeg, 0, 0, 0);
			bipedRightLeg2.setRotationPoint(-9f, 26.0f, 0f);
			setRotation(bipedRightLeg2, (float) (90f*Math.PI/180), (float) (-13f*Math.PI/180), 0);			
			
			bipedLeftLeg.setRotationPoint(2.0f, 12.0f, -6f);
			setRotation(bipedLeftLeg, 0, 0, 0);
			bipedLeftLeg2.setRotationPoint(7, 26.0f, 0f);
			setRotation(bipedLeftLeg2, (float) (-90f*Math.PI/180), (float) (-2f*Math.PI/180), 0);
			
			swordhand.setRotationPoint(0f, 9.0f, -12f);
			setRotation(swordhand, (float) (90f*Math.PI/180), 0, 0);
			
			/* Wake Up Animation */
		}else if(var5.getWakeUpTimer() > 0){
			float wakeUpTimer = var5.getWakeUpTimer();
			float timeToWakeUp = 30;
			mapRotationPoint(bipedHead, wakeUpTimer, timeToWakeUp, 0, -6f, 17.8f, 0f,  0,0,0);
			mapRotation(bipedHead, wakeUpTimer, timeToWakeUp, 0, 0, 0, (float) (90f*Math.PI/180),  0,0,0);
			
			mapRotationPoint(bipedRightArm, wakeUpTimer, timeToWakeUp, 0, -6f, 17.8f, 0f,  -5,2,0);
			mapRotation(bipedRightArm, wakeUpTimer, timeToWakeUp, 0,  (float) (-66f*Math.PI/180), 0, 0,  0,0,0);

			mapRotationPoint(bipedLeftArm, wakeUpTimer, timeToWakeUp, 0, 7f, 23.0f, 4.0f,  5,2,0);
			mapRotation(bipedLeftArm, wakeUpTimer, timeToWakeUp, 0,  0, 0, (float) (-90f*Math.PI/180),  0,0,0);

			mapRotationPoint(bipedBody, wakeUpTimer, timeToWakeUp, 0.0f, 0, 22.0f, 9.5f,  0,0,0);
			mapRotation(bipedBody, wakeUpTimer,timeToWakeUp, 0,  (float) (-90f*Math.PI/180), 0, 0,  0,0,0);

			mapRotationPoint(bipedRightLeg, wakeUpTimer,timeToWakeUp, 0f, -2.0f, 12.0f, -7f,  -1.9f,12f,0);
			mapRotation(bipedRightLeg, wakeUpTimer,timeToWakeUp, 0,  0, 0, 0,  0,0,0);
			mapRotationPoint(bipedRightLeg2, wakeUpTimer,timeToWakeUp, 0f, -9f, 26.0f, 0f,  -1.9f,12f,0);
			mapRotation(bipedRightLeg2, wakeUpTimer,timeToWakeUp, 0, (float) (90f*Math.PI/180), (float) (-13f*Math.PI/180), 0,  0,0,0);

			mapRotationPoint(bipedLeftLeg, wakeUpTimer,timeToWakeUp, 0f, 2.0f, 12.0f, -6f,  1.9f,12f,0);
			mapRotation(bipedLeftLeg, wakeUpTimer,timeToWakeUp, 0,  0, 0, 0,  0,0,0);
			mapRotationPoint(bipedLeftLeg2, wakeUpTimer,timeToWakeUp, 0f, 7, 26.0f, 0f,  1.9f,12f,0);
			mapRotation(bipedLeftLeg2, wakeUpTimer,timeToWakeUp, 0,(float) (-90f*Math.PI/180), (float) (-2f*Math.PI/180), 0,  0,0,0);

			mapRotationPoint(swordhand, wakeUpTimer,timeToWakeUp, 0f, 0f, 9.0f, -12f,  0, 9f,-12f);
			mapRotation(swordhand, wakeUpTimer,timeToWakeUp, 0,(float) (90f*Math.PI/180),0,0,  (float) (-70f*Math.PI/180),0,0);
			
			/* Attack Animation */
		}else if(var5.getAnimTime() > 0 && var5.getEntityState() == EntityStates.attacking){
			mapRotationPoint(swordhand, var5.getAnimTime(), var5.maxAnimTime, 0, 0, 9f,-17f,  0, 9f,-8f);
			mapRotation(swordhand, var5.getAnimTime(), var5.maxAnimTime, 0, 
					(float) (0*Math.PI/180),(float) (-10*Math.PI/180),(float) (-30 *Math.PI/180),
					(float) (-70*Math.PI/180),(float) (10*Math.PI/180),(float) (30 *Math.PI/180));
			
			/* Living Animation */
		}else{

			float mapValue;
			
			mapValue = (float)(MathHelper.cos(par2*0.6662F + (float)Math.PI) * 30 );
			float mapStart = 30;
			float mapEnd = -30;
			mapRotationPoint(swordhand, mapValue, mapStart,mapEnd, -4f,9f,-8,  6,9f,-4f);
			mapRotation(swordhand, mapValue, mapStart,mapEnd, 
					(float) (-40*Math.PI/180),(float) (-10*Math.PI/180),(float) (-30 *Math.PI/180),
					(float) (-70*Math.PI/180),(float) (10*Math.PI/180),(float) (30 *Math.PI/180));
			
			mapRotationPoint(bipedHead, mapValue, mapStart,mapEnd, 0,-2f,0f,  0,2f,0);
			mapRotation(bipedHead, mapValue, mapStart,mapEnd, 0,0,-0.1f,  0, 0, 0.1f);
			
			mapRotationPoint(bipedRightArm, mapValue, mapStart,mapEnd, -6,3,0,  -5,1,0);
			mapRotation(bipedRightArm, mapValue, mapStart,mapEnd,  (float)(-30*Math.PI/180), 0, 0,  (float)(30*Math.PI/180),0,0);

			mapRotationPoint(bipedLeftArm, mapValue, mapStart,mapEnd, 6,3,0,  5,1,0);
			mapRotation(bipedLeftArm, mapValue, mapStart,mapEnd, (float)(-30*Math.PI/180), 0, 0.05f,  (float)(30*Math.PI/180),0,0);

			mapRotationPoint(bipedBody, mapValue, mapStart,mapEnd, 0,0,1f,  0,0,-1f);
			mapRotation(bipedBody, mapValue, mapStart,mapEnd,  0,-0.05f,0,  0,0.05f,0);

			mapValue = (float)(MathHelper.cos(par2*0.6662F + (float)Math.PI) * par3 * 45 );
			mapRotationPoint(bipedRightLeg, mapValue, mapStart,mapEnd, -2.9f,12.0f,1.0f,  -1.9f,12f,0);
			mapRotation(bipedRightLeg, mapValue, mapStart,mapEnd,  (float)(-30*Math.PI/180), 0, 0.05f,  (float)(30*Math.PI/180),0,0);
			mapRotationPoint(bipedRightLeg2, mapValue, mapStart,mapEnd, -1.9f,12f,0,  -1.9f,12f,0);
			mapRotation(bipedRightLeg2, mapValue, mapStart,mapEnd, (float)(-10*Math.PI/180), 0, 0.05f,  (float)(10*Math.PI/180),0,0);

			mapRotationPoint(bipedLeftLeg, mapValue, mapStart,mapEnd, 2.9f,12f,-1.0f,  1.9f,12f,0);
			mapRotation(bipedLeftLeg, mapValue, mapStart,mapEnd,  (float)(30*Math.PI/180), 0, 0.05f,  (float)(-30*Math.PI/180),0,0);
			mapRotationPoint(bipedLeftLeg2, mapValue, mapStart,mapEnd, 1.9f,12f,0,  1.9f,12f,0);
			mapRotation(bipedLeftLeg2, mapValue, mapStart,mapEnd,  (float)(10*Math.PI/180), 0, 0.05f,  (float)(-10*Math.PI/180),0,0);
		}

		
	}
	
	private void mapRotationPoint(ModelRenderer modelRenderer, float mapValue, float mapMin, float mapMax,
			 float valueSetXMin, float valueSetYMin, float valueSetZMin,
			 float valueSetXMax, float valueSetYMax, float valueSetZMax){
		modelRenderer.rotationPointX = mapValueofSet1ToSet2(mapValue, mapMin, mapMax, valueSetXMin, valueSetXMax);
		modelRenderer.rotationPointY = mapValueofSet1ToSet2(mapValue, mapMin, mapMax, valueSetYMin, valueSetYMax);
		modelRenderer.rotationPointZ = mapValueofSet1ToSet2(mapValue, mapMin, mapMax, valueSetZMin, valueSetZMax);
	}
	
	private void mapRotation(ModelRenderer modelRenderer, float mapValue, float mapMin, float mapMax,
			 float valueSetXMin, float valueSetYMin, float valueSetZMin,
			 float valueSetXMax, float valueSetYMax, float valueSetZMax){
		modelRenderer.rotateAngleX = mapValueofSet1ToSet2(mapValue, mapMin, mapMax, valueSetXMin, valueSetXMax);
		modelRenderer.rotateAngleY = mapValueofSet1ToSet2(mapValue, mapMin, mapMax, valueSetYMin, valueSetYMax);
		modelRenderer.rotateAngleZ = mapValueofSet1ToSet2(mapValue, mapMin, mapMax, valueSetZMin, valueSetZMax);
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
