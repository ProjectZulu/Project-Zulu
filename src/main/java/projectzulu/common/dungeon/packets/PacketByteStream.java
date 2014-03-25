package projectzulu.common.dungeon.packets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import projectzulu.common.core.PZPacket;
import projectzulu.common.core.ProjectZuluLog;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

/**
 * Wraps ByteBuf into ByteStreams for additional read/write features such as writeString
 */
public abstract class PacketByteStream implements PZPacket {

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        ByteBufOutputStream data = new ByteBufOutputStream(buffer);
        try {
            writeData(ctx, data);
        } catch (Exception e) {
            ProjectZuluLog.severe("Error writing packet %s to ByteBufOutputStream", this);
            e.printStackTrace();
        }
    }

    protected abstract void writeData(ChannelHandlerContext ctx, ByteBufOutputStream buffer) throws IOException;

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        ByteBufInputStream byteStream = new ByteBufInputStream(buffer);
        try {
            readData(ctx, byteStream);
        } catch (Exception e) {
            ProjectZuluLog.severe("Error reading packet %s from ByteBufInputStream", this);
            e.printStackTrace();
        }
    }

    protected abstract void readData(ChannelHandlerContext ctx, ByteBufInputStream buffer) throws IOException;
}
