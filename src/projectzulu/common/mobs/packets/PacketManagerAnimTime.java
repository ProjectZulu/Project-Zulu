package projectzulu.common.mobs.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import projectzulu.common.core.PacketManager;
import projectzulu.common.mobs.EntityGenericCreature;
import cpw.mods.fml.common.network.Player;

public class PacketManagerAnimTime extends PacketManager{
	
	int entityIDtoSync;
	int animTime;
	
	public PacketManagerAnimTime(int packetID) {
		super(packetID);
	}
	
	public void setPacketData(int entityIDtoSync, int animTime){
		this.entityIDtoSync = entityIDtoSync;
		this.animTime = animTime;
	}
	
	@Override
	protected void writePacketData(DataOutputStream dataStream) throws IOException {
		dataStream.writeInt(entityIDtoSync);
		dataStream.writeInt(animTime);
	}
	
	@Override
	public boolean processPacket(DataInputStream dataStream, Player player) {
		World worldObj = ((EntityPlayer)player).worldObj;
		try{
			int packetID = dataStream.readInt();
			int entityToSyncID = dataStream.readInt();
			int dataToSync = dataStream.readInt();
			Entity entity = worldObj.getEntityByID(entityToSyncID);
			if(entity != null && entity instanceof EntityGenericCreature){
				((EntityGenericCreature)entity).setAnimTime(dataToSync);
			}else{
				//TODO Print to Log Entity No longer Exists
				return false;
			}
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
}
