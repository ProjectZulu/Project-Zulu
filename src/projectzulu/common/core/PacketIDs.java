package projectzulu.common.core;


public enum PacketIDs {
	/* PacketID: Unknown Packet, send a Warning */
	unknown(0),
	/*PacketID: Temperature Packet*/
	temperature(1),
	/* PacketID: Update Tile Entity Text [C->S] */
	tileEntityText(2),
	/* Packet: Sync Centipede Followers --> Master */
	followerMasterData(3),
	/* Packet: Sync Entity Name to Server from GUI */
	entityNameSync(4),
	/* Packet: Perform EntityGenericTameable Taming Effect */
	tameParticleEffect(5),
	/* Packet: Sync Entity Animation Time */
	animTime(6);

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

}
