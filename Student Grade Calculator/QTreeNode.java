public class QTreeNode {
    // Instance variables
    private int x, y, size, color;
    private QTreeNode parent;
    private QTreeNode[] children;

    // Default constructor initializes the node with default values.
    public QTreeNode() {
        this.parent = null;
        this.children = new QTreeNode[4]; // Four children for a quadtree, initially null.
        this.x = this.y = this.size = this.color = 0; // Default values for coordinates, size, and color.
    }

    // Constructor for creating a node with specified properties.
    public QTreeNode(QTreeNode[] theChildren, int xcoord, int ycoord, int theSize, int theColor) {
        this.children = theChildren; 
        this.x = xcoord; 
        this.y = ycoord; 
        this.size = theSize;
        this.color = theColor;
        this.parent = null; 
    }

    // Method to determine if a point (xcoord, ycoord) is within the bounds of this node
    public boolean contains(int xcoord, int ycoord) {
        return xcoord >= x && xcoord < x + size && ycoord >= y && ycoord < y + size;
    }

    // Getter methods for node properties

    public int getx() {
         return x; 
    } // Returns the x-coordinate of the node

    public int gety() { 
        return y; 
    } // Returns the y-coordinate of the node

    public int getSize() { 
        return size;
    } // Returns the size of the node

    public int getColor() {
         return color;
    } // Returns the color value of the node

    public QTreeNode getParent() {
        return parent; 
    } // Returns the parent node

    // Method to get a child node by index
    public QTreeNode getChild(int index) throws QTreeException {
        if (children == null || index < 0 || index >= children.length) {
            throw new QTreeException("Invalid child index.");
        }
        return children[index];
    }

    // Setter methods for node properties
    public void setx(int newx) { 
        this.x = newx; 
    } // Updates the x-coordinate

    public void sety(int newy) { 
        this.y = newy; 
    } // Updates the y-coordinate

    public void setSize(int newSize) {
         this.size = newSize; 
    } // Sets the size of the node

    public void setColor(int newColor) { 
        this.color = newColor; 
    } // Sets the color value of the node

    public void setParent(QTreeNode newParent) { 
        this.parent = newParent; 
    } // Sets the parent of the current node

    // Sets a child node at a given index and updates the parent reference in the child
    public void setChild(QTreeNode newChild, int index) {
        if (index < 0 || index >= children.length) {
            throw new QTreeException("Invalid child index.");
        }
        children[index] = newChild;
        if (newChild != null) newChild.setParent(this);
    }

    // Method to check if the node is a leaf
    public boolean isLeaf() {
        if (children == null) return true;
        for (QTreeNode child : children) {
            if (child != null) return false;
        }
        return true;
    }
}
