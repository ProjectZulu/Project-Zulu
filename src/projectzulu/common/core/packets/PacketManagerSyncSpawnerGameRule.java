package projectzulu.common.core.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import projectzulu.common.core.PacketManager;
import projectzulu.common.dungeon.TileEntityLimitedMobSpawner;
import cpw.mods.fml.common.network.Player;

public class PacketManagerSyncSpawnerGameRule extends PacketManager{	
	private boolean gameRuleResult;
	private int xPos;
	private int yPos;
	private int zPos;
	
	public PacketManagerSyncSpawnerGameRule(int packetID){
		super(packetID);
	}
	
	public void setPacketData(int xPos, int yPos, int zPos, boolean gameRuleResult){
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		this.gameRuleResult = gameRuleResult;
	}
	
	@Override
	protected void writePacketData(DataOutputStream dataStream) throws IOException{
		dataStream.writeInt(xPos);
		dataStream.writeInt(yPos);
		dataStream.writeInt(zPos);
		dataStream.writeBoolean(gameRuleResult);
	}
	
	@Override
	public boolean processPacket(DataInputStream dataStream, Player player){
		World worldObj = ((EntityPlayer)player).worldObj;
		try{
			int packetID = dataStream.readInt();
			setPacketData(dataStream.readInt(), dataStream.readInt(), dataStream.readInt(), dataStream.readBoolean());			
			TileEntity tileEntity = worldObj.getBlockTileEntity(xPos, yPos, zPos);
			if(tileEntity != null && tileEntity instanceof TileEntityLimitedMobSpawner){
				((TileEntityLimitedMobSpawner)tileEntity).debugMode = gameRuleResult;
			}
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
}
