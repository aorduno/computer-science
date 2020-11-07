package CTCI.LinkedList;

import DataStructures.LinkedList;
import DataStructures.LinkedListG;
import DataStructures.Node;
import DataStructures.NodeG;

public class LinkedListUtils {

    static LinkedListG createList(String word) {
        LinkedListG linkedList = new LinkedListG<>();
        for (char character : word.toCharArray()) {
            linkedList.append(String.valueOf(character));
        }

        return linkedList;
    }

    static LinkedList createList(int[] numbers) {
        LinkedList linkedList = new LinkedList();
        for (int number : numbers) {
            linkedList.append(number);
        }

        return linkedList;
    }

    static LinkedListG createListG(int[] numbers) {
        LinkedListG linkedList = new LinkedListG<>();
        for (int number : numbers) {
            linkedList.append(number);
        }

        return linkedList;
    }

    static void reverseList(LinkedList linkedList) {
        Node current = linkedList.getHead();
        Node previous = null;
        while (current != null) {
            Node tmp = current._next;
            current._next = previous;
            previous = current;
            current = tmp;
        }

        linkedList.setHead(previous);
    }

    static void reverseList(LinkedListG linkedList) {
        NodeG current = linkedList.getHead();
        NodeG previous = null;
        while (current != null) {
            NodeG tmp = current._next;
            current._next = previous;
            previous = current;
            current = tmp;
        }

        linkedList.setHead(previous);
    }

    static NodeG getTail(LinkedListG list) {
        NodeG head = list.getHead();
        while (head != null && head._next != null) {
            head = head._next;
        }
        return head;
    }

    static NodeG getNodeAt(LinkedListG list, int steps) {
        NodeG head = list.getHead();
        while (steps > 0) {
            head = head._next;
            steps--;
        }
        return head;
    }

    public static void print(LinkedListG listG) {
        NodeG head = listG.getHead();
        System.out.println("Printing list...");
        while (head != null) {
            System.out.print(head.getData() + " ");
            head = head._next;
        }
        System.out.println();
    }
}
