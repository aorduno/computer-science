package CTCI.LinkedList;

import DataStructures.LinkedList;
import DataStructures.Node;

public class SumLists {
    public static void main(String[] args) {
        // reverse
        testCase(new int[]{7, 1, 6}, new int[]{5, 9, 2}, true);
        testCase(new int[]{1, 4, 8}, new int[]{1, 0, 5}, true);
        testCase(new int[]{3, 1}, new int[]{7, 8, 9}, true);

        // non reverse
        testCase(new int[]{1, 2, 5}, new int[]{1, 2, 4}, false);
        testCase(new int[]{1, 5}, new int[]{1, 2, 8, 5}, false);
        testCase(new int[]{6, 6, 6}, new int[]{3, 3, 4}, false);
    }

    private static void testCase(int[] list1, int[] list2, boolean doReverse) {
        LinkedList linkedList1 = LinkedListUtils.createList(list1);
        System.out.println(doReverse ? "[[REVERSE]]" : "[NonREVERSE]]" + " The sum of");
        linkedList1.print();
        LinkedList linkedList2 = LinkedListUtils.createList(list2);
        System.out.println("And...");
        linkedList2.print();

        LinkedList sum = doReverse ? doSumReverse(linkedList1, linkedList2) : doSumNonReverse(linkedList1, linkedList2);

        System.out.println("is...");
        sum.print();
    }

    // this method sums two given lists holding the number in reverse order
    // meaning the less significant number are in list's head. i.e: 7 -> 1 -> 6, would be what humans recognize as 617
    // time complexity: o(n) where n = longest list given
    // space complexity: o(1) no extra space needed
    private static LinkedList doSumReverse(LinkedList linkedList1, LinkedList linkedList2) {
        Node currentList1 = linkedList1.getHead();
        Node currentList2 = linkedList2.getHead();
        LinkedList sum = new LinkedList();
        Node lastSumNode = null;
        boolean hasCarry = false;
        while (currentList1 != null || currentList2 != null) {
            int list1Value = getToSum(currentList1);
            int list2Value = getToSum(currentList2);
            int result = getSumResult(list1Value, list2Value, hasCarry);

            hasCarry = result >= 10;
            Node resultNode = getResultNode(result, hasCarry);
            if (sum.getHead() == null) {
                sum.setHead(resultNode);
                lastSumNode = resultNode;
            } else {
                lastSumNode._next = resultNode;
                lastSumNode = resultNode;
            }

            currentList1 = getNext(currentList1);
            currentList2 = getNext(currentList2);
        }

        if (hasCarry) {
            lastSumNode._next = new Node(1);
        }

        return sum;
    }

    // FOLLOW UP
    // similar concept, just doing sum of lists BUT this time the input is in human recognized format (non reverse)
    // i.e: 1 -> 2 -> 5 + 1 -> 2 -> 4 = 2 -> 4 -> 9
    private static LinkedList doSumNonReverse(LinkedList linkedList1, LinkedList linkedList2) {
        LinkedListUtils.reverseList(linkedList1);
        LinkedListUtils.reverseList(linkedList2);
        LinkedList reversedSum = doSumReverse(linkedList1, linkedList2);
        LinkedListUtils.reverseList(reversedSum);
        return reversedSum; // not reversed at this point
    }


    private static Node getNext(Node node) {
        return node != null ? node._next : null;
    }

    private static Node getResultNode(int result, boolean hasCarry) {
        int toAdd = hasCarry ? result - 10 : result;
        return new Node(toAdd);
    }

    private static int getSumResult(int list1Value, int list2Value, boolean hasCarry) {
        int result = hasCarry ? 1 : 0;
        return result + list1Value + list2Value;
    }

    private static int getToSum(Node node) {
        return node != null ? node.getData() : 0;
    }
}
