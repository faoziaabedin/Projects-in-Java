import java.util.ArrayList;
import java.util.Arrays;

public class BinarySearchTreeCollectionTests {
    public static void main(String[] args) {
        testAddTree();
        testGetTree();
        testDeleteTree();
        testMergeWithTwoTrees();
        testMergeWithThreeTrees();
        testAreStructurallyEquivalent();
        testGetTotalNodes();
        testMergeWithInvalidIndex();
    }

    public static void testAddTree() {
        BinarySearchTreeCollection bstc = new BinarySearchTreeCollection();
        BinarySearchTree bst = new BinarySearchTree();
        bst.addNode(10);
        bst.addNode(30);
        bst.addNode(7);
        bstc.addTree(bst);
        bstc.addTree(bst);
        if (bstc.getNumberOfTrees() == 2) {
            System.out.println("testAddTree passed");
        }
        else { System.out.println("testAddTree failed"); }
    }

    public static void testGetTree() {
        BinarySearchTreeCollection bstc = new BinarySearchTreeCollection();
        BinarySearchTree bst = new BinarySearchTree();
        bst.addNode(10);
        bst.addNode(30);
        bst.addNode(7);
        bstc.addTree(bst);
        if (bstc.getTree(0).root.value == 10) {
            System.out.println("testGetTree passed");
        }
        else {
            System.out.println("testGetTree failed");
        }
    }

    public static void testDeleteTree() {
        BinarySearchTreeCollection bstc = new BinarySearchTreeCollection();
        BinarySearchTree bst = new BinarySearchTree();
        bst.addNode(10);
        bstc.addTree(bst);
        bstc.addTree(bst);
        bstc.deleteTree(0);
        if (bstc.getNumberOfTrees() == 1) {
            System.out.println("testDeleteTree passed");
        }
        else { System.out.println("testDeleteTree failed"); }
    }

    public static void testMergeWithTwoTrees() {
        BinarySearchTree bst1 = new BinarySearchTree();
        bst1.addNode(10);
        bst1.addNode(5);
        bst1.addNode(15);

        BinarySearchTree bst2 = new BinarySearchTree();
        bst2.addNode(20);
        bst2.addNode(10);
        bst2.addNode(15);

        BinarySearchTreeCollection bstc = new BinarySearchTreeCollection();
        bstc.addTree(bst1);
        bstc.addTree(bst2);

        int[] indices = {0, 1};
        bstc.merge(indices);
        ArrayList<Integer> mergedResult = bstc.getTree(bstc.getNumberOfTrees() - 1).bfsTraversal();
        ArrayList<Integer> correctResult = new ArrayList<>(Arrays.asList(5, 10, 15, 20));
        if (mergedResult.equals(correctResult)) {
            System.out.println("testMergeWithTwoTrees passed");
        }
        else { System.out.println("testMergeWithTwoTrees failed"); }
    }

    public static void testMergeWithThreeTrees() {
        BinarySearchTree bst1 = new BinarySearchTree();
        bst1.addNode(10);
        bst1.addNode(5);
        bst1.addNode(15);

        BinarySearchTree bst2 = new BinarySearchTree();
        bst2.addNode(20);
        bst2.addNode(10);
        bst2.addNode(15);

        BinarySearchTree bst3 = new BinarySearchTree();
        bst3.addNode(100);
        bst3.addNode(1000);
        bst3.addNode(230);

        BinarySearchTreeCollection bstc = new BinarySearchTreeCollection();
        bstc.addTree(bst1);
        bstc.addTree(bst2);
        bstc.addTree(bst3);

        int[] indices = {0, 1, 2};
        bstc.merge(indices);
        ArrayList<Integer> mergedResult = bstc.getTree(bstc.getNumberOfTrees() - 1).bfsTraversal();
        ArrayList<Integer> correctResult = new ArrayList<>(Arrays.asList(5, 10, 15, 20, 100, 230, 1000));
        if (mergedResult.equals(correctResult)) {
            System.out.println("testMergeWithThreeTrees passed");
        }
        else { System.out.println("testMergeWithThreeTrees failed"); }
    }

    public static void testAreStructurallyEquivalent() {
        BinarySearchTree bst1 = new BinarySearchTree();
        bst1.addNode(10);
        bst1.addNode(5);
        bst1.addNode(15);

        BinarySearchTree bst2 = new BinarySearchTree();
        bst2.addNode(10);
        bst2.addNode(5);
        bst2.addNode(15);

        BinarySearchTree bst3 = new BinarySearchTree();
        bst3.addNode(10);
        bst3.addNode(5);
        bst3.addNode(15);

        BinarySearchTreeCollection bstc = new BinarySearchTreeCollection();
        bstc.addTree(bst1);
        bstc.addTree(bst2);
        bstc.addTree(bst3);

        int[] indices = {0, 1, 2};
        if (bstc.areStructurallyEquivalent(indices)) {
            System.out.println("testAreStructurallyEquivalent passed");
        }
        else { System.out.println("testAreStructurallyEquivalent failed"); }
    }

    public static void testGetTotalNodes() {
        BinarySearchTree bst1 = new BinarySearchTree();
        bst1.addNode(10);
        bst1.addNode(5);
        bst1.addNode(15);

        BinarySearchTree bst2 = new BinarySearchTree();
        bst2.addNode(20);
        bst2.addNode(10);
        bst2.addNode(15);

        BinarySearchTree bst3 = new BinarySearchTree();
        bst3.addNode(1);
        bst3.addNode(2);
        bst3.addNode(3);
        bst3.addNode(4);

        BinarySearchTreeCollection bstc = new BinarySearchTreeCollection();
        bstc.addTree(bst1);
        bstc.addTree(bst2);
        bstc.addTree(bst3);

        if(bstc.getTotalNodes() == 10) {
            System.out.println("testGetTotalNodes passed");
        }
        else { System.out.println("testGetTotalNodes failed"); }
    }

    public static void testMergeWithInvalidIndex() {
        boolean success = false;
        try {
            BinarySearchTreeCollection bstc = new BinarySearchTreeCollection();
            int[] indices = {0, 1};
            bstc.merge(indices);
        } catch (IllegalArgumentException e) {
            success = true;
            System.out.println("testMergeWithInvalidIndex passed");
        }
        if (!success) { System.out.println("testMergeWithInvalidIndex failed"); }
    }
}