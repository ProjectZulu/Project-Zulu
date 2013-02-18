package projectzulu.common.dungeon;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundPool;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;

import projectzulu.common.blocks.StringHelper;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ObfuscationHelper;
import projectzulu.common.core.PairFullShortName;
import projectzulu.common.core.ProjectZuluLog;

public class GuiLimitedMobSpawner extends GuiScreen{
	TileEntityLimitedMobSpawner limitedMobSpawner;
	Point backgroundSize = new Point(256, 244);
	
	int currentDataField = 0;
	int numberOfFields = 1;
	private List<DataFields> dataFields = new ArrayList<DataFields>();
	public DataFields getDataField(int index){
		return dataFields.get(index);
	}
	
	boolean fieldsCreated = false;

	/* Used by Scrolling Creature List to know which Field to Return a Selected String to */
	GUISelectionList scrollingList;
	private ListType currentListType = ListType.NONE;
	public ListType getListType(){
		return currentListType;
	}
	List<PairFullShortName<String, String>> creatureListName = new ArrayList<PairFullShortName<String, String>>();
	List<PairFullShortName<String, String>> soundListName = new ArrayList<PairFullShortName<String, String>>();

    /** Counts the number of screen updates. */
    private int updateCounter;
    
    public GuiLimitedMobSpawner(TileEntityLimitedMobSpawner limitedMobSpawner){
    	this.limitedMobSpawner = limitedMobSpawner;
    	
    	if(limitedMobSpawner.getSpawnList() != null){
        	numberOfFields = limitedMobSpawner.getSpawnList().size()+1;
        }else{
        	numberOfFields = 1;
        }
    }
    

    
    public Minecraft getMinecraft(){
    	return mc;
    }
    
    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui() {
    	super.initGui();
        controlList.clear();
        Keyboard.enableRepeatEvents(true);
        controlList.add(new GuiButton(ButtonIDs.BACKWARDS.index, this.width / 2 -25/2 -23, (this.height+backgroundSize.getY())/2-27-25+5, 25, 20, "<<"));
        controlList.add(new GuiButton(ButtonIDs.FORWARD.index, this.width / 2 -25/2 +23, (this.height+backgroundSize.getY())/2-27-25+5, 25, 20, ">>"));
        
        controlList.add(new GuiButton(ButtonIDs.CANCEL.index, this.width / 2 -70/2, (this.height+backgroundSize.getY())/2-27+2, 70, 20, "Cancel"));
        controlList.add(new GuiButton(ButtonIDs.SAVENCLOSE.index, this.width / 2 -70/2 - 75, (this.height+backgroundSize.getY())/2-27+2, 70, 20, "Save & Close")); //Three Button System: Save & Close - Cancel - + Entry
        
        controlList.add(new GuiButton(ButtonIDs.NEWENTRY.index, this.width / 2 -70/2 + 75, (this.height+backgroundSize.getY())/2-27+2, 70, 20, "New Entry"));
        controlList.add(new GuiButton(ButtonIDs.DELENTRY.index, this.width / 2 -70/2 + 75 , (this.height+backgroundSize.getY())/2-27-25+5, 70, 20, "Delete Entry"));

        limitedMobSpawner.setEditable(false);
        
        for (int i = 0; i < numberOfFields; i++) {
        	if(dataFields.isEmpty() || dataFields.size() <= i){
    			if(i == 0){
    				dataFields.add(0, new SpawnerFields().createFields(mc, this.width, this.height, backgroundSize));
    				dataFields.get(0).loadFromTileEntity(limitedMobSpawner, i);
    			}else{
                	dataFields.add(i, new CreatureFields(mc).createFields(mc, this.width, this.height, backgroundSize));
                    dataFields.get(i).loadFromTileEntity(limitedMobSpawner, i);
    			}
			}else{
				dataFields.get(i).createFields(mc, this.width, this.height, backgroundSize);
			}
		}
        
        
        switch (currentListType) {
        case Creature:
        	scrollingList = new GUISelectionList(this, creatureListName, currentListType, 70, new Point(this.width, this.height), backgroundSize);
            scrollingList.registerScrollButtons(this.controlList, 7, 8);
        	break;
        case Sound:
        	scrollingList = new GUISelectionList(this, soundListName, currentListType, 70, new Point(this.width, this.height), backgroundSize);
            scrollingList.registerScrollButtons(this.controlList, 7, 8);
        	break;
		default:
			break;
		}
    }
    
    public void openList(ListType listType){
    	currentListType = listType;
    	switch (currentListType) {
    	case Creature:
    		/* Create List if Empty */
    		if(creatureListName == null || creatureListName.isEmpty()){
    			Iterator stringToClassIterator = EntityList.stringToClassMapping.keySet().iterator();
    	    	while(stringToClassIterator.hasNext()){
    	    		String stringKey = (String) stringToClassIterator.next();
    	    		if( EntityLiving.class.isAssignableFrom( ((Class)EntityList.stringToClassMapping.get(stringKey))) ){
    	                creatureListName.add(new PairFullShortName<String, String>(
    	                		stringKey,
    	                		StringHelper.toTitleCase(StringHelper.simplifyStringNameForDisplay(stringKey, 10, "\\."))));
    	    		}
    	    	}
    	    	Collections.sort(creatureListName);
    		}
    		
            scrollingList = new GUISelectionList(this, creatureListName, currentListType, 70, new Point(this.width, this.height), backgroundSize);
            scrollingList.registerScrollButtons(this.controlList, 7, 8);
			break;
		case Sound:
			if(soundListName == null || soundListName.isEmpty()){
				SoundPool soundPool = mc.sndManager.soundPoolSounds;
				
				/* Grab "nameToSoundPoolEntriesMapping" : OBFSC: "m" : nameToSoundPoolEntriesMapping --> fields.csv --> joined.srg --> d */
				HashMap soundHash;
				if(ObfuscationHelper.isUnObfuscated(SoundPool.class.getDeclaredFields().clone(), "nameToSoundPoolEntriesMapping")){
					soundHash = ObfuscationHelper.getFieldFromReflection("nameToSoundPoolEntriesMapping", soundPool, HashMap.class);
				}else{
					soundHash = ObfuscationHelper.getFieldFromReflection("d", soundPool, HashMap.class);
				}
				if(soundHash != null){
					Iterator stringSoundIterator = soundHash.keySet().iterator();
					while(stringSoundIterator.hasNext()){
						String stringKey = (String) stringSoundIterator.next();
						soundListName.add(new PairFullShortName<String, String>(
								stringKey,
								StringHelper.toTitleCase(StringHelper.simplifyStringNameForDisplay(stringKey, 10, "\\."))));
					}
					Collections.sort(soundListName);
				}
			}
            scrollingList = new GUISelectionList(this, soundListName, currentListType, 70, new Point(this.width, this.height), backgroundSize);
            scrollingList.registerScrollButtons(this.controlList, 7, 8);
			break;
		default:
			throw new IllegalStateException("Trying to Open invalid List type " + listType.toString());
		}
    }
    
    public void closeList(){
    	currentListType = ListType.NONE;
        scrollingList = null;
    }
    
    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed(){
        Keyboard.enableRepeatEvents(false);
    	//TODO: Sync To Server
        limitedMobSpawner.setEditable(true);
    }
    
    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen(){
        ++this.updateCounter;
    }
    
    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton button){
    	/* If Not on Main Screen */
    	if (button.enabled){
    		switch (ButtonIDs.getButtonByIndex(button.id)) {
    		case CANCEL:
    			/* Close Menu With Saving*/
    			this.limitedMobSpawner.onInventoryChanged();
    			this.mc.displayGuiScreen((GuiScreen)null);
    			break;
    		case FORWARD:
    			if (currentDataField+1 < dataFields.size()) {
    				currentDataField++;
    				numberOfFields++;
				}
    			break;
    		case BACKWARDS:
    			if (currentDataField > 0) {
    				currentDataField--;
				}
    			break;
    		case SAVENCLOSE:
    			/* Close Menu With Saving*/
    			this.limitedMobSpawner.onInventoryChanged();
    			saveToTileEntity();
    			this.mc.displayGuiScreen((GuiScreen)null);
    			break;
    		case DELENTRY:
    			if(currentDataField != 0){
        			dataFields.remove(currentDataField);
        			currentDataField--;
        			numberOfFields--;
    			}
    			break;
    		case NEWENTRY:
            	dataFields.add(new CreatureFields(mc).createFields(mc, this.width, this.height, backgroundSize));
                dataFields.get(dataFields.size()-1).loadFromTileEntity(limitedMobSpawner, dataFields.size()-1);
    			break;
    		default:
    			throw new IllegalStateException("Button action does not exist.");
    		}            
    	}
    }
   
    public void saveToTileEntity(){
    	if(limitedMobSpawner.getSpawnList() == null){
    		limitedMobSpawner.setSpawnList(new ArrayList<TileEntityLimitedMobSpawnData>());
    	}
    	limitedMobSpawner.getSpawnList().clear();
    	for (int i = 0; i < dataFields.size(); i++) {
    		dataFields.get(i).saveToTileEntity(limitedMobSpawner);
		}
    	if(limitedMobSpawner.getSpawnList().isEmpty()){
    		limitedMobSpawner.setSpawnList(null);
    	}
    	limitedMobSpawner.syncToServer();
    }
    
    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    @Override
    protected void keyTyped(char keyChar, int keyID){
    	super.keyTyped(keyChar, keyID);
    	
    	if(dataFields.get(currentDataField).isEnabled()){
			dataFields.get(currentDataField).keyboardInput(keyChar, keyID);
		}
    }
    
    @Override
    protected void mouseClicked(int par1, int par2, int par3) {
    	super.mouseClicked(par1, par2, par3);
    	
    	if(dataFields.get(currentDataField).isEnabled()){
			dataFields.get(currentDataField).mouseClicked(this, mc, par1, par2, par3);
		}
    }
    
    /**
     * Draws the screen and all the components in it.
     */
//    creaturelistgui.png
    @Override
    public void drawScreen(int par1, int par2, float par3){
        this.drawDefaultBackground();
        
        if(dataFields.get(currentDataField).isEnabled()){
			dataFields.get(currentDataField).render(mc, par1, par2, par3, new Point(this.width, this.height), backgroundSize);
		}
        
        String titleString = "Edit Mob Spawner Settings " + Integer.toString(currentDataField) + "/"+ Integer.toString(dataFields.size()-1);
        fontRenderer.drawString(titleString, (width - fontRenderer.getStringWidth(titleString))/2, (height-backgroundSize.getY())/2+8, 4210752); //White: 16777215
        super.drawScreen(par1, par2, par3);

        if(currentListType != ListType.NONE){
        	scrollingList.drawBackground();
        	scrollingList.drawScreen(new Point(this.width, this.height), backgroundSize, par1, par2, par3);
        }
    }
    
    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    @Override
    public void drawDefaultBackground() {
    	super.drawDefaultBackground();
        int textureID = mc.renderEngine.getTexture(DefaultProps.dungeonDiretory+"mobspawnergui.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(textureID);
        int xCoord = (width - backgroundSize.getX()) / 2;
        int yCoord = (height - backgroundSize.getY()) / 2;
        this.drawTexturedModalRect(xCoord, yCoord, 0, 0, backgroundSize.getX(), backgroundSize.getY());
    }
}
