package projectzulu.common.potion.brewingstands;

import java.util.EnumSet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import projectzulu.common.core.RenderHelper;
import projectzulu.common.core.RenderHelper.Surface;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderBrewingStandSingle implements ISimpleBlockRenderingHandler {

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public int getRenderId() {
        return 0;
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
            RenderBlocks renderer) {
        return render(world, x, y, z, block, modelId, renderer);
    }

    public boolean render(IBlockAccess blockAccess, int posX, int posY, int posZ, Block par1Block, int modelId,
            RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(par1Block.getMixedBrightnessForBlock(blockAccess, posX, posY, posZ));
        float var6 = 1.0F;
        int var7 = par1Block.colorMultiplier(blockAccess, posX, posY, posZ);
        float var8 = (var7 >> 16 & 255) / 255.0F;
        float var9 = (var7 >> 8 & 255) / 255.0F;
        float var10 = (var7 & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable) {
            float var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
            float var12 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
            float var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
            var8 = var11;
            var9 = var12;
            var10 = var13;
        }
        tessellator.setColorOpaque_F(var6 * var8, var6 * var9, var6 * var10);

        TileEntity tileEntity = blockAccess.getTileEntity(posX, posY, posZ);
        int potionNumber = 1;
        if (tileEntity != null && tileEntity instanceof TileEntityBrewingBase) {
            potionNumber = ((TileEntityBrewingBase) tileEntity).brewingItemStacks.length - 1;
        }

        IIcon brewingPlateIcon = ((BlockBrewingStand) Blocks.brewing_stand).getIconBrewingStandBase();
        IIcon bowlIcon = renderer.getBlockIconFromSideAndMetadata(Blocks.glass, 2, 0);
        IIcon bowlRing = renderer.getBlockIconFromSideAndMetadata(Blocks.cobblestone, 2, 0);
        IIcon legSkin = renderer.getBlockIconFromSideAndMetadata(Blocks.log, 2, 0);
        IIcon legBottom = renderer.getBlockIconFromSideAndMetadata(Blocks.log, 1, 0);
        if (potionNumber > 1) {
            bowlRing = renderer.getBlockIconFromSideAndMetadata(Blocks.glowstone, 1, 0);
            legSkin = renderer.getBlockIconFromSideAndMetadata(Blocks.nether_brick, 1, 0);
            legBottom = renderer.getBlockIconFromSideAndMetadata(Blocks.nether_brick, 1, 0);
        }

        /* Brewing Plates and Pipes */
        for (int i = 0; i < potionNumber; i++) {
            switch (i) {
            case 0:
                RenderHelper.renderRotatedRectangle(brewingPlateIcon, 1.0f, renderer, posX, posY, posZ, 0.20f, 0.05f,
                        0.20f, 0);
                RenderHelper.renderRotatedRectangle(bowlIcon, 1.10f, renderer, posX, posY + 0.38f, posZ, 0.08f, 0.2f,
                        0.08f, 0);
                break;
            case 1:
                RenderHelper.renderRotatedRectangle(brewingPlateIcon, 2.0f, renderer, posX, posY, posZ + 0.30f, 0.20f,
                        0.05f, 0.20f, 0);
                RenderHelper.renderRotatedRectangle(bowlIcon, 1.10f, renderer, posX, posY + 0.30f, posZ + 0.145, 0.27f,
                        0.08f, 0.08f, 0);
                break;
            case 2:
                RenderHelper.renderRotatedRectangle(brewingPlateIcon, 2.0f, renderer, posX, posY, posZ - 0.30f, 0.20f,
                        0.05f, 0.20f, 0);
                RenderHelper.renderRotatedRectangle(bowlIcon, 1.10f, renderer, posX, posY + 0.30f, posZ - 0.13f, 0.28f,
                        0.08f, 0.08f, 0);
                break;
            case 3:
                RenderHelper.renderRotatedRectangle(brewingPlateIcon, 2.0f, renderer, posX + 0.30f, posY, posZ, 0.20f,
                        0.05f, 0.20f, 0);
                RenderHelper.renderRotatedRectangle(bowlIcon, 1.10f, renderer, posX + 0.16, posY + 0.30f, posZ, 0.23f,
                        0.08f, 0.08f, 90);
                break;
            case 4:
                RenderHelper.renderRotatedRectangle(brewingPlateIcon, 2.0f, renderer, posX - 0.30f, posY, posZ, 0.20f,
                        0.05f, 0.20f, 0);
                RenderHelper.renderRotatedRectangle(bowlIcon, 1.10f, renderer, posX - 0.16, posY + 0.30f, posZ, 0.23f,
                        0.08f, 0.08f, 90);
                break;
            }
        }

        /* Glass Bowl */
        // RenderHelper.renderRotatedRectangle(bowlIcon, 1.10f, renderer, posX, posY + 0.52f, posZ, 0.23f, 0.2f, 0.23f,
        // 0,
        // EnumSet.of(Surface.RIGHT, Surface.LEFT, Surface.FRONT, Surface.BACK, Surface.BOTTOM));

        /* Stone Ring */
        float ringScale = 2.0f;
        RenderHelper.renderRotatedRectangle(bowlRing, ringScale, renderer, posX + 0.00f, posY + 0.415f, posZ, 0.3f,
                0.1f, 0.3f, 0);

        RenderHelper.renderRotatedRectangle(bowlRing, ringScale, renderer, posX + 0.20f, posY + 0.40f, posZ, 0.19f,
                0.2f, 0.10f, 0);
        RenderHelper.renderRotatedRectangle(bowlRing, ringScale, renderer, posX - 0.20f, posY + 0.40f, posZ, 0.19f,
                0.2f, 0.10f, 0);
        RenderHelper.renderRotatedRectangle(bowlRing, ringScale, renderer, posX, posY + 0.410f, posZ + 0.20f, 0.10f,
                0.189f, 0.20f, 0);
        RenderHelper.renderRotatedRectangle(bowlRing, ringScale, renderer, posX, posY + 0.410f, posZ - 0.20f, 0.10f,
                0.189f, 0.20f, 0);

        RenderHelper.renderRotatedRectangle(bowlRing, ringScale, renderer, posX + 0.14f, posY + 0.405f, posZ - 0.14f,
                0.10f, 0.189f, 0.19f, 45);
        RenderHelper.renderRotatedRectangle(bowlRing, ringScale, renderer, posX - 0.14f, posY + 0.405f, posZ + 0.14f,
                0.10f, 0.189f, 0.19f, 45);
        RenderHelper.renderRotatedRectangle(bowlRing, ringScale, renderer, posX + 0.14f, posY + 0.405f, posZ + 0.14f,
                0.10f, 0.189f, 0.19f, -45);
        RenderHelper.renderRotatedRectangle(bowlRing, ringScale, renderer, posX - 0.14f, posY + 0.405f, posZ - 0.14f,
                0.10f, 0.189f, 0.19f, -45);

        /* Legs */
        RenderHelper.renderRotatedRectangle(legSkin, 3.0f, renderer, posX + 0.13f, posY + 0.2f, posZ + 0.13f, 0.10f,
                0.20f, 0.10f, 45);
        RenderHelper.renderRotatedRectangle(legSkin, 3.0f, renderer, posX - 0.13f, posY + 0.2f, posZ + 0.13f, 0.10f,
                0.20, 0.10f, 45);
        RenderHelper.renderRotatedRectangle(legSkin, 3.0f, renderer, posX + 0.13f, posY + 0.2f, posZ - 0.13f, 0.10f,
                0.20f, 0.10f, 45);
        RenderHelper.renderRotatedRectangle(legSkin, 3.0f, renderer, posX - 0.13f, posY + 0.2f, posZ - 0.13f, 0.10f,
                0.20f, 0.10f, 45);

        RenderHelper.renderRotatedRectangle(legSkin, 3.0f, renderer, posX + 0.13f, posY + 0.01f, posZ + 0.13f, 0.10f,
                0.19f, 0.10f, 45 + 180);
        RenderHelper.renderRotatedRectangle(legSkin, 3.0f, renderer, posX - 0.13f, posY + 0.01f, posZ + 0.13f, 0.10f,
                0.19f, 0.10f, 45 + 180);
        RenderHelper.renderRotatedRectangle(legSkin, 3.0f, renderer, posX + 0.13f, posY + 0.01f, posZ - 0.13f, 0.10f,
                0.19f, 0.10f, 45 + 180);
        RenderHelper.renderRotatedRectangle(legSkin, 3.0f, renderer, posX - 0.13f, posY + 0.01f, posZ - 0.13f, 0.10f,
                0.19f, 0.10f, 45 + 180);

        /* Feet */
        EnumSet<Surface> plainSides = EnumSet.of(Surface.LEFT);
        EnumSet<Surface> barkSides = EnumSet.allOf(Surface.class);
        barkSides.remove(Surface.LEFT);
        RenderHelper.renderRotatedRectangle(legSkin, 3.0f, renderer, posX + 0.23f, posY, posZ - 0.23f, 0.099f, 0.10f,
                0.20f, -45, barkSides);
        RenderHelper.renderRotatedRectangle(legSkin, 3.0f, renderer, posX - 0.23f, posY, posZ + 0.23f, 0.099f, 0.10f,
                0.20f, -45 + 180, barkSides);
        RenderHelper.renderRotatedRectangle(legSkin, 3.0f, renderer, posX + 0.23f, posY, posZ + 0.23f, 0.099f, 0.10f,
                0.20f, +45, barkSides);
        RenderHelper.renderRotatedRectangle(legSkin, 3.0f, renderer, posX - 0.23f, posY, posZ - 0.23f, 0.099f, 0.10f,
                0.20f, +45 + 180, barkSides);

        RenderHelper.renderRotatedRectangle(legBottom, 3.0f, renderer, posX + 0.23f, posY, posZ - 0.23f, 0.099f, 0.10f,
                0.20f, -45, plainSides);
        RenderHelper.renderRotatedRectangle(legBottom, 3.0f, renderer, posX - 0.23f, posY, posZ + 0.23f, 0.099f, 0.10f,
                0.20f, -45 + 180, plainSides);
        RenderHelper.renderRotatedRectangle(legBottom, 3.0f, renderer, posX + 0.23f, posY, posZ + 0.23f, 0.099f, 0.10f,
                0.20f, +45, plainSides);
        RenderHelper.renderRotatedRectangle(legBottom, 3.0f, renderer, posX - 0.23f, posY, posZ - 0.23f, 0.099f, 0.10f,
                0.20f, +45 + 180, plainSides);
        return true;
    }
}
