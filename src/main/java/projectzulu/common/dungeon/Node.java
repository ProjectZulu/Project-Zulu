package projectzulu.common.dungeon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import scala.annotation.meta.param;

public class Node implements Comparable<Node> {
    private Node parent;
    private String name;
    private List<Node> children = new ArrayList<Node>();

    public Node(Node parent, String data) {
        this.parent = parent;
        parseDataForNode(data);
        parseDataForChild(data);
    }

    public Node getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    /**
     * Return Node name Concated after the name of its Parent(s)
     * 
     * @return
     */
    public String getFullName() {
        return prefixParents(name);
    }

    private String prefixParents(String name) {
        if (parent != null && parent.parent != null && parent.parent.name.equals("root")) {
            return parent.prefixParents(parent.name + ":" + name);
        } else if (parent != null && !parent.name.equals("root")) {
            return parent.prefixParents(parent.name + "." + name);
        } else {
            return name;
        }
    }

    public boolean addChild(String data) {
        parseDataForChild(data);
        return true;
    }

    public Node getChild(int index) {
        return children.get(index);
    }

    public int numberOfChildren() {
        return children.size();
    }

    public void sortNodeTree() {
        Collections.sort(children);
        for (Node child : children) {
            child.sortNodeTree();
        }
    }

    /**
     * Return true if Name is Parsed from Data
     */
    private boolean parseDataForNode(String data) {
        String[] stringSplitData = data.split("\\.", 2);
        this.name = stringSplitData[0];
        return true;
    }

    /**
     * Return true if Child is added from Data
     */
    private boolean parseDataForChild(String data) {
        String[] stringSplitData = data.split("\\.", 2);
        if (stringSplitData.length < 2) {
            return false;
        }
        String[] stringSplitChild = stringSplitData[1].split("\\.", 2);

        String childName = stringSplitChild[0];
        for (Node child : children) {
            if (child.name.equals(childName)) {
                child.addChild(stringSplitData[1]);
                return true;
            }
        }
        children.add(new Node(this, stringSplitData[1]));
        return true;
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

        Node otherNode = (Node) otherObj;

        if (children == null) {
            if (otherNode.children != null) {
                return false;
            }
        } else if (!children.equals(otherNode.children)) {
            return false;
        }

        if (name == null) {
            if (otherNode.name != null) {
                return false;
            }
        } else if (!name.equals(otherNode.name)) {
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
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
        return result;
    }

    @Override
    public int compareTo(Node node) {
        if (node != null && node.children.size() > 0) {
            if (children.size() > 0) {
                return this.name.compareTo(node.name);
            } else {
                return +1;
            }
        } else if (this.children.size() > 0) {
            return -1;
        }
        return this.name.compareTo(node.name);
    }
}
