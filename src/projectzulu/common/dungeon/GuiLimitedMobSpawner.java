package projectzulu.common.dungeon;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import projectzulu.common.core.Pair;
import projectzulu.common.core.PairFullShortName;
import projectzulu.common.core.ProjectZuluLog;
//TODO: Scrolling Text in Creature List
//projectzuluresources\module_block\mobspawnergui.png
public class GuiLimitedMobSpawner extends GuiScreen{
	TileEntityLimitedMobSpawner limitedMobSpawner;
	Point backgroundSize = new Point(256, 244);
	private List<DataFields> dataFields = new ArrayList<DataFields>();
	public DataFields getDataField(int index){
		return dataFields.get(index);
	}
	boolean fieldsCreated = false;

	/* Used by Scrolling Creature List to know which Field to Return a Selected String to */
	int lastCalledElementID = -1;
	private ListType currentListType = ListType.NONE;
	GUISelectCreatureList scrollingList;
	List<PairFullShortName<String, String>> creatureListName = new ArrayList<PairFullShortName<String, String>>();
	List<PairFullShortName<String, String>> soundListName = new ArrayList<PairFullShortName<String, String>>();
	
    /** Counts the number of screen updates. */
    private int updateCounter;
    
    public GuiLimitedMobSpawner(TileEntityLimitedMobSpawner limitedMobSpawner){
    	this.limitedMobSpawner = limitedMobSpawner;
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
        controlList.add(new GuiButton(-3, this.width / 2 -70/2, (this.height+backgroundSize.getY())/2-27, 70, 20, "Cancel"));
        controlList.add(new GuiButton(-2, this.width / 2 -70/2 - 75, (this.height+backgroundSize.getY())/2-27, 70, 20, "Save & Close")); //Three Button System: Save & Close - Cancel - + Entry
        controlList.add(new GuiButton(-1, this.width / 2 -70/2 + 75, (this.height+backgroundSize.getY())/2-27, 70, 20, "New Entry"));
        limitedMobSpawner.setEditable(false);
        
        if(!fieldsCreated){
            dataFields.add(0, new SpawnerFields(0).createFields(0 , fontRenderer, this.width, this.height, backgroundSize));
            dataFields.get(0).loadFromTileEntity(limitedMobSpawner);
            for (int i = 1; i < 7; i++) {
            	dataFields.add(i, new CreatureFields(i).createFields(i, fontRenderer, this.width, this.height, backgroundSize));
                dataFields.get(i).loadFromTileEntity(limitedMobSpawner);
            }
            fieldsCreated = true;
        }else{
            for (int i = 0; i < 7; i++) {
            	dataFields.get(i).createFields(i , fontRenderer, this.width, this.height, backgroundSize);
            }
        }
        switch (currentListType) {
        case Creature:
        	scrollingList = new GUISelectCreatureList(this, creatureListName, currentListType, 70, new Point(this.width, this.height), backgroundSize);
            scrollingList.registerScrollButtons(this.controlList, 7, 8);
        	break;
		default:
			break;
		}
    }
    
    public void openList(ListType listType, int callingElementID){
    	lastCalledElementID = callingElementID;
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
    		
            scrollingList = new GUISelectCreatureList(this, creatureListName, currentListType, 70, new Point(this.width, this.height), backgroundSize);
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
            scrollingList = new GUISelectCreatureList(this, soundListName, currentListType, 70, new Point(this.width, this.height), backgroundSize);
            scrollingList.registerScrollButtons(this.controlList, 7, 8);
			break;
		default:
			throw new IllegalStateException("Trying to Open invalid List type " + listType.toString());
		}
    }
    
    public void closeList(){
    	lastCalledElementID = -1;
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
    		switch (button.id) {
    		case -3:
    			/* Close Menu With Saving*/
    			this.limitedMobSpawner.onInventoryChanged();
    			this.mc.displayGuiScreen((GuiScreen)null);
    			break;
    		case -2:
    			/* Close Menu With Saving*/
    			this.limitedMobSpawner.onInventoryChanged();
    			saveToTileEntity();
    			this.mc.displayGuiScreen((GuiScreen)null);
    			break;
    		case -1:
    			for (DataFields dataField : dataFields) {
    				if(!dataField.isEnabled()){
    					dataField.setIsEnabled(true);
    					break;
    				}
    			}
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
    	for (DataFields dataField : dataFields) {
    		if(dataField.isEnabled()){
    			dataField.keyboardInput(keyChar, keyID);
    		}
    	}
    }
    
    @Override
    protected void mouseClicked(int par1, int par2, int par3) {
    	super.mouseClicked(par1, par2, par3);
    	for (DataFields dataField : dataFields) {
    		dataField.mouseClicked(this, mc, par1, par2, par3);
    	}
    }
    
    /**
     * Draws the screen and all the components in it.
     */
//    creaturelistgui.png
    @Override
    public void drawScreen(int par1, int par2, float par3){
        this.drawDefaultBackground();
        fontRenderer.drawString("Edit Mob Spawner Settings", (width - fontRenderer.getStringWidth("Edit Mob Spawner Settings"))/2, (height-backgroundSize.getY())/2+8, 4210752); //White: 16777215
        super.drawScreen(par1, par2, par3);
        for (DataFields dataField : dataFields) {
        	dataField.render(mc, par1, par2, par3, new Point(this.width, this.height), backgroundSize);
        }
        
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
