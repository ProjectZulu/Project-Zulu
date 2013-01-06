package projectzulu.common.mobs;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;


public class RenderHauntedArmor extends RenderLiving{
	protected ModelHauntedArmor modelBipedMain;
	protected static EntityHauntedArmor entityHauntedArmor;
	protected float field_77070_b;

	public RenderHauntedArmor(ModelHauntedArmor modelBase, float par1){
		super(modelBase, par1);
		this.modelBipedMain = modelBase;
	}


	public void renderHauntedArmor(EntityHauntedArmor par1EntityHauntedArmor, double par2, double par4, double par6, float par8, float par9){
		entityHauntedArmor = par1EntityHauntedArmor;
		super.doRenderLiving(par1EntityHauntedArmor, par2, par4, par6, par8, par9);
	}

	@Override
	protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2){
		renderItemInHand(par1EntityLiving);
	}
	
	private void renderItemInHand(EntityLiving par1EntityLiving) {
		ItemStack var3 = par1EntityLiving.getHeldItem();
		if (var3 != null){
			GL11.glPushMatrix();
			this.modelBipedMain.swordhand.postRender(0.0625F);
//			GL11.glTranslatef(-0.0625F, -0.4375F, 0.0625F);
			float var4;

			if (var3.itemID < 256 && RenderBlocks.renderItemIn3d(Block.blocksList[var3.itemID].getRenderType())){
				var4 = 0.5F;
				GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
				var4 *= 0.75F;
				GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(var4, -var4, var4);
			}
			else if (var3.itemID == Item.bow.shiftedIndex){
				var4 = 0.625F;
				GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
				GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
				GL11.glScalef(var4, -var4, var4);
				GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			}
			else if (Item.itemsList[var3.itemID].isFull3D()){
				var4 = 0.625F;
				GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
				GL11.glScalef(var4, -var4, var4);
				GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
			}
			else{
				var4 = 0.375F;
				GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
				GL11.glScalef(var4, var4, var4);
				GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
				GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
			}
			this.renderManager.itemRenderer.renderItem(par1EntityLiving, var3, 0);

			if (var3.getItem().requiresMultipleRenderPasses())
			{
				this.renderManager.itemRenderer.renderItem(par1EntityLiving, var3, 1);
			}
			GL11.glPopMatrix();
		}
	}    

	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9){
		this.renderHauntedArmor((EntityHauntedArmor)par1EntityLiving, par2, par4, par6, par8, par9);
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
	 * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
	 * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
	 * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	 @Override
	 public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9){
		 this.renderHauntedArmor((EntityHauntedArmor)par1Entity, par2, par4, par6, par8, par9);
	 }
}


