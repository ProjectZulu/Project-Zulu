package projectzulu.common.dungeon;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;

import projectzulu.common.core.DefaultProps;

public class GUISelectionList extends GuiScrollingList {

    private GuiLimitedMobSpawner parent;
    // private List<PairFullShortName<String,String>> listNames;
    ListType listType;
    int selectedElement = -1;

    Node currentNode;
    GuiButton previous;
    public static final ResourceLocation CREATURE_GUI = new ResourceLocation(DefaultProps.dungeonKey,
            "creaturelistgui.png");

    public GUISelectionList(GuiLimitedMobSpawner parent, Node rootSoundNode, ListType listType, int listWidth,
            Point screenSize, Point backgroundSize) {
        super(parent.getMinecraft(), listWidth, backgroundSize.getY() + 50, // Width, Height
                (screenSize.getY() - backgroundSize.getY()) / 2 + 25, (screenSize.getY() - backgroundSize.getY()) / 2
                        + backgroundSize.getY() - 20, // Top, Bottom,
                (screenSize.getX() - backgroundSize.getX()) / 2 + 234, // Left
                parent.getMinecraft().fontRenderer.FONT_HEIGHT + 8); // Element Height
        this.parent = parent;
        this.currentNode = rootSoundNode;
        this.listType = listType;
        previous = new GuiButton(ButtonIDs.BACKWARDS.index, screenSize.getX() / 2 + 106,
                (screenSize.getY() + backgroundSize.getY()) / 2 - 240, 85, 20, "Parent Dir.");
    }

    @Override
    protected int getSize() {
        return currentNode.numberOfChildren();
    }

    @Override
    protected void elementClicked(int clickedIndex, boolean var2) {
        // TODO: update for Node
        Node childNode = currentNode.getChild(clickedIndex);
        if (childNode.numberOfChildren() > 0) {
            currentNode = childNode;
        } else {
            if (parent.getDataField(parent.currentDataField) instanceof CreatureFields) {
                ((CreatureFields) parent.getDataField(parent.currentDataField)).setDataFromList(
                        currentNode.getChild(clickedIndex).getFullName(), listType);
                parent.closeList();
            }
        }
        // if(parent.getDataField(parent.currentDataField) instanceof CreatureFields){
        // ((CreatureFields)parent.getDataField(parent.currentDataField)).setDataFromList(listNames.get(clickedIndex).getFullName(),
        // listType);
        // parent.closeList();
        // }
        // selectedElement = clickedIndex;
    }

    @Override
    protected boolean isSelected(int selectedIndex) {
        return selectedIndex == selectedElement;
    }

    @Override
    protected void drawBackground() {
        parent.getMinecraft().renderEngine.bindTexture(CREATURE_GUI);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int xCoord = (parent.width - parent.backgroundSize.getX()) / 2 + 230; // 277
        int yCoord = (parent.height - parent.backgroundSize.getY()) / 2;
        parent.drawTexturedModalRect(xCoord, yCoord, 0, 0, 91, 244);
    }

    @Override
    protected int getContentHeight() {
        return (this.getSize()) * slotHeight;// + 35*2 + 1;
    }

    @Override
    protected void drawSlot(int listIndex, int var2, int var3, int var4, Tessellator tessellator) {
        if (currentNode.numberOfChildren() <= listIndex) {
            return;
        }
        String renderString = currentNode.getChild(listIndex).getName(); // (160 << 16) + (145 << 8) + 114)
        boolean addDirTag = currentNode.getChild(listIndex).numberOfChildren() != 0;
        renderString = addDirTag ? "D:" + renderString : renderString;
        renderString = renderString.length() > 13 ? renderString.substring(0, 13) : renderString;
        parent.drawString(parent.getMinecraft().fontRenderer, renderString, this.left + 3, var3 + 2, 16777215); // Red:
                                                                                                                // 0xFF2222
                                                                                                                // //Blck:
                                                                                                                // 4210752
    }

    public void drawScreen(Point screenSize, Point backgroundSize, int mouseX, int mouseY, float p_22243_3_) {
        super.drawScreen(mouseX, mouseY, p_22243_3_);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        drawScrollOverlay(this.left, (screenSize.getY() - backgroundSize.getY()) / 2 + 4, this.top, 255, 255); // Top
                                                                                                               // Overlay
        drawScrollOverlay(this.left, this.bottom,
                (screenSize.getY() - backgroundSize.getY()) / 2 + backgroundSize.getY() - 4, 255, 255); // Bot Overlay
        // GL11.glEnable(GL11.GL_DEPTH_TEST);
        // GL11.glDisable(GL11.GL_TEXTURE_2D);
        previous.drawButton(parent.getMinecraft(), mouseX, mouseY);
    }

    private void drawScrollOverlay(int leftOffset, int topHeight, int botHeight, int alphaBottom, int alphaTop) {
        Tessellator var5 = Tessellator.instance;
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, parent.getMinecraft().renderEngine.getTexture(getBackgroundTexture())
                .getGlTextureId());
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        float imageSize = 32.0F;
        var5.startDrawingQuads();
        // var5.setColorRGBA_I(4210752, alphaTop);
        var5.addVertexWithUV(leftOffset, botHeight, 0.0D, 0.0D, botHeight / imageSize);
        var5.addVertexWithUV(leftOffset + (double) this.listWidth, botHeight, 0.0D, (this.listWidth) / imageSize,
                botHeight / imageSize);
        // var5.setColorRGBA_I(4210752, alphaBottom);
        var5.addVertexWithUV(leftOffset + (double) this.listWidth, topHeight, 0.0D, (this.listWidth) / imageSize,
                topHeight / imageSize);
        var5.addVertexWithUV(leftOffset, topHeight, 0.0D, 0.0D, topHeight / imageSize);
        var5.draw();
    }

    public void mouseClicked(int par1, int par2, int par3) {
        if (par3 == 0 && previous.mousePressed(parent.getMinecraft(), par1, par2)) {
            if (currentNode.getParent() != null) {
                currentNode = currentNode.getParent();
                parent.getMinecraft().getSoundHandler()
                        .playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
        }
    }
}
