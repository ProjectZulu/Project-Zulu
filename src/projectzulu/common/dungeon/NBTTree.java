package projectzulu.common.dungeon;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class NBTTree {
	private NBTNode root;

	public NBTTree(NBTBase rootData) {
		root = new NBTNode(rootData, null);		
	}
	
	public boolean addNode(NBTBase data, NBTNode parent){
		return parent.addChild(data);
	}
	
	public void removeChildNode(NBTNode nodeToRemove, NBTNode parentNode){
		parentNode.removeChild(nodeToRemove);
	}
	
	public NBTTagCompound toNBTTagCompound(){
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		root.writeNodeandChildrenToNBT(nbtTagCompound);
		return (NBTTagCompound)nbtTagCompound.getTag("");
	}
	
}
