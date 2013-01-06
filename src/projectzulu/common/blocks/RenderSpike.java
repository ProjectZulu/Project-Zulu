package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderSpike implements ISimpleBlockRenderingHandler{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess blockAccess, int par2, int par3, int par4,
			Block par1Block, int modelId, RenderBlocks renderer) {
		
		return Render(blockAccess, par2, par3, par4, par1Block, modelId, renderer);
		
	}

	private boolean Render(IBlockAccess blockAccess, int par2, int par3, int par4,
			Block par1Block, int modelId, RenderBlocks renderer){
        Tessellator var5 = Tessellator.instance;
        var5.setBrightness(par1Block.getMixedBrightnessForBlock(blockAccess, par2, par3, par4));
        float var6 = 1.0F;
        int var7 = par1Block.colorMultiplier(blockAccess, par2, par3, par4);
        float var8 = (float)(var7 >> 16 & 255) / 255.0F;
        float var9 = (float)(var7 >> 8 & 255) / 255.0F;
        float var10 = (float)(var7 & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float var11 = (var8 * 30.0F + var9 * 59.0F + var10 * 11.0F) / 100.0F;
            float var12 = (var8 * 30.0F + var9 * 70.0F) / 100.0F;
            float var13 = (var8 * 30.0F + var10 * 70.0F) / 100.0F;
            var8 = var11;
            var9 = var12;
            var10 = var13;
        }

        var5.setColorOpaque_F(var6 * var8, var6 * var9, var6 * var10);
        double var19 = (double)par2;
        double var20 = (double)par3;
        double var15 = (double)par4;

//        if (par1Block == Block.tallGrass)
//        {
//            long var17 = (long)(par2 * 3129871) ^ (long)par4 * 116129781L ^ (long)par3;
//            var17 = var17 * var17 * 42317861L + var17 * 11L;
//            var19 += ((double)((float)(var17 >> 16 & 15L) / 15.0F) - 0.5D) * 0.5D;
//            var20 += ((double)((float)(var17 >> 20 & 15L) / 15.0F) - 1.0D) * 0.2D;
//            var15 += ((double)((float)(var17 >> 24 & 15L) / 15.0F) - 0.5D) * 0.5D;
//        }
        if(blockAccess.getBlockId(par2, par3-1, par4) == Block.fence.blockID || blockAccess.getBlockId(par2, par3-1, par4) == Block.netherFence.blockID ){
//            renderAsSpike(blockAccess, par2, par3, par4, par1Block, modelId, renderer);
        	RenderFencePikes(par1Block, blockAccess.getBlockMetadata(par2, par3, par4), var19, var20, var15, renderer);
        	
        }else if(blockAccess.getBlockId(par2, par3-1, par4) == Block.fenceIron.blockID){
            boolean isNegZ = canThisPaneConnectToThisBlockID( blockAccess.getBlockId(par2, par3-1, par4 - 1) );
            boolean isPosZ = canThisPaneConnectToThisBlockID( blockAccess.getBlockId(par2, par3-1, par4 + 1) );
            boolean isNegX = canThisPaneConnectToThisBlockID( blockAccess.getBlockId(par2 - 1, par3-1, par4) );
            boolean isPosX = canThisPaneConnectToThisBlockID( blockAccess.getBlockId(par2 + 1, par3-1, par4) );
            
        	/* Check For Along Z*/
            if(isNegZ && isPosZ){
            	drawSpikeStrip(par1Block, blockAccess.getBlockMetadata(par2, par3, par4), var19, var20, var15, renderer,false,0);
        	}else if(!isNegZ && isPosZ){
        		drawSpikeStrip(par1Block, blockAccess.getBlockMetadata(par2, par3, par4), var19, var20, var15, renderer,false,1);
        	}else if(isNegZ && !isPosZ){
        		drawSpikeStrip(par1Block, blockAccess.getBlockMetadata(par2, par3, par4), var19, var20, var15, renderer,false,2);
        	}
            
        	/* Check For Along X*/
        	if(isNegX && isPosX){
        		drawSpikeStrip(par1Block, blockAccess.getBlockMetadata(par2, par3, par4), var19, var20, var15, renderer,true,0);
        	}else if(!isNegX && isPosX){
        		drawSpikeStrip(par1Block, blockAccess.getBlockMetadata(par2, par3, par4), var19, var20, var15, renderer,true,1);
        	}else if(isNegX && !isPosX){
        		drawSpikeStrip(par1Block, blockAccess.getBlockMetadata(par2, par3, par4), var19, var20, var15, renderer,true,2);
        	}
        	
        	if(!isNegZ && !isPosZ && !isNegX && !isPosX){
        		drawSpikeCross(par1Block, blockAccess.getBlockMetadata(par2, par3, par4), var19, var20, var15, renderer);
        	}
        	
        }else{
            drawCrossedSquares(par1Block, blockAccess.getBlockMetadata(par2, par3, par4), var19, var20, var15, renderer);
        }
        return true;
	}
	
    /**
     * Copied from BlockPane (Iron Fence).
     * Gets passed in the blockID of the block adjacent and supposed to return true if its allowed to connect to the
     * type of blockID passed in. Args: blockID
     */
    private final boolean canThisPaneConnectToThisBlockID(int par1)
    {
        return Block.opaqueCubeLookup[par1] || par1 == Block.fenceIron.blockID || par1 == Block.glass.blockID;
    }

    public void drawCrossedSquares(Block par1Block, int par2, double par3, double par5, double par7, RenderBlocks renderer)
    {
        Tessellator var9 = Tessellator.instance;
        int var10 = par1Block.getBlockTextureFromSideAndMetadata(0, par2);

        if (renderer.overrideBlockTexture >= 0){
            var10 = renderer.overrideBlockTexture;
        }

        int var11 = (var10 & 15) << 4;
        int var12 = var10 & 240;
        double var13 = (double)((float)var11 / 256.0F);
        double var15 = (double)(((float)var11 + 15.99F) / 256.0F);
        double var17 = (double)((float)var12 / 256.0F);
        double var19 = (double)(((float)var12 + 15.99F) / 256.0F);
        double var21 = par3 + 0.5D - 0.5D;
        double var23 = par3 + 0.5D + 0.5D;
        double var25 = par7 + 0.5D - 0.5D;
        double var27 = par7 + 0.5D + 0.5D;
                        
        switch (par2) {
        case 0:
        	drawOnSide0(var9, par5, var21, var23, var25, var27, var13, var15, var17, var19);
        	break;
        case 1:
        	drawOnSide1(var9, par5, var21, var23, var25, var27, var13, var15, var17, var19);
        	break;
        case 2:
        	drawOnSide2(var9, par5, var21, var23, var25, var27, var13, var15, var17, var19);
        	break;
        case 3:
        	drawOnSide3(var9, par5, var21, var23, var25, var27, var13, var15, var17, var19);
        	break;
        case 4:
        	drawOnSide4(var9, par5, var21, var23, var25, var27, var13, var15, var17, var19);
        	break;
        case 5:
        	drawOnSide5(var9, par5, var21, var23, var25, var27, var13, var15, var17, var19);
        	break;
        	
        case 6:
        	drawOnSide0(var9, par5, var21, var23, var25, var27, var13, var15, var17, var19);
        	break;
        case 7:
        	drawOnSide1(var9, par5, var21, var23, var25, var27, var13, var15, var17, var19);
        	break;
        case 8:
        	drawOnSide2(var9, par5, var21, var23, var25, var27, var13, var15, var17, var19);
        	break;
        case 9:
        	drawOnSide3(var9, par5, var21, var23, var25, var27, var13, var15, var17, var19);
        	break;
        case 10:
        	drawOnSide4(var9, par5, var21, var23, var25, var27, var13, var15, var17, var19);
        	break;
        case 11:
        	drawOnSide5(var9, par5, var21, var23, var25, var27, var13, var15, var17, var19);
        	break;
        	
        default:
        	drawOnSide0(var9, par5, var21, var23, var25, var27, var13, var15, var17, var19);
        	break;
        }
        
        
    }
    
    public void drawOnSide0(Tessellator var9, double par5, double var21, double var23, double var25, double var27, double var13, double var15, double var17, double var19){
        /* Bottom Facing Texture */
        var9.addVertexWithUV(var21, par5 + 1.0D, var25, var13, var17);
        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var13, var19);
        var9.addVertexWithUV(var23, par5 + 0.0D, var27, var15, var19);
        var9.addVertexWithUV(var23, par5 + 1.0D, var27, var15, var17);
        
        var9.addVertexWithUV(var23, par5 + 1.0D, var27, var13, var17);
        var9.addVertexWithUV(var23, par5 + 0.0D, var27, var13, var19);
        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var15, var19);
        var9.addVertexWithUV(var21, par5 + 1.0D, var25, var15, var17);

        /* Bottom Facing Texture */
        var9.addVertexWithUV(var21, par5 + 1.0D, var27, var13, var17);
        var9.addVertexWithUV(var21, par5 + 0.0D, var27, var13, var19);
        var9.addVertexWithUV(var23, par5 + 0.0D, var25, var15, var19);
        var9.addVertexWithUV(var23, par5 + 1.0D, var25, var15, var17);
        
        var9.addVertexWithUV(var23, par5 + 1.0D, var25, var13, var17);
        var9.addVertexWithUV(var23, par5 + 0.0D, var25, var13, var19);
        var9.addVertexWithUV(var21, par5 + 0.0D, var27, var15, var19);
        var9.addVertexWithUV(var21, par5 + 1.0D, var27, var15, var17);
    }
    
    public void drawOnSide1(Tessellator var9, double par5, double var21, double var23, double var25, double var27, double var13, double var15, double var17, double var19){
        /* First Texture */
    	var9.addVertexWithUV(var23, par5 + 1.0D, var25, var13, var17);
        var9.addVertexWithUV(var21, par5 + 1.0D, var25, var13, var19);
        var9.addVertexWithUV(var21, par5 + 0.0D, var27, var15, var19);
        var9.addVertexWithUV(var23, par5 + 0.0D, var27, var15, var17);

        var9.addVertexWithUV(var23, par5 + 0.0D, var27, var15, var17);
        var9.addVertexWithUV(var21, par5 + 0.0D, var27, var15, var19);
        var9.addVertexWithUV(var21, par5 + 1.0D, var25, var13, var19);        
        var9.addVertexWithUV(var23, par5 + 1.0D, var25, var13, var17);
        
        /* Second Texture */
        var9.addVertexWithUV(var23, par5 + 1.0D, var27, var13, var17);
        var9.addVertexWithUV(var21, par5 + 1.0D, var27, var13, var19);
        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var15, var19);
        var9.addVertexWithUV(var23, par5 + 0.0D, var25, var15, var17);
        
        var9.addVertexWithUV(var23, par5 + 0.0D, var25, var15, var17);
        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var15, var19);
        var9.addVertexWithUV(var21, par5 + 1.0D, var27, var13, var19);
        var9.addVertexWithUV(var23, par5 + 1.0D, var27, var13, var17);
    }
    
    public void drawOnSide2(Tessellator var9, double par5, double var21, double var23, double var25, double var27, double var13, double var15, double var17, double var19){
        /* First Texture */
        var9.addVertexWithUV(var23, par5 + 1.0D, var27, var13, var17);
        var9.addVertexWithUV(var23, par5 + 1.0D, var25, var13, var19);
        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var15, var19);
        var9.addVertexWithUV(var21, par5 + 0.0D, var27, var15, var17);

        var9.addVertexWithUV(var21, par5 + 0.0D, var27, var15, var17);
        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var15, var19);
        var9.addVertexWithUV(var23, par5 + 1.0D, var25, var13, var19);
        var9.addVertexWithUV(var23, par5 + 1.0D, var27, var13, var17);
        
        /* Second Texture */
        var9.addVertexWithUV(var21, par5 + 1.0D, var27, var13, var17);
        var9.addVertexWithUV(var21, par5 + 1.0D, var25, var13, var19);
        var9.addVertexWithUV(var23, par5 + 0.0D, var25, var15, var19);
        var9.addVertexWithUV(var23, par5 + 0.0D, var27, var15, var17);
        
        var9.addVertexWithUV(var23, par5 + 0.0D, var27, var15, var17);
        var9.addVertexWithUV(var23, par5 + 0.0D, var25, var15, var19);
        var9.addVertexWithUV(var21, par5 + 1.0D, var25, var13, var19);
        var9.addVertexWithUV(var21, par5 + 1.0D, var27, var13, var17);
    }
    
    public void drawOnSide3(Tessellator var9, double par5, double var21, double var23, double var25, double var27, double var13, double var15, double var17, double var19){
        /* First Texture */
        var9.addVertexWithUV(var21, par5 + 1.0D, var27, var13, var17);
        var9.addVertexWithUV(var23, par5 + 1.0D, var27, var13, var19);
        var9.addVertexWithUV(var23, par5 + 0.0D, var25, var15, var19);
        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var15, var17);

        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var15, var17);
        var9.addVertexWithUV(var23, par5 + 0.0D, var25, var15, var19);
        var9.addVertexWithUV(var23, par5 + 1.0D, var27, var13, var19);
        var9.addVertexWithUV(var21, par5 + 1.0D, var27, var13, var17);
        
        /* Second Texture */
        var9.addVertexWithUV(var21, par5 + 1.0D, var25, var13, var17);
        var9.addVertexWithUV(var23, par5 + 1.0D, var25, var13, var19);
        var9.addVertexWithUV(var23, par5 + 0.0D, var27, var15, var19);
        var9.addVertexWithUV(var21, par5 + 0.0D, var27, var15, var17);
        
        var9.addVertexWithUV(var21, par5 + 0.0D, var27, var15, var17);
        var9.addVertexWithUV(var23, par5 + 0.0D, var27, var15, var19);
        var9.addVertexWithUV(var23, par5 + 1.0D, var25, var13, var19);
        var9.addVertexWithUV(var21, par5 + 1.0D, var25, var13, var17);
    }
    
    public void drawOnSide4(Tessellator var9, double par5, double var21, double var23, double var25, double var27, double var13, double var15, double var17, double var19){
        /* First Texture */
        var9.addVertexWithUV(var21, par5 + 1.0D, var25, var13, var17);
        var9.addVertexWithUV(var21, par5 + 1.0D, var27, var13, var19);
        var9.addVertexWithUV(var23, par5 + 0.0D, var27, var15, var19);
        var9.addVertexWithUV(var23, par5 + 0.0D, var25, var15, var17);

        var9.addVertexWithUV(var23, par5 + 0.0D, var25, var15, var17);
        var9.addVertexWithUV(var23, par5 + 0.0D, var27, var15, var19);
        var9.addVertexWithUV(var21, par5 + 1.0D, var27, var13, var19);
        var9.addVertexWithUV(var21, par5 + 1.0D, var25, var13, var17);
        
        /* Second Texture */
        var9.addVertexWithUV(var23, par5 + 1.0D, var25, var13, var17);
        var9.addVertexWithUV(var23, par5 + 1.0D, var27, var13, var19);
        var9.addVertexWithUV(var21, par5 + 0.0D, var27, var15, var19);
        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var15, var17);
        
        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var15, var17);
        var9.addVertexWithUV(var21, par5 + 0.0D, var27, var15, var19);
        var9.addVertexWithUV(var23, par5 + 1.0D, var27, var13, var19);
        var9.addVertexWithUV(var23, par5 + 1.0D, var25, var13, var17);
    }
	
    public void drawOnSide5(Tessellator var9, double par5, double var21, double var23, double var25, double var27, double var13, double var15, double var17, double var19){
        
        /* First Texture */
        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var13, var17);
        var9.addVertexWithUV(var21, par5 + 1.0D, var25, var13, var19);
        var9.addVertexWithUV(var23, par5 + 1.0D, var27, var15, var19);
        var9.addVertexWithUV(var23, par5 + 0.0D, var27, var15, var17);
        
        var9.addVertexWithUV(var23, par5 + 0.0D, var27, var13, var17);
        var9.addVertexWithUV(var23, par5 + 1.0D, var27, var13, var19);
        var9.addVertexWithUV(var21, par5 + 1.0D, var25, var15, var19);
        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var15, var17);

        /* Second Texture */
        var9.addVertexWithUV(var21, par5 + 0.0D, var27, var13, var17);
        var9.addVertexWithUV(var21, par5 + 1.0D, var27, var13, var19);
        var9.addVertexWithUV(var23, par5 + 1.0D, var25, var15, var19);
        var9.addVertexWithUV(var23, par5 + 0.0D, var25, var15, var17);
        
        var9.addVertexWithUV(var23, par5 + 0.0D, var25, var13, var17);
        var9.addVertexWithUV(var23, par5 + 1.0D, var25, var13, var19);
        var9.addVertexWithUV(var21, par5 + 1.0D, var27, var15, var19);
        var9.addVertexWithUV(var21, par5 + 0.0D, var27, var15, var17);
    }
    	
    public void RenderFencePikes(Block par1Block, int par2, double par3, double par5, double par7, RenderBlocks renderer)
    {
        Tessellator var9 = Tessellator.instance;
        int var10 = par1Block.getBlockTextureFromSideAndMetadata(0, par2);

        if (renderer.overrideBlockTexture >= 0){
            var10 = renderer.overrideBlockTexture;
        }

        int var11 = (var10 & 15) << 4;
        int var12 = var10 & 240;
        double var13 = (double)(((float)var11 + 3.99F) / 256.0F);
        double var15 = (double)(((float)var11 + 11.99F) / 256.0F);
        double var17 = (double)(((float)var12 +0.0f) / 256.0F);
        double var19 = (double)(((float)var12 + 15.99F) / 256.0F);

        double var21 = par3 + 0.5D - (0.625-0.375)/2D;
        double var23 = par3 + 0.5D + (0.625-0.375)/2D;
        double var25 = par7 + 0.5D - (0.625-0.375)/2D;
        double var27 = par7 + 0.5D + (0.625-0.375)/2D;
        
//        float var6 = 0.375F;
//        float var7 = 0.625F;
        double maxY = 0.6;
        
        /* Bottom Facing Texture */
        var9.addVertexWithUV(var21, par5 + maxY, var25, var13, var17);
        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var13, var19);
        var9.addVertexWithUV(var23, par5 + 0.0D, var27, var15, var19);
        var9.addVertexWithUV(var23, par5 + maxY, var27, var15, var17);
        
        var9.addVertexWithUV(var23, par5 + maxY, var27, var13, var17);
        var9.addVertexWithUV(var23, par5 + 0.0D, var27, var13, var19);
        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var15, var19);
        var9.addVertexWithUV(var21, par5 + maxY, var25, var15, var17);

        /* Bottom Facing Texture */
        var9.addVertexWithUV(var21, par5 + maxY, var27, var13, var17);
        var9.addVertexWithUV(var21, par5 + 0.0D, var27, var13, var19);
        var9.addVertexWithUV(var23, par5 + 0.0D, var25, var15, var19);
        var9.addVertexWithUV(var23, par5 + maxY, var25, var15, var17);
        
        var9.addVertexWithUV(var23, par5 + maxY, var25, var13, var17);
        var9.addVertexWithUV(var23, par5 + 0.0D, var25, var13, var19);
        var9.addVertexWithUV(var21, par5 + 0.0D, var27, var15, var19);
        var9.addVertexWithUV(var21, par5 + maxY, var27, var15, var17);
    	
    }

    public void drawSpikeCross(Block par1Block, int par2, double par3, double par5, double par7, RenderBlocks renderer){
//    	Boolean alongX = false;
        Tessellator var9 = Tessellator.instance;
        int var10 = par1Block.getBlockTextureFromSideAndMetadata(0, par2);

        if (renderer.overrideBlockTexture >= 0){
            var10 = renderer.overrideBlockTexture;
        }
        
        int var11 = (var10 & 15) << 4;
        int var12 = var10 & 240;
        
        double var13 = (double)((float)var11 / 256.0F);
        double var15 = (double)(((float)var11 + 15.99F) / 256.0F);
        double var17 = (double)((float)var12 / 256.0F);
        double var19 = (double)(((float)var12 + 15.99F) / 256.0F);

        /* Render Strip Along X */
        double var21 = par3 + 0.5D - 0.5;
        double var23 = par3 + 0.5D + 0.5;
        double var25 = par7 + 0.5D - 0.06;
        double var27 = par7 + 0.5D + 0.06;

        //float var6 = 0.375F;
        //float var7 = 0.625F;
        double maxY = 0.6;

        /* Bottom Facing Texture */
        var9.addVertexWithUV(var21, par5 + maxY, var25, var13, var17);
        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var13, var19);
        var9.addVertexWithUV(var23, par5 + 0.0D, var27, var15, var19);
        var9.addVertexWithUV(var23, par5 + maxY, var27, var15, var17);

        var9.addVertexWithUV(var23, par5 + maxY, var27, var15, var17);
        var9.addVertexWithUV(var23, par5 + 0.0D, var27, var15, var19);
        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var13, var19);
        var9.addVertexWithUV(var21, par5 + maxY, var25, var13 , var17);

        /* Bottom Facing Texture */
        var9.addVertexWithUV(var21, par5 + maxY, var27, var13, var17);
        var9.addVertexWithUV(var21, par5 + 0.0D, var27, var13, var19);
        var9.addVertexWithUV(var23, par5 + 0.0D, var25, var15, var19);
        var9.addVertexWithUV(var23, par5 + maxY, var25, var15, var17);

        var9.addVertexWithUV(var23, par5 + maxY, var25, var15, var17);
        var9.addVertexWithUV(var23, par5 + 0.0D, var25, var15, var19);
        var9.addVertexWithUV(var21, par5 + 0.0D, var27, var13, var19);
        var9.addVertexWithUV(var21, par5 + maxY, var27, var13, var17);
        
        /* Render Strip Along Z */
        var21 = par3 + 0.5D - 0.06;
        var23 = par3 + 0.5D + 0.06;
        var25 = par7 + 0.5D - 0.5;
        var27 = par7 + 0.5D + 0.5;

        //float var6 = 0.375F;
        //float var7 = 0.625F;
        maxY = 0.6;

        /* Bottom Facing Texture */
        var9.addVertexWithUV(var21, par5 + maxY, var25, var13, var17);
        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var13, var19);
        var9.addVertexWithUV(var23, par5 + 0.0D, var27, var15, var19);
        var9.addVertexWithUV(var23, par5 + maxY, var27, var15, var17);

        var9.addVertexWithUV(var23, par5 + maxY, var27, var15, var17);
        var9.addVertexWithUV(var23, par5 + 0.0D, var27, var15, var19);
        var9.addVertexWithUV(var21, par5 + 0.0D, var25, var13, var19);
        var9.addVertexWithUV(var21, par5 + maxY, var25, var13 , var17);

        /* Bottom Facing Texture */
        var9.addVertexWithUV(var21, par5 + maxY, var27, var13, var17);
        var9.addVertexWithUV(var21, par5 + 0.0D, var27, var13, var19);
        var9.addVertexWithUV(var23, par5 + 0.0D, var25, var15, var19);
        var9.addVertexWithUV(var23, par5 + maxY, var25, var15, var17);

        var9.addVertexWithUV(var23, par5 + maxY, var25, var15, var17);
        var9.addVertexWithUV(var23, par5 + 0.0D, var25, var15, var19);
        var9.addVertexWithUV(var21, par5 + 0.0D, var27, var13, var19);
        var9.addVertexWithUV(var21, par5 + maxY, var27, var13, var17);
        
    }

    
    public void drawSpikeStrip(Block par1Block, int par2, double par3, double par5, double par7, RenderBlocks renderer, Boolean alongX, int type)
    {    	
    	
//    	Boolean alongX = false;
        Tessellator var9 = Tessellator.instance;
        int var10 = par1Block.getBlockTextureFromSideAndMetadata(0, par2);

        if (renderer.overrideBlockTexture >= 0){
            var10 = renderer.overrideBlockTexture;
        }
        
        int var11 = (var10 & 15) << 4;
        int var12 = var10 & 240;
        
        
        double var13;
        double var15;
        double var17;
        double var19;
        
    	switch (type) {
    	case 1:
	        var13 = (double)(((float)var11 + 0.0f + 15.99f/2.0) / 256.0F);
	        var15 = (double)(((float)var11 + 15.99F ) / 256.0F);
	        var17 = (double)(((float)var12 + 0.0f) / 256.0F);
	        var19 = (double)(((float)var12 + 15.99F) / 256.0F);
			break;
			
		case 2:
			var13 = (double)(((float)var11 +0.0f ) / 256.0F);
	        var15 = (double)(((float)var11 + 15.99F - 15.99f/2.) / 256.0F);
	        var17 = (double)(((float)var12 + 0.0f) / 256.0F);
	        var19 = (double)(((float)var12 + 15.99F) / 256.0F);
			break;
			
		default:
	        var13 = (double)((float)var11 / 256.0F);
	        var15 = (double)(((float)var11 + 15.99F) / 256.0F);
	        var17 = (double)((float)var12 / 256.0F);
	        var19 = (double)(((float)var12 + 15.99F) / 256.0F);
		}


        
        double var21;
        double var23;
        double var25;
        double var27;
        if(alongX){
        	switch (type) {
			case 1:
	            var21 = par3 + 0.5D - 0.0;
	            var23 = par3 + 0.5D + 0.5;
	            var25 = par7 + 0.5D - 0.06;
	            var27 = par7 + 0.5D + 0.06;

				break;
			case 2:
	            var21 = par3 + 0.5D - 0.5;
	            var23 = par3 + 0.5D + 0.0;
	            var25 = par7 + 0.5D - 0.06;
	            var27 = par7 + 0.5D + 0.06;
				break;
			default:
	            var21 = par3 + 0.5D - 0.5;
	            var23 = par3 + 0.5D + 0.5;
	            var25 = par7 + 0.5D - 0.06;
	            var27 = par7 + 0.5D + 0.06;
				break;
			}

        }else{
        	switch (type) {
			case 1:
	            var21 = par3 + 0.5D - 0.06;
	            var23 = par3 + 0.5D + 0.06;
	            var25 = par7 + 0.5D - 0.0;
	            var27 = par7 + 0.5D + 0.5;

				break;
			case 2:
	            var21 = par3 + 0.5D - 0.06;
	            var23 = par3 + 0.5D + 0.06;
	            var25 = par7 + 0.5D - 0.5;
	            var27 = par7 + 0.5D + 0.0;

				break;
			default:
	            var21 = par3 + 0.5D - 0.06;
	            var23 = par3 + 0.5D + 0.06;
	            var25 = par7 + 0.5D - 0.5;
	            var27 = par7 + 0.5D + 0.5;
				break;
			}
        }
        
//        float var6 = 0.375F;
//        float var7 = 0.625F;
        double maxY = 0.6;
        
        if(alongX){
            /* Bottom Facing Texture */
            var9.addVertexWithUV(var21, par5 + maxY, var25, var13, var17);
            var9.addVertexWithUV(var21, par5 + 0.0D, var25, var13, var19);
            var9.addVertexWithUV(var23, par5 + 0.0D, var27, var15, var19);
            var9.addVertexWithUV(var23, par5 + maxY, var27, var15, var17);
            
            var9.addVertexWithUV(var23, par5 + maxY, var27, var15, var17);
            var9.addVertexWithUV(var23, par5 + 0.0D, var27, var15, var19);
            var9.addVertexWithUV(var21, par5 + 0.0D, var25, var13, var19);
            var9.addVertexWithUV(var21, par5 + maxY, var25, var13 , var17);
            
            /* Bottom Facing Texture */
            var9.addVertexWithUV(var21, par5 + maxY, var27, var13, var17);
            var9.addVertexWithUV(var21, par5 + 0.0D, var27, var13, var19);
            var9.addVertexWithUV(var23, par5 + 0.0D, var25, var15, var19);
            var9.addVertexWithUV(var23, par5 + maxY, var25, var15, var17);
            
            var9.addVertexWithUV(var23, par5 + maxY, var25, var15, var17);
            var9.addVertexWithUV(var23, par5 + 0.0D, var25, var15, var19);
            var9.addVertexWithUV(var21, par5 + 0.0D, var27, var13, var19);
            var9.addVertexWithUV(var21, par5 + maxY, var27, var13, var17);

        }else{
            /* Bottom Facing Texture */
            var9.addVertexWithUV(var21, par5 + maxY, var25, var13, var17);
            var9.addVertexWithUV(var21, par5 + 0.0D, var25, var13, var19);
            var9.addVertexWithUV(var23, par5 + 0.0D, var27, var15, var19);
            var9.addVertexWithUV(var23, par5 + maxY, var27, var15, var17);
            
            var9.addVertexWithUV(var23, par5 + maxY, var27, var15, var17);
            var9.addVertexWithUV(var23, par5 + 0.0D, var27, var15, var19);
            var9.addVertexWithUV(var21, par5 + 0.0D, var25, var13, var19);
            var9.addVertexWithUV(var21, par5 + maxY, var25, var13, var17);
            
            /* Bottom Facing Texture */
            var9.addVertexWithUV(var21, par5 + maxY, var27, var15, var17);
            var9.addVertexWithUV(var21, par5 + 0.0D, var27, var15, var19);
            var9.addVertexWithUV(var23, par5 + 0.0D, var25, var13, var19);
            var9.addVertexWithUV(var23, par5 + maxY, var25, var13, var17);
            
            var9.addVertexWithUV(var23, par5 + maxY, var25, var13, var17);
            var9.addVertexWithUV(var23, par5 + 0.0D, var25, var13, var19);
            var9.addVertexWithUV(var21, par5 + 0.0D, var27, var15, var19);
            var9.addVertexWithUV(var21, par5 + maxY, var27, var15, var17);

        }
    }
    
    
    @Override
	public boolean shouldRender3DInInventory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRenderId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
