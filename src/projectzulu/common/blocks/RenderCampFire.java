package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderCampFire implements ISimpleBlockRenderingHandler{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return render(world, x, y, z, block, modelId, renderer);
	}
	
	/**
	 * Render Campfire Stones
	 */
	public boolean render(IBlockAccess blockAccess, int par2, int par3, int par4,
			Block par1Block, int modelId, RenderBlocks renderer){

		Tessellator var5 = Tessellator.instance;
		var5.setBrightness(par1Block.getMixedBrightnessForBlock(blockAccess, par2, par3, par4));
		float var6 = 1.0F;
		int var7 = par1Block.colorMultiplier(blockAccess, par2, par3, par4);
		float var8 = (float)(var7 >> 16 & 255) / 255.0F;
		float var9 = (float)(var7 >> 8 & 255) / 255.0F;
		float var10 = (float)(var7 & 255) / 255.0F;

		if (EntityRenderer.anaglyphEnable){
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
		
		switch (BlockCampfire.Type.getTypeByMeta(blockAccess.getBlockMetadata(par2, par3, par4))) {
		case WoodFire:
		case StoneFire:
			/* Render If Should and Render Campfire Fire*/
			renderBlockFire(blockAccess, par2, par3, par4, par1Block, modelId, renderer);
			break;
		case Stone:
		case Wood:
			break;
		}

		
		switch (BlockCampfire.Type.getTypeByMeta(blockAccess.getBlockMetadata(par2, par3, par4))) {
		case Wood:
		case WoodFire:
			/* Render Campfire Logs*/
			renderCampireRectangle2Sides(par1Block, 20, 21, blockAccess.getBlockMetadata(par2, par3, par4), var19     , var20, var15     , renderer, 0.35, 0.2, 0.2, 45*Math.PI/180);
			renderCampireRectangle2Sides(par1Block, 20, 21, blockAccess.getBlockMetadata(par2, par3, par4), var19+0.24, var20, var15+0.24, renderer, 0.33, 0.2, 0.2, 45*Math.PI/180);
			renderCampireRectangle2Sides(par1Block, 20, 21, blockAccess.getBlockMetadata(par2, par3, par4), var19-0.24, var20, var15-0.24, renderer, 0.33, 0.2, 0.2, 45*Math.PI/180);
			
			renderCampireRectangle2Sides(par1Block, 20, 21, blockAccess.getBlockMetadata(par2, par3, par4), var19-0.21, var20, var15+0.21, renderer, 0.4, 0.2, 0.2, (90+45)*Math.PI/180);
			renderCampireRectangle2Sides(par1Block, 20, 21, blockAccess.getBlockMetadata(par2, par3, par4), var19+0.21, var20, var15-0.21, renderer, 0.4, 0.2, 0.2, (90+45)*Math.PI/180);
			break;
		case Stone:
		case StoneFire:
			/* Render Campfire Logs*/
			renderCampireRectangle2Sides(par1Block, 20, 21, blockAccess.getBlockMetadata(par2, par3, par4), var19     , var20, var15     , renderer, 0.2, 0.2, 0.2, 45*Math.PI/180);
			renderCampireRectangle2Sides(par1Block, 20, 21, blockAccess.getBlockMetadata(par2, par3, par4), var19+0.14, var20, var15+0.14, renderer, 0.2, 0.2, 0.2, 45*Math.PI/180);
			renderCampireRectangle2Sides(par1Block, 20, 21, blockAccess.getBlockMetadata(par2, par3, par4), var19-0.14, var20, var15-0.14, renderer, 0.2, 0.2, 0.2, 45*Math.PI/180);
			renderCampireRectangle2Sides(par1Block, 20, 21, blockAccess.getBlockMetadata(par2, par3, par4), var19-0.14, var20, var15+0.14, renderer, 0.2, 0.2, 0.2, (90+45)*Math.PI/180);
			renderCampireRectangle2Sides(par1Block, 20, 21, blockAccess.getBlockMetadata(par2, par3, par4), var19+0.14, var20, var15-0.14, renderer, 0.2, 0.2, 0.2, (90+45)*Math.PI/180);
			
			/* Render Campfire Logs*/
			renderCampireRectangle2Sides(par1Block, 20, 21, blockAccess.getBlockMetadata(par2, par3, par4), var19     , var20, var15     , renderer, 0.2, 0.2, 0.2, 45*Math.PI/180);
			renderCampireRectangle2Sides(par1Block, 20, 21, blockAccess.getBlockMetadata(par2, par3, par4), var19+0.14, var20, var15+0.14, renderer, 0.2, 0.2, 0.2, 45*Math.PI/180);
			renderCampireRectangle2Sides(par1Block, 20, 21, blockAccess.getBlockMetadata(par2, par3, par4), var19-0.14, var20, var15-0.14, renderer, 0.2, 0.2, 0.2, 45*Math.PI/180);
			renderCampireRectangle2Sides(par1Block, 20, 21, blockAccess.getBlockMetadata(par2, par3, par4), var19-0.14, var20, var15+0.14, renderer, 0.2, 0.2, 0.2, (90+45)*Math.PI/180);
			renderCampireRectangle2Sides(par1Block, 20, 21, blockAccess.getBlockMetadata(par2, par3, par4), var19+0.14, var20, var15-0.14, renderer, 0.2, 0.2, 0.2, (90+45)*Math.PI/180);
		
			/* Render Campfire Stones */
			renderCampireRectangle(par1Block, 16, blockAccess.getBlockMetadata(par2, par3, par4), var19+0.4, var20, var15+0.0, renderer, 0.2, 0.3, 0.3, 0.0);
			renderCampireRectangle(par1Block, 16, blockAccess.getBlockMetadata(par2, par3, par4), var19-0.4, var20, var15+0.0, renderer, 0.2, 0.3, 0.3, 0.0);
			renderCampireRectangle(par1Block, 16, blockAccess.getBlockMetadata(par2, par3, par4), var19+0.0, var20, var15+0.4, renderer, 0.3, 0.2, 0.3, 0.0);
			renderCampireRectangle(par1Block, 16, blockAccess.getBlockMetadata(par2, par3, par4), var19+0.0, var20, var15-0.4, renderer, 0.3, 0.2, 0.3, 0.0);
			
			renderCampireRectangle(par1Block, 16, blockAccess.getBlockMetadata(par2, par3, par4), var19+0.33, var20, var15+0.33, renderer, 0.3, 0.2, 0.3, 135*Math.PI/180);
			renderCampireRectangle(par1Block, 16, blockAccess.getBlockMetadata(par2, par3, par4), var19-0.33, var20, var15-0.33, renderer, 0.3, 0.2, 0.3, 135*Math.PI/180);
			renderCampireRectangle(par1Block, 16, blockAccess.getBlockMetadata(par2, par3, par4), var19-0.33, var20, var15+0.33, renderer, 0.3, 0.2, 0.3, 45*Math.PI/180);
			renderCampireRectangle(par1Block, 16, blockAccess.getBlockMetadata(par2, par3, par4), var19+0.33, var20, var15-0.33, renderer, 0.3, 0.2, 0.3, 45*Math.PI/180);
			break;
		}
		return true;
	}
	/**
	 * Function that Renders a Rectangle at provided position with provided width, height and angle (about the Y axis)
	 * @param par1Block
	 * @param var10
	 * @param par2
	 * @param par3
	 * @param par5
	 * @param par7
	 * @param renderer
	 * @param xWidth
	 * @param zWidth
	 * @param yMax
	 * @param angle
	 * @return
	 */
	private boolean renderCampireRectangle2Sides(Block par1Block, int index1, int index2, int par2, double par3, double par5, double par7, RenderBlocks renderer, double xWidth, double zWidth, double yMax, double angle){
		Tessellator var9 = Tessellator.instance;		
    	Icon var10 = renderer.func_94165_a(Block.wood, 2, 0);
    	double var13 = var10.func_94209_e();
    	double var15 = var10.func_94209_e() + (var10.func_94212_f() - var10.func_94209_e())/2.01f;
    	double var17 = var10.func_94206_g();
    	double var19 = var10.func_94206_g() + (var10.func_94210_h() - var10.func_94206_g())/2.01f;
    	
		double yMin = 0.0f;
				
		double intermedVarX = -xWidth/2;
		double intermedVarZ = +zWidth/2;
		double point1X = par3 + 0.5D + intermedVarX*Math.cos(angle) - intermedVarZ*Math.sin(angle);
		double point1Z = par7 + 0.5D + intermedVarX*Math.sin(angle) + intermedVarZ*Math.cos(angle);
		
		intermedVarX = -xWidth/2;
		intermedVarZ = -zWidth/2;		
		double point2X = par3 + 0.5D + intermedVarX*Math.cos(angle) - intermedVarZ*Math.sin(angle);
		double point2Z = par7 + 0.5D + intermedVarX*Math.sin(angle) + intermedVarZ*Math.cos(angle);
		
		intermedVarX = +xWidth/2;
		intermedVarZ = -zWidth/2;
		double point3X = par3 + 0.5D + intermedVarX*Math.cos(angle) - intermedVarZ*Math.sin(angle);
		double point3Z = par7 + 0.5D + intermedVarX*Math.sin(angle) + intermedVarZ*Math.cos(angle);
		
		intermedVarX = +xWidth/2;
		intermedVarZ = +zWidth/2;
		double point4X = par3 + 0.5D + intermedVarX*Math.cos(angle) - intermedVarZ*Math.sin(angle);
		double point4Z = par7 + 0.5D + intermedVarX*Math.sin(angle) + intermedVarZ*Math.cos(angle);

		
		/* Side 2 */
		var9.addVertexWithUV(point1X, par5 + yMin, point1Z, var13, var17);
		var9.addVertexWithUV(point1X, par5 + yMax, point1Z, var13, var19);
		var9.addVertexWithUV(point4X, par5 + yMax, point4Z, var15, var19);
		var9.addVertexWithUV(point4X, par5 + yMin, point4Z, var15, var17);
		
		var9.addVertexWithUV(point4X, par5 + yMin, point4Z, var15, var17);
		var9.addVertexWithUV(point4X, par5 + yMax, point4Z, var15, var19);
		var9.addVertexWithUV(point1X, par5 + yMax, point1Z, var13, var19);
		var9.addVertexWithUV(point1X, par5 + yMin, point1Z, var13, var17);
		
		/* Side 4 */
		var9.addVertexWithUV(point3X, par5 + yMin, point3Z, var13, var17);
		var9.addVertexWithUV(point3X, par5 + yMax, point3Z, var13, var19);
		var9.addVertexWithUV(point2X, par5 + yMax, point2Z, var15, var19);
		var9.addVertexWithUV(point2X, par5 + yMin, point2Z, var15, var17);
		
		var9.addVertexWithUV(point2X, par5 + yMin, point2Z, var15, var17);
		var9.addVertexWithUV(point2X, par5 + yMax, point2Z, var15, var19);
		var9.addVertexWithUV(point3X, par5 + yMax, point3Z, var13, var19);
		var9.addVertexWithUV(point3X, par5 + yMin, point3Z, var13, var17);
		
		/* Side 5 */
		var9.addVertexWithUV(point1X, par5 + yMax, point1Z, var13, var17);
		var9.addVertexWithUV(point2X, par5 + yMax, point2Z, var13, var19);
		var9.addVertexWithUV(point3X, par5 + yMax, point3Z, var15, var19);
		var9.addVertexWithUV(point4X, par5 + yMax, point4Z, var15, var17);
		
		var9.addVertexWithUV(point4X, par5 + yMax, point4Z, var15, var17);
		var9.addVertexWithUV(point3X, par5 + yMax, point3Z, var15, var19);
		var9.addVertexWithUV(point2X, par5 + yMax, point2Z, var13, var19);
		var9.addVertexWithUV(point1X, par5 + yMax, point1Z, var13, var17);
		
		/* Side 6 */
		var9.addVertexWithUV(point1X, par5 + yMin, point1Z, var13, var17);
		var9.addVertexWithUV(point2X, par5 + yMin, point2Z, var13, var19);
		var9.addVertexWithUV(point3X, par5 + yMin, point3Z, var15, var19);
		var9.addVertexWithUV(point4X, par5 + yMin, point4Z, var15, var17);
		
		var9.addVertexWithUV(point4X, par5 + yMin, point4Z, var15, var17);
		var9.addVertexWithUV(point3X, par5 + yMin, point3Z, var15, var19);
		var9.addVertexWithUV(point2X, par5 + yMin, point2Z, var13, var19);
		var9.addVertexWithUV(point1X, par5 + yMin, point1Z, var13, var17);
		
    	var10 = renderer.func_94165_a(Block.wood, 1, 0);
    	var13 = var10.func_94209_e() + (var10.func_94212_f() - var10.func_94209_e())*0.99f/4f;
    	var15 = var10.func_94209_e() + (var10.func_94212_f() - var10.func_94209_e())*2.99f/4f;
    	var17 = var10.func_94206_g() + (var10.func_94210_h() - var10.func_94206_g())*0.99f/4f;
    	var19 = var10.func_94206_g() + (var10.func_94210_h() - var10.func_94206_g())*2.99f/4f;
		
		/* Side 1 */
		var9.addVertexWithUV(point1X, par5 + yMin, point1Z, var13, var17);
		var9.addVertexWithUV(point1X, par5 + yMax, point1Z, var13, var19);
		var9.addVertexWithUV(point2X, par5 + yMax, point2Z, var15, var19);
		var9.addVertexWithUV(point2X, par5 + yMin, point2Z, var15, var17);
		
		var9.addVertexWithUV(point2X, par5 + yMin, point2Z, var15, var17);
		var9.addVertexWithUV(point2X, par5 + yMax, point2Z, var15, var19);
		var9.addVertexWithUV(point1X, par5 + yMax, point1Z, var13, var19);
		var9.addVertexWithUV(point1X, par5 + yMin, point1Z, var13, var17);
		
		/* Side 3 */
		var9.addVertexWithUV(point4X, par5 + yMin, point4Z, var13, var17);
		var9.addVertexWithUV(point4X, par5 + yMax, point4Z, var13, var19);
		var9.addVertexWithUV(point3X, par5 + yMax, point3Z, var15, var19);
		var9.addVertexWithUV(point3X, par5 + yMin, point3Z, var15, var17);
		
		var9.addVertexWithUV(point3X, par5 + yMin, point3Z, var15, var17);
		var9.addVertexWithUV(point3X, par5 + yMax, point3Z, var15, var19);
		var9.addVertexWithUV(point4X, par5 + yMax, point4Z, var13, var19);
		var9.addVertexWithUV(point4X, par5 + yMin, point4Z, var13, var17);
		return true;
	}

	
	/**
	 * Function that Renders a Rectangle at provided position with provided width, height and angle (about the Y axis)
	 * @param par1Block
	 * @param var10
	 * @param par2
	 * @param par3
	 * @param par5
	 * @param par7
	 * @param renderer
	 * @param xWidth
	 * @param zWidth
	 * @param yMax
	 * @param angle
	 * @return
	 */
	private boolean renderCampireRectangle(Block par1Block, int var10, int par2, double par3, double par5, double par7, RenderBlocks renderer, double xWidth, double zWidth, double yMax, double angle){
		Tessellator var9 = Tessellator.instance;		
    	Icon icon = renderer.func_94165_a(Block.cobblestone,3, 2);
    	double var13 = icon.func_94209_e();
    	double var15 = icon.func_94209_e() + (icon.func_94212_f() - icon.func_94209_e())/2.01f;
    	double var17 = icon.func_94206_g();
    	double var19 = icon.func_94206_g() + (icon.func_94210_h() - icon.func_94206_g())/2.01f;
		
		double yMin = 0.0f;
				
		double intermedVarX = -xWidth/2;
		double intermedVarZ = +zWidth/2;
		double point1X = par3 + 0.5D + intermedVarX*Math.cos(angle) - intermedVarZ*Math.sin(angle);
		double point1Z = par7 + 0.5D + intermedVarX*Math.sin(angle) + intermedVarZ*Math.cos(angle);
		
		intermedVarX = -xWidth/2;
		intermedVarZ = -zWidth/2;		
		double point2X = par3 + 0.5D + intermedVarX*Math.cos(angle) - intermedVarZ*Math.sin(angle);
		double point2Z = par7 + 0.5D + intermedVarX*Math.sin(angle) + intermedVarZ*Math.cos(angle);
		
		intermedVarX = +xWidth/2;
		intermedVarZ = -zWidth/2;
		double point3X = par3 + 0.5D + intermedVarX*Math.cos(angle) - intermedVarZ*Math.sin(angle);
		double point3Z = par7 + 0.5D + intermedVarX*Math.sin(angle) + intermedVarZ*Math.cos(angle);
		
		intermedVarX = +xWidth/2;
		intermedVarZ = +zWidth/2;
		double point4X = par3 + 0.5D + intermedVarX*Math.cos(angle) - intermedVarZ*Math.sin(angle);
		double point4Z = par7 + 0.5D + intermedVarX*Math.sin(angle) + intermedVarZ*Math.cos(angle);

		/* Side 1 */
		var9.addVertexWithUV(point1X, par5 + yMin, point1Z, var13, var17);
		var9.addVertexWithUV(point1X, par5 + yMax, point1Z, var13, var19);
		var9.addVertexWithUV(point2X, par5 + yMax, point2Z, var15, var19);
		var9.addVertexWithUV(point2X, par5 + yMin, point2Z, var15, var17);
		
		var9.addVertexWithUV(point2X, par5 + yMin, point2Z, var15, var17);
		var9.addVertexWithUV(point2X, par5 + yMax, point2Z, var15, var19);
		var9.addVertexWithUV(point1X, par5 + yMax, point1Z, var13, var19);
		var9.addVertexWithUV(point1X, par5 + yMin, point1Z, var13, var17);
		
		/* Side 3 */
		var9.addVertexWithUV(point4X, par5 + yMin, point4Z, var13, var17);
		var9.addVertexWithUV(point4X, par5 + yMax, point4Z, var13, var19);
		var9.addVertexWithUV(point3X, par5 + yMax, point3Z, var15, var19);
		var9.addVertexWithUV(point3X, par5 + yMin, point3Z, var15, var17);
		
		var9.addVertexWithUV(point3X, par5 + yMin, point3Z, var15, var17);
		var9.addVertexWithUV(point3X, par5 + yMax, point3Z, var15, var19);
		var9.addVertexWithUV(point4X, par5 + yMax, point4Z, var13, var19);
		var9.addVertexWithUV(point4X, par5 + yMin, point4Z, var13, var17);
		
		/* Side 2 */
		var9.addVertexWithUV(point1X, par5 + yMin, point1Z, var13, var17);
		var9.addVertexWithUV(point1X, par5 + yMax, point1Z, var13, var19);
		var9.addVertexWithUV(point4X, par5 + yMax, point4Z, var15, var19);
		var9.addVertexWithUV(point4X, par5 + yMin, point4Z, var15, var17);
		
		var9.addVertexWithUV(point4X, par5 + yMin, point4Z, var15, var17);
		var9.addVertexWithUV(point4X, par5 + yMax, point4Z, var15, var19);
		var9.addVertexWithUV(point1X, par5 + yMax, point1Z, var13, var19);
		var9.addVertexWithUV(point1X, par5 + yMin, point1Z, var13, var17);
		
		/* Side 4 */
		var9.addVertexWithUV(point3X, par5 + yMin, point3Z, var13, var17);
		var9.addVertexWithUV(point3X, par5 + yMax, point3Z, var13, var19);
		var9.addVertexWithUV(point2X, par5 + yMax, point2Z, var15, var19);
		var9.addVertexWithUV(point2X, par5 + yMin, point2Z, var15, var17);
		
		var9.addVertexWithUV(point2X, par5 + yMin, point2Z, var15, var17);
		var9.addVertexWithUV(point2X, par5 + yMax, point2Z, var15, var19);
		var9.addVertexWithUV(point3X, par5 + yMax, point3Z, var13, var19);
		var9.addVertexWithUV(point3X, par5 + yMin, point3Z, var13, var17);
		
		/* Side 5 */
		var9.addVertexWithUV(point1X, par5 + yMax, point1Z, var13, var17);
		var9.addVertexWithUV(point2X, par5 + yMax, point2Z, var13, var19);
		var9.addVertexWithUV(point3X, par5 + yMax, point3Z, var15, var19);
		var9.addVertexWithUV(point4X, par5 + yMax, point4Z, var15, var17);
		
		var9.addVertexWithUV(point4X, par5 + yMax, point4Z, var15, var17);
		var9.addVertexWithUV(point3X, par5 + yMax, point3Z, var15, var19);
		var9.addVertexWithUV(point2X, par5 + yMax, point2Z, var13, var19);
		var9.addVertexWithUV(point1X, par5 + yMax, point1Z, var13, var17);
		
		/* Side 6 */
		var9.addVertexWithUV(point1X, par5 + yMin, point1Z, var13, var17);
		var9.addVertexWithUV(point2X, par5 + yMin, point2Z, var13, var19);
		var9.addVertexWithUV(point3X, par5 + yMin, point3Z, var15, var19);
		var9.addVertexWithUV(point4X, par5 + yMin, point4Z, var15, var17);
		
		var9.addVertexWithUV(point4X, par5 + yMin, point4Z, var15, var17);
		var9.addVertexWithUV(point3X, par5 + yMin, point3Z, var15, var19);
		var9.addVertexWithUV(point2X, par5 + yMin, point2Z, var13, var19);
		var9.addVertexWithUV(point1X, par5 + yMin, point1Z, var13, var17);
		
		return true;
	}

	/**
	 * Renders a fire block at the given coordinates
	 */
	public boolean renderBlockFire(IBlockAccess blockAccess, int par2, int par3, int par4, Block par1Block, int modelId, RenderBlocks renderer){
		Tessellator var5 = Tessellator.instance;
		var5.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		var5.setBrightness(par1Block.getMixedBrightnessForBlock(blockAccess, par2, par3, par4));
		
    	Icon icon = renderer.func_94165_a(Block.fire, 0, 0);
    	double var9 = icon.func_94209_e();
    	double var11 = icon.func_94212_f();
    	double var13 = icon.func_94206_g();
    	double var15 = icon.func_94210_h();
		
		float var17 = 1.4F;
		double var20;
		double var22;
		double var24;
		double var26;
		double var28;
		double var30;
		double var32;

		if (!blockAccess.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) && !Block.fire.canBlockCatchFire(blockAccess, par2, par3 - 1, par4, ForgeDirection.UP)){
			float var36 = 0.2F;
			float var19 = 0.0625F;

			if ((par2 + par3 + par4 & 1) == 1){
		    	var9 = icon.func_94209_e();
		    	var11 = icon.func_94212_f();
		    	var13 = icon.func_94206_g();
		    	var15 = icon.func_94210_h();

			}

			if ((par2 / 2 + par3 / 2 + par4 / 2 & 1) == 1){
				var20 = var11;
				var11 = var9;
				var9 = var20;
			}

			if (Block.fire.canBlockCatchFire(blockAccess, par2 - 1, par3, par4, ForgeDirection.EAST)){
				var5.addVertexWithUV((double)((float)par2 + var36), (double)((float)par3 + var17 + var19), (double)(par4 + 1), var11, var13);
				var5.addVertexWithUV((double)(par2 + 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 1), var11, var15);
				var5.addVertexWithUV((double)(par2 + 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 0), var9, var15);
				var5.addVertexWithUV((double)((float)par2 + var36), (double)((float)par3 + var17 + var19), (double)(par4 + 0), var9, var13);
				var5.addVertexWithUV((double)((float)par2 + var36), (double)((float)par3 + var17 + var19), (double)(par4 + 0), var9, var13);
				var5.addVertexWithUV((double)(par2 + 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 0), var9, var15);
				var5.addVertexWithUV((double)(par2 + 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 1), var11, var15);
				var5.addVertexWithUV((double)((float)par2 + var36), (double)((float)par3 + var17 + var19), (double)(par4 + 1), var11, var13);
			}

			if (Block.fire.canBlockCatchFire(blockAccess, par2 + 1, par3, par4, ForgeDirection.WEST)){
				var5.addVertexWithUV((double)((float)(par2 + 1) - var36), (double)((float)par3 + var17 + var19), (double)(par4 + 0), var9, var13);
				var5.addVertexWithUV((double)(par2 + 1 - 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 0), var9, var15);
				var5.addVertexWithUV((double)(par2 + 1 - 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 1), var11, var15);
				var5.addVertexWithUV((double)((float)(par2 + 1) - var36), (double)((float)par3 + var17 + var19), (double)(par4 + 1), var11, var13);
				var5.addVertexWithUV((double)((float)(par2 + 1) - var36), (double)((float)par3 + var17 + var19), (double)(par4 + 1), var11, var13);
				var5.addVertexWithUV((double)(par2 + 1 - 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 1), var11, var15);
				var5.addVertexWithUV((double)(par2 + 1 - 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 0), var9, var15);
				var5.addVertexWithUV((double)((float)(par2 + 1) - var36), (double)((float)par3 + var17 + var19), (double)(par4 + 0), var9, var13);
			}

			if (Block.fire.canBlockCatchFire(blockAccess, par2, par3, par4 - 1, ForgeDirection.SOUTH)){
				var5.addVertexWithUV((double)(par2 + 0), (double)((float)par3 + var17 + var19), (double)((float)par4 + var36), var11, var13);
				var5.addVertexWithUV((double)(par2 + 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 0), var11, var15);
				var5.addVertexWithUV((double)(par2 + 1), (double)((float)(par3 + 0) + var19), (double)(par4 + 0), var9, var15);
				var5.addVertexWithUV((double)(par2 + 1), (double)((float)par3 + var17 + var19), (double)((float)par4 + var36), var9, var13);
				var5.addVertexWithUV((double)(par2 + 1), (double)((float)par3 + var17 + var19), (double)((float)par4 + var36), var9, var13);
				var5.addVertexWithUV((double)(par2 + 1), (double)((float)(par3 + 0) + var19), (double)(par4 + 0), var9, var15);
				var5.addVertexWithUV((double)(par2 + 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 0), var11, var15);
				var5.addVertexWithUV((double)(par2 + 0), (double)((float)par3 + var17 + var19), (double)((float)par4 + var36), var11, var13);
			}

			if (Block.fire.canBlockCatchFire(blockAccess, par2, par3, par4 + 1, ForgeDirection.NORTH)){
				var5.addVertexWithUV((double)(par2 + 1), (double)((float)par3 + var17 + var19), (double)((float)(par4 + 1) - var36), var9, var13);
				var5.addVertexWithUV((double)(par2 + 1), (double)((float)(par3 + 0) + var19), (double)(par4 + 1 - 0), var9, var15);
				var5.addVertexWithUV((double)(par2 + 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 1 - 0), var11, var15);
				var5.addVertexWithUV((double)(par2 + 0), (double)((float)par3 + var17 + var19), (double)((float)(par4 + 1) - var36), var11, var13);
				var5.addVertexWithUV((double)(par2 + 0), (double)((float)par3 + var17 + var19), (double)((float)(par4 + 1) - var36), var11, var13);
				var5.addVertexWithUV((double)(par2 + 0), (double)((float)(par3 + 0) + var19), (double)(par4 + 1 - 0), var11, var15);
				var5.addVertexWithUV((double)(par2 + 1), (double)((float)(par3 + 0) + var19), (double)(par4 + 1 - 0), var9, var15);
				var5.addVertexWithUV((double)(par2 + 1), (double)((float)par3 + var17 + var19), (double)((float)(par4 + 1) - var36), var9, var13);
			}

			if (Block.fire.canBlockCatchFire(blockAccess, par2, par3 + 1, par4, ForgeDirection.DOWN)){
				var20 = (double)par2 + 0.5D + 0.5D; // 1
				var22 = (double)par2 + 0.5D - 0.5D; // 0
				var24 = (double)par4 + 0.5D + 0.5D; 
				var26 = (double)par4 + 0.5D - 0.5D;

				var28 = (double)par2 + 0.5D - 0.5D;
				var30 = (double)par2 + 0.5D + 0.5D;
				var32 = (double)par4 + 0.5D - 0.5D;
				double var34 = (double)par4 + 0.5D + 0.5D;

		    	var9 = icon.func_94209_e();
		    	var11 = icon.func_94212_f();
		    	var13 = icon.func_94206_g();
		    	var15 = icon.func_94210_h();

				++par3;
				var17 = -0.2F;

				if ((par2 + par3 + par4 & 1) == 0){
					var5.addVertexWithUV(var28, (double)((float)par3 + var17), (double)(par4 + 0), var11, var13);
					var5.addVertexWithUV(var20, (double)(par3 + 0), (double)(par4 + 0), var11, var15);
					var5.addVertexWithUV(var20, (double)(par3 + 0), (double)(par4 + 1), var9, var15);
					var5.addVertexWithUV(var28, (double)((float)par3 + var17), (double)(par4 + 1), var9, var13);

					var9 = icon.func_94209_e();
			    	var11 = icon.func_94212_f();
			    	var13 = icon.func_94206_g();
			    	var15 = icon.func_94210_h();

					var5.addVertexWithUV(var30, (double)((float)par3 + var17), (double)(par4 + 1), var11, var13);
					var5.addVertexWithUV(var22, (double)(par3 + 0), (double)(par4 + 1), var11, var15);
					var5.addVertexWithUV(var22, (double)(par3 + 0), (double)(par4 + 0), var9, var15);
					var5.addVertexWithUV(var30, (double)((float)par3 + var17), (double)(par4 + 0), var9, var13);
				}
				else{
					var5.addVertexWithUV((double)(par2 + 0), (double)((float)par3 + var17), var34, var11, var13);
					var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 0), var26, var11, var15);
					var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 0), var26, var9, var15);
					var5.addVertexWithUV((double)(par2 + 1), (double)((float)par3 + var17), var34, var9, var13);

					var9 = icon.func_94209_e();
			    	var11 = icon.func_94212_f();
			    	var13 = icon.func_94206_g();
			    	var15 = icon.func_94210_h();

					var5.addVertexWithUV((double)(par2 + 1), (double)((float)par3 + var17), var32, var11, var13);
					var5.addVertexWithUV((double)(par2 + 1), (double)(par3 + 0), var24, var11, var15);
					var5.addVertexWithUV((double)(par2 + 0), (double)(par3 + 0), var24, var9, var15);
					var5.addVertexWithUV((double)(par2 + 0), (double)((float)par3 + var17), var32, var9, var13);
				}
			}
		}else{
			double var18 = (double)par2 + 0.5D + 0.2D;
			var20 = (double)par2 + 0.5D - 0.2D; // 
			var22 = (double)par4 + 0.5D + 0.2D;
			var24 = (double)par4 + 0.5D - 0.2D;
			var26 = (double)par2 + 0.5D - 0.3D;
			var28 = (double)par2 + 0.5D + 0.3D;
			var30 = (double)par4 + 0.5D - 0.3D;
			var32 = (double)par4 + 0.5D + 0.3D;
			var5.addVertexWithUV(var26, (double)((float)par3 + var17), (double)(par4 + 0.8), var11, var13);
			var5.addVertexWithUV(var18, (double)(par3 + 0),            (double)(par4 + 0.8), var11, var15);
			var5.addVertexWithUV(var18, (double)(par3 + 0),            (double)(par4 + 0.2), var9, var15);
			var5.addVertexWithUV(var26, (double)((float)par3 + var17), (double)(par4 + 0.2), var9, var13);
			var5.addVertexWithUV(var28, (double)((float)par3 + var17), (double)(par4 + 0.2), var11, var13);
			var5.addVertexWithUV(var20, (double)(par3 + 0),            (double)(par4 + 0.2), var11, var15);
			var5.addVertexWithUV(var20, (double)(par3 + 0),            (double)(par4 + 0.8), var9, var15);
			var5.addVertexWithUV(var28, (double)((float)par3 + var17), (double)(par4 + 0.8), var9, var13);


	    	var9 = icon.func_94209_e();
	    	var11 = icon.func_94212_f();
	    	var13 = icon.func_94206_g();
	    	var15 = icon.func_94210_h();

			var5.addVertexWithUV( (double)(par2 + 0.8), (double)((float)par3 + var17), var32, var11, var13);
			var5.addVertexWithUV( (double)(par2 + 0.8), (double)(par3 + 0), var24, var11, var15);
			var5.addVertexWithUV( (double)(par2 + 0.2), (double)(par3 + 0), var24, var9, var15);
			var5.addVertexWithUV( (double)(par2 + 0.2), (double)((float)par3 + var17), var32, var9, var13);
			var5.addVertexWithUV( (double)(par2 + 0.2), (double)((float)par3 + var17), var30, var11, var13);
			var5.addVertexWithUV( (double)(par2 + 0.2), (double)(par3 + 0), var22, var11, var15);
			var5.addVertexWithUV( (double)(par2 + 0.8), (double)(par3 + 0), var22, var9, var15);
			var5.addVertexWithUV( (double)(par2 + 0.8), (double)((float)par3 + var17), var30, var9, var13);


			var18 = (double)par2 + 0.5D - 0.3D; //
			var20 = (double)par2 + 0.5D + 0.3D; ///
			var22 = (double)par4 + 0.5D - 0.3D;
			var24 = (double)par4 + 0.5D + 0.3D;
			var26 = (double)par2 + 0.5D - 0.3D; //
			var28 = (double)par2 + 0.5D + 0.3D; ///
			var30 = (double)par4 + 0.5D - 0.3D;
			var32 = (double)par4 + 0.5D + 0.3D;
			var5.addVertexWithUV(var26, (double)((float)par3 + var17), (double)(par4 + 0.2), var9, var13);
			var5.addVertexWithUV(var18, (double)(par3 + 0),            (double)(par4 + 0.2), var9, var15);
			var5.addVertexWithUV(var18, (double)(par3 + 0),            (double)(par4 + 0.8), var11, var15);
			var5.addVertexWithUV(var26, (double)((float)par3 + var17), (double)(par4 + 0.8), var11, var13);
			var5.addVertexWithUV(var28, (double)((float)par3 + var17), (double)(par4 + 0.8), var9, var13);
			var5.addVertexWithUV(var20, (double)(par3 + 0),            (double)(par4 + 0.8), var9, var15);
			var5.addVertexWithUV(var20, (double)(par3 + 0),            (double)(par4 + 0.2), var11, var15);
			var5.addVertexWithUV(var28, (double)((float)par3 + var17), (double)(par4 + 0.2), var11, var13);
			
	    	var9 = icon.func_94209_e();
	    	var11 = icon.func_94212_f();
	    	var13 = icon.func_94206_g();
	    	var15 = icon.func_94210_h();

			var5.addVertexWithUV((double)(par2 + 0.2), (double)((float)par3 + var17), var32, var9, var13);
			var5.addVertexWithUV((double)(par2 + 0.2), (double)(par3 + 0), var24, var9, var15);
			var5.addVertexWithUV((double)(par2 + 0.8), (double)(par3 + 0), var24, var11, var15);
			var5.addVertexWithUV((double)(par2 + 0.8), (double)((float)par3 + var17), var32, var11, var13);
			var5.addVertexWithUV((double)(par2 + 0.8), (double)((float)par3 + var17), var30, var9, var13);
			var5.addVertexWithUV((double)(par2 + 0.8), (double)(par3 + 0), var22, var9, var15);
			var5.addVertexWithUV((double)(par2 + 0.2), (double)(par3 + 0), var22, var11, var15);
			var5.addVertexWithUV((double)(par2 + 0.2), (double)((float)par3 + var17), var30, var11, var13);
		}
			
		return true;
	}
	
	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return 0;
	}
}
