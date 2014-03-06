package projectzulu.common.core.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import projectzulu.common.core.PZPacket;
import projectzulu.common.mobs.entity.EntityGenericTameable;

/**
 * Send Tame event to Client such that the EntityGenericTameable taming effect can be performed
 */
public class PacketTameParticle implements PZPacket {
    private int entityIdToTriggerEffect;
    private boolean tameingSuccess;

    public PacketTameParticle setPacketData(int entityIdToTriggerEffect, boolean tameingSuccess) {
        this.entityIdToTriggerEffect = entityIdToTriggerEffect;
        this.tameingSuccess = tameingSuccess;
        return this;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        buffer.writeInt(entityIdToTriggerEffect);
        buffer.writeBoolean(tameingSuccess);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        entityIdToTriggerEffect = buffer.readInt();
        tameingSuccess = buffer.readBoolean();
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
        Entity entity = player.worldObj.getEntityByID(entityIdToTriggerEffect);
        if (entity != null && entity instanceof EntityGenericTameable) {
            ((EntityGenericTameable) entity).playTameEffect(tameingSuccess);
        }
    }

    @Override
    public void handleServerSide(EntityPlayer player) {
    }
}
