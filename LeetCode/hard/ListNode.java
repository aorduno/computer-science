package LeetCode.hard;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode createListG(int[] ints) {
        ListNode head = new ListNode();
        ListNode node = head;
        for (int current : ints) {
            node.next = new ListNode(current);
            node = node.next;
        }

        return head.next;
    }

    public void print() {
        ListNode head = this;
        while (head != null) {
            System.out.print(head.val + ", ");
            head = head.next;
        }
    }
}
