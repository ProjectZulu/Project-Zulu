package projectzulu.common.potion.brewingstands;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class TileEntityBrewingStandRenderer extends TileEntitySpecialRenderer {
    
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double xRenderCoord, double yRenderCoord,
            double zRenderCoord, float var8) {
        renderTileEntity((TileEntityBrewingBase) tileEntity, xRenderCoord, yRenderCoord, zRenderCoord, var8);
    }

    private void renderTileEntity(TileEntityBrewingBase tileEntity, double xRenderCoord, double yRenderCoord,
            double zRenderCoord, float var8) {
        RenderItem itemRenderer = new RenderItem();
        itemRenderer.setRenderManager(RenderManager.instance);

        ItemStack ingredientStack = tileEntity.brewingItemStacks[tileEntity.brewingItemStacks.length - 1];
        if (ingredientStack != null) {
            if (ingredientStack.stackSize > 1) {
                renderItemStack(ingredientStack, itemRenderer, tileEntity.getWorldObj(), xRenderCoord, yRenderCoord + 0.3D,
                        zRenderCoord, (float) (Math.pow(400 - tileEntity.getBrewTime(), 2)) / 20 + 90f, 0.28f);
            } else {
                renderItemStack(ingredientStack, itemRenderer, tileEntity.getWorldObj(), xRenderCoord, yRenderCoord + 0.3D,
                        zRenderCoord, (float) (Math.pow(400 - tileEntity.getBrewTime(), 2)) / 20 + 90f, 0.4f);
            }
        }

        float[] zOffsets = new float[] { 0, +0.29f, -0.29f };
        float[] rotation = new float[] { 90f, 0.0f, 0.0f };
        for (int i = 0; i < tileEntity.brewingItemStacks.length - 1; i++) {
            ItemStack stackToRender = tileEntity.brewingItemStacks[i];
            if (stackToRender == null) {
                continue;
            }
            renderItemStack(stackToRender, itemRenderer, tileEntity.getWorldObj(), xRenderCoord, yRenderCoord - 0.25D,
                    zRenderCoord + zOffsets[i], rotation[i], 0.6f);
        }
    }

    private void renderItemStack(ItemStack stackToRender, RenderItem renderer, World world, double xRenderCoord,
            double yRenderCoord, double zRenderCoord, float rotation, float scale) {
        EntityItem entityItemToRender = new EntityItem(world, xRenderCoord, yRenderCoord, zRenderCoord, stackToRender);
        entityItemToRender.hoverStart = 0;

        float scaleItem = scale;
        GL11.glPushMatrix();
        bindTexture(TextureMap.locationBlocksTexture);
        GL11.glTranslatef((float) xRenderCoord + 0.5f, (float) (yRenderCoord + 0.3), (float) zRenderCoord + 0.5f);
        GL11.glScalef(scaleItem, scaleItem, scaleItem);
        GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F);
        renderer.doRender(entityItemToRender, 0, 0, 0, 0, 0);
        GL11.glPopMatrix();
    }
}