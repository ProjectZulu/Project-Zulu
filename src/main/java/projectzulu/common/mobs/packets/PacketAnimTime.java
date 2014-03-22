package projectzulu.common.mobs.packets;

import ibxm.Player;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import projectzulu.common.core.PZPacket;
import projectzulu.common.mobs.entity.EntityGenericCreature;

public class PacketAnimTime implements PZPacket {

    private int entityIDtoSync;
    private int animTime;

    public PacketAnimTime setPacketData(int entityIDtoSync, int animTime) {
        this.entityIDtoSync = entityIDtoSync;
        this.animTime = animTime;
        return this;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        buffer.writeInt(entityIDtoSync);
        buffer.writeInt(animTime);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        entityIDtoSync = buffer.readInt();
        animTime = buffer.readInt();
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
        World worldObj = ((EntityPlayer) player).worldObj;
        Entity entity = worldObj.getEntityByID(entityIDtoSync);
        if (entity != null && entity instanceof EntityGenericCreature) {
            ((EntityGenericCreature) entity).setAnimTime(animTime);
        } else {
            // TODO Print to Log Entity No longer Exists
        }
    }

    @Override
    public void handleServerSide(EntityPlayer player) {

    }
}
