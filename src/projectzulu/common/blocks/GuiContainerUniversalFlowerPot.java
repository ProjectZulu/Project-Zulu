package projectzulu.common.blocks;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import projectzulu.common.core.DefaultProps;

public class GuiContainerUniversalFlowerPot extends GuiContainer{

	public GuiContainerUniversalFlowerPot (InventoryPlayer inventoryPlayer,
			TileEntityUniversalFlowerPot tileEntity) {
		//the container is instanciated and passed to the superclass for handling
		super(new ContainerUniversalFlowerPot(inventoryPlayer, tileEntity));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		//draw text and stuff here
		//the parameters for drawString are: string, x, y, color
		fontRenderer.drawString("Flower Pot", 8, 6, 4210752);
		//draws "Inventory" or your regional equivalent
		fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2,
			int par3) {
		//draw your Gui here, only thing you need to change is the path
		int texture = mc.renderEngine.getTexture(DefaultProps.coreDiretory + "gui/FlowerPotGUI.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//		this.mc.renderEngine.bindTexture(texture); //TODO: Commented
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}
