package projectzulu.common.blocks.tombstone;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityTombstone extends TileEntity {
    /** An array of four strings storing the lines of text on the sign. */
    public String[] signText;
    public final int maxLines = 7;
    public final int maxcharPerLine = 13;

    /**
     * The index of the line currently being edited. Only used on client side, but defined on both. Note this is only
     * really used when the > < are going to be visible.
     */
    public int lineBeingEdited = -1;
    private boolean isEditable = true;

    public TileEntityTombstone() {
        signText = new String[maxLines];
        for (int i = 0; i < signText.length; i++) {
            signText[i] = "";
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        for (int i = 0; i < signText.length; i++) {
            if (signText[i].length() > 0) {
                par1NBTTagCompound.setString("Text" + (i + 1), this.signText[i]);
            }
        }
    }

    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        this.isEditable = false;
        super.readFromNBT(par1NBTTagCompound);
        for (int i = 0; i < signText.length; ++i) {
            this.signText[i] = par1NBTTagCompound.getString("Text" + (i + 1));
            if (this.signText[i].length() > maxcharPerLine) {
                this.signText[i] = this.signText[i].substring(0, maxcharPerLine);
            }
        }
    }

    /**
     * Overriden in a sign to provide the text.
     */
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound var1 = new NBTTagCompound();
        this.writeToNBT(var1);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 4, var1);

    }

    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData packet) {
        NBTTagCompound tag = packet.customParam1;
        for (int i = 0; i < signText.length; ++i) {
            if (tag.hasKey("Text".concat(Integer.toString((i + 1))))) {
                signText[i] = tag.getString("Text".concat(Integer.toString((i + 1))));
                if (signText[i].length() > maxcharPerLine) {
                    signText[i] = signText[i].substring(0, maxcharPerLine);
                }
            }
        }
    }

    public boolean isEditable() {
        return this.isEditable;
    }

    @SideOnly(Side.CLIENT)
    /**
     * Sets the sign's isEditable flag to the specified parameter.
     */
    public void setEditable(boolean par1) {
        this.isEditable = par1;
    }

    public void setSignString(String inputString) {
        String[] words = inputString.split(" ");
        int startWord = 0;
        for (int currentLine = 0; currentLine < signText.length; currentLine++) {
            int curCharPerLine = 0;

            /* Add Words until Line is full */
            for (int i = startWord; i < words.length; i++) {
                String currentWord = getFilteredWord(words[i]);
                if (curCharPerLine + 1 + currentWord.length() <= maxcharPerLine) {
                    curCharPerLine += 1 + currentWord.length();
                } else {
                    signText[currentLine] = getFilteredWord(words[startWord]);
                    for (int j = startWord + 1; j < i; j++) {
                        String nextWord = getFilteredWord(words[j]);
                        signText[currentLine] = signText[currentLine].concat(" ").concat(nextWord);
                    }
                    startWord = i;
                    break;
                }

                /**
                 * If There are no More Words, write the last few words to Sign Note The absence of Size Check, signText
                 * truncates itself elsewhere so we don't worry about it here
                 */
                if (i + 1 >= words.length) {
                    signText[currentLine] = words[startWord];
                    for (int j = startWord + 1; j <= i; j++) {
                        signText[currentLine] = signText[currentLine].concat(" ").concat(getFilteredWord(words[j]));
                    }
                    startWord = i + 1;
                    break;
                }
            }
        }
    }
    
    /**
     * Handlers filtering word if it is invalid in some way, such as truncating a word that is too long.
     */
    private String getFilteredWord(String word) {
        return word.length() > maxcharPerLine ? word.substring(0, maxcharPerLine - 2).concat(".") : word;
    }
}
