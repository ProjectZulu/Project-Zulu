package projectzulu.common.dungeon;

import java.util.ArrayList;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class NBTTree {
    private NBTNode root;

    public NBTTree(NBTBase rootData, String rootName) {
        root = new NBTNode(rootData, null, rootName);
    }

    public NBTNode getRootNode() {
        return root;
    }

    public boolean addNode(NBTBase data, NBTNode parent, String tagName) {
        return parent.addChild(data, tagName);
    }

    public void removeChildNode(NBTNode nodeToRemove, NBTNode parentNode) {
        parentNode.removeChild(nodeToRemove);
    }

    public NBTTagCompound toNBTTagCompound() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        root.writeNodeandChildrenToNBT(nbtTagCompound);
        return (NBTTagCompound) nbtTagCompound.getTag("Properties");
    }

    public ArrayList<NBTNode> toArrayList() {
        ArrayList<NBTNode> nodeList = new ArrayList<NBTNode>();
        root.writeNodeandChildrenToArrayList(nodeList);
        return nodeList;
    }
}
