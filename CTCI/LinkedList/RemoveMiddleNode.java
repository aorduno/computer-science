package CTCI.LinkedList;

import DataStructures.LinkedList;
import DataStructures.Node;

public class RemoveMiddleNode {
    public static void main(String[] args) {
        testCase(new int[]{1});
        testCase(new int[]{1, 2});
        testCase(new int[]{1, 2, 3, 4, 5});
        testCase(new int[]{1, 2, 3, 4, 5, 6});
    }

    private static void testCase(int[] numbers) {
        LinkedList linkedList = LinkedListUtils.createList(numbers);
        System.out.println("Removing middle node from list:");
        linkedList.print();
        doRemove(linkedList);
        System.out.println("After removing:");
        linkedList.print();
    }

    private static void doRemove(LinkedList linkedList) {
        Node fast = linkedList.getHead();
        Node slow = linkedList.getHead();
        if (fast._next == null || fast._next._next == null) {
            System.out.println("can not delete node in list with only 2 or less nodes");
            return;
        }

        while (true) {
            fast = fast._next._next;
            if (!hasValidNextMove(fast)) {
                break;
            }
            slow = slow._next;
        }

        slow._next = slow._next._next;
    }

    private static boolean hasValidNextMove(Node node) {
        return node._next != null && node._next._next != null;
    }
}
