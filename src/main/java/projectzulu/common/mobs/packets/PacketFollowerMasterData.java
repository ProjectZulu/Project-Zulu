package projectzulu.common.mobs.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import projectzulu.common.core.PZPacket;
import projectzulu.common.mobs.entity.EntityFollower;
import projectzulu.common.mobs.entity.EntityMaster;

public class PacketFollowerMasterData implements PZPacket {

    int childEntityID;
    int masterEntityID;
    int followerIndex;

    public PacketFollowerMasterData setPacketData(int childEntityID, int masterEntityID, int followerIndex) {
        this.childEntityID = childEntityID;
        this.masterEntityID = masterEntityID;
        this.followerIndex = followerIndex;
        return this;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        buffer.writeInt(childEntityID);
        buffer.writeInt(masterEntityID);
        buffer.writeInt(followerIndex);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        childEntityID = buffer.readInt();
        masterEntityID = buffer.readInt();
        followerIndex = buffer.readInt();
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
        World worldObj = ((EntityPlayer) player).worldObj;
        Entity childEntity = worldObj.getEntityByID(childEntityID);
        Entity masterEntity = worldObj.getEntityByID(masterEntityID);
        if (followerIndex == -1 || masterEntityID == -1 || childEntity == null
                || !(childEntity instanceof EntityFollower) || masterEntity == null
                || !(masterEntity instanceof EntityMaster)) {
            return;
        }
        ((EntityFollower) childEntity).linkMasterWithFollower(masterEntityID, followerIndex);
    }

    @Override
    public void handleServerSide(EntityPlayer player) {
    }
}
