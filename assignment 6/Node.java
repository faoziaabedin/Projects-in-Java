public class Node{
    public int value;
    public Node next;
    public Node prev; 
    
    // Constructor to initialize the node with a value
    public Node(int value){
        this.value= value; 
        this.next = null;
        this.prev = null; 
    }
}