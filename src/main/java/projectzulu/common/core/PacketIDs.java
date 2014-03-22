//package projectzulu.common.core;
//
//import java.util.EnumSet;
//import java.util.HashMap;
//
//import projectzulu.common.core.packets.PacketPlaySound;
//import projectzulu.common.core.packets.PacketStreamSound;
//import projectzulu.common.dungeon.packets.PacketMobSpawner;
//import projectzulu.common.mobs.packets.PacketAnimTime;
//import projectzulu.common.mobs.packets.PacketFollowerMasterData;
//
//public enum PacketIDs {
//    /* PacketID: Unknown Packet, send a Warning */
//    unknown(0) {
//        @Override
//        public PacketManager createPacketManager() {
//            return null;
//        }
//    },
//    /* PacketID: Temperature Packet */
//    temperature(1) {
//        @Override
//        public PacketManager createPacketManager() {
//            return null;
//        }
//    },
//    /* PacketID: Update Tile Entity Text [C->S] */
//    tileEntityText(2) {
//        @Override
//        public PacketManager createPacketManager() {
//            return null;
//        }
//    },
//    /* Packet: Sync Centipede Followers --> Master */
//    followerMasterData(3) {
//        @Override
//        public PacketFollowerMasterData createPacketManager() {
//            return new PacketFollowerMasterData(index);
//        }
//    },
//    /* Packet: Sync Entity Name to Server from GUI */
//    entityNameSync(4) {
//        @Override
//        public PacketManager createPacketManager() {
//            return null;
//        }
//    },
//    /* Packet: Perform EntityGenericTameable Taming Effect */
//    tameParticleEffect(5) {
//        @Override
//        public PacketManager createPacketManager() {
//            return null;
//        }
//    },
//    /* Packet: Sync Entity Animation Time */
//    animTime(6) {
//        @Override
//        public PacketAnimTime createPacketManager() {
//            return new PacketAnimTime(index);
//        }
//    },
//    /* Packet: Sync Mob Spawner Settings From Client to Server */
//    mobSpawner(7) {
//        @Override
//        public PacketMobSpawner createPacketManager() {
//            return new PacketMobSpawner(index);
//        }
//    },
//    /* Packet: Sync Sound to Play from Server to Client */
//    playSound(8) {
//        @Override
//        public PacketPlaySound createPacketManager() {
//            return new PacketPlaySound(index);
//        }
//    },
//    /* Packet: Sync Sound to Play from Server to Client */
//    streamSound(9) {
//        @Override
//        public PacketStreamSound createPacketManager() {
//            return new PacketStreamSound(index);
//        }
//    };
//
//    public final int index;
//
//    public int index() {
//        return index;
//    }
//
//    private static final HashMap<Integer, PacketIDs> lookupEnum = new HashMap<Integer, PacketIDs>();
//    static {
//        for (PacketIDs packetID : EnumSet.allOf(PacketIDs.class))
//            lookupEnum.put(packetID.index, packetID);
//    }
//
//    PacketIDs(int index) {
//        this.index = index;
//    }
//
//    /* Return unknown if State Cannot be Found */
//    public static PacketIDs getPacketIDbyIndex(int index) {
//        PacketIDs value = lookupEnum.get(index);
//        if (value != null) {
//            return value;
//        } else {
//            return unknown;
//        }
//    }
//
//    public abstract <T extends PacketManager> T createPacketManager();
//}
