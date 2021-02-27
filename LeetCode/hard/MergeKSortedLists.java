package LeetCode.hard;

import CTCI.LogUtils;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLists {
    public static void main(String[] args) {
        ListNode[] listNodes = new ListNode[3];
        listNodes[0] = ListNode.createListG(new int[]{1, 4, 5});
        listNodes[1] = ListNode.createListG(new int[]{1, 3, 4});
        listNodes[2] = ListNode.createListG(new int[]{2, 6});
        testCase(listNodes);

        ListNode[] listNodes1 = new ListNode[1];
        listNodes1[0] = ListNode.createListG(new int[]{0, 2, 5});
        testCase(listNodes1);

        ListNode[] listNodes2 = new ListNode[2];
        listNodes2[0] = ListNode.createListG(new int[]{1});
        listNodes2[1] = ListNode.createListG(new int[]{0});
        testCase(listNodes2);
    }

    private static void testCase(ListNode[] lists) {
        LogUtils.logMessage("[[MergeKSortedLists]] Merging given sorted lists into one", true);
        for (ListNode list : lists) {
            print(list);
        }
        LogUtils.logMessage("", true);
        ListNode merged = doMergeOptimized(lists);
        LogUtils.logMessage("Result:", true);
        print(merged);
        LogUtils.logMessage("", true);
    }

    private static ListNode doMergeOptimized(ListNode[] lists) {
        Integer minVal = computeMin(lists);
        if (minVal == null) {
            return new ListNode();
        }

        while (lists.length > 1) {
            int size = lists.length;
            int current = 0;
            while (current < size - 1) {
                mergeLists(current, current + 1, lists);
                current += 2;
            }

            // remove all nullify...
            lists = compact(lists);
        }

        return lists[0];
    }

    private static ListNode[] compact(ListNode[] lists) {
        int newLength = (int) Math.ceil(((double) lists.length / 2));
        ListNode[] compacted = new ListNode[newLength];
        int y = 0;
        for (ListNode list : lists) {
            if (list == null) {
                continue;
            }

            compacted[y] = list;
            y++;
        }
        return compacted;
    }


    // merges b into a
    private static void mergeLists(int indexA, int indexB, ListNode[] lists) {
        ListNode a = lists[indexA];
        ListNode b = lists[indexB];
        ListNode mergedHead = new ListNode();
        ListNode current = mergedHead;
        while (a != null) {
            if (b != null && b.val < a.val) {
                current.next = new ListNode(b.val);
                current = current.next;
                b = b.next;
            } else {
                current.next = new ListNode(a.val);
                current = current.next;
                a = a.next;
            }
        }

        // more b to go...
        while (b != null) {
            current.next = new ListNode(b.val);
            current = current.next;

            b = b.next;
        }

        // remove b
        lists[indexB] = null;
        lists[indexA] = mergedHead.next;
    }

    private static ListNode doMergeWithHeap(ListNode[] lists) {
        ListNode mergedHead = new ListNode();
        ListNode merged = mergedHead;
        Integer minVal = computeMin(lists);
        if (minVal == null) {
            return merged.next;
        }

        Queue<ListNode> toProcess = createPriorityQueue(lists);
        while (!toProcess.isEmpty()) {
            ListNode node = toProcess.poll();
            int current = node.val;
            while (node != null && node.val == current) {
                merged.next = new ListNode(node.val);
                merged = merged.next;
                // move forward
                node = node.next;
            }

            // update
            if (node != null) {
                toProcess.add(node);

            }
        }

        return mergedHead.next;
    }

    private static Queue<ListNode> createPriorityQueue(ListNode[] lists) {
        Queue<ListNode> indexQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode listNode : lists) {
            if (listNode != null) {
                indexQueue.add(listNode);
            }
        }

        return indexQueue;
    }

    private static void print(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.val + ", ");
            listNode = listNode.next;
        }
    }

    private static ListNode merge(ListNode[] lists) {
        ListNode mergedHead = new ListNode();
        ListNode merged = mergedHead;
        Integer minVal = computeMin(lists);
        if (minVal == null) {
            return merged.next;
        }

        int toProcess = minVal;
        int nextValue = Integer.MAX_VALUE;
        boolean hasNext = true;
        while (hasNext) {
            hasNext = false;
            int currentList = 0;
            while (currentList < lists.length) {
                ListNode node = lists[currentList];
                while (node != null && node.val < toProcess) {
                    node = node.next;
                }

                while (node != null && node.val == toProcess) {
                    merged.next = new ListNode(node.val);
                    merged = merged.next;
                    // update
                    node = node.next;
                }

                if (node != null) {
                    hasNext = true;
                    nextValue = Math.min(nextValue, node.val);
                    lists[currentList] = node;
                } else {
                    lists[currentList] = null;
                }

                currentList++;
            }

            toProcess = nextValue;
            nextValue = Integer.MAX_VALUE;
        }

        return mergedHead.next;
    }

    private static Integer computeMin(ListNode[] lists) {
        Integer min = null;
        for (ListNode list : lists) { // get starting point...
            if (min == null) {
                min = list.val;
                continue;
            }

            min = Math.min(list.val, min);
        }

        return min;
    }
}
