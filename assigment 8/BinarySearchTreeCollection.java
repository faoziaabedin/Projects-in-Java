import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class BinarySearchTreeCollection {
    // Collection of BinarySearchTree objects
    private ArrayList<BinarySearchTree> trees;

    // Constructor to initialize the collection
    public BinarySearchTreeCollection() {
        this.trees = new ArrayList<>();
    }

    // Adds a new BinarySearchTree object to the collection
    public void addTree(BinarySearchTree tree) {
        trees.add(tree);
    }

    // Retrieves a specific tree from the collection given an index
    public BinarySearchTree getTree(int index) {
        if (index < 0 || index >= trees.size()) {
            throw new IllegalArgumentException("Invalid argument!");
        }
        return trees.get(index);
    }

    // Removes a specific tree from the collection given an index
    public void deleteTree(int index) {
        if (index < 0 || index >= trees.size()) {
            throw new IllegalArgumentException("Invalid argument!");
        }
        trees.remove(index);
    }

    // Returns the number of trees in the collection
    public int getNumberOfTrees() {
        return trees.size();
    }

    // Checks if the trees at the given indices are structurally equivalent
    public boolean Equivalent(int[] indices) {
        if (indices.length < 2) {
            throw new IllegalArgumentException("Invalid argument!");
        }

        // Validate indices
        for (int index : indices) {
            if (index < 0 || index >= trees.size()) {
                throw new IllegalArgumentException("Invalid argument!");
            }
        }

        // Reference tree to compare with others
        BinarySearchTree referenceTree = trees.get(indices[0]);
        for (int i = 1; i < indices.length; i++) {
            if (!Equivalent(referenceTree.root, trees.get(indices[i]).root)) {
                return false;
            }
        }

        return true;
    }

    // Helper method to check if two nodes are structurally equivalent
    private boolean Equivalent(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        return node1.value == node2.value &&
                Equivalent(node1.leftChild, node2.leftChild) &&
                Equivalent(node1.rightChild, node2.rightChild);
    }

    // Merges the trees at the given indices into one larger tree
    public void merge(int[] indices) {
        if (indices.length < 2) {
            throw new IllegalArgumentException("Invalid argument!");
        }

        // Validate indices
        for (int index : indices) {
            if (index < 0 || index >= trees.size()) {
                throw new IllegalArgumentException("Invalid argument!");
            }
        }

        ArrayList<Integer> values = new ArrayList<>();
        // Collect all values from the specified trees
        for (int index : indices) {
            values.addAll(trees.get(index).bfsTraversal());
        }

        // Sort and remove duplicate values
        Collections.sort(values);
        Set<Integer> uniqueValues = new LinkedHashSet<>(values);

        // Create a new tree with the combined values
        BinarySearchTree newTree = new BinarySearchTree();
        for (int value : uniqueValues) {
            newTree.addNode(value);
        }

        // Remove the original trees from the collection
        for (int i = indices.length - 1; i >= 0; i--) {
            deleteTree(indices[i]);
        }

        // Add the new merged tree to the collection
        addTree(newTree);
    }

    // Sums and returns the number of nodes in all trees within the collection
    public int getTotalNodes() {
        int totalNodes = 0;
        for (BinarySearchTree tree : trees) {
            totalNodes += tree.getNodeCount();
        }
        return totalNodes;
    }
}
