package projectzulu.common.blocks;


import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

public class TileEntityUniversalFlowerPotRenderer extends TileEntitySpecialRenderer{
    private RenderBlocks blockRenderer = new RenderBlocks();
    private RenderItem itemRenderer = new RenderItem();
    
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double xRenderCoord, double yRenderCoord, double zRenderCoord, float var8) {
		
		renderTileEntityUniversalFlowerPotAt((TileEntityUniversalFlowerPot)tileEntity, xRenderCoord, yRenderCoord, zRenderCoord, var8);
	}

	private void renderTileEntityUniversalFlowerPotAt(TileEntityUniversalFlowerPot tileEntity, double xRenderCoord, double yRenderCoord, double zRenderCoord, float var8) {
		
		ItemStack itemStackToRender = tileEntity.getStackInSlot(0);
		
		if(itemStackToRender != null){
			Item itemToRender = itemStackToRender.getItem();
			
			/* Try to See if I should render as Blocks */
			if(itemToRender != null && Item.itemsList[itemToRender.itemID] instanceof ItemBlock && Block.blocksList[itemToRender.itemID].getRenderType() != -1){
				Block blockToRender = Block.blocksList[itemToRender.itemID];
				int meta = itemStackToRender.getItemDamage() > 16 ? 16 : itemStackToRender.getItemDamage() < 0 ? 0 : itemStackToRender.getItemDamage();
				
//				String textureLocation = blockToRender.getTextureFile(); //TODO: Commented
//				GL11.glBindTexture(GL11.GL_TEXTURE_2D, tileEntity.tileEntityRenderer.getTexture( textureLocation ));
//				bindTextureByName(textureLocation); //TODO: Commented
		        GL11.glPushMatrix();
		        float scaleBlock = (float) (0.29f / (blockToRender.getBlockBoundsMaxX() - blockToRender.getBlockBoundsMinX()));
		        float scaleY = (float) (scaleBlock * (blockToRender.getBlockBoundsMaxY() - blockToRender.getBlockBoundsMinY()));
		        
		        GL11.glTranslatef((float)xRenderCoord+0.5f, (float)(yRenderCoord + 0.2 + scaleY), (float)zRenderCoord+0.5f);
		        GL11.glScalef(scaleBlock, scaleBlock, scaleBlock);
		        
		        float brightness = 15f;
		        this.blockRenderer.renderBlockAsItem(blockToRender, meta, brightness);
		        GL11.glPopMatrix();
		        return;
			}
			
			/* Finally, try to render as Item */
			if(itemToRender != null ){
				itemRenderer.setRenderManager(RenderManager.instance);
				EntityItem entityItemToRender = new EntityItem(tileEntity.worldObj, xRenderCoord, yRenderCoord, zRenderCoord, itemStackToRender);
				entityItemToRender.hoverStart = 0;
				
		        float scaleItem = 0.6f;
		        GL11.glPushMatrix();
		        //TODO: Commented
//				GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture( itemToRender.getTextureFile() ));

		        GL11.glTranslatef((float)xRenderCoord + 0.5f, (float)(yRenderCoord + 0.3), (float)zRenderCoord + 0.5f);
		        GL11.glScalef(scaleItem, scaleItem, scaleItem);

				
				itemRenderer.doRenderItem(entityItemToRender, 0, 0, 0, 0, 0);
		        GL11.glPopMatrix();

			}
			
			
		}
	}


}
