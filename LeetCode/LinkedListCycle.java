package LeetCode;

import DataStructures.Node;

import java.util.HashMap;
import java.util.Map;

public class LinkedListCycle {
    public Node detectCycle(Node head) {
        Map<Node, Integer> nodePositionMap = new HashMap<>();
        Node current = head;
        int position = 0;
        while (current != null) {
            if (nodePositionMap.containsKey(current)) {
                return current;
            }
            nodePositionMap.put(current, position);
            current = current._next;
            position++;
        }

        return null;
    }

    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
    }

    private static void findAndPrint(Node head) {
        LinkedListCycle linkedListCycle = new LinkedListCycle();
        Node node = linkedListCycle.detectCycle(head);
        if (node != null) {
            System.out.println("Found cycle starts at Node with value: " + node.getData());
        } else {
            System.out.println("No cycle found!");
        }

    }

    private static void testCase1() {
        Node head = new Node(1);
        Node next = new Node(2);
        head._next = next;
        head._next._next = new Node(3);
        head._next._next._next = new Node(4);
        head._next._next._next._next = next;
        findAndPrint(head);
    }

    private static void testCase2() {
        Node head = new Node(1);
        head._next = new Node(2);
        head._next._next = head;
        findAndPrint(head);
    }

    private static void testCase3() {
        Node head = new Node(1);
        head._next = new Node(2);
        head._next._next = new Node(3);
        findAndPrint(head);
    }
}
