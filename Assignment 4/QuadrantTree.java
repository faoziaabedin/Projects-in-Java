public class QuadrantTree {
    private QTreeNode root;

    public QuadrantTree(int[][] thePixels) {
        root = buildTree(thePixels, 0, 0, thePixels.length);
    }

    private QTreeNode buildTree(int[][] pixels, int x, int y, int size) {
        if (size == 1) {
            return new QTreeNode(null, x, y, size, pixels[y][x]);
        }

        int newSize = size / 2;
        QTreeNode[] children = new QTreeNode[4];

        // Recursively build child quadrants
        children[0] = buildTree(pixels, x, y, newSize);
        children[1] = buildTree(pixels, x + newSize, y, newSize);
        children[2] = buildTree(pixels, x, y + newSize, newSize);
        children[3] = buildTree(pixels, x + newSize, y + newSize, newSize);

        int avgtheColor = Gui.averageColor(pixels, x, y, size); 
        return new QTreeNode(children, x, y, size, avgtheColor);
    }

    public QTreeNode getRoot() {
        return root;
    }

    // Method to return a list of nodes at a specified theLevel
    public ListNode<QTreeNode> getPixels(QTreeNode r, int theLevel) {
        if (r == null || theLevel == 0 || r.isLeaf()) {
            return r == null ? null : new ListNode<>(r); // Return null if node is null, else return a new ListNode with the node
        }        
    
        // Recursively get pixels from all children and combine lists
        ListNode<QTreeNode> resList = null;
        ListNode<QTreeNode> lasNode = null;
    
        for (int i = 0; i < 4; i++) {
            ListNode<QTreeNode> childList = getPixels(r.getChild(i), theLevel - 1);
            if (childList != null) { // Check if the child list is not empty
                if (resList == null) {
                    resList = childList;
                    lasNode = resList;
                    // Traverse to the end of the resList to update lasNode to point to the last element
                    while (lasNode.getNext() != null) {
                        lasNode = lasNode.getNext();
                    }
                } else {
                    lasNode.setNext(childList);
                    // Update lasNode to the last node of the newly added childList
                    do {
                        lasNode = lasNode.getNext();
                    } while (lasNode.getNext() != null);
                }
            }
        }
        
    
        return resList;
    }
    

    // Method to find nodes matching a theColor at a specified theLevel
    public Duple findMatching(QTreeNode r, int theColor, int theLevel) {
        Duple answer = new Duple();
    
        if (r == null) {
            return answer; 
        }
        if (theLevel == 0 || r.isLeaf()) {
            if (Gui.similarColor(r.getColor(), theColor)) {
                answer.setFront(new ListNode<>(r));
                answer.setCount(1);
            }
            return answer;
        }
        
        ListNode<QTreeNode> lasNode = null;

        // Recursively search in all children and combine answers
        for (int i = 0; i < 4; i++) {
            Duple childanswer = findMatching(r.getChild(i), theColor, theLevel - 1);
            if (childanswer.getFront() != null) {
                // Append childanswer at the end of the answer list
                if (answer.getFront() == null) {
                    answer.setFront(childanswer.getFront());
                    lasNode = answer.getFront();
                } else {
                    if (lasNode == null) {
                        // Initialize lasNode if it hasn't been set yet
                        lasNode = answer.getFront();
                        while (lasNode.getNext() != null) {
                            lasNode = lasNode.getNext();
                        }
                    }
                    lasNode.setNext(childanswer.getFront());
                }
                // Move lasNode to the end of the list
                while (lasNode.getNext() != null) {
                    lasNode = lasNode.getNext();
                }
                // Update the count of matching nodes
                answer.setCount(answer.getCount() + childanswer.getCount());
            }
        }
        
        return answer;
    }
    

    // Method to find a node containing a point at a specified theLevel
    public QTreeNode findNode(QTreeNode r, int theLevel, int x, int y) {
        if (r == null) {
            return null; 
        }
        // check the bounds directly
        if (x < r.getx() || x >= r.getx() + r.getSize() || y < r.gety() || y >= r.gety() + r.getSize()) {
            return null; 
        }
        if (theLevel == 0 || r.isLeaf()) {
            return r; 
        }
        
        // Calculate the middle coordinates of the node
        int midX = r.getx() + r.getSize() / 2;
        int midY = r.gety() + r.getSize() / 2;
        
        // Determine the quadrant of the child node that may contain the point
        int index = 0;
        if (x >= midX) index += 1; 
        if (y >= midY) index += 2; 
        
        return findNode(r.getChild(index), theLevel - 1, x, y);
    }        
    
}
