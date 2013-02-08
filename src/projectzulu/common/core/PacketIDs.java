package projectzulu.common.core;

import java.util.EnumSet;
import java.util.HashMap;

import projectzulu.common.dungeon.packets.PacketManagerMobSpawner;
import projectzulu.common.mobs.packets.PacketManagerAnimTime;
import projectzulu.common.mobs.packets.PacketManagerFollowerMasterData;
import projectzulu.common.packets.core.PacketManagerPlaySound;


public enum PacketIDs {
	/* PacketID: Unknown Packet, send a Warning */
	unknown(0) {
		@Override
		public PacketManager createPacketManager() {
			return null;
		}
	},
	/*PacketID: Temperature Packet*/
	temperature(1) {
		@Override
		public PacketManager createPacketManager() {
			return null;
		}
	},
	/* PacketID: Update Tile Entity Text [C->S] */
	tileEntityText(2) {
		@Override
		public PacketManager createPacketManager() {
			return null;
		}
	},
	/* Packet: Sync Centipede Followers --> Master */
	followerMasterData(3) {
		@Override
		public PacketManager createPacketManager() {
			return new PacketManagerFollowerMasterData(index);
		}
	},
	/* Packet: Sync Entity Name to Server from GUI */
	entityNameSync(4) {
		@Override
		public PacketManager createPacketManager() {
			return null;
		}
	},
	/* Packet: Perform EntityGenericTameable Taming Effect */
	tameParticleEffect(5) {
		@Override
		public PacketManager createPacketManager() {
			return null;
		}
	},
	/* Packet: Sync Entity Animation Time */
	animTime(6) {
		@Override
		public PacketManager createPacketManager() {
			return  new PacketManagerAnimTime(index);
		}
	},
	/* Packet: Sync Mob Spawner Settings From Client to Server */
	mobSpawner(7) {
		@Override
		public PacketManager createPacketManager() {
			return new PacketManagerMobSpawner(index);
		}
	},
	/* Packet: Sync Sound to Play from Server to Client */
	playSound(8) {
		@Override
		public PacketManager createPacketManager() {
			return new PacketManagerPlaySound(index);
		}
	};

	public final int index;
	public int index() { return index; }
	private static final HashMap<Integer, PacketIDs> lookupEnum = new HashMap<Integer, PacketIDs>();
	static {
        for(PacketIDs packetID : EnumSet.allOf(PacketIDs.class))
        	lookupEnum.put(packetID.index, packetID);
    }
	PacketIDs(int index) {
		this.index = index;
	}
	
	/* Return unknown if State Cannot be Found  */
	public static PacketIDs getPacketIDbyIndex(int index){		
		PacketIDs value = lookupEnum.get(index);
		if(value != null){
			return value;
		}else{
			return unknown;
		}
	}
	
	public abstract PacketManager createPacketManager();
}
