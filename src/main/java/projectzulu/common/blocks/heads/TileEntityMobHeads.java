package projectzulu.common.blocks.heads;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityMobHeads extends TileEntity {

    /* Passed in From Item Damage */
    private int skullType;
    private int rotation;
    private String ExtraType = "";

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByte("SkullType", (byte) (this.skullType & 255));
        par1NBTTagCompound.setByte("Rot", (byte) (this.rotation & 255));
        par1NBTTagCompound.setString("ExtraType", this.ExtraType);

    }

    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        skullType = par1NBTTagCompound.getByte("SkullType");
        rotation = par1NBTTagCompound.getByte("Rot");

        if (par1NBTTagCompound.hasKey("ExtraType")) {
            this.ExtraType = par1NBTTagCompound.getString("ExtraType");
        }
    }

    /**
     * Overriden in a sign to provide the text.
     */
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound var1 = new NBTTagCompound();
        this.writeToNBT(var1);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 4, var1);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        NBTTagCompound tag = packet.func_148857_g();
        skullType = tag.getByte("SkullType");
        rotation = tag.getByte("Rot");
    }

    public void setSkullType(int par1, String par2Str) {
        skullType = par1;
        ExtraType = par2Str;
    }

    public int getSkullType() {
        return skullType;
    }

    public void setRotation(int par1) {
        rotation = par1;
    }

    @SideOnly(Side.CLIENT)
    public int getRotation() {
        return rotation;
    }

    @SideOnly(Side.CLIENT)
    public String getExtraType() {
        return ExtraType;
    }
}
