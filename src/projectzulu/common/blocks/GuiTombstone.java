package projectzulu.common.blocks;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ChatAllowedCharacters;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiTombstone extends GuiScreen
{
    /**
     * This String is just a local copy of the characters allowed in text rendering of minecraft.
     */
    private static final String allowedCharacters = ChatAllowedCharacters.allowedCharacters;

    /** The title string that is displayed in the top-center of the screen. */
    protected String screenTitle = "Edit sign message:";

    /** Reference to the sign object. */
    private TileEntityTombstone entitySign;

    /** Counts the number of screen updates. */
    private int updateCounter;

    /** The number of the line that is being edited. */
    private int editLine = 0;

    public GuiTombstone(TileEntityTombstone par1TileEntitySign){
        this.entitySign = par1TileEntitySign;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @Override
    public void initGui(){
        this.buttonList.clear();
        Keyboard.enableRepeatEvents(true);
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120, "Done"));
        this.entitySign.setEditable(false);
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    @Override
    public void onGuiClosed(){
        Keyboard.enableRepeatEvents(false);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);
        
        /* Write PacketID into Packet */
        try {
        	data.writeInt(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        /* Write Temperature Into Packet*/
        try {
        	data.writeInt(this.entitySign.xCoord);
        	data.writeInt(this.entitySign.yCoord);
        	data.writeInt(this.entitySign.zCoord);
        	data.writeUTF(this.entitySign.signText[0]);
        	data.writeUTF(this.entitySign.signText[1]);
        	data.writeUTF(this.entitySign.signText[2]);
        	data.writeUTF(this.entitySign.signText[3]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "Channel_Zulu"; // CHANNEL MAX 16 CHARS
        packet.data = bytes.toByteArray();
        packet.length = packet.data.length;
		PacketDispatcher.sendPacketToServer(packet);		
        this.entitySign.setEditable(true);
    }

    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen(){
        ++this.updateCounter;
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    @Override
    protected void actionPerformed(GuiButton par1GuiButton){
        if (par1GuiButton.enabled)
        {
            if (par1GuiButton.id == 0)
            {
                this.entitySign.onInventoryChanged();
                this.mc.displayGuiScreen((GuiScreen)null);
            }
        }
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    @Override
    protected void keyTyped(char keyChar, int keyID){
        if (keyID == Keyboard.KEY_UP){
            this.editLine = this.editLine - 1 & 3;
        }

        if (keyID == Keyboard.KEY_DOWN || keyID == Keyboard.KEY_RETURN){
            this.editLine = this.editLine + 1 & 3;
        }

        if (keyID == Keyboard.KEY_BACK && this.entitySign.signText[this.editLine].length() > 0){
            this.entitySign.signText[this.editLine] = this.entitySign.signText[this.editLine].substring(0, this.entitySign.signText[this.editLine].length() - 1);
        }

        if (allowedCharacters.indexOf(keyChar) >= 0 && this.entitySign.signText[this.editLine].length() < 10){
            this.entitySign.signText[this.editLine] = this.entitySign.signText[this.editLine] + keyChar;
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int par1, int par2, float par3){
        this.drawDefaultBackground();
        
        this.drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 40, 16777215);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(this.width / 2), 0.0F, 50.0F);
        float var4 = 93.75F;
        GL11.glScalef(-var4, -var4, -var4);
        
        float var7 = 0.0F;
        GL11.glRotatef(var7, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0.0F, -1.0625F, 0.0F);
        
        if (this.updateCounter / 6 % 2 == 0){
            this.entitySign.lineBeingEdited = this.editLine;
        }
        
        TileEntityRenderer.instance.renderTileEntityAt(this.entitySign, -0.5D, -0.75D, -0.5D, -1f);
        this.entitySign.lineBeingEdited = -1;
        GL11.glPopMatrix();
        super.drawScreen(par1, par2, par3);
    }
}

