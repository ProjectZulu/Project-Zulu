package projectzulu.common.dungeon;

import java.util.List;

import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;

import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.PairFullShortName;

public class GUISelectionList extends GuiScrollingList{
	
    private GuiLimitedMobSpawner parent;
    private List<PairFullShortName<String,String>> listNames;
    ListType listType;
    int selectedElement = -1;
    
    public GUISelectionList(GuiLimitedMobSpawner parent, List<PairFullShortName<String,String>> listNames, ListType listType, int listWidth, Point screenSize, Point backgroundSize){
    	super(parent.getMinecraft(),
    			listWidth, backgroundSize.getY()+50, // Width, Height
    			(screenSize.getY()-backgroundSize.getY())/2+15, (screenSize.getY()-backgroundSize.getY())/2+backgroundSize.getY()-20, // Top, Bottom, 
    			(screenSize.getX()-backgroundSize.getX())/2+234, // Left
    			parent.getMinecraft().fontRenderer.FONT_HEIGHT+8); // Element Height
    	this.parent = parent;
    	this.listNames = listNames;
    	this.listType = listType;
    }
    
    @Override
    protected int getSize(){
        return listNames.size();
    }

    @Override
    protected void elementClicked(int clickedIndex, boolean var2){
    	if(parent.getDataField(parent.currentDataField) instanceof CreatureFields){
			((CreatureFields)parent.getDataField(parent.currentDataField)).setDataFromList(listNames.get(clickedIndex).getFullName(), listType);
    		parent.closeList();
    	}
    	selectedElement = clickedIndex;
    }
    
    @Override
    protected boolean isSelected(int selectedIndex){
    	return selectedIndex == selectedElement;
    }

    @Override
    protected void drawBackground(){
    	parent.getMinecraft().renderEngine.bindTexture(DefaultProps.dungeonDiretory+"creaturelistgui.png");
//        int textureID = parent.getMinecraft().renderEngine.getTexture(DefaultProps.dungeonDiretory+"creaturelistgui.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//        parent.getMinecraft().renderEngine.bindTexture(textureID); //TODO: Commented
        int xCoord = (parent.width - parent.backgroundSize.getX()) / 2+230; //277
        int yCoord = (parent.height - parent.backgroundSize.getY()) / 2;
        parent.drawTexturedModalRect(xCoord, yCoord, 0, 0, 91, 244);
    }
    
    @Override
    protected int getContentHeight(){
        return (this.getSize()) * slotHeight;// + 35*2 + 1;
    }
    
    @Override
    protected void drawSlot(int listIndex, int var2, int var3, int var4, Tessellator tessellator) {
    	String renderString = listNames.get(listIndex).getShortName(); //(160 << 16) + (145 << 8) + 114)
    	parent.drawString(parent.getMinecraft().fontRenderer, renderString, this.left + 3 , var3 + 2, 16777215); //Red: 0xFF2222 //Blck: 4210752
    }
    
    public void drawScreen(Point screenSize, Point backgroundSize, int mouseX, int mouseY, float p_22243_3_){
    	super.drawScreen(mouseX, mouseY, p_22243_3_);

    	GL11.glEnable(GL11.GL_TEXTURE_2D);
    	GL11.glDisable(GL11.GL_DEPTH_TEST);
    	drawScrollOverlay(this.left, (screenSize.getY()-backgroundSize.getY())/2+4, this.top, 255, 255); // Top Overlay
    	drawScrollOverlay(this.left, this.bottom, (screenSize.getY()-backgroundSize.getY())/2+backgroundSize.getY()-4, 255, 255); // Bot Overlay
//    	GL11.glEnable(GL11.GL_DEPTH_TEST);
//    	GL11.glDisable(GL11.GL_TEXTURE_2D);
    }
    
    private void drawScrollOverlay(int leftOffset, int topHeight, int botHeight, int alphaBottom, int alphaTop){
    	Tessellator var5 = Tessellator.instance;
    	GL11.glBindTexture(GL11.GL_TEXTURE_2D, parent.getMinecraft().renderEngine.getTexture(getBackgroundTexture()));
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	float imageSize = 32.0F;
    	var5.startDrawingQuads();
//    	var5.setColorRGBA_I(4210752, alphaTop);
    	var5.addVertexWithUV(
    			leftOffset, (double)botHeight, 0.0D,
    			0.0D, (double)((float)botHeight / imageSize));
    	var5.addVertexWithUV(
    			leftOffset+(double)this.listWidth, (double)botHeight, 0.0D,
    			(double)((float)(this.listWidth) / imageSize), (double)((float)botHeight / imageSize));
//    	var5.setColorRGBA_I(4210752, alphaBottom);
    	var5.addVertexWithUV(
    			leftOffset+(double)this.listWidth, (double)topHeight, 0.0D,
    			(double)((float)(this.listWidth) / imageSize), (double)((float)topHeight / imageSize));
    	var5.addVertexWithUV(
    			leftOffset, (double)topHeight, 0.0D,
    			0.0D, (double)((float)topHeight / imageSize));
    	var5.draw();
    }
    
}
