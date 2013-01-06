package projectzulu.common.blocks;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import projectzulu.common.mobs.EntityGenericTameable;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAnimalName extends GuiScreen
{
	/**
	 * This String is just a local copy of the characters allowed in text rendering of minecraft.
	 */
	private static final String allowedCharacters = ChatAllowedCharacters.allowedCharacters;

	/** The title string that is displayed in the top-center of the screen. */
	protected String screenTitle = "Entity Name:";

	/** Counts the number of screen updates. */
	private int updateCounter;

	/** The number of the line that is being edited. */
	private int editLine = 0;

	World world;
	EntityPlayer player;
	/* ID of Entity to be Named */
	int entityID;
	public String entityName = "";

	public GuiAnimalName(World world, EntityPlayer player, int entityID){
		this.world = world;
		this.player = player;
		this.entityID = entityID;
		entityName = ((EntityGenericTameable)world.getEntityByID(entityID)).getUsername();
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	public void initGui(){
		this.controlList.clear();
		Keyboard.enableRepeatEvents(true);
		this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120, "Done"));
//		this.entitySign.setEditable(false);
	}

	/**
	 * Called when the screen is unloaded. Used to disable keyboard repeat events
	 */
	public void onGuiClosed(){
		Keyboard.enableRepeatEvents(false);
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(bytes);
		
		
		/* Write PacketID into Packet */
		try {
			data.writeInt(4);
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* Write Temperature Into Packet*/
		try {
			data.writeInt(entityID);
			data.writeUTF(entityName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "Channel_Zulu"; // CHANNEL MAX 16 CHARS
		packet.data = bytes.toByteArray();
		packet.length = packet.data.length;
		PacketDispatcher.sendPacketToServer(packet);
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
	protected void actionPerformed(GuiButton par1GuiButton){
		if (par1GuiButton.enabled){
			if (par1GuiButton.id == 0){
				this.mc.displayGuiScreen((GuiScreen)null);
			}
		}
	}

	/**
	 * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
	 */
	protected void keyTyped(char par1, int par2){
		if (par2 == 14 && this.entityName.length() > 0){
			this.entityName = this.entityName.substring(0, this.entityName.length() - 1);
		}

		if (allowedCharacters.indexOf(par1) >= 0 && this.entityName.length() < 10){
			this.entityName = this.entityName + par1;
		}
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int par1, int par2, float par3){
		this.drawDefaultBackground();

		this.drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 40, 16777215);
		this.drawCenteredString(this.fontRenderer, this.entityName, this.width / 2, 40+fontRenderer.FONT_HEIGHT, 16777215);
		
		super.drawScreen(par1, par2, par3);
	}
}
