package DataStructures;

public class ReverseSingleLinkedList {
    private static Node doReverse(Node head) {
        Node current = head;
        Node previous = null;
        while (current != null) {
            Node next = current._next;
            current._next = previous;
            previous = current;
            current = next;
        }

        return previous; // previous ends up pointing to the last element in the list
    }

    public static void doAndPrint(Node head) {
        System.out.println("Reversing SingleLinkedList");
        LinkedList.print(head);

        Node reversed = doReverse(head);
        System.out.println("Reversed:");
        LinkedList.print(reversed);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head._next = new Node(2);
        head._next._next = new Node(3);
        head._next._next._next = new Node(4);
        head._next._next._next._next = new Node(5);

        doAndPrint(head);
    }
}
