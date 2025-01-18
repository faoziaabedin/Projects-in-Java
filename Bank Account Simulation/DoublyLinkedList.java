public class DoublyLinkedList {
    private Node head;
    private Node tail; 

     // Method to add a new node with the given value to the end of the list
     public void add(int value) {
        Node newnode = new Node(value);

        if (head == null) {
            // If the list is empty, the new node becomes both head and tail
            head = newnode;
            tail = newnode;
        } else {
            // Link the new node as the next node of the currentnode tail
            tail.next = newnode;
            newnode.prev = tail;
            // Update the tail to the new node
            tail = newnode;
        }
    }

    // Method to remove the first node with the specified value
    public boolean remove(int value) {
        Node currentnode = head;

        // Traverse the list to find the node with the specified value
        while (currentnode != null) {
            if (currentnode.value == value) {
                if (currentnode.prev != null) {
                    currentnode.prev.next = currentnode.next;
                } else {
                    head = currentnode.next;
                }

                if (currentnode.next != null) {
                    currentnode.next.prev = currentnode.prev;
                } else {
                    tail = currentnode.prev;
                }

                return true; // Node found and removed
            }
            currentnode = currentnode.next;
        }
        return false; // Node not found
    }

    // Method to reverse the order of the nodes in the list
    public void reverse() {
        Node currentnode = head;
        Node temp = null;

        while (currentnode != null) {
            // Swap the next and prev pointers of each node
            temp = currentnode.prev;
            currentnode.prev = currentnode.next;
            currentnode.next = temp;

            // Move to the next node (which is the previous node due to swapping)
            currentnode = currentnode.prev;
        }

        // Swap the head and tail pointers
        if (temp != null) {
            head = temp.prev;
        }
    }

    // Method to print the list from head to tail
    public void print() {
        Node currentnode = head;
        while (currentnode != null) {
            System.out.print(currentnode.value + " ");
            currentnode = currentnode.next;
        }
        System.out.println();
    }

    // Method to delete the entire list
    public void deleteList() {
        head = null;
        tail = null;
    }

    
    // helper method to print to a StringBuilder (for testing purposes)
    public void printToString(StringBuilder sb) { Node currentnodeNode = head;
        while (currentnodeNode != null) {
        sb.append(currentnodeNode.value).append(" ");
                    currentnodeNode = currentnodeNode.next;
                }
        }
}
