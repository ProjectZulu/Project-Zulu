package projectzulu.common.dungeon.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import projectzulu.common.core.PZPacket;
import projectzulu.common.core.ProjectZuluLog;

/**
 * Packet uses exposes data writing/reading via ByteArrayOutputStream/DataInputStream
 * 
 * This allows using Minecraft methods to write traditional Minecraft objects, such as NBT data
 */
public abstract class PacketDataStream implements PZPacket {

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        DataOutputStream data = new DataOutputStream(byteArray);
        try {
            writeData(ctx, data);
        } catch (Exception e) {
            // TODO: log exception
        }
        byte[] bytes = byteArray.toByteArray();
        buffer.writeInt(bytes.length);
        buffer.writeBytes(bytes);
    }

    protected abstract void writeData(ChannelHandlerContext ctx, DataOutputStream buffer) throws IOException;

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        int byteLength = buffer.readInt();
        byte[] dataBytes = new byte[byteLength];
        buffer.readBytes(dataBytes);

        ByteArrayInputStream byteArray = new ByteArrayInputStream(dataBytes);
        DataInputStream data = new DataInputStream(byteArray);
        try {
            readData(ctx, data);
        } catch (Exception e) {
            // TODO: log exception
        }
    }

    protected abstract void readData(ChannelHandlerContext ctx, DataInputStream buffer) throws IOException;

    /**
     * Reads a compressed NBTTagCompound from the InputStream
     */
    protected static NBTTagCompound readNBTTagCompound(DataInputStream par0DataInputStream) throws IOException {
        short var1 = par0DataInputStream.readShort();

        if (var1 < 0) {
            return null;
        } else {
            byte[] var2 = new byte[var1];
            par0DataInputStream.readFully(var2);
            return CompressedStreamTools.decompress(var2);
        }
    }

    /**
     * Writes a compressed NBTTagCompound to the OutputStream
     */
    protected static void writeNBTTagCompound(NBTTagCompound par0NBTTagCompound, DataOutputStream par1DataOutputStream)
            throws IOException {
        if (par0NBTTagCompound == null) {
            par1DataOutputStream.writeShort(-1);
        } else {
            byte[] var2 = CompressedStreamTools.compress(par0NBTTagCompound);
            par1DataOutputStream.writeShort((short) var2.length);
            par1DataOutputStream.write(var2);
        }
    }
}
