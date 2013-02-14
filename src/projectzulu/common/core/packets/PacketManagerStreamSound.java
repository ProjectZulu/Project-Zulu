package projectzulu.common.core.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import projectzulu.common.core.PacketManager;
import cpw.mods.fml.common.network.Player;

public class PacketManagerStreamSound extends PacketManager {	
	private int posX;
	private int posY;
	private int posZ;
	
	private String sound;

	public PacketManagerStreamSound(int packetID){
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
			worldObj.playRecord(sound, posX, posY, posZ);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
}