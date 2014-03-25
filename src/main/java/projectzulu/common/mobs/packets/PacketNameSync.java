package projectzulu.common.mobs.packets;

import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import projectzulu.common.dungeon.packets.PacketByteStream;
import projectzulu.common.mobs.entity.EntityGenericTameable;

/**
 * Sync entity Name to server from GUI
 */
public class PacketNameSync extends PacketByteStream {
    private int entityIdToBeNamed;
    private String entityName;

    public PacketNameSync setPacketData(int entityIdToBeNamed, String entityName) {
        this.entityIdToBeNamed = entityIdToBeNamed;
        this.entityName = entityName;
        return this;
    }

    @Override
    protected void writeData(ChannelHandlerContext ctx, ByteBufOutputStream buffer) throws IOException {
        buffer.writeInt(entityIdToBeNamed);
        buffer.writeInt(entityName.length());
        buffer.writeChars(entityName);
    }

    @Override
    protected void readData(ChannelHandlerContext ctx, ByteBufInputStream buffer) throws IOException {
        entityIdToBeNamed = buffer.readInt();
        int nameLength = buffer.readInt();
        char[] nameChars = new char[nameLength];
        for (int i = 0; i < nameLength; i++) {
            nameChars[i] = buffer.readChar();
        }
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
    }

    @Override
    public void handleServerSide(EntityPlayer player) {
        Entity entity = player.worldObj.getEntityByID(entityIdToBeNamed);
        if (entity != null && entity instanceof EntityGenericTameable) {
            ((EntityGenericTameable) entity).setUsername(entityName);
        }
    }
}
