package projectzulu.common.core.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import projectzulu.common.core.PacketManager;
import cpw.mods.fml.common.network.Player;

public class PacketManagerPlaySound extends PacketManager{
	private int posX;
	private int posY;
	private int posZ;
	
	private String sound;
	
	public PacketManagerPlaySound(int packetID){
		super(packetID);
	}

	public void setPacketData(int xPos, int yPos, int zPos, String sound ){
		this.posX = xPos;
		this.posY = yPos;
		this.posZ = zPos;
		this.sound = sound;
	}
	
	@Override
	protected void writePacketData(DataOutputStream dataStream) throws IOException{
		dataStream.writeInt(posX);
		dataStream.writeInt(posY);
		dataStream.writeInt(posZ);
		dataStream.writeUTF(sound);
	}

	@Override
	public boolean processPacket(DataInputStream dataStream, Player player){
		World worldObj = ((EntityPlayer)player).worldObj;
		try{
			int packetID = dataStream.readInt();
			setPacketData(dataStream.readInt(), dataStream.readInt(), dataStream.readInt(), dataStream.readUTF());
			worldObj.playSound(posX, posY, posZ, sound, 1.0f, 1.0f, false);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
}