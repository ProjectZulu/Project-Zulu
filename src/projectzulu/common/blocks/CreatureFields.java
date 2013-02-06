package projectzulu.common.blocks;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;

import projectzulu.common.core.ProjectZuluLog;

import com.google.common.base.CharMatcher;

public class CreatureFields implements DataFields {
	public int rowIndex;
	public int elementID;
	private boolean isEnabled = false;

	private GuiTextField creatureNameField;
	private GuiTextField weightedChanceField;
	/* Holds Data Loaded From Entity in the Spawner System Passed*/
	private NBTTagCompound loadedNBT = null;
	String[] titles = new String[]{"Name","Chance"};

	private GuiButton removeButton;
	private GuiButton searchForEntity;

	CreatureFields(Integer elementID){
		this.elementID = elementID;
	}
	
	public DataFields createFields(int rowIndex, FontRenderer fontRenderer, int screenWidth, int screenHeight, Point backgroundSize){
		this.rowIndex = rowIndex;		
		creatureNameField = setupTextField(fontRenderer, new Point(screenWidth, screenHeight), backgroundSize, new Point(34+1,39+24*rowIndex+7), new Point(100,18),
				creatureNameField != null ? creatureNameField.getText() : "", 60);
		
		weightedChanceField = setupTextField(fontRenderer, new Point(screenWidth, screenHeight), backgroundSize, new Point(205+4,39+24*rowIndex+7), new Point(20,18),
				weightedChanceField != null ? weightedChanceField.getText() : "1", 2);
		
		removeButton = new GuiButton(-2,
				(screenWidth - (int)backgroundSize.getX())/2+228,
				(screenHeight - (int)backgroundSize.getY())/2+38+24*rowIndex, 20, 20, "-");
		searchForEntity = new GuiButton(-2,
				(screenWidth - (int)backgroundSize.getX())/2+138,
				(screenHeight - (int)backgroundSize.getY())/2+38+24*rowIndex, 20, 20, "...");
		return this;
	}
	
	private GuiTextField setupTextField(FontRenderer fontRenderer, Point screenSize, Point backgroundSize, Point position, Point boxSize, String text, int maxText){
		GuiTextField newTextField = new GuiTextField( fontRenderer,
				(screenSize.getX() - (int)backgroundSize.getX())/2+position.getX(),
				(screenSize.getY() - (int)backgroundSize.getY())/2+position.getY(),
				boxSize.getX(), boxSize.getY());
		newTextField.setText(text);
		newTextField.setTextColor(-1);
		newTextField.func_82266_h(-1);
		newTextField.setMaxStringLength(maxText);
		newTextField.setEnableBackgroundDrawing(false);
		return newTextField;
	}
	
	@Override
	public void loadFromTileEntity(TileEntityLimitedMobSpawner limitedMobSpawner) {
		if(limitedMobSpawner.getSpawnList() != null && limitedMobSpawner.getSpawnList().size() > elementID-1){
			TileEntityLimitedMobSpawnData spawnEntryData = ((TileEntityLimitedMobSpawnData)limitedMobSpawner.getSpawnList().get(elementID-1));
			if(spawnEntryData.type.length() > 0){
				creatureNameField.setText(spawnEntryData.type);
				weightedChanceField.setText(Integer.toString(spawnEntryData.itemWeight));
				loadedNBT = spawnEntryData.properties;
				setIsEnabled(true);
			}
		}
	}
	
	@Override
	public void saveToTileEntity(TileEntityLimitedMobSpawner limitedMobSpawner) {
		if(isEnabled() && isEntryValid()){
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setString("Type", creatureNameField.getText());
			nbt.setInteger("Weight", Integer.parseInt(weightedChanceField.getText()));
			nbt.setCompoundTag("Properties", loadedNBT != null ? loadedNBT : new NBTTagCompound());
			
			limitedMobSpawner.getSpawnList().add(new TileEntityLimitedMobSpawnData(limitedMobSpawner, nbt));
		}
	}
	
	private boolean isEntryValid(){
		if(creatureNameField.getText().length() > 0 && EntityList.stringToClassMapping.containsKey(creatureNameField.getText())
				&& weightedChanceField.getText().length() > 0){
			return true;
		}else{
			ProjectZuluLog.info("Rejecting Entry due to Invalid data");
			return false;
		}
	}
	
	public void setcreatureNameField(String creatureName){
		creatureNameField.setText(creatureName);
	}
	
	public void setIsEnabled(boolean isEnabled){
		this.isEnabled = isEnabled;
	}

	public boolean isEnabled(){
		return isEnabled;
	}

	public boolean keyboardInput(char keyChar, int keyID, boolean mainScreen){
		if(isEnabled){
			if(creatureNameField.textboxKeyTyped(keyChar, keyID)){
				return true;
			}else if(weightedChanceField.textboxKeyTyped(keyChar, keyID)){
				weightedChanceField.setText(CharMatcher.anyOf("0123456789").retainFrom(weightedChanceField.getText()));
				return true;
			}
		}
		return false;
	}

	public void mouseClicked(GuiLimitedMobSpawner spawnerGUI, Minecraft mc, int par1, int par2, int par3, boolean mainScreen){
		if(isEnabled){
			creatureNameField.mouseClicked(par1, par2, par3);
			weightedChanceField.mouseClicked(par1, par2, par3);
			
			if(par3 == 0 && removeButton.mousePressed(mc, par1, par2)){
//                this.selectedButton = var5;
                mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
                setIsEnabled(false);
                //this.actionPerformed(var5); This Will be Out own Action in This Local Class i.e. TODO: isEnabled(false)
            }
			
			if(par3 == 0 && searchForEntity.mousePressed(mc, par1, par2)){
				spawnerGUI.mainScreen = false;
				spawnerGUI.lastCalledElementID = elementID;
//              this.selectedButton = var5;
				
				mc.sndManager.playSoundFX("random.click", 1.0F, 1.0F);
//				setIsEnabled(false);
              //this.actionPerformed(var5); This Will be Out own Action in This Local Class i.e. TODO: isEnabled(false)
          }
		}
	}

	public void mouseHover(int par1, int par2, int par3){
		if(isEnabled){
			
		}
	}
	
	public void render(Minecraft mc, int par1, int par2, float par3, Point screenSize, Point backgroundSize, boolean mainScreen){
		
		mc.fontRenderer.drawString(titles[0],
				(int)(screenSize.getX() - backgroundSize.getX())/2 + 6,
				(int)(screenSize.getY() - backgroundSize.getY())/2 +48+24*rowIndex, 4210752);// White: 16777215
		mc.fontRenderer.drawString(titles[1],
				(int)(screenSize.getX() - backgroundSize.getX())/2 + 165,
				(int)(screenSize.getY() - backgroundSize.getY())/2 +48+24*rowIndex, 4210752);
		
		if(isEnabled){
			/* Draw Raw Text */
			
			/* Draw TextBox Background Objects */
	        int textureID = mc.renderEngine.getTexture("/projectzuluresources/module_block/creaturelistgui.png");
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        mc.renderEngine.bindTexture(textureID);
	        drawBackgroundBox(new Point(33,39+24*rowIndex-1), screenSize, backgroundSize, new Point(154,22), new Point(102,20));
	        drawBackgroundBox(new Point(205,39+24*rowIndex-1), screenSize, backgroundSize, new Point(236,0), new Point(20,20));
			
			/* Draw Interactive+Text Boxes */
			creatureNameField.drawTextBox();
			weightedChanceField.drawTextBox();
			removeButton.drawButton(mc, par1, par2);
			searchForEntity.drawButton(mc, par1, par2);
			
		}else{
			/* Draw TextBox Background Objects */
	        int textureID = mc.renderEngine.getTexture("/projectzuluresources/module_block/creaturelistgui.png");
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        mc.renderEngine.bindTexture(textureID);
	        drawBackgroundBox(new Point(33,39+24*rowIndex-1), screenSize, backgroundSize, new Point(154,44), new Point(102,20));
	        drawBackgroundBox(new Point(205,39+24*rowIndex-1), screenSize, backgroundSize, new Point(215,0), new Point(20,20));
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
