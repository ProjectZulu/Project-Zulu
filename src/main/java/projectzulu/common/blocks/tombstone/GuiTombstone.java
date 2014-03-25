package projectzulu.common.blocks.tombstone;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ChatAllowedCharacters;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.mobs.packets.PacketTileText;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTombstone extends GuiScreen {
    /**
     * This String is just a local copy of the characters allowed in text rendering of minecraft.
     */
//    private static final char[] allowedCharacters = ChatAllowedCharacters.allowedCharacters;

    /** The title string that is displayed in the top-center of the screen. */
    protected String screenTitle = "Edit sign message:";

    /** Reference to the sign object. */
    private TileEntityTombstone entitySign;

    /** Counts the number of screen updates. */
    private int updateCounter;

    /** The number of the line that is being edited. */
    private int editLine = 0;

    public GuiTombstone(TileEntityTombstone par1TileEntitySign) {
        this.entitySign = par1TileEntitySign;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @Override
    public void initGui() {
        this.buttonList.clear();
        Keyboard.enableRepeatEvents(true);
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120, "Done"));
        this.entitySign.setEditable(false);
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        PacketTileText packet = new PacketTileText().setPacketData(entitySign.xCoord, entitySign.yCoord,
                entitySign.zCoord, entitySign.signText);
        ProjectZulu_Core.getPipeline().sendToServer(packet);
        entitySign.setEditable(true);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen() {
        ++this.updateCounter;
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {
        if (par1GuiButton.enabled) {
            if (par1GuiButton.id == 0) {
                entitySign.markDirty();
                mc.displayGuiScreen((GuiScreen) null);
            }
        }
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    @Override
    protected void keyTyped(char keyChar, int keyID) {
        if (keyID == Keyboard.KEY_UP) {
            editLine = editLine - 1 >= 0 ? editLine - 1 : entitySign.signText.length - 1;
        }

        if (keyID == Keyboard.KEY_DOWN || keyID == Keyboard.KEY_RETURN) {
            editLine = editLine + 1 < entitySign.signText.length ? editLine + 1 : 0;
        }

        if (keyID == Keyboard.KEY_BACK && entitySign.signText[editLine].length() > 0) {
            entitySign.signText[editLine] = entitySign.signText[editLine].substring(0,
                    entitySign.signText[editLine].length() - 1);
        }

        if (ChatAllowedCharacters.isAllowedCharacter(keyChar) && entitySign.signText[editLine].length() < entitySign.maxcharPerLine) {
            entitySign.signText[editLine] = entitySign.signText[editLine] + keyChar;
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();

        drawCenteredString(fontRendererObj, screenTitle, width / 2, 40, 16777215);
        GL11.glPushMatrix();
        GL11.glTranslatef(width / 2, 0.0F, 50.0F);
        float var4 = 93.75F;
        GL11.glScalef(-var4, -var4, -var4);

        float var7 = 0.0F;
        GL11.glRotatef(var7, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0.0F, -1.0625F, 0.0F);

        if (updateCounter / 6 % 2 == 0) {
            entitySign.lineBeingEdited = editLine;
        }

        TileEntityRendererDispatcher.instance.renderTileEntityAt(entitySign, -0.5D, -0.75D, -0.5D, -1f);
        entitySign.lineBeingEdited = -1;
        GL11.glPopMatrix();
        super.drawScreen(par1, par2, par3);
    }
}
