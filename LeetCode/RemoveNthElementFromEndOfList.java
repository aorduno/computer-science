package LeetCode;

import DataStructures.Node;

class Solution {
    // two passes
//    public Node removeNthFromEnd(Node head, int n) {
//        return removeNthFromEndBrute(head, n);
//    }

    // one pass
    public Node removeNthFromEnd(Node head, int n) {
        return removeNthFromEndBetter(head, n);
    }

    // 1) two pointers with n difference
    // 2) move forward pointer 1 by n positions
    // 3) move forward both pointers until pointer 1 has next
    // 4) remove from p2
    private Node removeNthFromEndBetter(Node head, int n) {
        Node p1 = head;
        Node p2 = head;
        int current = 1;
        while (p1._next != null && current < n) {
            p1 = p1._next;
            current++;
        }

        while (p1._next != null) {
            p1 = p1._next;
            if (p1._next != null) {
                p2 = p2._next;
            }
            current++;
        }

        if (current - n == 0) {
            return p2._next;
        }

        p2._next = p2._next._next;
        return head;
    }

    // 1) get Length
    // 2) diff between _length and n
    // 3) loop again until we reached that diff
    // 4) remove
    public Node removeNthFromEndBrute(Node head, int n) {
        int length = getLength(head);
        if (length == 0 || length < n) {
            return head;
        }

        int toDelete = length - n;
        if (toDelete == 0) {
            if (head._next == null) {
                return null;
            }

            return head._next;
        }

        Node p = head;
        int current = 1;
        while (p._next != null && current < toDelete) {
            p = p._next;
            current++;
        }

        p._next = p._next._next;
        return head;
    }

    int getLength(Node head) {
        if (head == null) {
            return 0;
        }

        Node p = head;
        int length = 1;
        while (p._next != null) {
            p = p._next;
            length++;
        }

        return length;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        Node head = new Node(10);

        Node node = solution.removeNthFromEnd(head, 1);
        System.out.println("done");

        head = new Node(5);
        head._next = new Node(10);
        head._next._next = new Node(15);

        node = solution.removeNthFromEnd(head, 3);
        System.out.println("done");

        head = new Node(1);
        head._next = new Node(2);
        head._next._next = new Node(3);
        head._next._next._next = new Node(4);
        head._next._next._next._next = new Node(5);

        node = solution.removeNthFromEnd(head, 2);
        System.out.println("done");

        head = new Node(1);
        head._next = new Node(2);

        node = solution.removeNthFromEnd(head, 2);
        System.out.println("done");
    }
}