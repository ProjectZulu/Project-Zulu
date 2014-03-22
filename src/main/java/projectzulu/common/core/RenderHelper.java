package projectzulu.common.core;

import java.util.EnumSet;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

public class RenderHelper {

    public static enum Surface {
        TOP(0), BOTTOM(1),

        /* +x is RIGHT */
        RIGHT(2), LEFT(3),

        /* +z is FRONT */
        FRONT(4), BACK(5);
        public final int index;

        Surface(int index) {
            this.index = index;
        }
    }

    public static boolean renderRotated2D(IIcon icon, float iconScale, RenderBlocks renderer, double posX, double posY,
            double posZ, double xWidth, double yHeight, double angle) {

        angle = angle * Math.PI / 180f;
        Tessellator tessellator = Tessellator.instance;
        double minU = icon.getMinU();
        double maxU = icon.getMinU() + (icon.getMaxU() - icon.getMinU()) / iconScale;
        double minV = icon.getMinV();
        double maxV = icon.getMinV() + (icon.getMaxV() - icon.getMinV()) / iconScale;

        double yMin = posY;

        double intermedVarX = +xWidth / 2;
        double point1X = posX + 0.5D - intermedVarX * Math.sin(angle);
        double point1Z = posZ + 0.5D + intermedVarX * Math.cos(angle);

        intermedVarX = -xWidth / 2;
        double point2X = posX + 0.5D - intermedVarX * Math.sin(angle);
        double point2Z = posZ + 0.5D + intermedVarX * Math.cos(angle);

        tessellator.addVertexWithUV(point1X, yMin + yHeight, point1Z, minU, minV);
        tessellator.addVertexWithUV(point1X, yMin + 0.0D, point1Z, minU, maxV);
        tessellator.addVertexWithUV(point2X, yMin + 0.0D, point2Z, maxU, maxV);
        tessellator.addVertexWithUV(point2X, yMin + yHeight, point2Z, maxU, minV);

        tessellator.addVertexWithUV(point2X, yMin + yHeight, point2Z, minU, minV);
        tessellator.addVertexWithUV(point2X, yMin + 0.0D, point2Z, minU, maxV);
        tessellator.addVertexWithUV(point1X, yMin + 0.0D, point1Z, maxU, maxV);
        tessellator.addVertexWithUV(point1X, yMin + yHeight, point1Z, maxU, minV);
        return true;
    }

    public static boolean renderRotatedRectangle(IIcon icon, float iconScale, RenderBlocks renderer, double posX,
            double posY, double posZ, double xWidth, double yHeight, double zWidth, double angle) {
        renderRotatedRectangle(icon, iconScale, renderer, posX, posY, posZ, xWidth, yHeight, zWidth, angle,
                EnumSet.allOf(Surface.class));
        return true;
    }

    public static boolean renderRotatedRectangle(IIcon icon, float iconScale, RenderBlocks renderer, double posX,
            double posY, double posZ, double xWidth, double yHeight, double zWidth, double angle,
            EnumSet<Surface> sidesToDraw) {
        angle = angle * Math.PI / 180f;
        Tessellator tessellator = Tessellator.instance;
        double deltaU = icon.getMaxU() - icon.getMinU();
        double deltaV = icon.getMaxV() - icon.getMinV();
        double minU = icon.getMinU() + deltaU / 2 * (1 - 1 / iconScale);
        double maxU = icon.getMaxU() - deltaU / 2 * (1 - 1 / iconScale);
        double minV = icon.getMinV() + deltaV / 2 * (1 - 1 / iconScale);
        double maxV = icon.getMaxV() - deltaV / 2 * (1 - 1 / iconScale);

        double yMin = posY;

        double intermedVarZ = -zWidth / 2;
        double intermedVarX = +xWidth / 2;
        double point1X = posX + 0.5D + intermedVarZ * Math.cos(angle) - intermedVarX * Math.sin(angle);
        double point1Z = posZ + 0.5D + intermedVarZ * Math.sin(angle) + intermedVarX * Math.cos(angle);

        intermedVarZ = -zWidth / 2;
        intermedVarX = -xWidth / 2;
        double point2X = posX + 0.5D + intermedVarZ * Math.cos(angle) - intermedVarX * Math.sin(angle);
        double point2Z = posZ + 0.5D + intermedVarZ * Math.sin(angle) + intermedVarX * Math.cos(angle);

        intermedVarZ = +zWidth / 2;
        intermedVarX = -xWidth / 2;
        double point3X = posX + 0.5D + intermedVarZ * Math.cos(angle) - intermedVarX * Math.sin(angle);
        double point3Z = posZ + 0.5D + intermedVarZ * Math.sin(angle) + intermedVarX * Math.cos(angle);

        intermedVarZ = +zWidth / 2;
        intermedVarX = +xWidth / 2;
        double point4X = posX + 0.5D + intermedVarZ * Math.cos(angle) - intermedVarX * Math.sin(angle);
        double point4Z = posZ + 0.5D + intermedVarZ * Math.sin(angle) + intermedVarX * Math.cos(angle);

        for (Surface surface : sidesToDraw) {
            switch (surface) {
            case RIGHT:
                tessellator.addVertexWithUV(point1X, yMin, point1Z, minU, minV);
                tessellator.addVertexWithUV(point1X, yMin + yHeight, point1Z, minU, maxV);
                tessellator.addVertexWithUV(point2X, yMin + yHeight, point2Z, maxU, maxV);
                tessellator.addVertexWithUV(point2X, yMin, point2Z, maxU, minV);

                tessellator.addVertexWithUV(point2X, yMin, point2Z, maxU, minV);
                tessellator.addVertexWithUV(point2X, yMin + yHeight, point2Z, maxU, maxV);
                tessellator.addVertexWithUV(point1X, yMin + yHeight, point1Z, minU, maxV);
                tessellator.addVertexWithUV(point1X, yMin, point1Z, minU, minV);
                break;
            case LEFT:
                tessellator.addVertexWithUV(point4X, yMin, point4Z, minU, minV);
                tessellator.addVertexWithUV(point4X, yMin + yHeight, point4Z, minU, maxV);
                tessellator.addVertexWithUV(point3X, yMin + yHeight, point3Z, maxU, maxV);
                tessellator.addVertexWithUV(point3X, yMin, point3Z, maxU, minV);

                tessellator.addVertexWithUV(point3X, yMin, point3Z, maxU, minV);
                tessellator.addVertexWithUV(point3X, yMin + yHeight, point3Z, maxU, maxV);
                tessellator.addVertexWithUV(point4X, yMin + yHeight, point4Z, minU, maxV);
                tessellator.addVertexWithUV(point4X, yMin, point4Z, minU, minV);
                break;
            case BACK:
                tessellator.addVertexWithUV(point1X, yMin, point1Z, minU, minV);
                tessellator.addVertexWithUV(point1X, yMin + yHeight, point1Z, minU, maxV);
                tessellator.addVertexWithUV(point4X, yMin + yHeight, point4Z, maxU, maxV);
                tessellator.addVertexWithUV(point4X, yMin, point4Z, maxU, minV);

                tessellator.addVertexWithUV(point4X, yMin, point4Z, maxU, minV);
                tessellator.addVertexWithUV(point4X, yMin + yHeight, point4Z, maxU, maxV);
                tessellator.addVertexWithUV(point1X, yMin + yHeight, point1Z, minU, maxV);
                tessellator.addVertexWithUV(point1X, yMin, point1Z, minU, minV);
                break;
            case FRONT:
                tessellator.addVertexWithUV(point3X, yMin, point3Z, minU, minV);
                tessellator.addVertexWithUV(point3X, yMin + yHeight, point3Z, minU, maxV);
                tessellator.addVertexWithUV(point2X, yMin + yHeight, point2Z, maxU, maxV);
                tessellator.addVertexWithUV(point2X, yMin, point2Z, maxU, minV);

                tessellator.addVertexWithUV(point2X, yMin, point2Z, maxU, minV);
                tessellator.addVertexWithUV(point2X, yMin + yHeight, point2Z, maxU, maxV);
                tessellator.addVertexWithUV(point3X, yMin + yHeight, point3Z, minU, maxV);
                tessellator.addVertexWithUV(point3X, yMin, point3Z, minU, minV);
                break;
            case TOP:
                tessellator.addVertexWithUV(point1X, yMin + yHeight, point1Z, minU, minV);
                tessellator.addVertexWithUV(point2X, yMin + yHeight, point2Z, minU, maxV);
                tessellator.addVertexWithUV(point3X, yMin + yHeight, point3Z, maxU, maxV);
                tessellator.addVertexWithUV(point4X, yMin + yHeight, point4Z, maxU, minV);

                tessellator.addVertexWithUV(point4X, yMin + yHeight, point4Z, maxU, minV);
                tessellator.addVertexWithUV(point3X, yMin + yHeight, point3Z, maxU, maxV);
                tessellator.addVertexWithUV(point2X, yMin + yHeight, point2Z, minU, maxV);
                tessellator.addVertexWithUV(point1X, yMin + yHeight, point1Z, minU, minV);
                break;
            case BOTTOM:
                tessellator.addVertexWithUV(point1X, yMin, point1Z, minU, minV);
                tessellator.addVertexWithUV(point2X, yMin, point2Z, minU, maxV);
                tessellator.addVertexWithUV(point3X, yMin, point3Z, maxU, maxV);
                tessellator.addVertexWithUV(point4X, yMin, point4Z, maxU, minV);

                tessellator.addVertexWithUV(point4X, yMin, point4Z, maxU, minV);
                tessellator.addVertexWithUV(point3X, yMin, point3Z, maxU, maxV);
                tessellator.addVertexWithUV(point2X, yMin, point2Z, minU, maxV);
                tessellator.addVertexWithUV(point1X, yMin, point1Z, minU, minV);
                break;
            }

        }
        return true;
    }
}
