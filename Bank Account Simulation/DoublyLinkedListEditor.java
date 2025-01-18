import java.util.Scanner;

public class DoublyLinkedListEditor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoublyLinkedList list = new DoublyLinkedList();

        while (true) {
            System.out.println("Choose an operation:");
            System.out.println("1. Add a node");
            System.out.println("2. Remove a node");
            System.out.println("3. Reverse the list");
            System.out.println("4. Print the contents");
            System.out.println("5. Delete the linked list and start over");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to add: ");
                    int addValue = scanner.nextInt();
                    list.add(addValue);
                    break;
                case 2:
                    System.out.print("Enter value to remove: ");
                    int removeValue = scanner.nextInt();
                    if (list.remove(removeValue)) {
                        System.out.println("Node removed.");
                    } else {
                        System.out.println("Node not found.");
                    }
                    break;
                case 3:
                    list.reverse();
                    System.out.println("List reversed.");
                    break;
                case 4:
                    System.out.println("List contents:");
                    list.print();
                    break;
                case 5:
                    list.deleteList();
                    System.out.println("List deleted. You can start over.");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}