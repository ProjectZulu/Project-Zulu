package projectzulu.common.dungeon;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.ServersideAttributeMap;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;

import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ObfuscationHelper;
import projectzulu.common.core.ProjectZuluLog;

import com.google.common.base.CharMatcher;

public class CreatureFields implements DataFields {
    private boolean isEnabled = true;
    private GuiSaveableTextField creatureNameField;
    private GuiSaveableTextField soundNameField;
    private GuiSaveableTextField weightedChanceField;
    private GuiSaveableTextField optionalParameter;

    private GUIEditNodeTextField selectedTagField;

    /* Holds Data Loaded From Entity in the Spawner System Passed */
    private NBTTagCompound loadedNBT = null;
    private GuiButton searchForEntity;
    private GuiButton searchForSound;

    private GuiButton resetNBTList;
    private GuiButton saveCurNBT;
    private GuiButton discardCurNBT;

    private GUINBTList nbtList;
    private NBTTree nbtTree;
    public Minecraft mc;
    Point screenSize;
    Point backgroundSize;

    public static final ResourceLocation CREATURE_LIST = new ResourceLocation(DefaultProps.dungeonKey,
            "creaturelistgui.png");

    CreatureFields(Minecraft mc) {
        this.mc = mc;
    }

    @Override
    public DataFields createFields(Minecraft mc, int screenWidth, int screenHeight, Point backgroundSize) {
        if (creatureNameField == null) {
            creatureNameField = new GuiSaveableTextField(mc.fontRenderer, 60, new Point(screenWidth, screenHeight),
                    backgroundSize, new Point(82, 30 - 3), new Point(116, 18));
            soundNameField = new GuiSaveableTextField(mc.fontRenderer, 60, new Point(screenWidth, screenHeight),
                    backgroundSize, new Point(82, 55 - 6), new Point(116, 18));
            weightedChanceField = new GuiSaveableTextField(mc.fontRenderer, 2, new Point(screenWidth, screenHeight),
                    backgroundSize, new Point(206, 39 + 42 - 9), new Point(20, 18));
            selectedTagField = new GUIEditNodeTextField(mc.fontRenderer, 60, new Point(screenWidth, screenHeight),
                    backgroundSize, new Point(8, 181), new Point(116, 18));
            optionalParameter = new GuiSaveableTextField(mc.fontRenderer, 2400, new Point(screenWidth, screenHeight),
                    backgroundSize, new Point(34, 39 + 42 - 9), new Point(116, 18));
        } else {
            creatureNameField = new GuiSaveableTextField(creatureNameField, mc.fontRenderer, 60, new Point(screenWidth,
                    screenHeight), backgroundSize, new Point(82, 30 - 3), new Point(116, 18));
            soundNameField = new GuiSaveableTextField(soundNameField, mc.fontRenderer, 60, new Point(screenWidth,
                    screenHeight), backgroundSize, new Point(82, 55 - 6), new Point(116, 18));
            weightedChanceField = new GuiSaveableTextField(weightedChanceField, mc.fontRenderer, 2, new Point(
                    screenWidth, screenHeight), backgroundSize, new Point(205, 39 + 42 - 9), new Point(20, 18));
            selectedTagField = new GUIEditNodeTextField(selectedTagField, mc.fontRenderer, 60, new Point(screenWidth,
                    screenHeight), backgroundSize, new Point(8, 181), new Point(116, 18));
            optionalParameter = new GuiSaveableTextField(optionalParameter, mc.fontRenderer, 2400, new Point(
                    screenWidth, screenHeight), backgroundSize, new Point(34, 39 + 42 - 9), new Point(116, 18));
        }

        searchForEntity = new GuiButton(1, (screenWidth - backgroundSize.getX()) / 2 + 201,
                (screenHeight - backgroundSize.getY()) / 2 + 38 - 15 - 3, 20, 20, "...");
        searchForSound = new GuiButton(2, (screenWidth - backgroundSize.getX()) / 2 + 201,
                (screenHeight - backgroundSize.getY()) / 2 + 38 + 10 - 6, 20, 20, "...");
        resetNBTList = new GuiButton(3, (screenWidth - backgroundSize.getX()) / 2 + 78,
                (screenHeight - backgroundSize.getY()) / 2 + 219, 70, 20, "Recreate NBT");

        saveCurNBT = new GuiButton(4, (screenWidth - backgroundSize.getX()) / 2 + 151,
                (screenHeight - backgroundSize.getY()) / 2 + 175, 34, 20, "Save");
        discardCurNBT = new GuiButton(5, (screenWidth - backgroundSize.getX()) / 2 + 187,
                (screenHeight - backgroundSize.getY()) / 2 + 175, 34, 20, "Abort");

        if (nbtList != null) {
            nbtList = new GUINBTList(this, mc, nbtTree, 214, new Point(screenWidth, screenHeight), backgroundSize);
        }
        this.screenSize = new Point(screenWidth, screenHeight);
        this.backgroundSize = backgroundSize;
        return this;
    }

    @Override
    public void loadFromTileEntity(TileEntityLimitedMobSpawner limitedMobSpawner, int elementID) {
        if (limitedMobSpawner.getSpawnList() != null && limitedMobSpawner.getSpawnList().size() > elementID - 1) {
            TileEntityLimitedMobSpawnData spawnEntryData = (limitedMobSpawner.getSpawnList().get(elementID - 1));
            if (spawnEntryData.type.length() > 0) {
                creatureNameField.setText(spawnEntryData.type);
                weightedChanceField.setText(Integer.toString(spawnEntryData.itemWeight));
                optionalParameter.setText(spawnEntryData.optionalParameters);
                loadedNBT = (NBTTagCompound) spawnEntryData.properties.copy();
                if (loadedNBT != null) {
                    nbtTree = new NBTTree(loadedNBT, "Properties");
                    nbtList = new GUINBTList(this, mc, nbtTree, 214, screenSize, backgroundSize);
                }
                soundNameField.setText(spawnEntryData.spawnSound);
                setIsEnabled(true);
            }
        }
    }

    @Override
    public void saveToTileEntity(TileEntityLimitedMobSpawner limitedMobSpawner) {
        if (isEnabled() && isEntryValid()) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setString("Type", creatureNameField.getText());
            nbt.setInteger("Weight", Integer.parseInt(weightedChanceField.getText()));
            nbt.setTag("Properties", nbtTree.toNBTTagCompound());
            nbt.setString("SpawnSound", soundNameField.getText());
            nbt.setString("OptionalParameter", optionalParameter.getText());
            limitedMobSpawner.getSpawnList().add(new TileEntityLimitedMobSpawnData(limitedMobSpawner, nbt));
        }
    }

    private boolean isEntryValid() {
        if (nbtTree == null) {
            ProjectZuluLog.info("Rejecting Mob Spawner Entry due to Invalid NBT data");
            return false;
        } else if (creatureNameField.getText().length() > 0
                && EntityList.stringToClassMapping.containsKey(creatureNameField.getText())
                && weightedChanceField.getText().length() > 0) {
            return true;
        } else {
            ProjectZuluLog.info("Rejecting Mob Spawner Entry due to Invalid data");
            return false;
        }
    }

    public void setDataFromList(String data, ListType listType) {
        switch (listType) {
        case Creature:
            creatureNameField.setText(data);
            resetNBTList();
            break;
        case Sound:
            soundNameField.setText(data);
            break;
        default:
            break;
        }
    }

    private void resetNBTList() {
        EntityLivingBase desiredEntity = (EntityLivingBase) EntityList.createEntityByName(creatureNameField.getText(),
                Minecraft.getMinecraft().theWorld);
        if (desiredEntity != null) {
            try {
                ObfuscationHelper.setCatchableFieldUsingReflection("field_110155_d", EntityLivingBase.class,
                        desiredEntity, true, new ServersideAttributeMap());
            } catch (Exception e) {
                ObfuscationHelper.setFieldUsingReflection("attributeMap", EntityLivingBase.class, desiredEntity, true,
                        new ServersideAttributeMap());
            }
            ObfuscationHelper.invokeMethod("applyEntityAttributes", "func_110147_ax", EntityLivingBase.class,
                    desiredEntity);
            loadedNBT = new NBTTagCompound();
            desiredEntity.writeToNBT(loadedNBT);
            nbtTree = new NBTTree(loadedNBT, "Properties");
            nbtList = new GUINBTList(this, mc, nbtTree, 214, screenSize, backgroundSize);
        }
    }

    public void setSelectedCurentNode(NBTNode tag) {
        selectedTagField.setSelectedNode(tag);
    }

    @Override
    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public boolean keyboardInput(char keyChar, int keyID) {
        if (isEnabled) {
            if (creatureNameField.textboxKeyTyped(keyChar, keyID)) {
                return true;
            } else if (weightedChanceField.textboxKeyTyped(keyChar, keyID)) {
                String originalString = weightedChanceField.getText();
                String newString = CharMatcher.anyOf("0123456789").retainFrom(weightedChanceField.getText());
                if (!originalString.equals(newString)) {
                    weightedChanceField.setText(newString);
                }
                return true;
            } else if (soundNameField.textboxKeyTyped(keyChar, keyID)) {
                return true;
            } else if (selectedTagField.textboxKeyTyped(keyChar, keyID)) {
                return true;
            } else if (optionalParameter.textboxKeyTyped(keyChar, keyID)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void mouseClicked(GuiLimitedMobSpawner spawnerGUI, Minecraft mc, int par1, int par2, int par3) {
        if (isEnabled) {
            creatureNameField.mouseClicked(par1, par2, par3);
            weightedChanceField.mouseClicked(par1, par2, par3);
            soundNameField.mouseClicked(par1, par2, par3);
            selectedTagField.mouseClicked(par1, par2, par3);
            optionalParameter.mouseClicked(par1, par2, par3);

            if (par3 == 0 && searchForEntity.mousePressed(mc, par1, par2)) {
                if (spawnerGUI.currentListType == ListType.Creature) {
                    spawnerGUI.closeList();
                } else {
                    spawnerGUI.openList(ListType.Creature);
                }
                // Used to random.click, leaving this as note during porting in case gui.button.press is wrong
                this.mc.getSoundHandler().playSound(
                        PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 0.8F));
            }

            if (par3 == 0 && searchForSound.mousePressed(mc, par1, par2)) {
                if (spawnerGUI.currentListType == ListType.Sound) {
                    spawnerGUI.closeList();
                } else {
                    spawnerGUI.openList(ListType.Sound);
                }
                this.mc.getSoundHandler().playSound(
                        PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }

            if (par3 == 0 && resetNBTList.mousePressed(mc, par1, par2)) {
                resetNBTList();
                this.mc.getSoundHandler().playSound(
                        PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
            }
            if (par3 == 0 && saveCurNBT.mousePressed(mc, par1, par2)) {
                if (selectedTagField.isEnabled() && nbtTree != null) {
                    selectedTagField.saveAndClear(nbtTree);
                    nbtList.recreateNodeList();
                    loadedNBT = nbtTree.toNBTTagCompound();
                    this.mc.getSoundHandler().playSound(
                            PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
                }
            }
            if (par3 == 0 && discardCurNBT.mousePressed(mc, par1, par2)) {
                if (selectedTagField.isEnabled()) {
                    selectedTagField.clear();
                    this.mc.getSoundHandler().playSound(
                            PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
                }
            }
        }
    }

    @Override
    public void mouseHover(int par1, int par2, int par3) {
        if (isEnabled) {

        }
    }

    @Override
    public void render(Minecraft mc, int par1, int par2, float par3, Point screenSize, Point backgroundSize) {
        if (nbtList != null) {
            nbtList.drawScreen(screenSize, backgroundSize, par1, par2, par3);
        }

        resetNBTList.drawButton(mc, par1, par2);
        saveCurNBT.drawButton(mc, par1, par2);
        discardCurNBT.drawButton(mc, par1, par2);
        searchForEntity.drawButton(mc, par1, par2);
        searchForSound.drawButton(mc, par1, par2);

        mc.fontRenderer.drawString("Name", (screenSize.getX() - backgroundSize.getX()) / 2 + 48,
                (screenSize.getY() - backgroundSize.getY()) / 2 + 48 - 15 - 3, 4210752); // White: 16777215
        mc.fontRenderer.drawString("Sound", (screenSize.getX() - backgroundSize.getX()) / 2 + 48,
                (screenSize.getY() - backgroundSize.getY()) / 2 + 48 + 10 - 6, 4210752); // White: 16777215
        mc.fontRenderer.drawString("Weight", (screenSize.getX() - backgroundSize.getX()) / 2 + 168,
                (screenSize.getY() - backgroundSize.getY()) / 2 + 74, 4210752);
        mc.fontRenderer.drawString("Tags", (screenSize.getX() - backgroundSize.getX()) / 2 + 6,
                (screenSize.getY() - backgroundSize.getY()) / 2 + 74, 4210752);

        bindTexture(mc);
        drawBackgroundBox(new Point(80, 20), screenSize, backgroundSize, new Point(136, 66), new Point(120, 20));
        drawBackgroundBox(new Point(80, 42), screenSize, backgroundSize, new Point(136, 66), new Point(120, 20));
        drawBackgroundBox(new Point(201, 48 + 25 - 9), screenSize, backgroundSize, new Point(236, 0), new Point(20, 20));
        drawBackgroundBox(new Point(32, 48 + 25 - 9), screenSize, backgroundSize, new Point(136, 66),
                new Point(120, 20));
        if (selectedTagField.isEnabled()) {
            drawBackgroundBox(new Point(6, 175), screenSize, backgroundSize, new Point(136, 66), new Point(120, 20));
        } else {
            drawBackgroundBox(new Point(6, 175), screenSize, backgroundSize, new Point(136, 89), new Point(120, 20));
        }

        creatureNameField.drawTextBox();
        soundNameField.drawTextBox();
        weightedChanceField.drawTextBox();
        selectedTagField.drawTextBox();
        optionalParameter.drawTextBox();
    }

    private void bindTexture(Minecraft mc) {
        mc.renderEngine.bindTexture(CREATURE_LIST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void drawBackgroundBox(Point position, Point screenSize, Point backgroundSize, Point imageLocation,
            Point imageSize) {
        int xCoord = (screenSize.getX() - backgroundSize.getX()) / 2 + position.getX();
        int yCoord = (screenSize.getY() - backgroundSize.getY()) / 2 + position.getY();
        this.drawTexturedModalRect(xCoord, yCoord, imageLocation.getX(), imageLocation.getY(), imageSize.getX(),
                imageSize.getY());
    }

    /**
     * Draws a textured rectangle at the stored z-value. Args: x, y, u, v, width, height
     */
    public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
        float var7 = 0.00390625F;
        float var8 = 0.00390625F;
        float zLevel = 0;
        Tessellator var9 = Tessellator.instance;
        var9.startDrawingQuads();
        var9.addVertexWithUV(par1 + 0, par2 + par6, zLevel, (par3 + 0) * var7, (par4 + par6) * var8);
        var9.addVertexWithUV(par1 + par5, par2 + par6, zLevel, (par3 + par5) * var7, (par4 + par6) * var8);
        var9.addVertexWithUV(par1 + par5, par2 + 0, zLevel, (par3 + par5) * var7, (par4 + 0) * var8);
        var9.addVertexWithUV(par1 + 0, par2 + 0, zLevel, (par3 + 0) * var7, (par4 + 0) * var8);
        var9.draw();
    }
}
