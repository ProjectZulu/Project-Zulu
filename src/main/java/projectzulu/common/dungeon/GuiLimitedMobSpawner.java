package projectzulu.common.dungeon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;

import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ObfuscationHelper;
import projectzulu.common.core.ProjectZuluLog;

//TODO: Implement Try Write / Read to Test if Saving Would Cause a Crash
public class GuiLimitedMobSpawner extends GuiScreen {
    public TileEntityLimitedMobSpawner limitedMobSpawner;
    Point backgroundSize = new Point(256, 244);

    int numberOfFields = 1;
    int currentDataField = 0;
    private List<DataFields> dataFields = new ArrayList<DataFields>();
    public static final ResourceLocation SPAWNER_GUI = new ResourceLocation(DefaultProps.dungeonKey,
            "mobspawnergui.png");

    public DataFields getDataField(int index) {
        return dataFields.get(index);
    }

    /* Used by Scrolling Creature List to know which Field to Return a Selected String to */
    GUISelectionList scrollingList;
    public ListType currentListType = ListType.NONE;
    Node rootCreatureNode = new Node(null, "root");
    Node rootSoundNode = new Node(null, "root");

    public GuiLimitedMobSpawner(TileEntityLimitedMobSpawner limitedMobSpawner) {
        this.limitedMobSpawner = limitedMobSpawner;
        if (limitedMobSpawner.getSpawnList() != null) {
            numberOfFields = limitedMobSpawner.getSpawnList().size() + 1;
        } else {
            numberOfFields = 1;
        }
    }

    public Minecraft getMinecraft() {
        return mc;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();
        Keyboard.enableRepeatEvents(true);
        buttonList.add(new GuiButton(ButtonIDs.BACKWARDS.index, this.width / 2 - 25 / 2 - 38,
                (this.height + backgroundSize.getY()) / 2 - 47, 25, 20, "<<"));
        buttonList.add(new GuiButton(ButtonIDs.FORWARD.index, this.width / 2 - 25 / 2 + 8,
                (this.height + backgroundSize.getY()) / 2 - 47, 25, 20, ">>"));

        buttonList.add(new GuiButton(ButtonIDs.SAVENCLOSE.index, this.width / 2 - 70 / 2 - 88,
                (this.height + backgroundSize.getY()) / 2 - 47, 70, 20, "Save & Quit")); // Three Button System: Save &
                                                                                         // Close - Cancel - + Entry
        buttonList.add(new GuiButton(ButtonIDs.CANCEL.index, this.width / 2 - 70 / 2 - 88,
                (this.height + backgroundSize.getY()) / 2 - 25, 70, 20, "Cancel"));

        buttonList.add(new GuiButton(ButtonIDs.NEWENTRY.index, this.width / 2 - 70 / 2 + 75 - 17,
                (this.height + backgroundSize.getY()) / 2 - 47, 70, 20, "New Entry"));
        buttonList.add(new GuiButton(ButtonIDs.DELENTRY.index, this.width / 2 - 70 / 2 + 75 - 17,
                (this.height + backgroundSize.getY()) / 2 - 25, 70, 20, "Delete Entry"));

        limitedMobSpawner.setEditable(false);

        for (int i = 0; i < numberOfFields; i++) {
            if (dataFields.isEmpty() || dataFields.size() <= i) {
                if (i == 0) {
                    dataFields
                            .add(0, new SpawnerFields(this).createFields(mc, this.width, this.height, backgroundSize));
                    dataFields.get(0).loadFromTileEntity(limitedMobSpawner, i);
                } else {
                    dataFields.add(i, new CreatureFields(mc).createFields(mc, this.width, this.height, backgroundSize));
                    dataFields.get(i).loadFromTileEntity(limitedMobSpawner, i);
                }
            } else {
                dataFields.get(i).createFields(mc, this.width, this.height, backgroundSize);
            }
        }

        switch (currentListType) {
        case Creature:
            scrollingList = new GUISelectionList(this, rootCreatureNode, currentListType, 85, new Point(this.width,
                    this.height), backgroundSize);
            scrollingList.registerScrollButtons(this.buttonList, 7, 8);
            break;
        case Sound:
            scrollingList = new GUISelectionList(this, rootSoundNode, currentListType, 85, new Point(this.width,
                    this.height), backgroundSize);
            scrollingList.registerScrollButtons(this.buttonList, 7, 8);
            break;
        default:
            break;
        }
    }

    public void openList(ListType listType) {
        currentListType = listType;
        switch (currentListType) {
        case Creature:
            /* Create List if Empty */
            if (rootCreatureNode.numberOfChildren() == 0) {
                Iterator stringToClassIterator = EntityList.stringToClassMapping.keySet().iterator();
                while (stringToClassIterator.hasNext()) {
                    String stringKey = (String) stringToClassIterator.next();
                    if (EntityLiving.class.isAssignableFrom(((Class) EntityList.stringToClassMapping.get(stringKey)))) {
                        if (stringKey.equals("Mob")) {
                            continue;
                        }
                        rootCreatureNode.addChild("root." + stringKey);
                    }
                }
                rootCreatureNode.sortNodeTree();
            }
            scrollingList = new GUISelectionList(this, rootCreatureNode, currentListType, 85, new Point(this.width,
                    this.height), backgroundSize);
            scrollingList.registerScrollButtons(this.buttonList, 7, 8);
            break;
        case Sound:
            if (rootSoundNode.numberOfChildren() == 0) {
                /*
                 * Grab "nameToSoundPoolEntriesMapping" : OBFSC: "m" : nameToSoundPoolEntriesMapping --> fields.csv -->
                 * joined.srg --> d
                 */
                SoundRegistry registry = ObfuscationHelper.getFieldFromReflection("field_147697_e", "sndRegistry",
                        mc.getSoundHandler(), SoundRegistry.class);
                HashMap soundHash = ObfuscationHelper.getFieldFromReflection("field_148764_a", "field_148764_a",
                        registry, HashMap.class);

                if (soundHash != null) {
                    Iterator stringSoundIterator = soundHash.keySet().iterator();
                    while (stringSoundIterator.hasNext()) {
                        ResourceLocation key = (ResourceLocation) stringSoundIterator.next();
                         rootSoundNode.addChild("root." + key.getResourceDomain() + "." + key.getResourcePath());
                    }
                    rootSoundNode.sortNodeTree();
                }
            }
            scrollingList = new GUISelectionList(this, rootSoundNode, currentListType, 85, new Point(this.width,
                    this.height), backgroundSize);
            scrollingList.registerScrollButtons(this.buttonList, 7, 8);
            break;
        default:
            throw new IllegalStateException("Trying to Open invalid List type " + listType.toString());
        }
    }

    public void closeList() {
        currentListType = ListType.NONE;
        scrollingList = null;
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        // TODO: Sync To Server
        limitedMobSpawner.setEditable(true);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen() {
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    @Override
    protected void actionPerformed(GuiButton button) {
        /* If Not on Main Screen */
        if (button.enabled) {
            switch (ButtonIDs.getButtonByIndex(button.id)) {
            case CANCEL:
                /* Close Menu With Saving */
                this.limitedMobSpawner.markDirty();
                closeGui();
                break;
            case FORWARD:
                if (currentDataField + 1 < dataFields.size()) {
                    currentDataField++;
                }
                break;
            case BACKWARDS:
                if (currentDataField > 0) {
                    currentDataField--;
                }
                break;
            case SAVENCLOSE:
                /* Close Menu With Saving */
                this.limitedMobSpawner.markDirty();
                saveGuiToTileEntity();
                closeGui();
                break;
            case DELENTRY:
                if (currentDataField != 0) {
                    dataFields.remove(currentDataField);
                    currentDataField--;
                    numberOfFields--;
                }
                break;
            case NEWENTRY:
                dataFields.add(new CreatureFields(mc).createFields(mc, this.width, this.height, backgroundSize));
                dataFields.get(dataFields.size() - 1).loadFromTileEntity(limitedMobSpawner, dataFields.size() - 1);
                currentDataField = dataFields.size() - 1;
                numberOfFields++;
                break;
            default:
                throw new IllegalStateException("Button action does not exist.");
            }
        }
    }

    public void closeGui() {
        this.mc.displayGuiScreen((GuiScreen) null);
    }

    public void loadGuiFromTileEntity() {
        for (int i = 0; i < dataFields.size(); i++) {
            dataFields.get(i).loadFromTileEntity(limitedMobSpawner, i);
        }
    }

    public void saveGuiToTileEntity() {
        if (limitedMobSpawner.getSpawnList() == null) {
            limitedMobSpawner.setSpawnList(new ArrayList<TileEntityLimitedMobSpawnData>());
        }
        limitedMobSpawner.getSpawnList().clear();
        for (int i = 0; i < dataFields.size(); i++) {
            dataFields.get(i).saveToTileEntity(limitedMobSpawner);
        }
        if (limitedMobSpawner.getSpawnList().isEmpty()) {
            limitedMobSpawner.setSpawnList(null);
        }
        limitedMobSpawner.syncToServer();
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    @Override
    protected void keyTyped(char keyChar, int keyID) {
        super.keyTyped(keyChar, keyID);

        if (dataFields.get(currentDataField).isEnabled()) {
            dataFields.get(currentDataField).keyboardInput(keyChar, keyID);
        }
    }

    @Override
    protected void mouseClicked(int par1, int par2, int par3) {
        super.mouseClicked(par1, par2, par3);

        if (dataFields.get(currentDataField).isEnabled()) {
            dataFields.get(currentDataField).mouseClicked(this, mc, par1, par2, par3);
        }

        if (currentListType != ListType.NONE) {
            scrollingList.mouseClicked(par1, par2, par3);
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    // creaturelistgui.png
    @Override
    public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();

        if (dataFields.get(currentDataField).isEnabled()) {
            dataFields.get(currentDataField).render(mc, par1, par2, par3, new Point(this.width, this.height),
                    backgroundSize);
        }

        String titleString = "Edit Mob Spawner Settings " + Integer.toString(currentDataField) + "/"
                + Integer.toString(dataFields.size() - 1);
        fontRendererObj.drawString(titleString, (width - fontRendererObj.getStringWidth(titleString)) / 2,
                (height - backgroundSize.getY()) / 2 + 8, 4210752); // White: 16777215
        super.drawScreen(par1, par2, par3);

        if (currentListType != ListType.NONE) {
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
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(SPAWNER_GUI);
        int xCoord = (width - backgroundSize.getX()) / 2;
        int yCoord = (height - backgroundSize.getY()) / 2;
        this.drawTexturedModalRect(xCoord, yCoord, 0, 0, backgroundSize.getX(), backgroundSize.getY());
    }
}
