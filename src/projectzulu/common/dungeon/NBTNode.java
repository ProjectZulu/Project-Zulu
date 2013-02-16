package projectzulu.common.dungeon;

import java.util.List;

import projectzulu.common.core.ProjectZuluLog;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class NBTNode {
	private NBTBase data;
	private NBTNode parent;
	private List<NBTNode> children;
	
	public NBTNode(NBTBase data, NBTNode parent) {
		this.data = data;
		this.parent = parent;
		this.children = NBTHelper.getByID(data.getId()).getChildTags(data, this);
	}
	
	public NBTBase getData(){
		return data;
	}
	
	public NBTNode getParent(NBTNode child){
		return child.parent;
	}
	
	public List<NBTNode> getChildren() {
		return children;
	}
	
	public boolean addChild(NBTBase data){
		return children.add(new NBTNode(data, this));
	}
	
	public boolean removeChild(NBTNode nodeToRemove){
		return children.remove(nodeToRemove);
	}
	
	public void writeNodeandChildrenToNBT(NBTTagCompound nbtTagCompound){
		NBTHelper helper = NBTHelper.getByID(data.getId());
		helper.writeToNBT(nbtTagCompound, this);
	}
	
	@Override
	public boolean equals(Object otherObj) {
		if (this == otherObj)
			return true;
		if (otherObj == null)
			return false;
		if (getClass() != otherObj.getClass())
			return false;
		
		NBTNode otherNode = (NBTNode) otherObj;
		if (children == null && otherNode.children != null && !children.equals(otherNode.children)) {
			return false;
		}else if(data == null && otherNode.data != null && !data.equals(otherNode.data)){
			return false;
		}else if (parent == null && otherNode.parent != null && !parent.equals(otherNode.parent)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		return result;
	}	
}
