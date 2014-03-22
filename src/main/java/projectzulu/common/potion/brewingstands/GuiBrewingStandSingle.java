package projectzulu.common.potion.brewingstands;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import projectzulu.common.core.DefaultProps;

public class GuiBrewingStandSingle extends GuiContainer {

    private TileEntityBrewingBase brewingStand;

    public static final ResourceLocation brewing1 = new ResourceLocation(DefaultProps.coreKey, "gui/brewing1.png");
    public static final ResourceLocation brewing3 = new ResourceLocation(DefaultProps.coreKey, "gui/brewing3.png");

    public GuiBrewingStandSingle(InventoryPlayer inventoryPlayer, TileEntityBrewingBase tileEntity) {
        super(new ContainerBrewingStandSingle(inventoryPlayer, tileEntity));
        brewingStand = tileEntity;
    }

    /**
     * Draw the Foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String s = StatCollector.translateToLocal(this.brewingStand.getInventoryName());
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2,
                4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (brewingStand.getSizeInventory() - 1 < 3) {
            this.mc.renderEngine.bindTexture(brewing1);
        } else {
            this.mc.renderEngine.bindTexture(brewing3);
        }

        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1 = this.brewingStand.getBrewTime();

        if (i1 > 0) {
            int j1 = (int) (28.0F * (1.0F - i1 / 400.0F));

            if (j1 > 0) {
                this.drawTexturedModalRect(k + 97, l + 16, 176, 0, 9, j1);
            }

            int k1 = i1 / 2 % 7;

            switch (k1) {
            case 0:
                j1 = 29;
                break;
            case 1:
                j1 = 24;
                break;
            case 2:
                j1 = 20;
                break;
            case 3:
                j1 = 16;
                break;
            case 4:
                j1 = 11;
                break;
            case 5:
                j1 = 6;
                break;
            case 6:
                j1 = 0;
            }

            if (j1 > 0) {
                this.drawTexturedModalRect(k + 65, l + 14 + 29 - j1, 185, 29 - j1, 12, j1);
            }
        }
    }
}
