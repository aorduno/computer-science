package LeetCode.medium;

import CTCI.LinkedList.LinkedListUtils;
import CTCI.LogUtils;
import DataStructures.LinkedListG;
import DataStructures.NodeG;

public class SwapNodesInPairs {
    public static void main(String[] args) {
        testCase(LinkedListUtils.createListG(new int[]{1, 2, 3, 4, 5}));
    }

    private static void testCase(LinkedListG list) {
        LogUtils.logMessage("[[SwapNodesInPairs]] Swapping nodes in pairs in given linked list", true);
        LinkedListUtils.print(list);

        NodeG head = swapInPairs(list);
        LinkedListG<Integer> swappedList = new LinkedListG<>();
        swappedList.setHead(head);
        LogUtils.logMessage("Swapped:", true);
        LinkedListUtils.print(swappedList);
    }

    private static NodeG swapInPairs(LinkedListG list) {
        NodeG current = list.getHead();
        NodeG previous = null;
        NodeG newHead = current != null && current._next != null ? current._next : current;
        while (current != null && current._next != null) {
            NodeG next = current._next;
            // swap
            swap(current, next);

            // update previous
            if (previous != null) {
                previous._next = next;
            }

            // move forward
            previous = current;
            current = current._next;
        }

        return newHead;
    }

    private static void swap(NodeG current, NodeG next) {
        NodeG temp = next._next;
        next._next = current;
        current._next = temp;
    }

}
