package projectzulu.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import projectzulu.common.core.DefaultProps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityTombstoneRenderer extends TileEntitySpecialRenderer
{
    /** The ModelSign instance used by the TileEntityTombstoneRenderer */
    private ModelTombstone modelSign = new ModelTombstone();

    public static final ResourceLocation TOMBSTONE = new ResourceLocation(DefaultProps.blockKey, "Tombstone.png");
    
    public void renderTileEntityTombstoneAt(TileEntityTombstone par1TileEntityTombstone, double par2, double par4, double par6, float par8){
        Block var9 = par1TileEntityTombstone.getBlockType();
        int meta;
        float rotation;
        if(par8 == -1){
            meta = 4;
            rotation = meta*360/8;
        }else{
            meta = par1TileEntityTombstone.getBlockMetadata();
            rotation = par1TileEntityTombstone.getBlockMetadata()*360/8;
        }
        
        this.func_110628_a(TOMBSTONE);
        
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glRotatef(0.0f, 0.0f, 1.0F, 0.0f);
        GL11.glTranslatef((float)par2+0.5f, (float)par4+0.88f, (float)par6+0.5f);
        GL11.glScalef(-0.9f, -0.8F, 0.9F);
        this.modelSign.render1((Entity)null, 0, 0, 0, 0, 0, 0.0459f, -rotation);
        GL11.glPopMatrix();
        
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glRotatef(0.0f, 0.0f, 1.0F, 0.0f);
        GL11.glTranslatef((float)par2+0.5f, (float)par4+0.88f, (float)par6+0.5f);
        GL11.glScalef(-0.899f, -0.8F, 0.899F);
        this.modelSign.render2((Entity)null, 0, 0, 0, 0, 0, 0.0459f, -rotation);
        GL11.glPopMatrix();
        
        GL11.glPushMatrix();
        float var10 = 0.6666667F;
        GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 0.5F, (float)par6 + 0.5F);
        GL11.glRotatef(rotation, 0.0F, 1.0F, 0.0F); // Var11 is getMetaData Rotation
        
        FontRenderer var17 = this.getFontRenderer();
        float var12 = 0.016666668F * 0.8f * var10;
        float xOffset = 0.0f;
	    float zOffset = 0.15f;
	    float yOffset = -0.32f;
        GL11.glTranslatef(0.0F + xOffset, 0.5F * var10 + yOffset, 0.07F * var10 + zOffset);
        GL11.glScalef(var12, -var12, var12);
        GL11.glNormal3f(0.0F, 0.0F, -1.0F * var12);
        GL11.glDepthMask(false);
        byte var13 = 0;

        for (int var14 = 0; var14 < par1TileEntityTombstone.signText.length; ++var14){
            String var15 = par1TileEntityTombstone.signText[var14];
            if (var14 == par1TileEntityTombstone.lineBeingEdited) {
                var15 = "> " + var15 + " <";
                var17.drawString(var15, -var17.getStringWidth(var15) / 2, var14 * 10 - par1TileEntityTombstone.signText.length * 5, var13);
            } else {
                var17.drawString(var15, -var17.getStringWidth(var15) / 2, var14 * 10 - par1TileEntityTombstone.signText.length * 5, var13);
            }
        }
        GL11.glDepthMask(true);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }
    
    public String reverse(String s) {
        return new StringBuffer(s).reverse().toString();
    }
    
    @Override
    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8){
        this.renderTileEntityTombstoneAt((TileEntityTombstone)par1TileEntity, par2, par4, par6, par8);
    }
}
