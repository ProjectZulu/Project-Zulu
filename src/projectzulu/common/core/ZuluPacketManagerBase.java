package projectzulu.common.core;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.Player;

/**
 * All Packets Sent Over Channel_Zulu are assumed to be of This Type
 */
public abstract class ZuluPacketManagerBase{
	
	int packetID;
	String channel = DefaultProps.defaultChannel;
	public ZuluPacketManagerBase(int packetID) {
		this.packetID = packetID;
	}
	
	/** Creates Packet with data from writePacketData
	 * See Individual implementations to ensure that individual packetData has been loaded into the Packet
	  */
	public Packet createPacket(){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(bytes);
        
        try {
            /* Write PacketID into Packet */
        	data.writeInt(packetID);
        	
            /* Write Custom Data into Packet */
        	writePacketData(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = channel;
        packet.data = bytes.toByteArray();
        packet.length = packet.data.length;
        return packet;
	}
	
	/* Write Custom Data into Packet */
	protected abstract void writePacketData(DataOutputStream dataStream) throws IOException;

	
	/* Processing of Packet, Return True if Succesful*/
	public abstract boolean processPacket(DataInputStream dataStream, Player player);
}
