package projectzulu.common.blocks;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityTombstone extends TileEntity
{
    /** An array of four strings storing the lines of text on the sign. */
    public String[] signText = new String[] {"", "", "", "",""};

    /**
     * The index of the line currently being edited. Only used on client side, but defined on both. Note this is only
     * really used when the > < are going to be visible.
     */
    public int lineBeingEdited = -1;
    private boolean isEditable = true;

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound){
    	
        super.writeToNBT(par1NBTTagCompound);
        for (int i = 0; i < 4; i++) {
            if(this.signText[i].length() > 0){
                par1NBTTagCompound.setString("Text"+(i+1), this.signText[i]);
            }
		}
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound){
        this.isEditable = false;
        super.readFromNBT(par1NBTTagCompound);
        for (int var2 = 0; var2 < 4; ++var2){
            this.signText[var2] = par1NBTTagCompound.getString("Text" + (var2 + 1));

            if (this.signText[var2].length() > 10){
                this.signText[var2] = this.signText[var2].substring(0, 10);
            }

        }
    }
    
    /**
     * Overriden in a sign to provide the text.
     */
    public Packet getDescriptionPacket(){
        NBTTagCompound var1 = new NBTTagCompound();
        this.writeToNBT(var1);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 4, var1);

    }
    
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData packet){
    	NBTTagCompound tag = packet.customParam1;		
    	for (int var2 = 0; var2 < 4; ++var2){
    		if(tag.hasKey("Text".concat(Integer.toString((var2 + 1))))){
    			this.signText[var2] = tag.getString("Text".concat(Integer.toString((var2 + 1))));    			
    			if (this.signText[var2].length() > 10){
    				this.signText[var2] = this.signText[var2].substring(0, 10);
    			}
    		}
    	}
    	
    }
    
    public boolean isEditable(){
        return this.isEditable;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Sets the sign's isEditable flag to the specified parameter.
     */
    public void setEditable(boolean par1){
        this.isEditable = par1;
    }
    
    public void setSignString(String inputString){
    	String[] words = inputString.split(" ");    	
    	int maxLine = 4;
		int maxcharPerLine = 10;
		int startWord = 0;
    	for (int currentLine = 0; currentLine < maxLine; currentLine++) {
    		int curCharPerLine = 0;
    		
    		/* Add Words until Line is full  */
    		for (int i = startWord; i < words.length; i++){
    			if(curCharPerLine + 1 + words[i].length() <= maxcharPerLine){
    				curCharPerLine += 1 + words[i].length();
    			}else{
    				signText[currentLine] = words[startWord];
    				for (int j = startWord + 1; j < i; j++){
    					signText[currentLine] = signText[currentLine].concat(" ").concat(words[j]);
    				}
    				startWord = i;
    				break;
    			}
    			
    			/**
    			 *  If There are no More Words, write the last few words to Sign 
    			 *  Note The absence of Size Check, signText truncates itself elsewhere so we don't worry about it here
    			 */
    			if(i + 1 >= words.length){
    				signText[currentLine] = words[startWord];
    				for (int j = startWord + 1; j <= i; j++){
    					signText[currentLine] = signText[currentLine].concat(" ").concat(words[j]);
    				}
    				startWord = i + 1;
    				break;
    			}
    		}
    	}
    }
}
