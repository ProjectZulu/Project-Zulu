package projectzulu.common.dungeon;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Optional;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import projectzulu.common.core.ProjectZuluLog;

public class NBTNode {
    private NBTBase data;
    private NBTNode parent;
    private Optional<String> tagName;
    private List<NBTNode> children;

    public NBTNode(NBTBase data, NBTNode parent, String tagName) {
        this.data = data;
        this.parent = parent;
        this.children = NBTHelper.getByID(data.getId()).getChildTags(data, this);
        this.tagName = tagName != null ? Optional.of(tagName) : Optional.<String> absent();
    }

    public String getTagName() {
        return tagName.isPresent() ? tagName.get() : "";
    }

    public NBTBase getData() {
        return data;
    }

    public String getValue() {
        return NBTHelper.getByID(data.getId()).getValue(data);
    }

    public NBTBase createNBTFromString(String newValue) {
        NBTBase nbtBase = null;
        try {
            nbtBase = NBTHelper.getByID(data.getId()).getNBTFromString(data, newValue);
        } catch (NumberFormatException e) {
            ProjectZuluLog.warning("Rejecting NBTTag Value %s due to incorrect formatting", newValue);
        }
        return nbtBase;
    }

    public NBTNode getParent() {
        return parent;
    }

    public boolean replaceChild(NBTNode childNode, NBTNode newChild) {
        int index = children.indexOf(childNode);
        if (index > -1) {
            children.set(index, newChild);
            return true;
        }
        ProjectZuluLog.warning("Could not find Child %s with Parent %s ", childNode.getTagName(), getData());
        return false;
    }

    public List<NBTNode> getChildren() {
        return children;
    }

    public int countParents() {
        int numParents = 0;
        if (parent != null) {
            numParents++;
            numParents += parent.countParents();
        }
        return numParents;
    }

    public boolean addChild(NBTBase data, String tagName) {
        return children.add(new NBTNode(data, this, tagName));
    }

    public boolean removeChild(NBTNode nodeToRemove) {
        return children.remove(nodeToRemove);
    }

    public void writeNodeandChildrenToNBT(NBTTagCompound nbtTagCompound) {
        NBTHelper helper = NBTHelper.getByID(data.getId());
        helper.writeToNBT(nbtTagCompound, this);
    }

    public void writeNodeandChildrenToArrayList(ArrayList<NBTNode> nodeList) {
        nodeList.add(this);
        for (NBTNode child : getChildren()) {
            child.writeNodeandChildrenToArrayList(nodeList);
        }
    }

    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) {
            return true;
        }
        if (otherObj == null) {
            return false;
        }
        if (getClass() != otherObj.getClass()) {
            return false;
        }

        NBTNode otherNode = (NBTNode) otherObj;

        if (children == null) {
            if (otherNode.children != null) {
                return false;
            }
        } else if (!children.equals(otherNode.children)) {
            return false;
        }

        if (data == null) {
            if (otherNode.data != null) {
                return false;
            }
        } else if (!data.equals(otherNode.data)) {
            return false;
        }

        if (parent == null) {
            if (otherNode.parent != null) {
                return false;
            }
        } else if (!parent.equals(otherNode.parent)) {
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
