package projectzulu.common.mobs.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import projectzulu.common.blocks.TileEntityLimitedMobSpawner;
import projectzulu.common.core.ZuluPacketManagerBase;
import cpw.mods.fml.common.network.Player;

public class PacketManagerMobSpawner extends ZuluPacketManagerBase{
	int entityIDtoSync;
	int animTime;
	
	private int posX;
	private int posY;
	private int posZ;
	
	private NBTTagCompound customData;
		
	public PacketManagerMobSpawner(int packetID) {
		super(packetID);
	}

	public void setPacketData(int xPos, int yPos, int zPos, NBTTagCompound customData ){
		this.posX = xPos;
		this.posY = yPos;
		this.posZ = zPos;
		this.customData = customData;
	}
	
	@Override
	protected void writePacketData(DataOutputStream dataStream) throws IOException {
		dataStream.writeInt(posX);
		dataStream.writeInt(posY);
		dataStream.writeInt(posZ);
		writeNBTTagCompound(customData, dataStream);
	}

	@Override
	public boolean processPacket(DataInputStream dataStream, Player player) {
		World worldObj = ((EntityPlayer)player).worldObj;
		try{
			int packetID = dataStream.readInt();
			this.posX = dataStream.readInt();
			this.posY = dataStream.readInt();
			this.posZ = dataStream.readInt();
			customData = readNBTTagCompound(dataStream);
			
			TileEntity tileEntity = worldObj.getBlockTileEntity(posX, posY, posZ);
			if(tileEntity != null && tileEntity instanceof TileEntityLimitedMobSpawner){
				tileEntity.readFromNBT(customData);
				((TileEntityLimitedMobSpawner)tileEntity).forceUpdate();
			}else{
				return false;
			}
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
}
