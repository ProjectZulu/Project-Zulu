package projectzulu.common.dungeon;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;

import projectzulu.common.core.DefaultProps;

import com.google.common.base.CharMatcher;

public class SpawnerFields implements DataFields{
	private boolean isEnabled = true;

	private GuiTextField minSpawnDelay;
	private GuiTextField maxSpawnDelay;
	private GuiTextField maxToSpawn;
	private GuiTextField requiredPlayerRange;
	private GuiTextField maxNearbyEntities;
	String[] titles = new String[]{"Spawn Delay","Max Spawn","Activation Range", "Max Nearby"};
	int[] titleOffset;
	int totalFieldWidth;
	
	SpawnerFields(){}
	
	public DataFields createFields(Minecraft mc, int screenWidth, int screenHeight, Point backgroundSize){		
		minSpawnDelay = setupTextField(mc.fontRenderer, new Point(screenWidth, screenHeight), backgroundSize, new Point(111+1,22+2), new Point(20,14),
				minSpawnDelay != null ? minSpawnDelay.getText() : "");

		maxSpawnDelay = setupTextField(mc.fontRenderer, new Point(screenWidth, screenHeight), backgroundSize, new Point(135+1,22+2), new Point(20,14),
				maxSpawnDelay != null ? maxSpawnDelay.getText() : "");

		maxToSpawn = setupTextField(mc.fontRenderer, new Point(screenWidth, screenHeight), backgroundSize, new Point(228+1,22+2), new Point(20,14),
				maxToSpawn != null ? maxToSpawn.getText() : "");

		requiredPlayerRange = setupTextField(mc.fontRenderer, new Point(screenWidth, screenHeight), backgroundSize, new Point(135+1,39+2), new Point(39,14),
				requiredPlayerRange != null ? requiredPlayerRange.getText() : "");
		
		maxNearbyEntities = setupTextField(mc.fontRenderer, new Point(screenWidth, screenHeight), backgroundSize, new Point(228+1,39+2), new Point(39,14),
				maxNearbyEntities != null ? maxNearbyEntities.getText() : "");
		return this;
	}
	
	private GuiTextField setupTextField(FontRenderer fontRenderer, Point screenSize, Point backgroundSize, Point position, Point boxSize, String text){
		GuiTextField newTextField = new GuiTextField( fontRenderer,
				(screenSize.getX() - (int)backgroundSize.getX())/2+position.getX(),
				(screenSize.getY() - (int)backgroundSize.getY())/2+position.getY(),
				boxSize.getX(), boxSize.getY());
		newTextField.setText(text);
		newTextField.setTextColor(-1);
		newTextField.func_82266_h(-1);
		newTextField.setEnableBackgroundDrawing(false);
		newTextField.setMaxStringLength(3);		
		return newTextField;
	}
	
	@Override
	public void loadFromTileEntity(TileEntityLimitedMobSpawner limitedMobSpawner, int elementID){
		minSpawnDelay.setText(Integer.toString(limitedMobSpawner.getMinSpawnDelay()/20));
		maxSpawnDelay.setText(Integer.toString(limitedMobSpawner.getMaxSpawnDelay()/20));
		maxToSpawn.setText(Integer.toString(limitedMobSpawner.getMaxSpawnableEntities()));
		requiredPlayerRange.setText(Integer.toString(limitedMobSpawner.getRequriedPLayerRange()));
		maxNearbyEntities.setText(Integer.toString(limitedMobSpawner.getMaxNearbyEntities()));		
	}
	
	@Override
	public void saveToTileEntity(TileEntityLimitedMobSpawner limitedMobSpawner){
		limitedMobSpawner.setMinMaxSpawnDelay(Integer.parseInt(minSpawnDelay.getText())*20, Integer.parseInt(maxSpawnDelay.getText())*20);
		limitedMobSpawner.setMaxSpawnableEntities(Integer.parseInt(maxToSpawn.getText()));
		limitedMobSpawner.setRequiredPlayerRange(Integer.parseInt(requiredPlayerRange.getText()));
		limitedMobSpawner.setMaxNearbyEntities(Integer.parseInt(maxNearbyEntities.getText()));		
	}
	
	public void setIsEnabled(boolean isEnabled){
		this.isEnabled = isEnabled;
	}

	public boolean isEnabled(){
		return isEnabled;
	}

	public boolean keyboardInput(char keyChar, int keyID){
		if(isEnabled){
			return correctIfInvalid(minSpawnDelay, keyChar, keyID) 
					|| correctIfInvalid(maxSpawnDelay, keyChar, keyID)
					|| correctIfInvalid(maxToSpawn, keyChar, keyID)
					|| correctIfInvalid(maxNearbyEntities, keyChar, keyID )
					|| correctIfInvalid(requiredPlayerRange, keyChar, keyID);
		}
		return false;
	}
	
	private boolean correctIfInvalid(GuiTextField guiTextField, char keyChar, int keyID){
		if(guiTextField.textboxKeyTyped(keyChar, keyID)){
			String originalString = guiTextField.getText();
			String numericString = CharMatcher.anyOf("0123456789").retainFrom(guiTextField.getText()).replaceAll("^0*", "");
			if(!originalString.equals(numericString)){
				guiTextField.setText(numericString);
			}
			if(guiTextField.getText().length() == 0 ){
				guiTextField.setText("0");
			}
			return true;
		}
		return false;
	}
	
	public void mouseClicked(GuiLimitedMobSpawner spawnerGUI, Minecraft mc, int par1, int par2, int par3){
		if(isEnabled){
			minSpawnDelay.mouseClicked(par1, par2, par3);
			maxSpawnDelay.mouseClicked(par1, par2, par3);
			maxToSpawn.mouseClicked(par1, par2, par3);
			maxNearbyEntities.mouseClicked(par1, par2, par3);
			requiredPlayerRange.mouseClicked(par1, par2, par3);
		}
	}

	public void mouseHover(int par1, int par2, int par3){
		if(isEnabled){
			
		}
	}
	
	public void render(Minecraft mc, int par1, int par2, float par3, Point screenSize, Point backgroundSize){
		if(isEnabled){
			/* Draw Raw Text */
			mc.fontRenderer.drawString(titles[0],
					(int)(screenSize.getX() - backgroundSize.getX())/2 + 46,
					(int)(screenSize.getY() - backgroundSize.getY())/2 + 26, 4210752); // White: 16777215
			mc.fontRenderer.drawString(titles[1],
					(int)(screenSize.getX() - backgroundSize.getX())/2 + 46+114,
					(int)(screenSize.getY() - backgroundSize.getY())/2 + 26, 4210752);
			mc.fontRenderer.drawString(titles[2],
					(int)(screenSize.getX() - backgroundSize.getX())/2 + 46,
					(int)(screenSize.getY() - backgroundSize.getY())/2 + 26+17, 4210752);
			mc.fontRenderer.drawString(titles[3],
					(int)(screenSize.getX() - backgroundSize.getX())/2 + 46+114,
					(int)(screenSize.getY() - backgroundSize.getY())/2 + 26+17, 4210752);
			
			/* Draw TextBox Background Objects */
	        int textureID = mc.renderEngine.getTexture(DefaultProps.dungeonDiretory+"creaturelistgui.png");
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        mc.renderEngine.bindTexture(textureID);
	        
	        Point smallBoxImageLocation = new Point(155,0);
	        Point smallBoxSize = new Point(22,14);
	        drawBackgroundBox(new Point(110,21), screenSize, backgroundSize, smallBoxImageLocation, smallBoxSize);
	        drawBackgroundBox(new Point(134,21), screenSize, backgroundSize, smallBoxImageLocation, smallBoxSize);
	        drawBackgroundBox(new Point(227,21), screenSize, backgroundSize, smallBoxImageLocation, smallBoxSize);
	        drawBackgroundBox(new Point(134,38), screenSize, backgroundSize, smallBoxImageLocation, smallBoxSize);
	        drawBackgroundBox(new Point(227,38), screenSize, backgroundSize, smallBoxImageLocation, smallBoxSize);
	        
			/* Draw Interactive Text Boxes */
			minSpawnDelay.drawTextBox();
			maxSpawnDelay.drawTextBox();
			maxToSpawn.drawTextBox();
			maxNearbyEntities.drawTextBox();
			requiredPlayerRange.drawTextBox();			
		}
	}
	
	private void drawBackgroundBox(Point position, Point screenSize, Point backgroundSize, Point imageLocation, Point imageSize){
        int xCoord = (screenSize.getX() - backgroundSize.getX()) / 2+position.getX();
        int yCoord = (screenSize.getY() - backgroundSize.getY()) / 2+position.getY();
        this.drawTexturedModalRect(xCoord, yCoord, imageLocation.getX(), imageLocation.getY(), imageSize.getX(), imageSize.getY());
	}
	
	/**
     * Draws a textured rectangle at the stored z-value. Args: x, y, u, v, width, height
     */
    public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6){
        float var7 = 0.00390625F;
        float var8 = 0.00390625F;
        float zLevel = 0;
        Tessellator var9 = Tessellator.instance;
        var9.startDrawingQuads();
        var9.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), (double)zLevel, (double)((float)(par3 + 0) * var7), (double)((float)(par4 + par6) * var8));
        var9.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), (double)zLevel, (double)((float)(par3 + par5) * var7), (double)((float)(par4 + par6) * var8));
        var9.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), (double)zLevel, (double)((float)(par3 + par5) * var7), (double)((float)(par4 + 0) * var8));
        var9.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)zLevel, (double)((float)(par3 + 0) * var7), (double)((float)(par4 + 0) * var8));
        var9.draw();
    }

}
