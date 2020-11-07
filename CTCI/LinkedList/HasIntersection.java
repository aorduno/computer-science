package CTCI.LinkedList;

import DataStructures.LinkedListG;
import DataStructures.NodeG;

import java.util.HashMap;
import java.util.Map;

public class HasIntersection {
    public static void main(String[] args) {
        testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, new int[]{1, 2}, 6, false);
        testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, new int[]{1}, 8, false);
        testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, new int[]{1}, 3, false);
        testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, new int[]{1}, -1, false);

        testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, new int[]{1, 2}, 6, true);
        testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, new int[]{1}, 8, true);
        testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, new int[]{1}, 3, true);
        testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, new int[]{1}, -1, true);
    }

    private static void testCase(int[] numbers1, int[] numbers2, int intersectAt, boolean noExtraSpace) {
        LinkedListG list1 = LinkedListUtils.createListG(numbers1);
        LinkedListG list2 = LinkedListUtils.createListG(numbers2);

        if (intersectAt != -1) {
            NodeG node = getIntersectionNode(intersectAt, list1);
            createIntersection(list2, node);
        }

        System.out.println(noExtraSpace ? "[[NOEXTRASPACE]]" : "[[EXTRASPACE]]" + "Finding intersection for lists...");
        list1.print();
        list2.print();
        NodeG intersection = noExtraSpace ? findIntersectionNoSpace(list1, list2) : findIntersectionExtraSpace(list1, list2);
        if (intersection == null) {
            System.out.println("No intersection found");
        } else {
            System.out.println("Intersection node is: " + intersection + " with value: " + intersection.getData());
        }
    }

    private static NodeG findIntersectionExtraSpace(LinkedListG list1, LinkedListG list2) {
        Map<NodeG, NodeG> visited = new HashMap<>();
        NodeG list1Node = list1.getHead();
        while (list1Node != null) {
            visited.put(list1Node, list1Node);
            list1Node = list1Node._next;
        }


        NodeG list2Node = list2.getHead();
        while (list2Node != null) {
            if (visited.containsKey(list2Node)) {
                return list2Node;
            }

            list2Node = list2Node._next;
        }

        // not intersection
        return null;
    }

    private static NodeG findIntersectionNoSpace(LinkedListG list1, LinkedListG list2) {
        NodeG list1Tail = LinkedListUtils.getTail(list1);
        NodeG list2Tail = LinkedListUtils.getTail(list2);
        if (!list1Tail.equals(list2Tail)) {
            return null;
        }

        int list1Size = getSize(list1);
        int list2Size = getSize(list2);
        int sizeDiff = list1Size - list2Size;
        boolean shouldAdvanceList1 = sizeDiff > 0;
        NodeG advancedNode;
        NodeG otherListNode;
        if (shouldAdvanceList1) {
            advancedNode = LinkedListUtils.getNodeAt(list1, sizeDiff);
            otherListNode = list2.getHead();
        } else {
            advancedNode = LinkedListUtils.getNodeAt(list2, sizeDiff);
            otherListNode = list1.getHead();
        }

        while (advancedNode != otherListNode) {
            advancedNode = advancedNode._next;
            otherListNode = otherListNode._next;
        }

        return advancedNode;
    }

    private static int getSize(LinkedListG list1) {
        int size = 0;
        NodeG head = list1.getHead();
        while (head != null) {
            size++;
            head = head._next;
        }

        return size;
    }

    private static void createIntersection(LinkedListG list, NodeG node) {
        NodeG head = LinkedListUtils.getTail(list);
        head._next = node;
    }

    private static NodeG getIntersectionNode(int intersectAt, LinkedListG list) {
        NodeG node = list.getHead();
        int current = 1;
        while (current < intersectAt) {
            node = node._next;
            current++;
        }
        return node;
    }
}
