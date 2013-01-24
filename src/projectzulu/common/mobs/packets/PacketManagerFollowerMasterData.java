package projectzulu.common.mobs.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import projectzulu.common.core.ZuluPacketManagerBase;
import projectzulu.common.mobs.EntityFollower;
import projectzulu.common.mobs.EntityMaster;
import cpw.mods.fml.common.network.Player;

public class PacketManagerFollowerMasterData extends ZuluPacketManagerBase{
	
	int childEntityID;
	int masterEntityID;
	int followerIndex;
	
	public PacketManagerFollowerMasterData(int packetID) {
		super(packetID);
	}
	
	public void setPacketData(int childEntityID, int masterEntityID, int followerIndex){
		this.childEntityID = childEntityID;
		this.masterEntityID = masterEntityID;
		this.followerIndex = followerIndex;
	}
	
	@Override
	protected void writePacketData(DataOutputStream dataStream) throws IOException {
		dataStream.writeInt(childEntityID);
		dataStream.writeInt(masterEntityID);
		dataStream.writeInt(followerIndex);
	}

	@Override
	public boolean processPacket(DataInputStream dataStream, Player player) {

		World worldObj = ((EntityPlayer)player).worldObj;
		try{
			int packetID = dataStream.readInt();
			int childEntityID = dataStream.readInt();
			int masterEntityID = dataStream.readInt();
			int followerIndex = dataStream.readInt();

			Entity childEntity = worldObj.getEntityByID(childEntityID);
			Entity masterEntity = worldObj.getEntityByID(masterEntityID);
			if(followerIndex == -1 || masterEntityID == -1
					|| childEntity == null || !(childEntity instanceof EntityFollower) 
					|| masterEntity == null || !(masterEntity instanceof EntityMaster)){
				return false;
			}
			
			((EntityFollower) childEntity).linkMasterWithFollower(masterEntityID, followerIndex);
			
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
}
