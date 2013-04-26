package projectzulu.common.potion.brewingstands;

import java.util.EnumSet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import projectzulu.common.core.RenderHelper;
import projectzulu.common.core.RenderHelper.Surface;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderBrewingStandSingle implements ISimpleBlockRenderingHandler {

    public final int potionNumber;

    public RenderBrewingStandSingle(int potionNumber) {
        this.potionNumber = potionNumber;
    }
    
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

    }

    @Override
    public boolean shouldRender3DInInventory() {
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

        /* ICONS */
        Icon glassIcon = renderer.getBlockIconFromSideAndMetadata(Block.glass, 2, 0);
        Icon cobbleIcon = renderer.getBlockIconFromSideAndMetadata(Block.cobblestone, 2, 0);
        Icon stoneIcon = renderer.getBlockIconFromSideAndMetadata(Block.stone, 2, 0);
        Icon steelIcon = renderer.getBlockIconFromSideAndMetadata(Block.blockSteel, 2, 0);
        Icon brewingPlateIcon = ((BlockBrewingStand)Block.brewingStand).func_94448_e();
        Icon barkIcon = renderer.getBlockIconFromSideAndMetadata(Block.wood, 2, 0);
        Icon woodIcon = renderer.getBlockIconFromSideAndMetadata(Block.wood, 1, 0);
        
        /* Glass Bowl */
        RenderHelper.renderRotatedRectangle(glassIcon, 1.10f, renderer, posX, posY + 0.52f, posZ, 0.23f, 0.2f, 0.23f, 0,
                EnumSet.of(Surface.RIGHT, Surface.LEFT, Surface.FRONT, Surface.BACK, Surface.BOTTOM));
//        RenderHelper.renderRotatedRectangle(glassIcon, 1.10f, renderer, posX + 0.12, posY + 0.52f, posZ, 0.23f, 0.2f,
//                0.05f, 0, EnumSet.of(Surface.TOP, Surface.RIGHT));
//        RenderHelper.renderRotatedRectangle(glassIcon, 1.10f, renderer, posX - 0.12, posY + 0.52f, posZ, 0.23f, 0.2f,
//                0.05f, 0, EnumSet.of(Surface.TOP, Surface.LEFT));
//        RenderHelper.renderRotatedRectangle(glassIcon, 1.10f, renderer, posX, posY + 0.52f, posZ + 0.08, 0.05f, 0.2f,
//                0.16f, 0, EnumSet.of(Surface.TOP, Surface.FRONT));
//        RenderHelper.renderRotatedRectangle(glassIcon, 1.10f, renderer, posX, posY + 0.52f, posZ - 0.08, 0.05f, 0.2f,
//                0.16f, 0, EnumSet.of(Surface.TOP, Surface.BACK));

        /* Stone Ring */
        float iconScale = 2.0f;
        RenderHelper.renderRotatedRectangle(stoneIcon, iconScale, renderer, posX + 0.00f, posY + 0.415f, posZ, 0.3f,
                0.1f, 0.3f, 0);
        RenderHelper.renderRotatedRectangle(stoneIcon, iconScale, renderer, posX + 0.17f, posY + 0.40f, posZ, 0.30f,
                0.2f, 0.10f, 0);
        RenderHelper.renderRotatedRectangle(stoneIcon, iconScale, renderer, posX - 0.17f, posY + 0.40f, posZ, 0.30f,
                0.2f, 0.10f, 0);
        RenderHelper.renderRotatedRectangle(stoneIcon, iconScale, renderer, posX, posY + 0.410f, posZ + 0.17f, 0.10f,
                0.189f, 0.3f, 0);
        RenderHelper.renderRotatedRectangle(stoneIcon, iconScale, renderer, posX, posY + 0.410f, posZ - 0.17f, 0.10f,
                0.189f, 0.3f, 0);
        
        TileEntity tileEntity = blockAccess.getBlockTileEntity(posX, posY, posZ);
        int potionNumber = 1;
        if (tileEntity != null && tileEntity instanceof TileEntityBrewingTriple) {
            potionNumber = ((TileEntityBrewingTriple) tileEntity).brewingItemStacks.length - 1;
        }
        for (int i = 0; i < potionNumber; i++) {
            switch (i) {
            case 0:
                RenderHelper.renderRotatedRectangle(brewingPlateIcon, 1.0f, renderer, posX, posY, posZ, 0.20f, 0.05f,
                        0.20f, 0);
                RenderHelper.renderRotatedRectangle(glassIcon, 1.10f, renderer, posX, posY + 0.38f, posZ, 0.08f, 0.2f,
                        0.08f, 0);
                break;
            case 1:
                RenderHelper.renderRotatedRectangle(brewingPlateIcon, 2.0f, renderer, posX, posY, posZ + 0.30f, 0.20f,
                        0.05f, 0.20f, 0);
                RenderHelper.renderRotatedRectangle(glassIcon, 1.10f, renderer, posX, posY + 0.30f, posZ + 0.125,
                        0.25f, 0.08f, 0.08f, 0);
                break;
            case 2:
                RenderHelper.renderRotatedRectangle(brewingPlateIcon, 2.0f, renderer, posX, posY, posZ - 0.30f, 0.20f,
                        0.05f, 0.20f, 0);
                RenderHelper.renderRotatedRectangle(glassIcon, 1.10f, renderer, posX, posY + 0.30f, posZ - 0.13, 0.25f,
                        0.08f, 0.08f, 0);
                break;
            case 3:
                RenderHelper.renderRotatedRectangle(brewingPlateIcon, 2.0f, renderer, posX + 0.30f, posY, posZ, 0.20f,
                        0.05f, 0.20f, 0);
                RenderHelper.renderRotatedRectangle(glassIcon, 1.10f, renderer, posX + 0.16, posY + 0.30f, posZ, 0.23f,
                        0.08f, 0.08f, 90);
                break;
            case 4:
                RenderHelper.renderRotatedRectangle(brewingPlateIcon, 2.0f, renderer, posX - 0.30f, posY, posZ, 0.20f,
                        0.05f, 0.20f, 0);
                RenderHelper.renderRotatedRectangle(glassIcon, 1.10f, renderer, posX - 0.16, posY + 0.30f, posZ, 0.23f,
                        0.08f, 0.08f, 90);
                break;
            }
        }
        
        /* Legs */
        RenderHelper.renderRotatedRectangle(barkIcon, 3.0f, renderer, posX + 0.13f, posY+0.2f, posZ + 0.13f, 0.10f, 0.19f, 0.10f, 45);
        RenderHelper.renderRotatedRectangle(barkIcon, 3.0f, renderer, posX - 0.13f, posY+0.2f, posZ + 0.13f, 0.10f, 0.219, 0.10f, 45);
        RenderHelper.renderRotatedRectangle(barkIcon, 3.0f, renderer, posX + 0.13f, posY+0.2f, posZ - 0.13f, 0.10f, 0.19f, 0.10f, 45);
        RenderHelper.renderRotatedRectangle(barkIcon, 3.0f, renderer, posX - 0.13f, posY+0.2f, posZ - 0.13f, 0.10f, 0.19f, 0.10f, 45);
        
        RenderHelper.renderRotatedRectangle(barkIcon, 3.0f, renderer, posX + 0.13f, posY+0.01f, posZ + 0.13f, 0.10f, 0.19f, 0.10f, 45+180);
        RenderHelper.renderRotatedRectangle(barkIcon, 3.0f, renderer, posX - 0.13f, posY+0.01f, posZ + 0.13f, 0.10f, 0.19f, 0.10f, 45+180);
        RenderHelper.renderRotatedRectangle(barkIcon, 3.0f, renderer, posX + 0.13f, posY+0.01f, posZ - 0.13f, 0.10f, 0.19f, 0.10f, 45+180);
        RenderHelper.renderRotatedRectangle(barkIcon, 3.0f, renderer, posX - 0.13f, posY+0.01f, posZ - 0.13f, 0.10f, 0.19f, 0.10f, 45+180);

        /* Feet */
        
        EnumSet<Surface> plainSides = EnumSet.of(Surface.LEFT);
        EnumSet<Surface> barkSides = EnumSet.allOf(Surface.class);
        barkSides.remove(Surface.LEFT);
        RenderHelper.renderRotatedRectangle(barkIcon, 3.0f, renderer, posX+0.25f, posY, posZ-0.25f, 0.10f, 0.10f, 0.25f, -45, barkSides);
        RenderHelper.renderRotatedRectangle(barkIcon, 3.0f, renderer, posX-0.25f, posY, posZ+0.25f, 0.10f, 0.10f, 0.25f, -45+180, barkSides);
        RenderHelper.renderRotatedRectangle(barkIcon, 3.0f, renderer, posX+0.25f, posY, posZ+0.25f, 0.10f, 0.10f, 0.25f, +45, barkSides);
        RenderHelper.renderRotatedRectangle(barkIcon, 3.0f, renderer, posX-0.25f, posY, posZ-0.25f, 0.10f, 0.10f, 0.25f, +45+180, barkSides);

        RenderHelper.renderRotatedRectangle(woodIcon, 3.0f, renderer, posX+0.25f, posY, posZ-0.25f, 0.11f, 0.10f, 0.25f, -45, plainSides);
        RenderHelper.renderRotatedRectangle(woodIcon, 3.0f, renderer, posX-0.25f, posY, posZ+0.25f, 0.11f, 0.10f, 0.25f, -45+180, plainSides);
        RenderHelper.renderRotatedRectangle(woodIcon, 3.0f, renderer, posX+0.25f, posY, posZ+0.25f, 0.11f, 0.10f, 0.25f, +45, plainSides);
        RenderHelper.renderRotatedRectangle(woodIcon, 3.0f, renderer, posX-0.25f, posY, posZ-0.25f, 0.11f, 0.10f, 0.25f, +45+180, plainSides);
        return true;
    }
}
