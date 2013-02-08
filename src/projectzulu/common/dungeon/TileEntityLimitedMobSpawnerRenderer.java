package projectzulu.common.dungeon;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

public class TileEntityLimitedMobSpawnerRenderer extends TileEntitySpecialRenderer{
    public void renderTileEntityMobSpawner(TileEntityLimitedMobSpawner limitedMobSpawner, double xCoord, double yCoord, double zCoord, float par8){
        GL11.glPushMatrix();
        GL11.glTranslatef((float)xCoord + 0.5F, (float)yCoord, (float)zCoord + 0.5F);
        Entity var9 = limitedMobSpawner.getMobEntity();

        if (var9 != null){
            var9.setWorld(limitedMobSpawner.getWorldObj());
            float var10 = 0.4375F;
            GL11.glTranslatef(0.0F, 0.4F, 0.0F);
            GL11.glRotatef((float)(limitedMobSpawner.yaw2 + (limitedMobSpawner.yaw - limitedMobSpawner.yaw2) * (double)par8) * 10.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(0.0F, -0.4F, 0.0F);
            GL11.glScalef(var10, var10, var10);
            var9.setLocationAndAngles(xCoord, yCoord, zCoord, 0.0F, 0.0F);
            RenderManager.instance.renderEntityWithPosYaw(var9, 0.0D, 0.0D, 0.0D, 0.0F, par8);
        }

        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double xCoord, double yCoord, double zCoord, float par8){
        this.renderTileEntityMobSpawner((TileEntityLimitedMobSpawner)par1TileEntity, xCoord, yCoord, zCoord, par8);
    }
}
