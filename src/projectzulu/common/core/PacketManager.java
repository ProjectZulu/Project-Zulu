package projectzulu.common.core;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.Player;

/**
 * All Packets Sent Over Channel_Zulu are assumed to be of This Type
 */
public abstract class PacketManager{
	
	int packetID;
	String channel = DefaultProps.defaultChannel;
	public PacketManager(int packetID) {
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
	
    /**
     * Reads a compressed NBTTagCompound from the InputStream
     */
    public static NBTTagCompound readNBTTagCompound(DataInputStream par0DataInputStream) throws IOException{
        short var1 = par0DataInputStream.readShort();

        if (var1 < 0){
            return null;
        }
        else{
            byte[] var2 = new byte[var1];
            par0DataInputStream.readFully(var2);
            return CompressedStreamTools.decompress(var2);
        }
    }

    /**
     * Writes a compressed NBTTagCompound to the OutputStream
     */
    protected static void writeNBTTagCompound(NBTTagCompound par0NBTTagCompound, DataOutputStream par1DataOutputStream) throws IOException{
        if (par0NBTTagCompound == null){
            par1DataOutputStream.writeShort(-1);
        }
        else{
            byte[] var2 = CompressedStreamTools.compress(par0NBTTagCompound);
            par1DataOutputStream.writeShort((short)var2.length);
            par1DataOutputStream.write(var2);
        }
    }
}
