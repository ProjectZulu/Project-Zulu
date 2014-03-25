package projectzulu.common.dungeon.packets;

import ibxm.Player;
import io.netty.channel.ChannelHandlerContext;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import projectzulu.common.core.PZPacket;
import projectzulu.common.dungeon.TileEntityLimitedMobSpawner;

public class PacketMobSpawner extends PacketDataStream {
    int entityIDtoSync;
    private int posX;
    private int posY;
    private int posZ;
    private NBTTagCompound customData;

    public PacketMobSpawner setPacketData(int xPos, int yPos, int zPos, NBTTagCompound customData) {
        this.posX = xPos;
        this.posY = yPos;
        this.posZ = zPos;
        this.customData = customData;
        return this;
    }

    @Override
    protected void writeData(ChannelHandlerContext ctx, DataOutputStream buffer) throws IOException {
        buffer.writeInt(posX);
        buffer.writeInt(posY);
        buffer.writeInt(posZ);
        writeNBTTagCompound(customData, buffer);
    }

    @Override
    protected void readData(ChannelHandlerContext ctx, DataInputStream buffer) throws IOException {
        posX = buffer.readInt();
        posY = buffer.readInt();
        posZ = buffer.readInt();
        customData = readNBTTagCompound(buffer);
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
        World world = player.getEntityWorld();
        TileEntity tileEntity = world.getTileEntity(posX, posY, posZ);
        if (tileEntity != null && tileEntity instanceof TileEntityLimitedMobSpawner) {
            tileEntity.readFromNBT(customData);
            ((TileEntityLimitedMobSpawner) tileEntity).forceUpdate();
        }
    }

    @Override
    public void handleServerSide(EntityPlayer player) {
        World world = player.getEntityWorld();
        TileEntity tileEntity = world.getTileEntity(posX, posY, posZ);
        if (tileEntity != null && tileEntity instanceof TileEntityLimitedMobSpawner) {
            tileEntity.readFromNBT(customData);
            ((TileEntityLimitedMobSpawner) tileEntity).forceUpdate();
        }
    }
}
