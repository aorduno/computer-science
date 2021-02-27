package LeetCode.hard;

import CTCI.LogUtils;

public class ReverseNodesInKGroup {
    public static void main(String[] args) {
        testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 4);
        testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 2);
        testCase(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 15);
        testCase(new int[]{1}, 1);
        testCase(new int[]{1, 2, 3, 4, 5}, 1);
    }

    private static void testCase(int[] nums, int k) {
        LogUtils.logMessage("[[ReverseNodesInKGroup]] Reversing nodes in k-group for given k: " + k, true);
        ListNode listNode = ListNode.createListG(nums);
        listNode.print();
        LogUtils.logMessage("", true);
        ListNode reversed = reverseInKGroup(listNode, k);

        LogUtils.logMessage("Reversed:", true);
        if (reversed == null) {
            listNode.print();
        } else {
            reversed.print();
        }
        LogUtils.logMessage("", true);
    }

    private static ListNode reverseInKGroup(ListNode head, int k) {
        int current = 1;
        ListNode startAt = head;
        ListNode lastFromPrevGroup = null;
        ListNode newHead = null;
        while (head != null) {
            if (current % k == 0) {
                // reverse
                if (newHead == null) {
                    newHead = head;
                }

                reverseGroup(startAt, head, lastFromPrevGroup, head.next);

                // update pointers
                lastFromPrevGroup = startAt;
                head = startAt;
                startAt = startAt.next;
            }

            head = head.next;
            current++;
        }

        return newHead;
    }

    private static void reverseGroup(ListNode start, ListNode end, ListNode lastFromPrevGroup, ListNode firstFromNextGroup) {
        ListNode current = start;
        ListNode prev = null;
        // reverse
        while (current != null) {
            ListNode temp = current.next;
            current.next = prev;
            prev = current;
            if (current == end) {
                break;
            }

            current = temp;
        }

        if (lastFromPrevGroup != null) {
            lastFromPrevGroup.next = end;
        }

        if (firstFromNextGroup != null) {
            start.next = firstFromNextGroup;
        }
    }
}
