package projectzulu.common.core;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import projectzulu.common.core.packets.PacketPlaySound;
import projectzulu.common.core.packets.PacketStreamSound;
import projectzulu.common.core.packets.PacketTameParticle;
import projectzulu.common.dungeon.packets.PacketMobSpawner;
import projectzulu.common.mobs.packets.PacketAnimTime;
import projectzulu.common.mobs.packets.PacketFollowerMasterData;
import projectzulu.common.mobs.packets.PacketNameSync;
import projectzulu.common.mobs.packets.PacketTileText;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Packet pipeline class. Directs all registered packet data to be handled by the packets themselves.
 * 
 * Based on PacketPipeline example code by @author sirgingalot and @author cpw
 */
@ChannelHandler.Sharable
public class PacketPipeline extends MessageToMessageCodec<FMLProxyPacket, PZPacket> {
    private final String CHANNEL;
    private EnumMap<Side, FMLEmbeddedChannel> channels;
    private ArrayList<Class<? extends PZPacket>> packets = new ArrayList<Class<? extends PZPacket>>();
    private boolean isInitialised;

    private class PacketRegister {
        private ArrayList<Class<? extends PZPacket>> packets;

        private PacketRegister(ArrayList<Class<? extends PZPacket>> packets) {
            this.packets = packets;
        }

        /**
         * Register your packet with the pipeline. Discriminators are automatically set.
         * 
         * @param clazz the class to register
         * 
         * @return whether registration was successful. Failure may occur if 256 packets have been registered or if the
         *         registry already contains this packet
         */
        public boolean registerPacket(Class<? extends PZPacket> clazz) {
            if (this.packets.size() > 256) {
                ProjectZuluLog.severe("Packet decoders are limited to 256. Cannot add %s", clazz.getClass());
                return false;
            }

            if (this.packets.contains(clazz)) {
                ProjectZuluLog.severe("Packet decoders %s is already registered and will be ignored.", clazz);
            }

            this.packets.add(clazz);
            return true;
        }
    }

    public PacketPipeline(String channel) {
        this.CHANNEL = channel;
    }

    /**
     * @param FMLInitializationEvent is not needed, but call MUST occur at FMLInitializationEvent for registration. Thus
     *            it is required to provide guidance
     */
    public void initialise(FMLInitializationEvent event) {
        if (!isInitialised) {
            isInitialised = true;
            this.channels = NetworkRegistry.INSTANCE.newChannel(CHANNEL, this);

            PacketRegister packetRegister = new PacketRegister(packets);
            packetRegister.registerPacket(PacketPlaySound.class);
            packetRegister.registerPacket(PacketStreamSound.class);
            packetRegister.registerPacket(PacketNameSync.class);
            packetRegister.registerPacket(PacketTileText.class);
            packetRegister.registerPacket(PacketMobSpawner.class);
            packetRegister.registerPacket(PacketAnimTime.class);
            packetRegister.registerPacket(PacketFollowerMasterData.class);
            packetRegister.registerPacket(PacketTameParticle.class);

            // TODO: 1) Add Packets Manually, in which case SORTING is ridiculous
            // or 2) post an event here to allow registering from anywhere and then perform SORTing to ensure order
            // or 3) Split into two methods initialize() postInit() where packets can be added after channel
            // registration but before sorting
            // 3 is clunky, 2 is great for modules but 1 is easier and simple to transition later
            Collections.sort(this.packets, new Comparator<Class<? extends PZPacket>>() {

                @Override
                public int compare(Class<? extends PZPacket> clazz1, Class<? extends PZPacket> clazz2) {
                    int com = String.CASE_INSENSITIVE_ORDER.compare(clazz1.getCanonicalName(),
                            clazz2.getCanonicalName());
                    if (com == 0) {
                        com = clazz1.getCanonicalName().compareTo(clazz2.getCanonicalName());
                    }

                    return com;
                }
            });
        }
    }

    // In line encoding of the packet, including discriminator setting
    @Override
    protected void encode(ChannelHandlerContext ctx, PZPacket msg, List<Object> out) throws Exception {
        ByteBuf buffer = Unpooled.buffer();
        Class<? extends PZPacket> clazz = msg.getClass();
        if (!this.packets.contains(msg.getClass())) {
            throw new NullPointerException("No Packet Registered for: " + msg.getClass().getCanonicalName());
        }

        byte discriminator = (byte) this.packets.indexOf(clazz);
        buffer.writeByte(discriminator);
        msg.encodeInto(ctx, buffer);
        FMLProxyPacket proxyPacket = new FMLProxyPacket(buffer.copy(), ctx.channel().attr(NetworkRegistry.FML_CHANNEL)
                .get());
        out.add(proxyPacket);
    }

    // In line decoding and handling of the packet
    @Override
    protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out) throws Exception {
        ByteBuf payload = msg.payload();
        byte discriminator = payload.readByte();
        Class<? extends PZPacket> clazz = this.packets.get(discriminator);
        if (clazz == null) {
            throw new NullPointerException("No packet registered for discriminator: " + discriminator);
        }

        PZPacket pkt = clazz.newInstance();
        pkt.decodeInto(ctx, payload.slice());

        EntityPlayer player;
        switch (FMLCommonHandler.instance().getEffectiveSide()) {
        case CLIENT:
            player = this.getClientPlayer();
            pkt.handleClientSide(player);
            break;

        case SERVER:
            INetHandler netHandler = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
            player = ((NetHandlerPlayServer) netHandler).playerEntity;
            pkt.handleServerSide(player);
            break;

        default:
        }

        out.add(pkt);
    }

    @SideOnly(Side.CLIENT)
    private EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    /**
     * Send this message to everyone.
     * <p/>
     * Adapted from CPW's code in cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
     * 
     * @param message The message to send
     */
    public void sendToAll(PZPacket message) {
        this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET)
                .set(FMLOutboundHandler.OutboundTarget.ALL);
        this.channels.get(Side.SERVER).writeAndFlush(message);
    }

    /**
     * Send this message to the specified player.
     * <p/>
     * Adapted from CPW's code in cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
     * 
     * @param message The message to send
     * @param player The player to send it to
     */
    public void sendTo(PZPacket message, EntityPlayerMP player) {
        this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET)
                .set(FMLOutboundHandler.OutboundTarget.PLAYER);
        this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
        this.channels.get(Side.SERVER).writeAndFlush(message);
    }

    /**
     * Send this message to everyone within a certain range of a point.
     * <p/>
     * Adapted from CPW's code in cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
     * 
     * @param message The message to send
     * @param point The {@link cpw.mods.fml.common.network.NetworkRegistry.TargetPoint} around which to send
     */
    public void sendToAllAround(PZPacket message, NetworkRegistry.TargetPoint point) {
        this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET)
                .set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
        this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
        this.channels.get(Side.SERVER).writeAndFlush(message);
    }

    /**
     * Send this message to everyone within the supplied dimension.
     * <p/>
     * Adapted from CPW's code in cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
     * 
     * @param message The message to send
     * @param dimensionId The dimension id to target
     */
    public void sendToDimension(PZPacket message, int dimensionId) {
        this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET)
                .set(FMLOutboundHandler.OutboundTarget.DIMENSION);
        this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(dimensionId);
        this.channels.get(Side.SERVER).writeAndFlush(message);
    }

    /**
     * Send this message to the server.
     * <p/>
     * Adapted from CPW's code in cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
     * 
     * @param message The message to send
     */
    public void sendToServer(PZPacket message) {
        this.channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET)
                .set(FMLOutboundHandler.OutboundTarget.TOSERVER);
        this.channels.get(Side.CLIENT).writeAndFlush(message);
    }
}