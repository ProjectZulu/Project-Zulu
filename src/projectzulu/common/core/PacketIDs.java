package projectzulu.common.core;

import projectzulu.common.mobs.packets.PacketManagerAnimTime;
import projectzulu.common.mobs.packets.PacketManagerFollowerMasterData;
import projectzulu.common.mobs.packets.PacketManagerMobSpawner;
import projectzulu.common.mobs.packets.PacketManagerPlaySound;


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

	PacketIDs(int index) {
		this.index = index;
	}
	
	/* Return unknown if State Cannot be Found  */
	public static PacketIDs getPacketIDbyIndex(int index){		
		for (PacketIDs packetID : PacketIDs.values()) {
			if(packetID.index == index){
				return packetID;
			}
		}
		return unknown;
	}
	
	public abstract PacketManager createPacketManager();
}
