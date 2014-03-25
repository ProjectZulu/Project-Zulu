package projectzulu.common.blocks.tombstone;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityTombstone extends TileEntity {
    /* List of Items this Tombstone will Empty Upon Right-Clicking */
    private ArrayList<ItemStack> deathItems = new ArrayList<ItemStack>();
    public int experience = 0;
    private EntityXPOrb xpOrb = null;

    public EntityXPOrb getEntityOrb() {
        if (hasDrops()) {
            if (xpOrb == null) {
                xpOrb = new EntityXPOrb(worldObj, xCoord, yCoord, zCoord, worldObj.rand.nextInt(5) + 1);
            }
            return xpOrb;
        }
        return null;
    }

    public boolean addDrop(ItemStack itemDrop) {
        return deathItems.add(itemDrop);
    }

    public boolean hasDrops() {
        return !deathItems.isEmpty() || experience > 0;
    }

    /* Give items in Tombstone to Player */
    public void giveItemsToPlayer(EntityPlayer player) {
        player.addExperience(experience);
        experience = 0;

        Iterator<ItemStack> unSortIterator = deathItems.iterator();
        boolean itemAdded = true;
        while (unSortIterator.hasNext() && itemAdded) {
            ItemStack deathItem = unSortIterator.next();
            itemAdded = player.inventory.addItemStackToInventory(deathItem);
            if (itemAdded) {
                unSortIterator.remove();
            }
        }
    }

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
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        for (int i = 0; i < signText.length; i++) {
            if (signText[i].length() > 0) {
                tagCompound.setString("Text" + (i + 1), signText[i]);
            }
        }
        tagCompound.setInteger("Experience", experience);
        addItemsToCompound(tagCompound, "DeathItems", deathItems);
    }

    private void addItemsToCompound(NBTTagCompound tileCompound, String itemTagName, List<ItemStack> items) {
        NBTTagList itemsTag = new NBTTagList();
        for (ItemStack itemStack : items) {
            NBTTagCompound tag = new NBTTagCompound();
            itemStack.writeToNBT(tag);
            itemsTag.appendTag(tag);
        }
        tileCompound.setTag(itemTagName, itemsTag);
    }

    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        this.isEditable = false;
        super.readFromNBT(tagCompound);
        for (int i = 0; i < signText.length; ++i) {
            this.signText[i] = tagCompound.getString("Text" + (i + 1));
            if (this.signText[i].length() > maxcharPerLine) {
                this.signText[i] = this.signText[i].substring(0, maxcharPerLine);
            }
        }
        experience = tagCompound.hasKey("Experience") ? tagCompound.getInteger("Experience") : 0;
        deathItems = readItemsFromCompound(tagCompound, "DeathItems");
    }

    public ArrayList<ItemStack> readItemsFromCompound(NBTTagCompound tileCompound, String itemTagName) {
        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        if (tileCompound.hasKey(itemTagName)) {
            NBTTagList itemsTag = (NBTTagList) tileCompound.getTag(itemTagName);
            for (int i = 0; i < itemsTag.tagCount(); i++) {
                ItemStack itemStack = ItemStack.loadItemStackFromNBT((NBTTagCompound) itemsTag.getCompoundTagAt(i));
                if (itemStack != null) {
                    items.add(itemStack);
                }
            }
        }
        return items;
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
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound tag = pkt.func_148857_g();
        readFromNBT(tag);
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
            StringBuilder currentLineText = new StringBuilder(maxcharPerLine);
            /* Add Words until Line is full */
            for (int i = startWord; i < words.length; i++) {
                String currentWord = getFilteredWord(words[i]);

                if (currentLineText.length() + (currentLineText.length() != 0 ? 1 : 0) + currentWord.length() <= maxcharPerLine) {
                    if (currentLineText.length() != 0) {
                        currentLineText.append(" ");
                    }
                    currentLineText.append(currentWord);
                    /* If This is the last line, we want to write the text */
                    if (i + 1 >= words.length) {
                        signText[currentLine] = getFilteredWord(currentLineText.toString());
                        currentLineText = new StringBuilder(maxcharPerLine);
                        startWord = i + 1;
                        break;
                    }
                } else {
                    signText[currentLine] = getFilteredWord(currentLineText.toString());
                    currentLineText = new StringBuilder(maxcharPerLine);
                    startWord = i;
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

    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 16384.0D;
    }
}
