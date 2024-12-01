import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;

public class TestDoublyLinkedList {
    
    public static void main(String[] args) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("tests_results_doubly_linked_list.txt"));
        
        DoublyLinkedList list1, list2, list3, list4, list5;
        
        // Test 1 - add nodes and check contents
        list1 = new DoublyLinkedList();
        list1.add(10);
        list1.add(20);
        list1.add(30);
        if (getListContents(list1).equals("10 20 30 ")) {
            System.out.println("Test 1 Passed");
            writer.write("Test 1 Passed\n");
        } else {
            System.out.println("Test 1 Failed");
            writer.write("Test 1 Failed\n");
        }
        
        // Test 2 - remove a node and check contents
        list2 = new DoublyLinkedList();
        list2.add(10);
        list2.add(20);
        list2.add(30);
        list2.remove(20);
        if (getListContents(list2).equals("10 30 ")) {
            System.out.println("Test 2 Passed");
            writer.write("Test 2 Passed\n");
        } else {
            System.out.println("Test 2 Failed");
            writer.write("Test 2 Failed\n");
        }
        
        // Test 3 - remove a non-existing node
        list3 = new DoublyLinkedList();
        list3.add(10);
        list3.add(20);
        list3.add(30);
        if (!list3.remove(40)) {
            System.out.println("Test 3 Passed");
            writer.write("Test 3 Passed\n");
        } else {
            System.out.println("Test 3 Failed");
            writer.write("Test 3 Failed\n");
        }
        
        // Test 4 - reverse the list
        list4 = new DoublyLinkedList();
        list4.add(10);
        list4.add(20);
        list4.add(30);
        list4.reverse();
        if (getListContents(list4).equals("30 20 10 ") || getListContents(list4).equals("30 20 10")) {
            System.out.println("Test 4 Passed");
            writer.write("Test 4 Passed\n");
        } else {
            System.out.println("Test 4 Failed");
            writer.write("Test 4 Failed\n");
        }
        
        // Test 5 - print list contents
        list5 = new DoublyLinkedList();
        list5.add(10);
        list5.add(20);
        list5.add(30);
        if (getListContents(list5).equals("10 20 30 ")) {
            System.out.println("Test 5 Passed");
            writer.write("Test 5 Passed\n");
        } else {
            System.out.println("Test 5 Failed");
            writer.write("Test 5 Failed\n");
        }
        
        // Test 6 - delete the list and check contents
        list5.deleteList();
        if (getListContents(list5).equals("")) {
            System.out.println("Test 6 Passed");
            writer.write("Test 6 Passed\n");
        } else {
            System.out.println("Test 6 Failed");
            writer.write("Test 6 Failed\n");
        }
        
        // Test 7 - add and remove all nodes
        list5.add(10);
        list5.add(20);
        list5.add(30);
        list5.remove(10);
        list5.remove(20);
        list5.remove(30);
        if (getListContents(list5).equals("")) {
            System.out.println("Test 7 Passed");
            writer.write("Test 7 Passed\n");
        } else {
            System.out.println("Test 7 Failed");
            writer.write("Test 7 Failed\n");
        }
        
        // Test 8 - reverse an empty list
        list5.reverse();
        if (getListContents(list5).equals("")) {
            System.out.println("Test 8 Passed");
            writer.write("Test 8 Passed\n");
        } else {
            System.out.println("Test 8 Failed");
            writer.write("Test 8 Failed\n");
        }
        
        writer.close();
    }
    
    // Helper method to get list contents as a string
    private static String getListContents(DoublyLinkedList list) {
        StringBuilder sb = new StringBuilder();
        list.printToString(sb);
        return sb.toString();
    }
}