package CTCI.LinkedList;

import DataStructures.LinkedListG;
import DataStructures.NodeG;

import java.util.HashMap;
import java.util.Map;

public class LoopDetection {
    public static void main(String[] args) {
        for (int x = 0; x < 9; x++) {
            testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, x, false);
        }

        testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, -1, false);

        for (int x = 0; x < 9; x++) {
            testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, x, true);
        }

        testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, -1, true);
    }

    private static void testCase(int[] numbers, int loopAt, boolean noExtraSpace) {
        LinkedListG listG = LinkedListUtils.createListG(numbers);
        System.out.println(noExtraSpace ? "[[NoExtraSpace]]" : "[[ExtraSpace]]" + "Finding loop in list...");
        listG.print();

        if (loopAt != -1) {
            System.out.println("Inserting loop at node #" + (loopAt + 1));
            NodeG tail = LinkedListUtils.getTail(listG);
            NodeG nodeAt = LinkedListUtils.getNodeAt(listG, loopAt);
            // loop
            tail._next = nodeAt;
        } else {
            System.out.println("Note -- No loop inserted");
        }

        NodeG loopNode = noExtraSpace ? findLoopNodeNoExtraSpace(listG) : findLoopNodeExtraSpace(listG);
        if (loopNode == null) {
            System.out.println("Note -- No loop detected");
        } else {
            System.out.println("Loop found! starting at node: " + loopNode + " with data: " + loopNode.getData());
        }
    }

    // time o(n)
    // space o(n)
    private static NodeG findLoopNodeExtraSpace(LinkedListG list) {
        Map<NodeG, NodeG> nodeMap = new HashMap<>();

        NodeG head = list.getHead();
        while (head != null) {
            if (nodeMap.containsKey(head)) {
                return head;
            }

            nodeMap.put(head, head);
            head = head._next;
        }

        return null;
    }

    // time o(n)
    // space o(1)
    // there's a very good explanation for this in https://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/
    private static NodeG findLoopNodeNoExtraSpace(LinkedListG list) {
        NodeG slow = list.getHead();
        NodeG fast = list.getHead();
        while (fast != null && fast._next != null) {
            slow = slow._next;
            fast = fast._next._next;
            if (slow.equals(fast)) {
                break;
            }
        }

        slow = list.getHead();
        while (fast != null && !fast.equals(slow)) {
            slow = slow._next;
            fast = fast._next;
        }

        return fast;
    }
}
